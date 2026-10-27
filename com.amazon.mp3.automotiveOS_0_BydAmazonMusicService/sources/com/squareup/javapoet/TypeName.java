package com.squareup.javapoet;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor8;

/* JADX INFO: loaded from: classes7.dex */
public class TypeName {
    public final List<AnnotationSpec> annotations;
    private String cachedString;
    private final String keyword;
    public static final TypeName VOID = new TypeName("void");
    public static final TypeName BOOLEAN = new TypeName("boolean");
    public static final TypeName BYTE = new TypeName("byte");
    public static final TypeName SHORT = new TypeName("short");
    public static final TypeName INT = new TypeName("int");
    public static final TypeName LONG = new TypeName("long");
    public static final TypeName CHAR = new TypeName("char");
    public static final TypeName FLOAT = new TypeName("float");
    public static final TypeName DOUBLE = new TypeName("double");
    public static final ClassName OBJECT = ClassName.get("java.lang", "Object", new String[0]);
    private static final ClassName BOXED_VOID = ClassName.get("java.lang", "Void", new String[0]);
    private static final ClassName BOXED_BOOLEAN = ClassName.get("java.lang", "Boolean", new String[0]);
    private static final ClassName BOXED_BYTE = ClassName.get("java.lang", "Byte", new String[0]);
    private static final ClassName BOXED_SHORT = ClassName.get("java.lang", "Short", new String[0]);
    private static final ClassName BOXED_INT = ClassName.get("java.lang", "Integer", new String[0]);
    private static final ClassName BOXED_LONG = ClassName.get("java.lang", "Long", new String[0]);
    private static final ClassName BOXED_CHAR = ClassName.get("java.lang", "Character", new String[0]);
    private static final ClassName BOXED_FLOAT = ClassName.get("java.lang", "Float", new String[0]);
    private static final ClassName BOXED_DOUBLE = ClassName.get("java.lang", "Double", new String[0]);

    private TypeName(String str) {
        this(str, new ArrayList());
    }

    private TypeName(String str, List<AnnotationSpec> list) {
        this.keyword = str;
        this.annotations = Util.immutableList(list);
    }

    TypeName(List<AnnotationSpec> list) {
        this(null, list);
    }

    protected final List<AnnotationSpec> concatAnnotations(List<AnnotationSpec> list) {
        ArrayList arrayList = new ArrayList(this.annotations);
        arrayList.addAll(list);
        return arrayList;
    }

    public boolean isAnnotated() {
        return !this.annotations.isEmpty();
    }

    public boolean isPrimitive() {
        return (this.keyword == null || this == VOID) ? false : true;
    }

    public boolean isBoxedPrimitive() {
        return equals(BOXED_BOOLEAN) || equals(BOXED_BYTE) || equals(BOXED_SHORT) || equals(BOXED_INT) || equals(BOXED_LONG) || equals(BOXED_CHAR) || equals(BOXED_FLOAT) || equals(BOXED_DOUBLE);
    }

    public TypeName box() {
        if (this.keyword == null) {
            return this;
        }
        if (this == VOID) {
            return BOXED_VOID;
        }
        if (this == BOOLEAN) {
            return BOXED_BOOLEAN;
        }
        if (this == BYTE) {
            return BOXED_BYTE;
        }
        if (this == SHORT) {
            return BOXED_SHORT;
        }
        if (this == INT) {
            return BOXED_INT;
        }
        if (this == LONG) {
            return BOXED_LONG;
        }
        if (this == CHAR) {
            return BOXED_CHAR;
        }
        if (this == FLOAT) {
            return BOXED_FLOAT;
        }
        if (this == DOUBLE) {
            return BOXED_DOUBLE;
        }
        throw new AssertionError(this.keyword);
    }

    public TypeName unbox() {
        if (this.keyword != null) {
            return this;
        }
        if (equals(BOXED_VOID)) {
            return VOID;
        }
        if (equals(BOXED_BOOLEAN)) {
            return BOOLEAN;
        }
        if (equals(BOXED_BYTE)) {
            return BYTE;
        }
        if (equals(BOXED_SHORT)) {
            return SHORT;
        }
        if (equals(BOXED_INT)) {
            return INT;
        }
        if (equals(BOXED_LONG)) {
            return LONG;
        }
        if (equals(BOXED_CHAR)) {
            return CHAR;
        }
        if (equals(BOXED_FLOAT)) {
            return FLOAT;
        }
        if (equals(BOXED_DOUBLE)) {
            return DOUBLE;
        }
        throw new UnsupportedOperationException("cannot unbox " + this);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return toString().equals(obj.toString());
        }
        return false;
    }

    public final int hashCode() {
        return toString().hashCode();
    }

    public final String toString() {
        String str = this.cachedString;
        if (str != null) {
            return str;
        }
        try {
            StringBuilder sb = new StringBuilder();
            emit(new CodeWriter(sb));
            String string = sb.toString();
            this.cachedString = string;
            return string;
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }

    CodeWriter emit(CodeWriter codeWriter) throws IOException {
        if (this.keyword == null) {
            throw new AssertionError();
        }
        if (isAnnotated()) {
            codeWriter.emit("");
            emitAnnotations(codeWriter);
        }
        return codeWriter.emitAndIndent(this.keyword);
    }

    CodeWriter emitAnnotations(CodeWriter codeWriter) throws IOException {
        Iterator<AnnotationSpec> it = this.annotations.iterator();
        while (it.hasNext()) {
            it.next().emit(codeWriter, true);
            codeWriter.emit(" ");
        }
        return codeWriter;
    }

    public static TypeName get(TypeMirror typeMirror) {
        return get(typeMirror, new LinkedHashMap());
    }

    static TypeName get(TypeMirror typeMirror, final Map<TypeParameterElement, TypeVariableName> map) {
        return (TypeName) typeMirror.accept(new SimpleTypeVisitor8<TypeName, Void>() { // from class: com.squareup.javapoet.TypeName.1
        }, (Object) null);
    }

    public static TypeName get(Type type) {
        return get(type, new LinkedHashMap());
    }

    static TypeName get(Type type, Map<Type, TypeVariableName> map) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return type == Void.TYPE ? VOID : type == Boolean.TYPE ? BOOLEAN : type == Byte.TYPE ? BYTE : type == Short.TYPE ? SHORT : type == Integer.TYPE ? INT : type == Long.TYPE ? LONG : type == Character.TYPE ? CHAR : type == Float.TYPE ? FLOAT : type == Double.TYPE ? DOUBLE : cls.isArray() ? ArrayTypeName.of(get(cls.getComponentType(), map)) : ClassName.get((Class<?>) cls);
        }
        if (type instanceof ParameterizedType) {
            return ParameterizedTypeName.get((ParameterizedType) type, map);
        }
        if (type instanceof WildcardType) {
            return WildcardTypeName.get((WildcardType) type, map);
        }
        if (type instanceof TypeVariable) {
            return TypeVariableName.get((TypeVariable<?>) type, map);
        }
        if (type instanceof GenericArrayType) {
            return ArrayTypeName.get((GenericArrayType) type, map);
        }
        throw new IllegalArgumentException("unexpected type: " + type);
    }

    static List<TypeName> list(Type[] typeArr, Map<Type, TypeVariableName> map) {
        ArrayList arrayList = new ArrayList(typeArr.length);
        for (Type type : typeArr) {
            arrayList.add(get(type, map));
        }
        return arrayList;
    }

    static ArrayTypeName asArray(TypeName typeName) {
        if (typeName instanceof ArrayTypeName) {
            return (ArrayTypeName) typeName;
        }
        return null;
    }
}
