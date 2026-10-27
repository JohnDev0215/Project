package Touch.MultiSelectorTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateLaunchMode;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.android.gms.cast.MediaTrack;
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
@Generated(from = "MultiSelectorTemplate", generator = "Immutables")
public final class ImmutableMultiSelectorTemplate extends MultiSelectorTemplate {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String backgroundImage;
    private final Long cacheSeconds;

    @Nullable
    private final ButtonElement callToActionButton;

    @Nullable
    private final ButtonElement closeButton;
    private volatile transient InitShim initShim;
    private final TemplateLaunchMode launchMode;
    private final List<Method> onBound;
    private final List<Method> onCreated;
    private final List<Method> onViewed;

    @Nullable
    private final String subtitle;

    @Nullable
    private final TabNavigatorWidgetElement tabNavigatorWidgetElement;

    @Nullable
    private final String title;
    private final List<WidgetElement> widgetElements;

    private ImmutableMultiSelectorTemplate(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.closeButton = builder.closeButton;
        this.callToActionButton = builder.callToActionButton;
        this.tabNavigatorWidgetElement = builder.tabNavigatorWidgetElement;
        this.backgroundImage = builder.backgroundImage;
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
        if (builder.widgetElementsIsSet()) {
            this.initShim.widgetElements(createUnmodifiableList(true, builder.widgetElements));
        }
        this.onCreated = this.initShim.onCreated();
        this.onBound = this.initShim.onBound();
        this.onViewed = this.initShim.onViewed();
        this.launchMode = this.initShim.launchMode();
        this.cacheSeconds = this.initShim.cacheSeconds();
        this.widgetElements = this.initShim.widgetElements();
        this.initShim = null;
    }

    private ImmutableMultiSelectorTemplate(List<Method> list, List<Method> list2, List<Method> list3, TemplateLaunchMode templateLaunchMode, Long l, @Nullable String str, @Nullable String str2, @Nullable ButtonElement buttonElement, @Nullable ButtonElement buttonElement2, @Nullable TabNavigatorWidgetElement tabNavigatorWidgetElement, List<WidgetElement> list4, @Nullable String str3) {
        this.initShim = new InitShim();
        this.onCreated = list;
        this.onBound = list2;
        this.onViewed = list3;
        this.launchMode = templateLaunchMode;
        this.cacheSeconds = l;
        this.title = str;
        this.subtitle = str2;
        this.closeButton = buttonElement;
        this.callToActionButton = buttonElement2;
        this.tabNavigatorWidgetElement = tabNavigatorWidgetElement;
        this.widgetElements = list4;
        this.backgroundImage = str3;
        this.initShim = null;
    }

    @Generated(from = "MultiSelectorTemplate", generator = "Immutables")
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
        private List<WidgetElement> widgetElements;
        private byte widgetElementsBuildStage;

        private InitShim() {
            this.onCreatedBuildStage = (byte) 0;
            this.onBoundBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.launchModeBuildStage = (byte) 0;
            this.cacheSecondsBuildStage = (byte) 0;
            this.widgetElementsBuildStage = (byte) 0;
        }

