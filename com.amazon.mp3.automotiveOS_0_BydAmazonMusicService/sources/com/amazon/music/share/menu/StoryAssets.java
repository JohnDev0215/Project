package com.amazon.music.share.menu;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StoryAssets.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lcom/amazon/music/share/menu/StoryAssets;", "", "stickerUri", "Landroid/net/Uri;", "backgroundUri", "mimeType", "", "(Landroid/net/Uri;Landroid/net/Uri;Ljava/lang/String;)V", "getBackgroundUri", "()Landroid/net/Uri;", "getMimeType", "()Ljava/lang/String;", "getStickerUri", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StoryAssets {
    private final Uri backgroundUri;
    private final String mimeType;
    private final Uri stickerUri;

    public StoryAssets(Uri stickerUri, Uri backgroundUri, String mimeType) {
        Intrinsics.checkNotNullParameter(stickerUri, "stickerUri");
        Intrinsics.checkNotNullParameter(backgroundUri, "backgroundUri");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        this.stickerUri = stickerUri;
        this.backgroundUri = backgroundUri;
        this.mimeType = mimeType;
    }

    public final Uri getStickerUri() {
        return this.stickerUri;
    }

    public final Uri getBackgroundUri() {
        return this.backgroundUri;
    }

    public final String getMimeType() {
        return this.mimeType;
    }
}
