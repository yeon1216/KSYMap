package com.example.ksymap.data.remote.source

import com.example.ksymap.data.JobResult
import com.example.ksymap.data.remote.api.TmapService
import com.example.ksymap.data.remote.api.handleApi
import com.example.ksymap.data.remote.dto.TmapResponse
import java.net.URLEncoder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaceRemoteSource @Inject constructor(
    private val tmapService: TmapService
) {
    suspend fun searchBooks(
        query: String,
        page: Int = 1,
        count: Int = 20,
        version: Int = 1,
        areaLLCode: String? = null,
        areaLMCode: String? = null,
        resCoordType: String? = "WGS84GEO",
        searchType: String? = "name",
        multiPoint: String? = null,
        searchtypCd: String? = "A",
        radius: String? = null,
        reqCoordType: String? = "WGS84GEO",
        centerLon: Double? = null,
        centerLat: Double? = null,
        poiGroupYn: String? = "N",
        callback: String? = null,
        appKey: String
    ): JobResult<TmapResponse> = handleApi {
        println("ksy : query: $query, page: $page, count: $count")
        tmapService.searchPOIs(
            version = version.toString(),
            page = page,
            count = count,
            searchKeyword = URLEncoder.encode(query, "UTF-8"),
            areaLLCode = areaLLCode,
            areaLMCode = areaLMCode,
            resCoordType = resCoordType,
            searchType = searchType,
            multiPoint = multiPoint,
            searchtypCd = searchtypCd,
            radius = radius,
            reqCoordType = reqCoordType,
            centerLon = centerLon,
            centerLat = centerLat,
            poiGroupYn = poiGroupYn,
            callback = callback,
            appKey = appKey
        )
    }
}