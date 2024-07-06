//package com.example.ksymap.di
//
//import android.content.Context
//import com.example.ksymap.data.local.KSYMapDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@InstallIn(SingletonComponent::class)
//@Module
//class LocalDatabaseModule {
//
//    @Singleton
//    @Provides
//    fun provideSearchBookDatabase(@ApplicationContext applicationContext: Context): KSYMapDatabase {
//        return KSYMapDatabase.getInstance(applicationContext)
//    }
//
////    @Provides
////    fun provideBookInfoDao(db: KSYMapDatabase): BookInfoDao {
////        return db.bookInfoDao()
////    }
//
//}
