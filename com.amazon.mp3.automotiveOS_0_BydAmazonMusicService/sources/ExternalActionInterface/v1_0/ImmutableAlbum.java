package ExternalActionInterface.v1_0;

import ExternalActionInterface.v1_0.Album;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "Album", generator = "Immutables")
public final class ImmutableAlbum extends Album {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String albumArtworkUrl;

    @Nullable
    private final List<Track> albumTracks;

    @Nullable
    private final String artistAsin;

    @Nullable
    private final String artistName;

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
    private final Date originalReleaseDate;

    @Nullable
    private final PlaymodeEligibility playmodeEligibility;

    @Nullable
    private final Album.PlaybackSource source;

    @Nullable
    private final String title;

    private ImmutableAlbum(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.isPrime = builder.isPrime;
        this.isSonicRush = builder.isSonicRush;
        this.isUnlimited = builder.isUnlimited;
        this.asin = builder.asin;
        this.albumArtworkUrl = builder.albumArtworkUrl;
        this.artistName = builder.artistName;
        this.artistAsin = builder.artistAsin;
        this.albumTracks = builder.albumTracks == null ? null : createUnmodifiableList(true, builder.albumTracks);
        this.originalReleaseDate = builder.originalReleaseDate;
        this.playmodeEligibility = builder.playmodeEligibility;
        this.source = builder.source;
        if (builder.isFreeIsSet()) {
            this.initShim.isFree(builder.isFree);
        }
        if (builder.isExplicitIsSet()) {
            this.initShim.isExplicit(builder.isExplicit);
        }
        this.isFree = this.initShim.isFree();
        this.isExplicit = this.initShim.isExplicit();
        this.initShim = null;
    }

    private ImmutableAlbum(@Nullable String str, boolean z, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, boolean z2, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable List<Track> list, @Nullable Date date, @Nullable PlaymodeEligibility playmodeEligibility, @Nullable Album.PlaybackSource playbackSource) {
        this.initShim = new InitShim();
        this.title = str;
        this.isFree = z;
        this.isPrime = bool;
        this.isSonicRush = bool2;
        this.isUnlimited = bool3;
        this.isExplicit = z2;
        this.asin = str2;
        this.albumArtworkUrl = str3;
        this.artistName = str4;
        this.artistAsin = str5;
        this.albumTracks = list;
        this.originalReleaseDate = date;
        this.playmodeEligibility = playmodeEligibility;
        this.source = playbackSource;
        this.initShim = null;
    }

    @Generated(from = "Album", generator = "Immutables")
    private final class InitShim {
        private boolean isExplicit;
        private byte isExplicitBuildStage;
        private boolean isFree;
        private byte isFreeBuildStage;

        private InitShim() {
            this.isFreeBuildStage = (byte) 0;
            this.isExplicitBuildStage = (byte) 0;
        }

        boolean isFree() {
            byte b = this.isFreeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isFreeBuildStage = (byte) -1;
                this.isFree = ImmutableAlbum.super.isFree();
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
                this.isExplicit = ImmutableAlbum.super.isExplicit();
                this.isExplicitBuildStage = (byte) 1;
            }
            return this.isExplicit;
        }

        void isExplicit(boolean z) {
            this.isExplicit = z;
            this.isExplicitBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isFreeBuildStage == -1) {
                arrayList.add("isFree");
            }
            if (this.isExplicitBuildStage == -1) {
                arrayList.add(BrushV2Converter.KEY_IS_EXPLICIT);
            }
            return "Cannot build Album, attribute initializers form cycle " + arrayList;
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

    @Override // ExternalActionInterface.v1_0.Album
    @JsonProperty("asin")
    @Nullable
    public String asin() {
        return this.asin;
    }

    @Override // ExternalActionInterface.v1_0.Album
    @JsonProperty("albumArtworkUrl")
    @Nullable
    public String albumArtworkUrl() {
        return this.albumArtworkUrl;
    }

    @Override // ExternalActionInterface.v1_0.Album
    @JsonProperty("artistName")
    @Nullable
    public String artistName() {
        return this.artistName;
    }

    @Override // ExternalActionInterface.v1_0.Album
    @JsonProperty("artistAsin")
    @Nullable
    public String artistAsin() {
        return this.artistAsin;
    }

    @Override // ExternalActionInterface.v1_0.Album
    @JsonProperty("albumTracks")
    @Nullable
    public List<Track> albumTracks() {
        return this.albumTracks;
    }

    @Override // ExternalActionInterface.v1_0.Album
    @JsonProperty("originalReleaseDate")
    @Nullable
    public Date originalReleaseDate() {
        return this.originalReleaseDate;
    }

