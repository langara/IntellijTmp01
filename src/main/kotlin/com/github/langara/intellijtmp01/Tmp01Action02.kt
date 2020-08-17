package com.github.langara.intellijtmp01

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class Tmp01Action02 : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        Messages.showMessageDialog(project, "Ho Ho Ho!!", "Say ho", Messages.getInformationIcon())
    }
}
