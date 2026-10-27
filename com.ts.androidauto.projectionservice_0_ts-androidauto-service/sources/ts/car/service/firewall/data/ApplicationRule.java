package ts.car.service.firewall.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ApplicationRule implements Parcelable {
    public static final Parcelable.Creator<ApplicationRule> CREATOR = new Parcelable.Creator<ApplicationRule>() { // from class: ts.car.service.firewall.data.ApplicationRule.1
        @Override // android.os.Parcelable.Creator
        public ApplicationRule createFromParcel(Parcel in) {
            ApplicationRule item = new ApplicationRule();
            item.mUid = in.readInt();
            item.packageName = in.readString();
            item.urlArray = in.createTypedArrayList(Url.CREATOR);
            item.mIface = in.createStringArrayList();
            return item;
        }

        @Override // android.os.Parcelable.Creator
        public ApplicationRule[] newArray(int size) {
            return new ApplicationRule[size];
        }
    };
    public ArrayList<Url> urlArray = new ArrayList<>();
    public ArrayList<String> mIface = new ArrayList<>();
    public int mUid = 0;
    public String packageName = null;

    public void setUid(int uid) {
        this.mUid = uid;
    }

    public int getUid() {
        return this.mUid;
    }

    public void setPackageName(String name) {
        this.packageName = name;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setUrl(List<Url> url) {
        for (int i = 0; i < url.size(); i++) {
            this.urlArray.add(url.get(i));
        }
    }

    public List<Url> getUrlArray() {
        List<Url> list = new ArrayList<>(this.urlArray.size());
        for (int i = 0; i < this.urlArray.size(); i++) {
            list.add(this.urlArray.get(i));
        }
        return list;
    }

    public void setIface(ArrayList<String> ifaces) {
        for (int i = 0; i < ifaces.size(); i++) {
            this.mIface.add(ifaces.get(i));
        }
    }

    public ArrayList<String> getIface() {
        return this.mIface;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mUid);
        dest.writeString(this.packageName);
        dest.writeTypedList(this.urlArray);
        dest.writeStringList(this.mIface);
    }
}
