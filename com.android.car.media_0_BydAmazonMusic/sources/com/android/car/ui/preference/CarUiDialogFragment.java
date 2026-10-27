package com.android.car.ui.preference;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.android.car.ui.preference.CarUiDialogFragment;
import java.util.Objects;
import spi.b2;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CarUiDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public static final /* synthetic */ int h = 0;
    public CharSequence a;
    public CharSequence b;
    public CharSequence c;
    public CharSequence d;

    @LayoutRes
    public int e;
    public BitmapDrawable f;
    public int g;

    public void c(@NonNull AlertDialog.Builder builder) {
    }

    @CallSuper
    public void onBindDialogView(@NonNull View view) {
        View viewD = b2.d(view, R.id.message);
        if (viewD != null) {
            CharSequence charSequence = this.d;
            int i = 8;
            if (!TextUtils.isEmpty(charSequence)) {
                if (viewD instanceof TextView) {
                    ((TextView) viewD).setText(charSequence);
                }
                i = 0;
            }
            if (viewD.getVisibility() != i) {
                viewD.setVisibility(i);
            }
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.g = i;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.a = bundle.getCharSequence("CarUiDialogFragment.title");
            this.b = bundle.getCharSequence("CarUiDialogFragment.positiveText");
            this.c = bundle.getCharSequence("CarUiDialogFragment.negativeText");
            this.d = bundle.getCharSequence("CarUiDialogFragment.message");
            this.e = bundle.getInt("CarUiDialogFragment.layout", 0);
            Bitmap bitmap = (Bitmap) bundle.getParcelable("CarUiDialogFragment.icon");
            if (bitmap != null) {
                this.f = new BitmapDrawable(getResources(), bitmap);
            }
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        FragmentActivity activity = getActivity();
        this.g = -2;
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(activity).setTitle(this.a).setIcon(this.f).setPositiveButton(this.b, this).setNegativeButton(this.c, this);
        int i = this.e;
        View viewInflate = i != 0 ? LayoutInflater.from(activity).inflate(i, (ViewGroup) null) : null;
        if (viewInflate != null) {
            onBindDialogView(viewInflate);
            negativeButton.setView(viewInflate);
        } else {
            negativeButton.setMessage(this.d);
        }
        c(negativeButton);
        final AlertDialog alertDialogCreate = negativeButton.create();
        if (this instanceof EditTextPreferenceDialogFragment) {
            alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: spi.d1
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    CarUiDialogFragment carUiDialogFragment = this.a;
                    Dialog dialog = alertDialogCreate;
                    int i2 = CarUiDialogFragment.h;
                    Objects.requireNonNull(carUiDialogFragment);
                    dialog.getWindow().setSoftInputMode(5);
                }
            });
        }
        return alertDialogCreate;
    }

    public abstract void onDialogClosed(boolean z);

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(@NonNull DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        onDialogClosed(this.g == -1);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("CarUiDialogFragment.title", this.a);
        bundle.putCharSequence("CarUiDialogFragment.positiveText", this.b);
        bundle.putCharSequence("CarUiDialogFragment.negativeText", this.c);
        bundle.putCharSequence("CarUiDialogFragment.message", this.d);
        bundle.putInt("CarUiDialogFragment.layout", this.e);
        BitmapDrawable bitmapDrawable = this.f;
        if (bitmapDrawable != null) {
            bundle.putParcelable("CarUiDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }
}
