package androidx.preference;

import android.R;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.DialogPreference;
import defpackage.ge2;
import defpackage.r90;

/* JADX INFO: compiled from: PreferenceDialogFragmentCompat.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends r90 implements DialogInterface.OnClickListener {
    public DialogPreference p0;
    public CharSequence q0;
    public CharSequence r0;
    public CharSequence s0;
    public CharSequence t0;
    public int u0;
    public BitmapDrawable v0;
    public int w0;

    @Override // defpackage.r90
    public Dialog J0(Bundle bundle) {
        this.w0 = -2;
        AlertDialog.Builder builder = new AlertDialog.Builder(x0());
        CharSequence charSequence = this.q0;
        AlertController.AlertParams alertParams = builder.a;
        alertParams.d = charSequence;
        alertParams.c = this.v0;
        builder.g(this.r0, this);
        alertParams.i = this.s0;
        alertParams.j = this;
        x0();
        int i = this.u0;
        View viewInflate = null;
        if (i != 0) {
            LayoutInflater layoutInflaterU0 = this.O;
            if (layoutInflaterU0 == null) {
                layoutInflaterU0 = u0(null);
            }
            viewInflate = layoutInflaterU0.inflate(i, (ViewGroup) null);
        }
        if (viewInflate != null) {
            O0(viewInflate);
            alertParams.r = viewInflate;
        } else {
            alertParams.f = this.t0;
        }
        Q0(builder);
        AlertDialog alertDialogA = builder.a();
        if (this instanceof a) {
            Window window = alertDialogA.getWindow();
            if (Build.VERSION.SDK_INT >= 30) {
                window.getDecorView().getWindowInsetsController().show(WindowInsets.Type.ime());
            } else {
                R0();
            }
        }
        return alertDialogA;
    }

    @SuppressLint({"UnknownNullness"})
    public final DialogPreference N0() {
        if (this.p0 == null) {
            this.p0 = (DialogPreference) ((DialogPreference.a) Q(true)).f(w0().getString("key"));
        }
        return this.p0;
    }

    public void O0(View view) {
        int i;
        View viewFindViewById = view.findViewById(R.id.message);
        if (viewFindViewById != null) {
            CharSequence charSequence = this.t0;
            if (TextUtils.isEmpty(charSequence)) {
                i = 8;
            } else {
                if (viewFindViewById instanceof TextView) {
                    ((TextView) viewFindViewById).setText(charSequence);
                }
                i = 0;
            }
            if (viewFindViewById.getVisibility() != i) {
                viewFindViewById.setVisibility(i);
            }
        }
    }

    public abstract void P0(boolean z);

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public void c0(Bundle bundle) {
        super.c0(bundle);
        ge2 ge2VarQ = Q(true);
        if (!(ge2VarQ instanceof DialogPreference.a)) {
            throw new IllegalStateException("Target fragment must implement TargetFragment interface");
        }
        DialogPreference.a aVar = (DialogPreference.a) ge2VarQ;
        String string = w0().getString("key");
        if (bundle != null) {
            this.q0 = bundle.getCharSequence("PreferenceDialogFragment.title");
            this.r0 = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
            this.s0 = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
            this.t0 = bundle.getCharSequence("PreferenceDialogFragment.message");
            this.u0 = bundle.getInt("PreferenceDialogFragment.layout", 0);
            Bitmap bitmap = (Bitmap) bundle.getParcelable("PreferenceDialogFragment.icon");
            if (bitmap != null) {
                this.v0 = new BitmapDrawable(N(), bitmap);
                return;
            }
            return;
        }
        DialogPreference dialogPreference = (DialogPreference) aVar.f(string);
        this.p0 = dialogPreference;
        this.q0 = dialogPreference.S;
        this.r0 = dialogPreference.V;
        this.s0 = dialogPreference.W;
        this.t0 = dialogPreference.T;
        this.u0 = dialogPreference.X;
        Drawable drawable = dialogPreference.U;
        if (drawable == null || (drawable instanceof BitmapDrawable)) {
            this.v0 = (BitmapDrawable) drawable;
            return;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        this.v0 = new BitmapDrawable(N(), bitmapCreateBitmap);
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public void n0(Bundle bundle) {
        super.n0(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", this.q0);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", this.r0);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", this.s0);
        bundle.putCharSequence("PreferenceDialogFragment.message", this.t0);
        bundle.putInt("PreferenceDialogFragment.layout", this.u0);
        BitmapDrawable bitmapDrawable = this.v0;
        if (bitmapDrawable != null) {
            bundle.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.w0 = i;
    }

    @Override // defpackage.r90, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        P0(this.w0 == -1);
    }

    public void R0() {
    }

    public void Q0(AlertDialog.Builder builder) {
    }
}
