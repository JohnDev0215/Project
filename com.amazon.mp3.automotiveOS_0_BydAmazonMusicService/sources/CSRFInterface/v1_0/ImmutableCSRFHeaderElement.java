package CSRFInterface.v1_0;

import com.amazon.music.skyfire.models.TetheredMessageKey;
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
@Generated(from = "CSRFHeaderElement", generator = "Immutables")
public final class ImmutableCSRFHeaderElement extends CSRFHeaderElement {
    private final String rndNonce;
    private final Long timestamp;
    private final String token;

    private ImmutableCSRFHeaderElement(String str, Long l, String str2) {
        this.token = str;
        this.timestamp = l;
        this.rndNonce = str2;
    }

    @Override // CSRFInterface.v1_0.CSRFHeaderElement
    @JsonProperty("token")
    public String token() {
        return this.token;
    }

    @Override // CSRFInterface.v1_0.CSRFHeaderElement
    @JsonProperty(TetheredMessageKey.timestamp)
    public Long timestamp() {
        return this.timestamp;
    }

    @Override // CSRFInterface.v1_0.CSRFHeaderElement
    @JsonProperty("rndNonce")
    public String rndNonce() {
        return this.rndNonce;
    }

    public final ImmutableCSRFHeaderElement withToken(String str) {
        String str2 = (String) requireNonNull(str, "token");
        return this.token.equals(str2) ? this : new ImmutableCSRFHeaderElement(str2, this.timestamp, this.rndNonce);
    }

    public final ImmutableCSRFHeaderElement withTimestamp(Long l) {
        Long l2 = (Long) requireNonNull(l, TetheredMessageKey.timestamp);
        return this.timestamp.equals(l2) ? this : new ImmutableCSRFHeaderElement(this.token, l2, this.rndNonce);
    }

    public final ImmutableCSRFHeaderElement withRndNonce(String str) {
        String str2 = (String) requireNonNull(str, "rndNonce");
        return this.rndNonce.equals(str2) ? this : new ImmutableCSRFHeaderElement(this.token, this.timestamp, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableCSRFHeaderElement) && equalTo(0, (ImmutableCSRFHeaderElement) obj);
    }

    private boolean equalTo(int i, ImmutableCSRFHeaderElement immutableCSRFHeaderElement) {
        return this.token.equals(immutableCSRFHeaderElement.token) && this.timestamp.equals(immutableCSRFHeaderElement.timestamp) && this.rndNonce.equals(immutableCSRFHeaderElement.rndNonce);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.token.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.timestamp.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.rndNonce.hashCode();
    }

    public String toString() {
        return "CSRFHeaderElement{token=" + this.token + ", timestamp=" + this.timestamp + ", rndNonce=" + this.rndNonce + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "CSRFHeaderElement", generator = "Immutables")
    static final class Json extends CSRFHeaderElement {

        @Nullable
        String rndNonce;

        @Nullable
        Long timestamp;

        @Nullable
        String token;

        Json() {
        }

        @JsonProperty("token")
        public void setToken(String str) {
            this.token = str;
        }

        @JsonProperty(TetheredMessageKey.timestamp)
        public void setTimestamp(Long l) {
            this.timestamp = l;
        }

        @JsonProperty("rndNonce")
        public void setRndNonce(String str) {
            this.rndNonce = str;
        }

        @Override // CSRFInterface.v1_0.CSRFHeaderElement
        public String token() {
            throw new UnsupportedOperationException();
        }

        @Override // CSRFInterface.v1_0.CSRFHeaderElement
        public Long timestamp() {
            throw new UnsupportedOperationException();
        }

        @Override // CSRFInterface.v1_0.CSRFHeaderElement
        public String rndNonce() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableCSRFHeaderElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.token;
        if (str != null) {
            builder.token(str);
        }
        Long l = json.timestamp;
        if (l != null) {
            builder.timestamp(l);
        }
        String str2 = json.rndNonce;
        if (str2 != null) {
            builder.rndNonce(str2);
        }
        return builder.build();
    }

    public static ImmutableCSRFHeaderElement copyOf(CSRFHeaderElement cSRFHeaderElement) {
        if (cSRFHeaderElement instanceof ImmutableCSRFHeaderElement) {
            return (ImmutableCSRFHeaderElement) cSRFHeaderElement;
        }
        return builder().from(cSRFHeaderElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "CSRFHeaderElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_RND_NONCE = 4;
        private static final long INIT_BIT_TIMESTAMP = 2;
        private static final long INIT_BIT_TOKEN = 1;
        private long initBits;

        @Nullable
        private String rndNonce;

        @Nullable
        private Long timestamp;

        @Nullable
        private String token;

        private Builder() {
            this.initBits = 7L;
        }

        public final Builder from(CSRFHeaderElement cSRFHeaderElement) {
            ImmutableCSRFHeaderElement.requireNonNull(cSRFHeaderElement, "instance");
            token(cSRFHeaderElement.token());
            timestamp(cSRFHeaderElement.timestamp());
            rndNonce(cSRFHeaderElement.rndNonce());
            return this;
        }

        @JsonProperty("token")
        public final Builder token(String str) {
            this.token = (String) ImmutableCSRFHeaderElement.requireNonNull(str, "token");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(TetheredMessageKey.timestamp)
        public final Builder timestamp(Long l) {
            this.timestamp = (Long) ImmutableCSRFHeaderElement.requireNonNull(l, TetheredMessageKey.timestamp);
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("rndNonce")
        public final Builder rndNonce(String str) {
            this.rndNonce = (String) ImmutableCSRFHeaderElement.requireNonNull(str, "rndNonce");
            this.initBits &= -5;
            return this;
        }

        public ImmutableCSRFHeaderElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableCSRFHeaderElement(this.token, this.timestamp, this.rndNonce);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("token");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(TetheredMessageKey.timestamp);
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("rndNonce");
            }
            return "Cannot build CSRFHeaderElement, some of required attributes are not set " + arrayList;
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
