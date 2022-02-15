package testing.junit_part1;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import ritmath.FunctionFactory;
import ritmath.MathFunction;
import ritmath.Sum;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some additional tests that follow the instructions in the lab
 * @author Zack Bamford
 * @author RIT CS
 */
@TestMethodOrder( MethodOrderer.MethodName.class )
@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class Part1BTests {
    // create vars
    MathFunction x = FunctionFactory.x();
    MathFunction c1 = FunctionFactory.constant(1.0);
    MathFunction c2 = FunctionFactory.constant(2.0);
    MathFunction c_2 = FunctionFactory.constant(-2.0);

    MathFunction sum1 = FunctionFactory.sum(c1, x, c2);


    /**
     * 1B Test 1
     * Check basic var handling
     */
    @Test
    public void t1() {
        String t1Help = "Does not follow the results expected in 2.1.1";
        assertEquals(7.5, x.evaluate(7.5), t1Help);
        assertEquals("x", x.toString(), t1Help);
        assertEquals(1, x.derivative().evaluate(0), t1Help);
        assertEquals(12.0, x.integral(5, 1), t1Help);
        assertFalse(x.isConstant(), t1Help);
    }


    /**
     * 1B Test 2
     * Test normalization of empty sum
     */
    @Test
    public void t2() {
        // create empty sum
        MathFunction sumEmpty = new Sum();

        String t2Help = "Empty sum should equal zero! (2.3.1.1)";
        assertEquals(0.0, sumEmpty.evaluate(10), t2Help);
    }

    /**
     * 1B Test 3
     * Test normalization
     */
    @Test
    public void t3() {
        String t3Help = "Did not normalize correctly. (2.3.1)";
        assertEquals("( x + 3.0 )", sum1.toString(), t3Help);
    }

    /**
     * 1B Test 4
     * Test canceling out things (normalization)
     */
    @Test
    public void t4() {
        // create cancel out sum
        MathFunction sumCancel = new Sum(c2, c_2);
        MathFunction sumCancelX = new Sum(c2, c_2, x);

        String t4Help = "Did not reduce constants correctly. (2.3.1)";
        assertTrue(sumCancel.isConstant(), t4Help); // check complete cancel
        assertEquals(200.0, sumCancelX.evaluate(200), t4Help); // check cancel with x
        assertTrue(sumCancelX.derivative().isConstant(), t4Help);
    }

}
