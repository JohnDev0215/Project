package com.amazon.alexa.voice.core.internal.util;

import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public final class RandomString implements CharSequence {
    private static final char[] CHARSET = "0123456789abcdef".toCharArray();
    private final String string;

    public RandomString(int i) {
        Random random = new Random();
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            char[] cArr2 = CHARSET;
            cArr[i2] = cArr2[random.nextInt(cArr2.length)];
        }
        this.string = new String(cArr);
    }

    public RandomString() {
        this(32);
    }

    public static String string() {
        return new RandomString().toString();
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.string.length();
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.string.charAt(i);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.string.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.string;
    }
}
