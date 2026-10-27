package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: KSTypeParameter.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "Lcom/google/devtools/ksp/symbol/KSName;", "getName", "()Lcom/google/devtools/ksp/symbol/KSName;", "name", "Lcom/google/devtools/ksp/symbol/Variance;", "getVariance", "()Lcom/google/devtools/ksp/symbol/Variance;", "variance", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getBounds", "()Lkotlin/sequences/Sequence;", "bounds", "api"}, k = 1, mv = {1, 7, 1})
public interface KSTypeParameter extends KSDeclaration {
    Sequence<KSTypeReference> getBounds();

    KSName getName();

    Variance getVariance();
}
