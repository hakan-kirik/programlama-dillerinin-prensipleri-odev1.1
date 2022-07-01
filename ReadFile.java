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
			File location=new File("");	//dosyanýn çalýþtýðý yolu almak için oluþturulan File nesnesi
			this.path=location.getAbsolutePath()+File.separator;	//programýn çalýþtýðý dosya yolunu bulur
			this.file=new File(path+fileName); //programýn çalýþtýðý dosya yolundaki verilen isimdeki dosyayý okur
			readText(file);
		}
		
		public void readText(File file) {	//dosyayý satýr satýr okuyarak StringBuildera atar 
			
			Scanner reader;
		
			String line="";
			try {
				 reader = new Scanner(file);
				 text=new StringBuilder();
				 while(reader.hasNextLine()) {
					 line=reader.nextLine();
					 text.append(line);	//alýnan satýrý StringBuildera ekler
					 text.append("\n");	//new line ekler
				 }
			} catch (IOException e) {	//okunacak dosya yoksa hata fýrlatýr
			
				e.printStackTrace();
			}
			
		}
		public String getText() {	//StringBuilderý string olarak döndürür
			
			return text.toString();
		}

	}

