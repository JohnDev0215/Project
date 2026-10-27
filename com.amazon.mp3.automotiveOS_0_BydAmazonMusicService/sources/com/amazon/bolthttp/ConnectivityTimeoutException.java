package com.amazon.bolthttp;

import android.net.NetworkInfo;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ConnectivityTimeoutException extends IOException {
    private final NetworkInfo.DetailedState mLastSeenState;

    public ConnectivityTimeoutException(@Nullable NetworkInfo.DetailedState detailedState) {
        this.mLastSeenState = detailedState;
    }

    @Nonnull
    public String toReportableString() {
        if (this.mLastSeenState == null) {
            return getClass().getSimpleName();
        }
        return getClass().getSimpleName() + "-" + this.mLastSeenState.toString();
    }
}
