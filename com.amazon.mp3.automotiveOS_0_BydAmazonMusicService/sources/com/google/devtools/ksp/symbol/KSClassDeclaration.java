package com.google.devtools.ksp.symbol;

import java.util.List;
import kotlin.Metadata;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: KSClassDeclaration.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u0016\u0010\u0007\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&R\u0014\u0010\u000b\u001a\u00020\b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "", "", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "typeArguments", "Lcom/google/devtools/ksp/symbol/KSType;", "asType", "Lcom/google/devtools/ksp/symbol/ClassKind;", "getClassKind", "()Lcom/google/devtools/ksp/symbol/ClassKind;", "classKind", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getSuperTypes", "()Lkotlin/sequences/Sequence;", "superTypes", "api"}, k = 1, mv = {1, 7, 1})
public interface KSClassDeclaration extends KSDeclaration {
    KSType asType(List<? extends KSTypeArgument> typeArguments);

    ClassKind getClassKind();

    Sequence<KSTypeReference> getSuperTypes();
}
