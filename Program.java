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
		Lexical lexical;	//lexical nesnesi olu�turur
		if(args.length!=0) {	//parametre girilmediyse varsay�lan �rnek java dosyas�n� okur girildiyse girileni okur
			lexical =new Lexical(args[0]);
		
		}
		else {
			lexical =new Lexical("ornek.java");
		}
		lexical.printOperators();
	}
}
