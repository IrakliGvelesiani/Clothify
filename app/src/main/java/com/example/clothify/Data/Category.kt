package com.example.clothify.Data

sealed class Category(val category: String) {
    object Cargos: Category("Cargos")
    object CropTops: Category("Crop Tops")
    object Hoodies: Category("Hoodies")
    object Jeans: Category("Jeans")
    object Joggers: Category("Joggers")
    object Trousers: Category("Trousers")
}