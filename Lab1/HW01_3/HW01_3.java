
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HW01_3 {
    public static void main(String[] args) {
        ArrayList<Character> list = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("izvor.txt"),"UTF8"));
            String currentLine;
            while((currentLine = br.readLine())!=null) {
                list.add('\n');
                char[] niza = currentLine.toCharArray();
                for(Character c : niza) {
                    list.add(c);
                }

            }
            Collections.reverse(list);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("destinacija.txt"),"UTF8"));
            for(Character k:list) {
                bw.write(k);
            }
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
