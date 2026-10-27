package com.squareup.javapoet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Modifier;

/* JADX INFO: loaded from: classes7.dex */
public final class FieldSpec {
    public final List<AnnotationSpec> annotations;
    public final CodeBlock initializer;
    public final CodeBlock javadoc;
    public final Set<Modifier> modifiers;
    public final String name;
    public final TypeName type;

    public boolean hasModifier(Modifier modifier) {
        return this.modifiers.contains(modifier);
    }

    void emit(CodeWriter codeWriter, Set<Modifier> set) throws IOException {
        codeWriter.emitJavadoc(this.javadoc);
        codeWriter.emitAnnotations(this.annotations, false);
        codeWriter.emitModifiers(this.modifiers, set);
        codeWriter.emit("$T $L", this.type, this.name);
        if (!this.initializer.isEmpty()) {
            codeWriter.emit(" = ");
            codeWriter.emit(this.initializer);
        }
        codeWriter.emit(";\n");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && FieldSpec.class == obj.getClass()) {
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
            emit(new CodeWriter(sb), Collections.emptySet());
            return sb.toString();
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }
}
