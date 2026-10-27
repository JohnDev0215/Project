package Touch.DetailTemplateInterface.v1_0;

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
@Generated(from = "AddVerticalItemsMethod", generator = "Immutables")
public final class ImmutableAddVerticalItemsMethod extends AddVerticalItemsMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final List<VerticalItemElement> items;
    private final List<Method> onEndOfList;
    private final Queue queue;

    private ImmutableAddVerticalItemsMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.itemsIsSet()) {
            this.initShim.items(createUnmodifiableList(true, builder.items));
        }
        if (builder.onEndOfListIsSet()) {
            this.initShim.onEndOfList(createUnmodifiableList(true, builder.onEndOfList));
        }
        this.forced = this.initShim.forced();
        this.items = this.initShim.items();
        this.onEndOfList = this.initShim.onEndOfList();
        this.initShim = null;
    }

    private ImmutableAddVerticalItemsMethod(Queue queue, Boolean bool, List<VerticalItemElement> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.items = list;
        this.onEndOfList = list2;
        this.initShim = null;
    }

    @Generated(from = "AddVerticalItemsMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private List<VerticalItemElement> items;
        private byte itemsBuildStage;
        private List<Method> onEndOfList;
        private byte onEndOfListBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.itemsBuildStage = (byte) 0;
            this.onEndOfListBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableAddVerticalItemsMethod.requireNonNull(ImmutableAddVerticalItemsMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        List<VerticalItemElement> items() {
            byte b = this.itemsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.itemsBuildStage = (byte) -1;
                this.items = ImmutableAddVerticalItemsMethod.createUnmodifiableList(false, ImmutableAddVerticalItemsMethod.createSafeList(ImmutableAddVerticalItemsMethod.super.items(), true, false));
                this.itemsBuildStage = (byte) 1;
            }
            return this.items;
        }

        void items(List<VerticalItemElement> list) {
            this.items = list;
            this.itemsBuildStage = (byte) 1;
        }

        List<Method> onEndOfList() {
            byte b = this.onEndOfListBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEndOfListBuildStage = (byte) -1;
                this.onEndOfList = ImmutableAddVerticalItemsMethod.createUnmodifiableList(false, ImmutableAddVerticalItemsMethod.createSafeList(ImmutableAddVerticalItemsMethod.super.onEndOfList(), true, false));
                this.onEndOfListBuildStage = (byte) 1;
            }
            return this.onEndOfList;
        }

        void onEndOfList(List<Method> list) {
            this.onEndOfList = list;
            this.onEndOfListBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.itemsBuildStage == -1) {
                arrayList.add(ContextMappingConstants.ITEMS);
            }
            if (this.onEndOfListBuildStage == -1) {
                arrayList.add("onEndOfList");
            }
            return "Cannot build AddVerticalItemsMethod, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.DetailTemplateInterface.v1_0.AddVerticalItemsMethod
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<VerticalItemElement> items() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.items();
        }
        return this.items;
    }

    @Override // Touch.DetailTemplateInterface.v1_0.AddVerticalItemsMethod
    @JsonProperty("onEndOfList")
    public List<Method> onEndOfList() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEndOfList();
        }
        return this.onEndOfList;
    }

    public final ImmutableAddVerticalItemsMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableAddVerticalItemsMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.items, this.onEndOfList);
    }

    public final ImmutableAddVerticalItemsMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableAddVerticalItemsMethod(this.queue, bool2, this.items, this.onEndOfList);
    }

    public final ImmutableAddVerticalItemsMethod withItems(VerticalItemElement... verticalItemElementArr) {
        return new ImmutableAddVerticalItemsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(verticalItemElementArr), true, false)), this.onEndOfList);
    }

    public final ImmutableAddVerticalItemsMethod withItems(Iterable<? extends VerticalItemElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableAddVerticalItemsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onEndOfList);
    }

    public final ImmutableAddVerticalItemsMethod withOnEndOfList(Method... methodArr) {
        return new ImmutableAddVerticalItemsMethod(this.queue, this.forced, this.items, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableAddVerticalItemsMethod withOnEndOfList(Iterable<? extends Method> iterable) {
        if (this.onEndOfList == iterable) {
            return this;
        }
        return new ImmutableAddVerticalItemsMethod(this.queue, this.forced, this.items, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableAddVerticalItemsMethod) && equalTo(0, (ImmutableAddVerticalItemsMethod) obj);
    }

    private boolean equalTo(int i, ImmutableAddVerticalItemsMethod immutableAddVerticalItemsMethod) {
        return this.queue.equals(immutableAddVerticalItemsMethod.queue) && this.forced.equals(immutableAddVerticalItemsMethod.forced) && this.items.equals(immutableAddVerticalItemsMethod.items) && this.onEndOfList.equals(immutableAddVerticalItemsMethod.onEndOfList);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.items.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.onEndOfList.hashCode();
    }

    public String toString() {
        return "AddVerticalItemsMethod{queue=" + this.queue + ", forced=" + this.forced + ", items=" + this.items + ", onEndOfList=" + this.onEndOfList + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "AddVerticalItemsMethod", generator = "Immutables")
    static final class Json extends AddVerticalItemsMethod {

        @Nullable
        Boolean forced;
        boolean itemsIsSet;
        boolean onEndOfListIsSet;

        @Nullable
        Queue queue;

        @Nullable
        List<VerticalItemElement> items = Collections.emptyList();

        @Nullable
        List<Method> onEndOfList = Collections.emptyList();

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
            this.itemsIsSet = true;
        }

        @JsonProperty("onEndOfList")
        public void setOnEndOfList(List<Method> list) {
            this.onEndOfList = list;
            this.onEndOfListIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.DetailTemplateInterface.v1_0.AddVerticalItemsMethod
        public List<VerticalItemElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.DetailTemplateInterface.v1_0.AddVerticalItemsMethod
        public List<Method> onEndOfList() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableAddVerticalItemsMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        if (json.itemsIsSet) {
            builder.addAllItems(json.items);
        }
        if (json.onEndOfListIsSet) {
            builder.addAllOnEndOfList(json.onEndOfList);
        }
        return builder.build();
    }

    public static ImmutableAddVerticalItemsMethod copyOf(AddVerticalItemsMethod addVerticalItemsMethod) {
        if (addVerticalItemsMethod instanceof ImmutableAddVerticalItemsMethod) {
            return (ImmutableAddVerticalItemsMethod) addVerticalItemsMethod;
        }
        return builder().from(addVerticalItemsMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "AddVerticalItemsMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_ITEMS = 1;
        private static final long OPT_BIT_ON_END_OF_LIST = 2;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<VerticalItemElement> items;
        private List<Method> onEndOfList;
        private long optBits;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
            this.items = new ArrayList();
            this.onEndOfList = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableAddVerticalItemsMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(AddVerticalItemsMethod addVerticalItemsMethod) {
            ImmutableAddVerticalItemsMethod.requireNonNull(addVerticalItemsMethod, "instance");
            from((Object) addVerticalItemsMethod);
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
            if (obj instanceof AddVerticalItemsMethod) {
                AddVerticalItemsMethod addVerticalItemsMethod = (AddVerticalItemsMethod) obj;
                addAllOnEndOfList(addVerticalItemsMethod.onEndOfList());
                addAllItems(addVerticalItemsMethod.items());
                if ((j & 1) == 0) {
                    forced(addVerticalItemsMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(addVerticalItemsMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableAddVerticalItemsMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableAddVerticalItemsMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addItems(VerticalItemElement verticalItemElement) {
            this.items.add((VerticalItemElement) ImmutableAddVerticalItemsMethod.requireNonNull(verticalItemElement, "items element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addItems(VerticalItemElement... verticalItemElementArr) {
            for (VerticalItemElement verticalItemElement : verticalItemElementArr) {
                this.items.add((VerticalItemElement) ImmutableAddVerticalItemsMethod.requireNonNull(verticalItemElement, "items element"));
            }
            this.optBits |= 1;
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
                this.items.add((VerticalItemElement) ImmutableAddVerticalItemsMethod.requireNonNull(it.next(), "items element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEndOfList(Method method) {
            this.onEndOfList.add((Method) ImmutableAddVerticalItemsMethod.requireNonNull(method, "onEndOfList element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnEndOfList(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEndOfList.add((Method) ImmutableAddVerticalItemsMethod.requireNonNull(method, "onEndOfList element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onEndOfList")
        public final Builder onEndOfList(Iterable<? extends Method> iterable) {
            this.onEndOfList.clear();
            return addAllOnEndOfList(iterable);
        }

        public final Builder addAllOnEndOfList(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEndOfList.add((Method) ImmutableAddVerticalItemsMethod.requireNonNull(it.next(), "onEndOfList element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableAddVerticalItemsMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableAddVerticalItemsMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean itemsIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEndOfListIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build AddVerticalItemsMethod, some of required attributes are not set " + arrayList;
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
