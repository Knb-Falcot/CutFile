/** ************************* CutFile **************************************
*/
//import BarProcess;
import java.io.*;
import alt.util.MultiFile;
import alt.util.OSArgument;
import java.util.Enumeration;
import alt.io.SequenceOutputStream;
import alt.io.FileOutputStreamEnumeration;

class CutFile {
	private static int BUFFER =  8192;  // 8 KB
	private static long ARCH_SIZE = 2048 * 1024; // 2 MB
	public static void main(String args[]) {
		OSArgument argument = new OSArgument(args);
		long f_size; 					// Cont� el tamany maxim de partici� d'arxiu.
		long size;						// Cont� el tamany del arxiu / BUFFER especificat.
		if (args.length > 0) {
			try {
				if (argument.exist("/v")) {
					f_size = argument.getInt("/v") * 1024;
					System.out.println("La mida de fitxer serà de " + f_size + " bytes.");
				}
				else {
					System.out.println("La mida del fitxer serà per defecte de " + ARCH_SIZE + " bytes.");
					f_size = ARCH_SIZE;
				}
				File f = new File(args[0]);
				if (f.exists()) {
					long numFiles = f.length() / f_size;        // Calcula nº de fitxers.
					System.out.println("Es crearan " + numFiles + " arxius.");
					if (f.length() % f_size > 0) numFiles++;
					Enumeration files = new MultiFile(f.getPath(), numFiles);
					Enumeration enu = new FileOutputStreamEnumeration(files);
					InputStream  fin  = new FileInputStream(f);
					OutputStream fout = new SequenceOutputStream(enu, f_size);
					InputStream finb = new BufferedInputStream(fin, BUFFER);
//					OutputStream foutb = new BufferedOutputStream(fout, BUFFER);
					size = f.length()/BUFFER;
					BarProcess bar = new BarProcess(size);
					System.out.println("Transferint dades . . . "); //en un total de " + size + " passos.");
					bar.init();
					byte buf[] = new byte[BUFFER];
					for (long c = 0; c <= size-1; c++) {
						finb.read(buf);
						fout.write(buf);
						bar.next();
					}
					size = f.length()%BUFFER;
					for (long c = 0; c < size; c++) {
						fout.write(finb.read());
					}
					bar.next();
					System.out.println("\nOperació completada.\nTancant fitxers.");
					fout.flush();
					fin.close();
					fout.close();
				} else
					System.out.println("No existeix l'arxiu especificat.");
			} catch (java.lang.NumberFormatException e) {
				System.out.println("Utilitzi:  CutFile <arxiu> [/v:<mida_KB>]");
			} catch (Exception e) {
				System.out.println(e);
			}
		} else
			System.out.println("Utilitzi:  CutFile <arxiu> [/v:<tamany_KB>]");
			System.out.println("rev: 01");
	}
}                                                                         //
