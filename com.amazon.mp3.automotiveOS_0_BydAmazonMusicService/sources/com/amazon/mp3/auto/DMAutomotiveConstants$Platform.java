package com.amazon.mp3.auto;

import kotlin.Metadata;

/* JADX INFO: compiled from: DMAutomotiveConstants.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"com/amazon/mp3/auto/DMAutomotiveConstants$Platform", "", "Lcom/amazon/mp3/auto/DMAutomotiveConstants$Platform;", "", "value", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "ANDROID", "ANDROID_AUTO", "ANDROID_AUTOMOTIVE_OS", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public enum DMAutomotiveConstants$Platform {
    ANDROID("ANDROID"),
    ANDROID_AUTO("ANDROID_AUTO"),
    ANDROID_AUTOMOTIVE_OS("ANDROID_AUTOMOTIVE_OS");

    private final String value;

    DMAutomotiveConstants$Platform(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
