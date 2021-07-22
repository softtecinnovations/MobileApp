package ke.co.softttech.lydia.softtech_sacco;

import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.synnapps.carouselview.CarouselView;


import java.util.ArrayList;
import java.util.List;

import ke.co.softttech.lydia.softtech_sacco.models.ServiceModel;


public class ServicesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ServicesAdapter adapter;

    private List<ServiceModel> serviceList;
    private CarouselView carouselView;
    int [] sampleImages = {R.drawable.carousel1, R.drawable.carousel2, R.drawable.carousel3, R.drawable.carousel4, R.drawable.carousel5};

    int [] icons = new int[] {
            R.mipmap.logo_foreground,
            R.mipmap.logo_foreground,
            R.drawable.carousel1,
            R.drawable.carousel2,
            R.drawable.carousel3,
            R.drawable.carousel4,

    };
    String names[] = {"My Account","Pay Bils","Buy Airtime","Find Office","Contact Admin","Get Statement"};




    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        serviceList = new ArrayList<>();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i=0;i<icons.length;i++){
            service=new ServiceModel();
            service.setThumbnail(icons[1]);
            service.setTittle(names[i]);

            serviceList.add(service);
        }

        adapter = new ServicesAdapter(getContext(),serviceList);
        recyclerView.setAdapter(adapter);

        return view;


    }

    ServiceModel service;

    private void prepareServiceList() {

        int [] icons = new int[] {
                R.mipmap.logo_foreground,
                R.mipmap.logo_foreground,
                R.drawable.balance_foreground,
                R.drawable.balance_foreground,
                R.drawable.balance_foreground,
                R.drawable.ic_baseline_account_balance_24,

        };

//        ServiceModel serviceModel = new ServiceModel("SEND",icons[0]);
//        serviceList.add(serviceModel);
//
//        serviceModel = new ServiceModel("RECEIVE",icons[1]);
//        serviceList.add(serviceModel);
//
//        serviceModel = new ServiceModel("PAY",icons[2]);
//        serviceList.add(serviceModel);
//
//        serviceModel = new ServiceModel("OTHERS",icons[3]);
//        serviceList.add(serviceModel);
//=======
//        service = new Service("Buy airtime",icons[0]);
//        serviceList.add(service);
//
//        service = new Service("Send Money",icons[1]);
//        serviceList.add(service);
//
//        service = new Service("Pay Bills",icons[2]);
//        serviceList.add(service);
//
//        service = new Service("Get Statement",icons[3]);
//        serviceList.add(service);
//>>>>>>> dc17503e6a07ef92df7b3b1be40a491311e0be7d
//
//        service = new Service("Find office",icons[4]);
//        serviceList.add(service);
//
//        service = new Service("Contact Admin",icons[5]);
//        serviceList.add(service);

        adapter.notifyDataSetChanged();

    }

}