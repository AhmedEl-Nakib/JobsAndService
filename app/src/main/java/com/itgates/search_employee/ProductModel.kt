package com.itgates.search_employee

class ProductModel(private val imgUrl: String?, private val title: String?, private val price: String?) {
    fun getImgUrl(): String? {
        return imgUrl
    }

    fun getTitle(): String? {
        return title
    }

    fun getPrice(): String? {
        return price
    }
}