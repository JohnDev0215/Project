package com.spotify.player.legacyplayer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.b;
import java.util.Map;
import kotlinx.parcelize.Parcelize;
import p.c71;
import p.ig2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@Parcelize
@c71(generateAdapter = true)
public final class PlayerTrack implements Parcelable {
    public static final Parcelable.Creator<PlayerTrack> CREATOR = new ig2(1);
    public final String c;
    public final String e;
    public final String k;
    public final String l;
    public final String m;
    public final Map n;

    public PlayerTrack(@z61(name = "uri") String str, @z61(name = "uid") String str2, @z61(name = "album_uri") String str3, @z61(name = "artist_uri") String str4, @z61(name = "provider") String str5, @z61(name = "metadata") Map<String, String> map) {
        v03.j(str, "uri");
        this.c = str;
        this.e = str2;
        this.k = str3;
        this.l = str4;
        this.m = str5;
        this.n = map;
        this.e = str2 == null ? "" : str2;
        b bVarA = ImmutableMap.a();
        if (map != null && (!map.isEmpty())) {
            bVarA.c(map.entrySet());
        }
        if (str3 != null && (map == null || !map.containsKey("album_uri"))) {
            bVarA.b("album_uri", str3);
        }
        if (str4 != null && (map == null || !map.containsKey("artist_uri"))) {
            bVarA.b("artist_uri", str4);
        }
        ImmutableMap immutableMapA = bVarA.a();
        v03.i(immutableMapA, "mapBuilder.build()");
        this.n = immutableMapA;
    }

    public final PlayerTrack copy(@z61(name = "uri") String str, @z61(name = "uid") String str2, @z61(name = "album_uri") String str3, @z61(name = "artist_uri") String str4, @z61(name = "provider") String str5, @z61(name = "metadata") Map<String, String> map) {
        v03.j(str, "uri");
        return new PlayerTrack(str, str2, str3, str4, str5, map);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayerTrack)) {
            return false;
        }
        PlayerTrack playerTrack = (PlayerTrack) obj;
        return v03.b(this.c, playerTrack.c) && v03.b(this.e, playerTrack.e) && v03.b(this.k, playerTrack.k) && v03.b(this.l, playerTrack.l) && v03.b(this.m, playerTrack.m) && v03.b(this.n, playerTrack.n);
    }

    public final int hashCode() {
        int iHashCode = this.c.hashCode() * 31;
        String str = this.e;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.k;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.l;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.m;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Map map = this.n;
        return iHashCode5 + (map != null ? map.hashCode() : 0);
    }

    public final String toString() {
        return "PlayerTrack(uri=" + this.c + ", uid=" + this.e + ", albumUri=" + this.k + ", artistUri=" + this.l + ", provider=" + this.m + ", metadata=" + this.n + ')';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        v03.j(parcel, "out");
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        Map map = this.n;
        if (map == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(map.size());
        for (Map.Entry entry : map.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }
}
