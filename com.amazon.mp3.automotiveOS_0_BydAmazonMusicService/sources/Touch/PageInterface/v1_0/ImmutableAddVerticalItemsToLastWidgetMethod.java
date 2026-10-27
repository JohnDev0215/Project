package Touch.PageInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
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
@Generated(from = "AddVerticalItemsToLastWidgetMethod", generator = "Immutables")
public final class ImmutableAddVerticalItemsToLastWidgetMethod extends AddVerticalItemsToLastWidgetMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final List<VerticalItemElement> items;
    private final List<Method> onEndOfWidget;
    private final List<Method> onViewed;
    private final Queue queue;

    private ImmutableAddVerticalItemsToLastWidgetMethod(Builder builder) {
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

    private ImmutableAddVerticalItemsToLastWidgetMethod(Queue queue, Boolean bool, List<VerticalItemElement> list, List<Method> list2, List<Method> list3) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.items = list;
        this.onEndOfWidget = list2;
        this.onViewed = list3;
        this.initShim = null;
    }

    @Generated(from = "AddVerticalItemsToLastWidgetMethod", generator = "Immutables")
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
                this.forced = (Boolean) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(ImmutableAddVerticalItemsToLastWidgetMethod.super.forced(), "forced");
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
                this.onEndOfWidget = ImmutableAddVerticalItemsToLastWidgetMethod.createUnmodifiableList(false, ImmutableAddVerticalItemsToLastWidgetMethod.createSafeList(ImmutableAddVerticalItemsToLastWidgetMethod.super.onEndOfWidget(), true, false));
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
                this.onViewed = ImmutableAddVerticalItemsToLastWidgetMethod.createUnmodifiableList(false, ImmutableAddVerticalItemsToLastWidgetMethod.createSafeList(ImmutableAddVerticalItemsToLastWidgetMethod.super.onViewed(), true, false));
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
            return "Cannot build AddVerticalItemsToLastWidgetMethod, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.PageInterface.v1_0.AddVerticalItemsToLastWidgetMethod
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<VerticalItemElement> items() {
        return this.items;
    }

    @Override // Touch.PageInterface.v1_0.AddVerticalItemsToLastWidgetMethod
    @JsonProperty("onEndOfWidget")
    public List<Method> onEndOfWidget() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEndOfWidget();
        }
        return this.onEndOfWidget;
    }

    @Override // Touch.PageInterface.v1_0.AddVerticalItemsToLastWidgetMethod
    @JsonProperty("onViewed")
    public List<Method> onViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onViewed();
        }
        return this.onViewed;
    }

    public final ImmutableAddVerticalItemsToLastWidgetMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableAddVerticalItemsToLastWidgetMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.items, this.onEndOfWidget, this.onViewed);
    }

    public final ImmutableAddVerticalItemsToLastWidgetMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableAddVerticalItemsToLastWidgetMethod(this.queue, bool2, this.items, this.onEndOfWidget, this.onViewed);
    }

    public final ImmutableAddVerticalItemsToLastWidgetMethod withItems(VerticalItemElement... verticalItemElementArr) {
        return new ImmutableAddVerticalItemsToLastWidgetMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(verticalItemElementArr), true, false)), this.onEndOfWidget, this.onViewed);
    }

    public final ImmutableAddVerticalItemsToLastWidgetMethod withItems(Iterable<? extends VerticalItemElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableAddVerticalItemsToLastWidgetMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onEndOfWidget, this.onViewed);
    }

    public final ImmutableAddVerticalItemsToLastWidgetMethod withOnEndOfWidget(Method... methodArr) {
        return new ImmutableAddVerticalItemsToLastWidgetMethod(this.queue, this.forced, this.items, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed);
    }

    public final ImmutableAddVerticalItemsToLastWidgetMethod withOnEndOfWidget(Iterable<? extends Method> iterable) {
        if (this.onEndOfWidget == iterable) {
            return this;
        }
        return new ImmutableAddVerticalItemsToLastWidgetMethod(this.queue, this.forced, this.items, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed);
    }

    public final ImmutableAddVerticalItemsToLastWidgetMethod withOnViewed(Method... methodArr) {
        return new ImmutableAddVerticalItemsToLastWidgetMethod(this.queue, this.forced, this.items, this.onEndOfWidget, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableAddVerticalItemsToLastWidgetMethod withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableAddVerticalItemsToLastWidgetMethod(this.queue, this.forced, this.items, this.onEndOfWidget, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableAddVerticalItemsToLastWidgetMethod) && equalTo(0, (ImmutableAddVerticalItemsToLastWidgetMethod) obj);
    }

    private boolean equalTo(int i, ImmutableAddVerticalItemsToLastWidgetMethod immutableAddVerticalItemsToLastWidgetMethod) {
        return this.queue.equals(immutableAddVerticalItemsToLastWidgetMethod.queue) && this.forced.equals(immutableAddVerticalItemsToLastWidgetMethod.forced) && this.items.equals(immutableAddVerticalItemsToLastWidgetMethod.items) && this.onEndOfWidget.equals(immutableAddVerticalItemsToLastWidgetMethod.onEndOfWidget) && this.onViewed.equals(immutableAddVerticalItemsToLastWidgetMethod.onViewed);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.items.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onEndOfWidget.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onViewed.hashCode();
    }

    public String toString() {
        return "AddVerticalItemsToLastWidgetMethod{queue=" + this.queue + ", forced=" + this.forced + ", items=" + this.items + ", onEndOfWidget=" + this.onEndOfWidget + ", onViewed=" + this.onViewed + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "AddVerticalItemsToLastWidgetMethod", generator = "Immutables")
    static final class Json extends AddVerticalItemsToLastWidgetMethod {

        @Nullable
        Boolean forced;
        boolean onEndOfWidgetIsSet;
        boolean onViewedIsSet;

        @Nullable
        Queue queue;

        @Nullable
        List<VerticalItemElement> items = Collections.emptyList();

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
        public void setItems(List<VerticalItemElement> list) {
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

        @Override // Touch.PageInterface.v1_0.AddVerticalItemsToLastWidgetMethod
        public List<VerticalItemElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.AddVerticalItemsToLastWidgetMethod
        public List<Method> onEndOfWidget() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.AddVerticalItemsToLastWidgetMethod
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableAddVerticalItemsToLastWidgetMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<VerticalItemElement> list = json.items;
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

    public static ImmutableAddVerticalItemsToLastWidgetMethod copyOf(AddVerticalItemsToLastWidgetMethod addVerticalItemsToLastWidgetMethod) {
        if (addVerticalItemsToLastWidgetMethod instanceof ImmutableAddVerticalItemsToLastWidgetMethod) {
            return (ImmutableAddVerticalItemsToLastWidgetMethod) addVerticalItemsToLastWidgetMethod;
        }
        return builder().from(addVerticalItemsToLastWidgetMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "AddVerticalItemsToLastWidgetMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_ON_END_OF_WIDGET = 1;
        private static final long OPT_BIT_ON_VIEWED = 2;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<VerticalItemElement> items;
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

        public final Builder from(AddVerticalItemsToLastWidgetMethod addVerticalItemsToLastWidgetMethod) {
            ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(addVerticalItemsToLastWidgetMethod, "instance");
            from((Object) addVerticalItemsToLastWidgetMethod);
            return this;
        }

        public final Builder from(Method method) {
            ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof AddVerticalItemsToLastWidgetMethod) {
                AddVerticalItemsToLastWidgetMethod addVerticalItemsToLastWidgetMethod = (AddVerticalItemsToLastWidgetMethod) obj;
                addAllOnEndOfWidget(addVerticalItemsToLastWidgetMethod.onEndOfWidget());
                addAllOnViewed(addVerticalItemsToLastWidgetMethod.onViewed());
                addAllItems(addVerticalItemsToLastWidgetMethod.items());
                forced(addVerticalItemsToLastWidgetMethod.forced());
                queue(addVerticalItemsToLastWidgetMethod.queue());
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof Method) {
                Method method = (Method) obj;
                if ((j & 1) == 0) {
                    forced(method.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(method.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addItems(VerticalItemElement verticalItemElement) {
            this.items.add((VerticalItemElement) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(verticalItemElement, "items element"));
            return this;
        }

        public final Builder addItems(VerticalItemElement... verticalItemElementArr) {
            for (VerticalItemElement verticalItemElement : verticalItemElementArr) {
                this.items.add((VerticalItemElement) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(verticalItemElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends VerticalItemElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends VerticalItemElement> iterable) {
            Iterator<? extends VerticalItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((VerticalItemElement) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        public final Builder addOnEndOfWidget(Method method) {
            this.onEndOfWidget.add((Method) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(method, "onEndOfWidget element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEndOfWidget(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEndOfWidget.add((Method) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(method, "onEndOfWidget element"));
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
                this.onEndOfWidget.add((Method) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(it.next(), "onEndOfWidget element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(method, "onViewed element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableAddVerticalItemsToLastWidgetMethod.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableAddVerticalItemsToLastWidgetMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableAddVerticalItemsToLastWidgetMethod(this);
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
            return "Cannot build AddVerticalItemsToLastWidgetMethod, some of required attributes are not set " + arrayList;
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
