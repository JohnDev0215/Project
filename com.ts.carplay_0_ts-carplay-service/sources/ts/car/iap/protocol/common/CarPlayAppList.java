package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayAppList {
    private String mAppBundleID;
    private boolean mAppBundleIDValid;
    private int mAppCategoryID;
    private boolean mAppCategoryIDValid;
    private String mAppDisplayName;
    private boolean mAppDisplayNameValid;
    private String mAppIconIdentifier;
    private boolean mAppIconIdentifierValid;
    private int mIndex;
    private boolean mIndexValid;

    public boolean isIndexValid() {
        return this.mIndexValid;
    }

    public void setIndex(int index) {
        this.mIndex = index;
        this.mIndexValid = true;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public boolean isAppBundleIDValid() {
        return this.mAppBundleIDValid;
    }

    public void setAppBundleID(String appBundleID) {
        this.mAppBundleID = appBundleID;
        this.mAppBundleIDValid = true;
    }

    public String getAppBundleID() {
        return this.mAppBundleID;
    }

    public boolean isAppDisplayNameValid() {
        return this.mAppDisplayNameValid;
    }

    public void setAppDisplayName(String appDisplayName) {
        this.mAppDisplayName = appDisplayName;
        this.mAppDisplayNameValid = true;
    }

    public String getAppDisplayName() {
        return this.mAppDisplayName;
    }

    public boolean isAppCategoryIDValid() {
        return this.mAppCategoryIDValid;
    }

    public void setAppCategoryID(int id) {
        this.mAppCategoryID = id;
        this.mAppBundleIDValid = true;
    }

    public int getAppCategoryID() {
        return this.mAppCategoryID;
    }

    public boolean isAppIconIdentifierValid() {
        return this.mAppIconIdentifierValid;
    }

    public void setAppIconIdentifier(String appIconId) {
        this.mAppIconIdentifier = appIconId;
        this.mAppIconIdentifierValid = true;
    }

    public String getAppIconIdentifier() {
        return this.mAppIconIdentifier;
    }
}
