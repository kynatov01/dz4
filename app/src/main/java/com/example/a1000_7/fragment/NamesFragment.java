package com.example.a1000_7.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1000_7.CatModel;
import com.example.a1000_7.CatsRepositiry;
import com.example.a1000_7.NamesAdapter;
import com.example.a1000_7.R;

import java.io.Serializable;

public class NamesFragment extends Fragment implements OnClickitemCat {

    private final CatsRepositiry catsRepositiry = new CatsRepositiry();
    private final NamesAdapter namesAdapter = new NamesAdapter();
    private RecyclerView recyclerView;

    public NamesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_names, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_names);

        recyclerView.setLayoutManager(new LinearLayoutManager
                (getContext(), RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(namesAdapter);
        namesAdapter.setListCat(catsRepositiry.getListOfCatHIP());
        namesAdapter.setOnClickItemCat(this);
    }

    @Override
    public void listenerClickItemCat(CatModel catModel ) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("cat",catModel);
        getParentFragmentManager().beginTransaction()
                .add(R.id.fragment_container_view, DetailFragment.class, bundle)
                .addToBackStack("NamesFragment")
                .commit();
    }
}