package vec;

import java.util.ArrayList;

/**
 * Created by zhangx on 16/5/24.
 */
public class MakeGuess {


    public static float sigmoid(float val){
        return (float)(1/( 1 + Math.pow(Math.E,(-1*val))));
    }

    public static float dotProduct(float[] a, float[] b){
        float dot = 0F;
        if(a.length==b.length){
            for(int i=0; i<a.length; i++){
                dot +=a[i]*b[i];
            }
        }else{
            System.out.println("two vectors have different length");
        }
        return dot;
    }


    public static float[] cumulate(ArrayList<float[]> vecs){

        float[] fi = new float[vecs.get(0).length];

        for(float[] v: vecs){
            for(int i=0; i<v.length; i++){
                fi[i]+=v[i];
            }
        }
        return fi;
    }

    public static String removeSpecificChar(String s, int index){
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for(int i=0; i<chars.length; i++){
            if(i!=index){
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public static float calChainProp(OccuModel occu, Word2VEC vec, String n_s){

        float chain_prop = 1F;
        while(n_s.length()>1){

            //int index = (int)(Math.random()*n_s.length());
            int index =0;

            String target = n_s.charAt(index)+"";
            float[] a = vec.getWordVector(target);
            n_s = removeSpecificChar(n_s,index);
            ArrayList<float[]> c_vectors = new ArrayList<float[]>();
            for(char c: n_s.toCharArray()){
                String ch = c+"";
                c_vectors.add(vec.getWordVector(ch));
            }
            float[] c = cumulate(c_vectors);

            float prop = sigmoid(dotProduct(a, c));
            chain_prop = chain_prop * prop;
        }
        if(n_s.length()==1){
            String target = n_s.charAt(0)+"";
            float size = 0;
            if(occu.getMap().containsKey(target)){
                size = (float)occu.getMap().get(target).size();
            }
            float single_char_prop = size/occu.getLines();
            chain_prop = chain_prop * single_char_prop;
        }
        return chain_prop;
    }

    public static float calProp2(OccuModel occu, Word2VEC vec, String s){

        if(s.length()==1){
            String target = s.charAt(0)+"";
            float size = 0;
            if(occu.getMap().containsKey(target)){
                size = (float)occu.getMap().get(target).size();
            }
            float single_char_prop = size/occu.getLines();
            return single_char_prop;
        }
        //general cases
        float tempHighest =0F;

        //when i<s.length() ; it calculates P(A4|A3, A2, A1), P(A3|A4, A2, A1), P(A2|A4, A3, A1), P(A1|A4, A3, A2); and find the maximum
        //when i<1 ; it calculates P(A4|A3, A2, A1)

        for(int i =0; i<1; i++){

            String target = s.charAt(0)+"";

            float[] a = vec.getWordVector(target);

            //find rest
            ArrayList<float[]> c_vectors = new ArrayList<float[]>();

            String n_s = removeSpecificChar(s,0);
            for(char c: n_s.toCharArray()){
                String ch = c+"";
                c_vectors.add(vec.getWordVector(ch));
            }

            float[] c = cumulate(c_vectors);

            float prop = sigmoid(dotProduct(a, c));


            //System.out.print(prop+" ");

            prop = prop * calChainProp(occu, vec, n_s);

            //System.out.print("chain_prop "+calChainProp(occu, vec, n_s));
            //prop = prop * co_occur_prop(n_s, occu);

            //System.out.println(prop+" ");

            if(prop>tempHighest)tempHighest=prop;
        }
        return tempHighest;
    }



}
