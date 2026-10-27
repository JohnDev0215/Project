package com.amazon.avod.media.ads.internal;

import android.net.Uri;
import com.amazon.avod.ads.api.AdNetworkException;
import com.amazon.avod.ads.api.Beacon;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.http.BoltHttpAdClient;
import com.amazon.avod.ads.http.DefaultAdHttpClient;
import com.amazon.avod.ads.http.HttpParameters;
import com.amazon.avod.http.HttpRequestBuilder;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.util.DLog;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdReportingUtils {
    public static AdReportingUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final AdReportingUtils INSTANCE = new AdReportingUtils();

        private SingletonHolder() {
        }
    }

    private AdReportingUtils() {
    }

    public void pingBeacon(@Nonnull final Beacon beacon, @Nonnull ExecutorService executorService) {
        Preconditions.checkNotNull(beacon, "beacon");
        Preconditions.checkNotNull(executorService, "executorService");
        executorService.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdReportingUtils$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AdReportingUtils.lambda$pingBeacon$0(beacon);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$pingBeacon$0(Beacon beacon) {
        try {
            DLog.devf("LiveAdTrackingManager: submitting beacon request, type: %s", beacon.getEventType());
            beacon.pingEvent();
            QALog.newQALog(PlaybackQAEvent.AUDIT_PING_SENT).addMetric(PlaybackQAMetric.AUDIT_PING_URL, beacon.getEvent().getUri()).send();
        } catch (AdNetworkException unused) {
            String.format(Locale.US, "Failure to send audit beacon for %s", beacon.getEvent().getUri());
            QALog.newQALog(PlaybackQAEvent.AUDIT_PING_ERROR).addMetric(PlaybackQAMetric.AUDIT_PING_URL, beacon.getEvent().getUri()).send();
        }
    }

    @Nullable
    public String constructNielsenAuditPingUrl(@Nonnull AdvertisingIdCollector.AdvertisingInfo advertisingInfo, @Nonnull String str, @Nonnull String str2, @Nonnull String str3, long j, int i, int i2, int i3) {
        String adId = advertisingInfo.getAdId();
        Boolean boolValueOf = Boolean.valueOf(advertisingInfo.isOptOut());
        if (boolValueOf != null && boolValueOf.booleanValue()) {
            adId = "";
            i2 = i3;
        }
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        HashFunction hashFunctionSha256 = Hashing.sha256();
        Charset charset = Charsets.UTF_8;
        return builderBuildUpon.appendQueryParameter("streamid", hashFunctionSha256.hashString(str3, charset).toString()).appendQueryParameter("sessionid", Hashing.sha256().hashString(str2, charset).toString()).appendQueryParameter("pingtype", String.valueOf(i)).appendQueryParameter("uoo", String.valueOf(i2)).appendQueryParameter("createtm", String.valueOf(j)).appendQueryParameter("devid", adId).build().toString();
    }

    @Nonnull
    public AdHttpClient createAdHttpClient(@Nonnull AdsConfig adsConfig) {
        String property = System.getProperty("http.agent");
        if (Strings.isNullOrEmpty(property)) {
            property = adsConfig.getUserAgent();
        }
        String strSanitizeUserAgent = HttpRequestBuilder.sanitizeUserAgent(property);
        HttpParameters httpParametersBuild = new HttpParameters.Builder().followRedirects(true).withBeaconRetryCount(adsConfig.getBeaconRetries()).withBeaconTimeout((int) adsConfig.getLiveBeaconTimeout().getTotalMilliseconds()).withRequestRetryCount(adsConfig.getRequestRetries()).withRequestTimeout((int) adsConfig.getRequestTimeout().getTotalMilliseconds()).withUserAgent(strSanitizeUserAgent).build();
        DLog.devf("userAgent for Ads Beacon = %s", strSanitizeUserAgent);
        return adsConfig.isBoltHttpClientEnabledForAds() ? new BoltHttpAdClient(httpParametersBuild) : new DefaultAdHttpClient(httpParametersBuild, new PersistentAndroidCookieStore());
    }
}
