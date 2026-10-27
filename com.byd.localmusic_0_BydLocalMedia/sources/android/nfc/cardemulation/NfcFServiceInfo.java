package android.nfc.cardemulation;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.proto.ProtoOutputStream;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes2.dex */
public final class NfcFServiceInfo implements Parcelable {
    public static final Parcelable.Creator<NfcFServiceInfo> CREATOR = new Parcelable.Creator<NfcFServiceInfo>() { // from class: android.nfc.cardemulation.NfcFServiceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NfcFServiceInfo createFromParcel(Parcel parcel) {
            return new NfcFServiceInfo(ResolveInfo.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? parcel.readString() : null, parcel.readString(), parcel.readInt() != 0 ? parcel.readString() : null, parcel.readInt(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NfcFServiceInfo[] newArray(int i) {
            return new NfcFServiceInfo[i];
        }
    };
    private static final String DEFAULT_T3T_PMM = "FFFFFFFFFFFFFFFF";
    static final String TAG = "NfcFServiceInfo";
    final String mDescription;
    String mDynamicNfcid2;
    String mDynamicSystemCode;
    final String mNfcid2;
    final ResolveInfo mService;
    final String mSystemCode;
    final String mT3tPmm;
    final int mUid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NfcFServiceInfo(ResolveInfo resolveInfo, String str, String str2, String str3, String str4, String str5, int i, String str6) {
        this.mService = resolveInfo;
        this.mDescription = str;
        this.mSystemCode = str2;
        this.mDynamicSystemCode = str3;
        this.mNfcid2 = str4;
        this.mDynamicNfcid2 = str5;
        this.mUid = i;
        this.mT3tPmm = str6;
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x0168  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public NfcFServiceInfo(android.content.pm.PackageManager r17, android.content.pm.ResolveInfo r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.nfc.cardemulation.NfcFServiceInfo.<init>(android.content.pm.PackageManager, android.content.pm.ResolveInfo):void");
    }

    public ComponentName getComponent() {
        return new ComponentName(this.mService.serviceInfo.packageName, this.mService.serviceInfo.name);
    }

    public String getSystemCode() {
        String str = this.mDynamicSystemCode;
        return str == null ? this.mSystemCode : str;
    }

    public void setOrReplaceDynamicSystemCode(String str) {
        this.mDynamicSystemCode = str;
    }

    public String getNfcid2() {
        String str = this.mDynamicNfcid2;
        return str == null ? this.mNfcid2 : str;
    }

    public void setOrReplaceDynamicNfcid2(String str) {
        this.mDynamicNfcid2 = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getUid() {
        return this.mUid;
    }

    public String getT3tPmm() {
        return this.mT3tPmm;
    }

    public CharSequence loadLabel(PackageManager packageManager) {
        return this.mService.loadLabel(packageManager);
    }

    public Drawable loadIcon(PackageManager packageManager) {
        return this.mService.loadIcon(packageManager);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NfcFService: ");
        sb.append(getComponent());
        sb.append(", description: " + this.mDescription);
        sb.append(", System Code: " + this.mSystemCode);
        if (this.mDynamicSystemCode != null) {
            sb.append(", dynamic System Code: " + this.mDynamicSystemCode);
        }
        sb.append(", NFCID2: " + this.mNfcid2);
        if (this.mDynamicNfcid2 != null) {
            sb.append(", dynamic NFCID2: " + this.mDynamicNfcid2);
        }
        sb.append(", T3T PMM:" + this.mT3tPmm);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NfcFServiceInfo)) {
            return false;
        }
        NfcFServiceInfo nfcFServiceInfo = (NfcFServiceInfo) obj;
        return nfcFServiceInfo.getComponent().equals(getComponent()) && nfcFServiceInfo.mSystemCode.equalsIgnoreCase(this.mSystemCode) && nfcFServiceInfo.mNfcid2.equalsIgnoreCase(this.mNfcid2) && nfcFServiceInfo.mT3tPmm.equalsIgnoreCase(this.mT3tPmm);
    }

    public int hashCode() {
        return getComponent().hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.mService.writeToParcel(parcel, i);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mSystemCode);
        parcel.writeInt(this.mDynamicSystemCode != null ? 1 : 0);
        String str = this.mDynamicSystemCode;
        if (str != null) {
            parcel.writeString(str);
        }
        parcel.writeString(this.mNfcid2);
        parcel.writeInt(this.mDynamicNfcid2 == null ? 0 : 1);
        String str2 = this.mDynamicNfcid2;
        if (str2 != null) {
            parcel.writeString(str2);
        }
        parcel.writeInt(this.mUid);
        parcel.writeString(this.mT3tPmm);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("    " + getComponent() + " (Description: " + getDescription() + ")");
        StringBuilder sb = new StringBuilder();
        sb.append("    System Code: ");
        sb.append(getSystemCode());
        printWriter.println(sb.toString());
        printWriter.println("    NFCID2: " + getNfcid2());
        printWriter.println("    T3tPmm: " + getT3tPmm());
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream) {
        getComponent().dumpDebug(protoOutputStream, 1146756268033L);
        protoOutputStream.write(1138166333442L, getDescription());
        protoOutputStream.write(1138166333443L, getSystemCode());
        protoOutputStream.write(1138166333444L, getNfcid2());
        protoOutputStream.write(1138166333445L, getT3tPmm());
    }
}
