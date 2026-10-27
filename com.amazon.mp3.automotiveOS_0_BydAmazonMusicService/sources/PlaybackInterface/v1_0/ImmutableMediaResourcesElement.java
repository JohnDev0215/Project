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
@Generated(from = "MediaResourcesElement", generator = "Immutables")
public final class ImmutableMediaResourcesElement extends MediaResourcesElement {

    @Nullable
    private final String miniPlayerDescription;

    @Nullable
    private final String nowPlayingLabel;

    private ImmutableMediaResourcesElement(@Nullable String str, @Nullable String str2) {
        this.nowPlayingLabel = str;
        this.miniPlayerDescription = str2;
    }

    @Override // PlaybackInterface.v1_0.MediaResourcesElement
    @JsonProperty("nowPlayingLabel")
    @Nullable
    public String nowPlayingLabel() {
        return this.nowPlayingLabel;
    }

    @Override // PlaybackInterface.v1_0.MediaResourcesElement
    @JsonProperty("miniPlayerDescription")
    @Nullable
    public String miniPlayerDescription() {
        return this.miniPlayerDescription;
    }

    public final ImmutableMediaResourcesElement withNowPlayingLabel(@Nullable String str) {
        return equals(this.nowPlayingLabel, str) ? this : new ImmutableMediaResourcesElement(str, this.miniPlayerDescription);
    }

    public final ImmutableMediaResourcesElement withMiniPlayerDescription(@Nullable String str) {
        return equals(this.miniPlayerDescription, str) ? this : new ImmutableMediaResourcesElement(this.nowPlayingLabel, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMediaResourcesElement) && equalTo(0, (ImmutableMediaResourcesElement) obj);
    }

    private boolean equalTo(int i, ImmutableMediaResourcesElement immutableMediaResourcesElement) {
        return equals(this.nowPlayingLabel, immutableMediaResourcesElement.nowPlayingLabel) && equals(this.miniPlayerDescription, immutableMediaResourcesElement.miniPlayerDescription);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.nowPlayingLabel) + 5381;
        return iHashCode + (iHashCode << 5) + hashCode(this.miniPlayerDescription);
    }

    public String toString() {
        return "MediaResourcesElement{nowPlayingLabel=" + this.nowPlayingLabel + ", miniPlayerDescription=" + this.miniPlayerDescription + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MediaResourcesElement", generator = "Immutables")
    static final class Json extends MediaResourcesElement {

        @Nullable
        String miniPlayerDescription;

        @Nullable
        String nowPlayingLabel;

        Json() {
        }

        @JsonProperty("nowPlayingLabel")
        public void setNowPlayingLabel(@Nullable String str) {
            this.nowPlayingLabel = str;
        }

        @JsonProperty("miniPlayerDescription")
        public void setMiniPlayerDescription(@Nullable String str) {
            this.miniPlayerDescription = str;
        }

        @Override // PlaybackInterface.v1_0.MediaResourcesElement
        public String nowPlayingLabel() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaResourcesElement
        public String miniPlayerDescription() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMediaResourcesElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.nowPlayingLabel;
        if (str != null) {
            builder.nowPlayingLabel(str);
        }
        String str2 = json.miniPlayerDescription;
        if (str2 != null) {
            builder.miniPlayerDescription(str2);
        }
        return builder.build();
    }

    public static ImmutableMediaResourcesElement copyOf(MediaResourcesElement mediaResourcesElement) {
        if (mediaResourcesElement instanceof ImmutableMediaResourcesElement) {
            return (ImmutableMediaResourcesElement) mediaResourcesElement;
        }
        return builder().from(mediaResourcesElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MediaResourcesElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {

        @Nullable
        private String miniPlayerDescription;

        @Nullable
        private String nowPlayingLabel;

        private Builder() {
        }

        public final Builder from(MediaResourcesElement mediaResourcesElement) {
            ImmutableMediaResourcesElement.requireNonNull(mediaResourcesElement, "instance");
            String strNowPlayingLabel = mediaResourcesElement.nowPlayingLabel();
            if (strNowPlayingLabel != null) {
                nowPlayingLabel(strNowPlayingLabel);
            }
            String strMiniPlayerDescription = mediaResourcesElement.miniPlayerDescription();
            if (strMiniPlayerDescription != null) {
                miniPlayerDescription(strMiniPlayerDescription);
            }
            return this;
        }

        @JsonProperty("nowPlayingLabel")
        public final Builder nowPlayingLabel(@Nullable String str) {
            this.nowPlayingLabel = str;
            return this;
        }

        @JsonProperty("miniPlayerDescription")
        public final Builder miniPlayerDescription(@Nullable String str) {
            this.miniPlayerDescription = str;
            return this;
        }

        public ImmutableMediaResourcesElement build() {
            return new ImmutableMediaResourcesElement(this.nowPlayingLabel, this.miniPlayerDescription);
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
