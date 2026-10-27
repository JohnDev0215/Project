package cerence.ark.assistant.api.event;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class AppEvent implements Parcelable {
    public static final Parcelable.Creator<AppEvent> CREATOR = new Parcelable.Creator<AppEvent>() { // from class: cerence.ark.assistant.api.event.AppEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppEvent createFromParcel(Parcel parcel) {
            return new AppEvent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppEvent[] newArray(int i) {
            return new AppEvent[i];
        }
    };
    private String mJsonData;
    private final String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AppEvent(String str) {
        this.mName = str;
    }

    protected AppEvent(Parcel parcel) {
        this.mName = parcel.readString();
        this.mJsonData = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mJsonData);
    }

    public String getName() {
        return this.mName;
    }

    public String getJsonData() {
        return this.mJsonData;
    }

    public void setJsonData(String str) {
        this.mJsonData = str;
    }

    public String toString() {
        return "AppEvent{mName='" + this.mName + "', mJsonData='" + this.mJsonData + "'}";
    }
}