    @Override // ExternalActionInterface.v1_0.Album
    @JsonProperty("playmodeEligibility")
    @Nullable
    public PlaymodeEligibility playmodeEligibility() {
        return this.playmodeEligibility;
    }

    @Override // ExternalActionInterface.v1_0.Album
    @JsonProperty(PageUriUtils.SOURCE_QUERY_PARAM)
    @Nullable
    public Album.PlaybackSource source() {
        return this.source;
    }

    public final ImmutableAlbum withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableAlbum(str, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.albumTracks, this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withIsFree(boolean z) {
        return this.isFree == z ? this : new ImmutableAlbum(this.title, z, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.albumTracks, this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withIsPrime(@Nullable Boolean bool) {
        return equals(this.isPrime, bool) ? this : new ImmutableAlbum(this.title, this.isFree, bool, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.albumTracks, this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withIsSonicRush(@Nullable Boolean bool) {
        return equals(this.isSonicRush, bool) ? this : new ImmutableAlbum(this.title, this.isFree, this.isPrime, bool, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.albumTracks, this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withIsUnlimited(@Nullable Boolean bool) {
        return equals(this.isUnlimited, bool) ? this : new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, bool, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.albumTracks, this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withIsExplicit(boolean z) {
        return this.isExplicit == z ? this : new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, z, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.albumTracks, this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withAsin(@Nullable String str) {
        return equals(this.asin, str) ? this : new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, str, this.albumArtworkUrl, this.artistName, this.artistAsin, this.albumTracks, this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withAlbumArtworkUrl(@Nullable String str) {
        return equals(this.albumArtworkUrl, str) ? this : new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, str, this.artistName, this.artistAsin, this.albumTracks, this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withArtistName(@Nullable String str) {
        return equals(this.artistName, str) ? this : new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, str, this.artistAsin, this.albumTracks, this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withArtistAsin(@Nullable String str) {
        return equals(this.artistAsin, str) ? this : new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, str, this.albumTracks, this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withAlbumTracks(@Nullable Track... trackArr) {
        if (trackArr == null) {
            return new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, null, this.originalReleaseDate, this.playmodeEligibility, this.source);
        }
        return new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, Arrays.asList(trackArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(trackArr), true, false)), this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withAlbumTracks(@Nullable Iterable<? extends Track> iterable) {
        if (this.albumTracks == iterable) {
            return this;
        }
        return new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.originalReleaseDate, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withOriginalReleaseDate(@Nullable Date date) {
        return this.originalReleaseDate == date ? this : new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.albumTracks, date, this.playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withPlaymodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
        return this.playmodeEligibility == playmodeEligibility ? this : new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.albumTracks, this.originalReleaseDate, playmodeEligibility, this.source);
    }

    public final ImmutableAlbum withSource(@Nullable Album.PlaybackSource playbackSource) {
        return this.source == playbackSource ? this : new ImmutableAlbum(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.albumTracks, this.originalReleaseDate, this.playmodeEligibility, playbackSource);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableAlbum) && equalTo(0, (ImmutableAlbum) obj);
    }

    private boolean equalTo(int i, ImmutableAlbum immutableAlbum) {
        return equals(this.title, immutableAlbum.title) && this.isFree == immutableAlbum.isFree && equals(this.isPrime, immutableAlbum.isPrime) && equals(this.isSonicRush, immutableAlbum.isSonicRush) && equals(this.isUnlimited, immutableAlbum.isUnlimited) && this.isExplicit == immutableAlbum.isExplicit && equals(this.asin, immutableAlbum.asin) && equals(this.albumArtworkUrl, immutableAlbum.albumArtworkUrl) && equals(this.artistName, immutableAlbum.artistName) && equals(this.artistAsin, immutableAlbum.artistAsin) && equals(this.albumTracks, immutableAlbum.albumTracks) && equals(this.originalReleaseDate, immutableAlbum.originalReleaseDate) && equals(this.playmodeEligibility, immutableAlbum.playmodeEligibility) && equals(this.source, immutableAlbum.source);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.title) + 5381;
        int i = iHashCode + (iHashCode << 5) + (this.isFree ? 1231 : 1237);
        int iHashCode2 = i + (i << 5) + hashCode(this.isPrime);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.isSonicRush);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.isUnlimited);
        int i2 = iHashCode4 + (iHashCode4 << 5) + (this.isExplicit ? 1231 : 1237);
        int iHashCode5 = i2 + (i2 << 5) + hashCode(this.asin);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.albumArtworkUrl);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.artistName);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.artistAsin);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.albumTracks);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.originalReleaseDate);
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + hashCode(this.playmodeEligibility);
        return iHashCode11 + (iHashCode11 << 5) + hashCode(this.source);
    }

    public String toString() {
        return "Album{title=" + this.title + ", isFree=" + this.isFree + ", isPrime=" + this.isPrime + ", isSonicRush=" + this.isSonicRush + ", isUnlimited=" + this.isUnlimited + ", isExplicit=" + this.isExplicit + ", asin=" + this.asin + ", albumArtworkUrl=" + this.albumArtworkUrl + ", artistName=" + this.artistName + ", artistAsin=" + this.artistAsin + ", albumTracks=" + this.albumTracks + ", originalReleaseDate=" + this.originalReleaseDate + ", playmodeEligibility=" + this.playmodeEligibility + ", source=" + this.source + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "Album", generator = "Immutables")
    static final class Json extends Album {

        @Nullable
        String albumArtworkUrl;

        @Nullable
        List<Track> albumTracks = null;

        @Nullable
        String artistAsin;

        @Nullable
        String artistName;

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
        Date originalReleaseDate;

        @Nullable
        PlaymodeEligibility playmodeEligibility;

        @Nullable
        Album.PlaybackSource source;

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

        @JsonProperty("albumArtworkUrl")
        public void setAlbumArtworkUrl(@Nullable String str) {
            this.albumArtworkUrl = str;
        }

        @JsonProperty("artistName")
        public void setArtistName(@Nullable String str) {
            this.artistName = str;
        }

        @JsonProperty("artistAsin")
        public void setArtistAsin(@Nullable String str) {
            this.artistAsin = str;
        }

        @JsonProperty("albumTracks")
        public void setAlbumTracks(@Nullable List<Track> list) {
            this.albumTracks = list;
        }

        @JsonProperty("originalReleaseDate")
        public void setOriginalReleaseDate(@Nullable Date date) {
            this.originalReleaseDate = date;
        }

        @JsonProperty("playmodeEligibility")
        public void setPlaymodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
            this.playmodeEligibility = playmodeEligibility;
        }

        @JsonProperty(PageUriUtils.SOURCE_QUERY_PARAM)
        public void setSource(@Nullable Album.PlaybackSource playbackSource) {
            this.source = playbackSource;
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

        @Override // ExternalActionInterface.v1_0.Album
        public String asin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Album
        public String albumArtworkUrl() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Album
        public String artistName() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Album
        public String artistAsin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Album
        public List<Track> albumTracks() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Album
        public Date originalReleaseDate() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Album
        public PlaymodeEligibility playmodeEligibility() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Album
        public Album.PlaybackSource source() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableAlbum fromJson(Json json) {
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
        String str3 = json.albumArtworkUrl;
        if (str3 != null) {
            builder.albumArtworkUrl(str3);
        }
        String str4 = json.artistName;
        if (str4 != null) {
            builder.artistName(str4);
        }
        String str5 = json.artistAsin;
        if (str5 != null) {
            builder.artistAsin(str5);
        }
        List<Track> list = json.albumTracks;
        if (list != null) {
            builder.addAllAlbumTracks(list);
        }
        Date date = json.originalReleaseDate;
        if (date != null) {
            builder.originalReleaseDate(date);
        }
        PlaymodeEligibility playmodeEligibility = json.playmodeEligibility;
        if (playmodeEligibility != null) {
            builder.playmodeEligibility(playmodeEligibility);
        }
        Album.PlaybackSource playbackSource = json.source;
        if (playbackSource != null) {
            builder.source(playbackSource);
        }
        return builder.build();
    }

    public static ImmutableAlbum copyOf(Album album) {
        if (album instanceof ImmutableAlbum) {
            return (ImmutableAlbum) album;
        }
        return builder().from(album).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "Album", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_IS_EXPLICIT = 2;
        private static final long OPT_BIT_IS_FREE = 1;

        @Nullable
        private String albumArtworkUrl;
        private List<Track> albumTracks;

        @Nullable
        private String artistAsin;

        @Nullable
        private String artistName;

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
        private Date originalReleaseDate;

        @Nullable
        private PlaymodeEligibility playmodeEligibility;

        @Nullable
        private Album.PlaybackSource source;

        @Nullable
        private String title;

        private Builder() {
            this.albumTracks = null;
        }

        public final Builder from(PlayableContent playableContent) {
            ImmutableAlbum.requireNonNull(playableContent, "instance");
            from((Object) playableContent);
            return this;
        }

        public final Builder from(Album album) {
            ImmutableAlbum.requireNonNull(album, "instance");
            from((Object) album);
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
                String strTitle = playableContent.title();
                if (strTitle != null) {
                    title(strTitle);
                }
                isFree(playableContent.isFree());
                Boolean boolIsPrime = playableContent.isPrime();
                if (boolIsPrime != null) {
                    isPrime(boolIsPrime);
                }
            }
            if (obj instanceof Album) {
                Album album = (Album) obj;
                List<Track> listAlbumTracks = album.albumTracks();
                if (listAlbumTracks != null) {
                    addAllAlbumTracks(listAlbumTracks);
                }
                String strAlbumArtworkUrl = album.albumArtworkUrl();
                if (strAlbumArtworkUrl != null) {
                    albumArtworkUrl(strAlbumArtworkUrl);
                }
                PlaymodeEligibility playmodeEligibility = album.playmodeEligibility();
                if (playmodeEligibility != null) {
                    playmodeEligibility(playmodeEligibility);
                }
                String strAsin = album.asin();
                if (strAsin != null) {
                    asin(strAsin);
                }
                String strArtistName = album.artistName();
                if (strArtistName != null) {
                    artistName(strArtistName);
                }
                Date dateOriginalReleaseDate = album.originalReleaseDate();
                if (dateOriginalReleaseDate != null) {
                    originalReleaseDate(dateOriginalReleaseDate);
                }
                Album.PlaybackSource playbackSourceSource = album.source();
                if (playbackSourceSource != null) {
                    source(playbackSourceSource);
                }
                String strArtistAsin = album.artistAsin();
                if (strArtistAsin != null) {
                    artistAsin(strArtistAsin);
                }
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

        @JsonProperty("albumArtworkUrl")
        public final Builder albumArtworkUrl(@Nullable String str) {
            this.albumArtworkUrl = str;
            return this;
        }

        @JsonProperty("artistName")
        public final Builder artistName(@Nullable String str) {
            this.artistName = str;
            return this;
        }

        @JsonProperty("artistAsin")
        public final Builder artistAsin(@Nullable String str) {
            this.artistAsin = str;
            return this;
        }

        public final Builder addAlbumTracks(Track track) {
            if (this.albumTracks == null) {
                this.albumTracks = new ArrayList();
            }
            this.albumTracks.add((Track) ImmutableAlbum.requireNonNull(track, "albumTracks element"));
            return this;
        }

        public final Builder addAlbumTracks(Track... trackArr) {
            if (this.albumTracks == null) {
                this.albumTracks = new ArrayList();
            }
            for (Track track : trackArr) {
                this.albumTracks.add((Track) ImmutableAlbum.requireNonNull(track, "albumTracks element"));
            }
            return this;
        }

        @JsonProperty("albumTracks")
        public final Builder albumTracks(@Nullable Iterable<? extends Track> iterable) {
            if (iterable == null) {
                this.albumTracks = null;
                return this;
            }
            this.albumTracks = new ArrayList();
            return addAllAlbumTracks(iterable);
        }

        public final Builder addAllAlbumTracks(Iterable<? extends Track> iterable) {
            ImmutableAlbum.requireNonNull(iterable, "albumTracks element");
            if (this.albumTracks == null) {
                this.albumTracks = new ArrayList();
            }
            Iterator<? extends Track> it = iterable.iterator();
            while (it.hasNext()) {
                this.albumTracks.add((Track) ImmutableAlbum.requireNonNull(it.next(), "albumTracks element"));
            }
            return this;
        }

        @JsonProperty("originalReleaseDate")
        public final Builder originalReleaseDate(@Nullable Date date) {
            this.originalReleaseDate = date;
            return this;
        }

        @JsonProperty("playmodeEligibility")
        public final Builder playmodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
            this.playmodeEligibility = playmodeEligibility;
            return this;
        }

        @JsonProperty(PageUriUtils.SOURCE_QUERY_PARAM)
        public final Builder source(@Nullable Album.PlaybackSource playbackSource) {
            this.source = playbackSource;
            return this;
        }

        public ImmutableAlbum build() {
            return new ImmutableAlbum(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isFreeIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isExplicitIsSet() {
            return (this.optBits & 2) != 0;
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

    private static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
        if ((iterable instanceof Collection) && ((Collection) iterable).size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!z2 || t != null) {
                if (z) {
                    requireNonNull(t, "element");
                }
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    private static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
        int size = list.size();
        if (size == 0) {
            return Collections.emptyList();
        }
        if (size == 1) {
            return Collections.singletonList(list.get(0));
        }
        if (z) {
            return Collections.unmodifiableList(new ArrayList(list));
        }
        if (list instanceof ArrayList) {
            ((ArrayList) list).trimToSize();
        }
        return Collections.unmodifiableList(list);
    }
}
