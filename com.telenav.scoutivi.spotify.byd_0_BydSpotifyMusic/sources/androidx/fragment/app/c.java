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
import androidx.lifecycle.f0;
import androidx.lifecycle.g0;

/* JADX INFO: compiled from: DialogFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private Handler f2285g0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private boolean f2294p0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    private Dialog f2296r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    private boolean f2297s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    private boolean f2298t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    private boolean f2299u0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private Runnable f2286h0 = new a();

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private DialogInterface.OnCancelListener f2287i0 = new b();

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private DialogInterface.OnDismissListener f2288j0 = new DialogInterfaceOnDismissListenerC0021c();

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private int f2289k0 = 0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private int f2290l0 = 0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private boolean f2291m0 = true;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private boolean f2292n0 = true;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    private int f2293o0 = -1;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    private androidx.lifecycle.u<androidx.lifecycle.o> f2295q0 = new d();

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    private boolean f2300v0 = false;

    /* JADX INFO: compiled from: DialogFragment.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        @SuppressLint({"SyntheticAccessor"})
        public void run() {
            c.this.f2288j0.onDismiss(c.this.f2296r0);
        }
    }

    /* JADX INFO: compiled from: DialogFragment.java */
    class b implements DialogInterface.OnCancelListener {
        b() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        @SuppressLint({"SyntheticAccessor"})
        public void onCancel(DialogInterface dialogInterface) {
            if (c.this.f2296r0 != null) {
                c cVar = c.this;
                cVar.onCancel(cVar.f2296r0);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DialogFragment.java */
    class DialogInterfaceOnDismissListenerC0021c implements DialogInterface.OnDismissListener {
        DialogInterfaceOnDismissListenerC0021c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        @SuppressLint({"SyntheticAccessor"})
        public void onDismiss(DialogInterface dialogInterface) {
            if (c.this.f2296r0 != null) {
                c cVar = c.this;
                cVar.onDismiss(cVar.f2296r0);
            }
        }
    }

    /* JADX INFO: compiled from: DialogFragment.java */
    class d implements androidx.lifecycle.u<androidx.lifecycle.o> {
        d() {
        }

        @Override // androidx.lifecycle.u
        @SuppressLint({"SyntheticAccessor"})
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(androidx.lifecycle.o oVar) {
            if (oVar == null || !c.this.f2292n0) {
                return;
            }
            View viewV1 = c.this.v1();
            if (viewV1.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            if (c.this.f2296r0 != null) {
                if (FragmentManager.F0(3)) {
                    Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + c.this.f2296r0);
                }
                c.this.f2296r0.setContentView(viewV1);
            }
        }
    }

    /* JADX INFO: compiled from: DialogFragment.java */
    class e extends f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ f f2305a;

        e(f fVar) {
            this.f2305a = fVar;
        }

        @Override // androidx.fragment.app.f
        public View f(int i7) {
            return this.f2305a.h() ? this.f2305a.f(i7) : c.this.V1(i7);
        }

        @Override // androidx.fragment.app.f
        public boolean h() {
            return this.f2305a.h() || c.this.W1();
        }
    }

    private void R1(boolean z6, boolean z7) {
        if (this.f2298t0) {
            return;
        }
        this.f2298t0 = true;
        this.f2299u0 = false;
        Dialog dialog = this.f2296r0;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.f2296r0.dismiss();
            if (!z7) {
                if (Looper.myLooper() == this.f2285g0.getLooper()) {
                    onDismiss(this.f2296r0);
                } else {
                    this.f2285g0.post(this.f2286h0);
                }
            }
        }
        this.f2297s0 = true;
        if (this.f2293o0 >= 0) {
            H().T0(this.f2293o0, 1);
            this.f2293o0 = -1;
            return;
        }
        r rVarL = H().l();
        rVarL.p(this);
        if (z6) {
            rVarL.j();
        } else {
            rVarL.i();
        }
    }

    private void X1(Bundle bundle) {
        if (this.f2292n0 && !this.f2300v0) {
            try {
                this.f2294p0 = true;
                Dialog dialogU1 = U1(bundle);
                this.f2296r0 = dialogU1;
                if (this.f2292n0) {
                    Z1(dialogU1, this.f2289k0);
                    Context contextS = s();
                    if (contextS instanceof Activity) {
                        this.f2296r0.setOwnerActivity((Activity) contextS);
                    }
                    this.f2296r0.setCancelable(this.f2291m0);
                    this.f2296r0.setOnCancelListener(this.f2287i0);
                    this.f2296r0.setOnDismissListener(this.f2288j0);
                    this.f2300v0 = true;
                } else {
                    this.f2296r0 = null;
                }
            } finally {
                this.f2294p0 = false;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void B0() {
        super.B0();
        Dialog dialog = this.f2296r0;
        if (dialog != null) {
            this.f2297s0 = true;
            dialog.setOnDismissListener(null);
            this.f2296r0.dismiss();
            if (!this.f2298t0) {
                onDismiss(this.f2296r0);
            }
            this.f2296r0 = null;
            this.f2300v0 = false;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void C0() {
        super.C0();
        if (!this.f2299u0 && !this.f2298t0) {
            this.f2298t0 = true;
        }
        Y().k(this.f2295q0);
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater D0(Bundle bundle) {
        LayoutInflater layoutInflaterD0 = super.D0(bundle);
        if (this.f2292n0 && !this.f2294p0) {
            X1(bundle);
            if (FragmentManager.F0(2)) {
                Log.d("FragmentManager", "get layout inflater for DialogFragment " + this + " from dialog context");
            }
            Dialog dialog = this.f2296r0;
            return dialog != null ? layoutInflaterD0.cloneInContext(dialog.getContext()) : layoutInflaterD0;
        }
        if (FragmentManager.F0(2)) {
            String str = "getting layout inflater for DialogFragment " + this;
            if (this.f2292n0) {
                Log.d("FragmentManager", "mCreatingDialog = true: " + str);
            } else {
                Log.d("FragmentManager", "mShowsDialog = false: " + str);
            }
        }
        return layoutInflaterD0;
    }

    @Override // androidx.fragment.app.Fragment
    public void Q0(Bundle bundle) {
        super.Q0(bundle);
        Dialog dialog = this.f2296r0;
        if (dialog != null) {
            Bundle bundleOnSaveInstanceState = dialog.onSaveInstanceState();
            bundleOnSaveInstanceState.putBoolean("android:dialogShowing", false);
            bundle.putBundle("android:savedDialogState", bundleOnSaveInstanceState);
        }
        int i7 = this.f2289k0;
        if (i7 != 0) {
            bundle.putInt("android:style", i7);
        }
        int i8 = this.f2290l0;
        if (i8 != 0) {
            bundle.putInt("android:theme", i8);
        }
        boolean z6 = this.f2291m0;
        if (!z6) {
            bundle.putBoolean("android:cancelable", z6);
        }
        boolean z7 = this.f2292n0;
        if (!z7) {
            bundle.putBoolean("android:showsDialog", z7);
        }
        int i9 = this.f2293o0;
        if (i9 != -1) {
            bundle.putInt("android:backStackId", i9);
        }
    }

    public void Q1() {
        R1(false, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void R0() {
        super.R0();
        Dialog dialog = this.f2296r0;
        if (dialog != null) {
            this.f2297s0 = false;
            dialog.show();
            View decorView = this.f2296r0.getWindow().getDecorView();
            f0.a(decorView, this);
            g0.a(decorView, this);
            androidx.savedstate.d.a(decorView, this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void S0() {
        super.S0();
        Dialog dialog = this.f2296r0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    public Dialog S1() {
        return this.f2296r0;
    }

    public int T1() {
        return this.f2290l0;
    }

    @Override // androidx.fragment.app.Fragment
    public void U0(Bundle bundle) {
        Bundle bundle2;
        super.U0(bundle);
        if (this.f2296r0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.f2296r0.onRestoreInstanceState(bundle2);
    }

    public Dialog U1(Bundle bundle) {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
        }
        return new Dialog(u1(), T1());
    }

    View V1(int i7) {
        Dialog dialog = this.f2296r0;
        if (dialog != null) {
            return dialog.findViewById(i7);
        }
        return null;
    }

    boolean W1() {
        return this.f2300v0;
    }

    public final Dialog Y1() {
        Dialog dialogS1 = S1();
        if (dialogS1 != null) {
            return dialogS1;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    public void Z1(Dialog dialog, int i7) {
        if (i7 != 1 && i7 != 2) {
            if (i7 != 3) {
                return;
            }
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public void a2(FragmentManager fragmentManager, String str) {
        this.f2298t0 = false;
        this.f2299u0 = true;
        r rVarL = fragmentManager.l();
        rVarL.e(this, str);
        rVarL.i();
    }

    @Override // androidx.fragment.app.Fragment
    void b1(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.b1(layoutInflater, viewGroup, bundle);
        if (this.M != null || this.f2296r0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.f2296r0.onRestoreInstanceState(bundle2);
    }

    @Override // androidx.fragment.app.Fragment
    f e() {
        return new e(super.e());
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.f2297s0) {
            return;
        }
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
        }
        R1(true, true);
    }

    @Override // androidx.fragment.app.Fragment
    public void r0(Context context) {
        super.r0(context);
        Y().g(this.f2295q0);
        if (this.f2299u0) {
            return;
        }
        this.f2298t0 = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void u0(Bundle bundle) {
        super.u0(bundle);
        this.f2285g0 = new Handler();
        this.f2292n0 = this.C == 0;
        if (bundle != null) {
            this.f2289k0 = bundle.getInt("android:style", 0);
            this.f2290l0 = bundle.getInt("android:theme", 0);
            this.f2291m0 = bundle.getBoolean("android:cancelable", true);
            this.f2292n0 = bundle.getBoolean("android:showsDialog", this.f2292n0);
            this.f2293o0 = bundle.getInt("android:backStackId", -1);
        }
    }
}
