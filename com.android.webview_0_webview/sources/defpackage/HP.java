package defpackage;

import J.N;
import android.content.Context;
import android.os.RemoteException;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import java.util.List;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.weblayer_private.ContentViewRenderView;
import org.chromium.weblayer_private.TabImpl;
import org.chromium.weblayer_private.TopControlsContainerView;
import org.chromium.weblayer_private.WebLayerFactoryImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class HP implements OQ, TQ, InterfaceC1436rO {
    public final ContentViewRenderView a;
    public final KP b;
    public final TopControlsContainerView c;
    public final FrameLayout d;
    public final C1438rQ e;
    public final C1546tO f;
    public TabImpl g;
    public UQ h;
    public boolean i;

    public HP(C1438rQ c1438rQ) {
        this.e = c1438rQ;
        Context context = (Context) c1438rQ.S().get();
        ContentViewRenderView contentViewRenderView = new ContentViewRenderView(context);
        this.a = contentViewRenderView;
        C1438rQ c1438rQ2 = this.e;
        contentViewRenderView.v = N.MuuxVGQm(contentViewRenderView, c1438rQ2);
        contentViewRenderView.w = c1438rQ2;
        contentViewRenderView.b(0, new ValueCallback() { // from class: LP
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(Object obj) {
                ContentViewRenderView.a();
            }
        });
        TopControlsContainerView topControlsContainerView = new TopControlsContainerView(context, this.a, this);
        this.c = topControlsContainerView;
        topControlsContainerView.setId(View.generateViewId());
        KP kpI = KP.i(context, this.c.z);
        this.b = kpI;
        this.a.addView(kpI, new FrameLayout.LayoutParams(-1, -1, -1));
        this.b.addView(this.c, new RelativeLayout.LayoutParams(-1, -2));
        this.d = new FrameLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 0);
        layoutParams.addRule(3, this.c.getId());
        layoutParams.addRule(12);
        this.b.addView(this.d, layoutParams);
        C1438rQ c1438rQ3 = this.e;
        c1438rQ3.x = this.d;
        c1438rQ3.A = c1438rQ3.y.isTouchExplorationEnabled();
        c1438rQ3.W();
        c1438rQ3.B = new C0660dO(c1438rQ3);
        C1546tO c1546tO = new C1546tO(new Hw(context), 0);
        this.f = c1546tO;
        c1546tO.h.f(this);
        C1546tO c1546tO2 = this.f;
        c1546tO2.a.put(1, new YQ(this, context));
        this.e.K = this.f;
    }

    public final void a() {
        if (this.h.a()) {
            return;
        }
        TopControlsContainerView topControlsContainerView = this.c;
        int i = topControlsContainerView.A;
        if (i == 0 || i == topControlsContainerView.getHeight()) {
            ContentViewRenderView contentViewRenderView = this.a;
            TopControlsContainerView topControlsContainerView2 = this.c;
            int i2 = topControlsContainerView2.w != null ? topControlsContainerView2.A : 0;
            if (i2 == contentViewRenderView.B) {
                return;
            }
            contentViewRenderView.B = i2;
            contentViewRenderView.c();
        }
    }

    public final void b(boolean z) {
        if (WebLayerFactoryImpl.c() >= 82 && this.f.f == 1) {
            try {
                ((ZR) this.g.m).i(z);
            } catch (RemoteException e) {
                throw new AndroidRuntimeException(e);
            }
        }
    }

    public void c() {
        if (this.h.a()) {
            TopControlsContainerView topControlsContainerView = this.c;
            this.i = (topControlsContainerView.w == null || topControlsContainerView.A == 0) ? false : true;
        }
        a();
    }

    public void d(TabImpl tabImpl) {
        TabImpl tabImpl2 = this.g;
        if (tabImpl == tabImpl2) {
            return;
        }
        if (tabImpl2 != null) {
            tabImpl2.w();
            tabImpl2.c.w();
            tabImpl2.G();
            N.Mdvft$Wr(tabImpl2.a, tabImpl2, 0L);
            UQ uq = this.h;
            ((GestureListenerManagerImpl) uq.a).t.g(uq.b);
            uq.b = null;
            uq.a = null;
            uq.f.setOnTouchListener(null);
            this.h = null;
        }
        C1546tO c1546tO = this.f;
        List list = (List) c1546tO.b.get(1);
        if (list != null) {
            while (!list.isEmpty()) {
                JO jo = (JO) list.remove(0);
                ((InterfaceC1656vO) jo.a(AbstractC1711wO.a)).b(jo, 6);
            }
        }
        c1546tO.a(1, 6);
        this.g = tabImpl;
        WebContents webContents = tabImpl != null ? tabImpl.c : null;
        if (this.g != null) {
            this.h = new UQ(this.b, webContents, this);
        }
        KP kp = this.b;
        kp.s = this.g;
        boolean zIsFocused = kp.isFocused();
        boolean zHasWindowFocus = kp.hasWindowFocus();
        boolean zIsAttachedToWindow = kp.isAttachedToWindow();
        if (zIsFocused) {
            kp.onFocusChanged(false, 2, null);
        }
        if (zHasWindowFocus) {
            kp.onWindowFocusChanged(false);
        }
        if (zIsAttachedToWindow) {
            kp.onDetachedFromWindow();
        }
        TabImpl tabImpl3 = kp.s;
        kp.t = tabImpl3 != null ? tabImpl3.c : null;
        if (zIsFocused) {
            kp.onFocusChanged(true, 2, null);
        }
        if (zHasWindowFocus) {
            kp.onWindowFocusChanged(true);
        }
        if (zIsAttachedToWindow) {
            kp.onAttachedToWindow();
        }
        ContentViewRenderView contentViewRenderView = this.a;
        contentViewRenderView.x = webContents;
        if (webContents != null) {
            contentViewRenderView.c();
            N.MQtCkWmJ(contentViewRenderView.v, webContents, contentViewRenderView.z, contentViewRenderView.A);
        }
        N.MYMjn0mf(contentViewRenderView.v, webContents);
        TopControlsContainerView topControlsContainerView = this.c;
        topControlsContainerView.y = webContents;
        N.MAiLwgGG(topControlsContainerView.s, topControlsContainerView, webContents);
        TabImpl tabImpl4 = this.g;
        if (tabImpl4 != null) {
            N.Mdvft$Wr(tabImpl4.a, tabImpl4, this.c.s);
            tabImpl4.G();
            tabImpl4.c.N();
            this.b.requestFocus();
        }
    }
}
