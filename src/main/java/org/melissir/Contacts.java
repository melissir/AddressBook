package org.melissir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mrhein on 4/8/16.
 */
public class Contacts extends Model {
    public static final String TABLE = "contacts";

    private int id = 0;
    private String first;
    private String last;
    private String street;
    private String state;
    private String zip;
    private String email;
    private String phone;



    public Contacts(){ }

    public Contacts(String first, String last, String street, String state, String zip, String email, String phone){
        this.first = first;
        this.last = last;
        this.street = street;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    void load(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        first = rs.getString("first");
        last = rs.getString("last");
        street = rs.getString("street");
        state = rs.getString("state");
        zip = rs.getString("zip");
        email = rs.getString("email");
        phone = rs.getString("phone");

    }

    @Override
    void insert() throws SQLException {
        Connection cx = ORM.connection();
        String sql = String.format(
                "insert into %s (first, last, street, state, zip, email, phone) values (?,?,?,?,?,?,?)", TABLE);
        PreparedStatement st = cx.prepareStatement(sql);
        int i = 0;
        st.setString(++i, first);
        st.setString(++i, last);
        st.setString(++i, street);
        st.setString(++i, state);
        st.setString(++i, zip);
        st.setString(++i, email);
        st.setString(++i, phone);
        st.executeUpdate();
        id = ORM.getMaxId(TABLE);
    }

    @Override
    void update() throws SQLException {
        Connection cx = ORM.connection();
        String sql = String.format(
                "update %s set first=?,last=?,street=?,state=?,zip=?,email=?,phone=? where id=?", TABLE);
        PreparedStatement st = cx.prepareStatement(sql);
        int i = 0;
        st.setString(++i, first);
        st.setString(++i, last);
        st.setString(++i, street);
        st.setString(++i, state);
        st.setString(++i, zip);
        st.setString(++i, email);
        st.setString(++i, phone);
        st.setInt(++i, id);
        st.executeUpdate();
    }

    @Override
    public String toString() {
        return String.format("(%s,%s,%s,%s,%s,%s,%s,%s)", id, first, last, street, state, zip, email, phone);
    }

}

