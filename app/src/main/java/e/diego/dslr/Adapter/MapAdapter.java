package e.diego.dslr.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import e.diego.dslr.Model.Map;
import e.diego.dslr.R;

/**
 * Created by Diego on 28/12/2017.
 */

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.ViewM>{

    private Context context;
    private List<Map> maps;

    public MapAdapter(Context context, List<Map> maps) {
        this.context = context;
        this.maps = maps;
    }

    @Override
    public MapAdapter.ViewM onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_card, parent, false);
        return new ViewM(view);
    }

    @Override
    public void onBindViewHolder(final MapAdapter.ViewM holder, final int position) {

        holder.nameMapVal.setText(maps.get(position).getNamePlace());
        /*holder.map = SupportMapFragment.newInstance();

        holder.map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng place = new LatLng(maps.get(position).getLatitude(), maps.get(position).getLongitude());
                googleMap.addMarker(new MarkerOptions().position(place).title(holder.nameMapVal.getText().toString()));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 10));
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return maps.size();
    }

    public class ViewM extends RecyclerView.ViewHolder{

        private TextView nameMapVal;
        //private SupportMapFragment map;

        public ViewM(View itemView) {
            super(itemView);
            nameMapVal = itemView.findViewById(R.id.tNamePlaceVal);
        }
    }
}
