package Watch.TetheredInterface.v1_0;

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
@Generated(from = "TetheredMultiplexorMethod", generator = "Immutables")
public final class ImmutableTetheredMultiplexorMethod extends TetheredMultiplexorMethod {
    private final Boolean forced;
    private final Queue queue;
    private final List<Method> tetheredModeIsOffMethods;
    private final List<Method> tetheredModeIsOnMethods;

    private ImmutableTetheredMultiplexorMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.tetheredModeIsOnMethods = createUnmodifiableList(true, builder.tetheredModeIsOnMethods);
        this.tetheredModeIsOffMethods = createUnmodifiableList(true, builder.tetheredModeIsOffMethods);
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableTetheredMultiplexorMethod(Queue queue, Boolean bool, List<Method> list, List<Method> list2) {
        this.queue = queue;
        this.forced = bool;
        this.tetheredModeIsOnMethods = list;
        this.tetheredModeIsOffMethods = list2;
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

    @Override // Watch.TetheredInterface.v1_0.TetheredMultiplexorMethod
    @JsonProperty("tetheredModeIsOnMethods")
    public List<Method> tetheredModeIsOnMethods() {
        return this.tetheredModeIsOnMethods;
    }

    @Override // Watch.TetheredInterface.v1_0.TetheredMultiplexorMethod
    @JsonProperty("tetheredModeIsOffMethods")
    public List<Method> tetheredModeIsOffMethods() {
        return this.tetheredModeIsOffMethods;
    }

    public final ImmutableTetheredMultiplexorMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableTetheredMultiplexorMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.tetheredModeIsOnMethods, this.tetheredModeIsOffMethods);
    }

    public final ImmutableTetheredMultiplexorMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableTetheredMultiplexorMethod(this.queue, bool2, this.tetheredModeIsOnMethods, this.tetheredModeIsOffMethods);
    }

    public final ImmutableTetheredMultiplexorMethod withTetheredModeIsOnMethods(Method... methodArr) {
        return new ImmutableTetheredMultiplexorMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.tetheredModeIsOffMethods);
    }

    public final ImmutableTetheredMultiplexorMethod withTetheredModeIsOnMethods(Iterable<? extends Method> iterable) {
        if (this.tetheredModeIsOnMethods == iterable) {
            return this;
        }
        return new ImmutableTetheredMultiplexorMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.tetheredModeIsOffMethods);
    }

    public final ImmutableTetheredMultiplexorMethod withTetheredModeIsOffMethods(Method... methodArr) {
        return new ImmutableTetheredMultiplexorMethod(this.queue, this.forced, this.tetheredModeIsOnMethods, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableTetheredMultiplexorMethod withTetheredModeIsOffMethods(Iterable<? extends Method> iterable) {
        if (this.tetheredModeIsOffMethods == iterable) {
            return this;
        }
        return new ImmutableTetheredMultiplexorMethod(this.queue, this.forced, this.tetheredModeIsOnMethods, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTetheredMultiplexorMethod) && equalTo(0, (ImmutableTetheredMultiplexorMethod) obj);
    }

    private boolean equalTo(int i, ImmutableTetheredMultiplexorMethod immutableTetheredMultiplexorMethod) {
        return this.queue.equals(immutableTetheredMultiplexorMethod.queue) && this.forced.equals(immutableTetheredMultiplexorMethod.forced) && this.tetheredModeIsOnMethods.equals(immutableTetheredMultiplexorMethod.tetheredModeIsOnMethods) && this.tetheredModeIsOffMethods.equals(immutableTetheredMultiplexorMethod.tetheredModeIsOffMethods);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.tetheredModeIsOnMethods.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.tetheredModeIsOffMethods.hashCode();
    }

    public String toString() {
        return "TetheredMultiplexorMethod{queue=" + this.queue + ", forced=" + this.forced + ", tetheredModeIsOnMethods=" + this.tetheredModeIsOnMethods + ", tetheredModeIsOffMethods=" + this.tetheredModeIsOffMethods + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "TetheredMultiplexorMethod", generator = "Immutables")
    static final class Json extends TetheredMultiplexorMethod {

        @Nullable
        Boolean forced;

        @Nullable
        Queue queue;

        @Nullable
        List<Method> tetheredModeIsOnMethods = Collections.emptyList();

        @Nullable
        List<Method> tetheredModeIsOffMethods = Collections.emptyList();

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

        @JsonProperty("tetheredModeIsOnMethods")
        public void setTetheredModeIsOnMethods(List<Method> list) {
            this.tetheredModeIsOnMethods = list;
        }

        @JsonProperty("tetheredModeIsOffMethods")
        public void setTetheredModeIsOffMethods(List<Method> list) {
            this.tetheredModeIsOffMethods = list;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.TetheredMultiplexorMethod
        public List<Method> tetheredModeIsOnMethods() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.TetheredMultiplexorMethod
        public List<Method> tetheredModeIsOffMethods() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTetheredMultiplexorMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<Method> list = json.tetheredModeIsOnMethods;
        if (list != null) {
            builder.addAllTetheredModeIsOnMethods(list);
        }
        List<Method> list2 = json.tetheredModeIsOffMethods;
        if (list2 != null) {
            builder.addAllTetheredModeIsOffMethods(list2);
        }
        return builder.build();
    }

    public static ImmutableTetheredMultiplexorMethod copyOf(TetheredMultiplexorMethod tetheredMultiplexorMethod) {
        if (tetheredMultiplexorMethod instanceof ImmutableTetheredMultiplexorMethod) {
            return (ImmutableTetheredMultiplexorMethod) tetheredMultiplexorMethod;
        }
        return builder().from(tetheredMultiplexorMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "TetheredMultiplexorMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private Queue queue;
        private List<Method> tetheredModeIsOffMethods;
        private List<Method> tetheredModeIsOnMethods;

        private Builder() {
            this.initBits = 1L;
            this.tetheredModeIsOnMethods = new ArrayList();
            this.tetheredModeIsOffMethods = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableTetheredMultiplexorMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(TetheredMultiplexorMethod tetheredMultiplexorMethod) {
            ImmutableTetheredMultiplexorMethod.requireNonNull(tetheredMultiplexorMethod, "instance");
            from((Object) tetheredMultiplexorMethod);
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
            if (obj instanceof TetheredMultiplexorMethod) {
                TetheredMultiplexorMethod tetheredMultiplexorMethod = (TetheredMultiplexorMethod) obj;
                addAllTetheredModeIsOffMethods(tetheredMultiplexorMethod.tetheredModeIsOffMethods());
                addAllTetheredModeIsOnMethods(tetheredMultiplexorMethod.tetheredModeIsOnMethods());
                if ((j & 1) == 0) {
                    forced(tetheredMultiplexorMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(tetheredMultiplexorMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableTetheredMultiplexorMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableTetheredMultiplexorMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addTetheredModeIsOnMethods(Method method) {
            this.tetheredModeIsOnMethods.add((Method) ImmutableTetheredMultiplexorMethod.requireNonNull(method, "tetheredModeIsOnMethods element"));
            return this;
        }

        public final Builder addTetheredModeIsOnMethods(Method... methodArr) {
            for (Method method : methodArr) {
                this.tetheredModeIsOnMethods.add((Method) ImmutableTetheredMultiplexorMethod.requireNonNull(method, "tetheredModeIsOnMethods element"));
            }
            return this;
        }

        @JsonProperty("tetheredModeIsOnMethods")
        public final Builder tetheredModeIsOnMethods(Iterable<? extends Method> iterable) {
            this.tetheredModeIsOnMethods.clear();
            return addAllTetheredModeIsOnMethods(iterable);
        }

        public final Builder addAllTetheredModeIsOnMethods(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.tetheredModeIsOnMethods.add((Method) ImmutableTetheredMultiplexorMethod.requireNonNull(it.next(), "tetheredModeIsOnMethods element"));
            }
            return this;
        }

        public final Builder addTetheredModeIsOffMethods(Method method) {
            this.tetheredModeIsOffMethods.add((Method) ImmutableTetheredMultiplexorMethod.requireNonNull(method, "tetheredModeIsOffMethods element"));
            return this;
        }

        public final Builder addTetheredModeIsOffMethods(Method... methodArr) {
            for (Method method : methodArr) {
                this.tetheredModeIsOffMethods.add((Method) ImmutableTetheredMultiplexorMethod.requireNonNull(method, "tetheredModeIsOffMethods element"));
            }
            return this;
        }

        @JsonProperty("tetheredModeIsOffMethods")
        public final Builder tetheredModeIsOffMethods(Iterable<? extends Method> iterable) {
            this.tetheredModeIsOffMethods.clear();
            return addAllTetheredModeIsOffMethods(iterable);
        }

        public final Builder addAllTetheredModeIsOffMethods(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.tetheredModeIsOffMethods.add((Method) ImmutableTetheredMultiplexorMethod.requireNonNull(it.next(), "tetheredModeIsOffMethods element"));
            }
            return this;
        }

        public ImmutableTetheredMultiplexorMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableTetheredMultiplexorMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build TetheredMultiplexorMethod, some of required attributes are not set " + arrayList;
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
