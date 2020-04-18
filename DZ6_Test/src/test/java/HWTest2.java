import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class HWTest2 {
    private int [] a;
    private boolean b ;

    public HWTest2(int[] a, boolean b) {
        this.a = a;
        this.b = b;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new int []{1,1,1,1,1,},false},
                {new int []{4,4,4,4,4},false},
                {new int [] {2,3,5,6,7},false},
                {new int []{1,2,3,4,5,6,},true}
        });
    }
    @Before
    public void arrInit(){}
    ArrForTest arr = new ArrForTest();
    @Test
    public void arrContainTest(){
        Assert.assertEquals(b,arr.arrayContain(a));
    }
}
