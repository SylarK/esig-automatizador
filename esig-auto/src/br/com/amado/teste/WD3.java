package br.com.amado.teste;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class WD3 {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		System.setProperty("webdriver.edge.driver","C:\\WebDriver\\edgedriver_win64\\msedgedriver.exe");
		
		WebDriver driver = new EdgeDriver();
		//WebDriverWait myWaitVar = new WebDriverWait(driver, 5);
		
		String baseUrl = "https://treinamento-sigeduc-ba.esig.com.br/sigeduc/verTelaLogin.do";		
		
		driver.get(baseUrl);
		
		WebElement username = driver.findElement(By.name("user.login"));
		WebElement password = driver.findElement(By.name("user.senha"));
		WebElement btnLogin = driver.findElement(By.xpath("//*[@id=\"corpo\"]/table[1]/tbody/tr/td[2]/table[1]/tbody/tr[2]/td/form/table/tbody[2]/tr/td/input"));
		
		username.sendKeys("admin");
		password.sendKeys("admin");
		btnLogin.click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
		driver.close();
		System.exit(1);
	}
	
	
}
