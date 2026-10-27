package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import p.c71;
import p.f60;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public class UserPublic {
    public String display_name;
    public Map<String, String> external_urls;
    public Followers followers;
    public String href;
    public String id;
    public List<Image> images;
    public String type;
    public String uri;

    @z61(name = "display_name")
    public static /* synthetic */ void getDisplay_name$annotations() {
    }

    @z61(name = "external_urls")
    public static /* synthetic */ void getExternal_urls$annotations() {
    }

    @z61(name = "followers")
    public static /* synthetic */ void getFollowers$annotations() {
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

    @z61(name = RxProductState.Keys.KEY_TYPE)
    public static /* synthetic */ void getType$annotations() {
    }

    @z61(name = "uri")
    public static /* synthetic */ void getUri$annotations() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserPublic)) {
            return false;
        }
        UserPublic userPublic = (UserPublic) obj;
        return f60.p(this.display_name, userPublic.display_name) && f60.p(this.external_urls, userPublic.external_urls) && f60.p(this.followers, userPublic.followers) && f60.p(this.href, userPublic.href) && f60.p(this.id, userPublic.id) && f60.p(this.images, userPublic.images) && f60.p(this.type, userPublic.type) && f60.p(this.uri, userPublic.uri);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.display_name, this.external_urls, this.followers, this.href, this.id, this.images, this.type, this.uri});
    }
}
