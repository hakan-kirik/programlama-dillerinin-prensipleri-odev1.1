/**
*
* @author  hakan kirik hakan.kirik1@ogr.sakarya.edu.tr
* @since 17.03.2022
* <p>
*
* </p>
*/


package Program;

public class Program {
	public static void main(String[] args) {
		Lexical lexical;	//lexical nesnesi oluþturur
		if(args.length!=0) {	//parametre girilmediyse varsayýlan örnek java dosyasýný okur girildiyse girileni okur
			lexical =new Lexical(args[0]);
		
		}
		else {
			lexical =new Lexical("ornek.java");
		}
		lexical.printOperators();
	}
}
