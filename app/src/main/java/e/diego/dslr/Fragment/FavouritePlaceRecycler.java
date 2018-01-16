package e.diego.dslr.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import e.diego.dslr.Adapter.MapAdapter;
import e.diego.dslr.InsertPlaceActivity;
import e.diego.dslr.Model.Map;
import e.diego.dslr.R;
import e.diego.dslr.Util.ConstantsUtils;

public class FavouritePlaceRecycler extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private MapAdapter mapAdapter;
    private List<Map> maps;
    private RecyclerView recyclerViewMap;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton fabMap;

    private OnFragmentInteractionListener mListener;

    public FavouritePlaceRecycler() {
    }

    public static FavouritePlaceRecycler newInstance(String param1, String param2) {
        FavouritePlaceRecycler fragment = new FavouritePlaceRecycler();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        maps = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourite_place_recycler, container, false);
        recyclerViewMap = view.findViewById(R.id.recyclerViewMap);
        fabMap = view.findViewById(R.id.fabMapRec);

        recyclerViewMap.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewMap.setLayoutManager(layoutManager);
        mapAdapter = new MapAdapter(getContext(), maps);
        recyclerViewMap.setAdapter(mapAdapter);

        fabMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertPlace = new Intent(getContext(), InsertPlaceActivity.class);
                startActivityForResult(insertPlace, ConstantsUtils.REQUEST_CODE_MAP);
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ConstantsUtils.REQUEST_CODE_MAP) {
            if (resultCode == Activity.RESULT_OK) {
                Map map = (Map) data.getSerializableExtra(ConstantsUtils.MAP_OBJECT);
                maps.add(map);
                mapAdapter.notifyDataSetChanged();
            }
        }
    }
}