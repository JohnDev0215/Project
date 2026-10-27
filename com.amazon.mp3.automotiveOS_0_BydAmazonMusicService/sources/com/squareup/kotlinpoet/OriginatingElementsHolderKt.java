package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.OriginatingElementsHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OriginatingElementsHolder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0002\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0000H\u0000¨\u0006\u0003"}, d2 = {"Lcom/squareup/kotlinpoet/OriginatingElementsHolder$Builder;", "Lcom/squareup/kotlinpoet/OriginatingElements;", "buildOriginatingElements", "kotlinpoet"}, k = 2, mv = {1, 6, 0})
public final class OriginatingElementsHolderKt {
    public static final OriginatingElements buildOriginatingElements(OriginatingElementsHolder.Builder<?> builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return new OriginatingElements(UtilKt.toImmutableList(builder.getOriginatingElements()));
    }
}
