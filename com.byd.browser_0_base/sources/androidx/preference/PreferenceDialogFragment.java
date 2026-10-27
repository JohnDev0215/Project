package androidx.preference;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ComponentCallbacks2;
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
import androidx.preference.DialogPreference;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class PreferenceDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public DialogPreference e;
    public CharSequence f;
    public CharSequence g;
    public CharSequence h;
    public CharSequence i;
    public int j;
    public BitmapDrawable k;
    public int l;

    @Deprecated
    public PreferenceDialogFragment() {
    }

    @Deprecated
    public final DialogPreference a() {
        if (this.e == null) {
            this.e = (DialogPreference) ((DialogPreference.a) getTargetFragment()).f(getArguments().getString("key"));
        }
        return this.e;
    }

    @Deprecated
    public void b(View view) {
        int i;
        View viewFindViewById = view.findViewById(R.id.message);
        if (viewFindViewById != null) {
            CharSequence charSequence = this.i;
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

    @Deprecated
    public abstract void c(boolean z);

    @Override // android.content.DialogInterface.OnClickListener
    @Deprecated
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.l = i;
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
            this.f = bundle.getCharSequence("PreferenceDialogFragment.title");
            this.g = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
            this.h = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
            this.i = bundle.getCharSequence("PreferenceDialogFragment.message");
            this.j = bundle.getInt("PreferenceDialogFragment.layout", 0);
            Bitmap bitmap = (Bitmap) bundle.getParcelable("PreferenceDialogFragment.icon");
            if (bitmap != null) {
                this.k = new BitmapDrawable(getResources(), bitmap);
                return;
            }
            return;
        }
        DialogPreference dialogPreference = (DialogPreference) aVar.f(string);
        this.e = dialogPreference;
        this.f = dialogPreference.S;
        this.g = dialogPreference.V;
        this.h = dialogPreference.W;
        this.i = dialogPreference.T;
        this.j = dialogPreference.X;
        Drawable drawable = dialogPreference.U;
        if (drawable == null || (drawable instanceof BitmapDrawable)) {
            this.k = (BitmapDrawable) drawable;
            return;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        this.k = new BitmapDrawable(getResources(), bitmapCreateBitmap);
    }

    @Override // android.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        this.l = -2;
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(activity).setTitle(this.f).setIcon(this.k).setPositiveButton(this.g, this).setNegativeButton(this.h, this);
        int i = this.j;
        View viewInflate = i != 0 ? LayoutInflater.from(activity).inflate(i, (ViewGroup) null) : null;
        if (viewInflate != null) {
            b(viewInflate);
            negativeButton.setView(viewInflate);
        } else {
            negativeButton.setMessage(this.i);
        }
        d(negativeButton);
        AlertDialog alertDialogCreate = negativeButton.create();
        if (this instanceof EditTextPreferenceDialogFragment) {
            Window window = alertDialogCreate.getWindow();
            if (Build.VERSION.SDK_INT >= 30) {
                window.getDecorView().getWindowInsetsController().show(WindowInsets.Type.ime());
            } else {
                window.setSoftInputMode(5);
            }
        }
        return alertDialogCreate;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        c(this.l == -1);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", this.f);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", this.g);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", this.h);
        bundle.putCharSequence("PreferenceDialogFragment.message", this.i);
        bundle.putInt("PreferenceDialogFragment.layout", this.j);
        BitmapDrawable bitmapDrawable = this.k;
        if (bitmapDrawable != null) {
            bundle.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }

    @Deprecated
    public void d(AlertDialog.Builder builder) {
    }
}
