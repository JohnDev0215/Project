package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SharedLibraryInfo implements Parcelable {
    public static final Parcelable.Creator<SharedLibraryInfo> CREATOR = new Parcelable.Creator<SharedLibraryInfo>() { // from class: android.content.pm.SharedLibraryInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SharedLibraryInfo createFromParcel(Parcel parcel) {
            return new SharedLibraryInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SharedLibraryInfo[] newArray(int i) {
            return new SharedLibraryInfo[i];
        }
    };
    public static final int TYPE_BUILTIN = 0;
    public static final int TYPE_DYNAMIC = 1;
    public static final int TYPE_STATIC = 2;
    public static final int VERSION_UNDEFINED = -1;
    private final List<String> mCodePaths;
    private final VersionedPackage mDeclaringPackage;
    private List<SharedLibraryInfo> mDependencies;
    private final List<VersionedPackage> mDependentPackages;
    private final String mName;
    private final String mPackageName;
    private final String mPath;
    private final int mType;
    private final long mVersion;

    @Retention(RetentionPolicy.SOURCE)
    @interface Type {
    }

    private static String typeToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "unknown" : "static" : "dynamic" : "builtin";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SharedLibraryInfo(String str, String str2, List<String> list, String str3, long j, int i, VersionedPackage versionedPackage, List<VersionedPackage> list2, List<SharedLibraryInfo> list3) {
        this.mPath = str;
        this.mPackageName = str2;
        this.mCodePaths = list;
        this.mName = str3;
        this.mVersion = j;
        this.mType = i;
        this.mDeclaringPackage = versionedPackage;
        this.mDependentPackages = list2;
        this.mDependencies = list3;
    }

    private SharedLibraryInfo(Parcel parcel) {
        this.mPath = parcel.readString8();
        this.mPackageName = parcel.readString8();
        if (parcel.readInt() != 0) {
            this.mCodePaths = Arrays.asList(parcel.createString8Array());
        } else {
            this.mCodePaths = null;
        }
        this.mName = parcel.readString8();
        this.mVersion = parcel.readLong();
        this.mType = parcel.readInt();
        this.mDeclaringPackage = (VersionedPackage) parcel.readParcelable(null);
        this.mDependentPackages = parcel.readArrayList(null);
        this.mDependencies = parcel.createTypedArrayList(CREATOR);
    }

    public int getType() {
        return this.mType;
    }

    public String getName() {
        return this.mName;
    }

    public String getPath() {
        return this.mPath;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public List<String> getAllCodePaths() {
        if (getPath() != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getPath());
            return arrayList;
        }
        return this.mCodePaths;
    }

    public void addDependency(SharedLibraryInfo sharedLibraryInfo) {
        if (sharedLibraryInfo == null) {
            return;
        }
        if (this.mDependencies == null) {
            this.mDependencies = new ArrayList();
        }
        this.mDependencies.add(sharedLibraryInfo);
    }

    public void clearDependencies() {
        this.mDependencies = null;
    }

    public List<SharedLibraryInfo> getDependencies() {
        return this.mDependencies;
    }

    @Deprecated
    public int getVersion() {
        long j = this.mVersion;
        if (j >= 0) {
            j &= 2147483647L;
        }
        return (int) j;
    }

    public long getLongVersion() {
        return this.mVersion;
    }

    public boolean isBuiltin() {
        return this.mType == 0;
    }

    public boolean isDynamic() {
        return this.mType == 1;
    }

    public boolean isStatic() {
        return this.mType == 2;
    }

    public VersionedPackage getDeclaringPackage() {
        return this.mDeclaringPackage;
    }

    public List<VersionedPackage> getDependentPackages() {
        List<VersionedPackage> list = this.mDependentPackages;
        return list == null ? Collections.emptyList() : list;
    }

    public String toString() {
        return "SharedLibraryInfo{name:" + this.mName + ", type:" + typeToString(this.mType) + ", version:" + this.mVersion + (!getDependentPackages().isEmpty() ? " has dependents" : "") + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString8(this.mPath);
        parcel.writeString8(this.mPackageName);
        if (this.mCodePaths != null) {
            parcel.writeInt(1);
            List<String> list = this.mCodePaths;
            parcel.writeString8Array((String[]) list.toArray(new String[list.size()]));
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString8(this.mName);
        parcel.writeLong(this.mVersion);
        parcel.writeInt(this.mType);
        parcel.writeParcelable(this.mDeclaringPackage, i);
        parcel.writeList(this.mDependentPackages);
        parcel.writeTypedList(this.mDependencies);
    }
}
