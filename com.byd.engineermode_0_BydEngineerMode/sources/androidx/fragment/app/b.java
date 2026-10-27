package androidx.fragment.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;

/* JADX INFO: compiled from: DialogFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private Handler Z;
    private Runnable a0 = new a();
    int b0 = 0;
    int c0 = 0;
    boolean d0 = true;
    boolean e0 = true;
    int f0 = -1;
    Dialog g0;
    boolean h0;
    boolean i0;
    boolean j0;

    /* JADX INFO: compiled from: DialogFragment.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = b.this;
            Dialog dialog = bVar.g0;
            if (dialog != null) {
                bVar.onDismiss(dialog);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void U(Bundle bundle) {
        Bundle bundle2;
        super.U(bundle);
        if (this.e0) {
            View viewK = K();
            if (viewK != null) {
                if (viewK.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.g0.setContentView(viewK);
            }
            FragmentActivity fragmentActivityI = i();
            if (fragmentActivityI != null) {
                this.g0.setOwnerActivity(fragmentActivityI);
            }
            this.g0.setCancelable(this.d0);
            this.g0.setOnCancelListener(this);
            this.g0.setOnDismissListener(this);
            if (bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
                return;
            }
            this.g0.onRestoreInstanceState(bundle2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void X(Context context) {
        super.X(context);
        if (this.j0) {
            return;
        }
        this.i0 = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void a0(Bundle bundle) {
        super.a0(bundle);
        this.Z = new Handler();
        this.e0 = this.y == 0;
        if (bundle != null) {
            this.b0 = bundle.getInt("android:style", 0);
            this.c0 = bundle.getInt("android:theme", 0);
            this.d0 = bundle.getBoolean("android:cancelable", true);
            this.e0 = bundle.getBoolean("android:showsDialog", this.e0);
            this.f0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void h0() {
        super.h0();
        Dialog dialog = this.g0;
        if (dialog != null) {
            this.h0 = true;
            dialog.setOnDismissListener(null);
            this.g0.dismiss();
            if (!this.i0) {
                onDismiss(this.g0);
            }
            this.g0 = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void i0() {
        super.i0();
        if (this.j0 || this.i0) {
            return;
        }
        this.i0 = true;
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater j0(Bundle bundle) {
        if (!this.e0) {
            return super.j0(bundle);
        }
        Dialog dialogQ1 = q1(bundle);
        this.g0 = dialogQ1;
        if (dialogQ1 == null) {
            return (LayoutInflater) this.u.i().getSystemService("layout_inflater");
        }
        r1(dialogQ1, this.b0);
        return (LayoutInflater) this.g0.getContext().getSystemService("layout_inflater");
    }

    public void n1() {
        o1(false, false);
    }

    void o1(boolean z, boolean z2) {
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
            a1().g(this.f0, 1);
            this.f0 = -1;
            return;
        }
        j jVarA = a1().a();
        jVarA.j(this);
        if (z) {
            jVarA.g();
        } else {
            jVarA.f();
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
        o1(true, true);
    }

    public Dialog p1() {
        return this.g0;
    }

    public Dialog q1(Bundle bundle) {
        throw null;
    }

    public void r1(Dialog dialog, int i) {
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public void s1(g gVar, String str) {
        this.i0 = false;
        this.j0 = true;
        j jVarA = gVar.a();
        jVarA.c(this, str);
        jVarA.f();
    }

    @Override // androidx.fragment.app.Fragment
    public void w0(Bundle bundle) {
        Bundle bundleOnSaveInstanceState;
        super.w0(bundle);
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
    public void x0() {
        super.x0();
        Dialog dialog = this.g0;
        if (dialog != null) {
            this.h0 = false;
            dialog.show();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void y0() {
        super.y0();
        Dialog dialog = this.g0;
        if (dialog != null) {
            dialog.hide();
        }
    }
}
