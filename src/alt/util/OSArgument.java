/** *********************** OSArgument **************************************
 *
 * @author			Eduard Calveras
 * @version			@(#)OSArgument.java 1.0, 04 Aug 1999
 *
 * Aquesta classe facilita  el  tractament  de  par�metres  que se li passen al
 * programa a trav�s del OS  (Sistema Operatiu).  Aquesta els tracta i crea una
 * llista relacionant  el  par�metre amb el valor que l'acompanya, si aquest hi
 * �s.  El par�metre,  per�,  ha de tenir una estructura per  poder ser tactat,
 * per exemple:
 *						<par�metre>[<:valor>]
 *
 * Per tant els dos punts ":" s�n essencials alhora de posar-hi un valor. La
 * classe tampoc contempla el caracter "/" o "-" ni la igualtat entre ells.
 * Al contructor se li haur� de passar l'String "args[]" que captura "main()".
 *
 * M�todes:
 * boolean exist(String s);   Retorna "true" si el par�metre est� a la llista.
 * int getInt(String s);		Retorna el valor "int" del par�metre "s".
 * String getString(String s); Retorna la cadena que acompanya l'argument "s".
 *
 ****************************************************************************/

package alt.util;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class OSArgument {
	public Hashtable table;
	public OSArgument(String args[]) {
		table = new Hashtable();
		try {
			for (int c=0; args[c] != null; c++) {
				StringTokenizer param = new StringTokenizer(args[c], ":");
				while (param.hasMoreTokens())
					table.put(param.nextToken(), (param.hasMoreTokens() ? param.nextToken():"true"));
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
	}
	public int getInt(String s) throws Exception {
		try {
			return Integer.parseInt((String) table.get(s));
		} catch (Exception e) {
			System.out.println("Format de paràmetre no vàlid: " + s +
									(table.get(s)==(String) "true" ? ":?" : (String) table.get(s)));
			throw e;
		}
	}
	public String getString(String s) {
		return (String) table.get(s);
	}
	public boolean exist(String s) {
		try {
			if (table.get(s) != null) return true;
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}                                                                          //
