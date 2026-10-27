package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.view.Window;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class c extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private Handler a0;
    private boolean j0;

    @Nullable
    private Dialog l0;
    private boolean m0;
    private boolean n0;
    private boolean o0;
    private Runnable b0 = new a();
    private DialogInterface.OnCancelListener c0 = new b();
    private DialogInterface.OnDismissListener d0 = new DialogInterfaceOnDismissListenerC0022c();
    private int e0 = 0;
    private int f0 = 0;
    private boolean g0 = true;
    private boolean h0 = true;
    private int i0 = -1;
    private androidx.lifecycle.l<androidx.lifecycle.f> k0 = new d();
    private boolean p0 = false;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        @SuppressLint({"SyntheticAccessor"})
        public void run() {
            c.this.d0.onDismiss(c.this.l0);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements DialogInterface.OnCancelListener {
        b() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        @SuppressLint({"SyntheticAccessor"})
        public void onCancel(@Nullable DialogInterface dialogInterface) {
            if (c.this.l0 != null) {
                c cVar = c.this;
                cVar.onCancel(cVar.l0);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class DialogInterfaceOnDismissListenerC0022c implements DialogInterface.OnDismissListener {
        DialogInterfaceOnDismissListenerC0022c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        @SuppressLint({"SyntheticAccessor"})
        public void onDismiss(@Nullable DialogInterface dialogInterface) {
            if (c.this.l0 != null) {
                c cVar = c.this;
                cVar.onDismiss(cVar.l0);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d implements androidx.lifecycle.l<androidx.lifecycle.f> {
        d() {
        }

        @Override // androidx.lifecycle.l
        @SuppressLint({"SyntheticAccessor"})
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(androidx.lifecycle.f fVar) {
            if (fVar == null || !c.this.h0) {
                return;
            }
            View viewE1 = c.this.e1();
            if (viewE1.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            if (c.this.l0 != null) {
                if (FragmentManager.D0(3)) {
                    Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + c.this.l0);
                }
                c.this.l0.setContentView(viewE1);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class e extends androidx.fragment.app.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ androidx.fragment.app.e f679a;

        e(androidx.fragment.app.e eVar) {
            this.f679a = eVar;
        }

        @Override // androidx.fragment.app.e
        @Nullable
        public View g(int i) {
            View viewX1 = c.this.x1(i);
            if (viewX1 != null) {
                return viewX1;
            }
            if (this.f679a.i()) {
                return this.f679a.g(i);
            }
            return null;
        }

        @Override // androidx.fragment.app.e
        public boolean i() {
            return c.this.y1() || this.f679a.i();
        }
    }

    private void u1(boolean z, boolean z2) {
        if (this.n0) {
            return;
        }
        this.n0 = true;
        this.o0 = false;
        Dialog dialog = this.l0;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.l0.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.a0.getLooper()) {
                    onDismiss(this.l0);
                } else {
                    this.a0.post(this.b0);
                }
            }
        }
        this.m0 = true;
        if (this.i0 >= 0) {
            B().Q0(this.i0, 1);
            this.i0 = -1;
            return;
        }
        r rVarL = B().l();
        rVarL.k(this);
        if (z) {
            rVarL.g();
        } else {
            rVarL.f();
        }
    }

    private void z1(@Nullable Bundle bundle) {
        if (this.h0 && !this.p0) {
            try {
                this.j0 = true;
                Dialog dialogW1 = w1(bundle);
                this.l0 = dialogW1;
                if (this.h0) {
                    B1(dialogW1, this.e0);
                    Context contextR = r();
                    if (contextR instanceof Activity) {
                        this.l0.setOwnerActivity((Activity) contextR);
                    }
                    this.l0.setCancelable(this.g0);
                    this.l0.setOnCancelListener(this.c0);
                    this.l0.setOnDismissListener(this.d0);
                    this.p0 = true;
                } else {
                    this.l0 = null;
                }
            } finally {
                this.j0 = false;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void A0() {
        super.A0();
        Dialog dialog = this.l0;
        if (dialog != null) {
            this.m0 = false;
            dialog.show();
        }
    }

    public void A1(boolean z) {
        this.g0 = z;
        Dialog dialog = this.l0;
        if (dialog != null) {
            dialog.setCancelable(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void B0() {
        super.B0();
        Dialog dialog = this.l0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void B1(@NonNull Dialog dialog, int i) {
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

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void D0(@Nullable Bundle bundle) {
        Bundle bundle2;
        super.D0(bundle);
        if (this.l0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.l0.onRestoreInstanceState(bundle2);
    }

    @Override // androidx.fragment.app.Fragment
    void K0(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Bundle bundle2;
        super.K0(layoutInflater, viewGroup, bundle);
        if (this.J != null || this.l0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.l0.onRestoreInstanceState(bundle2);
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void a0(@NonNull Context context) {
        super.a0(context);
        L().h(this.k0);
        if (this.o0) {
            return;
        }
        this.n0 = false;
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void d0(@Nullable Bundle bundle) {
        super.d0(bundle);
        this.a0 = new Handler();
        this.h0 = this.z == 0;
        if (bundle != null) {
            this.e0 = bundle.getInt("android:style", 0);
            this.f0 = bundle.getInt("android:theme", 0);
            this.g0 = bundle.getBoolean("android:cancelable", true);
            this.h0 = bundle.getBoolean("android:showsDialog", this.h0);
            this.i0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    androidx.fragment.app.e e() {
        return new e(super.e());
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void k0() {
        super.k0();
        Dialog dialog = this.l0;
        if (dialog != null) {
            this.m0 = true;
            dialog.setOnDismissListener(null);
            this.l0.dismiss();
            if (!this.n0) {
                onDismiss(this.l0);
            }
            this.l0 = null;
            this.p0 = false;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void l0() {
        super.l0();
        if (!this.o0 && !this.n0) {
            this.n0 = true;
        }
        L().k(this.k0);
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public LayoutInflater m0(@Nullable Bundle bundle) {
        LayoutInflater layoutInflaterM0 = super.m0(bundle);
        if (this.h0 && !this.j0) {
            z1(bundle);
            if (FragmentManager.D0(2)) {
                Log.d("FragmentManager", "get layout inflater for DialogFragment " + this + " from dialog context");
            }
            Dialog dialog = this.l0;
            return dialog != null ? layoutInflaterM0.cloneInContext(dialog.getContext()) : layoutInflaterM0;
        }
        if (FragmentManager.D0(2)) {
            String str = "getting layout inflater for DialogFragment " + this;
            if (this.h0) {
                Log.d("FragmentManager", "mCreatingDialog = true: " + str);
            } else {
                Log.d("FragmentManager", "mShowsDialog = false: " + str);
            }
        }
        return layoutInflaterM0;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(@NonNull DialogInterface dialogInterface) {
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(@NonNull DialogInterface dialogInterface) {
        if (this.m0) {
            return;
        }
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
        }
        u1(true, true);
    }

    @StyleRes
    public int v1() {
        return this.f0;
    }

    @NonNull
    @MainThread
    public Dialog w1(@Nullable Bundle bundle) {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
        }
        return new Dialog(d1(), v1());
    }

    @Nullable
    View x1(int i) {
        Dialog dialog = this.l0;
        if (dialog != null) {
            return dialog.findViewById(i);
        }
        return null;
    }

    boolean y1() {
        return this.p0;
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void z0(@NonNull Bundle bundle) {
        super.z0(bundle);
        Dialog dialog = this.l0;
        if (dialog != null) {
            bundle.putBundle("android:savedDialogState", dialog.onSaveInstanceState());
        }
        int i = this.e0;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.f0;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        boolean z = this.g0;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.h0;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i3 = this.i0;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }
}
