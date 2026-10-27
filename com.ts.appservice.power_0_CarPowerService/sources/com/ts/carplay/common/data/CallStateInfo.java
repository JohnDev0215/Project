package com.ts.carplay.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.ts.carplay.common.util.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class CallStateInfo implements Parcelable {
    public static final int CALL_STATE_ACTIVE = 4;
    public static final int CALL_STATE_CONNECTING = 3;
    public static final int CALL_STATE_DISCONNECTED = 0;
    public static final int CALL_STATE_DISCONNECTING = 6;
    public static final int CALL_STATE_HELD = 5;
    public static final int CALL_STATE_RINGING = 2;
    public static final int CALL_STATE_SENDING = 1;
    public static final int CALL_STATE_UNKNOW = -1;
    public static final Parcelable.Creator<CallStateInfo> CREATOR = new Parcelable.Creator<CallStateInfo>() { // from class: com.ts.carplay.common.data.CallStateInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CallStateInfo createFromParcel(Parcel source) {
            CallStateInfo info = new CallStateInfo(source);
            return info;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CallStateInfo[] newArray(int size) {
            return new CallStateInfo[size];
        }
    };
    private String mCallUuid;
    private boolean mCallUuidValid;
    private int mConferenceGroup;
    private String mDisplayName;
    private boolean mDisplayNameValid;
    private long mDuration;
    private boolean mDurationValid;
    private boolean mIsConferenced;
    private String mRemoteId;
    private boolean mRemoteIdValid;
    private int mStatus;

    public CallStateInfo() {
        this.mRemoteIdValid = false;
        this.mDisplayNameValid = false;
        this.mStatus = -1;
        this.mDurationValid = false;
        this.mIsConferenced = false;
        this.mCallUuidValid = false;
    }

    public void clear() {
        this.mRemoteIdValid = false;
        this.mDisplayNameValid = false;
        this.mStatus = -1;
        this.mDurationValid = false;
        this.mIsConferenced = false;
        this.mCallUuidValid = false;
    }

    public CallStateInfo(Parcel in) {
        this.mRemoteIdValid = in.readByte() != 0;
        this.mRemoteId = in.readString();
        this.mDisplayNameValid = in.readByte() != 0;
        this.mDisplayName = in.readString();
        this.mStatus = in.readInt();
        this.mDurationValid = in.readByte() != 0;
        this.mDuration = in.readLong();
        this.mIsConferenced = in.readByte() != 0;
        this.mConferenceGroup = in.readInt();
        this.mCallUuidValid = in.readByte() != 0;
        this.mCallUuid = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mRemoteIdValid ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mRemoteId);
        parcel.writeByte(this.mDisplayNameValid ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mDisplayName);
        parcel.writeInt(this.mStatus);
        parcel.writeByte(this.mDurationValid ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.mDuration);
        parcel.writeByte(this.mIsConferenced ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mConferenceGroup);
        parcel.writeByte(this.mCallUuidValid ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mCallUuid);
    }

    public void copy(CallStateInfo info) {
        if (info != null) {
            this.mRemoteIdValid = info.isRemoteIdValid();
            this.mRemoteId = info.getRemoteId();
            this.mDisplayNameValid = info.isDisplayNameValid();
            this.mDisplayName = info.getDisplayName();
            this.mStatus = info.getStatus();
            this.mDurationValid = info.isDurationValid();
            this.mDuration = info.getDuration();
            this.mIsConferenced = info.isConferencedValid();
            this.mConferenceGroup = info.getConferenceGroup();
            this.mCallUuidValid = info.isCallUuidValid();
            this.mCallUuid = info.getCallUuid();
        }
    }

    public boolean isDefaultValue() {
        if (this.mRemoteIdValid || this.mDisplayNameValid || this.mStatus != -1 || this.mDurationValid || this.mIsConferenced || this.mCallUuidValid) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("CallStateInfo{");
        sb.append("mRemoteIdValid = ");
        sb.append(this.mRemoteIdValid);
        sb.append(" ");
        sb.append("mRemoteId = ");
        sb.append(LogEncrypt.phoneNumberEncrypt(this.mRemoteId));
        sb.append(" ");
        sb.append("mDisplayNameValid = ");
        sb.append(this.mDisplayNameValid);
        sb.append(" ");
        sb.append("mDisplayName = ");
        sb.append(LogEncrypt.phoneNumberEncrypt(this.mDisplayName));
        sb.append(" ");
        sb.append("mStatus = ");
        sb.append(this.mStatus);
        sb.append(" ");
        sb.append("mDurationValid = ");
        sb.append(this.mDurationValid);
        sb.append(" ");
        sb.append("mDuration = ");
        sb.append(this.mDuration);
        sb.append(" ");
        sb.append("mIsConferenced = ");
        sb.append(this.mIsConferenced);
        sb.append(" ");
        sb.append("mConferenceGroup = ");
        sb.append(this.mConferenceGroup);
        sb.append(" ");
        sb.append("mCallId = ");
        sb.append(LogEncrypt.MacAddressEncrypt(this.mCallUuid));
        sb.append(" ");
        sb.append("mCallIdValid = ");
        sb.append(this.mCallUuidValid);
        sb.append(" ");
        sb.append("}");
        return sb.toString();
    }

    public String getRemoteId() {
        return this.mRemoteId;
    }

    public void setRemoteId(String id) {
        this.mRemoteId = id;
        this.mRemoteIdValid = true;
    }

    public boolean isRemoteIdValid() {
        return this.mRemoteIdValid;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String name) {
        this.mDisplayName = name;
        this.mDisplayNameValid = true;
    }

    public boolean isDisplayNameValid() {
        return this.mDisplayNameValid;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public void setDuration(long duration) {
        this.mDuration = duration;
        this.mDurationValid = true;
    }

    public boolean isDurationValid() {
        return this.mDurationValid;
    }

    public void setIsConferencedValid(boolean valid) {
        this.mIsConferenced = valid;
    }

    public boolean isConferencedValid() {
        return this.mIsConferenced;
    }

    public int getConferenceGroup() {
        return this.mConferenceGroup;
    }

    public void setConferenceGroup(int group) {
        this.mIsConferenced = true;
        this.mConferenceGroup = group;
    }

    public boolean isCallUuidValid() {
        return this.mCallUuidValid;
    }

    public String getCallUuid() {
        return this.mCallUuid;
    }

    public void setCallUuid(String uuid) {
        this.mCallUuid = uuid;
        this.mCallUuidValid = true;
    }
}
