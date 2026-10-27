package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class RecentListInfo {
    private String mAddressBookId;
    private String mDisplayName;
    private String mLabel;
    private String mRemoteId;
    private boolean mIndexValid = false;
    private int mIndex = -1;
    private boolean mRemoteIdValid = false;
    private boolean mDisplayNameValid = false;
    private boolean mLabelValid = false;
    private boolean mAddressBookIdValid = false;
    private boolean mServiceValid = false;
    private int mService = -1;
    private boolean mTypeValid = false;
    private int mType = -1;
    private boolean mUnixTimestampValid = false;
    private int mUnixTimestamp = -1;
    private boolean mDurationValid = false;
    private int mDuration = -1;
    private boolean mOccurrencesValid = false;
    private int mOccurrences = -1;

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int index) {
        this.mIndex = index;
        this.mIndexValid = true;
    }

    public boolean isIndexValid() {
        return this.mIndexValid;
    }

    public String getRemoteId() {
        return this.mRemoteId;
    }

    public void setRemoteId(String remoteId) {
        this.mRemoteId = remoteId;
        this.mRemoteIdValid = true;
    }

    public boolean isRemoteIdValid() {
        return this.mRemoteIdValid;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String displayName) {
        this.mDisplayName = displayName;
        this.mDisplayNameValid = true;
    }

    public boolean isDisplayNameValid() {
        return this.mDisplayNameValid;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public void setLabel(String lable) {
        this.mLabel = lable;
        this.mLabelValid = true;
    }

    public boolean isLableValid() {
        return this.mLabelValid;
    }

    public String getAddressBookId() {
        return this.mAddressBookId;
    }

    public void setAddressBookId(String addressBookId) {
        this.mAddressBookId = addressBookId;
        this.mAddressBookIdValid = true;
    }

    public boolean isAddressBookIdValid() {
        return this.mAddressBookIdValid;
    }

    public int getService() {
        return this.mService;
    }

    public void setService(int service) {
        this.mService = service;
        this.mServiceValid = true;
    }

    public boolean isServiceValid() {
        return this.mServiceValid;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int type) {
        this.mType = type;
        this.mTypeValid = true;
    }

    public boolean isTypeValid() {
        return this.mTypeValid;
    }

    public int getUnixTimestamp() {
        return this.mUnixTimestamp;
    }

    public void setUnixTimestamp(int unixTimestamp) {
        this.mUnixTimestamp = unixTimestamp;
        this.mUnixTimestampValid = true;
    }

    public boolean isUnixTimestampValid() {
        return this.mUnixTimestampValid;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
        this.mDurationValid = true;
    }

    public boolean isDurationValid() {
        return this.mDurationValid;
    }

    public int getOccurrences() {
        return this.mOccurrences;
    }

    public void setOccurrences(int occurrences) {
        this.mOccurrences = occurrences;
        this.mOccurrencesValid = true;
    }

    public boolean isOccurrencesValid() {
        return this.mOccurrencesValid;
    }
}
