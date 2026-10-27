package org.chromium.base.library_loader;

import defpackage.oj2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.library_loader.Linker;

/* JADX INFO: loaded from: classes.dex */
class LinkerJni {
    private static native void nativeFindMemoryRegionAtRandomAddress(Linker.LibInfo libInfo);

    private static native boolean nativeFindRegionReservedByWebViewZygote(Linker.LibInfo libInfo);

    private static native int nativeGetRelroSharingResult();

    private static native boolean nativeLoadLibrary(String str, Linker.LibInfo libInfo, boolean z);

    private static native void nativeReserveMemoryForLibrary(Linker.LibInfo libInfo);

    private static native boolean nativeUseRelros(long j, Linker.LibInfo libInfo);

    @CalledByNative
    public static void reportDlopenExtTime(long j) {
        oj2.b0(j, "ChromiumAndroidLinker.ModernLinkerDlopenExtTime");
    }

    @CalledByNative
    public static void reportIteratePhdrTime(long j) {
        oj2.b0(j, "ChromiumAndroidLinker.ModernLinkerIteratePhdrTime");
    }

    public final void a(Linker.LibInfo libInfo) {
        nativeFindMemoryRegionAtRandomAddress(libInfo);
    }

    public final boolean b(Linker.LibInfo libInfo) {
        return nativeFindRegionReservedByWebViewZygote(libInfo);
    }

    public final int c() {
        return nativeGetRelroSharingResult();
    }

    public final boolean d(String str, Linker.LibInfo libInfo, boolean z) {
        return nativeLoadLibrary(str, libInfo, z);
    }

    public final void e(Linker.LibInfo libInfo) {
        nativeReserveMemoryForLibrary(libInfo);
    }

    public final boolean f(long j, Linker.LibInfo libInfo) {
        return nativeUseRelros(j, libInfo);
    }
}
