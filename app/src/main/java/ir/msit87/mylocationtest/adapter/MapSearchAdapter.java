package ir.msit87.mylocationtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import ir.msit87.mylocationtest.R;
import ir.msit87.mylocationtest.api.ApiService;
import ir.msit87.mylocationtest.frameWork.ui.custom.AMTextView;
import ir.msit87.mylocationtest.listener.OnSearchHolderClickListener;
import ir.msit87.mylocationtest.listener.OnSearchRecyclerClickListener;
import ir.msit87.mylocationtest.model.Predictions;

/**
 * MapSearchAdapter Class Created by MSIT on 10/3/2017.
 */

public class MapSearchAdapter extends RecyclerView.Adapter<MapSearchAdapter.ViewHolder> implements OnSearchHolderClickListener {

    private List<Predictions> predictionsList;

    private Context context;

    private OnSearchRecyclerClickListener searchRecyclerClickListener;

    public MapSearchAdapter(Context context, OnSearchRecyclerClickListener searchRecyclerClickListener) {
        this.context = context;
        this.searchRecyclerClickListener = searchRecyclerClickListener;
        predictionsList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.map_result_row, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.description.setText(predictionsList.get(position).getStructured_formatting().getMain_text());
        holder.description.setText(predictionsList.get(position).getDescription());
        holder.description.setTextSize(11);
        holder.render(position);
    }

    @Override
    public int getItemCount() {
        return predictionsList.size();
    }

    @Override
    public void onSearchHolderRecyclerClick(int position) {
        searchRecyclerClickListener.onSearchRecyclerClick(predictionsList.get(position));
    }

    @Override
    public void onTransferData(String place_id, String key) {
        searchRecyclerClickListener.onGetData(place_id, key);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private int holderPosition;

        @Bind(R.id.mapRecyclerItem)
        AMTextView description;

        @Bind(R.id.mapPlaceList)
        View layout;

        OnSearchHolderClickListener clickListener;

        ViewHolder(View view, OnSearchHolderClickListener clickListener) {
            super(view);
            ButterKnife.bind(this, view);
            //description.setOnClickListener(this);
            this.clickListener = clickListener;
        }

        void render(int position) {
            holderPosition = position;
            // TODO: set default image or placeholder
        }


        @OnClick(R.id.mapPlaceList)
        void onClick() {
            if (clickListener == null)
                return;
            clickListener.onSearchHolderRecyclerClick(holderPosition);
            String place_id = predictionsList.get(holderPosition).getPlace_id();
            String key = ApiService.API_KEY;
            clickListener.onTransferData(place_id, key);
        }
    }

    public void updateLocationList(List<Predictions> list) {
        predictionsList.clear();
        predictionsList.addAll(list);
        notifyDataSetChanged();
    }
}
