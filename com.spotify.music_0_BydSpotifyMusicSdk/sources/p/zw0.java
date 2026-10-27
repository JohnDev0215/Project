package p;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public abstract class zw0 extends androidx.activity.a {
    public static final /* synthetic */ int E = 0;
    public boolean B;
    public boolean C;
    public final hk0 z;
    public final androidx.lifecycle.a A = new androidx.lifecycle.a(this);
    public boolean D = true;

    public zw0() {
        final t7 t7Var = (t7) this;
        this.z = new hk0(11, new yw0(t7Var));
        final int i = 1;
        this.m.b.b("android:support:lifecycle", new pv(i, this));
        final int i2 = 0;
        this.s.add(new cz() { // from class: p.xw0
            @Override // p.cz
            public final void accept(Object obj) {
                int i3 = i2;
                zw0 zw0Var = t7Var;
                switch (i3) {
                    case 0:
                        zw0Var.z.z();
                        break;
                    default:
                        zw0Var.z.z();
                        break;
                }
            }
        });
        this.u.add(new cz() { // from class: p.xw0
            @Override // p.cz
            public final void accept(Object obj) {
                int i3 = i;
                zw0 zw0Var = t7Var;
                switch (i3) {
                    case 0:
                        zw0Var.z.z();
                        break;
                    default:
                        zw0Var.z.z();
                        break;
                }
            }
        });
        n(new qv(this, i));
    }

    public static boolean t(ox0 ox0Var) {
        boolean zT = false;
        for (ww0 ww0Var : ox0Var.c.p()) {
            if (ww0Var != null) {
                yw0 yw0Var = ww0Var.A;
                if ((yw0Var == null ? null : yw0Var.u) != null) {
                    zT |= t(ww0Var.d());
                }
                hy0 hy0Var = ww0Var.V;
                Lifecycle$State lifecycle$State = Lifecycle$State.l;
                if (hy0Var != null) {
                    hy0Var.c();
                    if (hy0Var.l.c.compareTo(lifecycle$State) >= 0) {
                        ww0Var.V.l.h();
                        zT = true;
                    }
                }
                if (ww0Var.U.c.compareTo(lifecycle$State) >= 0) {
                    ww0Var.U.h();
                    zT = true;
                }
            }
        }
        return zT;
    }

    @Override // android.app.Activity
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        boolean z;
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (strArr != null && strArr.length > 0) {
            String str2 = strArr[0];
            str2.getClass();
            switch (str2) {
                case "--translation":
                    if (Build.VERSION.SDK_INT < 31) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                    break;
                case "--dump-dumpable":
                case "--list-dumpables":
                    if (Build.VERSION.SDK_INT < 33) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                    break;
                case "--contentcapture":
                    if (Build.VERSION.SDK_INT < 29) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                    break;
                case "--autofill":
                    z = true;
                    break;
                default:
                    z = false;
                    break;
            }
        } else {
            z = false;
        }
        if (true ^ z) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            String str3 = str + "  ";
            printWriter.print(str3);
            printWriter.print("mCreated=");
            printWriter.print(this.B);
            printWriter.print(" mResumed=");
            printWriter.print(this.C);
            printWriter.print(" mStopped=");
            printWriter.print(this.D);
            if (getApplication() != null) {
                v4 v4Var = new v4(j(), vb1.d);
                String canonicalName = vb1.class.getCanonicalName();
                if (canonicalName == null) {
                    throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
                }
                pe2 pe2Var = ((vb1) v4Var.m(vb1.class, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName))).c;
                if (pe2Var.k > 0) {
                    printWriter.print(str3);
                    printWriter.println("Loaders:");
                    if (pe2Var.k > 0) {
                        u4.z(pe2Var.e[0]);
                        printWriter.print(str3);
                        printWriter.print("  #");
                        printWriter.print(pe2Var.c[0]);
                        printWriter.print(": ");
                        throw null;
                    }
                }
            }
            ((yw0) this.z.e).t.u(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // androidx.activity.a, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        this.z.z();
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.activity.a, p.wv, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.A.f(Lifecycle$Event.ON_CREATE);
        ox0 ox0Var = ((yw0) this.z.e).t;
        ox0Var.E = false;
        ox0Var.F = false;
        ox0Var.L.h = false;
        ox0Var.t(1);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = ((yw0) this.z.e).t.f.onCreateView(view, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : viewOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ((yw0) this.z.e).t.k();
        this.A.f(Lifecycle$Event.ON_DESTROY);
    }

    @Override // androidx.activity.a, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 6) {
            return ((yw0) this.z.e).t.i();
        }
        return false;
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.C = false;
        ((yw0) this.z.e).t.t(5);
        this.A.f(Lifecycle$Event.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        this.A.f(Lifecycle$Event.ON_RESUME);
        ox0 ox0Var = ((yw0) this.z.e).t;
        ox0Var.E = false;
        ox0Var.F = false;
        ox0Var.L.h = false;
        ox0Var.t(7);
    }

    @Override // androidx.activity.a, android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.z.z();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onResume() {
        hk0 hk0Var = this.z;
        hk0Var.z();
        super.onResume();
        this.C = true;
        ((yw0) hk0Var.e).t.x(true);
    }

    @Override // android.app.Activity
    public void onStart() {
        hk0 hk0Var = this.z;
        hk0Var.z();
        super.onStart();
        this.D = false;
        if (!this.B) {
            this.B = true;
            ox0 ox0Var = ((yw0) hk0Var.e).t;
            ox0Var.E = false;
            ox0Var.F = false;
            ox0Var.L.h = false;
            ox0Var.t(4);
        }
        ((yw0) hk0Var.e).t.x(true);
        this.A.f(Lifecycle$Event.ON_START);
        ox0 ox0Var2 = ((yw0) hk0Var.e).t;
        ox0Var2.E = false;
        ox0Var2.F = false;
        ox0Var2.L.h = false;
        ox0Var2.t(5);
    }

    @Override // android.app.Activity
    public final void onStateNotSaved() {
        this.z.z();
    }

    @Override // android.app.Activity
    public void onStop() {
        hk0 hk0Var;
        super.onStop();
        this.D = true;
        do {
            hk0Var = this.z;
        } while (t(((yw0) hk0Var.e).t));
        ox0 ox0Var = ((yw0) hk0Var.e).t;
        ox0Var.F = true;
        ox0Var.L.h = true;
        ox0Var.t(4);
        this.A.f(Lifecycle$Event.ON_STOP);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = ((yw0) this.z.e).t.f.onCreateView(null, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewOnCreateView;
    }
}
