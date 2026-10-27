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
@Generated(from = "MusicExperience", generator = "Immutables")
public final class ImmutableMusicExperience extends MusicExperience {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String artistArtwork;

    @Nullable
    private final String artistAsin;

    @Nullable
    private final String artistName;

    @Nullable
    private final String experienceId;

    @Nullable
    private final PlayableContent featuredContent;
    private volatile transient InitShim initShim;

    @Nullable
    private final Boolean isExpired;
    private final boolean isExplicit;
    private final boolean isFree;

    @Nullable
    private final Boolean isPrime;

    @Nullable
    private final Boolean isSonicRush;

    @Nullable
    private final Boolean isUnlimited;

    @Nullable
    private final String musicExperienceArtwork;

    @Nullable
    private final String musicExperiencePlaybackContent;

    @Nullable
    private final MusicExperienceType musicExperienceType;

    @Nullable
    private final String title;

    private ImmutableMusicExperience(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.isPrime = builder.isPrime;
        this.isSonicRush = builder.isSonicRush;
        this.isUnlimited = builder.isUnlimited;
        this.artistName = builder.artistName;
        this.artistAsin = builder.artistAsin;
        this.artistArtwork = builder.artistArtwork;
        this.musicExperienceArtwork = builder.musicExperienceArtwork;
        this.musicExperienceType = builder.musicExperienceType;
        this.musicExperiencePlaybackContent = builder.musicExperiencePlaybackContent;
        this.featuredContent = builder.featuredContent;
        this.experienceId = builder.experienceId;
        this.isExpired = builder.isExpired;
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

    private ImmutableMusicExperience(@Nullable String str, boolean z, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, boolean z2, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable MusicExperienceType musicExperienceType, @Nullable String str6, @Nullable PlayableContent playableContent, @Nullable String str7, @Nullable Boolean bool4) {
        this.initShim = new InitShim();
        this.title = str;
        this.isFree = z;
        this.isPrime = bool;
        this.isSonicRush = bool2;
        this.isUnlimited = bool3;
        this.isExplicit = z2;
        this.artistName = str2;
        this.artistAsin = str3;
        this.artistArtwork = str4;
        this.musicExperienceArtwork = str5;
        this.musicExperienceType = musicExperienceType;
        this.musicExperiencePlaybackContent = str6;
        this.featuredContent = playableContent;
        this.experienceId = str7;
        this.isExpired = bool4;
        this.initShim = null;
    }

    @Generated(from = "MusicExperience", generator = "Immutables")
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
                this.isFree = ImmutableMusicExperience.super.isFree();
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
                this.isExplicit = ImmutableMusicExperience.super.isExplicit();
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
            return "Cannot build MusicExperience, attribute initializers form cycle " + arrayList;
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

    @Override // ExternalActionInterface.v1_0.MusicExperience
    @JsonProperty("artistName")
    @Nullable
    public String artistName() {
        return this.artistName;
    }

    @Override // ExternalActionInterface.v1_0.MusicExperience
    @JsonProperty("artistAsin")
    @Nullable
    public String artistAsin() {
        return this.artistAsin;
    }

    @Override // ExternalActionInterface.v1_0.MusicExperience
    @JsonProperty("artistArtwork")
    @Nullable
    public String artistArtwork() {
        return this.artistArtwork;
    }

    @Override // ExternalActionInterface.v1_0.MusicExperience
    @JsonProperty("musicExperienceArtwork")
    @Nullable
    public String musicExperienceArtwork() {
        return this.musicExperienceArtwork;
    }

    @Override // ExternalActionInterface.v1_0.MusicExperience
    @JsonProperty("musicExperienceType")
    @Nullable
    public MusicExperienceType musicExperienceType() {
        return this.musicExperienceType;
    }

    @Override // ExternalActionInterface.v1_0.MusicExperience
    @JsonProperty("musicExperiencePlaybackContent")
    @Nullable
    public String musicExperiencePlaybackContent() {
        return this.musicExperiencePlaybackContent;
    }

    @Override // ExternalActionInterface.v1_0.MusicExperience
    @JsonProperty("featuredContent")
    @Nullable
    public PlayableContent featuredContent() {
        return this.featuredContent;
    }

    @Override // ExternalActionInterface.v1_0.MusicExperience
    @JsonProperty("experienceId")
    @Nullable
    public String experienceId() {
        return this.experienceId;
    }

    @Override // ExternalActionInterface.v1_0.MusicExperience
    @JsonProperty("isExpired")
    @Nullable
    public Boolean isExpired() {
        return this.isExpired;
    }

    public final ImmutableMusicExperience withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableMusicExperience(str, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withIsFree(boolean z) {
        return this.isFree == z ? this : new ImmutableMusicExperience(this.title, z, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withIsPrime(@Nullable Boolean bool) {
        return equals(this.isPrime, bool) ? this : new ImmutableMusicExperience(this.title, this.isFree, bool, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withIsSonicRush(@Nullable Boolean bool) {
        return equals(this.isSonicRush, bool) ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, bool, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withIsUnlimited(@Nullable Boolean bool) {
        return equals(this.isUnlimited, bool) ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, this.isSonicRush, bool, this.isExplicit, this.artistName, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withIsExplicit(boolean z) {
        return this.isExplicit == z ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, z, this.artistName, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withArtistName(@Nullable String str) {
        return equals(this.artistName, str) ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, str, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withArtistAsin(@Nullable String str) {
        return equals(this.artistAsin, str) ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, str, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withArtistArtwork(@Nullable String str) {
        return equals(this.artistArtwork, str) ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, str, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withMusicExperienceArtwork(@Nullable String str) {
        return equals(this.musicExperienceArtwork, str) ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.artistArtwork, str, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withMusicExperienceType(@Nullable MusicExperienceType musicExperienceType) {
        return this.musicExperienceType == musicExperienceType ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withMusicExperiencePlaybackContent(@Nullable String str) {
        return equals(this.musicExperiencePlaybackContent, str) ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, str, this.featuredContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withFeaturedContent(@Nullable PlayableContent playableContent) {
        return this.featuredContent == playableContent ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, playableContent, this.experienceId, this.isExpired);
    }

    public final ImmutableMusicExperience withExperienceId(@Nullable String str) {
        return equals(this.experienceId, str) ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, str, this.isExpired);
    }

    public final ImmutableMusicExperience withIsExpired(@Nullable Boolean bool) {
        return equals(this.isExpired, bool) ? this : new ImmutableMusicExperience(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.artistArtwork, this.musicExperienceArtwork, this.musicExperienceType, this.musicExperiencePlaybackContent, this.featuredContent, this.experienceId, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMusicExperience) && equalTo(0, (ImmutableMusicExperience) obj);
    }

    private boolean equalTo(int i, ImmutableMusicExperience immutableMusicExperience) {
        return equals(this.title, immutableMusicExperience.title) && this.isFree == immutableMusicExperience.isFree && equals(this.isPrime, immutableMusicExperience.isPrime) && equals(this.isSonicRush, immutableMusicExperience.isSonicRush) && equals(this.isUnlimited, immutableMusicExperience.isUnlimited) && this.isExplicit == immutableMusicExperience.isExplicit && equals(this.artistName, immutableMusicExperience.artistName) && equals(this.artistAsin, immutableMusicExperience.artistAsin) && equals(this.artistArtwork, immutableMusicExperience.artistArtwork) && equals(this.musicExperienceArtwork, immutableMusicExperience.musicExperienceArtwork) && equals(this.musicExperienceType, immutableMusicExperience.musicExperienceType) && equals(this.musicExperiencePlaybackContent, immutableMusicExperience.musicExperiencePlaybackContent) && equals(this.featuredContent, immutableMusicExperience.featuredContent) && equals(this.experienceId, immutableMusicExperience.experienceId) && equals(this.isExpired, immutableMusicExperience.isExpired);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.title) + 5381;
        int i = iHashCode + (iHashCode << 5) + (this.isFree ? 1231 : 1237);
        int iHashCode2 = i + (i << 5) + hashCode(this.isPrime);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.isSonicRush);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.isUnlimited);
        int i2 = iHashCode4 + (iHashCode4 << 5) + (this.isExplicit ? 1231 : 1237);
        int iHashCode5 = i2 + (i2 << 5) + hashCode(this.artistName);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.artistAsin);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.artistArtwork);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.musicExperienceArtwork);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.musicExperienceType);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.musicExperiencePlaybackContent);
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + hashCode(this.featuredContent);
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + hashCode(this.experienceId);
        return iHashCode12 + (iHashCode12 << 5) + hashCode(this.isExpired);
    }

    public String toString() {
        return "MusicExperience{title=" + this.title + ", isFree=" + this.isFree + ", isPrime=" + this.isPrime + ", isSonicRush=" + this.isSonicRush + ", isUnlimited=" + this.isUnlimited + ", isExplicit=" + this.isExplicit + ", artistName=" + this.artistName + ", artistAsin=" + this.artistAsin + ", artistArtwork=" + this.artistArtwork + ", musicExperienceArtwork=" + this.musicExperienceArtwork + ", musicExperienceType=" + this.musicExperienceType + ", musicExperiencePlaybackContent=" + this.musicExperiencePlaybackContent + ", featuredContent=" + this.featuredContent + ", experienceId=" + this.experienceId + ", isExpired=" + this.isExpired + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MusicExperience", generator = "Immutables")
    static final class Json extends MusicExperience {

        @Nullable
        String artistArtwork;

        @Nullable
        String artistAsin;

        @Nullable
        String artistName;

        @Nullable
        String experienceId;

        @Nullable
        PlayableContent featuredContent;

        @Nullable
        Boolean isExpired;
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
        String musicExperienceArtwork;

        @Nullable
        String musicExperiencePlaybackContent;

        @Nullable
        MusicExperienceType musicExperienceType;

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

        @JsonProperty("artistName")
        public void setArtistName(@Nullable String str) {
            this.artistName = str;
        }

        @JsonProperty("artistAsin")
        public void setArtistAsin(@Nullable String str) {
            this.artistAsin = str;
        }

        @JsonProperty("artistArtwork")
        public void setArtistArtwork(@Nullable String str) {
            this.artistArtwork = str;
        }

        @JsonProperty("musicExperienceArtwork")
        public void setMusicExperienceArtwork(@Nullable String str) {
            this.musicExperienceArtwork = str;
        }

        @JsonProperty("musicExperienceType")
        public void setMusicExperienceType(@Nullable MusicExperienceType musicExperienceType) {
            this.musicExperienceType = musicExperienceType;
        }

        @JsonProperty("musicExperiencePlaybackContent")
        public void setMusicExperiencePlaybackContent(@Nullable String str) {
            this.musicExperiencePlaybackContent = str;
        }

        @JsonProperty("featuredContent")
        public void setFeaturedContent(@Nullable PlayableContent playableContent) {
            this.featuredContent = playableContent;
        }

        @JsonProperty("experienceId")
        public void setExperienceId(@Nullable String str) {
            this.experienceId = str;
        }

        @JsonProperty("isExpired")
        public void setIsExpired(@Nullable Boolean bool) {
            this.isExpired = bool;
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

        @Override // ExternalActionInterface.v1_0.MusicExperience
        public String artistName() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.MusicExperience
        public String artistAsin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.MusicExperience
        public String artistArtwork() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.MusicExperience
        public String musicExperienceArtwork() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.MusicExperience
        public MusicExperienceType musicExperienceType() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.MusicExperience
        public String musicExperiencePlaybackContent() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.MusicExperience
        public PlayableContent featuredContent() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.MusicExperience
        public String experienceId() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.MusicExperience
        public Boolean isExpired() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMusicExperience fromJson(Json json) {
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
        String str2 = json.artistName;
        if (str2 != null) {
            builder.artistName(str2);
        }
        String str3 = json.artistAsin;
        if (str3 != null) {
            builder.artistAsin(str3);
        }
        String str4 = json.artistArtwork;
        if (str4 != null) {
            builder.artistArtwork(str4);
        }
        String str5 = json.musicExperienceArtwork;
        if (str5 != null) {
            builder.musicExperienceArtwork(str5);
        }
        MusicExperienceType musicExperienceType = json.musicExperienceType;
        if (musicExperienceType != null) {
            builder.musicExperienceType(musicExperienceType);
        }
        String str6 = json.musicExperiencePlaybackContent;
        if (str6 != null) {
            builder.musicExperiencePlaybackContent(str6);
        }
        PlayableContent playableContent = json.featuredContent;
        if (playableContent != null) {
            builder.featuredContent(playableContent);
        }
        String str7 = json.experienceId;
        if (str7 != null) {
            builder.experienceId(str7);
        }
        Boolean bool4 = json.isExpired;
        if (bool4 != null) {
            builder.isExpired(bool4);
        }
        return builder.build();
    }

    public static ImmutableMusicExperience copyOf(MusicExperience musicExperience) {
        if (musicExperience instanceof ImmutableMusicExperience) {
            return (ImmutableMusicExperience) musicExperience;
        }
        return builder().from(musicExperience).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MusicExperience", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_IS_EXPLICIT = 2;
        private static final long OPT_BIT_IS_FREE = 1;

        @Nullable
        private String artistArtwork;

        @Nullable
        private String artistAsin;

        @Nullable
        private String artistName;

        @Nullable
        private String experienceId;

        @Nullable
        private PlayableContent featuredContent;

        @Nullable
        private Boolean isExpired;
        private boolean isExplicit;
        private boolean isFree;

        @Nullable
        private Boolean isPrime;

        @Nullable
        private Boolean isSonicRush;

        @Nullable
        private Boolean isUnlimited;

        @Nullable
        private String musicExperienceArtwork;

        @Nullable
        private String musicExperiencePlaybackContent;

        @Nullable
        private MusicExperienceType musicExperienceType;
        private long optBits;

        @Nullable
        private String title;

        private Builder() {
        }

        public final Builder from(MusicExperience musicExperience) {
            ImmutableMusicExperience.requireNonNull(musicExperience, "instance");
            from((Object) musicExperience);
            return this;
        }

        public final Builder from(PlayableContent playableContent) {
            ImmutableMusicExperience.requireNonNull(playableContent, "instance");
            from((Object) playableContent);
            return this;
        }

        private void from(Object obj) {
            if (obj instanceof MusicExperience) {
                MusicExperience musicExperience = (MusicExperience) obj;
                String strExperienceId = musicExperience.experienceId();
                if (strExperienceId != null) {
                    experienceId(strExperienceId);
                }
                String strMusicExperienceArtwork = musicExperience.musicExperienceArtwork();
                if (strMusicExperienceArtwork != null) {
                    musicExperienceArtwork(strMusicExperienceArtwork);
                }
                MusicExperienceType musicExperienceType = musicExperience.musicExperienceType();
                if (musicExperienceType != null) {
                    musicExperienceType(musicExperienceType);
                }
                String strArtistArtwork = musicExperience.artistArtwork();
                if (strArtistArtwork != null) {
                    artistArtwork(strArtistArtwork);
                }
                String strMusicExperiencePlaybackContent = musicExperience.musicExperiencePlaybackContent();
                if (strMusicExperiencePlaybackContent != null) {
                    musicExperiencePlaybackContent(strMusicExperiencePlaybackContent);
                }
                String strArtistName = musicExperience.artistName();
                if (strArtistName != null) {
                    artistName(strArtistName);
                }
                PlayableContent playableContentFeaturedContent = musicExperience.featuredContent();
                if (playableContentFeaturedContent != null) {
                    featuredContent(playableContentFeaturedContent);
                }
                Boolean boolIsExpired = musicExperience.isExpired();
                if (boolIsExpired != null) {
                    isExpired(boolIsExpired);
                }
                String strArtistAsin = musicExperience.artistAsin();
                if (strArtistAsin != null) {
                    artistAsin(strArtistAsin);
                }
            }
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

        @JsonProperty("artistArtwork")
        public final Builder artistArtwork(@Nullable String str) {
            this.artistArtwork = str;
            return this;
        }

        @JsonProperty("musicExperienceArtwork")
        public final Builder musicExperienceArtwork(@Nullable String str) {
            this.musicExperienceArtwork = str;
            return this;
        }

        @JsonProperty("musicExperienceType")
        public final Builder musicExperienceType(@Nullable MusicExperienceType musicExperienceType) {
            this.musicExperienceType = musicExperienceType;
            return this;
        }

        @JsonProperty("musicExperiencePlaybackContent")
        public final Builder musicExperiencePlaybackContent(@Nullable String str) {
            this.musicExperiencePlaybackContent = str;
            return this;
        }

        @JsonProperty("featuredContent")
        public final Builder featuredContent(@Nullable PlayableContent playableContent) {
            this.featuredContent = playableContent;
            return this;
        }

        @JsonProperty("experienceId")
        public final Builder experienceId(@Nullable String str) {
            this.experienceId = str;
            return this;
        }

        @JsonProperty("isExpired")
        public final Builder isExpired(@Nullable Boolean bool) {
            this.isExpired = bool;
            return this;
        }

        public ImmutableMusicExperience build() {
            return new ImmutableMusicExperience(this);
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
}
