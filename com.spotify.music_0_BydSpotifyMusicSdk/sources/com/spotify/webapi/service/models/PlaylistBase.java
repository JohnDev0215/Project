package com.spotify.webapi.service.models;

import java.util.List;
import java.util.Map;
import p.c71;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public class PlaylistBase extends Entity {
    public Boolean collaborative;
    public String description;
    public Map<String, String> external_urls;
    public String href;
    public String id;
    public List<Image> images;
    public Boolean is_public;
    public String name;
    public UserPublic owner;
    public String snapshot_id;
    public String uri;

    @z61(name = "collaborative")
    public static /* synthetic */ void getCollaborative$annotations() {
    }

    @z61(name = "description")
    public static /* synthetic */ void getDescription$annotations() {
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

    @z61(name = "images")
    public static /* synthetic */ void getImages$annotations() {
    }

    @z61(name = "name")
    public static /* synthetic */ void getName$annotations() {
    }

    @z61(name = "owner")
    public static /* synthetic */ void getOwner$annotations() {
    }

    @z61(name = "snapshot_id")
    public static /* synthetic */ void getSnapshot_id$annotations() {
    }

    @z61(name = "uri")
    public static /* synthetic */ void getUri$annotations() {
    }

    @z61(name = "public")
    public static /* synthetic */ void is_public$annotations() {
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
        return this.images;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public boolean isPlayable() {
        return true;
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
