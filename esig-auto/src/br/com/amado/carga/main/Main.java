package br.com.amado.carga.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.amado.carga.dto.CadastroAlunoDTO;
import br.com.amado.carga.model.COMPONENTES_ELETIVOS;
import br.com.amado.carga.model.ESCOLAS;
import br.com.amado.carga.model.VAGAS;
import br.com.amado.carga.repository.DadosEstudantesRepository;

public class Main {
	
	private static String escola;	
	private static String vaga;
	private static String primeiroComponenteEletivo;
	private static String segundoComponenteEletivo;	
	private static String arquivoBaseInput;
	private static int iterator = 1;
	private static Boolean isNovoEnsinoMedio = false;
	private static Boolean isAlunoPCD = false;
	
	 //Mapeamento para Cadastro de Estudante
	static WebElement cpfAluno ;
	static WebElement nomeAluno ;
	static WebElement dataNascimento ;
	static WebElement nomeMae ;
	static WebElement numeroRG ;
	static Select drpUF ;
	static WebElement orgaoExpedicao ;
	static WebElement dataExpedicao ;
	static WebElement btnSubmitFormularioEstudante1;
	static WebElement btnSubmitFormularioEstudante2;
	static WebElement btnSubmitFormularioEstudante3;
	static Select drpVaga;
	static WebElement radio;
	
	
	
	public static void main(String[] args) throws Exception {		
				
		limpaConsole();
		inputPathFile();	
		
		limpaConsole();
		inputInfoEscola();
		
		limpaConsole();
		inputInfoAlunoIsPCD();
		
		limpaConsole();
		inputInfoVaga();		
		limpaConsole();
		
		if(isNovoEnsinoMedio) {
			inputComponents();			
		}
		limpaConsole();
		
		System.setProperty("webdriver.edge.driver","C:\\WebDriver\\edgedriver_win64\\msedgedriver.exe");
		
		WebDriver driver = new EdgeDriver();
		WebDriverWait myWaitVar = new WebDriverWait(driver, Duration.ofSeconds(10));
		DadosEstudantesRepository dadosEstudantesRepository = new DadosEstudantesRepository();
		
		
		String baseUrl = "https://treinamento-sigeduc-ba.esig.com.br/sigeduc/verTelaLogin.do";		
//		String baseUrl = "http://localhost:8080/sigeduc";		
		
		driver.get(baseUrl);
		
		WebElement username = driver.findElement(By.name("user.login"));
		WebElement password = driver.findElement(By.name("user.senha"));
		WebElement btnLogin = driver.findElement(By.xpath("//*[@id=\"corpo\"]/table[1]/tbody/tr/td[2]/table[1]/tbody/tr[2]/td/form/table/tbody[2]/tr/td/input"));
		
		
		username.sendKeys("admin");		
		password.sendKeys("admin");
		btnLogin.click();
				
		WebElement selectSEC = driver.findElement(By.xpath("//*[@id=\"j_id_jsp_765123912_1\"]/table/tbody/tr/td/table/tbody/tr[2]/td[4]/a"));
		selectSEC.click();
		
		WebElement selectADM = driver.findElement(By.xpath("//*[@id=\"modulos\"]/ul[1]/li[9]/a"));
		selectADM.click();
				
		WebElement selectPortal = driver.findElement(By.xpath("//*[@id=\"administracao\"]/ul[6]/li/ul/li[2]/a"));
		selectPortal.click();
				
		WebElement inputFindEscolaPortal = driver.findElement(By.xpath("//*[@id=\"busca:unidade\"]"));
		inputFindEscolaPortal.sendKeys(escola);		
		sleepFewMoments(2);	
		inputFindEscolaPortal.sendKeys(Keys.ENTER);		
		WebElement btnSelecionarEscola = driver.findElement(By.xpath("//*[@id=\"busca:busca\"]"));
		btnSelecionarEscola.click();
		sleepFewMoments(2);		
		
		List<CadastroAlunoDTO> listaAlunos = new ArrayList<CadastroAlunoDTO>();		
		listaAlunos = dadosEstudantesRepository.getAllAlunos(arquivoBaseInput);	
		int tamanhoLista = listaAlunos.size();
		listaAlunos.forEach(aluno -> {
			WebElement btnSelecionaMenuEstudantes = driver.findElement(By.xpath("//*[@id=\"menu_escola_j_id_jsp_1733266339_238_menu\"]/table/tbody/tr/td[5]"));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnSelecionaMenuEstudantes);
					
			WebElement btnSubMenuEstudantes = driver.findElement(By.xpath("//*[@id=\"cmSubMenuID13\"]/table/tbody/tr[1]/td[2]"));
			actions.moveToElement(btnSubMenuEstudantes);		
			
			actions.click().build().perform();
			
			sleepFewMoments(3);
			WebElement btnCadastroAnoLetivoAtual = driver.findElement(By.xpath("//*[@id=\"cmSubMenuID14\"]/table/tbody/tr[1]/td[1]"));
			btnCadastroAnoLetivoAtual.click();
			sleepFewMoments(2);
			cadastrarFormularioEstudante(driver, aluno);
			System.out.println("Posição : " + iterator + " de " + tamanhoLista);
			iterator++;
		});			
		
