package ru.sntrk.tspack.tspackage

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.Messages
import io.ktor.util.*
import java.io.IOException

class CreateCustomFolderIndex : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        // Получаем текущий выбранный файл/директорию
        val currentDirectory = e.getData(CommonDataKeys.VIRTUAL_FILE)
        val ext = e.presentation.description.toString().toLowerCasePreservingASCIIRules()

        if (currentDirectory != null && currentDirectory.isDirectory) {
            // Показываем диалог для ввода названия директории
            val directoryName = Messages.showInputDialog("Enter directory name:", "Create Directory", Messages.getQuestionIcon())

            if (!directoryName.isNullOrBlank()) {
                ApplicationManager.getApplication().runWriteAction {
                    try {
                        // Создаем новую директорию
                        val newDirectory = currentDirectory.createChildDirectory(this, directoryName)
                        val indexFile = newDirectory.createChildData(this, "index.$ext")
                        indexFile.setBinaryContent("export { $directoryName } from './$directoryName';\n".toByteArray())

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

