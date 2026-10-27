package android.printservice;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class PrintServiceInfo implements Parcelable {
    public static final Parcelable.Creator<PrintServiceInfo> CREATOR = new Parcelable.Creator<PrintServiceInfo>() { // from class: android.printservice.PrintServiceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrintServiceInfo createFromParcel(Parcel parcel) {
            return new PrintServiceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrintServiceInfo[] newArray(int i) {
            return new PrintServiceInfo[i];
        }
    };
    private static final String LOG_TAG = "PrintServiceInfo";
    private static final String TAG_PRINT_SERVICE = "print-service";
    private final String mAddPrintersActivityName;
    private final String mAdvancedPrintOptionsActivityName;
    private final String mId;
    private boolean mIsEnabled;
    private final ResolveInfo mResolveInfo;
    private final String mSettingsActivityName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PrintServiceInfo(Parcel parcel) {
        this.mId = parcel.readString();
        this.mIsEnabled = parcel.readByte() != 0;
        this.mResolveInfo = (ResolveInfo) parcel.readParcelable(null);
        this.mSettingsActivityName = parcel.readString();
        this.mAddPrintersActivityName = parcel.readString();
        this.mAdvancedPrintOptionsActivityName = parcel.readString();
    }

    public PrintServiceInfo(ResolveInfo resolveInfo, String str, String str2, String str3) {
        this.mId = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name).flattenToString();
        this.mResolveInfo = resolveInfo;
        this.mSettingsActivityName = str;
        this.mAddPrintersActivityName = str2;
        this.mAdvancedPrintOptionsActivityName = str3;
    }

    public ComponentName getComponentName() {
        return new ComponentName(this.mResolveInfo.serviceInfo.packageName, this.mResolveInfo.serviceInfo.name);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d4 A[PHI: r1 r2 r3
  0x00d4: PHI (r1v16 java.lang.String) = (r1v14 java.lang.String), (r1v17 java.lang.String) binds: [B:41:0x00b8, B:45:0x00d2] A[DONT_GENERATE, DONT_INLINE]
  0x00d4: PHI (r2v9 java.lang.String) = (r2v7 java.lang.String), (r2v10 java.lang.String) binds: [B:41:0x00b8, B:45:0x00d2] A[DONT_GENERATE, DONT_INLINE]
  0x00d4: PHI (r3v18 java.lang.String) = (r3v16 java.lang.String), (r3v19 java.lang.String) binds: [B:41:0x00b8, B:45:0x00d2] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.printservice.PrintServiceInfo create(android.content.Context r8, android.content.pm.ResolveInfo r9) {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.printservice.PrintServiceInfo.create(android.content.Context, android.content.pm.ResolveInfo):android.printservice.PrintServiceInfo");
    }

    public String getId() {
        return this.mId;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public void setIsEnabled(boolean z) {
        this.mIsEnabled = z;
    }

    public ResolveInfo getResolveInfo() {
        return this.mResolveInfo;
    }

    public String getSettingsActivityName() {
        return this.mSettingsActivityName;
    }

    public String getAddPrintersActivityName() {
        return this.mAddPrintersActivityName;
    }

    public String getAdvancedOptionsActivityName() {
        return this.mAdvancedPrintOptionsActivityName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeByte(this.mIsEnabled ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.mResolveInfo, 0);
        parcel.writeString(this.mSettingsActivityName);
        parcel.writeString(this.mAddPrintersActivityName);
        parcel.writeString(this.mAdvancedPrintOptionsActivityName);
    }

    public int hashCode() {
        String str = this.mId;
        return (str == null ? 0 : str.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PrintServiceInfo printServiceInfo = (PrintServiceInfo) obj;
        String str = this.mId;
        if (str == null) {
            if (printServiceInfo.mId != null) {
                return false;
            }
        } else if (!str.equals(printServiceInfo.mId)) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PrintServiceInfo{");
        sb.append("id=").append(this.mId);
        sb.append("isEnabled=").append(this.mIsEnabled);
        sb.append(", resolveInfo=").append(this.mResolveInfo);
        sb.append(", settingsActivityName=").append(this.mSettingsActivityName);
        sb.append(", addPrintersActivityName=").append(this.mAddPrintersActivityName);
        sb.append(", advancedPrintOptionsActivityName=").append(this.mAdvancedPrintOptionsActivityName);
        sb.append("}");
        return sb.toString();
    }
}
