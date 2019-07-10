package com.vietthanh.tikidisplaykeyword.common.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIInteract {
    @GET("keywords.json")
    Observable<String[]> getKeywords();
}
