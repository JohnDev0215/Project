package com.google.devtools.ksp.symbol;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: KSDeclaration.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0001R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\r\u001a\u00020\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0005¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSDeclaration;", "", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "Lcom/google/devtools/ksp/symbol/KSName;", "getQualifiedName", "()Lcom/google/devtools/ksp/symbol/KSName;", "qualifiedName", "", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "getTypeParameters", "()Ljava/util/List;", "typeParameters", "getPackageName", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "api"}, k = 1, mv = {1, 7, 1})
public interface KSDeclaration extends KSAnnotated {
    KSName getPackageName();

    KSName getQualifiedName();

    List<KSTypeParameter> getTypeParameters();
}
