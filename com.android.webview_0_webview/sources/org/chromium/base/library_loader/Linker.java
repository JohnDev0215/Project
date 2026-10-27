package org.chromium.base.library_loader;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import defpackage.C1569tt;
import defpackage.C1624ut;
import defpackage.Is;
import defpackage.Ts;
import java.io.IOException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Linker {
    public static final Object e = new Object();
    public static Linker f;
    public LibInfo a;
    public boolean b = true;
    public long c = -1;
    public int d = 0;

    public static Linker b() {
        Linker linker;
        synchronized (e) {
            if (f == null) {
                String str = Is.a.getApplicationInfo().className;
                boolean z = str != null && str.contains("incrementalinstall");
                if (!C1569tt.l.f || z) {
                    f = new LegacyLinker();
                } else {
                    f = new ModernLinker();
                }
                Ts.d("Linker", "Using linker: %s", f.getClass().getName());
            }
            linker = f;
        }
        return linker;
    }

    public static native long nativeGetRandomBaseLoadAddress();

    public final void a() {
        if (this.d != 0) {
            return;
        }
        C1569tt.l();
        try {
            System.loadLibrary("chromium_android_linker");
            if (this.b) {
                d();
            }
            this.d = 1;
        } catch (UnsatisfiedLinkError e2) {
            boolean z = C1569tt.k;
            throw e2;
        }
    }

    public abstract void c(String str, boolean z);

    public final void d() {
        if (this.c == -1) {
            long jNativeGetRandomBaseLoadAddress = nativeGetRandomBaseLoadAddress();
            this.c = jNativeGetRandomBaseLoadAddress;
            if (jNativeGetRandomBaseLoadAddress == 0) {
                Ts.f("Linker", "Disabling shared RELROs due address space pressure", new Object[0]);
            }
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class LibInfo implements Parcelable {
        public static final Parcelable.Creator CREATOR = new C1624ut();
        public long mLoadAddress;
        public long mLoadSize;
        public int mRelroFd;
        public long mRelroSize;
        public long mRelroStart;
        public String s;

        public LibInfo() {
            this.mRelroFd = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.s);
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
                    Ts.a("Linker", "Can't write LibInfo file descriptor to parcel", e);
                }
            }
        }

        public LibInfo(Parcel parcel) {
            this.mRelroFd = -1;
            this.s = parcel.readString();
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
