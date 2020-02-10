package com.rjt.livedatamvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rjt.livedatamvvm.model.Category
import com.rjt.livedatamvvm.model.CategoryList
import com.rjt.livedatamvvm.network.ApiInterface
import com.rjt.livedatamvvm.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private var _listOfCategory = MutableLiveData<List<Category>>()

    val listOfCategory : LiveData<List<Category>>
        get() =_listOfCategory

    init {
        getAllCategories()
    }

    fun getAllCategories() {

        val call = ApiService.getClient().create(ApiInterface::class.java).getCategoryAll()


        call.enqueue(object : Callback<CategoryList> {
            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.e("error", t.message)
            }

            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                _listOfCategory.value = response.body()?.data
                
            }


        })
    }
}