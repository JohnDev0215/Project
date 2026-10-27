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
        stringBuffer.append("FavoriteContactListInfo{");
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
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
