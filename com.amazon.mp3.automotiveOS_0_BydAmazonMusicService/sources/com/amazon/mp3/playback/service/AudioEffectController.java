package com.amazon.mp3.playback.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.playback.service.audioeffects.AppBassSettings;
import com.amazon.mp3.playback.service.audioeffects.AppEqualizerSettings;
import com.amazon.mp3.playback.service.audioeffects.AppTrebleSettings;
import com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface;
import com.amazon.mp3.playback.service.audioeffects.HarleyAudioEffects;
import com.amazon.mp3.skiplimit.impl.HardCodedRefreshStrategy;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class AudioEffectController {
    private static final int[] EQUALIZER_UI_BAND_FREQS = {HardCodedRefreshStrategy.MS_IN_MINUTE, 230000, 910000, 360000, 14000000};
    private static final String TAG = "AudioEffectController";
    private static int mEqualizerUINumBands = 0;
    private static boolean sHasFailedToInstantiate = false;
    private static volatile AudioEffectController sInstance;
    private AudioEffectsInterface.BassEffectsInterface bassEffects;
    private AudioEffectsInterface.EqualizerEffectsInterface equalizerEffects;
    private HashMap<Short, Set> mBandMap;
    private SharedPreferences mDefaultSharedPreference;
    private AudioEffectsInterface.EqualizerSettings mEqUserSettings;
    private SharedPreferences mEqualizerSharedPreference;
    private AtomicBoolean mIsAudioEffectControllerInErrorState;
    private Map<String, StateListener> mListenerMap;
    private int mPresetNum;
    private AudioEffectsInterface.TrebleEffectsInterface trebleEffects;

    public interface StateListener {
    }

    public int getEqualizerMaxLevel() {
        return 100;
    }

    private AudioEffectController(@NonNull Context context) {
        this.mPresetNum = -1;
        this.mBandMap = new HashMap<>();
        this.mIsAudioEffectControllerInErrorState = new AtomicBoolean(false);
        int audioSessionId = new MediaPlayer().getAudioSessionId();
        this.mListenerMap = new HashMap();
        this.mDefaultSharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
        this.mEqualizerSharedPreference = context.getSharedPreferences("com.amazon.mp3.playback.service.AudioEffectController", 0);
        createAudioEffectsComponents(audioSessionId);
        initBandMap();
    }

    public void updateEqualizerSetting() {
        if (this.mDefaultSharedPreference.getBoolean("setting_pref_key_equalizer_enable", false) && isAudioEffectsPageSupported()) {
            restoreUserSettings();
        } else {
            initDefaultSetting();
        }
    }

    public void toggleEqualizerSetting(boolean enable) {
        if (enable) {
            usePreset(this.mPresetNum);
            return;
        }
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface = this.equalizerEffects;
        if (equalizerEffectsInterface != null) {
            equalizerEffectsInterface.useDefaultPreset();
        }
        AudioEffectsInterface.TrebleEffectsInterface trebleEffectsInterface = this.trebleEffects;
        if (trebleEffectsInterface != null) {
            trebleEffectsInterface.setTrebleLevel(0);
        }
        AudioEffectsInterface.BassEffectsInterface bassEffectsInterface = this.bassEffects;
        if (bassEffectsInterface != null) {
            bassEffectsInterface.setBassLevel(0);
        }
    }

    private void initDefaultSetting() {
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface = this.equalizerEffects;
        if (equalizerEffectsInterface != null) {
            equalizerEffectsInterface.useDefaultPreset();
        }
        AudioEffectsInterface.TrebleEffectsInterface trebleEffectsInterface = this.trebleEffects;
        if (trebleEffectsInterface != null) {
            trebleEffectsInterface.setTrebleLevel(0);
        }
        AudioEffectsInterface.BassEffectsInterface bassEffectsInterface = this.bassEffects;
        if (bassEffectsInterface != null) {
            bassEffectsInterface.setBassLevel(0);
        }
        SharedPreferences sharedPreferences = this.mEqualizerSharedPreference;
        if (sharedPreferences.contains("equalizer_user_settings")) {
            this.mEqUserSettings = loadStoredUserEqualizerSettings(sharedPreferences);
        } else {
            this.mEqUserSettings = getDefaultUserEqualizerSettings();
        }
        this.mPresetNum = this.equalizerEffects.getDefaultFlatPresetIndex();
    }

    private void initBandMap() {
        int i;
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface = this.equalizerEffects;
        if (equalizerEffectsInterface == null || !equalizerEffectsInterface.isEqualizerInitialized()) {
            return;
        }
        int bandCount = this.equalizerEffects.getBandCount();
        mEqualizerUINumBands = Math.min(bandCount, 5);
        short s = 0;
        while (true) {
            i = mEqualizerUINumBands;
            if (s >= i) {
                break;
            }
            this.mBandMap.put(Short.valueOf(s), new HashSet());
            s = (short) (s + 1);
        }
        if (bandCount <= i) {
            for (short s2 = 0; s2 < bandCount; s2 = (short) (s2 + 1)) {
                Set set = this.mBandMap.get(Short.valueOf(s2));
                if (set != null) {
                    set.add(Short.valueOf(s2));
                }
            }
            return;
        }
        for (short s3 = 0; s3 < bandCount; s3 = (short) (s3 + 1)) {
            double d = Double.POSITIVE_INFINITY;
            short s4 = 0;
            for (short s5 = 0; s5 < mEqualizerUINumBands; s5 = (short) (s5 + 1)) {
                AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface2 = this.equalizerEffects;
                if (equalizerEffectsInterface2 == null || !equalizerEffectsInterface2.isEqualizerInitialized()) {
                    return;
                }
                double dAbs = Math.abs(this.equalizerEffects.getCenterFreq(s3) - EQUALIZER_UI_BAND_FREQS[s5]);
                if (dAbs < d) {
                    s4 = s5;
                    d = dAbs;
                }
            }
            Set set2 = this.mBandMap.get(Short.valueOf(s4));
            if (set2 != null) {
                set2.add(Short.valueOf(s3));
            }
        }
    }

    private void restoreUserSettings() {
        SharedPreferences sharedPreferences = this.mEqualizerSharedPreference;
        if (sharedPreferences.contains("equalizer")) {
            AudioEffectsInterface.EqualizerSettings equalizerSettingsLoadStoredEqualizerSettings = loadStoredEqualizerSettings(sharedPreferences);
            AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface = this.equalizerEffects;
            if (equalizerEffectsInterface != null && equalizerEffectsInterface.isEqualizerInitialized()) {
                this.equalizerEffects.setEqualizerSettings(equalizerSettingsLoadStoredEqualizerSettings);
                this.mPresetNum = equalizerSettingsLoadStoredEqualizerSettings.getCurrPreset();
            }
        } else {
            initDefaultSetting();
        }
        if (sharedPreferences.contains("equalizer_user_settings")) {
            this.mEqUserSettings = loadStoredUserEqualizerSettings(sharedPreferences);
        } else {
            this.mEqUserSettings = getDefaultUserEqualizerSettings();
        }
        if (sharedPreferences.contains("bass_boost")) {
            AppBassSettings appBassSettings = new AppBassSettings(sharedPreferences.getString("bass_boost", ""));
            AudioEffectsInterface.BassEffectsInterface bassEffectsInterface = this.bassEffects;
            if (bassEffectsInterface != null && bassEffectsInterface.isBassInitialized()) {
                this.bassEffects.setBassSettings(appBassSettings);
            }
        }
        if (sharedPreferences.contains("treble")) {
            AppTrebleSettings appTrebleSettings = new AppTrebleSettings(sharedPreferences.getString("treble", ""));
            AudioEffectsInterface.TrebleEffectsInterface trebleEffectsInterface = this.trebleEffects;
            if (trebleEffectsInterface == null || !trebleEffectsInterface.isTrebleInitialized()) {
                return;
            }
            this.trebleEffects.setTrebleSettings(appTrebleSettings);
        }
    }

    private AudioEffectsInterface.EqualizerSettings loadStoredEqualizerSettings(SharedPreferences sharedPreferences) {
        return new AppEqualizerSettings(sharedPreferences.getString("equalizer", ""));
    }

    private AudioEffectsInterface.EqualizerSettings loadStoredUserEqualizerSettings(SharedPreferences sharedPreferences) {
        return new AppEqualizerSettings(sharedPreferences.getString("equalizer_user_settings", ""));
    }

    private AudioEffectsInterface.EqualizerSettings getDefaultUserEqualizerSettings() {
        ArrayList arrayList = new ArrayList();
        int bandCount = this.equalizerEffects.getBandCount();
        for (short s = 0; s < bandCount; s = (short) (s + 1)) {
            arrayList.add(AppEqualizerSettings.createBand(s, this.equalizerEffects.getBandLevel(s)));
        }
        return new AppEqualizerSettings(arrayList, this.equalizerEffects.getUserPresetIndex());
    }

    public static class AudioEffectException extends Exception {
        public AudioEffectException(String message) {
            super(message);
        }

        public AudioEffectException(String message, Throwable throwable) {
            super(message, throwable);
        }
    }

    public static void initialize(@NonNull Context context) {
        getInstance(context).onErrorReturn(new Func1<Throwable, AudioEffectController>() { // from class: com.amazon.mp3.playback.service.AudioEffectController.1
            @Override // rx.functions.Func1
            public AudioEffectController call(final Throwable throwable) {
                Log.error(AudioEffectController.TAG, "Unable to initialize AudioEffectsController", throwable);
                return null;
            }
        }).subscribe();
    }

    public static Observable<AudioEffectController> getInstance(@Nullable final Context context) {
        return Observable.create(new Observable.OnSubscribe<AudioEffectController>() { // from class: com.amazon.mp3.playback.service.AudioEffectController.2
            /* JADX WARN: Removed duplicated region for block: B:15:0x0043 A[Catch: all -> 0x0056, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0009, B:7:0x0013, B:9:0x0019, B:13:0x003d, B:15:0x0043, B:16:0x004a, B:17:0x0054, B:12:0x0026), top: B:24:0x0003, inners: #0 }] */
            @Override // rx.functions.Action1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void call(final rx.Subscriber<? super com.amazon.mp3.playback.service.AudioEffectController> r5) {
                /*
                    r4 = this;
                    java.lang.Class<com.amazon.mp3.playback.service.AudioEffectController> r0 = com.amazon.mp3.playback.service.AudioEffectController.class
                    monitor-enter(r0)
                    boolean r1 = com.amazon.mp3.playback.service.AudioEffectController.access$100()     // Catch: java.lang.Throwable -> L56
                    if (r1 == 0) goto L13
                    com.amazon.mp3.playback.service.AudioEffectController$AudioEffectException r1 = new com.amazon.mp3.playback.service.AudioEffectController$AudioEffectException     // Catch: java.lang.Throwable -> L56
                    java.lang.String r2 = "AudioEffectController has previously failed to instantiate elsewhere in the app"
                    r1.<init>(r2)     // Catch: java.lang.Throwable -> L56
                    r5.onError(r1)     // Catch: java.lang.Throwable -> L56
                L13:
                    com.amazon.mp3.playback.service.AudioEffectController r1 = com.amazon.mp3.playback.service.AudioEffectController.access$200()     // Catch: java.lang.Throwable -> L56
                    if (r1 != 0) goto L3d
                    com.amazon.mp3.playback.service.AudioEffectController r1 = new com.amazon.mp3.playback.service.AudioEffectController     // Catch: java.lang.RuntimeException -> L25 java.lang.Throwable -> L56
                    android.content.Context r2 = r1     // Catch: java.lang.RuntimeException -> L25 java.lang.Throwable -> L56
                    r3 = 0
                    r1.<init>(r2)     // Catch: java.lang.RuntimeException -> L25 java.lang.Throwable -> L56
                    com.amazon.mp3.playback.service.AudioEffectController.access$202(r1)     // Catch: java.lang.RuntimeException -> L25 java.lang.Throwable -> L56
                    goto L3d
                L25:
                    r1 = move-exception
                    java.lang.String r2 = com.amazon.mp3.playback.service.AudioEffectController.access$000()     // Catch: java.lang.Throwable -> L56
                    java.lang.String r3 = "Failed to instantiate audio effect objects in AudioEffectController "
                    com.amazon.mp3.util.Log.error(r2, r3, r1)     // Catch: java.lang.Throwable -> L56
                    r1 = 1
                    com.amazon.mp3.playback.service.AudioEffectController.access$102(r1)     // Catch: java.lang.Throwable -> L56
                    com.amazon.mp3.playback.service.AudioEffectController$AudioEffectException r1 = new com.amazon.mp3.playback.service.AudioEffectController$AudioEffectException     // Catch: java.lang.Throwable -> L56
                    java.lang.String r2 = "AudioEffectController has failed to instantiate effects"
                    r1.<init>(r2)     // Catch: java.lang.Throwable -> L56
                    r5.onError(r1)     // Catch: java.lang.Throwable -> L56
                L3d:
                    com.amazon.mp3.playback.service.AudioEffectController r1 = com.amazon.mp3.playback.service.AudioEffectController.access$200()     // Catch: java.lang.Throwable -> L56
                    if (r1 == 0) goto L4a
                    com.amazon.mp3.playback.service.AudioEffectController r1 = com.amazon.mp3.playback.service.AudioEffectController.access$200()     // Catch: java.lang.Throwable -> L56
                    r1.updateEqualizerSetting()     // Catch: java.lang.Throwable -> L56
                L4a:
                    com.amazon.mp3.playback.service.AudioEffectController r1 = com.amazon.mp3.playback.service.AudioEffectController.access$200()     // Catch: java.lang.Throwable -> L56
                    r5.onNext(r1)     // Catch: java.lang.Throwable -> L56
                    r5.onCompleted()     // Catch: java.lang.Throwable -> L56
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
                    return
                L56:
                    r5 = move-exception
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.service.AudioEffectController.AnonymousClass2.call(rx.Subscriber):void");
            }
        }).subscribeOn(Schedulers.computation());
    }

    public boolean isAudioEffectControllerInErrorState() {
        return this.mIsAudioEffectControllerInErrorState.get();
    }

    public static boolean isAudioEffectsPageSupported() {
        return AmazonApplication.getCapabilities().isEqualizerSupported();
    }

    public void setEqBandLevel(final short band, final short level) {
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface;
        this.mPresetNum = this.equalizerEffects.getUserPresetIndex();
        if (this.mIsAudioEffectControllerInErrorState.get() || (equalizerEffectsInterface = this.equalizerEffects) == null || !equalizerEffectsInterface.isEqualizerInitialized()) {
            return;
        }
        AudioEffectsInterface.EqualizerSettings equalizerProperties = this.equalizerEffects.getEqualizerProperties();
        equalizerProperties.setCurrPreset(this.equalizerEffects.getUserPresetIndex());
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface2 = this.equalizerEffects;
        if (equalizerEffectsInterface2 == null) {
            return;
        }
        equalizerEffectsInterface2.setEqualizerSettings(equalizerProperties);
        this.equalizerEffects.setEqualizerEnabled(true);
        int[] bandLevelRange = this.equalizerEffects.getBandLevelRange();
        int i = bandLevelRange[0];
        int i2 = i + (((bandLevelRange[1] - i) * level) / 100);
        Set set = this.mBandMap.get(Short.valueOf(band));
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            short sShortValue = ((Short) it.next()).shortValue();
            AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface3 = this.equalizerEffects;
            if (equalizerEffectsInterface3 != null && equalizerEffectsInterface3.isEqualizerInitialized()) {
                this.equalizerEffects.setBandLevel(sShortValue, (short) i2);
            }
        }
        updateUserEqSetting(band, i2);
    }

    public void setBassBoostStrength(final int strength) {
        AudioEffectsInterface.BassEffectsInterface bassEffectsInterface;
        if (this.mIsAudioEffectControllerInErrorState.get() || (bassEffectsInterface = this.bassEffects) == null || !bassEffectsInterface.isBassInitialized()) {
            return;
        }
        this.bassEffects.setBassEnabled(true);
        this.bassEffects.setBassLevel(strength);
    }

    public void setTrebleStrength(final int strength) {
        AudioEffectsInterface.TrebleEffectsInterface trebleEffectsInterface;
        if (this.mIsAudioEffectControllerInErrorState.get() || (trebleEffectsInterface = this.trebleEffects) == null || !trebleEffectsInterface.isTrebleInitialized()) {
            return;
        }
        this.trebleEffects.setTrebleEnabled(true);
        this.trebleEffects.setTrebleLevel(strength);
    }

    public int getTrebleMaxStrength() {
        AudioEffectsInterface.TrebleEffectsInterface trebleEffectsInterface = this.trebleEffects;
        if (trebleEffectsInterface != null) {
            return trebleEffectsInterface.getTrebleMaxLevel();
        }
        return 1;
    }

    public int getBassBoostMaxStrength() {
        AudioEffectsInterface.BassEffectsInterface bassEffectsInterface = this.bassEffects;
        if (bassEffectsInterface != null) {
            return bassEffectsInterface.getBassMaxLevel();
        }
        return 1;
    }

    public int getEqualizerNumBands() {
        return mEqualizerUINumBands;
    }

    public void saveSettings() {
        if (!this.mIsAudioEffectControllerInErrorState.get()) {
            SharedPreferences.Editor editorEdit = AmazonApplication.getContext().getSharedPreferences("com.amazon.mp3.playback.service.AudioEffectController", 0).edit();
            AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface = this.equalizerEffects;
            if (equalizerEffectsInterface != null && equalizerEffectsInterface.isEqualizerInitialized()) {
                AudioEffectsInterface.EqualizerSettings equalizerProperties = this.equalizerEffects.getEqualizerProperties();
                equalizerProperties.setCurrPreset(this.mPresetNum);
                if (this.mPresetNum == this.equalizerEffects.getUserPresetIndex()) {
                    editorEdit.putString("equalizer", this.mEqUserSettings.getStorageString());
                } else {
                    editorEdit.putString("equalizer", equalizerProperties.getStorageString());
                }
                AudioEffectsInterface.EqualizerSettings equalizerSettings = this.mEqUserSettings;
                if (equalizerSettings != null) {
                    editorEdit.putString("equalizer_user_settings", equalizerSettings.getStorageString());
                }
                checkIfEqualizerHasBeenModified(equalizerProperties);
            }
            AudioEffectsInterface.BassEffectsInterface bassEffectsInterface = this.bassEffects;
            if (bassEffectsInterface != null && bassEffectsInterface.isBassInitialized()) {
                AudioEffectsInterface.FrequencySettings bassProperties = this.bassEffects.getBassProperties();
                editorEdit.putString("bass_boost", bassProperties.getStorageString());
                updateBassBoostModifiedFlag(bassProperties);
            }
            AudioEffectsInterface.TrebleEffectsInterface trebleEffectsInterface = this.trebleEffects;
            if (trebleEffectsInterface != null && trebleEffectsInterface.isTrebleInitialized()) {
                AudioEffectsInterface.FrequencySettings trebleProperties = this.trebleEffects.getTrebleProperties();
                editorEdit.putString("treble", trebleProperties.getStorageString());
                updateTrebleModifiedFlag(trebleProperties);
            }
            editorEdit.apply();
            return;
        }
        Log.error(TAG, "Audio effects in error state, unable to save audio effect settings");
    }

    public int getBassBoostStrength() {
        AudioEffectsInterface.BassEffectsInterface bassEffectsInterface;
        if (this.mIsAudioEffectControllerInErrorState.get() || (bassEffectsInterface = this.bassEffects) == null || !bassEffectsInterface.isBassInitialized()) {
            return 0;
        }
        return this.bassEffects.getBassLevel();
    }

    public int getTrebleStrength() {
        AudioEffectsInterface.TrebleEffectsInterface trebleEffectsInterface;
        if (this.mIsAudioEffectControllerInErrorState.get() || (trebleEffectsInterface = this.trebleEffects) == null || !trebleEffectsInterface.isTrebleInitialized()) {
            return 0;
        }
        return this.trebleEffects.getTrebleLevel();
    }

    public int getBandLevel(final short band) {
        Set set;
        int[] bandLevelRange;
        int i;
        int i2;
        int i3;
        if (this.mIsAudioEffectControllerInErrorState.get() || (set = this.mBandMap.get(Short.valueOf(band))) == null) {
            return 0;
        }
        short sShortValue = ((Short[]) set.toArray(new Short[set.size()]))[0].shortValue();
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface = this.equalizerEffects;
        if (equalizerEffectsInterface == null || !equalizerEffectsInterface.isEqualizerInitialized() || (i = (bandLevelRange = this.equalizerEffects.getBandLevelRange())[0]) == Integer.MIN_VALUE || (i2 = bandLevelRange[1]) == Integer.MIN_VALUE || (i3 = i2 - i) == 0) {
            return 0;
        }
        return ((this.equalizerEffects.getBandLevel(sShortValue) - bandLevelRange[0]) * 100) / i3;
    }

    public int getBandHz(final short band) {
        Set set;
        if (this.mIsAudioEffectControllerInErrorState.get() || (set = this.mBandMap.get(Short.valueOf(band))) == null) {
            return 0;
        }
        short sShortValue = ((Short[]) set.toArray(new Short[set.size()]))[0].shortValue();
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface = this.equalizerEffects;
        if (equalizerEffectsInterface == null || !equalizerEffectsInterface.isEqualizerInitialized()) {
            return 0;
        }
        return this.equalizerEffects.getCenterFreq(sShortValue);
    }

    public int[] getBandDecibelRange() throws AudioEffectException {
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface = this.equalizerEffects;
        if (equalizerEffectsInterface == null) {
            return new int[]{0, 0};
        }
        try {
            int[] bandLevelRange = equalizerEffectsInterface.getBandLevelRange();
            bandLevelRange[0] = bandLevelRange[0] / 100;
            bandLevelRange[1] = bandLevelRange[1] / 100;
            return bandLevelRange;
        } catch (Exception e) {
            Log.error(TAG, "Error retrieving dB range for equalizer " + e.getMessage(), e);
            throw new AudioEffectException("Error retrieving dB range for equalizer", e);
        }
    }

    public String[] getPresets() {
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface = this.equalizerEffects;
        return equalizerEffectsInterface == null ? new String[0] : equalizerEffectsInterface.getPresets();
    }

    public void usePreset(final int presetNum) {
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface;
        this.mPresetNum = presetNum;
        if (this.mIsAudioEffectControllerInErrorState.get() || (equalizerEffectsInterface = this.equalizerEffects) == null || !equalizerEffectsInterface.isEqualizerInitialized()) {
            return;
        }
        if (this.mPresetNum != this.equalizerEffects.getUserPresetIndex()) {
            AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface2 = this.equalizerEffects;
            if (equalizerEffectsInterface2 != null) {
                equalizerEffectsInterface2.usePreset(this.mPresetNum);
                return;
            }
            return;
        }
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface3 = this.equalizerEffects;
        if (equalizerEffectsInterface3 != null) {
            equalizerEffectsInterface3.setEqualizerSettings(this.mEqUserSettings);
            Log.debug(TAG, "EQEFFECTS MPPRESET: " + this.mPresetNum + " STRING:" + this.equalizerEffects.getEqualizerProperties().getStorageString());
        }
    }

    private void updateUserEqSetting(int bandIndex, int bandValue) {
        AppEqualizerSettings updateEqualizerProperties = this.equalizerEffects.getUpdateEqualizerProperties(bandIndex, bandValue);
        this.mEqUserSettings = updateEqualizerProperties;
        updateEqualizerProperties.setCurrPreset(this.equalizerEffects.getUserPresetIndex());
    }

    public int getPresetNum() {
        return this.mPresetNum;
    }

    public boolean registerListener(final String identifier, final StateListener listener) {
        if (TextUtils.isEmpty(identifier)) {
            Log.error(TAG, "Unable to register listener if identifier is false.");
            return false;
        }
        if (listener == null) {
            Log.error(TAG, "Unable to register a null listener for: " + identifier);
            return false;
        }
        this.mListenerMap.put(identifier, listener);
        return true;
    }

    public void deregisterListener(final String identifier) {
        this.mListenerMap.remove(identifier);
    }

    private void createAudioEffectsComponents(final int audioSessionId) {
        HarleyAudioEffects harleyAudioEffects = new HarleyAudioEffects();
        this.equalizerEffects = harleyAudioEffects.getEqualizerEffects();
        this.bassEffects = harleyAudioEffects.getBassEffects();
        this.trebleEffects = harleyAudioEffects.getTrebleEffects();
        SharedPreferences.Editor editorEdit = AmazonApplication.getContext().getSharedPreferences("com.amazon.mp3.playback.service.AudioEffectController", 0).edit();
        AudioEffectsInterface.EqualizerEffectsInterface equalizerEffectsInterface = this.equalizerEffects;
        if (equalizerEffectsInterface != null && equalizerEffectsInterface.isEqualizerInitialized()) {
            updateDefaultEqualizerValues(this.equalizerEffects.getEqualizerProperties(), editorEdit);
        }
        AudioEffectsInterface.BassEffectsInterface bassEffectsInterface = this.bassEffects;
        if (bassEffectsInterface != null && bassEffectsInterface.isBassInitialized()) {
            updateDefaultBassBoostValues(this.bassEffects.getBassProperties(), editorEdit);
        }
        AudioEffectsInterface.TrebleEffectsInterface trebleEffectsInterface = this.trebleEffects;
        if (trebleEffectsInterface == null || !trebleEffectsInterface.isTrebleInitialized()) {
            return;
        }
        updateDefaultTrebleValues(this.trebleEffects.getTrebleProperties(), editorEdit);
    }

    private void updateDefaultEqualizerValues(final AudioEffectsInterface.EqualizerSettings eqDefaultSettings, final SharedPreferences.Editor editor) {
        editor.putString("equalizer_default_settings", eqDefaultSettings.getBandValueString());
    }

    private void updateDefaultBassBoostValues(final AudioEffectsInterface.FrequencySettings bassBoostDefaultSettings, final SharedPreferences.Editor editor) {
        editor.putString("bass_boost_default_settings", bassBoostDefaultSettings.getStorageString());
        editor.apply();
    }

    private void updateDefaultTrebleValues(final AudioEffectsInterface.FrequencySettings trebleDefaultSettings, final SharedPreferences.Editor editor) {
        editor.putString("treble_default_settings", trebleDefaultSettings.getStorageString());
        editor.apply();
    }

    private void checkIfEqualizerHasBeenModified(final AudioEffectsInterface.EqualizerSettings currentSettings) {
        SharedPreferences sharedPreferences = AmazonApplication.getContext().getSharedPreferences("com.amazon.mp3.playback.service.AudioEffectController", 0);
        String string = sharedPreferences.getString("equalizer_default_settings", null);
        if (string != null) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            if (!string.equals(currentSettings.getBandValueString())) {
                editorEdit.putBoolean("equalizer_modified", true);
                Log.debug(TAG, "user has modified equalizer  current:" + currentSettings.getStorageString() + " default:" + string);
            } else {
                editorEdit.putBoolean("equalizer_modified", false);
                Log.debug(TAG, "user has not modified equalizer!");
            }
            editorEdit.apply();
        }
    }

    private void updateBassBoostModifiedFlag(final AudioEffectsInterface.FrequencySettings currentSettings) {
        updateFrequencySettingsModifiedFlag(currentSettings, "bass_boost_default_settings", "bass_boost_modified", "user has modified bass boost", "user has NOT modified bass boost!");
    }

    private void updateTrebleModifiedFlag(final AudioEffectsInterface.FrequencySettings currentSettings) {
        updateFrequencySettingsModifiedFlag(currentSettings, "treble_default_settings", "treble_modified", "user has modified treble", "user has NOT modified treble!");
    }

    private void updateFrequencySettingsModifiedFlag(final AudioEffectsInterface.FrequencySettings currentSettings, final String sharedPrefDefaultSettingsKey, final String sharedPrefFlagHasBeenModifiedKey, final String hasBeenModifiedLogMessage, final String hasNotBeenModifiedLogMessage) {
        SharedPreferences sharedPreferences = AmazonApplication.getContext().getSharedPreferences("com.amazon.mp3.playback.service.AudioEffectController", 0);
        String string = sharedPreferences.getString(sharedPrefDefaultSettingsKey, null);
        if (sharedPrefFlagHasBeenModifiedKey != null) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            if (!string.equals(currentSettings.getStorageString())) {
                editorEdit.putBoolean(sharedPrefFlagHasBeenModifiedKey, true);
                Log.debug(TAG, hasBeenModifiedLogMessage);
            } else {
                editorEdit.putBoolean(sharedPrefFlagHasBeenModifiedKey, false);
                Log.debug(TAG, hasNotBeenModifiedLogMessage);
            }
            editorEdit.apply();
        }
    }
}
