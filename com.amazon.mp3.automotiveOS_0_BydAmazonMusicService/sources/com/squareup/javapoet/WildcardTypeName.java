package com.squareup.javapoet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes7.dex */
public final class WildcardTypeName extends TypeName {
    public final List<TypeName> lowerBounds;
    public final List<TypeName> upperBounds;

    private WildcardTypeName(List<TypeName> list, List<TypeName> list2) {
        this(list, list2, new ArrayList());
    }

    private WildcardTypeName(List<TypeName> list, List<TypeName> list2, List<AnnotationSpec> list3) {
        super(list3);
        List<TypeName> listImmutableList = Util.immutableList(list);
        this.upperBounds = listImmutableList;
        this.lowerBounds = Util.immutableList(list2);
        Util.checkArgument(listImmutableList.size() == 1, "unexpected extends bounds: %s", list);
        Iterator<TypeName> it = listImmutableList.iterator();
        while (it.hasNext()) {
            TypeName next = it.next();
            Util.checkArgument((next.isPrimitive() || next == TypeName.VOID) ? false : true, "invalid upper bound: %s", next);
        }
        Iterator<TypeName> it2 = this.lowerBounds.iterator();
        while (it2.hasNext()) {
            TypeName next2 = it2.next();
            Util.checkArgument((next2.isPrimitive() || next2 == TypeName.VOID) ? false : true, "invalid lower bound: %s", next2);
        }
    }

    @Override // com.squareup.javapoet.TypeName
    CodeWriter emit(CodeWriter codeWriter) throws IOException {
        return this.lowerBounds.size() == 1 ? codeWriter.emit("? super $T", this.lowerBounds.get(0)) : this.upperBounds.get(0).equals(TypeName.OBJECT) ? codeWriter.emit("?") : codeWriter.emit("? extends $T", this.upperBounds.get(0));
    }

    public static WildcardTypeName subtypeOf(TypeName typeName) {
        return new WildcardTypeName(Collections.singletonList(typeName), Collections.emptyList());
    }

    public static WildcardTypeName subtypeOf(Type type) {
        return subtypeOf(TypeName.get(type));
    }

    public static WildcardTypeName supertypeOf(TypeName typeName) {
        return new WildcardTypeName(Collections.singletonList(TypeName.OBJECT), Collections.singletonList(typeName));
    }

    static TypeName get(WildcardType wildcardType, Map<Type, TypeVariableName> map) {
        return new WildcardTypeName(TypeName.list(wildcardType.getUpperBounds(), map), TypeName.list(wildcardType.getLowerBounds(), map));
    }
}
