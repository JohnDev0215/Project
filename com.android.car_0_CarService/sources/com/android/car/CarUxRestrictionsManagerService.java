package com.android.car;

import android.car.drivingstate.CarDrivingStateEvent;
import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.CarUxRestrictionsConfiguration;
import android.car.drivingstate.ICarDrivingStateChangeListener;
import android.car.drivingstate.ICarUxRestrictionsChangeListener;
import android.car.drivingstate.ICarUxRestrictionsManager;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyEvent;
import android.car.hardware.property.ICarPropertyEventListener;
import android.content.Context;
import android.hardware.automotive.vehicle.V2_0.VehicleProperty;
import android.hardware.display.DisplayManager;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IRemoteCallback;
import android.os.Process;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.ArraySet;
import android.util.AtomicFile;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.DisplayAddress;
import com.android.car.Manifest;
import com.android.car.Utils;
import com.android.car.systeminterface.SystemInterface;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
public class CarUxRestrictionsManagerService extends ICarUxRestrictionsManager.Stub implements CarServiceBase {
    static final String CONFIG_FILENAME_PRODUCTION = "ux_restrictions_prod_config.json";
    static final String CONFIG_FILENAME_STAGED = "ux_restrictions_staged_config.json";
    private static final boolean DBG = false;
    private static final byte DEFAULT_PORT = 0;
    private static final String JSON_NAME_RESTRICTIONS = "restrictions";
    private static final String JSON_NAME_SCHEMA_VERSION = "schema_version";
    private static final int JSON_SCHEMA_VERSION_V1 = 1;
    private static final int JSON_SCHEMA_VERSION_V2 = 2;
    private static final int MAX_TRANSITION_LOG_SIZE = 20;
    private static final int PROPERTY_UPDATE_RATE = 5;
    private static final float SPEED_NOT_AVAILABLE = -1.0f;
    private static final String TAG = "CarUxR";
    private static final int UNKNOWN_JSON_SCHEMA_VERSION = -1;
    private final CarPropertyService mCarPropertyService;
    private Map<Byte, CarUxRestrictionsConfiguration> mCarUxRestrictionsConfigurations;
    private final Context mContext;
    private float mCurrentMovingSpeed;
    private Map<Byte, CarUxRestrictions> mCurrentUxRestrictions;
    private byte mDefaultDisplayPhysicalPort;
    private final DisplayManager mDisplayManager;
    private final CarDrivingStateService mDrivingStateService;
    private final HandlerThread mClientDispatchThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());
    private final Handler mClientDispatchHandler = new Handler(this.mClientDispatchThread.getLooper());
    private final RemoteCallbackList<ICarUxRestrictionsChangeListener> mUxRClients = new RemoteCallbackList<>();
    private final Object mLock = new Object();
    private final Map<Integer, Byte> mPortLookup = new HashMap();
    private String mRestrictionMode = "baseline";
    private final List<Byte> mPhysicalPorts = new ArrayList();
    private boolean mUxRChangeBroadcastEnabled = true;
    private final LinkedList<Utils.TransitionLog> mTransitionLogs = new LinkedList<>();
    private final ICarDrivingStateChangeListener mICarDrivingStateChangeEventListener = new ICarDrivingStateChangeListener.Stub() { // from class: com.android.car.CarUxRestrictionsManagerService.1
        public void onDrivingStateChanged(CarDrivingStateEvent event) {
            CarUxRestrictionsManagerService.logd("Driving State Changed:" + event.eventValue);
            synchronized (CarUxRestrictionsManagerService.this.mLock) {
                CarUxRestrictionsManagerService.this.handleDrivingStateEventLocked(event);
            }
        }
    };
    private final ICarPropertyEventListener mICarPropertyEventListener = new ICarPropertyEventListener.Stub() { // from class: com.android.car.CarUxRestrictionsManagerService.2
        public void onEvent(List<CarPropertyEvent> events) throws RemoteException {
            synchronized (CarUxRestrictionsManagerService.this.mLock) {
                for (CarPropertyEvent event : events) {
                    if (event.getEventType() == 0 && event.getCarPropertyValue().getPropertyId() == 291504647) {
                        CarUxRestrictionsManagerService.this.handleSpeedChangeLocked(((Float) event.getCarPropertyValue().getValue()).floatValue());
                    }
                }
            }
        }
    };
    private final SparseArray<DisplayInfo> mActivityViewDisplayInfoMap = new SparseArray<>();
    private final RemoteCallbackList<IRemoteCallback> mRemoteCallbackList = new RemoteCallbackList<IRemoteCallback>() { // from class: com.android.car.CarUxRestrictionsManagerService.3
        @Override // android.os.RemoteCallbackList
        public void onCallbackDied(IRemoteCallback callback) {
            synchronized (CarUxRestrictionsManagerService.this.mLock) {
                for (int i = CarUxRestrictionsManagerService.this.mActivityViewDisplayInfoMap.size() - 1; i >= 0; i--) {
                    DisplayInfo info = (DisplayInfo) CarUxRestrictionsManagerService.this.mActivityViewDisplayInfoMap.valueAt(i);
                    if (info.mOwner == callback) {
                        CarUxRestrictionsManagerService.logd("onCallbackDied: clean up callback=" + callback);
                        CarUxRestrictionsManagerService.this.mActivityViewDisplayInfoMap.removeAt(i);
                        CarUxRestrictionsManagerService.this.mPortLookup.remove(Integer.valueOf(CarUxRestrictionsManagerService.this.mActivityViewDisplayInfoMap.keyAt(i)));
                    }
                }
            }
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    private @interface JsonSchemaVersion {
    }

    private static class RemoteCallbackListCookie {
        final Byte mPhysicalPort;

        RemoteCallbackListCookie(Byte physicalPort) {
            this.mPhysicalPort = physicalPort;
        }
    }

    public CarUxRestrictionsManagerService(Context context, CarDrivingStateService drvService, CarPropertyService propertyService) {
        this.mContext = context;
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
        this.mDrivingStateService = drvService;
        this.mCarPropertyService = propertyService;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        synchronized (this.mLock) {
            this.mDefaultDisplayPhysicalPort = getDefaultDisplayPhysicalPort(this.mDisplayManager);
            initPhysicalPort();
            this.mCurrentUxRestrictions = new HashMap();
            Iterator<Byte> it = this.mPhysicalPorts.iterator();
            while (it.hasNext()) {
                byte port = it.next().byteValue();
                this.mCurrentUxRestrictions.put(Byte.valueOf(port), createUnrestrictedRestrictions());
            }
            this.mCarUxRestrictionsConfigurations = convertToMap(loadConfig());
        }
        this.mDrivingStateService.registerDrivingStateChangeListener(this.mICarDrivingStateChangeEventListener);
        this.mCarPropertyService.registerListener(VehicleProperty.PERF_VEHICLE_SPEED, 5.0f, this.mICarPropertyEventListener);
        initializeUxRestrictions();
    }

    public List<CarUxRestrictionsConfiguration> getConfigs() {
        ArrayList arrayList;
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_UX_RESTRICTIONS_CONFIGURATION);
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mCarUxRestrictionsConfigurations.values());
        }
        return arrayList;
    }

    List<CarUxRestrictionsConfiguration> loadConfig() {
        promoteStagedConfig();
        File prodConfig = getFile(CONFIG_FILENAME_PRODUCTION);
        if (prodConfig.exists()) {
            logd("Attempting to read production config");
            List<CarUxRestrictionsConfiguration> configs = readPersistedConfig(prodConfig);
            if (configs != null) {
                return configs;
            }
        }
        logd("Attempting to read config from XML resource");
        List<CarUxRestrictionsConfiguration> configs2 = readXmlConfig();
        if (configs2 != null) {
            return configs2;
        }
        Log.w(TAG, "Creating default config");
        List<CarUxRestrictionsConfiguration> configs3 = new ArrayList<>();
        synchronized (this.mLock) {
            Iterator<Byte> it = this.mPhysicalPorts.iterator();
            while (it.hasNext()) {
                byte port = it.next().byteValue();
                configs3.add(createDefaultConfig(port));
            }
        }
        return configs3;
    }

    private File getFile(String filename) {
        SystemInterface systemInterface = (SystemInterface) CarLocalServices.getService(SystemInterface.class);
        return new File(systemInterface.getSystemCarDir(), filename);
    }

    private List<CarUxRestrictionsConfiguration> readXmlConfig() {
        try {
            return CarUxRestrictionsConfigurationXmlParser.parse(this.mContext, R.xml.car_ux_restrictions_map);
        } catch (IOException | XmlPullParserException e) {
            Log.e(TAG, "Could not read config from XML resource", e);
            return null;
        }
    }

    private void promoteStagedConfig() {
        Path stagedConfig = getFile(CONFIG_FILENAME_STAGED).toPath();
        CarDrivingStateEvent currentDrivingStateEvent = this.mDrivingStateService.getCurrentDrivingState();
        if (currentDrivingStateEvent != null && currentDrivingStateEvent.eventValue == 0 && Files.exists(stagedConfig, new LinkOption[0])) {
            Path prod = getFile(CONFIG_FILENAME_PRODUCTION).toPath();
            try {
                logd("Attempting to promote stage config");
                Files.move(stagedConfig, prod, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                Log.e(TAG, "Could not promote state config", e);
            }
        }
    }

    private void initializeUxRestrictions() {
        CarDrivingStateEvent currentDrivingStateEvent = this.mDrivingStateService.getCurrentDrivingState();
        if (currentDrivingStateEvent == null || currentDrivingStateEvent.eventValue == -1) {
            return;
        }
        int currentDrivingState = currentDrivingStateEvent.eventValue;
        Float currentSpeed = getCurrentSpeed();
        if (currentSpeed.floatValue() == SPEED_NOT_AVAILABLE) {
            return;
        }
        synchronized (this.mLock) {
            handleDispatchUxRestrictionsLocked(currentDrivingState, currentSpeed.floatValue());
        }
    }

    private Float getCurrentSpeed() {
        CarPropertyValue value = this.mCarPropertyService.getProperty(VehicleProperty.PERF_VEHICLE_SPEED, 0);
        if (value != null) {
            return (Float) value.getValue();
        }
        return Float.valueOf(SPEED_NOT_AVAILABLE);
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        while (this.mUxRClients.getRegisteredCallbackCount() > 0) {
            for (int i = this.mUxRClients.getRegisteredCallbackCount() - 1; i >= 0; i--) {
                ICarUxRestrictionsChangeListener client = this.mUxRClients.getRegisteredCallbackItem(i);
                if (client != null) {
                    this.mUxRClients.unregister(client);
                }
            }
        }
        this.mDrivingStateService.unregisterDrivingStateChangeListener(this.mICarDrivingStateChangeEventListener);
        synchronized (this.mLock) {
            this.mActivityViewDisplayInfoMap.clear();
        }
    }

    public void registerUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener listener, int displayId) {
        Byte physicalPort;
        if (listener == null) {
            Log.e(TAG, "registerUxRestrictionsChangeListener(): listener null");
            throw new IllegalArgumentException("Listener is null");
        }
        synchronized (this.mLock) {
            physicalPort = getPhysicalPortLocked(displayId);
            if (physicalPort == null) {
                physicalPort = Byte.valueOf(this.mDefaultDisplayPhysicalPort);
            }
        }
        this.mUxRClients.register(listener, new RemoteCallbackListCookie(physicalPort));
    }

    public void unregisterUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener listener) {
        if (listener == null) {
            Log.e(TAG, "unregisterUxRestrictionsChangeListener(): listener null");
            throw new IllegalArgumentException("Listener is null");
        }
        this.mUxRClients.unregister(listener);
    }

    public CarUxRestrictions getCurrentUxRestrictions(int displayId) {
        CarUxRestrictions restrictions;
        synchronized (this.mLock) {
            restrictions = this.mCurrentUxRestrictions.get(getPhysicalPortLocked(displayId));
        }
        if (restrictions == null) {
            Log.e(TAG, String.format("Restrictions are null for displayId:%d. Returning full restrictions.", Integer.valueOf(displayId)));
            return createFullyRestrictedRestrictions();
        }
        return restrictions;
    }

    public CarUxRestrictions getCurrentUxRestrictions() {
        return getCurrentUxRestrictions(0);
    }

    public boolean saveUxRestrictionsConfigurationForNextBoot(List<CarUxRestrictionsConfiguration> configs) {
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_UX_RESTRICTIONS_CONFIGURATION);
        validateConfigs(configs);
        return persistConfig(configs, CONFIG_FILENAME_STAGED);
    }

    public List<CarUxRestrictionsConfiguration> getStagedConfigs() {
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_UX_RESTRICTIONS_CONFIGURATION);
        File stagedConfig = getFile(CONFIG_FILENAME_STAGED);
        if (stagedConfig.exists()) {
            logd("Attempting to read staged config");
            return readPersistedConfig(stagedConfig);
        }
        return null;
    }

    public boolean setRestrictionMode(String mode) {
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_UX_RESTRICTIONS_CONFIGURATION);
        Objects.requireNonNull(mode, "mode must not be null");
        synchronized (this.mLock) {
            if (this.mRestrictionMode.equals(mode)) {
                return true;
            }
            addTransitionLogLocked(TAG, this.mRestrictionMode, mode, System.currentTimeMillis(), "Restriction mode");
            this.mRestrictionMode = mode;
            logd("Set restriction mode to: " + mode);
            handleDispatchUxRestrictionsLocked(this.mDrivingStateService.getCurrentDrivingState().eventValue, getCurrentSpeed().floatValue());
            return true;
        }
    }

    public String getRestrictionMode() {
        String str;
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_UX_RESTRICTIONS_CONFIGURATION);
        synchronized (this.mLock) {
            str = this.mRestrictionMode;
        }
        return str;
    }

    private boolean persistConfig(List<CarUxRestrictionsConfiguration> configs, String filename) {
        File file = getFile(filename);
        AtomicFile stagedFile = new AtomicFile(file);
        try {
            FileOutputStream fos = stagedFile.startWrite();
            try {
                JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
                try {
                    writeJson(jsonWriter, configs);
                    jsonWriter.close();
                    stagedFile.finishWrite(fos);
                    return true;
                } finally {
                }
            } catch (IOException e) {
                Log.e(TAG, "Could not persist config", e);
                stagedFile.failWrite(fos);
                return false;
            }
        } catch (IOException e2) {
            Log.e(TAG, "Could not open file to persist config", e2);
            return false;
        }
    }

    void writeJson(JsonWriter jsonWriter, List<CarUxRestrictionsConfiguration> configs) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name(JSON_NAME_SCHEMA_VERSION).value(2L);
        jsonWriter.name(JSON_NAME_RESTRICTIONS);
        jsonWriter.beginArray();
        for (CarUxRestrictionsConfiguration config : configs) {
            config.writeJson(jsonWriter);
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    private List<CarUxRestrictionsConfiguration> readPersistedConfig(File file) {
        if (!file.exists()) {
            Log.e(TAG, "Could not find config file: " + file.getName());
            return null;
        }
        int schemaVersion = readFileSchemaVersion(file);
        AtomicFile configFile = new AtomicFile(file);
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(configFile.openRead(), StandardCharsets.UTF_8));
            try {
                List<CarUxRestrictionsConfiguration> configs = new ArrayList<>();
                if (schemaVersion == 1) {
                    readV1Json(reader, configs);
                } else if (schemaVersion == 2) {
                    readV2Json(reader, configs);
                } else {
                    Log.e(TAG, "Unable to parse schema for version " + schemaVersion);
                }
                reader.close();
                return configs;
            } finally {
            }
        } catch (IOException e) {
            Log.e(TAG, "Could not read persisted config file " + file.getName(), e);
            return null;
        }
    }

    private void readV1Json(JsonReader reader, List<CarUxRestrictionsConfiguration> configs) throws IOException {
        readRestrictionsArray(reader, configs, 1);
    }

    private void readV2Json(JsonReader reader, List<CarUxRestrictionsConfiguration> configs) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            byte b = -1;
            if (name.hashCode() == -1148295641 && name.equals(JSON_NAME_RESTRICTIONS)) {
                b = 0;
            }
            if (b == 0) {
                readRestrictionsArray(reader, configs, 2);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
    }

    private int readFileSchemaVersion(File file) {
        JsonReader reader;
        AtomicFile configFile = new AtomicFile(file);
        try {
            reader = new JsonReader(new InputStreamReader(configFile.openRead(), StandardCharsets.UTF_8));
            try {
                new ArrayList();
            } finally {
            }
        } catch (IOException e) {
            Log.e(TAG, "Could not read persisted config file " + file.getName(), e);
        }
        if (reader.peek() == JsonToken.BEGIN_ARRAY) {
            reader.close();
            reader.close();
            return 1;
        }
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (((name.hashCode() == 1684719674 && name.equals(JSON_NAME_SCHEMA_VERSION)) ? (byte) 0 : (byte) -1) == 0) {
                int schemaVersion = reader.nextInt();
                reader.close();
                reader.close();
                return schemaVersion;
            }
            reader.skipValue();
        }
        reader.endObject();
        reader.close();
        return -1;
    }

    private void readRestrictionsArray(JsonReader reader, List<CarUxRestrictionsConfiguration> configs, int schemaVersion) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            configs.add(CarUxRestrictionsConfiguration.readJson(reader, schemaVersion));
        }
        reader.endArray();
    }

    public void setUxRChangeBroadcastEnabled(boolean enable) {
        if (!isDebugBuild()) {
            Log.e(TAG, "Cannot set UX restriction change broadcast.");
            return;
        }
        if (this.mContext.getPackageManager().checkSignatures(Process.myUid(), Binder.getCallingUid()) != 0) {
            throw new SecurityException("Caller " + this.mContext.getPackageManager().getNameForUid(Binder.getCallingUid()) + " does not have the right signature");
        }
        synchronized (this.mLock) {
            if (enable) {
                this.mUxRChangeBroadcastEnabled = enable;
                handleDispatchUxRestrictionsLocked(this.mDrivingStateService.getCurrentDrivingState().eventValue, getCurrentSpeed().floatValue());
            } else {
                handleDispatchUxRestrictionsLocked(0, 0.0f);
                this.mUxRChangeBroadcastEnabled = enable;
            }
        }
    }

    private boolean isDebugBuild() {
        return Build.IS_USERDEBUG || Build.IS_ENG;
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        synchronized (this.mLock) {
            writer.println("*CarUxRestrictionsManagerService*");
            this.mUxRClients.dump(writer, "UX Restrictions Clients ");
            Iterator<Byte> it = this.mCurrentUxRestrictions.keySet().iterator();
            while (it.hasNext()) {
                byte port = it.next().byteValue();
                CarUxRestrictions restrictions = this.mCurrentUxRestrictions.get(Byte.valueOf(port));
                writer.printf("Port: 0x%02X UXR: %s\n", Byte.valueOf(port), restrictions.toString());
            }
            if (isDebugBuild()) {
                writer.println("mUxRChangeBroadcastEnabled? " + this.mUxRChangeBroadcastEnabled);
            }
            writer.println("UX Restriction configurations:");
            for (CarUxRestrictionsConfiguration config : this.mCarUxRestrictionsConfigurations.values()) {
                config.dump(writer);
            }
            writer.println("UX Restriction change log:");
            for (Utils.TransitionLog tlog : this.mTransitionLogs) {
                writer.println(tlog);
            }
            writer.println("UX Restriction display info:");
            for (int i = this.mActivityViewDisplayInfoMap.size() - 1; i >= 0; i--) {
                DisplayInfo info = this.mActivityViewDisplayInfoMap.valueAt(i);
                writer.printf("Display%d: physicalDisplayId=%d, owner=%s\n", Integer.valueOf(this.mActivityViewDisplayInfoMap.keyAt(i)), Integer.valueOf(info.mPhysicalDisplayId), info.mOwner);
            }
        }
    }

    void handleDrivingStateEventLocked(CarDrivingStateEvent event) {
        if (event == null) {
            return;
        }
        int drivingState = event.eventValue;
        Float speed = getCurrentSpeed();
        if (speed.floatValue() != SPEED_NOT_AVAILABLE) {
            this.mCurrentMovingSpeed = speed.floatValue();
        } else if (drivingState == 0 || drivingState == -1) {
            logd("Speed null when driving state is: " + drivingState);
            this.mCurrentMovingSpeed = 0.0f;
        } else {
            Log.e(TAG, "Unexpected:  Speed null when driving state is: " + drivingState);
            return;
        }
        handleDispatchUxRestrictionsLocked(drivingState, this.mCurrentMovingSpeed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSpeedChangeLocked(float newSpeed) {
        int currentDrivingState;
        if (newSpeed == this.mCurrentMovingSpeed || (currentDrivingState = this.mDrivingStateService.getCurrentDrivingState().eventValue) != 2) {
            return;
        }
        this.mCurrentMovingSpeed = newSpeed;
        handleDispatchUxRestrictionsLocked(currentDrivingState, newSpeed);
    }

    private void handleDispatchUxRestrictionsLocked(int currentDrivingState, float speed) {
        Objects.requireNonNull(this.mCarUxRestrictionsConfigurations, "mCarUxRestrictionsConfigurations must be initialized");
        Objects.requireNonNull(this.mCurrentUxRestrictions, "mCurrentUxRestrictions must be initialized");
        if (isDebugBuild() && !this.mUxRChangeBroadcastEnabled) {
            Log.d(TAG, "Not dispatching UX Restriction due to setting");
            return;
        }
        Map<Byte, CarUxRestrictions> newUxRestrictions = new HashMap<>();
        Iterator<Byte> it = this.mPhysicalPorts.iterator();
        while (it.hasNext()) {
            byte port = it.next().byteValue();
            CarUxRestrictionsConfiguration config = this.mCarUxRestrictionsConfigurations.get(Byte.valueOf(port));
            if (config != null) {
                CarUxRestrictions uxRestrictions = config.getUxRestrictions(currentDrivingState, speed, this.mRestrictionMode);
                logd(String.format("Display port 0x%02x\tDO old->new: %b -> %b", Byte.valueOf(port), Boolean.valueOf(this.mCurrentUxRestrictions.get(Byte.valueOf(port)).isRequiresDistractionOptimization()), Boolean.valueOf(uxRestrictions.isRequiresDistractionOptimization())));
                logd(String.format("Display port 0x%02x\tUxR old->new: 0x%x -> 0x%x", Byte.valueOf(port), Integer.valueOf(this.mCurrentUxRestrictions.get(Byte.valueOf(port)).getActiveRestrictions()), Integer.valueOf(uxRestrictions.getActiveRestrictions())));
                newUxRestrictions.put(Byte.valueOf(port), uxRestrictions);
            }
        }
        Set<Byte> displayToDispatch = new ArraySet<>();
        Iterator<Byte> it2 = newUxRestrictions.keySet().iterator();
        while (it2.hasNext()) {
            byte port2 = it2.next().byteValue();
            if (!this.mCurrentUxRestrictions.containsKey(Byte.valueOf(port2))) {
                Log.wtf(TAG, "Unrecognized port:" + ((int) port2));
            } else if (!this.mCurrentUxRestrictions.get(Byte.valueOf(port2)).isSameRestrictions(newUxRestrictions.get(Byte.valueOf(port2)))) {
                displayToDispatch.add(Byte.valueOf(port2));
            }
        }
        if (displayToDispatch.isEmpty()) {
            return;
        }
        Iterator<Byte> it3 = displayToDispatch.iterator();
        while (it3.hasNext()) {
            byte port3 = it3.next().byteValue();
            addTransitionLogLocked(this.mCurrentUxRestrictions.get(Byte.valueOf(port3)), newUxRestrictions.get(Byte.valueOf(port3)));
        }
        dispatchRestrictionsToClients(newUxRestrictions, displayToDispatch);
        this.mCurrentUxRestrictions = newUxRestrictions;
    }

    private void dispatchRestrictionsToClients(final Map<Byte, CarUxRestrictions> displayRestrictions, final Set<Byte> displayToDispatch) {
        logd("dispatching to clients");
        boolean success = this.mClientDispatchHandler.post(new Runnable() { // from class: com.android.car.-$$Lambda$CarUxRestrictionsManagerService$iYWKN5-O43_lJq_e42s6RBJeyfY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dispatchRestrictionsToClients$0$CarUxRestrictionsManagerService(displayToDispatch, displayRestrictions);
            }
        });
        if (!success) {
            Log.e(TAG, String.format("Unable to post (%s) event to dispatch handler", displayRestrictions));
        }
    }

    public /* synthetic */ void lambda$dispatchRestrictionsToClients$0$CarUxRestrictionsManagerService(Set displayToDispatch, Map displayRestrictions) {
        CarUxRestrictions restrictions;
        int numClients = this.mUxRClients.beginBroadcast();
        for (int i = 0; i < numClients; i++) {
            ICarUxRestrictionsChangeListener callback = this.mUxRClients.getBroadcastItem(i);
            RemoteCallbackListCookie cookie = (RemoteCallbackListCookie) this.mUxRClients.getBroadcastCookie(i);
            if (displayToDispatch.contains(cookie.mPhysicalPort) && (restrictions = (CarUxRestrictions) displayRestrictions.get(cookie.mPhysicalPort)) != null) {
                try {
                    callback.onUxRestrictionsChanged(restrictions);
                } catch (RemoteException e) {
                    Log.e(TAG, String.format("Dispatch to listener %s failed for restrictions (%s)", callback, restrictions));
                }
            }
        }
        this.mUxRClients.finishBroadcast();
    }

    static byte getDefaultDisplayPhysicalPort(DisplayManager displayManager) {
        Display defaultDisplay = displayManager.getDisplay(0);
        DisplayAddress.Physical address = defaultDisplay.getAddress();
        if (address == null) {
            Log.e(TAG, "Default display does not have physical display port. Using 0 as port.");
            return (byte) 0;
        }
        return address.getPort();
    }

    private void initPhysicalPort() {
        for (Display display : this.mDisplayManager.getDisplays()) {
            if (display.getType() != 5) {
                if (display.getDisplayId() == 0 && display.getAddress() == null) {
                    if (Log.isLoggable(TAG, 4)) {
                        Log.i(TAG, "Default display does not have display address. Using default.");
                    }
                    synchronized (this.mLock) {
                        this.mPhysicalPorts.add(Byte.valueOf(this.mDefaultDisplayPhysicalPort));
                    }
                } else if (display.getAddress() instanceof DisplayAddress.Physical) {
                    byte port = display.getAddress().getPort();
                    if (Log.isLoggable(TAG, 4)) {
                        Log.i(TAG, String.format("Display %d uses port %d", Integer.valueOf(display.getDisplayId()), Integer.valueOf(Byte.toUnsignedInt(port))));
                    }
                    synchronized (this.mLock) {
                        this.mPhysicalPorts.add(Byte.valueOf(port));
                    }
                } else {
                    Log.w(TAG, "At init non-virtual display has a non-physical display address: " + display);
                }
            }
        }
    }

    private Map<Byte, CarUxRestrictionsConfiguration> convertToMap(List<CarUxRestrictionsConfiguration> configs) {
        byte port;
        validateConfigs(configs);
        Map<Byte, CarUxRestrictionsConfiguration> result = new HashMap<>();
        if (configs.size() == 1) {
            CarUxRestrictionsConfiguration config = configs.get(0);
            synchronized (this.mLock) {
                if (config.getPhysicalPort() == null) {
                    port = this.mDefaultDisplayPhysicalPort;
                } else {
                    port = config.getPhysicalPort().byteValue();
                }
                result.put(Byte.valueOf(port), config);
            }
        } else {
            for (CarUxRestrictionsConfiguration config2 : configs) {
                result.put(config2.getPhysicalPort(), config2);
            }
        }
        return result;
    }

    void validateConfigs(List<CarUxRestrictionsConfiguration> configs) {
        if (configs.size() == 0) {
            throw new IllegalArgumentException("Empty configuration.");
        }
        if (configs.size() == 1) {
            return;
        }
        CarUxRestrictionsConfiguration first = configs.get(0);
        Set<Byte> existingPorts = new ArraySet<>();
        for (CarUxRestrictionsConfiguration config : configs) {
            if (!config.hasSameParameters(first)) {
                throw new IllegalArgumentException("Configurations should have the same restrictions parameters.");
            }
            Byte port = config.getPhysicalPort();
            if (port == null) {
                throw new IllegalArgumentException("Input contains multiple configurations; each must set physical port.");
            }
            if (existingPorts.contains(port)) {
                throw new IllegalArgumentException("Multiple configurations for port " + port);
            }
            existingPorts.add(port);
        }
    }

    private Byte getPhysicalPortLocked(int displayId) {
        if (!this.mPortLookup.containsKey(Integer.valueOf(displayId))) {
            Display display = this.mDisplayManager.getDisplay(displayId);
            if (display == null) {
                Log.w(TAG, "Could not retrieve display for id: " + displayId);
                return null;
            }
            byte port = doGetPhysicalPortLocked(display);
            this.mPortLookup.put(Integer.valueOf(displayId), Byte.valueOf(port));
        }
        return this.mPortLookup.get(Integer.valueOf(displayId));
    }

    private byte doGetPhysicalPortLocked(Display display) {
        if (display.getType() == 5) {
            Log.e(TAG, "Display " + display + " is a virtual display and does not have a known port.");
            return this.mDefaultDisplayPhysicalPort;
        }
        DisplayAddress.Physical address = display.getAddress();
        if (address == null) {
            Log.e(TAG, "Display " + display + " is not a virtual display but has null DisplayAddress.");
            return this.mDefaultDisplayPhysicalPort;
        }
        if (!(address instanceof DisplayAddress.Physical)) {
            Log.e(TAG, "Display " + display + " has non-physical address: " + address);
            return this.mDefaultDisplayPhysicalPort;
        }
        return address.getPort();
    }

    private CarUxRestrictions createUnrestrictedRestrictions() {
        return new CarUxRestrictions.Builder(false, 0, SystemClock.elapsedRealtimeNanos()).build();
    }

    private CarUxRestrictions createFullyRestrictedRestrictions() {
        return new CarUxRestrictions.Builder(true, 511, SystemClock.elapsedRealtimeNanos()).build();
    }

    CarUxRestrictionsConfiguration createDefaultConfig(byte port) {
        return new CarUxRestrictionsConfiguration.Builder().setPhysicalPort(port).setUxRestrictions(0, false, 0).setUxRestrictions(1, false, 0).setUxRestrictions(2, true, 511).setUxRestrictions(-1, true, 511).build();
    }

    private void addTransitionLogLocked(String name, String from, String to, long timestamp, String extra) {
        if (this.mTransitionLogs.size() >= 20) {
            this.mTransitionLogs.remove();
        }
        Utils.TransitionLog tLog = new Utils.TransitionLog(name, from, to, timestamp, extra);
        this.mTransitionLogs.add(tLog);
    }

    private void addTransitionLogLocked(CarUxRestrictions oldRestrictions, CarUxRestrictions newRestrictions) {
        if (this.mTransitionLogs.size() >= 20) {
            this.mTransitionLogs.remove();
        }
        StringBuilder extra = new StringBuilder();
        extra.append(oldRestrictions.isRequiresDistractionOptimization() ? "DO -> " : "No DO -> ");
        extra.append(newRestrictions.isRequiresDistractionOptimization() ? "DO" : "No DO");
        Utils.TransitionLog tLog = new Utils.TransitionLog(TAG, Integer.valueOf(oldRestrictions.getActiveRestrictions()), Integer.valueOf(newRestrictions.getActiveRestrictions()), System.currentTimeMillis(), extra.toString());
        this.mTransitionLogs.add(tLog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logd(String msg) {
    }

    private static final class DisplayInfo {
        final IRemoteCallback mOwner;
        final int mPhysicalDisplayId;

        DisplayInfo(IRemoteCallback owner, int physicalDisplayId) {
            this.mOwner = owner;
            this.mPhysicalDisplayId = physicalDisplayId;
        }
    }

    public void reportVirtualDisplayToPhysicalDisplay(IRemoteCallback callback, int virtualDisplayId, int physicalDisplayId) {
        logd("reportVirtualDisplayToPhysicalDisplay: callback=" + callback + ", virtualDisplayId=" + virtualDisplayId + ", physicalDisplayId=" + physicalDisplayId);
        boolean release = physicalDisplayId == -1;
        checkCallerOwnsDisplay(virtualDisplayId, release);
        synchronized (this.mLock) {
            if (release) {
                this.mRemoteCallbackList.unregister(callback);
                this.mActivityViewDisplayInfoMap.delete(virtualDisplayId);
                this.mPortLookup.remove(Integer.valueOf(virtualDisplayId));
                return;
            }
            this.mRemoteCallbackList.register(callback);
            this.mActivityViewDisplayInfoMap.put(virtualDisplayId, new DisplayInfo(callback, physicalDisplayId));
            Byte physicalPort = getPhysicalPortLocked(physicalDisplayId);
            if (physicalPort == null) {
                Log.wtf(TAG, "No known physicalPort for displayId:" + physicalDisplayId);
                physicalPort = Byte.valueOf(this.mDefaultDisplayPhysicalPort);
            }
            this.mPortLookup.put(Integer.valueOf(virtualDisplayId), physicalPort);
        }
    }

    public int getMappedPhysicalDisplayOfVirtualDisplay(int displayId) {
        logd("getMappedPhysicalDisplayOfVirtualDisplay: displayId=" + displayId);
        synchronized (this.mLock) {
            DisplayInfo foundInfo = this.mActivityViewDisplayInfoMap.get(displayId);
            if (foundInfo == null) {
                return -1;
            }
            while (foundInfo != null) {
                displayId = foundInfo.mPhysicalDisplayId;
                foundInfo = this.mActivityViewDisplayInfoMap.get(displayId);
            }
            return displayId;
        }
    }

    private void checkCallerOwnsDisplay(int displayId, boolean release) {
        Display display = this.mDisplayManager.getDisplay(displayId);
        if (display == null) {
            if (release) {
                return;
            }
            throw new IllegalArgumentException("Cannot find display for non-existent displayId: " + displayId);
        }
        int callingUid = Binder.getCallingUid();
        int displayOwnerUid = display.getOwnerUid();
        if (callingUid != displayOwnerUid) {
            throw new SecurityException("The caller doesn't own the display: callingUid=" + callingUid + ", displayOwnerUid=" + displayOwnerUid);
        }
    }
}
