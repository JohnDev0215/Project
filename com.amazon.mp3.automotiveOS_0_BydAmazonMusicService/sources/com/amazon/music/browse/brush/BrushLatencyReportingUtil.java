package com.amazon.music.browse.brush;

import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.google.android.gms.common.Scopes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: BrushLatencyReportingUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0007R\u001c\u0010\f\u001a\n \u000b*\u0004\u0018\u00010\n0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/browse/brush/BrushLatencyReportingUtil;", "", "", "uri", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "latencyLeg", "", "trackLatency", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageLatencyCode", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOG", "Lorg/slf4j/Logger;", "<init>", "()V", "DMMBrowse_release"}, k = 1, mv = {1, 6, 0})
public final class BrushLatencyReportingUtil {
    public static final BrushLatencyReportingUtil INSTANCE = new BrushLatencyReportingUtil();
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) BrushLatencyReportingUtil.class);

    private BrushLatencyReportingUtil() {
    }

    public void trackLatency(String uri, LatencyTrackingLeg latencyLeg) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(latencyLeg, "latencyLeg");
        PageLoadLatencyCode pageLatencyCode = getPageLatencyCode(uri);
        if (pageLatencyCode == null) {
            LOG.error("Error reporting latency leg: " + latencyLeg + " for pageUri: " + uri);
            return;
        }
        LOG.debug("reporting latency leg: " + latencyLeg + " for pageUri: " + uri + " with latency code: " + pageLatencyCode.getLatencyCode());
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(latencyLeg, pageLatencyCode));
    }

    @VisibleForTesting(otherwise = 4)
    public final PageLoadLatencyCode getPageLatencyCode(String uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (uri.equals("media-browser-home")) {
            return PageLoadLatencyCode.MEDIA_BROWSER_HOME;
        }
        if (uri.equals("media-browser-library")) {
            return PageLoadLatencyCode.MEDIA_BROWSER_LIBRARY;
        }
        if (uri.equals("carmode")) {
            return PageLoadLatencyCode.CAR_MODE;
        }
        if (uri.equals("carmode-home")) {
            return PageLoadLatencyCode.CAR_MODE_HOME;
        }
        if (uri.equals("carmode-detail")) {
            return PageLoadLatencyCode.CAR_MODE_DETAIL;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) NotificationCompat.CATEGORY_RECOMMENDATION, false, 2, (Object) null)) {
            return PageLoadLatencyCode.SEE_MORE;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "home", false, 2, (Object) null)) {
            return PageLoadLatencyCode.HOME;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "find", false, 2, (Object) null)) {
            return PageLoadLatencyCode.FIND;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "artist", false, 2, (Object) null)) {
            return PageLoadLatencyCode.ARTIST_DETAIL;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "album", false, 2, (Object) null)) {
            return PageLoadLatencyCode.ALBUM_DETAIL;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "playlists", false, 2, (Object) null)) {
            return PageLoadLatencyCode.PLAYLISTS;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) Scopes.PROFILE, false, 2, (Object) null)) {
            return PageLoadLatencyCode.PROFILE;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "videos", false, 2, (Object) null)) {
            return PageLoadLatencyCode.VIDEOS;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "uhd", false, 2, (Object) null)) {
            return PageLoadLatencyCode.UHD;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "3d", false, 2, (Object) null)) {
            return PageLoadLatencyCode.THREE_D;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) ParserUtil.GENRE_SEGMENT_NAME, false, 2, (Object) null)) {
            return PageLoadLatencyCode.GENRE;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "new", false, 2, (Object) null)) {
            return PageLoadLatencyCode.NEW_RELEASES;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "popular", false, 2, (Object) null)) {
            return PageLoadLatencyCode.CHARTS;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "ra360", false, 2, (Object) null)) {
            return PageLoadLatencyCode.RA360;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "atmos", false, 2, (Object) null)) {
            return PageLoadLatencyCode.ATMOS;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "album", false, 2, (Object) null)) {
            return PageLoadLatencyCode.ALBUM_DETAIL;
        }
        return null;
    }
}
