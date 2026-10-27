package ts.car.service.carplay.data;

import com.bydux.api.common.tools.util.ListUtils;

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
        stringBuffer.append("RecentContactListInfo{").append("mIndex = ").append(this.mIndex).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mRemoteId = ").append(this.mRemoteId).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mDisplayName = ").append(this.mDisplayName).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mLabel = ").append(this.mLabel).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mAddressBookId = ").append(this.mAddressBookId).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mService = ").append(this.mService).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mType = ").append(this.mType).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mUnixTimestamp = ").append(this.mUnixTimestamp).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mDuration = ").append(this.mDuration).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mOccurrences = ").append(this.mOccurrences);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
