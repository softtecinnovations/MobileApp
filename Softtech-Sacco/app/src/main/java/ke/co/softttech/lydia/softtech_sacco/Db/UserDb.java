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
    private static final int NUMBER_OF_THREADS = 1;
    static  final ExecutorService dataBaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UserDb getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (UserDb.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDb.class, "receipts_db").build();
                }
            }
        }
        return INSTANCE;
    }
}
