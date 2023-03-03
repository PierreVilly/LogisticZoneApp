package com.example.logisticzoneapp.core.domain.model

class Reel(
    val uniqueCode: String,
    val designation: String,
    val reference: String,
    val quantity: String,
    val locations: Array<String>,
    val defaultLocation: String,
)
