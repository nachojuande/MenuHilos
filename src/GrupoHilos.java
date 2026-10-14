import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GrupoHilos extends Thread {
	public void run() {
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		String linea = "";

		try {
			File f = new File("carta.txt");
			File f2 = null;
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			
			switch (currentThread().getName()) {
			case "HiloPrincipales":
				f2 = new File("principales.txt");
				break;
			case "HiloSegundos":
				f2 = new File("segundos.txt");
				break;
			case "HiloPostres":
				f2 = new File("postres.txt");
				break;
			}
			fw = new FileWriter(f2);
			bw = new BufferedWriter(fw);
			while (linea != null) {
				linea = br.readLine();
				if (linea != null) {
					switch (currentThread().getName()) {
					case "HiloPrincipales":
						if (linea.charAt(0)=='1') {
							if (!linea.substring(2).equals("PRIMEROS")) {
								bw.write(linea.substring(2) + "\r\n");
								System.out.println("El hilo " + "HiloPrincipales escribe... " + linea.substring(2));
							}
						}
						break;
					case "HiloSegundos":
						if (linea.charAt(0)=='2') {
							if (!linea.substring(2).equals("SEGUNDOS")) {
								bw.write(linea.substring(2) + "\r\n");
								System.out.println("El hilo " + "HiloSegundos escribe... " + linea.substring(2));
							}
						}
						break;
					case "HiloPostres":
						if (linea.charAt(0)=='3') {
							if (!linea.substring(2).equals("POSTRES")) {							
								bw.write(linea.substring(2) + "\r\n");
								System.out.println("El hilo " + "HiloPostres escribe... " + linea.substring(2));
							}
						}
						break;
					}
				}
			}
		} catch (IOException ex) {
			System.out.println("No existe el fichero");
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				System.out.println("Error de E/S");
			}
		}

	}

}