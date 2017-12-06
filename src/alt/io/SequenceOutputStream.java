/** ******************** SequenceOutputStream *******************************
 *
 *	@author      Eduard Calveras  1998-99
 *	@version     @(#)SequenceOutputStream.java 1.1, 04 Aug 1999
 *
 * Aquesta classe anomenada SequenceOutputStream (fluxe de sortida sequencial)
 * permet que  a  partir  d'un  origen de dades  s'en  realitzin  diferents de
 * sortida. Al constructor nom‚s li cal, una  "Enumeration"  que contingui els
 * noms  dels  "OutputStreams"  que  s'anir…n  enlla‡ant  a  mida que els vagi
 * necessitant, i el tamany que assolir… cadascun d'ells.
 *
 * mŠtodes:
 * void write(byte buf[]);				Escriu una matriu de bytes en  el fluxe de
 *                                  de sortida.
 * void write(int b);               Escriu un unic byte el el fluxe de sortida
 *                                  i com el parametre ‚s  un "int" permet que
 *                                  es cridi  al  mŠtode amb expressions sense
 *                                  tindre que realitzar conversi¢ns a byte.
 * void write(byte buf[], int off, int len);   Escriu "len" bytes en la matriu
 *                                  "buf[]" comen‡ant a partir de "buf[off]".
 * void flush();                    Inicialitza  l'estat  de   la  sortida  de
 *                                  manera que es netejen tots els buffers.
 * void close();                    Tanca  el  fluxe  de  sortida. Els intents
 *                                  d' escritura   posteriors  g enerar…n  una
 * 										   IOException.
 *
 * Primera revisio: 19 Nov 1998
 *						  La classe no llen‡a cap "IOExcepcion" (excepci¢ de E/S).
 ****************************************************************************/

package alt.io;
import java.util.Enumeration;
import java.io.*;

public class SequenceOutputStream extends OutputStream {
	OutputStream f;
	private Enumeration file;
	long size;
	int count;
	public SequenceOutputStream(Enumeration file, long size) throws Exception {
		try {
			count = 0;
			this.file = file;
			this.size = size;
			if (file.hasMoreElements()) f = (OutputStream) this.file.nextElement();
		} catch (Exception e) {
			System.out.println("Error de construcci¢[10]: M•dul SequenceOutputStream.");
			throw e;
		}
	}
	public void write(byte buf[]) throws IOException {
		try {
			f.write(buf);
			count+= buf.length;
			if (count >= size) {
				f.close();
				if (file.hasMoreElements()) f = (OutputStream) file.nextElement();
				count = 0;
			}
		} catch (IOException e) {
			System.out.println("Error d'escritura[20]: M•dul SequenceOutputStream.");
			throw e;
		}
   }                                                                       //
	public void write(int b) throws IOException {
		try {
				f.write(b);
				count++;
				if (count >= size) {
					f.close();
					if (file.hasMoreElements()) f = (OutputStream) file.nextElement();
					count = 0;
				}
		} catch (IOException e) {
			System.out.println("Error d'escritura[21]: M•dul SequenceOutputStream.");
			throw e;
		}
	}
	public void write(byte buf[], int off, int len) throws IOException {
		try {
			f.write(buf, off, len);
			count+= len;
			if (count >= size) {
				f.close();
				if (file.hasMoreElements()) f = (OutputStream) file.nextElement();
				count = 0;
			}
		} catch (IOException e) {
			System.out.println("Error d'escritura[22]: M•dul SequenceOutputStream.");
			throw e;
		}
	}
	public void flush() throws IOException {
		try {
			f.flush();
		} catch (IOException e) {
			System.out.println("Error al refrescar fitxer[30]: M•dul SequenceOutputStream.");
			throw e;
		}
	}
	public void close() throws IOException {
		try {
			f.close();
		} catch (IOException e) {
			System.out.println("Error al tancar fitxer[40]: M•dul SequenceOutputStream.");
			throw e;
		}
	}
}                                                                          //

