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
@Generated(from = "SetOnTetheredModeTurnedOnMethod", generator = "Immutables")
public final class ImmutableSetOnTetheredModeTurnedOnMethod extends SetOnTetheredModeTurnedOnMethod {
    private final Boolean forced;
    private final List<Method> onTetheredModeTurnedOn;
    private final Queue queue;

    private ImmutableSetOnTetheredModeTurnedOnMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.onTetheredModeTurnedOn = createUnmodifiableList(true, builder.onTetheredModeTurnedOn);
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableSetOnTetheredModeTurnedOnMethod(Queue queue, Boolean bool, List<Method> list) {
        this.queue = queue;
        this.forced = bool;
        this.onTetheredModeTurnedOn = list;
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

    @Override // Watch.TetheredInterface.v1_0.SetOnTetheredModeTurnedOnMethod
    @JsonProperty("onTetheredModeTurnedOn")
    public List<Method> onTetheredModeTurnedOn() {
        return this.onTetheredModeTurnedOn;
    }

    public final ImmutableSetOnTetheredModeTurnedOnMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetOnTetheredModeTurnedOnMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.onTetheredModeTurnedOn);
    }

    public final ImmutableSetOnTetheredModeTurnedOnMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetOnTetheredModeTurnedOnMethod(this.queue, bool2, this.onTetheredModeTurnedOn);
    }

    public final ImmutableSetOnTetheredModeTurnedOnMethod withOnTetheredModeTurnedOn(Method... methodArr) {
        return new ImmutableSetOnTetheredModeTurnedOnMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSetOnTetheredModeTurnedOnMethod withOnTetheredModeTurnedOn(Iterable<? extends Method> iterable) {
        if (this.onTetheredModeTurnedOn == iterable) {
            return this;
        }
        return new ImmutableSetOnTetheredModeTurnedOnMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetOnTetheredModeTurnedOnMethod) && equalTo(0, (ImmutableSetOnTetheredModeTurnedOnMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetOnTetheredModeTurnedOnMethod immutableSetOnTetheredModeTurnedOnMethod) {
        return this.queue.equals(immutableSetOnTetheredModeTurnedOnMethod.queue) && this.forced.equals(immutableSetOnTetheredModeTurnedOnMethod.forced) && this.onTetheredModeTurnedOn.equals(immutableSetOnTetheredModeTurnedOnMethod.onTetheredModeTurnedOn);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.onTetheredModeTurnedOn.hashCode();
    }

    public String toString() {
        return "SetOnTetheredModeTurnedOnMethod{queue=" + this.queue + ", forced=" + this.forced + ", onTetheredModeTurnedOn=" + this.onTetheredModeTurnedOn + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetOnTetheredModeTurnedOnMethod", generator = "Immutables")
    static final class Json extends SetOnTetheredModeTurnedOnMethod {

        @Nullable
        Boolean forced;

        @Nullable
        List<Method> onTetheredModeTurnedOn = Collections.emptyList();

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

        @JsonProperty("onTetheredModeTurnedOn")
        public void setOnTetheredModeTurnedOn(List<Method> list) {
            this.onTetheredModeTurnedOn = list;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetOnTetheredModeTurnedOnMethod
        public List<Method> onTetheredModeTurnedOn() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetOnTetheredModeTurnedOnMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<Method> list = json.onTetheredModeTurnedOn;
        if (list != null) {
            builder.addAllOnTetheredModeTurnedOn(list);
        }
        return builder.build();
    }

    public static ImmutableSetOnTetheredModeTurnedOnMethod copyOf(SetOnTetheredModeTurnedOnMethod setOnTetheredModeTurnedOnMethod) {
        if (setOnTetheredModeTurnedOnMethod instanceof ImmutableSetOnTetheredModeTurnedOnMethod) {
            return (ImmutableSetOnTetheredModeTurnedOnMethod) setOnTetheredModeTurnedOnMethod;
        }
        return builder().from(setOnTetheredModeTurnedOnMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetOnTetheredModeTurnedOnMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<Method> onTetheredModeTurnedOn;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
            this.onTetheredModeTurnedOn = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableSetOnTetheredModeTurnedOnMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SetOnTetheredModeTurnedOnMethod setOnTetheredModeTurnedOnMethod) {
            ImmutableSetOnTetheredModeTurnedOnMethod.requireNonNull(setOnTetheredModeTurnedOnMethod, "instance");
            from((Object) setOnTetheredModeTurnedOnMethod);
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
            if (obj instanceof SetOnTetheredModeTurnedOnMethod) {
                SetOnTetheredModeTurnedOnMethod setOnTetheredModeTurnedOnMethod = (SetOnTetheredModeTurnedOnMethod) obj;
                addAllOnTetheredModeTurnedOn(setOnTetheredModeTurnedOnMethod.onTetheredModeTurnedOn());
                if ((j & 1) == 0) {
                    forced(setOnTetheredModeTurnedOnMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(setOnTetheredModeTurnedOnMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetOnTetheredModeTurnedOnMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetOnTetheredModeTurnedOnMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addOnTetheredModeTurnedOn(Method method) {
            this.onTetheredModeTurnedOn.add((Method) ImmutableSetOnTetheredModeTurnedOnMethod.requireNonNull(method, "onTetheredModeTurnedOn element"));
            return this;
        }

        public final Builder addOnTetheredModeTurnedOn(Method... methodArr) {
            for (Method method : methodArr) {
                this.onTetheredModeTurnedOn.add((Method) ImmutableSetOnTetheredModeTurnedOnMethod.requireNonNull(method, "onTetheredModeTurnedOn element"));
            }
            return this;
        }

        @JsonProperty("onTetheredModeTurnedOn")
        public final Builder onTetheredModeTurnedOn(Iterable<? extends Method> iterable) {
            this.onTetheredModeTurnedOn.clear();
            return addAllOnTetheredModeTurnedOn(iterable);
        }

        public final Builder addAllOnTetheredModeTurnedOn(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onTetheredModeTurnedOn.add((Method) ImmutableSetOnTetheredModeTurnedOnMethod.requireNonNull(it.next(), "onTetheredModeTurnedOn element"));
            }
            return this;
        }

        public ImmutableSetOnTetheredModeTurnedOnMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetOnTetheredModeTurnedOnMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build SetOnTetheredModeTurnedOnMethod, some of required attributes are not set " + arrayList;
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
