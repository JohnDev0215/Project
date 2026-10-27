package com.android.car.developeroptions.development.autofill;

import android.R;
import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.android.car.developeroptions.Utils;
import com.android.settingslib.CustomEditTextPreferenceCompat;

/* JADX INFO: loaded from: classes.dex */
abstract class AbstractGlobalSettingsPreference extends CustomEditTextPreferenceCompat {
    private final int mDefaultValue;
    private final String mKey;
    private final AutofillDeveloperSettingsObserver mObserver;

    protected AbstractGlobalSettingsPreference(Context context, AttributeSet attributeSet, String str, int i) {
        super(context, attributeSet);
        this.mKey = str;
        this.mDefaultValue = i;
        this.mObserver = new AutofillDeveloperSettingsObserver(context, new Runnable() { // from class: com.android.car.developeroptions.development.autofill.-$$Lambda$AbstractGlobalSettingsPreference$wwIVUCsavOyVmz9wOWlwU3SJZ3w
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$AbstractGlobalSettingsPreference();
            }
        });
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.mObserver.register();
        lambda$new$0();
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        this.mObserver.unregister();
        super.onDetached();
    }

    private String getCurrentValue() {
        return Integer.toString(Settings.Global.getInt(getContext().getContentResolver(), this.mKey, this.mDefaultValue));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSummary, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0$AbstractGlobalSettingsPreference() {
        setSummary(getCurrentValue());
    }

    @Override // com.android.settingslib.CustomEditTextPreferenceCompat
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        EditText editText = (EditText) view.findViewById(R.id.edit);
        if (editText != null) {
            editText.setInputType(2);
            editText.setText(getCurrentValue());
            Utils.setEditTextCursorPosition(editText);
        }
    }

    @Override // com.android.settingslib.CustomEditTextPreferenceCompat
    protected void onDialogClosed(boolean z) {
        if (z) {
            String text = getText();
            int i = this.mDefaultValue;
            try {
                i = Integer.parseInt(text);
            } catch (Exception unused) {
                Log.e("AbstractGlobalSettingsPreference", "Error converting '" + text + "' to integer. Using " + this.mDefaultValue + " instead");
            }
            Settings.Global.putInt(getContext().getContentResolver(), this.mKey, i);
        }
    }
}
