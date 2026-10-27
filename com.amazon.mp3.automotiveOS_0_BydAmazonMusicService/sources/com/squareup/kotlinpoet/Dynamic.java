package com.squareup.kotlinpoet;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: Dynamic.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0016\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u0004\u0012\u00020\r0\u000bH\u0016J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0010¢\u0006\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/squareup/kotlinpoet/Dynamic;", "Lcom/squareup/kotlinpoet/TypeName;", "()V", "copy", "", "nullable", "", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "tags", "", "Lkotlin/reflect/KClass;", "", "emit", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "kotlinpoet"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Dynamic extends TypeName {
    public static final Dynamic INSTANCE = new Dynamic();

    private Dynamic() {
        super(false, CollectionsKt__CollectionsKt.emptyList(), new TagMap(MapsKt__MapsKt.emptyMap()), null);
    }

    @Override // com.squareup.kotlinpoet.TypeName
    public /* bridge */ /* synthetic */ TypeName copy(boolean z, List list, Map map) {
        return (TypeName) m2745copy(z, (List<AnnotationSpec>) list, (Map<KClass<?>, ? extends Object>) map);
    }

    /* JADX INFO: renamed from: copy, reason: collision with other method in class */
    public Void m2745copy(boolean nullable, List<AnnotationSpec> annotations, Map<KClass<?>, ? extends Object> tags) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(tags, "tags");
        throw new UnsupportedOperationException("dynamic doesn't support copying");
    }

    @Override // com.squareup.kotlinpoet.TypeName
    public CodeWriter emit$kotlinpoet(CodeWriter out) {
        Intrinsics.checkNotNullParameter(out, "out");
        CodeWriter.emit$default(out, "dynamic", false, 2, null);
        return out;
    }
}
