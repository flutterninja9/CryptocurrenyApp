package com.tfn.cryptocurrenyapp.domain.model

import com.tfn.cryptocurrenyapp.data.dto.TeamMember

data class CoinDetail(
    val id: String,
    val isActive: Boolean,
    val logo: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<TeamMember>,
)
