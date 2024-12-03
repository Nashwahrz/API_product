package com.nashwa.api_product.service

//import android.telecom.Call
import com.nashwa.api_product.model.ResponProduk
import retrofit2.Call
import retrofit2.http.GET


interface ProdukService {

    @GET("products") //end point
    fun getAllProduk() : Call<ResponProduk>
}