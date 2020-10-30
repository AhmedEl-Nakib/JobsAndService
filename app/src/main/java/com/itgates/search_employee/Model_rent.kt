package com.itgates.search_employee

class Model_rent(private var image: String?,private  var productName: String?,private  var description: String?,private  var price: String?,private  var personName: String?, private  var personPhone: String?) {
    fun getImage(): String? {
        return image
    }

    fun setImage(image: String?) {
        this.image = image
    }

    fun getProductName(): String? {
        return productName
    }

    fun setProductName(productName: String?) {
        this.productName = productName
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String?) {
        this.price = price
    }

    fun getPersonName(): String? {
        return personName
    }

    fun setPersonName(personName: String?) {
        this.personName = personName
    }

    fun getPersonPhone(): String? {
        return personPhone
    }

    fun setPersonPhone(personPhone: String?) {
        this.personPhone = personPhone
    }
}