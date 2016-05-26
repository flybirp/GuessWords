package vec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by zhangx on 16/5/26.
 */
public class Test {

    static int caiming_count = 0;
    static int diming_count = 0;

    public static void test(Word2VEC vec_caiming, Word2VEC vec_diming, OccuModel occu_caiming, OccuModel occu_diming, String s)throws Exception{

        System.out.println();
        float caiming = MakeGuess.calProp2(occu_caiming, vec_caiming, s);
        System.out.println(s+" "+caiming+" 菜名\n");
        float diming = MakeGuess.calProp2(occu_diming, vec_diming, s);
        System.out.println(s+" "+diming+" 地名\n");
        if(caiming>diming){
            System.out.println("答案: 菜名");
            caiming_count++;
        }else{
            System.out.println("答案: 地名");
            diming_count++;
        }

        System.out.println("------------------------------------------------------");
    }


    public static void main(String[] args)throws Exception{
        Word2VEC vec_caiming = new Word2VEC();
        vec_caiming.loadJavaModel("TrainedModels/caiming_javaVector");

        OccuModel occu_caiming = new OccuModel();
        occu_caiming.loadModel("TrainingData/caiming_occu.model");

        Word2VEC vec_diming = new Word2VEC();
        vec_diming.loadJavaModel("TrainedModels/diming_javaVector");

        OccuModel occu_diming = new OccuModel();
        occu_diming.loadModel("TrainingData/diming_occu.model");

//        String[] words = {"建国路","山鸡丁儿","鱼香鸡丝","国贸","油炸臭豆腐"
//                ,"通运门","龙须菜","炝冬笋","建国门","小白菜炒大肠","奶子坊"
//                ,"安定门","浇鸳鸯","烧鱼头","烧槟子","银乐迪","烧百合","炸豆腐"
//                ,"炸面筋","苦瓜炒萝卜","橙子炒肉","桔子面条","西瓜泡面","玉米炒葡萄"
//                ,"西瓜炒香蕉","月饼炒辣椒","鸡头炒鸡肉","巧克力炒饭","爆炒妙脆角","西瓜小学","一碗水前街"
//                ,"天堂堡","棺材坑","跌死狗","屎鱼坑","SM城市广场","大纱帽巷","烤鸭大街","麻婆豆腐大街","麻婆豆腐"};
//        for(String s:words){
//            test(vec_caiming, vec_diming, occu_caiming, occu_diming,s);
//        }

        //南新雅菜名
        BufferedReader br = new BufferedReader(new FileReader(new File("TestSets/北京地名.txt")));
        String line = br.readLine();
        while(line!=null){
            test(vec_caiming, vec_diming, occu_caiming, occu_diming,line);
            line = br.readLine();
        }


        System.out.println("caiming_count:"+caiming_count+"  diming_count:"+diming_count);
    }

}
