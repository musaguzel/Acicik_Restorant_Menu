package com.musaguzel.acicik.model

import com.google.firebase.firestore.DocumentId

data class Posts(
    @DocumentId
    val documentId: String = "",
    val imageUrl: String = "",
    val productName: String = "",
    val productPrice: String = ""
)

data class addedOrdersPosts(
        val productName: String = "",
        val productPrice: String = "",
        var productNumber: Int = 0
)