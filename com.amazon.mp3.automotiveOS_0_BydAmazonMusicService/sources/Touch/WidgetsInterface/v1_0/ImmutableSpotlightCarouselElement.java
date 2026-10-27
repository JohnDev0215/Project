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
@Generated(from = "SpotlightCarouselElement", generator = "Immutables")
public final class ImmutableSpotlightCarouselElement extends SpotlightCarouselElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String image;
    private volatile transient InitShim initShim;
    private final List<SpotlightCarouselSlideElement> items;
    private final CarouselNavigationElement leftNavigation;
    private final List<Method> onSwipeLeft;
    private final List<Method> onSwipeRight;
    private final List<Method> onViewed;
    private final CarouselNavigationElement rightNavigation;
    private final String widgetId;

    private ImmutableSpotlightCarouselElement(Builder builder) {
        this.initShim = new InitShim();
        this.image = builder.image;
        this.leftNavigation = builder.leftNavigation;
        this.rightNavigation = builder.rightNavigation;
        this.items = createUnmodifiableList(true, builder.items);
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
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.onSwipeLeft = this.initShim.onSwipeLeft();
        this.onSwipeRight = this.initShim.onSwipeRight();
        this.initShim = null;
    }

    private ImmutableSpotlightCarouselElement(String str, List<Method> list, String str2, CarouselNavigationElement carouselNavigationElement, CarouselNavigationElement carouselNavigationElement2, List<SpotlightCarouselSlideElement> list2, List<Method> list3, List<Method> list4) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.image = str2;
        this.leftNavigation = carouselNavigationElement;
        this.rightNavigation = carouselNavigationElement2;
        this.items = list2;
        this.onSwipeLeft = list3;
        this.onSwipeRight = list4;
        this.initShim = null;
    }

    @Generated(from = "SpotlightCarouselElement", generator = "Immutables")
    private final class InitShim {
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
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableSpotlightCarouselElement.requireNonNull(ImmutableSpotlightCarouselElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableSpotlightCarouselElement.createUnmodifiableList(false, ImmutableSpotlightCarouselElement.createSafeList(ImmutableSpotlightCarouselElement.super.onViewed(), true, false));
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
                this.onSwipeLeft = ImmutableSpotlightCarouselElement.createUnmodifiableList(false, ImmutableSpotlightCarouselElement.createSafeList(ImmutableSpotlightCarouselElement.super.onSwipeLeft(), true, false));
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
                this.onSwipeRight = ImmutableSpotlightCarouselElement.createUnmodifiableList(false, ImmutableSpotlightCarouselElement.createSafeList(ImmutableSpotlightCarouselElement.super.onSwipeRight(), true, false));
                this.onSwipeRightBuildStage = (byte) 1;
            }
            return this.onSwipeRight;
        }

        void onSwipeRight(List<Method> list) {
            this.onSwipeRight = list;
            this.onSwipeRightBuildStage = (byte) 1;
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
            return "Cannot build SpotlightCarouselElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
    @JsonProperty(ContextMappingConstants.IMAGE)
    public String image() {
        return this.image;
    }

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
    @JsonProperty("leftNavigation")
    public CarouselNavigationElement leftNavigation() {
        return this.leftNavigation;
    }

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
    @JsonProperty("rightNavigation")
    public CarouselNavigationElement rightNavigation() {
        return this.rightNavigation;
    }

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<SpotlightCarouselSlideElement> items() {
        return this.items;
    }

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
    @JsonProperty("onSwipeLeft")
    public List<Method> onSwipeLeft() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onSwipeLeft();
        }
        return this.onSwipeLeft;
    }

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
    @JsonProperty("onSwipeRight")
    public List<Method> onSwipeRight() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onSwipeRight();
        }
        return this.onSwipeRight;
    }

    public final ImmutableSpotlightCarouselElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableSpotlightCarouselElement(str2, this.onViewed, this.image, this.leftNavigation, this.rightNavigation, this.items, this.onSwipeLeft, this.onSwipeRight);
    }

    public final ImmutableSpotlightCarouselElement withOnViewed(Method... methodArr) {
        return new ImmutableSpotlightCarouselElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.image, this.leftNavigation, this.rightNavigation, this.items, this.onSwipeLeft, this.onSwipeRight);
    }

    public final ImmutableSpotlightCarouselElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableSpotlightCarouselElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.image, this.leftNavigation, this.rightNavigation, this.items, this.onSwipeLeft, this.onSwipeRight);
    }

    public final ImmutableSpotlightCarouselElement withImage(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.IMAGE);
        return this.image.equals(str2) ? this : new ImmutableSpotlightCarouselElement(this.widgetId, this.onViewed, str2, this.leftNavigation, this.rightNavigation, this.items, this.onSwipeLeft, this.onSwipeRight);
    }

    public final ImmutableSpotlightCarouselElement withLeftNavigation(CarouselNavigationElement carouselNavigationElement) {
        if (this.leftNavigation == carouselNavigationElement) {
            return this;
        }
        return new ImmutableSpotlightCarouselElement(this.widgetId, this.onViewed, this.image, (CarouselNavigationElement) requireNonNull(carouselNavigationElement, "leftNavigation"), this.rightNavigation, this.items, this.onSwipeLeft, this.onSwipeRight);
    }

    public final ImmutableSpotlightCarouselElement withRightNavigation(CarouselNavigationElement carouselNavigationElement) {
        if (this.rightNavigation == carouselNavigationElement) {
            return this;
        }
        return new ImmutableSpotlightCarouselElement(this.widgetId, this.onViewed, this.image, this.leftNavigation, (CarouselNavigationElement) requireNonNull(carouselNavigationElement, "rightNavigation"), this.items, this.onSwipeLeft, this.onSwipeRight);
    }

    public final ImmutableSpotlightCarouselElement withItems(SpotlightCarouselSlideElement... spotlightCarouselSlideElementArr) {
        return new ImmutableSpotlightCarouselElement(this.widgetId, this.onViewed, this.image, this.leftNavigation, this.rightNavigation, createUnmodifiableList(false, createSafeList(Arrays.asList(spotlightCarouselSlideElementArr), true, false)), this.onSwipeLeft, this.onSwipeRight);
    }

    public final ImmutableSpotlightCarouselElement withItems(Iterable<? extends SpotlightCarouselSlideElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableSpotlightCarouselElement(this.widgetId, this.onViewed, this.image, this.leftNavigation, this.rightNavigation, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onSwipeLeft, this.onSwipeRight);
    }

    public final ImmutableSpotlightCarouselElement withOnSwipeLeft(Method... methodArr) {
        return new ImmutableSpotlightCarouselElement(this.widgetId, this.onViewed, this.image, this.leftNavigation, this.rightNavigation, this.items, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onSwipeRight);
    }

    public final ImmutableSpotlightCarouselElement withOnSwipeLeft(Iterable<? extends Method> iterable) {
        if (this.onSwipeLeft == iterable) {
            return this;
        }
        return new ImmutableSpotlightCarouselElement(this.widgetId, this.onViewed, this.image, this.leftNavigation, this.rightNavigation, this.items, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onSwipeRight);
    }

    public final ImmutableSpotlightCarouselElement withOnSwipeRight(Method... methodArr) {
        return new ImmutableSpotlightCarouselElement(this.widgetId, this.onViewed, this.image, this.leftNavigation, this.rightNavigation, this.items, this.onSwipeLeft, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSpotlightCarouselElement withOnSwipeRight(Iterable<? extends Method> iterable) {
        if (this.onSwipeRight == iterable) {
            return this;
        }
        return new ImmutableSpotlightCarouselElement(this.widgetId, this.onViewed, this.image, this.leftNavigation, this.rightNavigation, this.items, this.onSwipeLeft, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSpotlightCarouselElement) && equalTo(0, (ImmutableSpotlightCarouselElement) obj);
    }

    private boolean equalTo(int i, ImmutableSpotlightCarouselElement immutableSpotlightCarouselElement) {
        return this.widgetId.equals(immutableSpotlightCarouselElement.widgetId) && this.onViewed.equals(immutableSpotlightCarouselElement.onViewed) && this.image.equals(immutableSpotlightCarouselElement.image) && this.leftNavigation.equals(immutableSpotlightCarouselElement.leftNavigation) && this.rightNavigation.equals(immutableSpotlightCarouselElement.rightNavigation) && this.items.equals(immutableSpotlightCarouselElement.items) && this.onSwipeLeft.equals(immutableSpotlightCarouselElement.onSwipeLeft) && this.onSwipeRight.equals(immutableSpotlightCarouselElement.onSwipeRight);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.image.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.leftNavigation.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.rightNavigation.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.items.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.onSwipeLeft.hashCode();
        return iHashCode7 + (iHashCode7 << 5) + this.onSwipeRight.hashCode();
    }

    public String toString() {
        return "SpotlightCarouselElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", image=" + this.image + ", leftNavigation=" + this.leftNavigation + ", rightNavigation=" + this.rightNavigation + ", items=" + this.items + ", onSwipeLeft=" + this.onSwipeLeft + ", onSwipeRight=" + this.onSwipeRight + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SpotlightCarouselElement", generator = "Immutables")
    static final class Json extends SpotlightCarouselElement {

        @Nullable
        String image;

        @Nullable
        CarouselNavigationElement leftNavigation;
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
        List<SpotlightCarouselSlideElement> items = Collections.emptyList();

        @Nullable
        List<Method> onSwipeLeft = Collections.emptyList();

        @Nullable
        List<Method> onSwipeRight = Collections.emptyList();

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

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(String str) {
            this.image = str;
        }

        @JsonProperty("leftNavigation")
        public void setLeftNavigation(CarouselNavigationElement carouselNavigationElement) {
            this.leftNavigation = carouselNavigationElement;
        }

        @JsonProperty("rightNavigation")
        public void setRightNavigation(CarouselNavigationElement carouselNavigationElement) {
            this.rightNavigation = carouselNavigationElement;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(List<SpotlightCarouselSlideElement> list) {
            this.items = list;
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

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
        public CarouselNavigationElement leftNavigation() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
        public CarouselNavigationElement rightNavigation() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
        public List<SpotlightCarouselSlideElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
        public List<Method> onSwipeLeft() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselElement
        public List<Method> onSwipeRight() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSpotlightCarouselElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        String str2 = json.image;
        if (str2 != null) {
            builder.image(str2);
        }
        CarouselNavigationElement carouselNavigationElement = json.leftNavigation;
        if (carouselNavigationElement != null) {
            builder.leftNavigation(carouselNavigationElement);
        }
        CarouselNavigationElement carouselNavigationElement2 = json.rightNavigation;
        if (carouselNavigationElement2 != null) {
            builder.rightNavigation(carouselNavigationElement2);
        }
        List<SpotlightCarouselSlideElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        if (json.onSwipeLeftIsSet) {
            builder.addAllOnSwipeLeft(json.onSwipeLeft);
        }
        if (json.onSwipeRightIsSet) {
            builder.addAllOnSwipeRight(json.onSwipeRight);
        }
        return builder.build();
    }

    public static ImmutableSpotlightCarouselElement copyOf(SpotlightCarouselElement spotlightCarouselElement) {
        if (spotlightCarouselElement instanceof ImmutableSpotlightCarouselElement) {
            return (ImmutableSpotlightCarouselElement) spotlightCarouselElement;
        }
        return builder().from(spotlightCarouselElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SpotlightCarouselElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_IMAGE = 1;
        private static final long INIT_BIT_LEFT_NAVIGATION = 2;
        private static final long INIT_BIT_RIGHT_NAVIGATION = 4;
        private static final long OPT_BIT_ON_SWIPE_LEFT = 2;
        private static final long OPT_BIT_ON_SWIPE_RIGHT = 4;
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private String image;
        private long initBits;
        private List<SpotlightCarouselSlideElement> items;

        @Nullable
        private CarouselNavigationElement leftNavigation;
        private List<Method> onSwipeLeft;
        private List<Method> onSwipeRight;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private CarouselNavigationElement rightNavigation;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 7L;
            this.onViewed = new ArrayList();
            this.items = new ArrayList();
            this.onSwipeLeft = new ArrayList();
            this.onSwipeRight = new ArrayList();
        }

        public final Builder from(SpotlightCarouselElement spotlightCarouselElement) {
            ImmutableSpotlightCarouselElement.requireNonNull(spotlightCarouselElement, "instance");
            from((Object) spotlightCarouselElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableSpotlightCarouselElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof SpotlightCarouselElement) {
                SpotlightCarouselElement spotlightCarouselElement = (SpotlightCarouselElement) obj;
                image(spotlightCarouselElement.image());
                widgetId(spotlightCarouselElement.widgetId());
                addAllOnSwipeLeft(spotlightCarouselElement.onSwipeLeft());
                leftNavigation(spotlightCarouselElement.leftNavigation());
                rightNavigation(spotlightCarouselElement.rightNavigation());
                addAllOnViewed(spotlightCarouselElement.onViewed());
                j = 3;
                addAllItems(spotlightCarouselElement.items());
                addAllOnSwipeRight(spotlightCarouselElement.onSwipeRight());
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
            this.widgetId = (String) ImmutableSpotlightCarouselElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableSpotlightCarouselElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableSpotlightCarouselElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableSpotlightCarouselElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(String str) {
            this.image = (String) ImmutableSpotlightCarouselElement.requireNonNull(str, ContextMappingConstants.IMAGE);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("leftNavigation")
        public final Builder leftNavigation(CarouselNavigationElement carouselNavigationElement) {
            this.leftNavigation = (CarouselNavigationElement) ImmutableSpotlightCarouselElement.requireNonNull(carouselNavigationElement, "leftNavigation");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("rightNavigation")
        public final Builder rightNavigation(CarouselNavigationElement carouselNavigationElement) {
            this.rightNavigation = (CarouselNavigationElement) ImmutableSpotlightCarouselElement.requireNonNull(carouselNavigationElement, "rightNavigation");
            this.initBits &= -5;
            return this;
        }

        public final Builder addItems(SpotlightCarouselSlideElement spotlightCarouselSlideElement) {
            this.items.add((SpotlightCarouselSlideElement) ImmutableSpotlightCarouselElement.requireNonNull(spotlightCarouselSlideElement, "items element"));
            return this;
        }

        public final Builder addItems(SpotlightCarouselSlideElement... spotlightCarouselSlideElementArr) {
            for (SpotlightCarouselSlideElement spotlightCarouselSlideElement : spotlightCarouselSlideElementArr) {
                this.items.add((SpotlightCarouselSlideElement) ImmutableSpotlightCarouselElement.requireNonNull(spotlightCarouselSlideElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends SpotlightCarouselSlideElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends SpotlightCarouselSlideElement> iterable) {
            Iterator<? extends SpotlightCarouselSlideElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((SpotlightCarouselSlideElement) ImmutableSpotlightCarouselElement.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        public final Builder addOnSwipeLeft(Method method) {
            this.onSwipeLeft.add((Method) ImmutableSpotlightCarouselElement.requireNonNull(method, "onSwipeLeft element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnSwipeLeft(Method... methodArr) {
            for (Method method : methodArr) {
                this.onSwipeLeft.add((Method) ImmutableSpotlightCarouselElement.requireNonNull(method, "onSwipeLeft element"));
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
                this.onSwipeLeft.add((Method) ImmutableSpotlightCarouselElement.requireNonNull(it.next(), "onSwipeLeft element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnSwipeRight(Method method) {
            this.onSwipeRight.add((Method) ImmutableSpotlightCarouselElement.requireNonNull(method, "onSwipeRight element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnSwipeRight(Method... methodArr) {
            for (Method method : methodArr) {
                this.onSwipeRight.add((Method) ImmutableSpotlightCarouselElement.requireNonNull(method, "onSwipeRight element"));
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
                this.onSwipeRight.add((Method) ImmutableSpotlightCarouselElement.requireNonNull(it.next(), "onSwipeRight element"));
            }
            this.optBits |= 4;
            return this;
        }

        public ImmutableSpotlightCarouselElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSpotlightCarouselElement(this);
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

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.IMAGE);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("leftNavigation");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("rightNavigation");
            }
            return "Cannot build SpotlightCarouselElement, some of required attributes are not set " + arrayList;
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
