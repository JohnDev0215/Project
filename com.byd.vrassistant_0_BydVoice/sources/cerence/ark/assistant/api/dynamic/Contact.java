package cerence.ark.assistant.api.dynamic;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Contact implements Parcelable {
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() { // from class: cerence.ark.assistant.api.dynamic.Contact.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Contact createFromParcel(Parcel parcel) {
            return new Contact(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Contact[] newArray(int i) {
            return new Contact[i];
        }
    };
    private long mId;
    private String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Contact() {
    }

    protected Contact(Parcel parcel) {
        this.mId = parcel.readLong();
        this.mName = parcel.readString();
    }

    public long getId() {
        return this.mId;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mId);
        parcel.writeString(this.mName);
    }

    public String toString() {
        return "Contact{mId=" + this.mId + ", mName='" + this.mName + "'}";
    }
}
