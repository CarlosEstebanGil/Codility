package codility;

public class BinaryGap {

	public BinaryGap() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		/*Our problem for the binary gap challenge is basically:

			1.Expect an integer between 1 and 2,147,483,647
			2.Convert that integer into its binary value
			3.Return the length of the largest sequence of zeros in between ones
			4.Return 0 if no zeros in between ones
		 */

			//1.
		int casoPrueba=65537;//12;
		
		System.out.println(Integer.toBinaryString(casoPrueba));	
		System.out.println(binaryGap(casoPrueba));
		 
		}
	
		public static int binaryGap(int n) throws Exception {
			String s=null;
			int max=0;
			int cont=0;
			boolean estoyEnUno=false;
			
			try {
				s = Integer.toBinaryString(n);
				
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i)=='1') {  // si i[0] == 1 ..
						estoyEnUno=true;
						if (cambio(i,s)) {
							max=Integer.max(cont,max);
							cont=0;
						}
					}else // si i[0] == 0 ..
						if (estoyEnUno)
							cont++;
						else // ( s[i] vale 0 pero no estoy con un uno de inicio
							continue;
				}
				
				return max;
				
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}	
		
		}


		
		public static boolean cambio(int i, String s) {
			boolean cambio= false;
			if(i>0)  // caso base. 1er item nunca es cambio, queda el false inicial
			{
				if (s.charAt(i)=='1' && s.charAt(i-1)=='0') 
					cambio=true;
			}
			return cambio;
			
			/*boolean cambio= false;
			if(i==0)  // caso base. 1er item nunca es cambio, queda el false inicial
			{
				cambio=true;
			}
			else {
				if (s.charAt(i-1)=='0') 
					cambio=true;
			}
			return cambio; */
		}
		
}
