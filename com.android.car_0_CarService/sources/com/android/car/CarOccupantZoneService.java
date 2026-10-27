package com.android.car;

import android.app.ActivityManager;
import android.car.Car;
import android.car.CarInfoManager;
import android.car.CarOccupantZoneManager;
import android.car.ICarOccupantZone;
import android.car.ICarOccupantZoneCallback;
import android.car.user.CarUserManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.UserManager;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.IntArray;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.DisplayAddress;
import com.android.car.Manifest;
import com.android.car.user.CarUserService;
import com.android.internal.car.ICarServiceHelper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class CarOccupantZoneService extends ICarOccupantZone.Stub implements CarServiceBase {
    private static final String ALL_COMPONENTS = "*";
    private static final String TAG = "CAR.OCCUPANT";
    private final HashMap<Integer, OccupantConfig> mActiveOccupantConfigs;
    private final SparseIntArray mAudioZoneIdToOccupantZoneIdMapping;
    private final RemoteCallbackList<ICarOccupantZoneCallback> mClientCallbacks;
    private final Context mContext;
    private final HashMap<Integer, DisplayConfig> mDisplayConfigs;
    final DisplayManager.DisplayListener mDisplayListener;
    private final DisplayManager mDisplayManager;
    private int mDriverSeat;
    private int mDriverZoneId;
    private final boolean mEnableProfileUserAssignmentForMultiDisplay;
    private boolean mEnableSourcePreferred;
    private ICarServiceHelper mICarServiceHelper;
    private final Object mLock;
    private final HashMap<Integer, CarOccupantZoneManager.OccupantZoneInfo> mOccupantsConfig;
    final CarUserService.PassengerCallback mPassengerCallback;
    private final ArraySet<Integer> mProfileUsers;
    private ArrayList<ComponentName> mSourcePreferredComponents;
    final CarUserManager.UserLifecycleListener mUserLifecycleListener;
    private final UserManager mUserManager;

    static class DisplayConfig {
        public final int displayType;
        public final int occupantZoneId;

        DisplayConfig(int displayType, int occupantZoneId) {
            this.displayType = displayType;
            this.occupantZoneId = occupantZoneId;
        }

        public String toString() {
            StringBuilder b = new StringBuilder(64);
            b.append("{displayType=");
            b.append(Integer.toHexString(this.displayType));
            b.append(" occupantZoneId=");
            b.append(this.occupantZoneId);
            b.append("}");
            return b.toString();
        }
    }

    static class DisplayInfo {
        public final Display display;
        public final int displayType;

        DisplayInfo(Display display, int displayType) {
            this.display = display;
            this.displayType = displayType;
        }

        public String toString() {
            StringBuilder b = new StringBuilder(64);
            b.append("{displayId=");
            b.append(this.display.getDisplayId());
            b.append(" displayType=");
            b.append(this.displayType);
            b.append("}");
            return b.toString();
        }
    }

    static class OccupantConfig {
        public int userId = -10000;
        public final ArrayList<DisplayInfo> displayInfos = new ArrayList<>();
        public int audioZoneId = -1;

        OccupantConfig() {
        }

        public String toString() {
            StringBuilder b = new StringBuilder(128);
            b.append("{userId=");
            b.append(this.userId);
            b.append(" displays=");
            for (int i = 0; i < this.displayInfos.size(); i++) {
                b.append(this.displayInfos.get(i).toString());
            }
            b.append(" audioZoneId=");
            int i2 = this.audioZoneId;
            if (i2 != -1) {
                b.append(i2);
            } else {
                b.append("none");
            }
            b.append("}");
            return b.toString();
        }
    }

    public /* synthetic */ void lambda$new$0$CarOccupantZoneService(CarUserManager.UserLifecycleEvent event) {
        if (Log.isLoggable(CarLog.TAG_MEDIA, 3)) {
            Log.d(CarLog.TAG_MEDIA, "onEvent(" + event + ")");
        }
        if (2 == event.getEventType()) {
            handleUserChange();
        }
    }

    public CarOccupantZoneService(Context context) {
        this(context, (DisplayManager) context.getSystemService(DisplayManager.class), (UserManager) context.getSystemService(UserManager.class), context.getResources().getBoolean(R.bool.enableProfileUserAssignmentForMultiDisplay) && context.getPackageManager().hasSystemFeature("android.software.managed_users"));
    }

    public CarOccupantZoneService(Context context, DisplayManager displayManager, UserManager userManager, boolean enableProfileUserAssignmentForMultiDisplay) {
        this.mLock = new Object();
        this.mProfileUsers = new ArraySet<>();
        this.mOccupantsConfig = new HashMap<>();
        this.mDisplayConfigs = new HashMap<>();
        this.mAudioZoneIdToOccupantZoneIdMapping = new SparseIntArray();
        this.mActiveOccupantConfigs = new HashMap<>();
        this.mDriverZoneId = -1;
        this.mUserLifecycleListener = new CarUserManager.UserLifecycleListener() { // from class: com.android.car.-$$Lambda$CarOccupantZoneService$d4QG_FBij2e7_3yeok4hIg8EMWc
            public final void onEvent(CarUserManager.UserLifecycleEvent userLifecycleEvent) {
                this.f$0.lambda$new$0$CarOccupantZoneService(userLifecycleEvent);
            }
        };
        this.mPassengerCallback = new CarUserService.PassengerCallback() { // from class: com.android.car.CarOccupantZoneService.1
            @Override // com.android.car.user.CarUserService.PassengerCallback
            public void onPassengerStarted(int passengerId, int zoneId) {
                CarOccupantZoneService.this.handlePassengerStarted(passengerId, zoneId);
            }

            @Override // com.android.car.user.CarUserService.PassengerCallback
            public void onPassengerStopped(int passengerId) {
                CarOccupantZoneService.this.handlePassengerStopped(passengerId);
            }
        };
        this.mDisplayListener = new DisplayManager.DisplayListener() { // from class: com.android.car.CarOccupantZoneService.2
            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayAdded(int displayId) {
                CarOccupantZoneService.this.handleDisplayChange();
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayRemoved(int displayId) {
                CarOccupantZoneService.this.handleDisplayChange();
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayChanged(int displayId) {
            }
        };
        this.mClientCallbacks = new RemoteCallbackList<>();
        this.mDriverSeat = 0;
        this.mContext = context;
        this.mDisplayManager = displayManager;
        this.mUserManager = userManager;
        this.mEnableProfileUserAssignmentForMultiDisplay = enableProfileUserAssignmentForMultiDisplay;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        Car car = new Car(this.mContext, null, null);
        CarInfoManager infoManager = new CarInfoManager(car, (IBinder) CarLocalServices.getService(CarPropertyService.class));
        int driverSeat = infoManager.getDriverSeat();
        synchronized (this.mLock) {
            this.mDriverSeat = driverSeat;
            parseOccupantZoneConfigsLocked();
            parseDisplayConfigsLocked();
            handleActiveDisplaysLocked();
            handleAudioZoneChangesLocked();
            handleUserChangesLocked();
        }
        CarUserService userService = (CarUserService) CarLocalServices.getService(CarUserService.class);
        userService.addUserLifecycleListener(this.mUserLifecycleListener);
        userService.addPassengerCallback(this.mPassengerCallback);
        this.mDisplayManager.registerDisplayListener(this.mDisplayListener, new Handler(Looper.getMainLooper()));
        CarUserService.ZoneUserBindingHelper helper = new CarUserService.ZoneUserBindingHelper() { // from class: com.android.car.CarOccupantZoneService.3
            @Override // com.android.car.user.CarUserService.ZoneUserBindingHelper
            public List<CarOccupantZoneManager.OccupantZoneInfo> getOccupantZones(int occupantType) {
                List<CarOccupantZoneManager.OccupantZoneInfo> zones = new ArrayList<>();
                for (CarOccupantZoneManager.OccupantZoneInfo ozi : CarOccupantZoneService.this.getAllOccupantZones()) {
                    if (ozi.occupantType == occupantType) {
                        zones.add(ozi);
                    }
                }
                return zones;
            }

            @Override // com.android.car.user.CarUserService.ZoneUserBindingHelper
            public boolean assignUserToOccupantZone(int userId, int zoneId) {
                synchronized (CarOccupantZoneService.this.mLock) {
                    for (Map.Entry<Integer, OccupantConfig> entry : CarOccupantZoneService.this.mActiveOccupantConfigs.entrySet()) {
                        OccupantConfig config = entry.getValue();
                        if (config.userId == userId && zoneId != entry.getKey().intValue()) {
                            Log.w("CAR.OCCUPANT", "cannot assign user to two different zone simultaneously");
                            return false;
                        }
                    }
                    OccupantConfig zoneConfig = (OccupantConfig) CarOccupantZoneService.this.mActiveOccupantConfigs.get(Integer.valueOf(zoneId));
                    if (zoneConfig == null) {
                        Log.w("CAR.OCCUPANT", "cannot find the zone(" + zoneId + ")");
                        return false;
                    }
                    if (zoneConfig.userId != -10000 && zoneConfig.userId != userId) {
                        Log.w("CAR.OCCUPANT", "other user already occupies the zone(" + zoneId + ")");
                        return false;
                    }
                    zoneConfig.userId = userId;
                    return true;
                }
            }

            @Override // com.android.car.user.CarUserService.ZoneUserBindingHelper
            public boolean unassignUserFromOccupantZone(int userId) {
                synchronized (CarOccupantZoneService.this.mLock) {
                    Iterator it = CarOccupantZoneService.this.mActiveOccupantConfigs.values().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        OccupantConfig config = (OccupantConfig) it.next();
                        if (config.userId == userId) {
                            config.userId = -10000;
                            break;
                        }
                    }
                }
                return true;
            }

            @Override // com.android.car.user.CarUserService.ZoneUserBindingHelper
            public boolean isPassengerDisplayAvailable() {
                for (CarOccupantZoneManager.OccupantZoneInfo ozi : CarOccupantZoneService.this.getAllOccupantZones()) {
                    if (CarOccupantZoneService.this.getDisplayForOccupant(ozi.zoneId, 1) != -1 && ozi.occupantType != 0) {
                        return true;
                    }
                }
                return false;
            }
        };
        userService.setZoneUserBindingHelper(helper);
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        this.mDisplayManager.unregisterDisplayListener(this.mDisplayListener);
        CarUserService userService = (CarUserService) CarLocalServices.getService(CarUserService.class);
        userService.removeUserLifecycleListener(this.mUserLifecycleListener);
        userService.removePassengerCallback(this.mPassengerCallback);
        synchronized (this.mLock) {
            this.mOccupantsConfig.clear();
            this.mDisplayConfigs.clear();
            this.mAudioZoneIdToOccupantZoneIdMapping.clear();
            this.mActiveOccupantConfigs.clear();
        }
    }

    public HashMap<Integer, CarOccupantZoneManager.OccupantZoneInfo> getOccupantsConfig() {
        HashMap<Integer, CarOccupantZoneManager.OccupantZoneInfo> map;
        synchronized (this.mLock) {
            map = (HashMap) this.mOccupantsConfig.clone();
        }
        return map;
    }

    public HashMap<Integer, DisplayConfig> getDisplayConfigs() {
        HashMap<Integer, DisplayConfig> map;
        synchronized (this.mLock) {
            map = (HashMap) this.mDisplayConfigs.clone();
        }
        return map;
    }

    SparseIntArray getAudioConfigs() {
        SparseIntArray sparseIntArrayClone;
        synchronized (this.mLock) {
            sparseIntArrayClone = this.mAudioZoneIdToOccupantZoneIdMapping.clone();
        }
        return sparseIntArrayClone;
    }

    public HashMap<Integer, OccupantConfig> getActiveOccupantConfigs() {
        HashMap<Integer, OccupantConfig> map;
        synchronized (this.mLock) {
            map = (HashMap) this.mActiveOccupantConfigs.clone();
        }
        return map;
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*OccupantZoneService*");
        synchronized (this.mLock) {
            writer.println("**mOccupantsConfig**");
            for (Map.Entry<Integer, CarOccupantZoneManager.OccupantZoneInfo> entry : this.mOccupantsConfig.entrySet()) {
                writer.println(" zoneId=" + entry.getKey() + " info=" + entry.getValue().toString());
            }
            writer.println("**mDisplayConfigs**");
            for (Map.Entry<Integer, DisplayConfig> entry2 : this.mDisplayConfigs.entrySet()) {
                writer.println(" port=" + Integer.toHexString(entry2.getKey().intValue()) + " config=" + entry2.getValue().toString());
            }
            writer.println("**mAudioZoneIdToOccupantZoneIdMapping**");
            for (int index = 0; index < this.mAudioZoneIdToOccupantZoneIdMapping.size(); index++) {
                int audioZoneId = this.mAudioZoneIdToOccupantZoneIdMapping.keyAt(index);
                writer.println(" audioZoneId=" + Integer.toHexString(audioZoneId) + " zoneId=" + this.mAudioZoneIdToOccupantZoneIdMapping.get(audioZoneId));
            }
            writer.println("**mActiveOccupantConfigs**");
            for (Map.Entry<Integer, OccupantConfig> entry3 : this.mActiveOccupantConfigs.entrySet()) {
                writer.println(" zoneId=" + entry3.getKey() + " config=" + entry3.getValue().toString());
            }
            writer.println("mEnableProfileUserAssignmentForMultiDisplay:" + this.mEnableProfileUserAssignmentForMultiDisplay);
            writer.println("mEnableSourcePreferred:" + this.mEnableSourcePreferred);
            writer.append("mSourcePreferredComponents: [");
            if (this.mSourcePreferredComponents != null) {
                for (int i = 0; i < this.mSourcePreferredComponents.size(); i++) {
                    if (i > 0) {
                        writer.append(' ');
                    }
                    writer.append((CharSequence) this.mSourcePreferredComponents.get(i).toString());
                }
            }
            writer.println(']');
        }
    }

    public List<CarOccupantZoneManager.OccupantZoneInfo> getAllOccupantZones() {
        List<CarOccupantZoneManager.OccupantZoneInfo> infos;
        synchronized (this.mLock) {
            infos = new ArrayList<>();
            for (Integer zoneId : this.mActiveOccupantConfigs.keySet()) {
                infos.add(this.mOccupantsConfig.get(zoneId));
            }
        }
        return infos;
    }

    public int[] getAllDisplaysForOccupantZone(int occupantZoneId) {
        synchronized (this.mLock) {
            OccupantConfig config = this.mActiveOccupantConfigs.get(Integer.valueOf(occupantZoneId));
            if (config == null) {
                return new int[0];
            }
            int[] displayIds = new int[config.displayInfos.size()];
            for (int i = 0; i < config.displayInfos.size(); i++) {
                displayIds[i] = config.displayInfos.get(i).display.getDisplayId();
            }
            return displayIds;
        }
    }

    public int getDisplayForOccupant(int occupantZoneId, int displayType) {
        synchronized (this.mLock) {
            OccupantConfig config = this.mActiveOccupantConfigs.get(Integer.valueOf(occupantZoneId));
            if (config == null) {
                return -1;
            }
            for (int i = 0; i < config.displayInfos.size(); i++) {
                if (displayType == config.displayInfos.get(i).displayType) {
                    return config.displayInfos.get(i).display.getDisplayId();
                }
            }
            return -1;
        }
    }

    public int getAudioZoneIdForOccupant(int occupantZoneId) {
        enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_SETTINGS);
        synchronized (this.mLock) {
            OccupantConfig config = this.mActiveOccupantConfigs.get(Integer.valueOf(occupantZoneId));
            if (config != null) {
                return config.audioZoneId;
            }
            if (!this.mOccupantsConfig.containsKey(Integer.valueOf(occupantZoneId))) {
                return -1;
            }
            return getAudioZoneIdForOccupantLocked(occupantZoneId);
        }
    }

    private int getAudioZoneIdForOccupantLocked(int occupantZoneId) {
        for (int index = 0; index < this.mAudioZoneIdToOccupantZoneIdMapping.size(); index++) {
            int audioZoneId = this.mAudioZoneIdToOccupantZoneIdMapping.keyAt(index);
            if (occupantZoneId == this.mAudioZoneIdToOccupantZoneIdMapping.get(audioZoneId)) {
                return audioZoneId;
            }
        }
        return -1;
    }

    public CarOccupantZoneManager.OccupantZoneInfo getOccupantForAudioZoneId(int audioZoneId) {
        enforcePermission(Manifest.permission.CAR_CONTROL_AUDIO_SETTINGS);
        synchronized (this.mLock) {
            int occupantZoneId = this.mAudioZoneIdToOccupantZoneIdMapping.get(audioZoneId, -1);
            if (occupantZoneId == -1) {
                return null;
            }
            return this.mOccupantsConfig.get(Integer.valueOf(occupantZoneId));
        }
    }

    private DisplayConfig findDisplayConfigForDisplayLocked(int displayId) {
        Byte portAddress;
        for (Map.Entry<Integer, DisplayConfig> entry : this.mDisplayConfigs.entrySet()) {
            Display display = this.mDisplayManager.getDisplay(displayId);
            if (display != null && (portAddress = getPortAddress(display)) != null) {
                DisplayConfig config = this.mDisplayConfigs.get(Integer.valueOf(Byte.toUnsignedInt(portAddress.byteValue())));
                return config;
            }
        }
        return null;
    }

    public int getDisplayType(int displayId) {
        synchronized (this.mLock) {
            DisplayConfig config = findDisplayConfigForDisplayLocked(displayId);
            if (config != null) {
                return config.displayType;
            }
            return 0;
        }
    }

    public int getUserForOccupant(int occupantZoneId) {
        synchronized (this.mLock) {
            OccupantConfig config = this.mActiveOccupantConfigs.get(Integer.valueOf(occupantZoneId));
            if (config == null) {
                return -10000;
            }
            return config.userId;
        }
    }

    public int getOccupantZoneIdForUserId(int userId) {
        synchronized (this.mLock) {
            Iterator<Integer> it = this.mActiveOccupantConfigs.keySet().iterator();
            while (it.hasNext()) {
                int occupantZoneId = it.next().intValue();
                OccupantConfig config = this.mActiveOccupantConfigs.get(Integer.valueOf(occupantZoneId));
                if (config.userId == userId) {
                    return occupantZoneId;
                }
            }
            Log.w("CAR.OCCUPANT", "Could not find occupantZoneId for userId" + userId + " returning invalid occupant zone id -1");
            return -1;
        }
    }

    public int getDriverUserId() {
        return getCurrentUser();
    }

    public void setAudioZoneIdsForOccupantZoneIds(SparseIntArray audioZoneIdToOccupantZoneMapping) {
        Objects.requireNonNull(audioZoneIdToOccupantZoneMapping, "audioZoneIdToOccupantZoneMapping can not be null");
        synchronized (this.mLock) {
            validateOccupantZoneIdsLocked(audioZoneIdToOccupantZoneMapping);
            this.mAudioZoneIdToOccupantZoneIdMapping.clear();
            for (int index = 0; index < audioZoneIdToOccupantZoneMapping.size(); index++) {
                int audioZoneId = audioZoneIdToOccupantZoneMapping.keyAt(index);
                this.mAudioZoneIdToOccupantZoneIdMapping.put(audioZoneId, audioZoneIdToOccupantZoneMapping.get(audioZoneId));
            }
            handleAudioZoneChangesLocked();
        }
        sendConfigChangeEvent(4);
    }

    private void validateOccupantZoneIdsLocked(SparseIntArray audioZoneIdToOccupantZoneMapping) {
        for (int i = 0; i < audioZoneIdToOccupantZoneMapping.size(); i++) {
            int occupantZoneId = audioZoneIdToOccupantZoneMapping.get(audioZoneIdToOccupantZoneMapping.keyAt(i));
            if (!this.mOccupantsConfig.containsKey(Integer.valueOf(occupantZoneId))) {
                throw new IllegalArgumentException("occupantZoneId " + occupantZoneId + " does not exist.");
            }
        }
    }

    public void registerCallback(ICarOccupantZoneCallback callback) {
        this.mClientCallbacks.register(callback);
    }

    public void unregisterCallback(ICarOccupantZoneCallback callback) {
        this.mClientCallbacks.unregister(callback);
    }

    public boolean assignProfileUserToOccupantZone(int occupantZoneId, int userId) {
        enforcePermission("android.permission.MANAGE_USERS");
        if (!this.mEnableProfileUserAssignmentForMultiDisplay) {
            throw new IllegalStateException("feature not enabled");
        }
        int currentUser = getCurrentUser();
        synchronized (this.mLock) {
            if (occupantZoneId == this.mDriverZoneId) {
                throw new IllegalArgumentException("Driver zone cannot have profile user");
            }
            updateEnabledProfilesLocked(currentUser);
            if (!this.mProfileUsers.contains(Integer.valueOf(userId)) && userId != -10000) {
                Log.w("CAR.OCCUPANT", "Invalid profile user id:" + userId);
                return false;
            }
            if (!this.mUserManager.isUserRunning(userId)) {
                Log.w("CAR.OCCUPANT", "User is not running:" + userId);
                return false;
            }
            OccupantConfig config = this.mActiveOccupantConfigs.get(Integer.valueOf(occupantZoneId));
            if (config == null) {
                throw new IllegalArgumentException("Invalid occupantZoneId:" + occupantZoneId);
            }
            if (config.userId == userId && userId != -10000) {
                Log.w("CAR.OCCUPANT", "assignProfileUserToOccupantZone zone:" + occupantZoneId + " already set to user:" + userId);
                return true;
            }
            if (userId == -10000) {
                config.userId = currentUser;
            } else {
                config.userId = userId;
            }
            sendConfigChangeEvent(2);
            return true;
        }
    }

    public void setCarServiceHelper(ICarServiceHelper helper) {
        doSyncWithCarServiceHelper(helper, true, true, true);
    }

    private void doSyncWithCarServiceHelper(ICarServiceHelper helper, boolean updateDisplay, boolean updateUser, boolean updateConfig) {
        int[] passengerDisplays = null;
        ArrayMap<Integer, IntArray> whitelists = null;
        ICarServiceHelper helperToUse = helper;
        synchronized (this.mLock) {
            if (helper == null) {
                if (this.mICarServiceHelper == null) {
                    return;
                } else {
                    helperToUse = this.mICarServiceHelper;
                }
            } else {
                this.mICarServiceHelper = helper;
            }
            if (updateDisplay) {
                passengerDisplays = getAllActivePassengerDisplaysLocked();
            }
            if (updateUser) {
                whitelists = createDisplayWhitelistsLocked();
            }
            if (updateDisplay) {
                updatePassengerDisplays(helperToUse, passengerDisplays);
            }
            if (updateUser) {
                updateUserAssignmentForDisplays(helperToUse, whitelists);
            }
            if (updateConfig) {
                Resources res = this.mContext.getResources();
                String[] components = res.getStringArray(R.array.config_sourcePreferredComponents);
                updateSourcePreferredComponents(helperToUse, components);
            }
        }
    }

    private int[] getAllActivePassengerDisplaysLocked() {
        IntArray displays = new IntArray();
        for (Map.Entry<Integer, OccupantConfig> entry : this.mActiveOccupantConfigs.entrySet()) {
            Integer zoneId = entry.getKey();
            if (zoneId.intValue() != this.mDriverZoneId) {
                OccupantConfig config = entry.getValue();
                for (int i = 0; i < config.displayInfos.size(); i++) {
                    displays.add(config.displayInfos.get(i).display.getDisplayId());
                }
            }
        }
        return displays.toArray();
    }

    private void updatePassengerDisplays(ICarServiceHelper helper, int[] passengerDisplayIds) {
        if (passengerDisplayIds == null) {
            return;
        }
        try {
            helper.setPassengerDisplays(passengerDisplayIds);
        } catch (RemoteException e) {
            Log.e("CAR.OCCUPANT", "ICarServiceHelper.setPassengerDisplays failed");
        }
    }

    private void updateSourcePreferredComponents(ICarServiceHelper helper, String[] components) {
        boolean enableSourcePreferred;
        ArrayList<ComponentName> componentNames = null;
        if (components == null || components.length == 0) {
            enableSourcePreferred = false;
            Log.i("CAR.OCCUPANT", "CarLaunchParamsModifier: disable source-preferred");
        } else {
            if (components.length == 1 && components[0].equals(ALL_COMPONENTS)) {
                enableSourcePreferred = true;
                Log.i("CAR.OCCUPANT", "CarLaunchParamsModifier: enable source-preferred for all Components");
            } else {
                componentNames = new ArrayList<>(components.length);
                for (String item : components) {
                    ComponentName name = ComponentName.unflattenFromString(item);
                    if (name == null) {
                        Log.e("CAR.OCCUPANT", "CarLaunchParamsModifier: Wrong ComponentName=" + item);
                        return;
                    }
                    componentNames.add(name);
                }
                enableSourcePreferred = true;
            }
        }
        try {
            helper.setSourcePreferredComponents(enableSourcePreferred, componentNames);
            this.mEnableSourcePreferred = enableSourcePreferred;
            this.mSourcePreferredComponents = componentNames;
        } catch (RemoteException e) {
            Log.e("CAR.OCCUPANT", "ICarServiceHelper.setSourcePreferredComponents failed");
        }
    }

    private ArrayMap<Integer, IntArray> createDisplayWhitelistsLocked() {
        ArrayMap<Integer, IntArray> whitelists = new ArrayMap<>();
        for (Map.Entry<Integer, OccupantConfig> entry : this.mActiveOccupantConfigs.entrySet()) {
            Integer zoneId = entry.getKey();
            if (zoneId.intValue() != this.mDriverZoneId) {
                OccupantConfig config = entry.getValue();
                if (!config.displayInfos.isEmpty()) {
                    IntArray displays = whitelists.get(Integer.valueOf(config.userId));
                    if (displays == null) {
                        displays = new IntArray();
                        whitelists.put(Integer.valueOf(config.userId), displays);
                    }
                    for (int i = 0; i < config.displayInfos.size(); i++) {
                        displays.add(config.displayInfos.get(i).display.getDisplayId());
                    }
                }
            }
        }
        return whitelists;
    }

    private void updateUserAssignmentForDisplays(ICarServiceHelper helper, ArrayMap<Integer, IntArray> whitelists) {
        if (whitelists == null || whitelists.isEmpty()) {
            return;
        }
        for (int i = 0; i < whitelists.size(); i++) {
            try {
                int userId = whitelists.keyAt(i).intValue();
                helper.setDisplayWhitelistForUser(userId, whitelists.valueAt(i).toArray());
            } catch (RemoteException e) {
                Log.e("CAR.OCCUPANT", "ICarServiceHelper.setDisplayWhitelistForUser failed");
                return;
            }
        }
    }

    private void throwFormatErrorInOccupantZones(String msg) {
        throw new RuntimeException("Format error in config_occupant_zones resource:" + msg);
    }

    int getDriverSeat() {
        int i;
        synchronized (this.mLock) {
            i = this.mDriverSeat;
        }
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x017f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseOccupantZoneConfigsLocked() {
        /*
            Method dump skipped, instruction units count: 676
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarOccupantZoneService.parseOccupantZoneConfigsLocked():void");
    }

    private void throwFormatErrorInDisplayMapping(String msg) {
        throw new RuntimeException("Format error in config_occupant_display_mapping resource:" + msg);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseDisplayConfigsLocked() {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarOccupantZoneService.parseDisplayConfigsLocked():void");
    }

    private Byte getPortAddress(Display display) {
        DisplayAddress.Physical physicalAddress;
        DisplayAddress.Physical address = display.getAddress();
        if ((address instanceof DisplayAddress.Physical) && (physicalAddress = address) != null) {
            return Byte.valueOf(physicalAddress.getPort());
        }
        return null;
    }

    private void addDisplayInfoToOccupantZoneLocked(int zoneId, DisplayInfo info) {
        OccupantConfig occupantConfig = this.mActiveOccupantConfigs.get(Integer.valueOf(zoneId));
        if (occupantConfig == null) {
            occupantConfig = new OccupantConfig();
            this.mActiveOccupantConfigs.put(Integer.valueOf(zoneId), occupantConfig);
        }
        occupantConfig.displayInfos.add(info);
    }

    private void handleActiveDisplaysLocked() {
        this.mActiveOccupantConfigs.clear();
        boolean hasDefaultDisplayConfig = false;
        for (Display display : this.mDisplayManager.getDisplays()) {
            Byte rawPortAddress = getPortAddress(display);
            if (rawPortAddress != null) {
                int portAddress = Byte.toUnsignedInt(rawPortAddress.byteValue());
                DisplayConfig displayConfig = this.mDisplayConfigs.get(Integer.valueOf(portAddress));
                if (displayConfig == null) {
                    Log.w("CAR.OCCUPANT", "Display id:" + display.getDisplayId() + " port:" + portAddress + " does not have configurations");
                } else {
                    if (display.getDisplayId() == 0) {
                        if (displayConfig.occupantZoneId != this.mDriverZoneId) {
                            throw new IllegalStateException("Default display should be only assigned to driver zone");
                        }
                        hasDefaultDisplayConfig = true;
                    }
                    addDisplayInfoToOccupantZoneLocked(displayConfig.occupantZoneId, new DisplayInfo(display, displayConfig.displayType));
                }
            }
        }
        if (!hasDefaultDisplayConfig) {
            Log.w("CAR.OCCUPANT", "Default display not assigned, will assign to driver zone");
            addDisplayInfoToOccupantZoneLocked(this.mDriverZoneId, new DisplayInfo(this.mDisplayManager.getDisplay(0), 1));
        }
    }

    int getCurrentUser() {
        return ActivityManager.getCurrentUser();
    }

    private void updateEnabledProfilesLocked(int userId) {
        this.mProfileUsers.clear();
        List<UserInfo> profileUsers = this.mUserManager.getEnabledProfiles(userId);
        for (UserInfo userInfo : profileUsers) {
            if (userInfo.id != userId) {
                this.mProfileUsers.add(Integer.valueOf(userInfo.id));
            }
        }
    }

    private void handleUserChangesLocked() {
        int driverUserId = getCurrentUser();
        if (this.mEnableProfileUserAssignmentForMultiDisplay) {
            updateEnabledProfilesLocked(driverUserId);
        }
        for (Map.Entry<Integer, OccupantConfig> entry : this.mActiveOccupantConfigs.entrySet()) {
            Integer zoneId = entry.getKey();
            OccupantConfig config = entry.getValue();
            if (this.mProfileUsers.contains(Integer.valueOf(config.userId))) {
                Log.i("CAR.OCCUPANT", "Profile user:" + config.userId + " already assigned for occupant zone:" + zoneId);
            } else {
                config.userId = driverUserId;
            }
        }
    }

    private void handleAudioZoneChangesLocked() {
        for (int index = 0; index < this.mAudioZoneIdToOccupantZoneIdMapping.size(); index++) {
            int audioZoneId = this.mAudioZoneIdToOccupantZoneIdMapping.keyAt(index);
            int occupantZoneId = this.mAudioZoneIdToOccupantZoneIdMapping.get(audioZoneId);
            OccupantConfig occupantConfig = this.mActiveOccupantConfigs.get(Integer.valueOf(occupantZoneId));
            if (occupantConfig != null) {
                occupantConfig.audioZoneId = audioZoneId;
            }
        }
    }

    private void sendConfigChangeEvent(int changeFlags) {
        boolean updateDisplay = false;
        boolean updateUser = false;
        if ((changeFlags & 1) != 0) {
            updateDisplay = true;
            updateUser = true;
        } else if ((changeFlags & 2) != 0) {
            updateUser = true;
        }
        doSyncWithCarServiceHelper(null, updateDisplay, updateUser, false);
        int n = this.mClientCallbacks.beginBroadcast();
        for (int i = 0; i < n; i++) {
            ICarOccupantZoneCallback callback = this.mClientCallbacks.getBroadcastItem(i);
            try {
                callback.onOccupantZoneConfigChanged(changeFlags);
            } catch (RemoteException e) {
            }
        }
        this.mClientCallbacks.finishBroadcast();
    }

    private void handleUserChange() {
        synchronized (this.mLock) {
            handleUserChangesLocked();
        }
        sendConfigChangeEvent(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePassengerStarted(int passengerId, int zoneId) {
        sendConfigChangeEvent(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePassengerStopped(int passengerId) {
        sendConfigChangeEvent(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDisplayChange() {
        synchronized (this.mLock) {
            handleActiveDisplaysLocked();
            handleAudioZoneChangesLocked();
            handleUserChangesLocked();
        }
        sendConfigChangeEvent(1);
    }

    private void enforcePermission(String permissionName) {
        if (this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }
}
