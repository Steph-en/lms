package com.ait.lms.enumerators

enum class Status(
        val status: Int
) {
    OK(200),
    CREATED(201),
    NO_CONTENT(204),
    NOT_MODIFIED(304),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500)
}