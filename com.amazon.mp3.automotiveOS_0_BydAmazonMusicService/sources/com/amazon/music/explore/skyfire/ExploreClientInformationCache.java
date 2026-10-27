package com.amazon.music.explore.skyfire;

import CoreInterface.v1_0.ClientInformation;
import Touch.SwipeablePagesTemplateInterface.v1_0.ImmutableSwipeablePagesClientInformation;
import Touch.SwipeablePagesTemplateInterface.v1_0.ImmutableSwipeableSectionMetricClientInformation;
import Touch.SwipeablePagesTemplateInterface.v1_0.ImpressionEndReason;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesClientInformation;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeableSectionMetricClientInformation;
import com.amazon.music.skyfire.core.network.ClientInformationCache;

/* JADX INFO: loaded from: classes4.dex */
public enum ExploreClientInformationCache implements ClientInformationCache {
    INSTANCE;

    private static final String SwipeablePagesClientInformation = SwipeablePagesClientInformation.class.getCanonicalName();
    private static final String SwipeableSectionMetricClientInformation = SwipeableSectionMetricClientInformation.class.getCanonicalName();
    public ImpressionEndReason impressionEndReason;
    public String impressionEndTimestamp;
    public String impressionTimestamp;
    public boolean isChartsV3Enabled;
    public boolean isXrayStageEnabled;

    public void clearPlaybackInformation() {
    }

    public void setPlaybackErrorMessage(String str) {
    }

    public void setResumedAtTimeStamp(long j) {
    }

    public void setTrackProgress(int i) {
    }

    @Override // com.amazon.music.skyfire.core.network.ClientInformationCache
    public String queryKey(ClientInformation clientInformation) {
        if (clientInformation instanceof ImmutableSwipeablePagesClientInformation) {
            return "swipeablePageConfig";
        }
        if (clientInformation instanceof ImmutableSwipeableSectionMetricClientInformation) {
            return "swipeableSectionMetric";
        }
        return null;
    }

    @Override // com.amazon.music.skyfire.core.network.ClientInformationCache
    public ClientInformation get(String str) {
        if (str.equals(SwipeablePagesClientInformation)) {
            return ImmutableSwipeablePagesClientInformation.builder().isStageEnabled(this.isXrayStageEnabled).isChartsV3Enabled(this.isChartsV3Enabled).build();
        }
        if (str.equals(SwipeableSectionMetricClientInformation)) {
            return ImmutableSwipeableSectionMetricClientInformation.builder().impressionTimestamp(this.impressionTimestamp).impressionEndTimestamp(this.impressionEndTimestamp).impressionEndReason(this.impressionEndReason).build();
        }
        return null;
    }
}
