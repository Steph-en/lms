package com.ait.lms.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class CourseRegistration(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @JsonProperty(value = "student")
    @ManyToOne
    var student: Student?,

    @JsonProperty(value = "course")
    @ManyToOne
    var course: Course?,

    @JsonProperty(value = "created_on")
    var createdOn: LocalDateTime? = LocalDateTime.now(),

    @JsonProperty(value = "updated_on")
    var updatedOn: LocalDateTime? = LocalDateTime.now(),
) {constructor() : this(
    null,
    null,
    null,
    null,
    null)

}