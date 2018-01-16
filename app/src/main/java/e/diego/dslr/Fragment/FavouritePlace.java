package e.diego.dslr.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import e.diego.dslr.InsertPlaceActivity;

import e.diego.dslr.R;
import e.diego.dslr.Util.ConstantsUtils;

public class FavouritePlace extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam;
    private String mParams;

    private FloatingActionButton mapsFab;
    private ImageView imageView;



    private OnFragmentInteractionListener mListener;

    public FavouritePlace() {
    }

    public static FavouritePlace newInstance(String param1, String param2) {
        FavouritePlace fragment = new FavouritePlace();
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
            mParam = getArguments().getString(ARG_PARAM1);
            mParams = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_place, container, false);

        imageView = view.findViewById(R.id.imageMap);
        mapsFab = view.findViewById(R.id.fabMap);

        mapsFab.setOnClickListener(new View.OnClickListener() {
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
}
