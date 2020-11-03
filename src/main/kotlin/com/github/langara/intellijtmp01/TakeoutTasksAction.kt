package com.github.langara.intellijtmp01

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import java.io.File

class TakeoutTasksAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) = convertTasksToMD()
}

fun convertTasksToMD() {
    val text = File(PATH).readText()
    val node = Json.decodeFromString<TaskNode>(text)
    File(OUTPATH).writeText(uMdEncodeToString(node))
}

private const val PATH = "/home/marek/Downloads/takeout-20201024T122319Z-001/Takeout/Tasks/Tasks.json"
private const val OUTPATH = "/home/marek/gtd/google-tasks-backup.md"

@OptIn(ExperimentalSerializationApi::class)
class UMdEncoder(private val output: StringBuilder) : AbstractEncoder() {

    private var indent = 0

    override val serializersModule: SerializersModule = EmptySerializersModule

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        encodeValue(descriptor.serialName)
        indent += WIDTH
        return this
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        indent -= WIDTH
    }

    override fun encodeValue(value: Any) {
        output.run {
            repeat(indent) { append(" ") }
            append("* $value\n".padStart(indent))
        }
    }

    override fun encodeNull() = Unit
}

private const val WIDTH = 4

inline fun <reified T> uMdEncodeToString(data: T) = buildString {
    serializer<T>().serialize(UMdEncoder(this), data)
}
