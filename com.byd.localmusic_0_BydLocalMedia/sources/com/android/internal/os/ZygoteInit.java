package com.android.internal.os;

import android.app.ApplicationLoaders;
import android.content.pm.SharedLibraryInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Environment;
import android.os.IInstalld;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.os.ZygoteProcess;
import android.os.storage.StorageManager;
import android.provider.SettingsStringUtil;
import android.security.keystore.AndroidKeyStoreProvider;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructCapUserData;
import android.system.StructCapUserHeader;
import android.text.Hyphenator;
import android.util.EventLog;
import android.util.Log;
import android.util.TimingsTraceLog;
import android.webkit.WebViewFactory;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.os.RuntimeInit;
import com.android.internal.util.FrameworkStatsLog;
import com.android.internal.util.Preconditions;
import dalvik.system.DexFile;
import dalvik.system.VMRuntime;
import dalvik.system.ZygoteHooks;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Provider;
import java.security.Security;

/* JADX INFO: loaded from: classes4.dex */
public class ZygoteInit {
    private static final String ABI_LIST_ARG = "--abi-list=";
    private static final int LOG_BOOT_PROGRESS_PRELOAD_END = 3030;
    private static final int LOG_BOOT_PROGRESS_PRELOAD_START = 3020;
    private static final String PRELOADED_CLASSES = "/system/etc/preloaded-classes";
    public static final boolean PRELOAD_RESOURCES = true;
    private static final String PROPERTY_DISABLE_GRAPHICS_DRIVER_PRELOADING = "ro.zygote.disable_gl_preload";
    private static final int ROOT_GID = 0;
    private static final int ROOT_UID = 0;
    private static final String SOCKET_NAME_ARG = "--socket-name=";
    private static final String TAG = "Zygote";
    private static final int UNPRIVILEGED_GID = 9999;
    private static final int UNPRIVILEGED_UID = 9999;
    private static Resources mResources;
    private static boolean sPreloadComplete;

    private static native void nativePreloadAppProcessHALs();

    static native void nativePreloadGraphicsDriver();

    private static final native void nativeZygoteInit();

    static void preload(TimingsTraceLog timingsTraceLog) {
        Log.d(TAG, "begin preload");
        timingsTraceLog.traceBegin("BeginPreload");
        beginPreload();
        timingsTraceLog.traceEnd();
        timingsTraceLog.traceBegin("PreloadClasses");
        ZygoteInitAccelerate.parallelPreloadClasses();
        timingsTraceLog.traceEnd();
        timingsTraceLog.traceBegin("CacheNonBootClasspathClassLoaders");
        cacheNonBootClasspathClassLoaders();
        timingsTraceLog.traceEnd();
        timingsTraceLog.traceBegin("PreloadResources");
        preloadResources();
        timingsTraceLog.traceEnd();
        Trace.traceBegin(16384L, "PreloadAppProcessHALs");
        nativePreloadAppProcessHALs();
        Trace.traceEnd(16384L);
        Trace.traceBegin(16384L, "PreloadGraphicsDriver");
        maybePreloadGraphicsDriver();
        Trace.traceEnd(16384L);
        preloadSharedLibraries();
        preloadTextResources();
        WebViewFactory.prepareWebViewInZygote();
        endPreload();
        warmUpJcaProviders();
        Log.d(TAG, "end preload");
        sPreloadComplete = true;
    }

    public static void lazyPreload() {
        Preconditions.checkState(!sPreloadComplete);
        Log.i(TAG, "Lazily preloading resources.");
        preload(new TimingsTraceLog("ZygoteInitTiming_lazy", 16384L));
    }

    private static void beginPreload() {
        Log.i(TAG, "Calling ZygoteHooks.beginPreload()");
        ZygoteHooks.onBeginPreload();
    }

    private static void endPreload() {
        ZygoteHooks.onEndPreload();
        Log.i(TAG, "Called ZygoteHooks.endPreload()");
    }

