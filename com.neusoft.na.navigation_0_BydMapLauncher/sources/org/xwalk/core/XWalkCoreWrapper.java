package org.xwalk.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import junit.framework.Assert;

/* JADX INFO: loaded from: classes4.dex */
class XWalkCoreWrapper {
    private static final String BRIDGE_PACKAGE = "org.xwalk.core.internal";
    private static final String TAG = "XWalkLib";
    private static final String WRAPPER_PACKAGE = "org.xwalk.core";
    private static final String XWALK_CORE_CLASSES_DEX = "classes.dex";
    private static XWalkCoreWrapper sInstance;
    private static XWalkCoreWrapper sProvisionalInstance;
    private int mApiVersion = 8;
    private Context mBridgeContext;
    private ClassLoader mBridgeLoader;
    private int mCoreStatus;
    private int mMinApiVersion;
    private Context mWrapperContext;
    private static LinkedList<String> sReservedActivities = new LinkedList<>();
    private static HashMap<String, LinkedList<ReservedAction>> sReservedActions = new HashMap<>();

    private static class ReservedAction {
        Object[] mArguments;
        Class<?> mClass;
        ReflectMethod mMethod;
        Object mObject;

        ReservedAction(Object obj) {
            this.mObject = obj;
        }

        ReservedAction(Class<?> cls) {
            this.mClass = cls;
        }

        ReservedAction(ReflectMethod reflectMethod) {
            this.mMethod = reflectMethod;
            if (reflectMethod.getArguments() != null) {
                this.mArguments = Arrays.copyOf(reflectMethod.getArguments(), reflectMethod.getArguments().length);
            }
        }
    }

    public static XWalkCoreWrapper getInstance() {
        return sInstance;
    }

    public static int getCoreStatus() {
        XWalkCoreWrapper xWalkCoreWrapper = sInstance;
        if (xWalkCoreWrapper != null) {
            return xWalkCoreWrapper.mCoreStatus;
        }
        XWalkCoreWrapper xWalkCoreWrapper2 = sProvisionalInstance;
        if (xWalkCoreWrapper2 == null) {
            return 0;
        }
        return xWalkCoreWrapper2.mCoreStatus;
    }

    public static void handlePreInit(String str) {
        if (sInstance != null) {
            return;
        }
        Log.d(TAG, "Pre init xwalk core in " + str);
        if (sReservedActions.containsKey(str)) {
            sReservedActions.remove(str);
        } else {
            sReservedActivities.add(str);
        }
        sReservedActions.put(str, new LinkedList<>());
    }

    public static void reserveReflectObject(Object obj) {
        String last = sReservedActivities.getLast();
        Log.d(TAG, "Reserve object " + obj.getClass() + " to " + last);
        sReservedActions.get(last).add(new ReservedAction(obj));
    }

    public static void reserveReflectClass(Class<?> cls) {
        String last = sReservedActivities.getLast();
        Log.d(TAG, "Reserve class " + cls.toString() + " to " + last);
        sReservedActions.get(last).add(new ReservedAction(cls));
    }

    public static void reserveReflectMethod(ReflectMethod reflectMethod) {
        String last = sReservedActivities.getLast();
        Log.d(TAG, "Reserve method " + reflectMethod.toString() + " to " + last);
        sReservedActions.get(last).add(new ReservedAction(reflectMethod));
    }

    public static void handlePostInit(String str) {
        Log.d(TAG, "Post init xwalk core in " + str);
        if (sReservedActions.containsKey(str)) {
            for (ReservedAction reservedAction : sReservedActions.get(str)) {
                if (reservedAction.mObject != null) {
                    Log.d(TAG, "Init reserved object: " + reservedAction.mObject.getClass());
                    new ReflectMethod(reservedAction.mObject, "reflectionInit", (Class<?>[]) new Class[0]).invoke(new Object[0]);
                } else if (reservedAction.mClass != null) {
                    Log.d(TAG, "Init reserved class: " + reservedAction.mClass.toString());
                    new ReflectMethod(reservedAction.mClass, "reflectionInit", (Class<?>[]) new Class[0]).invoke(new Object[0]);
                } else {
                    Log.d(TAG, "Call reserved method: " + reservedAction.mMethod.toString());
                    Object[] objArr = reservedAction.mArguments;
                    if (objArr != null) {
                        for (int i = 0; i < objArr.length; i++) {
                            Object obj = objArr[i];
                            if (obj instanceof ReflectMethod) {
                                objArr[i] = ((ReflectMethod) obj).invokeWithArguments();
                            }
                        }
                    }
                    reservedAction.mMethod.invoke(objArr);
                }
            }
            sReservedActions.remove(str);
            sReservedActivities.remove(str);
        }
    }

    public static void handleRuntimeError(RuntimeException runtimeException) {
        Log.e(TAG, "This API is incompatible with the Crosswalk runtime library");
        runtimeException.printStackTrace();
    }

