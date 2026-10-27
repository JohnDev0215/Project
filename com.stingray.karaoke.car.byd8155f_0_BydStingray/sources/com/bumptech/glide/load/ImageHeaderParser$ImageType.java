package com.bumptech.glide.load;

import e4.c;

/* JADX INFO: loaded from: classes.dex */
public enum ImageHeaderParser$ImageType {
    GIF(true),
    JPEG(false),
    RAW(false),
    PNG_A(true),
    PNG(false),
    WEBP_A(true),
    WEBP(false),
    ANIMATED_WEBP(true),
    AVIF(true),
    ANIMATED_AVIF(true),
    UNKNOWN(false);

    public final boolean l;

    ImageHeaderParser$ImageType(boolean z6) {
        this.l = z6;
    }

    public boolean hasAlpha() {
        return this.l;
    }

    public boolean isWebp() {
        int i7 = c.f14429a[ordinal()];
        return i7 == 1 || i7 == 2 || i7 == 3;
    }
}
