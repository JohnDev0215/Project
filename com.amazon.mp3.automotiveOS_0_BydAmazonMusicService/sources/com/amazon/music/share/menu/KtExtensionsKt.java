package com.amazon.music.share.menu;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KtExtensions.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"urlWithRefMarker", "", "Lcom/amazon/music/share/menu/ShareableContent;", "DMMShareProvider_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class KtExtensionsKt {
    public static final String urlWithRefMarker(ShareableContent shareableContent) {
        Intrinsics.checkNotNullParameter(shareableContent, "<this>");
        String url = shareableContent.getUrl();
        if (url == null) {
            return null;
        }
        return RefMarkerUtils.INSTANCE.appendRefMarker(url, shareableContent.getRef());
    }
}
