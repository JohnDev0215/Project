package ts.car.service.carplay.data;

/* JADX INFO: loaded from: classes2.dex */
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

    public boolean isDefaultValue() {
        return (this.mRemoteIdValid || this.mDisplayNameValid || this.mStatus != -1 || this.mDurationValid || this.mIsConferenced || this.mCallUuidValid) ? false : true;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CallStateInfo{").append("mRemoteIdValid = ").append(this.mRemoteIdValid).append(" ").append("mRemoteId = ").append(this.mRemoteId).append(" ").append("mDisplayNameValid = ").append(this.mDisplayNameValid).append(" ").append("mDisplayName = ").append(this.mDisplayName).append(" ").append("mStatus = ").append(this.mStatus).append(" ").append("mDurationValid = ").append(this.mDurationValid).append(" ").append("mDuration = ").append(this.mDuration).append(" ").append("mIsConferenced = ").append(this.mIsConferenced).append(" ").append("mConferenceGroup = ").append(this.mConferenceGroup).append(" ").append("mCallUuid = ").append(this.mCallUuid).append(" ").append("mCallUuidValid = ").append(this.mCallUuidValid).append(" ");
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    public String getRemoteId() {
        return this.mRemoteId;
    }

    public void setRemoteId(String str) {
        this.mRemoteId = str;
        this.mRemoteIdValid = true;
    }

    public boolean isRemoteIdValid() {
        return this.mRemoteIdValid;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
        this.mDisplayNameValid = true;
    }

    public boolean isDisplayNameValid() {
        return this.mDisplayNameValid;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public void setDuration(long j) {
        this.mDuration = j;
        this.mDurationValid = true;
    }

    public boolean isDurationValid() {
        return this.mDurationValid;
    }

    public void setIsConferencedValid(boolean z) {
        this.mIsConferenced = z;
    }

    public boolean isConferencedValid() {
        return this.mIsConferenced;
    }

    public int getConferenceGroup() {
        return this.mConferenceGroup;
    }

    public void setConferenceGroup(int i) {
        this.mIsConferenced = true;
        this.mConferenceGroup = i;
    }

    public boolean isCallUuidValid() {
        return this.mCallUuidValid;
    }

    public String getCallUuid() {
        return this.mCallUuid;
    }

    public void setCallUuid(String str) {
        this.mCallUuid = str;
        this.mCallUuidValid = true;
    }
}
