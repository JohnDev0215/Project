package com.android.car.developeroptions.gestures;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Switch;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class PreventRingingSwitchPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, SwitchBar.OnSwitchChangeListener {
    private final Context mContext;
    SwitchBar mSwitch;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "gesture_prevent_ringing_switch";
    }

    public PreventRingingSwitchPreferenceController(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        LayoutPreference layoutPreference;
        super.displayPreference(preferenceScreen);
        if (!isAvailable() || (layoutPreference = (LayoutPreference) preferenceScreen.findPreference(getPreferenceKey())) == null) {
            return;
        }
        new SettingObserver(layoutPreference);
        layoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.gestures.-$$Lambda$PreventRingingSwitchPreferenceController$wpV9DzlaiD2BjXzB9UkVJ7hwKIQ
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$displayPreference$0$PreventRingingSwitchPreferenceController(preference);
            }
        });
        SwitchBar switchBar = (SwitchBar) layoutPreference.findViewById(R.id.switch_bar);
        this.mSwitch = switchBar;
        if (switchBar != null) {
            switchBar.addOnSwitchChangeListener(this);
            this.mSwitch.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$displayPreference$0$PreventRingingSwitchPreferenceController(Preference preference) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "volume_hush_gesture", (Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_hush_gesture", 1) != 0 ? 1 : 0) ^ 1);
        return true;
    }

    public void setChecked(boolean z) {
        SwitchBar switchBar = this.mSwitch;
        if (switchBar != null) {
            switchBar.setChecked(z);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        setChecked(Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_hush_gesture", 1) != 0);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(android.R.bool.config_cecSoundbarModeDisabled_allowed);
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r1, boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "volume_hush_gesture", z ? 1 : 0);
    }

    private class SettingObserver extends ContentObserver {
        private final Uri VOLUME_HUSH_GESTURE;
        private final Preference mPreference;

        public SettingObserver(Preference preference) {
            super(new Handler());
            this.VOLUME_HUSH_GESTURE = Settings.Secure.getUriFor("volume_hush_gesture");
            this.mPreference = preference;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (uri == null || this.VOLUME_HUSH_GESTURE.equals(uri)) {
                PreventRingingSwitchPreferenceController.this.updateState(this.mPreference);
            }
        }
    }
}
