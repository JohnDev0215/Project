package StorageInterface.Persistent.v1_0;

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
@Generated(from = "ClearStorageMethod", generator = "Immutables")
public final class ImmutableClearStorageMethod extends ClearStorageMethod {
    private final Boolean forced;
    private final List<String> keys;
    private final Queue queue;

    private ImmutableClearStorageMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.keys = createUnmodifiableList(true, builder.keys);
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableClearStorageMethod(Queue queue, Boolean bool, List<String> list) {
        this.queue = queue;
        this.forced = bool;
        this.keys = list;
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

    @Override // StorageInterface.Persistent.v1_0.ClearStorageMethod
    @JsonProperty("keys")
    public List<String> keys() {
        return this.keys;
    }

    public final ImmutableClearStorageMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableClearStorageMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.keys);
    }

    public final ImmutableClearStorageMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableClearStorageMethod(this.queue, bool2, this.keys);
    }

    public final ImmutableClearStorageMethod withKeys(String... strArr) {
        return new ImmutableClearStorageMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(strArr), true, false)));
    }

    public final ImmutableClearStorageMethod withKeys(Iterable<String> iterable) {
        if (this.keys == iterable) {
            return this;
        }
        return new ImmutableClearStorageMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableClearStorageMethod) && equalTo(0, (ImmutableClearStorageMethod) obj);
    }

    private boolean equalTo(int i, ImmutableClearStorageMethod immutableClearStorageMethod) {
        return this.queue.equals(immutableClearStorageMethod.queue) && this.forced.equals(immutableClearStorageMethod.forced) && this.keys.equals(immutableClearStorageMethod.keys);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.keys.hashCode();
    }

    public String toString() {
        return "ClearStorageMethod{queue=" + this.queue + ", forced=" + this.forced + ", keys=" + this.keys + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ClearStorageMethod", generator = "Immutables")
    static final class Json extends ClearStorageMethod {

        @Nullable
        Boolean forced;

        @Nullable
        List<String> keys = Collections.emptyList();

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

        @JsonProperty("keys")
        public void setKeys(List<String> list) {
            this.keys = list;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // StorageInterface.Persistent.v1_0.ClearStorageMethod
        public List<String> keys() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableClearStorageMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<String> list = json.keys;
        if (list != null) {
            builder.addAllKeys(list);
        }
        return builder.build();
    }

    public static ImmutableClearStorageMethod copyOf(ClearStorageMethod clearStorageMethod) {
        if (clearStorageMethod instanceof ImmutableClearStorageMethod) {
            return (ImmutableClearStorageMethod) clearStorageMethod;
        }
        return builder().from(clearStorageMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ClearStorageMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<String> keys;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
            this.keys = new ArrayList();
        }

        public final Builder from(ClearStorageMethod clearStorageMethod) {
            ImmutableClearStorageMethod.requireNonNull(clearStorageMethod, "instance");
            from((Object) clearStorageMethod);
            return this;
        }

        public final Builder from(Method method) {
            ImmutableClearStorageMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof ClearStorageMethod) {
                ClearStorageMethod clearStorageMethod = (ClearStorageMethod) obj;
                forced(clearStorageMethod.forced());
                addAllKeys(clearStorageMethod.keys());
                queue(clearStorageMethod.queue());
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
            this.queue = (Queue) ImmutableClearStorageMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableClearStorageMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addKeys(String str) {
            this.keys.add((String) ImmutableClearStorageMethod.requireNonNull(str, "keys element"));
            return this;
        }

        public final Builder addKeys(String... strArr) {
            for (String str : strArr) {
                this.keys.add((String) ImmutableClearStorageMethod.requireNonNull(str, "keys element"));
            }
            return this;
        }

        @JsonProperty("keys")
        public final Builder keys(Iterable<String> iterable) {
            this.keys.clear();
            return addAllKeys(iterable);
        }

        public final Builder addAllKeys(Iterable<String> iterable) {
            Iterator<String> it = iterable.iterator();
            while (it.hasNext()) {
                this.keys.add((String) ImmutableClearStorageMethod.requireNonNull(it.next(), "keys element"));
            }
            return this;
        }

        public ImmutableClearStorageMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableClearStorageMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build ClearStorageMethod, some of required attributes are not set " + arrayList;
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
