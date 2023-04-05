package com.example.praktikaarshinov.model

import com.google.gson.annotations.SerializedName

class Article {
    @SerializedName("Id")
    var Id = 0
    @SerializedName("Title")
    var Title = ""
    @SerializedName("Description")
    var Description = ""
    @SerializedName("DateOfPublication")
    var DateOfPublication = ""
    @SerializedName("Photo")
    var Photo = ""
    @SerializedName("LastName")
    var LastName = ""
    @SerializedName("MiddleName")
    var MiddleName = ""
    @SerializedName("FirstName")
    var FirstName = ""
    @SerializedName("Login")
    var Login = ""
    @SerializedName("Email")
    var Email = ""
    @SerializedName("Gender")
    var Gender = 0
}