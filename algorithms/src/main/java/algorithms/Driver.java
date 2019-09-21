package algorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Driver {

	public static void main(String[] args) {

		try {
			BufferedReader br = new BufferedReader(new FileReader("edges.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		Scanner scanner;
//		try {
//			File file = new File("edges.txt");
//			scanner = new Scanner(file);
//			while (scanner.hasNextLine())
//				System.out.println(scanner.nextLine());
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
