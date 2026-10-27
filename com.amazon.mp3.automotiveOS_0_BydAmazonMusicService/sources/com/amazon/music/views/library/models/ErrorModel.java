package com.amazon.music.views.library.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ErrorModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/views/library/models/ErrorModel;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "throwable", "Ljava/lang/Throwable;", "getThrowable", "()Ljava/lang/Throwable;", "pageIndex", "I", "getPageIndex", "()I", "<init>", "(Ljava/lang/Throwable;I)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ErrorModel {
    private final int pageIndex;
    private final Throwable throwable;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ErrorModel)) {
            return false;
        }
        ErrorModel errorModel = (ErrorModel) other;
        return Intrinsics.areEqual(this.throwable, errorModel.throwable) && this.pageIndex == errorModel.pageIndex;
    }

    public int hashCode() {
        return (this.throwable.hashCode() * 31) + Integer.hashCode(this.pageIndex);
    }

    public String toString() {
        return "ErrorModel(throwable=" + this.throwable + ", pageIndex=" + this.pageIndex + ')';
    }

    public ErrorModel(Throwable throwable, int i) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        this.throwable = throwable;
        this.pageIndex = i;
    }

    public final int getPageIndex() {
        return this.pageIndex;
    }

    public final Throwable getThrowable() {
        return this.throwable;
    }
}
