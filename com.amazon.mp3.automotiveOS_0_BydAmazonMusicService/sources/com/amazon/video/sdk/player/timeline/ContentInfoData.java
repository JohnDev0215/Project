package com.amazon.video.sdk.player.timeline;

import com.amazon.video.sdk.player.timeline.metadata.ContentMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentInfo.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/ContentInfoData;", "Lcom/amazon/video/sdk/player/timeline/ContentInfo;", "", "toString", "", "hashCode", "", "other", "", "equals", "titleId", "Ljava/lang/String;", "getTitleId", "()Ljava/lang/String;", "Lcom/amazon/video/sdk/player/timeline/ContentType;", "contentType", "Lcom/amazon/video/sdk/player/timeline/ContentType;", "getContentType", "()Lcom/amazon/video/sdk/player/timeline/ContentType;", "Lcom/amazon/video/sdk/player/timeline/metadata/ContentMetadata;", "metadata", "Lcom/amazon/video/sdk/player/timeline/metadata/ContentMetadata;", "getMetadata", "()Lcom/amazon/video/sdk/player/timeline/metadata/ContentMetadata;", "<init>", "(Ljava/lang/String;Lcom/amazon/video/sdk/player/timeline/ContentType;Lcom/amazon/video/sdk/player/timeline/metadata/ContentMetadata;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ContentInfoData implements ContentInfo {
    private final ContentType contentType;
    private final ContentMetadata metadata;
    private final String titleId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContentInfoData)) {
            return false;
        }
        ContentInfoData contentInfoData = (ContentInfoData) other;
        return Intrinsics.areEqual(getTitleId(), contentInfoData.getTitleId()) && getContentType() == contentInfoData.getContentType() && Intrinsics.areEqual(getMetadata(), contentInfoData.getMetadata());
    }

    public int hashCode() {
        return (((getTitleId().hashCode() * 31) + getContentType().hashCode()) * 31) + (getMetadata() == null ? 0 : getMetadata().hashCode());
    }

    public String toString() {
        return "ContentInfoData(titleId=" + getTitleId() + ", contentType=" + getContentType() + ", metadata=" + getMetadata() + ')';
    }

    public ContentInfoData(String titleId, ContentType contentType, ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(titleId, "titleId");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        this.titleId = titleId;
        this.contentType = contentType;
        this.metadata = contentMetadata;
    }

    public /* synthetic */ ContentInfoData(String str, ContentType contentType, ContentMetadata contentMetadata, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, contentType, (i & 4) != 0 ? null : contentMetadata);
    }

    @Override // com.amazon.video.sdk.player.timeline.ContentInfo
    public String getTitleId() {
        return this.titleId;
    }

    @Override // com.amazon.video.sdk.player.timeline.ContentInfo
    public ContentType getContentType() {
        return this.contentType;
    }

    @Override // com.amazon.video.sdk.player.timeline.ContentInfo
    public ContentMetadata getMetadata() {
        return this.metadata;
    }
}
