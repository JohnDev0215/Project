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
@Deprecated
@Immutable
@Generated(from = "ArtistSpotlight", generator = "Immutables")
public final class ImmutableArtistSpotlight extends ArtistSpotlight {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String artistAsin;

    @Nullable
    private final String artistName;

    @Nullable
    private final PlayableContent featuredContent;
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
    private final String spotlightArtwork;

    @Nullable
    private final String spotlightPlaybackContent;

    @Nullable
    private final String title;

    private ImmutableArtistSpotlight(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.isPrime = builder.isPrime;
        this.isSonicRush = builder.isSonicRush;
        this.isUnlimited = builder.isUnlimited;
        this.artistName = builder.artistName;
        this.artistAsin = builder.artistAsin;
        this.spotlightArtwork = builder.spotlightArtwork;
        this.spotlightPlaybackContent = builder.spotlightPlaybackContent;
        this.featuredContent = builder.featuredContent;
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

    private ImmutableArtistSpotlight(@Nullable String str, boolean z, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, boolean z2, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable PlayableContent playableContent) {
        this.initShim = new InitShim();
        this.title = str;
        this.isFree = z;
        this.isPrime = bool;
        this.isSonicRush = bool2;
        this.isUnlimited = bool3;
        this.isExplicit = z2;
        this.artistName = str2;
        this.artistAsin = str3;
        this.spotlightArtwork = str4;
        this.spotlightPlaybackContent = str5;
        this.featuredContent = playableContent;
        this.initShim = null;
    }

    @Generated(from = "ArtistSpotlight", generator = "Immutables")
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
                this.isFree = ImmutableArtistSpotlight.super.isFree();
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
                this.isExplicit = ImmutableArtistSpotlight.super.isExplicit();
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
            return "Cannot build ArtistSpotlight, attribute initializers form cycle " + arrayList;
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

    @Override // ExternalActionInterface.v1_0.ArtistSpotlight
    @JsonProperty("artistName")
    @Nullable
    public String artistName() {
        return this.artistName;
    }

    @Override // ExternalActionInterface.v1_0.ArtistSpotlight
    @JsonProperty("artistAsin")
    @Nullable
    public String artistAsin() {
        return this.artistAsin;
    }

    @Override // ExternalActionInterface.v1_0.ArtistSpotlight
    @JsonProperty("spotlightArtwork")
    @Nullable
    public String spotlightArtwork() {
        return this.spotlightArtwork;
    }

    @Override // ExternalActionInterface.v1_0.ArtistSpotlight
    @JsonProperty("spotlightPlaybackContent")
    @Nullable
    public String spotlightPlaybackContent() {
        return this.spotlightPlaybackContent;
    }

    @Override // ExternalActionInterface.v1_0.ArtistSpotlight
    @JsonProperty("featuredContent")
    @Nullable
    public PlayableContent featuredContent() {
        return this.featuredContent;
    }

    public final ImmutableArtistSpotlight withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableArtistSpotlight(str, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.spotlightArtwork, this.spotlightPlaybackContent, this.featuredContent);
    }

    public final ImmutableArtistSpotlight withIsFree(boolean z) {
        return this.isFree == z ? this : new ImmutableArtistSpotlight(this.title, z, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.spotlightArtwork, this.spotlightPlaybackContent, this.featuredContent);
    }

    public final ImmutableArtistSpotlight withIsPrime(@Nullable Boolean bool) {
        return equals(this.isPrime, bool) ? this : new ImmutableArtistSpotlight(this.title, this.isFree, bool, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.spotlightArtwork, this.spotlightPlaybackContent, this.featuredContent);
    }

    public final ImmutableArtistSpotlight withIsSonicRush(@Nullable Boolean bool) {
        return equals(this.isSonicRush, bool) ? this : new ImmutableArtistSpotlight(this.title, this.isFree, this.isPrime, bool, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.spotlightArtwork, this.spotlightPlaybackContent, this.featuredContent);
    }

