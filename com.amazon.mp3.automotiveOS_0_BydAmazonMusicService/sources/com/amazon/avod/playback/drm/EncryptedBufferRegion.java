package com.amazon.avod.playback.drm;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class EncryptedBufferRegion {
    private int mOffset;
    private int mlength;

    public EncryptedBufferRegion(int i, int i2) {
        this.mOffset = i;
        this.mlength = i2;
    }

    public int getOffset() {
        return this.mOffset;
    }

    public int getLength() {
        return this.mlength;
    }

    public String toString() {
        return String.format(Locale.US, "[Offset: %d Length: %d]", Integer.valueOf(getOffset()), Integer.valueOf(getLength()));
    }
}
