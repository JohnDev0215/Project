package com.amazon.mp3.prime.cta;

import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u0012\u0016\b\u0002\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f¢\u0006\u0004\b&\u0010'J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR*\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R0\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010 \u001a\u0004\u0018\u00010\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006("}, d2 = {"Lcom/amazon/mp3/prime/cta/AlbumMetadata;", "", "", "toString", "", "hashCode", "other", "", "equals", "copyright", "Ljava/lang/String;", "getCopyright", "()Ljava/lang/String;", "setCopyright", "(Ljava/lang/String;)V", "", "Lcom/amazon/music/contentEncoding/ContentEncoding;", ContextMappingConstants.CONTENT_ENCODING, "Ljava/util/List;", "getContentEncoding", "()Ljava/util/List;", "setContentEncoding", "(Ljava/util/List;)V", "", "Lcom/amazon/mp3/prime/cta/TrackMetadata;", "tracksMetadata", "Ljava/util/Map;", "getTracksMetadata", "()Ljava/util/Map;", "setTracksMetadata", "(Ljava/util/Map;)V", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "setPlaymodeEligibility", "(Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/Map;Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AlbumMetadata {
    private List<? extends ContentEncoding> contentEncoding;
    private String copyright;
    private PlaymodeEligibility playmodeEligibility;
    private Map<String, TrackMetadata> tracksMetadata;

    public AlbumMetadata() {
        this(null, null, null, null, 15, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AlbumMetadata)) {
            return false;
        }
        AlbumMetadata albumMetadata = (AlbumMetadata) other;
        return Intrinsics.areEqual(this.copyright, albumMetadata.copyright) && Intrinsics.areEqual(this.contentEncoding, albumMetadata.contentEncoding) && Intrinsics.areEqual(this.tracksMetadata, albumMetadata.tracksMetadata) && Intrinsics.areEqual(this.playmodeEligibility, albumMetadata.playmodeEligibility);
    }

    public int hashCode() {
        String str = this.copyright;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        List<? extends ContentEncoding> list = this.contentEncoding;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        Map<String, TrackMetadata> map = this.tracksMetadata;
        int iHashCode3 = (iHashCode2 + (map == null ? 0 : map.hashCode())) * 31;
        PlaymodeEligibility playmodeEligibility = this.playmodeEligibility;
        return iHashCode3 + (playmodeEligibility != null ? playmodeEligibility.hashCode() : 0);
    }

    public String toString() {
        return "AlbumMetadata(copyright=" + ((Object) this.copyright) + ", contentEncoding=" + this.contentEncoding + ", tracksMetadata=" + this.tracksMetadata + ", playmodeEligibility=" + this.playmodeEligibility + ')';
    }

    public AlbumMetadata(String str, List<? extends ContentEncoding> list, Map<String, TrackMetadata> map, PlaymodeEligibility playmodeEligibility) {
        this.copyright = str;
        this.contentEncoding = list;
        this.tracksMetadata = map;
        this.playmodeEligibility = playmodeEligibility;
    }

    public /* synthetic */ AlbumMetadata(String str, List list, Map map, PlaymodeEligibility playmodeEligibility, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : list, (i & 4) != 0 ? null : map, (i & 8) != 0 ? null : playmodeEligibility);
    }

    public final String getCopyright() {
        return this.copyright;
    }

    public final void setCopyright(String str) {
        this.copyright = str;
    }

    public final List<ContentEncoding> getContentEncoding() {
        return this.contentEncoding;
    }

    public final void setContentEncoding(List<? extends ContentEncoding> list) {
        this.contentEncoding = list;
    }

    public final Map<String, TrackMetadata> getTracksMetadata() {
        return this.tracksMetadata;
    }

    public final PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }
}
