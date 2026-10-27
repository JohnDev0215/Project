package Touch.PageInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
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
@Generated(from = "AddHorizontalItemsToLastWidgetMethod", generator = "Immutables")
public final class ImmutableAddHorizontalItemsToLastWidgetMethod extends AddHorizontalItemsToLastWidgetMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final List<HorizontalItemElement> items;
    private final List<Method> onEndOfWidget;
    private final List<Method> onViewed;
    private final Queue queue;

    private ImmutableAddHorizontalItemsToLastWidgetMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.items = createUnmodifiableList(true, builder.items);
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.onEndOfWidgetIsSet()) {
            this.initShim.onEndOfWidget(createUnmodifiableList(true, builder.onEndOfWidget));
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        this.forced = this.initShim.forced();
        this.onEndOfWidget = this.initShim.onEndOfWidget();
        this.onViewed = this.initShim.onViewed();
        this.initShim = null;
    }

    private ImmutableAddHorizontalItemsToLastWidgetMethod(Queue queue, Boolean bool, List<HorizontalItemElement> list, List<Method> list2, List<Method> list3) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.items = list;
        this.onEndOfWidget = list2;
        this.onViewed = list3;
        this.initShim = null;
    }

    @Generated(from = "AddHorizontalItemsToLastWidgetMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private List<Method> onEndOfWidget;
        private byte onEndOfWidgetBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.onEndOfWidgetBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(ImmutableAddHorizontalItemsToLastWidgetMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        List<Method> onEndOfWidget() {
            byte b = this.onEndOfWidgetBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEndOfWidgetBuildStage = (byte) -1;
                this.onEndOfWidget = ImmutableAddHorizontalItemsToLastWidgetMethod.createUnmodifiableList(false, ImmutableAddHorizontalItemsToLastWidgetMethod.createSafeList(ImmutableAddHorizontalItemsToLastWidgetMethod.super.onEndOfWidget(), true, false));
                this.onEndOfWidgetBuildStage = (byte) 1;
            }
            return this.onEndOfWidget;
        }

        void onEndOfWidget(List<Method> list) {
            this.onEndOfWidget = list;
            this.onEndOfWidgetBuildStage = (byte) 1;
        }

        List<Method> onViewed() {
            byte b = this.onViewedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onViewedBuildStage = (byte) -1;
                this.onViewed = ImmutableAddHorizontalItemsToLastWidgetMethod.createUnmodifiableList(false, ImmutableAddHorizontalItemsToLastWidgetMethod.createSafeList(ImmutableAddHorizontalItemsToLastWidgetMethod.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.onEndOfWidgetBuildStage == -1) {
                arrayList.add("onEndOfWidget");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            return "Cannot build AddHorizontalItemsToLastWidgetMethod, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("queue")
    public Queue queue() {
        return this.queue;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("forced")
    public Boolean forced() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.forced();
        }
        return this.forced;
    }

    @Override // Touch.PageInterface.v1_0.AddHorizontalItemsToLastWidgetMethod
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<HorizontalItemElement> items() {
        return this.items;
    }

    @Override // Touch.PageInterface.v1_0.AddHorizontalItemsToLastWidgetMethod
    @JsonProperty("onEndOfWidget")
    public List<Method> onEndOfWidget() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEndOfWidget();
        }
        return this.onEndOfWidget;
    }

    @Override // Touch.PageInterface.v1_0.AddHorizontalItemsToLastWidgetMethod
    @JsonProperty("onViewed")
    public List<Method> onViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onViewed();
        }
        return this.onViewed;
    }

    public final ImmutableAddHorizontalItemsToLastWidgetMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableAddHorizontalItemsToLastWidgetMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.items, this.onEndOfWidget, this.onViewed);
    }

    public final ImmutableAddHorizontalItemsToLastWidgetMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableAddHorizontalItemsToLastWidgetMethod(this.queue, bool2, this.items, this.onEndOfWidget, this.onViewed);
    }

    public final ImmutableAddHorizontalItemsToLastWidgetMethod withItems(HorizontalItemElement... horizontalItemElementArr) {
        return new ImmutableAddHorizontalItemsToLastWidgetMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(horizontalItemElementArr), true, false)), this.onEndOfWidget, this.onViewed);
    }

    public final ImmutableAddHorizontalItemsToLastWidgetMethod withItems(Iterable<? extends HorizontalItemElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableAddHorizontalItemsToLastWidgetMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onEndOfWidget, this.onViewed);
    }

    public final ImmutableAddHorizontalItemsToLastWidgetMethod withOnEndOfWidget(Method... methodArr) {
        return new ImmutableAddHorizontalItemsToLastWidgetMethod(this.queue, this.forced, this.items, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed);
    }

    public final ImmutableAddHorizontalItemsToLastWidgetMethod withOnEndOfWidget(Iterable<? extends Method> iterable) {
        if (this.onEndOfWidget == iterable) {
            return this;
        }
        return new ImmutableAddHorizontalItemsToLastWidgetMethod(this.queue, this.forced, this.items, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed);
    }

    public final ImmutableAddHorizontalItemsToLastWidgetMethod withOnViewed(Method... methodArr) {
        return new ImmutableAddHorizontalItemsToLastWidgetMethod(this.queue, this.forced, this.items, this.onEndOfWidget, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableAddHorizontalItemsToLastWidgetMethod withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableAddHorizontalItemsToLastWidgetMethod(this.queue, this.forced, this.items, this.onEndOfWidget, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableAddHorizontalItemsToLastWidgetMethod) && equalTo(0, (ImmutableAddHorizontalItemsToLastWidgetMethod) obj);
    }

    private boolean equalTo(int i, ImmutableAddHorizontalItemsToLastWidgetMethod immutableAddHorizontalItemsToLastWidgetMethod) {
        return this.queue.equals(immutableAddHorizontalItemsToLastWidgetMethod.queue) && this.forced.equals(immutableAddHorizontalItemsToLastWidgetMethod.forced) && this.items.equals(immutableAddHorizontalItemsToLastWidgetMethod.items) && this.onEndOfWidget.equals(immutableAddHorizontalItemsToLastWidgetMethod.onEndOfWidget) && this.onViewed.equals(immutableAddHorizontalItemsToLastWidgetMethod.onViewed);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.items.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onEndOfWidget.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onViewed.hashCode();
    }

    public String toString() {
        return "AddHorizontalItemsToLastWidgetMethod{queue=" + this.queue + ", forced=" + this.forced + ", items=" + this.items + ", onEndOfWidget=" + this.onEndOfWidget + ", onViewed=" + this.onViewed + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "AddHorizontalItemsToLastWidgetMethod", generator = "Immutables")
    static final class Json extends AddHorizontalItemsToLastWidgetMethod {

        @Nullable
        Boolean forced;
        boolean onEndOfWidgetIsSet;
        boolean onViewedIsSet;

        @Nullable
        Queue queue;

        @Nullable
        List<HorizontalItemElement> items = Collections.emptyList();

        @Nullable
        List<Method> onEndOfWidget = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        Json() {
        }

        @JsonProperty("queue")
        public void setQueue(Queue queue) {
            this.queue = queue;
        }

        @JsonProperty("forced")
        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(List<HorizontalItemElement> list) {
            this.items = list;
        }

        @JsonProperty("onEndOfWidget")
        public void setOnEndOfWidget(List<Method> list) {
            this.onEndOfWidget = list;
            this.onEndOfWidgetIsSet = true;
        }

        @JsonProperty("onViewed")
        public void setOnViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.AddHorizontalItemsToLastWidgetMethod
        public List<HorizontalItemElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.AddHorizontalItemsToLastWidgetMethod
        public List<Method> onEndOfWidget() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.AddHorizontalItemsToLastWidgetMethod
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableAddHorizontalItemsToLastWidgetMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<HorizontalItemElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        if (json.onEndOfWidgetIsSet) {
            builder.addAllOnEndOfWidget(json.onEndOfWidget);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        return builder.build();
    }

    public static ImmutableAddHorizontalItemsToLastWidgetMethod copyOf(AddHorizontalItemsToLastWidgetMethod addHorizontalItemsToLastWidgetMethod) {
        if (addHorizontalItemsToLastWidgetMethod instanceof ImmutableAddHorizontalItemsToLastWidgetMethod) {
            return (ImmutableAddHorizontalItemsToLastWidgetMethod) addHorizontalItemsToLastWidgetMethod;
        }
        return builder().from(addHorizontalItemsToLastWidgetMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "AddHorizontalItemsToLastWidgetMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_ON_END_OF_WIDGET = 1;
        private static final long OPT_BIT_ON_VIEWED = 2;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<HorizontalItemElement> items;
        private List<Method> onEndOfWidget;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
            this.items = new ArrayList();
            this.onEndOfWidget = new ArrayList();
            this.onViewed = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(AddHorizontalItemsToLastWidgetMethod addHorizontalItemsToLastWidgetMethod) {
            ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(addHorizontalItemsToLastWidgetMethod, "instance");
            from((Object) addHorizontalItemsToLastWidgetMethod);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof Method) {
                Method method = (Method) obj;
                forced(method.forced());
                queue(method.queue());
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof AddHorizontalItemsToLastWidgetMethod) {
                AddHorizontalItemsToLastWidgetMethod addHorizontalItemsToLastWidgetMethod = (AddHorizontalItemsToLastWidgetMethod) obj;
                addAllOnEndOfWidget(addHorizontalItemsToLastWidgetMethod.onEndOfWidget());
                addAllOnViewed(addHorizontalItemsToLastWidgetMethod.onViewed());
                addAllItems(addHorizontalItemsToLastWidgetMethod.items());
                if ((j & 1) == 0) {
                    forced(addHorizontalItemsToLastWidgetMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(addHorizontalItemsToLastWidgetMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addItems(HorizontalItemElement horizontalItemElement) {
            this.items.add((HorizontalItemElement) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(horizontalItemElement, "items element"));
            return this;
        }

        public final Builder addItems(HorizontalItemElement... horizontalItemElementArr) {
            for (HorizontalItemElement horizontalItemElement : horizontalItemElementArr) {
                this.items.add((HorizontalItemElement) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(horizontalItemElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends HorizontalItemElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends HorizontalItemElement> iterable) {
            Iterator<? extends HorizontalItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((HorizontalItemElement) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        public final Builder addOnEndOfWidget(Method method) {
            this.onEndOfWidget.add((Method) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(method, "onEndOfWidget element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEndOfWidget(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEndOfWidget.add((Method) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(method, "onEndOfWidget element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEndOfWidget")
        public final Builder onEndOfWidget(Iterable<? extends Method> iterable) {
            this.onEndOfWidget.clear();
            return addAllOnEndOfWidget(iterable);
        }

        public final Builder addAllOnEndOfWidget(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEndOfWidget.add((Method) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(it.next(), "onEndOfWidget element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(method, "onViewed element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(method, "onViewed element"));
            }
            this.optBits |= 2;
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
                this.onViewed.add((Method) ImmutableAddHorizontalItemsToLastWidgetMethod.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableAddHorizontalItemsToLastWidgetMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableAddHorizontalItemsToLastWidgetMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEndOfWidgetIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build AddHorizontalItemsToLastWidgetMethod, some of required attributes are not set " + arrayList;
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
