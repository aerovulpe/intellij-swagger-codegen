/*
 * Copyright 2016 Jim Schubert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.jimschubert.intellij.swaggercodegen.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.jimschubert.intellij.swaggercodegen.ui.GenerateDialog

class CodegenGenerateAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return;
        val file = e.getData(PlatformDataKeys.VIRTUAL_FILE) ?: return

        GenerateDialog(project, file).show()
    }

    override fun update(e: AnActionEvent?) {
        try {
            val file = e?.getData(PlatformDataKeys.VIRTUAL_FILE)
            if (e != null && file != null) {
                e.presentation.isEnabled = file.extension != null && (file.extension.equals("yaml") || file.extension.equals("yml"))
            } else {
                super.update(e)
            }
        } catch(ex: Throwable) {
            super.update(e)
        }
    }
}
