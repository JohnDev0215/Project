package com.amazon.weblab.mobile.service.ratelimiter;

import android.os.SystemClock;
import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.service.ServiceCallStatus;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
class ExponentialBackoffRequestRatePolicy implements RequestRatePolicy {
    private int mJitterTimeSeconds;
    private int mWaitPeriodSeconds;

    @Override // com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy
    public int historySizeRequired() {
        return 1;
    }

    ExponentialBackoffRequestRatePolicy() {
        this(getJitterTimeInSeconds());
    }

    ExponentialBackoffRequestRatePolicy(int i) {
        this.mJitterTimeSeconds = i;
        this.mWaitPeriodSeconds = 2;
    }

    @Override // com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy
    public boolean allowRequest(SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection, RequestHistory requestHistory) {
        List<RequestEntry> historyEntries = requestHistory.getHistoryEntries();
        RequestEntry requestEntry = historyEntries.get(historyEntries.size() - 1);
        long jElapsedRealtime = SystemClock.elapsedRealtime() - requestEntry.getElapsedRealtime();
        if (requestEntry.getServiceCallStatus() == ServiceCallStatus.INTERNAL_SERVER_ERROR && jElapsedRealtime <= TimeUnit.SECONDS.toMillis(this.mWaitPeriodSeconds + this.mJitterTimeSeconds)) {
            return false;
        }
        this.mWaitPeriodSeconds = Math.min(this.mWaitPeriodSeconds * 2, 3600);
        return true;
    }

    @Override // com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy
    public boolean isPolicyApplicable(RequestHistory requestHistory) {
        List<RequestEntry> historyEntries = requestHistory.getHistoryEntries();
        if (historyEntries != null && historyEntries.size() >= 1) {
            z = historyEntries.get(historyEntries.size() - 1).getServiceCallStatus() == ServiceCallStatus.INTERNAL_SERVER_ERROR;
            if (!z) {
                this.mWaitPeriodSeconds = 2;
            }
        }
        return z;
    }

    private static int getJitterTimeInSeconds() {
        return new Random().nextInt(60);
    }
}
