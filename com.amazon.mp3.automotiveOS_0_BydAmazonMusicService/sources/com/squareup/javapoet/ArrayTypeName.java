package com.squareup.javapoet;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.ArrayType;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes7.dex */
public final class ArrayTypeName extends TypeName {
    public final TypeName componentType;

    private ArrayTypeName(TypeName typeName) {
        this(typeName, new ArrayList());
    }

    private ArrayTypeName(TypeName typeName, List<AnnotationSpec> list) {
        super(list);
        this.componentType = (TypeName) Util.checkNotNull(typeName, "rawType == null", new Object[0]);
    }

    @Override // com.squareup.javapoet.TypeName
    CodeWriter emit(CodeWriter codeWriter) throws IOException {
        return emit(codeWriter, false);
    }

    CodeWriter emit(CodeWriter codeWriter, boolean z) throws IOException {
        emitLeafType(codeWriter);
        return emitBrackets(codeWriter, z);
    }

    private CodeWriter emitLeafType(CodeWriter codeWriter) throws IOException {
        if (TypeName.asArray(this.componentType) != null) {
            return TypeName.asArray(this.componentType).emitLeafType(codeWriter);
        }
        return this.componentType.emit(codeWriter);
    }

    private CodeWriter emitBrackets(CodeWriter codeWriter, boolean z) throws IOException {
        if (isAnnotated()) {
            codeWriter.emit(" ");
            emitAnnotations(codeWriter);
        }
        ArrayTypeName arrayTypeNameAsArray = TypeName.asArray(this.componentType);
        String str = HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        if (arrayTypeNameAsArray == null) {
            if (z) {
                str = "...";
            }
            return codeWriter.emit(str);
        }
        codeWriter.emit(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        return TypeName.asArray(this.componentType).emitBrackets(codeWriter, z);
    }

    public static ArrayTypeName of(TypeName typeName) {
        return new ArrayTypeName(typeName);
    }

    public static ArrayTypeName get(ArrayType arrayType) {
        return get(arrayType, new LinkedHashMap());
    }

    static ArrayTypeName get(ArrayType arrayType, Map<TypeParameterElement, TypeVariableName> map) {
        return new ArrayTypeName(TypeName.get(arrayType.getComponentType(), map));
    }

    static ArrayTypeName get(GenericArrayType genericArrayType, Map<Type, TypeVariableName> map) {
        return of(TypeName.get(genericArrayType.getGenericComponentType(), map));
    }
}
