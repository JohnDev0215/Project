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
@Generated(from = "Video", generator = "Immutables")
public final class ImmutableVideo extends Video {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String asin;

    @Nullable
    private final Integer duration;

    @Nullable
    private final String imageUrl;
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
    private final String pid;

    @Nullable
    private final String title;

    @Nullable
    private final String videoType;

    private ImmutableVideo(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.isPrime = builder.isPrime;
        this.isSonicRush = builder.isSonicRush;
        this.isUnlimited = builder.isUnlimited;
        this.asin = builder.asin;
        this.imageUrl = builder.imageUrl;
        this.videoType = builder.videoType;
        this.pid = builder.pid;
        this.duration = builder.duration;
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

    private ImmutableVideo(@Nullable String str, boolean z, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, boolean z2, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Integer num) {
        this.initShim = new InitShim();
        this.title = str;
        this.isFree = z;
        this.isPrime = bool;
        this.isSonicRush = bool2;
        this.isUnlimited = bool3;
        this.isExplicit = z2;
        this.asin = str2;
        this.imageUrl = str3;
        this.videoType = str4;
        this.pid = str5;
        this.duration = num;
        this.initShim = null;
    }

    @Generated(from = "Video", generator = "Immutables")
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
                this.isFree = ImmutableVideo.super.isFree();
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
                this.isExplicit = ImmutableVideo.super.isExplicit();
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
            return "Cannot build Video, attribute initializers form cycle " + arrayList;
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

    @Override // ExternalActionInterface.v1_0.Video
    @JsonProperty("asin")
    @Nullable
    public String asin() {
        return this.asin;
    }

    @Override // ExternalActionInterface.v1_0.Video
    @JsonProperty("imageUrl")
    @Nullable
    public String imageUrl() {
        return this.imageUrl;
    }

    @Override // ExternalActionInterface.v1_0.Video
    @JsonProperty("videoType")
    @Nullable
    public String videoType() {
        return this.videoType;
    }

    @Override // ExternalActionInterface.v1_0.Video
    @JsonProperty("pid")
    @Nullable
    public String pid() {
        return this.pid;
    }

    @Override // ExternalActionInterface.v1_0.Video
    @JsonProperty("duration")
    @Nullable
    public Integer duration() {
        return this.duration;
    }

    public final ImmutableVideo withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableVideo(str, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.imageUrl, this.videoType, this.pid, this.duration);
    }

    public final ImmutableVideo withIsFree(boolean z) {
        return this.isFree == z ? this : new ImmutableVideo(this.title, z, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.imageUrl, this.videoType, this.pid, this.duration);
    }

    public final ImmutableVideo withIsPrime(@Nullable Boolean bool) {
        return equals(this.isPrime, bool) ? this : new ImmutableVideo(this.title, this.isFree, bool, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.imageUrl, this.videoType, this.pid, this.duration);
    }

    public final ImmutableVideo withIsSonicRush(@Nullable Boolean bool) {
        return equals(this.isSonicRush, bool) ? this : new ImmutableVideo(this.title, this.isFree, this.isPrime, bool, this.isUnlimited, this.isExplicit, this.asin, this.imageUrl, this.videoType, this.pid, this.duration);
    }

    public final ImmutableVideo withIsUnlimited(@Nullable Boolean bool) {
        return equals(this.isUnlimited, bool) ? this : new ImmutableVideo(this.title, this.isFree, this.isPrime, this.isSonicRush, bool, this.isExplicit, this.asin, this.imageUrl, this.videoType, this.pid, this.duration);
    }

    public final ImmutableVideo withIsExplicit(boolean z) {
        return this.isExplicit == z ? this : new ImmutableVideo(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, z, this.asin, this.imageUrl, this.videoType, this.pid, this.duration);
    }

    public final ImmutableVideo withAsin(@Nullable String str) {
        return equals(this.asin, str) ? this : new ImmutableVideo(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, str, this.imageUrl, this.videoType, this.pid, this.duration);
    }

    public final ImmutableVideo withImageUrl(@Nullable String str) {
        return equals(this.imageUrl, str) ? this : new ImmutableVideo(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, str, this.videoType, this.pid, this.duration);
    }

    public final ImmutableVideo withVideoType(@Nullable String str) {
        return equals(this.videoType, str) ? this : new ImmutableVideo(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.imageUrl, str, this.pid, this.duration);
    }

    public final ImmutableVideo withPid(@Nullable String str) {
        return equals(this.pid, str) ? this : new ImmutableVideo(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.imageUrl, this.videoType, str, this.duration);
    }

