package com.github.langara.intellijtmp01

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.junit.Test

internal class ScrapeWikipediaCovidTest {

    @Test
    fun scrapeCovidTest() {
        scrapeCovidTables()
    }

    @Test
    fun serializationTest() {
        serializationExample()
    }
}

val module = SerializersModule {
    polymorphic(Project::class) {
        subclass(OwnedProject::class)
    }
}

val format = Json {
    serializersModule = module
}

interface Project {
    val name: String
}

@SerialName("owned")
@Serializable
class OwnedProject(override val name: String, val owner: String) : Project

fun serializationExample() {

    val data: Project = OwnedProject("kotlinx.coroutines", "kotlin")
    println(format.encodeToString(data))
}
