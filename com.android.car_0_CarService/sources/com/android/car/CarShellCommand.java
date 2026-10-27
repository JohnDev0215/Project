package com.android.car;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.car.Car;
import android.car.user.CarUserManager;
import android.car.user.UserIdentificationAssociationResponse;
import android.car.user.UserRemovalResult;
import android.car.userlib.HalCallback;
import android.car.userlib.UserHalHelper;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.automotive.vehicle.V2_0.CreateUserResponse;
import android.hardware.automotive.vehicle.V2_0.CreateUserStatus;
import android.hardware.automotive.vehicle.V2_0.InitialUserInfoResponse;
import android.hardware.automotive.vehicle.V2_0.InitialUserInfoResponseAction;
import android.hardware.automotive.vehicle.V2_0.RemoveUserRequest;
import android.hardware.automotive.vehicle.V2_0.SwitchUserMessageType;
import android.hardware.automotive.vehicle.V2_0.SwitchUserRequest;
import android.hardware.automotive.vehicle.V2_0.SwitchUserResponse;
import android.hardware.automotive.vehicle.V2_0.SwitchUserStatus;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationAssociation;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationAssociationSetValue;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationAssociationType;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationAssociationValue;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationResponse;
import android.hardware.automotive.vehicle.V2_0.UserInfo;
import android.hardware.automotive.vehicle.V2_0.UsersInfo;
import android.hardware.automotive.vehicle.V2_0.VehicleArea;
import android.os.Binder;
import android.os.Build;
import android.os.ShellCommand;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import com.android.car.am.FixedActivityService;
import com.android.car.audio.CarAudioService;
import com.android.car.enhancedaudio.CarAudioServiceWrapper;
import com.android.car.garagemode.GarageModeService;
import com.android.car.hal.UserHalService;
import com.android.car.hal.VehicleHal;
import com.android.car.pm.CarPackageManagerService;
import com.android.car.systeminterface.SystemInterface;
import com.android.car.trust.CarTrustedDeviceService;
import com.android.car.user.CarUserService;
import com.android.internal.infra.AndroidFuture;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
final class CarShellCommand extends ShellCommand {
    private static final String COMMAND_CREATE_USER = "create-user";
    private static final String COMMAND_DAY_NIGHT_MODE = "day-night-mode";
    private static final String COMMAND_DISABLE_FEATURE = "disable-feature";
    private static final String COMMAND_ENABLE_FEATURE = "enable-feature";
    private static final String COMMAND_ENABLE_TRUSTED_DEVICE = "enable-trusted-device";
    private static final String COMMAND_ENABLE_UXR = "enable-uxr";
    private static final String COMMAND_GARAGE_MODE = "garage-mode";
    private static final String COMMAND_GET_CARPROPERTYCONFIG = "get-carpropertyconfig";
    private static final String COMMAND_GET_DO_ACTIVITIES = "get-do-activities";
    private static final String COMMAND_GET_INITIAL_USER = "get-initial-user";
    private static final String COMMAND_GET_INITIAL_USER_INFO = "get-initial-user-info";
    private static final String COMMAND_GET_PROPERTY_VALUE = "get-property-value";
    private static final String COMMAND_GET_USER_AUTH_ASSOCIATION = "get-user-auth-association";
    private static final String COMMAND_HELP = "-h";
    private static final String COMMAND_INJECT_ERROR_EVENT = "inject-error-event";
    private static final String COMMAND_INJECT_KEY = "inject-key";
    private static final String COMMAND_INJECT_ROTARY = "inject-rotary";
    private static final String COMMAND_INJECT_VHAL_EVENT = "inject-vhal-event";
    private static final String COMMAND_POWER_OFF = "power-off";
    private static final String COMMAND_PROJECTION_AP_TETHERING = "projection-tethering";
    private static final String COMMAND_PROJECTION_UI_MODE = "projection-ui-mode";
    private static final String COMMAND_REMOVE_TRUSTED_DEVICES = "remove-trusted-devices";
    private static final String COMMAND_REMOVE_USER = "remove-user";
    private static final String COMMAND_RESET_USER_ID_IN_OCCUPANT_ZONE = "reset-user-in-occupant-zone";
    private static final String COMMAND_RESUME = "resume";
    private static final String COMMAND_SET_UID_TO_ZONE = "set-audio-zone-for-uid";
    private static final String COMMAND_SET_USER_AUTH_ASSOCIATION = "set-user-auth-association";
    private static final String COMMAND_SET_USER_ID_TO_OCCUPANT_ZONE = "set-occupant-zone-for-user";
    private static final String COMMAND_START_FIXED_ACTIVITY_MODE = "start-fixed-activity-mode";
    private static final String COMMAND_STOP_FIXED_ACTIVITY_MODE = "stop-fixed-activity-mode";
    private static final String COMMAND_SUSPEND = "suspend";
    private static final String COMMAND_SWITCH_USER = "switch-user";
    private static final int DEFAULT_HAL_TIMEOUT_MS = 1000;
    private static final String DEVICE_POWER_PERMISSION = "android.permission.DEVICE_POWER";
    private static final int INVALID_USER_AUTH_TYPE_OR_VALUE = -1;
    private static final String NO_INITIAL_USER = "N/A";
    private static final String PARAM_DAY_MODE = "day";
    private static final String PARAM_NIGHT_MODE = "night";
    private static final String PARAM_OFF_MODE = "off";
    private static final String PARAM_ON_MODE = "on";
    private static final String PARAM_QUERY_MODE = "query";
    private static final String PARAM_REBOOT = "reboot";
    private static final String PARAM_SENSOR_MODE = "sensor";
    private static final String PARAM_VEHICLE_PROPERTY_AREA_GLOBAL = "0";
    private static final String POWER_OFF_SHUTDOWN = "--shutdown";
    private static final String POWER_OFF_SKIP_GARAGEMODE = "--skip-garagemode";
    private static final int RESULT_ERROR = -1;
    private static final int RESULT_OK = 0;
    private static final String TAG = CarShellCommand.class.getSimpleName();
    private static final ArrayMap<String, String> USER_BUILD_COMMAND_TO_PERMISSION_MAP;
    private static final SparseArray<String> VALID_USER_AUTH_SET_VALUES;
    private static final String VALID_USER_AUTH_SET_VALUES_HELP;
    private static final SparseArray<String> VALID_USER_AUTH_TYPES;
    private static final String VALID_USER_AUTH_TYPES_HELP;
    private static final boolean VERBOSE = false;
    private final CarAudioService mCarAudioService;
    private final CarAudioServiceWrapper mCarAudioServiceWrapper;
    private final CarInputService mCarInputService;
    private final CarOccupantZoneService mCarOccupantZoneService;
    private final CarPackageManagerService mCarPackageManagerService;
    private final CarPowerManagementService mCarPowerManagementService;
    private final CarProjectionService mCarProjectionService;
    private final CarTrustedDeviceService mCarTrustedDeviceService;
    private final CarUserService mCarUserService;
    private final Context mContext;
    private final CarFeatureController mFeatureController;
    private final FixedActivityService mFixedActivityService;
    private final GarageModeService mGarageModeService;
    private final VehicleHal mHal;
    private final SystemInterface mSystemInterface;

