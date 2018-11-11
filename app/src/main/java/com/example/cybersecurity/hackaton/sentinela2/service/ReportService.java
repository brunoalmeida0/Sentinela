package com.example.cybersecurity.hackaton.sentinela2.service;

import com.example.cybersecurity.hackaton.sentinela2.Models.Report;

import java.util.List;

import retrofit2.http.GET;

public interface ReportService {

    @GET("incidentes")
    List<Report> getAllReports();


}
