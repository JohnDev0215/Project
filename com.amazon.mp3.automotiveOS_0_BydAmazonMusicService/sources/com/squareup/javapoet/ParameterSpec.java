package com.squareup.javapoet;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Modifier;

/* JADX INFO: loaded from: classes7.dex */
public final class ParameterSpec {
    public final List<AnnotationSpec> annotations;
    public final CodeBlock javadoc;
    public final Set<Modifier> modifiers;
    public final String name;
    public final TypeName type;

    void emit(CodeWriter codeWriter, boolean z) throws IOException {
        codeWriter.emitAnnotations(this.annotations, true);
        codeWriter.emitModifiers(this.modifiers);
        if (z) {
            TypeName.asArray(this.type).emit(codeWriter, true);
        } else {
            this.type.emit(codeWriter);
        }
        codeWriter.emit(" $L", this.name);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ParameterSpec.class == obj.getClass()) {
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
            emit(new CodeWriter(sb), false);
            return sb.toString();
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }
}
