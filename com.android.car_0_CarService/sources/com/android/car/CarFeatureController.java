package com.android.car;

import android.car.CarFeatures;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AtomicFile;
import android.util.Log;
import android.util.Pair;
import com.android.car.Manifest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
public final class CarFeatureController implements CarServiceBase {
    private static final String CONFIG_FILE_LAST_LINE_MARKER = ",,";
    private static final String FEATURE_CONFIG_FILE_NAME = "car_feature_config.txt";
    private static final HashSet<String> MANDATORY_FEATURES = new HashSet<>(Arrays.asList("app_focus", "audio", "car_bluetooth", "car_bugreport", "configuration", "drivingstate", "android.car.input", "car_media", "car_occupant_zone_service", "trust_enroll", "car_user_service", "uxrestriction", "info", "package", "power", "projection", "property", "car-service-test", "car_watchdog", "enhanced_audio", "cluster_service", "cabin", "hvac", "sensor", "vendor_extension"));
    private static final HashSet<String> OPTIONAL_FEATURES = new HashSet<>(Arrays.asList(CarFeatures.FEATURE_CAR_USER_NOTICE_SERVICE, "car_navigation_service", "diagnostic", "occupant_awareness", "storage_monitoring", "vehicle_map_service"));
    private static final List<Pair<String, String>> SUPPORT_FEATURES = Arrays.asList(Pair.create("vehicle_map_service", "vehicle_map_subscriber_service"));
    private static final String TAG = "CAR.FEATURE";
    private final Context mContext;
    private final List<String> mDefaultEnabledFeaturesFromConfig;
    private final List<String> mDisabledFeaturesFromVhal;
    private final HashSet<String> mEnabledFeatures;
    private final AtomicFile mFeatureConfigFile;
    private final HandlerThread mHandlerThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());
    private final Handler mHandler = new Handler(this.mHandlerThread.getLooper());
    private final Object mLock = new Object();
    private final List<String> mPendingEnabledFeatures = new ArrayList();
    private final List<String> mPendingDisabledFeatures = new ArrayList();
    private HashSet<String> mAvailableExperimentalFeatures = new HashSet<>();

    public CarFeatureController(Context context, String[] defaultEnabledFeaturesFromConfig, String[] disabledFeaturesFromVhal, File dataDir) {
        this.mContext = context;
        this.mDefaultEnabledFeaturesFromConfig = Arrays.asList(defaultEnabledFeaturesFromConfig);
        this.mDisabledFeaturesFromVhal = Arrays.asList(disabledFeaturesFromVhal);
        Log.i(TAG, "mDefaultEnabledFeaturesFromConfig:" + this.mDefaultEnabledFeaturesFromConfig + ",mDisabledFeaturesFromVhal:" + this.mDisabledFeaturesFromVhal);
        this.mEnabledFeatures = new HashSet<>(MANDATORY_FEATURES);
        AtomicFile atomicFile = new AtomicFile(new File(dataDir, FEATURE_CONFIG_FILE_NAME), TAG);
        this.mFeatureConfigFile = atomicFile;
        boolean shouldLoadDefaultConfig = atomicFile.exists() ^ true;
        if (!shouldLoadDefaultConfig && !loadFromConfigFileLocked()) {
            shouldLoadDefaultConfig = true;
        }
        if (!checkMandatoryFeaturesLocked()) {
            this.mEnabledFeatures.clear();
            this.mEnabledFeatures.addAll(MANDATORY_FEATURES);
            shouldLoadDefaultConfig = true;
        }
        if (shouldLoadDefaultConfig) {
            parseDefaultConfig();
            dispatchDefaultConfigUpdate();
        }
        addSupportFeatures(this.mEnabledFeatures);
    }

    List<String> getDisabledFeaturesFromVhal() {
        return this.mDisabledFeaturesFromVhal;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*CarFeatureController*");
        writer.println(" mEnabledFeatures:" + this.mEnabledFeatures);
        writer.println(" mDefaultEnabledFeaturesFromConfig:" + this.mDefaultEnabledFeaturesFromConfig);
        writer.println(" mDisabledFeaturesFromVhal:" + this.mDisabledFeaturesFromVhal);
        synchronized (this.mLock) {
            writer.println(" mAvailableExperimentalFeatures:" + this.mAvailableExperimentalFeatures);
            writer.println(" mPendingEnabledFeatures:" + this.mPendingEnabledFeatures);
            writer.println(" mPendingDisabledFeatures:" + this.mPendingDisabledFeatures);
        }
    }

    public boolean isFeatureEnabled(String featureName) {
        return this.mEnabledFeatures.contains(featureName);
    }

    private boolean checkMandatoryFeaturesLocked() {
        for (String feature : MANDATORY_FEATURES) {
            if (!this.mEnabledFeatures.contains(feature)) {
                Log.e(TAG, "Mandatory feature missing in mEnabledFeatures:" + feature);
                return false;
            }
        }
        return true;
    }

    private int checkFeatureExisting(String featureName) {
        if (MANDATORY_FEATURES.contains(featureName)) {
            return 2;
        }
        if (!OPTIONAL_FEATURES.contains(featureName)) {
            synchronized (this.mLock) {
                if (!this.mAvailableExperimentalFeatures.contains(featureName)) {
                    Log.e(TAG, "enableFeature requested for non-existing feature:" + featureName);
                    return 3;
                }
                return 0;
            }
        }
        return 0;
    }

    public int enableFeature(String featureName) {
        assertPermission();
        int checkResult = checkFeatureExisting(featureName);
        if (checkResult != 0) {
            return checkResult;
        }
        boolean alreadyEnabled = this.mEnabledFeatures.contains(featureName);
        boolean shouldUpdateConfigFile = false;
        synchronized (this.mLock) {
            if (this.mPendingDisabledFeatures.remove(featureName)) {
                shouldUpdateConfigFile = true;
            }
            if (!this.mPendingEnabledFeatures.contains(featureName) && !alreadyEnabled) {
                shouldUpdateConfigFile = true;
                this.mPendingEnabledFeatures.add(featureName);
            }
        }
        if (shouldUpdateConfigFile) {
            Log.w(TAG, "Enabling feature in config file:" + featureName);
            dispatchDefaultConfigUpdate();
        }
        if (alreadyEnabled) {
            return 1;
        }
        return 0;
    }

    public int disableFeature(String featureName) {
        assertPermission();
        int checkResult = checkFeatureExisting(featureName);
        if (checkResult != 0) {
            return checkResult;
        }
        boolean alreadyDisabled = !this.mEnabledFeatures.contains(featureName);
        boolean shouldUpdateConfigFile = false;
        synchronized (this.mLock) {
            if (this.mPendingEnabledFeatures.remove(featureName)) {
                shouldUpdateConfigFile = true;
            }
            if (!this.mPendingDisabledFeatures.contains(featureName) && !alreadyDisabled) {
                shouldUpdateConfigFile = true;
                this.mPendingDisabledFeatures.add(featureName);
            }
        }
        if (shouldUpdateConfigFile) {
            Log.w(TAG, "Disabling feature in config file:" + featureName);
            dispatchDefaultConfigUpdate();
        }
        return alreadyDisabled ? 1 : 0;
    }

    public boolean setAvailableExperimentalFeatureList(List<String> experimentalFeatures) {
        assertPermission();
        if (Build.IS_USER) {
            Log.e(TAG, "Experimental feature list set for USER build", new RuntimeException());
            return false;
        }
        synchronized (this.mLock) {
            this.mAvailableExperimentalFeatures.clear();
            this.mAvailableExperimentalFeatures.addAll(experimentalFeatures);
        }
        return true;
    }

    public List<String> getAllEnabledFeatures() {
        assertPermission();
        return new ArrayList(this.mEnabledFeatures);
    }

    public List<String> getAllPendingDisabledFeatures() {
        ArrayList arrayList;
        assertPermission();
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mPendingDisabledFeatures);
        }
        return arrayList;
    }

    public List<String> getAllPendingEnabledFeatures() {
        ArrayList arrayList;
        assertPermission();
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mPendingEnabledFeatures);
        }
        return arrayList;
    }

    public List<String> getEnabledExperimentalFeatures() {
        if (Build.IS_USER) {
            Log.e(TAG, "getEnabledExperimentalFeatures called in USER build", new RuntimeException());
            return Collections.emptyList();
        }
        ArrayList<String> experimentalFeature = new ArrayList<>();
        for (String feature : this.mEnabledFeatures) {
            if (!MANDATORY_FEATURES.contains(feature) && !OPTIONAL_FEATURES.contains(feature)) {
                experimentalFeature.add(feature);
            }
        }
        return experimentalFeature;
    }

    void handleCorruptConfigFileLocked(String msg, String line) {
        Log.e(TAG, msg + ", considered as corrupt, line:" + line);
        this.mEnabledFeatures.clear();
    }

    private boolean loadFromConfigFileLocked() {
        try {
            FileInputStream fis = this.mFeatureConfigFile.openRead();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
                boolean lastLinePassed = false;
                while (true) {
                    try {
                        String line = reader.readLine();
                        if (line == null) {
                            if (!lastLinePassed) {
                                handleCorruptConfigFileLocked("No last line checksum", "");
                                reader.close();
                                return false;
                            }
                            reader.close();
                            Log.i(TAG, "Loaded features:" + this.mEnabledFeatures);
                            return true;
                        }
                        if (lastLinePassed && !line.isEmpty()) {
                            handleCorruptConfigFileLocked("Config file has additional line after last line marker", line);
                            reader.close();
                            return false;
                        }
                        if (line.startsWith(CONFIG_FILE_LAST_LINE_MARKER)) {
                            try {
                                int numberOfFeatures = Integer.parseInt(line.substring(CONFIG_FILE_LAST_LINE_MARKER.length()));
                                int actualNumberOfFeatures = this.mEnabledFeatures.size();
                                if (numberOfFeatures != actualNumberOfFeatures) {
                                    handleCorruptConfigFileLocked("Config file has wrong number of features, expected:" + numberOfFeatures + " actual:" + actualNumberOfFeatures, line);
                                    reader.close();
                                    return false;
                                }
                                lastLinePassed = true;
                            } catch (NumberFormatException e) {
                                handleCorruptConfigFileLocked("Config file has corrupt last line, not a number", line);
                                reader.close();
                                return false;
                            }
                        } else {
                            this.mEnabledFeatures.add(line);
                        }
                    } finally {
                    }
                }
            } catch (IOException e2) {
                Log.w(TAG, "Cannot load config file", e2);
                return false;
            }
        } catch (FileNotFoundException e3) {
            Log.i(TAG, "Feature config file not found, this could be 1st boot");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: persistToFeatureConfigFile, reason: merged with bridge method [inline-methods] */
    public void lambda$dispatchDefaultConfigUpdate$0$CarFeatureController(HashSet<String> features) {
        BufferedWriter writer;
        removeSupportFeatures(features);
        synchronized (this.mLock) {
            features.removeAll(this.mPendingDisabledFeatures);
            features.addAll(this.mPendingEnabledFeatures);
            try {
                FileOutputStream fos = this.mFeatureConfigFile.startWrite();
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
                } catch (IOException e) {
                    this.mFeatureConfigFile.failWrite(fos);
                    Log.e(TAG, "Cannot create config file", e);
                }
                try {
                    Log.i(TAG, "Updating features:" + features);
                    for (String feature : features) {
                        writer.write(feature);
                        writer.newLine();
                    }
                    writer.write(CONFIG_FILE_LAST_LINE_MARKER + features.size());
                    writer.flush();
                    this.mFeatureConfigFile.finishWrite(fos);
                    writer.close();
                } catch (Throwable th) {
                    try {
                        writer.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e2) {
                Log.e(TAG, "Cannot create config file", e2);
            }
        }
    }

    private void assertPermission() {
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CONTROL_CAR_FEATURES);
    }

    private void dispatchDefaultConfigUpdate() {
        this.mHandler.removeCallbacksAndMessages(null);
        final HashSet<String> featuresToPersist = new HashSet<>(this.mEnabledFeatures);
        this.mHandler.post(new Runnable() { // from class: com.android.car.-$$Lambda$CarFeatureController$ebNYhjd8H8Y3i7td96WCBGEY1tI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dispatchDefaultConfigUpdate$0$CarFeatureController(featuresToPersist);
            }
        });
    }

    private void parseDefaultConfig() {
        for (String feature : this.mDefaultEnabledFeaturesFromConfig) {
            if (!OPTIONAL_FEATURES.contains(feature)) {
                throw new IllegalArgumentException("config_default_enabled_optional_car_features include non-optional features:" + feature);
            }
            if (!this.mDisabledFeaturesFromVhal.contains(feature)) {
                this.mEnabledFeatures.add(feature);
            }
        }
        Log.i(TAG, "Loaded default features:" + this.mEnabledFeatures);
    }

    private static void addSupportFeatures(final Collection<String> features) {
        SUPPORT_FEATURES.stream().filter(new Predicate() { // from class: com.android.car.-$$Lambda$CarFeatureController$wC1qWGPgvuMESS4hY1k1o7oDqmc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return features.contains(((Pair) obj).first);
            }
        }).forEach(new Consumer() { // from class: com.android.car.-$$Lambda$CarFeatureController$r3h1lHG8rroScY4Vd4cpeKQ3obs
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                features.add((String) ((Pair) obj).second);
            }
        });
    }

    private static void removeSupportFeatures(final Collection<String> features) {
        SUPPORT_FEATURES.stream().filter(new Predicate() { // from class: com.android.car.-$$Lambda$CarFeatureController$Mr-riUT0Jmm0oMQ87lRMEevnNOU
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return features.contains(((Pair) obj).first);
            }
        }).forEach(new Consumer() { // from class: com.android.car.-$$Lambda$CarFeatureController$maJsB1AsfJxOy0u4hxGVzplV6gs
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                features.remove(((Pair) obj).second);
            }
        });
    }
}
