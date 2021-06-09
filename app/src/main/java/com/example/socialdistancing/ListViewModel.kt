package com.example.socialdistancing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class ListViewModel:ViewModel() {
    val listEntry = MutableLiveData<ArrayList<Entry>>()

    fun getListEntry(){
        val entries = arrayListOf<Entry>()
        val client = AsyncHttpClient()
        val url = "https://storage.googleapis.com/social-distancing-monitoring-b21/output1.json"
        client.get(url, object: AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                val result = String(responseBody)
                try{
                    val responseObject = JSONObject(result)
                    val items = responseObject.getJSONArray("Image")
                    for(i in 0 until items.length()) {
                        val item = items.getString(i)
                        val photo = item
                        val ii = i.toString()
                        val entry = Entry(photo, ii)
                        entries.add(entry)
                    }
                    listEntry.postValue(entries)
                }
                catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("Fail", error.message.toString())
            }
        })
    }

    fun getEntry(): LiveData<ArrayList<Entry>> {
        return listEntry
    }

}
