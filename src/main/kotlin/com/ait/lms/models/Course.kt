package com.ait.lms.models

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
@Data
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @JsonProperty(value = "course_code")
    var courseCode: String?,

    @JsonProperty(value = "course_name")
    var courseName: String?,

    @JsonProperty(value = "lecturer_id")
    @ManyToOne
    var courseLecturer: Lecturer?,

    @JsonProperty(value = "created_on")
    var createdOn: LocalDateTime? = LocalDateTime.now(),

    @JsonProperty(value = "updated_on")
    var updatedOn: LocalDateTime? = LocalDateTime.now(),
){
    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null)
}