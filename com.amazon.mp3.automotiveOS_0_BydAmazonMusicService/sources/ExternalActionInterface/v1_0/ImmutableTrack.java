package ExternalActionInterface.v1_0;

import com.amazon.music.find.converter.BrushV2Converter;
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
@Generated(from = "Track", generator = "Immutables")
public final class ImmutableTrack extends Track {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String albumArtworkUrl;

    @Nullable
    private final String albumAsin;

    @Nullable
    private final String albumName;

    @Nullable
    private final String artistAsin;

    @Nullable
    private final String artistName;

    @Nullable
    private final String asin;

    @Nullable
    private final Integer duration;
    private volatile transient InitShim initShim;
    private final boolean isExplicit;
    private final boolean isFree;

    @Nullable
    private final Boolean isPrime;

    @Nullable
    private final Boolean isSonicRush;

    @Nullable
    private final Boolean isUnlimited;
    private final boolean isVideo;

    @Nullable
    private final ImmutablePlaymodeEligibility playmodeEligibility;

    @Nullable
    private final String title;

    @Nullable
    private final String videoPid;

    private ImmutableTrack(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.isPrime = builder.isPrime;
        this.isSonicRush = builder.isSonicRush;
        this.isUnlimited = builder.isUnlimited;
        this.asin = builder.asin;
        this.albumName = builder.albumName;
        this.albumAsin = builder.albumAsin;
        this.albumArtworkUrl = builder.albumArtworkUrl;
        this.artistName = builder.artistName;
        this.artistAsin = builder.artistAsin;
        this.videoPid = builder.videoPid;
        this.playmodeEligibility = builder.playmodeEligibility == null ? null : ImmutablePlaymodeEligibility.copyOf(builder.playmodeEligibility);
        this.duration = builder.duration;
        if (builder.isFreeIsSet()) {
            this.initShim.isFree(builder.isFree);
        }
        if (builder.isExplicitIsSet()) {
            this.initShim.isExplicit(builder.isExplicit);
        }
        if (builder.isVideoIsSet()) {
            this.initShim.isVideo(builder.isVideo);
        }
        this.isFree = this.initShim.isFree();
        this.isExplicit = this.initShim.isExplicit();
        this.isVideo = this.initShim.isVideo();
        this.initShim = null;
    }

    private ImmutableTrack(@Nullable String str, boolean z, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, boolean z2, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, boolean z3, @Nullable String str8, @Nullable ImmutablePlaymodeEligibility immutablePlaymodeEligibility, @Nullable Integer num) {
        this.initShim = new InitShim();
        this.title = str;
        this.isFree = z;
        this.isPrime = bool;
        this.isSonicRush = bool2;
        this.isUnlimited = bool3;
        this.isExplicit = z2;
        this.asin = str2;
        this.albumName = str3;
        this.albumAsin = str4;
        this.albumArtworkUrl = str5;
        this.artistName = str6;
        this.artistAsin = str7;
        this.isVideo = z3;
        this.videoPid = str8;
        this.playmodeEligibility = immutablePlaymodeEligibility;
        this.duration = num;
        this.initShim = null;
    }

    @Generated(from = "Track", generator = "Immutables")
    private final class InitShim {
        private boolean isExplicit;
        private byte isExplicitBuildStage;
        private boolean isFree;
        private byte isFreeBuildStage;
        private boolean isVideo;
        private byte isVideoBuildStage;

        private InitShim() {
            this.isFreeBuildStage = (byte) 0;
            this.isExplicitBuildStage = (byte) 0;
            this.isVideoBuildStage = (byte) 0;
        }

        boolean isFree() {
            byte b = this.isFreeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isFreeBuildStage = (byte) -1;
                this.isFree = ImmutableTrack.super.isFree();
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
                this.isExplicit = ImmutableTrack.super.isExplicit();
                this.isExplicitBuildStage = (byte) 1;
            }
            return this.isExplicit;
        }

        void isExplicit(boolean z) {
            this.isExplicit = z;
            this.isExplicitBuildStage = (byte) 1;
        }

