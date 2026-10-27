package Watch.ListTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
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
@Generated(from = "AddWidgetsToEndOfListMethod", generator = "Immutables")
public final class ImmutableAddWidgetsToEndOfListMethod extends AddWidgetsToEndOfListMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final List<Method> onEndOfWidgetsReached;
    private final Queue queue;
    private final List<WidgetElement> widgets;

    private ImmutableAddWidgetsToEndOfListMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.widgets = createUnmodifiableList(true, builder.widgets);
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.onEndOfWidgetsReachedIsSet()) {
            this.initShim.onEndOfWidgetsReached(createUnmodifiableList(true, builder.onEndOfWidgetsReached));
        }
        this.forced = this.initShim.forced();
        this.onEndOfWidgetsReached = this.initShim.onEndOfWidgetsReached();
        this.initShim = null;
    }

    private ImmutableAddWidgetsToEndOfListMethod(Queue queue, Boolean bool, List<WidgetElement> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.widgets = list;
        this.onEndOfWidgetsReached = list2;
        this.initShim = null;
    }

    @Generated(from = "AddWidgetsToEndOfListMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private List<Method> onEndOfWidgetsReached;
        private byte onEndOfWidgetsReachedBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.onEndOfWidgetsReachedBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableAddWidgetsToEndOfListMethod.requireNonNull(ImmutableAddWidgetsToEndOfListMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        List<Method> onEndOfWidgetsReached() {
            byte b = this.onEndOfWidgetsReachedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEndOfWidgetsReachedBuildStage = (byte) -1;
                this.onEndOfWidgetsReached = ImmutableAddWidgetsToEndOfListMethod.createUnmodifiableList(false, ImmutableAddWidgetsToEndOfListMethod.createSafeList(ImmutableAddWidgetsToEndOfListMethod.super.onEndOfWidgetsReached(), true, false));
                this.onEndOfWidgetsReachedBuildStage = (byte) 1;
            }
            return this.onEndOfWidgetsReached;
        }

        void onEndOfWidgetsReached(List<Method> list) {
            this.onEndOfWidgetsReached = list;
            this.onEndOfWidgetsReachedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.onEndOfWidgetsReachedBuildStage == -1) {
                arrayList.add("onEndOfWidgetsReached");
            }
            return "Cannot build AddWidgetsToEndOfListMethod, attribute initializers form cycle " + arrayList;
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

    @Override // Watch.ListTemplateInterface.v1_0.AddWidgetsToEndOfListMethod
    @JsonProperty("widgets")
    public List<WidgetElement> widgets() {
        return this.widgets;
    }

    @Override // Watch.ListTemplateInterface.v1_0.AddWidgetsToEndOfListMethod
    @JsonProperty("onEndOfWidgetsReached")
    public List<Method> onEndOfWidgetsReached() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEndOfWidgetsReached();
        }
        return this.onEndOfWidgetsReached;
    }

    public final ImmutableAddWidgetsToEndOfListMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableAddWidgetsToEndOfListMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableAddWidgetsToEndOfListMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableAddWidgetsToEndOfListMethod(this.queue, bool2, this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableAddWidgetsToEndOfListMethod withWidgets(WidgetElement... widgetElementArr) {
        return new ImmutableAddWidgetsToEndOfListMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(widgetElementArr), true, false)), this.onEndOfWidgetsReached);
    }

    public final ImmutableAddWidgetsToEndOfListMethod withWidgets(Iterable<? extends WidgetElement> iterable) {
        if (this.widgets == iterable) {
            return this;
        }
        return new ImmutableAddWidgetsToEndOfListMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onEndOfWidgetsReached);
    }

    public final ImmutableAddWidgetsToEndOfListMethod withOnEndOfWidgetsReached(Method... methodArr) {
        return new ImmutableAddWidgetsToEndOfListMethod(this.queue, this.forced, this.widgets, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableAddWidgetsToEndOfListMethod withOnEndOfWidgetsReached(Iterable<? extends Method> iterable) {
        if (this.onEndOfWidgetsReached == iterable) {
            return this;
        }
        return new ImmutableAddWidgetsToEndOfListMethod(this.queue, this.forced, this.widgets, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableAddWidgetsToEndOfListMethod) && equalTo(0, (ImmutableAddWidgetsToEndOfListMethod) obj);
    }

    private boolean equalTo(int i, ImmutableAddWidgetsToEndOfListMethod immutableAddWidgetsToEndOfListMethod) {
        return this.queue.equals(immutableAddWidgetsToEndOfListMethod.queue) && this.forced.equals(immutableAddWidgetsToEndOfListMethod.forced) && this.widgets.equals(immutableAddWidgetsToEndOfListMethod.widgets) && this.onEndOfWidgetsReached.equals(immutableAddWidgetsToEndOfListMethod.onEndOfWidgetsReached);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.widgets.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.onEndOfWidgetsReached.hashCode();
    }

    public String toString() {
        return "AddWidgetsToEndOfListMethod{queue=" + this.queue + ", forced=" + this.forced + ", widgets=" + this.widgets + ", onEndOfWidgetsReached=" + this.onEndOfWidgetsReached + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "AddWidgetsToEndOfListMethod", generator = "Immutables")
    static final class Json extends AddWidgetsToEndOfListMethod {

        @Nullable
        Boolean forced;
        boolean onEndOfWidgetsReachedIsSet;

        @Nullable
        Queue queue;

        @Nullable
        List<WidgetElement> widgets = Collections.emptyList();

        @Nullable
        List<Method> onEndOfWidgetsReached = Collections.emptyList();

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

        @JsonProperty("widgets")
        public void setWidgets(List<WidgetElement> list) {
            this.widgets = list;
        }

        @JsonProperty("onEndOfWidgetsReached")
        public void setOnEndOfWidgetsReached(List<Method> list) {
            this.onEndOfWidgetsReached = list;
            this.onEndOfWidgetsReachedIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.AddWidgetsToEndOfListMethod
        public List<WidgetElement> widgets() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.AddWidgetsToEndOfListMethod
        public List<Method> onEndOfWidgetsReached() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableAddWidgetsToEndOfListMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<WidgetElement> list = json.widgets;
        if (list != null) {
            builder.addAllWidgets(list);
        }
        if (json.onEndOfWidgetsReachedIsSet) {
            builder.addAllOnEndOfWidgetsReached(json.onEndOfWidgetsReached);
        }
        return builder.build();
    }

    public static ImmutableAddWidgetsToEndOfListMethod copyOf(AddWidgetsToEndOfListMethod addWidgetsToEndOfListMethod) {
        if (addWidgetsToEndOfListMethod instanceof ImmutableAddWidgetsToEndOfListMethod) {
            return (ImmutableAddWidgetsToEndOfListMethod) addWidgetsToEndOfListMethod;
        }
        return builder().from(addWidgetsToEndOfListMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "AddWidgetsToEndOfListMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_ON_END_OF_WIDGETS_REACHED = 1;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<Method> onEndOfWidgetsReached;
        private long optBits;

        @Nullable
        private Queue queue;
        private List<WidgetElement> widgets;

        private Builder() {
            this.initBits = 1L;
            this.widgets = new ArrayList();
            this.onEndOfWidgetsReached = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableAddWidgetsToEndOfListMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(AddWidgetsToEndOfListMethod addWidgetsToEndOfListMethod) {
            ImmutableAddWidgetsToEndOfListMethod.requireNonNull(addWidgetsToEndOfListMethod, "instance");
            from((Object) addWidgetsToEndOfListMethod);
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
            if (obj instanceof AddWidgetsToEndOfListMethod) {
                AddWidgetsToEndOfListMethod addWidgetsToEndOfListMethod = (AddWidgetsToEndOfListMethod) obj;
                addAllWidgets(addWidgetsToEndOfListMethod.widgets());
                if ((j & 1) == 0) {
                    forced(addWidgetsToEndOfListMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(addWidgetsToEndOfListMethod.queue());
                }
                addAllOnEndOfWidgetsReached(addWidgetsToEndOfListMethod.onEndOfWidgetsReached());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableAddWidgetsToEndOfListMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableAddWidgetsToEndOfListMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addWidgets(WidgetElement widgetElement) {
            this.widgets.add((WidgetElement) ImmutableAddWidgetsToEndOfListMethod.requireNonNull(widgetElement, "widgets element"));
            return this;
        }

        public final Builder addWidgets(WidgetElement... widgetElementArr) {
            for (WidgetElement widgetElement : widgetElementArr) {
                this.widgets.add((WidgetElement) ImmutableAddWidgetsToEndOfListMethod.requireNonNull(widgetElement, "widgets element"));
            }
            return this;
        }

        @JsonProperty("widgets")
        public final Builder widgets(Iterable<? extends WidgetElement> iterable) {
            this.widgets.clear();
            return addAllWidgets(iterable);
        }

        public final Builder addAllWidgets(Iterable<? extends WidgetElement> iterable) {
            Iterator<? extends WidgetElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.widgets.add((WidgetElement) ImmutableAddWidgetsToEndOfListMethod.requireNonNull(it.next(), "widgets element"));
            }
            return this;
        }

        public final Builder addOnEndOfWidgetsReached(Method method) {
            this.onEndOfWidgetsReached.add((Method) ImmutableAddWidgetsToEndOfListMethod.requireNonNull(method, "onEndOfWidgetsReached element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEndOfWidgetsReached(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEndOfWidgetsReached.add((Method) ImmutableAddWidgetsToEndOfListMethod.requireNonNull(method, "onEndOfWidgetsReached element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEndOfWidgetsReached")
        public final Builder onEndOfWidgetsReached(Iterable<? extends Method> iterable) {
            this.onEndOfWidgetsReached.clear();
            return addAllOnEndOfWidgetsReached(iterable);
        }

        public final Builder addAllOnEndOfWidgetsReached(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEndOfWidgetsReached.add((Method) ImmutableAddWidgetsToEndOfListMethod.requireNonNull(it.next(), "onEndOfWidgetsReached element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableAddWidgetsToEndOfListMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableAddWidgetsToEndOfListMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEndOfWidgetsReachedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build AddWidgetsToEndOfListMethod, some of required attributes are not set " + arrayList;
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
