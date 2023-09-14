import watercolors.*;
import java.util.*;
import util.*;
import static watercolors.WaterColors.*;
public class WatercolorSets {
    public static void main(String[]args){
        Set<WaterColors> set1=EnumSet.range(BRILLIANT_RED,VIRIDIAN_HUE);
        Set<WaterColors> set2=EnumSet.range(CERULEAN_BLUE_HUE,BURNT_UMBER);
        System.out.println("set1:"+set1);
        System.out.println("set2:"+set2);
        System.out.println("union(set1,set2):"+Sets.union(set1,set2));
        Set<WaterColors> subset=Sets.intersection(set1,set2);
        System.out.println("intersection(set1,set2):"+subset);
        System.out.println("difference(set1,subset):"+Sets.difference(set1,subset));
        System.out.println("difference(set2,subset):"+Sets.difference(set2,subset));
        System.out.println("complement(set1,set2):"+Sets.complement(set1,set2));
    }
}
