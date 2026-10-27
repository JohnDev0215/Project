package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.PathPermission;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PatternMatcher;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class ParsedProvider extends ParsedMainComponent {
    public static final Parcelable.Creator<ParsedProvider> CREATOR = new Parcelable.Creator<ParsedProvider>() { // from class: android.content.pm.parsing.component.ParsedProvider.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedProvider createFromParcel(Parcel parcel) {
            return new ParsedProvider(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedProvider[] newArray(int i) {
            return new ParsedProvider[i];
        }
    };
    private String authority;
    boolean forceUriPermissions;
    boolean grantUriPermissions;
    int initOrder;
    boolean multiProcess;
    PathPermission[] pathPermissions;
    private String readPermission;
    boolean syncable;
    PatternMatcher[] uriPermissionPatterns;
    private String writePermission;

    @Override // android.content.pm.parsing.component.ParsedMainComponent, android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParsedProvider(ParsedProvider parsedProvider) {
        super(parsedProvider);
        this.authority = parsedProvider.authority;
        this.syncable = parsedProvider.syncable;
        this.readPermission = parsedProvider.readPermission;
        this.writePermission = parsedProvider.writePermission;
        this.grantUriPermissions = parsedProvider.grantUriPermissions;
        this.forceUriPermissions = parsedProvider.forceUriPermissions;
        this.multiProcess = parsedProvider.multiProcess;
        this.initOrder = parsedProvider.initOrder;
        this.uriPermissionPatterns = parsedProvider.uriPermissionPatterns;
        this.pathPermissions = parsedProvider.pathPermissions;
    }

    public void setAuthority(String str) {
        this.authority = TextUtils.safeIntern(str);
    }

    public void setSyncable(boolean z) {
        this.syncable = z;
    }

    public void setReadPermission(String str) {
        this.readPermission = TextUtils.isEmpty(str) ? null : str.intern();
    }

    public void setWritePermission(String str) {
        this.writePermission = TextUtils.isEmpty(str) ? null : str.intern();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Provider{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(' ');
        ComponentName.appendShortString(sb, getPackageName(), getName());
        sb.append('}');
        return sb.toString();
    }

    @Override // android.content.pm.parsing.component.ParsedMainComponent, android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.authority);
        parcel.writeBoolean(this.syncable);
        ParsingPackageImpl.sForInternedString.parcel(this.readPermission, parcel, i);
        ParsingPackageImpl.sForInternedString.parcel(this.writePermission, parcel, i);
        parcel.writeBoolean(this.grantUriPermissions);
        parcel.writeBoolean(this.forceUriPermissions);
        parcel.writeBoolean(this.multiProcess);
        parcel.writeInt(this.initOrder);
        parcel.writeTypedArray(this.uriPermissionPatterns, i);
        parcel.writeTypedArray(this.pathPermissions, i);
    }

    public ParsedProvider() {
    }

    protected ParsedProvider(Parcel parcel) {
        super(parcel);
        this.authority = parcel.readString();
        this.syncable = parcel.readBoolean();
        this.readPermission = ParsingPackageImpl.sForInternedString.unparcel(parcel);
        this.writePermission = ParsingPackageImpl.sForInternedString.unparcel(parcel);
        this.grantUriPermissions = parcel.readBoolean();
        this.forceUriPermissions = parcel.readBoolean();
        this.multiProcess = parcel.readBoolean();
        this.initOrder = parcel.readInt();
        this.uriPermissionPatterns = (PatternMatcher[]) parcel.createTypedArray(PatternMatcher.CREATOR);
        this.pathPermissions = (PathPermission[]) parcel.createTypedArray(PathPermission.CREATOR);
    }

    public String getAuthority() {
        return this.authority;
    }

    public boolean isSyncable() {
        return this.syncable;
    }

    public String getReadPermission() {
        return this.readPermission;
    }

    public String getWritePermission() {
        return this.writePermission;
    }

    public boolean isGrantUriPermissions() {
        return this.grantUriPermissions;
    }

    public boolean isForceUriPermissions() {
        return this.forceUriPermissions;
    }

    public boolean isMultiProcess() {
        return this.multiProcess;
    }

    public int getInitOrder() {
        return this.initOrder;
    }

    public PatternMatcher[] getUriPermissionPatterns() {
        return this.uriPermissionPatterns;
    }

    public PathPermission[] getPathPermissions() {
        return this.pathPermissions;
    }
}
