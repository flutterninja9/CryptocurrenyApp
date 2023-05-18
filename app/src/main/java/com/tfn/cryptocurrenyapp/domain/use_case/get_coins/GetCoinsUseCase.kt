package com.tfn.cryptocurrenyapp.domain.use_case.get_coins

import com.tfn.cryptocurrenyapp.common.Resource
import com.tfn.cryptocurrenyapp.domain.model.Coin
import com.tfn.cryptocurrenyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() : Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins()
            emit(Resource.Loaded(coins))
        } catch (e: HttpException) {
            emit(Resource.Failed(e.localizedMessage ?: "Unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Failed(e.localizedMessage ?: "Couldn't reach server. Check your internet connection!"))
        }
    }
}