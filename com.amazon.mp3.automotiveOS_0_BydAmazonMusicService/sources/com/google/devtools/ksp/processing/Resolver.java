package com.google.devtools.ksp.processing;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSName;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.Variance;
import kotlin.Metadata;

/* JADX INFO: compiled from: Resolver.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH&J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\fH&J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000eH&J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0012\u001a\u00020\u0011H'J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000eH'R\u0014\u0010\u0019\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001aÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/processing/Resolver;", "", "Lcom/google/devtools/ksp/symbol/KSName;", "name", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "getClassDeclarationByName", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "typeRef", "Lcom/google/devtools/ksp/symbol/Variance;", "variance", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "getTypeArgument", "", "getKSNameFromString", "Lcom/google/devtools/ksp/symbol/KSType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "createKSTypeReferenceFromKSType", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "declaration", "mapToJvmSignature", "", "isJavaRawType", "Lcom/google/devtools/ksp/processing/KSBuiltIns;", "getBuiltIns", "()Lcom/google/devtools/ksp/processing/KSBuiltIns;", "builtIns", "api"}, k = 1, mv = {1, 7, 1})
public interface Resolver {
    KSTypeReference createKSTypeReferenceFromKSType(KSType type);

    KSBuiltIns getBuiltIns();

    KSClassDeclaration getClassDeclarationByName(KSName name);

    KSName getKSNameFromString(String name);

    KSTypeArgument getTypeArgument(KSTypeReference typeRef, Variance variance);

    boolean isJavaRawType(KSType type);

    String mapToJvmSignature(KSDeclaration declaration);
}
