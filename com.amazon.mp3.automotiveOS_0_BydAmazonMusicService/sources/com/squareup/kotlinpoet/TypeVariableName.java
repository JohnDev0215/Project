package com.squareup.kotlinpoet;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.squareup.kotlinpoet.TypeName;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: TypeVariableName.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0001 Bg\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005\u0012\u0018\b\u0002\u0010\r\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\u0010\u0011JT\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u001a\u001a\u00020\t2\u0018\b\u0002\u0010\r\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0004\u0012\u00020\u00100\u000eJ6\u0010\u0019\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u0016\u0010\r\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0004\u0012\u00020\u00100\u000eH\u0016J\u0015\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0010¢\u0006\u0002\b\u001eJ\u0018\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005*\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006!"}, d2 = {"Lcom/squareup/kotlinpoet/TypeVariableName;", "Lcom/squareup/kotlinpoet/TypeName;", "name", "", "bounds", "", "variance", "Lcom/squareup/kotlinpoet/KModifier;", "isReified", "", "nullable", "annotations", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "tags", "", "Lkotlin/reflect/KClass;", "", "(Ljava/lang/String;Ljava/util/List;Lcom/squareup/kotlinpoet/KModifier;ZZLjava/util/List;Ljava/util/Map;)V", "getBounds", "()Ljava/util/List;", "()Z", "getName", "()Ljava/lang/String;", "getVariance", "()Lcom/squareup/kotlinpoet/KModifier;", "copy", "reified", "emit", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "withoutImplicitBound", "Companion", "kotlinpoet"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TypeVariableName extends TypeName {
    private final List<TypeName> bounds;
    private final boolean isReified;
    private final String name;
    private final KModifier variance;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<TypeName> NULLABLE_ANY_LIST = CollectionsKt__CollectionsJVMKt.listOf(CodeWriterKt.getNULLABLE_ANY());
    private static final ClassName JAVA_OBJECT = new ClassName("java.lang", "Object");

    @Override // com.squareup.kotlinpoet.TypeName
    public /* bridge */ /* synthetic */ TypeName copy(boolean z, List list, Map map) {
        return copy(z, (List<AnnotationSpec>) list, (Map<KClass<?>, ? extends Object>) map);
    }

    public final String getName() {
        return this.name;
    }

    public final List<TypeName> getBounds() {
        return this.bounds;
    }

    public final KModifier getVariance() {
        return this.variance;
    }

    /* JADX INFO: renamed from: isReified, reason: from getter */
    public final boolean getIsReified() {
        return this.isReified;
    }

    /* synthetic */ TypeVariableName(String str, List list, KModifier kModifier, boolean z, boolean z2, List list2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, (i & 4) != 0 ? null : kModifier, (i & 8) != 0 ? false : z, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list2, (i & 64) != 0 ? MapsKt__MapsKt.emptyMap() : map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TypeVariableName(String str, List<? extends TypeName> list, KModifier kModifier, boolean z, boolean z2, List<AnnotationSpec> list2, Map<KClass<?>, ? extends Object> map) {
        super(z2, list2, new TagMap(map), null);
        this.name = str;
        this.bounds = list;
        this.variance = kModifier;
        this.isReified = z;
    }

    @Override // com.squareup.kotlinpoet.TypeName
    public TypeVariableName copy(boolean nullable, List<AnnotationSpec> annotations, Map<KClass<?>, ? extends Object> tags) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(tags, "tags");
        return copy(nullable, annotations, this.bounds, this.isReified, tags);
    }

    public final TypeVariableName copy(boolean nullable, List<AnnotationSpec> annotations, List<? extends TypeName> bounds, boolean reified, Map<KClass<?>, ? extends Object> tags) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        Intrinsics.checkNotNullParameter(tags, "tags");
        return new TypeVariableName(this.name, withoutImplicitBound(bounds), this.variance, reified, nullable, annotations, tags);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<TypeName> withoutImplicitBound(List<? extends TypeName> list) {
        if (list.size() == 1) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!Intrinsics.areEqual((TypeName) obj, CodeWriterKt.getNULLABLE_ANY())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // com.squareup.kotlinpoet.TypeName
    public CodeWriter emit$kotlinpoet(CodeWriter out) {
        Intrinsics.checkNotNullParameter(out, "out");
        return CodeWriter.emit$default(out, this.name, false, 2, null);
    }

    /* JADX INFO: compiled from: TypeVariableName.kt */
    @Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0018\u0010\u0019J/\u0010\f\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000bJ1\u0010\u0014\u001a\u00020\t2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\r2\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t0\u000fH\u0000¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/squareup/kotlinpoet/TypeVariableName$Companion;", "", "", "name", "", "Lcom/squareup/kotlinpoet/TypeName;", "bounds", "Lcom/squareup/kotlinpoet/KModifier;", "variance", "Lcom/squareup/kotlinpoet/TypeVariableName;", "of$kotlinpoet", "(Ljava/lang/String;Ljava/util/List;Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/TypeVariableName;", "of", "Ljava/lang/reflect/TypeVariable;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "Ljava/lang/reflect/Type;", "map", "get$kotlinpoet", "(Ljava/lang/reflect/TypeVariable;Ljava/util/Map;)Lcom/squareup/kotlinpoet/TypeVariableName;", "get", "Lcom/squareup/kotlinpoet/ClassName;", "JAVA_OBJECT", "Lcom/squareup/kotlinpoet/ClassName;", "<init>", "()V", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TypeVariableName of$kotlinpoet(String name, List<? extends TypeName> bounds, KModifier variance) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            if (!(variance == null || UtilKt.isOneOf(variance, KModifier.IN, KModifier.OUT, (60 & 4) != 0 ? null : null, (60 & 8) != 0 ? null : null, (60 & 16) != 0 ? null : null, (60 & 32) != 0 ? null : null))) {
                throw new IllegalArgumentException((variance + " is an invalid variance modifier, the only allowed values are in and out!").toString());
            }
            if (!(true ^ bounds.isEmpty())) {
                throw new IllegalArgumentException(Intrinsics.stringPlus(name, " has no bounds").toString());
            }
            return new TypeVariableName(name, bounds, variance, false, false, null, null, 120, null);
        }

        public final TypeVariableName get$kotlinpoet(TypeVariable<?> type, Map<Type, TypeVariableName> map) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(map, "map");
            TypeVariableName typeVariableName = map.get(type);
            if (typeVariableName != null) {
                return typeVariableName;
            }
            ArrayList arrayList = new ArrayList();
            List visibleBounds = Collections.unmodifiableList(arrayList);
            String name = type.getName();
            Intrinsics.checkNotNullExpressionValue(name, "type.name");
            Intrinsics.checkNotNullExpressionValue(visibleBounds, "visibleBounds");
            TypeVariableName typeVariableName2 = new TypeVariableName(name, visibleBounds, null, false, false, null, null, 124, null);
            map.put(type, typeVariableName2);
            Type[] bounds = type.getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "type.bounds");
            int i = 0;
            int length = bounds.length;
            while (i < length) {
                Type bound = bounds[i];
                i++;
                TypeName.Companion companion = TypeName.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(bound, "bound");
                arrayList.add(companion.get$kotlinpoet(bound, map));
            }
            arrayList.remove(TypeNames.ANY);
            arrayList.remove(TypeVariableName.JAVA_OBJECT);
            if (arrayList.isEmpty()) {
                arrayList.add(CodeWriterKt.getNULLABLE_ANY());
            }
            return typeVariableName2;
        }
    }
}
