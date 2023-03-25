package com.dicoding.githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubuser.api.response.ItemsItem
import com.dicoding.githubuser.api.response.UserResponse
import com.dicoding.githubuser.api.response.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listUser = MutableLiveData<ArrayList<ItemsItem>>()
    val listUser: LiveData<ArrayList<ItemsItem>> = _listUser

    private val _listSearchUser = MutableLiveData<ArrayList<ItemsItem>>()
    val listSearchUser: LiveData<ArrayList<ItemsItem>> = _listSearchUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getUser()
    }

    private fun getUser() {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUser("a")
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listUser.value = responseBody.items as ArrayList<ItemsItem>
                    }
                } else {
                    Log.e("TAG", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }

     fun getSearchUser(name: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUser(name)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _listSearchUser.value = response.body()?.items as ArrayList<ItemsItem>
                } else {
                    Log.e("TAG", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })

    }

}