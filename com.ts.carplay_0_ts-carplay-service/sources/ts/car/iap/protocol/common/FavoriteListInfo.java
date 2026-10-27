package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class FavoriteListInfo {
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
}