    static {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        USER_BUILD_COMMAND_TO_PERMISSION_MAP = arrayMap;
        arrayMap.put(COMMAND_GARAGE_MODE, DEVICE_POWER_PERMISSION);
        USER_BUILD_COMMAND_TO_PERMISSION_MAP.put(COMMAND_RESUME, DEVICE_POWER_PERMISSION);
        USER_BUILD_COMMAND_TO_PERMISSION_MAP.put(COMMAND_SUSPEND, DEVICE_POWER_PERMISSION);
        USER_BUILD_COMMAND_TO_PERMISSION_MAP.put(COMMAND_GET_INITIAL_USER, "android.permission.INTERACT_ACROSS_USERS_FULL");
        USER_BUILD_COMMAND_TO_PERMISSION_MAP.put(COMMAND_GET_INITIAL_USER_INFO, "android.permission.MANAGE_USERS");
        USER_BUILD_COMMAND_TO_PERMISSION_MAP.put(COMMAND_SWITCH_USER, "android.permission.MANAGE_USERS");
        USER_BUILD_COMMAND_TO_PERMISSION_MAP.put(COMMAND_REMOVE_USER, "android.permission.MANAGE_USERS");
        USER_BUILD_COMMAND_TO_PERMISSION_MAP.put(COMMAND_CREATE_USER, "android.permission.MANAGE_USERS");
        USER_BUILD_COMMAND_TO_PERMISSION_MAP.put(COMMAND_GET_USER_AUTH_ASSOCIATION, "android.permission.MANAGE_USERS");
        USER_BUILD_COMMAND_TO_PERMISSION_MAP.put(COMMAND_SET_USER_AUTH_ASSOCIATION, "android.permission.MANAGE_USERS");
        SparseArray<String> sparseArray = new SparseArray<>(5);
        VALID_USER_AUTH_TYPES = sparseArray;
        sparseArray.put(1, UserIdentificationAssociationType.toString(1));
        VALID_USER_AUTH_TYPES.put(101, UserIdentificationAssociationType.toString(101));
        VALID_USER_AUTH_TYPES.put(102, UserIdentificationAssociationType.toString(102));
        VALID_USER_AUTH_TYPES.put(103, UserIdentificationAssociationType.toString(103));
        VALID_USER_AUTH_TYPES.put(104, UserIdentificationAssociationType.toString(104));
        VALID_USER_AUTH_TYPES_HELP = getHelpString("types", VALID_USER_AUTH_TYPES);
        SparseArray<String> sparseArray2 = new SparseArray<>(3);
        VALID_USER_AUTH_SET_VALUES = sparseArray2;
        sparseArray2.put(1, UserIdentificationAssociationSetValue.toString(1));
        VALID_USER_AUTH_SET_VALUES.put(2, UserIdentificationAssociationSetValue.toString(2));
        VALID_USER_AUTH_SET_VALUES.put(3, UserIdentificationAssociationSetValue.toString(3));
        VALID_USER_AUTH_SET_VALUES_HELP = getHelpString("values", VALID_USER_AUTH_SET_VALUES);
    }

    private static String getHelpString(String name, SparseArray<String> values) {
        StringBuilder sb = new StringBuilder("Valid ");
        sb.append(name);
        StringBuilder help = sb.append(" are: ");
        int size = values.size();
        for (int i = 0; i < size; i++) {
            help.append(values.valueAt(i));
            if (i != size - 1) {
                help.append(", ");
            }
        }
        help.append('.');
        return help.toString();
    }

    CarShellCommand(Context context, VehicleHal hal, CarAudioService carAudioService, CarAudioServiceWrapper carAudioServiceWrapper, CarPackageManagerService carPackageManagerService, CarProjectionService carProjectionService, CarPowerManagementService carPowerManagementService, CarTrustedDeviceService carTrustedDeviceService, FixedActivityService fixedActivityService, CarFeatureController featureController, CarInputService carInputService, SystemInterface systemInterface, GarageModeService garageModeService, CarUserService carUserService, CarOccupantZoneService carOccupantZoneService) {
        this.mContext = context;
        this.mHal = hal;
        this.mCarAudioService = carAudioService;
        this.mCarAudioServiceWrapper = carAudioServiceWrapper;
        this.mCarPackageManagerService = carPackageManagerService;
        this.mCarProjectionService = carProjectionService;
        this.mCarPowerManagementService = carPowerManagementService;
        this.mCarTrustedDeviceService = carTrustedDeviceService;
        this.mFixedActivityService = fixedActivityService;
        this.mFeatureController = featureController;
        this.mCarInputService = carInputService;
        this.mSystemInterface = systemInterface;
        this.mGarageModeService = garageModeService;
        this.mCarUserService = carUserService;
        this.mCarOccupantZoneService = carOccupantZoneService;
    }

    public int onCommand(String cmd) {
        String arg;
        if (cmd == null) {
            onHelp();
            return -1;
        }
        ArrayList<String> argsList = new ArrayList<>();
        argsList.add(cmd);
        do {
            arg = getNextArg();
            if (arg != null) {
                argsList.add(arg);
            }
        } while (arg != null);
        String[] args = new String[argsList.size()];
        argsList.toArray(args);
        return exec(args, getOutPrintWriter());
    }

    public void onHelp() {
        showHelp(getOutPrintWriter());
    }

