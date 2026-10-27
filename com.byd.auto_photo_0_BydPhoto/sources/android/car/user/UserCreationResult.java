package android.car.user;

import android.content.pm.UserInfo;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class UserCreationResult implements Parcelable {
    public static final Parcelable.Creator<UserCreationResult> CREATOR = new Parcelable.Creator<UserCreationResult>() { // from class: android.car.user.UserCreationResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserCreationResult createFromParcel(Parcel parcel) {
            return new UserCreationResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserCreationResult[] newArray(int i) {
            return new UserCreationResult[i];
        }
    };
    public static final int STATUS_ANDROID_FAILURE = 2;
    public static final int STATUS_HAL_FAILURE = 3;
    public static final int STATUS_HAL_INTERNAL_FAILURE = 4;
    public static final int STATUS_INVALID_REQUEST = 5;
    public static final int STATUS_SUCCESSFUL = 1;
    private final String mErrorMessage;
    private final int mStatus;
    private final UserInfo mUser;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    public UserCreationResult(int i, UserInfo userInfo, String str) {
        this.mStatus = i;
        if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            this.mUser = userInfo;
            this.mErrorMessage = str;
            return;
        }
        throw new IllegalArgumentException("status was " + i + " but must be one of: STATUS_SUCCESSFUL(1), STATUS_ANDROID_FAILURE(2), STATUS_HAL_FAILURE(3), STATUS_HAL_INTERNAL_FAILURE(4), STATUS_INVALID_REQUEST(5)");
    }

    UserCreationResult(Parcel parcel) {
        byte b2 = parcel.readByte();
        int i = parcel.readInt();
        UserInfo userInfo = (b2 & 2) == 0 ? null : (UserInfo) parcel.readTypedObject(UserInfo.CREATOR);
        String string = (b2 & 4) != 0 ? parcel.readString() : null;
        this.mStatus = i;
        if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            this.mUser = userInfo;
            this.mErrorMessage = string;
            return;
        }
        throw new IllegalArgumentException("status was " + i + " but must be one of: STATUS_SUCCESSFUL(1), STATUS_ANDROID_FAILURE(2), STATUS_HAL_FAILURE(3), STATUS_HAL_INTERNAL_FAILURE(4), STATUS_INVALID_REQUEST(5)");
    }

    @Deprecated
    private void __metadata() {
    }

    public static String statusToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? Integer.toHexString(i) : "STATUS_INVALID_REQUEST" : "STATUS_HAL_INTERNAL_FAILURE" : "STATUS_HAL_FAILURE" : "STATUS_ANDROID_FAILURE" : "STATUS_SUCCESSFUL";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public UserInfo getUser() {
        return this.mUser;
    }

    public boolean isSuccess() {
        return this.mStatus == 1;
    }

    public String toString() {
        return "UserCreationResult { status = " + statusToString(this.mStatus) + ", user = " + this.mUser + ", errorMessage = " + this.mErrorMessage + " }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        byte b2 = this.mUser != null ? (byte) 2 : (byte) 0;
        if (this.mErrorMessage != null) {
            b2 = (byte) (b2 | 4);
        }
        parcel.writeByte(b2);
        parcel.writeInt(this.mStatus);
        UserInfo userInfo = this.mUser;
        if (userInfo != null) {
            parcel.writeTypedObject(userInfo, i);
        }
        String str = this.mErrorMessage;
        if (str != null) {
            parcel.writeString(str);
        }
    }
}
