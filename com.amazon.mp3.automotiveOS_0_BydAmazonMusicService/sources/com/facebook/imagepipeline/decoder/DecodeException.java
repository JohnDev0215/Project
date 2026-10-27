package com.facebook.imagepipeline.decoder;

import com.facebook.imagepipeline.image.EncodedImage;

/* JADX INFO: loaded from: classes3.dex */
public class DecodeException extends RuntimeException {
    private final EncodedImage mEncodedImage;

    public DecodeException(String message, EncodedImage encodedImage) {
        super(message);
        this.mEncodedImage = encodedImage;
    }

    public EncodedImage getEncodedImage() {
        return this.mEncodedImage;
    }
}