    public static int attachXWalkCore() {
        Assert.assertFalse(sReservedActivities.isEmpty());
        Assert.assertNull(sInstance);
        Log.d(TAG, "Attach xwalk core");
        XWalkCoreWrapper xWalkCoreWrapper = new XWalkCoreWrapper(XWalkEnvironment.getApplicationContext(), 1);
        sProvisionalInstance = xWalkCoreWrapper;
        if (xWalkCoreWrapper.findEmbeddedCore()) {
            return sProvisionalInstance.mCoreStatus;
        }
        if (XWalkEnvironment.isDownloadMode()) {
            sProvisionalInstance.findDownloadedCore();
            return sProvisionalInstance.mCoreStatus;
        }
        if (XWalkEnvironment.is64bitDevice()) {
            if (!sProvisionalInstance.findSharedCore("org.xwalk.core") && !sProvisionalInstance.findSharedCore(XWalkLibraryInterface.XWALK_CORE64_PACKAGE) && XWalkEnvironment.isIaDevice()) {
                sProvisionalInstance.findSharedCore(XWalkLibraryInterface.XWALK_CORE64_IA_PACKAGE);
            }
        } else if (!sProvisionalInstance.findSharedCore("org.xwalk.core") && XWalkEnvironment.isIaDevice()) {
            sProvisionalInstance.findSharedCore(XWalkLibraryInterface.XWALK_CORE_IA_PACKAGE);
        }
        return sProvisionalInstance.mCoreStatus;
    }

    public static void dockXWalkCore() {
        Assert.assertNotNull(sProvisionalInstance);
        Assert.assertNull(sInstance);
        Log.d(TAG, "Dock xwalk core");
        XWalkCoreWrapper xWalkCoreWrapper = sProvisionalInstance;
        sInstance = xWalkCoreWrapper;
        sProvisionalInstance = null;
        xWalkCoreWrapper.initCoreBridge();
        sInstance.initXWalkView();
    }

    public static void initEmbeddedMode() {
        if (sInstance == null && sReservedActivities.isEmpty()) {
            Log.d(TAG, "Init embedded mode");
            XWalkCoreWrapper xWalkCoreWrapper = new XWalkCoreWrapper(null, -1);
            if (!xWalkCoreWrapper.findEmbeddedCore()) {
                throw new RuntimeException("Please have your activity extend XWalkActivity for shared mode");
            }
            sInstance = xWalkCoreWrapper;
            xWalkCoreWrapper.initCoreBridge();
        }
    }

    private XWalkCoreWrapper(Context context, int i) {
        this.mMinApiVersion = (i <= 0 || i > 8) ? 8 : i;
        this.mCoreStatus = 0;
        this.mWrapperContext = context;
    }

    private void initCoreBridge() {
        Log.d(TAG, "Init core bridge");
        new ReflectMethod(getBridgeClass("XWalkCoreBridge"), "init", (Class<?>[]) new Class[]{Context.class, Object.class}).invoke(this.mBridgeContext, this);
    }

    private void initXWalkView() {
        Log.d(TAG, "Init xwalk view");
        new ReflectMethod(getBridgeClass("XWalkViewDelegate"), "init", (Class<?>[]) new Class[]{Context.class, Context.class}).invoke(this.mBridgeContext, this.mWrapperContext);
    }

    private boolean findEmbeddedCore() {
        this.mBridgeContext = null;
        this.mBridgeLoader = XWalkCoreWrapper.class.getClassLoader();
        if (!checkCoreVersion() || !checkCoreArchitecture()) {
            this.mBridgeLoader = null;
            return false;
        }
        Log.d(TAG, "Running in embedded mode");
        this.mCoreStatus = 1;
        return true;
    }

    private boolean findSharedCore(String str) {
        if (!checkCorePackage(str)) {
            return false;
        }
        this.mBridgeLoader = this.mBridgeContext.getClassLoader();
        if (!checkCoreVersion() || !checkCoreArchitecture()) {
            this.mBridgeContext = null;
            this.mBridgeLoader = null;
            return false;
        }
        Log.d(TAG, "Running in shared mode");
        this.mCoreStatus = 1;
        return true;
    }

    private boolean findDownloadedCore() {
        String extractedCoreDir = XWalkEnvironment.getExtractedCoreDir();
        this.mBridgeLoader = new DexClassLoader(extractedCoreDir + File.separator + XWALK_CORE_CLASSES_DEX, XWalkEnvironment.getOptimizedDexDir(), extractedCoreDir, ClassLoader.getSystemClassLoader());
        if (!checkCoreVersion() || !checkCoreArchitecture()) {
            this.mBridgeLoader = null;
            return false;
        }
        Log.d(TAG, "Running in downloaded mode");
        this.mCoreStatus = 1;
        return true;
    }

