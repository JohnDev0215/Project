package cerence.ark.assistant.api.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicWord implements Parcelable {
    public static final Parcelable.Creator<DynamicWord> CREATOR = new Parcelable.Creator<DynamicWord>() { // from class: cerence.ark.assistant.api.data.DynamicWord.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DynamicWord createFromParcel(Parcel parcel) {
            return new DynamicWord(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DynamicWord[] newArray(int i) {
            return new DynamicWord[i];
        }
    };
    private final int mId;
    private final String mPhrase;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private DynamicWord(Builder builder) {
        this.mId = builder.mId;
        this.mPhrase = builder.mPhrase;
    }

    protected DynamicWord(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mPhrase = parcel.readString();
    }

    public int getId() {
        return this.mId;
    }

    public String getPhrase() {
        return this.mPhrase;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeString(this.mPhrase);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DynamicWord) {
            return this.mPhrase.equals(((DynamicWord) obj).mPhrase);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mPhrase);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int mId;
        private String mPhrase;

        private Builder() {
            this.mPhrase = "";
        }

        public Builder setId(int i) {
            this.mId = i;
            return this;
        }

        public Builder setPhrase(String str) {
            if (str == null) {
                return this;
            }
            this.mPhrase = str;
            return this;
        }

        public DynamicWord build() {
            return new DynamicWord(this);
        }
    }
}
