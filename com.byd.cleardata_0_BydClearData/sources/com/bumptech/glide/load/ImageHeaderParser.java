package com.bumptech.glide.load;

import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface ImageHeaderParser {

    public enum ImageType {
        GIF(true),
        JPEG(false),
        RAW(false),
        PNG_A(true),
        PNG(false),
        WEBP_A(true),
        WEBP(false),
        UNKNOWN(false);


        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f1959b;

        ImageType(boolean z) {
            this.f1959b = z;
        }

        public boolean hasAlpha() {
            return this.f1959b;
        }
    }

    ImageType a(ByteBuffer byteBuffer);

    int b(InputStream inputStream, com.bumptech.glide.load.n.a0.b bVar);

    ImageType c(InputStream inputStream);
}
