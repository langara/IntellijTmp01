package com.github.langara.intellijtmp01

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import java.io.File

class TakeoutTasksAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        Messages.showMessageDialog(project, "TODO: Takeout tasks", "Takeout tasks", Messages.getInformationIcon())
        val file = File(PATH)
        val text = file.readText()
        val node = Json.decodeFromString<TaskNode>(text)
        println(node)
        val text2 = Json.encodeToString(node)
        println(TaskNode.serializer().descriptor)
    }
}

private const val PATH = "/home/marek/Downloads/takeout-20201024T122319Z-001/Takeout/Tasks/Tasks.json"

class TaskMarkdownEncoder : AbstractEncoder() {

    override val serializersModule: SerializersModule = EmptySerializersModule

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        return super.beginStructure(descriptor)
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        super.endStructure(descriptor)
    }

    override fun encodeValue(value: Any) {
        super.encodeValue(value)
    }
}

class ListEncoder : AbstractEncoder() {
    val list = mutableListOf<Any>()

    override val serializersModule: SerializersModule = EmptySerializersModule

    override fun encodeValue(value: Any) {
        list.add(value)
    }
}