        boolean isVideo() {
            byte b = this.isVideoBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isVideoBuildStage = (byte) -1;
                this.isVideo = ImmutableTrack.super.isVideo();
                this.isVideoBuildStage = (byte) 1;
            }
            return this.isVideo;
        }

        void isVideo(boolean z) {
            this.isVideo = z;
            this.isVideoBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isFreeBuildStage == -1) {
                arrayList.add("isFree");
            }
            if (this.isExplicitBuildStage == -1) {
                arrayList.add(BrushV2Converter.KEY_IS_EXPLICIT);
            }
            if (this.isVideoBuildStage == -1) {
                arrayList.add("isVideo");
            }
            return "Cannot build Track, attribute initializers form cycle " + arrayList;
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

    @Override // ExternalActionInterface.v1_0.Track
    @JsonProperty("asin")
    @Nullable
    public String asin() {
        return this.asin;
    }

    @Override // ExternalActionInterface.v1_0.Track
    @JsonProperty("albumName")
    @Nullable
    public String albumName() {
        return this.albumName;
    }

    @Override // ExternalActionInterface.v1_0.Track
    @JsonProperty("albumAsin")
    @Nullable
    public String albumAsin() {
        return this.albumAsin;
    }

    @Override // ExternalActionInterface.v1_0.Track
    @JsonProperty("albumArtworkUrl")
    @Nullable
    public String albumArtworkUrl() {
        return this.albumArtworkUrl;
    }

    @Override // ExternalActionInterface.v1_0.Track
    @JsonProperty("artistName")
    @Nullable
    public String artistName() {
        return this.artistName;
    }

    @Override // ExternalActionInterface.v1_0.Track
    @JsonProperty("artistAsin")
    @Nullable
    public String artistAsin() {
        return this.artistAsin;
    }

    @Override // ExternalActionInterface.v1_0.Track
    @JsonProperty("isVideo")
    public boolean isVideo() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isVideo();
        }
        return this.isVideo;
    }

    @Override // ExternalActionInterface.v1_0.Track
    @JsonProperty("videoPid")
    @Nullable
    public String videoPid() {
        return this.videoPid;
    }

    @Override // ExternalActionInterface.v1_0.Track
    @JsonProperty("playmodeEligibility")
    @Nullable
    public ImmutablePlaymodeEligibility playmodeEligibility() {
        return this.playmodeEligibility;
    }

    @Override // ExternalActionInterface.v1_0.Track
    @JsonProperty("duration")
    @Nullable
    public Integer duration() {
        return this.duration;
    }

    public final ImmutableTrack withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableTrack(str, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withIsFree(boolean z) {
        return this.isFree == z ? this : new ImmutableTrack(this.title, z, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withIsPrime(@Nullable Boolean bool) {
        return equals(this.isPrime, bool) ? this : new ImmutableTrack(this.title, this.isFree, bool, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withIsSonicRush(@Nullable Boolean bool) {
        return equals(this.isSonicRush, bool) ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, bool, this.isUnlimited, this.isExplicit, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withIsUnlimited(@Nullable Boolean bool) {
        return equals(this.isUnlimited, bool) ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, bool, this.isExplicit, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withIsExplicit(boolean z) {
        return this.isExplicit == z ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, z, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withAsin(@Nullable String str) {
        return equals(this.asin, str) ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, str, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withAlbumName(@Nullable String str) {
        return equals(this.albumName, str) ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, str, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withAlbumAsin(@Nullable String str) {
        return equals(this.albumAsin, str) ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumName, str, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withAlbumArtworkUrl(@Nullable String str) {
        return equals(this.albumArtworkUrl, str) ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumName, this.albumAsin, str, this.artistName, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withArtistName(@Nullable String str) {
        return equals(this.artistName, str) ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, str, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withArtistAsin(@Nullable String str) {
        return equals(this.artistAsin, str) ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, str, this.isVideo, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withIsVideo(boolean z) {
        return this.isVideo == z ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, z, this.videoPid, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withVideoPid(@Nullable String str) {
        return equals(this.videoPid, str) ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, str, this.playmodeEligibility, this.duration);
    }

    public final ImmutableTrack withPlaymodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
        if (this.playmodeEligibility == playmodeEligibility) {
            return this;
        }
        return new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, this.videoPid, playmodeEligibility == null ? null : ImmutablePlaymodeEligibility.copyOf(playmodeEligibility), this.duration);
    }

    public final ImmutableTrack withDuration(@Nullable Integer num) {
        return equals(this.duration, num) ? this : new ImmutableTrack(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.albumName, this.albumAsin, this.albumArtworkUrl, this.artistName, this.artistAsin, this.isVideo, this.videoPid, this.playmodeEligibility, num);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTrack) && equalTo(0, (ImmutableTrack) obj);
    }

    private boolean equalTo(int i, ImmutableTrack immutableTrack) {
        return equals(this.title, immutableTrack.title) && this.isFree == immutableTrack.isFree && equals(this.isPrime, immutableTrack.isPrime) && equals(this.isSonicRush, immutableTrack.isSonicRush) && equals(this.isUnlimited, immutableTrack.isUnlimited) && this.isExplicit == immutableTrack.isExplicit && equals(this.asin, immutableTrack.asin) && equals(this.albumName, immutableTrack.albumName) && equals(this.albumAsin, immutableTrack.albumAsin) && equals(this.albumArtworkUrl, immutableTrack.albumArtworkUrl) && equals(this.artistName, immutableTrack.artistName) && equals(this.artistAsin, immutableTrack.artistAsin) && this.isVideo == immutableTrack.isVideo && equals(this.videoPid, immutableTrack.videoPid) && equals(this.playmodeEligibility, immutableTrack.playmodeEligibility) && equals(this.duration, immutableTrack.duration);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.title) + 5381;
        int i = iHashCode + (iHashCode << 5) + (this.isFree ? 1231 : 1237);
        int iHashCode2 = i + (i << 5) + hashCode(this.isPrime);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.isSonicRush);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.isUnlimited);
        int i2 = iHashCode4 + (iHashCode4 << 5) + (this.isExplicit ? 1231 : 1237);
        int iHashCode5 = i2 + (i2 << 5) + hashCode(this.asin);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.albumName);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.albumAsin);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.albumArtworkUrl);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.artistName);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.artistAsin);
        int i3 = iHashCode10 + (iHashCode10 << 5) + (this.isVideo ? 1231 : 1237);
        int iHashCode11 = i3 + (i3 << 5) + hashCode(this.videoPid);
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + hashCode(this.playmodeEligibility);
        return iHashCode12 + (iHashCode12 << 5) + hashCode(this.duration);
    }

    public String toString() {
        return "Track{title=" + this.title + ", isFree=" + this.isFree + ", isPrime=" + this.isPrime + ", isSonicRush=" + this.isSonicRush + ", isUnlimited=" + this.isUnlimited + ", isExplicit=" + this.isExplicit + ", asin=" + this.asin + ", albumName=" + this.albumName + ", albumAsin=" + this.albumAsin + ", albumArtworkUrl=" + this.albumArtworkUrl + ", artistName=" + this.artistName + ", artistAsin=" + this.artistAsin + ", isVideo=" + this.isVideo + ", videoPid=" + this.videoPid + ", playmodeEligibility=" + this.playmodeEligibility + ", duration=" + this.duration + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "Track", generator = "Immutables")
    static final class Json extends Track {

        @Nullable
        String albumArtworkUrl;

        @Nullable
        String albumAsin;

        @Nullable
        String albumName;

        @Nullable
        String artistAsin;

        @Nullable
        String artistName;

        @Nullable
        String asin;

        @Nullable
        Integer duration;
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
        boolean isVideo;
        boolean isVideoIsSet;

        @Nullable
        PlaymodeEligibility playmodeEligibility;

        @Nullable
        String title;

        @Nullable
        String videoPid;

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

        @JsonProperty("albumName")
        public void setAlbumName(@Nullable String str) {
            this.albumName = str;
        }

        @JsonProperty("albumAsin")
        public void setAlbumAsin(@Nullable String str) {
            this.albumAsin = str;
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

        @JsonProperty("isVideo")
        public void setIsVideo(boolean z) {
            this.isVideo = z;
            this.isVideoIsSet = true;
        }

        @JsonProperty("videoPid")
        public void setVideoPid(@Nullable String str) {
            this.videoPid = str;
        }

        @JsonProperty("playmodeEligibility")
        public void setPlaymodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
            this.playmodeEligibility = playmodeEligibility;
        }

        @JsonProperty("duration")
        public void setDuration(@Nullable Integer num) {
            this.duration = num;
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

        @Override // ExternalActionInterface.v1_0.Track
        public String asin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Track
        public String albumName() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Track
        public String albumAsin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Track
        public String albumArtworkUrl() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Track
        public String artistName() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Track
        public String artistAsin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Track
        public boolean isVideo() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Track
        public String videoPid() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Track
        public PlaymodeEligibility playmodeEligibility() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Track
        public Integer duration() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTrack fromJson(Json json) {
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
        String str3 = json.albumName;
        if (str3 != null) {
            builder.albumName(str3);
        }
        String str4 = json.albumAsin;
        if (str4 != null) {
            builder.albumAsin(str4);
        }
        String str5 = json.albumArtworkUrl;
        if (str5 != null) {
            builder.albumArtworkUrl(str5);
        }
        String str6 = json.artistName;
        if (str6 != null) {
            builder.artistName(str6);
        }
        String str7 = json.artistAsin;
        if (str7 != null) {
            builder.artistAsin(str7);
        }
        if (json.isVideoIsSet) {
            builder.isVideo(json.isVideo);
        }
        String str8 = json.videoPid;
        if (str8 != null) {
            builder.videoPid(str8);
        }
        PlaymodeEligibility playmodeEligibility = json.playmodeEligibility;
        if (playmodeEligibility != null) {
            builder.playmodeEligibility(playmodeEligibility);
        }
        Integer num = json.duration;
        if (num != null) {
            builder.duration(num);
        }
        return builder.build();
    }

    public static ImmutableTrack copyOf(Track track) {
        if (track instanceof ImmutableTrack) {
            return (ImmutableTrack) track;
        }
        return builder().from(track).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "Track", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_IS_EXPLICIT = 2;
        private static final long OPT_BIT_IS_FREE = 1;
        private static final long OPT_BIT_IS_VIDEO = 4;

        @Nullable
        private String albumArtworkUrl;

        @Nullable
        private String albumAsin;

        @Nullable
        private String albumName;

        @Nullable
        private String artistAsin;

        @Nullable
        private String artistName;

        @Nullable
        private String asin;

        @Nullable
        private Integer duration;
        private boolean isExplicit;
        private boolean isFree;

        @Nullable
        private Boolean isPrime;

        @Nullable
        private Boolean isSonicRush;

        @Nullable
        private Boolean isUnlimited;
        private boolean isVideo;
        private long optBits;

        @Nullable
        private PlaymodeEligibility playmodeEligibility;

        @Nullable
        private String title;

        @Nullable
        private String videoPid;

        private Builder() {
        }

        public final Builder from(PlayableContent playableContent) {
            ImmutableTrack.requireNonNull(playableContent, "instance");
            from((Object) playableContent);
            return this;
        }

        public final Builder from(Track track) {
            ImmutableTrack.requireNonNull(track, "instance");
            from((Object) track);
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
            if (obj instanceof Track) {
                Track track = (Track) obj;
                String strVideoPid = track.videoPid();
                if (strVideoPid != null) {
                    videoPid(strVideoPid);
                }
                Integer numDuration = track.duration();
                if (numDuration != null) {
                    duration(numDuration);
                }
                String strAlbumName = track.albumName();
                if (strAlbumName != null) {
                    albumName(strAlbumName);
                }
                String strAlbumAsin = track.albumAsin();
                if (strAlbumAsin != null) {
                    albumAsin(strAlbumAsin);
                }
                String strAlbumArtworkUrl = track.albumArtworkUrl();
                if (strAlbumArtworkUrl != null) {
                    albumArtworkUrl(strAlbumArtworkUrl);
                }
                PlaymodeEligibility playmodeEligibility = track.playmodeEligibility();
                if (playmodeEligibility != null) {
                    playmodeEligibility(playmodeEligibility);
                }
                isVideo(track.isVideo());
                String strAsin = track.asin();
                if (strAsin != null) {
                    asin(strAsin);
                }
                String strArtistName = track.artistName();
                if (strArtistName != null) {
                    artistName(strArtistName);
                }
                String strArtistAsin = track.artistAsin();
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

        @JsonProperty("albumName")
        public final Builder albumName(@Nullable String str) {
            this.albumName = str;
            return this;
        }

        @JsonProperty("albumAsin")
        public final Builder albumAsin(@Nullable String str) {
            this.albumAsin = str;
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

        @JsonProperty("isVideo")
        public final Builder isVideo(boolean z) {
            this.isVideo = z;
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("videoPid")
        public final Builder videoPid(@Nullable String str) {
            this.videoPid = str;
            return this;
        }

        @JsonProperty("playmodeEligibility")
        public final Builder playmodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
            this.playmodeEligibility = playmodeEligibility == null ? null : ImmutablePlaymodeEligibility.copyOf(playmodeEligibility);
            return this;
        }

        @JsonProperty("duration")
        public final Builder duration(@Nullable Integer num) {
            this.duration = num;
            return this;
        }

        public ImmutableTrack build() {
            return new ImmutableTrack(this);
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
        public boolean isVideoIsSet() {
            return (this.optBits & 4) != 0;
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
