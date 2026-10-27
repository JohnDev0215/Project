package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
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
@Generated(from = "MediaMetadataElement", generator = "Immutables")
public final class ImmutableMediaMetadataElement extends MediaMetadataElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String albumName;
    private final String artistName;
    private final String artwork;

    @Nullable
    private final Integer durationSeconds;
    private volatile transient InitShim initShim;
    private final String logo;
    private final String mediaId;
    private final List<Method> onBackgroundImageRequired;
    private final List<Method> onLyricsRequired;
    private final List<Method> onMediaViewed;

    @Nullable
    private final String subTitle;

    @Nullable
    private final String subTitleContainerType;
    private final String title;

    private ImmutableMediaMetadataElement(Builder builder) {
        this.initShim = new InitShim();
        this.mediaId = builder.mediaId;
        this.title = builder.title;
        this.artwork = builder.artwork;
        this.logo = builder.logo;
        this.durationSeconds = builder.durationSeconds;
        this.subTitle = builder.subTitle;
        this.subTitleContainerType = builder.subTitleContainerType;
        if (builder.artistName != null) {
            this.initShim.artistName(builder.artistName);
        }
        if (builder.albumName != null) {
            this.initShim.albumName(builder.albumName);
        }
        if (builder.onBackgroundImageRequiredIsSet()) {
            this.initShim.onBackgroundImageRequired(createUnmodifiableList(true, builder.onBackgroundImageRequired));
        }
        if (builder.onLyricsRequiredIsSet()) {
            this.initShim.onLyricsRequired(createUnmodifiableList(true, builder.onLyricsRequired));
        }
        if (builder.onMediaViewedIsSet()) {
            this.initShim.onMediaViewed(createUnmodifiableList(true, builder.onMediaViewed));
        }
        this.artistName = this.initShim.artistName();
        this.albumName = this.initShim.albumName();
        this.onBackgroundImageRequired = this.initShim.onBackgroundImageRequired();
        this.onLyricsRequired = this.initShim.onLyricsRequired();
        this.onMediaViewed = this.initShim.onMediaViewed();
        this.initShim = null;
    }

    private ImmutableMediaMetadataElement(String str, String str2, String str3, String str4, @Nullable Integer num, @Nullable String str5, @Nullable String str6, String str7, String str8, List<Method> list, List<Method> list2, List<Method> list3) {
        this.initShim = new InitShim();
        this.mediaId = str;
        this.title = str2;
        this.artwork = str3;
        this.logo = str4;
        this.durationSeconds = num;
        this.subTitle = str5;
        this.subTitleContainerType = str6;
        this.artistName = str7;
        this.albumName = str8;
        this.onBackgroundImageRequired = list;
        this.onLyricsRequired = list2;
        this.onMediaViewed = list3;
        this.initShim = null;
    }

    @Generated(from = "MediaMetadataElement", generator = "Immutables")
    private final class InitShim {
        private String albumName;
        private byte albumNameBuildStage;
        private String artistName;
        private byte artistNameBuildStage;
        private List<Method> onBackgroundImageRequired;
        private byte onBackgroundImageRequiredBuildStage;
        private List<Method> onLyricsRequired;
        private byte onLyricsRequiredBuildStage;
        private List<Method> onMediaViewed;
        private byte onMediaViewedBuildStage;

        private InitShim() {
            this.artistNameBuildStage = (byte) 0;
            this.albumNameBuildStage = (byte) 0;
            this.onBackgroundImageRequiredBuildStage = (byte) 0;
            this.onLyricsRequiredBuildStage = (byte) 0;
            this.onMediaViewedBuildStage = (byte) 0;
        }

        String artistName() {
            byte b = this.artistNameBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.artistNameBuildStage = (byte) -1;
                this.artistName = (String) ImmutableMediaMetadataElement.requireNonNull(ImmutableMediaMetadataElement.super.artistName(), "artistName");
                this.artistNameBuildStage = (byte) 1;
            }
            return this.artistName;
        }

        void artistName(String str) {
            this.artistName = str;
            this.artistNameBuildStage = (byte) 1;
        }

        String albumName() {
            byte b = this.albumNameBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.albumNameBuildStage = (byte) -1;
                this.albumName = (String) ImmutableMediaMetadataElement.requireNonNull(ImmutableMediaMetadataElement.super.albumName(), "albumName");
                this.albumNameBuildStage = (byte) 1;
            }
            return this.albumName;
        }

        void albumName(String str) {
            this.albumName = str;
            this.albumNameBuildStage = (byte) 1;
        }

        List<Method> onBackgroundImageRequired() {
            byte b = this.onBackgroundImageRequiredBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onBackgroundImageRequiredBuildStage = (byte) -1;
                this.onBackgroundImageRequired = ImmutableMediaMetadataElement.createUnmodifiableList(false, ImmutableMediaMetadataElement.createSafeList(ImmutableMediaMetadataElement.super.onBackgroundImageRequired(), true, false));
                this.onBackgroundImageRequiredBuildStage = (byte) 1;
            }
            return this.onBackgroundImageRequired;
        }

        void onBackgroundImageRequired(List<Method> list) {
            this.onBackgroundImageRequired = list;
            this.onBackgroundImageRequiredBuildStage = (byte) 1;
        }

        List<Method> onLyricsRequired() {
            byte b = this.onLyricsRequiredBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onLyricsRequiredBuildStage = (byte) -1;
                this.onLyricsRequired = ImmutableMediaMetadataElement.createUnmodifiableList(false, ImmutableMediaMetadataElement.createSafeList(ImmutableMediaMetadataElement.super.onLyricsRequired(), true, false));
                this.onLyricsRequiredBuildStage = (byte) 1;
            }
            return this.onLyricsRequired;
        }

        void onLyricsRequired(List<Method> list) {
            this.onLyricsRequired = list;
            this.onLyricsRequiredBuildStage = (byte) 1;
        }

        List<Method> onMediaViewed() {
            byte b = this.onMediaViewedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onMediaViewedBuildStage = (byte) -1;
                this.onMediaViewed = ImmutableMediaMetadataElement.createUnmodifiableList(false, ImmutableMediaMetadataElement.createSafeList(ImmutableMediaMetadataElement.super.onMediaViewed(), true, false));
                this.onMediaViewedBuildStage = (byte) 1;
            }
            return this.onMediaViewed;
        }

        void onMediaViewed(List<Method> list) {
            this.onMediaViewed = list;
            this.onMediaViewedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.artistNameBuildStage == -1) {
                arrayList.add("artistName");
            }
            if (this.albumNameBuildStage == -1) {
                arrayList.add("albumName");
            }
            if (this.onBackgroundImageRequiredBuildStage == -1) {
                arrayList.add("onBackgroundImageRequired");
            }
            if (this.onLyricsRequiredBuildStage == -1) {
                arrayList.add("onLyricsRequired");
            }
            if (this.onMediaViewedBuildStage == -1) {
                arrayList.add("onMediaViewed");
            }
            return "Cannot build MediaMetadataElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("mediaId")
    public String mediaId() {
        return this.mediaId;
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("title")
    public String title() {
        return this.title;
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("artwork")
    public String artwork() {
        return this.artwork;
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("logo")
    public String logo() {
        return this.logo;
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("durationSeconds")
    @Nullable
    public Integer durationSeconds() {
        return this.durationSeconds;
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("subTitle")
    @Nullable
    public String subTitle() {
        return this.subTitle;
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("subTitleContainerType")
    @Nullable
    public String subTitleContainerType() {
        return this.subTitleContainerType;
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("artistName")
    public String artistName() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.artistName();
        }
        return this.artistName;
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("albumName")
    public String albumName() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.albumName();
        }
        return this.albumName;
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("onBackgroundImageRequired")
    public List<Method> onBackgroundImageRequired() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onBackgroundImageRequired();
        }
        return this.onBackgroundImageRequired;
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("onLyricsRequired")
    public List<Method> onLyricsRequired() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onLyricsRequired();
        }
        return this.onLyricsRequired;
    }

    @Override // PlaybackInterface.v1_0.MediaMetadataElement
    @JsonProperty("onMediaViewed")
    public List<Method> onMediaViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onMediaViewed();
        }
        return this.onMediaViewed;
    }

    public final ImmutableMediaMetadataElement withMediaId(String str) {
        String str2 = (String) requireNonNull(str, "mediaId");
        return this.mediaId.equals(str2) ? this : new ImmutableMediaMetadataElement(str2, this.title, this.artwork, this.logo, this.durationSeconds, this.subTitle, this.subTitleContainerType, this.artistName, this.albumName, this.onBackgroundImageRequired, this.onLyricsRequired, this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withTitle(String str) {
        String str2 = (String) requireNonNull(str, "title");
        return this.title.equals(str2) ? this : new ImmutableMediaMetadataElement(this.mediaId, str2, this.artwork, this.logo, this.durationSeconds, this.subTitle, this.subTitleContainerType, this.artistName, this.albumName, this.onBackgroundImageRequired, this.onLyricsRequired, this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withArtwork(String str) {
        String str2 = (String) requireNonNull(str, "artwork");
        return this.artwork.equals(str2) ? this : new ImmutableMediaMetadataElement(this.mediaId, this.title, str2, this.logo, this.durationSeconds, this.subTitle, this.subTitleContainerType, this.artistName, this.albumName, this.onBackgroundImageRequired, this.onLyricsRequired, this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withLogo(String str) {
        String str2 = (String) requireNonNull(str, "logo");
        return this.logo.equals(str2) ? this : new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, str2, this.durationSeconds, this.subTitle, this.subTitleContainerType, this.artistName, this.albumName, this.onBackgroundImageRequired, this.onLyricsRequired, this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withDurationSeconds(@Nullable Integer num) {
        return equals(this.durationSeconds, num) ? this : new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, this.logo, num, this.subTitle, this.subTitleContainerType, this.artistName, this.albumName, this.onBackgroundImageRequired, this.onLyricsRequired, this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withSubTitle(@Nullable String str) {
        return equals(this.subTitle, str) ? this : new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, this.logo, this.durationSeconds, str, this.subTitleContainerType, this.artistName, this.albumName, this.onBackgroundImageRequired, this.onLyricsRequired, this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withSubTitleContainerType(@Nullable String str) {
        return equals(this.subTitleContainerType, str) ? this : new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, this.logo, this.durationSeconds, this.subTitle, str, this.artistName, this.albumName, this.onBackgroundImageRequired, this.onLyricsRequired, this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withArtistName(String str) {
        String str2 = (String) requireNonNull(str, "artistName");
        return this.artistName.equals(str2) ? this : new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, this.logo, this.durationSeconds, this.subTitle, this.subTitleContainerType, str2, this.albumName, this.onBackgroundImageRequired, this.onLyricsRequired, this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withAlbumName(String str) {
        String str2 = (String) requireNonNull(str, "albumName");
        return this.albumName.equals(str2) ? this : new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, this.logo, this.durationSeconds, this.subTitle, this.subTitleContainerType, this.artistName, str2, this.onBackgroundImageRequired, this.onLyricsRequired, this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withOnBackgroundImageRequired(Method... methodArr) {
        return new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, this.logo, this.durationSeconds, this.subTitle, this.subTitleContainerType, this.artistName, this.albumName, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onLyricsRequired, this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withOnBackgroundImageRequired(Iterable<? extends Method> iterable) {
        if (this.onBackgroundImageRequired == iterable) {
            return this;
        }
        return new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, this.logo, this.durationSeconds, this.subTitle, this.subTitleContainerType, this.artistName, this.albumName, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onLyricsRequired, this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withOnLyricsRequired(Method... methodArr) {
        return new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, this.logo, this.durationSeconds, this.subTitle, this.subTitleContainerType, this.artistName, this.albumName, this.onBackgroundImageRequired, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withOnLyricsRequired(Iterable<? extends Method> iterable) {
        if (this.onLyricsRequired == iterable) {
            return this;
        }
        return new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, this.logo, this.durationSeconds, this.subTitle, this.subTitleContainerType, this.artistName, this.albumName, this.onBackgroundImageRequired, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onMediaViewed);
    }

    public final ImmutableMediaMetadataElement withOnMediaViewed(Method... methodArr) {
        return new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, this.logo, this.durationSeconds, this.subTitle, this.subTitleContainerType, this.artistName, this.albumName, this.onBackgroundImageRequired, this.onLyricsRequired, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableMediaMetadataElement withOnMediaViewed(Iterable<? extends Method> iterable) {
        if (this.onMediaViewed == iterable) {
            return this;
        }
        return new ImmutableMediaMetadataElement(this.mediaId, this.title, this.artwork, this.logo, this.durationSeconds, this.subTitle, this.subTitleContainerType, this.artistName, this.albumName, this.onBackgroundImageRequired, this.onLyricsRequired, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMediaMetadataElement) && equalTo(0, (ImmutableMediaMetadataElement) obj);
    }

    private boolean equalTo(int i, ImmutableMediaMetadataElement immutableMediaMetadataElement) {
        return this.mediaId.equals(immutableMediaMetadataElement.mediaId) && this.title.equals(immutableMediaMetadataElement.title) && this.artwork.equals(immutableMediaMetadataElement.artwork) && this.logo.equals(immutableMediaMetadataElement.logo) && equals(this.durationSeconds, immutableMediaMetadataElement.durationSeconds) && equals(this.subTitle, immutableMediaMetadataElement.subTitle) && equals(this.subTitleContainerType, immutableMediaMetadataElement.subTitleContainerType) && this.artistName.equals(immutableMediaMetadataElement.artistName) && this.albumName.equals(immutableMediaMetadataElement.albumName) && this.onBackgroundImageRequired.equals(immutableMediaMetadataElement.onBackgroundImageRequired) && this.onLyricsRequired.equals(immutableMediaMetadataElement.onLyricsRequired) && this.onMediaViewed.equals(immutableMediaMetadataElement.onMediaViewed);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.mediaId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.title.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.artwork.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.logo.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.durationSeconds);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.subTitle);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.subTitleContainerType);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + this.artistName.hashCode();
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + this.albumName.hashCode();
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + this.onBackgroundImageRequired.hashCode();
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + this.onLyricsRequired.hashCode();
        return iHashCode11 + (iHashCode11 << 5) + this.onMediaViewed.hashCode();
    }

    public String toString() {
        return "MediaMetadataElement{mediaId=" + this.mediaId + ", title=" + this.title + ", artwork=" + this.artwork + ", logo=" + this.logo + ", durationSeconds=" + this.durationSeconds + ", subTitle=" + this.subTitle + ", subTitleContainerType=" + this.subTitleContainerType + ", artistName=" + this.artistName + ", albumName=" + this.albumName + ", onBackgroundImageRequired=" + this.onBackgroundImageRequired + ", onLyricsRequired=" + this.onLyricsRequired + ", onMediaViewed=" + this.onMediaViewed + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MediaMetadataElement", generator = "Immutables")
    static final class Json extends MediaMetadataElement {

        @Nullable
        String albumName;

        @Nullable
        String artistName;

        @Nullable
        String artwork;

        @Nullable
        Integer durationSeconds;

        @Nullable
        String logo;

        @Nullable
        String mediaId;
        boolean onBackgroundImageRequiredIsSet;
        boolean onLyricsRequiredIsSet;
        boolean onMediaViewedIsSet;

        @Nullable
        String subTitle;

        @Nullable
        String subTitleContainerType;

        @Nullable
        String title;

        @Nullable
        List<Method> onBackgroundImageRequired = Collections.emptyList();

        @Nullable
        List<Method> onLyricsRequired = Collections.emptyList();

        @Nullable
        List<Method> onMediaViewed = Collections.emptyList();

        Json() {
        }

        @JsonProperty("mediaId")
        public void setMediaId(String str) {
            this.mediaId = str;
        }

        @JsonProperty("title")
        public void setTitle(String str) {
            this.title = str;
        }

        @JsonProperty("artwork")
        public void setArtwork(String str) {
            this.artwork = str;
        }

        @JsonProperty("logo")
        public void setLogo(String str) {
            this.logo = str;
        }

        @JsonProperty("durationSeconds")
        public void setDurationSeconds(@Nullable Integer num) {
            this.durationSeconds = num;
        }

        @JsonProperty("subTitle")
        public void setSubTitle(@Nullable String str) {
            this.subTitle = str;
        }

        @JsonProperty("subTitleContainerType")
        public void setSubTitleContainerType(@Nullable String str) {
            this.subTitleContainerType = str;
        }

        @JsonProperty("artistName")
        public void setArtistName(String str) {
            this.artistName = str;
        }

        @JsonProperty("albumName")
        public void setAlbumName(String str) {
            this.albumName = str;
        }

        @JsonProperty("onBackgroundImageRequired")
        public void setOnBackgroundImageRequired(List<Method> list) {
            this.onBackgroundImageRequired = list;
            this.onBackgroundImageRequiredIsSet = true;
        }

        @JsonProperty("onLyricsRequired")
        public void setOnLyricsRequired(List<Method> list) {
            this.onLyricsRequired = list;
            this.onLyricsRequiredIsSet = true;
        }

        @JsonProperty("onMediaViewed")
        public void setOnMediaViewed(List<Method> list) {
            this.onMediaViewed = list;
            this.onMediaViewedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public String mediaId() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public String artwork() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public String logo() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public Integer durationSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public String subTitle() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public String subTitleContainerType() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public String artistName() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public String albumName() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public List<Method> onBackgroundImageRequired() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public List<Method> onLyricsRequired() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaMetadataElement
        public List<Method> onMediaViewed() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMediaMetadataElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.mediaId;
        if (str != null) {
            builder.mediaId(str);
        }
        String str2 = json.title;
        if (str2 != null) {
            builder.title(str2);
        }
        String str3 = json.artwork;
        if (str3 != null) {
            builder.artwork(str3);
        }
        String str4 = json.logo;
        if (str4 != null) {
            builder.logo(str4);
        }
        Integer num = json.durationSeconds;
        if (num != null) {
            builder.durationSeconds(num);
        }
        String str5 = json.subTitle;
        if (str5 != null) {
            builder.subTitle(str5);
        }
        String str6 = json.subTitleContainerType;
        if (str6 != null) {
            builder.subTitleContainerType(str6);
        }
        String str7 = json.artistName;
        if (str7 != null) {
            builder.artistName(str7);
        }
        String str8 = json.albumName;
        if (str8 != null) {
            builder.albumName(str8);
        }
        if (json.onBackgroundImageRequiredIsSet) {
            builder.addAllOnBackgroundImageRequired(json.onBackgroundImageRequired);
        }
        if (json.onLyricsRequiredIsSet) {
            builder.addAllOnLyricsRequired(json.onLyricsRequired);
        }
        if (json.onMediaViewedIsSet) {
            builder.addAllOnMediaViewed(json.onMediaViewed);
        }
        return builder.build();
    }

    public static ImmutableMediaMetadataElement copyOf(MediaMetadataElement mediaMetadataElement) {
        if (mediaMetadataElement instanceof ImmutableMediaMetadataElement) {
            return (ImmutableMediaMetadataElement) mediaMetadataElement;
        }
        return builder().from(mediaMetadataElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MediaMetadataElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ARTWORK = 4;
        private static final long INIT_BIT_LOGO = 8;
        private static final long INIT_BIT_MEDIA_ID = 1;
        private static final long INIT_BIT_TITLE = 2;
        private static final long OPT_BIT_ON_BACKGROUND_IMAGE_REQUIRED = 1;
        private static final long OPT_BIT_ON_LYRICS_REQUIRED = 2;
        private static final long OPT_BIT_ON_MEDIA_VIEWED = 4;

        @Nullable
        private String albumName;

        @Nullable
        private String artistName;

        @Nullable
        private String artwork;

        @Nullable
        private Integer durationSeconds;
        private long initBits;

        @Nullable
        private String logo;

        @Nullable
        private String mediaId;
        private List<Method> onBackgroundImageRequired;
        private List<Method> onLyricsRequired;
        private List<Method> onMediaViewed;
        private long optBits;

        @Nullable
        private String subTitle;

        @Nullable
        private String subTitleContainerType;

        @Nullable
        private String title;

        private Builder() {
            this.initBits = 15L;
            this.onBackgroundImageRequired = new ArrayList();
            this.onLyricsRequired = new ArrayList();
            this.onMediaViewed = new ArrayList();
        }

        public final Builder from(MediaMetadataElement mediaMetadataElement) {
            ImmutableMediaMetadataElement.requireNonNull(mediaMetadataElement, "instance");
            mediaId(mediaMetadataElement.mediaId());
            title(mediaMetadataElement.title());
            artwork(mediaMetadataElement.artwork());
            logo(mediaMetadataElement.logo());
            Integer numDurationSeconds = mediaMetadataElement.durationSeconds();
            if (numDurationSeconds != null) {
                durationSeconds(numDurationSeconds);
            }
            String strSubTitle = mediaMetadataElement.subTitle();
            if (strSubTitle != null) {
                subTitle(strSubTitle);
            }
            String strSubTitleContainerType = mediaMetadataElement.subTitleContainerType();
            if (strSubTitleContainerType != null) {
                subTitleContainerType(strSubTitleContainerType);
            }
            artistName(mediaMetadataElement.artistName());
            albumName(mediaMetadataElement.albumName());
            addAllOnBackgroundImageRequired(mediaMetadataElement.onBackgroundImageRequired());
            addAllOnLyricsRequired(mediaMetadataElement.onLyricsRequired());
            addAllOnMediaViewed(mediaMetadataElement.onMediaViewed());
            return this;
        }

        @JsonProperty("mediaId")
        public final Builder mediaId(String str) {
            this.mediaId = (String) ImmutableMediaMetadataElement.requireNonNull(str, "mediaId");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("title")
        public final Builder title(String str) {
            this.title = (String) ImmutableMediaMetadataElement.requireNonNull(str, "title");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("artwork")
        public final Builder artwork(String str) {
            this.artwork = (String) ImmutableMediaMetadataElement.requireNonNull(str, "artwork");
            this.initBits &= -5;
            return this;
        }

        @JsonProperty("logo")
        public final Builder logo(String str) {
            this.logo = (String) ImmutableMediaMetadataElement.requireNonNull(str, "logo");
            this.initBits &= -9;
            return this;
        }

        @JsonProperty("durationSeconds")
        public final Builder durationSeconds(@Nullable Integer num) {
            this.durationSeconds = num;
            return this;
        }

        @JsonProperty("subTitle")
        public final Builder subTitle(@Nullable String str) {
            this.subTitle = str;
            return this;
        }

        @JsonProperty("subTitleContainerType")
        public final Builder subTitleContainerType(@Nullable String str) {
            this.subTitleContainerType = str;
            return this;
        }

        @JsonProperty("artistName")
        public final Builder artistName(String str) {
            this.artistName = (String) ImmutableMediaMetadataElement.requireNonNull(str, "artistName");
            return this;
        }

        @JsonProperty("albumName")
        public final Builder albumName(String str) {
            this.albumName = (String) ImmutableMediaMetadataElement.requireNonNull(str, "albumName");
            return this;
        }

        public final Builder addOnBackgroundImageRequired(Method method) {
            this.onBackgroundImageRequired.add((Method) ImmutableMediaMetadataElement.requireNonNull(method, "onBackgroundImageRequired element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBackgroundImageRequired(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBackgroundImageRequired.add((Method) ImmutableMediaMetadataElement.requireNonNull(method, "onBackgroundImageRequired element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onBackgroundImageRequired")
        public final Builder onBackgroundImageRequired(Iterable<? extends Method> iterable) {
            this.onBackgroundImageRequired.clear();
            return addAllOnBackgroundImageRequired(iterable);
        }

        public final Builder addAllOnBackgroundImageRequired(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onBackgroundImageRequired.add((Method) ImmutableMediaMetadataElement.requireNonNull(it.next(), "onBackgroundImageRequired element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnLyricsRequired(Method method) {
            this.onLyricsRequired.add((Method) ImmutableMediaMetadataElement.requireNonNull(method, "onLyricsRequired element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnLyricsRequired(Method... methodArr) {
            for (Method method : methodArr) {
                this.onLyricsRequired.add((Method) ImmutableMediaMetadataElement.requireNonNull(method, "onLyricsRequired element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onLyricsRequired")
        public final Builder onLyricsRequired(Iterable<? extends Method> iterable) {
            this.onLyricsRequired.clear();
            return addAllOnLyricsRequired(iterable);
        }

        public final Builder addAllOnLyricsRequired(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onLyricsRequired.add((Method) ImmutableMediaMetadataElement.requireNonNull(it.next(), "onLyricsRequired element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnMediaViewed(Method method) {
            this.onMediaViewed.add((Method) ImmutableMediaMetadataElement.requireNonNull(method, "onMediaViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnMediaViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onMediaViewed.add((Method) ImmutableMediaMetadataElement.requireNonNull(method, "onMediaViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("onMediaViewed")
        public final Builder onMediaViewed(Iterable<? extends Method> iterable) {
            this.onMediaViewed.clear();
            return addAllOnMediaViewed(iterable);
        }

        public final Builder addAllOnMediaViewed(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onMediaViewed.add((Method) ImmutableMediaMetadataElement.requireNonNull(it.next(), "onMediaViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        public ImmutableMediaMetadataElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableMediaMetadataElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onBackgroundImageRequiredIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onLyricsRequiredIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onMediaViewedIsSet() {
            return (this.optBits & 4) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("mediaId");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("title");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("artwork");
            }
            if ((this.initBits & 8) != 0) {
                arrayList.add("logo");
            }
            return "Cannot build MediaMetadataElement, some of required attributes are not set " + arrayList;
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

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
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
