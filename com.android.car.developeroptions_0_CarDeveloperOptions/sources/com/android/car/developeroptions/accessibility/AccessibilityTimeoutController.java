package com.android.car.developeroptions.accessibility;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import androidx.lifecycle.LifecycleObserver;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.widget.RadioButtonPreference;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.google.common.primitives.Ints;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityTimeoutController extends AbstractPreferenceController implements LifecycleObserver, RadioButtonPreference.OnClickListener, PreferenceControllerMixin {
    private final Map<String, Integer> mAccessibilityTimeoutKeyToValueMap;
    private int mAccessibilityUiTimeoutValue;
    private final ContentResolver mContentResolver;
    private OnChangeListener mOnChangeListener;
    private RadioButtonPreference mPreference;
    private final String mPreferenceKey;
    private final Resources mResources;
    private final String mfragmentTag;

    public interface OnChangeListener {
        void onCheckedChanged(Preference preference);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public AccessibilityTimeoutController(Context context, Lifecycle lifecycle, String str, String str2) {
        super(context);
        this.mAccessibilityTimeoutKeyToValueMap = new HashMap();
        this.mContentResolver = context.getContentResolver();
        this.mResources = context.getResources();
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
        this.mPreferenceKey = str;
        this.mfragmentTag = str2;
    }

    protected static int getSecureAccessibilityTimeoutValue(ContentResolver contentResolver, String str) {
        Integer numTryParse;
        String string = Settings.Secure.getString(contentResolver, str);
        if (string == null || (numTryParse = Ints.tryParse(string)) == null) {
            return 0;
        }
        return numTryParse.intValue();
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.mOnChangeListener = onChangeListener;
    }

    private Map<String, Integer> getTimeoutValueToKeyMap() {
        if (this.mAccessibilityTimeoutKeyToValueMap.size() == 0) {
            String[] stringArray = null;
            if (this.mfragmentTag.equals("AccessibilityContentTimeoutPreferenceFragment")) {
                stringArray = this.mResources.getStringArray(R.array.accessibility_timeout_content_selector_keys);
            } else if (this.mfragmentTag.equals("AccessibilityControlTimeoutPreferenceFragment")) {
                stringArray = this.mResources.getStringArray(R.array.accessibility_timeout_control_selector_keys);
            }
            int[] intArray = this.mResources.getIntArray(R.array.accessibility_timeout_selector_values);
            int length = intArray.length;
            for (int i = 0; i < length; i++) {
                this.mAccessibilityTimeoutKeyToValueMap.put(stringArray[i], Integer.valueOf(intArray[i]));
            }
        }
        return this.mAccessibilityTimeoutKeyToValueMap;
    }

    private void putSecureString(String str, String str2) {
        Settings.Secure.putString(this.mContentResolver, str, str2);
    }

    private void handlePreferenceChange(String str) {
        if (this.mfragmentTag.equals("AccessibilityContentTimeoutPreferenceFragment")) {
            putSecureString("accessibility_non_interactive_ui_timeout_ms", str);
        } else if (this.mfragmentTag.equals("AccessibilityControlTimeoutPreferenceFragment")) {
            putSecureString("accessibility_interactive_ui_timeout_ms", str);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.mPreferenceKey;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        RadioButtonPreference radioButtonPreference = (RadioButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = radioButtonPreference;
        radioButtonPreference.setOnClickListener(this);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPreference.OnClickListener
    public void onRadioButtonClicked(RadioButtonPreference radioButtonPreference) {
        handlePreferenceChange(String.valueOf(getTimeoutValueToKeyMap().get(this.mPreferenceKey).intValue()));
        OnChangeListener onChangeListener = this.mOnChangeListener;
        if (onChangeListener != null) {
            onChangeListener.onCheckedChanged(this.mPreference);
        }
    }

    private int getAccessibilityTimeoutValue(String str) {
        if ("AccessibilityContentTimeoutPreferenceFragment".equals(str)) {
            return getSecureAccessibilityTimeoutValue(this.mContentResolver, "accessibility_non_interactive_ui_timeout_ms");
        }
        if ("AccessibilityControlTimeoutPreferenceFragment".equals(str)) {
            return getSecureAccessibilityTimeoutValue(this.mContentResolver, "accessibility_interactive_ui_timeout_ms");
        }
        return 0;
    }

    protected void updatePreferenceCheckedState(int i) {
        if (this.mAccessibilityUiTimeoutValue == i) {
            this.mPreference.setChecked(true);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        this.mAccessibilityUiTimeoutValue = getAccessibilityTimeoutValue(this.mfragmentTag);
        this.mPreference.setChecked(false);
        updatePreferenceCheckedState(getTimeoutValueToKeyMap().get(this.mPreference.getKey()).intValue());
    }
}
