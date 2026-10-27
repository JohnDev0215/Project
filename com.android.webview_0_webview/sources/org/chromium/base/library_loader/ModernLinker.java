package org.chromium.base.library_loader;

import android.os.ParcelFileDescriptor;
import defpackage.AbstractC0628ct;
import defpackage.Ts;
import org.chromium.base.library_loader.Linker;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ModernLinker extends Linker {
    public static native boolean nativeLoadLibraryCreateRelros(String str, long j, Linker.LibInfo libInfo);

    public static native boolean nativeLoadLibraryNoRelros(String str);

    public static native boolean nativeLoadLibraryUseRelros(String str, long j, int i);

    @Override // org.chromium.base.library_loader.Linker
    public void c(String str, boolean z) {
        Linker.LibInfo libInfo;
        if (!"monochrome".equals(str)) {
            Ts.d("ModernLinker", "loadLibraryImpl: %s, %b", str, Boolean.valueOf(z));
        }
        a();
        String strMapLibraryName = System.mapLibraryName(str);
        boolean z2 = !z;
        boolean z3 = z && this.b;
        long j = z ? this.c : 0L;
        if (z2) {
            if (!nativeLoadLibraryNoRelros(strMapLibraryName)) {
                this.d = 1;
                Ts.a("ModernLinker", "Cannot load without relro sharing", new Object[0]);
                throw new UnsatisfiedLinkError("Cannot load without relro sharing");
            }
            this.d = 3;
        } else if (z3) {
            Linker.LibInfo libInfo2 = new Linker.LibInfo();
            libInfo2.s = strMapLibraryName;
            if (!nativeLoadLibraryCreateRelros(strMapLibraryName, j, libInfo2)) {
                Ts.a("ModernLinker", "Unable to create relro, retrying without", new Object[0]);
                nativeLoadLibraryNoRelros(strMapLibraryName);
                libInfo2.mRelroFd = -1;
            }
            this.a = libInfo2;
            this.d = 2;
        } else {
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
            if (!nativeLoadLibraryUseRelros(strMapLibraryName, j, libInfo.mRelroFd)) {
                String str2 = String.format("Unable to load library: %s", strMapLibraryName);
                this.d = 1;
                Ts.a("ModernLinker", str2, new Object[0]);
                throw new UnsatisfiedLinkError(str2);
            }
            Linker.LibInfo libInfo3 = this.a;
            int i = libInfo3.mRelroFd;
            if (i >= 0) {
                AbstractC0628ct.a(ParcelFileDescriptor.adoptFd(i));
                libInfo3.mRelroFd = -1;
            }
            this.a = null;
            this.d = 3;
        }
        try {
            System.loadLibrary(str);
        } catch (UnsatisfiedLinkError unused2) {
            throw new UnsatisfiedLinkError("Unable to load the library a second time with the system linker");
        }
    }
}
