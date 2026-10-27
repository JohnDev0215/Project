package com.spotify.webapi.service.models.views;

import com.spotify.webapi.service.models.Entity;
import com.spotify.webapi.service.models.Image;
import java.util.List;
import p.c71;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public class Link extends Entity {
    public String href;
    public List<Image> images;
    public String name;
    public String uri;

    public Link() {
    }

    @z61(name = "href")
    public static /* synthetic */ void getHref$annotations() {
    }

    @z61(name = "images")
    public static /* synthetic */ void getImages$annotations() {
    }

    @z61(name = "name")
    public static /* synthetic */ void getName$annotations() {
    }

    @z61(name = "uri")
    public static /* synthetic */ void getUri$annotations() {
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String href() {
        return this.href;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String id() {
        return null;
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

    /* JADX WARN: Multi-variable type inference failed */
    public Link(String str, List<? extends Image> list, String str2, String str3) {
        this();
        this.href = str;
        this.images = list;
        this.name = str2;
        this.type = str3;
    }
}
