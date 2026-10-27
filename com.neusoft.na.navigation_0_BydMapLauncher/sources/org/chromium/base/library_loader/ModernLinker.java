package org.chromium.base.library_loader;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Locale;
import javax.annotation.Nullable;
import org.chromium.base.Log;
import org.chromium.base.PathUtils;
import org.chromium.base.library_loader.Linker;

/* JADX INFO: loaded from: classes3.dex */
class ModernLinker extends Linker {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "LibraryLoader";
    private boolean mInitialized = false;
    private boolean mWaitForSharedRelros = false;
    private HashMap<String, Linker.LibInfo> mSharedRelros = null;
    private Bundle mSharedRelrosBundle = null;
    private boolean mInBrowserProcess = true;
    private long mBaseLoadAddress = -1;
    private long mCurrentLoadAddress = -1;
    private boolean mPrepareLibraryLoadCalled = false;
    private HashMap<String, Linker.LibInfo> mLoadedLibraries = null;

    private static native boolean nativeCreateSharedRelro(String str, long j, String str2, Linker.LibInfo libInfo);

    private static native String nativeGetCpuAbi();

    private static native boolean nativeLoadLibrary(String str, long j, Linker.LibInfo libInfo);

    @Override // org.chromium.base.library_loader.Linker
    public boolean isUsingBrowserSharedRelros() {
        return false;
    }

    private ModernLinker() {
    }

    static Linker create() {
        return new ModernLinker();
    }

    private void ensureInitializedLocked() {
        if (this.mInitialized) {
            return;
        }
        loadLinkerJniLibrary();
        this.mInitialized = true;
    }

    @Override // org.chromium.base.library_loader.Linker
    public void prepareLibraryLoad() {
        synchronized (this.mLock) {
            ensureInitializedLocked();
            if (this.mInBrowserProcess) {
                setupBaseLoadAddressLocked();
                this.mSharedRelros = new HashMap<>();
            }
            this.mLoadedLibraries = new HashMap<>();
            this.mCurrentLoadAddress = this.mBaseLoadAddress;
            this.mPrepareLibraryLoadCalled = true;
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public void finishLibraryLoad() {
        HashMap<String, Linker.LibInfo> map;
        synchronized (this.mLock) {
            if (!this.mInBrowserProcess && (map = this.mSharedRelros) != null) {
                closeLibInfoMap(map);
                this.mSharedRelros = null;
            }
            if (NativeLibraries.sEnableLinkerTests) {
                runTestRunnerClassForTesting(0, this.mInBrowserProcess);
            }
        }
    }

    private void waitForSharedRelrosLocked() {
        if (this.mSharedRelros != null) {
            return;
        }
        while (this.mSharedRelros == null) {
            try {
                this.mLock.wait();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public void useSharedRelros(Bundle bundle) {
        synchronized (this.mLock) {
            this.mSharedRelros = createLibInfoMapFromBundle(bundle);
            this.mLock.notifyAll();
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public Bundle getSharedRelros() {
        HashMap<String, Linker.LibInfo> map;
        synchronized (this.mLock) {
            if (!this.mInBrowserProcess) {
                return null;
            }
            if (this.mSharedRelrosBundle == null && (map = this.mSharedRelros) != null) {
                this.mSharedRelrosBundle = createBundleFromLibInfoMap(map);
            }
            return this.mSharedRelrosBundle;
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public void disableSharedRelros() {
        synchronized (this.mLock) {
            this.mInBrowserProcess = false;
            this.mWaitForSharedRelros = false;
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public void initServiceProcess(long j) {
        synchronized (this.mLock) {
            this.mInBrowserProcess = false;
            this.mWaitForSharedRelros = true;
            this.mBaseLoadAddress = j;
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public long getBaseLoadAddress() {
        long j;
        synchronized (this.mLock) {
            ensureInitializedLocked();
            setupBaseLoadAddressLocked();
            j = this.mBaseLoadAddress;
        }
        return j;
    }

    private void setupBaseLoadAddressLocked() {
        if (this.mBaseLoadAddress == -1) {
            this.mBaseLoadAddress = getRandomBaseLoadAddress();
        }
        if (this.mBaseLoadAddress == 0) {
            Log.w(TAG, "Disabling shared RELROs due address space pressure", new Object[0]);
            this.mWaitForSharedRelros = false;
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    void loadLibraryImpl(@Nullable String str, String str2, boolean z) {
        long j;
        synchronized (this.mLock) {
            String str3 = str != null ? str + "!/lib/" + nativeGetCpuAbi() + "/crazy." + str2 : str2;
            if (this.mLoadedLibraries.containsKey(str3)) {
                return;
            }
            if (!this.mInBrowserProcess && this.mWaitForSharedRelros && z) {
                j = this.mCurrentLoadAddress;
                if (j > this.mBaseLoadAddress + 201326592) {
                    String str4 = "Load address outside reservation, for: " + str2;
                    Log.e(TAG, str4, new Object[0]);
                    throw new UnsatisfiedLinkError(str4);
                }
            } else {
                j = 0;
            }
            Linker.LibInfo libInfo = new Linker.LibInfo();
            boolean z2 = this.mInBrowserProcess;
            if (z2 && this.mCurrentLoadAddress != 0) {
                String str5 = PathUtils.getDataDirectory(null) + "/RELRO:" + str2;
                if (nativeCreateSharedRelro(str3, this.mCurrentLoadAddress, str5, libInfo)) {
                    this.mSharedRelros.put(str3, libInfo);
                } else {
                    Log.w(TAG, "Unable to create shared relro: " + str5, new Object[0]);
                }
            } else if (!z2 && this.mCurrentLoadAddress != 0 && this.mWaitForSharedRelros) {
                waitForSharedRelrosLocked();
                if (this.mSharedRelros.containsKey(str3)) {
                    libInfo = this.mSharedRelros.get(str3);
                }
            }
            if (!nativeLoadLibrary(str3, j, libInfo)) {
                String str6 = "Unable to load library: " + str3;
                Log.e(TAG, str6, new Object[0]);
                throw new UnsatisfiedLinkError(str6);
            }
            if (NativeLibraries.sEnableLinkerTests) {
                Log.i(TAG, String.format(Locale.US, "%s: %s %x", this.mInBrowserProcess ? "BROWSER_LIBRARY_ADDRESS" : "RENDERER_LIBRARY_ADDRESS", str2, Long.valueOf(libInfo.mLoadAddress)), new Object[0]);
            }
            if (j != 0 && this.mCurrentLoadAddress != 0) {
                this.mCurrentLoadAddress = libInfo.mLoadAddress + libInfo.mLoadSize + 16777216;
            }
            this.mLoadedLibraries.put(str3, libInfo);
        }
    }
}
