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

/**
 * Micro HTML tree. Either text should be null or tag should be null.
 * I don't use sealed classes intentionally: It's "micro" (and it's just a hacky experiment)
 */
@Serializable
data class UHtml(
    val text: String? = null,
    val tag: String? = null,
    val attrs: Map<String, String> = emptyMap(),
    val children: List<UHtml> = emptyList(),
)

private val Element.uhtml: UHtml
    get() = UHtml(
        tag = normalName(),
        attrs = attributes().associate { it.key to it.value },
        children = childNodes().mapNotNull { it.uhtml }
    )

private val TextNode.uhtml get() = UHtml(text = text())

private val Node.uhtml get() = when (this) {
    is TextNode -> uhtml
    is Element -> uhtml
    else -> null
}
