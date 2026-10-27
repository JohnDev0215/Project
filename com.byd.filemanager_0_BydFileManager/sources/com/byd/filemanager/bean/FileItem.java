package com.byd.filemanager.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import com.byd.filemanager.R;
import com.byd.filemanager.k.h;
import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class FileItem implements Parcelable {
    public static final Parcelable.Creator<FileItem> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3523b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3524c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private FileType f3525d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3526e;
    private int f;
    private FileType g;
    private long h;
    private long i;

    class a implements Parcelable.Creator<FileItem> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FileItem createFromParcel(Parcel parcel) {
            return new FileItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FileItem[] newArray(int i) {
            return new FileItem[i];
        }
    }

    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3527a;

        static {
            int[] iArr = new int[FileType.values().length];
            f3527a = iArr;
            try {
                iArr[FileType.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3527a[FileType.UNINITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3527a[FileType.DIRECTORY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3527a[FileType.AUDIO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3527a[FileType.VIDEO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3527a[FileType.IMAGE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3527a[FileType.COMPRESS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3527a[FileType.APK.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public FileItem() {
        this(null, FileType.UNINITIALIZED);
    }

    public static FileItem y(String str) {
        FileItem fileItem = new FileItem();
        fileItem.f3523b = str;
        fileItem.f3525d = FileType.UNINITIALIZED;
        return fileItem;
    }

    @SuppressLint({"NewApi"})
    public static FileItem z(String str) {
        FileItem fileItem = new FileItem();
        fileItem.f3523b = Paths.get(str, new String[0]).getFileName().toString();
        fileItem.f3524c = str;
        fileItem.f3525d = FileType.UNINITIALIZED;
        return fileItem;
    }

    public long A() {
        if (this.h == 0) {
            this.h = com.byd.filemanager.k.d.q(this.f3524c);
        }
        return this.h;
    }

    public long B() {
        if (this.i == 0) {
            this.i = com.byd.filemanager.k.d.o(w());
        }
        return this.i;
    }

    public String C() {
        return this.f3523b;
    }

    @SuppressLint({"NewApi"})
    public String D() {
        String str = this.f3524c;
        if (str != null) {
            return Paths.get(str, new String[0]).getParent().toString();
        }
        return null;
    }

    public String E() {
        return this.f3524c;
    }

    public int F() {
        return this.f;
    }

    public FileType G() {
        I();
        return this.f3525d;
    }

    public int H() {
        switch (b.f3527a[this.f3525d.ordinal()]) {
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
                return R.string.compressed_packages;
            case 8:
                return R.string.apk;
            default:
                return R.string.document;
        }
    }

    public void I() {
        if (this.f3525d == FileType.UNINITIALIZED) {
            if (com.byd.filemanager.k.d.G(this.f3524c)) {
                this.f3525d = FileType.DIRECTORY;
            } else {
                this.f3525d = h.d(this);
            }
        }
    }

    public boolean J() {
        return this.f3526e;
    }

    public boolean K() {
        FileType fileType = this.f3525d;
        return fileType == FileType.DIRECTORY || fileType == FileType.DOWNLOAD_DIRECTORY;
    }

    public boolean L() {
        return this.f3525d == FileType.DOWNLOAD_DIRECTORY;
    }

    public boolean M(String str) {
        return K() && com.byd.filemanager.k.d.F(this.f3524c, str);
    }

    public boolean N() {
        FileType fileType = this.f3525d;
        return (fileType == FileType.DIRECTORY || fileType == FileType.DOWNLOAD_DIRECTORY) ? false : true;
    }

    public boolean O() {
        return this.f3525d == FileType.NONE;
    }

    public void P(boolean z) {
        this.f3526e = z;
    }

    public void Q(FileType fileType) {
        this.g = fileType;
    }

    public void R(File file) {
        this.f3523b = file.getName();
        String absolutePath = file.getAbsolutePath();
        this.f3524c = absolutePath;
        this.f3526e = false;
        this.f3525d = com.byd.filemanager.k.d.G(absolutePath) ? FileType.DIRECTORY : FileType.UNINITIALIZED;
    }

    public void S(long j) {
        this.h = j;
    }

    public void T(long j) {
        this.i = j;
    }

    public void U(String str) {
        this.f3523b = str;
    }

    public void V(String str) {
        this.f3524c = str;
    }

    public void W(int i) {
        this.f = i;
    }

    public void X(FileType fileType) {
        FileType fileType2 = this.f3525d;
        if (fileType2 == FileType.UNINITIALIZED || fileType2 == FileType.UNKNOWN) {
            this.f3525d = fileType;
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
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f3524c, ((FileItem) obj).f3524c);
    }

    public int hashCode() {
        return Objects.hash(this.f3524c);
    }

    public String toString() {
        return "FileItem{name='" + this.f3523b + "', type=" + this.f3525d + ", isChecked=" + this.f3526e + ", path = " + this.f3524c + '}';
    }

    public boolean u() {
        return this.f3525d != FileType.NONE && com.byd.filemanager.k.d.h(this.f3524c);
    }

    public FileType v() {
        return this.g;
    }

    public File w() {
        if (this.f3524c != null) {
            return new File(this.f3524c);
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3523b);
        parcel.writeString(this.f3524c);
        FileType fileType = this.f3525d;
        parcel.writeInt(fileType == null ? -1 : fileType.ordinal());
        parcel.writeByte(this.f3526e ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f);
        FileType fileType2 = this.g;
        parcel.writeInt(fileType2 != null ? fileType2.ordinal() : -1);
        parcel.writeLong(this.h);
        parcel.writeLong(this.i);
    }

    public long x() {
        if (K()) {
            return com.byd.filemanager.k.d.n(w());
        }
        return 1L;
    }

    public FileItem(File file) {
        this(file, FileType.UNINITIALIZED);
    }

    public FileItem(File file, FileType fileType) {
        this.g = FileType.UNKNOWN;
        if (com.byd.filemanager.k.d.g(file)) {
            this.f3523b = file.getName();
            this.f3524c = file.getAbsolutePath();
            this.f3525d = file.isDirectory() ? FileType.DIRECTORY : fileType;
            return;
        }
        this.f3525d = FileType.NONE;
    }

    protected FileItem(Parcel parcel) {
        this.g = FileType.UNKNOWN;
        this.f3523b = parcel.readString();
        this.f3524c = parcel.readString();
        int i = parcel.readInt();
        this.f3525d = i == -1 ? null : FileType.values()[i];
        this.f3526e = parcel.readByte() != 0;
        this.f = parcel.readInt();
        int i2 = parcel.readInt();
        this.g = i2 != -1 ? FileType.values()[i2] : null;
        this.h = parcel.readLong();
        this.i = parcel.readLong();
    }
}
