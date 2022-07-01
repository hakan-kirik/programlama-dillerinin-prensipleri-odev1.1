/**
*
* @author hakan kirik hakan.kirik1@ogr.sakarya.edu.tr
* @since 17.03.2022
* <p>
* 
* </p>
*/

package Program;
import java.util.ArrayList;

public class Lexical {
	private int position;  //okuduðu stringin konum bilgisini tutar
	private String text;  //okunan string
	private char ch;   //diziyi dolaþacak char
	private char[]	chArr;   //stringin aktarýldýðý dizi
	private ReadFile read;  //dosya okuma
	
	private int add;   		//+ operatörünün sayýsý
	private int increase;  	//++
	private int subtract;  	//-
	private int decrease; 	//--
	private int multiply; 	//*
	private int divide; 	// /
	private int mod;        //%
	private int bitAnd;  	//&
	private int bitOr;  	// |
	private int bitxor;  	//^
	private int assign;		//=
	private int addAssign;	//+=
	private int subAssign;	//-=
	private int divAssign;	// /=
	private int mulAssign;	// *=
	private int modAssign;	// %=
	private int andAssign;	// &=
	private int orAssing;	// |=
	private int XorAssign;	// ^=
	private int less;		//<
	private int lessEqual;	//<=
	private int greater;	//>
	private int greaterEqual;//>=
	private int equal;		//==
	private int notEqual;	//!=
	private int and;		//&&
	private int or;			// ||
	private int not;		//!
	
	
	
	Lexical(String file){  //constructorda 
		//baþlangýç deðerlerinin atanmasý
		 add=0;
		 increase=0;
		 subtract=0;
		 decrease=0;
		 multiply=0;
		 divide=0;
		 mod=0;
		 bitAnd=0;
		 bitOr=0;
		 bitxor=0;
		 assign=0;
		 addAssign=0;
		 subAssign=0;
		 divAssign=0;
		 mulAssign=0;
		 modAssign=0;
		 andAssign=0;
		 orAssing=0;
		 XorAssign=0;
		 less=0;
		 lessEqual=0;
		 greater=0;
		 greaterEqual=0;
		 equal=0;
		 notEqual=0;
		 and=0;
		 or=0;
		 not=0;
		//
		read=new ReadFile(file);  //ReadFile nesnesi oluþturulur
		text=read.getText();		//dosyadaki textin alýnmasý
		chArr=text.toCharArray();	//text char dizisine dönüþtürülür
		
		ch=chArr[0];   //diziyi dolaþacak chara dizinin ilk elemanýnýn deðeri atanýr
		position=0;		//baþlangýç deðeri atanýr
		assignOperatorCounts();	//nesne oluþturulduðu gibi operatör sayýlarýný sayar
	}
	
	private void assignOperatorCounts() {  //operatör sayýlarýný hesaplayan ve üye deðiþkenlere atayan fonksiyonu çaðýrýr
		while (!countOperator()) {   //string bitince operatör sayýlarýný hesaplayan countOperator() true döndürür, üye deðiþkenlere atama tamamlanýr
        }
	}
	
