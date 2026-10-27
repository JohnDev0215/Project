package com.amazon.music.media.auto.util;

import com.amazon.music.skyfire.ui.skyfire.Contexts;
import kotlin.Metadata;

/* JADX INFO: compiled from: PageUri.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/media/auto/util/PageUri;", "", Contexts.OpenSeeMore.uri, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getURI", "()Ljava/lang/String;", "HOME", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum PageUri {
    HOME("home");

    private final String URI;

    PageUri(String str) {
        this.URI = str;
    }

    public final String getURI() {
        return this.URI;
    }
}
