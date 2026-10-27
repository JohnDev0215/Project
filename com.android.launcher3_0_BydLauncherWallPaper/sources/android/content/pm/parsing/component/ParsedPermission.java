package android.content.pm.parsing.component;

import android.content.pm.PermissionInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class ParsedPermission extends ParsedComponent {
    public static final Parcelable.Creator<ParsedPermission> CREATOR = new Parcelable.Creator<ParsedPermission>() { // from class: android.content.pm.parsing.component.ParsedPermission.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedPermission createFromParcel(Parcel parcel) {
            return new ParsedPermission(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedPermission[] newArray(int i) {
            return new ParsedPermission[i];
        }
    };
    String backgroundPermission;
    private String group;
    private ParsedPermissionGroup parsedPermissionGroup;
    int protectionLevel;
    int requestRes;
    boolean tree;

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParsedPermission() {
    }

    public ParsedPermission(ParsedPermission parsedPermission) {
        super(parsedPermission);
        this.backgroundPermission = parsedPermission.backgroundPermission;
        this.group = parsedPermission.group;
        this.requestRes = parsedPermission.requestRes;
        this.protectionLevel = parsedPermission.protectionLevel;
        this.tree = parsedPermission.tree;
        this.parsedPermissionGroup = parsedPermission.parsedPermissionGroup;
    }

    public ParsedPermission(ParsedPermission parsedPermission, PermissionInfo permissionInfo, String str, String str2) {
        this(parsedPermission);
        this.flags = permissionInfo.flags;
        this.descriptionRes = permissionInfo.descriptionRes;
        this.backgroundPermission = permissionInfo.backgroundPermission;
        this.group = permissionInfo.group;
        this.requestRes = permissionInfo.requestRes;
        this.protectionLevel = permissionInfo.protectionLevel;
        setName(str2);
        setPackageName(str);
    }

    public ParsedPermission setGroup(String str) {
        this.group = TextUtils.safeIntern(str);
        return this;
    }

    public ParsedPermission setFlags(int i) {
        this.flags = i;
        return this;
    }

    public boolean isRuntime() {
        return getProtection() == 1;
    }

    public boolean isAppOp() {
        return (this.protectionLevel & 64) != 0;
    }

    public int getProtection() {
        return this.protectionLevel & 15;
    }

    public int getProtectionFlags() {
        return this.protectionLevel & (-16);
    }

    public int calculateFootprint() {
        int length = getName().length();
        return getNonLocalizedLabel() != null ? length + getNonLocalizedLabel().length() : length;
    }

    public String toString() {
        return "Permission{" + Integer.toHexString(System.identityHashCode(this)) + " " + getName() + "}";
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.backgroundPermission);
        parcel.writeString(this.group);
        parcel.writeInt(this.requestRes);
        parcel.writeInt(this.protectionLevel);
        parcel.writeBoolean(this.tree);
        parcel.writeParcelable(this.parsedPermissionGroup, i);
    }

    protected ParsedPermission(Parcel parcel) {
        super(parcel);
        ClassLoader classLoader = Object.class.getClassLoader();
        this.backgroundPermission = parcel.readString();
        this.group = parcel.readString();
        this.requestRes = parcel.readInt();
        this.protectionLevel = parcel.readInt();
        this.tree = parcel.readBoolean();
        this.parsedPermissionGroup = (ParsedPermissionGroup) parcel.readParcelable(classLoader);
    }

    public String getBackgroundPermission() {
        return this.backgroundPermission;
    }

    public String getGroup() {
        return this.group;
    }

    public int getRequestRes() {
        return this.requestRes;
    }

    public int getProtectionLevel() {
        return this.protectionLevel;
    }

    public boolean isTree() {
        return this.tree;
    }

    public ParsedPermissionGroup getParsedPermissionGroup() {
        return this.parsedPermissionGroup;
    }

    public ParsedPermission setProtectionLevel(int i) {
        this.protectionLevel = i;
        return this;
    }

    public ParsedPermission setParsedPermissionGroup(ParsedPermissionGroup parsedPermissionGroup) {
        this.parsedPermissionGroup = parsedPermissionGroup;
        return this;
    }
}
