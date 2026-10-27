package com.squareup.kotlinpoet;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ParameterizedTypeName.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 !2\u00020\u0001:\u0001!B]\b\u0000\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0018\b\u0002\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0004\b\u001f\u0010 J6\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0016\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\t0\u0007H\u0016J\u0017\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0010¢\u0006\u0004\b\u000e\u0010\u000fJ\u001c\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e¨\u0006\""}, d2 = {"Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "Lcom/squareup/kotlinpoet/TypeName;", "", "nullable", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotations", "", "Lkotlin/reflect/KClass;", "", "tags", "copy", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;)Lcom/squareup/kotlinpoet/CodeWriter;", "emit", "", "name", "typeArguments", "nestedClass", "enclosingType", "Lcom/squareup/kotlinpoet/TypeName;", "Lcom/squareup/kotlinpoet/ClassName;", "rawType", "Lcom/squareup/kotlinpoet/ClassName;", "getRawType", "()Lcom/squareup/kotlinpoet/ClassName;", "Ljava/util/List;", "getTypeArguments", "()Ljava/util/List;", "<init>", "(Lcom/squareup/kotlinpoet/TypeName;Lcom/squareup/kotlinpoet/ClassName;Ljava/util/List;ZLjava/util/List;Ljava/util/Map;)V", "Companion", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final class ParameterizedTypeName extends TypeName {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final TypeName enclosingType;
    private final ClassName rawType;
    private final List<TypeName> typeArguments;

    @Override // com.squareup.kotlinpoet.TypeName
    public /* bridge */ /* synthetic */ TypeName copy(boolean z, List list, Map map) {
        return copy(z, (List<AnnotationSpec>) list, (Map<KClass<?>, ? extends Object>) map);
    }

    public final ClassName getRawType() {
        return this.rawType;
    }

    public /* synthetic */ ParameterizedTypeName(TypeName typeName, ClassName className, List list, boolean z, List list2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeName, className, list, (i & 8) != 0 ? false : z, (i & 16) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list2, (i & 32) != 0 ? MapsKt__MapsKt.emptyMap() : map);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ParameterizedTypeName(TypeName typeName, ClassName rawType, List<? extends TypeName> typeArguments, boolean z, List<AnnotationSpec> annotations, Map<KClass<?>, ? extends Object> tags) {
        super(z, annotations, new TagMap(tags), null);
        Intrinsics.checkNotNullParameter(rawType, "rawType");
        Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(tags, "tags");
        this.enclosingType = typeName;
        this.rawType = rawType;
        this.typeArguments = UtilKt.toImmutableList(typeArguments);
        boolean zIsEmpty = typeArguments.isEmpty();
        boolean z2 = true;
        if (!(!zIsEmpty) && typeName == null) {
            z2 = false;
        }
        if (!z2) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("no type arguments: ", getRawType()).toString());
        }
    }

    @Override // com.squareup.kotlinpoet.TypeName
    public ParameterizedTypeName copy(boolean nullable, List<AnnotationSpec> annotations, Map<KClass<?>, ? extends Object> tags) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(tags, "tags");
        return new ParameterizedTypeName(this.enclosingType, this.rawType, this.typeArguments, nullable, annotations, tags);
    }

    @Override // com.squareup.kotlinpoet.TypeName
    public CodeWriter emit$kotlinpoet(CodeWriter out) {
        Intrinsics.checkNotNullParameter(out, "out");
        TypeName typeName = this.enclosingType;
        if (typeName != null) {
            typeName.emitAnnotations$kotlinpoet(out);
            this.enclosingType.emit$kotlinpoet(out);
            CodeWriter.emit$default(out, Intrinsics.stringPlus(".", this.rawType.getSimpleName()), false, 2, null);
        } else {
            this.rawType.emitAnnotations$kotlinpoet(out);
            this.rawType.emit$kotlinpoet(out);
        }
        if (!this.typeArguments.isEmpty()) {
            CodeWriter.emit$default(out, "<", false, 2, null);
            int i = 0;
            for (Object obj : this.typeArguments) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                TypeName typeName2 = (TypeName) obj;
                if (i > 0) {
                    CodeWriter.emit$default(out, ",·", false, 2, null);
                }
                typeName2.emitAnnotations$kotlinpoet(out);
                typeName2.emit$kotlinpoet(out);
                typeName2.emitNullable$kotlinpoet(out);
                i = i2;
            }
            CodeWriter.emit$default(out, ">", false, 2, null);
        }
        return out;
    }

    public final ParameterizedTypeName nestedClass(String name, List<? extends TypeName> typeArguments) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
        return new ParameterizedTypeName(this, this.rawType.nestedClass(name), typeArguments, false, null, null, 56, null);
    }

    /* JADX INFO: compiled from: ParameterizedTypeName.kt */
    @Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010\t\u001a\u00020\u0006*\u00020\u00022\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\t\u001a\u00020\u0006*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\nH\u0007¢\u0006\u0004\b\u0007\u0010\u000bJ+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eH\u0000¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/squareup/kotlinpoet/ParameterizedTypeName$Companion;", "", "Lcom/squareup/kotlinpoet/ClassName;", "", "Lcom/squareup/kotlinpoet/TypeName;", "typeArguments", "Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "get", "(Lcom/squareup/kotlinpoet/ClassName;[Lcom/squareup/kotlinpoet/TypeName;)Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "parameterizedBy", "", "(Lcom/squareup/kotlinpoet/ClassName;Ljava/util/List;)Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "Ljava/lang/reflect/ParameterizedType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "Ljava/lang/reflect/Type;", "Lcom/squareup/kotlinpoet/TypeVariableName;", "map", "get$kotlinpoet", "(Ljava/lang/reflect/ParameterizedType;Ljava/util/Map;)Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "<init>", "()V", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        @JvmName(name = "get")
        public final ParameterizedTypeName get(ClassName className, TypeName... typeArguments) {
            Intrinsics.checkNotNullParameter(className, "<this>");
            Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
            return new ParameterizedTypeName(null, className, ArraysKt___ArraysKt.toList(typeArguments), false, null, null, 56, null);
        }

        @JvmStatic
        @JvmName(name = "get")
        public final ParameterizedTypeName get(ClassName className, List<? extends TypeName> typeArguments) {
            Intrinsics.checkNotNullParameter(className, "<this>");
            Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
            return new ParameterizedTypeName(null, className, typeArguments, false, null, null, 56, null);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.squareup.kotlinpoet.ParameterizedTypeName get$kotlinpoet(java.lang.reflect.ParameterizedType r12, java.util.Map<java.lang.reflect.Type, com.squareup.kotlinpoet.TypeVariableName> r13) {
            /*
                r11 = this;
                java.lang.String r0 = "type"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
                java.lang.String r0 = "map"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
                java.lang.reflect.Type r0 = r12.getRawType()
                java.lang.String r1 = "null cannot be cast to non-null type java.lang.Class<*>"
                if (r0 == 0) goto L8d
                java.lang.Class r0 = (java.lang.Class) r0
                com.squareup.kotlinpoet.ClassName r4 = com.squareup.kotlinpoet.ClassNames.get(r0)
                java.lang.reflect.Type r0 = r12.getOwnerType()
                boolean r0 = r0 instanceof java.lang.reflect.ParameterizedType
                if (r0 == 0) goto L49
                java.lang.reflect.Type r0 = r12.getRawType()
                if (r0 == 0) goto L43
                java.lang.Class r0 = (java.lang.Class) r0
                int r0 = r0.getModifiers()
                boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
                if (r0 != 0) goto L49
                java.lang.reflect.Type r0 = r12.getOwnerType()
                if (r0 == 0) goto L3b
                java.lang.reflect.ParameterizedType r0 = (java.lang.reflect.ParameterizedType) r0
                goto L4a
            L3b:
                java.lang.NullPointerException r12 = new java.lang.NullPointerException
                java.lang.String r13 = "null cannot be cast to non-null type java.lang.reflect.ParameterizedType"
                r12.<init>(r13)
                throw r12
            L43:
                java.lang.NullPointerException r12 = new java.lang.NullPointerException
                r12.<init>(r1)
                throw r12
            L49:
                r0 = 0
            L4a:
                java.lang.reflect.Type[] r12 = r12.getActualTypeArguments()
                java.lang.String r1 = "type.actualTypeArguments"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r1)
                java.util.ArrayList r5 = new java.util.ArrayList
                int r1 = r12.length
                r5.<init>(r1)
                r1 = 0
                int r2 = r12.length
            L5b:
                if (r1 >= r2) goto L70
                r3 = r12[r1]
                int r1 = r1 + 1
                com.squareup.kotlinpoet.TypeName$Companion r6 = com.squareup.kotlinpoet.TypeName.INSTANCE
                java.lang.String r7 = "it"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r7)
                com.squareup.kotlinpoet.TypeName r3 = r6.get$kotlinpoet(r3, r13)
                r5.add(r3)
                goto L5b
            L70:
                if (r0 == 0) goto L7f
                com.squareup.kotlinpoet.ParameterizedTypeName r12 = r11.get$kotlinpoet(r0, r13)
                java.lang.String r13 = r4.getSimpleName()
                com.squareup.kotlinpoet.ParameterizedTypeName r12 = r12.nestedClass(r13, r5)
                goto L8c
            L7f:
                com.squareup.kotlinpoet.ParameterizedTypeName r12 = new com.squareup.kotlinpoet.ParameterizedTypeName
                r3 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                r9 = 56
                r10 = 0
                r2 = r12
                r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            L8c:
                return r12
            L8d:
                java.lang.NullPointerException r12 = new java.lang.NullPointerException
                r12.<init>(r1)
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.ParameterizedTypeName.Companion.get$kotlinpoet(java.lang.reflect.ParameterizedType, java.util.Map):com.squareup.kotlinpoet.ParameterizedTypeName");
        }
    }
}
