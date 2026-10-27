package org.joda.time.base;

import java.io.Serializable;

/* JADX INFO: loaded from: classes6.dex */
public abstract class BaseDuration extends AbstractDuration implements Serializable {
    private volatile long iMillis;

    protected BaseDuration(long j) {
        this.iMillis = j;
    }

    @Override // org.joda.time.ReadableDuration
    public long getMillis() {
        return this.iMillis;
    }
}
