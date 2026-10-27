package ExternalActionInterface.v1_0;

import com.amazon.music.find.converter.BrushV2Converter;
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
@Generated(from = "Playlist", generator = "Immutables")
public final class ImmutablePlaylist extends Playlist {
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
    private final String luid;

    @Nullable
    private final String playlistArtworkUrl;

    @Nullable
    private final List<Track> playlistTracks;

    @Nullable
    private final PlaymodeEligibility playmodeEligibility;

    @Nullable
    private final String title;

    private ImmutablePlaylist(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.isPrime = builder.isPrime;
        this.isSonicRush = builder.isSonicRush;
        this.isUnlimited = builder.isUnlimited;
        this.asin = builder.asin;
        this.luid = builder.luid;
        this.playlistArtworkUrl = builder.playlistArtworkUrl;
        this.playlistTracks = builder.playlistTracks == null ? null : createUnmodifiableList(true, builder.playlistTracks);
        this.playmodeEligibility = builder.playmodeEligibility;
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

    private ImmutablePlaylist(@Nullable String str, boolean z, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, boolean z2, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable List<Track> list, @Nullable PlaymodeEligibility playmodeEligibility) {
        this.initShim = new InitShim();
        this.title = str;
        this.isFree = z;
        this.isPrime = bool;
        this.isSonicRush = bool2;
        this.isUnlimited = bool3;
        this.isExplicit = z2;
        this.asin = str2;
        this.luid = str3;
        this.playlistArtworkUrl = str4;
        this.playlistTracks = list;
        this.playmodeEligibility = playmodeEligibility;
        this.initShim = null;
    }

    @Generated(from = "Playlist", generator = "Immutables")
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
                this.isFree = ImmutablePlaylist.super.isFree();
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
                this.isExplicit = ImmutablePlaylist.super.isExplicit();
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
            return "Cannot build Playlist, attribute initializers form cycle " + arrayList;
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

    @Override // ExternalActionInterface.v1_0.Playlist
    @JsonProperty("asin")
    @Nullable
    public String asin() {
        return this.asin;
    }

    @Override // ExternalActionInterface.v1_0.Playlist
    @JsonProperty("luid")
    @Nullable
    public String luid() {
        return this.luid;
    }

    @Override // ExternalActionInterface.v1_0.Playlist
    @JsonProperty("playlistArtworkUrl")
    @Nullable
    public String playlistArtworkUrl() {
        return this.playlistArtworkUrl;
    }

    @Override // ExternalActionInterface.v1_0.Playlist
    @JsonProperty("playlistTracks")
    @Nullable
    public List<Track> playlistTracks() {
        return this.playlistTracks;
    }

    @Override // ExternalActionInterface.v1_0.Playlist
    @JsonProperty("playmodeEligibility")
    @Nullable
    public PlaymodeEligibility playmodeEligibility() {
        return this.playmodeEligibility;
    }

    public final ImmutablePlaylist withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutablePlaylist(str, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.luid, this.playlistArtworkUrl, this.playlistTracks, this.playmodeEligibility);
    }

    public final ImmutablePlaylist withIsFree(boolean z) {
        return this.isFree == z ? this : new ImmutablePlaylist(this.title, z, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.luid, this.playlistArtworkUrl, this.playlistTracks, this.playmodeEligibility);
    }

    public final ImmutablePlaylist withIsPrime(@Nullable Boolean bool) {
        return equals(this.isPrime, bool) ? this : new ImmutablePlaylist(this.title, this.isFree, bool, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.luid, this.playlistArtworkUrl, this.playlistTracks, this.playmodeEligibility);
    }

    public final ImmutablePlaylist withIsSonicRush(@Nullable Boolean bool) {
        return equals(this.isSonicRush, bool) ? this : new ImmutablePlaylist(this.title, this.isFree, this.isPrime, bool, this.isUnlimited, this.isExplicit, this.asin, this.luid, this.playlistArtworkUrl, this.playlistTracks, this.playmodeEligibility);
    }

    public final ImmutablePlaylist withIsUnlimited(@Nullable Boolean bool) {
        return equals(this.isUnlimited, bool) ? this : new ImmutablePlaylist(this.title, this.isFree, this.isPrime, this.isSonicRush, bool, this.isExplicit, this.asin, this.luid, this.playlistArtworkUrl, this.playlistTracks, this.playmodeEligibility);
    }

