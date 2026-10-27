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
@Generated(from = "ScrollableListElement", generator = "Immutables")
public final class ImmutableScrollableListElement extends ScrollableListElement {

    @Nullable
    private final String backgroundImage;

    @Nullable
    private final Integer duration;
    private final List<Element> elements;
    private final SwipeablePageLabelElement label;
    private final List<Method> onViewed;
    private final Boolean shouldBlurBackground;

    @Nullable
    private final Integer speed;

    private ImmutableScrollableListElement(Builder builder) {
        List<Method> listCreateUnmodifiableList;
        this.label = builder.label;
        this.duration = builder.duration;
        this.speed = builder.speed;
        this.backgroundImage = builder.backgroundImage;
        this.shouldBlurBackground = builder.shouldBlurBackground;
        this.elements = createUnmodifiableList(true, builder.elements);
        if (!builder.onViewedIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.onViewed(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.onViewed);
        }
        this.onViewed = listCreateUnmodifiableList;
    }

    private ImmutableScrollableListElement(SwipeablePageLabelElement swipeablePageLabelElement, @Nullable Integer num, @Nullable Integer num2, @Nullable String str, Boolean bool, List<Method> list, List<Element> list2) {
        this.label = swipeablePageLabelElement;
        this.duration = num;
        this.speed = num2;
        this.backgroundImage = str;
        this.shouldBlurBackground = bool;
        this.onViewed = list;
        this.elements = list2;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
    @JsonProperty(ContextMappingConstants.LABEL)
    public SwipeablePageLabelElement label() {
        return this.label;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
    @JsonProperty("duration")
    @Nullable
    public Integer duration() {
        return this.duration;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
    @JsonProperty("speed")
    @Nullable
    public Integer speed() {
        return this.speed;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
    @JsonProperty("backgroundImage")
    @Nullable
    public String backgroundImage() {
        return this.backgroundImage;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
    @JsonProperty("shouldBlurBackground")
    public Boolean shouldBlurBackground() {
        return this.shouldBlurBackground;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
    @JsonProperty("onViewed")
    public List<Method> onViewed() {
        return this.onViewed;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ScrollableListElement
    @JsonProperty(ContextMappingConstants.ELEMENTS)
    public List<Element> elements() {
        return this.elements;
    }

    public final ImmutableScrollableListElement withLabel(SwipeablePageLabelElement swipeablePageLabelElement) {
        return this.label == swipeablePageLabelElement ? this : new ImmutableScrollableListElement((SwipeablePageLabelElement) requireNonNull(swipeablePageLabelElement, ContextMappingConstants.LABEL), this.duration, this.speed, this.backgroundImage, this.shouldBlurBackground, this.onViewed, this.elements);
    }

    public final ImmutableScrollableListElement withDuration(@Nullable Integer num) {
        return equals(this.duration, num) ? this : new ImmutableScrollableListElement(this.label, num, this.speed, this.backgroundImage, this.shouldBlurBackground, this.onViewed, this.elements);
    }

    public final ImmutableScrollableListElement withSpeed(@Nullable Integer num) {
        return equals(this.speed, num) ? this : new ImmutableScrollableListElement(this.label, this.duration, num, this.backgroundImage, this.shouldBlurBackground, this.onViewed, this.elements);
    }

    public final ImmutableScrollableListElement withBackgroundImage(@Nullable String str) {
        return equals(this.backgroundImage, str) ? this : new ImmutableScrollableListElement(this.label, this.duration, this.speed, str, this.shouldBlurBackground, this.onViewed, this.elements);
    }

    public final ImmutableScrollableListElement withShouldBlurBackground(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "shouldBlurBackground");
        return this.shouldBlurBackground.equals(bool2) ? this : new ImmutableScrollableListElement(this.label, this.duration, this.speed, this.backgroundImage, bool2, this.onViewed, this.elements);
    }

    public final ImmutableScrollableListElement withOnViewed(Method... methodArr) {
        return new ImmutableScrollableListElement(this.label, this.duration, this.speed, this.backgroundImage, this.shouldBlurBackground, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.elements);
    }

    public final ImmutableScrollableListElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableScrollableListElement(this.label, this.duration, this.speed, this.backgroundImage, this.shouldBlurBackground, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.elements);
    }

    public final ImmutableScrollableListElement withElements(Element... elementArr) {
        return new ImmutableScrollableListElement(this.label, this.duration, this.speed, this.backgroundImage, this.shouldBlurBackground, this.onViewed, createUnmodifiableList(false, createSafeList(Arrays.asList(elementArr), true, false)));
    }

    public final ImmutableScrollableListElement withElements(Iterable<? extends Element> iterable) {
        if (this.elements == iterable) {
            return this;
        }
        return new ImmutableScrollableListElement(this.label, this.duration, this.speed, this.backgroundImage, this.shouldBlurBackground, this.onViewed, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableScrollableListElement) && equalTo(0, (ImmutableScrollableListElement) obj);
    }

    private boolean equalTo(int i, ImmutableScrollableListElement immutableScrollableListElement) {
        return this.label.equals(immutableScrollableListElement.label) && equals(this.duration, immutableScrollableListElement.duration) && equals(this.speed, immutableScrollableListElement.speed) && equals(this.backgroundImage, immutableScrollableListElement.backgroundImage) && this.shouldBlurBackground.equals(immutableScrollableListElement.shouldBlurBackground) && this.onViewed.equals(immutableScrollableListElement.onViewed) && this.elements.equals(immutableScrollableListElement.elements);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.label.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.duration);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.speed);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.backgroundImage);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.shouldBlurBackground.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.onViewed.hashCode();
        return iHashCode6 + (iHashCode6 << 5) + this.elements.hashCode();
    }

    public String toString() {
        return "ScrollableListElement{label=" + this.label + ", duration=" + this.duration + ", speed=" + this.speed + ", backgroundImage=" + this.backgroundImage + ", shouldBlurBackground=" + this.shouldBlurBackground + ", onViewed=" + this.onViewed + ", elements=" + this.elements + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ScrollableListElement", generator = "Immutables")
    static final class Json extends ScrollableListElement {

        @Nullable
        String backgroundImage;

        @Nullable
        Integer duration;

        @Nullable
        SwipeablePageLabelElement label;
        boolean onViewedIsSet;

        @Nullable
        Boolean shouldBlurBackground;

        @Nullable
        Integer speed;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<Element> elements = Collections.emptyList();

        Json() {
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public void setLabel(SwipeablePageLabelElement swipeablePageLabelElement) {
            this.label = swipeablePageLabelElement;
        }

        @JsonProperty("duration")
        public void setDuration(@Nullable Integer num) {
            this.duration = num;
        }

        @JsonProperty("speed")
        public void setSpeed(@Nullable Integer num) {
            this.speed = num;
        }

        @JsonProperty("backgroundImage")
        public void setBackgroundImage(@Nullable String str) {
            this.backgroundImage = str;
        }

        @JsonProperty("shouldBlurBackground")
        public void setShouldBlurBackground(Boolean bool) {
            this.shouldBlurBackground = bool;
        }

        @JsonProperty("onViewed")
        public void setOnViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedIsSet = true;
        }

        @JsonProperty(ContextMappingConstants.ELEMENTS)
        public void setElements(List<Element> list) {
            this.elements = list;
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
        public SwipeablePageLabelElement label() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
        public Integer duration() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
        public Integer speed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
        public String backgroundImage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
        public Boolean shouldBlurBackground() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ScrollableListElement
        public List<Element> elements() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableScrollableListElement fromJson(Json json) {
        Builder builder = builder();
        SwipeablePageLabelElement swipeablePageLabelElement = json.label;
        if (swipeablePageLabelElement != null) {
            builder.label(swipeablePageLabelElement);
        }
        Integer num = json.duration;
        if (num != null) {
            builder.duration(num);
        }
        Integer num2 = json.speed;
        if (num2 != null) {
            builder.speed(num2);
        }
        String str = json.backgroundImage;
        if (str != null) {
            builder.backgroundImage(str);
        }
        Boolean bool = json.shouldBlurBackground;
        if (bool != null) {
            builder.shouldBlurBackground(bool);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        List<Element> list = json.elements;
        if (list != null) {
            builder.addAllElements(list);
        }
        return builder.build();
    }

    public static ImmutableScrollableListElement copyOf(ScrollableListElement scrollableListElement) {
        if (scrollableListElement instanceof ImmutableScrollableListElement) {
            return (ImmutableScrollableListElement) scrollableListElement;
        }
        return builder().from(scrollableListElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ScrollableListElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_LABEL = 1;
        private static final long INIT_BIT_SHOULD_BLUR_BACKGROUND = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private String backgroundImage;

        @Nullable
        private Integer duration;
        private List<Element> elements;
        private long initBits;

        @Nullable
        private SwipeablePageLabelElement label;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private Boolean shouldBlurBackground;

        @Nullable
        private Integer speed;

        private Builder() {
            this.initBits = 3L;
            this.onViewed = new ArrayList();
            this.elements = new ArrayList();
        }

        public final Builder from(SwipeablePageElement swipeablePageElement) {
            ImmutableScrollableListElement.requireNonNull(swipeablePageElement, "instance");
            from((Object) swipeablePageElement);
            return this;
        }

        public final Builder from(ScrollableListElement scrollableListElement) {
            ImmutableScrollableListElement.requireNonNull(scrollableListElement, "instance");
            from((Object) scrollableListElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            Integer numSpeed;
            if (obj instanceof SwipeablePageElement) {
                SwipeablePageElement swipeablePageElement = (SwipeablePageElement) obj;
                Integer numDuration = swipeablePageElement.duration();
                if (numDuration != null) {
                    duration(numDuration);
                }
                label(swipeablePageElement.label());
                addAllOnViewed(swipeablePageElement.onViewed());
                shouldBlurBackground(swipeablePageElement.shouldBlurBackground());
                String strBackgroundImage = swipeablePageElement.backgroundImage();
                if (strBackgroundImage != null) {
                    backgroundImage(strBackgroundImage);
                }
                Integer numSpeed2 = swipeablePageElement.speed();
                if (numSpeed2 != null) {
                    speed(numSpeed2);
                }
                j = 63;
            } else {
                j = 0;
            }
            if (obj instanceof ScrollableListElement) {
                ScrollableListElement scrollableListElement = (ScrollableListElement) obj;
                if ((j & 1) == 0) {
                    Integer numDuration2 = scrollableListElement.duration();
                    if (numDuration2 != null) {
                        duration(numDuration2);
                    }
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    String strBackgroundImage2 = scrollableListElement.backgroundImage();
                    if (strBackgroundImage2 != null) {
                        backgroundImage(strBackgroundImage2);
                    }
                    j |= 2;
                }
                addAllElements(scrollableListElement.elements());
                if ((j & 4) == 0) {
                    label(scrollableListElement.label());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    addAllOnViewed(scrollableListElement.onViewed());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    shouldBlurBackground(scrollableListElement.shouldBlurBackground());
                    j |= 16;
                }
                if ((j & 32) != 0 || (numSpeed = scrollableListElement.speed()) == null) {
                    return;
                }
                speed(numSpeed);
            }
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public final Builder label(SwipeablePageLabelElement swipeablePageLabelElement) {
            this.label = (SwipeablePageLabelElement) ImmutableScrollableListElement.requireNonNull(swipeablePageLabelElement, ContextMappingConstants.LABEL);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("duration")
        public final Builder duration(@Nullable Integer num) {
            this.duration = num;
            return this;
        }

        @JsonProperty("speed")
        public final Builder speed(@Nullable Integer num) {
            this.speed = num;
            return this;
        }

        @JsonProperty("backgroundImage")
        public final Builder backgroundImage(@Nullable String str) {
            this.backgroundImage = str;
            return this;
        }

        @JsonProperty("shouldBlurBackground")
        public final Builder shouldBlurBackground(Boolean bool) {
            this.shouldBlurBackground = (Boolean) ImmutableScrollableListElement.requireNonNull(bool, "shouldBlurBackground");
            this.initBits &= -3;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableScrollableListElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableScrollableListElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableScrollableListElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addElements(Element element) {
            this.elements.add((Element) ImmutableScrollableListElement.requireNonNull(element, "elements element"));
            return this;
        }

        public final Builder addElements(Element... elementArr) {
            for (Element element : elementArr) {
                this.elements.add((Element) ImmutableScrollableListElement.requireNonNull(element, "elements element"));
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
                this.elements.add((Element) ImmutableScrollableListElement.requireNonNull(it.next(), "elements element"));
            }
            return this;
        }

        public ImmutableScrollableListElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableScrollableListElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.LABEL);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("shouldBlurBackground");
            }
            return "Cannot build ScrollableListElement, some of required attributes are not set " + arrayList;
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

    private static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
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

    private static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
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
