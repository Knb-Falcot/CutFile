/** *************************** MultiFile ***********************************
 *
 * @author			Eduard Calveras
 * @version       @(#)MultiFile.java 1.1, 28 Dec 1998
 *
 * Aquesta classe crea noms  de  fitxers fins  a un  limit de 1000, almenys en
 * la versió actual.  A mida que el client va necessitant noms,  Multifile els
 * hi va donant.  El constructor necessita un nom de fitxer com  a  patró i el
 * número de fitxers a realitzar,  i a partir d'aquests modifica l'extensió de
 * ".000" al numero de fitxers passats, ".999" com a màxim.
 *
 * Mètodes:
 * boolean hasMoreElements();       Retorna  "true"  si  hi  han  més String's
 *												(noms de fitxers) en la llista.
 * Object nextElement();				Retorna el següent String de la llista.
 *
 * Tercera revisió: 06 Nov 2007
 * 					Amb pàgina de codis UNICODE, ie: UTF8, les  extensions  es
 * 					realitzaven malament.
 *
 * Segona revisió:	05 Aug 1999
 *                   Modificació  del  constructor.  No  interesa  per  res el
 *							tamany ni la estructura del fitxer per fer-ne de nous.
 *
 * Primera revisió:  17 Nov 1998
 *                   Modificació d'estructura.
 ****************************************************************************/

package alt.util;
import java.util.Vector;
import alt.util.PathDivide;
import java.util.Enumeration;

public class MultiFile implements Enumeration {
	long numFiles;				// Només n'és long per compatibilitat.
	int  count;
	int  totalElements;
	private Vector files;
	public MultiFile(String name, long numFiles) {
		try {
			files = new Vector();
			count = 0;
			totalElements = 0;
			this.numFiles = numFiles;
			buildFiles(name);         		// Passa el nom del fitxer complet.
		} catch (Exception e) {
			System.out.println("Error en construcció d'arxius [45]: Mòdul MultiFile.");
			System.out.println("name: " + name + "\nnumFiles: " + numFiles);
		}
	}
	private void buildFiles(String fullName) {
		PathDivide name = new PathDivide(fullName); // <- aconsegueix el nom sense extenssió.
		StringBuffer ext = new StringBuffer();
		ext.append("000");
		int aux  = (int) numFiles % 100;
		int CEND = (int) numFiles / 100;	//Límit de centenes.
		int DEND = (int) aux / 10;		 	//Límit de decenes.
		int UEND = (int) aux % 10;			//Límit de unitats.
		if ( CEND > 9)
			try {
				System.out.println("Atenció! s'han demanat " + CEND + DEND + UEND +
										 " arxius i només s'en crearan 1000." +
										 " \nPremi Return per continuar . . .");
				System.in.read();
			} catch (Exception e) {
				System.out.println("Error de E/S [60]: Mòdul MultiFile.");
      }                                                                    //
/*		EL primer 'for' incrementa les centenes fins que arribi al límit CEND.
		El segon 'for' Incrementa decenes a 9 excepte si centenes son CEND.
		El tercer 'for' incrementa unitats a 9 excepte si decenes son DEND.	*/
		for (int c = 0; c <= CEND && c<10; c++) {
			char valc = (char) (c | 0x30);
			for (int d = 0; (c!=CEND || d<=DEND) && d<10; d++) {
				char vald = (char) (d | 0x30);
				for (int u = 0; (c!=CEND || d!=DEND || u<UEND) && u<10; u++) {
					char valu = (char) (u | 0x30);

					ext.setCharAt(0, valc);
					ext.setCharAt(1, vald);
					ext.setCharAt(2, valu);
					totalElements++;
					files.addElement(name.getOnlyPath() + name.getOnlyName() + "." + ext);
				}
			}
		}
	}
	public Object nextElement() {
		try {
			return files.elementAt(count++);
		} catch (Exception e) {
			System.out.println("No hi han més elements [86]: Mòdul MultiFile.");
			return null;
		}
	}
	public boolean hasMoreElements() {
		if (count < totalElements) return true;
		return false;
	}
}                                                                          //


/*
	long size;
	protected MultiFile(File f, long size) {
		try {
			count = 0;
			totalElements = 0;
			files = new Vector();
			this.size = size;
			numFiles = f.length() / this.size;
						if (f.length() % size > 0) numFiles++;
			buildFiles(f.getPath());
		} catch (Exception e) {
			System.out.println("Error en construcci� d'arxius: \n\t" + e);
		}
	}
*/
