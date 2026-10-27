package com.byd.base.view.dialog;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

/* JADX INFO: loaded from: classes.dex */
public class LeakDialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f567b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Runnable f568c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f569d = 0;
    public int e = 0;
    public boolean f = true;
    public boolean g = true;
    public int h = -1;

    @Nullable
    public Dialog i;
    public boolean j;
    public boolean k;
    public boolean l;
    public String m;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LeakDialogFragment leakDialogFragment = LeakDialogFragment.this;
            Dialog dialog = leakDialogFragment.i;
            if (dialog != null) {
                leakDialogFragment.onDismiss(dialog);
            }
        }
    }

    public class b implements DialogInterface.OnKeyListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return LeakDialogFragment.this.g(i, keyEvent);
        }
    }

    public void dismiss() {
        dismissInternal(false, false);
    }

    public void dismissInternal(boolean z, boolean z2) {
        if (this.k) {
            return;
        }
        this.k = true;
        this.l = false;
        Dialog dialog = this.i;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.i.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.f567b.getLooper()) {
                    onDismiss(this.i);
                } else {
                    this.f567b.post(this.f568c);
                }
            }
        }
        this.j = true;
        if (this.h >= 0) {
            requireFragmentManager().popBackStack(this.h, 1);
            this.h = -1;
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = requireFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.remove(this);
        if (z) {
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        } else {
            fragmentTransactionBeginTransaction.commit();
        }
    }

    public boolean g(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        dismiss();
        return true;
    }

    @Nullable
    public Dialog getDialog() {
        return this.i;
    }

    @StyleRes
    public int getTheme() {
        return this.e;
    }

    public void h(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        if (context != null) {
            sb.append(context.getPackageName());
            sb.append("/");
        }
        sb.append(str);
        this.m = sb.toString();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        Bundle bundle2;
        super.onActivityCreated(bundle);
        if (this.g) {
            View view = getView();
            if (view != null) {
                if (view.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.i.setContentView(view);
            }
            FragmentActivity activity = getActivity();
            if (activity != null) {
                this.i.setOwnerActivity(activity);
            }
            this.i.setCancelable(this.f);
            if (this.m == null) {
                this.m = getClass().getName();
            }
            this.i.getWindow().getAttributes().setTitle(this.m);
            if (bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
                return;
            }
            this.i.onRestoreInstanceState(bundle2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (this.l) {
            return;
        }
        this.k = false;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(@NonNull DialogInterface dialogInterface) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f567b = new Handler();
        if (bundle != null) {
            this.f569d = bundle.getInt("android:style", 0);
            this.e = bundle.getInt("android:theme", 0);
            this.f = bundle.getBoolean("android:cancelable", true);
            this.g = bundle.getBoolean("android:showsDialog", this.g);
            this.h = bundle.getInt("android:backStackId", -1);
        }
    }

    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        return new Dialog(requireContext(), getTheme());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Dialog dialog = this.i;
        if (dialog != null) {
            this.j = true;
            dialog.setOnDismissListener(null);
            this.i.setOnCancelListener(null);
            this.i.setOnKeyListener(null);
            this.i.dismiss();
            this.i = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (this.l || this.k) {
            return;
        }
        this.k = true;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(@NonNull DialogInterface dialogInterface) {
        if (this.j) {
            return;
        }
        dismissInternal(true, true);
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle bundle) {
        if (!this.g) {
            return super.onGetLayoutInflater(bundle);
        }
        Dialog dialogOnCreateDialog = onCreateDialog(bundle);
        this.i = dialogOnCreateDialog;
        if (dialogOnCreateDialog == null) {
            return (LayoutInflater) dialogOnCreateDialog.getContext().getSystemService("layout_inflater");
        }
        setupDialog(dialogOnCreateDialog, this.f569d);
        this.i.setOnKeyListener(new b());
        return (LayoutInflater) this.i.getContext().getSystemService("layout_inflater");
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        Bundle bundleOnSaveInstanceState;
        super.onSaveInstanceState(bundle);
        Dialog dialog = this.i;
        if (dialog != null && (bundleOnSaveInstanceState = dialog.onSaveInstanceState()) != null) {
            bundle.putBundle("android:savedDialogState", bundleOnSaveInstanceState);
        }
        int i = this.f569d;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.e;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        boolean z = this.f;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.g;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i3 = this.h;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = this.i;
        if (dialog != null) {
            this.j = false;
            dialog.show();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Dialog dialog = this.i;
        if (dialog != null) {
            dialog.hide();
        }
    }

    public void setStyle(int i, @StyleRes int i2) {
        this.f569d = i;
        if (i == 2 || i == 3) {
            this.e = R.style.Theme.Panel;
        }
        if (i2 != 0) {
            this.e = i2;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setupDialog(@NonNull Dialog dialog, int i) {
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }
}
