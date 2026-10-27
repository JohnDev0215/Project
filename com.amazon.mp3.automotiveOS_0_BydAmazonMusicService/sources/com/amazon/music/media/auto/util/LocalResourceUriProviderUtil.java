package com.amazon.music.media.auto.util;

import android.content.Context;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalResourceUriProviderUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/media/auto/util/LocalResourceUriProviderUtil;", "", "()V", "createLocalAndroidResourceUri", "Landroid/net/Uri;", "resId", "", "context", "Landroid/content/Context;", "authority", "", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LocalResourceUriProviderUtil {
    public static final LocalResourceUriProviderUtil INSTANCE = new LocalResourceUriProviderUtil();

    private LocalResourceUriProviderUtil() {
    }

    public final Uri createLocalAndroidResourceUri(int resId, Context context, String authority) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(authority, "authority");
        Uri uriBuild = new Uri.Builder().scheme("android.resource").authority(authority).appendPath(context.getResources().getResourceTypeName(resId)).appendPath(context.getResources().getResourceEntryName(resId)).build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "Builder()\n              …\n                .build()");
        return uriBuild;
    }
}
