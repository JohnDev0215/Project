package defpackage;

import android.app.Activity;
import android.graphics.Rect;
import android.view.Window;
import android.view.WindowManager;
import defpackage.cb0;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.widget.InsetObserverView;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: DisplayCutoutController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bb0 implements InsetObserverView.d {
    public Window e;
    public int f = 0;
    public InsetObserverView g;
    public zp1<Integer> h;
    public x10 i;
    public final a j;

    /* JADX INFO: compiled from: DisplayCutoutController.java */
    public interface a {
    }

    public bb0(cb0.b bVar) {
        this.j = bVar;
        b();
    }

    public final void b() {
        zp1 zp1Var;
        cb0.b bVar = (cb0.b) this.j;
        Activity activity = (Activity) bVar.a.D().k().get();
        if (activity == null) {
            return;
        }
        WindowAndroid windowAndroidD = bVar.a.D();
        zp1<Integer> zp1Var2 = null;
        if (windowAndroidD == null) {
            aj3<m01> aj3Var = m01.j;
            zp1Var = null;
        } else {
            zp1Var = (zp1) m01.j.e(windowAndroidD.r);
        }
        InsetObserverView insetObserverView = zp1Var == null ? null : (InsetObserverView) zp1Var.get();
        InsetObserverView insetObserverView2 = this.g;
        if (insetObserverView2 != insetObserverView) {
            if (insetObserverView2 != null) {
                insetObserverView2.f.c(this);
            }
            this.g = insetObserverView;
            if (insetObserverView != null) {
                insetObserverView.f.a(this);
            }
        }
        WindowAndroid windowAndroidD2 = bVar.a.D();
        if (windowAndroidD2 != null) {
            aj3<o2> aj3Var2 = o2.j;
            zp1Var2 = (zp1) o2.j.e(windowAndroidD2.r);
        }
        d(zp1Var2);
        this.e = activity.getWindow();
    }

    public final void c() {
        int iIntValue;
        Window window = this.e;
        WindowManager.LayoutParams attributes = window == null ? null : window.getAttributes();
        if (attributes == null) {
            return;
        }
        int i = 0;
        if (((cb0.b) this.j).a.isUserInteractable()) {
            zp1<Integer> zp1Var = this.h;
            if (zp1Var == null || (iIntValue = zp1Var.get().intValue()) == 0) {
                int i2 = this.f;
                if (i2 == 1) {
                    i = 2;
                } else if (i2 == 2 || i2 == 3) {
                    i = 1;
                }
            } else {
                i = iIntValue;
            }
        }
        if (attributes.layoutInDisplayCutoutMode == i) {
            return;
        }
        attributes.layoutInDisplayCutoutMode = i;
        this.e.setAttributes(attributes);
    }

    public final void d(zp1<Integer> zp1Var) {
        zp1<Integer> zp1Var2 = this.h;
        if (zp1Var2 == zp1Var) {
            return;
        }
        Callback<Integer> callback = this.i;
        if (callback != null) {
            zp1Var2.d(callback);
        }
        this.h = zp1Var;
        this.i = null;
        if (zp1Var != null) {
            x10 x10Var = new x10(25, this);
            this.i = x10Var;
            zp1Var.p(x10Var);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.InsetObserverView.d
    public final void m(Rect rect) {
        cb0.b bVar = (cb0.b) this.j;
        WebContents webContentsC = bVar.a.c();
        if (webContentsC == null) {
            return;
        }
        float f = bVar.a.c().V().h.d;
        rect.set((int) Math.ceil(rect.left / f), (int) Math.ceil(rect.top / f), (int) Math.ceil(rect.right / f), (int) Math.ceil(rect.bottom / f));
        webContentsC.F(rect);
    }

    @Override // org.chromium.components.browser_ui.widget.InsetObserverView.d
    public final void a(int i) {
    }
}
