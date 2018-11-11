package com.example.cybersecurity.hackaton.sentinela2.service;

import com.example.cybersecurity.hackaton.sentinela2.Models.Mensagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MensagemService {

    @GET("chatbot")
    Call<List<Mensagem>> getMensagem();

    @POST("chatbot")
    Call<List<Mensagem>> savePost(@Body String mensagem);

}
