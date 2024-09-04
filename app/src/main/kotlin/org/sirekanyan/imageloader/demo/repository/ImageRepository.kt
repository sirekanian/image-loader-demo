package org.sirekanyan.imageloader.demo.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.sirekanyan.imageloader.demo.adapter.DemoItemModel
import org.sirekanyan.imageloader.demo.extensions.toDemoItemModel
import org.sirekanyan.imageloader.demo.repository.model.ImageModel

interface ImageRepository {

    suspend fun getDemoItems(): List<DemoItemModel>
}

class ImageRepositoryImpl : ImageRepository {

    private val httpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override suspend fun getDemoItems(): List<DemoItemModel> =
        withContext(Dispatchers.IO) {
            httpClient.get(IMAGE_LIST_URL).body<List<ImageModel>>()
                .map(ImageModel::toDemoItemModel)
        }

    companion object {
        private const val IMAGE_LIST_URL: String =
            "https://zipoapps-storage-test.nyc3.digitaloceanspaces.com/image_list.json"
    }
}
