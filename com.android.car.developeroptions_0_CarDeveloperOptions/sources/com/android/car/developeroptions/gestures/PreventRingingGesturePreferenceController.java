package com.android.car.developeroptions.gestures;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.widget.RadioButtonPreference;
import com.android.car.developeroptions.widget.VideoPreference;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreate;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.core.lifecycle.events.OnSaveInstanceState;

/* JADX INFO: loaded from: classes.dex */
public class PreventRingingGesturePreferenceController extends AbstractPreferenceController implements RadioButtonPreference.OnClickListener, LifecycleObserver, OnSaveInstanceState, OnResume, OnPause, OnCreate, PreferenceControllerMixin {

    @VisibleForTesting
    static final String KEY_MUTE = "prevent_ringing_option_mute";

    @VisibleForTesting
    static final String KEY_VIBRATE = "prevent_ringing_option_vibrate";
    private final Context mContext;

    @VisibleForTesting
    RadioButtonPreference mMutePref;

    @VisibleForTesting
    PreferenceCategory mPreferenceCategory;
    private SettingObserver mSettingObserver;

    @VisibleForTesting
    RadioButtonPreference mVibratePref;
    private boolean mVideoPaused;
    private VideoPreference mVideoPreference;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "gesture_prevent_ringing_category";
    }

    public String getVideoPrefKey() {
        return "gesture_prevent_ringing_video";
    }

    public PreventRingingGesturePreferenceController(Context context, Lifecycle lifecycle) {
        super(context);
        this.mContext = context;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            this.mPreferenceCategory = (PreferenceCategory) preferenceScreen.findPreference(getPreferenceKey());
            this.mVibratePref = makeRadioPreference(KEY_VIBRATE, R.string.prevent_ringing_option_vibrate);
            this.mMutePref = makeRadioPreference(KEY_MUTE, R.string.prevent_ringing_option_mute);
            if (this.mPreferenceCategory != null) {
                this.mSettingObserver = new SettingObserver(this.mPreferenceCategory);
            }
            this.mVideoPreference = (VideoPreference) preferenceScreen.findPreference(getVideoPrefKey());
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(android.R.bool.config_cecSoundbarModeDisabled_allowed);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnSaveInstanceState
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("key_video_paused", this.mVideoPaused);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPreference.OnClickListener
    public void onRadioButtonClicked(RadioButtonPreference radioButtonPreference) {
        int iKeyToSetting = keyToSetting(radioButtonPreference.getKey());
        if (iKeyToSetting != Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_hush_gesture", 1)) {
            Settings.Secure.putInt(this.mContext.getContentResolver(), "volume_hush_gesture", iKeyToSetting);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        int i = Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_hush_gesture", 1);
        boolean z = i == 1;
        boolean z2 = i == 2;
        RadioButtonPreference radioButtonPreference = this.mVibratePref;
        if (radioButtonPreference != null && radioButtonPreference.isChecked() != z) {
            this.mVibratePref.setChecked(z);
        }
        RadioButtonPreference radioButtonPreference2 = this.mMutePref;
        if (radioButtonPreference2 != null && radioButtonPreference2.isChecked() != z2) {
            this.mMutePref.setChecked(z2);
        }
        if (i == 0) {
            this.mVibratePref.setEnabled(false);
            this.mMutePref.setEnabled(false);
        } else {
            this.mVibratePref.setEnabled(true);
            this.mMutePref.setEnabled(true);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnCreate
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.mVideoPaused = bundle.getBoolean("key_video_paused", false);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(this.mContext.getContentResolver());
            this.mSettingObserver.onChange(false, null);
        }
        VideoPreference videoPreference = this.mVideoPreference;
        if (videoPreference != null) {
            videoPreference.onViewVisible(this.mVideoPaused);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.unregister(this.mContext.getContentResolver());
        }
        VideoPreference videoPreference = this.mVideoPreference;
        if (videoPreference != null) {
            this.mVideoPaused = videoPreference.isVideoPaused();
            this.mVideoPreference.onViewInvisible();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int keyToSetting(java.lang.String r4) {
        /*
            r3 = this;
            int r3 = r4.hashCode()
            r0 = 945532335(0x385badaf, float:5.2375424E-5)
            r1 = 0
            r2 = 1
            if (r3 == r0) goto L1b
            r0 = 996174361(0x3b606a19, float:0.0034242927)
            if (r3 == r0) goto L11
            goto L25
        L11:
            java.lang.String r3 = "prevent_ringing_option_vibrate"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L25
            r3 = r2
            goto L26
        L1b:
            java.lang.String r3 = "prevent_ringing_option_mute"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L25
            r3 = r1
            goto L26
        L25:
            r3 = -1
        L26:
            if (r3 == 0) goto L2c
            if (r3 == r2) goto L2b
            return r1
        L2b:
            return r2
        L2c:
            r3 = 2
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.gestures.PreventRingingGesturePreferenceController.keyToSetting(java.lang.String):int");
    }

    private RadioButtonPreference makeRadioPreference(String str, int i) {
        RadioButtonPreference radioButtonPreference = new RadioButtonPreference(this.mPreferenceCategory.getContext());
        radioButtonPreference.setKey(str);
        radioButtonPreference.setTitle(i);
        radioButtonPreference.setOnClickListener(this);
        this.mPreferenceCategory.addPreference(radioButtonPreference);
        return radioButtonPreference;
    }

    private class SettingObserver extends ContentObserver {
        private final Uri VOLUME_HUSH_GESTURE;
        private final Preference mPreference;

        public SettingObserver(Preference preference) {
            super(new Handler());
            this.VOLUME_HUSH_GESTURE = Settings.Secure.getUriFor("volume_hush_gesture");
            this.mPreference = preference;
        }

        public void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.VOLUME_HUSH_GESTURE, false, this);
        }

        public void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (uri == null || this.VOLUME_HUSH_GESTURE.equals(uri)) {
                PreventRingingGesturePreferenceController.this.updateState(this.mPreference);
            }
        }
    }
}
