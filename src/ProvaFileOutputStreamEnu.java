import java.io.*;
import alt.util.MultiFile;
import alt.io.FileOutputStreamEnumeration;
class ProvaFileOutputStreamEnu {
	public static void main(String args[]) {
		if (args.length > 0) {
			try {
				File file = new File(args[0]);
				int c;
				if (file.exists()) {
					MultiFile files = new MultiFile(file.getName(), 100);
					MultiFile fcopia = new MultiFile(file.getName(),100);
					FileOutputStreamEnumeration outEnu = new FileOutputStreamEnumeration(files);
					System.out.println("Arxius creats a partir de " + file.getName() + " :");
					for ( c = 0; files.hasMoreElements(); c++) {
						System.out.println('\t' + outEnu.nextElement().toString() + "\t...es...\t" +
												 fcopia.nextElement().toString());
					}
					System.out.println("\nTotal arxius creats: " + c +
											 "\nfaci un \"dir\" per comprovar la existencia dels arxius.");
				} else
					System.out.println("No existeix l'arxiu especificat");
			} catch (Exception e) {
				System.out.println("Error en main: " + e);
			}
		} else
			System.out.println("Ha d'especificar el nom d'un arxiu.");
	}
}
