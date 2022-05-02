package web_prak.java.services;

import web_prak.java.DAO.Impl.Register_placeDAOImpl;
import web_prak.java.DAO.Register_placeDAO;
import web_prak.java.classes.Register_place;

import java.util.List;

public class Register_placeServices {
    private final Register_placeDAO register_placeDAO = new Register_placeDAOImpl();

    public void createRegisterPlace(Register_place register_place) {
        register_placeDAO.createRegPlace(register_place);
    }

    public void updateRegisterPlace(Register_place register_place) {
        register_placeDAO.updateRegPlace(register_place);
    }

    public void deleteRegisterPlace(Register_place register_place) {
        register_placeDAO.deleteRegPlace(register_place);
    }

    public Register_place getRegPlaceById(long place_id) {
        return register_placeDAO.getRegPlaceById(place_id);
    }

    public List<Register_place> getRegPlaceByFree(String free){
        return register_placeDAO.getRegPlaceByFree(free);
    }

    public List<Register_place> getRegPlaceByStorageLoc(String storage_location) {
        return register_placeDAO.getRegPlaceByStorageLoc(storage_location);
    }

    public List<Register_place> getAllRegPlace() {
        return register_placeDAO.getAllRegPlace();
    }
    public boolean getFreePlace(long place_id) {
        return register_placeDAO.getFreeById(place_id);
    }
}
