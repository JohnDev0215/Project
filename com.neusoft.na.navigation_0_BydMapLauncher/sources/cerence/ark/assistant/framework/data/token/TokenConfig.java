package cerence.ark.assistant.framework.data.token;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class TokenConfig implements Parcelable {
    public static final Parcelable.Creator<TokenConfig> CREATOR = new Parcelable.Creator<TokenConfig>() { // from class: cerence.ark.assistant.framework.data.token.TokenConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TokenConfig createFromParcel(Parcel parcel) {
            return new TokenConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TokenConfig[] newArray(int i) {
            return new TokenConfig[i];
        }
    };
    private final String mAdapterParams;
    private final String mClientId;
    private final String mClientSecret;
    private final String mExtraParams;
    private final String mGrantType;
    private final String mScope;
    private final String mSslCertificateBundle;
    private final String mUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private TokenConfig(Builder builder) {
        this.mGrantType = builder.mGrantType;
        this.mClientId = builder.mClientId;
        this.mClientSecret = builder.mClientSecret;
        this.mUrl = builder.mUrl;
        this.mScope = builder.mScope;
        this.mSslCertificateBundle = builder.mSslCertificateBundle;
        this.mExtraParams = builder.mExtraParams;
        this.mAdapterParams = builder.mAdapterParams;
    }

    protected TokenConfig(Parcel parcel) {
        this.mGrantType = parcel.readString();
        this.mClientId = parcel.readString();
        this.mClientSecret = parcel.readString();
        this.mUrl = parcel.readString();
        this.mScope = parcel.readString();
        this.mSslCertificateBundle = parcel.readString();
        this.mExtraParams = parcel.readString();
        this.mAdapterParams = parcel.readString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getClientId() {
        return this.mClientId;
    }

    public String getClientSecret() {
        return this.mClientSecret;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getScope() {
        return this.mScope;
    }

    public String getSslCertificateBundle() {
        return this.mSslCertificateBundle;
    }

    public String getAdapterParams() {
        return this.mAdapterParams;
    }

    public String getGrantType() {
        return this.mGrantType;
    }

    public String getExtraParams() {
        return this.mExtraParams;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mGrantType);
        parcel.writeString(this.mClientId);
        parcel.writeString(this.mClientSecret);
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mScope);
        parcel.writeString(this.mSslCertificateBundle);
        parcel.writeString(this.mExtraParams);
        parcel.writeString(this.mAdapterParams);
    }

    public String toString() {
        return "TokenConfig{mGrantType='" + this.mGrantType + "', mClientId='" + this.mClientId + "', mClientSecret='" + this.mClientSecret + "', mUrl='" + this.mUrl + "', mScope='" + this.mScope + "', mSslCertificateBundle='" + this.mSslCertificateBundle + "', mExtraParams='" + this.mExtraParams + "', mAdapterParams='" + this.mAdapterParams + "'}";
    }

    public static final class Builder {
        private String mAdapterParams;
        private String mClientId;
        private String mClientSecret;
        private String mExtraParams;
        private String mGrantType;
        private String mScope;
        private String mSslCertificateBundle;
        private String mUrl;

        public Builder setGrantType(String str) {
            this.mGrantType = str;
            return this;
        }

        public Builder setExtraParams(String str) {
            this.mExtraParams = str;
            return this;
        }

        public Builder setClientId(String str) {
            this.mClientId = str;
            return this;
        }

        public Builder setClientSecret(String str) {
            this.mClientSecret = str;
            return this;
        }

        public Builder setUrl(String str) {
            this.mUrl = str;
            return this;
        }

        public Builder setScope(String str) {
            this.mScope = str;
            return this;
        }

        public Builder setSslCertificateBundle(String str) {
            this.mSslCertificateBundle = str;
            return this;
        }

        public Builder setAdapterParams(String str) {
            this.mAdapterParams = str;
            return this;
        }

        public TokenConfig build() {
            return new TokenConfig(this);
        }
    }
}
