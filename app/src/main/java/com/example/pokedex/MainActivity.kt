package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btPesquisar.setOnClickListener {
            pesquisar()
        }


    }

    private fun pesquisar() {

        val okhttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build()

        val gitHubService = retrofit.create(PokedexService::class.java)
        gitHubService.buscarPokemon(etPesquisa.text.toString())
            .enqueue(object : Callback<Pokemon> {
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Toast.makeText(this@MainActivity,
                        t.message,
                        Toast.LENGTH_LONG)
                        .show()
                }

                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if(response.isSuccessful) {
                        val usuario = response.body()
                        tvNomePokemon.text = usuario?.nome
                        Picasso.get()
                            .load(usuario?.stripes?.imagem)
                            .error(R.mipmap.ic_launcher)
                            .placeholder(R.mipmap.ic_launcher)
                            .into(ivImagem)

                    } else {
                        Toast.makeText(this@MainActivity,
                            "Deu ruim",
                            Toast.LENGTH_LONG)
                            .show()
                    }
                }
            })
    }
}
