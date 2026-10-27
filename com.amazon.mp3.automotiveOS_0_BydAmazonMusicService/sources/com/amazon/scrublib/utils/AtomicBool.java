package com.amazon.scrublib.utils;

import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;

/* JADX INFO: compiled from: AtomicBool.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\f\u0010\rB\u0011\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\f\u0010\u000fJ\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0002R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/amazon/scrublib/utils/AtomicBool;", "", "", "get", "new", "", "set", "Ljava/util/concurrent/atomic/AtomicBoolean;", "_bool", "Ljava/util/concurrent/atomic/AtomicBoolean;", "get_bool", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "<init>", "()V", "initial", "(Z)V", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final class AtomicBool {
    private final AtomicBoolean _bool;

    public AtomicBool() {
        this._bool = new AtomicBoolean(false);
    }

    public AtomicBool(boolean z) {
        this();
        this._bool.set(z);
    }

    public final boolean get() {
        return this._bool.get();
    }

    public final void set(boolean z) {
        this._bool.set(z);
    }
}
