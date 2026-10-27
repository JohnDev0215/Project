package com.android.car.settings.sound;

import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.drivingstate.CarUxRestrictions;
import android.car.media.CarAudioManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.SparseArray;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.apps.common.util.Themes;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.common.SeekBarPreference;
import com.android.car.settings.sound.VolumeItemParser;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class VolumeSettingsPreferenceController extends PreferenceController<PreferenceGroup> {
    private static final Logger LOG = new Logger((Class<?>) VolumeSettingsPreferenceController.class);
    private static final String VOLUME_GROUP_KEY = "volume_group_key";
    private static final String VOLUME_USAGE_KEY = "volume_usage_key";
    private Car mCar;
    private CarAudioManager mCarAudioManager;
    private final VolumeSettingsRingtoneManager mRingtoneManager;
    private final ServiceConnection mServiceConnection;
    private final Handler mUiHandler;
    final CarAudioManager.CarVolumeCallback mVolumeChangeCallback;
    private final SparseArray<VolumeItemParser.VolumeItem> mVolumeItems;
    private final List<SeekBarPreference> mVolumePreferences;

    /* JADX INFO: renamed from: com.android.car.settings.sound.VolumeSettingsPreferenceController$1, reason: invalid class name */
    class AnonymousClass1 extends CarAudioManager.CarVolumeCallback {
        AnonymousClass1() {
        }

        @Override // android.car.media.CarAudioManager.CarVolumeCallback
        public void onGroupVolumeChanged(int zoneId, int groupId, int flags) {
            if (VolumeSettingsPreferenceController.this.mCarAudioManager != null) {
                final int value = VolumeSettingsPreferenceController.this.mCarAudioManager.getGroupVolume(groupId);
                for (final SeekBarPreference volumePreference : VolumeSettingsPreferenceController.this.mVolumePreferences) {
                    Bundle extras = volumePreference.getExtras();
                    if (extras.getInt(VolumeSettingsPreferenceController.VOLUME_GROUP_KEY) == groupId) {
                        if (volumePreference.getValue() != value) {
                            VolumeSettingsPreferenceController.this.mUiHandler.post(new Runnable() { // from class: com.android.car.settings.sound.-$$Lambda$VolumeSettingsPreferenceController$1$mUf7d4seNtsyI6f7gZNzaMHdhuc
                                @Override // java.lang.Runnable
                                public final void run() {
                                    volumePreference.setValue(value);
                                }
                            });
                            return;
                        }
                        return;
                    }
                }
            }
        }

        @Override // android.car.media.CarAudioManager.CarVolumeCallback
        public void onMasterMuteChanged(int zoneId, int flags) {
        }
    }

    public VolumeSettingsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mVolumePreferences = new ArrayList();
        this.mVolumeChangeCallback = new AnonymousClass1();
        this.mServiceConnection = new ServiceConnection() { // from class: com.android.car.settings.sound.VolumeSettingsPreferenceController.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                try {
                    VolumeSettingsPreferenceController.this.mCarAudioManager = (CarAudioManager) VolumeSettingsPreferenceController.this.mCar.getCarManager("audio");
                    int volumeGroupCount = VolumeSettingsPreferenceController.this.mCarAudioManager.getVolumeGroupCount();
                    VolumeSettingsPreferenceController.this.cleanUpVolumePreferences();
                    for (int groupId = 0; groupId < volumeGroupCount; groupId++) {
                        VolumeItemParser.VolumeItem volumeItem = VolumeSettingsPreferenceController.this.getVolumeItemForUsages(VolumeSettingsPreferenceController.this.mCarAudioManager.getUsagesForVolumeGroupId(groupId));
                        if (volumeItem != null) {
                            SeekBarPreference volumePreference = VolumeSettingsPreferenceController.this.createVolumeSeekBarPreference(groupId, volumeItem.getUsage(), volumeItem.getIcon(), volumeItem.getTitle());
                            VolumeSettingsPreferenceController.this.mVolumePreferences.add(volumePreference);
                        }
                    }
                    VolumeSettingsPreferenceController.this.mCarAudioManager.registerCarVolumeCallback(VolumeSettingsPreferenceController.this.mVolumeChangeCallback);
                    VolumeSettingsPreferenceController.this.refreshUi();
                } catch (CarNotConnectedException e) {
                    VolumeSettingsPreferenceController.LOG.e("Car is not connected!", e);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                VolumeSettingsPreferenceController.this.cleanupAudioManager();
            }
        };
        this.mCar = Car.createCar(getContext(), this.mServiceConnection);
        this.mVolumeItems = VolumeItemParser.loadAudioUsageItems(context, carVolumeItemsXml());
        this.mRingtoneManager = new VolumeSettingsRingtoneManager(getContext());
        this.mUiHandler = new Handler(Looper.getMainLooper());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mCar.connect();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        this.mCar.disconnect();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        for (SeekBarPreference preference : this.mVolumePreferences) {
            preferenceGroup.addPreference(preference);
        }
    }

    int carVolumeItemsXml() {
        return R.xml.car_volume_items;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SeekBarPreference createVolumeSeekBarPreference(int volumeGroupId, int usage, int iconResId, int titleId) {
        SeekBarPreference preference = new SeekBarPreference(getContext());
        preference.setTitle(getContext().getString(titleId));
        preference.setIcon(getContext().getDrawable(iconResId));
        preference.getIcon().setTintList(Themes.getAttrColorStateList(getContext(), R.attr.iconColor));
        try {
            preference.setValue(this.mCarAudioManager.getGroupVolume(volumeGroupId));
            preference.setMin(this.mCarAudioManager.getGroupMinVolume(volumeGroupId));
            preference.setMax(this.mCarAudioManager.getGroupMaxVolume(volumeGroupId));
        } catch (CarNotConnectedException e) {
            LOG.e("Car is not connected!", e);
        }
        preference.setContinuousUpdate(true);
        preference.setShowSeekBarValue(false);
        Bundle bundle = preference.getExtras();
        bundle.putInt(VOLUME_GROUP_KEY, volumeGroupId);
        bundle.putInt(VOLUME_USAGE_KEY, usage);
        preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.sound.-$$Lambda$VolumeSettingsPreferenceController$3dEIWYLhZ8YaY43gyiThsl8jtJo
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference2, Object obj) {
                return this.f$0.lambda$createVolumeSeekBarPreference$0$VolumeSettingsPreferenceController(preference2, obj);
            }
        });
        return preference;
    }

    public /* synthetic */ boolean lambda$createVolumeSeekBarPreference$0$VolumeSettingsPreferenceController(Preference pref, Object newValue) {
        int prefGroup = pref.getExtras().getInt(VOLUME_GROUP_KEY);
        int prefUsage = pref.getExtras().getInt(VOLUME_USAGE_KEY);
        int newVolume = ((Integer) newValue).intValue();
        setGroupVolume(prefGroup, newVolume);
        this.mRingtoneManager.playAudioFeedback(prefGroup, prefUsage);
        return true;
    }

    private void setGroupVolume(int volumeGroupId, int newVolume) {
        try {
            this.mCarAudioManager.setGroupVolume(volumeGroupId, newVolume, 0);
        } catch (CarNotConnectedException e) {
            LOG.w("Ignoring volume change event because the car isn't connected", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanupAudioManager() {
        cleanUpVolumePreferences();
        this.mCarAudioManager.unregisterCarVolumeCallback(this.mVolumeChangeCallback);
        this.mCarAudioManager = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanUpVolumePreferences() {
        this.mRingtoneManager.stopCurrentRingtone();
        this.mVolumePreferences.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VolumeItemParser.VolumeItem getVolumeItemForUsages(int[] usages) {
        int rank = Integer.MAX_VALUE;
        VolumeItemParser.VolumeItem result = null;
        for (int usage : usages) {
            VolumeItemParser.VolumeItem volumeItem = this.mVolumeItems.get(usage);
            if (volumeItem != null && volumeItem.getRank() < rank) {
                rank = volumeItem.getRank();
                result = volumeItem;
            }
        }
        return result;
    }
}
