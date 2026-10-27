package com.squareup.kotlinpoet;

import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Taggable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0002\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0000H\u0000¨\u0006\u0003"}, d2 = {"Lcom/squareup/kotlinpoet/Taggable$Builder;", "Lcom/squareup/kotlinpoet/TagMap;", "buildTagMap", "kotlinpoet"}, k = 2, mv = {1, 6, 0})
public final class TaggableKt {
    public static final TagMap buildTagMap(Taggable$Builder<?> taggable$Builder) {
        Intrinsics.checkNotNullParameter(taggable$Builder, "<this>");
        return new TagMap(new LinkedHashMap(taggable$Builder.getTags()));
    }
}
