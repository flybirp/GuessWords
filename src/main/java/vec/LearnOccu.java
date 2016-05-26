package vec;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangx on 16/5/24.
 */
public class LearnOccu {
    public static void main(String[] args)throws Exception{

        String filePath = "TrainingData/diming";

        BufferedReader br = new BufferedReader(new FileReader(new File(filePath+"_ready.txt")));
        String line = br.readLine();
        Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();


        int i =0;
        while(line!=null){


            for(String s: line.split(" ")){
                if(map.containsKey(s)){
                    ArrayList<Integer> al = map.get(s);
                    al.add(i);
                    map.put(s,al);
                }else{
                    ArrayList<Integer> al = new ArrayList<Integer>();
                    al.add(i);
                    map.put(s, al);
                }
            }

            line=br.readLine();
           i++;
        }
        br.close();


        OccuModel occu = new OccuModel(map, (float)i);

        //write model to disk
        FileOutputStream fos = new FileOutputStream(filePath+"_occu.model");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(occu);
        oos.close();

//        //read model out
//        FileInputStream fis = new FileInputStream("occu.model");
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        OccuModel another_map =  (OccuModel)ois.readObject();

        OccuModel another_map = new OccuModel();
        another_map.loadModel(filePath+"_occu.model");


        for(Map.Entry<String,ArrayList<Integer>> e: another_map.getMap().entrySet()){
            System.out.println(e.getKey());
            for(Integer k:e.getValue()){
                System.out.print(k+" ");
            }
            System.out.println();
        }
        System.out.println("how many lines there is : "+another_map.getLines());
    }
}
