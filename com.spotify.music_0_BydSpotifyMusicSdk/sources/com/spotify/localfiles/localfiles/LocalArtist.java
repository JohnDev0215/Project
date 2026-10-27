package com.spotify.localfiles.localfiles;

import com.spotify.webapi.service.models.Search;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class LocalArtist {
    private final String name;
    private final String uri;

    public LocalArtist(@z61(name = Search.Type.LINK) String str, @z61(name = "name") String str2) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        this.uri = str;
        this.name = str2;
    }

    public static /* synthetic */ LocalArtist copy$default(LocalArtist localArtist, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = localArtist.uri;
        }
        if ((i & 2) != 0) {
            str2 = localArtist.name;
        }
        return localArtist.copy(str, str2);
    }

    public final String component1() {
        return this.uri;
    }

    public final String component2() {
        return this.name;
    }

    public final LocalArtist copy(@z61(name = Search.Type.LINK) String str, @z61(name = "name") String str2) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        return new LocalArtist(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalArtist)) {
            return false;
        }
        LocalArtist localArtist = (LocalArtist) obj;
        return v03.b(this.uri, localArtist.uri) && v03.b(this.name, localArtist.name);
    }

    public final String getName() {
        return this.name;
    }

    public final String getUri() {
        return this.uri;
    }

    public int hashCode() {
        return this.name.hashCode() + (this.uri.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LocalArtist(uri=");
        sb.append(this.uri);
        sb.append(", name=");
        return hf2.d(sb, this.name, ')');
    }
}
