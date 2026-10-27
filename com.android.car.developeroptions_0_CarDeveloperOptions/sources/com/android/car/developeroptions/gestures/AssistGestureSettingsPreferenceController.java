package com.android.car.developeroptions.gestures;

import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class AssistGestureSettingsPreferenceController extends GesturePreferenceController {
    private static final int OFF = 0;
    private static final int ON = 1;
    private static final String PREF_KEY_VIDEO = "gesture_assist_video";
    private static final String SECURE_KEY_ASSIST = "assist_gesture_enabled";
    private static final String SECURE_KEY_SILENCE = "assist_gesture_silence_alerts_enabled";
    boolean mAssistOnly;
    private final AssistGestureFeatureProvider mFeatureProvider;
    private Preference mPreference;
    private PreferenceScreen mScreen;
    private boolean mWasAvailable;

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController
    protected String getVideoPrefKey() {
        return PREF_KEY_VIDEO;
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AssistGestureSettingsPreferenceController(Context context, String str) {
        super(context, str);
        this.mFeatureProvider = FeatureFactory.getFactory(context).getAssistGestureFeatureProvider();
        this.mWasAvailable = isAvailable();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mAssistOnly ? this.mFeatureProvider.isSupported(this.mContext) : this.mFeatureProvider.isSensorAvailable(this.mContext) ? 0 : 3;
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mScreen = preferenceScreen;
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        super.displayPreference(preferenceScreen);
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        if (this.mWasAvailable != isAvailable()) {
            updatePreference();
            this.mWasAvailable = isAvailable();
        }
    }

    public AssistGestureSettingsPreferenceController setAssistOnly(boolean z) {
        this.mAssistOnly = z;
        return this;
    }

    private void updatePreference() {
        if (this.mPreference == null) {
            return;
        }
        if (isAvailable()) {
            if (this.mScreen.findPreference(getPreferenceKey()) == null) {
                this.mScreen.addPreference(this.mPreference);
                return;
            }
            return;
        }
        this.mScreen.removePreference(this.mPreference);
    }

    private boolean isAssistGestureEnabled() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), SECURE_KEY_ASSIST, 1) != 0;
    }

    private boolean isSilenceGestureEnabled() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), SECURE_KEY_SILENCE, 1) != 0;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return Settings.Secure.putInt(this.mContext.getContentResolver(), SECURE_KEY_ASSIST, z ? 1 : 0);
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        boolean z = true;
        boolean z2 = isAssistGestureEnabled() && this.mFeatureProvider.isSupported(this.mContext);
        if (!this.mAssistOnly) {
            if (!z2 && !isSilenceGestureEnabled()) {
                z = false;
            }
            z2 = z;
        }
        return this.mContext.getText(z2 ? R.string.gesture_setting_on : R.string.gesture_setting_off);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), SECURE_KEY_ASSIST, 0) == 1;
    }
}
