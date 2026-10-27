package Touch.LiveStreamPlayerTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateLaunchMode;
import Touch.WidgetsInterface.v1_0.WidgetElement;
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
@Generated(from = "LiveStreamPlayerTemplate", generator = "Immutables")
public final class ImmutableLiveStreamPlayerTemplate extends LiveStreamPlayerTemplate {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Long cacheSeconds;
    private volatile transient InitShim initShim;
    private final TemplateLaunchMode launchMode;
    private final List<Method> onBound;
    private final List<Method> onCreated;
    private final List<Method> onViewed;
    private final TitleBarElement titleBar;
    private final LiveVideoAndChatWebElement videoAndChat;
    private final List<WidgetElement> widgets;

    private ImmutableLiveStreamPlayerTemplate(Builder builder) {
        this.initShim = new InitShim();
        this.videoAndChat = builder.videoAndChat;
        this.titleBar = builder.titleBar;
        if (builder.onCreatedIsSet()) {
            this.initShim.onCreated(createUnmodifiableList(true, builder.onCreated));
        }
        if (builder.onBoundIsSet()) {
            this.initShim.onBound(createUnmodifiableList(true, builder.onBound));
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.launchMode != null) {
            this.initShim.launchMode(builder.launchMode);
        }
        if (builder.cacheSeconds != null) {
            this.initShim.cacheSeconds(builder.cacheSeconds);
        }
        if (builder.widgetsIsSet()) {
            this.initShim.widgets(createUnmodifiableList(true, builder.widgets));
        }
        this.onCreated = this.initShim.onCreated();
        this.onBound = this.initShim.onBound();
        this.onViewed = this.initShim.onViewed();
        this.launchMode = this.initShim.launchMode();
        this.cacheSeconds = this.initShim.cacheSeconds();
        this.widgets = this.initShim.widgets();
        this.initShim = null;
    }

    private ImmutableLiveStreamPlayerTemplate(List<Method> list, List<Method> list2, List<Method> list3, TemplateLaunchMode templateLaunchMode, Long l, LiveVideoAndChatWebElement liveVideoAndChatWebElement, TitleBarElement titleBarElement, List<WidgetElement> list4) {
        this.initShim = new InitShim();
        this.onCreated = list;
        this.onBound = list2;
        this.onViewed = list3;
        this.launchMode = templateLaunchMode;
        this.cacheSeconds = l;
        this.videoAndChat = liveVideoAndChatWebElement;
        this.titleBar = titleBarElement;
        this.widgets = list4;
        this.initShim = null;
    }

    @Generated(from = "LiveStreamPlayerTemplate", generator = "Immutables")
    private final class InitShim {
        private Long cacheSeconds;
        private byte cacheSecondsBuildStage;
        private TemplateLaunchMode launchMode;
        private byte launchModeBuildStage;
        private List<Method> onBound;
        private byte onBoundBuildStage;
        private List<Method> onCreated;
        private byte onCreatedBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private List<WidgetElement> widgets;
        private byte widgetsBuildStage;

        private InitShim() {
            this.onCreatedBuildStage = (byte) 0;
            this.onBoundBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.launchModeBuildStage = (byte) 0;
            this.cacheSecondsBuildStage = (byte) 0;
            this.widgetsBuildStage = (byte) 0;
        }

        List<Method> onCreated() {
            byte b = this.onCreatedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCreatedBuildStage = (byte) -1;
                this.onCreated = ImmutableLiveStreamPlayerTemplate.createUnmodifiableList(false, ImmutableLiveStreamPlayerTemplate.createSafeList(ImmutableLiveStreamPlayerTemplate.super.onCreated(), true, false));
                this.onCreatedBuildStage = (byte) 1;
            }
            return this.onCreated;
        }

        void onCreated(List<Method> list) {
            this.onCreated = list;
            this.onCreatedBuildStage = (byte) 1;
        }

