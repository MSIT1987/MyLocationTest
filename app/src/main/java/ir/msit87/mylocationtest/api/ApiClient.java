package ir.msit87.mylocationtest.api;

import ir.msit87.mylocationtest.model.InputQuery;
import ir.msit87.mylocationtest.model.PlaceAutoCompleteResult;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
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

    public Observable<PlaceAutoCompleteResult> getPredictions(InputQuery inputQuery) {
        return apiService
                .getPredictions(inputQuery.getInput(),
                        inputQuery.getRadius(),
                        inputQuery.getLanguage(),
                        inputQuery.getComponent(),
                        inputQuery.getKey());
    }

    //    public Observable<Predictions> getPredictions(String input,
//                                                  String radius,
//                                                  String language,
//                                                  String component,
//                                                  String key) {
}