    private boolean checkCoreVersion() {
        Log.d(TAG, "[Environment] SDK:" + Build.VERSION.SDK_INT);
        Log.d(TAG, "[App Version] build:23.53.589.4, api:" + this.mApiVersion + ", min_api:" + this.mMinApiVersion);
        try {
            Class<?> bridgeClass = getBridgeClass("XWalkCoreVersion");
            String str = "";
            try {
                str = (String) new ReflectField(bridgeClass, "XWALK_BUILD_VERSION").get();
            } catch (RuntimeException unused) {
            }
            int iIntValue = ((Integer) new ReflectField(bridgeClass, "API_VERSION").get()).intValue();
            int iIntValue2 = ((Integer) new ReflectField(bridgeClass, "MIN_API_VERSION").get()).intValue();
            Log.d(TAG, "[Lib Version] build:" + str + ", api:" + iIntValue + ", min_api:" + iIntValue2);
            if (XWalkEnvironment.isDownloadMode() && XWalkEnvironment.isDownloadModeUpdate() && !str.isEmpty() && !str.equals(XWalkAppVersion.XWALK_BUILD_VERSION)) {
                this.mCoreStatus = 8;
                return false;
            }
            if (this.mMinApiVersion > iIntValue) {
                this.mCoreStatus = 3;
                return false;
            }
            if (this.mApiVersion < iIntValue2) {
                this.mCoreStatus = 4;
                return false;
            }
            Log.d(TAG, "XWalk core version matched");
            return true;
        } catch (RuntimeException unused2) {
            Log.d(TAG, "XWalk core not found");
            this.mCoreStatus = 2;
            return false;
        }
    }

    private boolean checkCoreArchitecture() {
        boolean zBooleanValue;
        try {
            ReflectMethod reflectMethod = new ReflectMethod(getBridgeClass("XWalkViewDelegate"), "loadXWalkLibrary", (Class<?>[]) new Class[]{Context.class, String.class});
            Context context = this.mBridgeContext;
            if (context != null) {
                zBooleanValue = ((Boolean) reflectMethod.invoke(this.mBridgeContext, null)).booleanValue();
            } else {
                try {
                    zBooleanValue = ((Boolean) reflectMethod.invoke(context, null)).booleanValue();
                } catch (RuntimeException e) {
                    Log.d(TAG, e.getLocalizedMessage());
                    zBooleanValue = false;
                }
                if (!zBooleanValue && this.mWrapperContext != null) {
                    zBooleanValue = ((Boolean) reflectMethod.invoke(this.mBridgeContext, XWalkEnvironment.getPrivateDataDir())).booleanValue();
                }
            }
            if (!zBooleanValue) {
                Log.d(TAG, "Mismatch of CPU architecture");
                this.mCoreStatus = 6;
                return false;
            }
            Log.d(TAG, "XWalk core architecture matched");
            return true;
        } catch (RuntimeException e2) {
            Log.d(TAG, e2.getLocalizedMessage());
            if (e2.getCause() instanceof UnsatisfiedLinkError) {
                this.mCoreStatus = 6;
                return false;
            }
            this.mCoreStatus = 5;
            return false;
        }
    }

    private boolean checkCorePackage(String str) {
        try {
            if (!verifyPackageInfo(this.mWrapperContext.getPackageManager().getPackageInfo(str, 64), "SHA-256", XWalkAppVersion.XWALK_APK_HASH_CODE)) {
                Log.d(TAG, str + " signature verification failed");
                this.mCoreStatus = 7;
                return false;
            }
            try {
                this.mBridgeContext = this.mWrapperContext.createPackageContext(str, 3);
                Log.d(TAG, "Created package context for " + str);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.d(TAG, str + " not found");
                return false;
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            Log.d(TAG, str + " not found");
            return false;
        }
    }

    private boolean verifyPackageInfo(PackageInfo packageInfo, String str, String str2) {
        if (packageInfo.signatures == null) {
            Log.e(TAG, "No signature in package info");
            return false;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            byte[] bArrHexStringToByteArray = hexStringToByteArray(str2);
            if (bArrHexStringToByteArray == null) {
                throw new IllegalArgumentException("Invalid hash code");
            }
            for (int i = 0; i < packageInfo.signatures.length; i++) {
                Log.d(TAG, "Checking signature " + i);
                if (!MessageDigest.isEqual(messageDigest.digest(packageInfo.signatures[i].toByteArray()), bArrHexStringToByteArray)) {
                    Log.e(TAG, "Hash code does not match");
                } else {
                    Log.d(TAG, "Signature passed verification");
                    return true;
                }
            }
            return false;
        } catch (NullPointerException | NoSuchAlgorithmException unused) {
            throw new IllegalArgumentException("Invalid hash algorithm");
        }
    }

    private byte[] hexStringToByteArray(String str) {
        if (str == null || str.isEmpty() || str.length() % 2 != 0) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length(); i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public boolean isSharedMode() {
        return this.mBridgeContext != null;
    }

    public Object getBridgeObject(Object obj) {
        try {
            return new ReflectMethod(obj, "getBridge", (Class<?>[]) new Class[0]).invoke(new Object[0]);
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public Object getWrapperObject(Object obj) {
        try {
            return new ReflectMethod(obj, "getWrapper", (Class<?>[]) new Class[0]).invoke(new Object[0]);
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public Class<?> getBridgeClass(String str) {
        try {
            return this.mBridgeLoader.loadClass("org.xwalk.core.internal." + str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
