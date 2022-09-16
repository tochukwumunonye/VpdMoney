package com.example.vpdmoney.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
@Parcelize
data class Address(
    val city: String,
    //val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
): Parcelable