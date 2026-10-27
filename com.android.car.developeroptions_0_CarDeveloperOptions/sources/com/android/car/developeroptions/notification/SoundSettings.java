package com.android.car.developeroptions.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.preference.SeekBarVolumizer;
import android.provider.SearchIndexableResource;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.RingtonePreference;
import com.android.car.developeroptions.core.OnActivityResultListener;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.notification.VolumeSeekBarPreference;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.sound.AudioSwitchPreferenceController;
import com.android.car.developeroptions.sound.HandsFreeProfileOutputPreferenceController;
import com.android.car.developeroptions.widget.PreferenceCategoryController;
import com.android.car.developeroptions.widget.UpdatableListPreferenceDialogFragment;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SoundSettings extends DashboardFragment implements OnActivityResultListener {
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.notification.SoundSettings.2
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.sound_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };
    static final int STOP_SAMPLE = 1;
    private UpdatableListPreferenceDialogFragment mDialogFragment;
    private String mHfpOutputControllerKey;
    private RingtonePreference mRequestPreference;
    final VolumePreferenceCallback mVolumeCallback = new VolumePreferenceCallback();
    final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.android.car.developeroptions.notification.SoundSettings.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            SoundSettings.this.mVolumeCallback.stopSample();
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_sound;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "SoundSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 336;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.sound_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            String string = bundle.getString("selected_preference", null);
            if (!TextUtils.isEmpty(string)) {
                this.mRequestPreference = (RingtonePreference) findPreference(string);
            }
            this.mDialogFragment = (UpdatableListPreferenceDialogFragment) getFragmentManager().findFragmentByTag("SoundSettings");
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mVolumeCallback.stopSample();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference instanceof RingtonePreference) {
            RingtonePreference ringtonePreference = (RingtonePreference) preference;
            this.mRequestPreference = ringtonePreference;
            ringtonePreference.onPrepareRingtonePickerIntent(ringtonePreference.getIntent());
            getActivity().startActivityForResultAsUser(this.mRequestPreference.getIntent(), 200, null, UserHandle.of(this.mRequestPreference.getUserId()));
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public void onDisplayPreferenceDialog(Preference preference) {
        UpdatableListPreferenceDialogFragment updatableListPreferenceDialogFragmentNewInstance = UpdatableListPreferenceDialogFragment.newInstance(preference.getKey(), this.mHfpOutputControllerKey.equals(preference.getKey()) ? 1416 : 0);
        this.mDialogFragment = updatableListPreferenceDialogFragmentNewInstance;
        updatableListPreferenceDialogFragmentNewInstance.setTargetFragment(this, 0);
        this.mDialogFragment.show(getFragmentManager(), "SoundSettings");
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, this, getSettingsLifecycle());
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        RingtonePreference ringtonePreference = this.mRequestPreference;
        if (ringtonePreference != null) {
            ringtonePreference.onActivityResult(i, i2, intent);
            this.mRequestPreference = null;
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        RingtonePreference ringtonePreference = this.mRequestPreference;
        if (ringtonePreference != null) {
            bundle.putString("selected_preference", ringtonePreference.getKey());
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ArrayList<VolumeSeekBarPreferenceController> arrayList = new ArrayList();
        arrayList.add((VolumeSeekBarPreferenceController) use(AlarmVolumePreferenceController.class));
        arrayList.add((VolumeSeekBarPreferenceController) use(MediaVolumePreferenceController.class));
        arrayList.add((VolumeSeekBarPreferenceController) use(RingVolumePreferenceController.class));
        arrayList.add((VolumeSeekBarPreferenceController) use(NotificationVolumePreferenceController.class));
        arrayList.add((VolumeSeekBarPreferenceController) use(CallVolumePreferenceController.class));
        arrayList.add((VolumeSeekBarPreferenceController) use(RemoteVolumePreferenceController.class));
        ((HandsFreeProfileOutputPreferenceController) use(HandsFreeProfileOutputPreferenceController.class)).setCallback(new AudioSwitchPreferenceController.AudioSwitchCallback() { // from class: com.android.car.developeroptions.notification.-$$Lambda$SoundSettings$hVqX3kSWsTeqECjmf5-BpRDj50E
            @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController.AudioSwitchCallback
            public final void onPreferenceDataChanged(ListPreference listPreference) {
                this.f$0.lambda$onAttach$0$SoundSettings(listPreference);
            }
        });
        this.mHfpOutputControllerKey = ((HandsFreeProfileOutputPreferenceController) use(HandsFreeProfileOutputPreferenceController.class)).getPreferenceKey();
        for (VolumeSeekBarPreferenceController volumeSeekBarPreferenceController : arrayList) {
            volumeSeekBarPreferenceController.setCallback(this.mVolumeCallback);
            getSettingsLifecycle().addObserver(volumeSeekBarPreferenceController);
        }
    }

    final class VolumePreferenceCallback implements VolumeSeekBarPreference.Callback {
        private SeekBarVolumizer mCurrent;

        VolumePreferenceCallback() {
        }

        @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreference.Callback
        public void onSampleStarting(SeekBarVolumizer seekBarVolumizer) {
            SeekBarVolumizer seekBarVolumizer2 = this.mCurrent;
            if (seekBarVolumizer2 != null && seekBarVolumizer2 != seekBarVolumizer) {
                seekBarVolumizer2.stopSample();
            }
            this.mCurrent = seekBarVolumizer;
            if (seekBarVolumizer != null) {
                SoundSettings.this.mHandler.removeMessages(1);
                SoundSettings.this.mHandler.sendEmptyMessageDelayed(1, 2000L);
            }
        }

        @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreference.Callback
        public void onStreamValueChanged(int i, int i2) {
            if (this.mCurrent != null) {
                SoundSettings.this.mHandler.removeMessages(1);
                SoundSettings.this.mHandler.sendEmptyMessageDelayed(1, 2000L);
            }
        }

        public void stopSample() {
            SeekBarVolumizer seekBarVolumizer = this.mCurrent;
            if (seekBarVolumizer != null) {
                seekBarVolumizer.stopSample();
            }
        }
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, SoundSettings soundSettings, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PhoneRingtonePreferenceController(context));
        arrayList.add(new AlarmRingtonePreferenceController(context));
        arrayList.add(new WorkSoundPreferenceController(context, soundSettings, lifecycle));
        DialPadTonePreferenceController dialPadTonePreferenceController = new DialPadTonePreferenceController(context, soundSettings, lifecycle);
        ScreenLockSoundPreferenceController screenLockSoundPreferenceController = new ScreenLockSoundPreferenceController(context, soundSettings, lifecycle);
        ChargingSoundPreferenceController chargingSoundPreferenceController = new ChargingSoundPreferenceController(context, soundSettings, lifecycle);
        DockingSoundPreferenceController dockingSoundPreferenceController = new DockingSoundPreferenceController(context, soundSettings, lifecycle);
        TouchSoundPreferenceController touchSoundPreferenceController = new TouchSoundPreferenceController(context, soundSettings, lifecycle);
        VibrateOnTouchPreferenceController vibrateOnTouchPreferenceController = new VibrateOnTouchPreferenceController(context, soundSettings, lifecycle);
        DockAudioMediaPreferenceController dockAudioMediaPreferenceController = new DockAudioMediaPreferenceController(context, soundSettings, lifecycle);
        BootSoundPreferenceController bootSoundPreferenceController = new BootSoundPreferenceController(context);
        EmergencyTonePreferenceController emergencyTonePreferenceController = new EmergencyTonePreferenceController(context, soundSettings, lifecycle);
        arrayList.add(dialPadTonePreferenceController);
        arrayList.add(screenLockSoundPreferenceController);
        arrayList.add(chargingSoundPreferenceController);
        arrayList.add(dockingSoundPreferenceController);
        arrayList.add(touchSoundPreferenceController);
        arrayList.add(vibrateOnTouchPreferenceController);
        arrayList.add(dockAudioMediaPreferenceController);
        arrayList.add(bootSoundPreferenceController);
        arrayList.add(emergencyTonePreferenceController);
        arrayList.add(new PreferenceCategoryController(context, "other_sounds_and_vibrations_category").setChildren(Arrays.asList(dialPadTonePreferenceController, screenLockSoundPreferenceController, chargingSoundPreferenceController, dockingSoundPreferenceController, touchSoundPreferenceController, vibrateOnTouchPreferenceController, dockAudioMediaPreferenceController, bootSoundPreferenceController, emergencyTonePreferenceController)));
        return arrayList;
    }

    void enableWorkSync() {
        WorkSoundPreferenceController workSoundPreferenceController = (WorkSoundPreferenceController) use(WorkSoundPreferenceController.class);
        if (workSoundPreferenceController != null) {
            workSoundPreferenceController.enableWorkSync();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPreferenceDataChanged, reason: merged with bridge method [inline-methods] */
    public void lambda$onAttach$0$SoundSettings(ListPreference listPreference) {
        UpdatableListPreferenceDialogFragment updatableListPreferenceDialogFragment = this.mDialogFragment;
        if (updatableListPreferenceDialogFragment != null) {
            updatableListPreferenceDialogFragment.onListPreferenceUpdated(listPreference);
        }
    }
}
