package com.nattv.app.domain.model

enum class IptvSourceType { M3U, XTREAM }

data class IptvAccount(
    val id: String,
    val displayName: String,
    val sourceType: IptvSourceType,
    val serverUrl: String,
    val username: String? = null,
    val isEnabled: Boolean = true
)

data class TvChannel(
    val id: String,
    val accountId: String,
    val name: String,
    val streamUrl: String,
    val logoUrl: String? = null,
    val groupName: String? = null,
    val tvgId: String? = null,
    val isFavorite: Boolean = false
)

data class Program(
    val id: String,
    val accountId: String,
    val channelId: String,
    val title: String,
    val description: String? = null,
    val startUtcMillis: Long,
    val endUtcMillis: Long
)