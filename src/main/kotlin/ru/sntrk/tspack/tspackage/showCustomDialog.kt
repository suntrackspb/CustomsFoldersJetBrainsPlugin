package ru.sntrk.tspack.tspackage

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.Messages
import io.ktor.util.*

import java.io.IOException

class showCustomDialog : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val dialog = CustomDialogWrapper()
        if (dialog.showAndGet()) {
            // Действие при нажатии кнопки "Create"
            val currentDirectory = e.getData(CommonDataKeys.VIRTUAL_FILE)
            if (currentDirectory != null && currentDirectory.isDirectory) {
                val ext = e.presentation.description.toString().toLowerCasePreservingASCIIRules()
                val directoryName = dialog.getDirectoryName()
                if (directoryName.isNotBlank()) {
                    ApplicationManager.getApplication().runWriteAction {
                        try {
                            // Проверяем, существует ли директория
                            val newDirectory = currentDirectory.findChild(directoryName)
                            if (newDirectory != null) {
                                // Если директория существует, показываем ошибку
                                Messages.showErrorDialog("Directory '$directoryName' already exists.", "Error")
                            } else {
                                // Создаем новую директорию
                                val createdDirectory = currentDirectory.createChildDirectory(this, directoryName)
                                val indexFile = createdDirectory.createChildData(this, "index.$ext")
                                indexFile.setBinaryContent("\n".toByteArray())
                            }
                        } catch (ioException: IOException) {
                            Messages.showErrorDialog("Failed to create directory or files.", "Error")
                        }
                    }
                } else {
                    Messages.showErrorDialog("Please enter directory name.", "Error")
                }
            } else {
                Messages.showErrorDialog("Please select a directory.", "Error")
            }
        }
    }
}
