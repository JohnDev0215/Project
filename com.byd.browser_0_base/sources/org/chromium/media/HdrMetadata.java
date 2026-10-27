package org.chromium.media;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class HdrMetadata {
    public long a;
    public final Object b;

    public HdrMetadata() {
        this.b = new Object();
        this.a = 0L;
    }

    @CalledByNative
    public static HdrMetadata create(long j) {
        return new HdrMetadata(j);
    }

    @CalledByNative
    public final void close() {
        synchronized (this.b) {
            this.a = 0L;
        }
    }

    public HdrMetadata(long j) {
        this.b = new Object();
        this.a = j;
    }
}
