package com.fasterxml.uuid;

import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public abstract class TimestampSynchronizer {
    protected abstract long initialize() throws IOException;

    protected abstract long update(long j) throws IOException;

    protected TimestampSynchronizer() {
    }
}
