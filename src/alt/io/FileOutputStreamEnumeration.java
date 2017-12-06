/** ********************** FileOutputStreamEnumeration **********************
 * @author        Eduard Calveras
 * @version       @(#)FileOutputStreamEnumeration.java 1.0, 04 Aug 1999
 *
 * Aquesta classe �s una "Enumeration" de FileOutputStream's (arxius f�sics de
 * escritura) i  t� la tasca de proporcianar-se'les a una altra classe que les
 * vagi necessitant,  no obstant aix�,  la classe en s�  no s'inventa els noms
 * "FileOutputStream's" ,  per tant  els  hi  haurem  de  passar amb una altra
 * "Enumeration" al constructor, la qual realitzarem apart.
 *
 * M�todes:
 * boolean hasMoreElements();      Retorna "true" si han m�s FileOutputStreams
 *                                 en la "Enumeration" per extreure.
 * Object nextElement();           Retorna la seg�ent "FileOutputStream" de la
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
			System.out.println("No hi han m�s arxius en la llista! [32]: M�dul FileOutputStreamEnumeration.");
			System.out.println(e);
			return null;
		}
	}
}