    public final ImmutableArtistSpotlight withIsUnlimited(@Nullable Boolean bool) {
        return equals(this.isUnlimited, bool) ? this : new ImmutableArtistSpotlight(this.title, this.isFree, this.isPrime, this.isSonicRush, bool, this.isExplicit, this.artistName, this.artistAsin, this.spotlightArtwork, this.spotlightPlaybackContent, this.featuredContent);
    }

    public final ImmutableArtistSpotlight withIsExplicit(boolean z) {
        return this.isExplicit == z ? this : new ImmutableArtistSpotlight(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, z, this.artistName, this.artistAsin, this.spotlightArtwork, this.spotlightPlaybackContent, this.featuredContent);
    }

    public final ImmutableArtistSpotlight withArtistName(@Nullable String str) {
        return equals(this.artistName, str) ? this : new ImmutableArtistSpotlight(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, str, this.artistAsin, this.spotlightArtwork, this.spotlightPlaybackContent, this.featuredContent);
    }

    public final ImmutableArtistSpotlight withArtistAsin(@Nullable String str) {
        return equals(this.artistAsin, str) ? this : new ImmutableArtistSpotlight(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, str, this.spotlightArtwork, this.spotlightPlaybackContent, this.featuredContent);
    }

    public final ImmutableArtistSpotlight withSpotlightArtwork(@Nullable String str) {
        return equals(this.spotlightArtwork, str) ? this : new ImmutableArtistSpotlight(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, str, this.spotlightPlaybackContent, this.featuredContent);
    }

    public final ImmutableArtistSpotlight withSpotlightPlaybackContent(@Nullable String str) {
        return equals(this.spotlightPlaybackContent, str) ? this : new ImmutableArtistSpotlight(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.spotlightArtwork, str, this.featuredContent);
    }

