package ir.msit87.mylocationtest.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by MSIT on 10/3/2017.
 */

public interface MapModel {

    interface GetPredictionCallBack {

        void onSuccess(List<Predictions> list);

        void onFailure(String error);

        void onNetworkFailure();
    }

    interface GetLocationCallBack {

        void onSuccess(List<Results> list);

        void onFailure(String error);

        void onNetworkFailure();
    }

    interface GetProgressCallBack {

        void onCompleted(String message);

        void onError(String error);

        void onNext(String message);
    }


    interface GetDirectionCallBack {

        void onSuccess(List<LatLng> point);

        void onFailure(String error);
    }
}
