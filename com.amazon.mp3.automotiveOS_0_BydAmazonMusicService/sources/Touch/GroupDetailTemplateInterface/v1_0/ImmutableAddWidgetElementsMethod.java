package Touch.GroupDetailTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import Touch.WidgetsInterface.v1_0.WidgetElement;
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
@Generated(from = "AddWidgetElementsMethod", generator = "Immutables")
public final class ImmutableAddWidgetElementsMethod extends AddWidgetElementsMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final List<WidgetElement> elements;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final List<Method> onEndOfList;
    private final Queue queue;

    private ImmutableAddWidgetElementsMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.elementsIsSet()) {
            this.initShim.elements(createUnmodifiableList(true, builder.elements));
        }
        if (builder.onEndOfListIsSet()) {
            this.initShim.onEndOfList(createUnmodifiableList(true, builder.onEndOfList));
        }
        this.forced = this.initShim.forced();
        this.elements = this.initShim.elements();
        this.onEndOfList = this.initShim.onEndOfList();
        this.initShim = null;
    }

    private ImmutableAddWidgetElementsMethod(Queue queue, Boolean bool, List<WidgetElement> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.elements = list;
        this.onEndOfList = list2;
        this.initShim = null;
    }

    @Generated(from = "AddWidgetElementsMethod", generator = "Immutables")
    private final class InitShim {
        private List<WidgetElement> elements;
        private byte elementsBuildStage;
        private Boolean forced;
        private byte forcedBuildStage;
        private List<Method> onEndOfList;
        private byte onEndOfListBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.elementsBuildStage = (byte) 0;
            this.onEndOfListBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableAddWidgetElementsMethod.requireNonNull(ImmutableAddWidgetElementsMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        List<WidgetElement> elements() {
            byte b = this.elementsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.elementsBuildStage = (byte) -1;
                this.elements = ImmutableAddWidgetElementsMethod.createUnmodifiableList(false, ImmutableAddWidgetElementsMethod.createSafeList(ImmutableAddWidgetElementsMethod.super.elements(), true, false));
                this.elementsBuildStage = (byte) 1;
            }
            return this.elements;
        }

        void elements(List<WidgetElement> list) {
            this.elements = list;
            this.elementsBuildStage = (byte) 1;
        }

        List<Method> onEndOfList() {
            byte b = this.onEndOfListBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEndOfListBuildStage = (byte) -1;
                this.onEndOfList = ImmutableAddWidgetElementsMethod.createUnmodifiableList(false, ImmutableAddWidgetElementsMethod.createSafeList(ImmutableAddWidgetElementsMethod.super.onEndOfList(), true, false));
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
            if (this.elementsBuildStage == -1) {
                arrayList.add(ContextMappingConstants.ELEMENTS);
            }
            if (this.onEndOfListBuildStage == -1) {
                arrayList.add("onEndOfList");
            }
            return "Cannot build AddWidgetElementsMethod, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.GroupDetailTemplateInterface.v1_0.AddWidgetElementsMethod
    @JsonProperty(ContextMappingConstants.ELEMENTS)
    public List<WidgetElement> elements() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.elements();
        }
        return this.elements;
    }

    @Override // Touch.GroupDetailTemplateInterface.v1_0.AddWidgetElementsMethod
    @JsonProperty("onEndOfList")
    public List<Method> onEndOfList() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEndOfList();
        }
        return this.onEndOfList;
    }

    public final ImmutableAddWidgetElementsMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableAddWidgetElementsMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.elements, this.onEndOfList);
    }

    public final ImmutableAddWidgetElementsMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableAddWidgetElementsMethod(this.queue, bool2, this.elements, this.onEndOfList);
    }

    public final ImmutableAddWidgetElementsMethod withElements(WidgetElement... widgetElementArr) {
        return new ImmutableAddWidgetElementsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(widgetElementArr), true, false)), this.onEndOfList);
    }

    public final ImmutableAddWidgetElementsMethod withElements(Iterable<? extends WidgetElement> iterable) {
        if (this.elements == iterable) {
            return this;
        }
        return new ImmutableAddWidgetElementsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onEndOfList);
    }

    public final ImmutableAddWidgetElementsMethod withOnEndOfList(Method... methodArr) {
        return new ImmutableAddWidgetElementsMethod(this.queue, this.forced, this.elements, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableAddWidgetElementsMethod withOnEndOfList(Iterable<? extends Method> iterable) {
        if (this.onEndOfList == iterable) {
            return this;
        }
        return new ImmutableAddWidgetElementsMethod(this.queue, this.forced, this.elements, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableAddWidgetElementsMethod) && equalTo(0, (ImmutableAddWidgetElementsMethod) obj);
    }

    private boolean equalTo(int i, ImmutableAddWidgetElementsMethod immutableAddWidgetElementsMethod) {
        return this.queue.equals(immutableAddWidgetElementsMethod.queue) && this.forced.equals(immutableAddWidgetElementsMethod.forced) && this.elements.equals(immutableAddWidgetElementsMethod.elements) && this.onEndOfList.equals(immutableAddWidgetElementsMethod.onEndOfList);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.elements.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.onEndOfList.hashCode();
    }

    public String toString() {
        return "AddWidgetElementsMethod{queue=" + this.queue + ", forced=" + this.forced + ", elements=" + this.elements + ", onEndOfList=" + this.onEndOfList + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "AddWidgetElementsMethod", generator = "Immutables")
    static final class Json extends AddWidgetElementsMethod {
        boolean elementsIsSet;

        @Nullable
        Boolean forced;
        boolean onEndOfListIsSet;

        @Nullable
        Queue queue;

        @Nullable
        List<WidgetElement> elements = Collections.emptyList();

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

        @JsonProperty(ContextMappingConstants.ELEMENTS)
        public void setElements(List<WidgetElement> list) {
            this.elements = list;
            this.elementsIsSet = true;
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

        @Override // Touch.GroupDetailTemplateInterface.v1_0.AddWidgetElementsMethod
        public List<WidgetElement> elements() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GroupDetailTemplateInterface.v1_0.AddWidgetElementsMethod
        public List<Method> onEndOfList() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableAddWidgetElementsMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        if (json.elementsIsSet) {
            builder.addAllElements(json.elements);
        }
        if (json.onEndOfListIsSet) {
            builder.addAllOnEndOfList(json.onEndOfList);
        }
        return builder.build();
    }

    public static ImmutableAddWidgetElementsMethod copyOf(AddWidgetElementsMethod addWidgetElementsMethod) {
        if (addWidgetElementsMethod instanceof ImmutableAddWidgetElementsMethod) {
            return (ImmutableAddWidgetElementsMethod) addWidgetElementsMethod;
        }
        return builder().from(addWidgetElementsMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "AddWidgetElementsMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_ELEMENTS = 1;
        private static final long OPT_BIT_ON_END_OF_LIST = 2;
        private List<WidgetElement> elements;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<Method> onEndOfList;
        private long optBits;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
            this.elements = new ArrayList();
            this.onEndOfList = new ArrayList();
        }

        public final Builder from(AddWidgetElementsMethod addWidgetElementsMethod) {
            ImmutableAddWidgetElementsMethod.requireNonNull(addWidgetElementsMethod, "instance");
            from((Object) addWidgetElementsMethod);
            return this;
        }

        public final Builder from(Method method) {
            ImmutableAddWidgetElementsMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof AddWidgetElementsMethod) {
                AddWidgetElementsMethod addWidgetElementsMethod = (AddWidgetElementsMethod) obj;
                addAllOnEndOfList(addWidgetElementsMethod.onEndOfList());
                forced(addWidgetElementsMethod.forced());
                queue(addWidgetElementsMethod.queue());
                j = 3;
                addAllElements(addWidgetElementsMethod.elements());
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
            this.queue = (Queue) ImmutableAddWidgetElementsMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableAddWidgetElementsMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addElements(WidgetElement widgetElement) {
            this.elements.add((WidgetElement) ImmutableAddWidgetElementsMethod.requireNonNull(widgetElement, "elements element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addElements(WidgetElement... widgetElementArr) {
            for (WidgetElement widgetElement : widgetElementArr) {
                this.elements.add((WidgetElement) ImmutableAddWidgetElementsMethod.requireNonNull(widgetElement, "elements element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty(ContextMappingConstants.ELEMENTS)
        public final Builder elements(Iterable<? extends WidgetElement> iterable) {
            this.elements.clear();
            return addAllElements(iterable);
        }

        public final Builder addAllElements(Iterable<? extends WidgetElement> iterable) {
            Iterator<? extends WidgetElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.elements.add((WidgetElement) ImmutableAddWidgetElementsMethod.requireNonNull(it.next(), "elements element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEndOfList(Method method) {
            this.onEndOfList.add((Method) ImmutableAddWidgetElementsMethod.requireNonNull(method, "onEndOfList element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnEndOfList(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEndOfList.add((Method) ImmutableAddWidgetElementsMethod.requireNonNull(method, "onEndOfList element"));
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
                this.onEndOfList.add((Method) ImmutableAddWidgetElementsMethod.requireNonNull(it.next(), "onEndOfList element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableAddWidgetElementsMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableAddWidgetElementsMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean elementsIsSet() {
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
            return "Cannot build AddWidgetElementsMethod, some of required attributes are not set " + arrayList;
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
