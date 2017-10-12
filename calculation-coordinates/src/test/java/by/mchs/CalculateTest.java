package by.mchs;

import by.mchs.model.Call;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertThat;

import static by.mchs.Calculate.getCoordinate;
import static org.hamcrest.CoreMatchers.is;

public class CalculateTest {

    @Test(expected = IllegalArgumentException.class)
    public void CountCallsLeast2(){
        ArrayList<Call> calls = new ArrayList<>(Arrays.asList(new Call(),new Call()));
        getCoordinate(calls);
    }

    @Test(expected = IllegalArgumentException.class)
    public void inValidParam(){
        ArrayList<Call> calls = new ArrayList<>(Arrays.asList(new Call(1,1,1,null,0)
                ,new Call(1,2,1,null,5000)
                ,new Call(1,4,1,null,7000)));
        getCoordinate(calls);
    }

    @Test
    public void getCoordinateValidParam(){
        ArrayList<Call> calls = new ArrayList<>(Arrays.asList(new Call(1,1,1,null,4500)
                ,new Call(1,2,1,null,8700)
                ,new Call(1,4,1,null,6500)));
        assertThat(getCoordinate(calls).getX(),is(4084.0));
        assertThat(getCoordinate(calls).getY(),is(2253.0));
    }



}
