package org.chromium.base.library_loader;

import android.os.Bundle;
import android.os.Parcel;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import org.chromium.base.Log;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.library_loader.Linker;

/* JADX INFO: loaded from: classes3.dex */
class LegacyLinker extends Linker {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "LibraryLoader";
    private boolean mInitialized = false;
    private boolean mInBrowserProcess = true;
    private boolean mWaitForSharedRelros = false;
    private boolean mBrowserUsesSharedRelro = false;
    private Bundle mSharedRelros = null;
    private long mBaseLoadAddress = -1;
    private long mCurrentLoadAddress = -1;
    private boolean mPrepareLibraryLoadCalled = false;
    private HashMap<String, Linker.LibInfo> mLoadedLibraries = null;

    private void dumpBundle(Bundle bundle) {
    }

    private static native boolean nativeCreateSharedRelro(String str, long j, Linker.LibInfo libInfo);

    private static native boolean nativeLoadLibrary(String str, long j, Linker.LibInfo libInfo);

    private static native boolean nativeLoadLibraryInZipFile(@Nullable String str, String str2, long j, Linker.LibInfo libInfo);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeRunCallbackOnUiThread(long j);

    private static native boolean nativeUseSharedRelro(String str, Linker.LibInfo libInfo);

    private LegacyLinker() {
    }

    static Linker create() {
        return new LegacyLinker();
    }

    private void ensureInitializedLocked() {
        if (this.mInitialized || !NativeLibraries.sUseLinker) {
            return;
        }
        loadLinkerJniLibrary();
        if (this.mMemoryDeviceConfig == 0) {
            if (SysUtils.isLowEndDevice()) {
                this.mMemoryDeviceConfig = 1;
            } else {
                this.mMemoryDeviceConfig = 2;
            }
        }
        if (this.mMemoryDeviceConfig == 1) {
            this.mBrowserUsesSharedRelro = true;
            Log.w(TAG, "Low-memory device: shared RELROs used in all processes", new Object[0]);
        } else {
            this.mBrowserUsesSharedRelro = false;
        }
        this.mInitialized = true;
    }

    @Override // org.chromium.base.library_loader.Linker
    public boolean isUsingBrowserSharedRelros() {
        boolean z;
        synchronized (this.mLock) {
            ensureInitializedLocked();
            z = this.mInBrowserProcess && this.mBrowserUsesSharedRelro;
        }
        return z;
    }

