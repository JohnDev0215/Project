package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

/* JADX INFO: compiled from: DialogFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private Handler Y;
    private Runnable Z = new a();
    private DialogInterface.OnCancelListener a0 = new DialogInterfaceOnCancelListenerC0019b();
    private DialogInterface.OnDismissListener b0 = new c();
    private int c0 = 0;
    private int d0 = 0;
    private boolean e0 = true;
    private boolean f0 = true;
    private int g0 = -1;
    private boolean h0;
    private Dialog i0;
    private boolean j0;
    private boolean k0;
    private boolean l0;

    /* JADX INFO: compiled from: DialogFragment.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        @SuppressLint({"SyntheticAccessor"})
        public void run() {
            b.this.b0.onDismiss(b.this.i0);
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DialogFragment.java */
    class DialogInterfaceOnCancelListenerC0019b implements DialogInterface.OnCancelListener {
        DialogInterfaceOnCancelListenerC0019b() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        @SuppressLint({"SyntheticAccessor"})
        public void onCancel(DialogInterface dialogInterface) {
            if (b.this.i0 != null) {
                b bVar = b.this;
                bVar.onCancel(bVar.i0);
            }
        }
    }

    /* JADX INFO: compiled from: DialogFragment.java */
    class c implements DialogInterface.OnDismissListener {
        c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        @SuppressLint({"SyntheticAccessor"})
        public void onDismiss(DialogInterface dialogInterface) {
            if (b.this.i0 != null) {
                b bVar = b.this;
                bVar.onDismiss(bVar.i0);
            }
        }
    }

    private void s1(boolean z, boolean z2) {
        if (this.k0) {
            return;
        }
        this.k0 = true;
        this.l0 = false;
        Dialog dialog = this.i0;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.i0.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.Y.getLooper()) {
                    onDismiss(this.i0);
                } else {
                    this.Y.post(this.Z);
                }
            }
        }
        this.j0 = true;
        if (this.g0 >= 0) {
            A().E0(this.g0, 1);
            this.g0 = -1;
            return;
        }
        o oVarI = A().i();
        oVarI.p(this);
        if (z) {
            oVarI.j();
        } else {
            oVarI.i();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void A0() {
        super.A0();
        Dialog dialog = this.i0;
        if (dialog != null) {
            this.j0 = false;
            dialog.show();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void B0() {
        super.B0();
        Dialog dialog = this.i0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void X(Bundle bundle) {
        Bundle bundle2;
        super.X(bundle);
        if (this.f0) {
            View viewL = L();
            if (this.i0 != null) {
                if (viewL != null) {
                    if (viewL.getParent() != null) {
                        throw new IllegalStateException("DialogFragment can not be attached to a container view");
                    }
                    this.i0.setContentView(viewL);
                }
                FragmentActivity fragmentActivityH = h();
                if (fragmentActivityH != null) {
                    this.i0.setOwnerActivity(fragmentActivityH);
                }
                this.i0.setCancelable(this.e0);
                this.i0.setOnCancelListener(this.a0);
                this.i0.setOnDismissListener(this.b0);
                if (bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
                    return;
                }
                this.i0.onRestoreInstanceState(bundle2);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void a0(Context context) {
        super.a0(context);
        if (this.l0) {
            return;
        }
        this.k0 = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void d0(Bundle bundle) {
        super.d0(bundle);
        this.Y = new Handler();
        this.f0 = this.y == 0;
        if (bundle != null) {
            this.c0 = bundle.getInt("android:style", 0);
            this.d0 = bundle.getInt("android:theme", 0);
            this.e0 = bundle.getBoolean("android:cancelable", true);
            this.f0 = bundle.getBoolean("android:showsDialog", this.f0);
            this.g0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void k0() {
        super.k0();
        Dialog dialog = this.i0;
        if (dialog != null) {
            this.j0 = true;
            dialog.setOnDismissListener(null);
            this.i0.dismiss();
            if (!this.k0) {
                onDismiss(this.i0);
            }
            this.i0 = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void l0() {
        super.l0();
        if (this.l0 || this.k0) {
            return;
        }
        this.k0 = true;
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater m0(Bundle bundle) {
        LayoutInflater layoutInflaterM0 = super.m0(bundle);
        if (!this.f0 || this.h0) {
            return layoutInflaterM0;
        }
        try {
            this.h0 = true;
            Dialog dialogV1 = v1(bundle);
            this.i0 = dialogV1;
            x1(dialogV1, this.c0);
            this.h0 = false;
            return layoutInflaterM0.cloneInContext(w1().getContext());
        } catch (Throwable th) {
            this.h0 = false;
            throw th;
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.j0) {
            return;
        }
        s1(true, true);
    }

    public void r1() {
        s1(false, false);
    }

    public Dialog t1() {
        return this.i0;
    }

    public int u1() {
        return this.d0;
    }

    public Dialog v1(Bundle bundle) {
        return new Dialog(c1(), u1());
    }

    public final Dialog w1() {
        Dialog dialogT1 = t1();
        if (dialogT1 != null) {
            return dialogT1;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    public void x1(Dialog dialog, int i2) {
        if (i2 != 1 && i2 != 2) {
            if (i2 != 3) {
                return;
            }
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public void y1(j jVar, String str) {
        this.k0 = false;
        this.l0 = true;
        o oVarI = jVar.i();
        oVarI.d(this, str);
        oVarI.i();
    }

    @Override // androidx.fragment.app.Fragment
    public void z0(Bundle bundle) {
        super.z0(bundle);
        Dialog dialog = this.i0;
        if (dialog != null) {
            bundle.putBundle("android:savedDialogState", dialog.onSaveInstanceState());
        }
        int i2 = this.c0;
        if (i2 != 0) {
            bundle.putInt("android:style", i2);
        }
        int i3 = this.d0;
        if (i3 != 0) {
            bundle.putInt("android:theme", i3);
        }
        boolean z = this.e0;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.f0;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i4 = this.g0;
        if (i4 != -1) {
            bundle.putInt("android:backStackId", i4);
        }
    }
}
