package com.bumptech.glide.load;

/* JADX INFO: loaded from: classes.dex */
public enum ImageHeaderParser$ImageType {
    GIF(true),
    JPEG(false),
    RAW(false),
    PNG_A(true),
    PNG(false),
    WEBP_A(true),
    WEBP(false),
    UNKNOWN(false);


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f2342a;

    ImageHeaderParser$ImageType(boolean z) {
        this.f2342a = z;
    }

    public boolean hasAlpha() {
        return this.f2342a;
    }
}