    private static void showHelp(PrintWriter pw) {
        pw.println("Car service commands:");
        pw.println("\t-h");
        pw.println("\t  Print this help text.");
        pw.println("\tday-night-mode [day|night|sensor]");
        pw.println("\t  Force into day/night mode or restore to auto.");
        pw.println("\tinject-vhal-event property [zone] data(can be comma separated list) [-t delay_time_seconds]");
        pw.println("\t  Inject a vehicle property for testing.");
        pw.println("\t  delay_time_seconds: the event timestamp is increased by certain second.");
        pw.println("\t  If not specified, it will be 0.");
        pw.println("\tinject-error-event property zone errorCode");
        pw.println("\t  Inject an error event from VHAL for testing.");
        pw.println("\tenable-uxr true|false");
        pw.println("\t  Enable/Disable UX restrictions and App blocking.");
        pw.println("\tgarage-mode [on|off|query|reboot]");
        pw.println("\t  Force into or out of garage mode, or check status.");
        pw.println("\t  With 'reboot', enter garage mode, then reboot when it completes.");
        pw.println("\tget-do-activities pkgname");
        pw.println("\t  Get Distraction Optimized activities in given package.");
        pw.println("\tget-carpropertyconfig [propertyId]");
        pw.println("\t  Get a CarPropertyConfig by Id in Hex or list all CarPropertyConfigs");
        pw.println("\tget-property-value [propertyId] [areaId]");
        pw.println("\t  Get a vehicle property value by property id in Hex and areaId");
        pw.println("\t  or list all property values for all areaId");
        pw.println("\tsuspend");
        pw.println("\t  Suspend the system to Deep Sleep.");
        pw.println("\tresume");
        pw.println("\t  Wake the system up after a 'suspend.'");
        pw.println("\tenable-trusted-device true|false");
        pw.println("\t  Enable/Disable Trusted device feature.");
        pw.println("\tremove-trusted-devices");
        pw.println("\t  Remove all trusted devices for the current foreground user.");
        pw.println("\tprojection-tethering [true|false]");
        pw.println("\t  Whether tethering should be used when creating access point for wireless projection");
        pw.println("\t--metrics");
        pw.println("\t  When used with dumpsys, only metrics will be in the dumpsys output.");
        pw.printf("\t%s [zoneid] [uid]\n", COMMAND_SET_UID_TO_ZONE);
        pw.println("\t  Maps the audio zoneid to uid.");
        pw.println("\tstart-fixed-activity displayId packageName activityName");
        pw.println("\t  Start an Activity the specified display as fixed mode");
        pw.println("\tstop-fixed-mode displayId");
        pw.println("\t  Stop fixed Activity mode for the given display. The Activity will not be restarted upon crash.");
        pw.println("\tenable-feature featureName");
        pw.println("\t  Enable the requested feature. Change will happen after reboot.");
        pw.println("\t  This requires root/su.");
        pw.println("\tdisable-feature featureName");
        pw.println("\t  Disable the requested feature. Change will happen after reboot");
        pw.println("\t  This requires root/su.");
        pw.println("\tinject-key [-d display] [-t down_delay_ms | -a down|up] key_code");
        pw.println("\t  inject key down and/or up event to car service");
        pw.println("\t  display: 0 for main, 1 for cluster. If not specified, it will be 0.");
        pw.println("\t  down_delay_ms: delay from down to up key event. If not specified,");
        pw.println("\t                 it will be 0");
        pw.println("\t  key_code: int key code defined in android KeyEvent");
        pw.println("\t  If -a isn't specified, both down and up will be injected.");
        pw.println("\tinject-rotary [-d display] [-i input_type] [-c clockwise]");
        pw.println("\t              [-dt delta_times_ms]");
        pw.println("\t  inject rotary input event to car service.");
        pw.println("\t  display: 0 for main, 1 for cluster. If not specified, it will be 0.");
        pw.println("\t  input_type: 10 for navigation controller input, 11 for volume");
        pw.println("\t              controller input. If not specified, it will be 10.");
        pw.println("\t  clockwise: true if the event is clockwise, false if the event is");
        pw.println("\t             counter-clockwise. If not specified, it will be false.");
        pw.println("\t  delta_times_ms: a list of delta time (current time minus event time)");
        pw.println("\t                  in descending order. If not specified, it will be 0.");
        pw.printf("\t%s <REQ_TYPE> [--hal-only] [--timeout TIMEOUT_MS]\n", COMMAND_GET_INITIAL_USER_INFO);
        pw.println("\t  Calls the Vehicle HAL to get the initial boot info, passing the given");
        pw.println("\t  REQ_TYPE (which could be either FIRST_BOOT, FIRST_BOOT_AFTER_OTA, ");
        pw.println("\t  COLD_BOOT, RESUME, or any numeric value that would be passed 'as-is')");
        pw.println("\t  and an optional TIMEOUT_MS to wait for the HAL response (if not set,");
        pw.println("\t  it will use a  default value).");
        pw.println("\t  The --hal-only option only calls HAL, without using CarUserService.");
        pw.printf("\t%s <USER_ID> [--hal-only] [--timeout TIMEOUT_MS]\n", COMMAND_SWITCH_USER);
        pw.println("\t  Switches to user USER_ID using the HAL integration.");
        pw.println("\t  The --hal-only option only calls HAL, without switching the user,");
        pw.println("\t  while the --timeout defines how long to wait for the HAL response.");
        pw.printf("\t%s <USER_ID> [--hal-only]\n", COMMAND_REMOVE_USER);
        pw.println("\t  Removes user with USER_ID using the HAL integration.");
        pw.println("\t  The --hal-only option only calls HAL, without removing the user,");
        pw.printf("\t%s [--hal-only] [--timeout TIMEOUT_MS] [--type TYPE] [--flags FLAGS] [NAME]\n", COMMAND_CREATE_USER);
        pw.println("\t  Creates a new user using the HAL integration.");
        pw.println("\t  The --hal-only uses UserManager to create the user,");
        pw.println("\t  while the --timeout defines how long to wait for the HAL response.");
        pw.printf("\t%s\n", COMMAND_GET_INITIAL_USER);
        pw.printf("\t  Gets the id of the initial user (or %s when it's not available)\n", NO_INITIAL_USER);
        pw.printf("\t%s [occupantZoneId] [userId]\n", COMMAND_SET_USER_ID_TO_OCCUPANT_ZONE);
        pw.println("\t  Maps the occupant zone id to user id.");
        pw.printf("\t%s [occupantZoneId]\n", COMMAND_RESET_USER_ID_IN_OCCUPANT_ZONE);
        pw.println("\t  Unmaps the user assigned to occupant zone id.");
        pw.printf("\t%s [--hal-only] [--user USER_ID] TYPE1 [..TYPE_N]\n", COMMAND_GET_USER_AUTH_ASSOCIATION);
        pw.println("\t  Gets the N user authentication values for the N types for the given user");
        pw.println("\t  (or current user when not specified).");
        pw.println("\t  By defautt it calls CarUserManager, but using --hal-only will call just UserHalService.");
        pw.printf("\t%s [--hal-only] [--user USER_ID] TYPE1 VALUE1 [..TYPE_N VALUE_N]\n", COMMAND_SET_USER_AUTH_ASSOCIATION);
        pw.println("\t  Sets the N user authentication types with the N values for the given user");
        pw.println("\t  (or current user when not specified).");
        pw.println("\t  By defautt it calls CarUserManager, but using --hal-only will call just UserHalService.");
        pw.printf("\t  %s\n", VALID_USER_AUTH_TYPES_HELP);
        pw.printf("\t  %s\n", VALID_USER_AUTH_SET_VALUES_HELP);
        pw.printf("\t%s [%s] [%s]\n", COMMAND_POWER_OFF, POWER_OFF_SKIP_GARAGEMODE, POWER_OFF_SHUTDOWN);
        pw.println("\t  Powers off the car.");
    }

