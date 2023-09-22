import java.util.*;
import coffee.*;
public class checkedList{
    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List ProbablyLatte){
        ProbablyLatte.add(new Breve());
    }
    public static void main(String[] args) {
        List<Latte> lattes1=new ArrayList<>();
        oldStyleMethod(lattes1);//Quietly accepts a Cat
        List<Latte> lattes2=Collections.checkedList(new ArrayList<Latte>(),Latte.class);
        try{
            oldStyleMethod(lattes2);//Throws an exception
        }catch(Exception e){
            System.out.println(e);
        }
        //Derived types work fine:
        List<Coffee> pets=Collections.checkedList(new ArrayList<Coffee>(),Coffee.class);
        pets.add(new Latte());
        pets.add(new Breve());
    }
}