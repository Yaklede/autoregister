package com.autoregister.crawling.response.domeme

import com.autoregister.crawling.exception.CrawlingException
import java.util.regex.Pattern

data class DeliveryInfo(
    val defaultDeliveryDay: Int,
    val averageShippingDays: Double,
    val deliveryCost: Int,
    val bundledDelivery: Boolean,
) {
    companion object {
        fun parse(input: String): DeliveryInfo {
            var input = input.replace("\n", "")
            val patternList = getPatterns()
            var index = 0
            var regex = Pattern.compile(patternList[index])
            var matchResult = regex.matcher(input)

            while (!matchResult.find()) {
                index++
                if (index > patternList.size - 1) {
                    throw CrawlingException("배송 정보 파싱 실패")
                }
                regex = Pattern.compile(patternList[index])
                matchResult = regex.matcher(input)
            }

            return DeliveryInfo(
                defaultDeliveryDay = parseDefaultDeliveryDay(matchResult.group(1)),
                averageShippingDays = matchResult.group(2).toDouble(),
                deliveryCost = matchResult.group(3).parseInt(),
                bundledDelivery = !input.contains("불가능")
            )
        }

        private fun getPatterns(): List<String> {
            val patternCase1 =
                "배송정보(\\d+일 이내 출고|익일출고|당일출고) \\(평균출고일([\\d.]+)일\\) 택배 (\\d+,\\d+)원 / 주문시결제(묶음배송 가능|불가능)"
            val patternCase2 =
                "배송정보(\\d+일 이내 출고|익일출고|당일출고) \\(평균출고일([\\d.]+)일\\) 택배( / 조건부 무료배송)? / 주문시결제(묶음배송 가능 \\(동일 출고지 상품만 가능\\)|불가능)"
            val patternCase3 =
                "배송정보(\\d+일 이내 출고|익일출고|당일출고) \\(평균출고일([\\d.]+)일\\) 택배 (\\d+,\\d+)원 / 주문시결제묶음배송 불가능"
            val patternCase4 =
                "배송정보(\\d+일 이내 출고|익일출고|당일출고) \\(평균출고일([\\d.]+)일\\) (해외직배송) / (조건부 무료배송) / 주문시결제(해외배송상품도 구매안전서비스\\(에스크로\\)가 적용되니 안심하고 주문하세요|묶음배송 불가능)"
            val patternCase5 =
                "배송정보(\\d+일 이내 출고|익일출고|당일출고) \\(평균출고일([\\d.]+)일\\) (해외직배송) / (수량별비례 적용) / 주문시결제(해외배송상품도 구매안전서비스\\(에스크로\\)가 적용되니 안심하고 주문하세요|묶음배송 불가능)"

            return listOf(
                patternCase1,
                patternCase2,
                patternCase3,
                patternCase4,
                patternCase5,
            )
        }

        private fun parseDefaultDeliveryDay(input: String): Int {
            return when {
                input.contains("당일") -> 0
                input.contains("익일") -> 1
                else -> input.parseInt()
            }
        }

        private fun String.parseInt(): Int {
            return this.replace(Regex("[^0-9]"), "")
                .takeIf { it.isNotEmpty() }?.toInt() ?: 0
        }
    }
}
