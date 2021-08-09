package ke.co.softttech.lydia.softtech_sacco;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import ke.co.softttech.lydia.softtech_sacco.models.PersonModel;
import ke.co.softttech.lydia.softtech_sacco.models.ServiceModel;
import ke.co.softttech.lydia.softtech_sacco.network.APIUtils;
import ke.co.softttech.lydia.softtech_sacco.network.RetrofitAPI;
import ke.co.softttech.lydia.softtech_sacco.otp.OtpActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ServicesAdapter adapter;
    private List<ServiceModel> serviceList;
    private CarouselView carouselView;
    private RetrofitAPI mRetrofitAPI;
    private Context mContext;
    private static final String TAG = "HomeFragment";
    int [] sampleImages = {R.drawable.carousel1, R.drawable.carousel2, R.drawable.carousel3, R.drawable.carousel4, R.drawable.carousel5};

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        carouselView = view.findViewById(R.id.carousel);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        recyclerView = view.findViewById(R.id.recycler_view);
        serviceList = new ArrayList<ServiceModel>();
        adapter = new ServicesAdapter(getContext(),serviceList);
        mRetrofitAPI = APIUtils.getAPIService();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerClickListener(getContext(), recyclerView, new RecyclerClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ServiceModel service = serviceList.get(position);
                String tittle = ((TextView) view.findViewById(R.id.title)).getText().toString();
                switch (tittle) {
                    case "PAY":
                        startActivity(new Intent(getContext(), SendActivity.class));
                        break;
                    case "RECEIVE":
                        startActivity(new Intent(getContext(), WithdrawActivity.class));
                        break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        prepareServiceList();
//        fetchServices();
        return view;


    }

    ImageListener imageListener = (position, imageView) -> {
        imageView.setImageResource(sampleImages[position]);
    };

    private void prepareServiceList() {

        int [] icons = new int[] {
                R.mipmap.logo_foreground,
                R.drawable.ic_baseline_call_received_24,
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_account_balance_24
        };
        ServiceModel service = new ServiceModel("SEND",icons[0]);
        serviceList.add(service);

        service = new ServiceModel("RECEIVE",icons[1]);
        serviceList.add(service);

        service = new ServiceModel("PAY",icons[2]);
        serviceList.add(service);

        service = new ServiceModel("OTHERS",icons[3]);
        serviceList.add(service);


        adapter.notifyDataSetChanged();

    }

    //remember to make image a string and set it as url

//    private void fetchServices(){
//        ServiceModel serviceModel = new ServiceModel();
//
//        mRetrofitAPI.getItems(serviceModel).enqueue(new Callback<ServiceModel>() {
//            @Override
//            public void onResponse(Call<ServiceModel> call, Response<ServiceModel> response) {
//
//                if(response.isSuccessful()) {
//                    Log.i(TAG, "post submitted to API." + response.body().toString());
//                    serviceList = (List<ServiceModel>) response.body();
//                    Log.d("TAG","Response = "+serviceList);
//                    adapter = new ServicesAdapter(getContext(),serviceList);
//                    recyclerView.setAdapter(adapter);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ServiceModel> call, Throwable t) {
//                Log.e(TAG, "Unable to submit data to API.");
//
//            }
//
//        });

   // }


}

