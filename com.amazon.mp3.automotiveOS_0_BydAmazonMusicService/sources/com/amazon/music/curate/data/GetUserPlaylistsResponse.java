package com.amazon.music.curate.data;

import com.amazon.music.curate.model.Playlist;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GetUserPlaylistsResponse.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0006HÆ\u0003J%\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/curate/data/GetUserPlaylistsResponse;", "", "playlists", "", "Lcom/amazon/music/curate/model/Playlist;", "token", "", "(Ljava/util/List;Ljava/lang/String;)V", "getPlaylists", "()Ljava/util/List;", "getToken", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class GetUserPlaylistsResponse {
    private final List<Playlist> playlists;
    private final String token;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GetUserPlaylistsResponse copy$default(GetUserPlaylistsResponse getUserPlaylistsResponse, List list, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            list = getUserPlaylistsResponse.playlists;
        }
        if ((i & 2) != 0) {
            str = getUserPlaylistsResponse.token;
        }
        return getUserPlaylistsResponse.copy(list, str);
    }

    public final List<Playlist> component1() {
        return this.playlists;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getToken() {
        return this.token;
    }

    public final GetUserPlaylistsResponse copy(List<? extends Playlist> playlists, String token) {
        Intrinsics.checkNotNullParameter(playlists, "playlists");
        return new GetUserPlaylistsResponse(playlists, token);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GetUserPlaylistsResponse)) {
            return false;
        }
        GetUserPlaylistsResponse getUserPlaylistsResponse = (GetUserPlaylistsResponse) other;
        return Intrinsics.areEqual(this.playlists, getUserPlaylistsResponse.playlists) && Intrinsics.areEqual(this.token, getUserPlaylistsResponse.token);
    }

    public int hashCode() {
        int iHashCode = this.playlists.hashCode() * 31;
        String str = this.token;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "GetUserPlaylistsResponse(playlists=" + this.playlists + ", token=" + ((Object) this.token) + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GetUserPlaylistsResponse(List<? extends Playlist> playlists, String str) {
        Intrinsics.checkNotNullParameter(playlists, "playlists");
        this.playlists = playlists;
        this.token = str;
    }

    public final List<Playlist> getPlaylists() {
        return this.playlists;
    }

    public final String getToken() {
        return this.token;
    }
}