    @Override // org.chromium.base.library_loader.Linker
    public void prepareLibraryLoad() {
        synchronized (this.mLock) {
            ensureInitializedLocked();
            this.mPrepareLibraryLoadCalled = true;
            if (this.mInBrowserProcess) {
                setupBaseLoadAddressLocked();
            }
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public void finishLibraryLoad() {
        Bundle bundle;
        synchronized (this.mLock) {
            ensureInitializedLocked();
            HashMap<String, Linker.LibInfo> map = this.mLoadedLibraries;
            if (map != null) {
                if (this.mInBrowserProcess) {
                    Bundle bundleCreateBundleFromLibInfoMap = createBundleFromLibInfoMap(map);
                    this.mSharedRelros = bundleCreateBundleFromLibInfoMap;
                    if (this.mBrowserUsesSharedRelro) {
                        useSharedRelrosLocked(bundleCreateBundleFromLibInfoMap);
                    }
                }
                if (this.mWaitForSharedRelros) {
                    while (true) {
                        bundle = this.mSharedRelros;
                        if (bundle != null) {
                            break;
                        }
                        try {
                            this.mLock.wait();
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    useSharedRelrosLocked(bundle);
                    this.mSharedRelros.clear();
                    this.mSharedRelros = null;
                }
            }
            if (NativeLibraries.sEnableLinkerTests) {
                runTestRunnerClassForTesting(this.mMemoryDeviceConfig, this.mInBrowserProcess);
            }
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public void useSharedRelros(Bundle bundle) {
        Bundle bundle2;
        if (bundle != null) {
            bundle.setClassLoader(Linker.LibInfo.class.getClassLoader());
            bundle2 = new Bundle(Linker.LibInfo.class.getClassLoader());
            Parcel parcelObtain = Parcel.obtain();
            bundle.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            bundle2.readFromParcel(parcelObtain);
            parcelObtain.recycle();
        } else {
            bundle2 = null;
        }
        synchronized (this.mLock) {
            this.mSharedRelros = bundle2;
            this.mLock.notifyAll();
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public Bundle getSharedRelros() {
        synchronized (this.mLock) {
            if (!this.mInBrowserProcess) {
                return null;
            }
            return this.mSharedRelros;
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public void disableSharedRelros() {
        synchronized (this.mLock) {
            ensureInitializedLocked();
            this.mInBrowserProcess = false;
            this.mWaitForSharedRelros = false;
            this.mBrowserUsesSharedRelro = false;
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public void initServiceProcess(long j) {
        synchronized (this.mLock) {
            ensureInitializedLocked();
            this.mInBrowserProcess = false;
            this.mBrowserUsesSharedRelro = false;
            this.mWaitForSharedRelros = true;
            this.mBaseLoadAddress = j;
            this.mCurrentLoadAddress = j;
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public long getBaseLoadAddress() {
        synchronized (this.mLock) {
            ensureInitializedLocked();
            if (!this.mInBrowserProcess) {
                Log.w(TAG, "Shared RELRO sections are disabled in this process!", new Object[0]);
                return 0L;
            }
            setupBaseLoadAddressLocked();
            return this.mBaseLoadAddress;
        }
    }

    private void setupBaseLoadAddressLocked() {
        if (this.mBaseLoadAddress == -1) {
            long randomBaseLoadAddress = getRandomBaseLoadAddress();
            this.mBaseLoadAddress = randomBaseLoadAddress;
            this.mCurrentLoadAddress = randomBaseLoadAddress;
            if (randomBaseLoadAddress == 0) {
                Log.w(TAG, "Disabling shared RELROs due address space pressure", new Object[0]);
                this.mBrowserUsesSharedRelro = false;
                this.mWaitForSharedRelros = false;
            }
        }
    }

    private void useSharedRelrosLocked(Bundle bundle) {
        if (bundle == null || this.mLoadedLibraries == null) {
            return;
        }
        HashMap<String, Linker.LibInfo> mapCreateLibInfoMapFromBundle = createLibInfoMapFromBundle(bundle);
        for (Map.Entry<String, Linker.LibInfo> entry : mapCreateLibInfoMapFromBundle.entrySet()) {
            String key = entry.getKey();
            if (!nativeUseSharedRelro(key, entry.getValue())) {
                Log.w(TAG, "Could not use shared RELRO section for " + key, new Object[0]);
            }
        }
        if (this.mInBrowserProcess) {
            return;
        }
        closeLibInfoMap(mapCreateLibInfoMapFromBundle);
    }

    @Override // org.chromium.base.library_loader.Linker
    void loadLibraryImpl(@Nullable String str, String str2, boolean z) {
        long j;
        long j2;
        String str3 = str;
        synchronized (this.mLock) {
            ensureInitializedLocked();
            if (this.mLoadedLibraries == null) {
                this.mLoadedLibraries = new HashMap<>();
            }
            if (this.mLoadedLibraries.containsKey(str2)) {
                return;
            }
            Linker.LibInfo libInfo = new Linker.LibInfo();
            if (z && ((this.mInBrowserProcess && this.mBrowserUsesSharedRelro) || this.mWaitForSharedRelros)) {
                j = this.mCurrentLoadAddress;
                if (j > this.mBaseLoadAddress + 201326592) {
                    String str4 = "Load address outside reservation, for: " + str2;
                    Log.e(TAG, str4, new Object[0]);
                    throw new UnsatisfiedLinkError(str4);
                }
            } else {
                j = 0;
            }
            if (str3 != null) {
                if (!nativeLoadLibraryInZipFile(str3, str2, j, libInfo)) {
                    String str5 = "Unable to load library: " + str2 + ", in: " + str3;
                    Log.e(TAG, str5, new Object[0]);
                    throw new UnsatisfiedLinkError(str5);
                }
            } else {
                if (!nativeLoadLibrary(str2, j, libInfo)) {
                    String str6 = "Unable to load library: " + str2;
                    Log.e(TAG, str6, new Object[0]);
                    throw new UnsatisfiedLinkError(str6);
                }
                str3 = str2;
            }
            if (NativeLibraries.sEnableLinkerTests) {
                j2 = j;
                Log.i(TAG, String.format(Locale.US, "%s: %s %x", this.mInBrowserProcess ? "BROWSER_LIBRARY_ADDRESS" : "RENDERER_LIBRARY_ADDRESS", str2, Long.valueOf(libInfo.mLoadAddress)), new Object[0]);
            } else {
                j2 = j;
            }
            if (this.mInBrowserProcess && !nativeCreateSharedRelro(str3, this.mCurrentLoadAddress, libInfo)) {
                Log.w(TAG, String.format(Locale.US, "Could not create shared RELRO for %s at %x", str2, Long.valueOf(this.mCurrentLoadAddress)), new Object[0]);
            }
            if (j2 != 0 && this.mCurrentLoadAddress != 0) {
                this.mCurrentLoadAddress = libInfo.mLoadAddress + libInfo.mLoadSize + 16777216;
            }
            this.mLoadedLibraries.put(str3, libInfo);
        }
    }

    public static void postCallbackOnMainThread(final long j) {
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.base.library_loader.LegacyLinker.1
            @Override // java.lang.Runnable
            public void run() {
                LegacyLinker.nativeRunCallbackOnUiThread(j);
            }
        });
    }
}
