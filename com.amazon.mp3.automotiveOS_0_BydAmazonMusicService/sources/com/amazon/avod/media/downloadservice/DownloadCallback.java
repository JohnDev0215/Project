package com.amazon.avod.media.downloadservice;

import android.net.Uri;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadCallback extends SaveCallback {
    default void onDownloadRedirected(@Nonnull Uri uri) {
    }
}
