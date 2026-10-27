package org.chromium.content_public.browser;

import android.os.Handler;
import android.os.Parcelable;
import defpackage.AbstractC0929iE;
import defpackage.InterfaceC0594cE;
import defpackage.InterfaceC0761fE;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public interface WebContents extends Parcelable {
    void A(AbstractC0929iE abstractC0929iE);

    ViewAndroidDelegate D();

    EventForwarder F();

    void G(int i);

    WindowAndroid H();

    boolean I();

    boolean J();

    void K(int i, int i2, int i3, int i4);

    void L(int i, int i2);

    RenderFrameHost M();

    void N();

    boolean c();

    void d(AbstractC0929iE abstractC0929iE);

    void g(String str, JavaScriptCallback javaScriptCallback);

    String getTitle();

    String h();

    NavigationController i();

    void k(boolean z);

    boolean l();

    int n();

    void o(String str, ViewAndroidDelegate viewAndroidDelegate, InterfaceC0594cE interfaceC0594cE, WindowAndroid windowAndroid, InterfaceC0761fE interfaceC0761fE);

    void p(String str, String str2, String str3, MessagePort[] messagePortArr);

    void q(WindowAndroid windowAndroid);

    GURL r();

    float s();

    void setSmartClipResultHandler(Handler handler);

    void stop();

    void t();

    boolean v();

    void w();
}
