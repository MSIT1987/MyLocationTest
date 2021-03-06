package ir.msit87.mylocationtest.api;

import ir.msit87.mylocationtest.model.LocationResponse;
import ir.msit87.mylocationtest.model.PlaceAutoCompleteResult;
import ir.msit87.mylocationtest.model.Predictions;
import ir.msit87.mylocationtest.model.Results;
import ir.msit87.mylocationtest.modelDirection.DirectionResponse;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * ApiService Interface Created by MSIT on 10/3/2017.
 */

public interface ApiService {

    /**
     * Google Map Location Constant
     */
    String BASE_URL = "https://maps.googleapis.com/maps/api/";
    String API_KEY = "AIzaSyCGljB_xvmHA037DARMHiDwWs6etWXh9gI";
    String RADIUS = "500";
    String LANGUAGE = "fa";
    String COMPONENT = "country:ir";

    /**
     * Google Map Direction Constant
     */
    String SENSOR = "sensor=false";
    String MODE = "mode=driving";


    @GET("place/autocomplete/json")
    Observable<PlaceAutoCompleteResult> getPredictions(@Query("input") String input,
                                                       @Query("radius") String radius,
                                                       @Query("language") String language,
                                                       @Query("component") String component,
                                                       @Query("key") String key);

    @GET("geocode/json")
    Observable<LocationResponse> getLocation(@Query("place_id") String place_id,
                                             @Query("key") String key);


    @GET("directions/json")
    Observable<DirectionResponse> getDirection(@Query("origin") String origin,
                                               @Query("destination") String destination,
                                               @Query("sensor") String sensor,
                                               @Query("mode") String mode);
//                                               @Query("key") String key);

}
