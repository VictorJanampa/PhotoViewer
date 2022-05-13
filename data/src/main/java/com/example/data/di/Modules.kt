package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.PhotoDatabase
import com.example.data.database.PhotoDatabaseDao
import com.example.data.network.PhotoApiService
import com.example.data.repository.PhotoRepositoryImpl
import com.example.domain.repository.PhotoRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

//val repositoryModule = module {
//    single { PhotoRepositoryImpl(get(),get()) }
//}
//
//val apiModule = module {
//
//    fun provideApi(retrofit: Retrofit): PhotoApiService {
//        return retrofit.create(PhotoApiService::class.java)
//    }
//    single { provideApi(get()) }
//}
//
//val retrofitModule = module {
//    single { provideMoshi() }
//    single { provideRetrofit(get()) }
//}
//
//val databaseModule = module {
//    single { provideDataBase(androidApplication()) }
//    single { provideDao(get()) }
//}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDao(photoDatabase: PhotoDatabase): PhotoDatabaseDao {
        return photoDatabase.photoDatabaseDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): PhotoDatabase {
        return Room.databaseBuilder(context, PhotoDatabase::class.java, "photos_database")
            .fallbackToDestructiveMigration()
            .build()
    }

}

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): PhotoApiService {
        return retrofit.create(PhotoApiService::class.java)
    }
    @Provides
    fun provideMoshi() : Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    @Provides
    fun provideRetrofit(moshi: Moshi) : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun bindRepository(impl: PhotoRepositoryImpl): PhotoRepository
}