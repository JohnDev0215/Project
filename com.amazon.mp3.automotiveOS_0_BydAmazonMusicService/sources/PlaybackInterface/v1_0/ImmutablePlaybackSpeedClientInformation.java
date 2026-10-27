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
@Generated(from = "PlaybackSpeedClientInformation", generator = "Immutables")
public final class ImmutablePlaybackSpeedClientInformation extends PlaybackSpeedClientInformation {
    private final Double playbackSpeed;

    private ImmutablePlaybackSpeedClientInformation(Double d) {
        this.playbackSpeed = d;
    }

    @Override // PlaybackInterface.v1_0.PlaybackSpeedClientInformation
    @JsonProperty("playbackSpeed")
    public Double playbackSpeed() {
        return this.playbackSpeed;
    }

    public final ImmutablePlaybackSpeedClientInformation withPlaybackSpeed(Double d) {
        Double d2 = (Double) requireNonNull(d, "playbackSpeed");
        return this.playbackSpeed.equals(d2) ? this : new ImmutablePlaybackSpeedClientInformation(d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlaybackSpeedClientInformation) && equalTo(0, (ImmutablePlaybackSpeedClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutablePlaybackSpeedClientInformation immutablePlaybackSpeedClientInformation) {
        return this.playbackSpeed.equals(immutablePlaybackSpeedClientInformation.playbackSpeed);
    }

    public int hashCode() {
        return 172192 + this.playbackSpeed.hashCode() + 5381;
    }

    public String toString() {
        return "PlaybackSpeedClientInformation{playbackSpeed=" + this.playbackSpeed + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PlaybackSpeedClientInformation", generator = "Immutables")
    static final class Json extends PlaybackSpeedClientInformation {

        @Nullable
        Double playbackSpeed;

        Json() {
        }

        @JsonProperty("playbackSpeed")
        public void setPlaybackSpeed(Double d) {
            this.playbackSpeed = d;
        }

        @Override // PlaybackInterface.v1_0.PlaybackSpeedClientInformation
        public Double playbackSpeed() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePlaybackSpeedClientInformation fromJson(Json json) {
        Builder builder = builder();
        Double d = json.playbackSpeed;
        if (d != null) {
            builder.playbackSpeed(d);
        }
        return builder.build();
    }

    public static ImmutablePlaybackSpeedClientInformation copyOf(PlaybackSpeedClientInformation playbackSpeedClientInformation) {
        if (playbackSpeedClientInformation instanceof ImmutablePlaybackSpeedClientInformation) {
            return (ImmutablePlaybackSpeedClientInformation) playbackSpeedClientInformation;
        }
        return builder().from(playbackSpeedClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PlaybackSpeedClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PLAYBACK_SPEED = 1;
        private long initBits;

        @Nullable
        private Double playbackSpeed;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(PlaybackSpeedClientInformation playbackSpeedClientInformation) {
            ImmutablePlaybackSpeedClientInformation.requireNonNull(playbackSpeedClientInformation, "instance");
            playbackSpeed(playbackSpeedClientInformation.playbackSpeed());
            return this;
        }

        @JsonProperty("playbackSpeed")
        public final Builder playbackSpeed(Double d) {
            this.playbackSpeed = (Double) ImmutablePlaybackSpeedClientInformation.requireNonNull(d, "playbackSpeed");
            this.initBits &= -2;
            return this;
        }

        public ImmutablePlaybackSpeedClientInformation build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePlaybackSpeedClientInformation(this.playbackSpeed);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("playbackSpeed");
            }
            return "Cannot build PlaybackSpeedClientInformation, some of required attributes are not set " + arrayList;
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
