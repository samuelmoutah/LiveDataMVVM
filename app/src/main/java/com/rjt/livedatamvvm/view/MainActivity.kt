package com.rjt.livedatamvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.rjt.livedatamvvm.R
import com.rjt.livedatamvvm.adapter.AdapterCategory
import com.rjt.livedatamvvm.model.Category
import com.rjt.livedatamvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapterCategory: AdapterCategory

    lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Providing ViewModel using ViewModelProviders
        // accessing to viewmodel from this activity
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)



        // creating object of observer which update recycler view
        val nameObserver = Observer<List<Category>>{
            adapterCategory = AdapterCategory(application, it)
            recycler_view.adapter = adapterCategory
        }

        // observe live data, passing in this activity as the lifecycle owner and the observer
        mainViewModel.listOfCategory.observe(this, nameObserver)


        //??
//        mainViewModel.listOfCategory.observe(this, Observer {
//            adapterCategory = AdapterCategory(application, it)
//            recycler_view.adapter = adapterCategory
//        })
    }
}
