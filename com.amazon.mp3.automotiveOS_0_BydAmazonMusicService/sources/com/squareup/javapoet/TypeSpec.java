package com.squareup.javapoet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

/* JADX INFO: loaded from: classes7.dex */
public final class TypeSpec {
    public final CodeBlock initializerBlock;
    public final CodeBlock javadoc;
    public final Kind kind;
    public final String name;
    public final CodeBlock staticBlock;
    public final CodeBlock anonymousTypeArguments = null;
    public final List<AnnotationSpec> annotations = Collections.emptyList();
    public final Set<Modifier> modifiers = Collections.emptySet();
    public final List<TypeVariableName> typeVariables = Collections.emptyList();
    public final TypeName superclass = null;
    public final List<TypeName> superinterfaces = Collections.emptyList();
    public final Map<String, TypeSpec> enumConstants = Collections.emptyMap();
    public final List<FieldSpec> fieldSpecs = Collections.emptyList();
    public final List<MethodSpec> methodSpecs = Collections.emptyList();
    public final List<TypeSpec> typeSpecs = Collections.emptyList();
    public final List<Element> originatingElements = Collections.emptyList();
    final Set<String> nestedTypesSimpleNames = Collections.emptySet();
    public final Set<String> alwaysQualifiedNames = Collections.emptySet();

    private TypeSpec(TypeSpec typeSpec) {
        this.kind = typeSpec.kind;
        this.name = typeSpec.name;
        this.javadoc = typeSpec.javadoc;
        this.staticBlock = typeSpec.staticBlock;
        this.initializerBlock = typeSpec.initializerBlock;
    }

