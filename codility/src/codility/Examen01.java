package codility;

public class Examen01 {

	public Examen01() {
		
	}
	
	
	public int solution(int[] A) {
		int r = 0; // r es elMayor actual ( o rtado en si de la funcion )
		int indexOpuesto=-1;
		
		try {
			if ( (A != null) && (A.length >0 ) ) {
				
				for (int i = 0; i < A.length; i++) {
					if (A[i]>0) { // verifico solo para los positivos
						if (A[i]!=r) { //xa que no revise los que ya estaban revisados
							// ni los que por mas que exista opuesto este es de < valor absoluto
							//al mayor encontrado al momento.
							indexOpuesto=findOpposite(A, A[i]);// busco 1rst occ de un opuesto neg
							
							if (indexOpuesto!=-1) { //lo encontró y obtuvo su indice (del op neg)
								//TODO modularizar acciones por si y por no:
								//1. Si este que tiene su opuesto es > al mayorActual lo guardo
								if(A[i]>r) {
									r=A[i];
								}
								//2. Pongo en cero el indice del neg encontrado para que no lo 
								//tome en cuenta en futuros procesos.
								// ojo, modifico el array PERO EN SI DEBEN TRABAJAR SOBRE 1 COPIA
								// O SINO YO HAGO LA COPIA ACÁ DENTRO. (q = es mejor q una estruc aux)?
								
								//no haria falta marcar los ya evaluados ni perderlos ya que igualmente
								//no se reprocesan los A[i] < 0
								//ent comento:
								//A[indexOpuesto]=0; //xa que no procese mas al opuesto.
													// no sigue buscando mas ocurrencias, procesa de 
													// a pares. es una limitacion x ahora xa < complej.
							}
							//i++; (auto por For..)
						}
					}
				}
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return r;
	}
	
	
	//funcion generica utilidad que dado un valor entero x  
	//devuelve la posicion de su opuesto si existe o sino -1 si no existe 
	
	//es obvio que a ya existe en el vector porque es llamado tomando el valor desde el mismo 
	//vector donde me estoy fijando.
	
	//lo voy a llamar siempre con x>0 (controlado en la func que invoca)
	//retorna el indice de la 1er ocurrencia
	public int findOpposite(int []A, int x) throws Exception{
		int r=-1;
		try {
			for (int i = 0; i < A.length; i++) {
				//if (A[i]>0) { // verifico solo para los positivos -> ??? -> new: NO iria mepa..
					//if (A[i]==(x*-1)) { 	//si actual es el op a x
					if (isOpposite(A[i],x)) { //quiere decir q es op y neg, me qdo con el ind+
						r=i;
						break; //quiero q salga del for ( no de 1 vuelta del for no es continue)
					} //1er ocurrencia de opuesto alcanza. retorno la pos del opuesto.
				//}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return r;
	}

	//dados 2 enteros devuelve si son opuestos
	public boolean isOpposite(int i,int x) {  
		boolean r=false;
		if (i==(x*-1)) {
			r=true;
		}
		return r;
	}
	
}