    private static int showInvalidArguments(PrintWriter pw) {
        pw.println("Incorrect number of arguments.");
        showHelp(pw);
        return -1;
    }

    private void runSetZoneIdForUid(String zoneString, String uidString) {
        int uid = Integer.parseInt(uidString);
        int zoneId = Integer.parseInt(zoneString);
        CarAudioServiceWrapper carAudioServiceWrapper = this.mCarAudioServiceWrapper;
        if (carAudioServiceWrapper != null) {
            carAudioServiceWrapper.setZoneIdForUid(zoneId, uid);
        } else {
            this.mCarAudioService.setZoneIdForUid(zoneId, uid);
        }
    }

    private void runSetOccupantZoneIdForUserId(String occupantZoneIdString, String userIdString) {
        int userId = Integer.parseInt(userIdString);
        int occupantZoneId = Integer.parseInt(occupantZoneIdString);
        if (!this.mCarOccupantZoneService.assignProfileUserToOccupantZone(occupantZoneId, userId)) {
            throw new IllegalStateException("Failed to set userId " + userId + " to occupantZoneId " + occupantZoneIdString);
        }
    }

    private void runResetOccupantZoneId(String occupantZoneIdString) {
        int occupantZoneId = Integer.parseInt(occupantZoneIdString);
        if (!this.mCarOccupantZoneService.assignProfileUserToOccupantZone(occupantZoneId, -10000)) {
            throw new IllegalStateException("Failed to reset occupantZoneId " + occupantZoneIdString);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    int exec(String[] args, PrintWriter writer) {
        byte b;
        String zone;
        String data;
        String cmd = args[0];
        String requiredPermission = USER_BUILD_COMMAND_TO_PERMISSION_MAP.get(cmd);
        if (Build.IS_USER && requiredPermission == null) {
            throw new SecurityException("The command " + cmd + "requires non-user build");
        }
        if (requiredPermission != null && !ICarImpl.hasPermission(this.mContext, requiredPermission)) {
            throw new SecurityException("The command " + cmd + "requires permission:" + requiredPermission);
        }
        switch (cmd.hashCode()) {
            case -1947496588:
                b = cmd.equals(COMMAND_GET_CARPROPERTYCONFIG) ? (byte) 6 : (byte) -1;
                break;
            case -1852006340:
                b = cmd.equals(COMMAND_SUSPEND) ? (byte) 11 : (byte) -1;
                break;
            case -1803231086:
                b = cmd.equals(COMMAND_PROJECTION_AP_TETHERING) ? (byte) 9 : (byte) -1;
                break;
            case -1661913154:
                b = cmd.equals(COMMAND_PROJECTION_UI_MODE) ? (byte) 8 : (byte) -1;
                break;
            case -1493699993:
                b = cmd.equals(COMMAND_GET_USER_AUTH_ASSOCIATION) ? (byte) 28 : (byte) -1;
                break;
            case -1483675411:
                b = cmd.equals(COMMAND_INJECT_ROTARY) ? (byte) 22 : (byte) -1;
                break;
            case -934426579:
                b = cmd.equals(COMMAND_RESUME) ? (byte) 10 : (byte) -1;
                break;
            case -766779255:
                b = cmd.equals(COMMAND_GARAGE_MODE) ? (byte) 1 : (byte) -1;
                break;
            case -650408333:
                b = cmd.equals(COMMAND_SET_USER_AUTH_ASSOCIATION) ? (byte) 29 : (byte) -1;
                break;
            case -539710980:
                b = cmd.equals(COMMAND_CREATE_USER) ? (byte) 26 : (byte) -1;
                break;
            case -413651266:
                b = cmd.equals(COMMAND_REMOVE_TRUSTED_DEVICES) ? (byte) 13 : (byte) -1;
                break;
            case -339687564:
                b = cmd.equals(COMMAND_REMOVE_USER) ? (byte) 25 : (byte) -1;
                break;
            case -258824931:
                b = cmd.equals(COMMAND_START_FIXED_ACTIVITY_MODE) ? (byte) 17 : (byte) -1;
                break;
            case -100820112:
                b = cmd.equals(COMMAND_GET_INITIAL_USER_INFO) ? (byte) 23 : (byte) -1;
                break;
            case 1499:
                b = cmd.equals(COMMAND_HELP) ? (byte) 0 : (byte) -1;
                break;
            case 97605688:
                b = cmd.equals(COMMAND_GET_DO_ACTIVITIES) ? (byte) 5 : (byte) -1;
                break;
            case 247238886:
                b = cmd.equals(COMMAND_INJECT_VHAL_EVENT) ? (byte) 2 : (byte) -1;
                break;
            case 417091756:
                b = cmd.equals(COMMAND_ENABLE_FEATURE) ? (byte) 19 : (byte) -1;
                break;
            case 792422104:
                b = cmd.equals(COMMAND_RESET_USER_ID_IN_OCCUPANT_ZONE) ? (byte) 16 : (byte) -1;
                break;
            case 844013735:
                b = cmd.equals(COMMAND_POWER_OFF) ? (byte) 30 : (byte) -1;
                break;
            case 905425270:
                b = cmd.equals(COMMAND_ENABLE_TRUSTED_DEVICE) ? (byte) 12 : (byte) -1;
                break;
            case 983226064:
                b = cmd.equals(COMMAND_GET_PROPERTY_VALUE) ? (byte) 7 : (byte) -1;
                break;
            case 1318574269:
                b = cmd.equals(COMMAND_STOP_FIXED_ACTIVITY_MODE) ? (byte) 18 : (byte) -1;
                break;
            case 1604342219:
                b = cmd.equals(COMMAND_GET_INITIAL_USER) ? (byte) 27 : (byte) -1;
                break;
            case 1648991321:
                b = cmd.equals(COMMAND_INJECT_ERROR_EVENT) ? (byte) 3 : (byte) -1;
                break;
            case 1654135057:
                b = cmd.equals(COMMAND_DISABLE_FEATURE) ? (byte) 20 : (byte) -1;
                break;
            case 1769710859:
                b = cmd.equals(COMMAND_SET_USER_ID_TO_OCCUPANT_ZONE) ? (byte) 15 : (byte) -1;
                break;
            case 1892083429:
                b = cmd.equals(COMMAND_ENABLE_UXR) ? (byte) 4 : (byte) -1;
                break;
            case 2030144547:
                b = cmd.equals(COMMAND_INJECT_KEY) ? (byte) 21 : (byte) -1;
                break;
            case 2051066893:
                b = cmd.equals(COMMAND_SET_UID_TO_ZONE) ? (byte) 14 : (byte) -1;
                break;
            case 2083239620:
                b = cmd.equals(COMMAND_SWITCH_USER) ? (byte) 24 : (byte) -1;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
                showHelp(writer);
                return 0;
            case 1:
                String value = args.length >= 2 ? args[1] : "";
                forceGarageMode(value, writer);
                return 0;
            case 2:
                int argNum = args.length;
                if (argNum < 3 || argNum > 6) {
                    return showInvalidArguments(writer);
                }
                String delayTime = args[argNum + (-2)].equals("-t") ? args[argNum - 1] : PARAM_VEHICLE_PROPERTY_AREA_GLOBAL;
                if (argNum == 4 || argNum == 6) {
                    String zone2 = args[2];
                    zone = zone2;
                    data = args[3];
                } else {
                    String str = args[2];
                    zone = PARAM_VEHICLE_PROPERTY_AREA_GLOBAL;
                    data = str;
                }
                injectVhalEvent(args[1], zone, data, false, delayTime, writer);
                return 0;
            case 3:
                if (args.length != 4) {
                    return showInvalidArguments(writer);
                }
                String errorAreaId = args[2];
                String errorCode = args[3];
                injectVhalEvent(args[1], errorAreaId, errorCode, true, PARAM_VEHICLE_PROPERTY_AREA_GLOBAL, writer);
                return 0;
            case 4:
                if (args.length != 2) {
                    return showInvalidArguments(writer);
                }
                boolean enableBlocking = Boolean.valueOf(args[1]).booleanValue();
                CarPackageManagerService carPackageManagerService = this.mCarPackageManagerService;
                if (carPackageManagerService != null) {
                    carPackageManagerService.setEnableActivityBlocking(enableBlocking);
                }
                return 0;
            case 5:
                if (args.length != 2) {
                    return showInvalidArguments(writer);
                }
                String pkgName = args[1].toLowerCase();
                CarPackageManagerService carPackageManagerService2 = this.mCarPackageManagerService;
                if (carPackageManagerService2 != null) {
                    String[] doActivities = carPackageManagerService2.getDistractionOptimizedActivities(pkgName);
                    if (doActivities != null) {
                        writer.println("DO Activities for " + pkgName);
                        int length = doActivities.length;
                        for (int i = 0; i < length; i++) {
                            String a = doActivities[i];
                            writer.println(a);
                        }
                    } else {
                        writer.println("No DO Activities for " + pkgName);
                    }
                }
                return 0;
            case 6:
                String propertyId = args.length >= 2 ? args[1] : "";
                this.mHal.dumpPropertyConfigs(writer, propertyId);
                return 0;
            case 7:
                String propId = args.length < 2 ? "" : args[1];
                String areaId = args.length >= 3 ? args[2] : "";
                this.mHal.dumpPropertyValueByCommend(writer, propId, areaId);
                return 0;
            case 8:
                if (args.length != 2) {
                    return showInvalidArguments(writer);
                }
                this.mCarProjectionService.setUiMode(Integer.valueOf(args[1]));
                return 0;
            case 9:
                if (args.length != 2) {
                    return showInvalidArguments(writer);
                }
                this.mCarProjectionService.setAccessPointTethering(Boolean.valueOf(args[1]).booleanValue());
                return 0;
            case 10:
                this.mCarPowerManagementService.forceSimulatedResume();
                writer.println("Resume: Simulating resuming from Deep Sleep");
                return 0;
            case 11:
                this.mCarPowerManagementService.forceSuspendAndMaybeReboot(false);
                writer.println("Suspend: Simulating powering down to Deep Sleep");
                return 0;
            case 12:
                if (args.length != 2) {
                    return showInvalidArguments(writer);
                }
                this.mCarTrustedDeviceService.getCarTrustAgentEnrollmentService().setTrustedDeviceEnrollmentEnabled(Boolean.valueOf(args[1]).booleanValue());
                this.mCarTrustedDeviceService.getCarTrustAgentUnlockService().setTrustedDeviceUnlockEnabled(Boolean.valueOf(args[1]).booleanValue());
                return 0;
            case 13:
                this.mCarTrustedDeviceService.getCarTrustAgentEnrollmentService().removeAllTrustedDevices(ActivityManager.getCurrentUser());
                return 0;
            case 14:
                if (args.length == 3) {
                    runSetZoneIdForUid(args[1], args[2]);
                    return 0;
                }
                return showInvalidArguments(writer);
            case 15:
                if (args.length == 3) {
                    runSetOccupantZoneIdForUserId(args[1], args[2]);
                    return 0;
                }
                return showInvalidArguments(writer);
            case 16:
                if (args.length == 2) {
                    runResetOccupantZoneId(args[1]);
                    return 0;
                }
                return showInvalidArguments(writer);
            case 17:
                startFixedActivity(args, writer);
                return 0;
            case 18:
                stopFixedMode(args, writer);
                return 0;
            case 19:
                if (args.length != 2) {
                    return showInvalidArguments(writer);
                }
                enableDisableFeature(args, writer, true);
                return 0;
            case 20:
                if (args.length != 2) {
                    return showInvalidArguments(writer);
                }
                enableDisableFeature(args, writer, false);
                return 0;
            case 21:
                if (args.length < 2) {
                    return showInvalidArguments(writer);
                }
                injectKey(args, writer);
                return 0;
            case 22:
                if (args.length < 1) {
                    return showInvalidArguments(writer);
                }
                injectRotary(args, writer);
                return 0;
            case 23:
                getInitialUserInfo(args, writer);
                return 0;
            case 24:
                switchUser(args, writer);
                return 0;
            case 25:
                removeUser(args, writer);
                return 0;
            case 26:
                createUser(args, writer);
                return 0;
            case 27:
                getInitialUser(writer);
                return 0;
            case 28:
                getUserAuthAssociation(args, writer);
                return 0;
            case 29:
                setUserAuthAssociation(args, writer);
                return 0;
            case 30:
                powerOff(args, writer);
                return 0;
            default:
                writer.println("Unknown command: \"" + cmd + "\"");
                showHelp(writer);
                return -1;
        }
    }

    private void startFixedActivity(String[] args, PrintWriter writer) {
        if (args.length != 4) {
            writer.println("Incorrect number of arguments");
            showHelp(writer);
            return;
        }
        try {
            int displayId = Integer.parseInt(args[1]);
            String packageName = args[2];
            String activityName = args[3];
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, activityName));
            intent.addFlags(268468224);
            ActivityOptions options = ActivityOptions.makeBasic();
            options.setLaunchDisplayId(displayId);
            if (!this.mFixedActivityService.startFixedActivityModeForDisplayAndUser(intent, options, displayId, ActivityManager.getCurrentUser())) {
                writer.println("Failed to start");
            } else {
                writer.println("Succeeded");
            }
        } catch (NumberFormatException e) {
            writer.println("Wrong display id:" + args[1]);
        }
    }