    void emit(CodeWriter codeWriter, String str, Set<Modifier> set) throws IOException {
        List<TypeName> listSingletonList;
        List<TypeName> listEmptyList;
        int i = codeWriter.statementLine;
        codeWriter.statementLine = -1;
        boolean z = true;
        try {
            if (str != null) {
                codeWriter.emitJavadoc(this.javadoc);
                codeWriter.emitAnnotations(this.annotations, false);
                codeWriter.emit("$L", str);
                if (!this.anonymousTypeArguments.formatParts.isEmpty()) {
                    codeWriter.emit("(");
                    codeWriter.emit(this.anonymousTypeArguments);
                    codeWriter.emit(")");
                }
                if (this.fieldSpecs.isEmpty() && this.methodSpecs.isEmpty() && this.typeSpecs.isEmpty()) {
                    return;
                } else {
                    codeWriter.emit(" {\n");
                }
            } else if (this.anonymousTypeArguments != null) {
                codeWriter.emit("new $T(", !this.superinterfaces.isEmpty() ? this.superinterfaces.get(0) : this.superclass);
                codeWriter.emit(this.anonymousTypeArguments);
                codeWriter.emit(") {\n");
            } else {
                codeWriter.pushType(new TypeSpec(this));
                codeWriter.emitJavadoc(this.javadoc);
                codeWriter.emitAnnotations(this.annotations, false);
                codeWriter.emitModifiers(this.modifiers, Util.union(set, this.kind.asMemberModifiers));
                Kind kind = this.kind;
                if (kind == Kind.ANNOTATION) {
                    codeWriter.emit("$L $L", "@interface", this.name);
                } else {
                    codeWriter.emit("$L $L", kind.name().toLowerCase(Locale.US), this.name);
                }
                codeWriter.emitTypeVariables(this.typeVariables);
                if (this.kind == Kind.INTERFACE) {
                    listSingletonList = this.superinterfaces;
                    listEmptyList = Collections.emptyList();
                } else {
                    if (this.superclass.equals(ClassName.OBJECT)) {
                        listSingletonList = Collections.emptyList();
                    } else {
                        listSingletonList = Collections.singletonList(this.superclass);
                    }
                    listEmptyList = this.superinterfaces;
                }
                if (!listSingletonList.isEmpty()) {
                    codeWriter.emit(" extends");
                    boolean z2 = true;
                    for (TypeName typeName : listSingletonList) {
                        if (!z2) {
                            codeWriter.emit(",");
                        }
                        codeWriter.emit(" $T", typeName);
                        z2 = false;
                    }
                }
                if (!listEmptyList.isEmpty()) {
                    codeWriter.emit(" implements");
                    boolean z3 = true;
                    for (TypeName typeName2 : listEmptyList) {
                        if (!z3) {
                            codeWriter.emit(",");
                        }
                        codeWriter.emit(" $T", typeName2);
                        z3 = false;
                    }
                }
                codeWriter.popType();
                codeWriter.emit(" {\n");
            }
            codeWriter.pushType(this);
            codeWriter.indent();
            Iterator<Map.Entry<String, TypeSpec>> it = this.enumConstants.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, TypeSpec> next = it.next();
                if (!z) {
                    codeWriter.emit("\n");
                }
                next.getValue().emit(codeWriter, next.getKey(), Collections.emptySet());
                if (it.hasNext()) {
                    codeWriter.emit(",\n");
                } else if (!this.fieldSpecs.isEmpty() || !this.methodSpecs.isEmpty() || !this.typeSpecs.isEmpty()) {
                    codeWriter.emit(";\n");
                } else {
                    codeWriter.emit("\n");
                }
                z = false;
            }
            for (FieldSpec fieldSpec : this.fieldSpecs) {
                if (fieldSpec.hasModifier(Modifier.STATIC)) {
                    if (!z) {
                        codeWriter.emit("\n");
                    }
                    fieldSpec.emit(codeWriter, this.kind.implicitFieldModifiers);
                    z = false;
                }
            }
            if (!this.staticBlock.isEmpty()) {
                if (!z) {
                    codeWriter.emit("\n");
                }
                codeWriter.emit(this.staticBlock);
                z = false;
            }
            for (FieldSpec fieldSpec2 : this.fieldSpecs) {
                if (!fieldSpec2.hasModifier(Modifier.STATIC)) {
                    if (!z) {
                        codeWriter.emit("\n");
                    }
                    fieldSpec2.emit(codeWriter, this.kind.implicitFieldModifiers);
                    z = false;
                }
            }
            if (!this.initializerBlock.isEmpty()) {
                if (!z) {
                    codeWriter.emit("\n");
                }
                codeWriter.emit(this.initializerBlock);
                z = false;
            }
            for (MethodSpec methodSpec : this.methodSpecs) {
                if (methodSpec.isConstructor()) {
                    if (!z) {
                        codeWriter.emit("\n");
                    }
                    methodSpec.emit(codeWriter, this.name, this.kind.implicitMethodModifiers);
                    z = false;
                }
            }
            for (MethodSpec methodSpec2 : this.methodSpecs) {
                if (!methodSpec2.isConstructor()) {
                    if (!z) {
                        codeWriter.emit("\n");
                    }
                    methodSpec2.emit(codeWriter, this.name, this.kind.implicitMethodModifiers);
                    z = false;
                }
            }
            for (TypeSpec typeSpec : this.typeSpecs) {
                if (!z) {
                    codeWriter.emit("\n");
                }
                typeSpec.emit(codeWriter, null, this.kind.implicitTypeModifiers);
                z = false;
            }
            codeWriter.unindent();
            codeWriter.popType();
            codeWriter.popTypeVariables(this.typeVariables);
            codeWriter.emit("}");
            if (str == null && this.anonymousTypeArguments == null) {
                codeWriter.emit("\n");
            }
        } finally {
            codeWriter.statementLine = i;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && TypeSpec.class == obj.getClass()) {
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
            emit(new CodeWriter(sb), null, Collections.emptySet());
            return sb.toString();
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }

    public enum Kind {
        CLASS(Collections.emptySet(), Collections.emptySet(), Collections.emptySet(), Collections.emptySet()),
        INTERFACE(Util.immutableSet(Arrays.asList(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)), Util.immutableSet(Arrays.asList(Modifier.PUBLIC, Modifier.ABSTRACT)), Util.immutableSet(Arrays.asList(Modifier.PUBLIC, Modifier.STATIC)), Util.immutableSet(Collections.singletonList(Modifier.STATIC))),
        ENUM(Collections.emptySet(), Collections.emptySet(), Collections.emptySet(), Collections.singleton(Modifier.STATIC)),
        ANNOTATION(Util.immutableSet(Arrays.asList(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)), Util.immutableSet(Arrays.asList(Modifier.PUBLIC, Modifier.ABSTRACT)), Util.immutableSet(Arrays.asList(Modifier.PUBLIC, Modifier.STATIC)), Util.immutableSet(Collections.singletonList(Modifier.STATIC)));

        private final Set<Modifier> asMemberModifiers;
        private final Set<Modifier> implicitFieldModifiers;
        private final Set<Modifier> implicitMethodModifiers;
        private final Set<Modifier> implicitTypeModifiers;

        Kind(Set set, Set set2, Set set3, Set set4) {
            this.implicitFieldModifiers = set;
            this.implicitMethodModifiers = set2;
            this.implicitTypeModifiers = set3;
            this.asMemberModifiers = set4;
        }
    }
}
