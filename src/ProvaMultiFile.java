import java.io.*;
import alt.util.MultiFile;
class ProvaMultiFile {
	public static void main(String args[]) {
		if (args.length > 0) {
			try {
				File file = new File(args[0]);
				int c;
				if (file.exists()) {
					MultiFile files = new MultiFile(file.getName(), 10);
//					String fileList[] = files.list();
					System.out.println("Arxius creats a partir de " + file.getName() + " :");
					for ( c = 0; files.hasMoreElements(); c++) {
						System.out.println('\t' + files.nextElement().toString() + "\t...A");
					}
					System.out.println("\nTotal arxius creats: " + c);
				} else
					System.out.println("No existeix l'arxiu especificat");
			} catch (Exception e) {
				System.out.println("Error en main: " + e);
			}
		} else
			System.out.println("Ha d'especificar el nom d'un arxiu.");
	}
}
