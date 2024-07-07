package com.example.ksymap.data.repository

import com.example.ksymap.data.JobResult
import com.example.ksymap.data.remote.dto.TmapResponse
import com.example.ksymap.data.remote.source.PlaceRemoteSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaceRepository @Inject constructor(
    private val placeRemoteSource: PlaceRemoteSource
) {



    suspend fun searchPlaces(
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
        callback: String? = null
    ): JobResult<TmapResponse> {
        return placeRemoteSource.searchBooks(
            query = query,
            page = page,
            count = count,
            version = version,
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
            callback = callback
        )
    }

}