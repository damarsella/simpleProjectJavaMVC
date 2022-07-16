package com.DAO;

import com.connection.ConnectionDb;
import java.sql.Statement;
import java.sql.ResultSet;
import com.model.ModelBarang;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoBarang implements ImplementBarang {
    
    Connection connection;    
    
    final String insert = "INSERT INTO tbarang (name, price, stock) VALUES (?, ?, ?)";
    final String delete = "DELETE FROM tbarang WHERE no=?";
    final String update = "UPDATE tbarang SET name=?, price=?, stock=? WHERE no=?";
    final String select = "SELECT * FROM tbarang";
    final String searchName = "SELECT * FROM tbarang WHERE name LIKE ?";

    public DaoBarang() {
       connection = ConnectionDb.Connection();
    }
    
    @Override
    public void insert(ModelBarang mb) {            
        PreparedStatement statement = null;
        
        try {
           statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
           statement.setString(1, mb.getName());
           statement.setDouble(2, mb.getPrice());
           statement.setInt(3, mb.getStock());
           statement.executeUpdate();
           ResultSet rs = statement.getGeneratedKeys();
           while (rs.next()){
               mb.setNo(rs.getInt(1));
           }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();                
            }catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }

    @Override
    public void delete(int no) {        
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(delete);
            
            statement.setInt(1, no);
            statement.executeUpdate();
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void update(ModelBarang mb) {
        PreparedStatement statement = null;
       
        try {
           statement = connection.prepareStatement(update);
           statement.setString(1, mb.getName());
           statement.setDouble(2, mb.getPrice());
           statement.setInt(3, mb.getStock());
           statement.setInt(4, mb.getNo());
           statement.executeUpdate();
           
        }catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
  
    @Override
    public List<ModelBarang> getAll() {
        List<ModelBarang> lmb = null;
        
        try {            
            lmb = new ArrayList<ModelBarang>();
            Statement st = connection.createStatement();
            
            ResultSet rs = st.executeQuery(select);
            while(rs.next()) {
                ModelBarang mb = new ModelBarang();
                mb.setNo(rs.getInt("no"));
                mb.setName(rs.getString("name"));
                mb.setPrice(rs.getDouble("price"));
                mb.setStock(rs.getInt("stock"));
                lmb.add(mb);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return lmb;       
    }

    @Override
    public List<ModelBarang> getSearchName(String name) {
        List<ModelBarang> lmb = null;
      
        try {
            lmb = new ArrayList<ModelBarang>();
          
            PreparedStatement st = connection.prepareStatement(searchName);
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                ModelBarang mb = new ModelBarang();
                mb.setNo(rs.getInt("no"));
                mb.setName(rs.getString("name"));
                mb.setPrice(rs.getDouble("price"));
                mb.setStock(rs.getInt("stock"));
                lmb.add(mb);
            }
          
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
      
        return lmb;   
    }    
    
}
