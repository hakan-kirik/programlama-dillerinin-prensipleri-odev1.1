/**
*
* @author  hakan kirik hakan.kirik1@ogr.sakarya.edu.tr
* @since 17.03.2022
* <p>
* </p>
*/


package Program;


	import java.io.File;
	import java.io.IOException;
	import java.util.Scanner;

	public class ReadFile {
		private File file;
		private String path;
		private StringBuilder text;
		
		public ReadFile(String fileName) {
			File location=new File("");	//dosyan�n �al��t��� yolu almak i�in olu�turulan File nesnesi
			this.path=location.getAbsolutePath()+File.separator;	//program�n �al��t��� dosya yolunu bulur
			this.file=new File(path+fileName); //program�n �al��t��� dosya yolundaki verilen isimdeki dosyay� okur
			readText(file);
		}
		
		public void readText(File file) {	//dosyay� sat�r sat�r okuyarak StringBuildera atar 
			
			Scanner reader;
		
			String line="";
			try {
				 reader = new Scanner(file);
				 text=new StringBuilder();
				 while(reader.hasNextLine()) {
					 line=reader.nextLine();
					 text.append(line);	//al�nan sat�r� StringBuildera ekler
					 text.append("\n");	//new line ekler
				 }
			} catch (IOException e) {	//okunacak dosya yoksa hata f�rlat�r
			
				e.printStackTrace();
			}
			
		}
		public String getText() {	//StringBuilder� string olarak d�nd�r�r
			
			return text.toString();
		}

	}

