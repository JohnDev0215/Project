package ts.car.service.firewall.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class FirewallData implements Parcelable {
    public static final Parcelable.Creator<FirewallData> CREATOR = new Parcelable.Creator<FirewallData>() { // from class: ts.car.service.firewall.data.FirewallData.1
        @Override // android.os.Parcelable.Creator
        public FirewallData createFromParcel(Parcel in) {
            FirewallData item = new FirewallData();
            item.mData = in.readInt();
            return item;
        }

        @Override // android.os.Parcelable.Creator
        public FirewallData[] newArray(int size) {
            return new FirewallData[size];
        }
    };
    public int mData;

    public FirewallData(int data) {
        this.mData = data;
    }

    public FirewallData() {
        this.mData = 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mData);
    }
}
