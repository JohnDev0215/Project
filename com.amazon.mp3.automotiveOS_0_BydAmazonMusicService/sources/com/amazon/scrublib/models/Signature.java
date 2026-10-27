package com.amazon.scrublib.models;

import com.amazon.scrublib.utils.AtomicInt;
import com.amazon.scrublib.utils.PlatformUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Signature.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0014\u0010\u0015J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR$\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/scrublib/models/Signature;", "", "", "startTime", "clone", "", "signature", "Ljava/lang/String;", "getSignature", "()Ljava/lang/String;", "Lcom/amazon/scrublib/utils/AtomicInt;", "_startTimeStore", "Lcom/amazon/scrublib/utils/AtomicInt;", "get_startTimeStore", "()Lcom/amazon/scrublib/utils/AtomicInt;", "value", "getStartTime", "()I", "setStartTime", "(I)V", "<init>", "(ILjava/lang/String;)V", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final class Signature {
    private final AtomicInt _startTimeStore;
    private final String signature;

    public Signature() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    public Signature(int i, String str) {
        this.signature = str;
        this._startTimeStore = new AtomicInt(i);
        PlatformUtilsKt.freeze(this);
    }

    public /* synthetic */ Signature(int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? null : str);
    }

    public final String getSignature() {
        return this.signature;
    }

    public final int getStartTime() {
        return this._startTimeStore.get();
    }

    public final void setStartTime(int i) {
        this._startTimeStore.set(i);
    }

    public final Signature clone(int startTime) {
        return new Signature(startTime, this.signature);
    }
}
