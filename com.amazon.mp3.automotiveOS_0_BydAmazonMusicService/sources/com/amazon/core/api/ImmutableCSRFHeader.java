package com.amazon.core.api;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes2.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "CSRFHeader", generator = "Immutables")
public final class ImmutableCSRFHeader extends CSRFHeader {
    private final String rndNonce;
    private final Long timestamp;
    private final String token;

    private ImmutableCSRFHeader(String str, String str2, Long l) {
        this.token = str;
        this.rndNonce = str2;
        this.timestamp = l;
    }

    @Override // com.amazon.core.api.CSRFHeader
    @JsonProperty("token")
    public String token() {
        return this.token;
    }

    @Override // com.amazon.core.api.CSRFHeader
    @JsonProperty("rndNonce")
    public String rndNonce() {
        return this.rndNonce;
    }

    @Override // com.amazon.core.api.CSRFHeader
    @JsonProperty(TetheredMessageKey.timestamp)
    public Long timestamp() {
        return this.timestamp;
    }

    public final ImmutableCSRFHeader withToken(String str) {
        String str2 = (String) requireNonNull(str, "token");
        return this.token.equals(str2) ? this : new ImmutableCSRFHeader(str2, this.rndNonce, this.timestamp);
    }

    public final ImmutableCSRFHeader withRndNonce(String str) {
        String str2 = (String) requireNonNull(str, "rndNonce");
        return this.rndNonce.equals(str2) ? this : new ImmutableCSRFHeader(this.token, str2, this.timestamp);
    }

    public final ImmutableCSRFHeader withTimestamp(Long l) {
        Long l2 = (Long) requireNonNull(l, TetheredMessageKey.timestamp);
        return this.timestamp.equals(l2) ? this : new ImmutableCSRFHeader(this.token, this.rndNonce, l2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableCSRFHeader) && equalTo(0, (ImmutableCSRFHeader) obj);
    }

    private boolean equalTo(int i, ImmutableCSRFHeader immutableCSRFHeader) {
        return this.token.equals(immutableCSRFHeader.token) && this.rndNonce.equals(immutableCSRFHeader.rndNonce) && this.timestamp.equals(immutableCSRFHeader.timestamp);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.token.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.rndNonce.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.timestamp.hashCode();
    }

    public String toString() {
        return "CSRFHeader{token=" + this.token + ", rndNonce=" + this.rndNonce + ", timestamp=" + this.timestamp + "}";
    }

    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "CSRFHeader", generator = "Immutables")
    static final class Json extends CSRFHeader {

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

        @JsonProperty("rndNonce")
        public void setRndNonce(String str) {
            this.rndNonce = str;
        }

        @JsonProperty(TetheredMessageKey.timestamp)
        public void setTimestamp(Long l) {
            this.timestamp = l;
        }

        @Override // com.amazon.core.api.CSRFHeader
        public String token() {
            throw new UnsupportedOperationException();
        }

        @Override // com.amazon.core.api.CSRFHeader
        public String rndNonce() {
            throw new UnsupportedOperationException();
        }

        @Override // com.amazon.core.api.CSRFHeader
        public Long timestamp() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableCSRFHeader fromJson(Json json) {
        Builder builder = builder();
        String str = json.token;
        if (str != null) {
            builder.token(str);
        }
        String str2 = json.rndNonce;
        if (str2 != null) {
            builder.rndNonce(str2);
        }
        Long l = json.timestamp;
        if (l != null) {
            builder.timestamp(l);
        }
        return builder.build();
    }

    public static ImmutableCSRFHeader copyOf(CSRFHeader cSRFHeader) {
        if (cSRFHeader instanceof ImmutableCSRFHeader) {
            return (ImmutableCSRFHeader) cSRFHeader;
        }
        return builder().from(cSRFHeader).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "CSRFHeader", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_RND_NONCE = 2;
        private static final long INIT_BIT_TIMESTAMP = 4;
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

        public final Builder from(CSRFHeader cSRFHeader) {
            ImmutableCSRFHeader.requireNonNull(cSRFHeader, "instance");
            token(cSRFHeader.token());
            rndNonce(cSRFHeader.rndNonce());
            timestamp(cSRFHeader.timestamp());
            return this;
        }

        @JsonProperty("token")
        public final Builder token(String str) {
            this.token = (String) ImmutableCSRFHeader.requireNonNull(str, "token");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("rndNonce")
        public final Builder rndNonce(String str) {
            this.rndNonce = (String) ImmutableCSRFHeader.requireNonNull(str, "rndNonce");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty(TetheredMessageKey.timestamp)
        public final Builder timestamp(Long l) {
            this.timestamp = (Long) ImmutableCSRFHeader.requireNonNull(l, TetheredMessageKey.timestamp);
            this.initBits &= -5;
            return this;
        }

        public ImmutableCSRFHeader build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableCSRFHeader(this.token, this.rndNonce, this.timestamp);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("token");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("rndNonce");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add(TetheredMessageKey.timestamp);
            }
            return "Cannot build CSRFHeader, some of required attributes are not set " + arrayList;
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
