package com.amazon.avod.userdownload;

import com.amazon.avod.userdownload.reporting.PauseCause;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class PauseToken {
    private final PauseCause mCause;
    private final Object mToken;

    public PauseToken(@Nonnull Object obj, @Nonnull PauseCause pauseCause) {
        this.mToken = Preconditions.checkNotNull(obj, "token");
        this.mCause = (PauseCause) Preconditions.checkNotNull(pauseCause, "cause");
    }

    @Nonnull
    public Object getRawToken() {
        return this.mToken;
    }

    @Nonnull
    public PauseCause getCause() {
        return this.mCause;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("token", this.mToken).add("cause", this.mCause).toString();
    }
}
