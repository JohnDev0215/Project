package com.amazon.video.sdk.stream;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStream.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0080\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\n\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u0004\u0018\u00010\u000f8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/amazon/video/sdk/stream/VideoStreamData;", "Lcom/amazon/video/sdk/stream/VideoStream;", "", "toString", "", "hashCode", "", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "", "Lcom/amazon/video/sdk/stream/VideoVariant;", "variants", "Ljava/util/List;", "getVariants", "()Ljava/util/List;", "setVariants", "(Ljava/util/List;)V", "activeVariant", "Lcom/amazon/video/sdk/stream/VideoVariant;", "getActiveVariant", "()Lcom/amazon/video/sdk/stream/VideoVariant;", "setActiveVariant", "(Lcom/amazon/video/sdk/stream/VideoVariant;)V", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/amazon/video/sdk/stream/VideoVariant;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class VideoStreamData implements VideoStream {
    private VideoVariant activeVariant;
    private final String id;
    private List<? extends VideoVariant> variants;

    public VideoStreamData() {
        this(null, null, null, 7, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VideoStreamData)) {
            return false;
        }
        VideoStreamData videoStreamData = (VideoStreamData) other;
        return Intrinsics.areEqual(getId(), videoStreamData.getId()) && Intrinsics.areEqual(getVariants(), videoStreamData.getVariants()) && getActiveVariant() == videoStreamData.getActiveVariant();
    }

    public int hashCode() {
        return (((getId().hashCode() * 31) + getVariants().hashCode()) * 31) + (getActiveVariant() == null ? 0 : getActiveVariant().hashCode());
    }

    public String toString() {
        return "VideoStreamData(id=" + getId() + ", variants=" + getVariants() + ", activeVariant=" + getActiveVariant() + ')';
    }

    public VideoStreamData(String id, List<? extends VideoVariant> variants, VideoVariant videoVariant) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(variants, "variants");
        this.id = id;
        this.variants = variants;
        this.activeVariant = videoVariant;
    }

    @Override // com.amazon.video.sdk.stream.Stream
    public String getId() {
        return this.id;
    }

    public /* synthetic */ VideoStreamData(String str, List list, VideoVariant videoVariant, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? new ArrayList() : list, (i & 4) != 0 ? null : videoVariant);
    }

    public List<VideoVariant> getVariants() {
        return this.variants;
    }

    public void setVariants(List<? extends VideoVariant> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.variants = list;
    }

    @Override // com.amazon.video.sdk.stream.Stream
    public VideoVariant getActiveVariant() {
        return this.activeVariant;
    }

    public void setActiveVariant(VideoVariant videoVariant) {
        this.activeVariant = videoVariant;
    }
}
