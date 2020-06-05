package com.currencyvidget.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.currencyvidget.R
import com.currencyvidget.data.CurrencyData
import com.currencyvidget.data.InterfaceCurrency
import com.currencyvidget.network.RetrofitFactory
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var retService: InterfaceCurrency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("check0")
        setContentView(R.layout.activity_main)
        println("check1")
        retService = RetrofitFactory
            .getRetrofitInstance()
            .create(InterfaceCurrency::class.java)
        getRequestWithQueryParameters()
    }



    private fun getRequestWithQueryParameters (){
        println("check2")
        val responseLiveData: LiveData<Response<CurrencyData>> = liveData {
            val response = retService.getSortedCurrency("RUB")
            emit(response)
            println("$response")
        }

        responseLiveData.observe(this, Observer {
            val currencyList = it.body()
            if (currencyList != null) {
                val result = " " + "base : ${currencyList.base}" + "\n" +
                            " " + "date : ${currencyList.date}" + "\n" +
                            " " + "rates : ${currencyList.rates.EUR}" + "\n\n\n"
                text_view_currency.append(result)
                }
            })
        }
}










