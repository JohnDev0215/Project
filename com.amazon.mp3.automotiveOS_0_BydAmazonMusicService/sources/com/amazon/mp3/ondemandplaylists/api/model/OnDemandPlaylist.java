package com.amazon.mp3.ondemandplaylists.api.model;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GetOnDemandPlaylistsResponse.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001c\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/ondemandplaylists/api/model/OnDemandPlaylist;", "", "", "toString", "", "hashCode", "other", "", "equals", "asin", "Ljava/lang/String;", "getAsin", "()Ljava/lang/String;", "seriesAsin", "getSeriesAsin", "Lcom/amazon/mp3/ondemandplaylists/api/model/RecSource;", "recSource", "Lcom/amazon/mp3/ondemandplaylists/api/model/RecSource;", "getRecSource", "()Lcom/amazon/mp3/ondemandplaylists/api/model/RecSource;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class OnDemandPlaylist {

    @SerializedName("asin")
    private final String asin;

    @SerializedName("recSource")
    private final RecSource recSource;

    @SerializedName("seriesAsin")
    private final String seriesAsin;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OnDemandPlaylist)) {
            return false;
        }
        OnDemandPlaylist onDemandPlaylist = (OnDemandPlaylist) other;
        return Intrinsics.areEqual(this.asin, onDemandPlaylist.asin) && Intrinsics.areEqual(this.seriesAsin, onDemandPlaylist.seriesAsin) && this.recSource == onDemandPlaylist.recSource;
    }

    public int hashCode() {
        String str = this.asin;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.seriesAsin;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        RecSource recSource = this.recSource;
        return iHashCode2 + (recSource != null ? recSource.hashCode() : 0);
    }

    public String toString() {
        return "OnDemandPlaylist(asin=" + ((Object) this.asin) + ", seriesAsin=" + ((Object) this.seriesAsin) + ", recSource=" + this.recSource + ')';
    }

    public final String getAsin() {
        return this.asin;
    }
}
