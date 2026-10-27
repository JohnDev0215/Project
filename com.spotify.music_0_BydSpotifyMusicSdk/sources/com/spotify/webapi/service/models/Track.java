package com.spotify.webapi.service.models;

import com.spotify.webapi.service.models.Search;
import java.util.List;
import java.util.Map;
import p.c71;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public final class Track extends TrackSimple {
    public AlbumSimple album;
    public Map<String, String> external_ids;
    public Integer popularity;

    @z61(name = Search.Type.ALBUM)
    public static /* synthetic */ void getAlbum$annotations() {
    }

    @z61(name = "external_ids")
    public static /* synthetic */ void getExternal_ids$annotations() {
    }

    @z61(name = "popularity")
    public static /* synthetic */ void getPopularity$annotations() {
    }

    @Override // com.spotify.webapi.service.models.TrackSimple, com.spotify.webapi.service.models.Entity
    public List<Image> images() {
        AlbumSimple albumSimple = this.album;
        if (albumSimple != null) {
            return albumSimple.images;
        }
        return null;
    }
}
