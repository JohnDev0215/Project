package com.spotify.webapi.service.models;

import java.util.List;
import java.util.Map;
import p.c71;
import p.l6;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public class TrackSimple extends Entity {
    public List<ArtistSimple> artists;
    public List<String> available_markets;
    public int disc_number;
    public long duration_ms;
    public Boolean explicit;
    public Map<String, String> external_urls;
    public String href;
    public String id;
    public Boolean is_playable;
    public LinkedTrack linked_from;
    public String name;
    public String preview_url;
    public String[] tags;
    public int track_number;
    public String uri;

    @z61(name = "artists")
    public static /* synthetic */ void getArtists$annotations() {
    }

    @z61(name = "available_markets")
    public static /* synthetic */ void getAvailable_markets$annotations() {
    }

    @z61(name = "disc_number")
    public static /* synthetic */ void getDisc_number$annotations() {
    }

    @z61(name = "duration_ms")
    public static /* synthetic */ void getDuration_ms$annotations() {
    }

    @z61(name = "explicit")
    public static /* synthetic */ void getExplicit$annotations() {
    }

    @z61(name = "external_urls")
    public static /* synthetic */ void getExternal_urls$annotations() {
    }

    @z61(name = "href")
    public static /* synthetic */ void getHref$annotations() {
    }

    @z61(name = "id")
    public static /* synthetic */ void getId$annotations() {
    }

    @z61(name = "linked_from")
    public static /* synthetic */ void getLinked_from$annotations() {
    }

    @z61(name = "name")
    public static /* synthetic */ void getName$annotations() {
    }

    @z61(name = "preview_url")
    public static /* synthetic */ void getPreview_url$annotations() {
    }

    @z61(name = "tags")
    public static /* synthetic */ void getTags$annotations() {
    }

    @z61(name = "track_number")
    public static /* synthetic */ void getTrack_number$annotations() {
    }

    @z61(name = "uri")
    public static /* synthetic */ void getUri$annotations() {
    }

    @z61(name = "is_playable")
    public static /* synthetic */ void is_playable$annotations() {
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String href() {
        return this.href;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String id() {
        return this.id;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public List<Image> images() {
        return null;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public boolean isExplicit() {
        Boolean bool = this.explicit;
        v03.h(bool);
        return bool.booleanValue();
    }

    @Override // com.spotify.webapi.service.models.Entity
    public boolean isPlayable() {
        Boolean bool = this.is_playable;
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String name() {
        return this.name;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String uri() {
        return this.uri;
    }
}
