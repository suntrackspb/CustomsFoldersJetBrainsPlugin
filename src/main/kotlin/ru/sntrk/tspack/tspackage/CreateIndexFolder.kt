package ru.sntrk.tspack.tspackage

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.Messages
import io.ktor.util.*
import java.io.IOException

class CreateIndexFolder : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        // Получаем текущий выбранный файл/директорию
        val currentDirectory = e.getData(CommonDataKeys.VIRTUAL_FILE)
        val myMenuGroup = e.presentation.description
        val matchResult = myMenuGroup.split('*')

        val directoryName = matchResult[0].toLowerCasePreservingASCIIRules()
        val ext = matchResult[1].toLowerCasePreservingASCIIRules()

        if (currentDirectory != null && currentDirectory.isDirectory) {
            if (directoryName.isNotBlank()) {
                    ApplicationManager.getApplication().runWriteAction {
                        try {
                            val newDirectory = currentDirectory.createChildDirectory(this, directoryName)
                            val indexFile = newDirectory.createChildData(this, "index.$ext")
                            indexFile.setBinaryContent("export {  } from './';\n".toByteArray())

                        } catch (ioException: IOException) {
                            Messages.showErrorDialog("Failed to create directory or files.", "Error")
                        }
                    }
                }
        } else {
            Messages.showErrorDialog("Please select a directory.", "Error")
        }
    }
}