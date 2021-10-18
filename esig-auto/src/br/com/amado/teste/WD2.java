package br.com.amado.teste;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WD2 {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.edge.driver","C:\\WebDriver\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		
		String baseUrl = "https://treinamento-sigeduc-ba.esig.com.br/sigeduc/verTelaLogin.do";		
		
		driver.get(baseUrl);
		
		String username = driver.findElement(By.name("name.login")).getTagName();
		System.out.println(username);	
		
		driver.close();
	}
	
	
}
