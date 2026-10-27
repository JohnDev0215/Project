package cerence.ark.assistant.framework.updater;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum UpdateResultCode implements Parcelable {
    UPDATE_OK,
    UPDATE_STOPPED,
    UPDATE_CONNECTION_ERROR,
    UPDATE_REMOTE_ERROR,
    UPDATE_CHECKSUM_ERROR,
    UPDATE_FILESYSTEM_ERROR,
    UPDATE_INTERNAL_ERROR,
    RECOVERY_NOT_FOUND_ERROR,
    UPDATE_ERROR,
    UPDATE_FATAL_ERROR;

    public static final Parcelable.Creator<UpdateResultCode> CREATOR = new Parcelable.Creator<UpdateResultCode>() { // from class: cerence.ark.assistant.framework.updater.UpdateResultCode.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UpdateResultCode createFromParcel(Parcel parcel) {
            return UpdateResultCode.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UpdateResultCode[] newArray(int i) {
            return new UpdateResultCode[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
