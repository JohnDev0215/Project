package com.amazon.music.skyfire.ui.providers.impl;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.ui.utils.SkyFireSkillPath;
import kotlin.Metadata;
import kotlin.text.StringsKt__StringsJVMKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: DefaultSkillLatencyTracker.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0002J\u0012\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u000e\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u000f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0010\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultSkillLatencyTracker;", "Lcom/amazon/music/skyfire/core/performance/SkillLatencyTracker;", "()V", "complete", "", "skillPath", "", "getPageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "matchPath", "", PageUriUtils.SOURCE_QUERY_PARAM, "target", "process", "rendering", "request", "response", "trackLatency", "pageLoadLatencyCode", "latencyLeg", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultSkillLatencyTracker implements SkillLatencyTracker {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSkillLatencyTracker.class.getSimpleName());

    @Override // com.amazon.music.skyfire.core.performance.SkillLatencyTracker
    public void request(String skillPath) {
        PageLoadLatencyCode pageLoadLatencyCode = getPageLoadLatencyCode(skillPath);
        if (pageLoadLatencyCode == null) {
            return;
        }
        trackLatency(pageLoadLatencyCode, LatencyTrackingLeg.REQUEST);
    }

    @Override // com.amazon.music.skyfire.core.performance.SkillLatencyTracker
    public void response(String skillPath) {
        PageLoadLatencyCode pageLoadLatencyCode = getPageLoadLatencyCode(skillPath);
        if (pageLoadLatencyCode == null) {
            return;
        }
        trackLatency(pageLoadLatencyCode, LatencyTrackingLeg.RESPONSE);
    }

    @Override // com.amazon.music.skyfire.core.performance.SkillLatencyTracker
    public void process(String skillPath) {
        PageLoadLatencyCode pageLoadLatencyCode = getPageLoadLatencyCode(skillPath);
        if (pageLoadLatencyCode == null) {
            return;
        }
        trackLatency(pageLoadLatencyCode, LatencyTrackingLeg.PROCESSING);
    }

    @Override // com.amazon.music.skyfire.core.performance.SkillLatencyTracker
    public void rendering(String skillPath) {
        PageLoadLatencyCode pageLoadLatencyCode = getPageLoadLatencyCode(skillPath);
        if (pageLoadLatencyCode == null) {
            return;
        }
        trackLatency(pageLoadLatencyCode, LatencyTrackingLeg.RENDERING);
    }

    @Override // com.amazon.music.skyfire.core.performance.SkillLatencyTracker
    public void complete(String skillPath) {
        PageLoadLatencyCode pageLoadLatencyCode = getPageLoadLatencyCode(skillPath);
        if (pageLoadLatencyCode == null) {
            return;
        }
        trackLatency(pageLoadLatencyCode, LatencyTrackingLeg.LOAD_COMPLETE);
    }

    private final PageLoadLatencyCode getPageLoadLatencyCode(String skillPath) {
        if (skillPath == null) {
            return null;
        }
        if (matchPath(skillPath, SkyFireSkillPath.showMerchDetail.getPath())) {
            return PageLoadLatencyCode.MERCH_DETAIL;
        }
        if (matchPath(skillPath, SkyFireSkillPath.showMerchShop.getPath())) {
            return PageLoadLatencyCode.MERCH_SHOP;
        }
        if (matchPath(skillPath, SkyFireSkillPath.showMerchAll.getPath())) {
            return PageLoadLatencyCode.MERCH_ALL;
        }
        if (matchPath(skillPath, SkyFireSkillPath.showLiveEventDetail.getPath())) {
            return PageLoadLatencyCode.LIVE_EVENT_DETAIL;
        }
        if (matchPath(skillPath, SkyFireSkillPath.showFindContent.getPath())) {
            return PageLoadLatencyCode.FIND_CONTENT;
        }
        if (matchPath(skillPath, SkyFireSkillPath.showFind.getPath())) {
            return PageLoadLatencyCode.FIND_V2;
        }
        if (matchPath(skillPath, SkyFireSkillPath.showReleasesDetail.getPath())) {
            return PageLoadLatencyCode.RELEASES_DETAIL;
        }
        if (matchPath(skillPath, SkyFireSkillPath.showCatalogArtist.getPath())) {
            return PageLoadLatencyCode.ARTIST_DETAIL_V2;
        }
        return null;
    }

    private final boolean matchPath(String source, String target) {
        return StringsKt__StringsJVMKt.startsWith$default(source, target, false, 2, null);
    }

    private final void trackLatency(PageLoadLatencyCode pageLoadLatencyCode, LatencyTrackingLeg latencyLeg) {
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(latencyLeg, pageLoadLatencyCode));
    }
}
