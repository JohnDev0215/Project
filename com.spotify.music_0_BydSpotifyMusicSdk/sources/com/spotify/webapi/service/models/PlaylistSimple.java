package com.spotify.webapi.service.models;

import java.util.List;
import p.c71;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public final class PlaylistSimple extends PlaylistBase {
    public PlaylistTracksInformation tracks;

    public PlaylistSimple() {
    }

    @z61(name = "tracks")
    public static /* synthetic */ void getTracks$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PlaylistSimple(String str, List<? extends Image> list, String str2, String str3) {
        this();
        this.uri = str;
        this.images = list;
        this.name = str2;
        this.type = str3;
    }
}
