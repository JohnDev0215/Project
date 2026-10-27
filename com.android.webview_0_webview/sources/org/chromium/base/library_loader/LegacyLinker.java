package org.chromium.base.library_loader;

import android.os.ParcelFileDescriptor;
import defpackage.AbstractC0628ct;
import defpackage.Ts;
import defpackage.Z7;
import org.chromium.base.library_loader.Linker;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LegacyLinker extends Linker {
    public static void e(Linker.LibInfo libInfo) {
        String str = libInfo.s;
        if (nativeUseSharedRelro(str, libInfo)) {
            return;
        }
        Ts.f("LegacyLinker", "Could not use shared RELRO section for %s", str);
    }

    public static native boolean nativeCreateSharedRelro(String str, long j, Linker.LibInfo libInfo);

    public static native boolean nativeLoadLibrary(String str, long j, Linker.LibInfo libInfo);

    public static native boolean nativeUseSharedRelro(String str, Linker.LibInfo libInfo);

    @Override // org.chromium.base.library_loader.Linker
    public void c(String str, boolean z) {
        Linker.LibInfo libInfo;
        a();
        boolean z2 = this.b;
        long j = z ? this.c : 0L;
        String strMapLibraryName = System.mapLibraryName(str);
        Linker.LibInfo libInfo2 = new Linker.LibInfo();
        if (!nativeLoadLibrary(strMapLibraryName, j, libInfo2)) {
            String strC = Z7.c("Unable to load library: ", strMapLibraryName);
            Ts.a("LegacyLinker", strC, new Object[0]);
            throw new UnsatisfiedLinkError(strC);
        }
        libInfo2.s = strMapLibraryName;
        if (z2) {
            if (!nativeCreateSharedRelro(strMapLibraryName, this.c, libInfo2)) {
                Ts.f("LegacyLinker", "Could not create shared RELRO for %s at %x", strMapLibraryName, Long.valueOf(this.c));
                libInfo2.mRelroFd = -1;
            }
            this.a = libInfo2;
            e(libInfo2);
            this.d = 2;
            return;
        }
        while (true) {
            libInfo = this.a;
            if (libInfo != null) {
                break;
            } else {
                try {
                    Linker.e.wait();
                } catch (InterruptedException unused) {
                }
            }
        }
        e(libInfo);
        Linker.LibInfo libInfo3 = this.a;
        int i = libInfo3.mRelroFd;
        if (i >= 0) {
            AbstractC0628ct.a(ParcelFileDescriptor.adoptFd(i));
            libInfo3.mRelroFd = -1;
        }
        this.a = null;
        this.d = 3;
    }
}
