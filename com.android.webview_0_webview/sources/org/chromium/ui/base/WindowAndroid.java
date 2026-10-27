package org.chromium.ui.base;

import J.N;
import android.R;
import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import defpackage.AN;
import defpackage.AbstractC0279Pk;
import defpackage.AbstractC0715eO;
import defpackage.AbstractC0827gO;
import defpackage.AbstractC0940iP;
import defpackage.AbstractC1623us;
import defpackage.C0660dO;
import defpackage.C0683dt;
import defpackage.C0994jO;
import defpackage.C1295ot;
import defpackage.C1350pt;
import defpackage.C1546tO;
import defpackage.InterfaceC0492aO;
import defpackage.InterfaceC0548bO;
import defpackage.InterfaceC0771fO;
import defpackage.InterfaceC0884hP;
import defpackage.Is;
import defpackage.JN;
import defpackage.Ku;
import defpackage.Mu;
import defpackage.NN;
import defpackage.RN;
import defpackage.Ts;
import defpackage.Ws;
import defpackage.YN;
import defpackage.Ys;
import defpackage.ZN;
import defpackage.Zs;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.BuildInfo;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WindowAndroid extends AbstractC0715eO implements JN, InterfaceC0771fO {
    public boolean A;
    public C0660dO B;
    public JN C;
    public List D;
    public Ys E;
    public Ys F;
    public final InterfaceC0884hP G;
    public long s;
    public final AbstractC0827gO t;
    public WeakReference u;
    public HashMap v;
    public HashSet w;
    public View x;
    public final AccessibilityManager y;
    public NN z;

    public WindowAndroid(Context context) {
        AbstractC0827gO abstractC0827gOC = AbstractC0827gO.c(context);
        int i = Build.VERSION.SDK_INT;
        AN an = AN.b;
        this.w = new HashSet();
        this.z = new NN();
        this.E = new Ys();
        this.F = new Ys();
        new Ys();
        this.G = new YN(this);
        this.u = new WeakReference(context);
        this.v = new HashMap();
        this.t = abstractC0827gOC;
        abstractC0827gOC.a.put(this, null);
        if (i >= 23) {
            V();
        }
        C0683dt c0683dtX = C0683dt.x();
        try {
            this.y = (AccessibilityManager) Is.a.getSystemService("accessibility");
            c0683dtX.close();
            if (i >= 26 && !Build.VERSION.RELEASE.equals("8.0.0") && Is.a(context) != null) {
                abstractC0827gOC.e(null, null, null, null, null, null, Boolean.valueOf(C1295ot.c(context.getResources().getConfiguration())), null, null, null);
            }
            AbstractC0940iP.a(this.G);
        } catch (Throwable th) {
            try {
                c0683dtX.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    private boolean applyDisableSurfaceControlWorkaround() {
        return ((C0994jO) this.t).n;
    }

    private void clearNativePointer() {
        this.s = 0L;
    }

    public static long createForTesting() {
        return new WindowAndroid(Is.a).getNativePointer();
    }

    private long getNativePointer() {
        Window windowU;
        if (this.s == 0) {
            int i = this.t.b;
            TypedValue typedValue = new TypedValue();
            Context context = (Context) S().get();
            this.s = N.MFjTMMS_(this, i, (context == null || !context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) ? 0.0f : typedValue.getDimension(context.getResources().getDisplayMetrics()), (BuildInfo.a() && (windowU = U()) != null) ? C1350pt.a(windowU) : false);
            N.MotttR54(this.s, this, false);
        }
        return this.s;
    }

    private float getRefreshRate() {
        return this.t.h;
    }

    private float[] getSupportedRefreshRates() {
        List list = this.D;
        if (list == null) {
            return null;
        }
        float[] fArr = new float[list.size()];
        for (int i = 0; i < this.D.size(); i++) {
            fArr[i] = ((Display.Mode) this.D.get(i)).getRefreshRate();
        }
        return fArr;
    }

    private void onSelectionHandlesStateChanged(boolean z) {
        Iterator it = this.F.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((InterfaceC0548bO) ws.next()).a(z);
            }
        }
    }

    private void setPreferredRefreshRate(float f) {
        if (this.D == null || !BuildInfo.a()) {
            return;
        }
        int modeId = 0;
        if (f != 0.0f) {
            Display.Mode mode = null;
            float f2 = Float.MAX_VALUE;
            for (int i = 0; i < this.D.size(); i++) {
                Display.Mode mode2 = (Display.Mode) this.D.get(i);
                float fAbs = Math.abs(f - mode2.getRefreshRate());
                if (fAbs < f2) {
                    mode = mode2;
                    f2 = fAbs;
                }
            }
            if (f2 > 2.0f) {
                Ts.a("WindowAndroid", "Refresh rate not supported : " + f, new Object[0]);
            } else {
                modeId = mode.getModeId();
            }
        }
        Window windowU = U();
        WindowManager.LayoutParams attributes = windowU.getAttributes();
        if (attributes.preferredDisplayModeId == modeId) {
            return;
        }
        attributes.preferredDisplayModeId = modeId;
        windowU.setAttributes(attributes);
    }

    @Override // defpackage.JN
    public boolean B(int i, String[] strArr, int[] iArr) {
        JN jn = this.C;
        if (jn != null) {
            return jn.B(i, strArr, iArr);
        }
        return false;
    }

    @Override // defpackage.AbstractC0715eO, defpackage.InterfaceC0771fO
    public void C(Display.Mode mode) {
        V();
    }

    @Override // defpackage.JN
    public final void E(String[] strArr, RN rn) {
        JN jn = this.C;
        if (jn != null) {
            jn.E(strArr, rn);
        } else {
            Ts.f("WindowAndroid", "Cannot request permissions as the context is not an Activity", new Object[0]);
        }
    }

    public boolean O(Intent intent) {
        return !Zs.b(intent, 0).isEmpty();
    }

    public void P() {
        if (this.s != 0) {
            N.MV00Qksi(this.s, this);
        }
        C0660dO c0660dO = this.B;
        if (c0660dO != null) {
            c0660dO.b.y.removeTouchExplorationStateChangeListener(c0660dO.a);
        }
        AbstractC0940iP.f(this.G);
        NN nn = this.z;
        for (Ku ku : nn.e) {
            ((Mu) ku).d.g(nn.f);
        }
        nn.e.clear();
    }

    public WeakReference Q() {
        return new WeakReference(null);
    }

    public int R() {
        return 6;
    }

    public WeakReference S() {
        return new WeakReference((Context) this.u.get());
    }

    public C1546tO T() {
        return null;
    }

    public final Window U() {
        Activity activityA = Is.a((Context) this.u.get());
        if (activityA == null) {
            return null;
        }
        return activityA.getWindow();
    }

    public final void V() {
        AbstractC0827gO abstractC0827gO = this.t;
        Display.Mode mode = abstractC0827gO.i;
        List list = abstractC0827gO.j;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (mode.equals(list.get(i))) {
                arrayList.add((Display.Mode) list.get(i));
            } else if (mode.getPhysicalWidth() == ((Display.Mode) list.get(i)).getPhysicalWidth() && mode.getPhysicalHeight() == ((Display.Mode) list.get(i)).getPhysicalHeight() && mode.getRefreshRate() != ((Display.Mode) list.get(i)).getRefreshRate()) {
                arrayList.add((Display.Mode) list.get(i));
            }
        }
        if (!arrayList.equals(this.D)) {
            this.D = arrayList;
            if (this.s != 0) {
                N.MTDQeb$o(this.s, this, getSupportedRefreshRates());
            }
        }
    }

    public final void W() {
        boolean z = !this.A && this.w.isEmpty();
        if (this.x.willNotDraw() != z) {
            this.x.setWillNotDraw(z);
        }
    }

    public int X(Intent intent, InterfaceC0492aO interfaceC0492aO, Integer num) {
        String str = "Can't show intent as context is not an Activity: " + intent;
        return -1;
    }

    public boolean Y(Intent intent, InterfaceC0492aO interfaceC0492aO, Integer num) {
        return X(intent, interfaceC0492aO, null) >= 0;
    }

    public void Z(Animator animator) {
        if (this.x == null) {
            return;
        }
        if (animator.isStarted()) {
            throw new IllegalArgumentException("Already started.");
        }
        if (!this.w.add(animator)) {
            throw new IllegalArgumentException("Already Added.");
        }
        animator.start();
        W();
        animator.addListener(new ZN(this));
    }

    @Override // defpackage.JN
    public final boolean canRequestPermission(String str) {
        JN jn = this.C;
        if (jn != null) {
            return jn.canRequestPermission(str);
        }
        Ts.f("WindowAndroid", "Cannot determine the request permission state as the context is not an Activity", new Object[0]);
        return false;
    }

    public IBinder getWindowToken() {
        View viewPeekDecorView;
        Window windowU = U();
        if (windowU == null || (viewPeekDecorView = windowU.peekDecorView()) == null) {
            return null;
        }
        return viewPeekDecorView.getWindowToken();
    }

    @Override // defpackage.JN
    public final boolean hasPermission(String str) {
        JN jn = this.C;
        return jn != null ? jn.hasPermission(str) : AbstractC1623us.a(Is.a, str, Process.myPid(), Process.myUid()) == 0;
    }

    @Override // defpackage.AbstractC0715eO, defpackage.InterfaceC0771fO
    public void j(float f) {
        if (this.s != 0) {
            N.MWNjxKcW(this.s, this, f);
        }
    }

    @Override // defpackage.AbstractC0715eO, defpackage.InterfaceC0771fO
    public void y(List list) {
        V();
    }
}
