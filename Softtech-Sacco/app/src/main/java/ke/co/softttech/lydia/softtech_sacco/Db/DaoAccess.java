package ke.co.softttech.lydia.softtech_sacco.Db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoAccess {
//    @Insert
//    void insertParcels(ParcelModel parcel);
//
//
//    @Query("SELECT * FROM parcelmodel WHERE id = (SELECT MAX(id)  FROM ParcelModel)")
//    List<ParcelModel> fetchLastParcel();
//
//
//    @Query("SELECT * FROM ParcelModel ORDER BY Parceltime DESC LIMIT 1")
//    List<ParcelModel> getLastParcel();
//
//    @Query("SELECT * FROM ParcelModel")
//    List<ParcelModel> getAllParcel();
//
//
//    @Update
//    void updateParcels(ParcelModel parcel);
//
//
//    @Delete
//    void deleteParcel(ParcelModel parcel);

    //////////////////user///////////////

    @Insert
    void insertUser(UserModel user);

    @Query("SELECT * FROM userModel")
    List<UserModel> fetchAllUsers();


    @Query("SELECT * FROM userModel WHERE id = (SELECT MAX(id) FROM userModel)")
    List<UserModel> getLatsUser();


    @Update
    void updatePassengers(UserModel passenger);


    @Delete
    void deleteUser(UserModel user);


}

