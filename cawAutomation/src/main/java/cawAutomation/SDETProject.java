package cawAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SDETProject {
	
	public static String deriveTextsFromTable(WebDriver driver) {
		List<WebElement> row = driver.findElements(By.xpath("//table[@id='dynamictable']/tr/td[1]"));
		List<WebElement> col = driver.findElements(By.xpath("//table[@id='dynamictable']/tr/th"));
		System.out.println("Row size: "+row.size()+ "Col size: "+col.size());
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=2;i<=row.size()+1;i++) {
			sb.append("{");
			
			for(int j=1;j<=col.size();j++) {
				String value = driver.findElement(By.xpath("//table[@id='dynamictable']/tr["+ i +"]/td["+ j +"]")).getText();
				if(j==1) {
					sb.append("\"name\" : ");
					sb.append("\"");
					sb.append(value);
					sb.append("\", ");
				}
				else if (j==2) {
					sb.append("\"age\" : ");
					sb.append(value);
					sb.append(", ");
				}
				else {
					sb.append("\"gender\": ");
					sb.append("\"");
					sb.append(value);
					sb.append("\" ");
				}
			}
			sb.append("}");
			if(i!=row.size()+1) {
				sb.append(",");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
}
