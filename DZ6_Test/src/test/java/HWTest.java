import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class HWTest {
    private int a[];
    private int b[];
    ArrForTest arr;


    public HWTest(int[] a, int[] b) {
        this.a = a;
        this.b = b;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new int[] {1,2}, new int [] {4,1,2}},
                {new int [] {3,3},new int[] {1,4,2,4,3,3}},
                {new int [] {1,1},new int [] {4,4,4,4,1,1}}
        });
    }
    @Before
    public void init(){
        arr = new ArrForTest();
    }
    @Test
    public void testArr(){
        Assert.assertArrayEquals(a,arr.array(b));
    }
    @Test(expected = RuntimeException.class)
    public void testArrException(){
        arr.array(new int []{1,2,3,5,6});
    }
}

