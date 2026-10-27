package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.SettingsStringUtil;
import android.util.DebugUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class UserInfo implements Parcelable {
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() { // from class: android.content.pm.UserInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo createFromParcel(Parcel parcel) {
            return new UserInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo[] newArray(int i) {
            return new UserInfo[i];
        }
    };
    public static final int FLAG_ADMIN = 2;

    @Deprecated
    public static final int FLAG_DEMO = 512;
    public static final int FLAG_DISABLED = 64;
    public static final int FLAG_EPHEMERAL = 256;
    public static final int FLAG_FULL = 1024;

    @Deprecated
    public static final int FLAG_GUEST = 4;
    public static final int FLAG_INITIALIZED = 16;

    @Deprecated
    public static final int FLAG_MANAGED_PROFILE = 32;
    public static final int FLAG_PRIMARY = 1;
    public static final int FLAG_PROFILE = 4096;
    public static final int FLAG_QUIET_MODE = 128;

    @Deprecated
    public static final int FLAG_RESTRICTED = 8;
    public static final int FLAG_SYSTEM = 2048;
    public static final int NO_PROFILE_GROUP_ID = -10000;
    public boolean convertedFromPreCreated;
    public long creationTime;
    public int flags;
    public boolean guestToRemove;
    public String iconPath;
    public int id;
    public String lastLoggedInFingerprint;
    public long lastLoggedInTime;
    public String name;
    public boolean partial;
    public boolean preCreated;
    public int profileBadge;
    public int profileGroupId;
    public int restrictedProfileParentId;
    public int serialNumber;
    public String userType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface UserInfoFlag {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserInfo(int i, String str, int i2) {
        this(i, str, null, i2);
    }

    public UserInfo(int i, String str, String str2, int i2) {
        this(i, str, str2, i2, getDefaultUserType(i2));
    }

    public UserInfo(int i, String str, String str2, int i2, String str3) {
        this.id = i;
        this.name = str;
        this.flags = i2;
        this.userType = str3;
        this.iconPath = str2;
        this.profileGroupId = -10000;
        this.restrictedProfileParentId = -10000;
    }

    public static String getDefaultUserType(int i) {
        if ((i & 2048) != 0) {
            throw new IllegalArgumentException("Cannot getDefaultUserType for flags " + Integer.toHexString(i) + " because it corresponds to a SYSTEM user type.");
        }
        int i2 = i & 556;
        if (i2 == 0) {
            return UserManager.USER_TYPE_FULL_SECONDARY;
        }
        if (i2 == 4) {
            return UserManager.USER_TYPE_FULL_GUEST;
        }
        if (i2 == 8) {
            return UserManager.USER_TYPE_FULL_RESTRICTED;
        }
        if (i2 == 32) {
            return UserManager.USER_TYPE_PROFILE_MANAGED;
        }
        if (i2 == 512) {
            return UserManager.USER_TYPE_FULL_DEMO;
        }
        throw new IllegalArgumentException("Cannot getDefaultUserType for flags " + Integer.toHexString(i) + " because it doesn't correspond to a valid user type.");
    }

    public boolean isPrimary() {
        return (this.flags & 1) == 1;
    }

    public boolean isAdmin() {
        return (this.flags & 2) == 2;
    }

    public boolean isGuest() {
        return UserManager.isUserTypeGuest(this.userType);
    }

    public boolean isRestricted() {
        return UserManager.isUserTypeRestricted(this.userType);
    }

    public boolean isProfile() {
        return (this.flags & 4096) != 0;
    }

    public boolean isManagedProfile() {
        return UserManager.isUserTypeManagedProfile(this.userType);
    }

    public boolean isEnabled() {
        return (this.flags & 64) != 64;
    }

    public boolean isQuietModeEnabled() {
        return (this.flags & 128) == 128;
    }

    public boolean isEphemeral() {
        return (this.flags & 256) == 256;
    }

    public boolean isInitialized() {
        return (this.flags & 16) == 16;
    }

    public boolean isDemo() {
        return UserManager.isUserTypeDemo(this.userType);
    }

    public boolean isFull() {
        return (this.flags & 1024) == 1024;
    }

    public boolean isSystemOnly() {
        return isSystemOnly(this.id);
    }

    public static boolean isSystemOnly(int i) {
        return i == 0 && UserManager.isSplitSystemUser();
    }

    public boolean supportsSwitchTo() {
        if ((!isEphemeral() || isEnabled()) && !this.preCreated) {
            return !isProfile();
        }
        return false;
    }

    public boolean supportsSwitchToByUser() {
        return !(UserManager.isHeadlessSystemUserMode() && this.id == 0) && supportsSwitchTo();
    }

    public boolean canHaveProfile() {
        if (isProfile() || isGuest() || isRestricted()) {
            return false;
        }
        return (UserManager.isSplitSystemUser() || UserManager.isHeadlessSystemUserMode()) ? this.id != 0 : this.id == 0;
    }

    @Deprecated
    public UserInfo() {
    }

    public UserInfo(UserInfo userInfo) {
        this.name = userInfo.name;
        this.iconPath = userInfo.iconPath;
        this.id = userInfo.id;
        this.flags = userInfo.flags;
        this.userType = userInfo.userType;
        this.serialNumber = userInfo.serialNumber;
        this.creationTime = userInfo.creationTime;
        this.lastLoggedInTime = userInfo.lastLoggedInTime;
        this.lastLoggedInFingerprint = userInfo.lastLoggedInFingerprint;
        this.partial = userInfo.partial;
        this.preCreated = userInfo.preCreated;
        this.convertedFromPreCreated = userInfo.convertedFromPreCreated;
        this.profileGroupId = userInfo.profileGroupId;
        this.restrictedProfileParentId = userInfo.restrictedProfileParentId;
        this.guestToRemove = userInfo.guestToRemove;
        this.profileBadge = userInfo.profileBadge;
    }

    public UserHandle getUserHandle() {
        return UserHandle.of(this.id);
    }

    public String toString() {
        return "UserInfo{" + this.id + SettingsStringUtil.DELIMITER + this.name + SettingsStringUtil.DELIMITER + Integer.toHexString(this.flags) + "}";
    }

    public String toFullString() {
        return "UserInfo[id=" + this.id + ", name=" + this.name + ", type=" + this.userType + ", flags=" + flagsToString(this.flags) + (this.preCreated ? " (pre-created)" : "") + (this.convertedFromPreCreated ? " (converted)" : "") + (this.partial ? " (partial)" : "") + "]";
    }

    public static String flagsToString(int i) {
        return DebugUtils.flagsToString(UserInfo.class, "FLAG_", i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString8(this.name);
        parcel.writeString8(this.iconPath);
        parcel.writeInt(this.flags);
        parcel.writeString8(this.userType);
        parcel.writeInt(this.serialNumber);
        parcel.writeLong(this.creationTime);
        parcel.writeLong(this.lastLoggedInTime);
        parcel.writeString8(this.lastLoggedInFingerprint);
        parcel.writeBoolean(this.partial);
        parcel.writeBoolean(this.preCreated);
        parcel.writeInt(this.profileGroupId);
        parcel.writeBoolean(this.guestToRemove);
        parcel.writeInt(this.restrictedProfileParentId);
        parcel.writeInt(this.profileBadge);
    }

    private UserInfo(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString8();
        this.iconPath = parcel.readString8();
        this.flags = parcel.readInt();
        this.userType = parcel.readString8();
        this.serialNumber = parcel.readInt();
        this.creationTime = parcel.readLong();
        this.lastLoggedInTime = parcel.readLong();
        this.lastLoggedInFingerprint = parcel.readString8();
        this.partial = parcel.readBoolean();
        this.preCreated = parcel.readBoolean();
        this.profileGroupId = parcel.readInt();
        this.guestToRemove = parcel.readBoolean();
        this.restrictedProfileParentId = parcel.readInt();
        this.profileBadge = parcel.readInt();
    }
}
