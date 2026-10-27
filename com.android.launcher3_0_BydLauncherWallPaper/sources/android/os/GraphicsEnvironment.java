package android.os;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetFileDescriptor;
import android.provider.Settings;
import android.provider.SettingsStringUtil;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;
import dalvik.system.VMRuntime;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GraphicsEnvironment {
    private static final String ACTION_ANGLE_FOR_ANDROID = "android.app.action.ANGLE_FOR_ANDROID";
    private static final String ACTION_ANGLE_FOR_ANDROID_TOAST_MESSAGE = "android.app.action.ANGLE_FOR_ANDROID_TOAST_MESSAGE";
    private static final String ANGLE_RULES_FILE = "a4a_rules.json";
    private static final String ANGLE_TEMP_RULES = "debug.angle.rules";
    private static final boolean DEBUG = false;
    private static final int GAME_DRIVER_GLOBAL_OPT_IN_DEFAULT = 0;
    private static final int GAME_DRIVER_GLOBAL_OPT_IN_GAME_DRIVER = 1;
    private static final int GAME_DRIVER_GLOBAL_OPT_IN_OFF = 3;
    private static final int GAME_DRIVER_GLOBAL_OPT_IN_PRERELEASE_DRIVER = 2;
    private static final String GAME_DRIVER_SPHAL_LIBRARIES_FILENAME = "sphal_libraries.txt";
    private static final String GAME_DRIVER_WHITELIST_ALL = "*";
    private static final String INTENT_KEY_A4A_TOAST_MESSAGE = "A4A Toast Message";
    private static final String METADATA_DEVELOPER_DRIVER_ENABLE = "com.android.graphics.developerdriver.enable";
    private static final String METADATA_DRIVER_BUILD_TIME = "com.android.gamedriver.build_time";
    private static final String METADATA_INJECT_LAYERS_ENABLE = "com.android.graphics.injectLayers.enable";
    private static final String PROPERTY_GFX_DRIVER = "ro.gfx.driver.0";
    private static final String PROPERTY_GFX_DRIVER_BUILD_TIME = "ro.gfx.driver_build_time";
    private static final String PROPERTY_GFX_DRIVER_PRERELEASE = "ro.gfx.driver.1";
    private static final String SYSTEM_DRIVER_NAME = "system";
    private static final long SYSTEM_DRIVER_VERSION_CODE = 0;
    private static final String SYSTEM_DRIVER_VERSION_NAME = "";
    private static final String TAG = "GraphicsEnvironment";
    private static final int VULKAN_1_0 = 4194304;
    private static final int VULKAN_1_1 = 4198400;
    private ClassLoader mClassLoader;
    private String mLibraryPermittedPaths;
    private String mLibrarySearchPaths;
    private static final GraphicsEnvironment sInstance = new GraphicsEnvironment();
    private static final Map<OpenGlDriverChoice, String> sDriverMap = buildMap();

    enum OpenGlDriverChoice {
        DEFAULT,
        NATIVE,
        ANGLE
    }

    private static native boolean getShouldUseAngle(String str);

    public static native void hintActivityLaunch();

    private static native boolean isDebuggable();

    private static native void setAngleInfo(String str, String str2, String str3, FileDescriptor fileDescriptor, long j, long j2);

    private static native void setDebugLayers(String str);

    private static native void setDebugLayersGLES(String str);

    private static native void setDriverPathAndSphalLibraries(String str, String str2);

    private static native void setGpuStats(String str, String str2, long j, long j2, String str3, int i);

    private static native boolean setInjectLayersPrSetDumpable();

    private static native void setLayerPaths(ClassLoader classLoader, String str);

    public static GraphicsEnvironment getInstance() {
        return sInstance;
    }

    public void setup(Context context, Bundle bundle) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        ApplicationInfo appInfoWithMetadata = getAppInfoWithMetadata(context, packageManager, packageName);
        Trace.traceBegin(2L, "setupGpuLayers");
        setupGpuLayers(context, bundle, packageManager, packageName, appInfoWithMetadata);
        Trace.traceEnd(2L);
        Trace.traceBegin(2L, "setupAngle");
        setupAngle(context, bundle, packageManager, packageName);
        Trace.traceEnd(2L);
        Trace.traceBegin(2L, "chooseDriver");
        if (!chooseDriver(context, bundle, packageManager, packageName, appInfoWithMetadata)) {
            setGpuStats("system", "", 0L, SystemProperties.getLong(PROPERTY_GFX_DRIVER_BUILD_TIME, 0L), packageName, getVulkanVersion(packageManager));
        }
        Trace.traceEnd(2L);
    }

    public static boolean shouldUseAngle(Context context, Bundle bundle, String str) {
        if (str.isEmpty()) {
            Log.v(TAG, "No package name available yet, ANGLE should not be used");
            return false;
        }
        String driverForPkg = getDriverForPkg(context, bundle, str);
        boolean zCheckAngleWhitelist = checkAngleWhitelist(context, bundle, str);
        boolean zEquals = driverForPkg.equals(sDriverMap.get(OpenGlDriverChoice.ANGLE));
        if (!(zCheckAngleWhitelist || zEquals)) {
            return false;
        }
        if (zCheckAngleWhitelist) {
            Log.v(TAG, "ANGLE whitelist includes " + str);
        }
        if (zEquals) {
            Log.v(TAG, "ANGLE developer option for " + str + ": " + driverForPkg);
        }
        return true;
    }

    private static int getVulkanVersion(PackageManager packageManager) {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_VULKAN_HARDWARE_VERSION, VULKAN_1_1) ? VULKAN_1_1 : packageManager.hasSystemFeature(PackageManager.FEATURE_VULKAN_HARDWARE_VERSION, 4194304) ? 4194304 : 0;
    }

    private static boolean canInjectLayers(ApplicationInfo applicationInfo) {
        return applicationInfo.metaData != null && applicationInfo.metaData.getBoolean(METADATA_INJECT_LAYERS_ENABLE) && setInjectLayersPrSetDumpable();
    }

    public void setLayerPaths(ClassLoader classLoader, String str, String str2) {
        this.mClassLoader = classLoader;
        this.mLibrarySearchPaths = str;
        this.mLibraryPermittedPaths = str2;
    }

    public String getDebugLayerPathsFromSettings(Bundle bundle, IPackageManager iPackageManager, String str, ApplicationInfo applicationInfo) {
        if (!debugLayerEnabled(bundle, str, applicationInfo)) {
            return null;
        }
        Log.i(TAG, "GPU debug layers enabled for " + str);
        String str2 = "";
        String string = bundle.getString(Settings.Global.GPU_DEBUG_LAYER_APP, "");
        if (!string.isEmpty()) {
            Log.i(TAG, "GPU debug layer apps: " + string);
            for (String str3 : string.split(SettingsStringUtil.DELIMITER)) {
                String debugLayerAppPaths = getDebugLayerAppPaths(iPackageManager, str3);
                if (!debugLayerAppPaths.isEmpty()) {
                    str2 = str2 + debugLayerAppPaths + File.pathSeparator;
                }
            }
        }
        return str2;
    }

    private static String getDebugLayerAppPaths(IPackageManager iPackageManager, String str) {
        try {
            ApplicationInfo applicationInfo = iPackageManager.getApplicationInfo(str, 131072, UserHandle.myUserId());
            if (applicationInfo == null) {
                Log.w(TAG, "Debug layer app '" + str + "' not installed");
                return "";
            }
            String strChooseAbi = chooseAbi(applicationInfo);
            StringBuilder sb = new StringBuilder();
            sb.append(applicationInfo.nativeLibraryDir).append(File.pathSeparator).append(applicationInfo.sourceDir).append("!/lib/").append(strChooseAbi);
            return sb.toString();
        } catch (RemoteException unused) {
            return "";
        }
    }

    private boolean debugLayerEnabled(Bundle bundle, String str, ApplicationInfo applicationInfo) {
        if ((!isDebuggable() && !canInjectLayers(applicationInfo)) || bundle.getInt(Settings.Global.ENABLE_GPU_DEBUG_LAYERS, 0) == 0) {
            return false;
        }
        String string = bundle.getString(Settings.Global.GPU_DEBUG_APP, "");
        return (str == null || string.isEmpty() || str.isEmpty() || !string.equals(str)) ? false : true;
    }

    private void setupGpuLayers(Context context, Bundle bundle, PackageManager packageManager, String str, ApplicationInfo applicationInfo) {
        String str2;
        if (debugLayerEnabled(bundle, str, applicationInfo)) {
            str2 = this.mLibraryPermittedPaths;
            String string = bundle.getString(Settings.Global.GPU_DEBUG_LAYERS);
            Log.i(TAG, "Vulkan debug layer list: " + string);
            if (string != null && !string.isEmpty()) {
                setDebugLayers(string);
            }
            String string2 = bundle.getString(Settings.Global.GPU_DEBUG_LAYERS_GLES);
            Log.i(TAG, "GLES debug layer list: " + string2);
            if (string2 != null && !string2.isEmpty()) {
                setDebugLayersGLES(string2);
            }
        } else {
            str2 = "";
        }
        setLayerPaths(this.mClassLoader, str2 + this.mLibrarySearchPaths);
    }

    private static Map<OpenGlDriverChoice, String> buildMap() {
        HashMap map = new HashMap();
        map.put(OpenGlDriverChoice.DEFAULT, "default");
        map.put(OpenGlDriverChoice.ANGLE, "angle");
        map.put(OpenGlDriverChoice.NATIVE, "native");
        return map;
    }

    private static List<String> getGlobalSettingsString(ContentResolver contentResolver, Bundle bundle, String str) {
        String string;
        if (bundle != null) {
            string = bundle.getString(str);
        } else {
            string = Settings.Global.getString(contentResolver, str);
        }
        if (string != null) {
            return new ArrayList(Arrays.asList(string.split(SmsManager.REGEX_PREFIX_DELIMITER)));
        }
        return new ArrayList();
    }

    private static int getGlobalSettingsPkgIndex(String str, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(str)) {
                return i;
            }
        }
        return -1;
    }

    private static ApplicationInfo getAppInfoWithMetadata(Context context, PackageManager packageManager, String str) {
        try {
            return packageManager.getApplicationInfo(str, 128);
        } catch (PackageManager.NameNotFoundException unused) {
            return context.getApplicationInfo();
        }
    }

    private static String getDriverForPkg(Context context, Bundle bundle, String str) {
        String string;
        if (bundle != null) {
            string = bundle.getString(Settings.Global.GLOBAL_SETTINGS_ANGLE_GL_DRIVER_ALL_ANGLE);
        } else {
            string = Settings.Global.getString(context.getContentResolver(), Settings.Global.GLOBAL_SETTINGS_ANGLE_GL_DRIVER_ALL_ANGLE);
        }
        if (string != null && string.equals("1")) {
            return sDriverMap.get(OpenGlDriverChoice.ANGLE);
        }
        ContentResolver contentResolver = context.getContentResolver();
        List<String> globalSettingsString = getGlobalSettingsString(contentResolver, bundle, Settings.Global.GLOBAL_SETTINGS_ANGLE_GL_DRIVER_SELECTION_PKGS);
        List<String> globalSettingsString2 = getGlobalSettingsString(contentResolver, bundle, Settings.Global.GLOBAL_SETTINGS_ANGLE_GL_DRIVER_SELECTION_VALUES);
        if (str == null || str.isEmpty()) {
            return sDriverMap.get(OpenGlDriverChoice.DEFAULT);
        }
        if (globalSettingsString.size() != globalSettingsString2.size()) {
            Log.w(TAG, "Global.Settings values are invalid: globalSettingsDriverPkgs.size = " + globalSettingsString.size() + ", globalSettingsDriverValues.size = " + globalSettingsString2.size());
            return sDriverMap.get(OpenGlDriverChoice.DEFAULT);
        }
        int globalSettingsPkgIndex = getGlobalSettingsPkgIndex(str, globalSettingsString);
        if (globalSettingsPkgIndex < 0) {
            return sDriverMap.get(OpenGlDriverChoice.DEFAULT);
        }
        return globalSettingsString2.get(globalSettingsPkgIndex);
    }

    private String getAnglePackageName(PackageManager packageManager) {
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(new Intent(ACTION_ANGLE_FOR_ANDROID), 1048576);
        if (listQueryIntentActivities.size() != 1) {
            Log.e(TAG, "Invalid number of ANGLE packages. Required: 1, Found: " + listQueryIntentActivities.size());
            Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
            while (it.hasNext()) {
                Log.e(TAG, "Found ANGLE package: " + it.next().activityInfo.packageName);
            }
            return "";
        }
        return listQueryIntentActivities.get(0).activityInfo.packageName;
    }

    private String getAngleDebugPackage(Context context, Bundle bundle) {
        String string;
        if (!isDebuggable()) {
            return "";
        }
        if (bundle != null) {
            string = bundle.getString(Settings.Global.GLOBAL_SETTINGS_ANGLE_DEBUG_PACKAGE);
        } else {
            string = Settings.Global.getString(context.getContentResolver(), Settings.Global.GLOBAL_SETTINGS_ANGLE_DEBUG_PACKAGE);
        }
        return (string == null || string.isEmpty()) ? "" : string;
    }

    private static boolean setupAngleWithTempRulesFile(Context context, String str, String str2, String str3) {
        if (!isDebuggable()) {
            Log.v(TAG, "Skipping loading temporary rules file");
            return false;
        }
        String str4 = SystemProperties.get(ANGLE_TEMP_RULES);
        if (str4 == null || str4.isEmpty()) {
            Log.v(TAG, "System property 'debug.angle.rules' is not set or is empty");
            return false;
        }
        Log.i(TAG, "Detected system property debug.angle.rules: " + str4);
        if (new File(str4).exists()) {
            Log.i(TAG, str4 + " exists, loading file.");
            try {
                FileInputStream fileInputStream = new FileInputStream(str4);
                try {
                    FileDescriptor fd = fileInputStream.getFD();
                    long size = fileInputStream.getChannel().size();
                    Log.i(TAG, "Loaded temporary ANGLE rules from " + str4);
                    setAngleInfo(str2, str, str3, fd, 0L, size);
                    fileInputStream.close();
                    return true;
                } catch (IOException e) {
                    Log.w(TAG, "Hit IOException thrown by FileInputStream: " + e);
                    return false;
                }
            } catch (FileNotFoundException e2) {
                Log.w(TAG, "Temp ANGLE rules file not found: " + e2);
            } catch (SecurityException e3) {
                Log.w(TAG, "Temp ANGLE rules file not accessible: " + e3);
            }
        }
        return false;
    }

    private static boolean setupAngleRulesApk(String str, ApplicationInfo applicationInfo, PackageManager packageManager, String str2, String str3, String str4) {
        try {
            try {
                AssetFileDescriptor assetFileDescriptorOpenFd = packageManager.getResourcesForApplication(applicationInfo).getAssets().openFd(ANGLE_RULES_FILE);
                setAngleInfo(str3, str2, str4, assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
                assetFileDescriptorOpenFd.close();
                return true;
            } catch (IOException e) {
                Log.w(TAG, "Failed to get AssetFileDescriptor for a4a_rules.json from '" + str + "': " + e);
                return false;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Log.w(TAG, "Failed to get AssetManager for '" + str + "': " + e2);
            return false;
        }
    }

    private static boolean checkAngleWhitelist(Context context, Bundle bundle, String str) {
        return getGlobalSettingsString(context.getContentResolver(), bundle, Settings.Global.GLOBAL_SETTINGS_ANGLE_WHITELIST).contains(str);
    }

    public boolean setupAngle(Context context, Bundle bundle, PackageManager packageManager, String str) {
        ApplicationInfo applicationInfo;
        String anglePackageName;
        if (!shouldUseAngle(context, bundle, str)) {
            return false;
        }
        ApplicationInfo applicationInfo2 = null;
        String angleDebugPackage = getAngleDebugPackage(context, bundle);
        if (!angleDebugPackage.isEmpty()) {
            Log.i(TAG, "ANGLE debug package enabled: " + angleDebugPackage);
            try {
                applicationInfo2 = packageManager.getApplicationInfo(angleDebugPackage, 0);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w(TAG, "ANGLE debug package '" + angleDebugPackage + "' not installed");
                return false;
            }
        }
        if (applicationInfo2 == null) {
            anglePackageName = getAnglePackageName(packageManager);
            if (!anglePackageName.isEmpty()) {
                Log.i(TAG, "ANGLE package enabled: " + anglePackageName);
                try {
                    applicationInfo = packageManager.getApplicationInfo(anglePackageName, 1048576);
                } catch (PackageManager.NameNotFoundException unused2) {
                    Log.w(TAG, "ANGLE package '" + anglePackageName + "' not installed");
                    return false;
                }
            } else {
                Log.e(TAG, "Failed to find ANGLE package.");
                return false;
            }
        } else {
            applicationInfo = applicationInfo2;
            anglePackageName = angleDebugPackage;
        }
        String str2 = applicationInfo.nativeLibraryDir + File.pathSeparator + applicationInfo.sourceDir + "!/lib/" + chooseAbi(applicationInfo);
        String driverForPkg = getDriverForPkg(context, bundle, str);
        return setupAngleWithTempRulesFile(context, str, str2, driverForPkg) || setupAngleRulesApk(anglePackageName, applicationInfo, packageManager, str, str2, driverForPkg);
    }

    private boolean shouldShowAngleInUseDialogBox(Context context) {
        try {
            return Settings.Global.getInt(context.getContentResolver(), Settings.Global.GLOBAL_SETTINGS_SHOW_ANGLE_IN_USE_DIALOG_BOX) == 1;
        } catch (Settings.SettingNotFoundException | SecurityException unused) {
            return false;
        }
    }

    private boolean setupAndUseAngle(Context context, String str) {
        if (!setupAngle(context, null, context.getPackageManager(), str)) {
            Log.v(TAG, "Package '" + str + "' should not use ANGLE");
            return false;
        }
        boolean shouldUseAngle = getShouldUseAngle(str);
        Log.v(TAG, "Package '" + str + "' should use ANGLE = '" + shouldUseAngle + "'");
        return shouldUseAngle;
    }

    public void showAngleInUseDialogBox(Context context) {
        String packageName = context.getPackageName();
        if (shouldShowAngleInUseDialogBox(context) && setupAndUseAngle(context, packageName)) {
            Intent intent = new Intent(ACTION_ANGLE_FOR_ANDROID_TOAST_MESSAGE);
            intent.setPackage(getAnglePackageName(context.getPackageManager()));
            context.sendOrderedBroadcast(intent, null, new BroadcastReceiver() { // from class: android.os.GraphicsEnvironment.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent2) {
                    Toast.makeText(context2, getResultExtras(true).getString(GraphicsEnvironment.INTENT_KEY_A4A_TOAST_MESSAGE), 1).show();
                }
            }, null, -1, null, null);
        }
    }

    private static String chooseDriverInternal(Bundle bundle, ApplicationInfo applicationInfo) {
        String str = SystemProperties.get(PROPERTY_GFX_DRIVER);
        boolean z = (str == null || str.isEmpty()) ? false : true;
        String str2 = SystemProperties.get(PROPERTY_GFX_DRIVER_PRERELEASE);
        boolean z2 = (str2 == null || str2.isEmpty()) ? false : true;
        if ((!z && !z2) || applicationInfo.isPrivilegedApp() || (applicationInfo.isSystemApp() && !applicationInfo.isUpdatedSystemApp())) {
            return null;
        }
        boolean z3 = (applicationInfo.metaData != null && applicationInfo.metaData.getBoolean(METADATA_DEVELOPER_DRIVER_ENABLE)) || isDebuggable();
        int i = bundle.getInt(Settings.Global.GAME_DRIVER_ALL_APPS, 0);
        if (i == 1) {
            if (z) {
                return str;
            }
            return null;
        }
        if (i == 2) {
            if (z2 && z3) {
                return str2;
            }
            return null;
        }
        if (i == 3) {
            return null;
        }
        String str3 = applicationInfo.packageName;
        if (getGlobalSettingsString(null, bundle, Settings.Global.GAME_DRIVER_OPT_OUT_APPS).contains(str3)) {
            return null;
        }
        if (getGlobalSettingsString(null, bundle, Settings.Global.GAME_DRIVER_PRERELEASE_OPT_IN_APPS).contains(str3)) {
            if (z2 && z3) {
                return str2;
            }
            return null;
        }
        if (!z) {
            return null;
        }
        boolean zContains = getGlobalSettingsString(null, bundle, Settings.Global.GAME_DRIVER_OPT_IN_APPS).contains(str3);
        List<String> globalSettingsString = getGlobalSettingsString(null, bundle, Settings.Global.GAME_DRIVER_WHITELIST);
        if (!zContains && globalSettingsString.indexOf("*") != 0 && !globalSettingsString.contains(str3)) {
            return null;
        }
        if (zContains || !getGlobalSettingsString(null, bundle, Settings.Global.GAME_DRIVER_BLACKLIST).contains(str3)) {
            return str;
        }
        return null;
    }

    private static boolean chooseDriver(Context context, Bundle bundle, PackageManager packageManager, String str, ApplicationInfo applicationInfo) {
        String strChooseAbi;
        String strChooseDriverInternal = chooseDriverInternal(bundle, applicationInfo);
        if (strChooseDriverInternal == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(strChooseDriverInternal, 1048704);
            ApplicationInfo applicationInfo2 = packageInfo.applicationInfo;
            if (applicationInfo2.targetSdkVersion < 26 || (strChooseAbi = chooseAbi(applicationInfo2)) == null) {
                return false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(applicationInfo2.nativeLibraryDir).append(File.pathSeparator);
            sb.append(applicationInfo2.sourceDir).append("!/lib/").append(strChooseAbi);
            setDriverPathAndSphalLibraries(sb.toString(), getSphalLibraries(context, strChooseDriverInternal));
            Objects.requireNonNull(applicationInfo2.metaData, "apk's meta-data cannot be null");
            String string = applicationInfo2.metaData.getString(METADATA_DRIVER_BUILD_TIME);
            if (string == null || string.isEmpty()) {
                throw new IllegalArgumentException("com.android.gamedriver.build_time is not set");
            }
            setGpuStats(strChooseDriverInternal, packageInfo.versionName, applicationInfo2.longVersionCode, Long.parseLong(string.substring(1)), str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w(TAG, "driver package '" + strChooseDriverInternal + "' not installed");
            return false;
        }
    }

    private static String chooseAbi(ApplicationInfo applicationInfo) {
        String currentInstructionSet = VMRuntime.getCurrentInstructionSet();
        if (applicationInfo.primaryCpuAbi != null && currentInstructionSet.equals(VMRuntime.getInstructionSet(applicationInfo.primaryCpuAbi))) {
            return applicationInfo.primaryCpuAbi;
        }
        if (applicationInfo.secondaryCpuAbi == null || !currentInstructionSet.equals(VMRuntime.getInstructionSet(applicationInfo.secondaryCpuAbi))) {
            return null;
        }
        return applicationInfo.secondaryCpuAbi;
    }

    private static String getSphalLibraries(Context context, String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.createPackageContext(str, 4).getAssets().open(GAME_DRIVER_SPHAL_LIBRARIES_FILENAME)));
            ArrayList arrayList = new ArrayList();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    arrayList.add(line);
                } else {
                    return String.join(SettingsStringUtil.DELIMITER, arrayList);
                }
            }
        } catch (PackageManager.NameNotFoundException | IOException unused) {
            return "";
        }
    }
}
