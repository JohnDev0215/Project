package com.spotify.webapi.service.models.views;

import com.spotify.webapi.service.models.Entity;
import com.spotify.webapi.service.models.Image;
import com.spotify.webapi.service.models.Pager;
import java.util.List;
import java.util.Map;
import p.c71;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public final class View extends Entity {
    public Pager<Entity> content;
    public Map<String, ? extends Object> custom_fields;
    public Map<String, String> external_urls;
    public String href;
    public String id;
    public List<Image> images;
    public String name;
    public String rendering;
    public String tag_line;

    @z61(name = "content")
    public static /* synthetic */ void getContent$annotations() {
    }

    @z61(name = "custom_fields")
    public static /* synthetic */ void getCustom_fields$annotations() {
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

    @z61(name = "rendering")
    public static /* synthetic */ void getRendering$annotations() {
    }

    @z61(name = "tag_line")
    public static /* synthetic */ void getTag_line$annotations() {
    }

    @Override // com.spotify.webapi.service.models.Entity
    public Pager<Entity> getChildren() {
        return this.content;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public boolean hasChildren() {
        List<Entity> list;
        Pager<Entity> pager = this.content;
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
    public boolean isPlayable() {
        return true;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String name() {
        return this.name;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String uri() {
        return null;
    }
}
