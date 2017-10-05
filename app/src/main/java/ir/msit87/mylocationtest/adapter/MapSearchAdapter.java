package ir.msit87.mylocationtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import ir.msit87.mylocationtest.R;
import ir.msit87.mylocationtest.frameWork.ui.custom.AMTextView;
import ir.msit87.mylocationtest.model.Predictions;

/**
 * MapSearchAdapter Class Created by MSIT on 10/3/2017.
 */

public class MapSearchAdapter extends RecyclerView.Adapter<MapSearchAdapter.ViewHolder> {

    List<Predictions> predictionsList;
    private Context context;

    public MapSearchAdapter(Context context) {
        this.context = context;
        predictionsList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_result_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.description.setText(predictionsList.get(position).getStructured_formatting().getMain_text());
        holder.description.setTextSize(11);
    }

    @Override
    public int getItemCount() {
        return predictionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AMTextView description;

        public ViewHolder(View view) {
            super(view);
            description = (AMTextView) view.findViewById(R.id.mapRecyclerItem);
        }

    }

    public void updateLocationList(List<Predictions> list) {
        predictionsList.clear();
        predictionsList.addAll(list);
        notifyDataSetChanged();
    }
}
