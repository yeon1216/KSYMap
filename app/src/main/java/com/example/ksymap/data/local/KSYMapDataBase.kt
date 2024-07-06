//package com.example.ksymap.data.local
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverter
//import androidx.room.TypeConverters
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//
//@Database(entities = [], version = 1, exportSchema = true)
//@TypeConverters(Converters::class)
//abstract class KSYMapDatabase: RoomDatabase() {
////    abstract fun bookInfoDao(): BookInfoDao
//
//    companion object {
//
//        private const val DATABASE_NAME = "ksymap"
//
//        @Volatile private var instance: KSYMapDatabase? = null
//
//        fun getInstance(context: Context): KSYMapDatabase {
//            return instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }
//        }
//
//        private fun buildDatabase(context: Context): KSYMapDatabase {
//            return Room.databaseBuilder(context, KSYMapDatabase::class.java, DATABASE_NAME)
//                .build()
//        }
//
//    }
//
//}
//
//class Converters {
//    @TypeConverter
//    fun fromString(value: String): List<String> {
//        val listType = object : TypeToken<List<String>>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromList(list: List<String>): String {
//        return Gson().toJson(list)
//    }
//}
