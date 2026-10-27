package com.android.inputmethodcommon;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InputMethodSettingsActivity extends PreferenceActivity implements InputMethodSettingsInterface {
    private final InputMethodSettingsImpl mSettings = new InputMethodSettingsImpl();

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(this));
        this.mSettings.init(this, getPreferenceScreen());
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setInputMethodSettingsCategoryTitle(int resId) {
        this.mSettings.setInputMethodSettingsCategoryTitle(resId);
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setInputMethodSettingsCategoryTitle(CharSequence title) {
        this.mSettings.setInputMethodSettingsCategoryTitle(title);
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setSubtypeEnablerTitle(int resId) {
        this.mSettings.setSubtypeEnablerTitle(resId);
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setSubtypeEnablerTitle(CharSequence title) {
        this.mSettings.setSubtypeEnablerTitle(title);
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setSubtypeEnablerIcon(int resId) {
        this.mSettings.setSubtypeEnablerIcon(resId);
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setSubtypeEnablerIcon(Drawable drawable) {
        this.mSettings.setSubtypeEnablerIcon(drawable);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.mSettings.updateSubtypeEnabler();
    }
}
