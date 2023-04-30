package com.ait.lms.models

import com.ait.lms.interfaces.User
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
@NoArgsConstructor
@AllArgsConstructor
class Lecturer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    override var surName: String?,
    override var firstName: String?,
    override var otherNames: String?,
    override var emailAddress: String?,
    override var password: String?,
    override var isActive: Boolean?,
    override var isVerified: Boolean?,
    override var createdOn: String?,
    override var updatedOn: String?,

    @OneToOne
    var course: Course?,
): User {
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
        null)
}