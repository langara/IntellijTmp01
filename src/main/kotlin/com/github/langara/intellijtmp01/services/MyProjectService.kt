package com.github.langara.intellijtmp01.services

import com.intellij.openapi.project.Project
import com.github.langara.intellijtmp01.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