    public final ImmutableVideo withDuration(@Nullable Integer num) {
        return equals(this.duration, num) ? this : new ImmutableVideo(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.asin, this.imageUrl, this.videoType, this.pid, num);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVideo) && equalTo(0, (ImmutableVideo) obj);
    }

    private boolean equalTo(int i, ImmutableVideo immutableVideo) {
        return equals(this.title, immutableVideo.title) && this.isFree == immutableVideo.isFree && equals(this.isPrime, immutableVideo.isPrime) && equals(this.isSonicRush, immutableVideo.isSonicRush) && equals(this.isUnlimited, immutableVideo.isUnlimited) && this.isExplicit == immutableVideo.isExplicit && equals(this.asin, immutableVideo.asin) && equals(this.imageUrl, immutableVideo.imageUrl) && equals(this.videoType, immutableVideo.videoType) && equals(this.pid, immutableVideo.pid) && equals(this.duration, immutableVideo.duration);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.title) + 5381;
        int i = iHashCode + (iHashCode << 5) + (this.isFree ? 1231 : 1237);
        int iHashCode2 = i + (i << 5) + hashCode(this.isPrime);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.isSonicRush);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.isUnlimited);
        int i2 = iHashCode4 + (iHashCode4 << 5) + (this.isExplicit ? 1231 : 1237);
        int iHashCode5 = i2 + (i2 << 5) + hashCode(this.asin);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.imageUrl);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.videoType);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.pid);
        return iHashCode8 + (iHashCode8 << 5) + hashCode(this.duration);
    }

    public String toString() {
        return "Video{title=" + this.title + ", isFree=" + this.isFree + ", isPrime=" + this.isPrime + ", isSonicRush=" + this.isSonicRush + ", isUnlimited=" + this.isUnlimited + ", isExplicit=" + this.isExplicit + ", asin=" + this.asin + ", imageUrl=" + this.imageUrl + ", videoType=" + this.videoType + ", pid=" + this.pid + ", duration=" + this.duration + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "Video", generator = "Immutables")
    static final class Json extends Video {

        @Nullable
        String asin;

        @Nullable
        Integer duration;

        @Nullable
        String imageUrl;
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
        String pid;

        @Nullable
        String title;

        @Nullable
        String videoType;

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

        @JsonProperty("imageUrl")
        public void setImageUrl(@Nullable String str) {
            this.imageUrl = str;
        }

        @JsonProperty("videoType")
        public void setVideoType(@Nullable String str) {
            this.videoType = str;
        }

        @JsonProperty("pid")
        public void setPid(@Nullable String str) {
            this.pid = str;
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

        @Override // ExternalActionInterface.v1_0.Video
        public String asin() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Video
        public String imageUrl() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Video
        public String videoType() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Video
        public String pid() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Video
        public Integer duration() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVideo fromJson(Json json) {
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
        String str3 = json.imageUrl;
        if (str3 != null) {
            builder.imageUrl(str3);
        }
        String str4 = json.videoType;
        if (str4 != null) {
            builder.videoType(str4);
        }
        String str5 = json.pid;
        if (str5 != null) {
            builder.pid(str5);
        }
        Integer num = json.duration;
        if (num != null) {
            builder.duration(num);
        }
        return builder.build();
    }

    public static ImmutableVideo copyOf(Video video) {
        if (video instanceof ImmutableVideo) {
            return (ImmutableVideo) video;
        }
        return builder().from(video).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "Video", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_IS_EXPLICIT = 2;
        private static final long OPT_BIT_IS_FREE = 1;

        @Nullable
        private String asin;

        @Nullable
        private Integer duration;

        @Nullable
        private String imageUrl;
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
        private String pid;

        @Nullable
        private String title;

        @Nullable
        private String videoType;

        private Builder() {
        }

        public final Builder from(PlayableContent playableContent) {
            ImmutableVideo.requireNonNull(playableContent, "instance");
            from((Object) playableContent);
            return this;
        }

        public final Builder from(Video video) {
            ImmutableVideo.requireNonNull(video, "instance");
            from((Object) video);
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
            if (obj instanceof Video) {
                Video video = (Video) obj;
                Integer numDuration = video.duration();
                if (numDuration != null) {
                    duration(numDuration);
                }
                String strAsin = video.asin();
                if (strAsin != null) {
                    asin(strAsin);
                }
                String strPid = video.pid();
                if (strPid != null) {
                    pid(strPid);
                }
                String strVideoType = video.videoType();
                if (strVideoType != null) {
                    videoType(strVideoType);
                }
                String strImageUrl = video.imageUrl();
                if (strImageUrl != null) {
                    imageUrl(strImageUrl);
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

        @JsonProperty("imageUrl")
        public final Builder imageUrl(@Nullable String str) {
            this.imageUrl = str;
            return this;
        }

        @JsonProperty("videoType")
        public final Builder videoType(@Nullable String str) {
            this.videoType = str;
            return this;
        }

        @JsonProperty("pid")
        public final Builder pid(@Nullable String str) {
            this.pid = str;
            return this;
        }

        @JsonProperty("duration")
        public final Builder duration(@Nullable Integer num) {
            this.duration = num;
            return this;
        }

        public ImmutableVideo build() {
            return new ImmutableVideo(this);
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
