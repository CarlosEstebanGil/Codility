package codility;

public class Examen {

	public Examen() {
	}

	public static void main(String[] args) {
		
		// ----------------------------------------------------------------------------
		//1) TASK 1.
		/*
		int[] A=new int[13];
		//TODO modularizar cargarMatriz ejemplo entrada de datos. xa !=s caso de prueba.
		
		//Caso de prueba general. ( caso 1 )
		A[0]=1; A[1]=3; A[2]=-2;A[3]=20;A[4]=11;A[5]=5;A[6]=3;
		A[7]=5;A[8]=-1;A[9]=-5;A[10]=2;A[11]=-13;A[12]=13;
		
		//TODO border cases..

		//Ejecucion:
		Examen01 examen01 = new Examen01();
		System.out.println(examen01.solution(A));
		
		*/
		// ----------------------------------------------------------------------------
		//2) TASK 2.

		final String SEP="; ";
		final String z=".COM";
		
		//Caso de prueba general. ( caso 1 )
		StringBuilder sb = new StringBuilder();
		String S=""; // new String("jhon.doe")
		String C="example";
		S=	sb.append("John Doe").append(SEP)
			  .append("Peter Benjamin Parker").append(SEP)
			  .append("Mary Jane Watson-Parker").append(SEP)
			  .append("John Elvis Doe").append(SEP)
			  .append("John Evan Doe").append(SEP)
			  .append("Jane Doe").append(SEP)
			  .append("Peter Brian Parker").toString();
		
		
		Examen02 examen02 = new Examen02();
		String strURLsProcesadas_Final= examen02.solution( S, C);
		System.out.println(strURLsProcesadas_Final);
		
	}
	
	
}
