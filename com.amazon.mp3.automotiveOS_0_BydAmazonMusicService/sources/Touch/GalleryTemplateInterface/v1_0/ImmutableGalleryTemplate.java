package Touch.GalleryTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateLaunchMode;
import Touch.WidgetsInterface.v1_0.MessageElement;
import Touch.WidgetsInterface.v1_0.NavigationBarElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
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
@Generated(from = "GalleryTemplate", generator = "Immutables")
public final class ImmutableGalleryTemplate extends GalleryTemplate {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String backgroundColor;

    @Nullable
    private final String backgroundImage;

    @Nullable
    private final TextButtonElement button;
    private final Long cacheSeconds;
    private final boolean canSwipeToRefresh;
    private volatile transient InitShim initShim;
    private final TemplateLaunchMode launchMode;

    @Nullable
    private final String mainText;

    @Nullable
    private final MessageElement messageElement;

    @Nullable
    private final NavigationBarElement navigationBarElement;
    private final List<Method> onBound;
    private final List<Method> onCreated;
    private final List<Method> onViewed;
    private final List<WidgetElement> widgetElements;

    private ImmutableGalleryTemplate(Builder builder) {
        this.initShim = new InitShim();
        this.mainText = builder.mainText;
        this.backgroundColor = builder.backgroundColor;
        this.backgroundImage = builder.backgroundImage;
        this.navigationBarElement = builder.navigationBarElement;
        this.button = builder.button;
        this.messageElement = builder.messageElement;
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
        if (builder.canSwipeToRefreshIsSet()) {
            this.initShim.canSwipeToRefresh(builder.canSwipeToRefresh);
        }
        this.onCreated = this.initShim.onCreated();
        this.onBound = this.initShim.onBound();
        this.onViewed = this.initShim.onViewed();
        this.launchMode = this.initShim.launchMode();
        this.cacheSeconds = this.initShim.cacheSeconds();
        this.widgetElements = this.initShim.widgetElements();
        this.canSwipeToRefresh = this.initShim.canSwipeToRefresh();
        this.initShim = null;
    }

    private ImmutableGalleryTemplate(List<Method> list, List<Method> list2, List<Method> list3, TemplateLaunchMode templateLaunchMode, Long l, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable NavigationBarElement navigationBarElement, List<WidgetElement> list4, boolean z, @Nullable TextButtonElement textButtonElement, @Nullable MessageElement messageElement) {
        this.initShim = new InitShim();
        this.onCreated = list;
        this.onBound = list2;
        this.onViewed = list3;
        this.launchMode = templateLaunchMode;
        this.cacheSeconds = l;
        this.mainText = str;
        this.backgroundColor = str2;
        this.backgroundImage = str3;
        this.navigationBarElement = navigationBarElement;
        this.widgetElements = list4;
        this.canSwipeToRefresh = z;
        this.button = textButtonElement;
        this.messageElement = messageElement;
        this.initShim = null;
    }

    @Generated(from = "GalleryTemplate", generator = "Immutables")
    private final class InitShim {
        private Long cacheSeconds;
        private byte cacheSecondsBuildStage;
        private boolean canSwipeToRefresh;
        private byte canSwipeToRefreshBuildStage;
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
            this.canSwipeToRefreshBuildStage = (byte) 0;
        }

