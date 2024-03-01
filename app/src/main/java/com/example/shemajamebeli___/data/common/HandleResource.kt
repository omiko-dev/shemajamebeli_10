package com.example.shemajamebeli___.data.common

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class HandleResource {
    fun <T: Any> handleResource(call: suspend () -> Response<T>): Flow<Resource<T>> =
        flow {
            try {
                emit(Resource.Loader(loader = true))
                val data = call()
                if(data.isSuccessful){
                    emit(Resource.Success(success = data.body()!!))
                }else{
                    emit(Resource.Error(error = data.errorBody()?.string() ?: ""))
                }
            }catch (e: Throwable){
                Log.i("omiko", e.message ?: "")
            }finally {
                emit(Resource.Loader(loader = false))
            }
        }
}