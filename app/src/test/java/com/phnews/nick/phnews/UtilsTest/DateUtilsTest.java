package com.phnews.nick.phnews.UtilsTest;
import com.phnews.nick.phnews.utils.DateUtils;

import org.junit.Test;

import static org.junit.Assert.*;
public class DateUtilsTest {
    String correctInput = "2018-07-10T05:09:30Z";
    String correctOutput = "Tue,10 Jul 2018 05:09";

    @Test
    public void formatNewsApiDate_correctInput_correctOutput(){
        String outputDate = DateUtils.formatNewsApiDate(correctInput);
        assertEquals(outputDate,correctOutput);
    }

    @Test
    public void formatNewsApiDate_nullInput_Outputnull(){
        String outputDate = DateUtils.formatNewsApiDate(null);
        assertEquals(outputDate,null);
    }
}
