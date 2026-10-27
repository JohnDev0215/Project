package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
@Generated(from = "LiveStreamBarkerElement", generator = "Immutables")
public final class ImmutableLiveStreamBarkerElement extends LiveStreamBarkerElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String avatar;
    private volatile transient InitShim initShim;
    private final List<Method> onItemSelected;
    private final List<Method> onViewed;
    private final String primaryText;
    private final String secondaryText;
    private final String state;

    @Nullable
    private final String tertiaryText;
    private final String thumbnailImage;
    private final String viewers;
    private final String widgetId;
    private final String wordmark;

    private ImmutableLiveStreamBarkerElement(Builder builder) {
        this.initShim = new InitShim();
        this.thumbnailImage = builder.thumbnailImage;
        this.avatar = builder.avatar;
        this.wordmark = builder.wordmark;
        this.state = builder.state;
        this.viewers = builder.viewers;
        this.primaryText = builder.primaryText;
        this.secondaryText = builder.secondaryText;
        this.tertiaryText = builder.tertiaryText;
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.onItemSelectedIsSet()) {
            this.initShim.onItemSelected(createUnmodifiableList(true, builder.onItemSelected));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.onItemSelected = this.initShim.onItemSelected();
        this.initShim = null;
    }

    private ImmutableLiveStreamBarkerElement(String str, List<Method> list, String str2, String str3, String str4, String str5, String str6, String str7, String str8, @Nullable String str9, List<Method> list2) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.thumbnailImage = str2;
        this.avatar = str3;
        this.wordmark = str4;
        this.state = str5;
        this.viewers = str6;
        this.primaryText = str7;
        this.secondaryText = str8;
        this.tertiaryText = str9;
        this.onItemSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "LiveStreamBarkerElement", generator = "Immutables")
    private final class InitShim {
        private List<Method> onItemSelected;
        private byte onItemSelectedBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.onItemSelectedBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableLiveStreamBarkerElement.requireNonNull(ImmutableLiveStreamBarkerElement.super.widgetId(), "widgetId");
                this.widgetIdBuildStage = (byte) 1;
            }
            return this.widgetId;
        }

        void widgetId(String str) {
            this.widgetId = str;
            this.widgetIdBuildStage = (byte) 1;
        }

        List<Method> onViewed() {
            byte b = this.onViewedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onViewedBuildStage = (byte) -1;
                this.onViewed = ImmutableLiveStreamBarkerElement.createUnmodifiableList(false, ImmutableLiveStreamBarkerElement.createSafeList(ImmutableLiveStreamBarkerElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        List<Method> onItemSelected() {
            byte b = this.onItemSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onItemSelectedBuildStage = (byte) -1;
                this.onItemSelected = ImmutableLiveStreamBarkerElement.createUnmodifiableList(false, ImmutableLiveStreamBarkerElement.createSafeList(ImmutableLiveStreamBarkerElement.super.onItemSelected(), true, false));
                this.onItemSelectedBuildStage = (byte) 1;
            }
            return this.onItemSelected;
        }

        void onItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.onItemSelectedBuildStage == -1) {
                arrayList.add("onItemSelected");
            }
            return "Cannot build LiveStreamBarkerElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // Touch.WidgetsInterface.v1_0.WidgetElement
    @JsonProperty("widgetId")
    public String widgetId() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.widgetId();
        }
        return this.widgetId;
    }

    @Override // Touch.WidgetsInterface.v1_0.WidgetElement
    @JsonProperty("onViewed")
    public List<Method> onViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onViewed();
        }
        return this.onViewed;
    }

    @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
    @JsonProperty("thumbnailImage")
    public String thumbnailImage() {
        return this.thumbnailImage;
    }

    @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
    @JsonProperty("avatar")
    public String avatar() {
        return this.avatar;
    }

    @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
    @JsonProperty("wordmark")
    public String wordmark() {
        return this.wordmark;
    }

    @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
    @JsonProperty("state")
    public String state() {
        return this.state;
    }

    @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
    @JsonProperty("viewers")
    public String viewers() {
        return this.viewers;
    }

    @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
    @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
    @Nullable
    public String tertiaryText() {
        return this.tertiaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onItemSelected();
        }
        return this.onItemSelected;
    }

    public final ImmutableLiveStreamBarkerElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableLiveStreamBarkerElement(str2, this.onViewed, this.thumbnailImage, this.avatar, this.wordmark, this.state, this.viewers, this.primaryText, this.secondaryText, this.tertiaryText, this.onItemSelected);
    }

    public final ImmutableLiveStreamBarkerElement withOnViewed(Method... methodArr) {
        return new ImmutableLiveStreamBarkerElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.thumbnailImage, this.avatar, this.wordmark, this.state, this.viewers, this.primaryText, this.secondaryText, this.tertiaryText, this.onItemSelected);
    }

    public final ImmutableLiveStreamBarkerElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableLiveStreamBarkerElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.thumbnailImage, this.avatar, this.wordmark, this.state, this.viewers, this.primaryText, this.secondaryText, this.tertiaryText, this.onItemSelected);
    }

    public final ImmutableLiveStreamBarkerElement withThumbnailImage(String str) {
        String str2 = (String) requireNonNull(str, "thumbnailImage");
        return this.thumbnailImage.equals(str2) ? this : new ImmutableLiveStreamBarkerElement(this.widgetId, this.onViewed, str2, this.avatar, this.wordmark, this.state, this.viewers, this.primaryText, this.secondaryText, this.tertiaryText, this.onItemSelected);
    }

    public final ImmutableLiveStreamBarkerElement withAvatar(String str) {
        String str2 = (String) requireNonNull(str, "avatar");
        return this.avatar.equals(str2) ? this : new ImmutableLiveStreamBarkerElement(this.widgetId, this.onViewed, this.thumbnailImage, str2, this.wordmark, this.state, this.viewers, this.primaryText, this.secondaryText, this.tertiaryText, this.onItemSelected);
    }

    public final ImmutableLiveStreamBarkerElement withWordmark(String str) {
        String str2 = (String) requireNonNull(str, "wordmark");
        return this.wordmark.equals(str2) ? this : new ImmutableLiveStreamBarkerElement(this.widgetId, this.onViewed, this.thumbnailImage, this.avatar, str2, this.state, this.viewers, this.primaryText, this.secondaryText, this.tertiaryText, this.onItemSelected);
    }

    public final ImmutableLiveStreamBarkerElement withState(String str) {
        String str2 = (String) requireNonNull(str, "state");
        return this.state.equals(str2) ? this : new ImmutableLiveStreamBarkerElement(this.widgetId, this.onViewed, this.thumbnailImage, this.avatar, this.wordmark, str2, this.viewers, this.primaryText, this.secondaryText, this.tertiaryText, this.onItemSelected);
    }

    public final ImmutableLiveStreamBarkerElement withViewers(String str) {
        String str2 = (String) requireNonNull(str, "viewers");
        return this.viewers.equals(str2) ? this : new ImmutableLiveStreamBarkerElement(this.widgetId, this.onViewed, this.thumbnailImage, this.avatar, this.wordmark, this.state, str2, this.primaryText, this.secondaryText, this.tertiaryText, this.onItemSelected);
    }

    public final ImmutableLiveStreamBarkerElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableLiveStreamBarkerElement(this.widgetId, this.onViewed, this.thumbnailImage, this.avatar, this.wordmark, this.state, this.viewers, str2, this.secondaryText, this.tertiaryText, this.onItemSelected);
    }

    public final ImmutableLiveStreamBarkerElement withSecondaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.SECONDARY_TEXT);
        return this.secondaryText.equals(str2) ? this : new ImmutableLiveStreamBarkerElement(this.widgetId, this.onViewed, this.thumbnailImage, this.avatar, this.wordmark, this.state, this.viewers, this.primaryText, str2, this.tertiaryText, this.onItemSelected);
    }

    public final ImmutableLiveStreamBarkerElement withTertiaryText(@Nullable String str) {
        return equals(this.tertiaryText, str) ? this : new ImmutableLiveStreamBarkerElement(this.widgetId, this.onViewed, this.thumbnailImage, this.avatar, this.wordmark, this.state, this.viewers, this.primaryText, this.secondaryText, str, this.onItemSelected);
    }

    public final ImmutableLiveStreamBarkerElement withOnItemSelected(Method... methodArr) {
        return new ImmutableLiveStreamBarkerElement(this.widgetId, this.onViewed, this.thumbnailImage, this.avatar, this.wordmark, this.state, this.viewers, this.primaryText, this.secondaryText, this.tertiaryText, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableLiveStreamBarkerElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableLiveStreamBarkerElement(this.widgetId, this.onViewed, this.thumbnailImage, this.avatar, this.wordmark, this.state, this.viewers, this.primaryText, this.secondaryText, this.tertiaryText, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableLiveStreamBarkerElement) && equalTo(0, (ImmutableLiveStreamBarkerElement) obj);
    }

    private boolean equalTo(int i, ImmutableLiveStreamBarkerElement immutableLiveStreamBarkerElement) {
        return this.widgetId.equals(immutableLiveStreamBarkerElement.widgetId) && this.onViewed.equals(immutableLiveStreamBarkerElement.onViewed) && this.thumbnailImage.equals(immutableLiveStreamBarkerElement.thumbnailImage) && this.avatar.equals(immutableLiveStreamBarkerElement.avatar) && this.wordmark.equals(immutableLiveStreamBarkerElement.wordmark) && this.state.equals(immutableLiveStreamBarkerElement.state) && this.viewers.equals(immutableLiveStreamBarkerElement.viewers) && this.primaryText.equals(immutableLiveStreamBarkerElement.primaryText) && this.secondaryText.equals(immutableLiveStreamBarkerElement.secondaryText) && equals(this.tertiaryText, immutableLiveStreamBarkerElement.tertiaryText) && this.onItemSelected.equals(immutableLiveStreamBarkerElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.thumbnailImage.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.avatar.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.wordmark.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.state.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.viewers.hashCode();
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + this.primaryText.hashCode();
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + this.secondaryText.hashCode();
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.tertiaryText);
        return iHashCode10 + (iHashCode10 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "LiveStreamBarkerElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", thumbnailImage=" + this.thumbnailImage + ", avatar=" + this.avatar + ", wordmark=" + this.wordmark + ", state=" + this.state + ", viewers=" + this.viewers + ", primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", tertiaryText=" + this.tertiaryText + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "LiveStreamBarkerElement", generator = "Immutables")
    static final class Json extends LiveStreamBarkerElement {

        @Nullable
        String avatar;
        boolean onItemSelectedIsSet;
        boolean onViewedIsSet;

        @Nullable
        String primaryText;

        @Nullable
        String secondaryText;

        @Nullable
        String state;

        @Nullable
        String tertiaryText;

        @Nullable
        String thumbnailImage;

        @Nullable
        String viewers;

        @Nullable
        String widgetId;

        @Nullable
        String wordmark;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty("widgetId")
        public void setWidgetId(String str) {
            this.widgetId = str;
        }

        @JsonProperty("onViewed")
        public void setOnViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedIsSet = true;
        }

        @JsonProperty("thumbnailImage")
        public void setThumbnailImage(String str) {
            this.thumbnailImage = str;
        }

        @JsonProperty("avatar")
        public void setAvatar(String str) {
            this.avatar = str;
        }

        @JsonProperty("wordmark")
        public void setWordmark(String str) {
            this.wordmark = str;
        }

        @JsonProperty("state")
        public void setState(String str) {
            this.state = str;
        }

        @JsonProperty("viewers")
        public void setViewers(String str) {
            this.viewers = str;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public void setPrimaryText(String str) {
            this.primaryText = str;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public void setSecondaryText(String str) {
            this.secondaryText = str;
        }

        @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
        public void setTertiaryText(@Nullable String str) {
            this.tertiaryText = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
        public String thumbnailImage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
        public String avatar() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
        public String wordmark() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
        public String state() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
        public String viewers() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
        public String tertiaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableLiveStreamBarkerElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        String str2 = json.thumbnailImage;
        if (str2 != null) {
            builder.thumbnailImage(str2);
        }
        String str3 = json.avatar;
        if (str3 != null) {
            builder.avatar(str3);
        }
        String str4 = json.wordmark;
        if (str4 != null) {
            builder.wordmark(str4);
        }
        String str5 = json.state;
        if (str5 != null) {
            builder.state(str5);
        }
        String str6 = json.viewers;
        if (str6 != null) {
            builder.viewers(str6);
        }
        String str7 = json.primaryText;
        if (str7 != null) {
            builder.primaryText(str7);
        }
        String str8 = json.secondaryText;
        if (str8 != null) {
            builder.secondaryText(str8);
        }
        String str9 = json.tertiaryText;
        if (str9 != null) {
            builder.tertiaryText(str9);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        return builder.build();
    }

    public static ImmutableLiveStreamBarkerElement copyOf(LiveStreamBarkerElement liveStreamBarkerElement) {
        if (liveStreamBarkerElement instanceof ImmutableLiveStreamBarkerElement) {
            return (ImmutableLiveStreamBarkerElement) liveStreamBarkerElement;
        }
        return builder().from(liveStreamBarkerElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "LiveStreamBarkerElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_AVATAR = 2;
        private static final long INIT_BIT_PRIMARY_TEXT = 32;
        private static final long INIT_BIT_SECONDARY_TEXT = 64;
        private static final long INIT_BIT_STATE = 8;
        private static final long INIT_BIT_THUMBNAIL_IMAGE = 1;
        private static final long INIT_BIT_VIEWERS = 16;
        private static final long INIT_BIT_WORDMARK = 4;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private String avatar;
        private long initBits;
        private List<Method> onItemSelected;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String primaryText;

        @Nullable
        private String secondaryText;

        @Nullable
        private String state;

        @Nullable
        private String tertiaryText;

        @Nullable
        private String thumbnailImage;

        @Nullable
        private String viewers;

        @Nullable
        private String widgetId;

        @Nullable
        private String wordmark;

        private Builder() {
            this.initBits = 127L;
            this.onViewed = new ArrayList();
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(LiveStreamBarkerElement liveStreamBarkerElement) {
            ImmutableLiveStreamBarkerElement.requireNonNull(liveStreamBarkerElement, "instance");
            from((Object) liveStreamBarkerElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableLiveStreamBarkerElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof LiveStreamBarkerElement) {
                LiveStreamBarkerElement liveStreamBarkerElement = (LiveStreamBarkerElement) obj;
                addAllOnItemSelected(liveStreamBarkerElement.onItemSelected());
                viewers(liveStreamBarkerElement.viewers());
                widgetId(liveStreamBarkerElement.widgetId());
                primaryText(liveStreamBarkerElement.primaryText());
                addAllOnViewed(liveStreamBarkerElement.onViewed());
                j = 3;
                avatar(liveStreamBarkerElement.avatar());
                wordmark(liveStreamBarkerElement.wordmark());
                state(liveStreamBarkerElement.state());
                thumbnailImage(liveStreamBarkerElement.thumbnailImage());
                String strTertiaryText = liveStreamBarkerElement.tertiaryText();
                if (strTertiaryText != null) {
                    tertiaryText(strTertiaryText);
                }
                secondaryText(liveStreamBarkerElement.secondaryText());
            } else {
                j = 0;
            }
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                if ((j & 1) == 0) {
                    widgetId(widgetElement.widgetId());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    addAllOnViewed(widgetElement.onViewed());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableLiveStreamBarkerElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableLiveStreamBarkerElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableLiveStreamBarkerElement.requireNonNull(method, "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onViewed")
        public final Builder onViewed(Iterable<? extends Method> iterable) {
            this.onViewed.clear();
            return addAllOnViewed(iterable);
        }

        public final Builder addAllOnViewed(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onViewed.add((Method) ImmutableLiveStreamBarkerElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("thumbnailImage")
        public final Builder thumbnailImage(String str) {
            this.thumbnailImage = (String) ImmutableLiveStreamBarkerElement.requireNonNull(str, "thumbnailImage");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("avatar")
        public final Builder avatar(String str) {
            this.avatar = (String) ImmutableLiveStreamBarkerElement.requireNonNull(str, "avatar");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("wordmark")
        public final Builder wordmark(String str) {
            this.wordmark = (String) ImmutableLiveStreamBarkerElement.requireNonNull(str, "wordmark");
            this.initBits &= -5;
            return this;
        }

        @JsonProperty("state")
        public final Builder state(String str) {
            this.state = (String) ImmutableLiveStreamBarkerElement.requireNonNull(str, "state");
            this.initBits &= -9;
            return this;
        }

        @JsonProperty("viewers")
        public final Builder viewers(String str) {
            this.viewers = (String) ImmutableLiveStreamBarkerElement.requireNonNull(str, "viewers");
            this.initBits &= -17;
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableLiveStreamBarkerElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -33;
            return this;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public final Builder secondaryText(String str) {
            this.secondaryText = (String) ImmutableLiveStreamBarkerElement.requireNonNull(str, ContextMappingConstants.SECONDARY_TEXT);
            this.initBits &= -65;
            return this;
        }

        @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
        public final Builder tertiaryText(@Nullable String str) {
            this.tertiaryText = str;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableLiveStreamBarkerElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableLiveStreamBarkerElement.requireNonNull(method, "onItemSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onItemSelected")
        public final Builder onItemSelected(Iterable<? extends Method> iterable) {
            this.onItemSelected.clear();
            return addAllOnItemSelected(iterable);
        }

        public final Builder addAllOnItemSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onItemSelected.add((Method) ImmutableLiveStreamBarkerElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableLiveStreamBarkerElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableLiveStreamBarkerElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemSelectedIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("thumbnailImage");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("avatar");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("wordmark");
            }
            if ((this.initBits & 8) != 0) {
                arrayList.add("state");
            }
            if ((this.initBits & 16) != 0) {
                arrayList.add("viewers");
            }
            if ((this.initBits & 32) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            if ((this.initBits & 64) != 0) {
                arrayList.add(ContextMappingConstants.SECONDARY_TEXT);
            }
            return "Cannot build LiveStreamBarkerElement, some of required attributes are not set " + arrayList;
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
