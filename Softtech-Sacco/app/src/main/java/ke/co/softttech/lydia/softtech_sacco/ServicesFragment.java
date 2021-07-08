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

import java.util.ArrayList;
import java.util.List;

import com.synnapps.*;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class ServicesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ServicesAdapter adapter;
    private List<Service> serviceList;

    int [] icons = new int[] {
            R.mipmap.logo_foreground,
            R.mipmap.logo_foreground,
            R.drawable.carousel1,
            R.drawable.carousel2,
            R.drawable.carousel3,
            R.drawable.carousel4,

    };
    String names[] = {"1","2","3","4","5","6"};



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
            service=new Service();
            service.setThumbnail(icons[1]);
            service.setTittle(names[i]);

            serviceList.add(service);
        }

        adapter = new ServicesAdapter(getContext(),serviceList);
        recyclerView.setAdapter(adapter);

        return view;


    }

    Service service;

    private void prepareServiceList() {

        int [] icons = new int[] {
                R.mipmap.logo_foreground,
                R.mipmap.logo_foreground,
                R.drawable.balance_foreground,
                R.drawable.balance_foreground,
                R.drawable.balance_foreground,
                R.drawable.ic_baseline_account_balance_24,

        };
        service = new Service("Buy airtime",icons[0]);
        serviceList.add(service);

        service = new Service("Send Money",icons[1]);
        serviceList.add(service);

        service = new Service("Pay Bills",icons[2]);
        serviceList.add(service);

        service = new Service("Get Statement",icons[3]);
        serviceList.add(service);

        service = new Service("Find office",icons[4]);
        serviceList.add(service);

        service = new Service("Contact Admin",icons[5]);
        serviceList.add(service);

        adapter.notifyDataSetChanged();

    }

}