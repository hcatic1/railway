package ba.unsa.etf.rpr.Bussiness;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Train;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class TrainManager {
    public void validateTrainName(String name) throws RailwayException {
        if(name == null || name.length() > 60 || name.length() < 1){
            throw new RailwayException("Train name must be between 1 and 60 chars.");
        }
    }
    public void validateTrainDateBought(Date dateBought) throws RailwayException {
        if(dateBought == null && dateBought.toLocalDate().isAfter(LocalDate.now(ZoneId.systemDefault()))){
            throw new RailwayException("Date of train purchase can not be in the future.");
        }
    }

    public Train add(Train train) throws RailwayException {
        if(train.getId() != 0){
            throw new RailwayException("Train with Id cannot be added. Id is autogenerated");
        }
        validateTrainName(train.getName());
        validateTrainDateBought(train.getDateBought());
        try{
            return DaoFactory.trainDao().add(train);
        }catch(RailwayException e){
            if(e.getMessage().contains("UQ_NAME")){
                throw new RailwayException("Train with the same name exists.");
            }
            throw e;
        }
    }
    public void delete(int trainId) throws RailwayException {
        try{
            DaoFactory.trainDao().delete(trainId);
        } catch (RailwayException e) {
            if(e.getMessage().contains("FOREIGN KEY")){
                throw new RailwayException("Cannot delete a train related to journeys. Delete all journeys related to a train before deleting train.");
            }
            throw e;
        }
    }
    public Train update(Train train) throws RailwayException {
        validateTrainName(train.getName());
        validateTrainDateBought(train.getDateBought());
        return DaoFactory.trainDao().update(train);
    }
}
