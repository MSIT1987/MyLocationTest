package ir.msit87.mylocationtest.api;

import ir.msit87.mylocationtest.model.InputPredictionQuery;
import ir.msit87.mylocationtest.model.LocationResponse;
import ir.msit87.mylocationtest.model.PlaceAutoCompleteResult;
import ir.msit87.mylocationtest.modelDirection.DirectionResponse;
import ir.msit87.mylocationtest.modelDirection.InputDirectionQuery;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.Query;
import rx.Observable;

/**
 * ApiClient Class Created by MSIT on 10/3/2017.
 */

public class ApiClient {

    private ApiService apiService;

    private static ApiClient instance;

    public ApiClient() {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(ApiService.BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public Observable<PlaceAutoCompleteResult> getPredictions(InputPredictionQuery inputPredictionQuery) {
        return apiService.getPredictions(inputPredictionQuery.getInput(),
                inputPredictionQuery.getRadius(),
                inputPredictionQuery.getLanguage(),
                inputPredictionQuery.getComponent(),
                inputPredictionQuery.getKey());
    }

    public Observable<LocationResponse> getLocation(String place_id, String key) {
        return apiService.getLocation(place_id, key);
    }

    public Observable<DirectionResponse> getDirection(InputDirectionQuery inputDirectionQuery) {
        return apiService.getDirection(inputDirectionQuery.getOrigin(),
                inputDirectionQuery.getDestination(),
                inputDirectionQuery.getSensor(),
                inputDirectionQuery.getMode());
//                inputDirectionQuery.getKey());
    }
}
