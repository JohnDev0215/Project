package android.view.inputmethod;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Printer;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
public final class InputMethodInfo implements Parcelable {
    public static final Parcelable.Creator<InputMethodInfo> CREATOR = new Parcelable.Creator<InputMethodInfo>() { // from class: android.view.inputmethod.InputMethodInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InputMethodInfo createFromParcel(Parcel parcel) {
            return new InputMethodInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InputMethodInfo[] newArray(int i) {
            return new InputMethodInfo[i];
        }
    };
    static final String TAG = "InputMethodInfo";
    private final boolean mForceDefault;
    final String mId;
    private final boolean mInlineSuggestionsEnabled;
    private final boolean mIsAuxIme;
    final int mIsDefaultResId;
    final boolean mIsVrOnly;
    final ResolveInfo mService;
    final String mSettingsActivityName;
    private final InputMethodSubtypeArray mSubtypes;
    private final boolean mSupportsSwitchingToNextInputMethod;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static String computeId(ResolveInfo resolveInfo) {
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        return new ComponentName(serviceInfo.packageName, serviceInfo.name).flattenToShortString();
    }

    public InputMethodInfo(Context context, ResolveInfo resolveInfo) throws XmlPullParserException, IOException {
        this(context, resolveInfo, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x011b, code lost:
    
        if (r6 == null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x011d, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0124, code lost:
    
        if (r5.size() != 0) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0126, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0128, code lost:
    
        r3 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x012a, code lost:
    
        if (r21 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x012c, code lost:
    
        r4 = r21.size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0130, code lost:
    
        if (r0 >= r4) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0132, code lost:
    
        r6 = r21.get(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x013c, code lost:
    
        if (r5.contains(r6) != false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x013e, code lost:
    
        r5.add(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0142, code lost:
    
        android.util.Slog.w(android.view.inputmethod.InputMethodInfo.TAG, "Duplicated subtype definition found: " + r6.getLocale() + ", " + r6.getMode());
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0168, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x016b, code lost:
    
        r18.mSubtypes = new android.view.inputmethod.InputMethodSubtypeArray(r5);
        r18.mSettingsActivityName = r11;
        r18.mIsDefaultResId = r14;
        r18.mIsAuxIme = r3;
        r18.mSupportsSwitchingToNextInputMethod = r15;
        r18.mInlineSuggestionsEnabled = r10;
        r18.mIsVrOnly = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x017e, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public InputMethodInfo(android.content.Context r19, android.content.pm.ResolveInfo r20, java.util.List<android.view.inputmethod.InputMethodSubtype> r21) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.inputmethod.InputMethodInfo.<init>(android.content.Context, android.content.pm.ResolveInfo, java.util.List):void");
    }

    InputMethodInfo(Parcel parcel) {
        this.mId = parcel.readString();
        this.mSettingsActivityName = parcel.readString();
        this.mIsDefaultResId = parcel.readInt();
        this.mIsAuxIme = parcel.readInt() == 1;
        this.mSupportsSwitchingToNextInputMethod = parcel.readInt() == 1;
        this.mInlineSuggestionsEnabled = parcel.readInt() == 1;
        this.mIsVrOnly = parcel.readBoolean();
        this.mService = ResolveInfo.CREATOR.createFromParcel(parcel);
        this.mSubtypes = new InputMethodSubtypeArray(parcel);
        this.mForceDefault = false;
    }

    public InputMethodInfo(String str, String str2, CharSequence charSequence, String str3) {
        this(buildDummyResolveInfo(str, str2, charSequence), false, str3, null, 0, false, true, false, false);
    }

    public InputMethodInfo(ResolveInfo resolveInfo, boolean z, String str, List<InputMethodSubtype> list, int i, boolean z2) {
        this(resolveInfo, z, str, list, i, z2, true, false, false);
    }

    public InputMethodInfo(ResolveInfo resolveInfo, boolean z, String str, List<InputMethodSubtype> list, int i, boolean z2, boolean z3, boolean z4) {
        this(resolveInfo, z, str, list, i, z2, z3, false, z4);
    }

    public InputMethodInfo(ResolveInfo resolveInfo, boolean z, String str, List<InputMethodSubtype> list, int i, boolean z2, boolean z3, boolean z4, boolean z5) {
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        this.mService = resolveInfo;
        this.mId = new ComponentName(serviceInfo.packageName, serviceInfo.name).flattenToShortString();
        this.mSettingsActivityName = str;
        this.mIsDefaultResId = i;
        this.mIsAuxIme = z;
        this.mSubtypes = new InputMethodSubtypeArray(list);
        this.mForceDefault = z2;
        this.mSupportsSwitchingToNextInputMethod = z3;
        this.mInlineSuggestionsEnabled = z4;
        this.mIsVrOnly = z5;
    }

    private static ResolveInfo buildDummyResolveInfo(String str, String str2, CharSequence charSequence) {
        ResolveInfo resolveInfo = new ResolveInfo();
        ServiceInfo serviceInfo = new ServiceInfo();
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.packageName = str;
        applicationInfo.enabled = true;
        serviceInfo.applicationInfo = applicationInfo;
        serviceInfo.enabled = true;
        serviceInfo.packageName = str;
        serviceInfo.name = str2;
        serviceInfo.exported = true;
        serviceInfo.nonLocalizedLabel = charSequence;
        resolveInfo.serviceInfo = serviceInfo;
        return resolveInfo;
    }

    public String getId() {
        return this.mId;
    }

    public String getPackageName() {
        return this.mService.serviceInfo.packageName;
    }

    public String getServiceName() {
        return this.mService.serviceInfo.name;
    }

    public ServiceInfo getServiceInfo() {
        return this.mService.serviceInfo;
    }

    public ComponentName getComponent() {
        return new ComponentName(this.mService.serviceInfo.packageName, this.mService.serviceInfo.name);
    }

    public CharSequence loadLabel(PackageManager packageManager) {
        return this.mService.loadLabel(packageManager);
    }

    public Drawable loadIcon(PackageManager packageManager) {
        return this.mService.loadIcon(packageManager);
    }

    public String getSettingsActivity() {
        return this.mSettingsActivityName;
    }

    public boolean isVrOnly() {
        return this.mIsVrOnly;
    }

    public int getSubtypeCount() {
        return this.mSubtypes.getCount();
    }

    public InputMethodSubtype getSubtypeAt(int i) {
        return this.mSubtypes.get(i);
    }

    public int getIsDefaultResourceId() {
        return this.mIsDefaultResId;
    }

    public boolean isDefault(Context context) {
        if (this.mForceDefault) {
            return true;
        }
        try {
            if (getIsDefaultResourceId() == 0) {
                return false;
            }
            return context.createPackageContext(getPackageName(), 0).getResources().getBoolean(getIsDefaultResourceId());
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
            return false;
        }
    }

    public void dump(Printer printer, String str) {
        printer.println(str + "mId=" + this.mId + " mSettingsActivityName=" + this.mSettingsActivityName + " mIsVrOnly=" + this.mIsVrOnly + " mSupportsSwitchingToNextInputMethod=" + this.mSupportsSwitchingToNextInputMethod + " mInlineSuggestionsEnabled=" + this.mInlineSuggestionsEnabled);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("mIsDefaultResId=0x");
        sb.append(Integer.toHexString(this.mIsDefaultResId));
        printer.println(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("Service:");
        printer.println(sb2.toString());
        this.mService.dump(printer, str + "  ");
    }

    public String toString() {
        return "InputMethodInfo{" + this.mId + ", settings: " + this.mSettingsActivityName + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof InputMethodInfo)) {
            return this.mId.equals(((InputMethodInfo) obj).mId);
        }
        return false;
    }

    public int hashCode() {
        return this.mId.hashCode();
    }

    public boolean isSystem() {
        return (this.mService.serviceInfo.applicationInfo.flags & 1) != 0;
    }

    public boolean isAuxiliaryIme() {
        return this.mIsAuxIme;
    }

    public boolean supportsSwitchingToNextInputMethod() {
        return this.mSupportsSwitchingToNextInputMethod;
    }

    public boolean isInlineSuggestionsEnabled() {
        return this.mInlineSuggestionsEnabled;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeString(this.mSettingsActivityName);
        parcel.writeInt(this.mIsDefaultResId);
        parcel.writeInt(this.mIsAuxIme ? 1 : 0);
        parcel.writeInt(this.mSupportsSwitchingToNextInputMethod ? 1 : 0);
        parcel.writeInt(this.mInlineSuggestionsEnabled ? 1 : 0);
        parcel.writeBoolean(this.mIsVrOnly);
        this.mService.writeToParcel(parcel, i);
        this.mSubtypes.writeToParcel(parcel);
    }
}
