package PlaybackInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.android.gms.cast.MediaTrack;
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
@Generated(from = "MediaOptionsElement", generator = "Immutables")
public final class ImmutableMediaOptionsElement extends MediaOptionsElement {

    @Nullable
    private final MediaOptionElement album;
    private final String altText;

    @Nullable
    private final MediaOptionElement artist;
    private final String description;

    @Nullable
    private final MediaOptionElement station;

    private ImmutableMediaOptionsElement(String str, String str2, @Nullable MediaOptionElement mediaOptionElement, @Nullable MediaOptionElement mediaOptionElement2, @Nullable MediaOptionElement mediaOptionElement3) {
        this.description = str;
        this.altText = str2;
        this.artist = mediaOptionElement;
        this.album = mediaOptionElement2;
        this.station = mediaOptionElement3;
    }

    @Override // PlaybackInterface.v1_0.MediaOptionsElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    public String description() {
        return this.description;
    }

    @Override // PlaybackInterface.v1_0.MediaOptionsElement
    @JsonProperty("altText")
    public String altText() {
        return this.altText;
    }

    @Override // PlaybackInterface.v1_0.MediaOptionsElement
    @JsonProperty("artist")
    @Nullable
    public MediaOptionElement artist() {
        return this.artist;
    }

    @Override // PlaybackInterface.v1_0.MediaOptionsElement
    @JsonProperty("album")
    @Nullable
    public MediaOptionElement album() {
        return this.album;
    }

    @Override // PlaybackInterface.v1_0.MediaOptionsElement
    @JsonProperty("station")
    @Nullable
    public MediaOptionElement station() {
        return this.station;
    }

    public final ImmutableMediaOptionsElement withDescription(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
        return this.description.equals(str2) ? this : new ImmutableMediaOptionsElement(str2, this.altText, this.artist, this.album, this.station);
    }

    public final ImmutableMediaOptionsElement withAltText(String str) {
        String str2 = (String) requireNonNull(str, "altText");
        return this.altText.equals(str2) ? this : new ImmutableMediaOptionsElement(this.description, str2, this.artist, this.album, this.station);
    }

    public final ImmutableMediaOptionsElement withArtist(@Nullable MediaOptionElement mediaOptionElement) {
        return this.artist == mediaOptionElement ? this : new ImmutableMediaOptionsElement(this.description, this.altText, mediaOptionElement, this.album, this.station);
    }

    public final ImmutableMediaOptionsElement withAlbum(@Nullable MediaOptionElement mediaOptionElement) {
        return this.album == mediaOptionElement ? this : new ImmutableMediaOptionsElement(this.description, this.altText, this.artist, mediaOptionElement, this.station);
    }

    public final ImmutableMediaOptionsElement withStation(@Nullable MediaOptionElement mediaOptionElement) {
        return this.station == mediaOptionElement ? this : new ImmutableMediaOptionsElement(this.description, this.altText, this.artist, this.album, mediaOptionElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMediaOptionsElement) && equalTo(0, (ImmutableMediaOptionsElement) obj);
    }

    private boolean equalTo(int i, ImmutableMediaOptionsElement immutableMediaOptionsElement) {
        return this.description.equals(immutableMediaOptionsElement.description) && this.altText.equals(immutableMediaOptionsElement.altText) && equals(this.artist, immutableMediaOptionsElement.artist) && equals(this.album, immutableMediaOptionsElement.album) && equals(this.station, immutableMediaOptionsElement.station);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.description.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altText.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.artist);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.album);
        return iHashCode4 + (iHashCode4 << 5) + hashCode(this.station);
    }

    public String toString() {
        return "MediaOptionsElement{description=" + this.description + ", altText=" + this.altText + ", artist=" + this.artist + ", album=" + this.album + ", station=" + this.station + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MediaOptionsElement", generator = "Immutables")
    static final class Json extends MediaOptionsElement {

        @Nullable
        MediaOptionElement album;

        @Nullable
        String altText;

        @Nullable
        MediaOptionElement artist;

        @Nullable
        String description;

        @Nullable
        MediaOptionElement station;

        Json() {
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public void setDescription(String str) {
            this.description = str;
        }

        @JsonProperty("altText")
        public void setAltText(String str) {
            this.altText = str;
        }

        @JsonProperty("artist")
        public void setArtist(@Nullable MediaOptionElement mediaOptionElement) {
            this.artist = mediaOptionElement;
        }

        @JsonProperty("album")
        public void setAlbum(@Nullable MediaOptionElement mediaOptionElement) {
            this.album = mediaOptionElement;
        }

        @JsonProperty("station")
        public void setStation(@Nullable MediaOptionElement mediaOptionElement) {
            this.station = mediaOptionElement;
        }

        @Override // PlaybackInterface.v1_0.MediaOptionsElement
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaOptionsElement
        public String altText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaOptionsElement
        public MediaOptionElement artist() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaOptionsElement
        public MediaOptionElement album() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaOptionsElement
        public MediaOptionElement station() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMediaOptionsElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.description;
        if (str != null) {
            builder.description(str);
        }
        String str2 = json.altText;
        if (str2 != null) {
            builder.altText(str2);
        }
        MediaOptionElement mediaOptionElement = json.artist;
        if (mediaOptionElement != null) {
            builder.artist(mediaOptionElement);
        }
        MediaOptionElement mediaOptionElement2 = json.album;
        if (mediaOptionElement2 != null) {
            builder.album(mediaOptionElement2);
        }
        MediaOptionElement mediaOptionElement3 = json.station;
        if (mediaOptionElement3 != null) {
            builder.station(mediaOptionElement3);
        }
        return builder.build();
    }

    public static ImmutableMediaOptionsElement copyOf(MediaOptionsElement mediaOptionsElement) {
        if (mediaOptionsElement instanceof ImmutableMediaOptionsElement) {
            return (ImmutableMediaOptionsElement) mediaOptionsElement;
        }
        return builder().from(mediaOptionsElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MediaOptionsElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ALT_TEXT = 2;
        private static final long INIT_BIT_DESCRIPTION = 1;

        @Nullable
        private MediaOptionElement album;

        @Nullable
        private String altText;

        @Nullable
        private MediaOptionElement artist;

        @Nullable
        private String description;
        private long initBits;

        @Nullable
        private MediaOptionElement station;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(MediaOptionsElement mediaOptionsElement) {
            ImmutableMediaOptionsElement.requireNonNull(mediaOptionsElement, "instance");
            description(mediaOptionsElement.description());
            altText(mediaOptionsElement.altText());
            MediaOptionElement mediaOptionElementArtist = mediaOptionsElement.artist();
            if (mediaOptionElementArtist != null) {
                artist(mediaOptionElementArtist);
            }
            MediaOptionElement mediaOptionElementAlbum = mediaOptionsElement.album();
            if (mediaOptionElementAlbum != null) {
                album(mediaOptionElementAlbum);
            }
            MediaOptionElement mediaOptionElementStation = mediaOptionsElement.station();
            if (mediaOptionElementStation != null) {
                station(mediaOptionElementStation);
            }
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(String str) {
            this.description = (String) ImmutableMediaOptionsElement.requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("altText")
        public final Builder altText(String str) {
            this.altText = (String) ImmutableMediaOptionsElement.requireNonNull(str, "altText");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("artist")
        public final Builder artist(@Nullable MediaOptionElement mediaOptionElement) {
            this.artist = mediaOptionElement;
            return this;
        }

        @JsonProperty("album")
        public final Builder album(@Nullable MediaOptionElement mediaOptionElement) {
            this.album = mediaOptionElement;
            return this;
        }

        @JsonProperty("station")
        public final Builder station(@Nullable MediaOptionElement mediaOptionElement) {
            this.station = mediaOptionElement;
            return this;
        }

        public ImmutableMediaOptionsElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableMediaOptionsElement(this.description, this.altText, this.artist, this.album, this.station);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(MediaTrack.ROLE_DESCRIPTION);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("altText");
            }
            return "Cannot build MediaOptionsElement, some of required attributes are not set " + arrayList;
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
