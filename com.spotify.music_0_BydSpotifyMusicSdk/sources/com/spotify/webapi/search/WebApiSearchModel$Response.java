package com.spotify.webapi.search;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class WebApiSearchModel$Response {
    public final WebApiSearchModel$Albums a;
    public final WebApiSearchModel$Artists b;
    public final WebApiSearchModel$Playlists c;
    public final WebApiSearchModel$Episodes d;
    public final WebApiSearchModel$Tracks e;

    public WebApiSearchModel$Response(@z61(name = "albums") WebApiSearchModel$Albums webApiSearchModel$Albums, @z61(name = "artists") WebApiSearchModel$Artists webApiSearchModel$Artists, @z61(name = "playlists") WebApiSearchModel$Playlists webApiSearchModel$Playlists, @z61(name = "episodes") WebApiSearchModel$Episodes webApiSearchModel$Episodes, @z61(name = "tracks") WebApiSearchModel$Tracks webApiSearchModel$Tracks) {
        this.a = webApiSearchModel$Albums;
        this.b = webApiSearchModel$Artists;
        this.c = webApiSearchModel$Playlists;
        this.d = webApiSearchModel$Episodes;
        this.e = webApiSearchModel$Tracks;
    }

    public final WebApiSearchModel$Response copy(@z61(name = "albums") WebApiSearchModel$Albums webApiSearchModel$Albums, @z61(name = "artists") WebApiSearchModel$Artists webApiSearchModel$Artists, @z61(name = "playlists") WebApiSearchModel$Playlists webApiSearchModel$Playlists, @z61(name = "episodes") WebApiSearchModel$Episodes webApiSearchModel$Episodes, @z61(name = "tracks") WebApiSearchModel$Tracks webApiSearchModel$Tracks) {
        return new WebApiSearchModel$Response(webApiSearchModel$Albums, webApiSearchModel$Artists, webApiSearchModel$Playlists, webApiSearchModel$Episodes, webApiSearchModel$Tracks);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebApiSearchModel$Response)) {
            return false;
        }
        WebApiSearchModel$Response webApiSearchModel$Response = (WebApiSearchModel$Response) obj;
        return v03.b(this.a, webApiSearchModel$Response.a) && v03.b(this.b, webApiSearchModel$Response.b) && v03.b(this.c, webApiSearchModel$Response.c) && v03.b(this.d, webApiSearchModel$Response.d) && v03.b(this.e, webApiSearchModel$Response.e);
    }

    public final int hashCode() {
        WebApiSearchModel$Albums webApiSearchModel$Albums = this.a;
        int iHashCode = (webApiSearchModel$Albums == null ? 0 : webApiSearchModel$Albums.hashCode()) * 31;
        WebApiSearchModel$Artists webApiSearchModel$Artists = this.b;
        int iHashCode2 = (iHashCode + (webApiSearchModel$Artists == null ? 0 : webApiSearchModel$Artists.hashCode())) * 31;
        WebApiSearchModel$Playlists webApiSearchModel$Playlists = this.c;
        int iHashCode3 = (iHashCode2 + (webApiSearchModel$Playlists == null ? 0 : webApiSearchModel$Playlists.hashCode())) * 31;
        WebApiSearchModel$Episodes webApiSearchModel$Episodes = this.d;
        int iHashCode4 = (iHashCode3 + (webApiSearchModel$Episodes == null ? 0 : webApiSearchModel$Episodes.hashCode())) * 31;
        WebApiSearchModel$Tracks webApiSearchModel$Tracks = this.e;
        return iHashCode4 + (webApiSearchModel$Tracks != null ? webApiSearchModel$Tracks.hashCode() : 0);
    }

    public final String toString() {
        return "Response(albums=" + this.a + ", artists=" + this.b + ", playlists=" + this.c + ", episodes=" + this.d + ", tracks=" + this.e + ')';
    }

    public /* synthetic */ WebApiSearchModel$Response(WebApiSearchModel$Albums webApiSearchModel$Albums, WebApiSearchModel$Artists webApiSearchModel$Artists, WebApiSearchModel$Playlists webApiSearchModel$Playlists, WebApiSearchModel$Episodes webApiSearchModel$Episodes, WebApiSearchModel$Tracks webApiSearchModel$Tracks, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : webApiSearchModel$Albums, (i & 2) != 0 ? null : webApiSearchModel$Artists, (i & 4) != 0 ? null : webApiSearchModel$Playlists, (i & 8) != 0 ? null : webApiSearchModel$Episodes, (i & 16) != 0 ? null : webApiSearchModel$Tracks);
    }
}
