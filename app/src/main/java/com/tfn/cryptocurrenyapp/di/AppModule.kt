package com.tfn.cryptocurrenyapp.di

import com.tfn.cryptocurrenyapp.common.Constants
import com.tfn.cryptocurrenyapp.data.data_source.CoinPaprikaApi
import com.tfn.cryptocurrenyapp.data.repository.CoinRepositoryImpl
import com.tfn.cryptocurrenyapp.domain.repository.CoinRepository
import com.tfn.cryptocurrenyapp.domain.use_case.get_coin.GetCoinDetailsUseCase
import com.tfn.cryptocurrenyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun getCoinsUsecase(repository: CoinRepository): GetCoinsUseCase {
        return GetCoinsUseCase(repository)
    }

    @Provides
    @Singleton
    fun getCoinDetailsUsecase(repository: CoinRepository): GetCoinDetailsUseCase {
        return GetCoinDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun getRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun getPaprikaApi(): CoinPaprikaApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }
}