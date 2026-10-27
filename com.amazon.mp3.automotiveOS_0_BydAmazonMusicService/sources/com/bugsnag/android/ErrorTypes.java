package com.bugsnag.android;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ErrorTypes.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0006¢\u0006\u0004\b\u0019\u0010\u001aB\u0011\b\u0010\u0012\u0006\u0010\u001b\u001a\u00020\u0006¢\u0006\u0004\b\u0019\u0010\u000fJ\u000f\u0010\u0004\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\t\u001a\u00020\bH\u0016R\"\u0010\n\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\"\u0010\u0013\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\"\u0010\u0016\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/bugsnag/android/ErrorTypes;", "", "copy$bugsnag_android_core_release", "()Lcom/bugsnag/android/ErrorTypes;", "copy", "other", "", "equals", "", "hashCode", "anrs", "Z", "getAnrs", "()Z", "setAnrs", "(Z)V", "ndkCrashes", "getNdkCrashes", "setNdkCrashes", "unhandledExceptions", "getUnhandledExceptions", "setUnhandledExceptions", "unhandledRejections", "getUnhandledRejections", "setUnhandledRejections", "<init>", "(ZZZZ)V", "detectErrors", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class ErrorTypes {
    private boolean anrs;
    private boolean ndkCrashes;
    private boolean unhandledExceptions;
    private boolean unhandledRejections;

    public ErrorTypes() {
        this(false, false, false, false, 15, null);
    }

    public ErrorTypes(boolean z, boolean z2, boolean z3, boolean z4) {
        this.anrs = z;
        this.ndkCrashes = z2;
        this.unhandledExceptions = z3;
        this.unhandledRejections = z4;
    }

    public final boolean getAnrs() {
        return this.anrs;
    }

    public final boolean getNdkCrashes() {
        return this.ndkCrashes;
    }

    public final boolean getUnhandledExceptions() {
        return this.unhandledExceptions;
    }

    public /* synthetic */ ErrorTypes(boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? true : z4);
    }

    public final boolean getUnhandledRejections() {
        return this.unhandledRejections;
    }

    public ErrorTypes(boolean z) {
        this(z, z, z, z);
    }

    public final ErrorTypes copy$bugsnag_android_core_release() {
        return new ErrorTypes(this.anrs, this.ndkCrashes, this.unhandledExceptions, this.unhandledRejections);
    }

    public boolean equals(Object other) {
        if (other instanceof ErrorTypes) {
            ErrorTypes errorTypes = (ErrorTypes) other;
            if (this.anrs == errorTypes.anrs && this.ndkCrashes == errorTypes.ndkCrashes && this.unhandledExceptions == errorTypes.unhandledExceptions && this.unhandledRejections == errorTypes.unhandledRejections) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((Boolean.valueOf(this.anrs).hashCode() * 31) + Boolean.valueOf(this.ndkCrashes).hashCode()) * 31) + Boolean.valueOf(this.unhandledExceptions).hashCode()) * 31) + Boolean.valueOf(this.unhandledRejections).hashCode();
    }
}
