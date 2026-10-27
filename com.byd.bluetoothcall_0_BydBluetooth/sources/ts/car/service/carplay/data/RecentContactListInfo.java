package ts.car.service.carplay.data;

import com.bydux.api.common.tools.util.ListUtils;

/* JADX INFO: loaded from: classes.dex */
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
        stringBuffer.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        stringBuffer.append("mRemoteId = ");
        stringBuffer.append(this.mRemoteId);
        stringBuffer.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        stringBuffer.append("mDisplayName = ");
        stringBuffer.append(this.mDisplayName);
        stringBuffer.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        stringBuffer.append("mLabel = ");
        stringBuffer.append(this.mLabel);
        stringBuffer.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        stringBuffer.append("mAddressBookId = ");
        stringBuffer.append(this.mAddressBookId);
        stringBuffer.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        stringBuffer.append("mService = ");
        stringBuffer.append(this.mService);
        stringBuffer.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        stringBuffer.append("mType = ");
        stringBuffer.append(this.mType);
        stringBuffer.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        stringBuffer.append("mUnixTimestamp = ");
        stringBuffer.append(this.mUnixTimestamp);
        stringBuffer.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        stringBuffer.append("mDuration = ");
        stringBuffer.append(this.mDuration);
        stringBuffer.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        stringBuffer.append("mOccurrences = ");
        stringBuffer.append(this.mOccurrences);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
