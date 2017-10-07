package ir.msit87.mylocationtest.listener;

/**
 * Created by MSIT on 10/6/2017.
 */

public interface OnSearchHolderClickListener {

    void onSearchHolderRecyclerClick(int position);

    void onTransferData(String place_id, String key);
}
