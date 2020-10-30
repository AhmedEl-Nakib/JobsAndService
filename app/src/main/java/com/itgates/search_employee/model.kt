package com.itgates.search_employee

class model(private var name: String?, private var phone: String?, private var salary: String?) {
    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

    fun getSalary(): String? {
        return salary
    }

    fun setSalary(salary: String?) {
        this.salary = salary
    }
}