package ke.co.softttech.lydia.softtech_sacco.Db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ke.co.softttech.lydia.softtech_sacco.models.UserModel;

@Dao
public interface DaoAccess {
    //////////////////user///////////////

    @Insert
    void insertUser(UserModel user);

    @Query("SELECT * FROM userModel")
    List<UserModel> fetchAllUsers();

    @Query("SELECT user_SaccoName FROM usermodel")
    List<String> getSaccoName();


    @Query("SELECT * FROM userModel WHERE id = (SELECT MAX(id) FROM userModel)")
    List<UserModel> getLatsUser();

//    @Query("SELECT * FROM userModel")
//    LiveData<userodel> getLiveData;

    @Update
    void updatePassengers(UserModel passenger);


    @Delete
    void deleteUser(UserModel user);


}

