interface SelfBoundedSetter<T extends SelfBoundedSetter<T>>{void set(T arg);}
interface Setter extends SelfBoundedSetter<Setter>{}
public class SelfBoundingAndConvariantArguments {
    void testA(Setter s1,Setter s2,SelfBoundedSetter sbs){
        s1.set(s2);
        //s1.set(sbs);//Error
    }
}
