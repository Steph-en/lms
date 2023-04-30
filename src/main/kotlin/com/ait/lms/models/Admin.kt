package com.ait.lms.models

import com.ait.lms.interfaces.User
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Admin(
    @Id
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
): User{
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
        null)
}