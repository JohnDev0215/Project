package ExternalActionInterface.v1_0;

import ExternalActionInterface.v1_0.PodcastEpisode;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
@Generated(from = "PodcastEpisode", generator = "Immutables")
public final class ImmutablePodcastEpisode extends PodcastEpisode {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String episodeId;
    private final String episodeTitle;

    @Nullable
    private final String image;
    private volatile transient InitShim initShim;
    private final boolean isExplicit;
    private final boolean isFree;

    @Nullable
    private final Boolean isPrime;

    @Nullable
    private final Boolean isSonicRush;

    @Nullable
    private final Boolean isUnlimited;
    private final String podcastId;
    private final String podcastTitle;
    private final PodcastEpisode.PlaybackSource source;

    @Nullable
    private final String title;

    private ImmutablePodcastEpisode(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.isPrime = builder.isPrime;
        this.isSonicRush = builder.isSonicRush;
        this.isUnlimited = builder.isUnlimited;
        this.episodeId = builder.episodeId;
        this.image = builder.image;
        if (builder.isFreeIsSet()) {
            this.initShim.isFree(builder.isFree);
        }
        if (builder.isExplicitIsSet()) {
            this.initShim.isExplicit(builder.isExplicit);
        }
        if (builder.episodeTitle != null) {
            this.initShim.episodeTitle(builder.episodeTitle);
        }
        if (builder.podcastId != null) {
            this.initShim.podcastId(builder.podcastId);
        }
        if (builder.podcastTitle != null) {
            this.initShim.podcastTitle(builder.podcastTitle);
        }
        if (builder.source != null) {
            this.initShim.source(builder.source);
        }
        this.isFree = this.initShim.isFree();
        this.isExplicit = this.initShim.isExplicit();
        this.episodeTitle = this.initShim.episodeTitle();
        this.podcastId = this.initShim.podcastId();
        this.podcastTitle = this.initShim.podcastTitle();
        this.source = this.initShim.source();
        this.initShim = null;
    }

    private ImmutablePodcastEpisode(@Nullable String str, boolean z, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, boolean z2, String str2, String str3, String str4, String str5, @Nullable String str6, PodcastEpisode.PlaybackSource playbackSource) {
        this.initShim = new InitShim();
        this.title = str;
        this.isFree = z;
        this.isPrime = bool;
        this.isSonicRush = bool2;
        this.isUnlimited = bool3;
        this.isExplicit = z2;
        this.episodeId = str2;
        this.episodeTitle = str3;
        this.podcastId = str4;
        this.podcastTitle = str5;
        this.image = str6;
        this.source = playbackSource;
        this.initShim = null;
    }

    @Generated(from = "PodcastEpisode", generator = "Immutables")
    private final class InitShim {
        private String episodeTitle;
        private byte episodeTitleBuildStage;
        private boolean isExplicit;
        private byte isExplicitBuildStage;
        private boolean isFree;
        private byte isFreeBuildStage;
        private String podcastId;
        private byte podcastIdBuildStage;
        private String podcastTitle;
        private byte podcastTitleBuildStage;
        private PodcastEpisode.PlaybackSource source;
        private byte sourceBuildStage;

        private InitShim() {
            this.isFreeBuildStage = (byte) 0;
            this.isExplicitBuildStage = (byte) 0;
            this.episodeTitleBuildStage = (byte) 0;
            this.podcastIdBuildStage = (byte) 0;
            this.podcastTitleBuildStage = (byte) 0;
            this.sourceBuildStage = (byte) 0;
        }

        boolean isFree() {
            byte b = this.isFreeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isFreeBuildStage = (byte) -1;
                this.isFree = ImmutablePodcastEpisode.super.isFree();
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
                this.isExplicit = ImmutablePodcastEpisode.super.isExplicit();
                this.isExplicitBuildStage = (byte) 1;
            }
            return this.isExplicit;
        }

