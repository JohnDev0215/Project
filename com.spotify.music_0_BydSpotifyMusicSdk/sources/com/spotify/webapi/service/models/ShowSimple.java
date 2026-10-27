package com.spotify.webapi.service.models;

import java.util.List;
import p.c71;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public final class ShowSimple extends Entity {
    public String description;
    public Pager<Entity> episodes;
    public Boolean explicit;
    public String href;
    public String id;
    public List<Image> images;
    public MediaType mediaType;
    public String name;
    public String publisher;
    public String uri;

    public enum MediaType {
        AUDIO,
        VIDEO,
        MIXED
    }

    @z61(name = "description")
    public static /* synthetic */ void getDescription$annotations() {
    }

    @z61(name = "episodes")
    public static /* synthetic */ void getEpisodes$annotations() {
    }

    @z61(name = "explicit")
    public static /* synthetic */ void getExplicit$annotations() {
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

    @z61(name = "media_type")
    public static /* synthetic */ void getMediaType$annotations() {
    }

    @z61(name = "name")
    public static /* synthetic */ void getName$annotations() {
    }

    @z61(name = "publisher")
    public static /* synthetic */ void getPublisher$annotations() {
    }

    @z61(name = "uri")
    public static /* synthetic */ void getUri$annotations() {
    }

    @Override // com.spotify.webapi.service.models.Entity
    public Pager<Entity> getChildren() {
        return this.episodes;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public boolean hasChildren() {
        List<Entity> list;
        Pager<Entity> pager = this.episodes;
        return (pager == null || (list = pager.items) == null || list.isEmpty()) ? false : true;
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
    public boolean isExplicit() {
        Boolean bool = this.explicit;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
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
