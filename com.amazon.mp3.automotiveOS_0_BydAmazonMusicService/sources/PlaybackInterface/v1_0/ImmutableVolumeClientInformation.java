package PlaybackInterface.v1_0;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
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
@Generated(from = "VolumeClientInformation", generator = "Immutables")
public final class ImmutableVolumeClientInformation extends VolumeClientInformation {
    private final Double volume;

    private ImmutableVolumeClientInformation(Double d) {
        this.volume = d;
    }

    @Override // PlaybackInterface.v1_0.VolumeClientInformation
    @JsonProperty(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME)
    public Double volume() {
        return this.volume;
    }

    public final ImmutableVolumeClientInformation withVolume(Double d) {
        Double d2 = (Double) requireNonNull(d, MediaRouteProviderProtocol.CLIENT_DATA_VOLUME);
        return this.volume.equals(d2) ? this : new ImmutableVolumeClientInformation(d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVolumeClientInformation) && equalTo(0, (ImmutableVolumeClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableVolumeClientInformation immutableVolumeClientInformation) {
        return this.volume.equals(immutableVolumeClientInformation.volume);
    }

    public int hashCode() {
        return 172192 + this.volume.hashCode() + 5381;
    }

    public String toString() {
        return "VolumeClientInformation{volume=" + this.volume + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VolumeClientInformation", generator = "Immutables")
    static final class Json extends VolumeClientInformation {

        @Nullable
        Double volume;

        Json() {
        }

        @JsonProperty(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME)
        public void setVolume(Double d) {
            this.volume = d;
        }

        @Override // PlaybackInterface.v1_0.VolumeClientInformation
        public Double volume() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVolumeClientInformation fromJson(Json json) {
        Builder builder = builder();
        Double d = json.volume;
        if (d != null) {
            builder.volume(d);
        }
        return builder.build();
    }

    public static ImmutableVolumeClientInformation copyOf(VolumeClientInformation volumeClientInformation) {
        if (volumeClientInformation instanceof ImmutableVolumeClientInformation) {
            return (ImmutableVolumeClientInformation) volumeClientInformation;
        }
        return builder().from(volumeClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VolumeClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_VOLUME = 1;
        private long initBits;

        @Nullable
        private Double volume;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(VolumeClientInformation volumeClientInformation) {
            ImmutableVolumeClientInformation.requireNonNull(volumeClientInformation, "instance");
            volume(volumeClientInformation.volume());
            return this;
        }

        @JsonProperty(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME)
        public final Builder volume(Double d) {
            this.volume = (Double) ImmutableVolumeClientInformation.requireNonNull(d, MediaRouteProviderProtocol.CLIENT_DATA_VOLUME);
            this.initBits &= -2;
            return this;
        }

        public ImmutableVolumeClientInformation build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableVolumeClientInformation(this.volume);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME);
            }
            return "Cannot build VolumeClientInformation, some of required attributes are not set " + arrayList;
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
