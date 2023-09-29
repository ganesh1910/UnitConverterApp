package com.gk.unitconverterapp.di

import android.content.Context
import androidx.room.Room
import com.gk.unitconverterapp.constant.Constants.DATABASE_NAME
import com.gk.unitconverterapp.data.ConverterDatabase
import com.gk.unitconverterapp.data.ConverterRepository
import com.gk.unitconverterapp.data.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConverterDatabase(
        @ApplicationContext context: Context
    ): ConverterDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = ConverterDatabase::class.java,
            name = DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideConverterRepository(db: ConverterDatabase): ConverterRepository {
        return ConverterRepositoryImpl(db.getConvertDAO())
    }
}