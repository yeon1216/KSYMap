package com.example.ksymap.data.remote.api

import com.example.ksymap.data.remote.dto.TmapResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmapService {

    @GET("/pois")
    suspend fun searchPOIs(
        @Query("version") version: String,
        @Query("page") page: Int,
        @Query("count") count: Int,
        @Query("searchKeyword") searchKeyword: String,
        @Query("areaLLCode") areaLLCode: String? = null,
        @Query("areaLMCode") areaLMCode: String? = null,
        @Query("resCoordType") resCoordType: String? = "WGS84GEO",
        @Query("searchType") searchType: String? = "name",
        @Query("multiPoint") multiPoint: String? = null,
        @Query("searchtypCd") searchtypCd: String? = "A",
        @Query("radius") radius: String? = null,
        @Query("reqCoordType") reqCoordType: String? = "WGS84GEO",
        @Query("centerLon") centerLon: Double? = null,
        @Query("centerLat") centerLat: Double? = null,
        @Query("poiGroupYn") poiGroupYn: String? = "N",
        @Query("callback") callback: String? = null,
        @Query("appKey") appKey: String
    ): TmapResponse // Define your response class accordingly

}