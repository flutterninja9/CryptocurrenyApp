package com.tfn.cryptocurrenyapp.domain.use_case.get_coin

import com.tfn.cryptocurrenyapp.common.Resource
import com.tfn.cryptocurrenyapp.domain.model.CoinDetail
import com.tfn.cryptocurrenyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String) : Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetails = repository.getCoin(coinId)
            emit(Resource.Loaded(coinDetails))
        } catch (e: HttpException) {
            emit(Resource.Failed(e.localizedMessage ?: "Unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Failed(e.localizedMessage ?: "Couldn't reach server. Check your internet connection!"))
        }
    }
}