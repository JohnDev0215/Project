package com.amazon.music.views.library.styles;

import kotlin.Metadata;

/* JADX INFO: compiled from: LinkStyleKey.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/styles/LinkStyleKey;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "STANDARD", "ACCENT", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum LinkStyleKey {
    STANDARD("Standard"),
    ACCENT("Accent");

    private final String key;

    LinkStyleKey(String str) {
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }
}
