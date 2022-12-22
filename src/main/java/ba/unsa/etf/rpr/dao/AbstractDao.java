package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Idable;
import java.sql.*;
import java.util.*;

public abstract class AbstractDao<Type extends Idable> implements Dao<Type>{
    private Connection connection;
    private String tableName;

    public AbstractDao(String tableName){
        try{
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = p.getProperty("db.connection_string");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            this.connection = DriverManager.getConnection(url, username, password);
        }catch(Exception e){
            System.out.println("Connection to database failed.");
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public abstract Type row2object(ResultSet rs);
    public abstract Map<String, Object> object2row(Type object);

    public Type getById(int id) throws RailwayException {
        try{
            String query = "SELECT * FROM " + this.tableName + " WHERE id = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Type result = row2object(rs);
                rs.close();
                return result;
            }else{
                throw new RailwayException("Object not found.");
            }
        }
        catch(SQLException e){
            throw new RailwayException(e.getMessage(), e);
        }
    }

    public List<Type> getAll() throws RailwayException {
        try{
            String query = "SELECT * FROM " + tableName;
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            List<Type> results = new ArrayList<Type>();
            while(rs.next()){
                Type object = row2object(rs);
                results.add(object);
            }
            return results;
        }
        catch(SQLException e){
            throw new RailwayException(e.getMessage(), e);
        }
    }

    public Type add(Type item){return null;}

    public Type update(Type item){return null;}

    public void delete(int id) throws RailwayException {
        try{
            String del = "DELETE FROM " + tableName + " WHERE id = ?";
            PreparedStatement stmt = this.connection.prepareStatement(del);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RailwayException(e.getMessage(), e);
        }
    }

    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){

        return null;
    }

}