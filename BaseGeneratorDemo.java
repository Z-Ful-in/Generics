import util.*;
public class BaseGeneratorDemo {
    public static void main(String[]args){
        Generator<CountObject> gen=BasicGenerator.create(CountObject.class);
        for(int i=0;i<5;i++)
            System.out.println(gen.next());
    }
}