    public final ImmutableArtistSpotlight withFeaturedContent(@Nullable PlayableContent playableContent) {
        return this.featuredContent == playableContent ? this : new ImmutableArtistSpotlight(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.artistName, this.artistAsin, this.spotlightArtwork, this.spotlightPlaybackContent, playableContent);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableArtistSpotlight) && equalTo(0, (ImmutableArtistSpotlight) obj);
    }

    private boolean equalTo(int i, ImmutableArtistSpotlight immutableArtistSpotlight) {
        return equals(this.title, immutableArtistSpotlight.title) && this.isFree == immutableArtistSpotlight.isFree && equals(this.isPrime, immutableArtistSpotlight.isPrime) && equals(this.isSonicRush, immutableArtistSpotlight.isSonicRush) && equals(this.isUnlimited, immutableArtistSpotlight.isUnlimited) && this.isExplicit == immutableArtistSpotlight.isExplicit && equals(this.artistName, immutableArtistSpotlight.artistName) && equals(this.artistAsin, immutableArtistSpotlight.artistAsin) && equals(this.spotlightArtwork, immutableArtistSpotlight.spotlightArtwork) && equals(this.spotlightPlaybackContent, immutableArtistSpotlight.spotlightPlaybackContent) && equals(this.featuredContent, immutableArtistSpotlight.featuredContent);
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
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.spotlightArtwork);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.spotlightPlaybackContent);
        return iHashCode8 + (iHashCode8 << 5) + hashCode(this.featuredContent);
    }

    public String toString() {
        return "ArtistSpotlight{title=" + this.title + ", isFree=" + this.isFree + ", isPrime=" + this.isPrime + ", isSonicRush=" + this.isSonicRush + ", isUnlimited=" + this.isUnlimited + ", isExplicit=" + this.isExplicit + ", artistName=" + this.artistName + ", artistAsin=" + this.artistAsin + ", spotlightArtwork=" + this.spotlightArtwork + ", spotlightPlaybackContent=" + this.spotlightPlaybackContent + ", featuredContent=" + this.featuredContent + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ArtistSpotlight", generator = "Immutables")
    static final class Json extends ArtistSpotlight {

        @Nullable
        String artistAsin;

        @Nullable
        String artistName;

        @Nullable
        PlayableContent featuredContent;
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
        String spotlightArtwork;

        @Nullable
        String spotlightPlaybackContent;

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

        @JsonProperty("spotlightArtwork")
        public void setSpotlightArtwork(@Nullable String str) {
            this.spotlightArtwork = str;
        }

        @JsonProperty("spotlightPlaybackContent")
        public void setSpotlightPlaybackContent(@Nullable String str) {
            this.spotlightPlaybackContent = str;
        }

        @JsonProperty("featuredContent")
        public void setFeaturedContent(@Nullable PlayableContent playableContent) {
            this.featuredContent = playableContent;
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

        @Override // ExternalActionInterface.v1_0.ArtistSpotlight
        public String artistName() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.ArtistSpotlight
        public String artistAsin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.ArtistSpotlight
        public String spotlightArtwork() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.ArtistSpotlight
        public String spotlightPlaybackContent() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.ArtistSpotlight
        public PlayableContent featuredContent() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableArtistSpotlight fromJson(Json json) {
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
        String str4 = json.spotlightArtwork;
        if (str4 != null) {
            builder.spotlightArtwork(str4);
        }
        String str5 = json.spotlightPlaybackContent;
        if (str5 != null) {
            builder.spotlightPlaybackContent(str5);
        }
        PlayableContent playableContent = json.featuredContent;
        if (playableContent != null) {
            builder.featuredContent(playableContent);
        }
        return builder.build();
    }

    public static ImmutableArtistSpotlight copyOf(ArtistSpotlight artistSpotlight) {
        if (artistSpotlight instanceof ImmutableArtistSpotlight) {
            return (ImmutableArtistSpotlight) artistSpotlight;
        }
        return builder().from(artistSpotlight).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ArtistSpotlight", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_IS_EXPLICIT = 2;
        private static final long OPT_BIT_IS_FREE = 1;

        @Nullable
        private String artistAsin;

        @Nullable
        private String artistName;

        @Nullable
        private PlayableContent featuredContent;
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
        private String spotlightArtwork;

        @Nullable
        private String spotlightPlaybackContent;

        @Nullable
        private String title;

        private Builder() {
        }

        public final Builder from(PlayableContent playableContent) {
            ImmutableArtistSpotlight.requireNonNull(playableContent, "instance");
            from((Object) playableContent);
            return this;
        }

        public final Builder from(ArtistSpotlight artistSpotlight) {
            ImmutableArtistSpotlight.requireNonNull(artistSpotlight, "instance");
            from((Object) artistSpotlight);
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
            if (obj instanceof ArtistSpotlight) {
                ArtistSpotlight artistSpotlight = (ArtistSpotlight) obj;
                String strSpotlightPlaybackContent = artistSpotlight.spotlightPlaybackContent();
                if (strSpotlightPlaybackContent != null) {
                    spotlightPlaybackContent(strSpotlightPlaybackContent);
                }
                String strArtistName = artistSpotlight.artistName();
                if (strArtistName != null) {
                    artistName(strArtistName);
                }
                PlayableContent playableContentFeaturedContent = artistSpotlight.featuredContent();
                if (playableContentFeaturedContent != null) {
                    featuredContent(playableContentFeaturedContent);
                }
                String strArtistAsin = artistSpotlight.artistAsin();
                if (strArtistAsin != null) {
                    artistAsin(strArtistAsin);
                }
                String strSpotlightArtwork = artistSpotlight.spotlightArtwork();
                if (strSpotlightArtwork != null) {
                    spotlightArtwork(strSpotlightArtwork);
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

        @JsonProperty("spotlightArtwork")
        public final Builder spotlightArtwork(@Nullable String str) {
            this.spotlightArtwork = str;
            return this;
        }

        @JsonProperty("spotlightPlaybackContent")
        public final Builder spotlightPlaybackContent(@Nullable String str) {
            this.spotlightPlaybackContent = str;
            return this;
        }

        @JsonProperty("featuredContent")
        public final Builder featuredContent(@Nullable PlayableContent playableContent) {
            this.featuredContent = playableContent;
            return this;
        }

        public ImmutableArtistSpotlight build() {
            return new ImmutableArtistSpotlight(this);
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
