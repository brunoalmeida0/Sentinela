package com.example.cybersecurity.hackaton.sentinela2.data;

import com.example.cybersecurity.hackaton.sentinela2.service.MensagemService;
import com.example.cybersecurity.hackaton.sentinela2.service.ReportService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInit {

    private final Retrofit retrofit;

    public RetrofitInit() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://172.29.40.44:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public ReportService getReportService() {
        return this.retrofit.create(ReportService.class);
    }
    public MensagemService getMensagemService() { return this.retrofit.create(MensagemService.class); }

}
