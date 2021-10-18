package br.com.amado.carga.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.com.amado.carga.dto.CadastroAlunoDTO;

public class DadosEstudantesRepository {

	private static List<CadastroAlunoDTO> listaAlunos = new ArrayList<CadastroAlunoDTO>();
	private static JSONParser jsonParser = new JSONParser();
		
	@SuppressWarnings({ "unchecked" })
	public List<CadastroAlunoDTO> getAllAlunos(String caminhoArquivo){
		
		try (FileReader reader = new FileReader(caminhoArquivo))
		{
			Object obj = jsonParser.parse(reader);
			
			JSONArray employeeList = (JSONArray) obj;
			System.out.println(employeeList);
						
			employeeList.forEach( estudante -> {
				CadastroAlunoDTO aluno = parseEstudanteToCadastroAlunoDTO( (JSONObject) estudante ); 
				listaAlunos.add(aluno);
			});
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		
		//listaAlunos.forEach(e -> System.out.println(e));
		return listaAlunos;
	}
	
	private static CadastroAlunoDTO parseEstudanteToCadastroAlunoDTO(JSONObject estudante) 
    {		
		CadastroAlunoDTO modeloCadastro = new CadastroAlunoDTO();
         
		modeloCadastro.setNomeEstudante((String) estudante.get("nome"));
		modeloCadastro.setDataNascimento((String) estudante.get("data_nasc"));
		modeloCadastro.setCpfEstudante((String) estudante.get("cpf"));		
		modeloCadastro.setNomeMae((String) estudante.get("mae"));
		modeloCadastro.setSexoEstudante((String) estudante.get("sexo"));
		
        //System.out.println(modeloCadastro);
        return modeloCadastro;
    }
	
}
