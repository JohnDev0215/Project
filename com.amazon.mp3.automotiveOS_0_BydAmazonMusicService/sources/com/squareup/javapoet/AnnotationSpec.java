package com.squareup.javapoet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes7.dex */
public final class AnnotationSpec {
    public final Map<String, List<CodeBlock>> members;
    public final TypeName type;

    void emit(CodeWriter codeWriter, boolean z) throws IOException {
        String str = z ? "" : "\n";
        String str2 = z ? ", " : ",\n";
        if (this.members.isEmpty()) {
            codeWriter.emit("@$T", this.type);
            return;
        }
        if (this.members.size() == 1 && this.members.containsKey("value")) {
            codeWriter.emit("@$T(", this.type);
            emitAnnotationValues(codeWriter, str, str2, this.members.get("value"));
            codeWriter.emit(")");
            return;
        }
        codeWriter.emit("@$T(" + str, this.type);
        codeWriter.indent(2);
        Iterator<Map.Entry<String, List<CodeBlock>>> it = this.members.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<CodeBlock>> next = it.next();
            codeWriter.emit("$L = ", next.getKey());
            emitAnnotationValues(codeWriter, str, str2, next.getValue());
            if (it.hasNext()) {
                codeWriter.emit(str2);
            }
        }
        codeWriter.unindent(2);
        codeWriter.emit(str + ")");
    }

    private void emitAnnotationValues(CodeWriter codeWriter, String str, String str2, List<CodeBlock> list) throws IOException {
        boolean z = true;
        if (list.size() == 1) {
            codeWriter.indent(2);
            codeWriter.emit(list.get(0));
            codeWriter.unindent(2);
            return;
        }
        codeWriter.emit("{" + str);
        codeWriter.indent(2);
        for (CodeBlock codeBlock : list) {
            if (!z) {
                codeWriter.emit(str2);
            }
            codeWriter.emit(codeBlock);
            z = false;
        }
        codeWriter.unindent(2);
        codeWriter.emit(str + "}");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AnnotationSpec.class == obj.getClass()) {
            return toString().equals(obj.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
            new CodeWriter(sb).emit("$L", this);
            return sb.toString();
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }
}
