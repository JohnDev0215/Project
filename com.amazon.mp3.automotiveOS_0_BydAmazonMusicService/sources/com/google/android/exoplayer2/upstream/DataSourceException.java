package com.google.android.exoplayer2.upstream;

import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public final class DataSourceException extends IOException {
    public final int reason;

    public DataSourceException(int i) {
        this.reason = i;
    }
}
