package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Import.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0011\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0000H\u0096\u0002J\t\u0010\u0007\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\bHÖ\u0003R\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/squareup/kotlinpoet/Import;", "", "", "toString", "other", "", "compareTo", "hashCode", "", "", "equals", "qualifiedName", "Ljava/lang/String;", "getQualifiedName", "()Ljava/lang/String;", "alias", "getAlias", "importString", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final /* data */ class Import implements Comparable<Import> {
    private final String alias;
    private final String importString;
    private final String qualifiedName;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Import)) {
            return false;
        }
        Import r5 = (Import) other;
        return Intrinsics.areEqual(this.qualifiedName, r5.qualifiedName) && Intrinsics.areEqual(this.alias, r5.alias);
    }

    public int hashCode() {
        int iHashCode = this.qualifiedName.hashCode() * 31;
        String str = this.alias;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final String getAlias() {
        return this.alias;
    }

    /* JADX INFO: renamed from: toString, reason: from getter */
    public String getImportString() {
        return this.importString;
    }

    @Override // java.lang.Comparable
    public int compareTo(Import other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.importString.compareTo(other.importString);
    }
}
