package PlaybackInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "ScrubClientInformation", generator = "Immutables")
public final class ImmutableScrubClientInformation extends ScrubClientInformation {

    @Nullable
    private final Integer currentTime;

    @Nullable
    private final Integer scrubTime;

    private ImmutableScrubClientInformation(@Nullable Integer num, @Nullable Integer num2) {
        this.currentTime = num;
        this.scrubTime = num2;
    }

    @Override // PlaybackInterface.v1_0.ScrubClientInformation
    @JsonProperty("currentTime")
    @Nullable
    public Integer currentTime() {
        return this.currentTime;
    }

    @Override // PlaybackInterface.v1_0.ScrubClientInformation
    @JsonProperty("scrubTime")
    @Nullable
    public Integer scrubTime() {
        return this.scrubTime;
    }

    public final ImmutableScrubClientInformation withCurrentTime(@Nullable Integer num) {
        return equals(this.currentTime, num) ? this : new ImmutableScrubClientInformation(num, this.scrubTime);
    }

    public final ImmutableScrubClientInformation withScrubTime(@Nullable Integer num) {
        return equals(this.scrubTime, num) ? this : new ImmutableScrubClientInformation(this.currentTime, num);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableScrubClientInformation) && equalTo(0, (ImmutableScrubClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableScrubClientInformation immutableScrubClientInformation) {
        return equals(this.currentTime, immutableScrubClientInformation.currentTime) && equals(this.scrubTime, immutableScrubClientInformation.scrubTime);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.currentTime) + 5381;
        return iHashCode + (iHashCode << 5) + hashCode(this.scrubTime);
    }

    public String toString() {
        return "ScrubClientInformation{currentTime=" + this.currentTime + ", scrubTime=" + this.scrubTime + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ScrubClientInformation", generator = "Immutables")
    static final class Json extends ScrubClientInformation {

        @Nullable
        Integer currentTime;

        @Nullable
        Integer scrubTime;

        Json() {
        }

        @JsonProperty("currentTime")
        public void setCurrentTime(@Nullable Integer num) {
            this.currentTime = num;
        }

        @JsonProperty("scrubTime")
        public void setScrubTime(@Nullable Integer num) {
            this.scrubTime = num;
        }

        @Override // PlaybackInterface.v1_0.ScrubClientInformation
        public Integer currentTime() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ScrubClientInformation
        public Integer scrubTime() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableScrubClientInformation fromJson(Json json) {
        Builder builder = builder();
        Integer num = json.currentTime;
        if (num != null) {
            builder.currentTime(num);
        }
        Integer num2 = json.scrubTime;
        if (num2 != null) {
            builder.scrubTime(num2);
        }
        return builder.build();
    }

    public static ImmutableScrubClientInformation copyOf(ScrubClientInformation scrubClientInformation) {
        if (scrubClientInformation instanceof ImmutableScrubClientInformation) {
            return (ImmutableScrubClientInformation) scrubClientInformation;
        }
        return builder().from(scrubClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ScrubClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {

        @Nullable
        private Integer currentTime;

        @Nullable
        private Integer scrubTime;

        private Builder() {
        }

        public final Builder from(ScrubClientInformation scrubClientInformation) {
            ImmutableScrubClientInformation.requireNonNull(scrubClientInformation, "instance");
            Integer numCurrentTime = scrubClientInformation.currentTime();
            if (numCurrentTime != null) {
                currentTime(numCurrentTime);
            }
            Integer numScrubTime = scrubClientInformation.scrubTime();
            if (numScrubTime != null) {
                scrubTime(numScrubTime);
            }
            return this;
        }

        @JsonProperty("currentTime")
        public final Builder currentTime(@Nullable Integer num) {
            this.currentTime = num;
            return this;
        }

        @JsonProperty("scrubTime")
        public final Builder scrubTime(@Nullable Integer num) {
            this.scrubTime = num;
            return this;
        }

        public ImmutableScrubClientInformation build() {
            return new ImmutableScrubClientInformation(this.currentTime, this.scrubTime);
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
