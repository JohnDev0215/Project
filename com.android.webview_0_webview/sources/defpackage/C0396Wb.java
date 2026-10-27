package defpackage;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Trace;
import android.util.SparseArray;
import android.view.View;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: Wb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0396Wb implements InterfaceC0555bc {
    public final Method a;
    public final Method b;
    public final Method c;
    public final Method d;
    public final Method e;
    public final Method f;
    public final Method g;
    public final Method h;
    public final Method i;
    public final Method j;
    public final Method k;

    public C0396Wb() {
        try {
            this.a = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            this.b = Class.forName("android.os.SystemProperties").getMethod("addChangeCallback", Runnable.class);
            this.c = View.class.getMethod("getViewRootImpl", new Class[0]);
            this.d = Class.forName("android.view.ViewRootImpl").getMethod("invokeFunctor", Long.TYPE, Boolean.TYPE);
            this.f = Class.forName("android.view.ViewRootImpl").getMethod("detachFunctor", Long.TYPE);
            this.e = Class.forName("android.view.HardwareCanvas").getMethod("callDrawGLFunction", Long.TYPE);
            this.g = AssetManager.class.getMethod("getAssignedPackageIdentifiers", new Class[0]);
            this.h = AssetManager.class.getMethod("addAssetPath", String.class);
            this.i = Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]);
            this.j = Class.forName("android.net.http.ErrorStrings").getMethod("getString", Integer.TYPE, Context.class);
            this.k = Class.forName("android.webkit.WebViewFactory").getMethod("getLoadedPackageInfo", new Class[0]);
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }

    @Override // defpackage.InterfaceC0555bc
    public void a(Canvas canvas, long j, Runnable runnable) {
        throw new RuntimeException("Call not supported");
    }

    @Override // defpackage.InterfaceC0555bc
    public void b(View view, long j) {
        try {
            Object objInvoke = this.c.invoke(view, new Object[0]);
            if (objInvoke != null) {
                this.f.invoke(objInvoke, Long.valueOf(j));
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }

    @Override // defpackage.InterfaceC0555bc
    public int c(Resources resources, String str) {
        try {
            SparseArray sparseArray = (SparseArray) this.g.invoke(resources.getAssets(), new Object[0]);
            for (int i = 0; i < sparseArray.size(); i++) {
                if (str.equals((String) sparseArray.valueAt(i))) {
                    return sparseArray.keyAt(i);
                }
            }
            throw new RuntimeException(Z7.c("Package not found: ", str));
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }

    @Override // defpackage.InterfaceC0250Nr
    public void d(Canvas canvas, int i) {
        throw new RuntimeException();
    }

    @Override // defpackage.InterfaceC0555bc
    public void e(Canvas canvas, long j) {
        try {
            this.e.invoke(canvas, Long.valueOf(j));
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }

    @Override // defpackage.InterfaceC0555bc
    public void f(View view, long j, boolean z) {
        try {
            Object objInvoke = this.c.invoke(view, new Object[0]);
            if (objInvoke != null) {
                this.d.invoke(objInvoke, Long.valueOf(j), Boolean.valueOf(z));
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }

    @Override // defpackage.InterfaceC0555bc
    public String g() {
        return null;
    }

    @Override // defpackage.InterfaceC0555bc
    public void h(Context context) {
        try {
            this.h.invoke(context.getResources().getAssets(), ((PackageInfo) this.k.invoke(null, new Object[0])).applicationInfo.sourceDir);
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }

    @Override // defpackage.InterfaceC0555bc
    public void i(InterfaceC0499ac interfaceC0499ac) {
        try {
            this.b.invoke(null, new RunnableC0378Vb(this, interfaceC0499ac));
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }

    @Override // defpackage.InterfaceC0555bc
    public boolean isMultiProcessEnabled() {
        throw new UnsupportedOperationException();
    }

    @Override // defpackage.InterfaceC0555bc
    public boolean j() {
        try {
            return ((Boolean) this.a.invoke(null, 16L)).booleanValue();
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }

    @Override // defpackage.InterfaceC0555bc
    public String k(Context context, int i) {
        try {
            return (String) this.j.invoke(null, Integer.valueOf(i), context);
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }

    @Override // defpackage.InterfaceC0555bc
    public Application l() {
        try {
            return (Application) this.i.invoke(null, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }

    @Override // defpackage.InterfaceC0555bc
    public boolean m(View view) {
        try {
            return this.c.invoke(view, new Object[0]) != null;
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }
}
