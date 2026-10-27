package com.spotify.webapi.service.models;

import java.util.List;
import java.util.Map;
import p.c71;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public final class Album extends AlbumSimple {
    public List<ArtistSimple> artists;
    public List<AlbumCopyright> copyrights;
    public Map<String, String> external_ids;
    public List<String> genres;
    public Integer popularity;
    public String release_date;
    public String release_date_precision;
    public Pager<TrackSimple> tracks;

    @z61(name = "artists")
    public static /* synthetic */ void getArtists$annotations() {
    }

    @z61(name = "copyrights")
    public static /* synthetic */ void getCopyrights$annotations() {
    }

    @z61(name = "external_ids")
    public static /* synthetic */ void getExternal_ids$annotations() {
    }

    @z61(name = "genres")
    public static /* synthetic */ void getGenres$annotations() {
    }

    @z61(name = "popularity")
    public static /* synthetic */ void getPopularity$annotations() {
    }

    @z61(name = "release_date")
    public static /* synthetic */ void getRelease_date$annotations() {
    }

    @z61(name = "release_date_precision")
    public static /* synthetic */ void getRelease_date_precision$annotations() {
    }

    @z61(name = "tracks")
    public static /* synthetic */ void getTracks$annotations() {
    }
}
