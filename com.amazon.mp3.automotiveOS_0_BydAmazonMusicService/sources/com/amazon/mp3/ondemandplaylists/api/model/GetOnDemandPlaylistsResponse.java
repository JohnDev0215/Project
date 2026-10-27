package com.amazon.mp3.ondemandplaylists.api.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GetOnDemandPlaylistsResponse.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/ondemandplaylists/api/model/GetOnDemandPlaylistsResponse;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/mp3/ondemandplaylists/api/model/OnDemandPlaylist;", "onDemandPlaylists", "Ljava/util/List;", "getOnDemandPlaylists", "()Ljava/util/List;", "lastUpdatedTime", "Ljava/lang/String;", "getLastUpdatedTime", "()Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class GetOnDemandPlaylistsResponse {

    @SerializedName("lastUpdatedTime")
    private final String lastUpdatedTime;

    @SerializedName("onDemandPlaylists")
    private final List<OnDemandPlaylist> onDemandPlaylists;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GetOnDemandPlaylistsResponse)) {
            return false;
        }
        GetOnDemandPlaylistsResponse getOnDemandPlaylistsResponse = (GetOnDemandPlaylistsResponse) other;
        return Intrinsics.areEqual(this.onDemandPlaylists, getOnDemandPlaylistsResponse.onDemandPlaylists) && Intrinsics.areEqual(this.lastUpdatedTime, getOnDemandPlaylistsResponse.lastUpdatedTime);
    }

    public int hashCode() {
        List<OnDemandPlaylist> list = this.onDemandPlaylists;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        String str = this.lastUpdatedTime;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "GetOnDemandPlaylistsResponse(onDemandPlaylists=" + this.onDemandPlaylists + ", lastUpdatedTime=" + ((Object) this.lastUpdatedTime) + ')';
    }

    public final List<OnDemandPlaylist> getOnDemandPlaylists() {
        return this.onDemandPlaylists;
    }
}
