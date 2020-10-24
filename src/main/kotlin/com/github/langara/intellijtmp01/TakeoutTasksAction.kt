package com.github.langara.intellijtmp01

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

class TakeoutTasksAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        Messages.showMessageDialog(project, "TODO: Takeout tasks", "Takeout tasks", Messages.getInformationIcon())
        val file = File(PATH)
        val text = file.readText()
        val node = Json.decodeFromString<TaskNode>(text)
        println(node)
    }
}

const val PATH = "/home/marek/Downloads/takeout-20201024T122319Z-001/Takeout/Tasks/Tasks.json"

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
