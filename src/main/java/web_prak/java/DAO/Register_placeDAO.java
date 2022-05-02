package web_prak.java.DAO;

import web_prak.java.classes.Register_place;

import java.util.List;

public interface Register_placeDAO {
    void createRegPlace(Register_place register_place);
    void updateRegPlace(Register_place register_place);
    void deleteRegPlace(Register_place register_place);
    Register_place getRegPlaceById(long place_id);
    List<Register_place> getRegPlaceByFree(String free);
    List<Register_place> getRegPlaceByStorageLoc(String storage_location);
    List<Register_place> getAllRegPlace();
    boolean getFreeById(long place_id);
}
