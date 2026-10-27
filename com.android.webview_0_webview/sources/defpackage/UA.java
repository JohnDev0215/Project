package defpackage;

import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class UA {
    public static void a() {
        if (!ThreadUtils.j()) {
            throw new AssertionError("Should be on UI thread.");
        }
    }
}
