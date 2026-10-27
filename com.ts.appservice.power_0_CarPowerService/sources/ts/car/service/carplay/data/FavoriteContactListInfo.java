package ts.car.service.carplay.data;

/* JADX INFO: loaded from: classes.dex */
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
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
