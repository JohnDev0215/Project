package com.amazon.avod.playback.sye;

import android.annotation.SuppressLint;
import com.amazon.atvplaybackresource.SyeCdn;
import com.amazon.atvplaybackresource.SyeUrlResponse;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.config.PlaybackDebugOverride;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SyeConfig;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.SyeDomain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyeDomainVendingMachine.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0017\b\u0002\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0004\b\u000f\u0010\u0010J\u0006\u0010\u0003\u001a\u00020\u0002J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/amazon/avod/playback/sye/SyeDomainVendingMachine;", "", "Lcom/amazon/avod/playback/sye/SyeDomainHolder;", "currentSyeDomain", "nextSyeDomain", "", "Lcom/netinsight/sye/syeClient/SyeDomain;", "allDomains", "syeDomains", "Ljava/util/List;", "Ljava/util/concurrent/atomic/AtomicInteger;", "index", "Ljava/util/concurrent/atomic/AtomicInteger;", "getIndex", "()Ljava/util/concurrent/atomic/AtomicInteger;", "<init>", "(Ljava/util/List;)V", "Companion", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0})
public class SyeDomainVendingMachine {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
    private static final PlaybackDebugOverride debugOverride = PlaybackDebugOverride.getInstance();
    private final AtomicInteger index;
    private final List<SyeDomainHolder> syeDomains;

    public /* synthetic */ SyeDomainVendingMachine(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(list);
    }

    private SyeDomainVendingMachine(List<SyeDomainHolder> list) {
        this.syeDomains = list;
        this.index = new AtomicInteger(0);
    }

    public final SyeDomainHolder currentSyeDomain() {
        return this.syeDomains.get(this.index.get());
    }

    public final SyeDomainHolder nextSyeDomain() {
        if (this.index.get() + 1 < this.syeDomains.size()) {
            return this.syeDomains.get(this.index.incrementAndGet());
        }
        return null;
    }

    public final List<SyeDomain> allDomains() {
        List<SyeDomainHolder> list = this.syeDomains;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((SyeDomainHolder) it.next()).getSyeDomain());
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: SyeDomainVendingMachine.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/avod/playback/sye/SyeDomainVendingMachine$Companion;", "", "()V", "DEFAULT_START_TIME", "", "INVALID_DVR_DURATION", "", "dateFormat", "Ljava/text/SimpleDateFormat;", "debugOverride", "Lcom/amazon/avod/playback/config/PlaybackDebugOverride;", "kotlin.jvm.PlatformType", "fromSpec", "Lcom/amazon/avod/playback/sye/SyeDomainVendingMachine;", "videoSpecification", "Lcom/amazon/avod/media/playback/VideoSpecification;", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @SuppressLint({"NewApi"})
        public final SyeDomainVendingMachine fromSpec(VideoSpecification videoSpecification) {
            long liveLookBackMillis;
            Intrinsics.checkNotNullParameter(videoSpecification, "videoSpecification");
            SyeDomainVendingMachine.dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            SyeUrlResponse syeResponse = videoSpecification.getSyeResponse();
            Intrinsics.checkNotNull(syeResponse);
            Intrinsics.checkNotNullExpressionValue(syeResponse, "videoSpecification.syeResponse!!");
            List<SyeCdn> syeUrls = videoSpecification.getSyeUrls();
            Intrinsics.checkNotNullExpressionValue(syeUrls, "videoSpecification.syeUrls");
            int i = 0;
            Preconditions.checkArgument(!syeUrls.isEmpty(), "Must be at least one Sye CDN", new Object[0]);
            if (SyeDomainVendingMachine.debugOverride.getLiveEventLookBackDurationMillis() > 0) {
                liveLookBackMillis = SyeDomainVendingMachine.debugOverride.getLiveEventLookBackDurationMillis();
            } else {
                liveLookBackMillis = videoSpecification.getLiveLookBackMillis();
            }
            long time = -1;
            if (syeResponse.startTime != null) {
                try {
                    Date date = SyeDomainVendingMachine.dateFormat.parse(syeResponse.startTime);
                    Intrinsics.checkNotNull(date);
                    time = date.getTime();
                } catch (ParseException unused) {
                    DLog.warnf("Failed to parse start time: %s, falling back to default");
                }
            }
            ArrayList<SyeDomainHolder> arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(syeUrls, 10));
            for (SyeCdn syeCdn : syeUrls) {
                Preconditions.checkNotNull(syeCdn.frontendUrl, "frontendUrl", new Object[i]);
                Preconditions.checkNotNull(syeCdn.token, "cdn", new Object[i]);
                Preconditions.checkNotNull(syeCdn.cdnName, "cdnName", new Object[i]);
                Preconditions.checkNotNull(syeCdn.channelId, "channelId", new Object[i]);
                Preconditions.checkNotNull(syeCdn.urlSetId, "urlSetId", new Object[i]);
                SyeConfig syeConfig = SyeConfig.getInstance();
                boolean useStaticCloudFrontUrl = syeConfig.getUseStaticCloudFrontUrl();
                String staticCloudFrontUrl = useStaticCloudFrontUrl ? syeConfig.getStaticCloudFrontUrl() : syeCdn.frontendUrl;
                Intrinsics.checkNotNullExpressionValue(staticCloudFrontUrl, "if (useCloudFrontUrl) co…ntUrl else it.frontendUrl");
                String str = syeCdn.cdnName;
                Intrinsics.checkNotNullExpressionValue(str, "it.cdnName");
                SyeDomain syeDomain = new SyeDomain(staticCloudFrontUrl, str);
                String str2 = syeCdn.token;
                Intrinsics.checkNotNullExpressionValue(str2, "it.token");
                String staticCloudFrontChannelId = useStaticCloudFrontUrl ? syeConfig.getStaticCloudFrontChannelId() : syeCdn.channelId;
                Intrinsics.checkNotNullExpressionValue(staticCloudFrontChannelId, "if (useCloudFrontUrl) co…annelId else it.channelId");
                String str3 = syeCdn.urlSetId;
                Intrinsics.checkNotNullExpressionValue(str3, "it.urlSetId");
                TimeUnit timeUnit = TimeUnit.SECONDS;
                Long l = syeResponse.duration;
                Intrinsics.checkNotNullExpressionValue(l, "syeResponse.duration");
                arrayList.add(new SyeDomainHolder(syeDomain, str2, staticCloudFrontChannelId, str3, (int) liveLookBackMillis, time, timeUnit.toMillis(l.longValue())));
                liveLookBackMillis = liveLookBackMillis;
                i = 0;
            }
            for (SyeDomainHolder syeDomainHolder : arrayList) {
                DLog.logf("SyeDomainVendingMachine initializing with %s, %s", syeDomainHolder.getSyeDomain().getBaseUrl(), syeDomainHolder.getSyeDomain().getName());
            }
            return new SyeDomainVendingMachine(arrayList, null);
        }
    }
}
