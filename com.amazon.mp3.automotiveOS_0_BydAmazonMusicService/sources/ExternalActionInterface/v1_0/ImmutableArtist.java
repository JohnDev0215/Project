package ExternalActionInterface.v1_0;

import ExternalActionInterface.v1_0.Artist;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.utils.PageUriUtils;
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
@Generated(from = "Artist", generator = "Immutables")
public final class ImmutableArtist extends Artist {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String asin;
    private volatile transient InitShim initShim;
    private final boolean isExplicit;
    private final boolean isFree;

    @Nullable
    private final Boolean isPrime;

    @Nullable
    private final Boolean isSonicRush;

    @Nullable
    private final Boolean isUnlimited;

    @Nullable
    private final ImmutablePlaymodeEligibility playmodeEligibility;
    private final boolean shuffle;

    @Nullable
    private final Artist.PlaybackSource source;
    private final boolean startStation;

    @Nullable
    private final String title;

    private ImmutableArtist(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.isPrime = builder.isPrime;
        this.isSonicRush = builder.isSonicRush;
        this.isUnlimited = builder.isUnlimited;
        this.asin = builder.asin;
        this.source = builder.source;
        this.playmodeEligibility = builder.playmodeEligibility == null ? null : ImmutablePlaymodeEligibility.copyOf(builder.playmodeEligibility);
        if (builder.isFreeIsSet()) {
            this.initShim.isFree(builder.isFree);
        }
        if (builder.isExplicitIsSet()) {
            this.initShim.isExplicit(builder.isExplicit);
        }
        if (builder.shuffleIsSet()) {
            this.initShim.shuffle(builder.shuffle);
        }
        if (builder.startStationIsSet()) {
            this.initShim.startStation(builder.startStation);
        }
        this.isFree = this.initShim.isFree();
        this.isExplicit = this.initShim.isExplicit();
        this.shuffle = this.initShim.shuffle();
        this.startStation = this.initShim.startStation();
        this.initShim = null;
    }

    private ImmutableArtist(@Nullable String str, boolean z, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, boolean z2, @Nullable String str2, boolean z3, boolean z4, @Nullable Artist.PlaybackSource playbackSource, @Nullable ImmutablePlaymodeEligibility immutablePlaymodeEligibility) {
        this.initShim = new InitShim();
        this.title = str;
        this.isFree = z;
        this.isPrime = bool;
        this.isSonicRush = bool2;
        this.isUnlimited = bool3;
        this.isExplicit = z2;
        this.asin = str2;
        this.shuffle = z3;
        this.startStation = z4;
        this.source = playbackSource;
        this.playmodeEligibility = immutablePlaymodeEligibility;
        this.initShim = null;
    }

    @Generated(from = "Artist", generator = "Immutables")
    private final class InitShim {
        private boolean isExplicit;
        private byte isExplicitBuildStage;
        private boolean isFree;
        private byte isFreeBuildStage;
        private boolean shuffle;
        private byte shuffleBuildStage;
        private boolean startStation;
        private byte startStationBuildStage;

        private InitShim() {
            this.isFreeBuildStage = (byte) 0;
            this.isExplicitBuildStage = (byte) 0;
            this.shuffleBuildStage = (byte) 0;
            this.startStationBuildStage = (byte) 0;
        }

        boolean isFree() {
            byte b = this.isFreeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isFreeBuildStage = (byte) -1;
                this.isFree = ImmutableArtist.super.isFree();
                this.isFreeBuildStage = (byte) 1;
            }
            return this.isFree;
        }

        void isFree(boolean z) {
            this.isFree = z;
            this.isFreeBuildStage = (byte) 1;
        }

