package com.example.restdog;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("image/random")
    Single<DogImage> loadImage();
}
