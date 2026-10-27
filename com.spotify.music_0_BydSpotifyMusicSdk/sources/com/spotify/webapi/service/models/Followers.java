package com.spotify.webapi.service.models;

import java.util.Arrays;
import p.c71;
import p.f60;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public final class Followers {
    public String href;
    public Integer total;

    @z61(name = "href")
    public static /* synthetic */ void getHref$annotations() {
    }

    @z61(name = "total")
    public static /* synthetic */ void getTotal$annotations() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Followers)) {
            return false;
        }
        Followers followers = (Followers) obj;
        return f60.p(this.href, followers.href) && f60.p(this.total, followers.total);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.href, this.total});
    }
}