		sleepFewMoments(2);
		driver.close();
		System.exit(1);
	}
	
	private static void inputInfoAlunoIsPCD() {
		
		System.out.println("Você deseja cadastrar estes alunos como PCD?");
		System.out.println("1\t-\tSIM");
		System.out.println("2\t-\tNAO\n");
		
		Scanner in = new Scanner(System.in);
		System.out.print("Digito: ");
		int valueInput = in.nextInt();
		
		atribuiEscolhaPCD(valueInput);
	}

	private static void atribuiEscolhaPCD(int valueInput) {

		if(valueInput == 1) {
			isAlunoPCD = true;
		}
		
	}

	private static void inputInfoEscola() throws Exception {
		
		System.out.println("Digite o número da escola correspondente: ");
		System.out.println("1\t-\t" + ESCOLAS.ESCOLA_LACERDA.label);
		System.out.println("2\t-\t" + ESCOLAS.ESCOLA_CABRAL.label);
		System.out.println("3\t-\t" + ESCOLAS.ESCOLA_OLIVEIRA.label);
		System.out.println("4\t-\t" + ESCOLAS.ESCOLA_ILE_AIYE.label);
		
		Scanner in = new Scanner(System.in);
		System.out.print("Digito escola: ");
		int intEscola = in.nextInt();
		
		validaEscolaDigitada(intEscola);
		atribuirEscola(intEscola);
	}

	private static void atribuirEscola(int intEscola) {
		
		switch (intEscola) {
		case 1:
			escola = ESCOLAS.ESCOLA_LACERDA.label;
			break;
		case 2:
			escola = ESCOLAS.ESCOLA_CABRAL.label;
			break;	
		case 3:
			escola = ESCOLAS.ESCOLA_OLIVEIRA.label;
			break;
		case 4:
			escola = ESCOLAS.ESCOLA_ILE_AIYE.label;
			break;	
		default:
			break;
		}
		
	}

	private static void validaEscolaDigitada(int intEscola) throws Exception {
		if(intEscola > 4 || intEscola > 4) {
			throw new Exception("O valor digitado não condiz com as opções apresentadas. Rode novamente o programa e digite o número correto correspondente a escola escolhida.");
		}		
	}

	private static void inputComponents() throws Exception {
		
		System.out.println("Digite o número correspondente para o primeiro e o segundo componente por favor: ");
		System.out.println("1\t-\t" + COMPONENTES_ELETIVOS.ARTES_GRAFICAS.label);
		System.out.println("2\t-\t" + COMPONENTES_ELETIVOS.AUDIOVISUAL.label);
		System.out.println("3\t-\t" + COMPONENTES_ELETIVOS.COMPONENTE_TESTE.label);
		System.out.println("4\t-\t" + COMPONENTES_ELETIVOS.INFORMATICA.label);
		System.out.println("5\t-\t" + COMPONENTES_ELETIVOS.OFICINA_LEITURA.label);
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Primeiro componente: ");
		int valuePrimeiroComponente = in.nextInt();
		System.out.print("\nSegundo componente: ");
		int valueSegundoComponente = in.nextInt();
		
		validaNumeroDigitado(valuePrimeiroComponente, valueSegundoComponente);
		atribuirComponente(valuePrimeiroComponente, 1);
		atribuirComponente(valueSegundoComponente, 2);
		
	}

	private static void validaNumeroDigitado(int valuePrimeiroComponente, int valueSegundoComponente) throws Exception {
		if(valuePrimeiroComponente > 5 || valueSegundoComponente > 5) {
			throw new Exception("O valor digitado não condiz com as opções apresentadas. Rode novamente o programa e digite o número correto correspondente ao componente escolhido.");
		}
		
	}

	private static void atribuirComponente(int valueComponente, int posicaoComponente) {
		
		switch (valueComponente) {
		case 1:
			if(posicaoComponente == 1) {
				primeiroComponenteEletivo = COMPONENTES_ELETIVOS.ARTES_GRAFICAS.label;
			}else {
				segundoComponenteEletivo =  COMPONENTES_ELETIVOS.ARTES_GRAFICAS.label;
			}
			break;
		case 2:
			if(posicaoComponente == 1) {
				primeiroComponenteEletivo = COMPONENTES_ELETIVOS.AUDIOVISUAL.label;
			}else {
				segundoComponenteEletivo =  COMPONENTES_ELETIVOS.AUDIOVISUAL.label;
			}
			break;
		case 3:
			if(posicaoComponente == 1) {
				primeiroComponenteEletivo = COMPONENTES_ELETIVOS.COMPONENTE_TESTE.label;
			}else {
				segundoComponenteEletivo =  COMPONENTES_ELETIVOS.COMPONENTE_TESTE.label;
			}
			break;	
		case 4:
			if(posicaoComponente == 1) {
				primeiroComponenteEletivo = COMPONENTES_ELETIVOS.INFORMATICA.label;
			}else {
				segundoComponenteEletivo =  COMPONENTES_ELETIVOS.INFORMATICA.label;
			}
			break;	
		case 5:
			if(posicaoComponente == 1) {
				primeiroComponenteEletivo = COMPONENTES_ELETIVOS.OFICINA_LEITURA.label;
			}else {
				segundoComponenteEletivo =  COMPONENTES_ELETIVOS.OFICINA_LEITURA.label;
			}
			break;	
		default:
			break;
		}
		
	}

	private static void inputInfoVaga() {
		System.out.println("A carga a ser realizada é para o novo ensino medio ou ensino fundamental? Digite o número correspondente e aperte enter: "
				+ "\n1\t-\tNovo Ensino Médio"
				+ "\n2\t-\tEnsino Fundamental\n");
		
		Scanner in = new Scanner(System.in);
		int valueInput = in.nextInt();		
		atribuirVaga(valueInput);
		verificaIsNovoEnsinoMedio(valueInput);
	}

	private static void atribuirVaga(int valueInput) {
		switch (valueInput) {
		case 1:
			vaga = VAGAS.NOVO_ENSINO_MEDIO_1S_MATUTINO.label;
			break;
		case 2:
			vaga = VAGAS.ENSINO_FUNDAMENTAL_ANOS_FINAIS_9ANO_MATUTINO.label;
			break;	
		default:
			break;
		}
		
	}

	private static void inputPathFile() throws IOException {
		System.out.println("Antes de prosseguir, favor digitar o diretorio do arquivo base: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String caminhoInput = reader.readLine();
		File file = new File(caminhoInput);
		if(file.exists()) {
			arquivoBaseInput = caminhoInput;
		} else {
			throw new IOException("O caminho digitado não é válido. Caminho : " + caminhoInput); 
		}		
	}

	private static void verificaIsNovoEnsinoMedio(int valueInput) {		
		if(valueInput == 1) {
			isNovoEnsinoMedio = true;
		}		
	}

	public static void sleepFewMoments(int seconds) {
		long timeSleep = seconds * 1000;
		
		try {
			Thread.sleep(timeSleep);
		} catch (InterruptedException e) {
			System.out.println("Erro ocorreu no momento de aguardar utilizando a function sleepFewMoments...");
			e.printStackTrace();
		}	
	}
	
	public static void cadastrarFormularioEstudante(WebDriver driver, CadastroAlunoDTO aluno) {
		initializeObjsFormularioEstudante(driver);
		cpfAluno.sendKeys(aluno.getCpfEstudante());
		nomeAluno.sendKeys(aluno.getNomeEstudante());
		dataNascimento.sendKeys(aluno.getDataNascimento());
		nomeMae.sendKeys(aluno.getNomeMae());
		numeroRG.sendKeys(aluno.getRG());	
		drpUF.selectByVisibleText("BA");
		orgaoExpedicao.sendKeys(aluno.getOrgaoExpedicao());
		dataExpedicao.sendKeys(aluno.getDataExpedicao());
		
		if(isAlunoPCD) {
			checkOptionsPCD(driver);
		}
		
		if(aluno.getSexoEstudante().toLowerCase() == "feminino") {
			radio = driver.findElement(By.xpath("//*[@id=\"form:sexo:1\"]"));
			radio.click();
		}
		
		btnSubmitFormularioEstudante1.click();
		sleepFewMoments(3);
		
		drpVaga = new Select(driver.findElement(By.xpath("//*[@id=\"form:comboVagas\"]")));
		drpVaga.selectByVisibleText(vaga);
		sleepFewMoments(2);
		if(isNovoEnsinoMedio) {
			sleepFewMoments(2);
			Select drpFirstComponent = new Select(driver.findElement(By.xpath("//*[@id=\"form:comboEscolaTemaOpcoes\"]")));
			Select drpSecondComponent = new Select(driver.findElement(By.xpath("//*[@id=\"form:comboEscolaTemaOpcoes2\"]")));
			
			drpFirstComponent.selectByVisibleText(primeiroComponenteEletivo);
			sleepFewMoments(2);
			drpSecondComponent.selectByVisibleText(segundoComponenteEletivo);						
		}
		
		sleepFewMoments(3);
		
		btnSubmitFormularioEstudante2 = driver.findElement(By.xpath("//*[@id=\"form:Proximo\"]"));		
		btnSubmitFormularioEstudante2.click();
		
		btnSubmitFormularioEstudante3 = driver.findElement(By.xpath("//*[@id=\"formResumo:Confirmar\"]"));
		btnSubmitFormularioEstudante3.click();
		
		WebElement btnPaginaPrincipalPGE = driver.findElement(By.xpath("//*[@id=\"conteudo\"]/h2/a"));
		btnPaginaPrincipalPGE.click();
		sleepFewMoments(4);		
	}

//	private static void checkOnlyEducacaoEspecial(WebDriver driver) {
//		WebElement radioOnlyEducacaoEspecial = driver.findElement(By.xpath("//*[@id=\"form:apenasEducacaoEspecial:0\"]"));
//		radioOnlyEducacaoEspecial.click();
//	}

	private static void checkOptionsPCD(WebDriver driver) {
		WebElement checkBoxDeficienciaFisica = driver.findElement(By.xpath("//*[@id=\"form:boxNecessidadeEducacionalEspecial\"]/tbody/tr[2]/td[1]/label/input"));
		WebElement checkBoxSurdez = driver.findElement(By.xpath("//*[@id=\"form:boxNecessidadeEducacionalEspecial\"]/tbody/tr[3]/td[3]/label/input"));
		WebElement checkBoxLeituraLabial = driver.findElement(By.xpath("//*[@id=\"form:boxRecursoNecessidadeEducacionalEspecial\"]/tbody/tr[2]/td[1]/label/input")); 
		
		checkBoxDeficienciaFisica.click();
		checkBoxSurdez.click();
		checkBoxLeituraLabial.click();		
	}

	private static void initializeObjsFormularioEstudante(WebDriver driver) {
		cpfAluno = driver.findElement(By.xpath("//*[@id=\"form:txtCPF\"]"));
		nomeAluno = driver.findElement(By.xpath("//*[@id=\"form:nomeOficial\"]"));
		dataNascimento = driver.findElement(By.xpath("//*[@id=\"form:Nascimento\"]"));
		nomeMae = driver.findElement(By.xpath("//*[@id=\"form:mae\"]"));
		numeroRG = driver.findElement(By.xpath("//*[@id=\"form:rg\"]"));
		drpUF = new Select(driver.findElement(By.xpath("//*[@id=\"form:ufRG\"]")));
		orgaoExpedicao = driver.findElement(By.xpath("//*[@id=\"form:orgaoExpedicao\"]"));
		dataExpedicao = driver.findElement(By.xpath("//*[@id=\"form:Expedicao\"]"));
		btnSubmitFormularioEstudante1 = driver.findElement(By.xpath("//*[@id=\"form:submetera\"]"));		
	}
	
	private static void limpaConsole() throws InterruptedException, IOException {	    
		
		 final int PAGE_SIZE = 100;
		 
		 for (int i = 0; i < PAGE_SIZE; i++) {
		        System.out.println();
		    }
	}
}
