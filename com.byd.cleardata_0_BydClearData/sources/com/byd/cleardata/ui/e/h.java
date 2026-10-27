package com.byd.cleardata.ui.e;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public class h extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
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

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            Dialog dialog = hVar.g0;
            if (dialog != null) {
                hVar.onDismiss(dialog);
            }
        }
    }

    void A1(boolean z, boolean z2) {
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
            k1().f(this.f0, 1);
            this.f0 = -1;
            return;
        }
        androidx.fragment.app.n nVarA = k1().a();
        nVarA.g(this);
        if (z) {
            nVarA.e();
        } else {
            nVarA.d();
        }
    }

    public Dialog B1() {
        return this.g0;
    }

    public int C1() {
        return this.c0;
    }

    public Dialog D1(Bundle bundle) {
        return new Dialog(j1(), C1());
    }

    public void E1(Dialog dialog, int i) {
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public void F1(androidx.fragment.app.i iVar, String str) {
        this.i0 = false;
        this.j0 = true;
        androidx.fragment.app.n nVarA = iVar.a();
        nVarA.b(this, str);
        nVarA.d();
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
            View viewU = U();
            if (viewU != null) {
                if (viewU.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.g0.setContentView(viewU);
            }
            androidx.fragment.app.d dVarP = p();
            if (dVarP != null) {
                this.g0.setOwnerActivity(dVarP);
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
        A1(true, true);
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
        Dialog dialogD1 = D1(bundle);
        this.g0 = dialogD1;
        if (dialogD1 != null) {
            E1(dialogD1, this.b0);
            dialogD1 = this.g0;
        }
        return (LayoutInflater) dialogD1.getContext().getSystemService("layout_inflater");
    }

    public void z1() {
        A1(false, false);
    }
}
