package org.chromium.content_public.browser;

import defpackage.RD;
import defpackage.VD;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public interface NavigationController {
    void a(int i);

    boolean b(int i);

    void c(boolean z);

    boolean canGoBack();

    boolean canGoForward();

    void clearHistory();

    void clearSslPreferences();

    void d();

    void e(RD rd);

    void f();

    VD g();

    void goBack();

    void goForward();
}
