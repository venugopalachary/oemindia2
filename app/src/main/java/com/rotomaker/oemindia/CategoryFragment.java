package com.rotomaker.oemindia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;


public class CategoryFragment extends Fragment {
    View view;
    RecyclerView recyclerView ;
    CategoryList[] categoryList;
    SearchView searchView;
    CategoryAdapter adapter ;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_category, container, false);
        categoryList=new CategoryList[] {
                new CategoryList("AllCategories" ,android.R.drawable.ic_dialog_dialer),
                new CategoryList("Medical Safety",android.R.drawable.ic_dialog_dialer),
                new CategoryList("Hygiene Personal Care",android.R.drawable.ic_dialog_dialer),
                new CategoryList("Pharmaceutical Drug",android.R.drawable.ic_dialog_dialer),
                new CategoryList("ChemicalsDyes",android.R.drawable.ic_dialog_dialer),
                new CategoryList("Hospital Equipment",android.R.drawable.ic_dialog_dialer),
                new CategoryList("Packaging Material",android.R.drawable.ic_dialog_dialer),
        };
        recyclerView=view.findViewById(R.id.recyclerView);
//       searchView=view.findViewById(R.id.searchview);
        adapter = new CategoryAdapter(categoryList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                if(categoryList.contains(query)){
//                    adapter.getFilter().filter(query);
//                }else{
//                    Toast.makeText(getContext(), "No Match found",Toast.LENGTH_LONG).show();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //    adapter.getFilter().filter(newText);
//                return false;
//            }
//        });

        return view;
    }


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}