import java.io.*;
import alt.util.PathDivide;
class ProvaPathDivide {
	public static void main(String args[]) {
		if (args.length > 0) {
			try {
				File f = new File(args[0]);
				PathDivide nom = new PathDivide(f.getAbsolutePath());
				System.out.println("S'ha entrat el arxiu " + nom);
				System.out.println("El seu nom:       " + nom.getOnlyName());
				System.out.println("Amb extensi� :    " + nom.getOnlyExtension());
				System.out.println("Amb via d'acc�s : " + nom.getOnlyPath());
			} catch (Exception e) {
				System.out.println("Error : " + e);
			}
		}
		else
				System.out.println("Introdueixi un nom d'arxiu");
	}
}

