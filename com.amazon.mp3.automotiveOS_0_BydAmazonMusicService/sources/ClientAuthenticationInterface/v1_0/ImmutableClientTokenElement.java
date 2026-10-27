package ClientAuthenticationInterface.v1_0;

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
@Generated(from = "ClientTokenElement", generator = "Immutables")
public final class ImmutableClientTokenElement extends ClientTokenElement {
    private final String accessToken;

    private ImmutableClientTokenElement(String str) {
        this.accessToken = str;
    }

    @Override // ClientAuthenticationInterface.v1_0.ClientTokenElement
    @JsonProperty("accessToken")
    public String accessToken() {
        return this.accessToken;
    }

    public final ImmutableClientTokenElement withAccessToken(String str) {
        String str2 = (String) requireNonNull(str, "accessToken");
        return this.accessToken.equals(str2) ? this : new ImmutableClientTokenElement(str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableClientTokenElement) && equalTo(0, (ImmutableClientTokenElement) obj);
    }

    private boolean equalTo(int i, ImmutableClientTokenElement immutableClientTokenElement) {
        return this.accessToken.equals(immutableClientTokenElement.accessToken);
    }

    public int hashCode() {
        return 172192 + this.accessToken.hashCode() + 5381;
    }

    public String toString() {
        return "ClientTokenElement{accessToken=" + this.accessToken + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ClientTokenElement", generator = "Immutables")
    static final class Json extends ClientTokenElement {

        @Nullable
        String accessToken;

        Json() {
        }

        @JsonProperty("accessToken")
        public void setAccessToken(String str) {
            this.accessToken = str;
        }

        @Override // ClientAuthenticationInterface.v1_0.ClientTokenElement
        public String accessToken() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableClientTokenElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.accessToken;
        if (str != null) {
            builder.accessToken(str);
        }
        return builder.build();
    }

    public static ImmutableClientTokenElement copyOf(ClientTokenElement clientTokenElement) {
        if (clientTokenElement instanceof ImmutableClientTokenElement) {
            return (ImmutableClientTokenElement) clientTokenElement;
        }
        return builder().from(clientTokenElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ClientTokenElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ACCESS_TOKEN = 1;

        @Nullable
        private String accessToken;
        private long initBits;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(ClientTokenElement clientTokenElement) {
            ImmutableClientTokenElement.requireNonNull(clientTokenElement, "instance");
            accessToken(clientTokenElement.accessToken());
            return this;
        }

        @JsonProperty("accessToken")
        public final Builder accessToken(String str) {
            this.accessToken = (String) ImmutableClientTokenElement.requireNonNull(str, "accessToken");
            this.initBits &= -2;
            return this;
        }

        public ImmutableClientTokenElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableClientTokenElement(this.accessToken);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("accessToken");
            }
            return "Cannot build ClientTokenElement, some of required attributes are not set " + arrayList;
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
