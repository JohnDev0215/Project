package com.google.devtools.ksp.symbol;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;

/* JADX INFO: compiled from: KSTypeArgument.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getType", "()Lcom/google/devtools/ksp/symbol/KSTypeReference;", "variance", "Lcom/google/devtools/ksp/symbol/Variance;", "getVariance", "()Lcom/google/devtools/ksp/symbol/Variance;", "api"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface KSTypeArgument extends KSAnnotated {
    KSTypeReference getType();

    Variance getVariance();
}
