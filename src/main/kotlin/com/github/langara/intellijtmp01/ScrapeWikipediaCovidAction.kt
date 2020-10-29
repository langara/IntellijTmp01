package com.github.langara.intellijtmp01

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import it.skrape.core.fetcher.HttpFetcher
import it.skrape.core.htmlDocument
import it.skrape.extract
import it.skrape.selects.eachText
import it.skrape.selects.html5.table
import it.skrape.selects.html5.th
import it.skrape.selects.html5.tr
import it.skrape.skrape

class ScrapeWikipediaCovidAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        scrapeCovidTables()
    }
}

internal fun scrapeCovid() {
    val data = skrape(HttpFetcher) {
        request {
            url = "https://en.wikipedia.org/wiki/COVID-19_pandemic_by_country_and_territory"
        }
        extract {
            htmlDocument {
                table {
                    withId = "thetable"
                    findFirst {
                        tr {
                            withClass = "covid-sticky"
                            th { findAll { eachText } }
                        }
                    }
                }
            }
        }
    }
    println(data)
}

internal fun scrapeCovidTables() {
    val data = skrape(HttpFetcher) {
        request {
            url = "https://en.wikipedia.org/wiki/COVID-19_pandemic_by_country_and_territory"
        }
        extract {
            htmlDocument {
                table {
                    findAll {
                        map { table ->
                            table.tr {
                                findAll {
                                    map { row ->
                                        row.selection("th, td") {
                                            findAll {
                                                map { col ->
                                                    col.text
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    println(data)
}
