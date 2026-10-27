package com.android.wifitrackerlib;

import android.net.wifi.ScanResult;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes6.dex */
public class ScanResultUpdater {
    private final Clock mClock;
    private final Object mLock;
    private final long mMaxScanAgeMillis;
    private HashMap<String, ScanResult> mScanResultsByBssid;

    public ScanResultUpdater(Clock clock) {
        this(clock, LongCompanionObject.MAX_VALUE);
    }

    public ScanResultUpdater(Clock clock, long maxScanAgeMillis) {
        this.mScanResultsByBssid = new HashMap<>();
        this.mLock = new Object();
        this.mMaxScanAgeMillis = maxScanAgeMillis;
        this.mClock = clock;
    }

    public void update(List<ScanResult> newResults) {
        synchronized (this.mLock) {
            evictOldScans();
            for (ScanResult result : newResults) {
                ScanResult prevResult = this.mScanResultsByBssid.get(result.BSSID);
                if (prevResult == null || prevResult.timestamp < result.timestamp) {
                    this.mScanResultsByBssid.put(result.BSSID, result);
                }
            }
        }
    }

    public List<ScanResult> getScanResults() {
        return getScanResults(this.mMaxScanAgeMillis);
    }

    public List<ScanResult> getScanResults(long maxScanAgeMillis) throws IllegalArgumentException {
        List<ScanResult> ageFilteredResults;
        if (maxScanAgeMillis > this.mMaxScanAgeMillis) {
            throw new IllegalArgumentException("maxScanAgeMillis argument cannot be greater than mMaxScanAgeMillis!");
        }
        synchronized (this.mLock) {
            ageFilteredResults = new ArrayList<>();
            for (ScanResult result : this.mScanResultsByBssid.values()) {
                if (this.mClock.millis() - (result.timestamp / 1000) <= maxScanAgeMillis) {
                    ageFilteredResults.add(result);
                }
            }
        }
        return ageFilteredResults;
    }

    private void evictOldScans() {
        synchronized (this.mLock) {
            this.mScanResultsByBssid.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$ScanResultUpdater$0dM-eCJ48FAV9jeYCNlP_G5xHfw
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f$0.lambda$evictOldScans$0$ScanResultUpdater((Map.Entry) obj);
                }
            });
        }
    }

    public /* synthetic */ boolean lambda$evictOldScans$0$ScanResultUpdater(Map.Entry entry) {
        return this.mClock.millis() - (((ScanResult) entry.getValue()).timestamp / 1000) > this.mMaxScanAgeMillis;
    }
}
