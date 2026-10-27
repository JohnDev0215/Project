package android.nfc.cardemulation;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class ApduServiceInfo implements Parcelable {
    public static final Parcelable.Creator<ApduServiceInfo> CREATOR = new Parcelable.Creator<ApduServiceInfo>() { // from class: android.nfc.cardemulation.ApduServiceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApduServiceInfo createFromParcel(Parcel parcel) {
            ResolveInfo resolveInfoCreateFromParcel = ResolveInfo.CREATOR.createFromParcel(parcel);
            String string = parcel.readString();
            boolean z = parcel.readInt() != 0;
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            ArrayList arrayList = new ArrayList();
            if (parcel.readInt() > 0) {
                parcel.readTypedList(arrayList, AidGroup.CREATOR);
            }
            ArrayList arrayList2 = new ArrayList();
            if (parcel.readInt() > 0) {
                parcel.readTypedList(arrayList2, AidGroup.CREATOR);
            }
            return new ApduServiceInfo(resolveInfoCreateFromParcel, z, string, arrayList, arrayList2, parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readString(), string2, string3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApduServiceInfo[] newArray(int i) {
            return new ApduServiceInfo[i];
        }
    };
    static final String TAG = "ApduServiceInfo";
    final int mBannerResourceId;
    final String mDescription;
    final HashMap<String, AidGroup> mDynamicAidGroups;
    String mOffHostName;
    final boolean mOnHost;
    final boolean mRequiresDeviceUnlock;
    final ResolveInfo mService;
    final String mSettingsActivityName;
    final HashMap<String, AidGroup> mStaticAidGroups;
    final String mStaticOffHostName;
    final int mUid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ApduServiceInfo(ResolveInfo resolveInfo, boolean z, String str, ArrayList<AidGroup> arrayList, ArrayList<AidGroup> arrayList2, boolean z2, int i, int i2, String str2, String str3, String str4) {
        this.mService = resolveInfo;
        this.mDescription = str;
        this.mStaticAidGroups = new HashMap<>();
        this.mDynamicAidGroups = new HashMap<>();
        this.mOffHostName = str3;
        this.mStaticOffHostName = str4;
        this.mOnHost = z;
        this.mRequiresDeviceUnlock = z2;
        for (AidGroup aidGroup : arrayList) {
            this.mStaticAidGroups.put(aidGroup.category, aidGroup);
        }
        for (AidGroup aidGroup2 : arrayList2) {
            this.mDynamicAidGroups.put(aidGroup2.category, aidGroup2);
        }
        this.mBannerResourceId = i;
        this.mUid = i2;
        this.mSettingsActivityName = str2;
    }

    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0289: MOVE (r5 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:117:0x0289 */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x028b: MOVE (r5 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:118:0x028b */
    /* JADX WARN: Removed duplicated region for block: B:100:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0075 A[Catch: all -> 0x0288, NameNotFoundException -> 0x028b, TryCatch #5 {NameNotFoundException -> 0x028b, all -> 0x0288, blocks: (B:7:0x0019, B:8:0x0020, B:13:0x002c, B:17:0x0036, B:18:0x003b, B:20:0x0041, B:23:0x004a, B:24:0x0051, B:26:0x0054, B:29:0x005e, B:30:0x0065, B:31:0x0066, B:33:0x0075, B:43:0x00e3, B:44:0x00f8, B:46:0x00fe, B:49:0x0106, B:52:0x0110, B:55:0x0118, B:59:0x0133, B:61:0x013d, B:63:0x0143, B:65:0x0167, B:64:0x0161, B:67:0x016f, B:70:0x0177, B:72:0x017f, B:74:0x0189, B:75:0x0191, B:79:0x019d, B:82:0x01a7, B:84:0x01bb, B:86:0x01c3, B:88:0x01dd, B:87:0x01c9, B:90:0x01e4, B:93:0x01ee, B:95:0x0208, B:97:0x0210, B:99:0x022a, B:98:0x0216, B:101:0x0230, B:104:0x023a, B:106:0x0254, B:108:0x025c, B:110:0x0276, B:109:0x0262, B:34:0x009d, B:36:0x00c1, B:38:0x00c9, B:39:0x00ce, B:41:0x00d8, B:42:0x00dc, B:119:0x028d, B:120:0x0294), top: B:135:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009d A[Catch: all -> 0x0288, NameNotFoundException -> 0x028b, TryCatch #5 {NameNotFoundException -> 0x028b, all -> 0x0288, blocks: (B:7:0x0019, B:8:0x0020, B:13:0x002c, B:17:0x0036, B:18:0x003b, B:20:0x0041, B:23:0x004a, B:24:0x0051, B:26:0x0054, B:29:0x005e, B:30:0x0065, B:31:0x0066, B:33:0x0075, B:43:0x00e3, B:44:0x00f8, B:46:0x00fe, B:49:0x0106, B:52:0x0110, B:55:0x0118, B:59:0x0133, B:61:0x013d, B:63:0x0143, B:65:0x0167, B:64:0x0161, B:67:0x016f, B:70:0x0177, B:72:0x017f, B:74:0x0189, B:75:0x0191, B:79:0x019d, B:82:0x01a7, B:84:0x01bb, B:86:0x01c3, B:88:0x01dd, B:87:0x01c9, B:90:0x01e4, B:93:0x01ee, B:95:0x0208, B:97:0x0210, B:99:0x022a, B:98:0x0216, B:101:0x0230, B:104:0x023a, B:106:0x0254, B:108:0x025c, B:110:0x0276, B:109:0x0262, B:34:0x009d, B:36:0x00c1, B:38:0x00c9, B:39:0x00ce, B:41:0x00d8, B:42:0x00dc, B:119:0x028d, B:120:0x0294), top: B:135:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0110 A[Catch: all -> 0x0288, NameNotFoundException -> 0x028b, TRY_ENTER, TryCatch #5 {NameNotFoundException -> 0x028b, all -> 0x0288, blocks: (B:7:0x0019, B:8:0x0020, B:13:0x002c, B:17:0x0036, B:18:0x003b, B:20:0x0041, B:23:0x004a, B:24:0x0051, B:26:0x0054, B:29:0x005e, B:30:0x0065, B:31:0x0066, B:33:0x0075, B:43:0x00e3, B:44:0x00f8, B:46:0x00fe, B:49:0x0106, B:52:0x0110, B:55:0x0118, B:59:0x0133, B:61:0x013d, B:63:0x0143, B:65:0x0167, B:64:0x0161, B:67:0x016f, B:70:0x0177, B:72:0x017f, B:74:0x0189, B:75:0x0191, B:79:0x019d, B:82:0x01a7, B:84:0x01bb, B:86:0x01c3, B:88:0x01dd, B:87:0x01c9, B:90:0x01e4, B:93:0x01ee, B:95:0x0208, B:97:0x0210, B:99:0x022a, B:98:0x0216, B:101:0x0230, B:104:0x023a, B:106:0x0254, B:108:0x025c, B:110:0x0276, B:109:0x0262, B:34:0x009d, B:36:0x00c1, B:38:0x00c9, B:39:0x00ce, B:41:0x00d8, B:42:0x00dc, B:119:0x028d, B:120:0x0294), top: B:135:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ApduServiceInfo(android.content.pm.PackageManager r18, android.content.pm.ResolveInfo r19, boolean r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 698
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.nfc.cardemulation.ApduServiceInfo.<init>(android.content.pm.PackageManager, android.content.pm.ResolveInfo, boolean):void");
    }

    public ComponentName getComponent() {
        return new ComponentName(this.mService.serviceInfo.packageName, this.mService.serviceInfo.name);
    }

    public String getOffHostSecureElement() {
        return this.mOffHostName;
    }

    public List<String> getAids() {
        ArrayList arrayList = new ArrayList();
        Iterator<AidGroup> it = getAidGroups().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().aids);
        }
        return arrayList;
    }

    public List<String> getPrefixAids() {
        ArrayList arrayList = new ArrayList();
        Iterator<AidGroup> it = getAidGroups().iterator();
        while (it.hasNext()) {
            for (String str : it.next().aids) {
                if (str.endsWith("*")) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    public List<String> getSubsetAids() {
        ArrayList arrayList = new ArrayList();
        Iterator<AidGroup> it = getAidGroups().iterator();
        while (it.hasNext()) {
            for (String str : it.next().aids) {
                if (str.endsWith("#")) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    public AidGroup getDynamicAidGroupForCategory(String str) {
        return this.mDynamicAidGroups.get(str);
    }

    public boolean removeDynamicAidGroupForCategory(String str) {
        return this.mDynamicAidGroups.remove(str) != null;
    }

    public ArrayList<AidGroup> getAidGroups() {
        ArrayList<AidGroup> arrayList = new ArrayList<>();
        Iterator<Map.Entry<String, AidGroup>> it = this.mDynamicAidGroups.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getValue());
        }
        for (Map.Entry<String, AidGroup> entry : this.mStaticAidGroups.entrySet()) {
            if (!this.mDynamicAidGroups.containsKey(entry.getKey())) {
                arrayList.add(entry.getValue());
            }
        }
        return arrayList;
    }

    public String getCategoryForAid(String str) {
        for (AidGroup aidGroup : getAidGroups()) {
            if (aidGroup.aids.contains(str.toUpperCase())) {
                return aidGroup.category;
            }
        }
        return null;
    }

    public boolean hasCategory(String str) {
        return this.mStaticAidGroups.containsKey(str) || this.mDynamicAidGroups.containsKey(str);
    }

    public boolean isOnHost() {
        return this.mOnHost;
    }

    public boolean requiresUnlock() {
        return this.mRequiresDeviceUnlock;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getUid() {
        return this.mUid;
    }

    public void setOrReplaceDynamicAidGroup(AidGroup aidGroup) {
        this.mDynamicAidGroups.put(aidGroup.getCategory(), aidGroup);
    }

    public void setOffHostSecureElement(String str) {
        this.mOffHostName = str;
    }

    public void unsetOffHostSecureElement() {
        this.mOffHostName = this.mStaticOffHostName;
    }

    public CharSequence loadLabel(PackageManager packageManager) {
        return this.mService.loadLabel(packageManager);
    }

    public CharSequence loadAppLabel(PackageManager packageManager) {
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mService.resolvePackageName, 128));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public Drawable loadIcon(PackageManager packageManager) {
        return this.mService.loadIcon(packageManager);
    }

    public Drawable loadBanner(PackageManager packageManager) {
        try {
            return packageManager.getResourcesForApplication(this.mService.serviceInfo.packageName).getDrawable(this.mBannerResourceId);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "Could not load banner.");
            return null;
        } catch (Resources.NotFoundException unused2) {
            Log.e(TAG, "Could not load banner.");
            return null;
        }
    }

    public String getSettingsActivityName() {
        return this.mSettingsActivityName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ApduService: ");
        sb.append(getComponent());
        sb.append(", description: " + this.mDescription);
        sb.append(", Static AID Groups: ");
        Iterator<AidGroup> it = this.mStaticAidGroups.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        sb.append(", Dynamic AID Groups: ");
        Iterator<AidGroup> it2 = this.mDynamicAidGroups.values().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next().toString());
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ApduServiceInfo) {
            return ((ApduServiceInfo) obj).getComponent().equals(getComponent());
        }
        return false;
    }

    public int hashCode() {
        return getComponent().hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.mService.writeToParcel(parcel, i);
        parcel.writeString(this.mDescription);
        parcel.writeInt(this.mOnHost ? 1 : 0);
        parcel.writeString(this.mOffHostName);
        parcel.writeString(this.mStaticOffHostName);
        parcel.writeInt(this.mStaticAidGroups.size());
        if (this.mStaticAidGroups.size() > 0) {
            parcel.writeTypedList(new ArrayList(this.mStaticAidGroups.values()));
        }
        parcel.writeInt(this.mDynamicAidGroups.size());
        if (this.mDynamicAidGroups.size() > 0) {
            parcel.writeTypedList(new ArrayList(this.mDynamicAidGroups.values()));
        }
        parcel.writeInt(this.mRequiresDeviceUnlock ? 1 : 0);
        parcel.writeInt(this.mBannerResourceId);
        parcel.writeInt(this.mUid);
        parcel.writeString(this.mSettingsActivityName);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("    " + getComponent() + " (Description: " + getDescription() + ")");
        if (this.mOnHost) {
            printWriter.println("    On Host Service");
        } else {
            printWriter.println("    Off-host Service");
            printWriter.println("        Current off-host SE:" + this.mOffHostName + " static off-host SE:" + this.mStaticOffHostName);
        }
        printWriter.println("    Static AID groups:");
        for (AidGroup aidGroup : this.mStaticAidGroups.values()) {
            printWriter.println("        Category: " + aidGroup.category);
            Iterator<String> it = aidGroup.aids.iterator();
            while (it.hasNext()) {
                printWriter.println("            AID: " + it.next());
            }
        }
        printWriter.println("    Dynamic AID groups:");
        for (AidGroup aidGroup2 : this.mDynamicAidGroups.values()) {
            printWriter.println("        Category: " + aidGroup2.category);
            Iterator<String> it2 = aidGroup2.aids.iterator();
            while (it2.hasNext()) {
                printWriter.println("            AID: " + it2.next());
            }
        }
        printWriter.println("    Settings Activity: " + this.mSettingsActivityName);
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream) {
        getComponent().dumpDebug(protoOutputStream, 1146756268033L);
        protoOutputStream.write(1138166333442L, getDescription());
        protoOutputStream.write(1133871366147L, this.mOnHost);
        if (!this.mOnHost) {
            protoOutputStream.write(1138166333444L, this.mOffHostName);
            protoOutputStream.write(1138166333445L, this.mStaticOffHostName);
        }
        for (AidGroup aidGroup : this.mStaticAidGroups.values()) {
            long jStart = protoOutputStream.start(2246267895814L);
            aidGroup.dump(protoOutputStream);
            protoOutputStream.end(jStart);
        }
        for (AidGroup aidGroup2 : this.mDynamicAidGroups.values()) {
            long jStart2 = protoOutputStream.start(2246267895814L);
            aidGroup2.dump(protoOutputStream);
            protoOutputStream.end(jStart2);
        }
        protoOutputStream.write(1138166333448L, this.mSettingsActivityName);
    }
}