        void isExplicit(boolean z) {
            this.isExplicit = z;
            this.isExplicitBuildStage = (byte) 1;
        }

        String episodeTitle() {
            byte b = this.episodeTitleBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.episodeTitleBuildStage = (byte) -1;
                this.episodeTitle = (String) ImmutablePodcastEpisode.requireNonNull(ImmutablePodcastEpisode.super.episodeTitle(), "episodeTitle");
                this.episodeTitleBuildStage = (byte) 1;
            }
            return this.episodeTitle;
        }

        void episodeTitle(String str) {
            this.episodeTitle = str;
            this.episodeTitleBuildStage = (byte) 1;
        }

        String podcastId() {
            byte b = this.podcastIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.podcastIdBuildStage = (byte) -1;
                this.podcastId = (String) ImmutablePodcastEpisode.requireNonNull(ImmutablePodcastEpisode.super.podcastId(), "podcastId");
                this.podcastIdBuildStage = (byte) 1;
            }
            return this.podcastId;
        }

        void podcastId(String str) {
            this.podcastId = str;
            this.podcastIdBuildStage = (byte) 1;
        }

        String podcastTitle() {
            byte b = this.podcastTitleBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.podcastTitleBuildStage = (byte) -1;
                this.podcastTitle = (String) ImmutablePodcastEpisode.requireNonNull(ImmutablePodcastEpisode.super.podcastTitle(), "podcastTitle");
                this.podcastTitleBuildStage = (byte) 1;
            }
            return this.podcastTitle;
        }

        void podcastTitle(String str) {
            this.podcastTitle = str;
            this.podcastTitleBuildStage = (byte) 1;
        }

        PodcastEpisode.PlaybackSource source() {
            byte b = this.sourceBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.sourceBuildStage = (byte) -1;
                this.source = (PodcastEpisode.PlaybackSource) ImmutablePodcastEpisode.requireNonNull(ImmutablePodcastEpisode.super.source(), PageUriUtils.SOURCE_QUERY_PARAM);
                this.sourceBuildStage = (byte) 1;
            }
            return this.source;
        }

        void source(PodcastEpisode.PlaybackSource playbackSource) {
            this.source = playbackSource;
            this.sourceBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isFreeBuildStage == -1) {
                arrayList.add("isFree");
            }
            if (this.isExplicitBuildStage == -1) {
                arrayList.add(BrushV2Converter.KEY_IS_EXPLICIT);
            }
            if (this.episodeTitleBuildStage == -1) {
                arrayList.add("episodeTitle");
            }
            if (this.podcastIdBuildStage == -1) {
                arrayList.add("podcastId");
            }
            if (this.podcastTitleBuildStage == -1) {
                arrayList.add("podcastTitle");
            }
            if (this.sourceBuildStage == -1) {
                arrayList.add(PageUriUtils.SOURCE_QUERY_PARAM);
            }
            return "Cannot build PodcastEpisode, attribute initializers form cycle " + arrayList;
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

    @Override // ExternalActionInterface.v1_0.PodcastEpisode
    @JsonProperty("episodeId")
    public String episodeId() {
        return this.episodeId;
    }

    @Override // ExternalActionInterface.v1_0.PodcastEpisode
    @JsonProperty("episodeTitle")
    public String episodeTitle() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.episodeTitle();
        }
        return this.episodeTitle;
    }

    @Override // ExternalActionInterface.v1_0.PodcastEpisode
    @JsonProperty("podcastId")
    public String podcastId() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.podcastId();
        }
        return this.podcastId;
    }

    @Override // ExternalActionInterface.v1_0.PodcastEpisode
    @JsonProperty("podcastTitle")
    public String podcastTitle() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.podcastTitle();
        }
        return this.podcastTitle;
    }

    @Override // ExternalActionInterface.v1_0.PodcastEpisode
    @JsonProperty(ContextMappingConstants.IMAGE)
    @Nullable
    public String image() {
        return this.image;
    }

    @Override // ExternalActionInterface.v1_0.PodcastEpisode
    @JsonProperty(PageUriUtils.SOURCE_QUERY_PARAM)
    public PodcastEpisode.PlaybackSource source() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.source();
        }
        return this.source;
    }

    public final ImmutablePodcastEpisode withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutablePodcastEpisode(str, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.episodeId, this.episodeTitle, this.podcastId, this.podcastTitle, this.image, this.source);
    }

    public final ImmutablePodcastEpisode withIsFree(boolean z) {
        return this.isFree == z ? this : new ImmutablePodcastEpisode(this.title, z, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.episodeId, this.episodeTitle, this.podcastId, this.podcastTitle, this.image, this.source);
    }

    public final ImmutablePodcastEpisode withIsPrime(@Nullable Boolean bool) {
        return equals(this.isPrime, bool) ? this : new ImmutablePodcastEpisode(this.title, this.isFree, bool, this.isSonicRush, this.isUnlimited, this.isExplicit, this.episodeId, this.episodeTitle, this.podcastId, this.podcastTitle, this.image, this.source);
    }

    public final ImmutablePodcastEpisode withIsSonicRush(@Nullable Boolean bool) {
        return equals(this.isSonicRush, bool) ? this : new ImmutablePodcastEpisode(this.title, this.isFree, this.isPrime, bool, this.isUnlimited, this.isExplicit, this.episodeId, this.episodeTitle, this.podcastId, this.podcastTitle, this.image, this.source);
    }

    public final ImmutablePodcastEpisode withIsUnlimited(@Nullable Boolean bool) {
        return equals(this.isUnlimited, bool) ? this : new ImmutablePodcastEpisode(this.title, this.isFree, this.isPrime, this.isSonicRush, bool, this.isExplicit, this.episodeId, this.episodeTitle, this.podcastId, this.podcastTitle, this.image, this.source);
    }

    public final ImmutablePodcastEpisode withIsExplicit(boolean z) {
        return this.isExplicit == z ? this : new ImmutablePodcastEpisode(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, z, this.episodeId, this.episodeTitle, this.podcastId, this.podcastTitle, this.image, this.source);
    }

    public final ImmutablePodcastEpisode withEpisodeId(String str) {
        String str2 = (String) requireNonNull(str, "episodeId");
        return this.episodeId.equals(str2) ? this : new ImmutablePodcastEpisode(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, str2, this.episodeTitle, this.podcastId, this.podcastTitle, this.image, this.source);
    }

    public final ImmutablePodcastEpisode withEpisodeTitle(String str) {
        String str2 = (String) requireNonNull(str, "episodeTitle");
        return this.episodeTitle.equals(str2) ? this : new ImmutablePodcastEpisode(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.episodeId, str2, this.podcastId, this.podcastTitle, this.image, this.source);
    }

    public final ImmutablePodcastEpisode withPodcastId(String str) {
        String str2 = (String) requireNonNull(str, "podcastId");
        return this.podcastId.equals(str2) ? this : new ImmutablePodcastEpisode(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.episodeId, this.episodeTitle, str2, this.podcastTitle, this.image, this.source);
    }

    public final ImmutablePodcastEpisode withPodcastTitle(String str) {
        String str2 = (String) requireNonNull(str, "podcastTitle");
        return this.podcastTitle.equals(str2) ? this : new ImmutablePodcastEpisode(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.episodeId, this.episodeTitle, this.podcastId, str2, this.image, this.source);
    }

    public final ImmutablePodcastEpisode withImage(@Nullable String str) {
        return equals(this.image, str) ? this : new ImmutablePodcastEpisode(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.episodeId, this.episodeTitle, this.podcastId, this.podcastTitle, str, this.source);
    }

    public final ImmutablePodcastEpisode withSource(PodcastEpisode.PlaybackSource playbackSource) {
        PodcastEpisode.PlaybackSource playbackSource2 = (PodcastEpisode.PlaybackSource) requireNonNull(playbackSource, PageUriUtils.SOURCE_QUERY_PARAM);
        return this.source == playbackSource2 ? this : new ImmutablePodcastEpisode(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.episodeId, this.episodeTitle, this.podcastId, this.podcastTitle, this.image, playbackSource2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePodcastEpisode) && equalTo(0, (ImmutablePodcastEpisode) obj);
    }

    private boolean equalTo(int i, ImmutablePodcastEpisode immutablePodcastEpisode) {
        return equals(this.title, immutablePodcastEpisode.title) && this.isFree == immutablePodcastEpisode.isFree && equals(this.isPrime, immutablePodcastEpisode.isPrime) && equals(this.isSonicRush, immutablePodcastEpisode.isSonicRush) && equals(this.isUnlimited, immutablePodcastEpisode.isUnlimited) && this.isExplicit == immutablePodcastEpisode.isExplicit && this.episodeId.equals(immutablePodcastEpisode.episodeId) && this.episodeTitle.equals(immutablePodcastEpisode.episodeTitle) && this.podcastId.equals(immutablePodcastEpisode.podcastId) && this.podcastTitle.equals(immutablePodcastEpisode.podcastTitle) && equals(this.image, immutablePodcastEpisode.image) && this.source.equals(immutablePodcastEpisode.source);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.title) + 5381;
        int i = iHashCode + (iHashCode << 5) + (this.isFree ? 1231 : 1237);
        int iHashCode2 = i + (i << 5) + hashCode(this.isPrime);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.isSonicRush);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.isUnlimited);
        int i2 = iHashCode4 + (iHashCode4 << 5) + (this.isExplicit ? 1231 : 1237);
        int iHashCode5 = i2 + (i2 << 5) + this.episodeId.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.episodeTitle.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.podcastId.hashCode();
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + this.podcastTitle.hashCode();
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.image);
        return iHashCode9 + (iHashCode9 << 5) + this.source.hashCode();
    }

    public String toString() {
        return "PodcastEpisode{title=" + this.title + ", isFree=" + this.isFree + ", isPrime=" + this.isPrime + ", isSonicRush=" + this.isSonicRush + ", isUnlimited=" + this.isUnlimited + ", isExplicit=" + this.isExplicit + ", episodeId=" + this.episodeId + ", episodeTitle=" + this.episodeTitle + ", podcastId=" + this.podcastId + ", podcastTitle=" + this.podcastTitle + ", image=" + this.image + ", source=" + this.source + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PodcastEpisode", generator = "Immutables")
    static final class Json extends PodcastEpisode {

        @Nullable
        String episodeId;

        @Nullable
        String episodeTitle;

        @Nullable
        String image;
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
        String podcastId;

        @Nullable
        String podcastTitle;

        @Nullable
        PodcastEpisode.PlaybackSource source;

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

        @JsonProperty("episodeId")
        public void setEpisodeId(String str) {
            this.episodeId = str;
        }

        @JsonProperty("episodeTitle")
        public void setEpisodeTitle(String str) {
            this.episodeTitle = str;
        }

        @JsonProperty("podcastId")
        public void setPodcastId(String str) {
            this.podcastId = str;
        }

        @JsonProperty("podcastTitle")
        public void setPodcastTitle(String str) {
            this.podcastTitle = str;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(@Nullable String str) {
            this.image = str;
        }

        @JsonProperty(PageUriUtils.SOURCE_QUERY_PARAM)
        public void setSource(PodcastEpisode.PlaybackSource playbackSource) {
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

        @Override // ExternalActionInterface.v1_0.PodcastEpisode
        public String episodeId() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PodcastEpisode
        public String episodeTitle() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PodcastEpisode
        public String podcastId() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PodcastEpisode
        public String podcastTitle() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PodcastEpisode
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PodcastEpisode
        public PodcastEpisode.PlaybackSource source() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePodcastEpisode fromJson(Json json) {
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
        String str2 = json.episodeId;
        if (str2 != null) {
            builder.episodeId(str2);
        }
        String str3 = json.episodeTitle;
        if (str3 != null) {
            builder.episodeTitle(str3);
        }
        String str4 = json.podcastId;
        if (str4 != null) {
            builder.podcastId(str4);
        }
        String str5 = json.podcastTitle;
        if (str5 != null) {
            builder.podcastTitle(str5);
        }
        String str6 = json.image;
        if (str6 != null) {
            builder.image(str6);
        }
        PodcastEpisode.PlaybackSource playbackSource = json.source;
        if (playbackSource != null) {
            builder.source(playbackSource);
        }
        return builder.build();
    }

    public static ImmutablePodcastEpisode copyOf(PodcastEpisode podcastEpisode) {
        if (podcastEpisode instanceof ImmutablePodcastEpisode) {
            return (ImmutablePodcastEpisode) podcastEpisode;
        }
        return builder().from(podcastEpisode).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PodcastEpisode", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_EPISODE_ID = 1;
        private static final long OPT_BIT_IS_EXPLICIT = 2;
        private static final long OPT_BIT_IS_FREE = 1;

        @Nullable
        private String episodeId;

        @Nullable
        private String episodeTitle;

        @Nullable
        private String image;
        private long initBits;
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
        private String podcastId;

        @Nullable
        private String podcastTitle;

        @Nullable
        private PodcastEpisode.PlaybackSource source;

        @Nullable
        private String title;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(PlayableContent playableContent) {
            ImmutablePodcastEpisode.requireNonNull(playableContent, "instance");
            from((Object) playableContent);
            return this;
        }

        public final Builder from(PodcastEpisode podcastEpisode) {
            ImmutablePodcastEpisode.requireNonNull(podcastEpisode, "instance");
            from((Object) podcastEpisode);
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
            if (obj instanceof PodcastEpisode) {
                PodcastEpisode podcastEpisode = (PodcastEpisode) obj;
                episodeTitle(podcastEpisode.episodeTitle());
                podcastId(podcastEpisode.podcastId());
                String strImage = podcastEpisode.image();
                if (strImage != null) {
                    image(strImage);
                }
                source(podcastEpisode.source());
                episodeId(podcastEpisode.episodeId());
                podcastTitle(podcastEpisode.podcastTitle());
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

        @JsonProperty("episodeId")
        public final Builder episodeId(String str) {
            this.episodeId = (String) ImmutablePodcastEpisode.requireNonNull(str, "episodeId");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("episodeTitle")
        public final Builder episodeTitle(String str) {
            this.episodeTitle = (String) ImmutablePodcastEpisode.requireNonNull(str, "episodeTitle");
            return this;
        }

        @JsonProperty("podcastId")
        public final Builder podcastId(String str) {
            this.podcastId = (String) ImmutablePodcastEpisode.requireNonNull(str, "podcastId");
            return this;
        }

        @JsonProperty("podcastTitle")
        public final Builder podcastTitle(String str) {
            this.podcastTitle = (String) ImmutablePodcastEpisode.requireNonNull(str, "podcastTitle");
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(@Nullable String str) {
            this.image = str;
            return this;
        }

        @JsonProperty(PageUriUtils.SOURCE_QUERY_PARAM)
        public final Builder source(PodcastEpisode.PlaybackSource playbackSource) {
            this.source = (PodcastEpisode.PlaybackSource) ImmutablePodcastEpisode.requireNonNull(playbackSource, PageUriUtils.SOURCE_QUERY_PARAM);
            return this;
        }

        public ImmutablePodcastEpisode build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePodcastEpisode(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isFreeIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isExplicitIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("episodeId");
            }
            return "Cannot build PodcastEpisode, some of required attributes are not set " + arrayList;
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
