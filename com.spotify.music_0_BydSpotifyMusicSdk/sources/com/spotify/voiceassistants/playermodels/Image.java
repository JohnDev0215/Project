package com.spotify.voiceassistants.playermodels;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class Image {
    private final int height;
    private final String url;
    private final int width;

    public Image(String str, int i, int i2) {
        v03.j(str, "url");
        this.url = str;
        this.width = i;
        this.height = i2;
    }

    public static /* synthetic */ Image copy$default(Image image, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = image.url;
        }
        if ((i3 & 2) != 0) {
            i = image.width;
        }
        if ((i3 & 4) != 0) {
            i2 = image.height;
        }
        return image.copy(str, i, i2);
    }

    public final String component1() {
        return this.url;
    }

    public final int component2() {
        return this.width;
    }

    public final int component3() {
        return this.height;
    }

    public final Image copy(String str, int i, int i2) {
        v03.j(str, "url");
        return new Image(str, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Image)) {
            return false;
        }
        Image image = (Image) obj;
        return v03.b(this.url, image.url) && this.width == image.width && this.height == image.height;
    }

    public final int getHeight() {
        return this.height;
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return Integer.hashCode(this.height) + hf2.a(this.width, this.url.hashCode() * 31, 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Image(url=");
        sb.append(this.url);
        sb.append(", width=");
        sb.append(this.width);
        sb.append(", height=");
        return u4.k(sb, this.height, ')');
    }

    public /* synthetic */ Image(String str, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2);
    }
}
