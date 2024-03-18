package ru.msu.vmk;

import org.apache.derby.drda.NetworkServerControl;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Library library = new LibraryImpl("jdbc:derby:memory:testdb;create=true", "test", "test");
        library.init();

        NetworkServerControl nsc = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
        nsc.start(new PrintWriter(System.out, true));

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        Connection c = DriverManager.getConnection("jdbc:derby:memory:testdb;create=true");
        try(Connection conn = c;
            PreparedStatement ps = conn.prepareStatement("create schema TEST");) {

            boolean success = ps.execute();
            System.out.println("Memory database created: " + success);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
