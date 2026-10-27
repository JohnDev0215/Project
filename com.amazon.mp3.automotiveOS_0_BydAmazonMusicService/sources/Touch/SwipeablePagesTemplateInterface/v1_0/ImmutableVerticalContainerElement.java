package Touch.SwipeablePagesTemplateInterface.v1_0;

import CoreInterface.v1_0.Element;
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
@Generated(from = "VerticalContainerElement", generator = "Immutables")
public final class ImmutableVerticalContainerElement extends VerticalContainerElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String backgroundImage;
    private final Boolean canSpillOverToNextSection;
    private final List<Element> elements;
    private volatile transient InitShim initShim;
    private final List<Method> onLongPressed;
    private final List<Method> onPreviewClicked;
    private final List<Method> onViewed;
    private final List<Method> onViewedPreview;
    private final Boolean shouldBlurBackground;

    private ImmutableVerticalContainerElement(Builder builder) {
        this.initShim = new InitShim();
        this.elements = createUnmodifiableList(true, builder.elements);
        this.backgroundImage = builder.backgroundImage;
        if (builder.shouldBlurBackground != null) {
            this.initShim.shouldBlurBackground(builder.shouldBlurBackground);
        }
        if (builder.canSpillOverToNextSection != null) {
            this.initShim.canSpillOverToNextSection(builder.canSpillOverToNextSection);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.onViewedPreviewIsSet()) {
            this.initShim.onViewedPreview(createUnmodifiableList(true, builder.onViewedPreview));
        }
        if (builder.onPreviewClickedIsSet()) {
            this.initShim.onPreviewClicked(createUnmodifiableList(true, builder.onPreviewClicked));
        }
        if (builder.onLongPressedIsSet()) {
            this.initShim.onLongPressed(createUnmodifiableList(true, builder.onLongPressed));
        }
        this.shouldBlurBackground = this.initShim.shouldBlurBackground();
        this.canSpillOverToNextSection = this.initShim.canSpillOverToNextSection();
        this.onViewed = this.initShim.onViewed();
        this.onViewedPreview = this.initShim.onViewedPreview();
        this.onPreviewClicked = this.initShim.onPreviewClicked();
        this.onLongPressed = this.initShim.onLongPressed();
        this.initShim = null;
    }

    private ImmutableVerticalContainerElement(List<Element> list, @Nullable String str, Boolean bool, Boolean bool2, List<Method> list2, List<Method> list3, List<Method> list4, List<Method> list5) {
        this.initShim = new InitShim();
        this.elements = list;
        this.backgroundImage = str;
        this.shouldBlurBackground = bool;
        this.canSpillOverToNextSection = bool2;
        this.onViewed = list2;
        this.onViewedPreview = list3;
        this.onPreviewClicked = list4;
        this.onLongPressed = list5;
        this.initShim = null;
    }

    @Generated(from = "VerticalContainerElement", generator = "Immutables")
    private final class InitShim {
        private Boolean canSpillOverToNextSection;
        private byte canSpillOverToNextSectionBuildStage;
        private List<Method> onLongPressed;
        private byte onLongPressedBuildStage;
        private List<Method> onPreviewClicked;
        private byte onPreviewClickedBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private List<Method> onViewedPreview;
        private byte onViewedPreviewBuildStage;
        private Boolean shouldBlurBackground;
        private byte shouldBlurBackgroundBuildStage;

        private InitShim() {
            this.shouldBlurBackgroundBuildStage = (byte) 0;
            this.canSpillOverToNextSectionBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.onViewedPreviewBuildStage = (byte) 0;
            this.onPreviewClickedBuildStage = (byte) 0;
            this.onLongPressedBuildStage = (byte) 0;
        }

        Boolean shouldBlurBackground() {
            byte b = this.shouldBlurBackgroundBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.shouldBlurBackgroundBuildStage = (byte) -1;
                this.shouldBlurBackground = (Boolean) ImmutableVerticalContainerElement.requireNonNull(ImmutableVerticalContainerElement.super.shouldBlurBackground(), "shouldBlurBackground");
                this.shouldBlurBackgroundBuildStage = (byte) 1;
            }
            return this.shouldBlurBackground;
        }

        void shouldBlurBackground(Boolean bool) {
            this.shouldBlurBackground = bool;
            this.shouldBlurBackgroundBuildStage = (byte) 1;
        }

        Boolean canSpillOverToNextSection() {
            byte b = this.canSpillOverToNextSectionBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.canSpillOverToNextSectionBuildStage = (byte) -1;
                this.canSpillOverToNextSection = (Boolean) ImmutableVerticalContainerElement.requireNonNull(ImmutableVerticalContainerElement.super.canSpillOverToNextSection(), "canSpillOverToNextSection");
                this.canSpillOverToNextSectionBuildStage = (byte) 1;
            }
            return this.canSpillOverToNextSection;
        }

        void canSpillOverToNextSection(Boolean bool) {
            this.canSpillOverToNextSection = bool;
            this.canSpillOverToNextSectionBuildStage = (byte) 1;
        }

        List<Method> onViewed() {
            byte b = this.onViewedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onViewedBuildStage = (byte) -1;
                this.onViewed = ImmutableVerticalContainerElement.createUnmodifiableList(false, ImmutableVerticalContainerElement.createSafeList(ImmutableVerticalContainerElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        List<Method> onViewedPreview() {
            byte b = this.onViewedPreviewBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onViewedPreviewBuildStage = (byte) -1;
                this.onViewedPreview = ImmutableVerticalContainerElement.createUnmodifiableList(false, ImmutableVerticalContainerElement.createSafeList(ImmutableVerticalContainerElement.super.onViewedPreview(), true, false));
                this.onViewedPreviewBuildStage = (byte) 1;
            }
            return this.onViewedPreview;
        }

        void onViewedPreview(List<Method> list) {
            this.onViewedPreview = list;
            this.onViewedPreviewBuildStage = (byte) 1;
        }

        List<Method> onPreviewClicked() {
            byte b = this.onPreviewClickedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onPreviewClickedBuildStage = (byte) -1;
                this.onPreviewClicked = ImmutableVerticalContainerElement.createUnmodifiableList(false, ImmutableVerticalContainerElement.createSafeList(ImmutableVerticalContainerElement.super.onPreviewClicked(), true, false));
                this.onPreviewClickedBuildStage = (byte) 1;
            }
            return this.onPreviewClicked;
        }

        void onPreviewClicked(List<Method> list) {
            this.onPreviewClicked = list;
            this.onPreviewClickedBuildStage = (byte) 1;
        }

        List<Method> onLongPressed() {
            byte b = this.onLongPressedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onLongPressedBuildStage = (byte) -1;
                this.onLongPressed = ImmutableVerticalContainerElement.createUnmodifiableList(false, ImmutableVerticalContainerElement.createSafeList(ImmutableVerticalContainerElement.super.onLongPressed(), true, false));
                this.onLongPressedBuildStage = (byte) 1;
            }
            return this.onLongPressed;
        }

        void onLongPressed(List<Method> list) {
            this.onLongPressed = list;
            this.onLongPressedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.shouldBlurBackgroundBuildStage == -1) {
                arrayList.add("shouldBlurBackground");
            }
            if (this.canSpillOverToNextSectionBuildStage == -1) {
                arrayList.add("canSpillOverToNextSection");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.onViewedPreviewBuildStage == -1) {
                arrayList.add("onViewedPreview");
            }
            if (this.onPreviewClickedBuildStage == -1) {
                arrayList.add("onPreviewClicked");
            }
            if (this.onLongPressedBuildStage == -1) {
                arrayList.add("onLongPressed");
            }
            return "Cannot build VerticalContainerElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
    @JsonProperty(ContextMappingConstants.ELEMENTS)
    public List<Element> elements() {
        return this.elements;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
    @JsonProperty("backgroundImage")
    @Nullable
    public String backgroundImage() {
        return this.backgroundImage;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
    @JsonProperty("shouldBlurBackground")
    public Boolean shouldBlurBackground() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shouldBlurBackground();
        }
        return this.shouldBlurBackground;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
    @JsonProperty("canSpillOverToNextSection")
    public Boolean canSpillOverToNextSection() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.canSpillOverToNextSection();
        }
        return this.canSpillOverToNextSection;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
    @JsonProperty("onViewed")
    public List<Method> onViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onViewed();
        }
        return this.onViewed;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
    @JsonProperty("onViewedPreview")
    public List<Method> onViewedPreview() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onViewedPreview();
        }
        return this.onViewedPreview;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
    @JsonProperty("onPreviewClicked")
    public List<Method> onPreviewClicked() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onPreviewClicked();
        }
        return this.onPreviewClicked;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
    @JsonProperty("onLongPressed")
    public List<Method> onLongPressed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onLongPressed();
        }
        return this.onLongPressed;
    }

    public final ImmutableVerticalContainerElement withElements(Element... elementArr) {
        return new ImmutableVerticalContainerElement(createUnmodifiableList(false, createSafeList(Arrays.asList(elementArr), true, false)), this.backgroundImage, this.shouldBlurBackground, this.canSpillOverToNextSection, this.onViewed, this.onViewedPreview, this.onPreviewClicked, this.onLongPressed);
    }

    public final ImmutableVerticalContainerElement withElements(Iterable<? extends Element> iterable) {
        return this.elements == iterable ? this : new ImmutableVerticalContainerElement(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.backgroundImage, this.shouldBlurBackground, this.canSpillOverToNextSection, this.onViewed, this.onViewedPreview, this.onPreviewClicked, this.onLongPressed);
    }

    public final ImmutableVerticalContainerElement withBackgroundImage(@Nullable String str) {
        return equals(this.backgroundImage, str) ? this : new ImmutableVerticalContainerElement(this.elements, str, this.shouldBlurBackground, this.canSpillOverToNextSection, this.onViewed, this.onViewedPreview, this.onPreviewClicked, this.onLongPressed);
    }

    public final ImmutableVerticalContainerElement withShouldBlurBackground(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "shouldBlurBackground");
        return this.shouldBlurBackground.equals(bool2) ? this : new ImmutableVerticalContainerElement(this.elements, this.backgroundImage, bool2, this.canSpillOverToNextSection, this.onViewed, this.onViewedPreview, this.onPreviewClicked, this.onLongPressed);
    }

    public final ImmutableVerticalContainerElement withCanSpillOverToNextSection(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "canSpillOverToNextSection");
        return this.canSpillOverToNextSection.equals(bool2) ? this : new ImmutableVerticalContainerElement(this.elements, this.backgroundImage, this.shouldBlurBackground, bool2, this.onViewed, this.onViewedPreview, this.onPreviewClicked, this.onLongPressed);
    }

    public final ImmutableVerticalContainerElement withOnViewed(Method... methodArr) {
        return new ImmutableVerticalContainerElement(this.elements, this.backgroundImage, this.shouldBlurBackground, this.canSpillOverToNextSection, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewedPreview, this.onPreviewClicked, this.onLongPressed);
    }

    public final ImmutableVerticalContainerElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableVerticalContainerElement(this.elements, this.backgroundImage, this.shouldBlurBackground, this.canSpillOverToNextSection, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewedPreview, this.onPreviewClicked, this.onLongPressed);
    }

    public final ImmutableVerticalContainerElement withOnViewedPreview(Method... methodArr) {
        return new ImmutableVerticalContainerElement(this.elements, this.backgroundImage, this.shouldBlurBackground, this.canSpillOverToNextSection, this.onViewed, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onPreviewClicked, this.onLongPressed);
    }

    public final ImmutableVerticalContainerElement withOnViewedPreview(Iterable<? extends Method> iterable) {
        if (this.onViewedPreview == iterable) {
            return this;
        }
        return new ImmutableVerticalContainerElement(this.elements, this.backgroundImage, this.shouldBlurBackground, this.canSpillOverToNextSection, this.onViewed, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onPreviewClicked, this.onLongPressed);
    }

    public final ImmutableVerticalContainerElement withOnPreviewClicked(Method... methodArr) {
        return new ImmutableVerticalContainerElement(this.elements, this.backgroundImage, this.shouldBlurBackground, this.canSpillOverToNextSection, this.onViewed, this.onViewedPreview, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onLongPressed);
    }

    public final ImmutableVerticalContainerElement withOnPreviewClicked(Iterable<? extends Method> iterable) {
        if (this.onPreviewClicked == iterable) {
            return this;
        }
        return new ImmutableVerticalContainerElement(this.elements, this.backgroundImage, this.shouldBlurBackground, this.canSpillOverToNextSection, this.onViewed, this.onViewedPreview, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onLongPressed);
    }

    public final ImmutableVerticalContainerElement withOnLongPressed(Method... methodArr) {
        return new ImmutableVerticalContainerElement(this.elements, this.backgroundImage, this.shouldBlurBackground, this.canSpillOverToNextSection, this.onViewed, this.onViewedPreview, this.onPreviewClicked, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableVerticalContainerElement withOnLongPressed(Iterable<? extends Method> iterable) {
        if (this.onLongPressed == iterable) {
            return this;
        }
        return new ImmutableVerticalContainerElement(this.elements, this.backgroundImage, this.shouldBlurBackground, this.canSpillOverToNextSection, this.onViewed, this.onViewedPreview, this.onPreviewClicked, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVerticalContainerElement) && equalTo(0, (ImmutableVerticalContainerElement) obj);
    }

    private boolean equalTo(int i, ImmutableVerticalContainerElement immutableVerticalContainerElement) {
        return this.elements.equals(immutableVerticalContainerElement.elements) && equals(this.backgroundImage, immutableVerticalContainerElement.backgroundImage) && this.shouldBlurBackground.equals(immutableVerticalContainerElement.shouldBlurBackground) && this.canSpillOverToNextSection.equals(immutableVerticalContainerElement.canSpillOverToNextSection) && this.onViewed.equals(immutableVerticalContainerElement.onViewed) && this.onViewedPreview.equals(immutableVerticalContainerElement.onViewedPreview) && this.onPreviewClicked.equals(immutableVerticalContainerElement.onPreviewClicked) && this.onLongPressed.equals(immutableVerticalContainerElement.onLongPressed);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.elements.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.backgroundImage);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.shouldBlurBackground.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.canSpillOverToNextSection.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.onViewed.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.onViewedPreview.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.onPreviewClicked.hashCode();
        return iHashCode7 + (iHashCode7 << 5) + this.onLongPressed.hashCode();
    }

    public String toString() {
        return "VerticalContainerElement{elements=" + this.elements + ", backgroundImage=" + this.backgroundImage + ", shouldBlurBackground=" + this.shouldBlurBackground + ", canSpillOverToNextSection=" + this.canSpillOverToNextSection + ", onViewed=" + this.onViewed + ", onViewedPreview=" + this.onViewedPreview + ", onPreviewClicked=" + this.onPreviewClicked + ", onLongPressed=" + this.onLongPressed + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VerticalContainerElement", generator = "Immutables")
    static final class Json extends VerticalContainerElement {

        @Nullable
        String backgroundImage;

        @Nullable
        Boolean canSpillOverToNextSection;
        boolean onLongPressedIsSet;
        boolean onPreviewClickedIsSet;
        boolean onViewedIsSet;
        boolean onViewedPreviewIsSet;

        @Nullable
        Boolean shouldBlurBackground;

        @Nullable
        List<Element> elements = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<Method> onViewedPreview = Collections.emptyList();

        @Nullable
        List<Method> onPreviewClicked = Collections.emptyList();

        @Nullable
        List<Method> onLongPressed = Collections.emptyList();

        Json() {
        }

        @JsonProperty(ContextMappingConstants.ELEMENTS)
        public void setElements(List<Element> list) {
            this.elements = list;
        }

        @JsonProperty("backgroundImage")
        public void setBackgroundImage(@Nullable String str) {
            this.backgroundImage = str;
        }

        @JsonProperty("shouldBlurBackground")
        public void setShouldBlurBackground(Boolean bool) {
            this.shouldBlurBackground = bool;
        }

        @JsonProperty("canSpillOverToNextSection")
        public void setCanSpillOverToNextSection(Boolean bool) {
            this.canSpillOverToNextSection = bool;
        }

        @JsonProperty("onViewed")
        public void setOnViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedIsSet = true;
        }

        @JsonProperty("onViewedPreview")
        public void setOnViewedPreview(List<Method> list) {
            this.onViewedPreview = list;
            this.onViewedPreviewIsSet = true;
        }

        @JsonProperty("onPreviewClicked")
        public void setOnPreviewClicked(List<Method> list) {
            this.onPreviewClicked = list;
            this.onPreviewClickedIsSet = true;
        }

        @JsonProperty("onLongPressed")
        public void setOnLongPressed(List<Method> list) {
            this.onLongPressed = list;
            this.onLongPressedIsSet = true;
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
        public List<Element> elements() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
        public String backgroundImage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
        public Boolean shouldBlurBackground() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
        public Boolean canSpillOverToNextSection() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
        public List<Method> onViewedPreview() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
        public List<Method> onPreviewClicked() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement
        public List<Method> onLongPressed() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVerticalContainerElement fromJson(Json json) {
        Builder builder = builder();
        List<Element> list = json.elements;
        if (list != null) {
            builder.addAllElements(list);
        }
        String str = json.backgroundImage;
        if (str != null) {
            builder.backgroundImage(str);
        }
        Boolean bool = json.shouldBlurBackground;
        if (bool != null) {
            builder.shouldBlurBackground(bool);
        }
        Boolean bool2 = json.canSpillOverToNextSection;
        if (bool2 != null) {
            builder.canSpillOverToNextSection(bool2);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        if (json.onViewedPreviewIsSet) {
            builder.addAllOnViewedPreview(json.onViewedPreview);
        }
        if (json.onPreviewClickedIsSet) {
            builder.addAllOnPreviewClicked(json.onPreviewClicked);
        }
        if (json.onLongPressedIsSet) {
            builder.addAllOnLongPressed(json.onLongPressed);
        }
        return builder.build();
    }

    public static ImmutableVerticalContainerElement copyOf(VerticalContainerElement verticalContainerElement) {
        if (verticalContainerElement instanceof ImmutableVerticalContainerElement) {
            return (ImmutableVerticalContainerElement) verticalContainerElement;
        }
        return builder().from(verticalContainerElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VerticalContainerElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_LONG_PRESSED = 8;
        private static final long OPT_BIT_ON_PREVIEW_CLICKED = 4;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private static final long OPT_BIT_ON_VIEWED_PREVIEW = 2;

        @Nullable
        private String backgroundImage;

        @Nullable
        private Boolean canSpillOverToNextSection;
        private List<Element> elements;
        private List<Method> onLongPressed;
        private List<Method> onPreviewClicked;
        private List<Method> onViewed;
        private List<Method> onViewedPreview;
        private long optBits;

        @Nullable
        private Boolean shouldBlurBackground;

        private Builder() {
            this.elements = new ArrayList();
            this.onViewed = new ArrayList();
            this.onViewedPreview = new ArrayList();
            this.onPreviewClicked = new ArrayList();
            this.onLongPressed = new ArrayList();
        }

        public final Builder from(VerticalContainerElement verticalContainerElement) {
            ImmutableVerticalContainerElement.requireNonNull(verticalContainerElement, "instance");
            addAllElements(verticalContainerElement.elements());
            String strBackgroundImage = verticalContainerElement.backgroundImage();
            if (strBackgroundImage != null) {
                backgroundImage(strBackgroundImage);
            }
            shouldBlurBackground(verticalContainerElement.shouldBlurBackground());
            canSpillOverToNextSection(verticalContainerElement.canSpillOverToNextSection());
            addAllOnViewed(verticalContainerElement.onViewed());
            addAllOnViewedPreview(verticalContainerElement.onViewedPreview());
            addAllOnPreviewClicked(verticalContainerElement.onPreviewClicked());
            addAllOnLongPressed(verticalContainerElement.onLongPressed());
            return this;
        }

        public final Builder addElements(Element element) {
            this.elements.add((Element) ImmutableVerticalContainerElement.requireNonNull(element, "elements element"));
            return this;
        }

        public final Builder addElements(Element... elementArr) {
            for (Element element : elementArr) {
                this.elements.add((Element) ImmutableVerticalContainerElement.requireNonNull(element, "elements element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ELEMENTS)
        public final Builder elements(Iterable<? extends Element> iterable) {
            this.elements.clear();
            return addAllElements(iterable);
        }

        public final Builder addAllElements(Iterable<? extends Element> iterable) {
            Iterator<? extends Element> it = iterable.iterator();
            while (it.hasNext()) {
                this.elements.add((Element) ImmutableVerticalContainerElement.requireNonNull(it.next(), "elements element"));
            }
            return this;
        }

        @JsonProperty("backgroundImage")
        public final Builder backgroundImage(@Nullable String str) {
            this.backgroundImage = str;
            return this;
        }

        @JsonProperty("shouldBlurBackground")
        public final Builder shouldBlurBackground(Boolean bool) {
            this.shouldBlurBackground = (Boolean) ImmutableVerticalContainerElement.requireNonNull(bool, "shouldBlurBackground");
            return this;
        }

        @JsonProperty("canSpillOverToNextSection")
        public final Builder canSpillOverToNextSection(Boolean bool) {
            this.canSpillOverToNextSection = (Boolean) ImmutableVerticalContainerElement.requireNonNull(bool, "canSpillOverToNextSection");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableVerticalContainerElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableVerticalContainerElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableVerticalContainerElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewedPreview(Method method) {
            this.onViewedPreview.add((Method) ImmutableVerticalContainerElement.requireNonNull(method, "onViewedPreview element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewedPreview(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewedPreview.add((Method) ImmutableVerticalContainerElement.requireNonNull(method, "onViewedPreview element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onViewedPreview")
        public final Builder onViewedPreview(Iterable<? extends Method> iterable) {
            this.onViewedPreview.clear();
            return addAllOnViewedPreview(iterable);
        }

        public final Builder addAllOnViewedPreview(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onViewedPreview.add((Method) ImmutableVerticalContainerElement.requireNonNull(it.next(), "onViewedPreview element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnPreviewClicked(Method method) {
            this.onPreviewClicked.add((Method) ImmutableVerticalContainerElement.requireNonNull(method, "onPreviewClicked element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnPreviewClicked(Method... methodArr) {
            for (Method method : methodArr) {
                this.onPreviewClicked.add((Method) ImmutableVerticalContainerElement.requireNonNull(method, "onPreviewClicked element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("onPreviewClicked")
        public final Builder onPreviewClicked(Iterable<? extends Method> iterable) {
            this.onPreviewClicked.clear();
            return addAllOnPreviewClicked(iterable);
        }

        public final Builder addAllOnPreviewClicked(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onPreviewClicked.add((Method) ImmutableVerticalContainerElement.requireNonNull(it.next(), "onPreviewClicked element"));
            }
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnLongPressed(Method method) {
            this.onLongPressed.add((Method) ImmutableVerticalContainerElement.requireNonNull(method, "onLongPressed element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addOnLongPressed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onLongPressed.add((Method) ImmutableVerticalContainerElement.requireNonNull(method, "onLongPressed element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("onLongPressed")
        public final Builder onLongPressed(Iterable<? extends Method> iterable) {
            this.onLongPressed.clear();
            return addAllOnLongPressed(iterable);
        }

        public final Builder addAllOnLongPressed(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onLongPressed.add((Method) ImmutableVerticalContainerElement.requireNonNull(it.next(), "onLongPressed element"));
            }
            this.optBits |= 8;
            return this;
        }

        public ImmutableVerticalContainerElement build() {
            return new ImmutableVerticalContainerElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedPreviewIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onPreviewClickedIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onLongPressedIsSet() {
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
