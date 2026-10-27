package com.amazon.bolthttp;

import com.amazon.bolthttp.policy.RetryLimitException;
import com.google.common.base.Preconditions;
import java.net.URL;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class BoltException extends Exception {
    private final Component mComponent;
    private final URL mRequestURL;

    public enum Component {
        NETWORK,
        RESPONSE_HANDLER
    }

    public BoltException(@Nonnull Component component, @Nonnull URL url, @Nonnull Throwable th) {
        super((Throwable) Preconditions.checkNotNull(th, "exception"));
        this.mComponent = (Component) Preconditions.checkNotNull(component, "component");
        this.mRequestURL = (URL) Preconditions.checkNotNull(url, "requestURL");
    }

    @Override // java.lang.Throwable
    @Nonnull
    public Throwable getCause() {
        return super.getCause();
    }

    @Nonnull
    public Throwable getComponentCause() {
        Throwable cause = getCause();
        return cause instanceof RetryLimitException ? cause.getCause() : cause;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return String.format("BoltException[component=%s, cause=%s]", this.mComponent, getCause());
    }
}
