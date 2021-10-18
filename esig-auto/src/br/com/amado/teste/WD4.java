package br.com.amado.teste;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WD4 {
	
	private static String escola = "ESCOLA TREINAMENTO LACERDA";
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		
		
		
		System.setProperty("webdriver.edge.driver","C:\\WebDriver\\edgedriver_win64\\msedgedriver.exe");
		
		WebDriver driver = new EdgeDriver();
		WebDriverWait myWaitVar = new WebDriverWait(driver, Duration.ofSeconds(10));
		
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
		
//		WebElement btnSelecionaMenuEstudantes = driver.findElement(By.xpath("//*[@id=\"menu_escola_j_id_jsp_1733266339_241_menu\"]/table/tbody/tr/td[5]"));
//		btnSelecionaMenuEstudantes.click();
//		sleepFewMoments(1);
//		WebElement btnCadastroNovoEstudanteAnoLetivo = driver.findElement(By.id("cadastroNovoEstudanteAnoLetivo"));
//		btnCadastroNovoEstudanteAnoLetivo.click();
//		sleepFewMoments(1);
//		WebElement btnCadastroAnoLetivoAtual = driver.findElement(By.id("cadastroNovoEstudanteAnoLetivoAtual"));
//		btnCadastroAnoLetivoAtual.click();
//		sleepFewMoments(1);
		
		WebElement btnSelecionaMenuEstudantes = driver.findElement(By.xpath("//*[@id=\"menu_escola_j_id_jsp_1733266339_241_menu\"]/table/tbody/tr/td[5]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(btnSelecionaMenuEstudantes);
				
		WebElement btnSubMenuEstudantes = driver.findElement(By.xpath("//*[@id=\"cmSubMenuID14\"]/table/tbody/tr[1]/td[2]"));
		actions.moveToElement(btnSubMenuEstudantes);		
		
		actions.click().build().perform();
		
		sleepFewMoments(2);
		WebElement btnCadastroAnoLetivoAtual = driver.findElement(By.xpath("//*[@id=\"cmSubMenuID15\"]/table/tbody/tr[1]/td[2]"));
		btnCadastroAnoLetivoAtual.click();
		
		sleepFewMoments(2);
		driver.close();
		System.exit(1);
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
	
}
