package com.normanhoeller.picturehub;

import com.normanhoeller.picturehub.model.ViewModelResult;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by norman on 12/09/16.
 */
public class TestViewModelResult {

    ViewModelResult result;

    @Before
    public void setup() {
         result = new ViewModelResult("someUrl", "someDescription");
    }

    @Test
    public void testInitialization() {
        assertNotNull(result);
        assertEquals("someUrl", result.getUrl());
        assertEquals("someDescription", result.getDescription());
    }


}