    private void stopFixedMode(String[] args, PrintWriter writer) {
        if (args.length != 2) {
            writer.println("Incorrect number of arguments");
            showHelp(writer);
            return;
        }
        try {
            int displayId = Integer.parseInt(args[1]);
            this.mFixedActivityService.stopFixedActivityMode(displayId);
        } catch (NumberFormatException e) {
            writer.println("Wrong display id:" + args[1]);
        }
    }

    private void enableDisableFeature(String[] args, PrintWriter writer, boolean enable) {
        int r;
        if (Binder.getCallingUid() != 0) {
            writer.println("Only allowed to root/su");
            return;
        }
        String featureName = args[1];
        long id = Binder.clearCallingIdentity();
        if (enable) {
            r = this.mFeatureController.enableFeature(featureName);
        } else {
            r = this.mFeatureController.disableFeature(featureName);
        }
        if (r != 0) {
            if (r != 1) {
                if (r == 2) {
                    writer.println("Cannot change mandatory feature:" + featureName);
                } else if (r == 3) {
                    writer.println("Non-existing feature:" + featureName);
                } else {
                    writer.println("Unknown error:" + r);
                }
            } else if (enable) {
                writer.println("Already enabled:" + featureName);
            } else {
                writer.println("Already disabled:" + featureName);
            }
        } else if (enable) {
            writer.println("Enabled feature:" + featureName);
        } else {
            writer.println("Disabled feature:" + featureName);
        }
        Binder.restoreCallingIdentity(id);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void injectKey(java.lang.String[] r13, java.io.PrintWriter r14) {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarShellCommand.injectKey(java.lang.String[], java.io.PrintWriter):void");
    }

    private void injectKeyEvent(int action, int keyCode, int display) {
        this.mCarInputService.onKeyEvent(new KeyEvent(action, keyCode), display);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void injectRotary(java.lang.String[] r13, java.io.PrintWriter r14) {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarShellCommand.injectRotary(java.lang.String[], java.io.PrintWriter):void");
    }

    private void getInitialUserInfo(String[] args, final PrintWriter writer) {
        if (args.length < 2) {
            writer.println("Insufficient number of args");
            return;
        }
        String typeArg = args[1];
        int requestType = UserHalHelper.parseInitialUserInfoRequestType(typeArg);
        boolean halOnly = false;
        int timeout = 1000;
        int i = 2;
        while (i < args.length) {
            String arg = args[i];
            byte b = -1;
            int iHashCode = arg.hashCode();
            if (iHashCode != -90130362) {
                if (iHashCode == 72070081 && arg.equals("--timeout")) {
                    b = 0;
                }
            } else if (arg.equals("--hal-only")) {
                b = 1;
            }
            if (b != 0) {
                if (b == 1) {
                    halOnly = true;
                } else {
                    writer.println("Invalid option at index " + i + ": " + arg);
                    return;
                }
            } else {
                i++;
                timeout = Integer.parseInt(args[i]);
            }
            i++;
        }
        Log.d(TAG, "handleGetInitialUserInfo(): type=" + requestType + " (" + typeArg + "), timeout=" + timeout);
        final CountDownLatch latch = new CountDownLatch(1);
        HalCallback<InitialUserInfoResponse> callback = new HalCallback() { // from class: com.android.car.-$$Lambda$CarShellCommand$SjU9Y2u9rcPl567hfAGxMV4qNx8
            @Override // android.car.userlib.HalCallback
            public final void onResponse(int i2, Object obj) {
                CarShellCommand.lambda$getInitialUserInfo$0(writer, latch, i2, (InitialUserInfoResponse) obj);
            }
        };
        if (halOnly) {
            UsersInfo usersInfo = generateUsersInfo();
            this.mHal.getUserHal().getInitialUserInfo(requestType, timeout, usersInfo, callback);
        } else {
            this.mCarUserService.getInitialUserInfo(requestType, callback);
        }
        waitForHal(writer, latch, timeout);
    }

    static /* synthetic */ void lambda$getInitialUserInfo$0(PrintWriter writer, CountDownLatch latch, int status, InitialUserInfoResponse resp) {
        try {
            Log.d(TAG, "GetUserInfoResponse: status=" + status + ", resp=" + resp);
            writer.printf("Call status: %s\n", UserHalHelper.halCallbackStatusToString(status));
            if (status != 1) {
                return;
            }
            writer.printf("Request id: %d\n", Integer.valueOf(resp.requestId));
            writer.printf("Action: %s\n", InitialUserInfoResponseAction.toString(resp.action));
            if (!TextUtils.isEmpty(resp.userNameToCreate)) {
                writer.printf("User name: %s\n", resp.userNameToCreate);
            }
            if (resp.userToSwitchOrCreate.userId != -10000) {
                writer.printf("User id: %d\n", Integer.valueOf(resp.userToSwitchOrCreate.userId));
            }
            if (resp.userToSwitchOrCreate.flags != 0) {
                writer.printf("User flags: %s\n", UserHalHelper.userFlagsToString(resp.userToSwitchOrCreate.flags));
            }
            if (!TextUtils.isEmpty(resp.userLocales)) {
                writer.printf("User locales: %s\n", resp.userLocales);
            }
        } finally {
            latch.countDown();
        }
    }

    private UsersInfo generateUsersInfo() {
        return UserHalHelper.newUsersInfo(UserManager.get(this.mContext));
    }

    private int getUserHalFlags(int userId) {
        return UserHalHelper.getFlags(UserManager.get(this.mContext), userId);
    }

    private static void waitForHal(PrintWriter writer, CountDownLatch latch, int timeoutMs) {
        try {
            if (!latch.await(timeoutMs, TimeUnit.MILLISECONDS)) {
                writer.printf("HAL didn't respond in %dms\n", Integer.valueOf(timeoutMs));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            writer.println("Interrupted waiting for HAL");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void switchUser(java.lang.String[] r11, final java.io.PrintWriter r12) {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarShellCommand.switchUser(java.lang.String[], java.io.PrintWriter):void");
    }

    static /* synthetic */ void lambda$switchUser$1(PrintWriter writer, UserHalService userHal, SwitchUserRequest request, CountDownLatch latch, int status, SwitchUserResponse resp) {
        try {
            Log.d(TAG, "SwitchUserResponse: status=" + status + ", resp=" + resp);
            writer.printf("Call Status: %s\n", UserHalHelper.halCallbackStatusToString(status));
            if (status != 1) {
                return;
            }
            writer.printf("Request id: %d\n", Integer.valueOf(resp.requestId));
            writer.printf("Message type: %s\n", SwitchUserMessageType.toString(resp.messageType));
            writer.printf("Switch Status: %s\n", SwitchUserStatus.toString(resp.status));
            String errorMessage = resp.errorMessage;
            if (!TextUtils.isEmpty(errorMessage)) {
                writer.printf("Error message: %s", errorMessage);
            }
            if (status == 1 && resp.status == 1) {
                userHal.postSwitchResponse(request);
            }
        } finally {
            latch.countDown();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x021e  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void createUser(java.lang.String[] r21, final java.io.PrintWriter r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 594
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarShellCommand.createUser(java.lang.String[], java.io.PrintWriter):void");
    }

    static /* synthetic */ void lambda$createUser$2(PrintWriter writer, AtomicBoolean halOk, CountDownLatch latch, int status, CreateUserResponse resp) {
        Log.d(TAG, "CreateUserResponse: status=" + status + ", resp=" + resp);
        writer.printf("Call Status: %s\n", UserHalHelper.halCallbackStatusToString(status));
        if (status == 1) {
            halOk.set(resp.status == 1);
            writer.printf("Request id: %d\n", Integer.valueOf(resp.requestId));
            writer.printf("Create Status: %s\n", CreateUserStatus.toString(resp.status));
            String errorMessage = resp.errorMessage;
            if (!TextUtils.isEmpty(errorMessage)) {
                writer.printf("Error message: %s", errorMessage);
            }
        }
        latch.countDown();
    }

    private void removeUser(String[] args, PrintWriter writer) {
        if (args.length < 2) {
            writer.println("Insufficient number of args");
            return;
        }
        int userId = Integer.parseInt(args[1]);
        boolean halOnly = false;
        int i = 2;
        while (true) {
            if (i < args.length) {
                String arg = args[i];
                if (((arg.hashCode() == -90130362 && arg.equals("--hal-only")) ? (byte) 0 : (byte) -1) == 0) {
                    halOnly = true;
                    i++;
                } else {
                    writer.println("Invalid option at index " + i + ": " + arg);
                    return;
                }
            } else {
                Log.d(TAG, "handleRemoveUser(): User to remove=" + userId + ", halOnly=" + halOnly);
                if (halOnly) {
                    UserHalService userHal = this.mHal.getUserHal();
                    UsersInfo usersInfo = generateUsersInfo();
                    UserInfo userInfo = new UserInfo();
                    userInfo.userId = userId;
                    userInfo.flags = getUserHalFlags(userId);
                    RemoveUserRequest request = new RemoveUserRequest();
                    request.removedUserInfo = userInfo;
                    request.usersInfo = usersInfo;
                    userHal.removeUser(request);
                    writer.printf("User removal sent for HAL only.\n", new Object[0]);
                    return;
                }
                CarUserManager carUserManager = getCarUserManager(this.mContext);
                UserRemovalResult result = carUserManager.removeUser(userId);
                if (result == null) {
                    return;
                }
                writer.printf("UserRemovalResult: status = %s\n", UserRemovalResult.statusToString(result.getStatus()));
                return;
            }
        }
    }

    private static <T> T waitForFuture(PrintWriter printWriter, AndroidFuture<T> androidFuture, int i) {
        T t = null;
        try {
            t = (T) androidFuture.get(i, TimeUnit.MILLISECONDS);
            if (t == null) {
                printWriter.printf("Service didn't respond in %d ms", Integer.valueOf(i));
            }
        } catch (Exception e) {
            printWriter.printf("Exception getting future: %s", e);
        }
        return t;
    }

    private void getInitialUser(PrintWriter writer) {
        android.content.pm.UserInfo user = this.mCarUserService.getInitialUser();
        writer.println(user == null ? NO_INITIAL_USER : Integer.valueOf(user.id));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getUserAuthAssociation(java.lang.String[] r13, java.io.PrintWriter r14) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarShellCommand.getUserAuthAssociation(java.lang.String[], java.io.PrintWriter):void");
    }

    private CarUserManager getCarUserManager(PrintWriter writer, int userId) {
        Context context;
        if (userId == this.mContext.getUserId()) {
            context = this.mContext;
        } else {
            Context context2 = this.mContext;
            context = context2.createContextAsUser(UserHandle.of(userId), 0);
        }
        int actualUserId = Binder.getCallingUid();
        if (actualUserId != userId) {
            writer.printf("Emulating call for user id %d, but caller's user id is %d, so that's what CarUserService will use when calling HAL.\n", Integer.valueOf(userId), Integer.valueOf(actualUserId));
        }
        return getCarUserManager(context);
    }

    private CarUserManager getCarUserManager(Context context) {
        Car car = Car.createCar(context);
        CarUserManager carUserManager = (CarUserManager) car.getCarManager("car_user_service");
        return carUserManager;
    }

    private void showResponse(PrintWriter writer, UserIdentificationResponse response) {
        if (response == null) {
            writer.println("null response");
            return;
        }
        if (!TextUtils.isEmpty(response.errorMessage)) {
            writer.printf("Error message: %s\n", response.errorMessage);
        }
        int numberAssociations = response.associations.size();
        writer.printf("%d associations:\n", Integer.valueOf(numberAssociations));
        for (int i = 0; i < numberAssociations; i++) {
            UserIdentificationAssociation association = response.associations.get(i);
            writer.printf("  %s\n", association);
        }
    }

    private void showResponse(PrintWriter writer, UserIdentificationAssociationResponse response) {
        if (response == null) {
            writer.println("null response");
            return;
        }
        if (!response.isSuccess()) {
            writer.printf("failed response: %s\n", response);
            return;
        }
        String errorMessage = response.getErrorMessage();
        if (!TextUtils.isEmpty(errorMessage)) {
            writer.printf("Error message: %s\n", errorMessage);
        }
        int[] values = response.getValues();
        if (values == null) {
            writer.printf("no associations on %s\n", response);
            return;
        }
        writer.printf("%d associations:\n", Integer.valueOf(values.length));
        for (int i : values) {
            writer.printf("  %s\n", UserIdentificationAssociationValue.toString(i));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setUserAuthAssociation(java.lang.String[] r18, final java.io.PrintWriter r19) {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarShellCommand.setUserAuthAssociation(java.lang.String[], java.io.PrintWriter):void");
    }

    public /* synthetic */ void lambda$setUserAuthAssociation$3$CarShellCommand(PrintWriter writer, CountDownLatch latch, int status, UserIdentificationResponse response) {
        Log.d(TAG, "setUserAuthAssociation(): response=" + response);
        try {
            showResponse(writer, response);
        } finally {
            latch.countDown();
        }
    }

    private static int parseAuthArg(SparseArray<String> types, String type) {
        for (int i = 0; i < types.size(); i++) {
            if (types.valueAt(i).equals(type)) {
                return types.keyAt(i);
            }
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void forceGarageMode(java.lang.String r7, java.io.PrintWriter r8) {
        /*
            r6 = this;
            int r0 = r7.hashCode()
            r1 = -934938715(0xffffffffc845f7a5, float:-202718.58)
            r2 = 0
            r3 = 3
            r4 = 2
            r5 = 1
            if (r0 == r1) goto L3a
            r1 = 3551(0xddf, float:4.976E-42)
            if (r0 == r1) goto L30
            r1 = 109935(0x1ad6f, float:1.54052E-40)
            if (r0 == r1) goto L26
            r1 = 107944136(0x66f18c8, float:4.496911E-35)
            if (r0 == r1) goto L1c
        L1b:
            goto L44
        L1c:
            java.lang.String r0 = "query"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L1b
            r0 = r4
            goto L45
        L26:
            java.lang.String r0 = "off"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L1b
            r0 = r5
            goto L45
        L30:
            java.lang.String r0 = "on"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L1b
            r0 = r2
            goto L45
        L3a:
            java.lang.String r0 = "reboot"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L1b
            r0 = r3
            goto L45
        L44:
            r0 = -1
        L45:
            java.lang.String r1 = "Garage mode: "
            if (r0 == 0) goto L89
            if (r0 == r5) goto L66
            if (r0 == r4) goto L60
            if (r0 == r3) goto L55
            java.lang.String r0 = "Unknown value. Valid argument: on|off|query|reboot"
            r8.println(r0)
            goto Lac
        L55:
            com.android.car.CarPowerManagementService r0 = r6.mCarPowerManagementService
            r0.forceSuspendAndMaybeReboot(r5)
            java.lang.String r0 = "Entering Garage Mode. Will reboot when it completes."
            r8.println(r0)
            goto Lac
        L60:
            com.android.car.garagemode.GarageModeService r0 = r6.mGarageModeService
            r0.dump(r8)
            goto Lac
        L66:
            com.android.car.systeminterface.SystemInterface r0 = r6.mSystemInterface
            r0.setDisplayState(r5)
            com.android.car.garagemode.GarageModeService r0 = r6.mGarageModeService
            r0.stopAndResetGarageMode()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            com.android.car.garagemode.GarageModeService r1 = r6.mGarageModeService
            boolean r1 = r1.isGarageModeActive()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.println(r0)
            goto Lac
        L89:
            com.android.car.systeminterface.SystemInterface r0 = r6.mSystemInterface
            r0.setDisplayState(r2)
            com.android.car.garagemode.GarageModeService r0 = r6.mGarageModeService
            r0.forceStartGarageMode()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            com.android.car.garagemode.GarageModeService r1 = r6.mGarageModeService
            boolean r1 = r1.isGarageModeActive()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.println(r0)
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarShellCommand.forceGarageMode(java.lang.String, java.io.PrintWriter):void");
    }

    private void powerOff(String[] args, PrintWriter writer) {
        boolean skipGarageMode = false;
        boolean shutdown = false;
        for (int index = 1; index < args.length; index++) {
            String str = args[index];
            byte b = -1;
            int iHashCode = str.hashCode();
            if (iHashCode != -153588746) {
                if (iHashCode == 1911562424 && str.equals(POWER_OFF_SKIP_GARAGEMODE)) {
                    b = 0;
                }
            } else if (str.equals(POWER_OFF_SHUTDOWN)) {
                b = 1;
            }
            if (b == 0) {
                skipGarageMode = true;
            } else if (b == 1) {
                shutdown = true;
            } else {
                writer.printf("Not supported option: %s\n", args[index]);
                return;
            }
        }
        this.mCarPowerManagementService.powerOffFromCommand(skipGarageMode, shutdown);
    }

    private void injectVhalEvent(String property, String zone, String value, boolean isErrorEvent, String delayTime, PrintWriter writer) {
        if (zone != null && zone.equalsIgnoreCase(PARAM_VEHICLE_PROPERTY_AREA_GLOBAL) && !isPropertyAreaTypeGlobal(property)) {
            writer.println("Property area type inconsistent with given zone");
            return;
        }
        try {
            if (isErrorEvent) {
                this.mHal.injectOnPropertySetError(property, zone, value);
            } else {
                this.mHal.injectVhalEvent(property, zone, value, delayTime);
            }
        } catch (NumberFormatException e) {
            writer.println("Invalid property Id zone Id or value" + e);
            showHelp(writer);
        }
    }

    private static boolean isPropertyAreaTypeGlobal(String property) {
        return property != null && (Integer.decode(property).intValue() & VehicleArea.MASK) == 16777216;
    }
}
