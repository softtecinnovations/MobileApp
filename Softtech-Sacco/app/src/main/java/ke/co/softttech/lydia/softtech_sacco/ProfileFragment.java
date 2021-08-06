package ke.co.softttech.lydia.softtech_sacco;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ke.co.softttech.lydia.softtech_sacco.Db.DatabaseExecutor;
import ke.co.softttech.lydia.softtech_sacco.Db.UserDb;
import ke.co.softttech.lydia.softtech_sacco.api.members;
import ke.co.softttech.lydia.softtech_sacco.models.UserModel;
import ke.co.softttech.lydia.softtech_sacco.api.Api;
import ke.co.softttech.lydia.softtech_sacco.api.apiClient;
import ke.co.softttech.lydia.softtech_sacco.api.model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    ImageButton logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }
    TextView fullname,id,phone,kra,sacconame;
    ListView saccos;
    private Api api;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        assert container != null;
        context = getContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        saccos = view.findViewById(R.id.sacconame);

        kra = view .findViewById(R.id.kra);
        sacconame = view.findViewById(R.id.sacconame);
        fullname = view.findViewById(R.id.fullname);
        //id = view.findViewById(R.id.id);
        phone = view.findViewById(R.id.phon);

        //getUserData();
        getapiData();

        return view;
    }

    private void getapiData(){
        SharedPreferences sharedPreferences;
        sharedPreferences = getContext().getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        Retrofit retrofit = apiClient.getClient();
        Api api = retrofit.create(Api.class);
        Call<members> call = apiClient.getInstance().getMyApi().getmember(sharedPreferences.getString("phoneNumber", ""));
        call.enqueue(new Callback<members>() {
            @Override
            public void onResponse(Call<members> call, Response<members> response) {
                if (response.isSuccessful()){
                    members user =  response.body();
                    //String[] details= new String[user.size()];

                        fullname.setText(user.getFullname());
                        kra.setText(user.getKrapin());
                        sacconame.setText(user.getSacconame());
                        //id.setText(user.getId_number());
                        phone.setText(user.getPhonenumber());

                }
            }

            @Override
            public void onFailure(Call<members> call, Throwable t) {

            }
        });
//        call.enqueue(new Callback<List<members>>() {
//            @Override
//            public void onResponse(Call<List<members>> call, Response<List<members>> response) {
//                List<members> user = response.body();
//                String[] heroes = new String[user.size()];
//                for (int i = 0;i<user.size();i++){
//                    heroes[i] = user.get(i).getFullname();
//                }
//
//                saccos.setAdapter(new ArrayAdapter<>(context,R.layout.item, heroes));
//            }
//
//            @Override
//            public void onFailure(Call<List<members>> call, Throwable t) {
//                Toast.makeText(getContext(), "Something is wrong somewhere", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void getUserData(){
        DatabaseExecutor.getInstance().mainThread().execute(() -> {
            UserDb database = UserDb.getInstance(getContext());
            List<UserModel> list = database.daoAccess().getLatsUser();
            for (UserModel model : list){
                fullname.setText(model.getUser_Name());
                //id.setText(model.getUser_Id());
                phone.setText(model.getUser_PhoneNumber());
                kra.setText(model.getUser_KraPin());
                sacconame.setText(model.getUser_SaccoName());
            }
        });
    }
}