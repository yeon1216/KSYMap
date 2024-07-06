package com.example.ksymap.data.remote.dto

data class TmapResponse(
    val searchPoiInfo: SearchPoiInfo
)

data class SearchPoiInfo(
    val totalCount: Int,
    val count: Int,
    val page: Int,
    val pois: Pois
)

data class Pois(
    val poi: List<Poi>
)

data class Poi(
    val id: String,
    val pkey: String,
    val navSeq: String,
    val collectionType: String,
    val name: String,
    val telNo: String,
    val frontLat: Double,
    val frontLon: Double,
    val noorLat: Double,
    val noorLon: Double,
    val upperAddrName: String,
    val middleAddrName: String,
    val lowerAddrName: String,
    val detailAddrName: String,
    val mlClass: String,
    val firstNo: String,
    val secondNo: String,
    val roadName: String,
    val firstBuildNo: String,
    val secondBuildNo: String,
    val radius: String,
    val upperBizName: String,
    val middleBizName: String,
    val lowerBizName: String,
    val detailBizName: String,
    val rpFlag: String,
    val parkFlag: Int?,
    val detailInfoFlag: Int?,
    val desc: String,
    val dataKind: String,
    val zipCode: String,
    val adminDongCode: String,
    val legalDongCode: String,
    val buildingName: String,
    val evChargers: EvChargers?,
    val groupSubLists: GroupSubLists?,
    val newAddressList: NewAddressList
)

data class EvChargers(
    val evCharger: List<EvCharger>
)

data class EvCharger(
    val operatorId: String,
    val stationId: String,
    val chargerId: String,
    val status: String,
    val type: String,
    val powerType: String,
    val operatorName: String,
    val chargingDateTime: String,
    val updateDateTime: String,
    val isFast: String,
    val isAvailable: String
)

data class GroupSubLists(
    val groupSub: List<GroupSub>
)

data class GroupSub(
    val subPkey: String,
    val subSeq: String,
    val subName: String,
    val subCenterY: Double,
    val subCenterX: Double,
    val subNavY: Double,
    val subNavX: Double,
    val subRpFlag: String,
    val subPoiId: String,
    val subNavSeq: String,
    val subParkYn: String,
    val subClassCd: String,
    val subClassNmA: String,
    val subClassNmB: String,
    val subClassNmC: String,
    val subClassNmD: String
)

data class NewAddressList(
    val newAddress: List<NewAddress>
)

data class NewAddress(
    val centerLat: Double,
    val centerLon: Double,
    val frontLat: Double,
    val frontLon: Double,
    val roadName: String,
    val bldNo1: String,
    val bldNo2: String,
    val roadId: String,
    val fullAddressRoad: String
)