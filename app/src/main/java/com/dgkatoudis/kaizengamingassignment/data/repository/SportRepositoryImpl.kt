package com.dgkatoudis.kaizengamingassignment.data.repository

import android.content.Context
import android.content.res.AssetManager
import com.dgkatoudis.kaizengamingassignment.data.mappers.RemoteToDomainSportsMapper
import com.dgkatoudis.kaizengamingassignment.data.model.RemoteSport
import com.dgkatoudis.kaizengamingassignment.domain.model.Resource
import com.dgkatoudis.kaizengamingassignment.domain.model.Sport
import com.dgkatoudis.kaizengamingassignment.domain.repository.SportRepository
import com.dgkatoudis.kaizengamingassignment.presentation.sports.SportsMapper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class SportRepositoryImpl(
    private val moshi: Moshi,
    private val context: Context,
    private val remoteToDomainSportsMapper: RemoteToDomainSportsMapper
) : SportRepository {
    override suspend fun getSports(): Resource<List<Sport>> {
        return withContext(Dispatchers.IO) {
            val type = Types.newParameterizedType(List::class.java, RemoteSport::class.java)
            val adapter: JsonAdapter<List<RemoteSport>> = moshi.adapter(type)
            val sports = adapter.fromJson(context.assets.readAssetsFile("sports.json"))
            Resource.Success(remoteToDomainSportsMapper.map(sports ?: emptyList()))
        }
    }

    private fun AssetManager.readAssetsFile(fileName: String): String =
        open(fileName).bufferedReader().use { it.readText() }
}

