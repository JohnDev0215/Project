package android.window;

import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class DisplayAreaInfo implements Parcelable {
    public static final Parcelable.Creator<DisplayAreaInfo> CREATOR = new Parcelable.Creator<DisplayAreaInfo>() { // from class: android.window.DisplayAreaInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayAreaInfo createFromParcel(Parcel parcel) {
            return new DisplayAreaInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayAreaInfo[] newArray(int i) {
            return new DisplayAreaInfo[i];
        }
    };
    public final Configuration configuration;
    public final int displayId;
    public final int featureId;
    public final WindowContainerToken token;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DisplayAreaInfo(WindowContainerToken windowContainerToken, int i, int i2) {
        this.configuration = new Configuration();
        this.token = windowContainerToken;
        this.displayId = i;
        this.featureId = i2;
    }

    private DisplayAreaInfo(Parcel parcel) {
        Configuration configuration = new Configuration();
        this.configuration = configuration;
        this.token = WindowContainerToken.CREATOR.createFromParcel(parcel);
        configuration.readFromParcel(parcel);
        this.displayId = parcel.readInt();
        this.featureId = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.token.writeToParcel(parcel, i);
        this.configuration.writeToParcel(parcel, i);
        parcel.writeInt(this.displayId);
        parcel.writeInt(this.featureId);
    }

    public String toString() {
        return "DisplayAreaInfo{token=" + this.token + " config=" + this.configuration + "}";
    }
}
