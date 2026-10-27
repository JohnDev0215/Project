package com.amazon.video.sdk.player.timeline.metadata;

import com.google.common.collect.Lists;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentMetadata.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u000b\b\u0080\b\u0018\u00002\u00020\u0001BO\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0002\u0012\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001a\u0012\b\b\u0002\u0010\u001f\u001a\u00020\r¢\u0006\u0004\b#\u0010$J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001a\u0010\t\u001a\u00020\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"¨\u0006%"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/metadata/AdMetadataData;", "", "", "toString", "", "hashCode", "other", "", "equals", "showCountdownTimer", "Z", "getShowCountdownTimer", "()Z", "", "skipOffset", "Ljava/lang/Long;", "getSkipOffset", "()Ljava/lang/Long;", "showSkipCountdownTimer", "Ljava/lang/Boolean;", "getShowSkipCountdownTimer", "()Ljava/lang/Boolean;", "clickThroughUri", "Ljava/lang/String;", "getClickThroughUri", "()Ljava/lang/String;", "", "extensions", "Ljava/util/List;", "getExtensions", "()Ljava/util/List;", "offsetInMs", "J", "getOffsetInMs", "()J", "<init>", "(ZLjava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;J)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AdMetadataData implements ContentMetadata {
    private final String clickThroughUri;
    private final List<Object> extensions;
    private final long offsetInMs;
    private final boolean showCountdownTimer;
    private final Boolean showSkipCountdownTimer;
    private final Long skipOffset;

    public AdMetadataData() {
        this(false, null, null, null, null, 0L, 63, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdMetadataData)) {
            return false;
        }
        AdMetadataData adMetadataData = (AdMetadataData) other;
        return getShowCountdownTimer() == adMetadataData.getShowCountdownTimer() && Intrinsics.areEqual(getSkipOffset(), adMetadataData.getSkipOffset()) && Intrinsics.areEqual(getShowSkipCountdownTimer(), adMetadataData.getShowSkipCountdownTimer()) && Intrinsics.areEqual(getClickThroughUri(), adMetadataData.getClickThroughUri()) && Intrinsics.areEqual(getExtensions(), adMetadataData.getExtensions()) && this.offsetInMs == adMetadataData.offsetInMs;
    }

    public int hashCode() {
        boolean showCountdownTimer = getShowCountdownTimer();
        int i = showCountdownTimer;
        if (showCountdownTimer) {
            i = 1;
        }
        return (((((((((i * 31) + (getSkipOffset() == null ? 0 : getSkipOffset().hashCode())) * 31) + (getShowSkipCountdownTimer() == null ? 0 : getShowSkipCountdownTimer().hashCode())) * 31) + (getClickThroughUri() != null ? getClickThroughUri().hashCode() : 0)) * 31) + getExtensions().hashCode()) * 31) + Long.hashCode(this.offsetInMs);
    }

    public String toString() {
        return "AdMetadataData(showCountdownTimer=" + getShowCountdownTimer() + ", skipOffset=" + getSkipOffset() + ", showSkipCountdownTimer=" + getShowSkipCountdownTimer() + ", clickThroughUri=" + ((Object) getClickThroughUri()) + ", extensions=" + getExtensions() + ", offsetInMs=" + this.offsetInMs + ')';
    }

    public AdMetadataData(boolean z, Long l, Boolean bool, String str, List<Object> extensions, long j) {
        Intrinsics.checkNotNullParameter(extensions, "extensions");
        this.showCountdownTimer = z;
        this.skipOffset = l;
        this.showSkipCountdownTimer = bool;
        this.clickThroughUri = str;
        this.extensions = extensions;
        this.offsetInMs = j;
    }

    public boolean getShowCountdownTimer() {
        return this.showCountdownTimer;
    }

    public Long getSkipOffset() {
        return this.skipOffset;
    }

    public Boolean getShowSkipCountdownTimer() {
        return this.showSkipCountdownTimer;
    }

    public String getClickThroughUri() {
        return this.clickThroughUri;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ AdMetadataData(boolean z, Long l, Boolean bool, String str, List list, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        z = (i & 1) != 0 ? true : z;
        Long l2 = (i & 2) != 0 ? null : l;
        Boolean bool2 = (i & 4) != 0 ? null : bool;
        String str2 = (i & 8) == 0 ? str : null;
        if ((i & 16) != 0) {
            list = Lists.newArrayList();
            Intrinsics.checkNotNullExpressionValue(list, "newArrayList()");
        }
        this(z, l2, bool2, str2, list, (i & 32) != 0 ? 0L : j);
    }

    public List<Object> getExtensions() {
        return this.extensions;
    }

    public final long getOffsetInMs() {
        return this.offsetInMs;
    }
}
