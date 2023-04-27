package com.ait.lms.interfaces

interface User {
    val surName: String?
    val firstName : String?
    var otherNames : String?
    var emailAddress : String?
    var password: String?
    var isActive: Boolean?
    var isVerified: Boolean?
    var createdOn: String?
    var updatedOn: String?
}