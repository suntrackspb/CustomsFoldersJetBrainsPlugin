package ru.sntrk.tspack.tspackage

import com.intellij.openapi.ui.DialogWrapper
import javax.swing.*
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File

class CustomDialogWrapper : DialogWrapper(true) {

    private val mainPanel: JPanel = JPanel(BorderLayout())
    private val textField: JTextField = JTextField(20)

    init {
        init()
        title = "Create Directory"
        mainPanel.add(JLabel("Enter directory name:"), BorderLayout.NORTH)
        mainPanel.add(textField, BorderLayout.CENTER)

        val buttonsPanel = JPanel()
        val buttons = arrayOf("components", "hooks", "utils")

        for (buttonText in buttons) {
            val button = JButton(buttonText)
            button.addActionListener(ActionListener {
                textField.text = buttonText  // Устанавливаем текст кнопки в текстовое поле
            })
            buttonsPanel.add(button)
        }

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH)

        setOKButtonText("Create")  // Настройка текста кнопки OK
        setCancelButtonText("Cancel")  // Настройка текста кнопки Cancel
    }

    override fun createCenterPanel(): JComponent? {
        return mainPanel
    }

    fun getDirectoryName(): String {
        return textField.text
    }
}