package com.spotify.localfiles.localfiles;

import com.spotify.webapi.service.models.Search;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class LocalAlbum {
    private final LocalCovers covers;
    private final String name;
    private final String uri;

    public LocalAlbum(@z61(name = Search.Type.LINK) String str, @z61(name = "name") String str2, @z61(name = "covers") LocalCovers localCovers) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        this.uri = str;
        this.name = str2;
        this.covers = localCovers;
    }

    public static /* synthetic */ LocalAlbum copy$default(LocalAlbum localAlbum, String str, String str2, LocalCovers localCovers, int i, Object obj) {
        if ((i & 1) != 0) {
            str = localAlbum.uri;
        }
        if ((i & 2) != 0) {
            str2 = localAlbum.name;
        }
        if ((i & 4) != 0) {
            localCovers = localAlbum.covers;
        }
        return localAlbum.copy(str, str2, localCovers);
    }

    public final String component1() {
        return this.uri;
    }

    public final String component2() {
        return this.name;
    }

    public final LocalCovers component3() {
        return this.covers;
    }

    public final LocalAlbum copy(@z61(name = Search.Type.LINK) String str, @z61(name = "name") String str2, @z61(name = "covers") LocalCovers localCovers) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        return new LocalAlbum(str, str2, localCovers);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalAlbum)) {
            return false;
        }
        LocalAlbum localAlbum = (LocalAlbum) obj;
        return v03.b(this.uri, localAlbum.uri) && v03.b(this.name, localAlbum.name) && v03.b(this.covers, localAlbum.covers);
    }

    public final LocalCovers getCovers() {
        return this.covers;
    }

    public final String getName() {
        return this.name;
    }

    public final String getUri() {
        return this.uri;
    }

    public int hashCode() {
        int iB = hf2.b(this.name, this.uri.hashCode() * 31, 31);
        LocalCovers localCovers = this.covers;
        return iB + (localCovers == null ? 0 : localCovers.hashCode());
    }

    public String toString() {
        return "LocalAlbum(uri=" + this.uri + ", name=" + this.name + ", covers=" + this.covers + ')';
    }
}
