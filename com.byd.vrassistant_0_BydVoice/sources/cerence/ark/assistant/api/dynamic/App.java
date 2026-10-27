package cerence.ark.assistant.api.dynamic;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class App implements Parcelable {
    public static final Parcelable.Creator<App> CREATOR = new Parcelable.Creator<App>() { // from class: cerence.ark.assistant.api.dynamic.App.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public App createFromParcel(Parcel parcel) {
            return new App(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public App[] newArray(int i) {
            return new App[i];
        }
    };
    private int mId;
    private String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public App() {
    }

    protected App(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mName = parcel.readString();
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String toString() {
        return "App{id=" + this.mId + ", name='" + this.mName + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeString(this.mName);
    }
}
