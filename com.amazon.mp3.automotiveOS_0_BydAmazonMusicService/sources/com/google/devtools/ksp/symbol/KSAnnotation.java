package com.google.devtools.ksp.symbol;

import kotlin.Metadata;

/* JADX INFO: compiled from: KSAnnotation.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSAnnotation;", "", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getAnnotationType", "()Lcom/google/devtools/ksp/symbol/KSTypeReference;", "annotationType", "Lcom/google/devtools/ksp/symbol/KSName;", "getShortName", "()Lcom/google/devtools/ksp/symbol/KSName;", "shortName", "api"}, k = 1, mv = {1, 7, 1})
public interface KSAnnotation {
    KSTypeReference getAnnotationType();

    KSName getShortName();
}
