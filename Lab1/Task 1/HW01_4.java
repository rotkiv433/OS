
import java.io.*;

public class HW01_4 {
    public static void main(String[] args) {
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rezultati.csv"),"UTF8"));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("rezultati.tsv"),"UTF8"));
                ){
            String currentLine;
            String firstLine = br.readLine();
            String[] firstSplit = firstLine.split(",");
            for(int i=0;i<firstSplit.length;i++) {
                bw.write(firstSplit[i]+"\t");
            }


            while((currentLine = br.readLine())!=null) {
                String[] split = currentLine.split(",");
                bw.write("\n");
                bw.write(split[0]+"\t");
                Double prosek=0.0;

                for(int i=1;i<split.length;i++) {
                    prosek += Double.parseDouble(split[i]);
                    bw.write(split[i]+"\t");
                }
                prosek = prosek/(split.length-1);
                System.out.println(split[0]+" "+prosek);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
