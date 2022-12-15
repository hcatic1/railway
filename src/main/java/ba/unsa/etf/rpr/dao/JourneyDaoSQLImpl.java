package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class JourneyDaoSQLImpl implements JourneyDao{
    private Connection connection;

    public JourneyDaoSQLImpl(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql6583526", "sql6583526", "");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Journey add(Journey item) {
        return null;
    }

    @Override
    public Journey update(Journey item) {
        return null;
    }

    @Override
    public Journey getById(int id) {
        try{
            String query = "SELECT * FROM Journeys WHERE id = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Journey journey = new Journey();
                journey.setId(id);
                journey.setTrainId(rs.getInt("train"));
                journey.setDepartureStationId(rs.getInt("departureStation"));
                journey.setArrivalStationId(rs.getInt("arrivalStation"));
                journey.setDepartureDate(rs.getDate("departureDate"));
                journey.setArrivalDate(rs.getDate("arrivalDate"));
                journey.setDepartureTime(rs.getTime("departureTime"));
                journey.setArrivalTime(rs.getTime("arrivalTime"));
                return journey;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Journey> getAll() {
        return null;
    }

    @Override
    public List<Journey> searchByTrain(Train train) {
        return null;
    }

    @Override
    public List<Journey> searchByDepartureStation(RailwayStation departureStation) {
        return null;
    }

    @Override
    public List<Journey> searchByArrivalStation(RailwayStation arrivalStation) {
        return null;
    }

    @Override
    public List<Journey> searchByDepartureTime(Date departureDate) {
        return null;
    }

    @Override
    public List<Journey> searchByArrivalTime(Date arrivalDate) {
        return null;
    }

    @Override
    public List<Journey> searchByDepartureTime(Time departureTime) {
        return null;
    }

    @Override
    public List<Journey> searchByArrivalTime(Time arrivalTime) {
        return null;
    }
}
