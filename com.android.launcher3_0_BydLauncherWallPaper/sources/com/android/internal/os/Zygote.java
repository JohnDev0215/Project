package com.android.internal.os;

import android.content.pm.ApplicationInfo;
import android.net.Credentials;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.net.NetworkUtils;
import android.os.FactoryTest;
import android.os.Process;
import android.os.SystemProperties;
import android.os.Trace;
import android.provider.DeviceConfig;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import dalvik.annotation.optimization.FastNative;
import dalvik.system.ZygoteHooks;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class Zygote {
    private static final String ANDROID_SOCKET_PREFIX = "ANDROID_SOCKET_";
    public static final int API_ENFORCEMENT_POLICY_MASK = 12288;
    public static final String BIND_MOUNT_APP_DATA_DIRS = "--bind-mount-data-dirs";
    public static final String BIND_MOUNT_APP_STORAGE_DIRS = "--bind-mount-storage-dirs";
    public static final String CHILD_ZYGOTE_ABI_LIST_ARG = "--abi-list=";
    public static final String CHILD_ZYGOTE_SOCKET_NAME_ARG = "--zygote-socket=";
    public static final String CHILD_ZYGOTE_UID_RANGE_END = "--uid-range-end=";
    public static final String CHILD_ZYGOTE_UID_RANGE_START = "--uid-range-start=";
    public static final int DEBUG_ALWAYS_JIT = 64;
    public static final int DEBUG_ENABLE_ASSERT = 4;
    public static final int DEBUG_ENABLE_CHECKJNI = 2;
    public static final int DEBUG_ENABLE_JDWP = 1;
    public static final int DEBUG_ENABLE_JNI_LOGGING = 16;
    public static final int DEBUG_ENABLE_SAFEMODE = 8;
    public static final int DEBUG_GENERATE_DEBUG_INFO = 32;
    public static final int DEBUG_GENERATE_MINI_DEBUG_INFO = 2048;
    public static final int DEBUG_IGNORE_APP_SIGNAL_HANDLER = 131072;
    public static final int DEBUG_JAVA_DEBUGGABLE = 256;
    public static final int DEBUG_NATIVE_DEBUGGABLE = 128;
    public static final int DISABLE_TEST_API_ENFORCEMENT_POLICY = 262144;
    public static final int DISABLE_VERIFIER = 512;
    public static final int GWP_ASAN_LEVEL_ALWAYS = 4194304;
    public static final int GWP_ASAN_LEVEL_LOTTERY = 2097152;
    public static final int GWP_ASAN_LEVEL_MASK = 6291456;
    public static final int GWP_ASAN_LEVEL_NEVER = 0;
    public static final int MEMORY_TAG_LEVEL_ASYNC = 1048576;
    public static final int MEMORY_TAG_LEVEL_MASK = 1572864;
    public static final int MEMORY_TAG_LEVEL_SYNC = 1572864;
    public static final int MEMORY_TAG_LEVEL_TBI = 524288;
    public static final int MOUNT_EXTERNAL_ANDROID_WRITABLE = 8;
    public static final int MOUNT_EXTERNAL_DEFAULT = 1;
    public static final int MOUNT_EXTERNAL_FULL = 6;
    public static final int MOUNT_EXTERNAL_INSTALLER = 5;
    public static final int MOUNT_EXTERNAL_LEGACY = 4;
    public static final int MOUNT_EXTERNAL_NONE = 0;
    public static final int MOUNT_EXTERNAL_PASS_THROUGH = 7;
    public static final int MOUNT_EXTERNAL_READ = 2;
    public static final int MOUNT_EXTERNAL_WRITE = 3;
    public static final int ONLY_USE_SYSTEM_OAT_FILES = 1024;
    public static final String PKG_DATA_INFO_MAP = "--pkg-data-info-map";
    public static final String PRIMARY_SOCKET_NAME = "zygote";
    private static final int PRIORITY_MAX = -20;
    public static final int PROFILE_FROM_SHELL = 32768;
    public static final int PROFILE_SYSTEM_SERVER = 16384;
    public static final long PROPERTY_CHECK_INTERVAL = 60000;
    public static final String SECONDARY_SOCKET_NAME = "zygote_secondary";
    public static final int SOCKET_BUFFER_SIZE = 256;
    public static final String START_AS_TOP_APP_ARG = "--is-top-app";
    private static final String USAP_ERROR_PREFIX = "Invalid command to USAP: ";
    static final int USAP_MANAGEMENT_MESSAGE_BYTES = 8;
    public static final String USAP_POOL_PRIMARY_SOCKET_NAME = "usap_pool_primary";
    public static final String USAP_POOL_SECONDARY_SOCKET_NAME = "usap_pool_secondary";
    public static final int USE_APP_IMAGE_STARTUP_CACHE = 65536;
    public static final String WHITELISTED_DATA_INFO_MAP = "--whitelisted-data-info-map";
    public static final int API_ENFORCEMENT_POLICY_SHIFT = Integer.numberOfTrailingZeros(12288);
    static final int[][] INT_ARRAY_2D = (int[][]) Array.newInstance((Class<?>) int.class, 0, 0);

    protected static native void nativeAllowFileAcrossFork(String str);

    private static native void nativeBlockSigTerm();

    private static native void nativeBoostUsapPriority();

    private static native void nativeEmptyUsapPool();

    private static native int nativeForkAndSpecialize(int i, int i2, int[] iArr, int i3, int[][] iArr2, int i4, String str, String str2, int[] iArr3, int[] iArr4, boolean z, String str3, String str4, boolean z2, String[] strArr, String[] strArr2, boolean z3, boolean z4);

    private static native int nativeForkSystemServer(int i, int i2, int[] iArr, int i3, int[][] iArr2, long j, long j2);

    private static native int nativeForkUsap(int i, int i2, int[] iArr, boolean z);

    private static native int[] nativeGetUsapPipeFDs();

    private static native int nativeGetUsapPoolCount();

    private static native int nativeGetUsapPoolEventFD();

    protected static native void nativeInitNativeState(boolean z);

    protected static native void nativeInstallSeccompUidGidFilter(int i, int i2);

    @FastNative
    public static native int nativeParseSigChld(byte[] bArr, int i, int[] iArr);

    static native void nativePreApplicationInit();

    private static native boolean nativeRemoveUsapTableEntry(int i);

    private static native void nativeSpecializeAppProcess(int i, int i2, int[] iArr, int i3, int[][] iArr2, int i4, String str, String str2, boolean z, String str3, String str4, boolean z2, String[] strArr, String[] strArr2, boolean z3, boolean z4);

    public static native boolean nativeSupportsTaggedPointers();

    private static native void nativeUnblockSigTerm();

    private Zygote() {
    }

    private static boolean containsInetGid(int[] iArr) {
        for (int i : iArr) {
            if (i == 3003) {
                return true;
            }
        }
        return false;
    }

    static int forkAndSpecialize(int i, int i2, int[] iArr, int i3, int[][] iArr2, int i4, String str, String str2, int[] iArr3, int[] iArr4, boolean z, String str3, String str4, boolean z2, String[] strArr, String[] strArr2, boolean z3, boolean z4) {
        ZygoteHooks.preFork();
        int iNativeForkAndSpecialize = nativeForkAndSpecialize(i, i2, iArr, i3, iArr2, i4, str, str2, iArr3, iArr4, z, str3, str4, z2, strArr, strArr2, z3, z4);
        if (iNativeForkAndSpecialize == 0) {
            Trace.traceBegin(64L, "PostFork");
            if (iArr != null && iArr.length > 0) {
                NetworkUtils.setAllowNetworkingForProcess(containsInetGid(iArr));
            }
        }
        Thread.currentThread().setPriority(5);
        ZygoteHooks.postForkCommon();
        return iNativeForkAndSpecialize;
    }

    private static void specializeAppProcess(int i, int i2, int[] iArr, int i3, int[][] iArr2, int i4, String str, String str2, boolean z, String str3, String str4, boolean z2, String[] strArr, String[] strArr2, boolean z3, boolean z4) {
        nativeSpecializeAppProcess(i, i2, iArr, i3, iArr2, i4, str, str2, z, str3, str4, z2, strArr, strArr2, z3, z4);
        Trace.traceBegin(64L, "PostFork");
        Thread.currentThread().setPriority(5);
        ZygoteHooks.postForkCommon();
    }

    static int forkSystemServer(int i, int i2, int[] iArr, int i3, int[][] iArr2, long j, long j2) {
        ZygoteHooks.preFork();
        int iNativeForkSystemServer = nativeForkSystemServer(i, i2, iArr, i3, iArr2, j, j2);
        Thread.currentThread().setPriority(5);
        ZygoteHooks.postForkCommon();
        return iNativeForkSystemServer;
    }

    static void allowAppFilesAcrossFork(ApplicationInfo applicationInfo) {
        for (String str : applicationInfo.getAllApkPaths()) {
            nativeAllowFileAcrossFork(str);
        }
    }

    static void initNativeState(boolean z) {
        nativeInitNativeState(z);
    }

    public static String getConfigurationProperty(String str, String str2) {
        return SystemProperties.get(String.join(".", "persist.device_config", DeviceConfig.NAMESPACE_RUNTIME_NATIVE, str), str2);
    }

    static void emptyUsapPool() {
        nativeEmptyUsapPool();
    }

    public static boolean getConfigurationPropertyBoolean(String str, Boolean bool) {
        return SystemProperties.getBoolean(String.join(".", "persist.device_config", DeviceConfig.NAMESPACE_RUNTIME_NATIVE, str), bool.booleanValue());
    }

    static int getUsapPoolCount() {
        return nativeGetUsapPoolCount();
    }

    static FileDescriptor getUsapPoolEventFD() {
        FileDescriptor fileDescriptor = new FileDescriptor();
        fileDescriptor.setInt$(nativeGetUsapPoolEventFD());
        return fileDescriptor;
    }

    static Runnable forkUsap(LocalServerSocket localServerSocket, int[] iArr, boolean z) {
        try {
            FileDescriptor[] fileDescriptorArrPipe2 = Os.pipe2(OsConstants.O_CLOEXEC);
            if (nativeForkUsap(fileDescriptorArrPipe2[0].getInt$(), fileDescriptorArrPipe2[1].getInt$(), iArr, z) == 0) {
                IoUtils.closeQuietly(fileDescriptorArrPipe2[0]);
                return usapMain(localServerSocket, fileDescriptorArrPipe2[1]);
            }
            IoUtils.closeQuietly(fileDescriptorArrPipe2[1]);
            return null;
        } catch (ErrnoException e) {
            throw new IllegalStateException("Unable to create USAP pipe.", e);
        }
    }

    private static Runnable usapMain(LocalServerSocket localServerSocket, FileDescriptor fileDescriptor) {
        DataOutputStream dataOutputStream;
        Credentials peerCredentials;
        String[] argumentList;
        ZygoteArguments zygoteArguments;
        ByteArrayOutputStream byteArrayOutputStream;
        int iMyPid = Process.myPid();
        Process.setArgV0(Process.is64Bit() ? "usap64" : "usap32");
        boostUsapPriority();
        LocalSocket localSocketAccept = null;
        while (true) {
            try {
                localSocketAccept = localServerSocket.accept();
                blockSigTerm();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(localSocketAccept.getInputStream()));
                dataOutputStream = new DataOutputStream(localSocketAccept.getOutputStream());
                peerCredentials = localSocketAccept.getPeerCredentials();
                argumentList = readArgumentList(bufferedReader);
            } catch (Exception e) {
                e = e;
            }
            if (argumentList != null) {
                zygoteArguments = new ZygoteArguments(argumentList);
                validateUsapCommand(zygoteArguments);
                try {
                    break;
                } finally {
                    unblockSigTerm();
                }
            }
            try {
                Log.e("USAP", "Truncated command received.");
                IoUtils.closeQuietly(localSocketAccept);
                unblockSigTerm();
            } catch (Exception e2) {
                e = e2;
                Log.e("USAP", e.getMessage());
                IoUtils.closeQuietly(localSocketAccept);
                unblockSigTerm();
            }
            Log.e("USAP", e.getMessage());
            IoUtils.closeQuietly(localSocketAccept);
            unblockSigTerm();
        }
        setAppProcessName(zygoteArguments, "USAP");
        applyUidSecurityPolicy(zygoteArguments, peerCredentials);
        applyDebuggerSystemProperty(zygoteArguments);
        int[][] iArr = zygoteArguments.mRLimits != null ? (int[][]) zygoteArguments.mRLimits.toArray(INT_ARRAY_2D) : null;
        try {
            try {
                dataOutputStream.writeInt(iMyPid);
                IoUtils.closeQuietly(localSocketAccept);
                try {
                    Os.close(localServerSocket.getFileDescriptor());
                    try {
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream(8);
                            DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream);
                            dataOutputStream2.writeLong(iMyPid);
                            dataOutputStream2.flush();
                        } catch (Exception e3) {
                            e = e3;
                        } catch (Throwable th) {
                            th = th;
                            IoUtils.closeQuietly(fileDescriptor);
                            throw th;
                        }
                        try {
                            Os.write(fileDescriptor, byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
                            IoUtils.closeQuietly(fileDescriptor);
                            specializeAppProcess(zygoteArguments.mUid, zygoteArguments.mGid, zygoteArguments.mGids, zygoteArguments.mRuntimeFlags, iArr, zygoteArguments.mMountExternal, zygoteArguments.mSeInfo, zygoteArguments.mNiceName, zygoteArguments.mStartChildZygote, zygoteArguments.mInstructionSet, zygoteArguments.mAppDataDir, zygoteArguments.mIsTopApp, zygoteArguments.mPkgDataInfoList, zygoteArguments.mWhitelistedDataInfoList, zygoteArguments.mBindMountAppDataDirs, zygoteArguments.mBindMountAppStorageDirs);
                            Trace.traceEnd(64L);
                            return ZygoteInit.zygoteInit(zygoteArguments.mTargetSdkVersion, zygoteArguments.mDisabledCompatChanges, zygoteArguments.mRemainingArgs, null);
                        } catch (Exception e4) {
                            e = e4;
                            Log.e("USAP", String.format("Failed to write PID (%d) to pipe (%d): %s", Integer.valueOf(iMyPid), Integer.valueOf(fileDescriptor.getInt$()), e.getMessage()));
                            throw new RuntimeException(e);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        IoUtils.closeQuietly(fileDescriptor);
                        throw th;
                    }
                } catch (ErrnoException e5) {
                    Log.e("USAP", "Failed to close USAP pool socket");
                    throw new RuntimeException(e5);
                }
            } catch (IOException e6) {
                Log.e("USAP", "Failed to write response to session socket: " + e6.getMessage());
                throw new RuntimeException(e6);
            }
        } catch (Throwable th3) {
            IoUtils.closeQuietly(localSocketAccept);
            try {
                Os.close(localServerSocket.getFileDescriptor());
                throw th3;
            } catch (ErrnoException e7) {
                Log.e("USAP", "Failed to close USAP pool socket");
                throw new RuntimeException(e7);
            }
        }
    }

    private static void blockSigTerm() {
        nativeBlockSigTerm();
    }

    private static void unblockSigTerm() {
        nativeUnblockSigTerm();
    }

    private static void boostUsapPriority() {
        nativeBoostUsapPriority();
    }

    static void setAppProcessName(ZygoteArguments zygoteArguments, String str) {
        if (zygoteArguments.mNiceName != null) {
            Process.setArgV0(zygoteArguments.mNiceName);
        } else if (zygoteArguments.mPackageName != null) {
            Process.setArgV0(zygoteArguments.mPackageName);
        } else {
            Log.w(str, "Unable to set package name.");
        }
    }

    private static void validateUsapCommand(ZygoteArguments zygoteArguments) {
        if (zygoteArguments.mAbiListQuery) {
            throw new IllegalArgumentException("Invalid command to USAP: --query-abi-list");
        }
        if (zygoteArguments.mPidQuery) {
            throw new IllegalArgumentException("Invalid command to USAP: --get-pid");
        }
        if (zygoteArguments.mPreloadDefault) {
            throw new IllegalArgumentException("Invalid command to USAP: --preload-default");
        }
        if (zygoteArguments.mPreloadPackage != null) {
            throw new IllegalArgumentException("Invalid command to USAP: --preload-package");
        }
        if (zygoteArguments.mPreloadApp != null) {
            throw new IllegalArgumentException("Invalid command to USAP: --preload-app");
        }
        if (zygoteArguments.mStartChildZygote) {
            throw new IllegalArgumentException("Invalid command to USAP: --start-child-zygote");
        }
        if (zygoteArguments.mApiBlacklistExemptions != null) {
            throw new IllegalArgumentException("Invalid command to USAP: --set-api-blacklist-exemptions");
        }
        if (zygoteArguments.mHiddenApiAccessLogSampleRate != -1) {
            throw new IllegalArgumentException("Invalid command to USAP: --hidden-api-log-sampling-rate=");
        }
        if (zygoteArguments.mHiddenApiAccessStatslogSampleRate != -1) {
            throw new IllegalArgumentException("Invalid command to USAP: --hidden-api-statslog-sampling-rate=");
        }
        if (zygoteArguments.mInvokeWith != null) {
            throw new IllegalArgumentException("Invalid command to USAP: --invoke-with");
        }
        if (zygoteArguments.mPermittedCapabilities != 0 || zygoteArguments.mEffectiveCapabilities != 0) {
            throw new ZygoteSecurityException("Client may not specify capabilities: permitted=0x" + Long.toHexString(zygoteArguments.mPermittedCapabilities) + ", effective=0x" + Long.toHexString(zygoteArguments.mEffectiveCapabilities));
        }
    }

    static int[] getUsapPipeFDs() {
        return nativeGetUsapPipeFDs();
    }

    static boolean removeUsapTableEntry(int i) {
        return nativeRemoveUsapTableEntry(i);
    }

    static void applyUidSecurityPolicy(ZygoteArguments zygoteArguments, Credentials credentials) throws ZygoteSecurityException {
        if (credentials.getUid() == 1000) {
            if ((FactoryTest.getMode() == 0) && zygoteArguments.mUidSpecified && zygoteArguments.mUid < 1000) {
                throw new ZygoteSecurityException("System UID may not launch process with UID < 1000");
            }
        }
        if (!zygoteArguments.mUidSpecified) {
            zygoteArguments.mUid = credentials.getUid();
            zygoteArguments.mUidSpecified = true;
        }
        if (zygoteArguments.mGidSpecified) {
            return;
        }
        zygoteArguments.mGid = credentials.getGid();
        zygoteArguments.mGidSpecified = true;
    }

    static void applyDebuggerSystemProperty(ZygoteArguments zygoteArguments) {
        if (RoSystemProperties.DEBUGGABLE) {
            zygoteArguments.mRuntimeFlags |= 1;
        }
    }

    static void applyInvokeWithSecurityPolicy(ZygoteArguments zygoteArguments, Credentials credentials) throws ZygoteSecurityException {
        int uid = credentials.getUid();
        if (zygoteArguments.mInvokeWith != null && uid != 0 && (zygoteArguments.mRuntimeFlags & 1) == 0) {
            throw new ZygoteSecurityException("Peer is permitted to specify an explicit invoke-with wrapper command only for debuggable applications.");
        }
    }

    public static String getWrapProperty(String str) {
        String str2;
        if (str == null || str.isEmpty() || (str2 = SystemProperties.get("wrap." + str)) == null || str2.isEmpty()) {
            return null;
        }
        return str2;
    }

    static void applyInvokeWithSystemProperty(ZygoteArguments zygoteArguments) {
        if (zygoteArguments.mInvokeWith == null) {
            zygoteArguments.mInvokeWith = getWrapProperty(zygoteArguments.mNiceName);
        }
    }

    static String[] readArgumentList(BufferedReader bufferedReader) throws IOException {
        try {
            String line = bufferedReader.readLine();
            if (line == null) {
                return null;
            }
            int i = Integer.parseInt(line);
            if (i > 1024) {
                throw new IOException("Max arg count exceeded");
            }
            String[] strArr = new String[i];
            for (int i2 = 0; i2 < i; i2++) {
                strArr[i2] = bufferedReader.readLine();
                if (strArr[i2] == null) {
                    throw new IOException("Truncated request");
                }
            }
            return strArr;
        } catch (NumberFormatException unused) {
            Log.e("Zygote", "Invalid Zygote wire format: non-int at argc");
            throw new IOException("Invalid wire format");
        }
    }

    static LocalServerSocket createManagedSocketFromInitSocket(String str) {
        String str2 = ANDROID_SOCKET_PREFIX + str;
        try {
            int i = Integer.parseInt(System.getenv(str2));
            try {
                FileDescriptor fileDescriptor = new FileDescriptor();
                fileDescriptor.setInt$(i);
                return new LocalServerSocket(fileDescriptor);
            } catch (IOException e) {
                throw new RuntimeException("Error building socket from file descriptor: " + i, e);
            }
        } catch (RuntimeException e2) {
            throw new RuntimeException("Socket unset or invalid: " + str2, e2);
        }
    }

    private static void callPostForkSystemServerHooks(int i) {
        ZygoteHooks.postForkSystemServer(i);
    }

    private static void callPostForkChildHooks(int i, boolean z, boolean z2, String str) {
        ZygoteHooks.postForkChild(i, z, z2, str);
    }

    static void execShell(String str) {
        String[] strArr = {"/system/bin/sh", "-c", str};
        try {
            Os.execv(strArr[0], strArr);
        } catch (ErrnoException e) {
            throw new RuntimeException(e);
        }
    }

    static void appendQuotedShellArgs(StringBuilder sb, String[] strArr) {
        for (String str : strArr) {
            sb.append(" '").append(str.replace("'", "'\\''")).append("'");
        }
    }
}
