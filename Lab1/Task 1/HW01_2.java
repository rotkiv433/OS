
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<Character> list = new ArrayList<>();
        try (
                FileInputStream in = new FileInputStream("izvor.txt");
                InputStreamReader isr = new InputStreamReader(in, "UTF8");
                FileOutputStream out = new FileOutputStream("destinacija.txt");
                OutputStreamWriter osw = new OutputStreamWriter(out, "UTF8");
                ) {
            int c;
            while((c = isr.read()) != -1) {
                list.add((char) c);

            }
            Collections.reverse(list);
            for(Character k : list) {
                osw.write(k);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
