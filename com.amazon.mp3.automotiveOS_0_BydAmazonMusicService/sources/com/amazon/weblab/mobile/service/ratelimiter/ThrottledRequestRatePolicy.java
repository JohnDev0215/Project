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
class ThrottledRequestRatePolicy implements RequestRatePolicy {
    private final int mCoolDownPeriodInMinutes;

    @Override // com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy
    public int historySizeRequired() {
        return 1;
    }

    ThrottledRequestRatePolicy() {
        this(getCoolDownPeriodInMinutes());
    }

    ThrottledRequestRatePolicy(int i) {
        this.mCoolDownPeriodInMinutes = i;
    }

    @Override // com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy
    public boolean allowRequest(SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection, RequestHistory requestHistory) {
        List<RequestEntry> historyEntries = requestHistory.getHistoryEntries();
        RequestEntry requestEntry = historyEntries.get(historyEntries.size() - 1);
        return requestEntry.getServiceCallStatus() != ServiceCallStatus.THROTTLED || requestEntry.getElapsedRealtime() + TimeUnit.MINUTES.toMillis((long) this.mCoolDownPeriodInMinutes) < SystemClock.elapsedRealtime();
    }

    @Override // com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy
    public boolean isPolicyApplicable(RequestHistory requestHistory) {
        List<RequestEntry> historyEntries = requestHistory.getHistoryEntries();
        return historyEntries.size() >= 1 && historyEntries.get(historyEntries.size() - 1).getServiceCallStatus() == ServiceCallStatus.THROTTLED;
    }

    private static int getCoolDownPeriodInMinutes() {
        return new Random().nextInt(60) + 60;
    }
}
