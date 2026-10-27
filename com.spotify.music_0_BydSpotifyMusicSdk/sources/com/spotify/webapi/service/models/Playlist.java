package com.spotify.webapi.service.models;

import p.c71;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public class Playlist extends PlaylistBase {
    public Followers followers;
    public Pager<PlaylistTrack> tracks;

    @z61(name = "followers")
    public static /* synthetic */ void getFollowers$annotations() {
    }

    @z61(name = "tracks")
    public static /* synthetic */ void getTracks$annotations() {
    }
}
