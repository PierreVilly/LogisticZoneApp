package com.example.logisticzoneapp.core.util

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

@RequiresApi(Build.VERSION_CODES.O)
class BasicAuthInterceptor() :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = getCredentials()?.let {
            request.newBuilder()
                .header("Authorization", it).build()
        }
        return if(authCredentials != null){
            chain.proceed(authenticatedRequest!!)
        } else {
            chain.proceed(request)
        }
    }

    companion object AuthCredentials {
        private var authCredentials: String? = null

        fun setCredentials(username: String, password: String){
            this.authCredentials = Credentials.basic(username, password)
        }

        fun getCredentials(): String? {
            return this.authCredentials
        }
    }
}