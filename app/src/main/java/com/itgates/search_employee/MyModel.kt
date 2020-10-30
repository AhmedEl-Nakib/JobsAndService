package com.itgates.search_employee

class MyModel(private var Category: String?,private var place: String?,private var description: String?,private var Uid: String?) {
    fun getCategory(): String? {
        return Category
    }

    fun setCategory(category: String?) {
        Category = category
    }

    fun getPlace(): String? {
        return place
    }

    fun setPlace(place: String?) {
        this.place = place
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getUid(): String? {
        return Uid
    }

    fun setUid(uid: String?) {
        Uid = uid
    }
}