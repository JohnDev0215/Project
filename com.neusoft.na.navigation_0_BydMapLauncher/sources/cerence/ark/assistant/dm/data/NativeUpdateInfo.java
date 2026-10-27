package cerence.ark.assistant.dm.data;

/* JADX INFO: loaded from: classes2.dex */
public class NativeUpdateInfo {
    private String mAttribute;
    private long mCurrentSize;
    private String mDescription;
    private boolean mDownloading;
    private String mNewVersion;
    private String mOldVersion;
    private long mTotalSize;

    public NativeUpdateInfo() {
    }

    public NativeUpdateInfo(String str, String str2, String str3, String str4, long j, long j2, boolean z) {
        this.mAttribute = str;
        this.mOldVersion = str2;
        this.mNewVersion = str3;
        this.mDescription = str4;
        this.mTotalSize = j;
        this.mCurrentSize = j2;
        this.mDownloading = z;
    }

    public String getOldVersion() {
        return this.mOldVersion;
    }

    public void setOldVersion(String str) {
        this.mOldVersion = str;
    }

    public String getNewVersion() {
        return this.mNewVersion;
    }

    public void setNewVersion(String str) {
        this.mNewVersion = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public long getTotalSize() {
        return this.mTotalSize;
    }

    public void setTotalSize(long j) {
        this.mTotalSize = j;
    }

    public String getAttribute() {
        return this.mAttribute;
    }

    public void setAttribute(String str) {
        this.mAttribute = str;
    }

    public long getCurrentSize() {
        return this.mCurrentSize;
    }

    public void setCurrentSize(long j) {
        this.mCurrentSize = j;
    }

    public boolean getDownloading() {
        return this.mDownloading;
    }

    public void setDownloading(boolean z) {
        this.mDownloading = z;
    }
}
