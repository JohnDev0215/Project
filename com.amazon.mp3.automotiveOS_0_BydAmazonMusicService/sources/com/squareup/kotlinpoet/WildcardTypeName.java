package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.TypeName;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: WildcardTypeName.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019BY\b\u0002\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0018\b\u0002\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0004\b\u0017\u0010\u0018J6\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0016\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\t0\u0007H\u0016J\u0017\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0010¢\u0006\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014¨\u0006\u001a"}, d2 = {"Lcom/squareup/kotlinpoet/WildcardTypeName;", "Lcom/squareup/kotlinpoet/TypeName;", "", "nullable", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotations", "", "Lkotlin/reflect/KClass;", "", "tags", "copy", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;)Lcom/squareup/kotlinpoet/CodeWriter;", "emit", "outTypes", "Ljava/util/List;", "getOutTypes", "()Ljava/util/List;", "inTypes", "getInTypes", "<init>", "(Ljava/util/List;Ljava/util/List;ZLjava/util/List;Ljava/util/Map;)V", "Companion", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final class WildcardTypeName extends TypeName {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<TypeName> inTypes;
    private final List<TypeName> outTypes;

    @Override // com.squareup.kotlinpoet.TypeName
    public /* bridge */ /* synthetic */ TypeName copy(boolean z, List list, Map map) {
        return copy(z, (List<AnnotationSpec>) list, (Map<KClass<?>, ? extends Object>) map);
    }

    /* synthetic */ WildcardTypeName(List list, List list2, boolean z, List list3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list3, (i & 16) != 0 ? MapsKt__MapsKt.emptyMap() : map);
    }

    private WildcardTypeName(List<? extends TypeName> list, List<? extends TypeName> list2, boolean z, List<AnnotationSpec> list3, Map<KClass<?>, ? extends Object> map) {
        super(z, list3, new TagMap(map), null);
        List<TypeName> immutableList = UtilKt.toImmutableList(list);
        this.outTypes = immutableList;
        this.inTypes = UtilKt.toImmutableList(list2);
        if (!(immutableList.size() == 1)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("unexpected out types: ", list).toString());
        }
    }

    @Override // com.squareup.kotlinpoet.TypeName
    public WildcardTypeName copy(boolean nullable, List<AnnotationSpec> annotations, Map<KClass<?>, ? extends Object> tags) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(tags, "tags");
        return new WildcardTypeName(this.outTypes, this.inTypes, nullable, annotations, tags);
    }

    @Override // com.squareup.kotlinpoet.TypeName
    public CodeWriter emit$kotlinpoet(CodeWriter out) {
        Intrinsics.checkNotNullParameter(out, "out");
        return this.inTypes.size() == 1 ? out.emitCode("in·%T", this.inTypes.get(0)) : Intrinsics.areEqual(this.outTypes, TypeNames.STAR.outTypes) ? CodeWriter.emit$default(out, "*", false, 2, null) : out.emitCode("out·%T", this.outTypes.get(0));
    }

    /* JADX INFO: compiled from: WildcardTypeName.kt */
    @Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0002H\u0007J+\u0010\u0010\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0000¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/squareup/kotlinpoet/WildcardTypeName$Companion;", "", "Lcom/squareup/kotlinpoet/TypeName;", "outType", "Lcom/squareup/kotlinpoet/WildcardTypeName;", "producerOf", "inType", "consumerOf", "Ljava/lang/reflect/WildcardType;", "wildcardName", "", "Ljava/lang/reflect/Type;", "Lcom/squareup/kotlinpoet/TypeVariableName;", "map", "get$kotlinpoet", "(Ljava/lang/reflect/WildcardType;Ljava/util/Map;)Lcom/squareup/kotlinpoet/TypeName;", "get", "<init>", "()V", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final WildcardTypeName producerOf(TypeName outType) {
            Intrinsics.checkNotNullParameter(outType, "outType");
            return new WildcardTypeName(CollectionsKt__CollectionsJVMKt.listOf(outType), CollectionsKt__CollectionsKt.emptyList(), false, null, null, 28, null);
        }

        @JvmStatic
        public final WildcardTypeName consumerOf(TypeName inType) {
            Intrinsics.checkNotNullParameter(inType, "inType");
            return new WildcardTypeName(CollectionsKt__CollectionsJVMKt.listOf(TypeNames.ANY), CollectionsKt__CollectionsJVMKt.listOf(inType), false, null, null, 28, null);
        }

        public final TypeName get$kotlinpoet(WildcardType wildcardName, Map<Type, TypeVariableName> map) {
            Intrinsics.checkNotNullParameter(wildcardName, "wildcardName");
            Intrinsics.checkNotNullParameter(map, "map");
            Type[] upperBounds = wildcardName.getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "wildcardName.upperBounds");
            ArrayList arrayList = new ArrayList(upperBounds.length);
            int length = upperBounds.length;
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                Type it = upperBounds[i2];
                i2++;
                TypeName.Companion companion = TypeName.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                arrayList.add(companion.get$kotlinpoet(it, map));
            }
            Type[] lowerBounds = wildcardName.getLowerBounds();
            Intrinsics.checkNotNullExpressionValue(lowerBounds, "wildcardName.lowerBounds");
            ArrayList arrayList2 = new ArrayList(lowerBounds.length);
            int length2 = lowerBounds.length;
            while (i < length2) {
                Type it2 = lowerBounds[i];
                i++;
                TypeName.Companion companion2 = TypeName.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                arrayList2.add(companion2.get$kotlinpoet(it2, map));
            }
            return new WildcardTypeName(arrayList, arrayList2, false, null, null, 28, null);
        }
    }
}
