import java.lang.reflect.*;
import java.util.*;
import util.*;
class MixinProxy implements InvocationHandler{
    Map<String,Object> delegatesByMethod;
    public MixinProxy(TwoTuple<Object,Class<?>>...pairs){
        delegatesByMethod=new HashMap<String,Object>();
        for(TwoTuple<Object,Class<?>> pair:pairs)
            for(Method method:pair.second.getMethods()) {
                if (!delegatesByMethod.containsKey(method.getName()))
                    delegatesByMethod.put(method.getName(), pair.first);
                else {
                    throw new IllegalArgumentException(
                            "Two different methods with the same name: " + method.getName());
                }
            }
    }
    public Object invoke(Object proxy,Method method,Object[] args) throws Throwable{
        String methodName=method.getName();
        Object delegate=delegatesByMethod.get(methodName);
        return method.invoke(delegate,args);
    }
}
class getDynProxy{
    @SuppressWarnings("unchecked")
    public static Object newInstance(TwoTuple...pairs){
        Class[] interfaces= new Class[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
            interfaces[i]=(Class)pairs[i].second;
        }
        ClassLoader c1=pairs[0].first.getClass().getClassLoader();
        return Proxy.newProxyInstance(c1, interfaces,new MixinProxy(pairs));
    }
}
public class DynamicProxyMixin {
    public static void main(String[] args) {
        Object mixin=getDynProxy.newInstance(
                Tuple.tuple(new BasicImp(),Basic.class),
                Tuple.tuple(new TimeStampedImp(),TimeStamped.class),
                Tuple.tuple(new SerialNumberedImp(),SerialNumbered.class));
        Basic b=(Basic)mixin;
        TimeStamped t=(TimeStamped)mixin;
        SerialNumbered s=(SerialNumbered)mixin;
        b.set("Hello");
        System.out.println(b.get());
        System.out.println(t.getStamp());
        System.out.println(s.getSerialNumber());
    }
}