    public final ImmutablePlaylist withIsExplicit(boolean z) {
        return this.isExplicit == z ? this : new ImmutablePlaylist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, z, this.asin, this.luid, this.playlistArtworkUrl, this.playlistTracks, this.playmodeEligibility);
    }

    public final ImmutablePlaylist withAsin(@Nullable String str) {
        return equals(this.asin, str) ? this : new ImmutablePlaylist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, str, this.luid, this.playlistArtworkUrl, this.playlistTracks, this.playmodeEligibility);
    }

    public final ImmutablePlaylist withLuid(@Nullable String str) {
        return equals(this.luid, str) ? this : new ImmutablePlaylist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, str, this.playlistArtworkUrl, this.playlistTracks, this.playmodeEligibility);
    }

    public final ImmutablePlaylist withPlaylistArtworkUrl(@Nullable String str) {
        return equals(this.playlistArtworkUrl, str) ? this : new ImmutablePlaylist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.luid, str, this.playlistTracks, this.playmodeEligibility);
    }

    public final ImmutablePlaylist withPlaylistTracks(@Nullable Track... trackArr) {
        if (trackArr == null) {
            return new ImmutablePlaylist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.luid, this.playlistArtworkUrl, null, this.playmodeEligibility);
        }
        return new ImmutablePlaylist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.luid, this.playlistArtworkUrl, Arrays.asList(trackArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(trackArr), true, false)), this.playmodeEligibility);
    }

    public final ImmutablePlaylist withPlaylistTracks(@Nullable Iterable<? extends Track> iterable) {
        if (this.playlistTracks == iterable) {
            return this;
        }
        return new ImmutablePlaylist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.luid, this.playlistArtworkUrl, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.playmodeEligibility);
    }

    public final ImmutablePlaylist withPlaymodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
        return this.playmodeEligibility == playmodeEligibility ? this : new ImmutablePlaylist(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.luid, this.playlistArtworkUrl, this.playlistTracks, playmodeEligibility);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlaylist) && equalTo(0, (ImmutablePlaylist) obj);
    }

    private boolean equalTo(int i, ImmutablePlaylist immutablePlaylist) {
        return equals(this.title, immutablePlaylist.title) && this.isFree == immutablePlaylist.isFree && equals(this.isPrime, immutablePlaylist.isPrime) && equals(this.isSonicRush, immutablePlaylist.isSonicRush) && equals(this.isUnlimited, immutablePlaylist.isUnlimited) && this.isExplicit == immutablePlaylist.isExplicit && equals(this.asin, immutablePlaylist.asin) && equals(this.luid, immutablePlaylist.luid) && equals(this.playlistArtworkUrl, immutablePlaylist.playlistArtworkUrl) && equals(this.playlistTracks, immutablePlaylist.playlistTracks) && equals(this.playmodeEligibility, immutablePlaylist.playmodeEligibility);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.title) + 5381;
        int i = iHashCode + (iHashCode << 5) + (this.isFree ? 1231 : 1237);
        int iHashCode2 = i + (i << 5) + hashCode(this.isPrime);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.isSonicRush);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.isUnlimited);
        int i2 = iHashCode4 + (iHashCode4 << 5) + (this.isExplicit ? 1231 : 1237);
        int iHashCode5 = i2 + (i2 << 5) + hashCode(this.asin);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.luid);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.playlistArtworkUrl);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.playlistTracks);
        return iHashCode8 + (iHashCode8 << 5) + hashCode(this.playmodeEligibility);
    }

    public String toString() {
        return "Playlist{title=" + this.title + ", isFree=" + this.isFree + ", isPrime=" + this.isPrime + ", isSonicRush=" + this.isSonicRush + ", isUnlimited=" + this.isUnlimited + ", isExplicit=" + this.isExplicit + ", asin=" + this.asin + ", luid=" + this.luid + ", playlistArtworkUrl=" + this.playlistArtworkUrl + ", playlistTracks=" + this.playlistTracks + ", playmodeEligibility=" + this.playmodeEligibility + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "Playlist", generator = "Immutables")
    static final class Json extends Playlist {

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
        String luid;

        @Nullable
        String playlistArtworkUrl;

        @Nullable
        List<Track> playlistTracks = null;

        @Nullable
        PlaymodeEligibility playmodeEligibility;

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

        @JsonProperty("luid")
        public void setLuid(@Nullable String str) {
            this.luid = str;
        }

        @JsonProperty("playlistArtworkUrl")
        public void setPlaylistArtworkUrl(@Nullable String str) {
            this.playlistArtworkUrl = str;
        }

        @JsonProperty("playlistTracks")
        public void setPlaylistTracks(@Nullable List<Track> list) {
            this.playlistTracks = list;
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

        @Override // ExternalActionInterface.v1_0.Playlist
        public String asin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Playlist
        public String luid() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Playlist
        public String playlistArtworkUrl() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Playlist
        public List<Track> playlistTracks() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Playlist
        public PlaymodeEligibility playmodeEligibility() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePlaylist fromJson(Json json) {
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
        String str3 = json.luid;
        if (str3 != null) {
            builder.luid(str3);
        }
        String str4 = json.playlistArtworkUrl;
        if (str4 != null) {
            builder.playlistArtworkUrl(str4);
        }
        List<Track> list = json.playlistTracks;
        if (list != null) {
            builder.addAllPlaylistTracks(list);
        }
        PlaymodeEligibility playmodeEligibility = json.playmodeEligibility;
        if (playmodeEligibility != null) {
            builder.playmodeEligibility(playmodeEligibility);
        }
        return builder.build();
    }

    public static ImmutablePlaylist copyOf(Playlist playlist) {
        if (playlist instanceof ImmutablePlaylist) {
            return (ImmutablePlaylist) playlist;
        }
        return builder().from(playlist).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "Playlist", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_IS_EXPLICIT = 2;
        private static final long OPT_BIT_IS_FREE = 1;

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

        @Nullable
        private String luid;
        private long optBits;

        @Nullable
        private String playlistArtworkUrl;
        private List<Track> playlistTracks;

        @Nullable
        private PlaymodeEligibility playmodeEligibility;

        @Nullable
        private String title;

        private Builder() {
            this.playlistTracks = null;
        }

        public final Builder from(PlayableContent playableContent) {
            ImmutablePlaylist.requireNonNull(playableContent, "instance");
            from((Object) playableContent);
            return this;
        }

        public final Builder from(Playlist playlist) {
            ImmutablePlaylist.requireNonNull(playlist, "instance");
            from((Object) playlist);
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
            if (obj instanceof Playlist) {
                Playlist playlist = (Playlist) obj;
                PlaymodeEligibility playmodeEligibility = playlist.playmodeEligibility();
                if (playmodeEligibility != null) {
                    playmodeEligibility(playmodeEligibility);
                }
                String strAsin = playlist.asin();
                if (strAsin != null) {
                    asin(strAsin);
                }
                List<Track> listPlaylistTracks = playlist.playlistTracks();
                if (listPlaylistTracks != null) {
                    addAllPlaylistTracks(listPlaylistTracks);
                }
                String strLuid = playlist.luid();
                if (strLuid != null) {
                    luid(strLuid);
                }
                String strPlaylistArtworkUrl = playlist.playlistArtworkUrl();
                if (strPlaylistArtworkUrl != null) {
                    playlistArtworkUrl(strPlaylistArtworkUrl);
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

        @JsonProperty("luid")
        public final Builder luid(@Nullable String str) {
            this.luid = str;
            return this;
        }

        @JsonProperty("playlistArtworkUrl")
        public final Builder playlistArtworkUrl(@Nullable String str) {
            this.playlistArtworkUrl = str;
            return this;
        }

        public final Builder addPlaylistTracks(Track track) {
            if (this.playlistTracks == null) {
                this.playlistTracks = new ArrayList();
            }
            this.playlistTracks.add((Track) ImmutablePlaylist.requireNonNull(track, "playlistTracks element"));
            return this;
        }

        public final Builder addPlaylistTracks(Track... trackArr) {
            if (this.playlistTracks == null) {
                this.playlistTracks = new ArrayList();
            }
            for (Track track : trackArr) {
                this.playlistTracks.add((Track) ImmutablePlaylist.requireNonNull(track, "playlistTracks element"));
            }
            return this;
        }

        @JsonProperty("playlistTracks")
        public final Builder playlistTracks(@Nullable Iterable<? extends Track> iterable) {
            if (iterable == null) {
                this.playlistTracks = null;
                return this;
            }
            this.playlistTracks = new ArrayList();
            return addAllPlaylistTracks(iterable);
        }

        public final Builder addAllPlaylistTracks(Iterable<? extends Track> iterable) {
            ImmutablePlaylist.requireNonNull(iterable, "playlistTracks element");
            if (this.playlistTracks == null) {
                this.playlistTracks = new ArrayList();
            }
            Iterator<? extends Track> it = iterable.iterator();
            while (it.hasNext()) {
                this.playlistTracks.add((Track) ImmutablePlaylist.requireNonNull(it.next(), "playlistTracks element"));
            }
            return this;
        }

        @JsonProperty("playmodeEligibility")
        public final Builder playmodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
            this.playmodeEligibility = playmodeEligibility;
            return this;
        }

        public ImmutablePlaylist build() {
            return new ImmutablePlaylist(this);
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
