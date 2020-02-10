package com.rjt.livedatamvvm.network

import com.rjt.livedatamvvm.model.CategoryList
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("category")

    fun getCategoryAll() : Call<CategoryList>
}