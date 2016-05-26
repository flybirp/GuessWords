package vec;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhangx on 16/5/24.
 */
public class OccuModel implements Serializable{

    public Map<String, ArrayList<Integer>> getMap() {
        return map;
    }

    public void setMap(Map<String, ArrayList<Integer>> map) {
        this.map = map;
    }

    public Map<String, ArrayList<Integer>> map;

    public float getLines() {
        return lines;
    }

    public void setLines(float lines) {
        this.lines = lines;
    }

    public float lines;

    OccuModel(){

    }

    OccuModel(Map<String, ArrayList<Integer>> map, float lines){
        this.map = map;
        this.lines = lines;
    }


    public void loadModel(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            OccuModel another_map = (OccuModel) ois.readObject();
            this.map = another_map.getMap();
            this.lines = another_map.getLines();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
