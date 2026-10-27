package com.android.car.developeroptions.notification;

import android.app.NotificationChannel;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.notification.NotificationSettingsBase;

/* JADX INFO: loaded from: classes.dex */
public class SoundPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener, PreferenceManager.OnActivityResultListener {
    private final SettingsPreferenceFragment mFragment;
    private final NotificationSettingsBase.ImportanceListener mListener;
    private NotificationSoundPreference mPreference;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "ringtone";
    }

    public SoundPreferenceController(Context context, SettingsPreferenceFragment settingsPreferenceFragment, NotificationSettingsBase.ImportanceListener importanceListener, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
        this.mFragment = settingsPreferenceFragment;
        this.mListener = importanceListener;
    }

    @Override // com.android.car.developeroptions.notification.NotificationPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return super.isAvailable() && this.mChannel != null && checkCanBeVisible(3) && !isDefaultChannel();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (NotificationSoundPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (this.mAppRow == null || this.mChannel == null) {
            return;
        }
        NotificationSoundPreference notificationSoundPreference = (NotificationSoundPreference) preference;
        notificationSoundPreference.setEnabled(this.mAdmin == null && isChannelConfigurable());
        notificationSoundPreference.setRingtone(this.mChannel.getSound());
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel == null) {
            return true;
        }
        notificationChannel.setSound((Uri) obj, notificationChannel.getAudioAttributes());
        saveChannel();
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!"ringtone".equals(preference.getKey()) || this.mFragment == null) {
            return false;
        }
        NotificationSoundPreference notificationSoundPreference = (NotificationSoundPreference) preference;
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel != null && notificationChannel.getAudioAttributes() != null) {
            if (4 == this.mChannel.getAudioAttributes().getUsage()) {
                notificationSoundPreference.setRingtoneType(4);
            } else if (6 == this.mChannel.getAudioAttributes().getUsage()) {
                notificationSoundPreference.setRingtoneType(1);
            } else {
                notificationSoundPreference.setRingtoneType(2);
            }
        }
        notificationSoundPreference.onPrepareRingtonePickerIntent(notificationSoundPreference.getIntent());
        this.mFragment.startActivityForResult(preference.getIntent(), 200);
        return true;
    }

    @Override // android.preference.PreferenceManager.OnActivityResultListener
    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (200 != i) {
            return false;
        }
        NotificationSoundPreference notificationSoundPreference = this.mPreference;
        if (notificationSoundPreference != null) {
            notificationSoundPreference.onActivityResult(i, i2, intent);
        }
        this.mListener.onImportanceChanged();
        return true;
    }

    protected static boolean hasValidSound(NotificationChannel notificationChannel) {
        return (notificationChannel == null || notificationChannel.getSound() == null || Uri.EMPTY.equals(notificationChannel.getSound())) ? false : true;
    }
}
