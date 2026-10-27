package androidx.preference;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ComponentCallbacks2;
import android.content.Context;
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
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.preference.DialogPreference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public abstract class PreferenceDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DialogPreference f1042c;
    private CharSequence d;
    private CharSequence e;
    private CharSequence f;
    private CharSequence g;

    @LayoutRes
    private int h;
    private BitmapDrawable i;
    private int j;

    @Deprecated
    public PreferenceDialogFragment() {
    }

    private void g(Dialog dialog) {
        if (Build.VERSION.SDK_INT < 30) {
            dialog.getWindow().setSoftInputMode(5);
        } else {
            dialog.getWindow().getDecorView().getWindowInsetsController().show(WindowInsets.Type.ime());
        }
    }

    @Deprecated
    public DialogPreference a() {
        if (this.f1042c == null) {
            this.f1042c = (DialogPreference) ((DialogPreference.a) getTargetFragment()).d(getArguments().getString("key"));
        }
        return this.f1042c;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    protected boolean b() {
        return false;
    }

    @Deprecated
    protected void c(View view) {
        View viewFindViewById = view.findViewById(R.id.message);
        if (viewFindViewById != null) {
            CharSequence charSequence = this.g;
            int i = 8;
            if (!TextUtils.isEmpty(charSequence)) {
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

    @Deprecated
    protected View d(Context context) {
        int i = this.h;
        if (i == 0) {
            return null;
        }
        return LayoutInflater.from(context).inflate(i, (ViewGroup) null);
    }

    @Deprecated
    public abstract void e(boolean z);

    @Deprecated
    protected void f(AlertDialog.Builder builder) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    @Deprecated
    public void onClick(DialogInterface dialogInterface, int i) {
        this.j = i;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ComponentCallbacks2 targetFragment = getTargetFragment();
        if (!(targetFragment instanceof DialogPreference.a)) {
            throw new IllegalStateException("Target fragment must implement TargetFragment interface");
        }
        DialogPreference.a aVar = (DialogPreference.a) targetFragment;
        String string = getArguments().getString("key");
        if (bundle != null) {
            this.d = bundle.getCharSequence("PreferenceDialogFragment.title");
            this.e = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
            this.f = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
            this.g = bundle.getCharSequence("PreferenceDialogFragment.message");
            this.h = bundle.getInt("PreferenceDialogFragment.layout", 0);
            Bitmap bitmap = (Bitmap) bundle.getParcelable("PreferenceDialogFragment.icon");
            if (bitmap != null) {
                this.i = new BitmapDrawable(getResources(), bitmap);
                return;
            }
            return;
        }
        DialogPreference dialogPreference = (DialogPreference) aVar.d(string);
        this.f1042c = dialogPreference;
        this.d = dialogPreference.H0();
        this.e = this.f1042c.J0();
        this.f = this.f1042c.I0();
        this.g = this.f1042c.G0();
        this.h = this.f1042c.F0();
        Drawable drawableE0 = this.f1042c.E0();
        if (drawableE0 == null || (drawableE0 instanceof BitmapDrawable)) {
            this.i = (BitmapDrawable) drawableE0;
            return;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawableE0.getIntrinsicWidth(), drawableE0.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawableE0.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawableE0.draw(canvas);
        this.i = new BitmapDrawable(getResources(), bitmapCreateBitmap);
    }

    @Override // android.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        this.j = -2;
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(activity).setTitle(this.d).setIcon(this.i).setPositiveButton(this.e, this).setNegativeButton(this.f, this);
        View viewD = d(activity);
        if (viewD != null) {
            c(viewD);
            negativeButton.setView(viewD);
        } else {
            negativeButton.setMessage(this.g);
        }
        f(negativeButton);
        AlertDialog alertDialogCreate = negativeButton.create();
        if (b()) {
            g(alertDialogCreate);
        }
        return alertDialogCreate;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        e(this.j == -1);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", this.d);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", this.e);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", this.f);
        bundle.putCharSequence("PreferenceDialogFragment.message", this.g);
        bundle.putInt("PreferenceDialogFragment.layout", this.h);
        BitmapDrawable bitmapDrawable = this.i;
        if (bitmapDrawable != null) {
            bundle.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }
}
