package android.view.textservice;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.SettingsStringUtil;
import android.util.PrintWriterPrinter;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class SpellCheckerInfo implements Parcelable {
    public static final Parcelable.Creator<SpellCheckerInfo> CREATOR = new Parcelable.Creator<SpellCheckerInfo>() { // from class: android.view.textservice.SpellCheckerInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpellCheckerInfo createFromParcel(Parcel parcel) {
            return new SpellCheckerInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpellCheckerInfo[] newArray(int i) {
            return new SpellCheckerInfo[i];
        }
    };
    private static final String TAG = "SpellCheckerInfo";
    private final String mId;
    private final int mLabel;
    private final ResolveInfo mService;
    private final String mSettingsActivityName;
    private final ArrayList<SpellCheckerSubtype> mSubtypes;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x00bc, code lost:
    
        if (r3 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00be, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00c1, code lost:
    
        r19.mLabel = r9;
        r19.mSettingsActivityName = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00c5, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SpellCheckerInfo(android.content.Context r20, android.content.pm.ResolveInfo r21) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.textservice.SpellCheckerInfo.<init>(android.content.Context, android.content.pm.ResolveInfo):void");
    }

    public SpellCheckerInfo(Parcel parcel) {
        ArrayList<SpellCheckerSubtype> arrayList = new ArrayList<>();
        this.mSubtypes = arrayList;
        this.mLabel = parcel.readInt();
        this.mId = parcel.readString();
        this.mSettingsActivityName = parcel.readString();
        this.mService = ResolveInfo.CREATOR.createFromParcel(parcel);
        parcel.readTypedList(arrayList, SpellCheckerSubtype.CREATOR);
    }

    public String getId() {
        return this.mId;
    }

    public ComponentName getComponent() {
        return new ComponentName(this.mService.serviceInfo.packageName, this.mService.serviceInfo.name);
    }

    public String getPackageName() {
        return this.mService.serviceInfo.packageName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mLabel);
        parcel.writeString(this.mId);
        parcel.writeString(this.mSettingsActivityName);
        this.mService.writeToParcel(parcel, i);
        parcel.writeTypedList(this.mSubtypes);
    }

    public CharSequence loadLabel(PackageManager packageManager) {
        return (this.mLabel == 0 || packageManager == null) ? "" : packageManager.getText(getPackageName(), this.mLabel, this.mService.serviceInfo.applicationInfo);
    }

    public Drawable loadIcon(PackageManager packageManager) {
        return this.mService.loadIcon(packageManager);
    }

    public ServiceInfo getServiceInfo() {
        return this.mService.serviceInfo;
    }

    public String getSettingsActivity() {
        return this.mSettingsActivityName;
    }

    public int getSubtypeCount() {
        return this.mSubtypes.size();
    }

    public SpellCheckerSubtype getSubtypeAt(int i) {
        return this.mSubtypes.get(i);
    }

    public void dump(PrintWriter printWriter, String str) {
        printWriter.println(str + "mId=" + this.mId);
        printWriter.println(str + "mSettingsActivityName=" + this.mSettingsActivityName);
        printWriter.println(str + "Service:");
        this.mService.dump(new PrintWriterPrinter(printWriter), str + "  ");
        int subtypeCount = getSubtypeCount();
        for (int i = 0; i < subtypeCount; i++) {
            SpellCheckerSubtype subtypeAt = getSubtypeAt(i);
            printWriter.println(str + "  Subtype #" + i + SettingsStringUtil.DELIMITER);
            printWriter.println(str + "    locale=" + subtypeAt.getLocale() + " languageTag=" + subtypeAt.getLanguageTag());
            printWriter.println(str + "    extraValue=" + subtypeAt.getExtraValue());
        }
    }
}
