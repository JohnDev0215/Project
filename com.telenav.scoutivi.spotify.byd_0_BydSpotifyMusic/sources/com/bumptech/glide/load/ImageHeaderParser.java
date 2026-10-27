package com.bumptech.glide.load;

import java.io.InputStream;
import java.nio.ByteBuffer;
import r1.b;

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


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final boolean f4017e;

        ImageType(boolean z6) {
            this.f4017e = z6;
        }

        public boolean hasAlpha() {
            return this.f4017e;
        }
    }

    ImageType a(ByteBuffer byteBuffer);

    int b(InputStream inputStream, b bVar);

    ImageType c(InputStream inputStream);
}
