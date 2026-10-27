package android.os.storage;

import android.annotation.SystemApi;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.provider.DocumentsContract;
import com.android.internal.content.NativeLibraryHelper;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.Preconditions;
import java.io.CharArrayWriter;
import java.io.File;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class StorageVolume implements Parcelable {
    private static final String ACTION_OPEN_EXTERNAL_DIRECTORY = "android.os.storage.action.OPEN_EXTERNAL_DIRECTORY";
    public static final Parcelable.Creator<StorageVolume> CREATOR = new Parcelable.Creator<StorageVolume>() { // from class: android.os.storage.StorageVolume.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StorageVolume createFromParcel(Parcel parcel) {
            return new StorageVolume(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StorageVolume[] newArray(int i) {
            return new StorageVolume[i];
        }
    };
    public static final String EXTRA_DIRECTORY_NAME = "android.os.storage.extra.DIRECTORY_NAME";
    public static final String EXTRA_STORAGE_VOLUME = "android.os.storage.extra.STORAGE_VOLUME";
    public static final int STORAGE_ID_INVALID = 0;
    public static final int STORAGE_ID_PRIMARY = 65537;
    private final boolean mAllowMassStorage;
    private final String mDescription;
    private final String mDeviceType;
    private final boolean mEmulated;
    private final String mFsUuid;
    private final String mId;
    private final File mInternalPath;
    private final long mMaxFileSize;
    private final UserHandle mOwner;
    private final File mPath;
    private final boolean mPrimary;
    private final boolean mRemovable;
    private final String mState;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public StorageVolume(String str, File file, File file2, String str2, boolean z, boolean z2, boolean z3, boolean z4, long j, UserHandle userHandle, String str3, String str4) {
        this.mId = (String) Preconditions.checkNotNull(str);
        this.mPath = (File) Preconditions.checkNotNull(file);
        this.mInternalPath = (File) Preconditions.checkNotNull(file2);
        this.mDescription = (String) Preconditions.checkNotNull(str2);
        this.mPrimary = z;
        this.mRemovable = z2;
        this.mEmulated = z3;
        this.mAllowMassStorage = z4;
        this.mMaxFileSize = j;
        this.mOwner = (UserHandle) Preconditions.checkNotNull(userHandle);
        this.mFsUuid = str3;
        this.mState = (String) Preconditions.checkNotNull(str4);
        this.mDeviceType = "";
    }

    public StorageVolume(String str, File file, File file2, String str2, boolean z, boolean z2, boolean z3, boolean z4, long j, UserHandle userHandle, String str3, String str4, String str5) {
        this.mId = (String) Preconditions.checkNotNull(str);
        this.mPath = (File) Preconditions.checkNotNull(file);
        this.mInternalPath = (File) Preconditions.checkNotNull(file2);
        this.mDescription = (String) Preconditions.checkNotNull(str2);
        this.mPrimary = z;
        this.mRemovable = z2;
        this.mEmulated = z3;
        this.mAllowMassStorage = z4;
        this.mMaxFileSize = j;
        this.mOwner = (UserHandle) Preconditions.checkNotNull(userHandle);
        this.mFsUuid = str3;
        this.mState = (String) Preconditions.checkNotNull(str4);
        this.mDeviceType = (String) Preconditions.checkNotNull(str5);
    }

    private StorageVolume(Parcel parcel) {
        this.mId = parcel.readString8();
        this.mPath = new File(parcel.readString8());
        this.mInternalPath = new File(parcel.readString8());
        this.mDescription = parcel.readString8();
        this.mPrimary = parcel.readInt() != 0;
        this.mRemovable = parcel.readInt() != 0;
        this.mEmulated = parcel.readInt() != 0;
        this.mAllowMassStorage = parcel.readInt() != 0;
        this.mMaxFileSize = parcel.readLong();
        this.mOwner = (UserHandle) parcel.readParcelable(null);
        this.mFsUuid = parcel.readString8();
        this.mState = parcel.readString8();
        this.mDeviceType = parcel.readString8();
    }

    @SystemApi
    public String getId() {
        return this.mId;
    }

    public String getPath() {
        return this.mPath.toString();
    }

    public String getInternalPath() {
        return this.mInternalPath.toString();
    }

    public File getPathFile() {
        return this.mPath;
    }

    public File getDirectory() {
        String str = this.mState;
        str.hashCode();
        if (str.equals(Environment.MEDIA_MOUNTED) || str.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return this.mPath;
        }
        return null;
    }

    public String getDescription(Context context) {
        return this.mDescription;
    }

    public boolean isPrimary() {
        return this.mPrimary;
    }

    public boolean isRemovable() {
        return this.mRemovable;
    }

    public boolean isEmulated() {
        return this.mEmulated;
    }

    public boolean allowMassStorage() {
        return this.mAllowMassStorage;
    }

    public long getMaxFileSize() {
        return this.mMaxFileSize;
    }

    public UserHandle getOwner() {
        return this.mOwner;
    }

    public String getUuid() {
        return this.mFsUuid;
    }

    public String getMediaStoreVolumeName() {
        return isPrimary() ? "external_primary" : getNormalizedUuid();
    }

    public static String normalizeUuid(String str) {
        if (str != null) {
            return str.toLowerCase(Locale.US);
        }
        return null;
    }

    public String getNormalizedUuid() {
        return normalizeUuid(this.mFsUuid);
    }

    public int getFatVolumeId() {
        String str = this.mFsUuid;
        if (str != null && str.length() == 9) {
            try {
                return (int) Long.parseLong(this.mFsUuid.replace(NativeLibraryHelper.CLEAR_ABI_OVERRIDE, ""), 16);
            } catch (NumberFormatException unused) {
            }
        }
        return -1;
    }

    public String getUserLabel() {
        return this.mDescription;
    }

    public String getState() {
        return this.mState;
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    @Deprecated
    public Intent createAccessIntent(String str) {
        if (isPrimary() && str == null) {
            return null;
        }
        if (str != null && !Environment.isStandardDirectory(str)) {
            return null;
        }
        Intent intent = new Intent(ACTION_OPEN_EXTERNAL_DIRECTORY);
        intent.putExtra(EXTRA_STORAGE_VOLUME, this);
        intent.putExtra(EXTRA_DIRECTORY_NAME, str);
        return intent;
    }

    public Intent createOpenDocumentTreeIntent() {
        return new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE).putExtra(DocumentsContract.EXTRA_INITIAL_URI, DocumentsContract.buildRootUri(DocumentsContract.EXTERNAL_STORAGE_PROVIDER_AUTHORITY, isEmulated() ? DocumentsContract.EXTERNAL_STORAGE_PRIMARY_EMULATED_ROOT_ID : this.mFsUuid)).putExtra(DocumentsContract.EXTRA_SHOW_ADVANCED, true);
    }

    public boolean equals(Object obj) {
        File file;
        if (!(obj instanceof StorageVolume) || (file = this.mPath) == null) {
            return false;
        }
        return file.equals(((StorageVolume) obj).mPath);
    }

    public int hashCode() {
        return this.mPath.hashCode();
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("StorageVolume: ").append(this.mDescription);
        if (this.mFsUuid != null) {
            sbAppend.append(" (").append(this.mFsUuid).append(")");
        }
        return sbAppend.toString();
    }

    public String dump() {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        dump(new IndentingPrintWriter(charArrayWriter, "    ", 80));
        return charArrayWriter.toString();
    }

    public void dump(IndentingPrintWriter indentingPrintWriter) {
        indentingPrintWriter.println("StorageVolume:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.printPair("mId", this.mId);
        indentingPrintWriter.printPair("mPath", this.mPath);
        indentingPrintWriter.printPair("mInternalPath", this.mInternalPath);
        indentingPrintWriter.printPair("mDescription", this.mDescription);
        indentingPrintWriter.printPair("mPrimary", Boolean.valueOf(this.mPrimary));
        indentingPrintWriter.printPair("mRemovable", Boolean.valueOf(this.mRemovable));
        indentingPrintWriter.printPair("mEmulated", Boolean.valueOf(this.mEmulated));
        indentingPrintWriter.printPair("mAllowMassStorage", Boolean.valueOf(this.mAllowMassStorage));
        indentingPrintWriter.printPair("mMaxFileSize", Long.valueOf(this.mMaxFileSize));
        indentingPrintWriter.printPair("mOwner", this.mOwner);
        indentingPrintWriter.printPair("mFsUuid", this.mFsUuid);
        indentingPrintWriter.printPair("mState", this.mState);
        indentingPrintWriter.printPair("mDeviceType", this.mDeviceType);
        indentingPrintWriter.decreaseIndent();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString8(this.mId);
        parcel.writeString8(this.mPath.toString());
        parcel.writeString8(this.mInternalPath.toString());
        parcel.writeString8(this.mDescription);
        parcel.writeInt(this.mPrimary ? 1 : 0);
        parcel.writeInt(this.mRemovable ? 1 : 0);
        parcel.writeInt(this.mEmulated ? 1 : 0);
        parcel.writeInt(this.mAllowMassStorage ? 1 : 0);
        parcel.writeLong(this.mMaxFileSize);
        parcel.writeParcelable(this.mOwner, i);
        parcel.writeString8(this.mFsUuid);
        parcel.writeString8(this.mState);
        parcel.writeString8(this.mDeviceType);
    }
}
