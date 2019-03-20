import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		String path;
		path = input.nextLine();
		
		File[] files;
		
		try {
			double sum=0.0;
			int brojac=0;
			File f;
			f = new File(path);
			files = f.listFiles();
			for(File file: files) {
				if(file.getName().endsWith(".txt")) {
					brojac++;
					sum+=file.length();
					
				}
					
			}
			System.out.println(sum/brojac);
		}
		catch(Exception e) {
			System.exit(-1);
		}		
	}
}
