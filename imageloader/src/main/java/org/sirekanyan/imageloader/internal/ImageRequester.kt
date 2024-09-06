package org.sirekanyan.imageloader.internal

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal interface ImageRequester {

    suspend fun request(url: String): ByteReadChannel
}

internal class ImageRequesterImpl : ImageRequester {

    private val httpClient = HttpClient(OkHttp)

    override suspend fun request(url: String): ByteReadChannel =
        withContext(Dispatchers.IO) {
            httpClient.get(url).bodyAsChannel()
        }
}
