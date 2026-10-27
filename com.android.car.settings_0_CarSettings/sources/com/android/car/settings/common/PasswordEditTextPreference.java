package com.android.car.settings.common;

import android.content.Context;
import android.util.AttributeSet;
import com.android.car.ui.R;

/* JADX INFO: loaded from: classes5.dex */
public class PasswordEditTextPreference extends ValidatedEditTextPreference {
    public PasswordEditTextPreference(Context context) {
        super(context);
        init();
    }

    public PasswordEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public PasswordEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setDialogLayoutResource(R.layout.preference_dialog_password_edittext);
        setPersistent(false);
    }
}
