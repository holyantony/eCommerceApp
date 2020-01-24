package com.wc.heady.network;




import com.wc.heady.model.ECommerceData;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface APIServices {

  @GET("json")
  Flowable<ECommerceData> getECommerceData();
}

