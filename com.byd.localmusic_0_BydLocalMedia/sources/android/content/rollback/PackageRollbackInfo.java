package android.content.rollback;

import android.annotation.SystemApi;
import android.content.pm.VersionedPackage;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.IntArray;
import android.util.SparseLongArray;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class PackageRollbackInfo implements Parcelable {
    public static final Parcelable.Creator<PackageRollbackInfo> CREATOR = new Parcelable.Creator<PackageRollbackInfo>() { // from class: android.content.rollback.PackageRollbackInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PackageRollbackInfo createFromParcel(Parcel parcel) {
            return new PackageRollbackInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PackageRollbackInfo[] newArray(int i) {
            return new PackageRollbackInfo[i];
        }
    };
    private final SparseLongArray mCeSnapshotInodes;
    private final boolean mIsApex;
    private final boolean mIsApkInApex;
    private final IntArray mPendingBackups;
    private final ArrayList<RestoreInfo> mPendingRestores;
    private final int mRollbackDataPolicy;
    private final IntArray mSnapshottedUsers;
    private final VersionedPackage mVersionRolledBackFrom;
    private final VersionedPackage mVersionRolledBackTo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static class RestoreInfo {
        public final int appId;
        public final String seInfo;
        public final int userId;

        public RestoreInfo(int i, int i2, String str) {
            this.userId = i;
            this.appId = i2;
            this.seInfo = str;
        }
    }

    public String getPackageName() {
        return this.mVersionRolledBackFrom.getPackageName();
    }

    public VersionedPackage getVersionRolledBackFrom() {
        return this.mVersionRolledBackFrom;
    }

    public VersionedPackage getVersionRolledBackTo() {
        return this.mVersionRolledBackTo;
    }

    public void addPendingBackup(int i) {
        this.mPendingBackups.add(i);
    }

    public IntArray getPendingBackups() {
        return this.mPendingBackups;
    }

    public ArrayList<RestoreInfo> getPendingRestores() {
        return this.mPendingRestores;
    }

    public RestoreInfo getRestoreInfo(int i) {
        for (RestoreInfo restoreInfo : this.mPendingRestores) {
            if (restoreInfo.userId == i) {
                return restoreInfo;
            }
        }
        return null;
    }

    public void removeRestoreInfo(RestoreInfo restoreInfo) {
        this.mPendingRestores.remove(restoreInfo);
    }

    public boolean isApex() {
        return this.mIsApex;
    }

    public int getRollbackDataPolicy() {
        return this.mRollbackDataPolicy;
    }

    public boolean isApkInApex() {
        return this.mIsApkInApex;
    }

    public IntArray getSnapshottedUsers() {
        return this.mSnapshottedUsers;
    }

    public SparseLongArray getCeSnapshotInodes() {
        return this.mCeSnapshotInodes;
    }

    public void putCeSnapshotInode(int i, long j) {
        this.mCeSnapshotInodes.put(i, j);
    }

    public void removePendingBackup(int i) {
        int iIndexOf = this.mPendingBackups.indexOf(i);
        if (iIndexOf != -1) {
            this.mPendingBackups.remove(iIndexOf);
        }
    }

    public void removePendingRestoreInfo(int i) {
        removeRestoreInfo(getRestoreInfo(i));
    }

    public PackageRollbackInfo(VersionedPackage versionedPackage, VersionedPackage versionedPackage2, IntArray intArray, ArrayList<RestoreInfo> arrayList, boolean z, boolean z2, IntArray intArray2, SparseLongArray sparseLongArray) {
        this(versionedPackage, versionedPackage2, intArray, arrayList, z, z2, intArray2, sparseLongArray, 0);
    }

    public PackageRollbackInfo(VersionedPackage versionedPackage, VersionedPackage versionedPackage2, IntArray intArray, ArrayList<RestoreInfo> arrayList, boolean z, boolean z2, IntArray intArray2, SparseLongArray sparseLongArray, int i) {
        this.mVersionRolledBackFrom = versionedPackage;
        this.mVersionRolledBackTo = versionedPackage2;
        this.mPendingBackups = intArray;
        this.mPendingRestores = arrayList;
        this.mIsApex = z;
        this.mRollbackDataPolicy = i;
        this.mIsApkInApex = z2;
        this.mSnapshottedUsers = intArray2;
        this.mCeSnapshotInodes = sparseLongArray;
    }

    private PackageRollbackInfo(Parcel parcel) {
        this.mVersionRolledBackFrom = VersionedPackage.CREATOR.createFromParcel(parcel);
        this.mVersionRolledBackTo = VersionedPackage.CREATOR.createFromParcel(parcel);
        this.mIsApex = parcel.readBoolean();
        this.mIsApkInApex = parcel.readBoolean();
        this.mPendingRestores = null;
        this.mPendingBackups = null;
        this.mSnapshottedUsers = null;
        this.mCeSnapshotInodes = null;
        this.mRollbackDataPolicy = 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.mVersionRolledBackFrom.writeToParcel(parcel, i);
        this.mVersionRolledBackTo.writeToParcel(parcel, i);
        parcel.writeBoolean(this.mIsApex);
        parcel.writeBoolean(this.mIsApkInApex);
    }
}
