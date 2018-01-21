/********************** SequenceOutputTextStream *******************************/


package alt.io;
import java.util.Enumeration;
import java.io.*;


/**
   Aquesta classe anomenada SequenceOutputTextStream (fluxe de sortida de text
   sequencial) permet que a partir d'un origen de dades de text s'en realitzin  
   diferents de sortida.  Al constructor només li cal, una  "Enumeration"  que 
   contingui els  "OutputStreams"  que  s'aniràn enllaçant a mida que els vagi 
   necessitant, quan assoleixi la "mida" especificada de cadascun d'ells.
   
   Com a principal característica, a diferencia de la {@link}SequenceOutputStream, no
   talla les linies de text.  És a dir,  comença  el  següent  OutputStream al 
   finalitzar amb un salt de linia.

  	@author      Eduard Calveras
  	@version     1.0, Jan-2018
  	@since		 1.8
  	@see 		 OutputStream
*/  
public class SequenceOutputTextStream extends OutputStream {
	OutputStream f;
	private Enumeration<OutputStream> file;
	long size;
	int count;
	int max_count;
	byte old_value;
	private static byte RET = '\n';
	/**
	 * SequenceOutputTextStream Constructor only needs a Enumeration that must bring 
	 * all the OutputStreams, and thus it will link when each OutputStream
	 * become to fill the size specified in "size" 
	 * @param enu			Enumeration containing the Outputstreams names.
	 * @param size			Max size of each OutputStream of the Enumeration.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SequenceOutputTextStream(Enumeration<? extends OutputStream> enu, long size) throws Exception {
		try {
			max_count = 1024;
			count = 0;
			this.file = (Enumeration<OutputStream>) enu;
			this.size = size;
			if (enu.hasMoreElements()) f = this.file.nextElement();
		} catch (Exception e) {
			System.out.println("Error de construcció[10]: Mòdul SequenceOutputStream.");
			throw e;
		}
	}
	
	/*
	 * Escriu un vector de bytes en el fluxe de sortida.
	 * 
	 * @param buf[] 	Vector o Buffer de Bytes que s'han d'escriure.
	 */
	public void write(byte buf[]) throws IOException {
		try {
			write_and_check(buf, 0, buf.length);
		} catch (IOException e) {
			System.out.println("Error d'escritura[20]: Mòdul SequenceOutputStream.");
			throw e;
		}
	}                                                                      
	
	/*
	 * Escriu un unic byte el el fluxe de sortida i com el parametre és  
	 * un "int" permet que es cridi  al  métode amb expressions sense
	 * tenir que realitzar conversions a byte.
	 * 
	 * @param b		byte que cal escriure.
	 */
	public void write(int b) throws IOException {
		try {
			  byte[] buf = new byte[4];
			/*buf[0] = (byte) (b >> 24);
			  buf[1] = (byte) (b >> 16);
			  buf[2] = (byte) (b >> 8);*/
			  buf[3] = (byte) (b);
			  write_and_check(buf, 3, 1);
		} catch (IOException e) {
			System.out.println("Error d'escritura[21]: Mòdul SequenceOutputStream.");
			throw e;
		}
	}
	
	/*
	 * Escriu "len" bytes en el vector "buf[]" començant a partir de l'offset "off".
	 * 
	 * @param buff[] 	Vector o Buffer de Bytes que cal esciure.
	 * @param off		Offset, posició en el Vector en la que començar a escriure.
	 * @param len		Quantitat de bytes a escriure.
	 */
	public void write(byte buf[], int off, int len) throws IOException {
		try {
			write_and_check(buf, off, len);
		} catch (IOException e) {
			System.out.println("Error d'escritura[22]: Mòdul SequenceOutputStream.");
			throw e;
		}
	}
	
	/*
	 * Força l'escriptura al mitja de destí alliberant la cua o buffer utilitzat en la tasca.
	 * Inicialitza també l'estat de la sortida alliberant tots els buffers.
	 */
	public void flush() throws IOException {
		try {
			f.flush();
		} catch (IOException e) {
			System.out.println("Error al refrescar fitxer[30]: Mòdul SequenceOutputStream.");
			throw e;
		}
	}
	
	
	/*
	 * Tanca  el  fluxe  de  sortida. Els intents d' escritura  posteriors  
	 * generaràn una IOException.
	 */
	public void close() throws IOException {
		try {
			f.close();
		} catch (IOException e) {
			System.out.println("Error al tancar fitxer[40]: Mòdul SequenceOutputStream.");
			throw e;
		}
	}
	
	/**
	 * Escriu "len" bytes en el vector "buf[]" començant a partir de l'offset "off".
	 * 
	 * @param buff[] 		Vector o Buffer de Bytes que cal esciure.
	 * @param off			Offset, posició en el Vector en la que començar a escriure.
	 * @param len			Quantitat de bytes a escriure.
	 * @throws IOException
	 */
	private void write_and_check(byte buf[], int off, int len) throws IOException {
		int c_off = off;
		int c_len = len;

		for (int i = 0; i < len; i++)	{
			if (count >= size) {
				if ((buf[i + off] == RET) || (count == (size + max_count))) {
					f.write(buf, off, (i+1));
					f.close();
					if (file.hasMoreElements()) 
						f = file.nextElement();
					else 
						System.out.println("No hi ha més arxius!");
					count = 0;
					c_off += (i+1);
					c_len -= (i+1);
					continue;
				}
			}
		}
		f.write(buf, c_off, c_len);
		count += len;
	}
}                                                                        

