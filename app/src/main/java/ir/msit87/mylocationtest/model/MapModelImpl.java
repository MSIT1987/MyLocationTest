package ir.msit87.mylocationtest.model;

import java.util.List;

import ir.msit87.mylocationtest.api.ApiClient;

import ir.msit87.mylocationtest.modelDirection.DirectionResponse;
import ir.msit87.mylocationtest.modelDirection.InputDirectionQuery;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MapModelImpl Class Created by MSIT on 10/3/2017.
 */

public class MapModelImpl implements MapModel {

    public void getPredictions(final GetPredictionCallBack callBack, InputPredictionQuery inputPredictionQuery) {

//        ApiClient
//                .getInstance()
//                .getPredictions(inputPredictionQuery)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<PlaceAutoCompleteResult>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callBack.onFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(PlaceAutoCompleteResult placeAutoCompleteResult) {
//                        if (placeAutoCompleteResult.getStatus().equals("OK")) {
//                            List<Predictions> predictionsList = placeAutoCompleteResult.getPredictions();
//                            callBack.onSuccess(predictionsList);
//                        }
//                    }
//                });
    }

    public void getLocations(final GetLocationCallBack callBack, String place_id, String key) {

//        ApiClient.getInstance()
//                .getLocation(place_id, key)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<LocationResponse>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callBack.onFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(LocationResponse locationResponse) {
//                        if (locationResponse.getStatus().equals("OK")) {
//                            List<Results> resultsList = locationResponse.getResults();
//                            callBack.onSuccess(resultsList);
//                        }
//                    }
//                });
    }

    public void getDirection(final GetDirectionCallBack callBack, InputDirectionQuery inputDirectionQuery) {

        ApiClient.getInstance()
                .getDirection(inputDirectionQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<DirectionResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(DirectionResponse directionResponse) {
                        if (directionResponse.getStatus().equals("OK")) {

                        }
                        //callBack.
                    }
                });

    }

    public void asyncTasks(final GetProgressCallBack callBack, final String message) {

//        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                try {
//                    String res = message;//your original doInBackground
//                    subscriber.onNext(res);
//
//                    subscriber.onCompleted();
//                    // onNext would be comparable to AsyncTask.onProgressUpdate
//                    // and usually applies when background process runs a loop
//                } catch (Exception e) {
//                    // if the process throws an exception or produces a result
//                    // you'd consider error then use onError
//                    subscriber.onError(e);
//                }
//            }
//        });
//
//        observable
//                .observeOn(Schedulers.newThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
////                        mapView.dismissProgress();
//                        callBack.onCompleted(message);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
////                        mapView.errorProgress(e.getMessage());
//                        callBack.onError(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(String message) {
//                        // result from Observable.onNext. The methods below correspond
//                        // to their Observable counterparts.
////                        mapView.showProgress(message);
//                        callBack.onNext(message);
//                    }
//                });


    }

}
