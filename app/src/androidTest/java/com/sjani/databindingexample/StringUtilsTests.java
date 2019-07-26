package com.sjani.databindingexample;



import com.sjani.databindingexample.Utils.StringUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTests {

    @Test
    public void ISOtoCustomStringUtilTest() {
        assertEquals(StringUtils.formatDateTime("2015-01-01T11:32:00.000Z"), "11:32 AM Jan 01, 2015");
    }

    @Test
    public void CustomToISOStringUtilTest() {
        String inputString = StringUtils.ConvertToISO8601("01-01-2015", "11:32");
        assertEquals(inputString, "2015-01-01T11:32:00.000Z");
    }

}
