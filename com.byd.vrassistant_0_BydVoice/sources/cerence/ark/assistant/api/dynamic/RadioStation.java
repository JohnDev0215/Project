package cerence.ark.assistant.api.dynamic;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class RadioStation implements Parcelable {
    public static final Parcelable.Creator<RadioStation> CREATOR = new Parcelable.Creator<RadioStation>() { // from class: cerence.ark.assistant.api.dynamic.RadioStation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RadioStation createFromParcel(Parcel parcel) {
            return new RadioStation(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RadioStation[] newArray(int i) {
            return new RadioStation[i];
        }
    };
    private final int mId;
    private final String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private RadioStation(Builder builder) {
        this.mId = builder.mId;
        this.mName = builder.mName;
    }

    protected RadioStation(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mName = parcel.readString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder copy(RadioStation radioStation) {
        Builder builder = new Builder();
        builder.mId = radioStation.mId;
        builder.mName = radioStation.mName;
        return builder;
    }

    public int getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String toString() {
        return "RadioStation{mId=" + this.mId + ", mName='" + this.mName + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeString(this.mName);
    }

    public static class Builder {
        private int mId;
        private String mName;

        private Builder() {
        }

        public Builder setId(int i) {
            this.mId = i;
            return this;
        }

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public RadioStation build() {
            return new RadioStation(this);
        }
    }
}
