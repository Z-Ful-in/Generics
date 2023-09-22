import java.math.*;
import java.util.concurrent.*;
import java.util.*;

interface Combiner<T>{ T combine(T x, T y);}
interface UnaryFunction<R,T>{ R function(T x);}
interface Collector<T> extends UnaryFunction<T,T>{
    T result();// Extract the result of collecting parameter
}
interface UnaryPredicate<T>{boolean test(T x);}
public class Functional {
    public static<T> T reduce(Iterable<T>seq, Combiner<T> combiner){
        Iterator<T> it=seq.iterator();
        if(it.hasNext()){
            T result=it.next();
            while(it.hasNext()){
                result=combiner.combine(result,it.next());
            }
            return result;
        }
        return null;
    }
    // take a function object and call it on each object in
    // the list, ignoring the return value. The function
    // object may act as a collector parameter, so it is
    // returned at the end.
    public static<T> Collector<T>
        forEach(Iterable<T> seq, Collector<T> func){
        for (T t : seq) {
            func.function(t);
        }
        return func;
    }
    // create a list of results by calling a
    // function object for each object in the list:
    public static<T,R> List<R>
        transform(Iterable<T> seq, UnaryFunction<R,T> func){
        List<R> result=new ArrayList<>();
        for(T t: seq)
            result.add(func.function(t));
        return result;
    }
    // Apply a unary predicate to each item in a sequence,
    // and return a list of items that produced "true":
    public static<T>List<T>
        filter(Iterable<T> seq, UnaryPredicate<T> pred){
        List<T> result=new ArrayList<>();
        for(T t: seq)
            if(pred.test(t))
                result.add(t);
        return result;
    }
    // to use the above generic methods, we need to create
    // function objects to adapt to our particular needs:
    static class IntegerAdder implements Combiner<Integer>{
        @Override
        public Integer combine(Integer x, Integer y) {
            return x+y;
        }
    }
    static class IntegerSubtracter implements Combiner<Integer>{
        @Override
        public Integer combine(Integer x, Integer y) {
            return x-y;
        }
    }
    static class BigDecimalAdder implements Combiner<BigDecimal>{
        @Override
        public BigDecimal combine(BigDecimal x, BigDecimal y) {
            return x.add(y);
        }
    }
    static class BigIntegerAdder implements Combiner<BigInteger>{
        @Override
        public BigInteger combine(BigInteger x, BigInteger y) {
            return x.add(y);
        }
    }
    /*static class AtomicLongAdder implements Combiner<AtomicLong>{
        @Override
        public AtomicLong combine(AtomicLong x, AtomicLong y) {
            // not clear whether this is meaningful
            return new AtomicLong(x.addAndGet(y.get()));
        }
    }*/
    // We can even make a UnaryFunction with an "ulp"
    // (Units in the last place):
    static class BigDecimalUlp implements UnaryFunction<BigDecimal,BigDecimal>{
        @Override
        public BigDecimal function(BigDecimal x) {
            return x.ulp();
        }
    }
    static class GreaterThan<T extends Comparable<T>>
        implements UnaryPredicate<T>{
        T bound;
        public GreaterThan(T bound){ this.bound=bound; }

        @Override
        public boolean test(T x) {
            return x.compareTo(bound)>0;
        }
    }
    static class MultiplyingIntegerCollector
        implements Collector<Integer>{
        private Integer val=1;

        @Override
        public Integer function(Integer x) {
            val*=x;
            return val;
        }
        public Integer result(){ return val; }
    }

    public static void main(String[] args) {
        // Generics,varargs & boxing working together:
        List<Integer> li=Arrays.asList(1,2,3,4,5,6,7);
        Integer result=reduce(li,new IntegerAdder());
        System.out.println(result);

        result=reduce(li,new IntegerSubtracter());
        System.out.println(result);

        System.out.println(filter(li,new GreaterThan<>(4)));

        System.out.println(forEach(li,new MultiplyingIntegerCollector()).result());

        MathContext mc=new MathContext(7);
        List<BigDecimal> lbd=Arrays.asList(
                new BigDecimal(1.1,mc),new BigDecimal(2.2,mc),
                new BigDecimal(3.3,mc),new BigDecimal(4.4,mc)
                );
        BigDecimal rbd=reduce(lbd,new BigDecimalAdder());
        System.out.println(rbd);
        System.out.println(filter(lbd,new GreaterThan<>(new BigDecimal(3))));

        List<BigInteger> lbi=new ArrayList<>();
        BigInteger bi=BigInteger.valueOf(11);
        for (int i = 0; i < 11; i++) {
            lbi.add(bi);
            bi=bi.nextProbablePrime();
        }
        System.out.println(lbi);

        BigInteger rbi=reduce(lbi,new BigIntegerAdder());
        System.out.println(rbi);
        // The sum of this list of primes is also prime:
        System.out.println(rbi.isProbablePrime(5));
    }
}
