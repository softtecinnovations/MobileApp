package ke.co.softttech.lydia.softtech_sacco.Db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserModel.class}, version = 1,exportSchema = false)
public abstract class UserDb extends RoomDatabase {

    public abstract DaoAccess daoAccess();

    private static volatile UserDb INSTANCE;
    private static UserDb userDB;
    private static final int NUMBER_OF_THREADS = 1;
    static  final ExecutorService dataBaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UserDb getInstance(Context context){
        if (userDB == null){
            userDB = buildDatabaseInstance(context);
            /*synchronized (ReceiptDatabase.class){
                if (receiptDB ==null){
                    receiptDB = Room.databaseBuilder(context.getApplicationContext(),
                            ReceiptDatabase.class,"parcels_db").build();
                }
            }*/
        }
        return userDB;
    }

    private static UserDb
    buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                UserDb.class,
                "sacco_db")
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        userDB = null;
    }
}
