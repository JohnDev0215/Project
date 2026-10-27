package com.amazon.music.views.library.styles.keys;

import kotlin.Metadata;

/* JADX INFO: compiled from: TextSizeKeys.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/styles/keys/TextSizeKeys;", "", "key", "", "(Ljava/lang/String;IF)V", "getKey", "()F", "MALAYALAM_TEXT_SIZE", "DEFAULT_TEXT_SIZE", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum TextSizeKeys {
    MALAYALAM_TEXT_SIZE(10.0f),
    DEFAULT_TEXT_SIZE(14.0f);

    private final float key;

    TextSizeKeys(float f) {
        this.key = f;
    }

    public final float getKey() {
        return this.key;
    }
}
