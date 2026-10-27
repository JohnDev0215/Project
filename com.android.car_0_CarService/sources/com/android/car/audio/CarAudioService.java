package com.android.car.audio;

import android.car.Car;
import android.car.CarOccupantZoneManager;
import android.car.media.CarAudioPatchHandle;
import android.car.media.ICarAudio;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioDeviceAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioDevicePort;
import android.media.AudioFocusInfo;
import android.media.AudioGain;
import android.media.AudioGainConfig;
import android.media.AudioManager;
import android.media.AudioPatch;
import android.media.AudioPlaybackConfiguration;
import android.media.AudioPortConfig;
import android.media.audiopolicy.AudioPolicy;
import android.os.IBinder;
import android.os.Looper;
import android.os.UserHandle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import androidx.fragment.app.FragmentTransaction;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import com.android.car.CarLocalServices;
import com.android.car.CarLog;
import com.android.car.CarOccupantZoneService;
import com.android.car.CarServiceBase;
import com.android.car.Manifest;
import com.android.car.R;
import com.android.car.audio.hal.AudioControlFactory;
import com.android.car.audio.hal.AudioControlWrapper;
import com.android.car.audio.hal.AudioControlWrapperV1;
import com.android.car.audio.hal.HalAudioFocus;
import com.android.internal.util.Preconditions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
public class CarAudioService extends ICarAudio.Stub implements CarServiceBase {
    static final int DEFAULT_AUDIO_USAGE = 1;
    private static final boolean ENABLE_DELAYED_AUDIO_FOCUS = true;
    private AudioControlWrapper mAudioControlWrapper;
    private final AudioManager mAudioManager;
    private AudioPolicy mAudioPolicy;
    private SparseIntArray mAudioZoneIdToOccupantZoneIdMapping;
    private String mCarAudioConfigurationPath;
    private final CarAudioSettings mCarAudioSettings;
    private SparseArray<CarAudioZone> mCarAudioZones;
    private final Context mContext;
    private CarZonesAudioFocus mFocusHandler;
    private HalAudioFocus mHalAudioFocus;
    private CarOccupantZoneManager mOccupantZoneManager;
    private CarOccupantZoneService mOccupantZoneService;
    private final boolean mPersistMasterMuteState;
    private final TelephonyManager mTelephonyManager;
    private final boolean mUseDynamicRouting;
    private static boolean sUseCarAudioFocus = true;
    static final int DEFAULT_AUDIO_CONTEXT = CarAudioContext.getContextForUsage(1);
    private static final String[] AUDIO_CONFIGURATION_PATHS = {"/vendor/etc/car_audio_configuration.xml", "/system/etc/car_audio_configuration.xml"};
    private static final int[] SYSTEM_USAGES = {17, 1000, 1001, 1002, 1003};
    private final Object mImplLock = new Object();
    private final AudioPolicy.AudioPolicyVolumeCallback mAudioPolicyVolumeCallback = new AudioPolicy.AudioPolicyVolumeCallback() { // from class: com.android.car.audio.CarAudioService.1
        public void onVolumeAdjustment(int adjustment) {
            int groupId;
            int suggestedContext = CarAudioService.this.getSuggestedAudioContext();
            synchronized (CarAudioService.this.mImplLock) {
                groupId = CarAudioService.this.getVolumeGroupIdForAudioContextLocked(0, suggestedContext);
            }
            if (Log.isLoggable(CarLog.TAG_AUDIO, 2)) {
                Log.v(CarLog.TAG_AUDIO, "onVolumeAdjustment: " + AudioManager.adjustToString(adjustment) + " suggested audio context: " + CarAudioContext.toString(suggestedContext) + " suggested volume group: " + groupId);
            }
            int currentVolume = CarAudioService.this.getGroupVolume(0, groupId);
            if (adjustment == -100) {
                CarAudioService.this.setMasterMute(true, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                CarAudioService.this.callbackMasterMuteChange(0, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                return;
            }
            if (adjustment == -1) {
                int maxValue = currentVolume - 1;
                int minValue = Math.max(maxValue, CarAudioService.this.getGroupMinVolume(0, groupId));
                CarAudioService.this.setGroupVolume(0, groupId, minValue, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            } else if (adjustment == 1) {
                int maxValue2 = Math.min(currentVolume + 1, CarAudioService.this.getGroupMaxVolume(0, groupId));
                CarAudioService.this.setGroupVolume(0, groupId, maxValue2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            } else if (adjustment == 100) {
                CarAudioService.this.setMasterMute(false, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                CarAudioService.this.callbackMasterMuteChange(0, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            } else if (adjustment == 101) {
                CarAudioService carAudioService = CarAudioService.this;
                carAudioService.setMasterMute(true ^ carAudioService.mAudioManager.isMasterMute(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                CarAudioService.this.callbackMasterMuteChange(0, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            }
        }
    };
    private final BroadcastReceiver mLegacyVolumeChangedReceiver = new BroadcastReceiver() { // from class: com.android.car.audio.CarAudioService.2
        /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r8, android.content.Intent r9) {
            /*
                r7 = this;
                r0 = 0
                java.lang.String r1 = r9.getAction()
                int r2 = r1.hashCode()
                r3 = -1940635523(0xffffffff8c54407d, float:-1.6351292E-31)
                r4 = 1
                r5 = -1
                r6 = 0
                if (r2 == r3) goto L21
                r3 = 1170999219(0x45cc07b3, float:6528.9624)
                if (r2 == r3) goto L17
            L16:
                goto L2b
            L17:
                java.lang.String r2 = "android.media.MASTER_MUTE_CHANGED_ACTION"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L16
                r1 = r4
                goto L2c
            L21:
                java.lang.String r2 = "android.media.VOLUME_CHANGED_ACTION"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L16
                r1 = r6
                goto L2c
            L2b:
                r1 = r5
            L2c:
                if (r1 == 0) goto L37
                if (r1 == r4) goto L31
                goto L62
            L31:
                com.android.car.audio.CarAudioService r1 = com.android.car.audio.CarAudioService.this
                com.android.car.audio.CarAudioService.access$400(r1, r6, r6)
                goto L62
            L37:
                java.lang.String r1 = "android.media.EXTRA_VOLUME_STREAM_TYPE"
                int r1 = r9.getIntExtra(r1, r5)
                com.android.car.audio.CarAudioService r2 = com.android.car.audio.CarAudioService.this
                int r2 = com.android.car.audio.CarAudioService.access$600(r2, r1)
                if (r2 != r5) goto L5c
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Unknown stream type: "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                java.lang.String r4 = "CAR.AUDIO"
                android.util.Log.w(r4, r3)
                goto L62
            L5c:
                com.android.car.audio.CarAudioService r3 = com.android.car.audio.CarAudioService.this
                com.android.car.audio.CarAudioService.access$700(r3, r6, r2, r6)
            L62:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.car.audio.CarAudioService.AnonymousClass2.onReceive(android.content.Context, android.content.Intent):void");
        }
    };
    private CarOccupantZoneManager.OccupantZoneConfigChangeListener mOccupantZoneConfigChangeListener = new CarAudioOccupantConfigChangeListener();
    private Map<Integer, Integer> mUidToZoneMap = new HashMap();
    private final CarVolumeCallbackHandler mCarVolumeCallbackHandler = new CarVolumeCallbackHandler();
    private final SparseIntArray mAudioZoneIdToUserIdMapping = new SparseIntArray();

    public CarAudioService(Context context) {
        this.mContext = context;
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.mUseDynamicRouting = this.mContext.getResources().getBoolean(R.bool.audioUseDynamicRouting);
        this.mPersistMasterMuteState = this.mContext.getResources().getBoolean(R.bool.audioPersistMasterMuteState);
        this.mCarAudioSettings = new CarAudioSettings(this.mContext.getContentResolver());
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        synchronized (this.mImplLock) {
            this.mOccupantZoneService = (CarOccupantZoneService) CarLocalServices.getService(CarOccupantZoneService.class);
            Car car = new Car(this.mContext, null, null);
            this.mOccupantZoneManager = new CarOccupantZoneManager(car, this.mOccupantZoneService);
            if (this.mUseDynamicRouting) {
                setupDynamicRoutingLocked();
                setupHalAudioFocusListenerLocked();
            } else {
                Log.i(CarLog.TAG_AUDIO, "Audio dynamic routing not enabled, run in legacy mode");
                setupLegacyVolumeChangedListener();
            }
            if (this.mPersistMasterMuteState) {
                boolean storedMasterMute = this.mCarAudioSettings.getMasterMute();
                setMasterMute(storedMasterMute, 0);
            }
            this.mAudioManager.setSupportedSystemUsages(SYSTEM_USAGES);
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        synchronized (this.mImplLock) {
            if (this.mUseDynamicRouting) {
                if (this.mAudioPolicy != null) {
                    this.mAudioManager.unregisterAudioPolicyAsync(this.mAudioPolicy);
                    this.mAudioPolicy = null;
                    this.mFocusHandler.setOwningPolicy(null, null);
                    this.mFocusHandler = null;
                }
            } else {
                this.mContext.unregisterReceiver(this.mLegacyVolumeChangedReceiver);
            }
            this.mCarVolumeCallbackHandler.release();
            if (this.mHalAudioFocus != null) {
                this.mHalAudioFocus.unregisterFocusListener();
            }
            if (this.mAudioControlWrapper != null) {
                this.mAudioControlWrapper.unlinkToDeath();
                this.mAudioControlWrapper = null;
            }
        }
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*CarAudioService*");
        StringBuilder sb = new StringBuilder();
        sb.append("\tRun in legacy mode? ");
        sb.append(!this.mUseDynamicRouting);
        writer.println(sb.toString());
        writer.println("\tPersist master mute state? " + this.mPersistMasterMuteState);
        writer.println("\tMaster muted? " + this.mAudioManager.isMasterMute());
        if (this.mCarAudioConfigurationPath != null) {
            writer.println("\tCar audio configuration path: " + this.mCarAudioConfigurationPath);
        }
        writer.println();
        if (this.mUseDynamicRouting) {
            synchronized (this.mImplLock) {
                for (int i = 0; i < this.mCarAudioZones.size(); i++) {
                    CarAudioZone zone = this.mCarAudioZones.valueAt(i);
                    zone.dump("\t", writer);
                }
            }
            writer.println();
            writer.println("\tUserId to Zone Mapping:");
            for (int index = 0; index < this.mAudioZoneIdToUserIdMapping.size(); index++) {
                int audioZoneId = this.mAudioZoneIdToUserIdMapping.keyAt(index);
                writer.printf("\t\tUserId %d mapped to zone %d\n", Integer.valueOf(this.mAudioZoneIdToUserIdMapping.get(audioZoneId)), Integer.valueOf(audioZoneId));
            }
            writer.println("\tUID to Zone Mapping:");
            Iterator<Integer> it = this.mUidToZoneMap.keySet().iterator();
            while (it.hasNext()) {
                int callingId = it.next().intValue();
                writer.printf("\t\tUID %d mapped to zone %d\n", Integer.valueOf(callingId), this.mUidToZoneMap.get(Integer.valueOf(callingId)));
            }
            writer.println();
            this.mFocusHandler.dump("\t", writer);
            writer.println();
            getAudioControlWrapperLocked().dump("\t", writer);
            if (this.mHalAudioFocus != null) {
                writer.println();
                this.mHalAudioFocus.dump("\t", writer);
            } else {
                writer.println("\tNo HalAudioFocus instance\n");
            }
        }
    }

    public boolean isDynamicRoutingEnabled() {
        return this.mUseDynamicRouting;
    }

    public void setGroupVolume(int zoneId, int groupId, int index, int flags) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_VOLUME);
            callbackGroupVolumeChange(zoneId, groupId, flags);
            if (!this.mUseDynamicRouting) {
                this.mAudioManager.setStreamVolume(CarAudioDynamicRouting.STREAM_TYPES[groupId], index, flags);
            } else {
                CarVolumeGroup group = getCarVolumeGroupLocked(zoneId, groupId);
                group.setCurrentGainIndex(index);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackGroupVolumeChange(int zoneId, int groupId, int flags) {
        this.mCarVolumeCallbackHandler.onVolumeGroupChange(zoneId, groupId, flags);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMasterMute(boolean mute, int flags) {
        this.mAudioManager.setMasterMute(mute, flags);
        int keycode = mute ? 127 : 126;
        this.mAudioManager.dispatchMediaKeyEvent(new KeyEvent(0, keycode));
        this.mAudioManager.dispatchMediaKeyEvent(new KeyEvent(1, keycode));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackMasterMuteChange(int zoneId, int flags) {
        this.mCarVolumeCallbackHandler.onMasterMuteChanged(zoneId, flags);
        if (this.mPersistMasterMuteState) {
            this.mCarAudioSettings.storeMasterMute(Boolean.valueOf(this.mAudioManager.isMasterMute()));
        }
    }

    public int getGroupMaxVolume(int zoneId, int groupId) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_VOLUME);
            if (!this.mUseDynamicRouting) {
                return this.mAudioManager.getStreamMaxVolume(CarAudioDynamicRouting.STREAM_TYPES[groupId]);
            }
            CarVolumeGroup group = getCarVolumeGroupLocked(zoneId, groupId);
            return group.getMaxGainIndex();
        }
    }

    public int getGroupMinVolume(int zoneId, int groupId) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_VOLUME);
            if (!this.mUseDynamicRouting) {
                return this.mAudioManager.getStreamMinVolume(CarAudioDynamicRouting.STREAM_TYPES[groupId]);
            }
            CarVolumeGroup group = getCarVolumeGroupLocked(zoneId, groupId);
            return group.getMinGainIndex();
        }
    }

    public int getGroupVolume(int zoneId, int groupId) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_VOLUME);
            if (!this.mUseDynamicRouting) {
                return this.mAudioManager.getStreamVolume(CarAudioDynamicRouting.STREAM_TYPES[groupId]);
            }
            CarVolumeGroup group = getCarVolumeGroupLocked(zoneId, groupId);
            return group.getCurrentGainIndex();
        }
    }

    private CarVolumeGroup getCarVolumeGroupLocked(int zoneId, int groupId) {
        return getCarAudioZoneLocked(zoneId).getVolumeGroup(groupId);
    }

    private void setupLegacyVolumeChangedListener() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SystemMediaRouteProvider.LegacyImpl.VolumeChangeReceiver.VOLUME_CHANGED_ACTION);
        intentFilter.addAction("android.media.MASTER_MUTE_CHANGED_ACTION");
        this.mContext.registerReceiver(this.mLegacyVolumeChangedReceiver, intentFilter);
    }

    private List<CarAudioDeviceInfo> generateCarAudioDeviceInfos() {
        AudioDeviceInfo[] deviceInfos = this.mAudioManager.getDevices(2);
        return (List) Arrays.stream(deviceInfos).filter(new Predicate() { // from class: com.android.car.audio.-$$Lambda$CarAudioService$-o5H6HCVkMODYKE8SIWcHJiGIMQ
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CarAudioService.lambda$generateCarAudioDeviceInfos$0((AudioDeviceInfo) obj);
            }
        }).map(new Function() { // from class: com.android.car.audio.-$$Lambda$qxGvgq1Pf3Qc3xpesA5BNkquGrU
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new CarAudioDeviceInfo((AudioDeviceInfo) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$generateCarAudioDeviceInfos$0(AudioDeviceInfo info) {
        return info.getType() == 21;
    }

    private AudioDeviceInfo[] getAllInputDevices() {
        return this.mAudioManager.getDevices(1);
    }

    private SparseArray<CarAudioZone> loadCarAudioConfigurationLocked(List<CarAudioDeviceInfo> carAudioDeviceInfos) {
        AudioDeviceInfo[] inputDevices = getAllInputDevices();
        try {
            InputStream inputStream = new FileInputStream(this.mCarAudioConfigurationPath);
            try {
                CarAudioZonesHelper zonesHelper = new CarAudioZonesHelper(this.mCarAudioSettings, inputStream, carAudioDeviceInfos, inputDevices);
                this.mAudioZoneIdToOccupantZoneIdMapping = zonesHelper.getCarAudioZoneIdToOccupantZoneIdMapping();
                SparseArray<CarAudioZone> sparseArrayLoadAudioZones = zonesHelper.loadAudioZones();
                inputStream.close();
                return sparseArrayLoadAudioZones;
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | XmlPullParserException e) {
            throw new RuntimeException("Failed to parse audio zone configuration", e);
        }
    }

    private SparseArray<CarAudioZone> loadVolumeGroupConfigurationWithAudioControlLocked(List<CarAudioDeviceInfo> carAudioDeviceInfos) {
        AudioControlWrapper audioControlWrapper = getAudioControlWrapperLocked();
        if (!(audioControlWrapper instanceof AudioControlWrapperV1)) {
            throw new IllegalStateException("Updated version of IAudioControl no longer supports CarAudioZonesHelperLegacy. Please provide car_audio_configuration.xml.");
        }
        CarAudioZonesHelperLegacy legacyHelper = new CarAudioZonesHelperLegacy(this.mContext, R.xml.car_volume_groups, carAudioDeviceInfos, (AudioControlWrapperV1) audioControlWrapper, this.mCarAudioSettings);
        return legacyHelper.loadAudioZones();
    }

    private void loadCarAudioZonesLocked() {
        List<CarAudioDeviceInfo> carAudioDeviceInfos = generateCarAudioDeviceInfos();
        String audioConfigurationPath = getAudioConfigurationPath();
        this.mCarAudioConfigurationPath = audioConfigurationPath;
        if (audioConfigurationPath != null) {
            this.mCarAudioZones = loadCarAudioConfigurationLocked(carAudioDeviceInfos);
        } else {
            this.mCarAudioZones = loadVolumeGroupConfigurationWithAudioControlLocked(carAudioDeviceInfos);
        }
        CarAudioZonesValidator.validate(this.mCarAudioZones);
    }

    private void setupDynamicRoutingLocked() {
        AudioPolicy.Builder builder = new AudioPolicy.Builder(this.mContext);
        builder.setLooper(Looper.getMainLooper());
        loadCarAudioZonesLocked();
        for (int i = 0; i < this.mCarAudioZones.size(); i++) {
            CarAudioZone zone = this.mCarAudioZones.valueAt(i);
            zone.synchronizeCurrentGainIndex();
            Log.v(CarLog.TAG_AUDIO, "Processed audio zone: " + zone);
        }
        CarAudioDynamicRouting.setupAudioDynamicRouting(builder, this.mCarAudioZones);
        builder.setAudioPolicyVolumeCallback(this.mAudioPolicyVolumeCallback);
        if (sUseCarAudioFocus) {
            CarZonesAudioFocus carZonesAudioFocus = new CarZonesAudioFocus(this.mAudioManager, this.mContext.getPackageManager(), this.mCarAudioZones, this.mCarAudioSettings, true);
            this.mFocusHandler = carZonesAudioFocus;
            builder.setAudioPolicyFocusListener(carZonesAudioFocus);
            builder.setIsAudioFocusPolicy(true);
        }
        AudioPolicy audioPolicyBuild = builder.build();
        this.mAudioPolicy = audioPolicyBuild;
        if (sUseCarAudioFocus) {
            this.mFocusHandler.setOwningPolicy(this, audioPolicyBuild);
        }
        int r = this.mAudioManager.registerAudioPolicy(this.mAudioPolicy);
        if (r != 0) {
            throw new RuntimeException("registerAudioPolicy failed " + r);
        }
        setupOccupantZoneInfo();
    }

    private void setupOccupantZoneInfo() {
        SparseIntArray audioZoneIdToOccupantZoneMapping;
        CarOccupantZoneService occupantZoneService;
        CarOccupantZoneManager occupantZoneManager;
        CarOccupantZoneManager.OccupantZoneConfigChangeListener listener;
        synchronized (this.mImplLock) {
            audioZoneIdToOccupantZoneMapping = this.mAudioZoneIdToOccupantZoneIdMapping;
            occupantZoneService = this.mOccupantZoneService;
            occupantZoneManager = this.mOccupantZoneManager;
            listener = this.mOccupantZoneConfigChangeListener;
        }
        occupantZoneService.setAudioZoneIdsForOccupantZoneIds(audioZoneIdToOccupantZoneMapping);
        occupantZoneManager.registerOccupantZoneConfigChangeListener(listener);
    }

    private void setupHalAudioFocusListenerLocked() {
        AudioControlWrapper audioControlWrapper = getAudioControlWrapperLocked();
        if (!audioControlWrapper.supportsHalAudioFocus()) {
            Log.d(CarLog.TAG_AUDIO, "HalAudioFocus is not supported on this device");
            return;
        }
        HalAudioFocus halAudioFocus = new HalAudioFocus(this.mAudioManager, this.mAudioControlWrapper, getAudioZoneIds());
        this.mHalAudioFocus = halAudioFocus;
        halAudioFocus.registerFocusListener();
    }

    private String getAudioConfigurationPath() {
        for (String path : AUDIO_CONFIGURATION_PATHS) {
            File configuration = new File(path);
            if (configuration.exists()) {
                return path;
            }
        }
        return null;
    }

    public void setFadeTowardFront(float value) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_VOLUME);
            getAudioControlWrapperLocked().setFadeTowardFront(value);
        }
    }

    public void setBalanceTowardRight(float value) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_VOLUME);
            getAudioControlWrapperLocked().setBalanceTowardRight(value);
        }
    }

    public String[] getExternalSources() {
        String[] strArr;
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_SETTINGS);
            List<String> sourceAddresses = new ArrayList<>();
            AudioDeviceInfo[] devices = this.mAudioManager.getDevices(1);
            if (devices.length == 0) {
                Log.w(CarLog.TAG_AUDIO, "getExternalSources, no input devices found.");
            }
            for (AudioDeviceInfo info : devices) {
                switch (info.getType()) {
                    case 5:
                    case 6:
                    case 9:
                    case 11:
                    case 12:
                    case 14:
                    case 16:
                    case 17:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                        String address = info.getAddress();
                        if (TextUtils.isEmpty(address)) {
                            Log.w(CarLog.TAG_AUDIO, "Discarded device with empty address, type=" + info.getType());
                        } else {
                            sourceAddresses.add(address);
                        }
                        break;
                }
            }
            strArr = (String[]) sourceAddresses.toArray(new String[0]);
        }
        return strArr;
    }

    public CarAudioPatchHandle createAudioPatch(String sourceAddress, int usage, int gainInMillibels) {
        CarAudioPatchHandle carAudioPatchHandleCreateAudioPatchLocked;
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_SETTINGS);
            carAudioPatchHandleCreateAudioPatchLocked = createAudioPatchLocked(sourceAddress, usage, gainInMillibels);
        }
        return carAudioPatchHandleCreateAudioPatchLocked;
    }

    public void releaseAudioPatch(CarAudioPatchHandle carPatch) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_SETTINGS);
            releaseAudioPatchLocked(carPatch);
        }
    }

    private CarAudioPatchHandle createAudioPatchLocked(String sourceAddress, int usage, int gainInMillibels) {
        AudioDeviceInfo sourcePortInfo = null;
        AudioDeviceInfo[] deviceInfos = this.mAudioManager.getDevices(1);
        int length = deviceInfos.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            AudioDeviceInfo info = deviceInfos[i];
            if (!sourceAddress.equals(info.getAddress())) {
                i++;
            } else {
                sourcePortInfo = info;
                break;
            }
        }
        Objects.requireNonNull(sourcePortInfo, "Specified source is not available: " + sourceAddress);
        AudioDevicePort audioPort = getAudioPort(usage);
        Objects.requireNonNull(audioPort, "Sink not available for usage: " + AudioAttributes.usageToString(usage));
        AudioDevicePort sinkPort = audioPort;
        AudioPortConfig sinkConfig = sinkPort.buildConfig(0, 1, 1, (AudioGainConfig) null);
        Log.d(CarLog.TAG_AUDIO, "createAudioPatch sinkConfig: " + sinkConfig);
        CarAudioDeviceInfo helper = new CarAudioDeviceInfo(sourcePortInfo);
        AudioGain audioGain = helper.getAudioGain();
        Objects.requireNonNull(audioGain, "Gain controller not available for source port");
        AudioGain audioGain2 = audioGain;
        AudioGainConfig audioGainConfig = audioGain2.buildConfig(1, audioGain2.channelMask(), new int[]{gainInMillibels}, 0);
        AudioPortConfig sourceConfig = sourcePortInfo.getPort().buildConfig(0, 1, 1, audioGainConfig);
        AudioPatch[] patch = {null};
        int result = AudioManager.createAudioPatch(patch, new AudioPortConfig[]{sourceConfig}, new AudioPortConfig[]{sinkConfig});
        if (result != 0) {
            throw new RuntimeException("createAudioPatch failed with code " + result);
        }
        Objects.requireNonNull(patch[0], "createAudioPatch didn't provide expected single handle");
        Log.d(CarLog.TAG_AUDIO, "Audio patch created: " + patch[0]);
        int groupId = getVolumeGroupIdForUsage(0, usage);
        setGroupVolume(0, groupId, getGroupVolume(0, groupId), 0);
        return new CarAudioPatchHandle(patch[0]);
    }

    private void releaseAudioPatchLocked(CarAudioPatchHandle carPatch) {
        Objects.requireNonNull(carPatch);
        ArrayList<AudioPatch> patches = new ArrayList<>();
        int result = AudioManager.listAudioPatches(patches);
        if (result != 0) {
            throw new RuntimeException("listAudioPatches failed with code " + result);
        }
        for (AudioPatch patch : patches) {
            if (carPatch.represents(patch)) {
                int result2 = AudioManager.releaseAudioPatch(patch);
                if (result2 != 0) {
                    throw new RuntimeException("releaseAudioPatch failed with code " + result2);
                }
                return;
            }
        }
        Log.e(CarLog.TAG_AUDIO, "releaseAudioPatch found no match for " + carPatch);
    }

    public int getVolumeGroupCount(int zoneId) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_VOLUME);
            if (this.mUseDynamicRouting) {
                return getCarAudioZoneLocked(zoneId).getVolumeGroupCount();
            }
            return CarAudioDynamicRouting.STREAM_TYPES.length;
        }
    }

    public int getVolumeGroupIdForUsage(int zoneId, int usage) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_VOLUME);
            if (!this.mUseDynamicRouting) {
                for (int i = 0; i < CarAudioDynamicRouting.STREAM_TYPE_USAGES.length; i++) {
                    if (usage == CarAudioDynamicRouting.STREAM_TYPE_USAGES[i]) {
                        return i;
                    }
                }
                return -1;
            }
            int audioContext = CarAudioContext.getContextForUsage(usage);
            return getVolumeGroupIdForAudioContextLocked(zoneId, audioContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getVolumeGroupIdForAudioContextLocked(int zoneId, int audioContext) {
        CarVolumeGroup[] groups = getCarAudioZoneLocked(zoneId).getVolumeGroups();
        for (int i = 0; i < groups.length; i++) {
            int[] groupAudioContexts = groups[i].getContexts();
            for (int groupAudioContext : groupAudioContexts) {
                if (audioContext == groupAudioContext) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int[] getUsagesForVolumeGroupId(int zoneId, int groupId) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_VOLUME);
            if (!this.mUseDynamicRouting) {
                return new int[]{CarAudioDynamicRouting.STREAM_TYPE_USAGES[groupId]};
            }
            CarVolumeGroup group = getCarVolumeGroupLocked(zoneId, groupId);
            Set<Integer> contexts = (Set) Arrays.stream(group.getContexts()).boxed().collect(Collectors.toSet());
            List<Integer> usages = new ArrayList<>();
            Iterator<Integer> it = contexts.iterator();
            while (it.hasNext()) {
                int context = it.next().intValue();
                int[] usagesForContext = CarAudioContext.getUsagesForContext(context);
                for (int usage : usagesForContext) {
                    usages.add(Integer.valueOf(usage));
                }
            }
            return usages.stream().mapToInt(new ToIntFunction() { // from class: com.android.car.audio.-$$Lambda$CarAudioService$7ozJt1tz-Yv6XmZZvR16HbSWjvc
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((Integer) obj).intValue();
                }
            }).toArray();
        }
    }

    public int[] getAudioZoneIds() {
        int[] zoneIds;
        enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_SETTINGS);
        requireDynamicRouting();
        synchronized (this.mImplLock) {
            zoneIds = new int[this.mCarAudioZones.size()];
            for (int i = 0; i < this.mCarAudioZones.size(); i++) {
                zoneIds[i] = this.mCarAudioZones.keyAt(i);
            }
        }
        return zoneIds;
    }

    public int getZoneIdForUid(int uid) {
        enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_SETTINGS);
        requireDynamicRouting();
        synchronized (this.mImplLock) {
            if (this.mUidToZoneMap.containsKey(Integer.valueOf(uid))) {
                return this.mUidToZoneMap.get(Integer.valueOf(uid)).intValue();
            }
            int userId = UserHandle.getUserId(uid);
            return getZoneIdForUserIdLocked(userId);
        }
    }

    private int getZoneIdForUserIdLocked(int userId) {
        CarOccupantZoneService carOccupantZoneService = this.mOccupantZoneService;
        int audioZoneId = carOccupantZoneService.getAudioZoneIdForOccupant(carOccupantZoneService.getOccupantZoneIdForUserId(userId));
        if (audioZoneId != -1) {
            return audioZoneId;
        }
        Log.w(CarLog.TAG_AUDIO, "getZoneIdForUid userId " + userId + " does not have a zone. Defaulting to PRIMARY_AUDIO_ZONE:0");
        return 0;
    }

    public boolean setZoneIdForUid(int zoneId, int uid) {
        enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_SETTINGS);
        requireDynamicRouting();
        synchronized (this.mImplLock) {
            checkAudioZoneIdLocked(zoneId);
            Log.i(CarLog.TAG_AUDIO, "setZoneIdForUid Calling uid " + uid + " mapped to : " + zoneId);
            requiredOccupantZoneMappingDisabledLocked();
            Integer currentZoneId = this.mUidToZoneMap.get(Integer.valueOf(uid));
            ArrayList<AudioFocusInfo> currentFocusHoldersForUid = new ArrayList<>();
            ArrayList<AudioFocusInfo> currentFocusLosersForUid = new ArrayList<>();
            if (currentZoneId != null) {
                currentFocusHoldersForUid = this.mFocusHandler.getAudioFocusHoldersForUid(uid, currentZoneId.intValue());
                currentFocusLosersForUid = this.mFocusHandler.getAudioFocusLosersForUid(uid, currentZoneId.intValue());
                if (!currentFocusHoldersForUid.isEmpty() || !currentFocusLosersForUid.isEmpty()) {
                    this.mFocusHandler.transientlyLoseInFocusInZone(currentFocusLosersForUid, currentZoneId.intValue());
                    this.mFocusHandler.transientlyLoseInFocusInZone(currentFocusHoldersForUid, currentZoneId.intValue());
                }
            }
            if (checkAndRemoveUidLocked(uid) && setZoneIdForUidNoCheckLocked(zoneId, uid)) {
                if (!currentFocusLosersForUid.isEmpty()) {
                    regainAudioFocusLocked(currentFocusLosersForUid, zoneId);
                }
                if (!currentFocusHoldersForUid.isEmpty()) {
                    regainAudioFocusLocked(currentFocusHoldersForUid, zoneId);
                }
                return true;
            }
            return false;
        }
    }

    public String getOutputDeviceAddressForUsage(int zoneId, int usage) {
        enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_SETTINGS);
        requireDynamicRouting();
        int contextForUsage = CarAudioContext.getContextForUsage(usage);
        Preconditions.checkArgument(contextForUsage != 0, "Invalid audio attribute usage %d", new Object[]{Integer.valueOf(usage)});
        return getCarAudioZone(zoneId).getAddressForContext(contextForUsage);
    }

    void regainAudioFocusLocked(ArrayList<AudioFocusInfo> afiList, int zoneId) {
        for (AudioFocusInfo info : afiList) {
            if (this.mFocusHandler.reevaluateAndRegainAudioFocus(info) != 1) {
                Log.i(CarLog.TAG_AUDIO, " Focus could not be granted for entry " + info.getClientId() + " uid " + info.getClientUid() + " in zone " + zoneId);
            }
        }
    }

    public boolean clearZoneIdForUid(int uid) {
        boolean zCheckAndRemoveUidLocked;
        enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_SETTINGS);
        requireDynamicRouting();
        synchronized (this.mImplLock) {
            requiredOccupantZoneMappingDisabledLocked();
            zCheckAndRemoveUidLocked = checkAndRemoveUidLocked(uid);
        }
        return zCheckAndRemoveUidLocked;
    }

    private boolean setZoneIdForUidNoCheckLocked(int zoneId, int uid) {
        Log.d(CarLog.TAG_AUDIO, "setZoneIdForUidNoCheck Calling uid " + uid + " mapped to " + zoneId);
        List<AudioDeviceInfo> deviceInfos = getCarAudioZoneLocked(zoneId).getAudioDeviceInfos();
        if (this.mAudioPolicy.setUidDeviceAffinity(uid, deviceInfos)) {
            this.mUidToZoneMap.put(Integer.valueOf(uid), Integer.valueOf(zoneId));
            return true;
        }
        Log.w(CarLog.TAG_AUDIO, "setZoneIdForUidNoCheck Failed set device affinity for uid " + uid + " in zone " + zoneId);
        return false;
    }

    private boolean checkAndRemoveUidLocked(int uid) {
        Integer zoneId = this.mUidToZoneMap.get(Integer.valueOf(uid));
        if (zoneId == null) {
            return true;
        }
        Log.i(CarLog.TAG_AUDIO, "checkAndRemoveUid removing Calling uid " + uid + " from zone " + zoneId);
        if (this.mAudioPolicy.removeUidDeviceAffinity(uid)) {
            this.mUidToZoneMap.remove(Integer.valueOf(uid));
            return true;
        }
        Log.w(CarLog.TAG_AUDIO, "checkAndRemoveUid Failed remove device affinity for uid " + uid + " in zone " + zoneId);
        return false;
    }

    public void registerVolumeCallback(IBinder binder) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_VOLUME);
            this.mCarVolumeCallbackHandler.registerCallback(binder);
        }
    }

    public void unregisterVolumeCallback(IBinder binder) {
        synchronized (this.mImplLock) {
            enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_VOLUME);
            this.mCarVolumeCallbackHandler.unregisterCallback(binder);
        }
    }

    public List<AudioDeviceAttributes> getInputDevicesForZoneId(int zoneId) {
        enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_SETTINGS);
        requireDynamicRouting();
        return getCarAudioZone(zoneId).getInputAudioDevices();
    }

    private void enforcePermission(String permissionName) {
        if (this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    private void requireDynamicRouting() {
        Preconditions.checkState(this.mUseDynamicRouting, "Dynamic routing is required");
    }

    private void requiredOccupantZoneMappingDisabledLocked() {
        if (isOccupantZoneMappingAvailableLocked()) {
            throw new IllegalStateException("UID based routing is not supported while using occupant zone mapping");
        }
    }

    private AudioDevicePort getAudioPort(int usage) {
        int groupId = getVolumeGroupIdForUsage(0, usage);
        CarVolumeGroup carVolumeGroupLocked = getCarVolumeGroupLocked(0, groupId);
        Objects.requireNonNull(carVolumeGroupLocked, "Can not find CarVolumeGroup by usage: " + AudioAttributes.usageToString(usage));
        CarVolumeGroup group = carVolumeGroupLocked;
        return group.getAudioDevicePortForContext(CarAudioContext.getContextForUsage(usage));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSuggestedAudioContext() {
        int callState = this.mTelephonyManager.getCallState();
        List<AudioPlaybackConfiguration> configurations = this.mAudioManager.getActivePlaybackConfigurations();
        return CarVolume.getSuggestedAudioContext(configurations, callState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getVolumeGroupIdForStreamType(int streamType) {
        for (int i = 0; i < CarAudioDynamicRouting.STREAM_TYPES.length; i++) {
            if (streamType == CarAudioDynamicRouting.STREAM_TYPES[i]) {
                int groupId = i;
                return groupId;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOccupantZoneUserChanged() {
        int driverUserId = this.mOccupantZoneService.getDriverUserId();
        synchronized (this.mImplLock) {
            if (!isOccupantZoneMappingAvailableLocked()) {
                for (int i = 0; i < this.mCarAudioZones.size(); i++) {
                    CarAudioZone zone = this.mCarAudioZones.valueAt(i);
                    zone.updateVolumeGroupsForUser(driverUserId);
                    this.mFocusHandler.updateUserForZoneId(zone.getId(), driverUserId);
                }
                return;
            }
            int occupantZoneForDriver = getOccupantZoneIdForDriver();
            for (int index = 0; index < this.mAudioZoneIdToOccupantZoneIdMapping.size(); index++) {
                int audioZoneId = this.mAudioZoneIdToOccupantZoneIdMapping.keyAt(index);
                int occupantZoneId = this.mAudioZoneIdToOccupantZoneIdMapping.get(audioZoneId);
                updateUserForOccupantZoneLocked(occupantZoneId, audioZoneId, driverUserId, occupantZoneForDriver);
            }
        }
    }

    private boolean isOccupantZoneMappingAvailableLocked() {
        return this.mAudioZoneIdToOccupantZoneIdMapping.size() > 0;
    }

    private void updateUserForOccupantZoneLocked(int occupantZoneId, int audioZoneId, int driverUserId, int occupantZoneForDriver) {
        CarAudioZone audioZone = getCarAudioZoneLocked(audioZoneId);
        int userId = this.mOccupantZoneService.getUserForOccupant(occupantZoneId);
        int prevUserId = getUserIdForZoneLocked(audioZoneId);
        if (userId == prevUserId) {
            return;
        }
        removeUserIdDeviceAffinitiesLocked(prevUserId);
        if (userId == -10000) {
            resetZoneToDefaultUser(audioZone, driverUserId);
            return;
        }
        if (userId != driverUserId || occupantZoneId == occupantZoneForDriver) {
            setUserIdDeviceAffinitiesLocked(audioZone, userId, audioZoneId);
            this.mAudioZoneIdToUserIdMapping.put(audioZoneId, userId);
        }
        audioZone.updateVolumeGroupsForUser(userId);
        this.mFocusHandler.updateUserForZoneId(audioZoneId, userId);
    }

    private int getOccupantZoneIdForDriver() {
        List<CarOccupantZoneManager.OccupantZoneInfo> occupantZoneInfos = this.mOccupantZoneManager.getAllOccupantZones();
        for (CarOccupantZoneManager.OccupantZoneInfo info : occupantZoneInfos) {
            if (info.occupantType == 0) {
                return info.zoneId;
            }
        }
        return -1;
    }

    private void setUserIdDeviceAffinitiesLocked(CarAudioZone zone, int userId, int audioZoneId) {
        if (!this.mAudioPolicy.setUserIdDeviceAffinity(userId, zone.getAudioDeviceInfos())) {
            throw new IllegalStateException(String.format("setUserIdDeviceAffinity for userId %d in zone %d Failed, could not set audio routing.", Integer.valueOf(userId), Integer.valueOf(audioZoneId)));
        }
    }

    private void resetZoneToDefaultUser(CarAudioZone zone, int driverUserId) {
        resetCarZonesAudioFocus(zone.getId(), driverUserId);
        zone.updateVolumeGroupsForUser(driverUserId);
    }

    private void resetCarZonesAudioFocus(int audioZoneId, int driverUserId) {
        this.mFocusHandler.updateUserForZoneId(audioZoneId, driverUserId);
    }

    private void removeUserIdDeviceAffinitiesLocked(int userId) {
        if (Log.isLoggable(CarLog.TAG_AUDIO, 3)) {
            Log.d(CarLog.TAG_AUDIO, "removeUserIdDeviceAffinities(" + userId + ") Succeeded");
        }
        if (userId != -10000 && !this.mAudioPolicy.removeUserIdDeviceAffinity(userId)) {
            Log.e(CarLog.TAG_AUDIO, "removeUserIdDeviceAffinities(" + userId + ") Failed");
        }
    }

    private int getUserIdForZoneLocked(int audioZoneId) {
        return this.mAudioZoneIdToUserIdMapping.get(audioZoneId, -10000);
    }

    private AudioControlWrapper getAudioControlWrapperLocked() {
        if (this.mAudioControlWrapper == null) {
            AudioControlWrapper audioControlWrapperNewAudioControl = AudioControlFactory.newAudioControl();
            this.mAudioControlWrapper = audioControlWrapperNewAudioControl;
            audioControlWrapperNewAudioControl.linkToDeath(new AudioControlWrapper.AudioControlDeathRecipient() { // from class: com.android.car.audio.-$$Lambda$CarAudioService$7I9b2FYVvwDi34jUfzwoJmiF3aY
                @Override // com.android.car.audio.hal.AudioControlWrapper.AudioControlDeathRecipient
                public final void serviceDied() {
                    this.f$0.resetHalAudioFocus();
                }
            });
        }
        return this.mAudioControlWrapper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetHalAudioFocus() {
        HalAudioFocus halAudioFocus = this.mHalAudioFocus;
        if (halAudioFocus != null) {
            halAudioFocus.reset();
            this.mHalAudioFocus.registerFocusListener();
        }
    }

    boolean isAudioZoneIdValid(int zoneId) {
        boolean zContains;
        synchronized (this.mImplLock) {
            zContains = this.mCarAudioZones.contains(zoneId);
        }
        return zContains;
    }

    private CarAudioZone getCarAudioZone(int zoneId) {
        CarAudioZone carAudioZoneLocked;
        synchronized (this.mImplLock) {
            carAudioZoneLocked = getCarAudioZoneLocked(zoneId);
        }
        return carAudioZoneLocked;
    }

    private CarAudioZone getCarAudioZoneLocked(int zoneId) {
        checkAudioZoneIdLocked(zoneId);
        return this.mCarAudioZones.get(zoneId);
    }

    private void checkAudioZoneIdLocked(int zoneId) {
        Preconditions.checkArgument(this.mCarAudioZones.contains(zoneId), "Invalid audio zone Id " + zoneId);
    }

    private class CarAudioOccupantConfigChangeListener implements CarOccupantZoneManager.OccupantZoneConfigChangeListener {
        private CarAudioOccupantConfigChangeListener() {
        }

        @Override // android.car.CarOccupantZoneManager.OccupantZoneConfigChangeListener
        public void onOccupantZoneConfigChanged(int flags) {
            if (Log.isLoggable(CarLog.TAG_AUDIO, 3)) {
                Log.d(CarLog.TAG_AUDIO, "onOccupantZoneConfigChanged(" + flags + ")");
            }
            if ((flags & 2) == 2 || (flags & 1) == 1) {
                CarAudioService.this.handleOccupantZoneUserChanged();
            }
        }
    }
}
