package com.byd.cleardata.d;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import com.byd.cleardata.R;
import com.byd.cleardata.i.i;
import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2590b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2591c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private f f2592d;
    private boolean e;
    private int f;
    private f g;
    private long h;
    private long i;

    class a implements Parcelable.Creator<d> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i) {
            return new d[i];
        }
    }

    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2593a;

        static {
            int[] iArr = new int[f.values().length];
            f2593a = iArr;
            try {
                iArr[f.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2593a[f.UNINITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2593a[f.DIRECTORY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2593a[f.AUDIO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2593a[f.VIDEO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2593a[f.IMAGE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2593a[f.COMPRESS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2593a[f.APK.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public d() {
        this(null, f.UNINITIALIZED);
    }

    protected d(Parcel parcel) {
        this.g = f.UNKNOWN;
        this.f2590b = parcel.readString();
        this.f2591c = parcel.readString();
        int i = parcel.readInt();
        this.f2592d = i == -1 ? null : f.values()[i];
        this.e = parcel.readByte() != 0;
        this.f = parcel.readInt();
        int i2 = parcel.readInt();
        this.g = i2 != -1 ? f.values()[i2] : null;
        this.h = parcel.readLong();
        this.i = parcel.readLong();
    }

    public d(File file) {
        this(file, f.UNINITIALIZED);
    }

    public d(File file, f fVar) {
        this.g = f.UNKNOWN;
        if (!com.byd.cleardata.i.e.h(file)) {
            this.f2592d = f.NONE;
            return;
        }
        this.f2590b = file.getName();
        String absolutePath = file.getAbsolutePath();
        this.f2591c = absolutePath;
        this.f2592d = com.byd.cleardata.i.e.G(absolutePath) ? f.DIRECTORY : fVar;
    }

    public static d y(String str) {
        d dVar = new d();
        dVar.f2590b = str;
        dVar.f2592d = f.UNINITIALIZED;
        return dVar;
    }

    @SuppressLint({"NewApi"})
    public static d z(String str) {
        d dVar = new d();
        dVar.f2590b = Paths.get(str, new String[0]).getFileName().toString();
        dVar.f2591c = str;
        dVar.f2592d = f.UNINITIALIZED;
        return dVar;
    }

    public long A() {
        if (this.h == 0) {
            this.h = com.byd.cleardata.i.e.q(this.f2591c);
        }
        return this.h;
    }

    public long B() {
        if (this.i == 0) {
            this.i = com.byd.cleardata.i.e.o(w());
        }
        return this.i;
    }

    public String C() {
        return this.f2590b;
    }

    @SuppressLint({"NewApi"})
    public String D() {
        String str = this.f2591c;
        if (str != null) {
            return Paths.get(str, new String[0]).getParent().toString();
        }
        return null;
    }

    public String E() {
        return this.f2591c;
    }

    public int F() {
        return this.f;
    }

    public f G() {
        I();
        return this.f2592d;
    }

    public int H() {
        switch (b.f2593a[this.f2592d.ordinal()]) {
            case 1:
            case 2:
                return R.string.other;
            case 3:
                return R.string.directory;
            case 4:
                return R.string.audio;
            case 5:
                return R.string.video;
            case 6:
                return R.string.image;
            case 7:
                return R.string.compress_type;
            case 8:
                return R.string.apk;
            default:
                return R.string.document;
        }
    }

    public void I() {
        if (this.f2592d == f.UNINITIALIZED) {
            this.f2592d = com.byd.cleardata.i.e.G(this.f2591c) ? f.DIRECTORY : i.d(this);
        }
    }

    public boolean J() {
        return this.e;
    }

    public boolean K() {
        f fVar = this.f2592d;
        return fVar == f.DIRECTORY || fVar == f.DOWNLOAD_DIRECTORY;
    }

    public boolean L() {
        return this.f2592d == f.DOWNLOAD_DIRECTORY;
    }

    public boolean M(String str) {
        return K() && com.byd.cleardata.i.e.F(this.f2591c, str);
    }

    public boolean N() {
        f fVar = this.f2592d;
        return (fVar == f.DIRECTORY || fVar == f.DOWNLOAD_DIRECTORY) ? false : true;
    }

    public boolean O() {
        return this.f2592d == f.NONE;
    }

    public void P(boolean z) {
        this.e = z;
    }

    public void Q(f fVar) {
        this.g = fVar;
    }

    public void R(File file) {
        this.f2590b = file.getName();
        String absolutePath = file.getAbsolutePath();
        this.f2591c = absolutePath;
        this.e = false;
        this.f2592d = com.byd.cleardata.i.e.G(absolutePath) ? f.DIRECTORY : f.UNINITIALIZED;
    }

    public void S(long j) {
        this.h = j;
    }

    public void T(long j) {
        this.i = j;
    }

    public void U(String str) {
        this.f2590b = str;
    }

    public void V(String str) {
        this.f2591c = str;
    }

    public void W(int i) {
        this.f = i;
    }

    public void X(f fVar) {
        f fVar2 = this.f2592d;
        if (fVar2 == f.UNINITIALIZED || fVar2 == f.UNKNOWN) {
            this.f2592d = fVar;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f2591c, ((d) obj).f2591c);
    }

    public int hashCode() {
        return Objects.hash(this.f2591c);
    }

    public String toString() {
        return "FileItem{name='" + this.f2590b + "', type=" + this.f2592d + ", isChecked=" + this.e + ", path = " + this.f2591c + '}';
    }

    public boolean u() {
        return this.f2592d != f.NONE && com.byd.cleardata.i.e.i(this.f2591c);
    }

    public f v() {
        return this.g;
    }

    public File w() {
        if (this.f2591c != null) {
            return new File(this.f2591c);
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2590b);
        parcel.writeString(this.f2591c);
        f fVar = this.f2592d;
        parcel.writeInt(fVar == null ? -1 : fVar.ordinal());
        parcel.writeByte(this.e ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f);
        f fVar2 = this.g;
        parcel.writeInt(fVar2 != null ? fVar2.ordinal() : -1);
        parcel.writeLong(this.h);
        parcel.writeLong(this.i);
    }

    public long x() {
        if (K()) {
            return com.byd.cleardata.i.e.n(w());
        }
        return 1L;
    }
}
