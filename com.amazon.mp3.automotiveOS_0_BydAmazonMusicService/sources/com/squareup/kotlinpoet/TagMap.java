package com.squareup.kotlinpoet;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: Taggable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0016\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00010\u0002¢\u0006\u0004\b\b\u0010\tR*\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/squareup/kotlinpoet/TagMap;", "", "", "Lkotlin/reflect/KClass;", "tags", "Ljava/util/Map;", "getTags", "()Ljava/util/Map;", "<init>", "(Ljava/util/Map;)V", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final class TagMap {
    private final Map<KClass<?>, Object> tags;

    public TagMap(Map<KClass<?>, ? extends Object> tags) {
        Intrinsics.checkNotNullParameter(tags, "tags");
        this.tags = UtilKt.toImmutableMap(tags);
    }

    public Map<KClass<?>, Object> getTags() {
        return this.tags;
    }
}
