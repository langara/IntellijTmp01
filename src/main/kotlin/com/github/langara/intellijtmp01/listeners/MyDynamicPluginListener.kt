package com.github.langara.intellijtmp01.listeners

import com.github.langara.intellijtmp01.notify
import com.intellij.ide.plugins.DynamicPluginListener
import com.intellij.ide.plugins.IdeaPluginDescriptor

// TODO: use to stop long operations and clean resources
internal class MyDynamicPluginListener : DynamicPluginListener {
    override fun beforePluginLoaded(pluginDescriptor: IdeaPluginDescriptor) {
        notify("Jo, beforePluginLoaded!")
    }

    override fun beforePluginUnload(pluginDescriptor: IdeaPluginDescriptor, isUpdate: Boolean) {
        notify("Jo, beforePluginUnload! isUpdate = $isUpdate")
    }

    override fun checkUnloadPlugin(pluginDescriptor: IdeaPluginDescriptor) {
        notify("Jo, checkUnloadPlugin!")
    }

    override fun pluginLoaded(pluginDescriptor: IdeaPluginDescriptor) {
        notify("Jo, pluginLoaded!")
    }

    override fun pluginUnloaded(pluginDescriptor: IdeaPluginDescriptor, isUpdate: Boolean) {
        notify("Jo, pluginUnloaded! isUpdate = $isUpdate")
    }
}
