package com.dicoding.githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubuser.api.response.ApiConfig
import com.dicoding.githubuser.api.response.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {

    private var _dataUser = MutableLiveData<DetailUserResponse>()
    val dataUser: LiveData<DetailUserResponse> = _dataUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

     fun getDataUser(username: String) {
         if (dataUser.value == null) {
             _isLoading.value = true
             val client = ApiConfig.getApiService().getDetailUser(username)
             client.enqueue(object: Callback<DetailUserResponse> {
                 override fun onResponse(
                     call: Call<DetailUserResponse>,
                     response: Response<DetailUserResponse>
                 ) {
                     _isLoading.value = false
                     if (response.isSuccessful) {
                         _dataUser.value = response.body()
                         Log.d("TAG", "onResponse: ${response.body()}")
                     } else {
                         Log.e("TAG", "onFailureResponse: ${response.message()}")
                     }
                 }

                 override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                     _isLoading.value = false
                     Log.e("TAG", "onFailure: ${t.message}")
                 }
             })
         }
    }
}