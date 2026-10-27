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
@Generated(from = "ExclusiveCarouselElement", generator = "Immutables")
public final class ImmutableExclusiveCarouselElement extends ExclusiveCarouselElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final Integer durationSeconds;
    private volatile transient InitShim initShim;
    private final List<ExclusiveCarouselSlideElement> items;

    @Nullable
    private final CarouselNavigationElement leftNavigation;
    private final List<Method> onAutoAdvance;
    private final List<Method> onSwipeLeft;
    private final List<Method> onSwipeRight;
    private final List<Method> onViewed;

    @Nullable
    private final CarouselNavigationElement rightNavigation;
    private final String widgetId;

    private ImmutableExclusiveCarouselElement(Builder builder) {
        this.initShim = new InitShim();
        this.durationSeconds = builder.durationSeconds;
        this.leftNavigation = builder.leftNavigation;
        this.rightNavigation = builder.rightNavigation;
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.onSwipeLeftIsSet()) {
            this.initShim.onSwipeLeft(createUnmodifiableList(true, builder.onSwipeLeft));
        }
        if (builder.onSwipeRightIsSet()) {
            this.initShim.onSwipeRight(createUnmodifiableList(true, builder.onSwipeRight));
        }
        if (builder.onAutoAdvanceIsSet()) {
            this.initShim.onAutoAdvance(createUnmodifiableList(true, builder.onAutoAdvance));
        }
        if (builder.itemsIsSet()) {
            this.initShim.items(createUnmodifiableList(true, builder.items));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.onSwipeLeft = this.initShim.onSwipeLeft();
        this.onSwipeRight = this.initShim.onSwipeRight();
        this.onAutoAdvance = this.initShim.onAutoAdvance();
        this.items = this.initShim.items();
        this.initShim = null;
    }

    private ImmutableExclusiveCarouselElement(String str, List<Method> list, @Nullable Integer num, List<Method> list2, List<Method> list3, List<Method> list4, @Nullable CarouselNavigationElement carouselNavigationElement, @Nullable CarouselNavigationElement carouselNavigationElement2, List<ExclusiveCarouselSlideElement> list5) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.durationSeconds = num;
        this.onSwipeLeft = list2;
        this.onSwipeRight = list3;
        this.onAutoAdvance = list4;
        this.leftNavigation = carouselNavigationElement;
        this.rightNavigation = carouselNavigationElement2;
        this.items = list5;
        this.initShim = null;
    }

    @Generated(from = "ExclusiveCarouselElement", generator = "Immutables")
    private final class InitShim {
        private List<ExclusiveCarouselSlideElement> items;
        private byte itemsBuildStage;
        private List<Method> onAutoAdvance;
        private byte onAutoAdvanceBuildStage;
        private List<Method> onSwipeLeft;
        private byte onSwipeLeftBuildStage;
        private List<Method> onSwipeRight;
        private byte onSwipeRightBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.onSwipeLeftBuildStage = (byte) 0;
            this.onSwipeRightBuildStage = (byte) 0;
            this.onAutoAdvanceBuildStage = (byte) 0;
            this.itemsBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableExclusiveCarouselElement.requireNonNull(ImmutableExclusiveCarouselElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableExclusiveCarouselElement.createUnmodifiableList(false, ImmutableExclusiveCarouselElement.createSafeList(ImmutableExclusiveCarouselElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        List<Method> onSwipeLeft() {
            byte b = this.onSwipeLeftBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onSwipeLeftBuildStage = (byte) -1;
                this.onSwipeLeft = ImmutableExclusiveCarouselElement.createUnmodifiableList(false, ImmutableExclusiveCarouselElement.createSafeList(ImmutableExclusiveCarouselElement.super.onSwipeLeft(), true, false));
                this.onSwipeLeftBuildStage = (byte) 1;
            }
            return this.onSwipeLeft;
        }

        void onSwipeLeft(List<Method> list) {
            this.onSwipeLeft = list;
            this.onSwipeLeftBuildStage = (byte) 1;
        }

        List<Method> onSwipeRight() {
            byte b = this.onSwipeRightBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onSwipeRightBuildStage = (byte) -1;
                this.onSwipeRight = ImmutableExclusiveCarouselElement.createUnmodifiableList(false, ImmutableExclusiveCarouselElement.createSafeList(ImmutableExclusiveCarouselElement.super.onSwipeRight(), true, false));
                this.onSwipeRightBuildStage = (byte) 1;
            }
            return this.onSwipeRight;
        }

        void onSwipeRight(List<Method> list) {
            this.onSwipeRight = list;
            this.onSwipeRightBuildStage = (byte) 1;
        }

        List<Method> onAutoAdvance() {
            byte b = this.onAutoAdvanceBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onAutoAdvanceBuildStage = (byte) -1;
                this.onAutoAdvance = ImmutableExclusiveCarouselElement.createUnmodifiableList(false, ImmutableExclusiveCarouselElement.createSafeList(ImmutableExclusiveCarouselElement.super.onAutoAdvance(), true, false));
                this.onAutoAdvanceBuildStage = (byte) 1;
            }
            return this.onAutoAdvance;
        }

        void onAutoAdvance(List<Method> list) {
            this.onAutoAdvance = list;
            this.onAutoAdvanceBuildStage = (byte) 1;
        }

        List<ExclusiveCarouselSlideElement> items() {
            byte b = this.itemsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.itemsBuildStage = (byte) -1;
                this.items = ImmutableExclusiveCarouselElement.createUnmodifiableList(false, ImmutableExclusiveCarouselElement.createSafeList(ImmutableExclusiveCarouselElement.super.items(), true, false));
                this.itemsBuildStage = (byte) 1;
            }
            return this.items;
        }

        void items(List<ExclusiveCarouselSlideElement> list) {
            this.items = list;
            this.itemsBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.onSwipeLeftBuildStage == -1) {
                arrayList.add("onSwipeLeft");
            }
            if (this.onSwipeRightBuildStage == -1) {
                arrayList.add("onSwipeRight");
            }
            if (this.onAutoAdvanceBuildStage == -1) {
                arrayList.add("onAutoAdvance");
            }
            if (this.itemsBuildStage == -1) {
                arrayList.add(ContextMappingConstants.ITEMS);
            }
            return "Cannot build ExclusiveCarouselElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
    @JsonProperty("durationSeconds")
    @Nullable
    public Integer durationSeconds() {
        return this.durationSeconds;
    }

    @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
    @JsonProperty("onSwipeLeft")
    public List<Method> onSwipeLeft() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onSwipeLeft();
        }
        return this.onSwipeLeft;
    }

    @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
    @JsonProperty("onSwipeRight")
    public List<Method> onSwipeRight() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onSwipeRight();
        }
        return this.onSwipeRight;
    }

    @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
    @JsonProperty("onAutoAdvance")
    public List<Method> onAutoAdvance() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onAutoAdvance();
        }
        return this.onAutoAdvance;
    }

    @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
    @JsonProperty("leftNavigation")
    @Nullable
    public CarouselNavigationElement leftNavigation() {
        return this.leftNavigation;
    }

    @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
    @JsonProperty("rightNavigation")
    @Nullable
    public CarouselNavigationElement rightNavigation() {
        return this.rightNavigation;
    }

    @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<ExclusiveCarouselSlideElement> items() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.items();
        }
        return this.items;
    }

    public final ImmutableExclusiveCarouselElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableExclusiveCarouselElement(str2, this.onViewed, this.durationSeconds, this.onSwipeLeft, this.onSwipeRight, this.onAutoAdvance, this.leftNavigation, this.rightNavigation, this.items);
    }

    public final ImmutableExclusiveCarouselElement withOnViewed(Method... methodArr) {
        return new ImmutableExclusiveCarouselElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.durationSeconds, this.onSwipeLeft, this.onSwipeRight, this.onAutoAdvance, this.leftNavigation, this.rightNavigation, this.items);
    }

    public final ImmutableExclusiveCarouselElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableExclusiveCarouselElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.durationSeconds, this.onSwipeLeft, this.onSwipeRight, this.onAutoAdvance, this.leftNavigation, this.rightNavigation, this.items);
    }

    public final ImmutableExclusiveCarouselElement withDurationSeconds(@Nullable Integer num) {
        return equals(this.durationSeconds, num) ? this : new ImmutableExclusiveCarouselElement(this.widgetId, this.onViewed, num, this.onSwipeLeft, this.onSwipeRight, this.onAutoAdvance, this.leftNavigation, this.rightNavigation, this.items);
    }

    public final ImmutableExclusiveCarouselElement withOnSwipeLeft(Method... methodArr) {
        return new ImmutableExclusiveCarouselElement(this.widgetId, this.onViewed, this.durationSeconds, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onSwipeRight, this.onAutoAdvance, this.leftNavigation, this.rightNavigation, this.items);
    }

    public final ImmutableExclusiveCarouselElement withOnSwipeLeft(Iterable<? extends Method> iterable) {
        if (this.onSwipeLeft == iterable) {
            return this;
        }
        return new ImmutableExclusiveCarouselElement(this.widgetId, this.onViewed, this.durationSeconds, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onSwipeRight, this.onAutoAdvance, this.leftNavigation, this.rightNavigation, this.items);
    }

    public final ImmutableExclusiveCarouselElement withOnSwipeRight(Method... methodArr) {
        return new ImmutableExclusiveCarouselElement(this.widgetId, this.onViewed, this.durationSeconds, this.onSwipeLeft, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onAutoAdvance, this.leftNavigation, this.rightNavigation, this.items);
    }

    public final ImmutableExclusiveCarouselElement withOnSwipeRight(Iterable<? extends Method> iterable) {
        if (this.onSwipeRight == iterable) {
            return this;
        }
        return new ImmutableExclusiveCarouselElement(this.widgetId, this.onViewed, this.durationSeconds, this.onSwipeLeft, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onAutoAdvance, this.leftNavigation, this.rightNavigation, this.items);
    }

    public final ImmutableExclusiveCarouselElement withOnAutoAdvance(Method... methodArr) {
        return new ImmutableExclusiveCarouselElement(this.widgetId, this.onViewed, this.durationSeconds, this.onSwipeLeft, this.onSwipeRight, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.leftNavigation, this.rightNavigation, this.items);
    }

    public final ImmutableExclusiveCarouselElement withOnAutoAdvance(Iterable<? extends Method> iterable) {
        if (this.onAutoAdvance == iterable) {
            return this;
        }
        return new ImmutableExclusiveCarouselElement(this.widgetId, this.onViewed, this.durationSeconds, this.onSwipeLeft, this.onSwipeRight, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.leftNavigation, this.rightNavigation, this.items);
    }

    public final ImmutableExclusiveCarouselElement withLeftNavigation(@Nullable CarouselNavigationElement carouselNavigationElement) {
        return this.leftNavigation == carouselNavigationElement ? this : new ImmutableExclusiveCarouselElement(this.widgetId, this.onViewed, this.durationSeconds, this.onSwipeLeft, this.onSwipeRight, this.onAutoAdvance, carouselNavigationElement, this.rightNavigation, this.items);
    }

    public final ImmutableExclusiveCarouselElement withRightNavigation(@Nullable CarouselNavigationElement carouselNavigationElement) {
        return this.rightNavigation == carouselNavigationElement ? this : new ImmutableExclusiveCarouselElement(this.widgetId, this.onViewed, this.durationSeconds, this.onSwipeLeft, this.onSwipeRight, this.onAutoAdvance, this.leftNavigation, carouselNavigationElement, this.items);
    }

    public final ImmutableExclusiveCarouselElement withItems(ExclusiveCarouselSlideElement... exclusiveCarouselSlideElementArr) {
        return new ImmutableExclusiveCarouselElement(this.widgetId, this.onViewed, this.durationSeconds, this.onSwipeLeft, this.onSwipeRight, this.onAutoAdvance, this.leftNavigation, this.rightNavigation, createUnmodifiableList(false, createSafeList(Arrays.asList(exclusiveCarouselSlideElementArr), true, false)));
    }

    public final ImmutableExclusiveCarouselElement withItems(Iterable<? extends ExclusiveCarouselSlideElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableExclusiveCarouselElement(this.widgetId, this.onViewed, this.durationSeconds, this.onSwipeLeft, this.onSwipeRight, this.onAutoAdvance, this.leftNavigation, this.rightNavigation, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableExclusiveCarouselElement) && equalTo(0, (ImmutableExclusiveCarouselElement) obj);
    }

    private boolean equalTo(int i, ImmutableExclusiveCarouselElement immutableExclusiveCarouselElement) {
        return this.widgetId.equals(immutableExclusiveCarouselElement.widgetId) && this.onViewed.equals(immutableExclusiveCarouselElement.onViewed) && equals(this.durationSeconds, immutableExclusiveCarouselElement.durationSeconds) && this.onSwipeLeft.equals(immutableExclusiveCarouselElement.onSwipeLeft) && this.onSwipeRight.equals(immutableExclusiveCarouselElement.onSwipeRight) && this.onAutoAdvance.equals(immutableExclusiveCarouselElement.onAutoAdvance) && equals(this.leftNavigation, immutableExclusiveCarouselElement.leftNavigation) && equals(this.rightNavigation, immutableExclusiveCarouselElement.rightNavigation) && this.items.equals(immutableExclusiveCarouselElement.items);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.durationSeconds);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onSwipeLeft.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.onSwipeRight.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.onAutoAdvance.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.leftNavigation);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.rightNavigation);
        return iHashCode8 + (iHashCode8 << 5) + this.items.hashCode();
    }

    public String toString() {
        return "ExclusiveCarouselElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", durationSeconds=" + this.durationSeconds + ", onSwipeLeft=" + this.onSwipeLeft + ", onSwipeRight=" + this.onSwipeRight + ", onAutoAdvance=" + this.onAutoAdvance + ", leftNavigation=" + this.leftNavigation + ", rightNavigation=" + this.rightNavigation + ", items=" + this.items + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ExclusiveCarouselElement", generator = "Immutables")
    static final class Json extends ExclusiveCarouselElement {

        @Nullable
        Integer durationSeconds;
        boolean itemsIsSet;

        @Nullable
        CarouselNavigationElement leftNavigation;
        boolean onAutoAdvanceIsSet;
        boolean onSwipeLeftIsSet;
        boolean onSwipeRightIsSet;
        boolean onViewedIsSet;

        @Nullable
        CarouselNavigationElement rightNavigation;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<Method> onSwipeLeft = Collections.emptyList();

        @Nullable
        List<Method> onSwipeRight = Collections.emptyList();

        @Nullable
        List<Method> onAutoAdvance = Collections.emptyList();

        @Nullable
        List<ExclusiveCarouselSlideElement> items = Collections.emptyList();

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

        @JsonProperty("durationSeconds")
        public void setDurationSeconds(@Nullable Integer num) {
            this.durationSeconds = num;
        }

        @JsonProperty("onSwipeLeft")
        public void setOnSwipeLeft(List<Method> list) {
            this.onSwipeLeft = list;
            this.onSwipeLeftIsSet = true;
        }

        @JsonProperty("onSwipeRight")
        public void setOnSwipeRight(List<Method> list) {
            this.onSwipeRight = list;
            this.onSwipeRightIsSet = true;
        }

        @JsonProperty("onAutoAdvance")
        public void setOnAutoAdvance(List<Method> list) {
            this.onAutoAdvance = list;
            this.onAutoAdvanceIsSet = true;
        }

        @JsonProperty("leftNavigation")
        public void setLeftNavigation(@Nullable CarouselNavigationElement carouselNavigationElement) {
            this.leftNavigation = carouselNavigationElement;
        }

        @JsonProperty("rightNavigation")
        public void setRightNavigation(@Nullable CarouselNavigationElement carouselNavigationElement) {
            this.rightNavigation = carouselNavigationElement;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(List<ExclusiveCarouselSlideElement> list) {
            this.items = list;
            this.itemsIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
        public Integer durationSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
        public List<Method> onSwipeLeft() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
        public List<Method> onSwipeRight() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
        public List<Method> onAutoAdvance() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
        public CarouselNavigationElement leftNavigation() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.GenericCarouselElement
        public CarouselNavigationElement rightNavigation() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselElement
        public List<ExclusiveCarouselSlideElement> items() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableExclusiveCarouselElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        Integer num = json.durationSeconds;
        if (num != null) {
            builder.durationSeconds(num);
        }
        if (json.onSwipeLeftIsSet) {
            builder.addAllOnSwipeLeft(json.onSwipeLeft);
        }
        if (json.onSwipeRightIsSet) {
            builder.addAllOnSwipeRight(json.onSwipeRight);
        }
        if (json.onAutoAdvanceIsSet) {
            builder.addAllOnAutoAdvance(json.onAutoAdvance);
        }
        CarouselNavigationElement carouselNavigationElement = json.leftNavigation;
        if (carouselNavigationElement != null) {
            builder.leftNavigation(carouselNavigationElement);
        }
        CarouselNavigationElement carouselNavigationElement2 = json.rightNavigation;
        if (carouselNavigationElement2 != null) {
            builder.rightNavigation(carouselNavigationElement2);
        }
        if (json.itemsIsSet) {
            builder.addAllItems(json.items);
        }
        return builder.build();
    }

    public static ImmutableExclusiveCarouselElement copyOf(ExclusiveCarouselElement exclusiveCarouselElement) {
        if (exclusiveCarouselElement instanceof ImmutableExclusiveCarouselElement) {
            return (ImmutableExclusiveCarouselElement) exclusiveCarouselElement;
        }
        return builder().from(exclusiveCarouselElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ExclusiveCarouselElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ITEMS = 16;
        private static final long OPT_BIT_ON_AUTO_ADVANCE = 8;
        private static final long OPT_BIT_ON_SWIPE_LEFT = 2;
        private static final long OPT_BIT_ON_SWIPE_RIGHT = 4;
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private Integer durationSeconds;
        private List<ExclusiveCarouselSlideElement> items;

        @Nullable
        private CarouselNavigationElement leftNavigation;
        private List<Method> onAutoAdvance;
        private List<Method> onSwipeLeft;
        private List<Method> onSwipeRight;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private CarouselNavigationElement rightNavigation;

        @Nullable
        private String widgetId;

        private Builder() {
            this.onViewed = new ArrayList();
            this.onSwipeLeft = new ArrayList();
            this.onSwipeRight = new ArrayList();
            this.onAutoAdvance = new ArrayList();
            this.items = new ArrayList();
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableExclusiveCarouselElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        public final Builder from(GenericCarouselElement genericCarouselElement) {
            ImmutableExclusiveCarouselElement.requireNonNull(genericCarouselElement, "instance");
            from((Object) genericCarouselElement);
            return this;
        }

        public final Builder from(ExclusiveCarouselElement exclusiveCarouselElement) {
            ImmutableExclusiveCarouselElement.requireNonNull(exclusiveCarouselElement, "instance");
            from((Object) exclusiveCarouselElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                widgetId(widgetElement.widgetId());
                addAllOnViewed(widgetElement.onViewed());
                j = 68;
            } else {
                j = 0;
            }
            if (obj instanceof GenericCarouselElement) {
                GenericCarouselElement genericCarouselElement = (GenericCarouselElement) obj;
                if ((j & 1) == 0) {
                    Integer numDurationSeconds = genericCarouselElement.durationSeconds();
                    if (numDurationSeconds != null) {
                        durationSeconds(numDurationSeconds);
                    }
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    addAllOnAutoAdvance(genericCarouselElement.onAutoAdvance());
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    widgetId(genericCarouselElement.widgetId());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    addAllOnSwipeLeft(genericCarouselElement.onSwipeLeft());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    CarouselNavigationElement carouselNavigationElementLeftNavigation = genericCarouselElement.leftNavigation();
                    if (carouselNavigationElementLeftNavigation != null) {
                        leftNavigation(carouselNavigationElementLeftNavigation);
                    }
                    j |= 16;
                }
                if ((j & 32) == 0) {
                    CarouselNavigationElement carouselNavigationElementRightNavigation = genericCarouselElement.rightNavigation();
                    if (carouselNavigationElementRightNavigation != null) {
                        rightNavigation(carouselNavigationElementRightNavigation);
                    }
                    j |= 32;
                }
                if ((j & 64) == 0) {
                    addAllOnViewed(genericCarouselElement.onViewed());
                    j |= 64;
                }
                if ((j & 128) == 0) {
                    addAllOnSwipeRight(genericCarouselElement.onSwipeRight());
                    j |= 128;
                }
            }
            if (obj instanceof ExclusiveCarouselElement) {
                ExclusiveCarouselElement exclusiveCarouselElement = (ExclusiveCarouselElement) obj;
                if ((j & 1) == 0) {
                    Integer numDurationSeconds2 = exclusiveCarouselElement.durationSeconds();
                    if (numDurationSeconds2 != null) {
                        durationSeconds(numDurationSeconds2);
                    }
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    addAllOnAutoAdvance(exclusiveCarouselElement.onAutoAdvance());
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    widgetId(exclusiveCarouselElement.widgetId());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    addAllOnSwipeLeft(exclusiveCarouselElement.onSwipeLeft());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    CarouselNavigationElement carouselNavigationElementLeftNavigation2 = exclusiveCarouselElement.leftNavigation();
                    if (carouselNavigationElementLeftNavigation2 != null) {
                        leftNavigation(carouselNavigationElementLeftNavigation2);
                    }
                    j |= 16;
                }
                if ((j & 32) == 0) {
                    CarouselNavigationElement carouselNavigationElementRightNavigation2 = exclusiveCarouselElement.rightNavigation();
                    if (carouselNavigationElementRightNavigation2 != null) {
                        rightNavigation(carouselNavigationElementRightNavigation2);
                    }
                    j |= 32;
                }
                if ((j & 64) == 0) {
                    addAllOnViewed(exclusiveCarouselElement.onViewed());
                    j |= 64;
                }
                addAllItems(exclusiveCarouselElement.items());
                if ((j & 128) == 0) {
                    addAllOnSwipeRight(exclusiveCarouselElement.onSwipeRight());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableExclusiveCarouselElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("durationSeconds")
        public final Builder durationSeconds(@Nullable Integer num) {
            this.durationSeconds = num;
            return this;
        }

        public final Builder addOnSwipeLeft(Method method) {
            this.onSwipeLeft.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(method, "onSwipeLeft element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnSwipeLeft(Method... methodArr) {
            for (Method method : methodArr) {
                this.onSwipeLeft.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(method, "onSwipeLeft element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onSwipeLeft")
        public final Builder onSwipeLeft(Iterable<? extends Method> iterable) {
            this.onSwipeLeft.clear();
            return addAllOnSwipeLeft(iterable);
        }

        public final Builder addAllOnSwipeLeft(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onSwipeLeft.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(it.next(), "onSwipeLeft element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnSwipeRight(Method method) {
            this.onSwipeRight.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(method, "onSwipeRight element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnSwipeRight(Method... methodArr) {
            for (Method method : methodArr) {
                this.onSwipeRight.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(method, "onSwipeRight element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("onSwipeRight")
        public final Builder onSwipeRight(Iterable<? extends Method> iterable) {
            this.onSwipeRight.clear();
            return addAllOnSwipeRight(iterable);
        }

        public final Builder addAllOnSwipeRight(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onSwipeRight.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(it.next(), "onSwipeRight element"));
            }
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnAutoAdvance(Method method) {
            this.onAutoAdvance.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(method, "onAutoAdvance element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addOnAutoAdvance(Method... methodArr) {
            for (Method method : methodArr) {
                this.onAutoAdvance.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(method, "onAutoAdvance element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("onAutoAdvance")
        public final Builder onAutoAdvance(Iterable<? extends Method> iterable) {
            this.onAutoAdvance.clear();
            return addAllOnAutoAdvance(iterable);
        }

        public final Builder addAllOnAutoAdvance(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onAutoAdvance.add((Method) ImmutableExclusiveCarouselElement.requireNonNull(it.next(), "onAutoAdvance element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("leftNavigation")
        public final Builder leftNavigation(@Nullable CarouselNavigationElement carouselNavigationElement) {
            this.leftNavigation = carouselNavigationElement;
            return this;
        }

        @JsonProperty("rightNavigation")
        public final Builder rightNavigation(@Nullable CarouselNavigationElement carouselNavigationElement) {
            this.rightNavigation = carouselNavigationElement;
            return this;
        }

        public final Builder addItems(ExclusiveCarouselSlideElement exclusiveCarouselSlideElement) {
            this.items.add((ExclusiveCarouselSlideElement) ImmutableExclusiveCarouselElement.requireNonNull(exclusiveCarouselSlideElement, "items element"));
            this.optBits |= 16;
            return this;
        }

        public final Builder addItems(ExclusiveCarouselSlideElement... exclusiveCarouselSlideElementArr) {
            for (ExclusiveCarouselSlideElement exclusiveCarouselSlideElement : exclusiveCarouselSlideElementArr) {
                this.items.add((ExclusiveCarouselSlideElement) ImmutableExclusiveCarouselElement.requireNonNull(exclusiveCarouselSlideElement, "items element"));
            }
            this.optBits |= 16;
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends ExclusiveCarouselSlideElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends ExclusiveCarouselSlideElement> iterable) {
            Iterator<? extends ExclusiveCarouselSlideElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((ExclusiveCarouselSlideElement) ImmutableExclusiveCarouselElement.requireNonNull(it.next(), "items element"));
            }
            this.optBits |= 16;
            return this;
        }

        public ImmutableExclusiveCarouselElement build() {
            return new ImmutableExclusiveCarouselElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onSwipeLeftIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onSwipeRightIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onAutoAdvanceIsSet() {
            return (this.optBits & 8) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean itemsIsSet() {
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
