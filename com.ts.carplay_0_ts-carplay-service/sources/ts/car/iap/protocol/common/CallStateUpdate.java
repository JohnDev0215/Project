package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class CallStateUpdate {
    private String mAddressBookId;
    private String mCallUuid;
    private String mDisplayName;
    private String mLabel;
    private String mRemoteId;
    private long mStartTimestamp;
    private boolean mRemoteIdValid = false;
    private boolean mDisplayNameValid = false;
    private int mStatus = -1;
    private int mDirection = -1;
    private boolean mCallUuidValid = false;
    private boolean mAddressBookIdValid = false;
    private boolean mLabelValid = false;
    private int mService = -1;
    private int mIsConferenced = -1;
    private int mConferenceGroup = -1;
    private int mDisconnectReason = -1;
    private boolean mStartTimestampValid = false;

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

    public boolean isStatusValid() {
        return -1 != this.mStatus;
    }

    public int getDirection() {
        return this.mDirection;
    }

    public void setDirection(int direction) {
        this.mDirection = direction;
    }

    public boolean isDirectionValid() {
        return -1 != this.mDirection;
    }

    public String getCallUuid() {
        return this.mCallUuid;
    }

    public void setCallUuid(String uuid) {
        this.mCallUuid = uuid;
        this.mCallUuidValid = true;
    }

    public boolean isCallUuidValid() {
        return this.mCallUuidValid;
    }

    public String getAddressBookId() {
        return this.mAddressBookId;
    }

    public void setAddressBookId(String id) {
        this.mAddressBookId = id;
        this.mAddressBookIdValid = true;
    }

    public boolean isAddressBookIdValid() {
        return this.mAddressBookIdValid;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public void setLabel(String label) {
        this.mLabel = label;
        this.mLabelValid = true;
    }

    public boolean isLabelValid() {
        return this.mLabelValid;
    }

    public int getService() {
        return this.mService;
    }

    public void setService(int service) {
        this.mService = service;
    }

    public boolean isServiceValid() {
        return -1 != this.mService;
    }

    public int getIsConferenced() {
        return this.mIsConferenced;
    }

    public void setIsConferenced(int isConferenced) {
        this.mIsConferenced = isConferenced;
    }

    public boolean isIsConferencedValid() {
        return -1 != this.mIsConferenced;
    }

    public int getConferenceGroup() {
        return this.mConferenceGroup;
    }

    public void setConferenceGroup(int group) {
        this.mConferenceGroup = group;
    }

    public boolean isConferenceGroupValid() {
        return -1 != this.mConferenceGroup;
    }

    public int getDisconnectReason() {
        return this.mDisconnectReason;
    }

    public void setDisconnectReason(int reason) {
        this.mDisconnectReason = reason;
    }

    public boolean isDisconnectReasonValid() {
        return -1 != this.mDisconnectReason;
    }

    public long getStartTimestamp() {
        return this.mStartTimestamp;
    }

    public void setStartTimestamp(long ts2) {
        this.mStartTimestamp = ts2;
        this.mStartTimestampValid = true;
    }

    public boolean isStartTimestampValid() {
        return this.mStartTimestampValid;
    }
}