	private boolean ignoreCommends() {  //yorum satýrlarýný atlar. Bu fonksiyona girebilmesi için / karakterinin okunmuþ olmasý gerekir. Bu fonksiyonun içinde 2.karakter kontrol edilir yani / zaten elimizde var gelen 2.karakter / ise // gelmiþ demektir, yorumdur. Gelen karakter * ise /* gelmiþtir, yorumdur gibi.
	 	char nch=takeNextChar();		//bir sonraki karaktere geçilir
	 	int pos=this.position;	//okunan karater yorum satýrý deðilse baþlangýca geri dönebilmek için baþlangýç pozisyonunu tutarýz
        if (nch != '*') {		//alýnan karakter * deðil ise '/' olam durumunu kontol eder.
        	 if(nch=='/')		//ikinci kararakterin / olma durumunu kontrol eder  (yani // gelmiþ , yorumdur)
        	        while(true) { //satir sonuna kadar okur
        	        	  if(this.ch!='\n') {	//gelen char satýr sonu deðilse devam eder,bu þekilde satýrý bitirir, atlamýþ olur
        	        		  takeNextChar();
        	        	 }else {
        	        		 takeNextChar();	//gelen char satýr sonuysa true döndürür, satýrý atlama iþlemi bitmiþ demektir
        	        		 return true;
        	        	 }
        	        }
        	 this.position=pos;	//eðer yorum satýrý deðilse baþlangýç pozisyonuna geri döner
        	 return false;
        	 
        }
        
        takeNextChar();	//  * gelmiþ demektir. Bir sonraki chara geçilir
        while (true) { 
            if (this.ch == (char)0) {	//char null ise dosya bitmiþtir fonksiyon true döndürür.(bu kuralý takeNextChar() fonksiyonu içinde kendimiz belirledik)
                break;
            }
            else if (this.ch == '*') {	
                if (takeNextChar() == '/') {	// */ gelmiþse true döndürür,yorum satýrý bitmiþ demektir
                	takeNextChar();
                    return true;
                }
            }
            else {
            	takeNextChar();	 //sýradaki char alýnýr
            }
        }
        return true;
	}
	
	private void ignoreChar() { 	//charýn içindeki operatörleri es geçer
		char chr=takeNextChar();
		while(chr!='\'') {		//char kapanana kadar çalýþýr
			if(chr==(char)0) {	//char kapanmadýysa ve dosya sonuna gelindiyse hata verir ve programý sonlandýrýr
				System.out.println("hatali char ifade var");
				System.exit(1);
			}
			chr=takeNextChar();
		}
		takeNextChar();
	}
	
	private void ignoreString() {	//stringin içindeki operatörleri es geçer
		char chr=takeNextChar();
		while(chr!='\"') {	//string ifade kapanana kadar çalýþýr
			
			if(chr==(char)0 ||chr=='\n') {	//string ifade kapanmadýysa ve dosya sonuna gelindiyse hata verir ve programý sonlandýrýr
				System.out.println("hatali string ifade var");
				System.exit(1);
			}
			chr=takeNextChar();
		}
		takeNextChar();
		
	}
	
