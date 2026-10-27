package com.amazon.music.media.auto.provider;

import android.net.Uri;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalImageUriProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"contentProviderUri", "Landroid/net/Uri;", "Ljava/io/File;", "getContentProviderUri", "(Ljava/io/File;)Landroid/net/Uri;", "DMMAutoMediaBrowser_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class LocalImageUriProviderKt {
    public static final Uri getContentProviderUri(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Uri uriBuild = new Uri.Builder().scheme("content").authority("com.amazon.mp3.media.automotive.provider").appendPath(file.getPath()).build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "Builder()\n            .s…ath)\n            .build()");
        return uriBuild;
    }
}
