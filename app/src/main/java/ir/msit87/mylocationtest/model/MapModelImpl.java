package ir.msit87.mylocationtest.model;

import java.util.List;

import ir.msit87.mylocationtest.api.ApiClient;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MapModelImpl Class Created by MSIT on 10/3/2017.
 */

public class MapModelImpl implements MapModel {

    public void FetchDataFromGoogleMap(final GetMapCallBack callBack, InputQuery inputQuery) {

        ApiClient
                .getInstance()
                .getPredictions(inputQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<PlaceAutoCompleteResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(PlaceAutoCompleteResult placeAutoCompleteResult) {
                        if (placeAutoCompleteResult.getStatus().equals("OK")) {
                            List<Predictions> predictionsList = placeAutoCompleteResult.getPredictions();
                            callBack.onSuccess(predictionsList);
                        }
                    }
                });
    }
}
