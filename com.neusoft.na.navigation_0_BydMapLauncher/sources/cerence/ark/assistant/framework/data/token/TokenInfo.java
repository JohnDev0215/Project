package cerence.ark.assistant.framework.data.token;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class TokenInfo implements Parcelable {
    public static final Parcelable.Creator<TokenInfo> CREATOR = new Parcelable.Creator<TokenInfo>() { // from class: cerence.ark.assistant.framework.data.token.TokenInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TokenInfo createFromParcel(Parcel parcel) {
            return new TokenInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TokenInfo[] newArray(int i) {
            return new TokenInfo[i];
        }
    };
    private final String mAccessToken;
    private final int mExpireIn;
    private final String mRefreshToken;
    private final String mTokenType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private TokenInfo(Builder builder) {
        this.mTokenType = builder.mTokenType;
        this.mAccessToken = builder.mAccessToken;
        this.mRefreshToken = builder.mRefreshToken;
        this.mExpireIn = builder.mExpireIn;
    }

    protected TokenInfo(Parcel parcel) {
        this.mTokenType = parcel.readString();
        this.mAccessToken = parcel.readString();
        this.mRefreshToken = parcel.readString();
        this.mExpireIn = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTokenType);
        parcel.writeString(this.mAccessToken);
        parcel.writeString(this.mRefreshToken);
        parcel.writeInt(this.mExpireIn);
    }

    public String getTokenType() {
        return this.mTokenType;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public int getExpireIn() {
        return this.mExpireIn;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String toString() {
        return "TokenInfo{mTokenType='" + this.mTokenType + "', mAccessToken='" + this.mAccessToken + "', mRefreshToken='" + this.mRefreshToken + "', mExpireIn=" + this.mExpireIn + '}';
    }

    public static final class Builder {
        private String mAccessToken;
        private int mExpireIn;
        private String mRefreshToken;
        private String mTokenType;

        public Builder setTokenType(String str) {
            this.mTokenType = str;
            return this;
        }

        public Builder setAccessToken(String str) {
            this.mAccessToken = str;
            return this;
        }

        public Builder setRefreshToken(String str) {
            this.mRefreshToken = str;
            return this;
        }

        public Builder setExpireIn(int i) {
            this.mExpireIn = i;
            return this;
        }

        public TokenInfo build() {
            return new TokenInfo(this);
        }
    }
}
