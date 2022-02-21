package com.example.data.api

import com.example.data.api.model.*
import retrofit2.Response
import retrofit2.http.*

interface IService {
    @GET("/sales")
    suspend fun getSale() : Response<List<SaleDTO>>

    @POST("/sales")
    suspend fun postSales(@Body sale: SaleDTO) : Response<PostResponseDTO>

    @DELETE("/sales/{id}")
    suspend fun deleteSales(@Path("row") row: String) : Response<Map<String, List<SaleDTO>>>

    @GET("/sales/price")
    suspend fun getPrice(@Body sale: SaleDTO) : Response<SaleDTO>

    @GET("/inventory")
    suspend fun getInventory(@Query("name") name: String) : Response<List<InventoryDTO>>

    @GET("/inventory")
    suspend fun getInventory() : Response<List<InventoryDTO>>

    @GET("/product")
    suspend fun getProduct(@Query("row") row: String) : Response<ProductDTO>
}