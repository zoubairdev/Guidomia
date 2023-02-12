package com.example.guidomia.models

data class Parent(
    val model: String,
    val customerPrice: Double,
    val rating: Int,
    val imgFile : String,
    val children: List<Child>
)
