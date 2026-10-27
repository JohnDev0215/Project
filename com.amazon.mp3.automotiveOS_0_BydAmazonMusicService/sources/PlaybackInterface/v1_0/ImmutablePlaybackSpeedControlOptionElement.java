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
@Generated(from = "PlaybackSpeedControlOptionElement", generator = "Immutables")
public final class ImmutablePlaybackSpeedControlOptionElement extends PlaybackSpeedControlOptionElement {
    private final Double speed;
    private final String text;

    private ImmutablePlaybackSpeedControlOptionElement(Double d, String str) {
        this.speed = d;
        this.text = str;
    }

    @Override // PlaybackInterface.v1_0.PlaybackSpeedControlOptionElement
    @JsonProperty("speed")
    public Double speed() {
        return this.speed;
    }

    @Override // PlaybackInterface.v1_0.PlaybackSpeedControlOptionElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    public final ImmutablePlaybackSpeedControlOptionElement withSpeed(Double d) {
        Double d2 = (Double) requireNonNull(d, "speed");
        return this.speed.equals(d2) ? this : new ImmutablePlaybackSpeedControlOptionElement(d2, this.text);
    }

    public final ImmutablePlaybackSpeedControlOptionElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutablePlaybackSpeedControlOptionElement(this.speed, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlaybackSpeedControlOptionElement) && equalTo(0, (ImmutablePlaybackSpeedControlOptionElement) obj);
    }

    private boolean equalTo(int i, ImmutablePlaybackSpeedControlOptionElement immutablePlaybackSpeedControlOptionElement) {
        return this.speed.equals(immutablePlaybackSpeedControlOptionElement.speed) && this.text.equals(immutablePlaybackSpeedControlOptionElement.text);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.speed.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.text.hashCode();
    }

    public String toString() {
        return "PlaybackSpeedControlOptionElement{speed=" + this.speed + ", text=" + this.text + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PlaybackSpeedControlOptionElement", generator = "Immutables")
    static final class Json extends PlaybackSpeedControlOptionElement {

        @Nullable
        Double speed;

        @Nullable
        String text;

        Json() {
        }

        @JsonProperty("speed")
        public void setSpeed(Double d) {
            this.speed = d;
        }

        @JsonProperty("text")
        public void setText(String str) {
            this.text = str;
        }

        @Override // PlaybackInterface.v1_0.PlaybackSpeedControlOptionElement
        public Double speed() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlaybackSpeedControlOptionElement
        public String text() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePlaybackSpeedControlOptionElement fromJson(Json json) {
        Builder builder = builder();
        Double d = json.speed;
        if (d != null) {
            builder.speed(d);
        }
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        return builder.build();
    }

    public static ImmutablePlaybackSpeedControlOptionElement copyOf(PlaybackSpeedControlOptionElement playbackSpeedControlOptionElement) {
        if (playbackSpeedControlOptionElement instanceof ImmutablePlaybackSpeedControlOptionElement) {
            return (ImmutablePlaybackSpeedControlOptionElement) playbackSpeedControlOptionElement;
        }
        return builder().from(playbackSpeedControlOptionElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PlaybackSpeedControlOptionElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_SPEED = 1;
        private static final long INIT_BIT_TEXT = 2;
        private long initBits;

        @Nullable
        private Double speed;

        @Nullable
        private String text;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(PlaybackSpeedControlOptionElement playbackSpeedControlOptionElement) {
            ImmutablePlaybackSpeedControlOptionElement.requireNonNull(playbackSpeedControlOptionElement, "instance");
            speed(playbackSpeedControlOptionElement.speed());
            text(playbackSpeedControlOptionElement.text());
            return this;
        }

        @JsonProperty("speed")
        public final Builder speed(Double d) {
            this.speed = (Double) ImmutablePlaybackSpeedControlOptionElement.requireNonNull(d, "speed");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutablePlaybackSpeedControlOptionElement.requireNonNull(str, "text");
            this.initBits &= -3;
            return this;
        }

        public ImmutablePlaybackSpeedControlOptionElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePlaybackSpeedControlOptionElement(this.speed, this.text);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("speed");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("text");
            }
            return "Cannot build PlaybackSpeedControlOptionElement, some of required attributes are not set " + arrayList;
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
