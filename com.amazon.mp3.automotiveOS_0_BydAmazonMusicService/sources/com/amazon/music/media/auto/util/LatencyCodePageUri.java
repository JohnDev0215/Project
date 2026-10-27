package com.amazon.music.media.auto.util;

import com.amazon.music.skyfire.ui.skyfire.Contexts;
import kotlin.Metadata;

/* JADX INFO: compiled from: LatencyCodePageUri.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/media/auto/util/LatencyCodePageUri;", "", Contexts.OpenSeeMore.uri, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getURI", "()Ljava/lang/String;", "MEDIA_BROWSER_HOME", "MEDIA_BROWSER_LIBRARY", "MEDIA_BROWSER_PODCAST", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum LatencyCodePageUri {
    MEDIA_BROWSER_HOME("media-browser-home"),
    MEDIA_BROWSER_LIBRARY("media-browser-library"),
    MEDIA_BROWSER_PODCAST("media-browser-podcast");

    private final String URI;

    LatencyCodePageUri(String str) {
        this.URI = str;
    }

    public final String getURI() {
        return this.URI;
    }
}