	private boolean countOperator() {		//operatörlerin sayýlarý hesaplanýr
		 while (Character.isWhitespace(this.ch)) {  //boþluksa sýradaki chara geçer
			 takeNextChar();
	        }
		 if(this.ch==(char)0)	//char null ise dosya bitmiþtir fonksiyon true döndürür.(bu kuralý takeNextChar() fonksiyonu içinde kendimiz belirledik)
		 {
			 return true;
		 }else if(this.ch=='/') {	
			 if(!ignoreCommends()) {	//gelen char / ise yorum satýrý olup olmadýðý kontrol edilir. 
				 char chr=takeNextChar();
				 if(chr=='=') {	//yorum satýrý deðil ve bir sonraki karakter = ise /= operatörü arttýrýlýr
					 this.divAssign++;
					 takeNextChar();
				 }else {		// /= deðil ise / operatörü 1 arttýrýlýr
					 this.divide++;
				 }
			 }
			 
		 }else if(this.ch=='<') {
			 char chr=takeNextChar();
			 if(chr=='=') {		//alýnan 2.karakter = ise <= 1 arttýrýlýr
				 this.lessEqual++;	
				 takeNextChar();
			 }else {
				 this.less++;	//2.karakter = deðilse < arttýrýlýr
			 }
		 }else if(this.ch=='=') {
			 char chr=takeNextChar();
			 if(chr=='=') {	//2.karakter de = ise == operatörü 1 arttýrýlýr
				 this.equal++;
				 takeNextChar();
			 }else {		//2.alýnan karakter de = deðilse = operatörü 1 arttýrýlýr
				 this.assign++;
			 }
		 }else if(this.ch=='!') {
			 char chr=takeNextChar();
			 if(chr=='=') {		//2.alýnan karakter = ise != operatörü 1 arttýrýlýr
				 this.notEqual++;
				 takeNextChar();
			 }else {
				 this.not++;	// ! operatörü 1 arttýrýlýr
			 }
		 }else if(this.ch=='&') {
			 char chr=takeNextChar();
			 if(chr=='=') {		// &= operatörü 1 arttýrýlýr
				 this.addAssign++;
				 takeNextChar();
			 }else if(chr=='&') {	//&& operatörü 1 arttýrýlýr
				 this.and++;
				 takeNextChar();
			 }else {		//& operatörü 1 arttýrlýr
				 this.bitAnd++;
			 }
		 }else if(this.ch=='|') {
			 char chr=takeNextChar();
			 if(chr=='=') {		// |= operatörü 1 arttýrýlýr 
				 this.orAssing++;
				 takeNextChar();
			 }else if(chr=='|') {	// || operatörü 1 arttýrýlýr
				 this.or++;
				 takeNextChar();
			 }else {		// | operatörü 1 arttýrýlýr
				 this.bitOr++;
			 }
		 }else if(this.ch=='>') {	
			 char chr=takeNextChar();
			 if(chr=='=') {		// >= arttýrýlýr
				 this.greaterEqual++;
				 takeNextChar();
			 }else {		// > arttýrýlýr
				 this.greater++;
			 }
		 }else if (this.ch=='%') {
			 char chr=takeNextChar();
			 if(chr=='=') {		// %= arttýrýlýr
				 this.modAssign++;
				 takeNextChar();
			 }else {		// % arttýrýlýr
				 this.mod++;
			 }
		 }else if(this.ch=='+') {
			 char chr=takeNextChar();
			 if(chr=='=') {		// += arttýrýlýr
				 this.addAssign++;
				 takeNextChar();
			 }else if(chr=='+') {	// ++ arttýrýlýr
				 this.increase++;
				 takeNextChar();
			 }else {	// + arttýrýlýr
				 this.add++;
			 }
		 }else if(this.ch=='-') {
			 char chr=takeNextChar();
			 if(chr=='=') {		// -= arttýrýlýr
				 this.subAssign++;
				 takeNextChar();
			 }else if(chr=='-') {	// -- arttýrýlýr
				 this.decrease++;
				 takeNextChar();
			 }else {
				 this.subtract++;	// - arttýrýlýr
			 }
		 }else if(this.ch=='^') {
			 char chr=takeNextChar();
			 if(chr=='=') {		// ^= arttýrýlýr
				 this.XorAssign++;
				 takeNextChar();
			 }else {
				 this.bitxor++;		// ^ arttýrýlýr
			 }
		 }else if(this.ch=='*') {
			 char chr=takeNextChar();
			 if(chr=='=') {		// *= arttýrýlýr
				 this.mulAssign++;
				 takeNextChar();
			 }else {
				 this.multiply++;	// * arttýrýlýr
			 }
		 }else if(this.ch=='\'') {	//char ifade gördüyse atlar. çünkü char ifadenin içindekiler bir operatör sayýlmaz yalnýzca karakterdirler
			 ignoreChar();
			 
		 }else if(this.ch=='"') {	//string ifade gördüyse atlar çünkü string ifadenin içindekiler operatör sayýlmaz yalnýzca karakterdirler
			 ignoreString();
		 }else {
			 takeNextChar();	//hiçbiri deðilse yeni karakter alýnýr 
		 }
		 
		 return false;
	}
	
	
	private char takeNextChar() {	//sonraki karakter alýnýr 
		position++;
		
		if(position<chArr.length) {
			this.ch=chArr[position];
		}else {		//kendi kuralýmýz, sonuna geldik mi diye anlamak için kullaýrýz
			this.ch=(char)0;
		}
		
		return this.ch;
	}
	void printOperators() {
		ArrayList<Integer>single=new ArrayList();	//operatörler liste içinde dökümanda istenilen þekilde gruplandýrýlýr
		ArrayList<Integer>dual=new ArrayList();
		ArrayList<Integer>arithmetic=new ArrayList();
		ArrayList<Integer>logical=new ArrayList();
		ArrayList<Integer>relational=new ArrayList();
		
		single.add(this.not);		//tekli operatörlere ekleme yapýlýr
		single.add(this.increase);
		single.add(this.decrease);
		
		dual.add(this.add);		//ikili operatörlere ekleme yapýlýr
		dual.add(this.subtract);
		dual.add(this.multiply);
		dual.add(this.divide);
		dual.add(this.mod);
		dual.add(this.bitAnd);
		dual.add(this.bitOr);
		dual.add(this.bitxor);
		dual.add(this.assign);
		dual.add(this.addAssign);
		dual.add(this.subAssign);
		dual.add(this.divAssign);
		dual.add(this.mulAssign);
		dual.add(this.modAssign);
		dual.add(this.andAssign);
		dual.add(this.orAssing);
		dual.add(this.XorAssign);
		
		arithmetic.add(this.increase);	//sayýsal operatörlere ekleme iþlemi yapýlýr
		arithmetic.add(this.decrease);
		arithmetic.add(this.add);
		arithmetic.add(this.subtract);
		arithmetic.add(this.multiply);
		arithmetic.add(this.divide);
		arithmetic.add(this.mod);
		arithmetic.add(this.bitAnd);
		arithmetic.add(this.bitOr);
		arithmetic.add(this.bitxor);
		arithmetic.add(this.assign);
		arithmetic.add(this.addAssign);
		arithmetic.add(this.subAssign);
		arithmetic.add(this.divAssign);
		arithmetic.add(this.mulAssign);
		arithmetic.add(this.modAssign);
		arithmetic.add(this.andAssign);
		arithmetic.add(this.orAssing);
		arithmetic.add(this.XorAssign);
		
		logical.add(this.and);	//mantýksal operatörlere ekleme yapýlýr
		logical.add(this.or);
		logical.add(this.not);
		
		relational.add(this.less);	//iliþkisel operatörlere ekleme yapýlýr
		relational.add(this.greater);
		relational.add(this.lessEqual);
		relational.add(this.greaterEqual);
		relational.add(this.equal);
		relational.add(this.notEqual);
		
		//baþlangýç deðerleri 0 olarak belirlenir
		int totalRelational=0;
		int totalLogical=0;
		int totalArithmetic=0;
		int totalDual=0;
		int totalSingle=0;
		
		//tek tek foreach mantýðýyla sayýlarýný toplar
		for(int count:single) {
			totalSingle+=count;
		}
		for(int count:dual) {
			totalDual+=count;
		}
		for(int count:arithmetic) {
			totalArithmetic+=count;
		}
		for(int count:logical) {
			totalLogical+=count;
		}
		for(int count:relational) {
			totalRelational+=count;
		}
		
		
		//operand sayýsý bulunur ve ekrana istenilenler basýlýr
		int operandCount=totalSingle+totalDual*2+totalLogical*2+totalRelational*2-this.not*2;
		System.out.println("operator bilgisi:");
		System.out.println("\t tekli operator sayisi: "+totalSingle);
		System.out.println("\t ikili operator sayisi: "+totalDual);
		System.out.println("\t sayisal operator sayisi: "+totalArithmetic);
		System.out.println("\t iliskisel operator sayisi: "+totalRelational);
		System.out.println("\t mantiksal operator sayisi: "+totalLogical);
		System.out.println("operand bilgisi:");
		System.out.println("\t operand sayisi: "+operandCount);
		System.out.println();
		
	}
	
}
