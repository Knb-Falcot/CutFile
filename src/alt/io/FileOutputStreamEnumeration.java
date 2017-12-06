/** ********************** FileOutputStreamEnumeration **********************
 * @author        Eduard Calveras
 * @version       @(#)FileOutputStreamEnumeration.java 1.0, 04 Aug 1999
 *
 * Aquesta classe Çs una "Enumeration" de FileOutputStream's (arxius f°sics de
 * escritura) i  tÇ la tasca de proporcianar-se'les a una altra classe que les
 * vagi necessitant,  no obstant aixï,  la classe en s°  no s'inventa els noms
 * "FileOutputStream's" ,  per tant  els  hi  haurem  de  passar amb una altra
 * "Enumeration" al constructor, la qual realitzarem apart.
 *
 * Mätodes:
 * boolean hasMoreElements();      Retorna "true" si han mÇs FileOutputStreams
 *                                 en la "Enumeration" per extreure.
 * Object nextElement();           Retorna la segÅent "FileOutputStream" de la
 *                                 "Enumeration".
 *
 ****************************************************************************/

package alt.io;
import java.io.FileOutputStream;
import java.util.Enumeration;

public class FileOutputStreamEnumeration implements Enumeration {
	private Enumeration noms;
	public FileOutputStreamEnumeration(Enumeration noms) {
		this.noms = noms;
	}
	public boolean hasMoreElements() {
		return noms.hasMoreElements();
	}
	public Object nextElement() {
		try {
			return new FileOutputStream(noms.nextElement().toString());

		} catch (Exception e) {
			System.out.println("No hi han mÇs arxius en la llista! [32]: Mïdul FileOutputStreamEnumeration.");
			System.out.println(e);
			return null;
		}
	}
}
