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
@Generated(from = "SetOnTetheredModeTurnedOffMethod", generator = "Immutables")
public final class ImmutableSetOnTetheredModeTurnedOffMethod extends SetOnTetheredModeTurnedOffMethod {
    private final Boolean forced;
    private final List<Method> onTetheredModeTurnedOff;
    private final Queue queue;

    private ImmutableSetOnTetheredModeTurnedOffMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.onTetheredModeTurnedOff = createUnmodifiableList(true, builder.onTetheredModeTurnedOff);
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableSetOnTetheredModeTurnedOffMethod(Queue queue, Boolean bool, List<Method> list) {
        this.queue = queue;
        this.forced = bool;
        this.onTetheredModeTurnedOff = list;
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

    @Override // Watch.TetheredInterface.v1_0.SetOnTetheredModeTurnedOffMethod
    @JsonProperty("onTetheredModeTurnedOff")
    public List<Method> onTetheredModeTurnedOff() {
        return this.onTetheredModeTurnedOff;
    }

    public final ImmutableSetOnTetheredModeTurnedOffMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetOnTetheredModeTurnedOffMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.onTetheredModeTurnedOff);
    }

    public final ImmutableSetOnTetheredModeTurnedOffMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetOnTetheredModeTurnedOffMethod(this.queue, bool2, this.onTetheredModeTurnedOff);
    }

    public final ImmutableSetOnTetheredModeTurnedOffMethod withOnTetheredModeTurnedOff(Method... methodArr) {
        return new ImmutableSetOnTetheredModeTurnedOffMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSetOnTetheredModeTurnedOffMethod withOnTetheredModeTurnedOff(Iterable<? extends Method> iterable) {
        if (this.onTetheredModeTurnedOff == iterable) {
            return this;
        }
        return new ImmutableSetOnTetheredModeTurnedOffMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetOnTetheredModeTurnedOffMethod) && equalTo(0, (ImmutableSetOnTetheredModeTurnedOffMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetOnTetheredModeTurnedOffMethod immutableSetOnTetheredModeTurnedOffMethod) {
        return this.queue.equals(immutableSetOnTetheredModeTurnedOffMethod.queue) && this.forced.equals(immutableSetOnTetheredModeTurnedOffMethod.forced) && this.onTetheredModeTurnedOff.equals(immutableSetOnTetheredModeTurnedOffMethod.onTetheredModeTurnedOff);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.onTetheredModeTurnedOff.hashCode();
    }

    public String toString() {
        return "SetOnTetheredModeTurnedOffMethod{queue=" + this.queue + ", forced=" + this.forced + ", onTetheredModeTurnedOff=" + this.onTetheredModeTurnedOff + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetOnTetheredModeTurnedOffMethod", generator = "Immutables")
    static final class Json extends SetOnTetheredModeTurnedOffMethod {

        @Nullable
        Boolean forced;

        @Nullable
        List<Method> onTetheredModeTurnedOff = Collections.emptyList();

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

        @JsonProperty("onTetheredModeTurnedOff")
        public void setOnTetheredModeTurnedOff(List<Method> list) {
            this.onTetheredModeTurnedOff = list;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetOnTetheredModeTurnedOffMethod
        public List<Method> onTetheredModeTurnedOff() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetOnTetheredModeTurnedOffMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<Method> list = json.onTetheredModeTurnedOff;
        if (list != null) {
            builder.addAllOnTetheredModeTurnedOff(list);
        }
        return builder.build();
    }

    public static ImmutableSetOnTetheredModeTurnedOffMethod copyOf(SetOnTetheredModeTurnedOffMethod setOnTetheredModeTurnedOffMethod) {
        if (setOnTetheredModeTurnedOffMethod instanceof ImmutableSetOnTetheredModeTurnedOffMethod) {
            return (ImmutableSetOnTetheredModeTurnedOffMethod) setOnTetheredModeTurnedOffMethod;
        }
        return builder().from(setOnTetheredModeTurnedOffMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetOnTetheredModeTurnedOffMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<Method> onTetheredModeTurnedOff;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
            this.onTetheredModeTurnedOff = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableSetOnTetheredModeTurnedOffMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SetOnTetheredModeTurnedOffMethod setOnTetheredModeTurnedOffMethod) {
            ImmutableSetOnTetheredModeTurnedOffMethod.requireNonNull(setOnTetheredModeTurnedOffMethod, "instance");
            from((Object) setOnTetheredModeTurnedOffMethod);
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
            if (obj instanceof SetOnTetheredModeTurnedOffMethod) {
                SetOnTetheredModeTurnedOffMethod setOnTetheredModeTurnedOffMethod = (SetOnTetheredModeTurnedOffMethod) obj;
                addAllOnTetheredModeTurnedOff(setOnTetheredModeTurnedOffMethod.onTetheredModeTurnedOff());
                if ((j & 1) == 0) {
                    forced(setOnTetheredModeTurnedOffMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(setOnTetheredModeTurnedOffMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetOnTetheredModeTurnedOffMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetOnTetheredModeTurnedOffMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addOnTetheredModeTurnedOff(Method method) {
            this.onTetheredModeTurnedOff.add((Method) ImmutableSetOnTetheredModeTurnedOffMethod.requireNonNull(method, "onTetheredModeTurnedOff element"));
            return this;
        }

        public final Builder addOnTetheredModeTurnedOff(Method... methodArr) {
            for (Method method : methodArr) {
                this.onTetheredModeTurnedOff.add((Method) ImmutableSetOnTetheredModeTurnedOffMethod.requireNonNull(method, "onTetheredModeTurnedOff element"));
            }
            return this;
        }

        @JsonProperty("onTetheredModeTurnedOff")
        public final Builder onTetheredModeTurnedOff(Iterable<? extends Method> iterable) {
            this.onTetheredModeTurnedOff.clear();
            return addAllOnTetheredModeTurnedOff(iterable);
        }

        public final Builder addAllOnTetheredModeTurnedOff(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onTetheredModeTurnedOff.add((Method) ImmutableSetOnTetheredModeTurnedOffMethod.requireNonNull(it.next(), "onTetheredModeTurnedOff element"));
            }
            return this;
        }

        public ImmutableSetOnTetheredModeTurnedOffMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetOnTetheredModeTurnedOffMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build SetOnTetheredModeTurnedOffMethod, some of required attributes are not set " + arrayList;
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
