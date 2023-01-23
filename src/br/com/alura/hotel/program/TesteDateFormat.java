package br.com.alura.hotel.program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteDateFormat {

	public static void main(String[] args) throws ParseException {
		
		String data1 = "31/05/2023";
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = sdf1.parse(data1);
		System.out.println(sdf2.format(date));
	}
}
