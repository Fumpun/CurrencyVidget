package com.currencyvidget.UI

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

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        retService = RetrofitFactory
            .getRetrofitInstance()
            .create(InterfaceCurrency::class.java)
        getRequestWithQueryParameters()
    }



    private fun getRequestWithQueryParameters (){

        val responseLiveData: LiveData<Response<CurrencyData>> = liveData {
            val response = retService.getSortedCurrency("RUB")
            emit(response)
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














    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

     */
