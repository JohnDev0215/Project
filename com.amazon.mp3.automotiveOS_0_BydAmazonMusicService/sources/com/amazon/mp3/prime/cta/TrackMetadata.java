package com.amazon.mp3.prime.cta;

import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0004\b\u0011\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R*\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/prime/cta/TrackMetadata;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/contentEncoding/ContentEncoding;", ContextMappingConstants.CONTENT_ENCODING, "Ljava/util/List;", "getContentEncoding", "()Ljava/util/List;", "setContentEncoding", "(Ljava/util/List;)V", "<init>", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TrackMetadata {
    private List<? extends ContentEncoding> contentEncoding;

    public TrackMetadata() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TrackMetadata) && Intrinsics.areEqual(this.contentEncoding, ((TrackMetadata) other).contentEncoding);
    }

    public int hashCode() {
        List<? extends ContentEncoding> list = this.contentEncoding;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public String toString() {
        return "TrackMetadata(contentEncoding=" + this.contentEncoding + ')';
    }

    public TrackMetadata(List<? extends ContentEncoding> list) {
        this.contentEncoding = list;
    }

    public /* synthetic */ TrackMetadata(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public final List<ContentEncoding> getContentEncoding() {
        return this.contentEncoding;
    }
}