        List<Method> onBound() {
            byte b = this.onBoundBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onBoundBuildStage = (byte) -1;
                this.onBound = ImmutableLiveStreamPlayerTemplate.createUnmodifiableList(false, ImmutableLiveStreamPlayerTemplate.createSafeList(ImmutableLiveStreamPlayerTemplate.super.onBound(), true, false));
                this.onBoundBuildStage = (byte) 1;
            }
            return this.onBound;
        }

        void onBound(List<Method> list) {
            this.onBound = list;
            this.onBoundBuildStage = (byte) 1;
        }

        List<Method> onViewed() {
            byte b = this.onViewedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onViewedBuildStage = (byte) -1;
                this.onViewed = ImmutableLiveStreamPlayerTemplate.createUnmodifiableList(false, ImmutableLiveStreamPlayerTemplate.createSafeList(ImmutableLiveStreamPlayerTemplate.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        TemplateLaunchMode launchMode() {
            byte b = this.launchModeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.launchModeBuildStage = (byte) -1;
                this.launchMode = (TemplateLaunchMode) ImmutableLiveStreamPlayerTemplate.requireNonNull(ImmutableLiveStreamPlayerTemplate.super.launchMode(), "launchMode");
                this.launchModeBuildStage = (byte) 1;
            }
            return this.launchMode;
        }

        void launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = templateLaunchMode;
            this.launchModeBuildStage = (byte) 1;
        }

        Long cacheSeconds() {
            byte b = this.cacheSecondsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.cacheSecondsBuildStage = (byte) -1;
                this.cacheSeconds = (Long) ImmutableLiveStreamPlayerTemplate.requireNonNull(ImmutableLiveStreamPlayerTemplate.super.cacheSeconds(), "cacheSeconds");
                this.cacheSecondsBuildStage = (byte) 1;
            }
            return this.cacheSeconds;
        }

        void cacheSeconds(Long l) {
            this.cacheSeconds = l;
            this.cacheSecondsBuildStage = (byte) 1;
        }

        List<WidgetElement> widgets() {
            byte b = this.widgetsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetsBuildStage = (byte) -1;
                this.widgets = ImmutableLiveStreamPlayerTemplate.createUnmodifiableList(false, ImmutableLiveStreamPlayerTemplate.createSafeList(ImmutableLiveStreamPlayerTemplate.super.widgets(), true, false));
                this.widgetsBuildStage = (byte) 1;
            }
            return this.widgets;
        }

        void widgets(List<WidgetElement> list) {
            this.widgets = list;
            this.widgetsBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.onCreatedBuildStage == -1) {
                arrayList.add("onCreated");
            }
            if (this.onBoundBuildStage == -1) {
                arrayList.add("onBound");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.launchModeBuildStage == -1) {
                arrayList.add("launchMode");
            }
            if (this.cacheSecondsBuildStage == -1) {
                arrayList.add("cacheSeconds");
            }
            if (this.widgetsBuildStage == -1) {
                arrayList.add("widgets");
            }
            return "Cannot build LiveStreamPlayerTemplate, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("onCreated")
    public List<Method> onCreated() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onCreated();
        }
        return this.onCreated;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("onBound")
    public List<Method> onBound() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onBound();
        }
        return this.onBound;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("onViewed")
    public List<Method> onViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onViewed();
        }
        return this.onViewed;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("launchMode")
    public TemplateLaunchMode launchMode() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.launchMode();
        }
        return this.launchMode;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("cacheSeconds")
    public Long cacheSeconds() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.cacheSeconds();
        }
        return this.cacheSeconds;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.LiveStreamPlayerTemplate
    @JsonProperty("videoAndChat")
    public LiveVideoAndChatWebElement videoAndChat() {
        return this.videoAndChat;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.LiveStreamPlayerTemplate
    @JsonProperty("titleBar")
    public TitleBarElement titleBar() {
        return this.titleBar;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.LiveStreamPlayerTemplate
    @JsonProperty("widgets")
    public List<WidgetElement> widgets() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.widgets();
        }
        return this.widgets;
    }

    public final ImmutableLiveStreamPlayerTemplate withOnCreated(Method... methodArr) {
        return new ImmutableLiveStreamPlayerTemplate(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.videoAndChat, this.titleBar, this.widgets);
    }

    public final ImmutableLiveStreamPlayerTemplate withOnCreated(Iterable<? extends Method> iterable) {
        return this.onCreated == iterable ? this : new ImmutableLiveStreamPlayerTemplate(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.videoAndChat, this.titleBar, this.widgets);
    }

    public final ImmutableLiveStreamPlayerTemplate withOnBound(Method... methodArr) {
        return new ImmutableLiveStreamPlayerTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.videoAndChat, this.titleBar, this.widgets);
    }

    public final ImmutableLiveStreamPlayerTemplate withOnBound(Iterable<? extends Method> iterable) {
        if (this.onBound == iterable) {
            return this;
        }
        return new ImmutableLiveStreamPlayerTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.videoAndChat, this.titleBar, this.widgets);
    }

    public final ImmutableLiveStreamPlayerTemplate withOnViewed(Method... methodArr) {
        return new ImmutableLiveStreamPlayerTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.launchMode, this.cacheSeconds, this.videoAndChat, this.titleBar, this.widgets);
    }

    public final ImmutableLiveStreamPlayerTemplate withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableLiveStreamPlayerTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.launchMode, this.cacheSeconds, this.videoAndChat, this.titleBar, this.widgets);
    }

    public final ImmutableLiveStreamPlayerTemplate withLaunchMode(TemplateLaunchMode templateLaunchMode) {
        TemplateLaunchMode templateLaunchMode2 = (TemplateLaunchMode) requireNonNull(templateLaunchMode, "launchMode");
        return this.launchMode == templateLaunchMode2 ? this : new ImmutableLiveStreamPlayerTemplate(this.onCreated, this.onBound, this.onViewed, templateLaunchMode2, this.cacheSeconds, this.videoAndChat, this.titleBar, this.widgets);
    }

    public final ImmutableLiveStreamPlayerTemplate withCacheSeconds(Long l) {
        Long l2 = (Long) requireNonNull(l, "cacheSeconds");
        return this.cacheSeconds.equals(l2) ? this : new ImmutableLiveStreamPlayerTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, l2, this.videoAndChat, this.titleBar, this.widgets);
    }

    public final ImmutableLiveStreamPlayerTemplate withVideoAndChat(LiveVideoAndChatWebElement liveVideoAndChatWebElement) {
        if (this.videoAndChat == liveVideoAndChatWebElement) {
            return this;
        }
        return new ImmutableLiveStreamPlayerTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, (LiveVideoAndChatWebElement) requireNonNull(liveVideoAndChatWebElement, "videoAndChat"), this.titleBar, this.widgets);
    }

    public final ImmutableLiveStreamPlayerTemplate withTitleBar(TitleBarElement titleBarElement) {
        if (this.titleBar == titleBarElement) {
            return this;
        }
        return new ImmutableLiveStreamPlayerTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.videoAndChat, (TitleBarElement) requireNonNull(titleBarElement, "titleBar"), this.widgets);
    }

    public final ImmutableLiveStreamPlayerTemplate withWidgets(WidgetElement... widgetElementArr) {
        return new ImmutableLiveStreamPlayerTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.videoAndChat, this.titleBar, createUnmodifiableList(false, createSafeList(Arrays.asList(widgetElementArr), true, false)));
    }

    public final ImmutableLiveStreamPlayerTemplate withWidgets(Iterable<? extends WidgetElement> iterable) {
        if (this.widgets == iterable) {
            return this;
        }
        return new ImmutableLiveStreamPlayerTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.videoAndChat, this.titleBar, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableLiveStreamPlayerTemplate) && equalTo(0, (ImmutableLiveStreamPlayerTemplate) obj);
    }

    private boolean equalTo(int i, ImmutableLiveStreamPlayerTemplate immutableLiveStreamPlayerTemplate) {
        return this.onCreated.equals(immutableLiveStreamPlayerTemplate.onCreated) && this.onBound.equals(immutableLiveStreamPlayerTemplate.onBound) && this.onViewed.equals(immutableLiveStreamPlayerTemplate.onViewed) && this.launchMode.equals(immutableLiveStreamPlayerTemplate.launchMode) && this.cacheSeconds.equals(immutableLiveStreamPlayerTemplate.cacheSeconds) && this.videoAndChat.equals(immutableLiveStreamPlayerTemplate.videoAndChat) && this.titleBar.equals(immutableLiveStreamPlayerTemplate.titleBar) && this.widgets.equals(immutableLiveStreamPlayerTemplate.widgets);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onCreated.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onBound.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.launchMode.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.cacheSeconds.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.videoAndChat.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.titleBar.hashCode();
        return iHashCode7 + (iHashCode7 << 5) + this.widgets.hashCode();
    }

    public String toString() {
        return "LiveStreamPlayerTemplate{onCreated=" + this.onCreated + ", onBound=" + this.onBound + ", onViewed=" + this.onViewed + ", launchMode=" + this.launchMode + ", cacheSeconds=" + this.cacheSeconds + ", videoAndChat=" + this.videoAndChat + ", titleBar=" + this.titleBar + ", widgets=" + this.widgets + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "LiveStreamPlayerTemplate", generator = "Immutables")
    static final class Json extends LiveStreamPlayerTemplate {

        @Nullable
        Long cacheSeconds;

        @Nullable
        TemplateLaunchMode launchMode;
        boolean onBoundIsSet;
        boolean onCreatedIsSet;
        boolean onViewedIsSet;

        @Nullable
        TitleBarElement titleBar;

        @Nullable
        LiveVideoAndChatWebElement videoAndChat;
        boolean widgetsIsSet;

        @Nullable
        List<Method> onCreated = Collections.emptyList();

        @Nullable
        List<Method> onBound = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<WidgetElement> widgets = Collections.emptyList();

        Json() {
        }

        @JsonProperty("onCreated")
        public void setOnCreated(List<Method> list) {
            this.onCreated = list;
            this.onCreatedIsSet = true;
        }

        @JsonProperty("onBound")
        public void setOnBound(List<Method> list) {
            this.onBound = list;
            this.onBoundIsSet = true;
        }

        @JsonProperty("onViewed")
        public void setOnViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedIsSet = true;
        }

        @JsonProperty("launchMode")
        public void setLaunchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = templateLaunchMode;
        }

        @JsonProperty("cacheSeconds")
        public void setCacheSeconds(Long l) {
            this.cacheSeconds = l;
        }

        @JsonProperty("videoAndChat")
        public void setVideoAndChat(LiveVideoAndChatWebElement liveVideoAndChatWebElement) {
            this.videoAndChat = liveVideoAndChatWebElement;
        }

        @JsonProperty("titleBar")
        public void setTitleBar(TitleBarElement titleBarElement) {
            this.titleBar = titleBarElement;
        }

        @JsonProperty("widgets")
        public void setWidgets(List<WidgetElement> list) {
            this.widgets = list;
            this.widgetsIsSet = true;
        }

        @Override // CoreInterface.v1_0.Template
        public List<Method> onCreated() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public List<Method> onBound() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public TemplateLaunchMode launchMode() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public Long cacheSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.LiveStreamPlayerTemplate
        public LiveVideoAndChatWebElement videoAndChat() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.LiveStreamPlayerTemplate
        public TitleBarElement titleBar() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.LiveStreamPlayerTemplate
        public List<WidgetElement> widgets() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableLiveStreamPlayerTemplate fromJson(Json json) {
        Builder builder = builder();
        if (json.onCreatedIsSet) {
            builder.addAllOnCreated(json.onCreated);
        }
        if (json.onBoundIsSet) {
            builder.addAllOnBound(json.onBound);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        TemplateLaunchMode templateLaunchMode = json.launchMode;
        if (templateLaunchMode != null) {
            builder.launchMode(templateLaunchMode);
        }
        Long l = json.cacheSeconds;
        if (l != null) {
            builder.cacheSeconds(l);
        }
        LiveVideoAndChatWebElement liveVideoAndChatWebElement = json.videoAndChat;
        if (liveVideoAndChatWebElement != null) {
            builder.videoAndChat(liveVideoAndChatWebElement);
        }
        TitleBarElement titleBarElement = json.titleBar;
        if (titleBarElement != null) {
            builder.titleBar(titleBarElement);
        }
        if (json.widgetsIsSet) {
            builder.addAllWidgets(json.widgets);
        }
        return builder.build();
    }

    public static ImmutableLiveStreamPlayerTemplate copyOf(LiveStreamPlayerTemplate liveStreamPlayerTemplate) {
        if (liveStreamPlayerTemplate instanceof ImmutableLiveStreamPlayerTemplate) {
            return (ImmutableLiveStreamPlayerTemplate) liveStreamPlayerTemplate;
        }
        return builder().from(liveStreamPlayerTemplate).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "LiveStreamPlayerTemplate", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TITLE_BAR = 2;
        private static final long INIT_BIT_VIDEO_AND_CHAT = 1;
        private static final long OPT_BIT_ON_BOUND = 2;
        private static final long OPT_BIT_ON_CREATED = 1;
        private static final long OPT_BIT_ON_VIEWED = 4;
        private static final long OPT_BIT_WIDGETS = 8;

        @Nullable
        private Long cacheSeconds;
        private long initBits;

        @Nullable
        private TemplateLaunchMode launchMode;
        private List<Method> onBound;
        private List<Method> onCreated;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private TitleBarElement titleBar;

        @Nullable
        private LiveVideoAndChatWebElement videoAndChat;
        private List<WidgetElement> widgets;

        private Builder() {
            this.initBits = 3L;
            this.onCreated = new ArrayList();
            this.onBound = new ArrayList();
            this.onViewed = new ArrayList();
            this.widgets = new ArrayList();
        }

        public final Builder from(Template template) {
            ImmutableLiveStreamPlayerTemplate.requireNonNull(template, "instance");
            from((Object) template);
            return this;
        }

        public final Builder from(LiveStreamPlayerTemplate liveStreamPlayerTemplate) {
            ImmutableLiveStreamPlayerTemplate.requireNonNull(liveStreamPlayerTemplate, "instance");
            from((Object) liveStreamPlayerTemplate);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof Template) {
                Template template = (Template) obj;
                addAllOnBound(template.onBound());
                launchMode(template.launchMode());
                addAllOnViewed(template.onViewed());
                addAllOnCreated(template.onCreated());
                cacheSeconds(template.cacheSeconds());
                j = 31;
            } else {
                j = 0;
            }
            if (obj instanceof LiveStreamPlayerTemplate) {
                LiveStreamPlayerTemplate liveStreamPlayerTemplate = (LiveStreamPlayerTemplate) obj;
                if ((j & 1) == 0) {
                    addAllOnBound(liveStreamPlayerTemplate.onBound());
                    j |= 1;
                }
                titleBar(liveStreamPlayerTemplate.titleBar());
                if ((j & 2) == 0) {
                    cacheSeconds(liveStreamPlayerTemplate.cacheSeconds());
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    launchMode(liveStreamPlayerTemplate.launchMode());
                    j |= 4;
                }
                videoAndChat(liveStreamPlayerTemplate.videoAndChat());
                if ((j & 8) == 0) {
                    addAllOnViewed(liveStreamPlayerTemplate.onViewed());
                    j |= 8;
                }
                addAllWidgets(liveStreamPlayerTemplate.widgets());
                if ((j & 16) == 0) {
                    addAllOnCreated(liveStreamPlayerTemplate.onCreated());
                }
            }
        }

        public final Builder addOnCreated(Method method) {
            this.onCreated.add((Method) ImmutableLiveStreamPlayerTemplate.requireNonNull(method, "onCreated element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCreated(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCreated.add((Method) ImmutableLiveStreamPlayerTemplate.requireNonNull(method, "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onCreated")
        public final Builder onCreated(Iterable<? extends Method> iterable) {
            this.onCreated.clear();
            return addAllOnCreated(iterable);
        }

        public final Builder addAllOnCreated(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onCreated.add((Method) ImmutableLiveStreamPlayerTemplate.requireNonNull(it.next(), "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBound(Method method) {
            this.onBound.add((Method) ImmutableLiveStreamPlayerTemplate.requireNonNull(method, "onBound element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnBound(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBound.add((Method) ImmutableLiveStreamPlayerTemplate.requireNonNull(method, "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onBound")
        public final Builder onBound(Iterable<? extends Method> iterable) {
            this.onBound.clear();
            return addAllOnBound(iterable);
        }

        public final Builder addAllOnBound(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onBound.add((Method) ImmutableLiveStreamPlayerTemplate.requireNonNull(it.next(), "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableLiveStreamPlayerTemplate.requireNonNull(method, "onViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableLiveStreamPlayerTemplate.requireNonNull(method, "onViewed element"));
            }
            this.optBits |= 4;
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
                this.onViewed.add((Method) ImmutableLiveStreamPlayerTemplate.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("launchMode")
        public final Builder launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = (TemplateLaunchMode) ImmutableLiveStreamPlayerTemplate.requireNonNull(templateLaunchMode, "launchMode");
            return this;
        }

        @JsonProperty("cacheSeconds")
        public final Builder cacheSeconds(Long l) {
            this.cacheSeconds = (Long) ImmutableLiveStreamPlayerTemplate.requireNonNull(l, "cacheSeconds");
            return this;
        }

        @JsonProperty("videoAndChat")
        public final Builder videoAndChat(LiveVideoAndChatWebElement liveVideoAndChatWebElement) {
            this.videoAndChat = (LiveVideoAndChatWebElement) ImmutableLiveStreamPlayerTemplate.requireNonNull(liveVideoAndChatWebElement, "videoAndChat");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("titleBar")
        public final Builder titleBar(TitleBarElement titleBarElement) {
            this.titleBar = (TitleBarElement) ImmutableLiveStreamPlayerTemplate.requireNonNull(titleBarElement, "titleBar");
            this.initBits &= -3;
            return this;
        }

        public final Builder addWidgets(WidgetElement widgetElement) {
            this.widgets.add((WidgetElement) ImmutableLiveStreamPlayerTemplate.requireNonNull(widgetElement, "widgets element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addWidgets(WidgetElement... widgetElementArr) {
            for (WidgetElement widgetElement : widgetElementArr) {
                this.widgets.add((WidgetElement) ImmutableLiveStreamPlayerTemplate.requireNonNull(widgetElement, "widgets element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("widgets")
        public final Builder widgets(Iterable<? extends WidgetElement> iterable) {
            this.widgets.clear();
            return addAllWidgets(iterable);
        }

        public final Builder addAllWidgets(Iterable<? extends WidgetElement> iterable) {
            Iterator<? extends WidgetElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.widgets.add((WidgetElement) ImmutableLiveStreamPlayerTemplate.requireNonNull(it.next(), "widgets element"));
            }
            this.optBits |= 8;
            return this;
        }

        public ImmutableLiveStreamPlayerTemplate build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableLiveStreamPlayerTemplate(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onCreatedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onBoundIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean widgetsIsSet() {
            return (this.optBits & 8) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("videoAndChat");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("titleBar");
            }
            return "Cannot build LiveStreamPlayerTemplate, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
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
