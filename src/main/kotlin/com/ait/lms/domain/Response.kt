package com.ait.lms.domain

import com.fasterxml.jackson.annotation.JsonProperty

class GeneralResponse (
        @JsonProperty(value = "request_id")
        val requestId: String,
        val status: Int,
        val message: String,
        val count: Int,
        val data : Any?
)