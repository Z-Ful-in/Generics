import util.*;
class FArray{
    public static <T> T[] fill(T[] a,Generator<T> gen){
        for (int i = 0; i < a.length; i++) {
            a[i]=gen.next();
        }
        return a;
    }
}

