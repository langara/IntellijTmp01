package com.github.langara.intellijtmp01.services

import com.github.langara.intellijtmp01.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
