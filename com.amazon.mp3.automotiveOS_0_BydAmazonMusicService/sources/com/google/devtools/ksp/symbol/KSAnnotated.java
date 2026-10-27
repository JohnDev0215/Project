package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: KSAnnotated.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSAnnotated;", "", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "getAnnotations", "()Lkotlin/sequences/Sequence;", "annotations", "api"}, k = 1, mv = {1, 7, 1})
public interface KSAnnotated {
    Sequence<KSAnnotation> getAnnotations();
}