        List<Method> onCreated() {
            byte b = this.onCreatedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCreatedBuildStage = (byte) -1;
                this.onCreated = ImmutableMultiSelectorTemplate.createUnmodifiableList(false, ImmutableMultiSelectorTemplate.createSafeList(ImmutableMultiSelectorTemplate.super.onCreated(), true, false));
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
                this.onBound = ImmutableMultiSelectorTemplate.createUnmodifiableList(false, ImmutableMultiSelectorTemplate.createSafeList(ImmutableMultiSelectorTemplate.super.onBound(), true, false));
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
                this.onViewed = ImmutableMultiSelectorTemplate.createUnmodifiableList(false, ImmutableMultiSelectorTemplate.createSafeList(ImmutableMultiSelectorTemplate.super.onViewed(), true, false));
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
                this.launchMode = (TemplateLaunchMode) ImmutableMultiSelectorTemplate.requireNonNull(ImmutableMultiSelectorTemplate.super.launchMode(), "launchMode");
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
                this.cacheSeconds = (Long) ImmutableMultiSelectorTemplate.requireNonNull(ImmutableMultiSelectorTemplate.super.cacheSeconds(), "cacheSeconds");
                this.cacheSecondsBuildStage = (byte) 1;
            }
            return this.cacheSeconds;
        }

        void cacheSeconds(Long l) {
            this.cacheSeconds = l;
            this.cacheSecondsBuildStage = (byte) 1;
        }

        List<WidgetElement> widgetElements() {
            byte b = this.widgetElementsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetElementsBuildStage = (byte) -1;
                this.widgetElements = ImmutableMultiSelectorTemplate.createUnmodifiableList(false, ImmutableMultiSelectorTemplate.createSafeList(ImmutableMultiSelectorTemplate.super.widgetElements(), true, false));
                this.widgetElementsBuildStage = (byte) 1;
            }
            return this.widgetElements;
        }

        void widgetElements(List<WidgetElement> list) {
            this.widgetElements = list;
            this.widgetElementsBuildStage = (byte) 1;
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
            if (this.widgetElementsBuildStage == -1) {
                arrayList.add("widgetElements");
            }
            return "Cannot build MultiSelectorTemplate, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
    @JsonProperty("title")
    @Nullable
    public String title() {
        return this.title;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
    @JsonProperty(MediaTrack.ROLE_SUBTITLE)
    @Nullable
    public String subtitle() {
        return this.subtitle;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
    @JsonProperty("closeButton")
    @Nullable
    public ButtonElement closeButton() {
        return this.closeButton;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
    @JsonProperty("callToActionButton")
    @Nullable
    public ButtonElement callToActionButton() {
        return this.callToActionButton;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
    @JsonProperty("tabNavigatorWidgetElement")
    @Nullable
    public TabNavigatorWidgetElement tabNavigatorWidgetElement() {
        return this.tabNavigatorWidgetElement;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
    @JsonProperty("widgetElements")
    public List<WidgetElement> widgetElements() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.widgetElements();
        }
        return this.widgetElements;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
    @JsonProperty("backgroundImage")
    @Nullable
    public String backgroundImage() {
        return this.backgroundImage;
    }

    public final ImmutableMultiSelectorTemplate withOnCreated(Method... methodArr) {
        return new ImmutableMultiSelectorTemplate(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withOnCreated(Iterable<? extends Method> iterable) {
        return this.onCreated == iterable ? this : new ImmutableMultiSelectorTemplate(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withOnBound(Method... methodArr) {
        return new ImmutableMultiSelectorTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withOnBound(Iterable<? extends Method> iterable) {
        if (this.onBound == iterable) {
            return this;
        }
        return new ImmutableMultiSelectorTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withOnViewed(Method... methodArr) {
        return new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.launchMode, this.cacheSeconds, this.title, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.launchMode, this.cacheSeconds, this.title, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withLaunchMode(TemplateLaunchMode templateLaunchMode) {
        TemplateLaunchMode templateLaunchMode2 = (TemplateLaunchMode) requireNonNull(templateLaunchMode, "launchMode");
        return this.launchMode == templateLaunchMode2 ? this : new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, this.onViewed, templateLaunchMode2, this.cacheSeconds, this.title, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withCacheSeconds(Long l) {
        Long l2 = (Long) requireNonNull(l, "cacheSeconds");
        return this.cacheSeconds.equals(l2) ? this : new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, l2, this.title, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, str, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withSubtitle(@Nullable String str) {
        return equals(this.subtitle, str) ? this : new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, str, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withCloseButton(@Nullable ButtonElement buttonElement) {
        return this.closeButton == buttonElement ? this : new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.subtitle, buttonElement, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withCallToActionButton(@Nullable ButtonElement buttonElement) {
        return this.callToActionButton == buttonElement ? this : new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.subtitle, this.closeButton, buttonElement, this.tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withTabNavigatorWidgetElement(@Nullable TabNavigatorWidgetElement tabNavigatorWidgetElement) {
        return this.tabNavigatorWidgetElement == tabNavigatorWidgetElement ? this : new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.subtitle, this.closeButton, this.callToActionButton, tabNavigatorWidgetElement, this.widgetElements, this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withWidgetElements(WidgetElement... widgetElementArr) {
        return new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, createUnmodifiableList(false, createSafeList(Arrays.asList(widgetElementArr), true, false)), this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withWidgetElements(Iterable<? extends WidgetElement> iterable) {
        if (this.widgetElements == iterable) {
            return this;
        }
        return new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.backgroundImage);
    }

    public final ImmutableMultiSelectorTemplate withBackgroundImage(@Nullable String str) {
        return equals(this.backgroundImage, str) ? this : new ImmutableMultiSelectorTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.subtitle, this.closeButton, this.callToActionButton, this.tabNavigatorWidgetElement, this.widgetElements, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMultiSelectorTemplate) && equalTo(0, (ImmutableMultiSelectorTemplate) obj);
    }

    private boolean equalTo(int i, ImmutableMultiSelectorTemplate immutableMultiSelectorTemplate) {
        return this.onCreated.equals(immutableMultiSelectorTemplate.onCreated) && this.onBound.equals(immutableMultiSelectorTemplate.onBound) && this.onViewed.equals(immutableMultiSelectorTemplate.onViewed) && this.launchMode.equals(immutableMultiSelectorTemplate.launchMode) && this.cacheSeconds.equals(immutableMultiSelectorTemplate.cacheSeconds) && equals(this.title, immutableMultiSelectorTemplate.title) && equals(this.subtitle, immutableMultiSelectorTemplate.subtitle) && equals(this.closeButton, immutableMultiSelectorTemplate.closeButton) && equals(this.callToActionButton, immutableMultiSelectorTemplate.callToActionButton) && equals(this.tabNavigatorWidgetElement, immutableMultiSelectorTemplate.tabNavigatorWidgetElement) && this.widgetElements.equals(immutableMultiSelectorTemplate.widgetElements) && equals(this.backgroundImage, immutableMultiSelectorTemplate.backgroundImage);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onCreated.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onBound.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.launchMode.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.cacheSeconds.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.title);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.subtitle);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.closeButton);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.callToActionButton);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.tabNavigatorWidgetElement);
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + this.widgetElements.hashCode();
        return iHashCode11 + (iHashCode11 << 5) + hashCode(this.backgroundImage);
    }

    public String toString() {
        return "MultiSelectorTemplate{onCreated=" + this.onCreated + ", onBound=" + this.onBound + ", onViewed=" + this.onViewed + ", launchMode=" + this.launchMode + ", cacheSeconds=" + this.cacheSeconds + ", title=" + this.title + ", subtitle=" + this.subtitle + ", closeButton=" + this.closeButton + ", callToActionButton=" + this.callToActionButton + ", tabNavigatorWidgetElement=" + this.tabNavigatorWidgetElement + ", widgetElements=" + this.widgetElements + ", backgroundImage=" + this.backgroundImage + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MultiSelectorTemplate", generator = "Immutables")
    static final class Json extends MultiSelectorTemplate {

        @Nullable
        String backgroundImage;

        @Nullable
        Long cacheSeconds;

        @Nullable
        ButtonElement callToActionButton;

        @Nullable
        ButtonElement closeButton;

        @Nullable
        TemplateLaunchMode launchMode;
        boolean onBoundIsSet;
        boolean onCreatedIsSet;
        boolean onViewedIsSet;

        @Nullable
        String subtitle;

        @Nullable
        TabNavigatorWidgetElement tabNavigatorWidgetElement;

        @Nullable
        String title;
        boolean widgetElementsIsSet;

        @Nullable
        List<Method> onCreated = Collections.emptyList();

        @Nullable
        List<Method> onBound = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<WidgetElement> widgetElements = Collections.emptyList();

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

        @JsonProperty("title")
        public void setTitle(@Nullable String str) {
            this.title = str;
        }

        @JsonProperty(MediaTrack.ROLE_SUBTITLE)
        public void setSubtitle(@Nullable String str) {
            this.subtitle = str;
        }

        @JsonProperty("closeButton")
        public void setCloseButton(@Nullable ButtonElement buttonElement) {
            this.closeButton = buttonElement;
        }

        @JsonProperty("callToActionButton")
        public void setCallToActionButton(@Nullable ButtonElement buttonElement) {
            this.callToActionButton = buttonElement;
        }

        @JsonProperty("tabNavigatorWidgetElement")
        public void setTabNavigatorWidgetElement(@Nullable TabNavigatorWidgetElement tabNavigatorWidgetElement) {
            this.tabNavigatorWidgetElement = tabNavigatorWidgetElement;
        }

        @JsonProperty("widgetElements")
        public void setWidgetElements(List<WidgetElement> list) {
            this.widgetElements = list;
            this.widgetElementsIsSet = true;
        }

        @JsonProperty("backgroundImage")
        public void setBackgroundImage(@Nullable String str) {
            this.backgroundImage = str;
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

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
        public String subtitle() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
        public ButtonElement closeButton() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
        public ButtonElement callToActionButton() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
        public TabNavigatorWidgetElement tabNavigatorWidgetElement() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
        public List<WidgetElement> widgetElements() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate
        public String backgroundImage() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMultiSelectorTemplate fromJson(Json json) {
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
        String str = json.title;
        if (str != null) {
            builder.title(str);
        }
        String str2 = json.subtitle;
        if (str2 != null) {
            builder.subtitle(str2);
        }
        ButtonElement buttonElement = json.closeButton;
        if (buttonElement != null) {
            builder.closeButton(buttonElement);
        }
        ButtonElement buttonElement2 = json.callToActionButton;
        if (buttonElement2 != null) {
            builder.callToActionButton(buttonElement2);
        }
        TabNavigatorWidgetElement tabNavigatorWidgetElement = json.tabNavigatorWidgetElement;
        if (tabNavigatorWidgetElement != null) {
            builder.tabNavigatorWidgetElement(tabNavigatorWidgetElement);
        }
        if (json.widgetElementsIsSet) {
            builder.addAllWidgetElements(json.widgetElements);
        }
        String str3 = json.backgroundImage;
        if (str3 != null) {
            builder.backgroundImage(str3);
        }
        return builder.build();
    }

    public static ImmutableMultiSelectorTemplate copyOf(MultiSelectorTemplate multiSelectorTemplate) {
        if (multiSelectorTemplate instanceof ImmutableMultiSelectorTemplate) {
            return (ImmutableMultiSelectorTemplate) multiSelectorTemplate;
        }
        return builder().from(multiSelectorTemplate).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MultiSelectorTemplate", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_BOUND = 2;
        private static final long OPT_BIT_ON_CREATED = 1;
        private static final long OPT_BIT_ON_VIEWED = 4;
        private static final long OPT_BIT_WIDGET_ELEMENTS = 8;

        @Nullable
        private String backgroundImage;

        @Nullable
        private Long cacheSeconds;

        @Nullable
        private ButtonElement callToActionButton;

        @Nullable
        private ButtonElement closeButton;

        @Nullable
        private TemplateLaunchMode launchMode;
        private List<Method> onBound;
        private List<Method> onCreated;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String subtitle;

        @Nullable
        private TabNavigatorWidgetElement tabNavigatorWidgetElement;

        @Nullable
        private String title;
        private List<WidgetElement> widgetElements;

        private Builder() {
            this.onCreated = new ArrayList();
            this.onBound = new ArrayList();
            this.onViewed = new ArrayList();
            this.widgetElements = new ArrayList();
        }

        public final Builder from(MultiSelectorTemplate multiSelectorTemplate) {
            ImmutableMultiSelectorTemplate.requireNonNull(multiSelectorTemplate, "instance");
            from((Object) multiSelectorTemplate);
            return this;
        }

        public final Builder from(Template template) {
            ImmutableMultiSelectorTemplate.requireNonNull(template, "instance");
            from((Object) template);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof MultiSelectorTemplate) {
                MultiSelectorTemplate multiSelectorTemplate = (MultiSelectorTemplate) obj;
                addAllOnBound(multiSelectorTemplate.onBound());
                ButtonElement buttonElementCallToActionButton = multiSelectorTemplate.callToActionButton();
                if (buttonElementCallToActionButton != null) {
                    callToActionButton(buttonElementCallToActionButton);
                }
                cacheSeconds(multiSelectorTemplate.cacheSeconds());
                ButtonElement buttonElementCloseButton = multiSelectorTemplate.closeButton();
                if (buttonElementCloseButton != null) {
                    closeButton(buttonElementCloseButton);
                }
                String strBackgroundImage = multiSelectorTemplate.backgroundImage();
                if (strBackgroundImage != null) {
                    backgroundImage(strBackgroundImage);
                }
                String strSubtitle = multiSelectorTemplate.subtitle();
                if (strSubtitle != null) {
                    subtitle(strSubtitle);
                }
                launchMode(multiSelectorTemplate.launchMode());
                TabNavigatorWidgetElement tabNavigatorWidgetElement = multiSelectorTemplate.tabNavigatorWidgetElement();
                if (tabNavigatorWidgetElement != null) {
                    tabNavigatorWidgetElement(tabNavigatorWidgetElement);
                }
                addAllOnViewed(multiSelectorTemplate.onViewed());
                String strTitle = multiSelectorTemplate.title();
                if (strTitle != null) {
                    title(strTitle);
                }
                addAllOnCreated(multiSelectorTemplate.onCreated());
                j = 31;
                addAllWidgetElements(multiSelectorTemplate.widgetElements());
            } else {
                j = 0;
            }
            if (obj instanceof Template) {
                Template template = (Template) obj;
                if ((j & 1) == 0) {
                    addAllOnBound(template.onBound());
                    j |= 1;
                }
                if ((j & 4) == 0) {
                    launchMode(template.launchMode());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    addAllOnViewed(template.onViewed());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    addAllOnCreated(template.onCreated());
                    j |= 16;
                }
                if ((j & 2) == 0) {
                    cacheSeconds(template.cacheSeconds());
                }
            }
        }

        public final Builder addOnCreated(Method method) {
            this.onCreated.add((Method) ImmutableMultiSelectorTemplate.requireNonNull(method, "onCreated element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCreated(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCreated.add((Method) ImmutableMultiSelectorTemplate.requireNonNull(method, "onCreated element"));
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
                this.onCreated.add((Method) ImmutableMultiSelectorTemplate.requireNonNull(it.next(), "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBound(Method method) {
            this.onBound.add((Method) ImmutableMultiSelectorTemplate.requireNonNull(method, "onBound element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnBound(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBound.add((Method) ImmutableMultiSelectorTemplate.requireNonNull(method, "onBound element"));
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
                this.onBound.add((Method) ImmutableMultiSelectorTemplate.requireNonNull(it.next(), "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableMultiSelectorTemplate.requireNonNull(method, "onViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableMultiSelectorTemplate.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableMultiSelectorTemplate.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("launchMode")
        public final Builder launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = (TemplateLaunchMode) ImmutableMultiSelectorTemplate.requireNonNull(templateLaunchMode, "launchMode");
            return this;
        }

        @JsonProperty("cacheSeconds")
        public final Builder cacheSeconds(Long l) {
            this.cacheSeconds = (Long) ImmutableMultiSelectorTemplate.requireNonNull(l, "cacheSeconds");
            return this;
        }

        @JsonProperty("title")
        public final Builder title(@Nullable String str) {
            this.title = str;
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_SUBTITLE)
        public final Builder subtitle(@Nullable String str) {
            this.subtitle = str;
            return this;
        }

        @JsonProperty("closeButton")
        public final Builder closeButton(@Nullable ButtonElement buttonElement) {
            this.closeButton = buttonElement;
            return this;
        }

        @JsonProperty("callToActionButton")
        public final Builder callToActionButton(@Nullable ButtonElement buttonElement) {
            this.callToActionButton = buttonElement;
            return this;
        }

        @JsonProperty("tabNavigatorWidgetElement")
        public final Builder tabNavigatorWidgetElement(@Nullable TabNavigatorWidgetElement tabNavigatorWidgetElement) {
            this.tabNavigatorWidgetElement = tabNavigatorWidgetElement;
            return this;
        }

        public final Builder addWidgetElements(WidgetElement widgetElement) {
            this.widgetElements.add((WidgetElement) ImmutableMultiSelectorTemplate.requireNonNull(widgetElement, "widgetElements element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addWidgetElements(WidgetElement... widgetElementArr) {
            for (WidgetElement widgetElement : widgetElementArr) {
                this.widgetElements.add((WidgetElement) ImmutableMultiSelectorTemplate.requireNonNull(widgetElement, "widgetElements element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("widgetElements")
        public final Builder widgetElements(Iterable<? extends WidgetElement> iterable) {
            this.widgetElements.clear();
            return addAllWidgetElements(iterable);
        }

        public final Builder addAllWidgetElements(Iterable<? extends WidgetElement> iterable) {
            Iterator<? extends WidgetElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.widgetElements.add((WidgetElement) ImmutableMultiSelectorTemplate.requireNonNull(it.next(), "widgetElements element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("backgroundImage")
        public final Builder backgroundImage(@Nullable String str) {
            this.backgroundImage = str;
            return this;
        }

        public ImmutableMultiSelectorTemplate build() {
            return new ImmutableMultiSelectorTemplate(this);
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
        public boolean widgetElementsIsSet() {
            return (this.optBits & 8) != 0;
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
