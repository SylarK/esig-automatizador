package br.com.amado.horarioponto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Main {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.edge.driver","C:\\WebDriver\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		
		String baseUrl = "https://quarkrh.com.br/login.jsf";	
		driver.get(baseUrl);
		
		

	}

}
