package com.github.langara.intellijtmp01

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import it.skrape.core.htmlDocument
import kotlinx.serialization.Serializable
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import java.io.File

class TakeoutBookmarksAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        val file = File(PATH)
        val doc = htmlDocument(file)
        val uhtml = doc.document.uhtml
        println(uhtml)
        // TODO: serialize uhtml to json and publish with rsocket, then consume using jetpack compose app
    }
}

private const val PATH = "/home/marek/Downloads/takeout-20201024T122319Z-001/Takeout/Chrome/Bookmarks.html"

