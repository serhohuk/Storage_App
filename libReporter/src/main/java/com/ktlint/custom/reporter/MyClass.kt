package com.ktlint.custom.reporter

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.api.EditorConfigProperties
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class CustomRule : Rule("no-cast") {

    override fun afterLastNode() {
        super.afterLastNode()
    }

    override fun afterVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        super.afterVisitChildNodes(node, autoCorrect, emit)
        
    }

    override fun beforeFirstNode(editorConfigProperties: EditorConfigProperties) {
        super.beforeFirstNode(editorConfigProperties)
    }

    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        super.beforeVisitChildNodes(node, autoCorrect, emit)
    }
}