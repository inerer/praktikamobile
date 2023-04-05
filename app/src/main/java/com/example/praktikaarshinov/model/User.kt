package com.example.praktikaarshinov.model

import com.google.gson.annotations.SerializedName

class User {
    var Id: Int = 0;
    @SerializedName("firstName")
    var UserFirstName:String = ""
    @SerializedName("lastName")
    var UserLastName:String = ""
    @SerializedName("middleName")
    var UserMiddleName = ""
    @SerializedName("email")
    var UserEmail = ""
    @SerializedName("role")
    var UserRole = ""
    @SerializedName("password")
    var UserPassword = ""
    @SerializedName("login")
    var UserLogin = ""
    @SerializedName("photo")
    var UserPhoto = ""
    @SerializedName("gender")
    var UserGender = ""
    @SerializedName("dateOfBirth")
    var UserBithday = ""
}