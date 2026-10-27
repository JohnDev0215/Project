package org.chromium.base.library_loader;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import defpackage.fa1;
import defpackage.oj2;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.chromium.base.annotations.AccessedByNative;

/* JADX INFO: loaded from: classes.dex */
public final class Linker {
    public LibInfo b;
    public LibInfo c;
    public a e;
    public final Object a = new Object();
    public boolean d = true;
    public int f = 0;

    public static class a {
        public final boolean a;
        public final long b;

        public a(long j, boolean z) {
            this.a = z;
            this.b = j;
        }
    }

    public static String e() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/self/cgroup")));
            String strSubstring = null;
            do {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    int iIndexOf = line.indexOf(":blkio:");
                    strSubstring = iIndexOf == -1 ? "" : line.substring(iIndexOf + 7);
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused) {
                    }
                }
            } while (strSubstring.equals(""));
            if (strSubstring != null) {
                if (!strSubstring.equals("")) {
                    if (strSubstring.equals("/")) {
                        return "Foreground";
                    }
                    if (strSubstring.equals("/background")) {
                        return "Background";
                    }
                    fa1.b("Linker", "blkio cgroup with unexpected name: '%s'", strSubstring);
                    return "Unknown";
                }
            }
            bufferedReader.close();
            return "Unknown";
        } catch (IOException unused2) {
            fa1.b("Linker", "IOException while reading %s", "/proc/self/cgroup");
            return "Unknown";
        }
    }

    public final void a(boolean z) {
        if (this.c.mRelroFd == -1 || this.b == null) {
            return;
        }
        new LinkerJni().f(this.b.mLoadAddress, this.c);
        oj2.O("ChromiumAndroidLinker.RelroAvailableImmediately", z);
        oj2.U(new LinkerJni().c(), 9, "ChromiumAndroidLinker.RelroSharingStatus2");
    }

    public final void b(int i, String str) {
        boolean z;
        if (!"monochrome".equals(str)) {
            fa1.h("Linker", "loadLibraryImplLocked: %s, relroMode=%d", str, Integer.valueOf(i));
        }
        String strE = e();
        long jUptimeMillis = SystemClock.uptimeMillis();
        String strMapLibraryName = System.mapLibraryName(str);
        boolean z2 = false;
        if (i == 0) {
            this.f = 3;
            z = false;
        } else {
            if (i == 1) {
                this.b.e = strMapLibraryName;
                if (!new LinkerJni().d(strMapLibraryName, this.b, true)) {
                    Log.e("cr_Linker", "Unable to load with Linker, using the system linker instead");
                    this.b.mRelroFd = -1;
                }
                oj2.O("ChromiumAndroidLinker.RelroProvidedSuccessfully", this.b.mRelroFd != -1);
                this.f = 2;
            } else {
                if (!new LinkerJni().d(strMapLibraryName, this.b, false)) {
                    String str2 = String.format("Unable to load library: %s", strMapLibraryName);
                    this.f = 1;
                    Log.e("cr_Linker", str2);
                    throw new UnsatisfiedLinkError(str2);
                }
                this.f = 3;
            }
            z = true;
        }
        String strE2 = e();
        if (!strE.equals(strE2)) {
            strE = (strE.equals("Unknown") || strE2.equals("Unknown")) ? "Unknown" : "Mixed";
        }
        if (z) {
            String str3 = i == 1 ? "Produce" : "Consume";
            long jUptimeMillis2 = SystemClock.uptimeMillis() - jUptimeMillis;
            oj2.b0(jUptimeMillis2, "ChromiumAndroidLinker.ModernLinkerDetailedLoadTime.".concat(str3));
            oj2.b0(jUptimeMillis2, "ChromiumAndroidLinker.ModernLinkerDetailedLoadTimeByBlkioCgroup." + str3 + "." + strE);
        }
        long jUptimeMillis3 = SystemClock.uptimeMillis();
        try {
            System.loadLibrary(str);
            String str4 = z ? "Second" : "NoSharing";
            long jUptimeMillis4 = SystemClock.uptimeMillis() - jUptimeMillis3;
            oj2.b0(jUptimeMillis4, "ChromiumAndroidLinker.ModernLinkerDetailedLoadTime.".concat(str4));
            oj2.b0(jUptimeMillis4, "ChromiumAndroidLinker.ModernLinkerDetailedLoadTimeByBlkioCgroup." + str4 + "." + strE);
            if (this.c != null && this.f == 3 && this.b.mLoadAddress != 0) {
                z2 = true;
            }
            if (z2) {
                a(true);
            }
        } catch (UnsatisfiedLinkError unused) {
            this.f = 1;
            Log.e("cr_Linker", "Failed at System.loadLibrary()");
            throw new UnsatisfiedLinkError("Failed at System.loadLibrary()");
        }
    }

    public final void c(long j, int i, boolean z) {
        this.b = new LibInfo();
        this.d = z;
        org.chromium.base.library_loader.a aVar = org.chromium.base.library_loader.a.l;
        System.loadLibrary("chromium_android_linker");
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return;
                }
            }
            new LinkerJni().a(this.b);
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        boolean zB = new LinkerJni().b(this.b);
        this.e = new a(SystemClock.uptimeMillis() - jUptimeMillis, zB);
        if (zB && (j == 0 || j == this.b.mLoadAddress)) {
            return;
        }
        this.b.mLoadAddress = j;
        if (j != 0) {
            new LinkerJni().e(this.b);
            if (this.b.mLoadAddress != 0) {
                return;
            }
        }
        new LinkerJni().a(this.b);
    }

    public final void d(long j, int i, boolean z) {
        synchronized (this.a) {
            if (this.f != 0) {
                return;
            }
            c(j, i, z);
            this.f = 1;
        }
    }

    public static class LibInfo implements Parcelable {
        public static final Parcelable.Creator<LibInfo> CREATOR = new a();
        public String e;

        @AccessedByNative
        public long mLoadAddress;

        @AccessedByNative
        public long mLoadSize;

        @AccessedByNative
        public int mRelroFd;

        @AccessedByNative
        public long mRelroSize;

        @AccessedByNative
        public long mRelroStart;

        public class a implements Parcelable.Creator<LibInfo> {
            @Override // android.os.Parcelable.Creator
            public final LibInfo createFromParcel(Parcel parcel) {
                return new LibInfo(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final LibInfo[] newArray(int i) {
                return new LibInfo[i];
            }
        }

        public LibInfo() {
            this.mRelroFd = -1;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 1;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.e);
            parcel.writeLong(this.mLoadAddress);
            parcel.writeLong(this.mLoadSize);
            parcel.writeLong(this.mRelroStart);
            parcel.writeLong(this.mRelroSize);
            parcel.writeInt(this.mRelroFd >= 0 ? 1 : 0);
            int i2 = this.mRelroFd;
            if (i2 >= 0) {
                try {
                    ParcelFileDescriptor parcelFileDescriptorFromFd = ParcelFileDescriptor.fromFd(i2);
                    parcelFileDescriptorFromFd.writeToParcel(parcel, 0);
                    parcelFileDescriptorFromFd.close();
                } catch (IOException e) {
                    Log.e("cr_Linker", "Can't write LibInfo file descriptor to parcel", e);
                }
            }
        }

        public LibInfo(Parcel parcel) {
            this.mRelroFd = -1;
            this.e = parcel.readString();
            this.mLoadAddress = parcel.readLong();
            this.mLoadSize = parcel.readLong();
            this.mRelroStart = parcel.readLong();
            this.mRelroSize = parcel.readLong();
            if (parcel.readInt() != 0) {
                ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                if (parcelFileDescriptor != null) {
                    this.mRelroFd = parcelFileDescriptor.detachFd();
                    return;
                }
                return;
            }
            this.mRelroFd = -1;
        }
    }
}
