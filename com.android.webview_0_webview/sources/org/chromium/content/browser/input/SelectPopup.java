package org.chromium.content.browser.input;

import J.N;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import defpackage.AbstractC0591cB;
import defpackage.C0590cA;
import defpackage.C0926iB;
import defpackage.C1149mB;
import defpackage.C1204nB;
import defpackage.C1905zz;
import defpackage.InterfaceC0479aB;
import defpackage.InterfaceC1074kt;
import defpackage.InterfaceC1740wz;
import defpackage.WN;
import defpackage.Xz;
import defpackage.Yz;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.input.SelectPopup;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SelectPopup extends Xz implements InterfaceC1740wz, WN, Yz, InterfaceC1074kt {
    public final WebContentsImpl s;
    public View t;
    public InterfaceC0479aB u;
    public long v;
    public long w;

    public SelectPopup(WebContents webContents) {
        WebContentsImpl webContentsImpl = (WebContentsImpl) webContents;
        this.s = webContentsImpl;
        ViewAndroidDelegate viewAndroidDelegateD = webContentsImpl.D();
        this.t = viewAndroidDelegateD.getContainerView();
        viewAndroidDelegateD.c.f(this);
        WebContentsImpl webContentsImpl2 = this.s;
        if (webContentsImpl2 != null) {
            C1905zz.a(webContentsImpl2).s.add(this);
        }
        C0590cA.P(this.s).s.f(this);
    }

    public static SelectPopup create(WebContents webContents, long j) {
        SelectPopup selectPopup = (SelectPopup) ((WebContentsImpl) webContents).S(SelectPopup.class, AbstractC0591cB.a);
        selectPopup.v = j;
        return selectPopup;
    }

    private void onNativeDestroyed() {
        this.v = 0L;
    }

    private void show(View view, long j, String[] strArr, int[] iArr, boolean z, int[] iArr2, boolean z2) {
        if (this.t.getParent() == null || this.t.getVisibility() != 0) {
            this.w = j;
            O(null);
            return;
        }
        C1905zz.c(this.s);
        Context contextR = this.s.R();
        if (contextR == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < strArr.length; i++) {
            arrayList.add(new C1204nB(strArr[i], iArr[i]));
        }
        WebContentsAccessibilityImpl webContentsAccessibilityImplI = WebContentsAccessibilityImpl.i(this.s);
        if (!DeviceFormFactor.isTablet() || z || webContentsAccessibilityImplI.O) {
            this.u = new C0926iB(contextR, new Callback(this) { // from class: ZA
                public final SelectPopup a;

                {
                    this.a = this;
                }

                @Override // org.chromium.base.Callback
                public void a(Object obj) {
                    this.a.O((int[]) obj);
                }
            }, arrayList, z, iArr2);
        } else {
            this.u = new C1149mB(contextR, new Callback(this) { // from class: YA
                public final SelectPopup a;

                {
                    this.a = this;
                }

                @Override // org.chromium.base.Callback
                public void a(Object obj) {
                    this.a.O((int[]) obj);
                }
            }, view, arrayList, iArr2, z2, this.s);
        }
        this.w = j;
        this.u.a();
    }

    public void O(int[] iArr) {
        if (this.v != 0) {
            N.ME0LgXse(this.v, this, this.w, iArr);
        }
        this.w = 0L;
        this.u = null;
    }

    @Override // defpackage.InterfaceC1740wz
    public void a() {
        InterfaceC0479aB interfaceC0479aB = this.u;
        if (interfaceC0479aB != null) {
            interfaceC0479aB.b(true);
        }
    }

    public void hideWithoutCancel() {
        InterfaceC0479aB interfaceC0479aB = this.u;
        if (interfaceC0479aB == null) {
            return;
        }
        interfaceC0479aB.b(false);
        this.u = null;
        this.w = 0L;
    }

    @Override // defpackage.WN
    public void m(ViewGroup viewGroup) {
        this.t = viewGroup;
        InterfaceC0479aB interfaceC0479aB = this.u;
        if (interfaceC0479aB != null) {
            interfaceC0479aB.b(true);
        }
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void u(WindowAndroid windowAndroid) {
        this.u = null;
    }
}
