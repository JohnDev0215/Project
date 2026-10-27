package com.google.devtools.ksp.processing;

import com.google.devtools.ksp.symbol.KSType;
import kotlin.Metadata;

/* JADX INFO: compiled from: KSBuiltIns.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0007\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/processing/KSBuiltIns;", "", "Lcom/google/devtools/ksp/symbol/KSType;", "getUnitType", "()Lcom/google/devtools/ksp/symbol/KSType;", "unitType", "getArrayType", "arrayType", "api"}, k = 1, mv = {1, 7, 1})
public interface KSBuiltIns {
    KSType getArrayType();

    KSType getUnitType();
}
