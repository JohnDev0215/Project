package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class E3 extends K3 implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    public int p0 = 0;
    public int q0 = 0;
    public boolean r0 = true;
    public boolean s0 = true;
    public int t0 = -1;
    public Dialog u0;
    public boolean v0;
    public boolean w0;
    public boolean x0;

    @Override // defpackage.K3
    public void B(Context context) {
        super.B(context);
        if (this.x0) {
            return;
        }
        this.w0 = false;
    }

    @Override // defpackage.K3
    public void C(Bundle bundle) {
        super.C(bundle);
        this.s0 = this.P == 0;
        if (bundle != null) {
            this.p0 = bundle.getInt("android:style", 0);
            this.q0 = bundle.getInt("android:theme", 0);
            this.r0 = bundle.getBoolean("android:cancelable", true);
            this.s0 = bundle.getBoolean("android:showsDialog", this.s0);
            this.t0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // defpackage.K3
    public void G() {
        this.X = true;
        Dialog dialog = this.u0;
        if (dialog != null) {
            this.v0 = true;
            dialog.dismiss();
            this.u0 = null;
        }
    }

    @Override // defpackage.K3
    public void H() {
        this.X = true;
        if (this.x0 || this.w0) {
            return;
        }
        this.w0 = true;
    }

    @Override // defpackage.K3
    public LayoutInflater I(Bundle bundle) {
        if (!this.s0) {
            return super.I(bundle);
        }
        C1497sc c1497sc = (C1497sc) this;
        if (c1497sc.y0 == null) {
            c1497sc.s0 = false;
        }
        Dialog dialog = c1497sc.y0;
        this.u0 = dialog;
        if (dialog == null) {
            return (LayoutInflater) this.f4J.b.getSystemService("layout_inflater");
        }
        int i = this.p0;
        if (i == 1 || i == 2) {
            dialog.requestWindowFeature(1);
        } else if (i == 3) {
            dialog.getWindow().addFlags(24);
            dialog.requestWindowFeature(1);
        }
        return (LayoutInflater) this.u0.getContext().getSystemService("layout_inflater");
    }

    @Override // defpackage.K3
    public void M(Bundle bundle) {
        Bundle bundleOnSaveInstanceState;
        Dialog dialog = this.u0;
        if (dialog != null && (bundleOnSaveInstanceState = dialog.onSaveInstanceState()) != null) {
            bundle.putBundle("android:savedDialogState", bundleOnSaveInstanceState);
        }
        int i = this.p0;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.q0;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        boolean z = this.r0;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.s0;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i3 = this.t0;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }

    @Override // defpackage.K3
    public void N() {
        this.X = true;
        Dialog dialog = this.u0;
        if (dialog != null) {
            this.v0 = false;
            dialog.show();
        }
    }

    @Override // defpackage.K3
    public void O() {
        this.X = true;
        Dialog dialog = this.u0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.v0 || this.w0) {
            return;
        }
        this.w0 = true;
        this.x0 = false;
        Dialog dialog = this.u0;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.v0 = true;
        int i = this.t0;
        if (i < 0) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.I;
            Objects.requireNonNull(layoutInflaterFactory2C0974j4);
            C3 c3 = new C3(layoutInflaterFactory2C0974j4);
            c3.b(new B3(3, this));
            c3.e(true);
            return;
        }
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j42 = this.I;
        Objects.requireNonNull(layoutInflaterFactory2C0974j42);
        if (i < 0) {
            throw new IllegalArgumentException(Z7.a("Bad id: ", i));
        }
        layoutInflaterFactory2C0974j42.M(new C0863h4(layoutInflaterFactory2C0974j42, null, i, 1), false);
        this.t0 = -1;
    }

    @Override // defpackage.K3
    public void z(Bundle bundle) {
        Bundle bundle2;
        this.X = true;
        if (this.s0) {
            View view = this.Z;
            if (view != null) {
                if (view.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.u0.setContentView(view);
            }
            O3 o3F = f();
            if (o3F != null) {
                this.u0.setOwnerActivity(o3F);
            }
            this.u0.setCancelable(this.r0);
            this.u0.setOnCancelListener(this);
            this.u0.setOnDismissListener(this);
            if (bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
                return;
            }
            this.u0.onRestoreInstanceState(bundle2);
        }
    }
}
