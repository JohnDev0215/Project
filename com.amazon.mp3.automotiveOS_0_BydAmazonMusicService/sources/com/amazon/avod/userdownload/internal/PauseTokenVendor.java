package com.amazon.avod.userdownload.internal;

import com.amazon.avod.threading.UIThreadUtils;
import com.amazon.avod.userdownload.PauseToken;
import com.amazon.avod.userdownload.reporting.PauseCause;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Throwables2;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PauseTokenVendor {
    private final Object mLock = new Object();

    @GuardedBy("mLock")
    private final Map<Object, PauseCause> mVendedTokenCauses = Maps.newHashMap();

    @GuardedBy("mLock")
    private final Map<PauseToken, PauseTokenGuard> mVendedTokenGuard = new WeakHashMap();

    public PauseToken requestToken(@Nonnull PauseCause pauseCause) {
        PauseToken pauseToken;
        Preconditions.checkNotNull(pauseCause, "cause");
        synchronized (this.mLock) {
            pauseToken = new PauseToken(new Object(), pauseCause);
            this.mVendedTokenGuard.put(pauseToken, new PauseTokenGuard(pauseToken));
            this.mVendedTokenCauses.put(pauseToken.getRawToken(), pauseToken.getCause());
            DLog.devf("Created new pause token: %s", pauseToken);
        }
        return pauseToken;
    }

    public void releaseToken(@Nonnull PauseToken pauseToken) {
        Preconditions.checkNotNull(pauseToken, "token");
        synchronized (this.mLock) {
            PauseTokenGuard pauseTokenGuardRemove = this.mVendedTokenGuard.remove(pauseToken);
            this.mVendedTokenCauses.remove(pauseToken.getRawToken());
            if (pauseTokenGuardRemove != null) {
                pauseTokenGuardRemove.release();
                DLog.devf("Releasing existing pause token: %s", pauseToken);
            } else {
                DLog.devf("Could not release existing pause token (leak): %s", pauseToken);
            }
        }
    }

    public boolean hasAnyVendedTokens() {
        boolean z;
        synchronized (this.mLock) {
            z = !this.mVendedTokenCauses.isEmpty();
        }
        return z;
    }

    public ImmutableSet<PauseCause> getVendedTokenCauses() {
        ImmutableSet<PauseCause> immutableSetCopyOf;
        synchronized (this.mLock) {
            immutableSetCopyOf = ImmutableSet.copyOf((Collection) this.mVendedTokenCauses.values());
        }
        return immutableSetCopyOf;
    }

    private static class PauseTokenGuard {
        private final Throwable mAllocationSite;
        private volatile boolean mWasReleased = false;

        public PauseTokenGuard(PauseToken pauseToken) {
            this.mAllocationSite = new RuntimeException(String.format(Locale.US, "PauseToken allocated here: %s", pauseToken));
        }

        public void release() {
            this.mWasReleased = true;
        }

        protected void finalize() throws Throwable {
            try {
                try {
                    if (!this.mWasReleased) {
                        Throwables2.propagateIfWeakMode(this.mAllocationSite);
                    }
                } catch (RuntimeException e) {
                    UIThreadUtils.reportException(e);
                }
            } finally {
                super.finalize();
            }
        }
    }
}
