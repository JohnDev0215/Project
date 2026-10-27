package PlaybackInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "LicenseRequestClientInformation", generator = "Immutables")
public final class ImmutableLicenseRequestClientInformation extends LicenseRequestClientInformation {
    private final String assetId;
    private final String licenseInfo;
    private final String mediaId;

    private ImmutableLicenseRequestClientInformation(String str, String str2, String str3) {
        this.mediaId = str;
        this.assetId = str2;
        this.licenseInfo = str3;
    }

    @Override // PlaybackInterface.v1_0.LicenseRequestClientInformation
    @JsonProperty("mediaId")
    public String mediaId() {
        return this.mediaId;
    }

    @Override // PlaybackInterface.v1_0.LicenseRequestClientInformation
    @JsonProperty("assetId")
    public String assetId() {
        return this.assetId;
    }

    @Override // PlaybackInterface.v1_0.LicenseRequestClientInformation
    @JsonProperty("licenseInfo")
    public String licenseInfo() {
        return this.licenseInfo;
    }

    public final ImmutableLicenseRequestClientInformation withMediaId(String str) {
        String str2 = (String) requireNonNull(str, "mediaId");
        return this.mediaId.equals(str2) ? this : new ImmutableLicenseRequestClientInformation(str2, this.assetId, this.licenseInfo);
    }

    public final ImmutableLicenseRequestClientInformation withAssetId(String str) {
        String str2 = (String) requireNonNull(str, "assetId");
        return this.assetId.equals(str2) ? this : new ImmutableLicenseRequestClientInformation(this.mediaId, str2, this.licenseInfo);
    }

    public final ImmutableLicenseRequestClientInformation withLicenseInfo(String str) {
        String str2 = (String) requireNonNull(str, "licenseInfo");
        return this.licenseInfo.equals(str2) ? this : new ImmutableLicenseRequestClientInformation(this.mediaId, this.assetId, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableLicenseRequestClientInformation) && equalTo(0, (ImmutableLicenseRequestClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableLicenseRequestClientInformation immutableLicenseRequestClientInformation) {
        return this.mediaId.equals(immutableLicenseRequestClientInformation.mediaId) && this.assetId.equals(immutableLicenseRequestClientInformation.assetId) && this.licenseInfo.equals(immutableLicenseRequestClientInformation.licenseInfo);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.mediaId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.assetId.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.licenseInfo.hashCode();
    }

    public String toString() {
        return "LicenseRequestClientInformation{mediaId=" + this.mediaId + ", assetId=" + this.assetId + ", licenseInfo=" + this.licenseInfo + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "LicenseRequestClientInformation", generator = "Immutables")
    static final class Json extends LicenseRequestClientInformation {

        @Nullable
        String assetId;

        @Nullable
        String licenseInfo;

        @Nullable
        String mediaId;

        Json() {
        }

        @JsonProperty("mediaId")
        public void setMediaId(String str) {
            this.mediaId = str;
        }

        @JsonProperty("assetId")
        public void setAssetId(String str) {
            this.assetId = str;
        }

        @JsonProperty("licenseInfo")
        public void setLicenseInfo(String str) {
            this.licenseInfo = str;
        }

        @Override // PlaybackInterface.v1_0.LicenseRequestClientInformation
        public String mediaId() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.LicenseRequestClientInformation
        public String assetId() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.LicenseRequestClientInformation
        public String licenseInfo() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableLicenseRequestClientInformation fromJson(Json json) {
        Builder builder = builder();
        String str = json.mediaId;
        if (str != null) {
            builder.mediaId(str);
        }
        String str2 = json.assetId;
        if (str2 != null) {
            builder.assetId(str2);
        }
        String str3 = json.licenseInfo;
        if (str3 != null) {
            builder.licenseInfo(str3);
        }
        return builder.build();
    }

    public static ImmutableLicenseRequestClientInformation copyOf(LicenseRequestClientInformation licenseRequestClientInformation) {
        if (licenseRequestClientInformation instanceof ImmutableLicenseRequestClientInformation) {
            return (ImmutableLicenseRequestClientInformation) licenseRequestClientInformation;
        }
        return builder().from(licenseRequestClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "LicenseRequestClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ASSET_ID = 2;
        private static final long INIT_BIT_LICENSE_INFO = 4;
        private static final long INIT_BIT_MEDIA_ID = 1;

        @Nullable
        private String assetId;
        private long initBits;

        @Nullable
        private String licenseInfo;

        @Nullable
        private String mediaId;

        private Builder() {
            this.initBits = 7L;
        }

        public final Builder from(LicenseRequestClientInformation licenseRequestClientInformation) {
            ImmutableLicenseRequestClientInformation.requireNonNull(licenseRequestClientInformation, "instance");
            mediaId(licenseRequestClientInformation.mediaId());
            assetId(licenseRequestClientInformation.assetId());
            licenseInfo(licenseRequestClientInformation.licenseInfo());
            return this;
        }

        @JsonProperty("mediaId")
        public final Builder mediaId(String str) {
            this.mediaId = (String) ImmutableLicenseRequestClientInformation.requireNonNull(str, "mediaId");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("assetId")
        public final Builder assetId(String str) {
            this.assetId = (String) ImmutableLicenseRequestClientInformation.requireNonNull(str, "assetId");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("licenseInfo")
        public final Builder licenseInfo(String str) {
            this.licenseInfo = (String) ImmutableLicenseRequestClientInformation.requireNonNull(str, "licenseInfo");
            this.initBits &= -5;
            return this;
        }

        public ImmutableLicenseRequestClientInformation build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableLicenseRequestClientInformation(this.mediaId, this.assetId, this.licenseInfo);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("mediaId");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("assetId");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("licenseInfo");
            }
            return "Cannot build LicenseRequestClientInformation, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
