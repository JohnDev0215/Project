package Touch.SyncInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
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
@Generated(from = "SetWidgetSyncElementsMethod", generator = "Immutables")
public final class ImmutableSetWidgetSyncElementsMethod extends SetWidgetSyncElementsMethod {
    private final List<WidgetSyncElement> elements;
    private final Boolean forced;
    private final PageContext pageContext;
    private final Queue queue;

    private ImmutableSetWidgetSyncElementsMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.elements = createUnmodifiableList(true, builder.elements);
        this.pageContext = builder.pageContext;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableSetWidgetSyncElementsMethod(Queue queue, Boolean bool, List<WidgetSyncElement> list, PageContext pageContext) {
        this.queue = queue;
        this.forced = bool;
        this.elements = list;
        this.pageContext = pageContext;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("queue")
    public Queue queue() {
        return this.queue;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("forced")
    public Boolean forced() {
        return this.forced;
    }

    @Override // Touch.SyncInterface.v1_0.SetWidgetSyncElementsMethod
    @JsonProperty(ContextMappingConstants.ELEMENTS)
    public List<WidgetSyncElement> elements() {
        return this.elements;
    }

    @Override // Touch.SyncInterface.v1_0.SetWidgetSyncElementsMethod
    @JsonProperty(ContextMappingConstants.PAGE_CONTEXT)
    public PageContext pageContext() {
        return this.pageContext;
    }

    public final ImmutableSetWidgetSyncElementsMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetWidgetSyncElementsMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.elements, this.pageContext);
    }

    public final ImmutableSetWidgetSyncElementsMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetWidgetSyncElementsMethod(this.queue, bool2, this.elements, this.pageContext);
    }

    public final ImmutableSetWidgetSyncElementsMethod withElements(WidgetSyncElement... widgetSyncElementArr) {
        return new ImmutableSetWidgetSyncElementsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(widgetSyncElementArr), true, false)), this.pageContext);
    }

    public final ImmutableSetWidgetSyncElementsMethod withElements(Iterable<? extends WidgetSyncElement> iterable) {
        if (this.elements == iterable) {
            return this;
        }
        return new ImmutableSetWidgetSyncElementsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.pageContext);
    }

    public final ImmutableSetWidgetSyncElementsMethod withPageContext(PageContext pageContext) {
        PageContext pageContext2 = (PageContext) requireNonNull(pageContext, ContextMappingConstants.PAGE_CONTEXT);
        return this.pageContext == pageContext2 ? this : new ImmutableSetWidgetSyncElementsMethod(this.queue, this.forced, this.elements, pageContext2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetWidgetSyncElementsMethod) && equalTo(0, (ImmutableSetWidgetSyncElementsMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetWidgetSyncElementsMethod immutableSetWidgetSyncElementsMethod) {
        return this.queue.equals(immutableSetWidgetSyncElementsMethod.queue) && this.forced.equals(immutableSetWidgetSyncElementsMethod.forced) && this.elements.equals(immutableSetWidgetSyncElementsMethod.elements) && this.pageContext.equals(immutableSetWidgetSyncElementsMethod.pageContext);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.elements.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.pageContext.hashCode();
    }

    public String toString() {
        return "SetWidgetSyncElementsMethod{queue=" + this.queue + ", forced=" + this.forced + ", elements=" + this.elements + ", pageContext=" + this.pageContext + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetWidgetSyncElementsMethod", generator = "Immutables")
    static final class Json extends SetWidgetSyncElementsMethod {

        @Nullable
        List<WidgetSyncElement> elements = Collections.emptyList();

        @Nullable
        Boolean forced;

        @Nullable
        PageContext pageContext;

        @Nullable
        Queue queue;

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
        public void setElements(List<WidgetSyncElement> list) {
            this.elements = list;
        }

        @JsonProperty(ContextMappingConstants.PAGE_CONTEXT)
        public void setPageContext(PageContext pageContext) {
            this.pageContext = pageContext;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.SetWidgetSyncElementsMethod
        public List<WidgetSyncElement> elements() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.SetWidgetSyncElementsMethod
        public PageContext pageContext() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetWidgetSyncElementsMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<WidgetSyncElement> list = json.elements;
        if (list != null) {
            builder.addAllElements(list);
        }
        PageContext pageContext = json.pageContext;
        if (pageContext != null) {
            builder.pageContext(pageContext);
        }
        return builder.build();
    }

    public static ImmutableSetWidgetSyncElementsMethod copyOf(SetWidgetSyncElementsMethod setWidgetSyncElementsMethod) {
        if (setWidgetSyncElementsMethod instanceof ImmutableSetWidgetSyncElementsMethod) {
            return (ImmutableSetWidgetSyncElementsMethod) setWidgetSyncElementsMethod;
        }
        return builder().from(setWidgetSyncElementsMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetWidgetSyncElementsMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PAGE_CONTEXT = 2;
        private static final long INIT_BIT_QUEUE = 1;
        private List<WidgetSyncElement> elements;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private PageContext pageContext;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 3L;
            this.elements = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableSetWidgetSyncElementsMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SetWidgetSyncElementsMethod setWidgetSyncElementsMethod) {
            ImmutableSetWidgetSyncElementsMethod.requireNonNull(setWidgetSyncElementsMethod, "instance");
            from((Object) setWidgetSyncElementsMethod);
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
            if (obj instanceof SetWidgetSyncElementsMethod) {
                SetWidgetSyncElementsMethod setWidgetSyncElementsMethod = (SetWidgetSyncElementsMethod) obj;
                pageContext(setWidgetSyncElementsMethod.pageContext());
                if ((j & 1) == 0) {
                    forced(setWidgetSyncElementsMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(setWidgetSyncElementsMethod.queue());
                }
                addAllElements(setWidgetSyncElementsMethod.elements());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetWidgetSyncElementsMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetWidgetSyncElementsMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addElements(WidgetSyncElement widgetSyncElement) {
            this.elements.add((WidgetSyncElement) ImmutableSetWidgetSyncElementsMethod.requireNonNull(widgetSyncElement, "elements element"));
            return this;
        }

        public final Builder addElements(WidgetSyncElement... widgetSyncElementArr) {
            for (WidgetSyncElement widgetSyncElement : widgetSyncElementArr) {
                this.elements.add((WidgetSyncElement) ImmutableSetWidgetSyncElementsMethod.requireNonNull(widgetSyncElement, "elements element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ELEMENTS)
        public final Builder elements(Iterable<? extends WidgetSyncElement> iterable) {
            this.elements.clear();
            return addAllElements(iterable);
        }

        public final Builder addAllElements(Iterable<? extends WidgetSyncElement> iterable) {
            Iterator<? extends WidgetSyncElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.elements.add((WidgetSyncElement) ImmutableSetWidgetSyncElementsMethod.requireNonNull(it.next(), "elements element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.PAGE_CONTEXT)
        public final Builder pageContext(PageContext pageContext) {
            this.pageContext = (PageContext) ImmutableSetWidgetSyncElementsMethod.requireNonNull(pageContext, ContextMappingConstants.PAGE_CONTEXT);
            this.initBits &= -3;
            return this;
        }

        public ImmutableSetWidgetSyncElementsMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetWidgetSyncElementsMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(ContextMappingConstants.PAGE_CONTEXT);
            }
            return "Cannot build SetWidgetSyncElementsMethod, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
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
