package org.apache.shiro.session.mgt;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractSessionManager implements SessionManager {
    public static final long DEFAULT_GLOBAL_SESSION_TIMEOUT = 1800000;
    protected static final long MILLIS_PER_HOUR = 3600000;
    protected static final long MILLIS_PER_MINUTE = 60000;
    protected static final long MILLIS_PER_SECOND = 1000;
    private long globalSessionTimeout = DEFAULT_GLOBAL_SESSION_TIMEOUT;

    public long getGlobalSessionTimeout() {
        return this.globalSessionTimeout;
    }

    public void setGlobalSessionTimeout(long j) {
        this.globalSessionTimeout = j;
    }
}
