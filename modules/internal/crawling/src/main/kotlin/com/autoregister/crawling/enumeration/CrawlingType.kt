package com.autoregister.crawling.enumeration

enum class CrawlingType(
    val typeName: String,
    val defaultPath: String,
) {
    DOMEME(
        typeName = "도매매",
        defaultPath = "https://domemedb.domeggook.com/index/item/supplyList.php?sf=subject&enc=utf8&fromOversea=0&mode=search&sw="
    );
}