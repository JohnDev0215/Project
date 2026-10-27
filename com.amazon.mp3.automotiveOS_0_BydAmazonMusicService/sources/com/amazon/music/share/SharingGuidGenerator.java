package com.amazon.music.share;

import java.util.Random;

/* JADX INFO: loaded from: classes4.dex */
public class SharingGuidGenerator {
    private static final Random RAND = new Random();
    private final int mGuidLength;

    public SharingGuidGenerator(int i) {
        this.mGuidLength = i;
    }

    public String next() {
        int i = this.mGuidLength;
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(RAND.nextInt(62));
        }
        return new String(cArr);
    }
}
