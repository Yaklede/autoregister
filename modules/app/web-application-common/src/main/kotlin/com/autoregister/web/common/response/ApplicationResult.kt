package com.autoregister.web.common.response

import org.springframework.http.HttpStatus

data class ApplicationResult(
    val code: Int,
    val message: String,
) {
    companion object {
        fun ok() = ApplicationResult(
            code = HttpStatus.OK.value(),
            message = "ok"
        )

        fun ok(message: String) = ApplicationResult(
            code = HttpStatus.OK.value(),
            message = message
        )

        fun created() = ApplicationResult(
            code = HttpStatus.CREATED.value(),
            message = "created"
        )

        fun created(message: String) = ApplicationResult(
            code = HttpStatus.CREATED.value(),
            message = message
        )

        fun badRequest() = ApplicationResult(
            code = HttpStatus.BAD_REQUEST.value(),
            message = "bad request"
        )

        fun badRequest(message: String) = ApplicationResult(
            code = HttpStatus.BAD_REQUEST.value(),
            message = message
        )

        fun unauthorized() = ApplicationResult(
            code = HttpStatus.UNAUTHORIZED.value(),
            message = "unauthorized"
        )

        fun unauthorized(message: String) = ApplicationResult(
            code = HttpStatus.UNAUTHORIZED.value(),
            message = message
        )

        fun serverError() = ApplicationResult(
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "server error"
        )

        fun serverError(message: String) = ApplicationResult(
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = message
        )
    }
}
