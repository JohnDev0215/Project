package com.byd.filemanager.ui.c;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/* JADX INFO: compiled from: LeakDialogFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private Handler Z;
    private final Runnable a0 = new a();
    int b0 = 0;
    int c0 = 0;
    boolean d0 = true;
    boolean e0 = true;
    int f0 = -1;
    Dialog g0;
    boolean h0;
    boolean i0;
    boolean j0;

    /* JADX INFO: compiled from: LeakDialogFragment.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g gVar = g.this;
            Dialog dialog = gVar.g0;
            if (dialog != null) {
                gVar.onDismiss(dialog);
            }
        }
    }

    public void A1(boolean z) {
        B1(z, false);
    }

    void B1(boolean z, boolean z2) {
        if (this.i0) {
            return;
        }
        this.i0 = true;
        this.j0 = false;
        Dialog dialog = this.g0;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.g0.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.Z.getLooper()) {
                    onDismiss(this.g0);
                } else {
                    this.Z.post(this.a0);
                }
            }
        }
        this.h0 = true;
        if (this.f0 >= 0) {
            k1().g(this.f0, 1);
            this.f0 = -1;
            return;
        }
        androidx.fragment.app.j jVarA = k1().a();
        jVarA.j(this);
        if (z) {
            jVarA.g();
        } else {
            jVarA.f();
        }
    }

    public Dialog C1() {
        return this.g0;
    }

    public int D1() {
        return this.c0;
    }

    public Dialog E1(Bundle bundle) {
        return new Dialog(j1(), D1());
    }

    public void F1(Dialog dialog, int i) {
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    @Override // androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        Bundle bundleOnSaveInstanceState;
        super.G0(bundle);
        Dialog dialog = this.g0;
        if (dialog != null && (bundleOnSaveInstanceState = dialog.onSaveInstanceState()) != null) {
            bundle.putBundle("android:savedDialogState", bundleOnSaveInstanceState);
        }
        int i = this.b0;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.c0;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        boolean z = this.d0;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.e0;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i3 = this.f0;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }

    public void G1(androidx.fragment.app.g gVar, String str) {
        this.i0 = false;
        this.j0 = true;
        androidx.fragment.app.j jVarA = gVar.a();
        jVarA.c(this, str);
        jVarA.f();
    }

    @Override // androidx.fragment.app.Fragment
    public void H0() {
        super.H0();
        Dialog dialog = this.g0;
        if (dialog != null) {
            this.h0 = false;
            dialog.show();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void I0() {
        super.I0();
        Dialog dialog = this.g0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        Bundle bundle2;
        super.e0(bundle);
        if (this.e0) {
            View viewT = T();
            if (viewT != null) {
                if (viewT.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.g0.setContentView(viewT);
            }
            FragmentActivity fragmentActivityP = p();
            if (fragmentActivityP != null) {
                this.g0.setOwnerActivity(fragmentActivityP);
            }
            this.g0.setCancelable(this.d0);
            if (bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
                return;
            }
            this.g0.onRestoreInstanceState(bundle2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void h0(Context context) {
        super.h0(context);
        if (this.j0) {
            return;
        }
        this.i0 = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        this.Z = new Handler();
        if (bundle != null) {
            this.b0 = bundle.getInt("android:style", 0);
            this.c0 = bundle.getInt("android:theme", 0);
            this.d0 = bundle.getBoolean("android:cancelable", true);
            this.e0 = bundle.getBoolean("android:showsDialog", this.e0);
            this.f0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.h0) {
            return;
        }
        B1(true, true);
    }

    @Override // androidx.fragment.app.Fragment
    public void r0() {
        super.r0();
        Dialog dialog = this.g0;
        if (dialog != null) {
            this.h0 = true;
            dialog.setOnDismissListener(null);
            this.g0.setOnCancelListener(null);
            this.g0.dismiss();
            this.g0 = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void s0() {
        super.s0();
        if (this.j0 || this.i0) {
            return;
        }
        this.i0 = true;
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater t0(Bundle bundle) {
        if (!this.e0) {
            return super.t0(bundle);
        }
        Dialog dialogE1 = E1(bundle);
        this.g0 = dialogE1;
        if (dialogE1 == null) {
            return (LayoutInflater) dialogE1.getContext().getSystemService("layout_inflater");
        }
        F1(dialogE1, this.b0);
        return (LayoutInflater) this.g0.getContext().getSystemService("layout_inflater");
    }

    public void z1() {
        B1(false, false);
    }
}
