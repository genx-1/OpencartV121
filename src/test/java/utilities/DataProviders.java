package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders {
//DataProvider 1
	@DataProvider(name="LoginData")

    public String [][] getData() throws IOException
	{
	String path = ".\\testData\\Opencart_LoginData.xlsx";//taking excel from testData
	ExcelUtility xlutil = new ExcelUtility(path);//Creating an object for ExcelUtility
	int totalrows = xlutil.getRowCount("Sheet1");
	int totalcols = xlutil.getCellCount("Sheet1",1);
	String logindata[][] = new String [totalrows][totalcols];
	
	for(int i=1; i<=totalrows; i++) 
	{
		for (int j=0; j<totalcols; j++)
		{
			logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);//1,0
		}
	}
	return logindata; //returning two dimentional array
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
