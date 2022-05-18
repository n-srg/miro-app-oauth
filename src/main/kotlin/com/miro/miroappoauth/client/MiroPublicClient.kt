package com.miro.miroappoauth.client

import com.miro.miroappoauth.dto.*
import com.miro.miroappoauth.dto.CreateBoardDto.SharingPolicyDto
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.web.client.RestTemplate

/**
 * See [Miro REST API](https://developers.miro.com/reference).
 * Note: we use camelCase for json parsing here.
 */
class MiroPublicClient(
    private val rest: RestTemplate
) {

    fun getSelfUser(accessToken: String): UserDto {
        val headers = HttpHeaders().apply {
            //set(HttpHeaders.HOST, "api.miro.com")
            setBearerAuth(accessToken)
        }
        val request = HttpEntity<Any>(null, headers)

        return rest.exchange("/v1/users/me", GET, request, UserDto::class.java).body!!
    }

    fun getSelfUserV2(accessToken: String, userId: Long): UserDto {
        val headers = HttpHeaders().apply { setBearerAuth(accessToken) }
        val request = HttpEntity<Any>(null, headers)

        return rest.exchange("/v2/users/{userId}", GET, request, UserDto::class.java, userId).body!!
    }

    fun createBoard(
        accessToken: String,
        name: String,
        accessType: AccessType,
        teamAccessType: TeamAccessType
    ): BoardDto {
        val headers = HttpHeaders().apply { setBearerAuth(accessToken) }
        val body = CreateBoardDto(name, SharingPolicyDto(accessType, teamAccessType))
        val request = HttpEntity<Any>(body, headers)

        return rest.exchange("/v2alpha/boards", POST, request, BoardDto::class.java).body!!
    }

    // todo GET https://api.miro.com/v1/oauth-token
}
