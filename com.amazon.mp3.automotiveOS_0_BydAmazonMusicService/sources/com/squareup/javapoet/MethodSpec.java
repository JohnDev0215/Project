package com.squareup.javapoet;

import com.squareup.javapoet.CodeBlock;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Modifier;

/* JADX INFO: loaded from: classes7.dex */
public final class MethodSpec {
    public final List<AnnotationSpec> annotations;
    public final CodeBlock code;
    public final CodeBlock defaultValue;
    public final List<TypeName> exceptions;
    public final CodeBlock javadoc;
    public final Set<Modifier> modifiers;
    public final String name;
    public final List<ParameterSpec> parameters;
    public final TypeName returnType;
    public final List<TypeVariableName> typeVariables;
    public final boolean varargs;

    void emit(CodeWriter codeWriter, String str, Set<Modifier> set) throws IOException {
        codeWriter.emitJavadoc(javadocWithParameters());
        codeWriter.emitAnnotations(this.annotations, false);
        codeWriter.emitModifiers(this.modifiers, set);
        if (!this.typeVariables.isEmpty()) {
            codeWriter.emitTypeVariables(this.typeVariables);
            codeWriter.emit(" ");
        }
        if (isConstructor()) {
            codeWriter.emit("$L($Z", str);
        } else {
            codeWriter.emit("$T $L($Z", this.returnType, this.name);
        }
        Iterator<ParameterSpec> it = this.parameters.iterator();
        boolean z = true;
        while (it.hasNext()) {
            ParameterSpec next = it.next();
            if (!z) {
                codeWriter.emit(",").emitWrappingSpace();
            }
            next.emit(codeWriter, !it.hasNext() && this.varargs);
            z = false;
        }
        codeWriter.emit(")");
        CodeBlock codeBlock = this.defaultValue;
        if (codeBlock != null && !codeBlock.isEmpty()) {
            codeWriter.emit(" default ");
            codeWriter.emit(this.defaultValue);
        }
        if (!this.exceptions.isEmpty()) {
            codeWriter.emitWrappingSpace().emit("throws");
            boolean z2 = true;
            for (TypeName typeName : this.exceptions) {
                if (!z2) {
                    codeWriter.emit(",");
                }
                codeWriter.emitWrappingSpace().emit("$T", typeName);
                z2 = false;
            }
        }
        if (hasModifier(Modifier.ABSTRACT)) {
            codeWriter.emit(";\n");
        } else if (hasModifier(Modifier.NATIVE)) {
            codeWriter.emit(this.code);
            codeWriter.emit(";\n");
        } else {
            codeWriter.emit(" {\n");
            codeWriter.indent();
            codeWriter.emit(this.code, true);
            codeWriter.unindent();
            codeWriter.emit("}\n");
        }
        codeWriter.popTypeVariables(this.typeVariables);
    }

    private CodeBlock javadocWithParameters() {
        CodeBlock.Builder builder = this.javadoc.toBuilder();
        boolean z = true;
        for (ParameterSpec parameterSpec : this.parameters) {
            if (!parameterSpec.javadoc.isEmpty()) {
                if (z && !this.javadoc.isEmpty()) {
                    builder.add("\n", new Object[0]);
                }
                builder.add("@param $L $L", parameterSpec.name, parameterSpec.javadoc);
                z = false;
            }
        }
        return builder.build();
    }

    public boolean hasModifier(Modifier modifier) {
        return this.modifiers.contains(modifier);
    }

    public boolean isConstructor() {
        return this.name.equals("<init>");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && MethodSpec.class == obj.getClass()) {
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
            emit(new CodeWriter(sb), "Constructor", Collections.emptySet());
            return sb.toString();
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }
}
