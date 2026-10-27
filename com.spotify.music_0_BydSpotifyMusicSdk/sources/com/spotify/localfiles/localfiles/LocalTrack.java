package com.spotify.localfiles.localfiles;

import com.spotify.webapi.service.models.Search;
import java.util.List;
import p.c71;
import p.hf2;
import p.u4;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class LocalTrack {
    private final LocalAlbum album;
    private final List<LocalArtist> artists;
    private final String contentUri;
    private final boolean is19PlusOnly;
    private final boolean isCurated;
    private final boolean isExplicit;
    private final String name;
    private final String rowId;
    private final String uri;

    public LocalTrack(@z61(name = Search.Type.LINK) String str, @z61(name = "rowId") String str2, @z61(name = "name") String str3, @z61(name = Search.Type.ALBUM) LocalAlbum localAlbum, @z61(name = "artists") List<LocalArtist> list, @z61(name = "isExplicit") boolean z, @z61(name = "contentUri") String str4, @z61(name = "is19PlusOnly") boolean z2, @z61(name = "isCurated") boolean z3) {
        v03.j(str, "uri");
        v03.j(str2, "rowId");
        v03.j(str3, "name");
        v03.j(str4, "contentUri");
        this.uri = str;
        this.rowId = str2;
        this.name = str3;
        this.album = localAlbum;
        this.artists = list;
        this.isExplicit = z;
        this.contentUri = str4;
        this.is19PlusOnly = z2;
        this.isCurated = z3;
    }

    public final String component1() {
        return this.uri;
    }

    public final String component2() {
        return this.rowId;
    }

    public final String component3() {
        return this.name;
    }

    public final LocalAlbum component4() {
        return this.album;
    }

    public final List<LocalArtist> component5() {
        return this.artists;
    }

    public final boolean component6() {
        return this.isExplicit;
    }

    public final String component7() {
        return this.contentUri;
    }

    public final boolean component8() {
        return this.is19PlusOnly;
    }

    public final boolean component9() {
        return this.isCurated;
    }

    public final LocalTrack copy(@z61(name = Search.Type.LINK) String str, @z61(name = "rowId") String str2, @z61(name = "name") String str3, @z61(name = Search.Type.ALBUM) LocalAlbum localAlbum, @z61(name = "artists") List<LocalArtist> list, @z61(name = "isExplicit") boolean z, @z61(name = "contentUri") String str4, @z61(name = "is19PlusOnly") boolean z2, @z61(name = "isCurated") boolean z3) {
        v03.j(str, "uri");
        v03.j(str2, "rowId");
        v03.j(str3, "name");
        v03.j(str4, "contentUri");
        return new LocalTrack(str, str2, str3, localAlbum, list, z, str4, z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalTrack)) {
            return false;
        }
        LocalTrack localTrack = (LocalTrack) obj;
        return v03.b(this.uri, localTrack.uri) && v03.b(this.rowId, localTrack.rowId) && v03.b(this.name, localTrack.name) && v03.b(this.album, localTrack.album) && v03.b(this.artists, localTrack.artists) && this.isExplicit == localTrack.isExplicit && v03.b(this.contentUri, localTrack.contentUri) && this.is19PlusOnly == localTrack.is19PlusOnly && this.isCurated == localTrack.isCurated;
    }

    public final LocalAlbum getAlbum() {
        return this.album;
    }

    public final List<LocalArtist> getArtists() {
        return this.artists;
    }

    public final String getContentUri() {
        return this.contentUri;
    }

    public final String getName() {
        return this.name;
    }

    public final String getRowId() {
        return this.rowId;
    }

    public final String getUri() {
        return this.uri;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [int] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v7, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    public int hashCode() {
        int iB = hf2.b(this.name, hf2.b(this.rowId, this.uri.hashCode() * 31, 31), 31);
        LocalAlbum localAlbum = this.album;
        int iHashCode = (iB + (localAlbum == null ? 0 : localAlbum.hashCode())) * 31;
        List<LocalArtist> list = this.artists;
        int iHashCode2 = (iHashCode + (list != null ? list.hashCode() : 0)) * 31;
        boolean z = this.isExplicit;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int iB2 = hf2.b(this.contentUri, (iHashCode2 + r2) * 31, 31);
        boolean z2 = this.is19PlusOnly;
        ?? r22 = z2;
        if (z2) {
            r22 = 1;
        }
        int i = (iB2 + r22) * 31;
        boolean z3 = this.isCurated;
        return i + (z3 ? 1 : z3);
    }

    public final boolean is19PlusOnly() {
        return this.is19PlusOnly;
    }

    public final boolean isCurated() {
        return this.isCurated;
    }

    public final boolean isExplicit() {
        return this.isExplicit;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LocalTrack(uri=");
        sb.append(this.uri);
        sb.append(", rowId=");
        sb.append(this.rowId);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", album=");
        sb.append(this.album);
        sb.append(", artists=");
        sb.append(this.artists);
        sb.append(", isExplicit=");
        sb.append(this.isExplicit);
        sb.append(", contentUri=");
        sb.append(this.contentUri);
        sb.append(", is19PlusOnly=");
        sb.append(this.is19PlusOnly);
        sb.append(", isCurated=");
        return u4.n(sb, this.isCurated, ')');
    }
}
