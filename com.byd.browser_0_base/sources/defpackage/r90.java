package defpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.ComponentDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManager.n;
import androidx.lifecycle.viewmodel.R$id;

/* JADX INFO: compiled from: DialogFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class r90 extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    public Handler Z;
    public boolean i0;
    public Dialog k0;
    public boolean l0;
    public boolean m0;
    public boolean n0;
    public final a a0 = new a();
    public final b b0 = new b();
    public final c c0 = new c();
    public int d0 = 0;
    public int e0 = 0;
    public boolean f0 = true;
    public boolean g0 = true;
    public int h0 = -1;
    public final d j0 = new d();
    public boolean o0 = false;

    /* JADX INFO: compiled from: DialogFragment.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        @SuppressLint({"SyntheticAccessor"})
        public final void run() {
            r90 r90Var = r90.this;
            r90Var.c0.onDismiss(r90Var.k0);
        }
    }

    /* JADX INFO: compiled from: DialogFragment.java */
    public class b implements DialogInterface.OnCancelListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        @SuppressLint({"SyntheticAccessor"})
        public final void onCancel(DialogInterface dialogInterface) {
            r90 r90Var = r90.this;
            Dialog dialog = r90Var.k0;
            if (dialog != null) {
                r90Var.onCancel(dialog);
            }
        }
    }

    /* JADX INFO: compiled from: DialogFragment.java */
    public class c implements DialogInterface.OnDismissListener {
        public c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        @SuppressLint({"SyntheticAccessor"})
        public final void onDismiss(DialogInterface dialogInterface) {
            r90 r90Var = r90.this;
            Dialog dialog = r90Var.k0;
            if (dialog != null) {
                r90Var.onDismiss(dialog);
            }
        }
    }

    /* JADX INFO: compiled from: DialogFragment.java */
    public class d implements bq1<b71> {
        public d() {
        }

        @Override // defpackage.bq1
        @SuppressLint({"SyntheticAccessor"})
        public final void a(b71 b71Var) {
            if (b71Var != null) {
                r90 r90Var = r90.this;
                if (r90Var.g0) {
                    View viewY0 = r90Var.y0();
                    if (viewY0.getParent() != null) {
                        throw new IllegalStateException("DialogFragment can not be attached to a container view");
                    }
                    Dialog dialog = r90Var.k0;
                    if (dialog != null) {
                        dialog.setContentView(viewY0);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: DialogFragment.java */
    public class e extends vd1 {
        public final /* synthetic */ vd1 k;

        public e(Fragment.b bVar) {
            this.k = bVar;
        }

        @Override // defpackage.vd1
        public final View r(int i) {
            vd1 vd1Var = this.k;
            if (vd1Var.x()) {
                return vd1Var.r(i);
            }
            Dialog dialog = r90.this.k0;
            if (dialog != null) {
                return dialog.findViewById(i);
            }
            return null;
        }

        @Override // defpackage.vd1
        public final boolean x() {
            return this.k.x() || r90.this.o0;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final vd1 G() {
        return new e(new Fragment.b());
    }

    public final void H0() {
        I0(false, false);
    }

    public final void I0(boolean z, boolean z2) {
        if (this.m0) {
            return;
        }
        this.m0 = true;
        this.n0 = false;
        Dialog dialog = this.k0;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.k0.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.Z.getLooper()) {
                    onDismiss(this.k0);
                } else {
                    this.Z.post(this.a0);
                }
            }
        }
        this.l0 = true;
        if (this.h0 >= 0) {
            FragmentManager fragmentManagerM = M();
            int i = this.h0;
            if (i < 0) {
                throw new IllegalArgumentException(gq2.e("Bad id: ", i));
            }
            fragmentManagerM.u(fragmentManagerM.new n(i, 1), z);
            this.h0 = -1;
            return;
        }
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(M());
        aVar.p = true;
        aVar.k(this);
        if (z) {
            aVar.h(true);
        } else {
            aVar.g();
        }
    }

    public Dialog J0(Bundle bundle) {
        return new ComponentDialog(x0(), this.e0);
    }

    public final Dialog K0() {
        Dialog dialog = this.k0;
        if (dialog != null) {
            return dialog;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    public void L0(Dialog dialog, int i) {
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            }
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public final void M0(FragmentManager fragmentManager, String str) {
        this.m0 = false;
        this.n0 = true;
        fragmentManager.getClass();
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(fragmentManager);
        aVar.p = true;
        aVar.d(0, this, str, 1);
        aVar.g();
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated
    public final void Y() {
        this.H = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void a0(Context context) {
        super.a0(context);
        this.U.e(this.j0);
        if (this.n0) {
            return;
        }
        this.m0 = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void c0(Bundle bundle) {
        super.c0(bundle);
        this.Z = new Handler();
        this.g0 = this.A == 0;
        if (bundle != null) {
            this.d0 = bundle.getInt("android:style", 0);
            this.e0 = bundle.getInt("android:theme", 0);
            this.f0 = bundle.getBoolean("android:cancelable", true);
            this.g0 = bundle.getBoolean("android:showsDialog", this.g0);
            this.h0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void g0() {
        this.H = true;
        Dialog dialog = this.k0;
        if (dialog != null) {
            this.l0 = true;
            dialog.setOnDismissListener(null);
            this.k0.dismiss();
            if (!this.m0) {
                onDismiss(this.k0);
            }
            this.k0 = null;
            this.o0 = false;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void i0() {
        this.H = true;
        if (!this.n0 && !this.m0) {
            this.m0 = true;
        }
        this.U.h(this.j0);
    }

    @Override // androidx.fragment.app.Fragment
    public final LayoutInflater j0(Bundle bundle) {
        LayoutInflater layoutInflaterJ0 = super.j0(bundle);
        boolean z = this.g0;
        if (!z || this.i0) {
            return layoutInflaterJ0;
        }
        if (z && !this.o0) {
            try {
                this.i0 = true;
                Dialog dialogJ0 = J0(bundle);
                this.k0 = dialogJ0;
                if (this.g0) {
                    L0(dialogJ0, this.d0);
                    Context contextK = K();
                    if (contextK instanceof Activity) {
                        this.k0.setOwnerActivity((Activity) contextK);
                    }
                    this.k0.setCancelable(this.f0);
                    this.k0.setOnCancelListener(this.b0);
                    this.k0.setOnDismissListener(this.c0);
                    this.o0 = true;
                } else {
                    this.k0 = null;
                }
            } finally {
                this.i0 = false;
            }
        }
        Dialog dialog = this.k0;
        return dialog != null ? layoutInflaterJ0.cloneInContext(dialog.getContext()) : layoutInflaterJ0;
    }

    @Override // androidx.fragment.app.Fragment
    public void n0(Bundle bundle) {
        Dialog dialog = this.k0;
        if (dialog != null) {
            Bundle bundleOnSaveInstanceState = dialog.onSaveInstanceState();
            bundleOnSaveInstanceState.putBoolean("android:dialogShowing", false);
            bundle.putBundle("android:savedDialogState", bundleOnSaveInstanceState);
        }
        int i = this.d0;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.e0;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        boolean z = this.f0;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.g0;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i3 = this.h0;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void o0() {
        this.H = true;
        Dialog dialog = this.k0;
        if (dialog != null) {
            this.l0 = false;
            dialog.show();
            View decorView = this.k0.getWindow().getDecorView();
            z7.f0(decorView, this);
            decorView.setTag(R$id.view_tree_view_model_store_owner, this);
            n3.g1(decorView, this);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.l0) {
            return;
        }
        I0(true, true);
    }

    @Override // androidx.fragment.app.Fragment
    public void p0() {
        this.H = true;
        Dialog dialog = this.k0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void r0(Bundle bundle) {
        Bundle bundle2;
        this.H = true;
        if (this.k0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.k0.onRestoreInstanceState(bundle2);
    }

    @Override // androidx.fragment.app.Fragment
    public final void t0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.t0(layoutInflater, viewGroup, bundle);
        if (this.f12J != null || this.k0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.k0.onRestoreInstanceState(bundle2);
    }

    public void onCancel(DialogInterface dialogInterface) {
    }
}
