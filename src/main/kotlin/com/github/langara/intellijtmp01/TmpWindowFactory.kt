package com.github.langara.intellijtmp01

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType.WARNING
import com.intellij.notification.Notifications
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.CollectionListModel
import com.intellij.ui.ToolbarDecorator
import com.intellij.ui.components.JBList
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.GrowPolicy
import com.intellij.ui.layout.panel

val logsModel = CollectionListModel<String>().apply {
    add("First element")
    add("Second element")
    add("Third element")
}

class TmpWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {

        val logsPanel = panel {
            val jbList = JBList(logsModel)
            val decorator = ToolbarDecorator.createDecorator(jbList)
            val innerPanel = decorator.createPanel()
            row {
                innerPanel(CCFlags.grow, growPolicy = GrowPolicy.MEDIUM_TEXT)
            }
        }

        val somePanel = panel {
            @Suppress("MagicNumber")
            for (i in 1..10) row { label("rrr $i") }
            row {
                checkBox("first check box in a row")
                checkBox("second check box in a row")
            }
        }

        val otherPanel = panel {
            @Suppress("MagicNumber")
            for (i in 1..5) row { label("oooooo $i") }
            row {
                button("first button in a row") { project.notify("First button clicked") }
                button("second button in a row") { project.notify("Second button clicked") }
            }
        }

        val tabContent = toolWindow.contentManager.factory::createContent

        toolWindow.contentManager.run {
            addContent(tabContent(logsPanel, "Logs panel", true))
            addContent(tabContent(somePanel, "Some panel", true))
            addContent(tabContent(otherPanel, "Other panel", true))
        }
    }
}

fun Project?.notify(content: String) = Notifications.Bus.notify(notification(content), this)
fun notify(content: String) = null.notify(content)

private fun notification(content: String) =
    Notification("some group disp id", "some title", content, WARNING)
