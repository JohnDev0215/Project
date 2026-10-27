package com.neusoft.na.mots;

import kotlin.Metadata;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/neusoft/na/mots/WebViewMode;", "", "v", "", "(Ljava/lang/String;II)V", "getV", "()I", "USE_WEBVIEW", "PREFER_XWALK", "REQUIRE_XWALK", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum WebViewMode {
    USE_WEBVIEW(0),
    PREFER_XWALK(1),
    REQUIRE_XWALK(2);

    private final int v;

    WebViewMode(int i) {
        this.v = i;
    }

    public final int getV() {
        return this.v;
    }
}
