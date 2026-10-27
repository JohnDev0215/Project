package ts.car.service.carplay.data;

/* JADX INFO: loaded from: classes2.dex */
public class RecentContactListInfo {
    public int mIndex = -1;
    public String mRemoteId = null;
    public String mDisplayName = null;
    public String mLabel = null;
    public String mAddressBookId = null;
    public int mService = -1;
    public int mType = -1;
    public int mUnixTimestamp = -1;
    public int mDuration = -1;
    public int mOccurrences = -1;

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("RecentContactListInfo{");
        stringBuffer.append("mIndex = ");
        stringBuffer.append(this.mIndex);
        stringBuffer.append(",");
        stringBuffer.append("mRemoteId = ");
        stringBuffer.append(this.mRemoteId);
        stringBuffer.append(",");
        stringBuffer.append("mDisplayName = ");
        stringBuffer.append(this.mDisplayName);
        stringBuffer.append(",");
        stringBuffer.append("mLabel = ");
        stringBuffer.append(this.mLabel);
        stringBuffer.append(",");
        stringBuffer.append("mAddressBookId = ");
        stringBuffer.append(this.mAddressBookId);
        stringBuffer.append(",");
        stringBuffer.append("mService = ");
        stringBuffer.append(this.mService);
        stringBuffer.append(",");
        stringBuffer.append("mType = ");
        stringBuffer.append(this.mType);
        stringBuffer.append(",");
        stringBuffer.append("mUnixTimestamp = ");
        stringBuffer.append(this.mUnixTimestamp);
        stringBuffer.append(",");
        stringBuffer.append("mDuration = ");
        stringBuffer.append(this.mDuration);
        stringBuffer.append(",");
        stringBuffer.append("mOccurrences = ");
        stringBuffer.append(this.mOccurrences);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
