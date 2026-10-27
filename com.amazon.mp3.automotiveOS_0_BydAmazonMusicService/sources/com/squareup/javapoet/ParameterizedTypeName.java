package com.squareup.javapoet;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes7.dex */
public final class ParameterizedTypeName extends TypeName {
    private final ParameterizedTypeName enclosingType;
    public final ClassName rawType;
    public final List<TypeName> typeArguments;

    ParameterizedTypeName(ParameterizedTypeName parameterizedTypeName, ClassName className, List<TypeName> list) {
        this(parameterizedTypeName, className, list, new ArrayList());
    }

    private ParameterizedTypeName(ParameterizedTypeName parameterizedTypeName, ClassName className, List<TypeName> list, List<AnnotationSpec> list2) {
        super(list2);
        this.rawType = ((ClassName) Util.checkNotNull(className, "rawType == null", new Object[0])).annotated(list2);
        this.enclosingType = parameterizedTypeName;
        List<TypeName> listImmutableList = Util.immutableList(list);
        this.typeArguments = listImmutableList;
        Util.checkArgument((listImmutableList.isEmpty() && parameterizedTypeName == null) ? false : true, "no type arguments: %s", className);
        Iterator<TypeName> it = listImmutableList.iterator();
        while (it.hasNext()) {
            TypeName next = it.next();
            Util.checkArgument((next.isPrimitive() || next == TypeName.VOID) ? false : true, "invalid type parameter: %s", next);
        }
    }

    @Override // com.squareup.javapoet.TypeName
    CodeWriter emit(CodeWriter codeWriter) throws IOException {
        ParameterizedTypeName parameterizedTypeName = this.enclosingType;
        if (parameterizedTypeName != null) {
            parameterizedTypeName.emit(codeWriter);
            codeWriter.emit(".");
            if (isAnnotated()) {
                codeWriter.emit(" ");
                emitAnnotations(codeWriter);
            }
            codeWriter.emit(this.rawType.simpleName());
        } else {
            this.rawType.emit(codeWriter);
        }
        if (!this.typeArguments.isEmpty()) {
            codeWriter.emitAndIndent("<");
            boolean z = true;
            for (TypeName typeName : this.typeArguments) {
                if (!z) {
                    codeWriter.emitAndIndent(", ");
                }
                typeName.emit(codeWriter);
                z = false;
            }
            codeWriter.emitAndIndent(">");
        }
        return codeWriter;
    }

    public ParameterizedTypeName nestedClass(String str, List<TypeName> list) {
        Util.checkNotNull(str, "name == null", new Object[0]);
        return new ParameterizedTypeName(this, this.rawType.nestedClass(str), list, new ArrayList());
    }

    public static ParameterizedTypeName get(ClassName className, TypeName... typeNameArr) {
        return new ParameterizedTypeName(null, className, Arrays.asList(typeNameArr));
    }

    static ParameterizedTypeName get(ParameterizedType parameterizedType, Map<Type, TypeVariableName> map) {
        ClassName className = ClassName.get((Class<?>) parameterizedType.getRawType());
        ParameterizedType parameterizedType2 = (!(parameterizedType.getOwnerType() instanceof ParameterizedType) || Modifier.isStatic(((Class) parameterizedType.getRawType()).getModifiers())) ? null : (ParameterizedType) parameterizedType.getOwnerType();
        List<TypeName> list = TypeName.list(parameterizedType.getActualTypeArguments(), map);
        if (parameterizedType2 != null) {
            return get(parameterizedType2, map).nestedClass(className.simpleName(), list);
        }
        return new ParameterizedTypeName(null, className, list);
    }
}
