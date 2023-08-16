package com.autoregister.web.common.response

data class ApplicationResponse<T>(
    val result: ApplicationResult,
    val payload: T
) {
    companion object {
        fun <T> ok(payload: T) = ApplicationResponse(
            result = ApplicationResult.ok(),
            payload = payload
        )

        fun <T> ok(message: String, payload: T) = ApplicationResponse(
            result = ApplicationResult.ok(message),
            payload = payload
        )

        fun <T> created(payload: T) = ApplicationResponse(
            result = ApplicationResult.created(),
            payload = payload
        )

        fun <T> created(message: String, payload: T) = ApplicationResponse(
            result = ApplicationResult.created(message),
            payload = payload
        )

        fun <T> badRequest(payload: T) = ApplicationResponse(
            result = ApplicationResult.badRequest(),
            payload = payload
        )

        fun <T> badRequest(message: String, payload: T) = ApplicationResponse(
            result = ApplicationResult.badRequest(message),
            payload = payload
        )

        fun <T> unauthorized(payload: T) = ApplicationResponse(
            result = ApplicationResult.unauthorized(),
            payload = payload
        )

        fun <T> unauthorized(message: String, payload: T) = ApplicationResponse(
            result = ApplicationResult.unauthorized(message),
            payload = payload
        )

        fun <T> serverError(payload: T) = ApplicationResponse(
            result = ApplicationResult.serverError(),
            payload = payload
        )

        fun <T> serverError(message: String, payload: T) = ApplicationResponse(
            result = ApplicationResult.serverError(message),
            payload = payload
        )

    }
}