    private static void preloadSharedLibraries() {
        Log.i(TAG, "Preloading shared libraries...");
        System.loadLibrary("android");
        System.loadLibrary("compiler_rt");
        System.loadLibrary("jnigraphics");
        try {
            System.loadLibrary("sfplugin_ccodec");
        } catch (Error | RuntimeException unused) {
        }
    }

    private static void maybePreloadGraphicsDriver() {
        if (SystemProperties.getBoolean(PROPERTY_DISABLE_GRAPHICS_DRIVER_PRELOADING, false)) {
            return;
        }
        nativePreloadGraphicsDriver();
    }

    private static void preloadTextResources() {
        Hyphenator.init();
        TextView.preloadFontCache();
    }

    private static void warmUpJcaProviders() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        Trace.traceBegin(16384L, "Starting installation of AndroidKeyStoreProvider");
        AndroidKeyStoreProvider.install();
        Log.i(TAG, "Installed AndroidKeyStoreProvider in " + (SystemClock.uptimeMillis() - jUptimeMillis) + "ms.");
        Trace.traceEnd(16384L);
        long jUptimeMillis2 = SystemClock.uptimeMillis();
        Trace.traceBegin(16384L, "Starting warm up of JCA providers");
        for (Provider provider : Security.getProviders()) {
            provider.warmUpServiceProvision();
        }
        Log.i(TAG, "Warmed up JCA providers in " + (SystemClock.uptimeMillis() - jUptimeMillis2) + "ms.");
        Trace.traceEnd(16384L);
    }

    /* JADX WARN: Removed duplicated region for block: B:86:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void preloadClasses() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 508
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.ZygoteInit.preloadClasses():void");
    }

    private static void cacheNonBootClasspathClassLoaders() {
        SharedLibraryInfo sharedLibraryInfo = new SharedLibraryInfo("/system/framework/android.hidl.base-V1.0-java.jar", null, null, null, 0L, 0, null, null, null);
        SharedLibraryInfo sharedLibraryInfo2 = new SharedLibraryInfo("/system/framework/android.hidl.manager-V1.0-java.jar", null, null, null, 0L, 0, null, null, null);
        sharedLibraryInfo2.addDependency(sharedLibraryInfo);
        ApplicationLoaders.getDefault().createAndCacheNonBootclasspathSystemClassLoaders(new SharedLibraryInfo[]{sharedLibraryInfo, sharedLibraryInfo2, new SharedLibraryInfo("/system/framework/android.test.base.jar", null, null, null, 0L, 0, null, null, null)});
    }

    private static void preloadResources() {
        VMRuntime.getRuntime();
        try {
            Resources system = Resources.getSystem();
            mResources = system;
            system.startPreloading();
            Log.i(TAG, "Preloading resources...");
            long jUptimeMillis = SystemClock.uptimeMillis();
            TypedArray typedArrayObtainTypedArray = mResources.obtainTypedArray(R.array.preloaded_drawables);
            int iPreloadDrawables = preloadDrawables(typedArrayObtainTypedArray);
            typedArrayObtainTypedArray.recycle();
            Log.i(TAG, "...preloaded " + iPreloadDrawables + " resources in " + (SystemClock.uptimeMillis() - jUptimeMillis) + "ms.");
            long jUptimeMillis2 = SystemClock.uptimeMillis();
            TypedArray typedArrayObtainTypedArray2 = mResources.obtainTypedArray(R.array.preloaded_color_state_lists);
            int iPreloadColorStateLists = preloadColorStateLists(typedArrayObtainTypedArray2);
            typedArrayObtainTypedArray2.recycle();
            Log.i(TAG, "...preloaded " + iPreloadColorStateLists + " resources in " + (SystemClock.uptimeMillis() - jUptimeMillis2) + "ms.");
            if (mResources.getBoolean(R.bool.config_freeformWindowManagement)) {
                long jUptimeMillis3 = SystemClock.uptimeMillis();
                TypedArray typedArrayObtainTypedArray3 = mResources.obtainTypedArray(R.array.preloaded_freeform_multi_window_drawables);
                int iPreloadDrawables2 = preloadDrawables(typedArrayObtainTypedArray3);
                typedArrayObtainTypedArray3.recycle();
                Log.i(TAG, "...preloaded " + iPreloadDrawables2 + " resource in " + (SystemClock.uptimeMillis() - jUptimeMillis3) + "ms.");
            }
            mResources.finishPreloading();
        } catch (RuntimeException e) {
            Log.w(TAG, "Failure preloading resources", e);
        }
    }

    private static int preloadColorStateLists(TypedArray typedArray) {
        int length = typedArray.length();
        for (int i = 0; i < length; i++) {
            int resourceId = typedArray.getResourceId(i, 0);
            if (resourceId != 0 && mResources.getColorStateList(resourceId, null) == null) {
                throw new IllegalArgumentException("Unable to find preloaded color resource #0x" + Integer.toHexString(resourceId) + " (" + typedArray.getString(i) + ")");
            }
        }
        return length;
    }

    private static int preloadDrawables(TypedArray typedArray) {
        int length = typedArray.length();
        for (int i = 0; i < length; i++) {
            int resourceId = typedArray.getResourceId(i, 0);
            if (resourceId != 0 && mResources.getDrawable(resourceId, null) == null) {
                throw new IllegalArgumentException("Unable to find preloaded drawable resource #0x" + Integer.toHexString(resourceId) + " (" + typedArray.getString(i) + ")");
            }
        }
        return length;
    }

    private static void gcAndFinalize() {
        ZygoteHooks.gcAndFinalize();
    }

    private static boolean shouldProfileSystemServer() {
        return SystemProperties.getBoolean("persist.device_config.runtime_native_boot.profilesystemserver", SystemProperties.getBoolean("dalvik.vm.profilesystemserver", false));
    }

    private static Runnable handleSystemServerProcess(ZygoteArguments zygoteArguments) {
        String[] strArr;
        Os.umask(OsConstants.S_IRWXG | OsConstants.S_IRWXO);
        if (zygoteArguments.mNiceName != null) {
            Process.setArgV0(zygoteArguments.mNiceName);
        }
        String str = Os.getenv("SYSTEMSERVERCLASSPATH");
        if (str != null) {
            performSystemServerDexOpt(str);
            if (shouldProfileSystemServer() && (Build.IS_USERDEBUG || Build.IS_ENG)) {
                try {
                    Log.d(TAG, "Preparing system server profile");
                    prepareSystemServerProfile(str);
                } catch (Exception e) {
                    Log.wtf(TAG, "Failed to set up system server profile", e);
                }
            }
        }
        if (zygoteArguments.mInvokeWith != null) {
            String[] strArr2 = zygoteArguments.mRemainingArgs;
            if (str != null) {
                String[] strArr3 = new String[strArr2.length + 2];
                strArr3[0] = "-cp";
                strArr3[1] = str;
                System.arraycopy(strArr2, 0, strArr3, 2, strArr2.length);
                strArr = strArr3;
            } else {
                strArr = strArr2;
            }
            WrapperInit.execApplication(zygoteArguments.mInvokeWith, zygoteArguments.mNiceName, zygoteArguments.mTargetSdkVersion, VMRuntime.getCurrentInstructionSet(), null, strArr);
            throw new IllegalStateException("Unexpected return from WrapperInit.execApplication");
        }
        ClassLoader classLoaderCreatePathClassLoader = null;
        if (str != null) {
            classLoaderCreatePathClassLoader = createPathClassLoader(str, zygoteArguments.mTargetSdkVersion);
            Thread.currentThread().setContextClassLoader(classLoaderCreatePathClassLoader);
        }
        return zygoteInit(zygoteArguments.mTargetSdkVersion, zygoteArguments.mDisabledCompatChanges, zygoteArguments.mRemainingArgs, classLoaderCreatePathClassLoader);
    }

    private static void prepareSystemServerProfile(String str) throws RemoteException {
        if (str.isEmpty()) {
            return;
        }
        String[] strArrSplit = str.split(SettingsStringUtil.DELIMITER);
        IInstalld.Stub.asInterface(ServiceManager.getService("installd")).prepareAppProfile("android", 0, UserHandle.getAppId(1000), "primary.prof", strArrSplit[0], null);
        VMRuntime.registerAppInfo(new File(Environment.getDataProfilesDePackageDirectory(0, "android"), "primary.prof").getAbsolutePath(), strArrSplit);
    }

    public static void setApiBlacklistExemptions(String[] strArr) {
        VMRuntime.getRuntime().setHiddenApiExemptions(strArr);
    }

    public static void setHiddenApiAccessLogSampleRate(int i) {
        VMRuntime.getRuntime().setHiddenApiAccessLogSamplingRate(i);
    }

    public static void setHiddenApiUsageLogger(VMRuntime.HiddenApiUsageLogger hiddenApiUsageLogger) {
        VMRuntime.getRuntime();
        VMRuntime.setHiddenApiUsageLogger(hiddenApiUsageLogger);
    }

    static ClassLoader createPathClassLoader(String str, int i) {
        String property = System.getProperty("java.library.path");
        return ClassLoaderFactory.createClassLoader(str, property, property, ClassLoader.getSystemClassLoader().getParent(), i, true, null);
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 5 */
    private static void performSystemServerDexOpt(String str) {
        int dexOptNeeded;
        int i;
        int i2;
        String str2;
        String str3;
        String[] strArrSplit = str.split(SettingsStringUtil.DELIMITER);
        IInstalld iInstalldAsInterface = IInstalld.Stub.asInterface(ServiceManager.getService("installd"));
        String strVmInstructionSet = VMRuntime.getRuntime().vmInstructionSet();
        int length = strArrSplit.length;
        String strEncodeSystemServerClassPath = "";
        int i3 = 0;
        while (i3 < length) {
            String str4 = strArrSplit[i3];
            String str5 = SystemProperties.get("dalvik.vm.systemservercompilerfilter", "verify");
            String systemServerClassLoaderContext = getSystemServerClassLoaderContext(strEncodeSystemServerClassPath);
            try {
                dexOptNeeded = DexFile.getDexOptNeeded(str4, strVmInstructionSet, str5, systemServerClassLoaderContext, false, false);
            } catch (FileNotFoundException unused) {
                i = i3;
                i2 = length;
                Log.w(TAG, "Missing classpath element for system server: " + str4);
                strEncodeSystemServerClassPath = strEncodeSystemServerClassPath;
            } catch (IOException e) {
                Log.w(TAG, "Error checking classpath element for system server: " + str4, e);
                dexOptNeeded = 0;
            }
            if (dexOptNeeded != 0) {
                i = i3;
                String str6 = strEncodeSystemServerClassPath;
                i2 = length;
                try {
                    iInstalldAsInterface.dexopt(str4, 1000, "*", strVmInstructionSet, dexOptNeeded, null, 0, str5, StorageManager.UUID_PRIVATE_INTERNAL, systemServerClassLoaderContext, null, false, 0, null, null, "server-dexopt");
                    str2 = str4;
                } catch (RemoteException e2) {
                    e = e2;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed compiling classpath element for system server: ");
                    str2 = str4;
                    sb.append(str2);
                    Log.w(TAG, sb.toString(), e);
                } catch (ServiceSpecificException e3) {
                    e = e3;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Failed compiling classpath element for system server: ");
                    str2 = str4;
                    sb2.append(str2);
                    Log.w(TAG, sb2.toString(), e);
                }
                str3 = str6;
            } else {
                str2 = str4;
                i = i3;
                i2 = length;
                str3 = strEncodeSystemServerClassPath;
            }
            strEncodeSystemServerClassPath = encodeSystemServerClassPath(str3, str2);
            i3 = i + 1;
            length = i2;
        }
    }

    private static String getSystemServerClassLoaderContext(String str) {
        if (str == null) {
            return "PCL[]";
        }
        return "PCL[" + str + "]";
    }

    private static String encodeSystemServerClassPath(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return str2;
        }
        return str + SettingsStringUtil.DELIMITER + str2;
    }

    private static Runnable forkSystemServer(String str, String str2, ZygoteServer zygoteServer) {
        long jPosixCapabilitiesAsBits = posixCapabilitiesAsBits(OsConstants.CAP_IPC_LOCK, OsConstants.CAP_KILL, OsConstants.CAP_NET_ADMIN, OsConstants.CAP_NET_BIND_SERVICE, OsConstants.CAP_NET_BROADCAST, OsConstants.CAP_NET_RAW, OsConstants.CAP_SYS_MODULE, OsConstants.CAP_SYS_NICE, OsConstants.CAP_SYS_PTRACE, OsConstants.CAP_SYS_TIME, OsConstants.CAP_SYS_TTY_CONFIG, OsConstants.CAP_WAKE_ALARM, OsConstants.CAP_BLOCK_SUSPEND);
        try {
            StructCapUserData[] structCapUserDataArrCapget = Os.capget(new StructCapUserHeader(OsConstants._LINUX_CAPABILITY_VERSION_3, 0));
            long j = jPosixCapabilitiesAsBits & (((long) structCapUserDataArrCapget[0].effective) | (((long) structCapUserDataArrCapget[1].effective) << 32));
            try {
                ZygoteArguments zygoteArguments = new ZygoteArguments(new String[]{"--setuid=1000", "--setgid=1000", "--setgroups=1001,1002,1003,1004,1005,1006,1007,1008,1009,1010,1018,1021,1023,1024,1032,1065,3001,3002,3003,3006,3007,3009,3010,3011", "--capabilities=" + j + "," + j, "--nice-name=system_server", "--runtime-args", "--target-sdk-version=10000", "com.android.server.SystemServer"});
                Zygote.applyDebuggerSystemProperty(zygoteArguments);
                Zygote.applyInvokeWithSystemProperty(zygoteArguments);
                if (Zygote.nativeSupportsTaggedPointers()) {
                    zygoteArguments.mRuntimeFlags |= 524288;
                }
                zygoteArguments.mRuntimeFlags |= 2097152;
                if (shouldProfileSystemServer()) {
                    zygoteArguments.mRuntimeFlags |= 16384;
                }
                if (Zygote.forkSystemServer(zygoteArguments.mUid, zygoteArguments.mGid, zygoteArguments.mGids, zygoteArguments.mRuntimeFlags, null, zygoteArguments.mPermittedCapabilities, zygoteArguments.mEffectiveCapabilities) != 0) {
                    return null;
                }
                if (hasSecondZygote(str)) {
                    waitForSecondaryZygote(str2);
                }
                zygoteServer.closeServerSocket();
                return handleSystemServerProcess(zygoteArguments);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        } catch (ErrnoException e2) {
            throw new RuntimeException("Failed to capget()", e2);
        }
    }

    private static long posixCapabilitiesAsBits(int... iArr) {
        long j = 0;
        for (int i : iArr) {
            if (i < 0 || i > OsConstants.CAP_LAST_CAP) {
                throw new IllegalArgumentException(String.valueOf(i));
            }
            j |= 1 << i;
        }
        return j;
    }

    public static void main(String[] strArr) {
        boolean z = false;
        try {
            Os.setpgid(0, 0);
            ZygoteServer zygoteServer = null;
            try {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                boolean zEquals = "1".equals(SystemProperties.get("sys.boot_completed"));
                TimingsTraceLog timingsTraceLog = new TimingsTraceLog(Process.is64Bit() ? "Zygote64Timing" : "Zygote32Timing", 16384L);
                timingsTraceLog.traceBegin("ZygoteInit");
                RuntimeInit.preForkInit();
                boolean z2 = false;
                String strSubstring = null;
                String strSubstring2 = Zygote.PRIMARY_SOCKET_NAME;
                for (int i = 1; i < strArr.length; i++) {
                    if ("start-system-server".equals(strArr[i])) {
                        z2 = true;
                    } else if ("--enable-lazy-preload".equals(strArr[i])) {
                        z = true;
                    } else if (strArr[i].startsWith("--abi-list=")) {
                        strSubstring = strArr[i].substring(11);
                    } else if (strArr[i].startsWith(SOCKET_NAME_ARG)) {
                        strSubstring2 = strArr[i].substring(14);
                    } else {
                        throw new RuntimeException("Unknown command line argument: " + strArr[i]);
                    }
                }
                boolean zEquals2 = strSubstring2.equals(Zygote.PRIMARY_SOCKET_NAME);
                if (!zEquals) {
                    if (zEquals2) {
                        FrameworkStatsLog.write(240, 17, jElapsedRealtime);
                    } else if (strSubstring2.equals(Zygote.SECONDARY_SOCKET_NAME)) {
                        FrameworkStatsLog.write(240, 18, jElapsedRealtime);
                    }
                }
                if (strSubstring == null) {
                    throw new RuntimeException("No ABI list supplied.");
                }
                if (!z) {
                    timingsTraceLog.traceBegin("ZygotePreload");
                    EventLog.writeEvent(3020, SystemClock.uptimeMillis());
                    preload(timingsTraceLog);
                    EventLog.writeEvent(3030, SystemClock.uptimeMillis());
                    timingsTraceLog.traceEnd();
                }
                timingsTraceLog.traceBegin("PostZygoteInitGC");
                gcAndFinalize();
                timingsTraceLog.traceEnd();
                timingsTraceLog.traceEnd();
                Zygote.initNativeState(zEquals2);
                ZygoteServer zygoteServer2 = new ZygoteServer(zEquals2);
                if (z2) {
                    try {
                        Runnable runnableForkSystemServer = forkSystemServer(strSubstring, strSubstring2, zygoteServer2);
                        if (runnableForkSystemServer != null) {
                            runnableForkSystemServer.run();
                            zygoteServer2.closeServerSocket();
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        zygoteServer = zygoteServer2;
                        try {
                            Log.e(TAG, "System zygote died with exception", th);
                            throw th;
                        } catch (Throwable th2) {
                            if (zygoteServer != null) {
                                zygoteServer.closeServerSocket();
                            }
                            throw th2;
                        }
                    }
                }
                Log.i(TAG, "Accepting command socket connections");
                Runnable runnableRunSelectLoop = zygoteServer2.runSelectLoop(strSubstring);
                zygoteServer2.closeServerSocket();
                if (runnableRunSelectLoop != null) {
                    runnableRunSelectLoop.run();
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (ErrnoException e) {
            throw new RuntimeException("Failed to setpgid(0,0)", e);
        }
    }

    private static boolean hasSecondZygote(String str) {
        return !SystemProperties.get("ro.product.cpu.abilist").equals(str);
    }

    private static void waitForSecondaryZygote(String str) {
        String str2 = Zygote.PRIMARY_SOCKET_NAME;
        if (Zygote.PRIMARY_SOCKET_NAME.equals(str)) {
            str2 = Zygote.SECONDARY_SOCKET_NAME;
        }
        ZygoteProcess.waitForConnectionToZygote(str2);
    }

    static boolean isPreloadComplete() {
        return sPreloadComplete;
    }

    private ZygoteInit() {
    }

    public static final Runnable zygoteInit(int i, long[] jArr, String[] strArr, ClassLoader classLoader) {
        Trace.traceBegin(64L, "ZygoteInit");
        RuntimeInit.redirectLogStreams();
        RuntimeInit.commonInit();
        nativeZygoteInit();
        return RuntimeInit.applicationInit(i, jArr, strArr, classLoader);
    }

    static final Runnable childZygoteInit(int i, String[] strArr, ClassLoader classLoader) {
        RuntimeInit.Arguments arguments = new RuntimeInit.Arguments(strArr);
        return RuntimeInit.findStaticMain(arguments.startClass, arguments.startArgs, classLoader);
    }
}
