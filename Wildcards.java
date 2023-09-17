public class Wildcards {
    static void rawArgs(Holder holder,Object arg){
        //holder.set(arg);  //Warning:
        // unchecked call to set(T) as a member of the raw type Holder

        //holder.set(new Wildcards());  // Same Warning
        //T t=holder.get();
        Object obj=holder.get();  //OK, but type information has been lost
    }
    static void unboundedArg(Holder<?> holder, Object arg){
        //holder.set(arg); //Error :
        // set (capture <?>) in Holder<capture <?>> cannot be applied to (Object)
        //holder.set(new Wildcards()); //Same error
        // T t=holder.get();
        Object obj=holder.get(); // OK, but type information has been lost
    }
    static <T> T exact1(Holder<T> holder){
        T t=holder.get();
        return t;
    }
    static <T> T exact2(Holder<T> holder,T arg){
        holder.set(arg);
        T t=holder.get();
        return t;
    }
    static <T> T wildSubtype(Holder<? extends T> holder,T arg){
        //holder.set(arg); //Error:
        // set (capture <? extends T>) in Holder<capture <? extends T>>
        // cannot be applied to (T)
        T t=holder.get();
        return t;
    }
    static<T> void wildSupertype(Holder<? super T>holder, T arg){
        holder.set(arg);
        //T t=holder.get(); Error:
        // Incompatible types: found Object, required T
        Object obj=holder.get();    //OK, but type information has been lost
    }

    public static void main(String[] args) {
        Holder raw=new Holder<Long>();
        raw=new Holder();
        Holder<Long> qualified=new Holder<>();
        Holder<?> unbounded=new Holder<Long>();
        Holder<? extends Long> bounded=new Holder<Long>();
        Long lng=1L;

        rawArgs(raw,lng);
        rawArgs(qualified,lng);
        rawArgs(unbounded,lng);
        rawArgs(bounded,lng);

        unboundedArg(raw,lng);
        unboundedArg(qualified,lng);
        unboundedArg(unbounded,lng);
        unboundedArg(bounded,lng);

         Object r1=exact1(raw); //Warning:
        // Unchecked conversion from Holder to Holder<T>
        Long r2=exact1(qualified);
        Object r3=exact1(unbounded); // Must return Object
        Long r4=exact1(bounded);

        // Long r5=exact2(raw,lng); //Warning:
        // Unchecked conversion from Holder to Holder<Long>
        Long r6=exact2(qualified,lng);
        // Long r7=exact2(unbounded,lng); //Error:
        // exact2 (Holder<T>,T) in Wildcards cannot be applied to
        // (Holder<capture <?>>,Long)

        // Long r8=exact2(bounded,lng); //Error:
        // exact2 (Holder<T>,T) in Wildcards cannot be applied to
        // (Holder<capture <? extends Long>>,Long)

        // Long r9=wildSubtype(raw,lng); //Warning:
        // Unchecked conversion from Holder to Holder<? extends Long>

        Long r10=wildSubtype(qualified,lng);
        // OK, but can only return Object:
        Object r11=wildSubtype(unbounded,lng);
        Long r12=wildSubtype(bounded,lng);

        // wildSupertype(raw,lng); //Warning:
        // Unchecked conversion from Holder to Holder<? super Long>
        wildSupertype(qualified,lng);
        // wildSupertype(unbounded,lng); //Error:
        // wildSupertype (Holder<? super T>,T) in Wildcards cannot be
        // applied to (Holder<capture <? extends Long>>,Long)

        // wildSupertype(bounded,lng); //Error:
        // wildSupertype (Holder<? super T>,T) in Wildcards cannot be
        // applied to (Holder<capture <? extends Long>>,Long)
    }
}
