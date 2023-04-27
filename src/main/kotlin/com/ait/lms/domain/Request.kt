package com.ait.lms.domain

import com.ait.lms.enumerators.AccountType
import com.ait.lms.models.Course
import com.fasterxml.jackson.annotation.JsonProperty

class SignIn(
    @JsonProperty(value = "email_address")
    val email: String,
    @JsonProperty(value = "password")
    val password: String
)

class StudentSignIn(
    @JsonProperty(value = "index_number")
    val indexNumber: String,
    @JsonProperty(value = "password")
    val password: String
)


class CreateCourse(
    @JsonProperty(value = "course_code")
    val courseCode: String,
    @JsonProperty(value = "course_name")
    val courseName: String,
)

class EnrollCourse(
    @JsonProperty(value = "course_code")
    val courseCode: String,
    @JsonProperty(value = "index_number")
    var indexNumber: String,
)

class ConfirmEnrollMFA(
    @JsonProperty(value = "user_id")
    val memberId: String,
    @JsonProperty(value = "otp_code")
    var otpCode: String,
    @JsonProperty(value = "oob_code")
    var oobCode: String,
)

class ChallengeMFA(
    @JsonProperty(value = "user_id")
    val memberId: String,
    @JsonProperty(value = "otp_code")
    var otpCode: String,
    @JsonProperty(value = "oob_code")
    var oobCode: String,
)


class CreateStudentAccount(
    @JsonProperty(value = "email_address")
    var emailAddress: String,

    @JsonProperty(value = "first_name")
    var firstName: String,

    @JsonProperty(value = "last_name")
    var surName: String?,

    @JsonProperty(value = "other_names")
    var otherNames: String?,

    @JsonProperty(value = "program")
    var program: String?,

    @JsonProperty(value = "index_number")
    var indexNumber: String?,

    @JsonProperty(value = "country")
    var country: String?,
)

class CreateLecturer(
    @JsonProperty(value = "email_address")
    var emailAddress: String,

    @JsonProperty(value = "first_name")
    var firstName: String,

    @JsonProperty(value = "last_name")
    var surName: String?,

    @JsonProperty(value = "title")
    var title: String?,

    @JsonProperty(value = "course_code")
    var courseCode: String?,

    )


class CompleteAccount(

    @JsonProperty(value = "email_address")
    var emailAddress: String,

    @JsonProperty(value = "first_name")
    var firstName: String,

    @JsonProperty(value = "last_name")
    var surName: String?,

    @JsonProperty(value = "other_names")
    var otherNames: String?,

    @JsonProperty(value = "program")
    var program: String?,

    @JsonProperty(value = "index_number")
    var indexNumber: String?,

    )

