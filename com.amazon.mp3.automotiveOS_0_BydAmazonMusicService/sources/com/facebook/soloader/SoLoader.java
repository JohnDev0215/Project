package com.facebook.soloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.facebook.soloader.SysUtil;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes6.dex */
@ThreadSafe
public class SoLoader {

    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static ApplicationSoSource sApplicationSoSource;

    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static UnpackingSoSource[] sBackupSoSources;

    @GuardedBy("sSoSourcesLock")
    private static int sFlags;

    @Nullable
    static SoFileLoader sSoFileLoader;
    private static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();

    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static volatile SoSource[] sSoSources = null;

    @GuardedBy("sSoSourcesLock")
    private static final AtomicInteger sSoSourcesVersion = new AtomicInteger(0);

    @GuardedBy("SoLoader.class")
    private static final HashSet<String> sLoadedLibraries = new HashSet<>();

    @GuardedBy("SoLoader.class")
    private static final Map<String, Object> sLoadingLibraries = new HashMap();
    private static final Set<String> sLoadedAndMergedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());

    @Nullable
    private static SystemLoadLibraryWrapper sSystemLoadLibraryWrapper = null;
    private static final String[] DEFAULT_DENY_LIST = {System.mapLibraryName("breakpad")};
    private static int sAppType = 0;
    static final boolean SYSTRACE_LIBRARY_LOADING = true;

    public static void init(Context context, int i) throws IOException {
        init(context, i, null, DEFAULT_DENY_LIST);
    }

    public static void init(Context context, int i, @Nullable SoFileLoader soFileLoader, String[] strArr) throws IOException {
        if (isInitialized()) {
            return;
        }
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            int appType = getAppType(context, i);
            sAppType = appType;
            if ((i & 128) == 0 && SysUtil.isSupportedDirectLoad(context, appType)) {
                i |= 72;
            }
            initSoLoader(soFileLoader);
            initSoSources(context, i, strArr);
            NativeLoader.initIfUninitialized(new NativeLoaderToSoLoaderDelegate());
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
        }
    }

    public static void init(Context context, boolean z) {
        try {
            init(context, z ? 1 : 0, null, DEFAULT_DENY_LIST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initSoSources(Context context, int i, String[] strArr) throws IOException {
        if (sSoSources != null) {
            return;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.writeLock().lock();
        if (sSoSources != null) {
            reentrantReadWriteLock.writeLock().unlock();
            return;
        }
        try {
            sFlags = i;
            ArrayList arrayList = new ArrayList();
            AddSystemLibSoSource(arrayList, strArr);
            if (context != null) {
                if ((i & 1) != 0) {
                    sBackupSoSources = null;
                    if (Log.isLoggable("SoLoader", 3)) {
                        Log.d("SoLoader", "adding exo package source: lib-main");
                    }
                    arrayList.add(0, new ExoSoSource(context, "lib-main"));
                } else {
                    if ((i & 64) != 0) {
                        addDirectApkSoSource(context, arrayList);
                    }
                    addApplicationSoSource(context, arrayList, getApplicationSoSourceFlags());
                    AddBackupSoSource(context, arrayList, 1);
                }
            }
            SoSource[] soSourceArr = (SoSource[]) arrayList.toArray(new SoSource[arrayList.size()]);
            int iMakePrepareFlags = makePrepareFlags();
            int length = soSourceArr.length;
            while (true) {
                int i2 = length - 1;
                if (length <= 0) {
                    break;
                }
                if (Log.isLoggable("SoLoader", 3)) {
                    Log.d("SoLoader", "Preparing SO source: " + soSourceArr[i2]);
                }
                boolean z = SYSTRACE_LIBRARY_LOADING;
                if (z) {
                    Api18TraceUtils.beginTraceSection("SoLoader", Attributes.PREDEFINED_ATTRIBUTE_PREFIX, soSourceArr[i2].getClass().getSimpleName());
                }
                soSourceArr[i2].prepare(iMakePrepareFlags);
                if (z) {
                    Api18TraceUtils.endSection();
                }
                length = i2;
            }
            sSoSources = soSourceArr;
            sSoSourcesVersion.getAndIncrement();
            if (Log.isLoggable("SoLoader", 3)) {
                Log.d("SoLoader", "init finish: " + sSoSources.length + " SO sources prepared");
            }
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    private static int getApplicationSoSourceFlags() {
        int i = sAppType;
        if (i == 1) {
            return 0;
        }
        if (i == 2 || i == 3) {
            return 1;
        }
        throw new RuntimeException("Unsupported app type, we should not reach here");
    }

    private static void addDirectApkSoSource(Context context, ArrayList<SoSource> arrayList) {
        if (context.getApplicationInfo().splitSourceDirs != null) {
            if (Log.isLoggable("SoLoader", 3)) {
                Log.d("SoLoader", "adding directApk sources from split apks");
            }
            for (String str : context.getApplicationInfo().splitSourceDirs) {
                DirectApkSoSource directApkSoSource = new DirectApkSoSource(new File(str));
                if (Log.isLoggable("SoLoader", 3)) {
                    Log.d("SoLoader", "adding directApk source: " + directApkSoSource.toString());
                }
                arrayList.add(0, directApkSoSource);
            }
        }
        DirectApkSoSource directApkSoSource2 = new DirectApkSoSource(context);
        if (Log.isLoggable("SoLoader", 3)) {
            Log.d("SoLoader", "adding directApk source: " + directApkSoSource2.toString());
        }
        arrayList.add(0, directApkSoSource2);
    }

    private static void addApplicationSoSource(Context context, ArrayList<SoSource> arrayList, int i) {
        sApplicationSoSource = new ApplicationSoSource(context, i);
        if (Log.isLoggable("SoLoader", 3)) {
            Log.d("SoLoader", "adding application source: " + sApplicationSoSource.toString());
        }
        arrayList.add(0, sApplicationSoSource);
    }

    private static void AddBackupSoSource(Context context, ArrayList<SoSource> arrayList, int i) throws IOException {
        if ((sFlags & 8) != 0) {
            sBackupSoSources = null;
            File soStorePath = UnpackingSoSource.getSoStorePath(context, "lib-main");
            try {
                SysUtil.dumbDeleteRecursive(soStorePath);
                return;
            } catch (IOException e) {
                Log.w("SoLoader", "Failed to delete " + soStorePath.getCanonicalPath(), e);
                return;
            }
        }
        File file = new File(context.getApplicationInfo().sourceDir);
        ArrayList arrayList2 = new ArrayList();
        ApkSoSource apkSoSource = new ApkSoSource(context, file, "lib-main", i);
        arrayList2.add(apkSoSource);
        if (Log.isLoggable("SoLoader", 3)) {
            Log.d("SoLoader", "adding backup source from : " + apkSoSource.toString());
        }
        addBackupSoSourceFromSplitApk(context, i, arrayList2);
        sBackupSoSources = (UnpackingSoSource[]) arrayList2.toArray(new UnpackingSoSource[arrayList2.size()]);
        arrayList.addAll(0, arrayList2);
    }

    private static void addBackupSoSourceFromSplitApk(Context context, int i, ArrayList<UnpackingSoSource> arrayList) {
        if (context.getApplicationInfo().splitSourceDirs != null) {
            if (Log.isLoggable("SoLoader", 3)) {
                Log.d("SoLoader", "adding backup sources from split apks");
            }
            String[] strArr = context.getApplicationInfo().splitSourceDirs;
            int length = strArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                File file = new File(strArr[i2]);
                StringBuilder sb = new StringBuilder();
                sb.append("lib-");
                int i4 = i3 + 1;
                sb.append(i3);
                ApkSoSource apkSoSource = new ApkSoSource(context, file, sb.toString(), i);
                if (Log.isLoggable("SoLoader", 3)) {
                    Log.d("SoLoader", "adding backup source: " + apkSoSource.toString());
                }
                arrayList.add(apkSoSource);
                i2++;
                i3 = i4;
            }
        }
    }

    private static void AddSystemLibSoSource(ArrayList<SoSource> arrayList, String[] strArr) {
        String str = SysUtil.is64Bit() ? "/system/lib64:/vendor/lib64" : "/system/lib:/vendor/lib";
        String str2 = System.getenv("LD_LIBRARY_PATH");
        if (str2 != null && !str2.equals("")) {
            str = str + ":" + str2;
        }
        for (String str3 : new HashSet(Arrays.asList(str.split(":")))) {
            if (Log.isLoggable("SoLoader", 3)) {
                Log.d("SoLoader", "adding system library source: " + str3);
            }
            arrayList.add(new DirectorySoSource(new File(str3), 2, strArr));
        }
    }

    private static int makePrepareFlags() {
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.writeLock().lock();
        try {
            int i = (sFlags & 2) != 0 ? 1 : 0;
            reentrantReadWriteLock.writeLock().unlock();
            return i;
        } catch (Throwable th) {
            sSoSourcesLock.writeLock().unlock();
            throw th;
        }
    }

    private static synchronized void initSoLoader(@Nullable SoFileLoader soFileLoader) {
        if (soFileLoader == null) {
            if (sSoFileLoader != null) {
                return;
            }
        }
        if (soFileLoader != null) {
            sSoFileLoader = soFileLoader;
            return;
        }
        final Runtime runtime = Runtime.getRuntime();
        final Method nativeLoadRuntimeMethod = getNativeLoadRuntimeMethod();
        final boolean z = nativeLoadRuntimeMethod != null;
        final String classLoaderLdLoadLibrary = z ? SysUtil.Api14Utils.getClassLoaderLdLoadLibrary() : null;
        final String strMakeNonZipPath = makeNonZipPath(classLoaderLdLoadLibrary);
        sSoFileLoader = new SoFileLoader() { // from class: com.facebook.soloader.SoLoader.1
            @Override // com.facebook.soloader.SoFileLoader
            public void loadBytes(String str, ElfByteChannel elfByteChannel, int i) {
                throw new UnsupportedOperationException();
            }

            /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
            
                if (r1 == null) goto L56;
             */
            /* JADX WARN: Code restructure failed: missing block: B:18:0x0037, code lost:
            
                android.util.Log.e("SoLoader", "Error when loading lib: " + r1 + " lib hash: " + getLibHash(r9) + " search path is " + r10);
             */
            /* JADX WARN: Code restructure failed: missing block: B:56:?, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:57:?, code lost:
            
                return;
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:45:0x009e  */
            /* JADX WARN: Type inference failed for: r1v0 */
            /* JADX WARN: Type inference failed for: r1v2 */
            @Override // com.facebook.soloader.SoFileLoader
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void load(java.lang.String r9, int r10) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 205
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.AnonymousClass1.load(java.lang.String, int):void");
            }

            private String getLibHash(String str) {
                try {
                    File file = new File(str);
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int i = fileInputStream.read(bArr);
                            if (i > 0) {
                                messageDigest.update(bArr, 0, i);
                            } else {
                                String str2 = String.format("%32x", new BigInteger(1, messageDigest.digest()));
                                fileInputStream.close();
                                return str2;
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (IOException | SecurityException | NoSuchAlgorithmException e) {
                    return e.toString();
                }
            }
        };
    }

    @Nullable
    private static Method getNativeLoadRuntimeMethod() {
        if (Build.VERSION.SDK_INT > 27) {
            return null;
        }
        try {
            Method declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", String.class, ClassLoader.class, String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException | SecurityException e) {
            Log.w("SoLoader", "Cannot get nativeLoad method", e);
            return null;
        }
    }

    private static int getAppType(Context context, int i) {
        int i2 = sAppType;
        if (i2 != 0) {
            return i2;
        }
        if ((i & 32) == 0 && context != null) {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            int i3 = applicationInfo.flags;
            i = (i3 & 1) != 0 ? (i3 & 128) != 0 ? 3 : 2 : 1;
            if (Log.isLoggable("SoLoader", 3)) {
                Log.d("SoLoader", "ApplicationInfo.flags is: " + applicationInfo.flags + " appType is: " + i);
            }
        }
        return i;
    }

    public static final class WrongAbiError extends UnsatisfiedLinkError {
        WrongAbiError(Throwable th, String str) {
            super("APK was built for a different platform. Supported ABIs: " + Arrays.toString(SysUtil.getSupportedAbis()) + " error: " + str);
            initCause(th);
        }
    }

    public static boolean loadLibrary(String str) {
        return loadLibrary(str, 0);
    }

    public static boolean loadLibrary(String str, int i) throws UnsatisfiedLinkError {
        SystemLoadLibraryWrapper systemLoadLibraryWrapper;
        Boolean boolLoadLibraryOnNonAndroid = loadLibraryOnNonAndroid(str);
        if (boolLoadLibraryOnNonAndroid != null) {
            return boolLoadLibraryOnNonAndroid.booleanValue();
        }
        int i2 = sAppType;
        if ((i2 == 2 || i2 == 3) && (systemLoadLibraryWrapper = sSystemLoadLibraryWrapper) != null) {
            systemLoadLibraryWrapper.loadLibrary(str);
            return true;
        }
        String strMapLibName = MergedSoMapping.mapLibName(str);
        return loadLibraryBySoName(System.mapLibraryName(strMapLibName != null ? strMapLibName : str), str, strMapLibName, i, null);
    }

    @Nullable
    private static Boolean loadLibraryOnNonAndroid(String str) {
        Boolean boolValueOf;
        if (sSoSources != null) {
            return null;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    assertInitialized();
                } else {
                    synchronized (SoLoader.class) {
                        boolean z = !sLoadedLibraries.contains(str);
                        if (z) {
                            SystemLoadLibraryWrapper systemLoadLibraryWrapper = sSystemLoadLibraryWrapper;
                            if (systemLoadLibraryWrapper != null) {
                                systemLoadLibraryWrapper.loadLibrary(str);
                            } else {
                                System.loadLibrary(str);
                            }
                        }
                        boolValueOf = Boolean.valueOf(z);
                    }
                    reentrantReadWriteLock.readLock().unlock();
                    return boolValueOf;
                }
            }
            reentrantReadWriteLock.readLock().unlock();
            return null;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    static void loadLibraryBySoName(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        loadLibraryBySoNameImpl(str, null, null, i, threadPolicy);
    }

    private static boolean loadLibraryBySoName(String str, @Nullable String str2, @Nullable String str3, int i, @Nullable StrictMode.ThreadPolicy threadPolicy) {
        boolean z;
        boolean zLoadLibraryBySoNameImpl = false;
        do {
            try {
                zLoadLibraryBySoNameImpl = loadLibraryBySoNameImpl(str, str2, str3, i, threadPolicy);
                z = false;
            } catch (UnsatisfiedLinkError e) {
                int i2 = sSoSourcesVersion.get();
                sSoSourcesLock.writeLock().lock();
                try {
                    try {
                        if (sApplicationSoSource == null || !sApplicationSoSource.checkAndMaybeUpdate()) {
                            z = false;
                        } else {
                            Log.w("SoLoader", "sApplicationSoSource updated during load: " + str + ", attempting load again.");
                            sSoSourcesVersion.getAndIncrement();
                            z = true;
                        }
                        sSoSourcesLock.writeLock().unlock();
                        if (sSoSourcesVersion.get() == i2) {
                            throw e;
                        }
                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }
                } catch (Throwable th) {
                    sSoSourcesLock.writeLock().unlock();
                    throw th;
                }
            }
        } while (z);
        return zLoadLibraryBySoNameImpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00c9 A[Catch: all -> 0x0159, PHI: r3
  0x00c9: PHI (r3v5 boolean) = (r3v2 boolean), (r3v3 boolean), (r3v3 boolean) binds: [B:23:0x0045, B:34:0x005c, B:45:0x00a2] A[DONT_GENERATE, DONT_INLINE], TryCatch #5 {, blocks: (B:24:0x0047, B:29:0x0051, B:35:0x005e, B:37:0x0066, B:38:0x007c, B:39:0x007f, B:41:0x0087, B:42:0x009d, B:48:0x00a5, B:50:0x00a7, B:52:0x00ad, B:54:0x00b5, B:55:0x00c4, B:56:0x00c5, B:59:0x00c8, B:60:0x00c9, B:62:0x00cd, B:64:0x00d3, B:69:0x00e0, B:71:0x00e4, B:77:0x011b, B:84:0x0146, B:86:0x014a, B:87:0x014d, B:88:0x014e, B:43:0x009e, B:44:0x00a1, B:25:0x0048, B:28:0x0050, B:33:0x005b, B:72:0x00eb, B:74:0x00f3, B:75:0x0111, B:82:0x0122, B:83:0x0145), top: B:108:0x0047, outer: #6, inners: #1, #2, #3, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00cd A[Catch: all -> 0x0159, TryCatch #5 {, blocks: (B:24:0x0047, B:29:0x0051, B:35:0x005e, B:37:0x0066, B:38:0x007c, B:39:0x007f, B:41:0x0087, B:42:0x009d, B:48:0x00a5, B:50:0x00a7, B:52:0x00ad, B:54:0x00b5, B:55:0x00c4, B:56:0x00c5, B:59:0x00c8, B:60:0x00c9, B:62:0x00cd, B:64:0x00d3, B:69:0x00e0, B:71:0x00e4, B:77:0x011b, B:84:0x0146, B:86:0x014a, B:87:0x014d, B:88:0x014e, B:43:0x009e, B:44:0x00a1, B:25:0x0048, B:28:0x0050, B:33:0x005b, B:72:0x00eb, B:74:0x00f3, B:75:0x0111, B:82:0x0122, B:83:0x0145), top: B:108:0x0047, outer: #6, inners: #1, #2, #3, #7 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean loadLibraryBySoNameImpl(java.lang.String r11, @javax.annotation.Nullable java.lang.String r12, @javax.annotation.Nullable java.lang.String r13, int r14, @javax.annotation.Nullable android.os.StrictMode.ThreadPolicy r15) {
        /*
            Method dump skipped, instruction units count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.loadLibraryBySoNameImpl(java.lang.String, java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }

    public static File unpackLibraryAndDependencies(String str) throws UnsatisfiedLinkError {
        assertInitialized();
        try {
            return unpackLibraryBySoName(System.mapLibraryName(str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0053, code lost:
    
        if (android.util.Log.isLoggable("SoLoader", 3) == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0055, code lost:
    
        android.util.Log.d("SoLoader", "Trying backup SoSource for " + r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0069, code lost:
    
        r8 = com.facebook.soloader.SoLoader.sBackupSoSources;
        r9 = r8.length;
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006d, code lost:
    
        if (r10 >= r9) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006f, code lost:
    
        r11 = r8[r10];
        r11.prepare(r12);
        r11 = r11.loadLibrary(r12, r13, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0078, code lost:
    
        if (r11 != 1) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007a, code lost:
    
        r1 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007c, code lost:
    
        r10 = r10 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void doLoadLibraryBySoName(java.lang.String r12, int r13, @javax.annotation.Nullable android.os.StrictMode.ThreadPolicy r14) throws java.lang.UnsatisfiedLinkError {
        /*
            Method dump skipped, instruction units count: 407
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.doLoadLibraryBySoName(java.lang.String, int, android.os.StrictMode$ThreadPolicy):void");
    }

    @Nullable
    public static String makeNonZipPath(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(":");
        ArrayList arrayList = new ArrayList(strArrSplit.length);
        for (String str2 : strArrSplit) {
            if (!str2.contains("!")) {
                arrayList.add(str2);
            }
        }
        return TextUtils.join(":", arrayList);
    }

    static File unpackLibraryBySoName(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            for (SoSource soSource : sSoSources) {
                File fileUnpackLibrary = soSource.unpackLibrary(str);
                if (fileUnpackLibrary != null) {
                    return fileUnpackLibrary;
                }
            }
            sSoSourcesLock.readLock().unlock();
            throw new FileNotFoundException(str);
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    private static void assertInitialized() {
        if (!isInitialized()) {
            throw new IllegalStateException("SoLoader.init() not yet called");
        }
    }

    public static boolean isInitialized() {
        if (sSoSources != null) {
            return true;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            boolean z = sSoSources != null;
            reentrantReadWriteLock.readLock().unlock();
            return z;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    public static int getSoSourcesVersion() {
        return sSoSourcesVersion.get();
    }

    public static int getLoadedLibrariesCount() {
        return sLoadedLibraries.size();
    }
}
