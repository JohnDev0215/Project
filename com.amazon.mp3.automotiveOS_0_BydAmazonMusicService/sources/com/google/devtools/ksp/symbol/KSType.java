package com.google.devtools.ksp.symbol;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: KSType.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0005\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&J\b\u0010\u0006\u001a\u00020\u0000H&J\b\u0010\u0007\u001a\u00020\u0000H&J\b\u0010\b\u001a\u00020\u0000H&R\u0014\u0010\f\u001a\u00020\t8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00138&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00138&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015¨\u0006\u0018À\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSType;", "", "", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "arguments", "replace", "starProjection", "makeNullable", "makeNotNullable", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "getDeclaration", "()Lcom/google/devtools/ksp/symbol/KSDeclaration;", "declaration", "Lcom/google/devtools/ksp/symbol/Nullability;", "getNullability", "()Lcom/google/devtools/ksp/symbol/Nullability;", "nullability", "getArguments", "()Ljava/util/List;", "", "isMarkedNullable", "()Z", "isError", "isSuspendFunctionType", "api"}, k = 1, mv = {1, 7, 1})
public interface KSType {
    List<KSTypeArgument> getArguments();

    KSDeclaration getDeclaration();

    Nullability getNullability();

    boolean isError();

    boolean isMarkedNullable();

    boolean isSuspendFunctionType();

    KSType makeNotNullable();

    KSType makeNullable();

    KSType replace(List<? extends KSTypeArgument> arguments);

    KSType starProjection();
}
