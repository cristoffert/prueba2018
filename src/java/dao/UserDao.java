
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class UserDao {
    DbConnection conn;

    public UserDao(DbConnection conn) {
        this.conn = conn;
    }
    public boolean insert(User user){
        String sql = "insert into users values (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getRol());
            ps.setString(5, user.getPassword());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
           return false;
        }
    }
    
    public List<User> all() {
        try {
            String sql = "select * from users";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<User> list = new ArrayList();
            User user;
            while (rs.next()) {                
                user = new User(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRol(rs.getString("rol"));
                user.setPassword(rs.getString("password"));
                //agregar user object a la lista 
                list.add(user);
            }
            return list;      
        } catch (Exception e) {
            System.out.println("Error UserDao.all() : " + e.getMessage());
            return null;
        }
    }  
    
    public List<User> getByQuery(String query) {
        try {
            String sql = "select * from users where (username like ? or email like ?)";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, "%" +query+ "%");
            ps.setString(2, "%" +query+ "%");
            ResultSet rs = ps.executeQuery();
            List<User> list = new ArrayList();
            User user;
            while (rs.next()) {                
                user = new User(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRol(rs.getString("rol"));
                user.setPassword(rs.getString("password"));
                //agregar user object a la lista 
                list.add(user);
            }
            return list;
            
        } catch (Exception e) {
            System.out.println("Error UserDao.getByQuery: " + e.getMessage());
            return null;
        }           
    }
    
    public User login(String username, String password) {
        try {
            String sql = "select * from users where username = ? and password = ? limit 1";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            User user = new User(0);
            while (rs.next()) {                
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRol(rs.getString("rol"));
                user.setPassword(rs.getString("password"));
            }
            return user;
            
        } catch (Exception e) {
            System.out.println("Error UserDao.login(): " + e.getMessage());
            return null;
        }
        
    }
    
    //metodo para borrar un usuario determinado
    public int delete(int idUser){
        try {
            String sql = "delete from users where id=?";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, idUser);
            int row = ps.executeUpdate();
            return row;
        } catch (Exception e) {
            System.out.println("Error UserDao.delete()" + e.getMessage());
            return 0;
        }
    }
    
}