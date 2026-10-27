package com.android.car.ui.preference;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.Nullable;
import androidx.preference.DialogPreference;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PreferenceDialogFragment extends CarUiDialogFragment {
    public DialogPreference i;

    public final DialogPreference getPreference() {
        if (this.i == null) {
            Bundle arguments = getArguments();
            String string = arguments == null ? null : arguments.getString("key");
            DialogPreference.TargetFragment targetFragment = (DialogPreference.TargetFragment) getTargetFragment();
            if (targetFragment != null && string != null) {
                this.i = (DialogPreference) targetFragment.findPreference(string);
            }
        }
        return this.i;
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityResultCaller targetFragment = getTargetFragment();
        if (!(targetFragment instanceof DialogPreference.TargetFragment)) {
            throw new IllegalStateException("Target fragment must implement TargetFragment interface");
        }
        DialogPreference.TargetFragment targetFragment2 = (DialogPreference.TargetFragment) targetFragment;
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString("key");
        Log.e("PreferenceDialog", "onCreate bundle == null !!!");
        if (bundle != null || string == null) {
            return;
        }
        DialogPreference dialogPreference = (DialogPreference) targetFragment2.findPreference(string);
        this.i = dialogPreference;
        if (dialogPreference != null) {
            this.a = dialogPreference.getDialogTitle();
            this.b = this.i.getPositiveButtonText();
            this.c = this.i.getNegativeButtonText();
            this.d = this.i.getDialogMessage();
            this.e = this.i.getDialogLayoutResource();
            Drawable dialogIcon = this.i.getDialogIcon();
            if (dialogIcon == null || (dialogIcon instanceof BitmapDrawable)) {
                this.f = (BitmapDrawable) dialogIcon;
                return;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(dialogIcon.getIntrinsicWidth(), dialogIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            dialogIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            dialogIcon.draw(canvas);
            this.f = new BitmapDrawable(getResources(), bitmapCreateBitmap);
        }
    }
}
