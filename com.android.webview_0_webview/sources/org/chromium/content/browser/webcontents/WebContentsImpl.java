package org.chromium.content.browser.webcontents;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import defpackage.AbstractC0929iE;
import defpackage.Az;
import defpackage.C0590cA;
import defpackage.C1036kA;
import defpackage.C1130lt;
import defpackage.C1425rD;
import defpackage.C1535tD;
import defpackage.InterfaceC0594cE;
import defpackage.InterfaceC0761fE;
import defpackage.InterfaceC0873hE;
import defpackage.InterfaceC1074kt;
import defpackage.InterfaceC1480sD;
import defpackage.Wz;
import defpackage.Xz;
import defpackage.Yz;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.framehost.RenderFrameHostDelegate;
import org.chromium.content.browser.framehost.RenderFrameHostImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.AccessibilitySnapshotCallback;
import org.chromium.content_public.browser.AccessibilitySnapshotNode;
import org.chromium.content_public.browser.ImageDownloadCallback;
import org.chromium.content_public.browser.JavaScriptCallback;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebContentsImpl extends Xz implements WebContents, RenderFrameHostDelegate, Yz {
    public String A;
    public boolean B;
    public Throwable C;
    public final List s = new ArrayList();
    public long t;
    public NavigationController u;
    public WebContentsObserverProxy v;
    public SmartClipCallback w;
    public EventForwarder x;
    public Az y;
    public InterfaceC0761fE z;
    public static UUID D = UUID.randomUUID();
    public static final Parcelable.Creator CREATOR = new C1425rD();

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class SmartClipCallback {
        public final Handler a;

        public SmartClipCallback(Handler handler) {
            this.a = handler;
        }
    }

    public WebContentsImpl(long j, NavigationController navigationController) {
        this.t = j;
        this.u = navigationController;
    }

    public static void addAccessibilityNodeAsChild(AccessibilitySnapshotNode accessibilitySnapshotNode, AccessibilitySnapshotNode accessibilitySnapshotNode2) {
        accessibilitySnapshotNode.s.add(accessibilitySnapshotNode2);
    }

    public static void addToBitmapList(List list, Bitmap bitmap) {
        list.add(bitmap);
    }

    private void clearNativePtr() {
        this.C = new RuntimeException("clearNativePtr");
        this.t = 0L;
        this.u = null;
        WebContentsObserverProxy webContentsObserverProxy = this.v;
        if (webContentsObserverProxy != null) {
            webContentsObserverProxy.destroy();
            this.v = null;
        }
    }

    public static WebContentsImpl create(long j, NavigationController navigationController) {
        return new WebContentsImpl(j, navigationController);
    }

    public static AccessibilitySnapshotNode createAccessibilitySnapshotNode(int i, int i2, int i3, int i4, boolean z, String str, int i5, int i6, float f, boolean z2, boolean z3, boolean z4, boolean z5, String str2) {
        AccessibilitySnapshotNode accessibilitySnapshotNode = new AccessibilitySnapshotNode(str, str2);
        if (f >= 0.0d) {
            accessibilitySnapshotNode.j = i5;
            accessibilitySnapshotNode.k = i6;
            accessibilitySnapshotNode.f = f;
            accessibilitySnapshotNode.l = z2;
            accessibilitySnapshotNode.m = z3;
            accessibilitySnapshotNode.n = z4;
            accessibilitySnapshotNode.o = z5;
            accessibilitySnapshotNode.i = true;
        }
        accessibilitySnapshotNode.a = i;
        accessibilitySnapshotNode.b = i2;
        accessibilitySnapshotNode.c = i3;
        accessibilitySnapshotNode.d = i4;
        accessibilitySnapshotNode.e = z;
        return accessibilitySnapshotNode;
    }

    public static List createBitmapList() {
        return new ArrayList();
    }

    public static Rect createSize(int i, int i2) {
        return new Rect(0, 0, i, i2);
    }

    public static void createSizeAndAddToList(List list, int i, int i2) {
        list.add(new Rect(0, 0, i, i2));
    }

    public static List createSizeList() {
        return new ArrayList();
    }

    private long getNativePointer() {
        return this.t;
    }

    public static void onAccessibilitySnapshot(AccessibilitySnapshotNode accessibilitySnapshotNode, AccessibilitySnapshotCallback accessibilitySnapshotCallback) {
        C1036kA c1036kA = (C1036kA) accessibilitySnapshotCallback;
        c1036kA.a.setClassName("");
        c1036kA.a.setHint(c1036kA.c.v);
        if (accessibilitySnapshotNode == null) {
            c1036kA.a.asyncCommit();
        } else {
            c1036kA.c.h(c1036kA.a, accessibilitySnapshotNode, c1036kA.b);
        }
    }

    private void onDownloadImageFinished(ImageDownloadCallback imageDownloadCallback, int i, int i2, String str, List list, List list2) {
        imageDownloadCallback.a(i, i2, str, list, list2);
    }

    public static void onEvaluateJavaScriptResult(String str, JavaScriptCallback javaScriptCallback) {
        javaScriptCallback.a(str);
    }

    public static void onSmartClipDataExtracted(String str, String str2, int i, int i2, int i3, int i4, SmartClipCallback smartClipCallback) {
        Rect rect = new Rect(i, i2, i3, i4);
        Az az = WebContentsImpl.this.y;
        rect.offset(0, (int) (az.k / az.j));
        Bundle bundle = new Bundle();
        WebContentsImpl webContentsImpl = WebContentsImpl.this;
        webContentsImpl.O();
        bundle.putString("url", ((GURL) N.M8927Uaf(webContentsImpl.t, webContentsImpl)).c());
        bundle.putString("title", WebContentsImpl.this.getTitle());
        bundle.putString("text", str);
        bundle.putString("html", str2);
        bundle.putParcelable("rect", rect);
        Message messageObtain = Message.obtain(smartClipCallback.a, 0);
        messageObtain.setData(bundle);
        messageObtain.sendToTarget();
    }

    public static void setAccessibilitySnapshotSelection(AccessibilitySnapshotNode accessibilitySnapshotNode, int i, int i2) {
        accessibilitySnapshotNode.p = true;
        accessibilitySnapshotNode.q = i;
        accessibilitySnapshotNode.r = i2;
    }

    private final void setMediaSession(MediaSessionImpl mediaSessionImpl) {
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void A(AbstractC0929iE abstractC0929iE) {
        WebContentsObserverProxy webContentsObserverProxy = this.v;
        if (webContentsObserverProxy == null) {
            return;
        }
        webContentsObserverProxy.c.g(abstractC0929iE);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public ViewAndroidDelegate D() {
        InterfaceC0873hE interfaceC0873hE = this.z.get();
        if (interfaceC0873hE == null) {
            return null;
        }
        return ((C1535tD) interfaceC0873hE).b;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public EventForwarder F() {
        if (this.x == null) {
            O();
            this.x = (EventForwarder) N.MJJFrmZs(this.t, this);
        }
        return this.x;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void G(int i) {
        O();
        N.MkBVGSRs(this.t, this, i);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public WindowAndroid H() {
        O();
        return (WindowAndroid) N.MunY3e38(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean I() {
        O();
        return N.M6It8dra(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean J() {
        O();
        return N.M2hIwGoV(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void K(int i, int i2, int i3, int i4) {
        if (this.w == null) {
            return;
        }
        O();
        float f = this.y.j;
        N.MHF1rPTW(this.t, this, this.w, (int) (i / f), (int) ((i2 - ((int) r0.k)) / f), (int) (i3 / f), (int) (i4 / f));
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void L(int i, int i2) {
        O();
        N.M7tTrJ_X(this.t, this, i, i2);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public RenderFrameHost M() {
        O();
        return (RenderFrameHost) N.MjidYpBx(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void N() {
        O();
        WebContentsAccessibilityImpl webContentsAccessibilityImplI = WebContentsAccessibilityImpl.i(this);
        if (webContentsAccessibilityImplI != null) {
            webContentsAccessibilityImplI.B(webContentsAccessibilityImplI.t.isEnabled());
        }
        SelectionPopupControllerImpl selectionPopupControllerImplT = SelectionPopupControllerImpl.t(this);
        if (selectionPopupControllerImplT != null) {
            selectionPopupControllerImplT.restoreSelectionPopupsIfNecessary();
        }
        N.MtakfqIH(this.t, this);
    }

    public final void O() {
        if (this.t == 0) {
            throw new IllegalStateException("Native WebContents already destroyed", this.C);
        }
    }

    public void P() {
        O();
        N.MpfMxfut(this.t, this);
    }

    public void Q() {
        O();
        N.MhIiCaN7(this.t, this);
    }

    public Context R() {
        WindowAndroid windowAndroidH = H();
        if (windowAndroidH != null) {
            return (Context) windowAndroidH.S().get();
        }
        return null;
    }

    public InterfaceC1074kt S(Class cls, InterfaceC1480sD interfaceC1480sD) {
        C1130lt c1130ltU;
        if (!this.B || (c1130ltU = U()) == null) {
            return null;
        }
        InterfaceC1074kt interfaceC1074ktC = c1130ltU.c(cls);
        if (interfaceC1074ktC == null) {
            InterfaceC1074kt interfaceC1074kt = (InterfaceC1074kt) interfaceC1480sD.a(this);
            c1130ltU.b();
            C1130lt.a(interfaceC1074kt != null);
            c1130ltU.b.put(cls, interfaceC1074kt);
            interfaceC1074ktC = c1130ltU.c(cls);
        }
        return (InterfaceC1074kt) cls.cast(interfaceC1074ktC);
    }

    public RenderWidgetHostViewImpl T() {
        RenderWidgetHostViewImpl renderWidgetHostViewImpl;
        long j = this.t;
        if (j != 0 && (renderWidgetHostViewImpl = (RenderWidgetHostViewImpl) N.Mj9slq6o(j, this)) != null) {
            if (!(renderWidgetHostViewImpl.a == 0)) {
                return renderWidgetHostViewImpl;
            }
        }
        return null;
    }

    public final C1130lt U() {
        InterfaceC0873hE interfaceC0873hE;
        InterfaceC0761fE interfaceC0761fE = this.z;
        if (interfaceC0761fE == null || (interfaceC0873hE = interfaceC0761fE.get()) == null) {
            return null;
        }
        return ((C1535tD) interfaceC0873hE).a;
    }

    public void V() {
        O();
        N.MYRJ_nNk(this.t, this);
    }

    public void W() {
        O();
        N.MgbVQff0(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean c() {
        O();
        return N.MtSTkEp2(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void d(AbstractC0929iE abstractC0929iE) {
        if (this.v == null) {
            this.v = new WebContentsObserverProxy(this);
        }
        this.v.c.f(abstractC0929iE);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // defpackage.AbstractC0715eO, defpackage.InterfaceC0771fO
    public void e(float f) {
        long j = this.t;
        if (j == 0) {
            return;
        }
        this.y.j = f;
        N.MqhGkzSt(j, this);
    }

    @Override // org.chromium.content.browser.framehost.RenderFrameHostDelegate
    public void f(RenderFrameHostImpl renderFrameHostImpl) {
        this.s.add(renderFrameHostImpl);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void g(String str, JavaScriptCallback javaScriptCallback) {
        ThreadUtils.b();
        if (v() || str == null) {
            return;
        }
        N.M0uS2SDH(this.t, this, str, javaScriptCallback);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public String getTitle() {
        O();
        return N.M7OgjMU8(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public String h() {
        O();
        return N.MrqMRJsG(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public NavigationController i() {
        return this.u;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void k(boolean z) {
        if (this.t == 0) {
            return;
        }
        N.M6R_ShZs(this.t, this, z);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean l() {
        O();
        return N.MZbfAARG(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public int n() {
        O();
        return N.MOzDgqoz(this.t);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void o(String str, ViewAndroidDelegate viewAndroidDelegate, InterfaceC0594cE interfaceC0594cE, WindowAndroid windowAndroid, InterfaceC0761fE interfaceC0761fE) {
        this.A = str;
        this.z = interfaceC0761fE;
        C1535tD c1535tD = new C1535tD(null);
        c1535tD.a = new C1130lt();
        this.z.a(c1535tD);
        Az az = new Az();
        this.y = az;
        az.b = 0.0f;
        az.a = 0.0f;
        az.g = 1.0f;
        this.B = true;
        O();
        ((C1535tD) this.z.get()).b = viewAndroidDelegate;
        N.MgyWdCWB(this.t, this, viewAndroidDelegate);
        O();
        N.MOKG_Wbb(this.t, this, windowAndroid);
        C0590cA.P(this).u(windowAndroid);
        Wz.a(this).h(interfaceC0594cE);
        this.y.j = windowAndroid.t.d;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void p(String str, String str2, String str3, MessagePort[] messagePortArr) {
        if (messagePortArr != null) {
            for (MessagePort messagePort : messagePortArr) {
                if (messagePort.c() || messagePort.a()) {
                    throw new IllegalStateException("Port is already closed or transferred");
                }
                if (messagePort.d()) {
                    throw new IllegalStateException("Port is already started");
                }
            }
        }
        if (str3.equals("*")) {
            str3 = "";
        }
        N.MZFXk0el(this.t, this, str, null, str3, messagePortArr);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void q(WindowAndroid windowAndroid) {
        O();
        N.MOKG_Wbb(this.t, this, windowAndroid);
        C0590cA.P(this).u(windowAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public GURL r() {
        O();
        return (GURL) N.M8927Uaf(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public float s() {
        O();
        return N.MoQgY_pw(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void setSmartClipResultHandler(Handler handler) {
        if (handler == null) {
            this.w = null;
        } else {
            this.w = new SmartClipCallback(handler);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void stop() {
        O();
        N.M$$25N5$(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void t() {
        O();
        N.M6c69Eq5(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean v() {
        return this.t == 0 || N.M5A4vDoy(this.t, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void w() {
        O();
        SelectionPopupControllerImpl selectionPopupControllerImplT = SelectionPopupControllerImpl.t(this);
        if (selectionPopupControllerImplT != null) {
            selectionPopupControllerImplT.hidePopupsAndPreserveSelection();
        }
        N.MHNkuuGQ(this.t, this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putLong("version", 0L);
        bundle.putParcelable("processguard", new ParcelUuid(D));
        bundle.putLong("webcontents", this.t);
        parcel.writeBundle(bundle);
    }

    @Override // defpackage.AbstractC0715eO, defpackage.InterfaceC0771fO
    public void x(int i) {
        int i2;
        if (this.t == 0) {
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
        N.MlztHl3v(this.t, this, i2);
    }

    @Override // org.chromium.content.browser.framehost.RenderFrameHostDelegate
    public void z(RenderFrameHostImpl renderFrameHostImpl) {
        this.s.remove(renderFrameHostImpl);
    }
}
