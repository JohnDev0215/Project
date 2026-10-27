package cerence.ark.assistant.framework.data.token;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum TokenProviderType implements Parcelable {
    OAUTH,
    JWT;

    public static final Parcelable.Creator<TokenProviderType> CREATOR = new Parcelable.Creator<TokenProviderType>() { // from class: cerence.ark.assistant.framework.data.token.TokenProviderType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TokenProviderType createFromParcel(Parcel parcel) {
            return TokenProviderType.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TokenProviderType[] newArray(int i) {
            return new TokenProviderType[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
