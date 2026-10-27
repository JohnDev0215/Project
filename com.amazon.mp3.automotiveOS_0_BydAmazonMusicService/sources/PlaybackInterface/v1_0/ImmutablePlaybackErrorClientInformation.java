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
@Generated(from = "PlaybackErrorClientInformation", generator = "Immutables")
public final class ImmutablePlaybackErrorClientInformation extends PlaybackErrorClientInformation {

    @Nullable
    private final String context;
    private final String message;

    private ImmutablePlaybackErrorClientInformation(String str, @Nullable String str2) {
        this.message = str;
        this.context = str2;
    }

    @Override // PlaybackInterface.v1_0.PlaybackErrorClientInformation
    @JsonProperty("message")
    public String message() {
        return this.message;
    }

    @Override // PlaybackInterface.v1_0.PlaybackErrorClientInformation
    @JsonProperty("context")
    @Nullable
    public String context() {
        return this.context;
    }

    public final ImmutablePlaybackErrorClientInformation withMessage(String str) {
        String str2 = (String) requireNonNull(str, "message");
        return this.message.equals(str2) ? this : new ImmutablePlaybackErrorClientInformation(str2, this.context);
    }

    public final ImmutablePlaybackErrorClientInformation withContext(@Nullable String str) {
        return equals(this.context, str) ? this : new ImmutablePlaybackErrorClientInformation(this.message, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlaybackErrorClientInformation) && equalTo(0, (ImmutablePlaybackErrorClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutablePlaybackErrorClientInformation immutablePlaybackErrorClientInformation) {
        return this.message.equals(immutablePlaybackErrorClientInformation.message) && equals(this.context, immutablePlaybackErrorClientInformation.context);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.message.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + hashCode(this.context);
    }

    public String toString() {
        return "PlaybackErrorClientInformation{message=" + this.message + ", context=" + this.context + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PlaybackErrorClientInformation", generator = "Immutables")
    static final class Json extends PlaybackErrorClientInformation {

        @Nullable
        String context;

        @Nullable
        String message;

        Json() {
        }

        @JsonProperty("message")
        public void setMessage(String str) {
            this.message = str;
        }

        @JsonProperty("context")
        public void setContext(@Nullable String str) {
            this.context = str;
        }

        @Override // PlaybackInterface.v1_0.PlaybackErrorClientInformation
        public String message() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlaybackErrorClientInformation
        public String context() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePlaybackErrorClientInformation fromJson(Json json) {
        Builder builder = builder();
        String str = json.message;
        if (str != null) {
            builder.message(str);
        }
        String str2 = json.context;
        if (str2 != null) {
            builder.context(str2);
        }
        return builder.build();
    }

    public static ImmutablePlaybackErrorClientInformation copyOf(PlaybackErrorClientInformation playbackErrorClientInformation) {
        if (playbackErrorClientInformation instanceof ImmutablePlaybackErrorClientInformation) {
            return (ImmutablePlaybackErrorClientInformation) playbackErrorClientInformation;
        }
        return builder().from(playbackErrorClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PlaybackErrorClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_MESSAGE = 1;

        @Nullable
        private String context;
        private long initBits;

        @Nullable
        private String message;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(PlaybackErrorClientInformation playbackErrorClientInformation) {
            ImmutablePlaybackErrorClientInformation.requireNonNull(playbackErrorClientInformation, "instance");
            message(playbackErrorClientInformation.message());
            String strContext = playbackErrorClientInformation.context();
            if (strContext != null) {
                context(strContext);
            }
            return this;
        }

        @JsonProperty("message")
        public final Builder message(String str) {
            this.message = (String) ImmutablePlaybackErrorClientInformation.requireNonNull(str, "message");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("context")
        public final Builder context(@Nullable String str) {
            this.context = str;
            return this;
        }

        public ImmutablePlaybackErrorClientInformation build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePlaybackErrorClientInformation(this.message, this.context);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("message");
            }
            return "Cannot build PlaybackErrorClientInformation, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
