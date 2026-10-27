package ts.car.service.carplay.data;

/* JADX INFO: loaded from: classes.dex */
public class CallStateInfo {
    public static final int CALL_STATE_ACTIVE = 4;
    public static final int CALL_STATE_CONNECTING = 3;
    public static final int CALL_STATE_DISCONNECTED = 0;
    public static final int CALL_STATE_DISCONNECTING = 6;
    public static final int CALL_STATE_HELD = 5;
    public static final int CALL_STATE_RINGING = 2;
    public static final int CALL_STATE_SENDING = 1;
    public static final int CALL_STATE_UNKNOW = -1;
    private String mCallUuid;
    private int mConferenceGroup;
    private String mDisplayName;
    private long mDuration;
    private String mRemoteId;
    private boolean mRemoteIdValid = false;
    private boolean mDisplayNameValid = false;
    private int mStatus = -1;
    private boolean mDurationValid = false;
    private boolean mIsConferenced = false;
    private boolean mCallUuidValid = false;

    public void clear() {
        this.mRemoteIdValid = false;
        this.mDisplayNameValid = false;
        this.mStatus = -1;
        this.mDurationValid = false;
        this.mIsConferenced = false;
        this.mCallUuidValid = false;
    }

    public void copy(CallStateInfo callStateInfo) {
        if (callStateInfo != null) {
            this.mRemoteIdValid = callStateInfo.isRemoteIdValid();
            this.mRemoteId = callStateInfo.getRemoteId();
            this.mDisplayNameValid = callStateInfo.isDisplayNameValid();
            this.mDisplayName = callStateInfo.getDisplayName();
            this.mStatus = callStateInfo.getStatus();
            this.mDurationValid = callStateInfo.isDurationValid();
            this.mDuration = callStateInfo.getDuration();
            this.mIsConferenced = callStateInfo.isConferencedValid();
            this.mConferenceGroup = callStateInfo.getConferenceGroup();
            this.mCallUuidValid = callStateInfo.isCallUuidValid();
            this.mCallUuid = callStateInfo.getCallUuid();
        }
    }

    public String getCallUuid() {
        return this.mCallUuid;
    }

    public int getConferenceGroup() {
        return this.mConferenceGroup;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public String getRemoteId() {
        return this.mRemoteId;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public boolean isCallUuidValid() {
        return this.mCallUuidValid;
    }

    public boolean isConferencedValid() {
        return this.mIsConferenced;
    }

    public boolean isDefaultValue() {
        return (this.mRemoteIdValid || this.mDisplayNameValid || this.mStatus != -1 || this.mDurationValid || this.mIsConferenced || this.mCallUuidValid) ? false : true;
    }

    public boolean isDisplayNameValid() {
        return this.mDisplayNameValid;
    }

    public boolean isDurationValid() {
        return this.mDurationValid;
    }

    public boolean isRemoteIdValid() {
        return this.mRemoteIdValid;
    }

    public void setCallUuid(String str) {
        this.mCallUuid = str;
        this.mCallUuidValid = true;
    }

    public void setConferenceGroup(int i) {
        this.mIsConferenced = true;
        this.mConferenceGroup = i;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
        this.mDisplayNameValid = true;
    }

    public void setDuration(long j) {
        this.mDuration = j;
        this.mDurationValid = true;
    }

    public void setIsConferencedValid(boolean z) {
        this.mIsConferenced = z;
    }

    public void setRemoteId(String str) {
        this.mRemoteId = str;
        this.mRemoteIdValid = true;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CallStateInfo{");
        stringBuffer.append("mRemoteIdValid = ");
        stringBuffer.append(this.mRemoteIdValid);
        stringBuffer.append(" ");
        stringBuffer.append("mRemoteId = ");
        stringBuffer.append(this.mRemoteId);
        stringBuffer.append(" ");
        stringBuffer.append("mDisplayNameValid = ");
        stringBuffer.append(this.mDisplayNameValid);
        stringBuffer.append(" ");
        stringBuffer.append("mDisplayName = ");
        stringBuffer.append(this.mDisplayName);
        stringBuffer.append(" ");
        stringBuffer.append("mStatus = ");
        stringBuffer.append(this.mStatus);
        stringBuffer.append(" ");
        stringBuffer.append("mDurationValid = ");
        stringBuffer.append(this.mDurationValid);
        stringBuffer.append(" ");
        stringBuffer.append("mDuration = ");
        stringBuffer.append(this.mDuration);
        stringBuffer.append(" ");
        stringBuffer.append("mIsConferenced = ");
        stringBuffer.append(this.mIsConferenced);
        stringBuffer.append(" ");
        stringBuffer.append("mConferenceGroup = ");
        stringBuffer.append(this.mConferenceGroup);
        stringBuffer.append(" ");
        stringBuffer.append("mCallUuid = ");
        stringBuffer.append(this.mCallUuid);
        stringBuffer.append(" ");
        stringBuffer.append("mCallUuidValid = ");
        stringBuffer.append(this.mCallUuidValid);
        stringBuffer.append(" ");
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
