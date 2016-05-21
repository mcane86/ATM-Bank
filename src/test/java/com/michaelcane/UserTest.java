package com.michaelcane;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michaelcane on 5/19/16.
 */
public class UserTest {

    User user;
    @Before
    public void setUp() throws Exception {
        user = new User("Tommy Tutone");
    }

    @Test
    public void getUserName() throws Exception {
        String expected = "Tommy Tutone";
        String actual = user.getUserName();
        assertEquals(expected, actual);
    }

}