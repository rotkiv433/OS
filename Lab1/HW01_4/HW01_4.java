package HW01_4;

import java.io.*;

public class HW01_4 {
    public static void main(String[] args) {
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rezultati.csv"),"UTF8"));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("rezultati.tsv"),"UTF8"));
                ){

            String currentLine;
            //ЧИТАЊЕ НА ПРВА ЛИНИЈА ОД .csv FILE
            String firstLine = br.readLine();
            String[] firstSplit = firstLine.split(",");
            //ZAPISUVANJE VO .tsv FILE
            //PRVA LINIJA
            //Студент,Предмет1,Предмет2,Предмет3,...
            for(int i=0;i<firstSplit.length;i++) {
                bw.write(firstSplit[i]+"\t");
            }

            //ЧИТАЊЕ НА ОСТАНАНТИ ЛИНИИ
            //брИндекс, оценка1, оценка2, оценка3,...
            while((currentLine = br.readLine())!=null) {
                String[] split = currentLine.split(",");
                //ЗАПИШУВАЊЕ НОВ РЕД ВО .tsv FILE
                bw.write("\n");
                //ЗАПИШИ БРОЈ НА ИНДЕКС И ТАБУЛАТОР
                bw.write(split[0]+"\t");
                Double prosek=0.0;
                
                //ЗАПИШУВАЊЕ НА ОЦЕНКИТЕ ВО .tsv FILE
                //СОБИРАЊЕ НА ОЦЕНКИТЕ ОД .csv FILE ЗА ФОРМИРАЊЕ ПРОСЕК
                for(int i=1;i<split.length;i++) {
                    prosek += Double.parseDouble(split[i]);
                    bw.write(split[i]+"\t");
                }
                
                //ФОРМИРАЊЕ НА ПРОСЕК; ЗБИР НА СИТЕ ОЦЕНКИ(prosek) ПОДЕЛЕН СО БРОЈ НА ОЦЕНКИ
                prosek = prosek/(split.length-1);
                //ПЕЧАТЕЊЕ НА ЕКРАН; БРОЈ НА ИНДЕКС И ПРОСЕЧНА ОЦЕНКА ОД ВНЕСЕНИТЕ
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
