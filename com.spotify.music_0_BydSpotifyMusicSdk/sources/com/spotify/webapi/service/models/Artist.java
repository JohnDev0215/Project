package com.spotify.webapi.service.models;

import java.util.List;
import p.c71;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public final class Artist extends ArtistSimple {
    public Followers followers;
    public List<String> genres;
    public List<Image> images;
    public Integer popularity;

    @z61(name = "followers")
    public static /* synthetic */ void getFollowers$annotations() {
    }

    @z61(name = "genres")
    public static /* synthetic */ void getGenres$annotations() {
    }

    @z61(name = "images")
    public static /* synthetic */ void getImages$annotations() {
    }

    @z61(name = "popularity")
    public static /* synthetic */ void getPopularity$annotations() {
    }

    @Override // com.spotify.webapi.service.models.ArtistSimple, com.spotify.webapi.service.models.Entity
    public List<Image> images() {
        return this.images;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public boolean isPlayable() {
        return true;
    }
}
