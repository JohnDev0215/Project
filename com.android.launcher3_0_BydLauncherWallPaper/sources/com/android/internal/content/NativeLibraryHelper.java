package com.android.internal.content;

import android.content.Context;
import android.content.pm.PackageParser;
import android.os.Build;
import android.os.IBinder;
import android.os.SELinux;
import android.os.ServiceManager;
import android.os.incremental.IIncrementalService;
import android.os.incremental.IncrementalManager;
import android.os.incremental.IncrementalStorage;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.ArraySet;
import android.util.Slog;
import dalvik.system.CloseGuard;
import dalvik.system.VMRuntime;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class NativeLibraryHelper {
    private static final int BITCODE_PRESENT = 1;
    public static final String CLEAR_ABI_OVERRIDE = "-";
    private static final boolean DEBUG_NATIVE = false;
    public static final String LIB64_DIR_NAME = "lib64";
    public static final String LIB_DIR_NAME = "lib";
    private static final String TAG = "NativeHelper";

    private static native int hasRenderscriptBitcode(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeClose(long j);

    private static native int nativeCopyNativeBinaries(long j, String str, String str2, boolean z, boolean z2);

    private static native int nativeFindSupportedAbi(long j, String[] strArr, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeOpenApk(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeOpenApkFd(FileDescriptor fileDescriptor, String str);

    private static native long nativeSumNativeBinaries(long j, String str, boolean z);

    public static class Handle implements Closeable {
        final long[] apkHandles;
        final String[] apkPaths;
        final boolean debuggable;
        final boolean extractNativeLibs;
        private volatile boolean mClosed;
        private final CloseGuard mGuard;
        final boolean multiArch;

        public static Handle create(File file) throws IOException {
            try {
                return create(PackageParser.parsePackageLite(file, 0));
            } catch (PackageParser.PackageParserException e) {
                throw new IOException("Failed to parse package: " + file, e);
            }
        }

        public static Handle create(PackageParser.PackageLite packageLite) throws IOException {
            return create(packageLite.getAllCodePaths(), packageLite.multiArch, packageLite.extractNativeLibs, packageLite.debuggable);
        }

        public static Handle create(List<String> list, boolean z, boolean z2, boolean z3) throws IOException {
            int size = list.size();
            String[] strArr = new String[size];
            long[] jArr = new long[size];
            for (int i = 0; i < size; i++) {
                String str = list.get(i);
                strArr[i] = str;
                jArr[i] = NativeLibraryHelper.nativeOpenApk(str);
                if (jArr[i] == 0) {
                    for (int i2 = 0; i2 < i; i2++) {
                        NativeLibraryHelper.nativeClose(jArr[i2]);
                    }
                    throw new IOException("Unable to open APK: " + str);
                }
            }
            return new Handle(strArr, jArr, z, z2, z3);
        }

        public static Handle createFd(PackageParser.PackageLite packageLite, FileDescriptor fileDescriptor) throws IOException {
            String str = packageLite.baseCodePath;
            long[] jArr = {NativeLibraryHelper.nativeOpenApkFd(fileDescriptor, str)};
            if (jArr[0] == 0) {
                throw new IOException("Unable to open APK " + str + " from fd " + fileDescriptor);
            }
            return new Handle(new String[]{str}, jArr, packageLite.multiArch, packageLite.extractNativeLibs, packageLite.debuggable);
        }

        Handle(String[] strArr, long[] jArr, boolean z, boolean z2, boolean z3) {
            CloseGuard closeGuard = CloseGuard.get();
            this.mGuard = closeGuard;
            this.apkPaths = strArr;
            this.apkHandles = jArr;
            this.multiArch = z;
            this.extractNativeLibs = z2;
            this.debuggable = z3;
            closeGuard.open("close");
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (long j : this.apkHandles) {
                NativeLibraryHelper.nativeClose(j);
            }
            this.mGuard.close();
            this.mClosed = true;
        }

        protected void finalize() throws Throwable {
            CloseGuard closeGuard = this.mGuard;
            if (closeGuard != null) {
                closeGuard.warnIfOpen();
            }
            try {
                if (!this.mClosed) {
                    close();
                }
            } finally {
                super.finalize();
            }
        }
    }

    private static long sumNativeBinaries(Handle handle, String str) {
        long jNativeSumNativeBinaries = 0;
        for (long j : handle.apkHandles) {
            jNativeSumNativeBinaries += nativeSumNativeBinaries(j, str, handle.debuggable);
        }
        return jNativeSumNativeBinaries;
    }

    public static int copyNativeBinaries(Handle handle, File file, String str) {
        for (long j : handle.apkHandles) {
            int iNativeCopyNativeBinaries = nativeCopyNativeBinaries(j, file.getPath(), str, handle.extractNativeLibs, handle.debuggable);
            if (iNativeCopyNativeBinaries != 1) {
                return iNativeCopyNativeBinaries;
            }
        }
        return 1;
    }

    public static int findSupportedAbi(Handle handle, String[] strArr) {
        int i = -114;
        for (long j : handle.apkHandles) {
            int iNativeFindSupportedAbi = nativeFindSupportedAbi(j, strArr, handle.debuggable);
            if (iNativeFindSupportedAbi != -114) {
                if (iNativeFindSupportedAbi != -113) {
                    if (iNativeFindSupportedAbi < 0) {
                        return iNativeFindSupportedAbi;
                    }
                    if (i < 0 || iNativeFindSupportedAbi < i) {
                        i = iNativeFindSupportedAbi;
                    }
                } else if (i < 0) {
                    i = -113;
                }
            }
        }
        return i;
    }

    public static void removeNativeBinariesLI(String str) {
        if (str == null) {
            return;
        }
        removeNativeBinariesFromDirLI(new File(str), false);
    }

    public static void removeNativeBinariesFromDirLI(File file, boolean z) {
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (int i = 0; i < fileArrListFiles.length; i++) {
                    if (fileArrListFiles[i].isDirectory()) {
                        removeNativeBinariesFromDirLI(fileArrListFiles[i], true);
                    } else if (!fileArrListFiles[i].delete()) {
                        Slog.w(TAG, "Could not delete native binary: " + fileArrListFiles[i].getPath());
                    }
                }
            }
            if (!z || file.delete()) {
                return;
            }
            Slog.w(TAG, "Could not delete native binary directory: " + file.getPath());
        }
    }

    public static void createNativeLibrarySubdir(File file) throws IOException {
        if (!file.isDirectory()) {
            file.delete();
            if (!file.mkdir()) {
                throw new IOException("Cannot create " + file.getPath());
            }
            try {
                Os.chmod(file.getPath(), OsConstants.S_IRWXU | OsConstants.S_IRGRP | OsConstants.S_IXGRP | OsConstants.S_IROTH | OsConstants.S_IXOTH);
                return;
            } catch (ErrnoException e) {
                throw new IOException("Cannot chmod native library directory " + file.getPath(), e);
            }
        }
        if (!SELinux.restorecon(file)) {
            throw new IOException("Cannot set SELinux context for " + file.getPath());
        }
    }

    private static long sumNativeBinariesForSupportedAbi(Handle handle, String[] strArr) {
        int iFindSupportedAbi = findSupportedAbi(handle, strArr);
        if (iFindSupportedAbi >= 0) {
            return sumNativeBinaries(handle, strArr[iFindSupportedAbi]);
        }
        return 0L;
    }

    public static int copyNativeBinariesForSupportedAbi(Handle handle, File file, String[] strArr, boolean z, boolean z2) throws IOException {
        int iFindSupportedAbi = findSupportedAbi(handle, strArr);
        if (iFindSupportedAbi < 0) {
            return iFindSupportedAbi;
        }
        String str = strArr[iFindSupportedAbi];
        File file2 = z ? new File(file, VMRuntime.getInstructionSet(str)) : file;
        if (z2) {
            int iIncrementalConfigureNativeBinariesForSupportedAbi = incrementalConfigureNativeBinariesForSupportedAbi(handle, file2, str);
            return iIncrementalConfigureNativeBinariesForSupportedAbi != 1 ? iIncrementalConfigureNativeBinariesForSupportedAbi : iFindSupportedAbi;
        }
        createNativeLibrarySubdir(file);
        if (file2 != file) {
            createNativeLibrarySubdir(file2);
        }
        int iCopyNativeBinaries = copyNativeBinaries(handle, file2, str);
        return iCopyNativeBinaries != 1 ? iCopyNativeBinaries : iFindSupportedAbi;
    }

    public static int copyNativeBinariesWithOverride(Handle handle, File file, String str, boolean z) {
        int iCopyNativeBinariesForSupportedAbi;
        int iCopyNativeBinariesForSupportedAbi2;
        try {
            if (handle.multiArch) {
                if (str != null && !CLEAR_ABI_OVERRIDE.equals(str)) {
                    Slog.w(TAG, "Ignoring abiOverride for multi arch application.");
                }
                if (Build.SUPPORTED_32_BIT_ABIS.length > 0 && (iCopyNativeBinariesForSupportedAbi2 = copyNativeBinariesForSupportedAbi(handle, file, Build.SUPPORTED_32_BIT_ABIS, true, z)) < 0 && iCopyNativeBinariesForSupportedAbi2 != -114 && iCopyNativeBinariesForSupportedAbi2 != -113) {
                    Slog.w(TAG, "Failure copying 32 bit native libraries; copyRet=" + iCopyNativeBinariesForSupportedAbi2);
                    return iCopyNativeBinariesForSupportedAbi2;
                }
                if (Build.SUPPORTED_64_BIT_ABIS.length > 0 && (iCopyNativeBinariesForSupportedAbi = copyNativeBinariesForSupportedAbi(handle, file, Build.SUPPORTED_64_BIT_ABIS, true, z)) < 0 && iCopyNativeBinariesForSupportedAbi != -114 && iCopyNativeBinariesForSupportedAbi != -113) {
                    Slog.w(TAG, "Failure copying 64 bit native libraries; copyRet=" + iCopyNativeBinariesForSupportedAbi);
                    return iCopyNativeBinariesForSupportedAbi;
                }
            } else {
                if (CLEAR_ABI_OVERRIDE.equals(str) || str == null) {
                    str = null;
                }
                String[] strArr = str != null ? new String[]{str} : Build.SUPPORTED_ABIS;
                if (Build.SUPPORTED_64_BIT_ABIS.length > 0 && str == null && hasRenderscriptBitcode(handle)) {
                    strArr = Build.SUPPORTED_32_BIT_ABIS;
                }
                int iCopyNativeBinariesForSupportedAbi3 = copyNativeBinariesForSupportedAbi(handle, file, strArr, true, z);
                if (iCopyNativeBinariesForSupportedAbi3 < 0 && iCopyNativeBinariesForSupportedAbi3 != -114) {
                    Slog.w(TAG, "Failure copying native libraries [errorCode=" + iCopyNativeBinariesForSupportedAbi3 + "]");
                    return iCopyNativeBinariesForSupportedAbi3;
                }
            }
            return 1;
        } catch (IOException e) {
            Slog.e(TAG, "Copying native libraries failed", e);
            return -110;
        }
    }

    public static long sumNativeBinariesWithOverride(Handle handle, String str) throws IOException {
        long jSumNativeBinariesForSupportedAbi;
        if (handle.multiArch) {
            if (str != null && !CLEAR_ABI_OVERRIDE.equals(str)) {
                Slog.w(TAG, "Ignoring abiOverride for multi arch application.");
            }
            jSumNativeBinariesForSupportedAbi = Build.SUPPORTED_32_BIT_ABIS.length > 0 ? 0 + sumNativeBinariesForSupportedAbi(handle, Build.SUPPORTED_32_BIT_ABIS) : 0L;
            if (Build.SUPPORTED_64_BIT_ABIS.length <= 0) {
                return jSumNativeBinariesForSupportedAbi;
            }
            jSumNativeBinariesForSupportedAbi = sumNativeBinariesForSupportedAbi(handle, Build.SUPPORTED_64_BIT_ABIS);
        } else {
            if (CLEAR_ABI_OVERRIDE.equals(str) || str == null) {
                str = null;
            }
            String[] strArr = str != null ? new String[]{str} : Build.SUPPORTED_ABIS;
            if (Build.SUPPORTED_64_BIT_ABIS.length > 0 && str == null && hasRenderscriptBitcode(handle)) {
                strArr = Build.SUPPORTED_32_BIT_ABIS;
            }
            jSumNativeBinariesForSupportedAbi = sumNativeBinariesForSupportedAbi(handle, strArr);
        }
        return jSumNativeBinariesForSupportedAbi + jSumNativeBinariesForSupportedAbi;
    }

    private static int incrementalConfigureNativeBinariesForSupportedAbi(Handle handle, File file, String str) {
        String[] strArr = handle.apkPaths;
        if (strArr == null || strArr.length == 0) {
            Slog.e(TAG, "No apks to extract native libraries from.");
            return -110;
        }
        IBinder service = ServiceManager.getService(Context.INCREMENTAL_SERVICE);
        if (service == null) {
            return -110;
        }
        IncrementalManager incrementalManager = new IncrementalManager(IIncrementalService.Stub.asInterface(service));
        File parentFile = new File(strArr[0]).getParentFile();
        IncrementalStorage incrementalStorageOpenStorage = incrementalManager.openStorage(parentFile.getAbsolutePath());
        if (incrementalStorageOpenStorage == null) {
            Slog.e(TAG, "Failed to find incremental storage");
            return -110;
        }
        String relativePath = getRelativePath(parentFile, file);
        if (relativePath == null) {
            return -110;
        }
        for (String str2 : strArr) {
            if (!incrementalStorageOpenStorage.configureNativeBinaries(str2, relativePath, str, handle.extractNativeLibs)) {
                return -110;
            }
        }
        return 1;
    }

    private static String getRelativePath(File file, File file2) {
        try {
            Path pathRelativize = file.toPath().relativize(file2.toPath());
            return pathRelativize.toString().isEmpty() ? "" : pathRelativize.toString();
        } catch (IllegalArgumentException unused) {
            Slog.e(TAG, "Failed to find relative path between: " + file.getAbsolutePath() + " and: " + file2.getAbsolutePath());
            return null;
        }
    }

    public static boolean hasRenderscriptBitcode(Handle handle) throws IOException {
        for (long j : handle.apkHandles) {
            int iHasRenderscriptBitcode = hasRenderscriptBitcode(j);
            if (iHasRenderscriptBitcode < 0) {
                throw new IOException("Error scanning APK, code: " + iHasRenderscriptBitcode);
            }
            if (iHasRenderscriptBitcode == 1) {
                return true;
            }
        }
        return false;
    }

    public static void waitForNativeBinariesExtraction(ArraySet<IncrementalStorage> arraySet) {
        for (int i = 0; i < arraySet.size(); i++) {
            arraySet.valueAtUnchecked(i).waitForNativeBinariesExtraction();
        }
    }
}
