package com.ait.lms.models

import com.ait.lms.interfaces.User
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
class Student(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @JsonProperty(value = "index_number")
    var indexNumber: String?,

    @JsonProperty(value = "last_name")
    override var surName: String?,

    @JsonProperty(value = "first_name")
    override var firstName: String?,

    @JsonProperty(value = "other_names")
    override var otherNames: String?,

    @JsonProperty(value = "email_address")
    override var emailAddress: String?,

    @JsonProperty(value = "program") var program: String?,

    @JsonProperty(value = "country") var country: String?,

    @JsonProperty(value = "is_active")
    override var isActive: Boolean?,

    @JsonProperty(value = "is_online") var isOnline: Boolean?,

    @JsonProperty(value = "is_verified")
    override var isVerified: Boolean?,

    @JsonProperty(value = "created_on")
    override var createdOn: String?,

    @JsonProperty(value = "updated_on")
    override var updatedOn: String?,

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    override var password: String?,
) : User{
    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null)
}