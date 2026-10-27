package ExternalActionInterface.v1_0;

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
@Generated(from = "PreFetchMethod", generator = "Immutables")
public final class ImmutablePreFetchMethod extends PreFetchMethod {
    private final Boolean forced;
    private final List<PlayableContent> items;
    private final Queue queue;

    private ImmutablePreFetchMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.items = createUnmodifiableList(true, builder.items);
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutablePreFetchMethod(Queue queue, Boolean bool, List<PlayableContent> list) {
        this.queue = queue;
        this.forced = bool;
        this.items = list;
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

    @Override // ExternalActionInterface.v1_0.PreFetchMethod
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<PlayableContent> items() {
        return this.items;
    }

    public final ImmutablePreFetchMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutablePreFetchMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.items);
    }

    public final ImmutablePreFetchMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutablePreFetchMethod(this.queue, bool2, this.items);
    }

    public final ImmutablePreFetchMethod withItems(PlayableContent... playableContentArr) {
        return new ImmutablePreFetchMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(playableContentArr), true, false)));
    }

    public final ImmutablePreFetchMethod withItems(Iterable<? extends PlayableContent> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutablePreFetchMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePreFetchMethod) && equalTo(0, (ImmutablePreFetchMethod) obj);
    }

    private boolean equalTo(int i, ImmutablePreFetchMethod immutablePreFetchMethod) {
        return this.queue.equals(immutablePreFetchMethod.queue) && this.forced.equals(immutablePreFetchMethod.forced) && this.items.equals(immutablePreFetchMethod.items);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.items.hashCode();
    }

    public String toString() {
        return "PreFetchMethod{queue=" + this.queue + ", forced=" + this.forced + ", items=" + this.items + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PreFetchMethod", generator = "Immutables")
    static final class Json extends PreFetchMethod {

        @Nullable
        Boolean forced;

        @Nullable
        List<PlayableContent> items = Collections.emptyList();

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

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(List<PlayableContent> list) {
            this.items = list;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PreFetchMethod
        public List<PlayableContent> items() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePreFetchMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<PlayableContent> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        return builder.build();
    }

    public static ImmutablePreFetchMethod copyOf(PreFetchMethod preFetchMethod) {
        if (preFetchMethod instanceof ImmutablePreFetchMethod) {
            return (ImmutablePreFetchMethod) preFetchMethod;
        }
        return builder().from(preFetchMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PreFetchMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<PlayableContent> items;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
            this.items = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutablePreFetchMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(PreFetchMethod preFetchMethod) {
            ImmutablePreFetchMethod.requireNonNull(preFetchMethod, "instance");
            from((Object) preFetchMethod);
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
            if (obj instanceof PreFetchMethod) {
                PreFetchMethod preFetchMethod = (PreFetchMethod) obj;
                addAllItems(preFetchMethod.items());
                if ((j & 1) == 0) {
                    forced(preFetchMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(preFetchMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutablePreFetchMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutablePreFetchMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addItems(PlayableContent playableContent) {
            this.items.add((PlayableContent) ImmutablePreFetchMethod.requireNonNull(playableContent, "items element"));
            return this;
        }

        public final Builder addItems(PlayableContent... playableContentArr) {
            for (PlayableContent playableContent : playableContentArr) {
                this.items.add((PlayableContent) ImmutablePreFetchMethod.requireNonNull(playableContent, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends PlayableContent> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends PlayableContent> iterable) {
            Iterator<? extends PlayableContent> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((PlayableContent) ImmutablePreFetchMethod.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        public ImmutablePreFetchMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePreFetchMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build PreFetchMethod, some of required attributes are not set " + arrayList;
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
