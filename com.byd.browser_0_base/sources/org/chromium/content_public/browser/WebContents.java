package org.chromium.content_public.browser;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Parcelable;
import defpackage.dd1;
import defpackage.rq3;
import defpackage.yv2;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.ui.OverscrollRefreshHandler;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public interface WebContents extends Parcelable {

    public interface a {
    }

    void A(boolean z);

    void B(String str);

    void C(rq3 rq3Var);

    void D();

    void E(boolean z);

    void F(Rect rect);

    EventForwarder H();

    void I();

    boolean J();

    void K();

    void N();

    void O(OverscrollRefreshHandler overscrollRefreshHandler);

    float Q();

    boolean S();

    RenderFrameHost U();

    WindowAndroid V();

    void W();

    void Y();

    void Z(yv2 yv2Var);

    RenderWidgetHostViewImpl b0();

    void c0(MessagePayload messagePayload, String str, MessagePort[] messagePortArr);

    boolean d0();

    void destroy();

    void e();

    boolean e0();

    void f0();

    GURL g();

    String getTitle();

    MessagePort[] h0();

    void i(ViewAndroidDelegate viewAndroidDelegate, ContentView contentView, WindowAndroid windowAndroid, b bVar);

    void i0(WindowAndroid windowAndroid);

    boolean isDestroyed();

    boolean isIncognito();

    int j();

    boolean k();

    void k0(boolean z);

    void l();

    int l0();

    NavigationController m();

    void m0(int i, int i2);

    int n(GURL gurl, dd1 dd1Var);

    Rect p();

    int r();

    GURL s();

    void setSmartClipResultHandler(Handler handler);

    void stop();

    ViewAndroidDelegate u();

    void v(int i);

    void x(int i, int i2, int i3, int i4);

    RenderFrameHost y();

    void z(rq3 rq3Var);
}
