package codility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Examen02 {

	public Examen02() {
	}

	//John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; Jhon Elvis Doe; Jhon Evan Doe; 
	//Jane Doe; Peter Brian Parker

	final static String ARROBA="@";
	final static String SEP_FIRST_LAST=".";
	final static String Z=".COM";
	final static String EMPRESA = "gmail";
	final static String EMAILS_SEPARATOR= ";";
	
	// a mi me viene string con nombres completos yseparados por ;  este hash es lo que voy armando 1 x 1 de estos
	// con el orig (ya sea sin alterar, o truncado o sequenciado etc + @ + empresa + ".com" y eso va al hash.
	// tomo el sig del str arr de origs , lo "acomodo si ya existe en el map tmp q estuve armando) 
	// y lo meto de nuevo eso ya acomodado , al hash tmp 
	
	//finalmente recorro el hash tmp y armo el str de salida pero como acá lo voy armando a medida q van qdando 1 a 1
	//en el hash los meto tmb en un SB devuelvo ese SB y me ahorro un recorrido extra al final.
	
	private HashMap<String, String> hashEmailsStr=new HashMap<String, String>();
	
	public String solution(String S, String C) {
        String r=null; //r será la lista definitiva de direcciones validadas y armadas
        
        final String SEP="; ";
        final String z=".COM";
        
        StringBuilder sbTmp= new StringBuilder();
        String strDirTmp="";
        // Proceso:
        
        //1) "Spliteo" el arr en base al separador configurado.
        String[] arrOfStrNames= S.split(SEP);
        
        //2) Proceso Cada String del arr orig (str orig splited to arr), Para Armar su direccion:
        for (int i = 0; i < arrOfStrNames.length; i++) {
			//System.out.println(arrOfStrNames[i]);
        	strDirTmp=ArmarDireccion(arrOfStrNames[i]); //arma la dir truncada acomodada etc
        	if(strDirTmp.compareTo("")!=0) { //pudo armarla
        		
        		if (yaExistEnteEnHash(strDirTmp,hashEmailsStr)) { //duplicated name (strActualTruncada en hashTmp)
        			strDirTmp=corregirURLFinalDuplicada(strDirTmp,hashEmailsStr); //la corrige
        		}else // strEmailActual no existe aun en hash
        		{
        			
        		}
        		//existiese y fuese corrigida, o no existiese ,xa ambos casos agrego al hash (la new o corregida)
        		hashEmailsStr.put(strDirTmp, strDirTmp); //actualiza el hashtmp de dirs del arr orig ya procesadas y upd
        		//y concateno al sb xa la salida
        		sbTmp.append(strDirTmp).append(EMAILS_SEPARATOR);
        		//sbTmp.append(strDirTmp);
        	}
		}
        
        r= sbTmp.toString();
        return r;
    }
	
	public boolean yaExistEnteEnHash(String strEMAILURL, HashMap<String, String> hashUrls) {
		boolean r=false;
		
		// proceso ( busca en hash y altera r si existe ..................
		if ( ( hashUrls!=null) & ( strEMAILURL !=null) ) {
			if ( ( hashUrls.size() > 0 ) && (strEMAILURL.length()>0 ) ) {
				if (hashUrls.get(strEMAILURL)!=null) { //existe
					r=true;
				}
			}
		}
		
		return r;
	}
	
	//Hacer.... //ya lo llamo desde ell ppal q verificó q existe asi q es duplicada y ya existe
	// una identica e incluso debo verificar porque pueden o no existir mas (con numeracion corr)
	public String corregirURLFinalDuplicada(String strDuplicatedEMAILURL, HashMap<String, String> hashEmailsStr) {
		String r=strDuplicatedEMAILURL;
		//toma el hashMap de la propia clase ( xero xa eso seria private, sino lo 
		//recibo tmb al hash x param ent es mas utilitaria general mas public.
		
		//proceso: mente: habria que recorrer el hashmap y ver si existe ,PERO OJO, tmb 
		//puede existir un numero correlativo del mismo en el hash ya y tnga q poner un 3ero 
		//o 4to o lo q sea. Por lo que Debo buscar el ultimo que coindida en nombre y TODOS 
		//los q coincidan en nombre y tengan mas caracteres ( luego ver si esos caracteres son 
		//numeros, si no lo son no son de la "flia" y si lo son debo quedarme con el mayor 
		//numero, sumarle 1 , concatenarlo a mi strDuplicado y eso es lo q agrego al hash
		// finalmente en la rutina ppal lo q debo devolver es un str de urls finales q sale 
		//de recorrer el hashmap y separarlos por ; 
		
		//Obtengo una lista de strs coincidentes con strDupli param como substr de c/u del hash
		//luego de esa lista la recorro, o sino en realidad hago el ctrl mientras recorro el hash
		//ent:
		
		//1. tomo el 1ero del hash. ( AL FINAL SI VOY A RECORRER 1X1 EN EL HASH ENT NI HARIA 
									// FALTA Q SEA UN HASH. AUNQUE X AHI SI XQ EN LA FUNC ANT
									//USO EL BUSCAR ASI QUE XA ESO SI AGILIZA.
		//ADEMAS SI NO EXISTE strActual en Hash ent ni busco el 2 o 3 o n, eso obvio. 
		
		//". si coincide letra a letra con cada char del h' fin strDupli ent 
				//(osea hago yo la comparacion, no uso substr, voy char x char mejor..)
			// si no es el fin del del hash ent 
					//si lo que sigue es numerico, 
						//me lo guardo como maxint ej 1 y paso al sig
						//y paso al sig
			// si era el fin y coinciden en chars y fin ent al nuevo debo agregarle un "1"
		//sino paso al sig	
		
		//al salir de recorrer todo el hash si maxInt vale algo es xq lo encontré y lo añado 
		//Ent: impl:
		 
		//for i = 1 hasta fin hash //recorro TODOS los del ,1 a 1, si o si. para verific "simil"
								// osea coincidencia del nombre sin importar si siguen nros
		//Iterator it = hashEmailsStr.entrySet().iterator();
		//Iterate through the entrySet()
		
		int iDefMax= -1; // VA A GUARDAR EL MAYOR NUMERO INT ENCONTRADO PARA UN COINCIDENTE YA PRENUMERADO O 0 SI 
						//  ERA COINCIDENTE PERO AUN NO NUMERADO O -1 SI NO HAY NINGUNO! COINCIDENTE
		int maxCorrelativNumb=0;
		String  hashElemActualStr="";
		for (String key : hashEmailsStr.keySet()) { //tomo c/u del hash q recorro 1 a 1 (keys)
			hashElemActualStr=key; //1er elem (email) en el hash
			maxCorrelativNumb =coincidente( strDuplicatedEMAILURL,  hashElemActualStr); //toma el max si coinciden 
			if ( maxCorrelativNumb > 0) { //si coincidente y arreglado (coinciden) 
				//iDefMax=maxCorrelativNumb + 1; // iDefMax será el sig xa ese nro xa el coincidente encontrado 
				iDefMax=Integer.max(maxCorrelativNumb,iDefMax);
			}else if (maxCorrelativNumb < 0) { // no coincidentes, el string deberia quedar igual
				//Puede aparecer en otro posterior.. no hago nada (skip)
			}else { //maxCorrelativNumb es 0 , eran exactamente iguales, me quedo con un IDefMax = 1
				iDefMax=Integer.max(maxCorrelativNumb,iDefMax); // pone en 0 a idefmax si pasa este caso
			}
			
//			luego ya sea xq coincidia o no, xa ambos casos paso al sig del hash , hash.leer sig

		} //end "for each".
		
		//ya recorri todo el hash y calculé el max a devolver
		
		 //if hallé algo y vi bien q meterle
		if (iDefMax!= -1) { //encontro al menos 1 con o sin numeracion pero ya existente 
			iDefMax= iDefMax + 1; //guardó el mayor encontrado xa mismo name, 0 -1 si no encontro o si encontro solo
			//uno identico sin numerar ( si era 0 pone 1 si era 1 pone 2 etc)
			r= parteNameOnlyyStringUnicamente( r)  + iDefMax + ARROBA+EMPRESA+Z;//hashElemActualStr) + iDefMax; 
		}
	
			//r=r + Integer.toString(max..);
			//la parte string del strHashActual + el maxCorrelativNumb ya calculado concatenado.
			
		return r; //sale el orig si no encontró nada. sino con un 1 si solio habia 1 sino con n
	}
	
	public String parteNameOnlyyStringUnicamente(String hashElemActualStr) {
		String r="";
		int i = 0;
		while (i<hashElemActualStr.length()) {
			if ( (Character.isDigit(hashElemActualStr.charAt(i)) || (Character.toString(hashElemActualStr.charAt(i)).compareToIgnoreCase(ARROBA)==0 ) )) {
				break;
			}
			r = r + hashElemActualStr.charAt(i);
			i++;
		}
		 
		return r;
	}
	
//retorna -1 si no es coincidente 
//o el numero mas alto ya existente xa ese nombre ( 0 si es coincidente identico sin resto) 
//(a lo que habria q sumarle 1)
	public int coincidente(String strDuplicatedEMAILURL, String hashElemActualStr) {
		//boolean r=false;
		
		//NEW: OJO, YA VENGO CON LAS URLS ARMADAS EJ john.doe@gmail.com y jhon.doe1@gmail.com 
		// y sale del while por el 1 , pero no por que i > a la long de strDuplicado!!!  
		
		// solucion temporal, le quito todo lo previo al arroba a cada uno xa la comparacion yl lo vuelvo a meter:
		
		strDuplicatedEMAILURL=strDuplicatedEMAILURL.substring(0,strDuplicatedEMAILURL.indexOf(ARROBA));
		hashElemActualStr=hashElemActualStr.substring(0,hashElemActualStr.indexOf(ARROBA));
		
		int r=-1;
		int i= 0; 
		
		int iRestoHash=-1;
		
		while (i < strDuplicatedEMAILURL.length()  ) { //recorro todo el str 1
			if ( strDuplicatedEMAILURL.charAt(i) != hashElemActualStr.charAt(i) ) {
				break; //a la primera q  no coincidan salgo , con el i incompleto
			}
			i++;
		}	
		//si salió por completamente coincidente:
		//if (i == strDuplicatedEMAILURL.length() ) { 
		if (i >= strDuplicatedEMAILURL.length() ) {	//RECORRIÓ ENTERO el strDupli , sin break todo x chars igual
			
			//si ( qdan_mas_en_el_hashElemActualStr desde la pos sig
			if ( hashElemActualStr.length()>i ) {
				
				String strRestoHash = hashElemActualStr.substring(i,hashElemActualStr.length());
				
				if (isInteger(strRestoHash)) {//,10)) {
				 //si es_un_todo_eso_q_sobra_ un numero() { 
//						a maxIntTmp le doy ese numero + 1 q es lo q le meteré sino 
//						(habria un err de data)
					iRestoHash=Integer.parseInt(strRestoHash);
				}
				
			}else if  ( hashElemActualStr.length()==i ) { //eran exactamente iguales
				iRestoHash=0;
			}
			
		} else { //  no eran coincidentes  
			iRestoHash=-1;
		}
		
		r=iRestoHash;
		
		return r;
	}
	
//		public static boolean isInteger(String s, int radix) { // ej (s,10)
//		    if(s.isEmpty()) return false;
//		    for(int i = 0; i < s.length(); i++) {
//		        if(i == 0 && s.charAt(i) == '-') {
//		            if(s.length() == 1) return false;
//		            else continue;
//		        }
//		        if(Character.digit(s.charAt(i),radix) < 0) return false;
//		    }
//		    return true;
//		}
		 

		public static boolean isInteger(String s) {
		    try { 
		        Integer.parseInt(s); 
		    } catch(NumberFormatException e) { 
		        return false; 
		    } catch(NullPointerException e) {
		        return false;
		    }
		    // only got here if we didn't return false
		    return true;
		}
		
		
	public String ArmarDireccion(String strCurrentCompleteName) {
		String r="";
		final String SEP=" ";
		int posCampoTmp=0;
		//proceso:
		String[] arrOfStrSubNames= strCurrentCompleteName.split(SEP);
		
		if ( (arrOfStrSubNames.length==2) || (arrOfStrSubNames.length==3) ) { 
			
			switch (arrOfStrSubNames.length) {
			case 2:
				posCampoTmp=1;
				break;
			case 3:
				posCampoTmp=2;
				break;
			default:
				break;
			}
			
			r= ArmarURLEmail(arrOfStrSubNames,posCampoTmp);
			
		}else {
			//entrada formato invalido. no se procesa el nombre. (r="")
		}
		
		return r;
	}
	
	
	public String ArmarURLEmail(String[] arrOfStrSubNames, int posCampoTmp ) {
		String r="";
		String firstName="";
		String lastName="";
		//proceso
		
			firstName= arrOfStrSubNames[0];
			lastName=arrOfStrSubNames[posCampoTmp];
			
			//Remueve el guion del lastName
			if (lastName.contains("-")) {
				lastName= removerGuion(lastName);
			}
			
			//Trunca cada campo a 8 si es necesario 
			if (firstName.length()>8) {
				firstName=firstName.substring(0,7);
			}
			if (lastName.length()>8) {
				lastName=lastName.substring(0,7);
			}
			
			//convierte a minusculas
			firstName=firstName.toLowerCase();
			lastName=lastName.toLowerCase();
			
		r=firstName + SEP_FIRST_LAST + lastName +  ARROBA + EMPRESA + Z;	
		return r;
	}
	
	public String  removerGuion(String s){
		String r="";
				
		r=s.replace("-", "");
		return r;
	}
	// HACIENDO ESTO QUE FALTA QUE DICE "HACER" ya estaría, mas ver los casos border.
	
	//(Falta)chequear que si ya existia ese email entonces le agregue un int secuencial!!
	
	//idea: me armo un hashmap donde pueda buscar rapido los emails armados ??
	//entonces cada url que voy armando la voy agregando al hash, y si ya existe la actual 
	//en ese entonces modif la actual sumandole (el ult numero q ya existe en el hash xa esa
	// eso lo hago con una func q tome el ultimo char de la url del hash y si es un numero ent
	// es ese numero + 1 , sino entonces es 1.
	
	//finalmente recorro ese hash de direcciones acumuladas armando un string separado x un 
	//separador xxx conf tmb como kte q sea para separar dirs ulrs finales en el string final
	
	

}
