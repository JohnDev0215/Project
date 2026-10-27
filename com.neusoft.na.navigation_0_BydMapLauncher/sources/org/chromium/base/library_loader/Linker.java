package org.chromium.base.library_loader;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Linker {
    protected static final int ADDRESS_SPACE_RESERVATION = 201326592;
    protected static final int BREAKPAD_GUARD_REGION_BYTES = 16777216;
    public static final int BROWSER_SHARED_RELRO_CONFIG = 1;
    public static final int BROWSER_SHARED_RELRO_CONFIG_ALWAYS = 2;
    public static final int BROWSER_SHARED_RELRO_CONFIG_LOW_RAM_ONLY = 1;
    public static final int BROWSER_SHARED_RELRO_CONFIG_NEVER = 0;
    protected static final boolean DEBUG = false;
    public static final String EXTRA_LINKER_SHARED_RELROS = "org.chromium.base.android.linker.shared_relros";
    public static final int LINKER_IMPLEMENTATION_LEGACY = 1;
    public static final int LINKER_IMPLEMENTATION_MODERN = 2;
    private static final String LINKER_JNI_LIBRARY = "chromium_android_linker";
    public static final int MEMORY_DEVICE_CONFIG_INIT = 0;
    public static final int MEMORY_DEVICE_CONFIG_LOW = 1;
    public static final int MEMORY_DEVICE_CONFIG_NORMAL = 2;
    private static final String TAG = "LibraryLoader";
    private static Linker sSingleton;
    private static Object sSingletonLock = new Object();
    protected int mMemoryDeviceConfig = 0;
    protected final Object mLock = new Object();
    private String mTestRunnerClassName = null;

    public interface TestRunner {
        boolean runChecks(int i, boolean z);
    }

    private static native long nativeGetRandomBaseLoadAddress();

    public abstract void disableSharedRelros();

    public abstract void finishLibraryLoad();

    public abstract long getBaseLoadAddress();

    public abstract Bundle getSharedRelros();

    public abstract void initServiceProcess(long j);

    public abstract boolean isUsingBrowserSharedRelros();

    abstract void loadLibraryImpl(@Nullable String str, String str2, boolean z);

    public abstract void prepareLibraryLoad();

    public abstract void useSharedRelros(Bundle bundle);

    protected Linker() {
    }

    public static final Linker getInstance() {
        Linker linker;
        synchronized (sSingletonLock) {
            if (sSingleton == null) {
                String str = ContextUtils.getApplicationContext().getApplicationInfo().className;
                if (!(str != null && str.contains("incrementalinstall"))) {
                    sSingleton = ModernLinker.create();
                } else {
                    sSingleton = LegacyLinker.create();
                }
                Log.i(TAG, "Using linker: " + sSingleton.getClass().getName(), new Object[0]);
            }
            linker = sSingleton;
        }
        return linker;
    }

    public static boolean areTestsEnabled() {
        return NativeLibraries.sEnableLinkerTests;
    }

    private static void assertForTesting(boolean z) {
        if (!z) {
            throw new AssertionError();
        }
    }

    private static void assertLinkerTestsAreEnabled() {
        if (!NativeLibraries.sEnableLinkerTests) {
            throw new AssertionError("Testing method called in non-testing context");
        }
    }

    public static final void setImplementationForTesting(int i) {
        assertLinkerTestsAreEnabled();
        assertForTesting(i == 1 || i == 2);
        synchronized (sSingletonLock) {
            assertForTesting(sSingleton == null);
            if (i == 2) {
                sSingleton = ModernLinker.create();
            } else if (i == 1) {
                sSingleton = LegacyLinker.create();
            }
            Log.i(TAG, "Forced linker: " + sSingleton.getClass().getName(), new Object[0]);
        }
    }

    public final int getImplementationForTesting() {
        assertLinkerTestsAreEnabled();
        synchronized (sSingletonLock) {
            assertForTesting(sSingleton == this);
            Linker linker = sSingleton;
            if (linker instanceof ModernLinker) {
                return 2;
            }
            if (linker instanceof LegacyLinker) {
                return 1;
            }
            Log.wtf(TAG, "Invalid linker: " + sSingleton.getClass().getName(), new Object[0]);
            assertForTesting(false);
            return 0;
        }
    }

    public final void setTestRunnerClassNameForTesting(String str) {
        assertLinkerTestsAreEnabled();
        synchronized (this.mLock) {
            assertForTesting(this.mTestRunnerClassName == null);
            this.mTestRunnerClassName = str;
        }
    }

    public final String getTestRunnerClassNameForTesting() {
        String str;
        assertLinkerTestsAreEnabled();
        synchronized (this.mLock) {
            str = this.mTestRunnerClassName;
        }
        return str;
    }

    public static final void setupForTesting(int i, String str) {
        assertLinkerTestsAreEnabled();
        synchronized (sSingletonLock) {
            Linker linker = sSingleton;
            if (linker == null) {
                setImplementationForTesting(i);
                sSingleton.setTestRunnerClassNameForTesting(str);
                return;
            }
            boolean z = true;
            assertForTesting(linker.getImplementationForTesting() == i);
            String testRunnerClassNameForTesting = sSingleton.getTestRunnerClassNameForTesting();
            if (str == null) {
                if (testRunnerClassNameForTesting != null) {
                    z = false;
                }
                assertForTesting(z);
            } else {
                assertForTesting(testRunnerClassNameForTesting.equals(str));
            }
        }
    }

    protected final void runTestRunnerClassForTesting(int i, boolean z) {
        TestRunner testRunner;
        assertLinkerTestsAreEnabled();
        synchronized (this.mLock) {
            if (this.mTestRunnerClassName == null) {
                Log.wtf(TAG, "Linker runtime tests not set up for this process", new Object[0]);
                assertForTesting(false);
            }
            try {
                testRunner = (TestRunner) Class.forName(this.mTestRunnerClassName).newInstance();
            } catch (Exception e) {
                Log.wtf(TAG, "Could not instantiate test runner class by name", e);
                assertForTesting(false);
                testRunner = null;
            }
            if (!testRunner.runChecks(i, z)) {
                Log.wtf(TAG, "Linker runtime tests failed in this process", new Object[0]);
                assertForTesting(false);
            }
            Log.i(TAG, "All linker tests passed", new Object[0]);
        }
    }

    public final void setMemoryDeviceConfigForTesting(int i) {
        assertLinkerTestsAreEnabled();
        assertForTesting(i == 1 || i == 2);
        synchronized (this.mLock) {
            assertForTesting(this.mMemoryDeviceConfig == 0);
            this.mMemoryDeviceConfig = i;
        }
    }

    public boolean isChromiumLinkerLibrary(String str) {
        return str.equals(LINKER_JNI_LIBRARY) || str.equals("chromium_android_linker.cr");
    }

    protected static void loadLinkerJniLibrary() {
        try {
            System.loadLibrary(LINKER_JNI_LIBRARY);
        } catch (UnsatisfiedLinkError unused) {
            Log.w(TAG, "Couldn't load libchromium_android_linker.so, trying libchromium_android_linker.so.cr", new Object[0]);
            System.loadLibrary("chromium_android_linker.cr");
        }
    }

    protected long getRandomBaseLoadAddress() {
        return nativeGetRandomBaseLoadAddress();
    }

    public void loadLibrary(@Nullable String str, String str2) {
        loadLibraryImpl(str, str2, true);
    }

    public void loadLibraryNoFixedAddress(@Nullable String str, String str2) {
        loadLibraryImpl(str, str2, false);
    }

    public static boolean isInZipFile() {
        return NativeLibraries.sUseLibraryInZipFile;
    }

    public static boolean isUsed() {
        return NativeLibraries.sUseLinker;
    }

    public static class LibInfo implements Parcelable {
        public static final Parcelable.Creator<LibInfo> CREATOR = new Parcelable.Creator<LibInfo>() { // from class: org.chromium.base.library_loader.Linker.LibInfo.1
            @Override // android.os.Parcelable.Creator
            public LibInfo createFromParcel(Parcel parcel) {
                return new LibInfo(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public LibInfo[] newArray(int i) {
                return new LibInfo[i];
            }
        };
        public long mLoadAddress;
        public long mLoadSize;
        public int mRelroFd;
        public long mRelroSize;
        public long mRelroStart;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 1;
        }

        public LibInfo() {
            this.mLoadAddress = 0L;
            this.mLoadSize = 0L;
            this.mRelroStart = 0L;
            this.mRelroSize = 0L;
            this.mRelroFd = -1;
        }

        public void close() {
            int i = this.mRelroFd;
            if (i >= 0) {
                try {
                    ParcelFileDescriptor.adoptFd(i).close();
                } catch (IOException unused) {
                }
                this.mRelroFd = -1;
            }
        }

        public LibInfo(Parcel parcel) {
            this.mLoadAddress = parcel.readLong();
            this.mLoadSize = parcel.readLong();
            this.mRelroStart = parcel.readLong();
            this.mRelroSize = parcel.readLong();
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
            this.mRelroFd = parcelFileDescriptor == null ? -1 : parcelFileDescriptor.detachFd();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (this.mRelroFd >= 0) {
                parcel.writeLong(this.mLoadAddress);
                parcel.writeLong(this.mLoadSize);
                parcel.writeLong(this.mRelroStart);
                parcel.writeLong(this.mRelroSize);
                try {
                    ParcelFileDescriptor parcelFileDescriptorFromFd = ParcelFileDescriptor.fromFd(this.mRelroFd);
                    parcelFileDescriptorFromFd.writeToParcel(parcel, 0);
                    parcelFileDescriptorFromFd.close();
                } catch (IOException e) {
                    Log.e(Linker.TAG, "Can't write LibInfo file descriptor to parcel", e);
                }
            }
        }

        public String toString() {
            return String.format(Locale.US, "[load=0x%x-0x%x relro=0x%x-0x%x fd=%d]", Long.valueOf(this.mLoadAddress), Long.valueOf(this.mLoadAddress + this.mLoadSize), Long.valueOf(this.mRelroStart), Long.valueOf(this.mRelroStart + this.mRelroSize), Integer.valueOf(this.mRelroFd));
        }
    }

    protected Bundle createBundleFromLibInfoMap(HashMap<String, LibInfo> map) {
        Bundle bundle = new Bundle(map.size());
        for (Map.Entry<String, LibInfo> entry : map.entrySet()) {
            bundle.putParcelable(entry.getKey(), entry.getValue());
        }
        return bundle;
    }

    protected HashMap<String, LibInfo> createLibInfoMapFromBundle(Bundle bundle) {
        HashMap<String, LibInfo> map = new HashMap<>();
        for (String str : bundle.keySet()) {
            map.put(str, (LibInfo) bundle.getParcelable(str));
        }
        return map;
    }

    protected void closeLibInfoMap(HashMap<String, LibInfo> map) {
        Iterator<Map.Entry<String, LibInfo>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().close();
        }
    }
}
