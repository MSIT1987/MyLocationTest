package ir.msit87.mylocationtest.model;

import java.util.List;

/**
 * Created by MSIT on 10/3/2017.
 */

public interface MapModel {

    interface GetMapCallBack {
        void onSuccess(List<Predictions> list);

        void onFailure(String error);

        void onNetworkFailure();
    }
}