        boolean isExplicit() {
            byte b = this.isExplicitBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isExplicitBuildStage = (byte) -1;
                this.isExplicit = ImmutableArtist.super.isExplicit();
                this.isExplicitBuildStage = (byte) 1;
            }
            return this.isExplicit;
        }

        void isExplicit(boolean z) {
            this.isExplicit = z;
            this.isExplicitBuildStage = (byte) 1;
        }

        boolean shuffle() {
            byte b = this.shuffleBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.shuffleBuildStage = (byte) -1;
                this.shuffle = ImmutableArtist.super.shuffle();
                this.shuffleBuildStage = (byte) 1;
            }
            return this.shuffle;
        }

        void shuffle(boolean z) {
            this.shuffle = z;
            this.shuffleBuildStage = (byte) 1;
        }

        boolean startStation() {
            byte b = this.startStationBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.startStationBuildStage = (byte) -1;
                this.startStation = ImmutableArtist.super.startStation();
                this.startStationBuildStage = (byte) 1;
            }
            return this.startStation;
        }

        void startStation(boolean z) {
            this.startStation = z;
            this.startStationBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isFreeBuildStage == -1) {
                arrayList.add("isFree");
            }
            if (this.isExplicitBuildStage == -1) {
                arrayList.add(BrushV2Converter.KEY_IS_EXPLICIT);
            }
            if (this.shuffleBuildStage == -1) {
                arrayList.add("shuffle");
            }
            if (this.startStationBuildStage == -1) {
                arrayList.add("startStation");
            }
            return "Cannot build Artist, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty("title")
    @Nullable
    public String title() {
        return this.title;
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty("isFree")
    public boolean isFree() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isFree();
        }
        return this.isFree;
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty("isPrime")
    @Nullable
    public Boolean isPrime() {
        return this.isPrime;
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty("isSonicRush")
    @Nullable
    public Boolean isSonicRush() {
        return this.isSonicRush;
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty("isUnlimited")
    @Nullable
    public Boolean isUnlimited() {
        return this.isUnlimited;
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty(BrushV2Converter.KEY_IS_EXPLICIT)
    public boolean isExplicit() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isExplicit();
        }
        return this.isExplicit;
    }

    @Override // ExternalActionInterface.v1_0.Artist
    @JsonProperty("asin")
    @Nullable
    public String asin() {
        return this.asin;
    }

    @Override // ExternalActionInterface.v1_0.Artist
    @JsonProperty("shuffle")
    public boolean shuffle() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shuffle();
        }
        return this.shuffle;
    }

    @Override // ExternalActionInterface.v1_0.Artist
    @JsonProperty("startStation")
    public boolean startStation() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.startStation();
        }
        return this.startStation;
    }

    @Override // ExternalActionInterface.v1_0.Artist
    @JsonProperty(PageUriUtils.SOURCE_QUERY_PARAM)
    @Nullable
    public Artist.PlaybackSource source() {
        return this.source;
    }

    @Override // ExternalActionInterface.v1_0.Artist
    @JsonProperty("playmodeEligibility")
    @Nullable
    public ImmutablePlaymodeEligibility playmodeEligibility() {
        return this.playmodeEligibility;
    }

    public final ImmutableArtist withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableArtist(str, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.shuffle, this.startStation, this.source, this.playmodeEligibility);
    }

    public final ImmutableArtist withIsFree(boolean z) {
        return this.isFree == z ? this : new ImmutableArtist(this.title, z, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.shuffle, this.startStation, this.source, this.playmodeEligibility);
    }

    public final ImmutableArtist withIsPrime(@Nullable Boolean bool) {
        return equals(this.isPrime, bool) ? this : new ImmutableArtist(this.title, this.isFree, bool, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.shuffle, this.startStation, this.source, this.playmodeEligibility);
    }

    public final ImmutableArtist withIsSonicRush(@Nullable Boolean bool) {
        return equals(this.isSonicRush, bool) ? this : new ImmutableArtist(this.title, this.isFree, this.isPrime, bool, this.isUnlimited, this.isExplicit, this.asin, this.shuffle, this.startStation, this.source, this.playmodeEligibility);
    }

    public final ImmutableArtist withIsUnlimited(@Nullable Boolean bool) {
        return equals(this.isUnlimited, bool) ? this : new ImmutableArtist(this.title, this.isFree, this.isPrime, this.isSonicRush, bool, this.isExplicit, this.asin, this.shuffle, this.startStation, this.source, this.playmodeEligibility);
    }

    public final ImmutableArtist withIsExplicit(boolean z) {
        return this.isExplicit == z ? this : new ImmutableArtist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, z, this.asin, this.shuffle, this.startStation, this.source, this.playmodeEligibility);
    }

    public final ImmutableArtist withAsin(@Nullable String str) {
        return equals(this.asin, str) ? this : new ImmutableArtist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, str, this.shuffle, this.startStation, this.source, this.playmodeEligibility);
    }

    public final ImmutableArtist withShuffle(boolean z) {
        return this.shuffle == z ? this : new ImmutableArtist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, z, this.startStation, this.source, this.playmodeEligibility);
    }

    public final ImmutableArtist withStartStation(boolean z) {
        return this.startStation == z ? this : new ImmutableArtist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.shuffle, z, this.source, this.playmodeEligibility);
    }

    public final ImmutableArtist withSource(@Nullable Artist.PlaybackSource playbackSource) {
        return this.source == playbackSource ? this : new ImmutableArtist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.shuffle, this.startStation, playbackSource, this.playmodeEligibility);
    }

    public final ImmutableArtist withPlaymodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
        if (this.playmodeEligibility == playmodeEligibility) {
            return this;
        }
        return new ImmutableArtist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.shuffle, this.startStation, this.source, playmodeEligibility == null ? null : ImmutablePlaymodeEligibility.copyOf(playmodeEligibility));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableArtist) && equalTo(0, (ImmutableArtist) obj);
    }

    private boolean equalTo(int i, ImmutableArtist immutableArtist) {
        return equals(this.title, immutableArtist.title) && this.isFree == immutableArtist.isFree && equals(this.isPrime, immutableArtist.isPrime) && equals(this.isSonicRush, immutableArtist.isSonicRush) && equals(this.isUnlimited, immutableArtist.isUnlimited) && this.isExplicit == immutableArtist.isExplicit && equals(this.asin, immutableArtist.asin) && this.shuffle == immutableArtist.shuffle && this.startStation == immutableArtist.startStation && equals(this.source, immutableArtist.source) && equals(this.playmodeEligibility, immutableArtist.playmodeEligibility);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.title) + 5381;
        int i = iHashCode + (iHashCode << 5) + (this.isFree ? 1231 : 1237);
        int iHashCode2 = i + (i << 5) + hashCode(this.isPrime);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.isSonicRush);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.isUnlimited);
        int i2 = iHashCode4 + (iHashCode4 << 5) + (this.isExplicit ? 1231 : 1237);
        int iHashCode5 = i2 + (i2 << 5) + hashCode(this.asin);
        int i3 = iHashCode5 + (iHashCode5 << 5) + (this.shuffle ? 1231 : 1237);
        int i4 = i3 + (i3 << 5) + (this.startStation ? 1231 : 1237);
        int iHashCode6 = i4 + (i4 << 5) + hashCode(this.source);
        return iHashCode6 + (iHashCode6 << 5) + hashCode(this.playmodeEligibility);
    }

    public String toString() {
        return "Artist{title=" + this.title + ", isFree=" + this.isFree + ", isPrime=" + this.isPrime + ", isSonicRush=" + this.isSonicRush + ", isUnlimited=" + this.isUnlimited + ", isExplicit=" + this.isExplicit + ", asin=" + this.asin + ", shuffle=" + this.shuffle + ", startStation=" + this.startStation + ", source=" + this.source + ", playmodeEligibility=" + this.playmodeEligibility + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "Artist", generator = "Immutables")
    static final class Json extends Artist {

        @Nullable
        String asin;
        boolean isExplicit;
        boolean isExplicitIsSet;
        boolean isFree;
        boolean isFreeIsSet;

        @Nullable
        Boolean isPrime;

        @Nullable
        Boolean isSonicRush;

        @Nullable
        Boolean isUnlimited;

        @Nullable
        PlaymodeEligibility playmodeEligibility;
        boolean shuffle;
        boolean shuffleIsSet;

        @Nullable
        Artist.PlaybackSource source;
        boolean startStation;
        boolean startStationIsSet;

        @Nullable
        String title;

        Json() {
        }

        @JsonProperty("title")
        public void setTitle(@Nullable String str) {
            this.title = str;
        }

        @JsonProperty("isFree")
        public void setIsFree(boolean z) {
            this.isFree = z;
            this.isFreeIsSet = true;
        }

        @JsonProperty("isPrime")
        public void setIsPrime(@Nullable Boolean bool) {
            this.isPrime = bool;
        }

        @JsonProperty("isSonicRush")
        public void setIsSonicRush(@Nullable Boolean bool) {
            this.isSonicRush = bool;
        }

        @JsonProperty("isUnlimited")
        public void setIsUnlimited(@Nullable Boolean bool) {
            this.isUnlimited = bool;
        }

        @JsonProperty(BrushV2Converter.KEY_IS_EXPLICIT)
        public void setIsExplicit(boolean z) {
            this.isExplicit = z;
            this.isExplicitIsSet = true;
        }

        @JsonProperty("asin")
        public void setAsin(@Nullable String str) {
            this.asin = str;
        }

        @JsonProperty("shuffle")
        public void setShuffle(boolean z) {
            this.shuffle = z;
            this.shuffleIsSet = true;
        }

        @JsonProperty("startStation")
        public void setStartStation(boolean z) {
            this.startStation = z;
            this.startStationIsSet = true;
        }

        @JsonProperty(PageUriUtils.SOURCE_QUERY_PARAM)
        public void setSource(@Nullable Artist.PlaybackSource playbackSource) {
            this.source = playbackSource;
        }

        @JsonProperty("playmodeEligibility")
        public void setPlaymodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
            this.playmodeEligibility = playmodeEligibility;
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public boolean isFree() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public Boolean isPrime() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public Boolean isSonicRush() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public Boolean isUnlimited() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public boolean isExplicit() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Artist
        public String asin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Artist
        public boolean shuffle() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Artist
        public boolean startStation() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Artist
        public Artist.PlaybackSource source() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Artist
        public PlaymodeEligibility playmodeEligibility() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableArtist fromJson(Json json) {
        Builder builder = builder();
        String str = json.title;
        if (str != null) {
            builder.title(str);
        }
        if (json.isFreeIsSet) {
            builder.isFree(json.isFree);
        }
        Boolean bool = json.isPrime;
        if (bool != null) {
            builder.isPrime(bool);
        }
        Boolean bool2 = json.isSonicRush;
        if (bool2 != null) {
            builder.isSonicRush(bool2);
        }
        Boolean bool3 = json.isUnlimited;
        if (bool3 != null) {
            builder.isUnlimited(bool3);
        }
        if (json.isExplicitIsSet) {
            builder.isExplicit(json.isExplicit);
        }
        String str2 = json.asin;
        if (str2 != null) {
            builder.asin(str2);
        }
        if (json.shuffleIsSet) {
            builder.shuffle(json.shuffle);
        }
        if (json.startStationIsSet) {
            builder.startStation(json.startStation);
        }
        Artist.PlaybackSource playbackSource = json.source;
        if (playbackSource != null) {
            builder.source(playbackSource);
        }
        PlaymodeEligibility playmodeEligibility = json.playmodeEligibility;
        if (playmodeEligibility != null) {
            builder.playmodeEligibility(playmodeEligibility);
        }
        return builder.build();
    }

    public static ImmutableArtist copyOf(Artist artist) {
        if (artist instanceof ImmutableArtist) {
            return (ImmutableArtist) artist;
        }
        return builder().from(artist).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "Artist", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_IS_EXPLICIT = 2;
        private static final long OPT_BIT_IS_FREE = 1;
        private static final long OPT_BIT_SHUFFLE = 4;
        private static final long OPT_BIT_START_STATION = 8;

        @Nullable
        private String asin;
        private boolean isExplicit;
        private boolean isFree;

        @Nullable
        private Boolean isPrime;

        @Nullable
        private Boolean isSonicRush;

        @Nullable
        private Boolean isUnlimited;
        private long optBits;

        @Nullable
        private PlaymodeEligibility playmodeEligibility;
        private boolean shuffle;

        @Nullable
        private Artist.PlaybackSource source;
        private boolean startStation;

        @Nullable
        private String title;

        private Builder() {
        }

        public final Builder from(PlayableContent playableContent) {
            ImmutableArtist.requireNonNull(playableContent, "instance");
            from((Object) playableContent);
            return this;
        }

        public final Builder from(Artist artist) {
            ImmutableArtist.requireNonNull(artist, "instance");
            from((Object) artist);
            return this;
        }

        private void from(Object obj) {
            if (obj instanceof PlayableContent) {
                PlayableContent playableContent = (PlayableContent) obj;
                Boolean boolIsSonicRush = playableContent.isSonicRush();
                if (boolIsSonicRush != null) {
                    isSonicRush(boolIsSonicRush);
                }
                isExplicit(playableContent.isExplicit());
                Boolean boolIsUnlimited = playableContent.isUnlimited();
                if (boolIsUnlimited != null) {
                    isUnlimited(boolIsUnlimited);
                }
                isFree(playableContent.isFree());
                String strTitle = playableContent.title();
                if (strTitle != null) {
                    title(strTitle);
                }
                Boolean boolIsPrime = playableContent.isPrime();
                if (boolIsPrime != null) {
                    isPrime(boolIsPrime);
                }
            }
            if (obj instanceof Artist) {
                Artist artist = (Artist) obj;
                PlaymodeEligibility playmodeEligibility = artist.playmodeEligibility();
                if (playmodeEligibility != null) {
                    playmodeEligibility(playmodeEligibility);
                }
                String strAsin = artist.asin();
                if (strAsin != null) {
                    asin(strAsin);
                }
                startStation(artist.startStation());
                Artist.PlaybackSource playbackSourceSource = artist.source();
                if (playbackSourceSource != null) {
                    source(playbackSourceSource);
                }
                shuffle(artist.shuffle());
            }
        }

        @JsonProperty("title")
        public final Builder title(@Nullable String str) {
            this.title = str;
            return this;
        }

        @JsonProperty("isFree")
        public final Builder isFree(boolean z) {
            this.isFree = z;
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("isPrime")
        public final Builder isPrime(@Nullable Boolean bool) {
            this.isPrime = bool;
            return this;
        }

        @JsonProperty("isSonicRush")
        public final Builder isSonicRush(@Nullable Boolean bool) {
            this.isSonicRush = bool;
            return this;
        }

        @JsonProperty("isUnlimited")
        public final Builder isUnlimited(@Nullable Boolean bool) {
            this.isUnlimited = bool;
            return this;
        }

        @JsonProperty(BrushV2Converter.KEY_IS_EXPLICIT)
        public final Builder isExplicit(boolean z) {
            this.isExplicit = z;
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("asin")
        public final Builder asin(@Nullable String str) {
            this.asin = str;
            return this;
        }

        @JsonProperty("shuffle")
        public final Builder shuffle(boolean z) {
            this.shuffle = z;
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("startStation")
        public final Builder startStation(boolean z) {
            this.startStation = z;
            this.optBits |= 8;
            return this;
        }

        @JsonProperty(PageUriUtils.SOURCE_QUERY_PARAM)
        public final Builder source(@Nullable Artist.PlaybackSource playbackSource) {
            this.source = playbackSource;
            return this;
        }

        @JsonProperty("playmodeEligibility")
        public final Builder playmodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
            this.playmodeEligibility = playmodeEligibility == null ? null : ImmutablePlaymodeEligibility.copyOf(playmodeEligibility);
            return this;
        }

        public ImmutableArtist build() {
            return new ImmutableArtist(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isFreeIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isExplicitIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shuffleIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean startStationIsSet() {
            return (this.optBits & 8) != 0;
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
