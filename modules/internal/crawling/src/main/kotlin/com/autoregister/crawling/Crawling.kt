package com.autoregister.crawling

import com.autoregister.crawling.enumeration.CrawlingType

interface Crawling<R> {
    fun crawl(query: String, returns: Class<R>, type: CrawlingType): R?
}