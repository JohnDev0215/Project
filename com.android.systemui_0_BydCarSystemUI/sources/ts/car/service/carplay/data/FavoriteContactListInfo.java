package ts.car.service.carplay.data;

import com.bydux.api.common.tools.util.ListUtils;

/* JADX INFO: loaded from: classes2.dex */
public class FavoriteContactListInfo {
    public int mIndex = -1;
    public String mRemoteId = null;
    public String mDisplayName = null;
    public String mLabel = null;
    public String mAddressBookId = null;
    public int mService = -1;

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FavoriteContactListInfo{").append("mIndex = ").append(this.mIndex).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mRemoteId = ").append(this.mRemoteId).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mDisplayName = ").append(this.mDisplayName).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mLabel = ").append(this.mLabel).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mAddressBookId = ").append(this.mAddressBookId).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append("mService = ").append(this.mService);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
