package com.amazon.avod.media.ads.internal;

import com.amazon.avod.ads.api.AdBreakErrorCode;
import com.amazon.avod.ads.api.AdBreakInfo;
import com.amazon.avod.ads.api.AdInfo;
import com.amazon.avod.ads.api.AdInfoErrorCode;
import com.amazon.avod.ads.api.AdNetworkException;
import com.amazon.avod.ads.api.Duration;
import com.amazon.avod.util.DLog;
import java.net.URI;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes2.dex */
class AdErrorReporter {
    private final ExecutorService mExecutor;

    AdErrorReporter(ExecutorService executorService) {
        this.mExecutor = executorService;
    }

    void sendVmapError(final AdBreakInfo adBreakInfo, final AdBreakErrorCode adBreakErrorCode) {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdErrorReporter.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DLog.logf("Sending AdBreak error: %s", adBreakErrorCode);
                    adBreakInfo.sendError(adBreakErrorCode, null);
                } catch (AdNetworkException e) {
                    DLog.exceptionf(e, "Failure to send VMAP error of type %s", adBreakErrorCode);
                }
            }
        });
    }

    void sendError(AdInfo adInfo, AdInfoErrorCode adInfoErrorCode) {
        sendError(adInfo, adInfoErrorCode, null, null);
    }

    void sendError(final AdInfo adInfo, final AdInfoErrorCode adInfoErrorCode, final Duration duration, final URI uri) {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdErrorReporter.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (adInfo == null) {
                        DLog.warnf("AdInfo is null. Error: %s play head: %s uri: %s", adInfoErrorCode, duration, uri);
                    } else {
                        DLog.logf("Sending AdInfo error: %s play head: %s uri: %s", adInfoErrorCode, duration, uri);
                        adInfo.sendError(adInfoErrorCode, duration, uri);
                    }
                } catch (AdNetworkException e) {
                    DLog.exceptionf(e, "Failure to send VAST error of type: %s play head: %s uri: %s", adInfoErrorCode, duration, uri);
                }
            }
        });
    }
}
