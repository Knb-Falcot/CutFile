/** **************************** PathDivide *********************************
 *
 * @author			Eduard Calveras
 * @version			@(#)PathDivide.java 1.0, 19 Nov 1998
 *
 * Aqueta classe descomposa el "path" (nom complet d'un fitxer) amb l'extensi¢
 * del fitxer, nom i cam¡. El construcctor necessita per tant tot el nom
 * complet del fitxer per dividir-lo amb les respectives parts.
 *
 * MŠtodes:
 * getOnlyName();				Retorna una  "String" (cadena de caracters) corres-
 *									ponent al nom del fitxer.
 * getOnlyExtension();		Retorna una "String" corresponent a l' extensi¢ del
 *									fitxer.
 * getOnlyPath();				Retorna una  "String"  corresponent al cam¡,  en el
 *									qual est… el fitxer.
 * toString();					Retorna una "String" amb el nom complet.
 *
 ****************************************************************************/

package alt.util;

public class PathDivide {
	String absolutePath;
	String path;
	String name;
	String ext;
	public PathDivide(String absolutePath) {
		this.absolutePath = absolutePath;
		descomposa(this.absolutePath);
	}
	private void descomposa(String cadena) throws IndexOutOfBoundsException {
		try {
			int aux   = cadena.lastIndexOf(".");
			int punt  = (aux == -1 ? cadena.length(): aux);
				 aux   = cadena.lastIndexOf("/");
				 aux   = (aux == -1 ? cadena.lastIndexOf("\\"): aux);
			int barra = (aux == -1 ? 0 : aux+1);
			ext  = cadena.substring(punt +1);
			name = cadena.substring(barra , punt);
			path = cadena.substring(0, barra);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index perdut! [20]: M•dul PathDivide.");
			throw e;
		}

	}
	public String getOnlyName() {
		if (name == "\0") return "";
		return name;
	}
	public String getOnlyPath() {
		if (path == "\0") return "";
		return path;
	}
	public String getOnlyExtension() {
		if (ext == "\0") return "";
		return ext;
	}
	public String toString() {
		return absolutePath;
	}
}                                                                          //
