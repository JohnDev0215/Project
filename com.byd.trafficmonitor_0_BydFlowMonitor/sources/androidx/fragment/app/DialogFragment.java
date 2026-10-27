package androidx.fragment.app;

import android.R;
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
import b.l.d.p;
import b.n.g;
import b.n.m;
import b.n.u;
import b.n.v;

/* JADX INFO: loaded from: classes.dex */
public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f873b;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f880i;
    public Dialog k;
    public boolean l;
    public boolean m;
    public boolean n;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f874c = new a();
    public DialogInterface.OnCancelListener mOnCancelListener = new b();
    public DialogInterface.OnDismissListener mOnDismissListener = new c();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f875d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f876e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f877f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f878g = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f879h = -1;
    public m<g> j = new d();
    public boolean o = false;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        @SuppressLint({"SyntheticAccessor"})
        public void run() {
            DialogFragment.this.mOnDismissListener.onDismiss(DialogFragment.this.k);
        }
    }

    public class b implements DialogInterface.OnCancelListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        @SuppressLint({"SyntheticAccessor"})
        public void onCancel(DialogInterface dialogInterface) {
            if (DialogFragment.this.k != null) {
                DialogFragment dialogFragment = DialogFragment.this;
                dialogFragment.onCancel(dialogFragment.k);
            }
        }
    }

    public class c implements DialogInterface.OnDismissListener {
        public c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        @SuppressLint({"SyntheticAccessor"})
        public void onDismiss(DialogInterface dialogInterface) {
            if (DialogFragment.this.k != null) {
                DialogFragment dialogFragment = DialogFragment.this;
                dialogFragment.onDismiss(dialogFragment.k);
            }
        }
    }

    public class d implements m<g> {
        public d() {
        }

        @Override // b.n.m
        @SuppressLint({"SyntheticAccessor"})
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            if (gVar == null || !DialogFragment.this.f878g) {
                return;
            }
            View viewRequireView = DialogFragment.this.requireView();
            if (viewRequireView.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            if (DialogFragment.this.k != null) {
                if (FragmentManager.G0(3)) {
                    Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + DialogFragment.this.k);
                }
                DialogFragment.this.k.setContentView(viewRequireView);
            }
        }
    }

    public class e extends b.l.d.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.l.d.d f885a;

        public e(b.l.d.d dVar) {
            this.f885a = dVar;
        }

        @Override // b.l.d.d
        public View c(int i2) {
            return this.f885a.d() ? this.f885a.c(i2) : DialogFragment.this.j(i2);
        }

        @Override // b.l.d.d
        public boolean d() {
            return this.f885a.d() || DialogFragment.this.k();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public b.l.d.d createFragmentContainer() {
        return new e(super.createFragmentContainer());
    }

    public void e() {
        f(false, false);
    }

    public final void f(boolean z, boolean z2) {
        if (this.m) {
            return;
        }
        this.m = true;
        this.n = false;
        Dialog dialog = this.k;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.k.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.f873b.getLooper()) {
                    onDismiss(this.k);
                } else {
                    this.f873b.post(this.f874c);
                }
            }
        }
        this.l = true;
        if (this.f879h >= 0) {
            getParentFragmentManager().X0(this.f879h, 1);
            this.f879h = -1;
            return;
        }
        p pVarM = getParentFragmentManager().m();
        pVarM.p(this);
        if (z) {
            pVarM.i();
        } else {
            pVarM.h();
        }
    }

    public Dialog g() {
        return this.k;
    }

    public int h() {
        return this.f876e;
    }

    public Dialog i(Bundle bundle) {
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
        }
        return new Dialog(requireContext(), h());
    }

    public View j(int i2) {
        Dialog dialog = this.k;
        if (dialog != null) {
            return dialog.findViewById(i2);
        }
        return null;
    }

    public boolean k() {
        return this.o;
    }

    public final void l(Bundle bundle) {
        if (this.f878g && !this.o) {
            try {
                this.f880i = true;
                Dialog dialogI = i(bundle);
                this.k = dialogI;
                if (this.f878g) {
                    o(dialogI, this.f875d);
                    Context context = getContext();
                    if (context instanceof Activity) {
                        this.k.setOwnerActivity((Activity) context);
                    }
                    this.k.setCancelable(this.f877f);
                    this.k.setOnCancelListener(this.mOnCancelListener);
                    this.k.setOnDismissListener(this.mOnDismissListener);
                    this.o = true;
                } else {
                    this.k = null;
                }
            } finally {
                this.f880i = false;
            }
        }
    }

    public final Dialog m() {
        Dialog dialogG = g();
        if (dialogG != null) {
            return dialogG;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    public void n(int i2, int i3) {
        if (FragmentManager.G0(2)) {
            Log.d("FragmentManager", "Setting style and theme for DialogFragment " + this + " to " + i2 + ", " + i3);
        }
        this.f875d = i2;
        if (i2 == 2 || i2 == 3) {
            this.f876e = R.style.Theme.Panel;
        }
        if (i3 != 0) {
            this.f876e = i3;
        }
    }

    public void o(Dialog dialog, int i2) {
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

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        getViewLifecycleOwnerLiveData().f(this.j);
        if (this.n) {
            return;
        }
        this.m = false;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f873b = new Handler();
        this.f878g = this.mContainerId == 0;
        if (bundle != null) {
            this.f875d = bundle.getInt("android:style", 0);
            this.f876e = bundle.getInt("android:theme", 0);
            this.f877f = bundle.getBoolean("android:cancelable", true);
            this.f878g = bundle.getBoolean("android:showsDialog", this.f878g);
            this.f879h = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Dialog dialog = this.k;
        if (dialog != null) {
            this.l = true;
            dialog.setOnDismissListener(null);
            this.k.dismiss();
            if (!this.m) {
                onDismiss(this.k);
            }
            this.k = null;
            this.o = false;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (!this.n && !this.m) {
            this.m = true;
        }
        getViewLifecycleOwnerLiveData().i(this.j);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.l) {
            return;
        }
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
        }
        f(true, true);
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        LayoutInflater layoutInflaterOnGetLayoutInflater = super.onGetLayoutInflater(bundle);
        if (this.f878g && !this.f880i) {
            l(bundle);
            if (FragmentManager.G0(2)) {
                Log.d("FragmentManager", "get layout inflater for DialogFragment " + this + " from dialog context");
            }
            Dialog dialog = this.k;
            return dialog != null ? layoutInflaterOnGetLayoutInflater.cloneInContext(dialog.getContext()) : layoutInflaterOnGetLayoutInflater;
        }
        if (FragmentManager.G0(2)) {
            String str = "getting layout inflater for DialogFragment " + this;
            if (this.f878g) {
                Log.d("FragmentManager", "mCreatingDialog = true: " + str);
            } else {
                Log.d("FragmentManager", "mShowsDialog = false: " + str);
            }
        }
        return layoutInflaterOnGetLayoutInflater;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Dialog dialog = this.k;
        if (dialog != null) {
            Bundle bundleOnSaveInstanceState = dialog.onSaveInstanceState();
            bundleOnSaveInstanceState.putBoolean("android:dialogShowing", false);
            bundle.putBundle("android:savedDialogState", bundleOnSaveInstanceState);
        }
        int i2 = this.f875d;
        if (i2 != 0) {
            bundle.putInt("android:style", i2);
        }
        int i3 = this.f876e;
        if (i3 != 0) {
            bundle.putInt("android:theme", i3);
        }
        boolean z = this.f877f;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.f878g;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i4 = this.f879h;
        if (i4 != -1) {
            bundle.putInt("android:backStackId", i4);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = this.k;
        if (dialog != null) {
            this.l = false;
            dialog.show();
            View decorView = this.k.getWindow().getDecorView();
            u.a(decorView, this);
            v.a(decorView, this);
            b.t.d.a(decorView, this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Dialog dialog = this.k;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        Bundle bundle2;
        super.onViewStateRestored(bundle);
        if (this.k == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.k.onRestoreInstanceState(bundle2);
    }

    public void p(FragmentManager fragmentManager, String str) {
        this.m = false;
        this.n = true;
        p pVarM = fragmentManager.m();
        pVarM.d(this, str);
        pVarM.j();
    }

    @Override // androidx.fragment.app.Fragment
    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.performCreateView(layoutInflater, viewGroup, bundle);
        if (this.mView != null || this.k == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.k.onRestoreInstanceState(bundle2);
    }
}
