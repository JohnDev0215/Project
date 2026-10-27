package org.chromium.content.browser.webcontents;

import J.N;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.util.Log;
import defpackage.dd1;
import defpackage.eh0;
import defpackage.jt3;
import defpackage.kt3;
import defpackage.lk3;
import defpackage.mk3;
import defpackage.qq3;
import defpackage.rq3;
import defpackage.sm3;
import defpackage.uv;
import defpackage.yv2;
import defpackage.za2;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.content.browser.AppWebMessagePort;
import org.chromium.content.browser.ContentUiEventHandler;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content.browser.framehost.RenderFrameHostDelegate;
import org.chromium.content.browser.framehost.RenderFrameHostImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.ImageDownloadCallback;
import org.chromium.content_public.browser.JavaScriptCallback;
import org.chromium.content_public.browser.MessagePayload;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.a;
import org.chromium.ui.OverscrollRefreshHandler;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class WebContentsImpl implements WebContents, RenderFrameHostDelegate, jt3 {
    public final ArrayList e = new ArrayList();
    public long f;
    public NavigationController g;
    public WebContentsObserverProxy h;
    public SmartClipCallback i;
    public EventForwarder j;
    public yv2 k;
    public za2 l;
    public WebContents.a m;
    public String n;
    public boolean o;
    public uv p;
    public org.chromium.base.b<Runnable> q;
    public static final UUID r = UUID.randomUUID();

    @SuppressLint({"ParcelClassLoader"})
    public static final Parcelable.Creator<WebContents> CREATOR = new a();

    public class SmartClipCallback {
        public final Handler a;

        public SmartClipCallback(Handler handler) {
            this.a = handler;
        }
    }

    public class a implements Parcelable.Creator<WebContents> {
        @Override // android.os.Parcelable.Creator
        public final WebContents createFromParcel(Parcel parcel) {
            Bundle bundle = parcel.readBundle();
            if (bundle.getLong("version", -1L) != 0) {
                return null;
            }
            if (WebContentsImpl.r.compareTo(((ParcelUuid) bundle.getParcelable("processguard")).getUuid()) != 0) {
                return null;
            }
            return (WebContents) N.M$eaBDjM(bundle.getLong("webcontents"));
        }

        @Override // android.os.Parcelable.Creator
        public final WebContents[] newArray(int i) {
            return new WebContents[i];
        }
    }

    public class b implements EventForwarder.a {
        public b() {
        }
    }

    public static class c extends RuntimeException {
        public c(Throwable th) {
            super("Dangling pointer to BrowserContext in WebContents", th);
        }
    }

    public interface d<T> {
        Object i(WebContentsImpl webContentsImpl);
    }

    public static class e implements qq3 {
        public mk3 a;
        public ViewAndroidDelegate b;
    }

    public WebContentsImpl(long j, NavigationController navigationController) {
        this.f = j;
        this.g = navigationController;
    }

    @CalledByNative
    public static void addRenderFrameHostToArray(RenderFrameHost[] renderFrameHostArr, int i, RenderFrameHost renderFrameHost) {
        renderFrameHostArr[i] = renderFrameHost;
    }

    @CalledByNative
    public static void addToBitmapList(List<Bitmap> list, Bitmap bitmap) {
        list.add(bitmap);
    }

    @CalledByNative
    public static WebContentsImpl create(long j, NavigationController navigationController) {
        return new WebContentsImpl(j, navigationController);
    }

    @CalledByNative
    public static List<Bitmap> createBitmapList() {
        return new ArrayList();
    }

    @CalledByNative
    public static RenderFrameHost[] createRenderFrameHostArray(int i) {
        return new RenderFrameHost[i];
    }

    @CalledByNative
    public static Rect createSize(int i, int i2) {
        return new Rect(0, 0, i, i2);
    }

    @CalledByNative
    public static void createSizeAndAddToList(List<Rect> list, int i, int i2) {
        list.add(new Rect(0, 0, i, i2));
    }

    @CalledByNative
    public static List<Rect> createSizeList() {
        return new ArrayList();
    }

    @CalledByNative
    public static void onEvaluateJavaScriptResult(String str, JavaScriptCallback javaScriptCallback) {
        javaScriptCallback.a();
    }

    @CalledByNative
    public static void onSmartClipDataExtracted(String str, String str2, int i, int i2, int i3, int i4, SmartClipCallback smartClipCallback) {
        Rect rect = new Rect(i, i2, i3, i4);
        WebContentsImpl webContentsImpl = WebContentsImpl.this;
        za2 za2Var = webContentsImpl.l;
        rect.offset(0, (int) (za2Var.k / za2Var.j));
        Bundle bundle = new Bundle();
        bundle.putString("url", webContentsImpl.s().j());
        bundle.putString("title", webContentsImpl.getTitle());
        bundle.putString("text", str);
        bundle.putString("html", str2);
        bundle.putParcelable("rect", rect);
        Message messageObtain = Message.obtain(smartClipCallback.a, 0);
        messageObtain.setData(bundle);
        messageObtain.sendToTarget();
    }

    @CalledByNative
    public static void reportDanglingPtrToBrowserContext(Throwable th) {
        N.MLlibBXh(false, new c(th));
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void A(boolean z) {
        R();
        N.M4fkbrQM(this.f, z);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void B(String str) {
        R();
        N.MseJ7A4a(this.f, 2, str);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void C(rq3 rq3Var) {
        if (this.h == null) {
            this.h = new WebContentsObserverProxy(this);
        }
        this.h.g.a(rq3Var);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void D() {
        WebContentsObserverProxy webContentsObserverProxy = this.h;
        if (webContentsObserverProxy != null) {
            webContentsObserverProxy.destroy();
            this.h = null;
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void E(boolean z) {
        long j = this.f;
        if (j == 0) {
            return;
        }
        N.M9QxNoTJ(j, z);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void F(Rect rect) {
        long j = this.f;
        if (j == 0) {
            return;
        }
        N.MtjP03pj(j, rect.top, rect.left, rect.bottom, rect.right);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final EventForwarder H() {
        if (this.j == null) {
            R();
            EventForwarder eventForwarder = (EventForwarder) N.MJJFrmZs(this.f);
            this.j = eventForwarder;
            eventForwarder.g = new b();
        }
        return this.j;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void I() {
        long j = this.f;
        if (j == 0) {
            return;
        }
        N.M6R_ShZs(j, false);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final boolean J() {
        R();
        return N.MgB0r7fM(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void K() {
        long j = this.f;
        if (j == 0) {
            return;
        }
        N.Mzsx8Sk2(j);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void N() {
        long j = this.f;
        if (j == 0) {
            return;
        }
        N.MgcGzQax(j);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void O(OverscrollRefreshHandler overscrollRefreshHandler) {
        R();
        N.MTTB8znA(this.f, overscrollRefreshHandler);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final float Q() {
        R();
        return N.MoQgY_pw(this.f);
    }

    public final void R() {
        if (this.f == 0) {
            throw new IllegalStateException("Native WebContents already destroyed", this.p);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final boolean S() {
        R();
        return N.MZao1OQG(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final RenderFrameHost U() {
        R();
        return (RenderFrameHost) N.MT2cFaRc(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final WindowAndroid V() {
        R();
        return (WindowAndroid) N.MunY3e38(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void W() {
        R();
        N.MQnLkNkP(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void Y() {
        R();
        SelectionPopupControllerImpl selectionPopupControllerImplJ = SelectionPopupControllerImpl.j(this);
        if (selectionPopupControllerImplJ != null) {
            selectionPopupControllerImplJ.restoreSelectionPopupsIfNecessary();
        }
        N.MtakfqIH(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void Z(yv2 yv2Var) {
        this.k = yv2Var;
        long j = this.f;
        if (j == 0) {
            return;
        }
        N.MbcpLoZf(j, yv2Var != null);
    }

    @Override // org.chromium.content.browser.framehost.RenderFrameHostDelegate
    public final void b(RenderFrameHostImpl renderFrameHostImpl) {
        this.e.add(renderFrameHostImpl);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void c0(MessagePayload messagePayload, String str, MessagePort[] messagePortArr) {
        for (MessagePort messagePort : messagePortArr) {
            if (messagePort.isClosed() || messagePort.c()) {
                throw new IllegalStateException("Port is already closed or transferred");
            }
            if (messagePort.d()) {
                throw new IllegalStateException("Port is already started");
            }
        }
        N.MZFXk0el(this.f, messagePayload, str, "", messagePortArr);
    }

    @CalledByNative
    public void clearNativePtr() {
        this.p = new uv("clearNativePtr");
        this.f = 0L;
        this.g = null;
        WebContentsObserverProxy webContentsObserverProxy = this.h;
        if (webContentsObserverProxy != null) {
            webContentsObserverProxy.destroy();
            this.h = null;
        }
    }

    @Override // org.chromium.content.browser.framehost.RenderFrameHostDelegate
    public final void d(RenderFrameHostImpl renderFrameHostImpl) {
        this.e.remove(renderFrameHostImpl);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final boolean d0() {
        R();
        return N.MS0xMYL9(this.f);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void destroy() {
        if (!ThreadUtils.g()) {
            throw new IllegalStateException("Attempting to destroy WebContents on non-UI thread");
        }
        WebContentsObserverProxy webContentsObserverProxy = this.h;
        if (webContentsObserverProxy != null) {
            if (webContentsObserverProxy.h > 0) {
                throw new uv("Attempting to destroy WebContents while handling observer calls");
            }
        }
        long j = this.f;
        if (j != 0) {
            N.MxxzO9Pe(j);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void e() {
        R();
        SelectionPopupControllerImpl selectionPopupControllerImplJ = SelectionPopupControllerImpl.j(this);
        if (selectionPopupControllerImplJ != null) {
            selectionPopupControllerImplJ.hidePopupsAndPreserveSelection();
        }
        N.MHNkuuGQ(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final boolean e0() {
        R();
        return N.MkIL2bW9(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void f0() {
        R();
        N.MSOsA4Ii(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final GURL g() {
        R();
        return (GURL) N.MrqMRJsG(this.f);
    }

    @CalledByNative
    public final long getNativePointer() {
        return this.f;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final String getTitle() {
        R();
        return N.M7OgjMU8(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final MessagePort[] h0() {
        return (AppWebMessagePort[]) N.M9LtFyNP();
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void i(ViewAndroidDelegate viewAndroidDelegate, ContentView contentView, WindowAndroid windowAndroid, org.chromium.content_public.browser.b bVar) {
        e eVar;
        this.n = "4.1.1.0";
        WebContents.a aVar = this.m;
        if (aVar != null) {
            eVar = (e) ((org.chromium.content_public.browser.b) aVar).a;
        } else {
            eVar = new e();
            eVar.a = new mk3();
        }
        this.m = bVar;
        bVar.a = eVar;
        if (this.l == null) {
            this.l = new za2();
        }
        this.o = true;
        R();
        ((e) ((org.chromium.content_public.browser.b) this.m).a).b = viewAndroidDelegate;
        N.MgyWdCWB(this.f, viewAndroidDelegate);
        i0(windowAndroid);
        a.InterfaceC0153a eh0Var = contentView;
        if (contentView == null) {
            eh0Var = new eh0();
        }
        WebContentsImpl webContentsImpl = ((sm3) n0(sm3.class, sm3.a.a)).e;
        GestureListenerManagerImpl.e(webContentsImpl).k = eh0Var;
        ((ContentUiEventHandler) webContentsImpl.n0(ContentUiEventHandler.class, ContentUiEventHandler.a.a)).f = eh0Var;
        if (windowAndroid != null) {
            this.l.j = windowAndroid.h.d;
        }
        GestureListenerManagerImpl.e(this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void i0(WindowAndroid windowAndroid) {
        R();
        N.MOKG_Wbb(this.f, windowAndroid);
        kt3.e(this).a(windowAndroid);
        WebContentsObserverProxy webContentsObserverProxy = this.h;
        if (webContentsObserverProxy != null) {
            webContentsObserverProxy.h(windowAndroid);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final boolean isDestroyed() {
        long j = this.f;
        return j == 0 || N.M5A4vDoy(j);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final boolean isIncognito() {
        R();
        return N.MZbfAARG(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final int j() {
        R();
        return N.MGZCJ6jO(this.f);
    }

    public final Context j0() {
        WindowAndroid windowAndroidV = V();
        if (windowAndroidV != null) {
            return windowAndroidV.i.get();
        }
        return null;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final boolean k() {
        R();
        return N.MtSTkEp2(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void k0(boolean z) {
        R();
        N.M12SiBFk(this.f, z);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void l() {
        R();
        N.M6c69Eq5(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final int l0() {
        R();
        return N.MHjza5Uz(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final NavigationController m() {
        return this.g;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void m0(int i, int i2) {
        R();
        Log.i("cr_WebContentsImpl", "set size: width: " + i + ", height: " + i2);
        N.M7tTrJ_X(this.f, i, i2);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final int n(GURL gurl, dd1 dd1Var) {
        R();
        return N.Mi3V1mlO(this.f, gurl, false, 2048, false, dd1Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends lk3> T n0(Class<T> cls, d<T> dVar) {
        mk3 mk3VarP0;
        if (!this.o || (mk3VarP0 = p0()) == 0) {
            return null;
        }
        lk3 lk3VarB = mk3VarP0.b(cls);
        if (lk3VarB == null && dVar != null) {
            lk3VarB = mk3VarP0.d(cls, (lk3) dVar.i(this));
        }
        return cls.cast(lk3VarB);
    }

    @Override // org.chromium.content_public.browser.WebContents
    /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
    public final RenderWidgetHostViewImpl b0() {
        RenderWidgetHostViewImpl renderWidgetHostViewImpl;
        long j = this.f;
        if (j != 0 && (renderWidgetHostViewImpl = (RenderWidgetHostViewImpl) N.Mj9slq6o(j)) != null) {
            if (!(renderWidgetHostViewImpl.a == 0)) {
                return renderWidgetHostViewImpl;
            }
        }
        return null;
    }

    @CalledByNative
    public final void onDownloadImageFinished(ImageDownloadCallback imageDownloadCallback, int i, int i2, GURL gurl, List<Bitmap> list, List<Rect> list2) {
        imageDownloadCallback.a(i, list, list2);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final Rect p() {
        R();
        return (Rect) N.MN9JdEk5(this.f);
    }

    public final mk3 p0() {
        qq3 qq3Var;
        WebContents.a aVar = this.m;
        if (aVar == null || (qq3Var = ((org.chromium.content_public.browser.b) aVar).a) == null) {
            return null;
        }
        return ((e) qq3Var).a;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final int r() {
        R();
        return N.MOzDgqoz(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final GURL s() {
        R();
        return (GURL) N.M8927Uaf(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void setSmartClipResultHandler(Handler handler) {
        if (handler == null) {
            this.i = null;
        } else {
            this.i = new SmartClipCallback(handler);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void stop() {
        R();
        N.M$$25N5$(this.f);
    }

    @Override // ya0.a
    public final void t(float f) {
        long j = this.f;
        if (j == 0) {
            return;
        }
        this.l.j = f;
        N.MqhGkzSt(j);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final ViewAndroidDelegate u() {
        qq3 qq3Var = ((org.chromium.content_public.browser.b) this.m).a;
        if (qq3Var == null) {
            return null;
        }
        return ((e) qq3Var).b;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void v(int i) {
        R();
        N.MkBVGSRs(this.f, i);
    }

    @Override // ya0.a
    public final void w(int i) {
        int i2;
        long j = this.f;
        if (j == 0) {
            return;
        }
        if (i == 0) {
            i2 = 0;
        } else if (i == 1) {
            i2 = 90;
        } else if (i == 2) {
            i2 = 180;
        } else {
            if (i != 3) {
                throw new IllegalStateException("Display.getRotation() shouldn't return that value");
            }
            i2 = -90;
        }
        N.MlztHl3v(j, i2);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putLong("version", 0L);
        bundle.putParcelable("processguard", new ParcelUuid(r));
        bundle.putLong("webcontents", this.f);
        parcel.writeBundle(bundle);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void x(int i, int i2, int i3, int i4) {
        if (this.i == null) {
            return;
        }
        R();
        float f = this.l.j;
        N.MHF1rPTW(this.f, this.i, (int) (i / f), (int) ((i2 - ((int) r0.k)) / f), (int) (i3 / f), (int) (i4 / f));
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final RenderFrameHost y() {
        R();
        return (RenderFrameHost) N.MjidYpBx(this.f);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public final void z(rq3 rq3Var) {
        WebContentsObserverProxy webContentsObserverProxy = this.h;
        if (webContentsObserverProxy == null) {
            return;
        }
        webContentsObserverProxy.g.c(rq3Var);
    }

    @CalledByNative
    public final void setMediaSession(MediaSessionImpl mediaSessionImpl) {
    }
}
