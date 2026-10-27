package com.squareup.kotlinpoet;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemberName.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u0000 $2\u00020\u0001:\u0001$J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\bH\u0016J\t\u0010\u000b\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u000f\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u0012R\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001f\u0010!R\u0017\u0010\"\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\"\u0010\u0010\u001a\u0004\b#\u0010\u0012¨\u0006%"}, d2 = {"Lcom/squareup/kotlinpoet/MemberName;", "", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "", "emit$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;)V", "emit", "", "toString", "", "hashCode", "other", "", "equals", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "Ljava/lang/String;", "getPackageName", "()Ljava/lang/String;", "Lcom/squareup/kotlinpoet/ClassName;", "enclosingClassName", "Lcom/squareup/kotlinpoet/ClassName;", "getEnclosingClassName", "()Lcom/squareup/kotlinpoet/ClassName;", "simpleName", "getSimpleName", "Lcom/squareup/kotlinpoet/KOperator;", "operator", "Lcom/squareup/kotlinpoet/KOperator;", "getOperator", "()Lcom/squareup/kotlinpoet/KOperator;", "isExtension", "Z", "()Z", "canonicalName", "getCanonicalName", "Companion", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final /* data */ class MemberName {
    private final String canonicalName;
    private final ClassName enclosingClassName;
    private final boolean isExtension;
    private final KOperator operator;
    private final String packageName;
    private final String simpleName;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MemberName)) {
            return false;
        }
        MemberName memberName = (MemberName) other;
        return Intrinsics.areEqual(this.packageName, memberName.packageName) && Intrinsics.areEqual(this.enclosingClassName, memberName.enclosingClassName) && Intrinsics.areEqual(this.simpleName, memberName.simpleName) && this.operator == memberName.operator && this.isExtension == memberName.isExtension;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8 */
    public int hashCode() {
        int iHashCode = this.packageName.hashCode() * 31;
        ClassName className = this.enclosingClassName;
        int iHashCode2 = (((iHashCode + (className == null ? 0 : className.hashCode())) * 31) + this.simpleName.hashCode()) * 31;
        KOperator kOperator = this.operator;
        int iHashCode3 = (iHashCode2 + (kOperator != null ? kOperator.hashCode() : 0)) * 31;
        boolean z = this.isExtension;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode3 + r1;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final ClassName getEnclosingClassName() {
        return this.enclosingClassName;
    }

    public final String getSimpleName() {
        return this.simpleName;
    }

    /* JADX INFO: renamed from: isExtension, reason: from getter */
    public final boolean getIsExtension() {
        return this.isExtension;
    }

    public final String getCanonicalName() {
        return this.canonicalName;
    }

    public final void emit$kotlinpoet(CodeWriter out) {
        Intrinsics.checkNotNullParameter(out, "out");
        if (this.operator == null) {
            CodeWriter.emit$default(out, UtilKt.escapeSegmentsIfNecessary$default(out.lookupName(this), (char) 0, 1, null), false, 2, null);
        } else {
            out.lookupName(this);
            CodeWriter.emit$default(out, this.operator.getOperator(), false, 2, null);
        }
    }

    public String toString() {
        return this.canonicalName;
    }
}