        List<Method> onCreated() {
            byte b = this.onCreatedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCreatedBuildStage = (byte) -1;
                this.onCreated = ImmutableGalleryTemplate.createUnmodifiableList(false, ImmutableGalleryTemplate.createSafeList(ImmutableGalleryTemplate.super.onCreated(), true, false));
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
                this.onBound = ImmutableGalleryTemplate.createUnmodifiableList(false, ImmutableGalleryTemplate.createSafeList(ImmutableGalleryTemplate.super.onBound(), true, false));
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
                this.onViewed = ImmutableGalleryTemplate.createUnmodifiableList(false, ImmutableGalleryTemplate.createSafeList(ImmutableGalleryTemplate.super.onViewed(), true, false));
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
                this.launchMode = (TemplateLaunchMode) ImmutableGalleryTemplate.requireNonNull(ImmutableGalleryTemplate.super.launchMode(), "launchMode");
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
                this.cacheSeconds = (Long) ImmutableGalleryTemplate.requireNonNull(ImmutableGalleryTemplate.super.cacheSeconds(), "cacheSeconds");
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
                this.widgetElements = ImmutableGalleryTemplate.createUnmodifiableList(false, ImmutableGalleryTemplate.createSafeList(ImmutableGalleryTemplate.super.widgetElements(), true, false));
                this.widgetElementsBuildStage = (byte) 1;
            }
            return this.widgetElements;
        }

        void widgetElements(List<WidgetElement> list) {
            this.widgetElements = list;
            this.widgetElementsBuildStage = (byte) 1;
        }

        boolean canSwipeToRefresh() {
            byte b = this.canSwipeToRefreshBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.canSwipeToRefreshBuildStage = (byte) -1;
                this.canSwipeToRefresh = ImmutableGalleryTemplate.super.canSwipeToRefresh();
                this.canSwipeToRefreshBuildStage = (byte) 1;
            }
            return this.canSwipeToRefresh;
        }

        void canSwipeToRefresh(boolean z) {
            this.canSwipeToRefresh = z;
            this.canSwipeToRefreshBuildStage = (byte) 1;
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
            if (this.canSwipeToRefreshBuildStage == -1) {
                arrayList.add("canSwipeToRefresh");
            }
            return "Cannot build GalleryTemplate, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
    @JsonProperty("mainText")
    @Nullable
    public String mainText() {
        return this.mainText;
    }

    @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
    @JsonProperty("backgroundColor")
    @Nullable
    public String backgroundColor() {
        return this.backgroundColor;
    }

    @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
    @JsonProperty("backgroundImage")
    @Nullable
    public String backgroundImage() {
        return this.backgroundImage;
    }

    @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
    @JsonProperty("navigationBarElement")
    @Nullable
    public NavigationBarElement navigationBarElement() {
        return this.navigationBarElement;
    }

    @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
    @JsonProperty("widgetElements")
    public List<WidgetElement> widgetElements() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.widgetElements();
        }
        return this.widgetElements;
    }

    @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
    @JsonProperty("canSwipeToRefresh")
    public boolean canSwipeToRefresh() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.canSwipeToRefresh();
        }
        return this.canSwipeToRefresh;
    }

    @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
    @JsonProperty("button")
    @Nullable
    public TextButtonElement button() {
        return this.button;
    }

    @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
    @JsonProperty("messageElement")
    @Nullable
    public MessageElement messageElement() {
        return this.messageElement;
    }

    public final ImmutableGalleryTemplate withOnCreated(Method... methodArr) {
        return new ImmutableGalleryTemplate(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withOnCreated(Iterable<? extends Method> iterable) {
        return this.onCreated == iterable ? this : new ImmutableGalleryTemplate(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withOnBound(Method... methodArr) {
        return new ImmutableGalleryTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withOnBound(Iterable<? extends Method> iterable) {
        if (this.onBound == iterable) {
            return this;
        }
        return new ImmutableGalleryTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withOnViewed(Method... methodArr) {
        return new ImmutableGalleryTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableGalleryTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withLaunchMode(TemplateLaunchMode templateLaunchMode) {
        TemplateLaunchMode templateLaunchMode2 = (TemplateLaunchMode) requireNonNull(templateLaunchMode, "launchMode");
        return this.launchMode == templateLaunchMode2 ? this : new ImmutableGalleryTemplate(this.onCreated, this.onBound, this.onViewed, templateLaunchMode2, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withCacheSeconds(Long l) {
        Long l2 = (Long) requireNonNull(l, "cacheSeconds");
        return this.cacheSeconds.equals(l2) ? this : new ImmutableGalleryTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, l2, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withMainText(@Nullable String str) {
        return equals(this.mainText, str) ? this : new ImmutableGalleryTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, str, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withBackgroundColor(@Nullable String str) {
        return equals(this.backgroundColor, str) ? this : new ImmutableGalleryTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, str, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withBackgroundImage(@Nullable String str) {
        return equals(this.backgroundImage, str) ? this : new ImmutableGalleryTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, str, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withNavigationBarElement(@Nullable NavigationBarElement navigationBarElement) {
        return this.navigationBarElement == navigationBarElement ? this : new ImmutableGalleryTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withWidgetElements(WidgetElement... widgetElementArr) {
        return new ImmutableGalleryTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, createUnmodifiableList(false, createSafeList(Arrays.asList(widgetElementArr), true, false)), this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withWidgetElements(Iterable<? extends WidgetElement> iterable) {
        if (this.widgetElements == iterable) {
            return this;
        }
        return new ImmutableGalleryTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.canSwipeToRefresh, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withCanSwipeToRefresh(boolean z) {
        return this.canSwipeToRefresh == z ? this : new ImmutableGalleryTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, z, this.button, this.messageElement);
    }

    public final ImmutableGalleryTemplate withButton(@Nullable TextButtonElement textButtonElement) {
        return this.button == textButtonElement ? this : new ImmutableGalleryTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, textButtonElement, this.messageElement);
    }

    public final ImmutableGalleryTemplate withMessageElement(@Nullable MessageElement messageElement) {
        return this.messageElement == messageElement ? this : new ImmutableGalleryTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.mainText, this.backgroundColor, this.backgroundImage, this.navigationBarElement, this.widgetElements, this.canSwipeToRefresh, this.button, messageElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableGalleryTemplate) && equalTo(0, (ImmutableGalleryTemplate) obj);
    }

    private boolean equalTo(int i, ImmutableGalleryTemplate immutableGalleryTemplate) {
        return this.onCreated.equals(immutableGalleryTemplate.onCreated) && this.onBound.equals(immutableGalleryTemplate.onBound) && this.onViewed.equals(immutableGalleryTemplate.onViewed) && this.launchMode.equals(immutableGalleryTemplate.launchMode) && this.cacheSeconds.equals(immutableGalleryTemplate.cacheSeconds) && equals(this.mainText, immutableGalleryTemplate.mainText) && equals(this.backgroundColor, immutableGalleryTemplate.backgroundColor) && equals(this.backgroundImage, immutableGalleryTemplate.backgroundImage) && equals(this.navigationBarElement, immutableGalleryTemplate.navigationBarElement) && this.widgetElements.equals(immutableGalleryTemplate.widgetElements) && this.canSwipeToRefresh == immutableGalleryTemplate.canSwipeToRefresh && equals(this.button, immutableGalleryTemplate.button) && equals(this.messageElement, immutableGalleryTemplate.messageElement);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onCreated.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onBound.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.launchMode.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.cacheSeconds.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.mainText);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.backgroundColor);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.backgroundImage);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.navigationBarElement);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + this.widgetElements.hashCode();
        int i = iHashCode10 + (iHashCode10 << 5) + (this.canSwipeToRefresh ? 1231 : 1237);
        int iHashCode11 = i + (i << 5) + hashCode(this.button);
        return iHashCode11 + (iHashCode11 << 5) + hashCode(this.messageElement);
    }

    public String toString() {
        return "GalleryTemplate{onCreated=" + this.onCreated + ", onBound=" + this.onBound + ", onViewed=" + this.onViewed + ", launchMode=" + this.launchMode + ", cacheSeconds=" + this.cacheSeconds + ", mainText=" + this.mainText + ", backgroundColor=" + this.backgroundColor + ", backgroundImage=" + this.backgroundImage + ", navigationBarElement=" + this.navigationBarElement + ", widgetElements=" + this.widgetElements + ", canSwipeToRefresh=" + this.canSwipeToRefresh + ", button=" + this.button + ", messageElement=" + this.messageElement + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "GalleryTemplate", generator = "Immutables")
    static final class Json extends GalleryTemplate {

        @Nullable
        String backgroundColor;

        @Nullable
        String backgroundImage;

        @Nullable
        TextButtonElement button;

        @Nullable
        Long cacheSeconds;
        boolean canSwipeToRefresh;
        boolean canSwipeToRefreshIsSet;

        @Nullable
        TemplateLaunchMode launchMode;

        @Nullable
        String mainText;

        @Nullable
        MessageElement messageElement;

        @Nullable
        NavigationBarElement navigationBarElement;
        boolean onBoundIsSet;
        boolean onCreatedIsSet;
        boolean onViewedIsSet;
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

        @JsonProperty("mainText")
        public void setMainText(@Nullable String str) {
            this.mainText = str;
        }

        @JsonProperty("backgroundColor")
        public void setBackgroundColor(@Nullable String str) {
            this.backgroundColor = str;
        }

        @JsonProperty("backgroundImage")
        public void setBackgroundImage(@Nullable String str) {
            this.backgroundImage = str;
        }

        @JsonProperty("navigationBarElement")
        public void setNavigationBarElement(@Nullable NavigationBarElement navigationBarElement) {
            this.navigationBarElement = navigationBarElement;
        }

        @JsonProperty("widgetElements")
        public void setWidgetElements(List<WidgetElement> list) {
            this.widgetElements = list;
            this.widgetElementsIsSet = true;
        }

        @JsonProperty("canSwipeToRefresh")
        public void setCanSwipeToRefresh(boolean z) {
            this.canSwipeToRefresh = z;
            this.canSwipeToRefreshIsSet = true;
        }

        @JsonProperty("button")
        public void setButton(@Nullable TextButtonElement textButtonElement) {
            this.button = textButtonElement;
        }

        @JsonProperty("messageElement")
        public void setMessageElement(@Nullable MessageElement messageElement) {
            this.messageElement = messageElement;
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

        @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
        public String mainText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
        public String backgroundColor() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
        public String backgroundImage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
        public NavigationBarElement navigationBarElement() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
        public List<WidgetElement> widgetElements() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
        public boolean canSwipeToRefresh() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
        public TextButtonElement button() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GalleryTemplateInterface.v1_0.GalleryTemplate
        public MessageElement messageElement() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableGalleryTemplate fromJson(Json json) {
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
        String str = json.mainText;
        if (str != null) {
            builder.mainText(str);
        }
        String str2 = json.backgroundColor;
        if (str2 != null) {
            builder.backgroundColor(str2);
        }
        String str3 = json.backgroundImage;
        if (str3 != null) {
            builder.backgroundImage(str3);
        }
        NavigationBarElement navigationBarElement = json.navigationBarElement;
        if (navigationBarElement != null) {
            builder.navigationBarElement(navigationBarElement);
        }
        if (json.widgetElementsIsSet) {
            builder.addAllWidgetElements(json.widgetElements);
        }
        if (json.canSwipeToRefreshIsSet) {
            builder.canSwipeToRefresh(json.canSwipeToRefresh);
        }
        TextButtonElement textButtonElement = json.button;
        if (textButtonElement != null) {
            builder.button(textButtonElement);
        }
        MessageElement messageElement = json.messageElement;
        if (messageElement != null) {
            builder.messageElement(messageElement);
        }
        return builder.build();
    }

    public static ImmutableGalleryTemplate copyOf(GalleryTemplate galleryTemplate) {
        if (galleryTemplate instanceof ImmutableGalleryTemplate) {
            return (ImmutableGalleryTemplate) galleryTemplate;
        }
        return builder().from(galleryTemplate).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "GalleryTemplate", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_CAN_SWIPE_TO_REFRESH = 16;
        private static final long OPT_BIT_ON_BOUND = 2;
        private static final long OPT_BIT_ON_CREATED = 1;
        private static final long OPT_BIT_ON_VIEWED = 4;
        private static final long OPT_BIT_WIDGET_ELEMENTS = 8;

        @Nullable
        private String backgroundColor;

        @Nullable
        private String backgroundImage;

        @Nullable
        private TextButtonElement button;

        @Nullable
        private Long cacheSeconds;
        private boolean canSwipeToRefresh;

        @Nullable
        private TemplateLaunchMode launchMode;

        @Nullable
        private String mainText;

        @Nullable
        private MessageElement messageElement;

        @Nullable
        private NavigationBarElement navigationBarElement;
        private List<Method> onBound;
        private List<Method> onCreated;
        private List<Method> onViewed;
        private long optBits;
        private List<WidgetElement> widgetElements;

        private Builder() {
            this.onCreated = new ArrayList();
            this.onBound = new ArrayList();
            this.onViewed = new ArrayList();
            this.widgetElements = new ArrayList();
        }

        public final Builder from(GalleryTemplate galleryTemplate) {
            ImmutableGalleryTemplate.requireNonNull(galleryTemplate, "instance");
            from((Object) galleryTemplate);
            return this;
        }

        public final Builder from(Template template) {
            ImmutableGalleryTemplate.requireNonNull(template, "instance");
            from((Object) template);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof GalleryTemplate) {
                GalleryTemplate galleryTemplate = (GalleryTemplate) obj;
                addAllOnBound(galleryTemplate.onBound());
                String strBackgroundColor = galleryTemplate.backgroundColor();
                if (strBackgroundColor != null) {
                    backgroundColor(strBackgroundColor);
                }
                canSwipeToRefresh(galleryTemplate.canSwipeToRefresh());
                NavigationBarElement navigationBarElement = galleryTemplate.navigationBarElement();
                if (navigationBarElement != null) {
                    navigationBarElement(navigationBarElement);
                }
                String strBackgroundImage = galleryTemplate.backgroundImage();
                if (strBackgroundImage != null) {
                    backgroundImage(strBackgroundImage);
                }
                addAllOnViewed(galleryTemplate.onViewed());
                MessageElement messageElement = galleryTemplate.messageElement();
                if (messageElement != null) {
                    messageElement(messageElement);
                }
                addAllWidgetElements(galleryTemplate.widgetElements());
                TextButtonElement textButtonElementButton = galleryTemplate.button();
                if (textButtonElementButton != null) {
                    button(textButtonElementButton);
                }
                cacheSeconds(galleryTemplate.cacheSeconds());
                launchMode(galleryTemplate.launchMode());
                String strMainText = galleryTemplate.mainText();
                if (strMainText != null) {
                    mainText(strMainText);
                }
                addAllOnCreated(galleryTemplate.onCreated());
                j = 31;
            } else {
                j = 0;
            }
            if (obj instanceof Template) {
                Template template = (Template) obj;
                if ((j & 1) == 0) {
                    addAllOnBound(template.onBound());
                    j |= 1;
                }
                if ((j & 8) == 0) {
                    launchMode(template.launchMode());
                    j |= 8;
                }
                if ((j & 2) == 0) {
                    addAllOnViewed(template.onViewed());
                    j |= 2;
                }
                if ((j & 16) == 0) {
                    addAllOnCreated(template.onCreated());
                    j |= 16;
                }
                if ((j & 4) == 0) {
                    cacheSeconds(template.cacheSeconds());
                }
            }
        }

        public final Builder addOnCreated(Method method) {
            this.onCreated.add((Method) ImmutableGalleryTemplate.requireNonNull(method, "onCreated element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCreated(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCreated.add((Method) ImmutableGalleryTemplate.requireNonNull(method, "onCreated element"));
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
                this.onCreated.add((Method) ImmutableGalleryTemplate.requireNonNull(it.next(), "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBound(Method method) {
            this.onBound.add((Method) ImmutableGalleryTemplate.requireNonNull(method, "onBound element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnBound(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBound.add((Method) ImmutableGalleryTemplate.requireNonNull(method, "onBound element"));
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
                this.onBound.add((Method) ImmutableGalleryTemplate.requireNonNull(it.next(), "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableGalleryTemplate.requireNonNull(method, "onViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableGalleryTemplate.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableGalleryTemplate.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("launchMode")
        public final Builder launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = (TemplateLaunchMode) ImmutableGalleryTemplate.requireNonNull(templateLaunchMode, "launchMode");
            return this;
        }

        @JsonProperty("cacheSeconds")
        public final Builder cacheSeconds(Long l) {
            this.cacheSeconds = (Long) ImmutableGalleryTemplate.requireNonNull(l, "cacheSeconds");
            return this;
        }

        @JsonProperty("mainText")
        public final Builder mainText(@Nullable String str) {
            this.mainText = str;
            return this;
        }

        @JsonProperty("backgroundColor")
        public final Builder backgroundColor(@Nullable String str) {
            this.backgroundColor = str;
            return this;
        }

        @JsonProperty("backgroundImage")
        public final Builder backgroundImage(@Nullable String str) {
            this.backgroundImage = str;
            return this;
        }

        @JsonProperty("navigationBarElement")
        public final Builder navigationBarElement(@Nullable NavigationBarElement navigationBarElement) {
            this.navigationBarElement = navigationBarElement;
            return this;
        }

        public final Builder addWidgetElements(WidgetElement widgetElement) {
            this.widgetElements.add((WidgetElement) ImmutableGalleryTemplate.requireNonNull(widgetElement, "widgetElements element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addWidgetElements(WidgetElement... widgetElementArr) {
            for (WidgetElement widgetElement : widgetElementArr) {
                this.widgetElements.add((WidgetElement) ImmutableGalleryTemplate.requireNonNull(widgetElement, "widgetElements element"));
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
                this.widgetElements.add((WidgetElement) ImmutableGalleryTemplate.requireNonNull(it.next(), "widgetElements element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("canSwipeToRefresh")
        public final Builder canSwipeToRefresh(boolean z) {
            this.canSwipeToRefresh = z;
            this.optBits |= 16;
            return this;
        }

        @JsonProperty("button")
        public final Builder button(@Nullable TextButtonElement textButtonElement) {
            this.button = textButtonElement;
            return this;
        }

        @JsonProperty("messageElement")
        public final Builder messageElement(@Nullable MessageElement messageElement) {
            this.messageElement = messageElement;
            return this;
        }

        public ImmutableGalleryTemplate build() {
            return new ImmutableGalleryTemplate(this);
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

        /* JADX INFO: Access modifiers changed from: private */
        public boolean canSwipeToRefreshIsSet() {
            return (this.optBits & 16) != 0;
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
