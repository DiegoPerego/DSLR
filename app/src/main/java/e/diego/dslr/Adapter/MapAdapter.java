package e.diego.dslr.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import e.diego.dslr.Model.MapList;
import e.diego.dslr.Model.MyMap;
import e.diego.dslr.R;

/**
 * Created by Diego on 28/12/2017.
 */

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.ViewM>{

    private Context context;
    private MapList maps;

    public MapAdapter(Context context, MapList maps) {
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

        holder.nameMapVal.setText(maps.getMapList().get(position).getNamePlace());

    }

    @Override
    public int getItemCount() {
        return maps.getMapList().size();
    }

    public class ViewM extends RecyclerView.ViewHolder{

        private TextView nameMapVal;

        public ViewM(View itemView) {
            super(itemView);
            nameMapVal = itemView.findViewById(R.id.tNamePlaceVal);
        }
    }
}
