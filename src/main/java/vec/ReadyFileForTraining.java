package vec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by zhangx on 16/5/24.
 */
public class ReadyFileForTraining {

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(new File("TrainingData/diming.txt")));
        PrintWriter pw = new PrintWriter(new File("TrainingData/diming_ready.txt"));
        String line = br.readLine();
        while(line!=null){
            char[] cs = line.toCharArray();
            StringBuilder sb = new StringBuilder();
            for(char c: cs){
                sb.append(c+" ");
            }
            pw.println(sb.toString().substring(0,sb.length()-1));
            line=br.readLine();
        }
        br.close();
        pw.close();
    }
    
}
