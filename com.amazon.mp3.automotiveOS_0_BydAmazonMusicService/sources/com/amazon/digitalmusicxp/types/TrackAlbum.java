package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackAlbum.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001f\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\fR\u0017\u0010\u0015\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/amazon/digitalmusicxp/types/TrackAlbum;", "", "", "toString", "", "hashCode", "other", "", "equals", "artistName", "Ljava/lang/String;", "getArtistName", "()Ljava/lang/String;", "", "Lcom/amazon/digitalmusicxp/types/TrackArtist;", "artists", "Ljava/util/List;", "getArtists", "()Ljava/util/List;", "asin", "getAsin", "name", "getName", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class TrackAlbum {
    private final String artistName;
    private final List<TrackArtist> artists;
    private final String asin;
    private final String name;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrackAlbum)) {
            return false;
        }
        TrackAlbum trackAlbum = (TrackAlbum) other;
        return Intrinsics.areEqual(this.artistName, trackAlbum.artistName) && Intrinsics.areEqual(this.artists, trackAlbum.artists) && Intrinsics.areEqual(this.asin, trackAlbum.asin) && Intrinsics.areEqual(this.name, trackAlbum.name);
    }

    public int hashCode() {
        String str = this.artistName;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        List<TrackArtist> list = this.artists;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        String str2 = this.asin;
        return ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.name.hashCode();
    }

    public String toString() {
        return "TrackAlbum(artistName=" + this.artistName + ", artists=" + this.artists + ", asin=" + this.asin + ", name=" + this.name + ')';
    }

    public TrackAlbum(String str, List<TrackArtist> list, String str2, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.artistName = str;
        this.artists = list;
        this.asin = str2;
        this.name = name;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getAsin() {
        return this.asin;
    }

    public final String getName() {
        return this.name;
    }
}
