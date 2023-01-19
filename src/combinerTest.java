import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class combinerTest {
    
    @Test
    public void testShortPath(){
        assertEquals("accessories.csv", combiner.findPath("/accessories.csv"));
    }

    @Test
    public void testLongPath() {
        assertEquals("accessories.csv", combiner.findPath("/testtest/testtest/accessories.csv"));

    }
}   
