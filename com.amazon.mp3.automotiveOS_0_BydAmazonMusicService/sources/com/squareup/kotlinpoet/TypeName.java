package com.squareup.kotlinpoet;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.squareup.kotlinpoet.ParameterizedTypeName;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor7;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: TypeName.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 12\u00020\u0001:\u00011B'\b\u0004\u0012\u0006\u0010\u001c\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b/\u00100J \u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J<\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0018\b\u0002\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u00010\bH&J\u0013\u0010\f\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u0017\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H ¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u001a\u0010\u0018R\u0017\u0010\u001c\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001c\u0010\u001eR\u001a\u0010 \u001a\u00020\u001f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010$\u001a\u0004\b%\u0010&R\u001b\u0010+\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R$\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u00010\b8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0011\u0010.\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b.\u0010\u001e\u0082\u0001\u000523456¨\u00067"}, d2 = {"Lcom/squareup/kotlinpoet/TypeName;", "", "", "nullable", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotations", "copy", "", "Lkotlin/reflect/KClass;", "tags", "other", "equals", "", "hashCode", "", "toString", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;)Lcom/squareup/kotlinpoet/CodeWriter;", "emit", "", "emitAnnotations$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;)V", "emitAnnotations", "emitNullable$kotlinpoet", "emitNullable", "isNullable", "Z", "()Z", "Lcom/squareup/kotlinpoet/TagMap;", "tagMap", "Lcom/squareup/kotlinpoet/TagMap;", "getTagMap$kotlinpoet", "()Lcom/squareup/kotlinpoet/TagMap;", "Ljava/util/List;", "getAnnotations", "()Ljava/util/List;", "cachedString$delegate", "Lkotlin/Lazy;", "getCachedString", "()Ljava/lang/String;", "cachedString", "getTags", "()Ljava/util/Map;", "isAnnotated", "<init>", "(ZLjava/util/List;Lcom/squareup/kotlinpoet/TagMap;)V", "Companion", "Lcom/squareup/kotlinpoet/ClassName;", "Lcom/squareup/kotlinpoet/Dynamic;", "Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "Lcom/squareup/kotlinpoet/TypeVariableName;", "Lcom/squareup/kotlinpoet/WildcardTypeName;", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public abstract class TypeName {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<AnnotationSpec> annotations;

    /* JADX INFO: renamed from: cachedString$delegate, reason: from kotlin metadata */
    private final Lazy cachedString;
    private final boolean isNullable;
    private final TagMap tagMap;

    public /* synthetic */ TypeName(boolean z, List list, TagMap tagMap, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, list, tagMap);
    }

    public abstract TypeName copy(boolean nullable, List<AnnotationSpec> annotations, Map<KClass<?>, ? extends Object> tags);

    public abstract CodeWriter emit$kotlinpoet(CodeWriter out);

    public Map<KClass<?>, Object> getTags() {
        return this.tagMap.getTags();
    }

    private TypeName(boolean z, List<AnnotationSpec> list, TagMap tagMap) {
        this.isNullable = z;
        this.tagMap = tagMap;
        this.annotations = UtilKt.toImmutableList(list);
        this.cachedString = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.squareup.kotlinpoet.TypeName$cachedString$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                TypeName typeName = this.this$0;
                StringBuilder sb = new StringBuilder();
                CodeWriter codeWriter = new CodeWriter(sb, null, null, null, null, Integer.MAX_VALUE, 30, null);
                try {
                    typeName.emitAnnotations$kotlinpoet(codeWriter);
                    typeName.emit$kotlinpoet(codeWriter);
                    if (typeName.getIsNullable()) {
                        CodeWriter.emit$default(codeWriter, "?", false, 2, null);
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(codeWriter, null);
                    String string = sb.toString();
                    Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.toString()");
                    return string;
                } finally {
                }
            }
        });
    }

    /* JADX INFO: renamed from: isNullable, reason: from getter */
    public final boolean getIsNullable() {
        return this.isNullable;
    }

    private final String getCachedString() {
        return (String) this.cachedString.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TypeName copy$default(TypeName typeName, boolean z, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copy");
        }
        if ((i & 1) != 0) {
            z = typeName.isNullable;
        }
        if ((i & 2) != 0) {
            list = CollectionsKt___CollectionsKt.toList(typeName.annotations);
        }
        return typeName.copy(z, list);
    }

    public final TypeName copy(boolean nullable, List<AnnotationSpec> annotations) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        return copy(nullable, annotations, getTags());
    }

    public final boolean isAnnotated() {
        return !this.annotations.isEmpty();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && Intrinsics.areEqual(getClass(), other.getClass())) {
            return Intrinsics.areEqual(toString(), other.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        return getCachedString();
    }

    public final void emitAnnotations$kotlinpoet(CodeWriter out) {
        Intrinsics.checkNotNullParameter(out, "out");
        Iterator<AnnotationSpec> it = this.annotations.iterator();
        while (it.hasNext()) {
            AnnotationSpec.emit$kotlinpoet$default(it.next(), out, true, false, 4, null);
            CodeWriter.emit$default(out, " ", false, 2, null);
        }
    }

    public final void emitNullable$kotlinpoet(CodeWriter out) {
        Intrinsics.checkNotNullParameter(out, "out");
        if (this.isNullable) {
            CodeWriter.emit$default(out, "?", false, 2, null);
        }
    }

    /* JADX INFO: compiled from: TypeName.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bH\u0000¢\u0006\u0002\b\nJ)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\u000eH\u0000¢\u0006\u0002\b\n¨\u0006\u0010"}, d2 = {"Lcom/squareup/kotlinpoet/TypeName$Companion;", "", "()V", "get", "Lcom/squareup/kotlinpoet/TypeName;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/reflect/Type;", "map", "", "Lcom/squareup/kotlinpoet/TypeVariableName;", "get$kotlinpoet", "mirror", "Ljavax/lang/model/type/TypeMirror;", "typeVariables", "", "Ljavax/lang/model/element/TypeParameterElement;", "kotlinpoet"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TypeName get$kotlinpoet(TypeMirror mirror, final Map<TypeParameterElement, TypeVariableName> typeVariables) {
            Intrinsics.checkNotNullParameter(mirror, "mirror");
            Intrinsics.checkNotNullParameter(typeVariables, "typeVariables");
            Object objAccept = mirror.accept(new SimpleTypeVisitor7<TypeName, Void>() { // from class: com.squareup.kotlinpoet.TypeName$Companion$get$1
            }, (Object) null);
            Intrinsics.checkNotNullExpressionValue(objAccept, "typeVariables: Map<TypeP…  },\n        null\n      )");
            return (TypeName) objAccept;
        }

        public final TypeName get$kotlinpoet(Type type, Map<Type, TypeVariableName> map) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(map, "map");
            if (type instanceof Class) {
                if (type == Void.TYPE) {
                    return TypeNames.UNIT;
                }
                if (type == Boolean.TYPE) {
                    return TypeNames.BOOLEAN;
                }
                if (type == Byte.TYPE) {
                    return TypeNames.BYTE;
                }
                if (type == Short.TYPE) {
                    return TypeNames.SHORT;
                }
                if (type == Integer.TYPE) {
                    return TypeNames.INT;
                }
                if (type == Long.TYPE) {
                    return TypeNames.LONG;
                }
                if (type == Character.TYPE) {
                    return TypeNames.CHAR;
                }
                if (type == Float.TYPE) {
                    return TypeNames.FLOAT;
                }
                if (type == Double.TYPE) {
                    return TypeNames.DOUBLE;
                }
                Class cls = (Class) type;
                if (!cls.isArray()) {
                    return ClassNames.get((Class<?>) cls);
                }
                ParameterizedTypeName.Companion companion = ParameterizedTypeName.Companion;
                ClassName className = TypeNames.ARRAY;
                Class<?> componentType = cls.getComponentType();
                Intrinsics.checkNotNullExpressionValue(componentType, "type.componentType");
                return companion.get(className, get$kotlinpoet(componentType, map));
            }
            if (type instanceof ParameterizedType) {
                return ParameterizedTypeName.Companion.get$kotlinpoet((ParameterizedType) type, map);
            }
            if (type instanceof WildcardType) {
                return WildcardTypeName.INSTANCE.get$kotlinpoet((WildcardType) type, map);
            }
            if (type instanceof TypeVariable) {
                return TypeVariableName.Companion.get$kotlinpoet((TypeVariable) type, map);
            }
            if (!(type instanceof GenericArrayType)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("unexpected type: ", type));
            }
            ParameterizedTypeName.Companion companion2 = ParameterizedTypeName.Companion;
            ClassName className2 = TypeNames.ARRAY;
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            Intrinsics.checkNotNullExpressionValue(genericComponentType, "type.genericComponentType");
            return companion2.get(className2, get$kotlinpoet(genericComponentType, map));
        }
    }
}
