/** ********************** FileOutputStreamEnumeration **********************
 * @author        Eduard Calveras
 * @version       @(#)FileOutputStreamEnumeration.java 1.0, 04 Aug 1999
 *
 * Aquesta classe és una "Enumeration" de FileOutputStream's (arxius físics de
 * escritura) i  té la tasca de proporcianar-se'les a una altra classe que les
 * vagi necessitant,  no obstant això,  la classe en sí  no s'inventa els noms
 * "FileOutputStream's" ,  per tant  els  hi  haurem  de  passar amb una altra
 * "Enumeration" al constructor, la qual realitzarem apart.
 *
 * Métodes:
 * boolean hasMoreElements();      Retorna "true" si han més FileOutputStreams
 *                                 en la "Enumeration" per extreure.
 * Object nextElement();           Retorna la següent "FileOutputStream" de la
 *                                 "Enumeration".
 *
 ****************************************************************************/

package alt.io;
import java.io.FileOutputStream;
import java.util.Enumeration;

public class FileOutputStreamEnumeration implements Enumeration<FileOutputStream> {
	private Enumeration<String> noms;
	public FileOutputStreamEnumeration(Enumeration<String> noms) {
		this.noms = noms;
	}
	public boolean hasMoreElements() {
		return noms.hasMoreElements();
	}
	public FileOutputStream nextElement() {
		try {
			return new FileOutputStream(noms.nextElement().toString());

		} catch (Exception e) {
			System.out.println("No hi han m�s arxius en la llista! [32]: M�dul FileOutputStreamEnumeration.");
			System.out.println(e);
			return null;
		}
	}
}
