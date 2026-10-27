package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.Taggable$Builder;
import java.util.Map;
import kotlin.Metadata;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: Taggable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0010\b\u0000\u0010\u0002 \u0001*\b\u0012\u0004\u0012\u00028\u00000\u00012\u00020\u0003R$\u0010\b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00030\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"com/squareup/kotlinpoet/Taggable$Builder", "Lcom/squareup/kotlinpoet/Taggable$Builder;", "T", "", "", "Lkotlin/reflect/KClass;", "getTags", "()Ljava/util/Map;", "tags", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public interface Taggable$Builder<T extends Taggable$Builder<? extends T>> {
    Map<KClass<?>, Object> getTags();
}
