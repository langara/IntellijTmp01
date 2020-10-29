package com.github.langara.intellijtmp01

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode

@Serializable
data class TaskNode(
    val kind: String,
    val id: String? = null,
    val parent: String? = null,
    val title: String? = null,
    @SerialName("task_type") val type: String? = null,
    val updated: String? = null, // TODO: datetime? what format? use kotlinx-datetime?
    val completed: String? = null, // TODO: datetime? what format? use kotlinx-datetime?
    val due: String? = null, // TODO: datetime? what format? use kotlinx-datetime?
    val selfLink: String? = null,
    val status: String? = null,
    val items: List<TaskNode> = emptyList(),
    val links: List<TaskLink> = emptyList(),
    val notes: String? = null,
)

@Serializable
data class TaskLink(
    val link: String,
    val type: String? = null,
    val desc: String? = null,
)

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

val Element.uhtml: UHtml
    get() = UHtml(
        tag = normalName()!!,
        attrs = attributes().associate { it.key!! to it.value!! },
        children = childNodes().mapNotNull { it.uhtml }
    )

val TextNode.uhtml get() = UHtml(text = text().orEmpty())

val Node.uhtml get() = when (this) {
    is TextNode -> uhtml
    is Element -> uhtml
    else -> null
}
