package com.miro.miroappoauth.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app")
data class AppProperties(
    val miroBaseUrl: String,
    val miroApiBaseUrl: String,
    val teamId: Long?,
    val clientId: Long,
    val clientSecret: String,
    val appName: String?
)
