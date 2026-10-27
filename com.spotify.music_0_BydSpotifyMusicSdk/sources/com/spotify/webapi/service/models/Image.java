package com.spotify.webapi.service.models;

import java.util.Arrays;
import p.c71;
import p.f60;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public class Image {
    public Integer height;
    public String name;
    public String url;
    public Integer width;

    public Image() {
    }

    @z61(name = "height")
    public static /* synthetic */ void getHeight$annotations() {
    }

    @z61(name = "name")
    public static /* synthetic */ void getName$annotations() {
    }

    @z61(name = "url")
    public static /* synthetic */ void getUrl$annotations() {
    }

    @z61(name = "width")
    public static /* synthetic */ void getWidth$annotations() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Image)) {
            return false;
        }
        Image image = (Image) obj;
        return f60.p(this.width, image.width) && f60.p(this.height, image.height) && f60.p(this.url, image.url) && f60.p(this.name, image.name);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.width, this.height, this.url, this.name});
    }

    public Image(Integer num, Integer num2, String str, String str2) {
        this();
        this.width = num;
        this.height = num2;
        this.url = str;
        this.name = str2;
    }

    public Image(Integer num, Integer num2, String str) {
        this(num, num2, str, null);
    }
}
