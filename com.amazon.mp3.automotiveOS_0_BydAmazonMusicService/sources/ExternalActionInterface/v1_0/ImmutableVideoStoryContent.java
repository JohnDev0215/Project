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
@Generated(from = "VideoStoryContent", generator = "Immutables")
public final class ImmutableVideoStoryContent extends VideoStoryContent {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String artistArtworkUrl;

    @Nullable
    private final String artistAsin;

    @Nullable
    private final String artistName;

    @Nullable
    private final String artworkUrl;

    @Nullable
    private final String asin;

    @Nullable
    private final Integer duration;
    private volatile transient InitShim initShim;

    @Nullable
    private final String introUri;
    private final boolean isExplicit;
    private final boolean isFree;

    @Nullable
    private final Boolean isPrime;

    @Nullable
    private final Boolean isSonicRush;

    @Nullable
    private final Boolean isUnlimited;

    @Nullable
    private final String title;

    @Nullable
    private final String uri;

    @Nullable
    private final String videoPid;

    private ImmutableVideoStoryContent(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.isPrime = builder.isPrime;
        this.isSonicRush = builder.isSonicRush;
        this.isUnlimited = builder.isUnlimited;
        this.uri = builder.uri;
        this.introUri = builder.introUri;
        this.asin = builder.asin;
        this.artworkUrl = builder.artworkUrl;
        this.videoPid = builder.videoPid;
        this.duration = builder.duration;
        this.artistAsin = builder.artistAsin;
        this.artistName = builder.artistName;
        this.artistArtworkUrl = builder.artistArtworkUrl;
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

    private ImmutableVideoStoryContent(@Nullable String str, boolean z, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, boolean z2, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Integer num, @Nullable String str7, @Nullable String str8, @Nullable String str9) {
        this.initShim = new InitShim();
        this.title = str;
        this.isFree = z;
        this.isPrime = bool;
        this.isSonicRush = bool2;
        this.isUnlimited = bool3;
        this.isExplicit = z2;
        this.uri = str2;
        this.introUri = str3;
        this.asin = str4;
        this.artworkUrl = str5;
        this.videoPid = str6;
        this.duration = num;
        this.artistAsin = str7;
        this.artistName = str8;
        this.artistArtworkUrl = str9;
        this.initShim = null;
    }

    @Generated(from = "VideoStoryContent", generator = "Immutables")
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
                this.isFree = ImmutableVideoStoryContent.super.isFree();
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
                this.isExplicit = ImmutableVideoStoryContent.super.isExplicit();
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
            return "Cannot build VideoStoryContent, attribute initializers form cycle " + arrayList;
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

    @Override // ExternalActionInterface.v1_0.VideoStoryContent
    @JsonProperty("uri")
    @Nullable
    public String uri() {
        return this.uri;
    }

    @Override // ExternalActionInterface.v1_0.VideoStoryContent
    @JsonProperty("introUri")
    @Nullable
    public String introUri() {
        return this.introUri;
    }

    @Override // ExternalActionInterface.v1_0.VideoStoryContent
    @JsonProperty("asin")
    @Nullable
    public String asin() {
        return this.asin;
    }

    @Override // ExternalActionInterface.v1_0.VideoStoryContent
    @JsonProperty("artworkUrl")
    @Nullable
    public String artworkUrl() {
        return this.artworkUrl;
    }

    @Override // ExternalActionInterface.v1_0.VideoStoryContent
    @JsonProperty("videoPid")
    @Nullable
    public String videoPid() {
        return this.videoPid;
    }

    @Override // ExternalActionInterface.v1_0.VideoStoryContent
    @JsonProperty("duration")
    @Nullable
    public Integer duration() {
        return this.duration;
    }

    @Override // ExternalActionInterface.v1_0.VideoStoryContent
    @JsonProperty("artistAsin")
    @Nullable
    public String artistAsin() {
        return this.artistAsin;
    }

    @Override // ExternalActionInterface.v1_0.VideoStoryContent
    @JsonProperty("artistName")
    @Nullable
    public String artistName() {
        return this.artistName;
    }

    @Override // ExternalActionInterface.v1_0.VideoStoryContent
    @JsonProperty("artistArtworkUrl")
    @Nullable
    public String artistArtworkUrl() {
        return this.artistArtworkUrl;
    }

    public final ImmutableVideoStoryContent withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableVideoStoryContent(str, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.uri, this.introUri, this.asin, this.artworkUrl, this.videoPid, this.duration, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withIsFree(boolean z) {
        return this.isFree == z ? this : new ImmutableVideoStoryContent(this.title, z, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.uri, this.introUri, this.asin, this.artworkUrl, this.videoPid, this.duration, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withIsPrime(@Nullable Boolean bool) {
        return equals(this.isPrime, bool) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, bool, this.isSonicRush, this.isUnlimited, this.isExplicit, this.uri, this.introUri, this.asin, this.artworkUrl, this.videoPid, this.duration, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withIsSonicRush(@Nullable Boolean bool) {
        return equals(this.isSonicRush, bool) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, bool, this.isUnlimited, this.isExplicit, this.uri, this.introUri, this.asin, this.artworkUrl, this.videoPid, this.duration, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withIsUnlimited(@Nullable Boolean bool) {
        return equals(this.isUnlimited, bool) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, this.isSonicRush, bool, this.isExplicit, this.uri, this.introUri, this.asin, this.artworkUrl, this.videoPid, this.duration, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withIsExplicit(boolean z) {
        return this.isExplicit == z ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, z, this.uri, this.introUri, this.asin, this.artworkUrl, this.videoPid, this.duration, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withUri(@Nullable String str) {
        return equals(this.uri, str) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, str, this.introUri, this.asin, this.artworkUrl, this.videoPid, this.duration, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withIntroUri(@Nullable String str) {
        return equals(this.introUri, str) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.uri, str, this.asin, this.artworkUrl, this.videoPid, this.duration, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withAsin(@Nullable String str) {
        return equals(this.asin, str) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.uri, this.introUri, str, this.artworkUrl, this.videoPid, this.duration, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withArtworkUrl(@Nullable String str) {
        return equals(this.artworkUrl, str) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.uri, this.introUri, this.asin, str, this.videoPid, this.duration, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withVideoPid(@Nullable String str) {
        return equals(this.videoPid, str) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.uri, this.introUri, this.asin, this.artworkUrl, str, this.duration, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withDuration(@Nullable Integer num) {
        return equals(this.duration, num) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.uri, this.introUri, this.asin, this.artworkUrl, this.videoPid, num, this.artistAsin, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withArtistAsin(@Nullable String str) {
        return equals(this.artistAsin, str) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.uri, this.introUri, this.asin, this.artworkUrl, this.videoPid, this.duration, str, this.artistName, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withArtistName(@Nullable String str) {
        return equals(this.artistName, str) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.uri, this.introUri, this.asin, this.artworkUrl, this.videoPid, this.duration, this.artistAsin, str, this.artistArtworkUrl);
    }

    public final ImmutableVideoStoryContent withArtistArtworkUrl(@Nullable String str) {
        return equals(this.artistArtworkUrl, str) ? this : new ImmutableVideoStoryContent(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.uri, this.introUri, this.asin, this.artworkUrl, this.videoPid, this.duration, this.artistAsin, this.artistName, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVideoStoryContent) && equalTo(0, (ImmutableVideoStoryContent) obj);
    }

    private boolean equalTo(int i, ImmutableVideoStoryContent immutableVideoStoryContent) {
        return equals(this.title, immutableVideoStoryContent.title) && this.isFree == immutableVideoStoryContent.isFree && equals(this.isPrime, immutableVideoStoryContent.isPrime) && equals(this.isSonicRush, immutableVideoStoryContent.isSonicRush) && equals(this.isUnlimited, immutableVideoStoryContent.isUnlimited) && this.isExplicit == immutableVideoStoryContent.isExplicit && equals(this.uri, immutableVideoStoryContent.uri) && equals(this.introUri, immutableVideoStoryContent.introUri) && equals(this.asin, immutableVideoStoryContent.asin) && equals(this.artworkUrl, immutableVideoStoryContent.artworkUrl) && equals(this.videoPid, immutableVideoStoryContent.videoPid) && equals(this.duration, immutableVideoStoryContent.duration) && equals(this.artistAsin, immutableVideoStoryContent.artistAsin) && equals(this.artistName, immutableVideoStoryContent.artistName) && equals(this.artistArtworkUrl, immutableVideoStoryContent.artistArtworkUrl);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.title) + 5381;
        int i = iHashCode + (iHashCode << 5) + (this.isFree ? 1231 : 1237);
        int iHashCode2 = i + (i << 5) + hashCode(this.isPrime);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.isSonicRush);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.isUnlimited);
        int i2 = iHashCode4 + (iHashCode4 << 5) + (this.isExplicit ? 1231 : 1237);
        int iHashCode5 = i2 + (i2 << 5) + hashCode(this.uri);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.introUri);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.asin);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.artworkUrl);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.videoPid);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.duration);
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + hashCode(this.artistAsin);
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + hashCode(this.artistName);
        return iHashCode12 + (iHashCode12 << 5) + hashCode(this.artistArtworkUrl);
    }

    public String toString() {
        return "VideoStoryContent{title=" + this.title + ", isFree=" + this.isFree + ", isPrime=" + this.isPrime + ", isSonicRush=" + this.isSonicRush + ", isUnlimited=" + this.isUnlimited + ", isExplicit=" + this.isExplicit + ", uri=" + this.uri + ", introUri=" + this.introUri + ", asin=" + this.asin + ", artworkUrl=" + this.artworkUrl + ", videoPid=" + this.videoPid + ", duration=" + this.duration + ", artistAsin=" + this.artistAsin + ", artistName=" + this.artistName + ", artistArtworkUrl=" + this.artistArtworkUrl + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VideoStoryContent", generator = "Immutables")
    static final class Json extends VideoStoryContent {

        @Nullable
        String artistArtworkUrl;

        @Nullable
        String artistAsin;

        @Nullable
        String artistName;

        @Nullable
        String artworkUrl;

        @Nullable
        String asin;

        @Nullable
        Integer duration;

        @Nullable
        String introUri;
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
        String title;

        @Nullable
        String uri;

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

        @JsonProperty("uri")
        public void setUri(@Nullable String str) {
            this.uri = str;
        }

        @JsonProperty("introUri")
        public void setIntroUri(@Nullable String str) {
            this.introUri = str;
        }

        @JsonProperty("asin")
        public void setAsin(@Nullable String str) {
            this.asin = str;
        }

        @JsonProperty("artworkUrl")
        public void setArtworkUrl(@Nullable String str) {
            this.artworkUrl = str;
        }

        @JsonProperty("videoPid")
        public void setVideoPid(@Nullable String str) {
            this.videoPid = str;
        }

        @JsonProperty("duration")
        public void setDuration(@Nullable Integer num) {
            this.duration = num;
        }

        @JsonProperty("artistAsin")
        public void setArtistAsin(@Nullable String str) {
            this.artistAsin = str;
        }

        @JsonProperty("artistName")
        public void setArtistName(@Nullable String str) {
            this.artistName = str;
        }

        @JsonProperty("artistArtworkUrl")
        public void setArtistArtworkUrl(@Nullable String str) {
            this.artistArtworkUrl = str;
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

        @Override // ExternalActionInterface.v1_0.VideoStoryContent
        public String uri() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.VideoStoryContent
        public String introUri() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.VideoStoryContent
        public String asin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.VideoStoryContent
        public String artworkUrl() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.VideoStoryContent
        public String videoPid() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.VideoStoryContent
        public Integer duration() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.VideoStoryContent
        public String artistAsin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.VideoStoryContent
        public String artistName() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.VideoStoryContent
        public String artistArtworkUrl() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVideoStoryContent fromJson(Json json) {
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
        String str2 = json.uri;
        if (str2 != null) {
            builder.uri(str2);
        }
        String str3 = json.introUri;
        if (str3 != null) {
            builder.introUri(str3);
        }
        String str4 = json.asin;
        if (str4 != null) {
            builder.asin(str4);
        }
        String str5 = json.artworkUrl;
        if (str5 != null) {
            builder.artworkUrl(str5);
        }
        String str6 = json.videoPid;
        if (str6 != null) {
            builder.videoPid(str6);
        }
        Integer num = json.duration;
        if (num != null) {
            builder.duration(num);
        }
        String str7 = json.artistAsin;
        if (str7 != null) {
            builder.artistAsin(str7);
        }
        String str8 = json.artistName;
        if (str8 != null) {
            builder.artistName(str8);
        }
        String str9 = json.artistArtworkUrl;
        if (str9 != null) {
            builder.artistArtworkUrl(str9);
        }
        return builder.build();
    }

    public static ImmutableVideoStoryContent copyOf(VideoStoryContent videoStoryContent) {
        if (videoStoryContent instanceof ImmutableVideoStoryContent) {
            return (ImmutableVideoStoryContent) videoStoryContent;
        }
        return builder().from(videoStoryContent).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VideoStoryContent", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_IS_EXPLICIT = 2;
        private static final long OPT_BIT_IS_FREE = 1;

        @Nullable
        private String artistArtworkUrl;

        @Nullable
        private String artistAsin;

        @Nullable
        private String artistName;

        @Nullable
        private String artworkUrl;

        @Nullable
        private String asin;

        @Nullable
        private Integer duration;

        @Nullable
        private String introUri;
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
        private String title;

        @Nullable
        private String uri;

        @Nullable
        private String videoPid;

        private Builder() {
        }

        public final Builder from(PlayableContent playableContent) {
            ImmutableVideoStoryContent.requireNonNull(playableContent, "instance");
            from((Object) playableContent);
            return this;
        }

        public final Builder from(VideoStoryContent videoStoryContent) {
            ImmutableVideoStoryContent.requireNonNull(videoStoryContent, "instance");
            from((Object) videoStoryContent);
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
            if (obj instanceof VideoStoryContent) {
                VideoStoryContent videoStoryContent = (VideoStoryContent) obj;
                String strVideoPid = videoStoryContent.videoPid();
                if (strVideoPid != null) {
                    videoPid(strVideoPid);
                }
                Integer numDuration = videoStoryContent.duration();
                if (numDuration != null) {
                    duration(numDuration);
                }
                String strIntroUri = videoStoryContent.introUri();
                if (strIntroUri != null) {
                    introUri(strIntroUri);
                }
                String strArtworkUrl = videoStoryContent.artworkUrl();
                if (strArtworkUrl != null) {
                    artworkUrl(strArtworkUrl);
                }
                String strArtistArtworkUrl = videoStoryContent.artistArtworkUrl();
                if (strArtistArtworkUrl != null) {
                    artistArtworkUrl(strArtistArtworkUrl);
                }
                String strAsin = videoStoryContent.asin();
                if (strAsin != null) {
                    asin(strAsin);
                }
                String strArtistName = videoStoryContent.artistName();
                if (strArtistName != null) {
                    artistName(strArtistName);
                }
                String strUri = videoStoryContent.uri();
                if (strUri != null) {
                    uri(strUri);
                }
                String strArtistAsin = videoStoryContent.artistAsin();
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

        @JsonProperty("uri")
        public final Builder uri(@Nullable String str) {
            this.uri = str;
            return this;
        }

        @JsonProperty("introUri")
        public final Builder introUri(@Nullable String str) {
            this.introUri = str;
            return this;
        }

        @JsonProperty("asin")
        public final Builder asin(@Nullable String str) {
            this.asin = str;
            return this;
        }

        @JsonProperty("artworkUrl")
        public final Builder artworkUrl(@Nullable String str) {
            this.artworkUrl = str;
            return this;
        }

        @JsonProperty("videoPid")
        public final Builder videoPid(@Nullable String str) {
            this.videoPid = str;
            return this;
        }

        @JsonProperty("duration")
        public final Builder duration(@Nullable Integer num) {
            this.duration = num;
            return this;
        }

        @JsonProperty("artistAsin")
        public final Builder artistAsin(@Nullable String str) {
            this.artistAsin = str;
            return this;
        }

        @JsonProperty("artistName")
        public final Builder artistName(@Nullable String str) {
            this.artistName = str;
            return this;
        }

        @JsonProperty("artistArtworkUrl")
        public final Builder artistArtworkUrl(@Nullable String str) {
            this.artistArtworkUrl = str;
            return this;
        }

        public ImmutableVideoStoryContent build() {
            return new ImmutableVideoStoryContent(this);
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
