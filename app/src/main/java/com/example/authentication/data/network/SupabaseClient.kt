package com.example.authentication.data.network

import com.example.authentication.BuildConfig
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPERBASE_URL,
        supabaseKey = BuildConfig.SUPERBASE_KEY
    ){
        install(plugin=Auth)
    }
}