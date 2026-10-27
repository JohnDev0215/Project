package p;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public class s90 extends ww0 implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    public Handler b0;
    public boolean k0;
    public Dialog m0;
    public boolean n0;
    public boolean o0;
    public final rv c0 = new rv(6, this);
    public final p90 d0 = new p90(this);
    public final q90 e0 = new q90(this);
    public int f0 = 0;
    public int g0 = 0;
    public boolean h0 = true;
    public boolean i0 = true;
    public int j0 = -1;
    public final p01 l0 = new p01(9, this);
    public boolean p0 = false;

    @Override // p.ww0
    public final void A(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.A(layoutInflater, viewGroup, bundle);
        if (this.M != null || this.m0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.m0.onRestoreInstanceState(bundle2);
    }

    public final void F(boolean z, boolean z2) {
        if (this.o0) {
            return;
        }
        this.o0 = true;
        Dialog dialog = this.m0;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.m0.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.b0.getLooper()) {
                    onDismiss(this.m0);
                } else {
                    this.b0.post(this.c0);
                }
            }
        }
        this.n0 = true;
        if (this.j0 >= 0) {
            ox0 ox0VarH = h();
            int i = this.j0;
            if (i < 0) {
                throw new IllegalArgumentException(u4.e("Bad id: ", i));
            }
            ox0VarH.v(new nx0(ox0VarH, i), z);
            this.j0 = -1;
            return;
        }
        sk skVar = new sk(h());
        skVar.o = true;
        ox0 ox0Var = this.z;
        if (ox0Var != null && ox0Var != skVar.f182p) {
            throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + toString() + " is already attached to a FragmentManager.");
        }
        skVar.b(new vx0(3, this));
        if (z) {
            skVar.d(true);
        } else {
            skVar.d(false);
        }
    }

    public Dialog G() {
        if (Log.isLoggable("FragmentManager", 3)) {
            toString();
        }
        return new yv(B(), this.g0);
    }

    @Override // p.ww0
    public final bk0 b() {
        return new r90(this, new tw0(this));
    }

    public void onCancel(DialogInterface dialogInterface) {
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.n0) {
            return;
        }
        if (Log.isLoggable("FragmentManager", 3)) {
            toString();
        }
        F(true, true);
    }

    @Override // p.ww0
    public final void p() {
        this.K = true;
    }

    @Override // p.ww0
    public final void q(Context context) {
        super.q(context);
        this.W.d(this.l0);
        this.o0 = false;
    }

    @Override // p.ww0
    public void r(Bundle bundle) {
        super.r(bundle);
        this.b0 = new Handler();
        this.i0 = this.E == 0;
        if (bundle != null) {
            this.f0 = bundle.getInt("android:style", 0);
            this.g0 = bundle.getInt("android:theme", 0);
            this.h0 = bundle.getBoolean("android:cancelable", true);
            this.i0 = bundle.getBoolean("android:showsDialog", this.i0);
            this.j0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // p.ww0
    public final void t() {
        this.K = true;
        Dialog dialog = this.m0;
        if (dialog != null) {
            this.n0 = true;
            dialog.setOnDismissListener(null);
            this.m0.dismiss();
            if (!this.o0) {
                onDismiss(this.m0);
            }
            this.m0 = null;
            this.p0 = false;
        }
    }

    @Override // p.ww0
    public final void u() {
        this.K = true;
        if (!this.o0) {
            this.o0 = true;
        }
        p01 p01Var = this.l0;
        androidx.lifecycle.b bVar = this.W;
        bVar.getClass();
        androidx.lifecycle.b.a("removeObserver");
        sb1 sb1Var = (sb1) bVar.b.b(p01Var);
        if (sb1Var == null) {
            return;
        }
        sb1Var.f();
        sb1Var.c(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0046 A[Catch: all -> 0x004e, TryCatch #0 {all -> 0x004e, blocks: (B:12:0x001a, B:14:0x0026, B:24:0x003e, B:26:0x0046, B:29:0x0050, B:20:0x0030, B:22:0x0036, B:23:0x003b, B:30:0x0068), top: B:45:0x001a }] */
    @Override // p.ww0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.LayoutInflater v(android.os.Bundle r8) {
        /*
            r7 = this;
            android.view.LayoutInflater r8 = super.v(r8)
            boolean r0 = r7.i0
            java.lang.String r1 = "FragmentManager"
            r2 = 2
            if (r0 == 0) goto L87
            boolean r3 = r7.k0
            if (r3 == 0) goto L11
            goto L87
        L11:
            if (r0 != 0) goto L14
            goto L71
        L14:
            boolean r0 = r7.p0
            if (r0 != 0) goto L71
            r0 = 0
            r3 = 1
            r7.k0 = r3     // Catch: java.lang.Throwable -> L4e
            android.app.Dialog r4 = r7.G()     // Catch: java.lang.Throwable -> L4e
            r7.m0 = r4     // Catch: java.lang.Throwable -> L4e
            boolean r5 = r7.i0     // Catch: java.lang.Throwable -> L4e
            if (r5 == 0) goto L68
            int r5 = r7.f0     // Catch: java.lang.Throwable -> L4e
            if (r5 == r3) goto L3b
            if (r5 == r2) goto L3b
            r6 = 3
            if (r5 == r6) goto L30
            goto L3e
        L30:
            android.view.Window r5 = r4.getWindow()     // Catch: java.lang.Throwable -> L4e
            if (r5 == 0) goto L3b
            r6 = 24
            r5.addFlags(r6)     // Catch: java.lang.Throwable -> L4e
        L3b:
            r4.requestWindowFeature(r3)     // Catch: java.lang.Throwable -> L4e
        L3e:
            android.content.Context r4 = r7.e()     // Catch: java.lang.Throwable -> L4e
            boolean r5 = r4 instanceof android.app.Activity     // Catch: java.lang.Throwable -> L4e
            if (r5 == 0) goto L50
            android.app.Dialog r5 = r7.m0     // Catch: java.lang.Throwable -> L4e
            android.app.Activity r4 = (android.app.Activity) r4     // Catch: java.lang.Throwable -> L4e
            r5.setOwnerActivity(r4)     // Catch: java.lang.Throwable -> L4e
            goto L50
        L4e:
            r8 = move-exception
            goto L6e
        L50:
            android.app.Dialog r4 = r7.m0     // Catch: java.lang.Throwable -> L4e
            boolean r5 = r7.h0     // Catch: java.lang.Throwable -> L4e
            r4.setCancelable(r5)     // Catch: java.lang.Throwable -> L4e
            android.app.Dialog r4 = r7.m0     // Catch: java.lang.Throwable -> L4e
            p.p90 r5 = r7.d0     // Catch: java.lang.Throwable -> L4e
            r4.setOnCancelListener(r5)     // Catch: java.lang.Throwable -> L4e
            android.app.Dialog r4 = r7.m0     // Catch: java.lang.Throwable -> L4e
            p.q90 r5 = r7.e0     // Catch: java.lang.Throwable -> L4e
            r4.setOnDismissListener(r5)     // Catch: java.lang.Throwable -> L4e
            r7.p0 = r3     // Catch: java.lang.Throwable -> L4e
            goto L6b
        L68:
            r3 = 0
            r7.m0 = r3     // Catch: java.lang.Throwable -> L4e
        L6b:
            r7.k0 = r0
            goto L71
        L6e:
            r7.k0 = r0
            throw r8
        L71:
            boolean r0 = android.util.Log.isLoggable(r1, r2)
            if (r0 == 0) goto L7a
            r7.toString()
        L7a:
            android.app.Dialog r0 = r7.m0
            if (r0 == 0) goto L86
            android.content.Context r0 = r0.getContext()
            android.view.LayoutInflater r8 = r8.cloneInContext(r0)
        L86:
            return r8
        L87:
            boolean r0 = android.util.Log.isLoggable(r1, r2)
            if (r0 == 0) goto L90
            r7.toString()
        L90:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p.s90.v(android.os.Bundle):android.view.LayoutInflater");
    }

    @Override // p.ww0
    public void w(Bundle bundle) {
        Dialog dialog = this.m0;
        if (dialog != null) {
            Bundle bundleOnSaveInstanceState = dialog.onSaveInstanceState();
            bundleOnSaveInstanceState.putBoolean("android:dialogShowing", false);
            bundle.putBundle("android:savedDialogState", bundleOnSaveInstanceState);
        }
        int i = this.f0;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.g0;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        boolean z = this.h0;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.i0;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i3 = this.j0;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }

    @Override // p.ww0
    public void x() {
        this.K = true;
        Dialog dialog = this.m0;
        if (dialog != null) {
            this.n0 = false;
            dialog.show();
            View decorView = this.m0.getWindow().getDecorView();
            l13.l(decorView, this);
            decorView.setTag(R.id.view_tree_view_model_store_owner, this);
            l13.m(decorView, this);
        }
    }

    @Override // p.ww0
    public void y() {
        this.K = true;
        Dialog dialog = this.m0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // p.ww0
    public final void z(Bundle bundle) {
        Bundle bundle2;
        this.K = true;
        if (this.m0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.m0.onRestoreInstanceState(bundle2);
    }
}
