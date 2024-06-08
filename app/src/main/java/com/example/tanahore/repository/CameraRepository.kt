package com.example.tanahore.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.tanahore.data.Results
import com.example.tanahore.data.response.IotResponse
import com.example.tanahore.data.response.ScanResponse
import com.example.tanahore.data.retrofit.ServiceApi
import okhttp3.MultipartBody

class CameraRepository (
    private val apiService: ServiceApi
){
    fun postImage(
        file: MultipartBody.Part
    ): LiveData<Results<ScanResponse>> = liveData {
        emit(Results.Loading)
        try {
            val response = apiService.postImage(file)
            emit(Results.Success(response))
        } catch (e: Exception) {
            Log.d("post_image", e.message.toString())
            emit(Results.Error(e.message.toString()))
        }
    }

    fun postPh(jenisTanah: String, id: String): LiveData<Results<IotResponse>> = liveData {
        emit(Results.Loading)
        try {
            val response = apiService.postPh(jenisTanah, id)
            emit(Results.Success(response))
        } catch (e: Exception) {
            Log.d("post_ph", e.message.toString())
            emit(Results.Error(e.message.toString()))
        }
    }
}