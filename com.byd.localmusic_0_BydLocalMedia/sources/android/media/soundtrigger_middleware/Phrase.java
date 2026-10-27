package android.media.soundtrigger_middleware;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class Phrase implements Parcelable {
    public static final Parcelable.Creator<Phrase> CREATOR = new Parcelable.Creator<Phrase>() { // from class: android.media.soundtrigger_middleware.Phrase.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Phrase createFromParcel(Parcel parcel) {
            Phrase phrase = new Phrase();
            phrase.readFromParcel(parcel);
            return phrase;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Phrase[] newArray(int i) {
            return new Phrase[i];
        }
    };
    public int id;
    public String locale;
    public int recognitionModes;
    public String text;
    public int[] users;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.id);
        parcel.writeInt(this.recognitionModes);
        parcel.writeIntArray(this.users);
        parcel.writeString(this.locale);
        parcel.writeString(this.text);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int i = parcel.readInt();
        if (i < 0) {
            return;
        }
        try {
            this.id = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.recognitionModes = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.users = parcel.createIntArray();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.locale = parcel.readString();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.text = parcel.readString();
                            if (parcel.dataPosition() - iDataPosition < i) {
                            }
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
