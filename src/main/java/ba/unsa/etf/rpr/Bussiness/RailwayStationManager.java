package ba.unsa.etf.rpr.Bussiness;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.RailwayStation;

import java.util.List;

public class RailwayStationManager {
    public void validateStationName(String name) throws RailwayException {
        if(name == null || name.length() < 1 || name.length() > 255){
            throw new RailwayException("Station name must be between 1 and 255 chars.");
        }
    }

    public void validateStationAddress(String address) throws RailwayException {
        if(address == null || address.length() < 1 || address.length() > 255){
            throw new RailwayException("Station adress must be between 1 and 255 chars.");
        }
    }

    public RailwayStation add(RailwayStation station) throws RailwayException {
        if(station.getId() != 0){
            throw new RailwayException("Station with Id cannot be added. Id is autogenerated.");
        }
        validateStationName(station.getName());
        validateStationAddress(station.getAddress());
        try{
            return DaoFactory.railwayStationDao().add(station);
        }catch(RailwayException e){
            if(e.getMessage().contains("UNIQUE")){
                throw new RailwayException("Station with the same name exists.");
            }
            throw e;
        }
    }
    public List<RailwayStation> getAll() throws RailwayException {
        return DaoFactory.railwayStationDao().getAll();
    }
}
