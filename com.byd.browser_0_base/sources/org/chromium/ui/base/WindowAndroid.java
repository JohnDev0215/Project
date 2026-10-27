package org.chromium.ui.base;

import J.N;
import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import defpackage.aj3;
import defpackage.bi1;
import defpackage.c6;
import defpackage.g3;
import defpackage.ku2;
import defpackage.nx0;
import defpackage.t41;
import defpackage.u80;
import defpackage.x7;
import defpackage.xz;
import defpackage.ya0;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.ui.permissions.AndroidPermissionDelegate;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: loaded from: classes3.dex */
public class WindowAndroid implements AndroidPermissionDelegate, ya0.a {
    public static final nx0<Activity> A = new nx0<>(null);
    public final org.chromium.ui.base.d e;
    public t41 f;
    public long g;
    public final ya0 h;
    public final nx0<Context> i;
    public final HashSet<Animator> j;
    public View k;
    public final AccessibilityManager l;
    public final x7 m;
    public boolean n;
    public f o;
    public AndroidPermissionDelegate p;
    public ArrayList q;
    public final org.chromium.base.c r;
    public float s;
    public boolean t;
    public final org.chromium.ui.base.e u;
    public final org.chromium.base.b<b> v;
    public boolean w;
    public final org.chromium.base.b<e> x;
    public final boolean y;
    public final org.chromium.base.b<d> z;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            animator.removeListener(this);
            WindowAndroid windowAndroid = WindowAndroid.this;
            windowAndroid.j.remove(animator);
            windowAndroid.s();
        }
    }

    public interface b {
        void b();

        void c();

        void e();
    }

    public interface c {
        void b(Intent intent, int i);
    }

    public interface d {
        void a();
    }

    public interface e {
        void a(boolean z);
    }

    public class f {
        public final org.chromium.ui.base.f a;
        public final /* synthetic */ WindowAndroid b;

        public f(g3 g3Var) {
            this.b = g3Var;
            org.chromium.ui.base.f fVar = new org.chromium.ui.base.f(this);
            this.a = fVar;
            g3Var.l.addTouchExplorationStateChangeListener(fVar);
        }
    }

    public WindowAndroid(Context context) {
        this(context, ya0.a(context));
    }

    @CalledByNative
    public static long createForTesting() {
        return new WindowAndroid(xz.a).getNativePointer();
    }

    @CalledByNative
    private long getNativePointer() {
        if (this.g == 0) {
            int i = this.h.b;
            TypedValue typedValue = new TypedValue();
            Context context = this.i.get();
            float dimension = (context == null || !context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) ? 0.0f : typedValue.getDimension(context.getResources().getDisplayMetrics());
            Window windowP = p();
            long jMFjTMMS_ = N.MFjTMMS_(this, i, dimension, windowP == null ? false : windowP.isWideColorGamut());
            this.g = jMFjTMMS_;
            N.MotttR54(jMFjTMMS_, this, false);
        }
        return this.g;
    }

    @CalledByNative
    private void onSelectionHandlesStateChanged(boolean z) {
        this.w = z;
        Iterator<e> it = this.x.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((e) aVar.next()).a(z);
            }
        }
    }

    @Override // ya0.a
    public final void G() {
        r();
    }

    @Override // ya0.a
    public final void L(float f2) {
        long j = this.g;
        if (j != 0) {
            N.MWNjxKcW(j, this, f2);
        }
    }

    public void addContextMenuCloseListener(d dVar) {
        this.z.a(dVar);
    }

    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    public final void b(String[] strArr, PermissionCallback permissionCallback) {
        AndroidPermissionDelegate androidPermissionDelegate = this.p;
        if (androidPermissionDelegate != null) {
            androidPermissionDelegate.b(strArr, permissionCallback);
        } else {
            Log.w("cr_WindowAndroid", "Cannot request permissions as the context is not an Activity");
        }
    }

    @Override // ya0.a
    public final void c() {
        r();
    }

    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    @CalledByNative
    public boolean canRequestPermission(String str) {
        AndroidPermissionDelegate androidPermissionDelegate = this.p;
        if (androidPermissionDelegate != null) {
            return androidPermissionDelegate.canRequestPermission(str);
        }
        Log.w("cr_WindowAndroid", "Cannot determine the request permission state as the context is not an Activity");
        return false;
    }

    @CalledByNative
    public final void clearNativePointer() {
        this.g = 0L;
    }

    @CalledByNative
    public void destroy() {
        org.chromium.ui.base.e eVar;
        long j = this.g;
        if (j != 0) {
            N.MV00Qksi(j, this);
        }
        org.chromium.base.c cVar = this.r;
        cVar.a.getClass();
        u80 u80Var = cVar.b;
        if (!u80Var.e) {
            Iterator it = new HashSet(cVar.d.keySet()).iterator();
            while (it.hasNext()) {
                ((aj3) it.next()).c(cVar);
            }
            cVar.d = null;
            cVar.c = null;
            u80Var.e = true;
        }
        f fVar = this.o;
        if (fVar != null) {
            fVar.b.l.removeTouchExplorationStateChangeListener(fVar.a);
        }
        this.m.destroy();
        if (Build.VERSION.SDK_INT < 32 || (eVar = this.u) == null) {
            return;
        }
        eVar.a();
    }

    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    public final boolean g(String str) {
        AndroidPermissionDelegate androidPermissionDelegate = this.p;
        return androidPermissionDelegate != null && androidPermissionDelegate.g(str);
    }

    @CalledByNative
    public final int getOverlayTransform() {
        org.chromium.ui.base.e eVar;
        int iC = (Build.VERSION.SDK_INT < 32 || (eVar = this.u) == null) ? 0 : eVar.c();
        if (iC != 0) {
            return iC;
        }
        int i = this.h.i;
        if (i == 1) {
            return 4;
        }
        if (i != 2) {
            return i != 3 ? 1 : 6;
        }
        return 5;
    }

    @CalledByNative
    public final float getRefreshRate() {
        return this.h.j;
    }

    @SuppressLint({"NewApi"})
    @CalledByNative
    public final float[] getSupportedRefreshRates() {
        ArrayList arrayList = this.q;
        if (arrayList == null || !this.y) {
            return null;
        }
        float[] fArr = new float[arrayList.size()];
        for (int i = 0; i < this.q.size(); i++) {
            fArr[i] = ((Display.Mode) this.q.get(i)).getRefreshRate();
        }
        return fArr;
    }

    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    public final boolean h(int i, String[] strArr, int[] iArr) {
        AndroidPermissionDelegate androidPermissionDelegate = this.p;
        if (androidPermissionDelegate != null) {
            return androidPermissionDelegate.h(i, strArr, iArr);
        }
        return false;
    }

    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    @CalledByNative
    public boolean hasPermission(String str) {
        AndroidPermissionDelegate androidPermissionDelegate = this.p;
        if (androidPermissionDelegate != null) {
            return androidPermissionDelegate.hasPermission(str);
        }
        return c6.a(Process.myPid(), Process.myUid(), xz.a, str) == 0;
    }

    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    public final boolean i(String str) {
        AndroidPermissionDelegate androidPermissionDelegate = this.p;
        if (androidPermissionDelegate != null) {
            return androidPermissionDelegate.i(str);
        }
        Log.w("cr_WindowAndroid", "Cannot determine the policy permission state as the context is not an Activity");
        return false;
    }

    public final void j(float f2) {
        if (this.q == null || !this.y) {
            return;
        }
        int modeId = 0;
        if (f2 != 0.0f) {
            Display.Mode mode = null;
            float f3 = Float.MAX_VALUE;
            for (int i = 0; i < this.q.size(); i++) {
                Display.Mode mode2 = (Display.Mode) this.q.get(i);
                float fAbs = Math.abs(f2 - mode2.getRefreshRate());
                if (fAbs < f3) {
                    mode = mode2;
                    f3 = fAbs;
                }
            }
            if (f3 > 2.0f) {
                Log.e("cr_WindowAndroid", "Refresh rate not supported : " + f2);
            } else {
                modeId = mode.getModeId();
            }
        }
        Window windowP = p();
        if (windowP == null) {
            return;
        }
        WindowManager.LayoutParams attributes = windowP.getAttributes();
        if (attributes.preferredDisplayModeId == modeId) {
            return;
        }
        attributes.preferredDisplayModeId = modeId;
        windowP.setAttributes(attributes);
    }

    public nx0 k() {
        return A;
    }

    public int l() {
        return 6;
    }

    public t41 m() {
        return this.f;
    }

    public bi1 n() {
        return null;
    }

    public View o() {
        return null;
    }

    final Window p() {
        Activity activityA = xz.a(this.i.get());
        if (activityA == null || activityA.isFinishing()) {
            return null;
        }
        return activityA.getWindow();
    }

    public final IBinder q() {
        View viewPeekDecorView;
        Window windowP = p();
        if (windowP == null || (viewPeekDecorView = windowP.peekDecorView()) == null) {
            return null;
        }
        return viewPeekDecorView.getWindowToken();
    }

    public final void r() {
        ya0 ya0Var = this.h;
        Display.Mode mode = ya0Var.k;
        List<Display.Mode> list = ya0Var.l;
        if (mode == null || list == null || list.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (mode.equals(list.get(i))) {
                arrayList.add(list.get(i));
            } else if (mode.getPhysicalWidth() == list.get(i).getPhysicalWidth() && mode.getPhysicalHeight() == list.get(i).getPhysicalHeight() && mode.getRefreshRate() != list.get(i).getRefreshRate()) {
                arrayList.add(list.get(i));
            }
        }
        if (!arrayList.equals(this.q)) {
            this.q = arrayList;
            long j = this.g;
            if (j != 0) {
                N.MTDQeb$o(j, this, getSupportedRefreshRates());
            }
        }
    }

    public void removeContextMenuCloseListener(d dVar) {
        this.z.c(dVar);
    }

    public final void s() {
        boolean z = !this.n && this.j.isEmpty();
        if (this.k.willNotDraw() != z) {
            this.k.setWillNotDraw(z);
        }
    }

    @SuppressLint({"NewApi"})
    @CalledByNative
    public final void setPreferredRefreshRate(float f2) {
        this.s = f2;
        if (this.t) {
            j(f2);
        }
    }

    @CalledByNative
    public void setWideColorEnabled(boolean z) {
        Window windowP = p();
        if (windowP == null) {
            return;
        }
        windowP.setColorMode(z ? 1 : 0);
    }

    public final boolean u(Intent intent, c cVar, Integer num) {
        org.chromium.ui.base.d dVar = this.e;
        if (dVar != null) {
            return dVar.a(intent, cVar, num) >= 0;
        }
        Objects.toString(intent);
        return false;
    }

    public final void v(Animator animator) {
        if (this.k == null) {
            return;
        }
        if (animator.isStarted()) {
            throw new IllegalArgumentException("Already started.");
        }
        if (!this.j.add(animator)) {
            throw new IllegalArgumentException("Already Added.");
        }
        animator.start();
        s();
        animator.addListener(new a());
    }

    public WindowAndroid(Context context, org.chromium.ui.base.c cVar) {
        this(context, ya0.a(context));
        this.e = (org.chromium.ui.base.d) cVar;
    }

    @SuppressLint({"UseSparseArrays"})
    public WindowAndroid(Context context, ya0 ya0Var) {
        this.f = t41.f;
        this.j = new HashSet<>();
        this.m = new x7();
        this.r = new org.chromium.base.c();
        this.t = true;
        this.v = new org.chromium.base.b<>();
        this.x = new org.chromium.base.b<>();
        this.z = new org.chromium.base.b<>();
        this.i = new nx0<>(context);
        this.h = ya0Var;
        ya0Var.a.put(this, null);
        int i = Build.VERSION.SDK_INT;
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        this.y = true ^ (uiModeManager != null && uiModeManager.getCurrentModeType() == 4);
        r();
        ku2 ku2VarT = ku2.t();
        try {
            this.l = (AccessibilityManager) xz.a.getSystemService("accessibility");
            ku2VarT.close();
            if (!Build.VERSION.RELEASE.equals("8.0.0") && xz.a(context) != null) {
                ya0Var.d(null, null, null, null, null, null, null, null, Boolean.valueOf(context.getResources().getConfiguration().isScreenWideColorGamut()), null, null, null);
            }
            if (i >= 32) {
                this.u = p() != null ? new org.chromium.ui.base.e(this) : null;
            }
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
