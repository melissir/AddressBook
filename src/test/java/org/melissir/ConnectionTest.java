package org.melissir;

import junit.framework.TestCase;

import java.sql.Connection;

/**
 * Created by mrhein on 4/12/16.
 */
public class ConnectionTest extends TestCase {

    public ConnectionTest(String name){
        super(name);
    }

    public void testConn() throws Exception{
        ORM.init();
        Connection cx = ORM.connection();
        assertTrue(cx.isValid(0));

    }
}
