package org.chromium.content_public.browser;

import android.os.Handler;
import defpackage.UD;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public interface MessagePort {
    boolean a();

    void b(UD ud, Handler handler);

    boolean c();

    void close();

    boolean d();

    void e(String str, MessagePort[] messagePortArr);
}
