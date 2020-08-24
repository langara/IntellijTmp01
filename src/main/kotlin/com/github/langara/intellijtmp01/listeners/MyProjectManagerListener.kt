package com.github.langara.intellijtmp01.listeners

import com.github.langara.intellijtmp01.notify
import com.github.langara.intellijtmp01.services.MyProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener

internal class MyProjectManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
        project.notify("Jo, projectOpened!")
        project.service<MyProjectService>()
    }
}
