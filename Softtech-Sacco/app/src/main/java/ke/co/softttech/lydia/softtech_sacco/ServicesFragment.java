package ke.co.softttech.lydia.softtech_sacco;

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
import java.util.zip.Inflater;


public class ServicesFragment extends Fragment {

    private RecyclerView recyclerView;
    private ServicesAdapter adapter;
    private List<Service> serviceList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        serviceList = new ArrayList<>();
        adapter = new ServicesAdapter(getContext(),serviceList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareServiceList();
        return view;


    }

    private void prepareServiceList() {

        int [] icons = new int[] {
                R.mipmap.logo_foreground,
                R.drawable.ic_baseline_call_received_24,
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_account_balance_24
        };
        Service service = new Service("SEND",icons[0]);
        serviceList.add(service);

        service = new Service("RECEIVE",icons[1]);
        serviceList.add(service);

        service = new Service("PAY",icons[2]);
        serviceList.add(service);

        service = new Service("OTHERS",icons[3]);
        serviceList.add(service);

        adapter.notifyDataSetChanged();

    }


}

