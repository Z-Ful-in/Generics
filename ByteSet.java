import java.util.*;
public class ByteSet {
    Byte[] possibles={1,2,3,4,5,6,7,8,9};
    Set<Byte> mySet=new HashSet<>(Arrays.asList(possibles));
    //Error:
    //Set<Byte> mySet1=new HashSet<Byte>(Arrays.<Byte>asList(1,2,3,4,5,6,7,8,9));
}
