package com.amazon.music.media.auto.util;

import kotlin.Metadata;

/* JADX INFO: compiled from: AutomotiveServiceUtility.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/media/auto/util/AutomotiveServiceUtil;", "", "()V", "isAutomotiveServiceRunning", "", "()Z", "setAutomotiveServiceRunning", "(Z)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutomotiveServiceUtil {
    public static final AutomotiveServiceUtil INSTANCE = new AutomotiveServiceUtil();
    private static boolean isAutomotiveServiceRunning;

    private AutomotiveServiceUtil() {
    }

    public final boolean isAutomotiveServiceRunning() {
        return isAutomotiveServiceRunning;
    }

    public final void setAutomotiveServiceRunning(boolean z) {
        isAutomotiveServiceRunning = z;
    }
}
