package com.autoregister.crawling.response.domeme

import com.autoregister.crawling.response.domeme.enumeration.CountryType

data class MadeCountry(
    val countryType: CountryType,
    val countrySide: String,
    val city: String
) {
    companion object {
        fun parse(input: String): MadeCountry {
            val inputParseString = input.split("/")

            val countryType = when {
                inputParseString[0].contains("국산") -> CountryType.DOMESTIC
                else -> CountryType.IMPORTED
            }

            if (inputParseString.size != 3) {
                return MadeCountry(
                    countryType = countryType,
                    countrySide = "N/A",
                    city = "N/A"
                )
            }

            val side = inputParseString[1].trim()
            val city = inputParseString[2].trim()

            return MadeCountry(
                countryType = countryType,
                countrySide = side,
                city = city
            )
        }
    }
}
