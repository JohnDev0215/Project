package VideoPlaybackInterface.v1_0;

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
@Generated(from = "VideoPlaybackHeaderElement", generator = "Immutables")
public final class ImmutableVideoPlaybackHeaderElement extends VideoPlaybackHeaderElement {
    private final Long expirationMS;
    private final String token;

    private ImmutableVideoPlaybackHeaderElement(String str, Long l) {
        this.token = str;
        this.expirationMS = l;
    }

    @Override // VideoPlaybackInterface.v1_0.VideoPlaybackHeaderElement
    @JsonProperty("token")
    public String token() {
        return this.token;
    }

    @Override // VideoPlaybackInterface.v1_0.VideoPlaybackHeaderElement
    @JsonProperty("expirationMS")
    public Long expirationMS() {
        return this.expirationMS;
    }

    public final ImmutableVideoPlaybackHeaderElement withToken(String str) {
        String str2 = (String) requireNonNull(str, "token");
        return this.token.equals(str2) ? this : new ImmutableVideoPlaybackHeaderElement(str2, this.expirationMS);
    }

    public final ImmutableVideoPlaybackHeaderElement withExpirationMS(Long l) {
        Long l2 = (Long) requireNonNull(l, "expirationMS");
        return this.expirationMS.equals(l2) ? this : new ImmutableVideoPlaybackHeaderElement(this.token, l2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVideoPlaybackHeaderElement) && equalTo(0, (ImmutableVideoPlaybackHeaderElement) obj);
    }

    private boolean equalTo(int i, ImmutableVideoPlaybackHeaderElement immutableVideoPlaybackHeaderElement) {
        return this.token.equals(immutableVideoPlaybackHeaderElement.token) && this.expirationMS.equals(immutableVideoPlaybackHeaderElement.expirationMS);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.token.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.expirationMS.hashCode();
    }

    public String toString() {
        return "VideoPlaybackHeaderElement{token=" + this.token + ", expirationMS=" + this.expirationMS + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VideoPlaybackHeaderElement", generator = "Immutables")
    static final class Json extends VideoPlaybackHeaderElement {

        @Nullable
        Long expirationMS;

        @Nullable
        String token;

        Json() {
        }

        @JsonProperty("token")
        public void setToken(String str) {
            this.token = str;
        }

        @JsonProperty("expirationMS")
        public void setExpirationMS(Long l) {
            this.expirationMS = l;
        }

        @Override // VideoPlaybackInterface.v1_0.VideoPlaybackHeaderElement
        public String token() {
            throw new UnsupportedOperationException();
        }

        @Override // VideoPlaybackInterface.v1_0.VideoPlaybackHeaderElement
        public Long expirationMS() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVideoPlaybackHeaderElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.token;
        if (str != null) {
            builder.token(str);
        }
        Long l = json.expirationMS;
        if (l != null) {
            builder.expirationMS(l);
        }
        return builder.build();
    }

    public static ImmutableVideoPlaybackHeaderElement copyOf(VideoPlaybackHeaderElement videoPlaybackHeaderElement) {
        if (videoPlaybackHeaderElement instanceof ImmutableVideoPlaybackHeaderElement) {
            return (ImmutableVideoPlaybackHeaderElement) videoPlaybackHeaderElement;
        }
        return builder().from(videoPlaybackHeaderElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VideoPlaybackHeaderElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_EXPIRATION_M_S = 2;
        private static final long INIT_BIT_TOKEN = 1;

        @Nullable
        private Long expirationMS;
        private long initBits;

        @Nullable
        private String token;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(VideoPlaybackHeaderElement videoPlaybackHeaderElement) {
            ImmutableVideoPlaybackHeaderElement.requireNonNull(videoPlaybackHeaderElement, "instance");
            token(videoPlaybackHeaderElement.token());
            expirationMS(videoPlaybackHeaderElement.expirationMS());
            return this;
        }

        @JsonProperty("token")
        public final Builder token(String str) {
            this.token = (String) ImmutableVideoPlaybackHeaderElement.requireNonNull(str, "token");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("expirationMS")
        public final Builder expirationMS(Long l) {
            this.expirationMS = (Long) ImmutableVideoPlaybackHeaderElement.requireNonNull(l, "expirationMS");
            this.initBits &= -3;
            return this;
        }

        public ImmutableVideoPlaybackHeaderElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableVideoPlaybackHeaderElement(this.token, this.expirationMS);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("token");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("expirationMS");
            }
            return "Cannot build VideoPlaybackHeaderElement, some of required attributes are not set " + arrayList;
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
