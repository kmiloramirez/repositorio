package com.bibliotecario.bibliotecario;

import java.util.Calendar;

public class CalificadorUtil {
	
	public static Calendar sumarDiasSinContarDomingos(Calendar fechaAsumar, int diasASumar) {		
		int diasOperar = diasASumar - 1;
		
		while(diasOperar > 0){
			
			fechaAsumar.add(Calendar.DATE, 1);
			
			diasOperar = disminuirDiaSiNoEsDomingo(fechaAsumar, diasOperar);
		}
		
		return fechaAsumar;
	}

	private static int disminuirDiaSiNoEsDomingo(Calendar fechaAsumar, int diasOperar) {
		if(noEsDomingo(fechaAsumar)){
			diasOperar --;
		}
		return diasOperar;
	}

	private static boolean noEsDomingo(Calendar fechaAsumar) {
		return fechaAsumar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;
	}

}
