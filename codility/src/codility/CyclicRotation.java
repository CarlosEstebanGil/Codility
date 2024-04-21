package codility;

public class CyclicRotation {

	public CyclicRotation() {
		// TODO Auto-generated constructor stub
	}

	static final int CANT_VUELTAS  =2; // 1; //3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		int k=CANT_VUELTAS; //3
		
		int[] vec = {3,8,9,7,6};
		
		for (int i = 0; i < CANT_VUELTAS; i++) {
			rotarCiclicamente(vec); //, k);
		}
			
		imprimirVector(vec);
	}
	
	// Saber: los objetos ya pasan por referencia asi que modifico el mismo vec y listo.
	//public static int[] rotarCiclicamente(int[] vec, int k) {
	public static void rotarCiclicamente(int[] vec) { // , int k) { 
		try {
			if ( ( vec !=null) && vec.length > 1 ) {
				int lastElem = vec[vec.length-1]; //me guardo el valor del ult xa no perderlo
				
				int iDondeMeterTmp=vec.length-1; //arranco del ultimo
				int iParaMeter=vec.length -1 -1 ; //arranco a meter el anteultimo en el ultimo
				
				while (iDondeMeterTmp >0) {
					
					vec[iDondeMeterTmp] = vec[iParaMeter]; 
					iDondeMeterTmp-- ;
					iParaMeter--;
					
				}
				vec[0]=lastElem;
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	public static final void imprimirVector(int[] vec) {
		try {
			for (int i = 0; i < vec.length; i++) {
				System.out.println(vec[i] );
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
