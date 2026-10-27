package android.content.om;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class OverlayInfo implements Parcelable {
    public static final String CATEGORY_THEME = "android.theme";
    public static final Parcelable.Creator<OverlayInfo> CREATOR = new Parcelable.Creator<OverlayInfo>() { // from class: android.content.om.OverlayInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OverlayInfo createFromParcel(Parcel parcel) {
            return new OverlayInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OverlayInfo[] newArray(int i) {
            return new OverlayInfo[i];
        }
    };
    public static final int STATE_DISABLED = 2;
    public static final int STATE_ENABLED = 3;

    @Deprecated
    public static final int STATE_ENABLED_IMMUTABLE = 6;
    public static final int STATE_MISSING_TARGET = 0;
    public static final int STATE_NO_IDMAP = 1;
    public static final int STATE_OVERLAY_IS_BEING_REPLACED = 5;

    @Deprecated
    public static final int STATE_TARGET_IS_BEING_REPLACED = 4;
    public static final int STATE_UNKNOWN = -1;
    public final String baseCodePath;
    public final String category;
    public final boolean isMutable;
    public final String packageName;
    public final int priority;
    public final int state;
    public final String targetOverlayableName;
    public final String targetPackageName;
    public final int userId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public static String stateToString(int i) {
        switch (i) {
            case -1:
                return "STATE_UNKNOWN";
            case 0:
                return "STATE_MISSING_TARGET";
            case 1:
                return "STATE_NO_IDMAP";
            case 2:
                return "STATE_DISABLED";
            case 3:
                return "STATE_ENABLED";
            case 4:
                return "STATE_TARGET_IS_BEING_REPLACED";
            case 5:
                return "STATE_OVERLAY_IS_BEING_REPLACED";
            case 6:
                return "STATE_ENABLED_IMMUTABLE";
            default:
                return "<unknown state>";
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OverlayInfo(OverlayInfo overlayInfo, int i) {
        this(overlayInfo.packageName, overlayInfo.targetPackageName, overlayInfo.targetOverlayableName, overlayInfo.category, overlayInfo.baseCodePath, i, overlayInfo.userId, overlayInfo.priority, overlayInfo.isMutable);
    }

    public OverlayInfo(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, boolean z) {
        this.packageName = str;
        this.targetPackageName = str2;
        this.targetOverlayableName = str3;
        this.category = str4;
        this.baseCodePath = str5;
        this.state = i;
        this.userId = i2;
        this.priority = i3;
        this.isMutable = z;
        ensureValidState();
    }

    public OverlayInfo(Parcel parcel) {
        this.packageName = parcel.readString();
        this.targetPackageName = parcel.readString();
        this.targetOverlayableName = parcel.readString();
        this.category = parcel.readString();
        this.baseCodePath = parcel.readString();
        this.state = parcel.readInt();
        this.userId = parcel.readInt();
        this.priority = parcel.readInt();
        this.isMutable = parcel.readBoolean();
        ensureValidState();
    }

    @SystemApi
    public String getPackageName() {
        return this.packageName;
    }

    @SystemApi
    public String getTargetPackageName() {
        return this.targetPackageName;
    }

    @SystemApi
    public String getCategory() {
        return this.category;
    }

    @SystemApi
    public int getUserId() {
        return this.userId;
    }

    @SystemApi
    public String getTargetOverlayableName() {
        return this.targetOverlayableName;
    }

    private void ensureValidState() {
        if (this.packageName == null) {
            throw new IllegalArgumentException("packageName must not be null");
        }
        if (this.targetPackageName == null) {
            throw new IllegalArgumentException("targetPackageName must not be null");
        }
        if (this.baseCodePath == null) {
            throw new IllegalArgumentException("baseCodePath must not be null");
        }
        switch (this.state) {
            case -1:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return;
            default:
                throw new IllegalArgumentException("State " + this.state + " is not a valid state");
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.packageName);
        parcel.writeString(this.targetPackageName);
        parcel.writeString(this.targetOverlayableName);
        parcel.writeString(this.category);
        parcel.writeString(this.baseCodePath);
        parcel.writeInt(this.state);
        parcel.writeInt(this.userId);
        parcel.writeInt(this.priority);
        parcel.writeBoolean(this.isMutable);
    }

    @SystemApi
    public boolean isEnabled() {
        int i = this.state;
        return i == 3 || i == 6;
    }

    public int hashCode() {
        int i = (((this.userId + 31) * 31) + this.state) * 31;
        String str = this.packageName;
        int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.targetPackageName;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.targetOverlayableName;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.category;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.baseCodePath;
        return iHashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OverlayInfo overlayInfo = (OverlayInfo) obj;
        return this.userId == overlayInfo.userId && this.state == overlayInfo.state && this.packageName.equals(overlayInfo.packageName) && this.targetPackageName.equals(overlayInfo.targetPackageName) && Objects.equals(this.targetOverlayableName, overlayInfo.targetOverlayableName) && Objects.equals(this.category, overlayInfo.category) && this.baseCodePath.equals(overlayInfo.baseCodePath);
    }

    public String toString() {
        return "OverlayInfo { overlay=" + this.packageName + ", targetPackage=" + this.targetPackageName + (this.targetOverlayableName == null ? "" : ", targetOverlayable=" + this.targetOverlayableName) + ", state=" + this.state + " (" + stateToString(this.state) + "), userId=" + this.userId + " }";
    }
}
