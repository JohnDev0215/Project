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
@Generated(from = "ReadMethodsFromStorageMethod", generator = "Immutables")
public final class ImmutableReadMethodsFromStorageMethod extends ReadMethodsFromStorageMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final List<Method> after;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final String key;
    private final Queue queue;

    private ImmutableReadMethodsFromStorageMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.key = builder.key;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.afterIsSet()) {
            this.initShim.after(createUnmodifiableList(true, builder.after));
        }
        this.forced = this.initShim.forced();
        this.after = this.initShim.after();
        this.initShim = null;
    }

    private ImmutableReadMethodsFromStorageMethod(Queue queue, Boolean bool, String str, List<Method> list) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.key = str;
        this.after = list;
        this.initShim = null;
    }

    @Generated(from = "ReadMethodsFromStorageMethod", generator = "Immutables")
    private final class InitShim {
        private List<Method> after;
        private byte afterBuildStage;
        private Boolean forced;
        private byte forcedBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.afterBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableReadMethodsFromStorageMethod.requireNonNull(ImmutableReadMethodsFromStorageMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        List<Method> after() {
            byte b = this.afterBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.afterBuildStage = (byte) -1;
                this.after = ImmutableReadMethodsFromStorageMethod.createUnmodifiableList(false, ImmutableReadMethodsFromStorageMethod.createSafeList(ImmutableReadMethodsFromStorageMethod.super.after(), true, false));
                this.afterBuildStage = (byte) 1;
            }
            return this.after;
        }

        void after(List<Method> list) {
            this.after = list;
            this.afterBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.afterBuildStage == -1) {
                arrayList.add("after");
            }
            return "Cannot build ReadMethodsFromStorageMethod, attribute initializers form cycle " + arrayList;
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

    @Override // StorageInterface.Persistent.v1_0.ReadMethodsFromStorageMethod
    @JsonProperty("key")
    public String key() {
        return this.key;
    }

    @Override // StorageInterface.Persistent.v1_0.ReadMethodsFromStorageMethod
    @JsonProperty("after")
    public List<Method> after() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.after();
        }
        return this.after;
    }

    public final ImmutableReadMethodsFromStorageMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableReadMethodsFromStorageMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.key, this.after);
    }

    public final ImmutableReadMethodsFromStorageMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableReadMethodsFromStorageMethod(this.queue, bool2, this.key, this.after);
    }

    public final ImmutableReadMethodsFromStorageMethod withKey(String str) {
        String str2 = (String) requireNonNull(str, "key");
        return this.key.equals(str2) ? this : new ImmutableReadMethodsFromStorageMethod(this.queue, this.forced, str2, this.after);
    }

    public final ImmutableReadMethodsFromStorageMethod withAfter(Method... methodArr) {
        return new ImmutableReadMethodsFromStorageMethod(this.queue, this.forced, this.key, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableReadMethodsFromStorageMethod withAfter(Iterable<? extends Method> iterable) {
        if (this.after == iterable) {
            return this;
        }
        return new ImmutableReadMethodsFromStorageMethod(this.queue, this.forced, this.key, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableReadMethodsFromStorageMethod) && equalTo(0, (ImmutableReadMethodsFromStorageMethod) obj);
    }

    private boolean equalTo(int i, ImmutableReadMethodsFromStorageMethod immutableReadMethodsFromStorageMethod) {
        return this.queue.equals(immutableReadMethodsFromStorageMethod.queue) && this.forced.equals(immutableReadMethodsFromStorageMethod.forced) && this.key.equals(immutableReadMethodsFromStorageMethod.key) && this.after.equals(immutableReadMethodsFromStorageMethod.after);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.key.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.after.hashCode();
    }

    public String toString() {
        return "ReadMethodsFromStorageMethod{queue=" + this.queue + ", forced=" + this.forced + ", key=" + this.key + ", after=" + this.after + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ReadMethodsFromStorageMethod", generator = "Immutables")
    static final class Json extends ReadMethodsFromStorageMethod {

        @Nullable
        List<Method> after = Collections.emptyList();
        boolean afterIsSet;

        @Nullable
        Boolean forced;

        @Nullable
        String key;

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

        @JsonProperty("key")
        public void setKey(String str) {
            this.key = str;
        }

        @JsonProperty("after")
        public void setAfter(List<Method> list) {
            this.after = list;
            this.afterIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // StorageInterface.Persistent.v1_0.ReadMethodsFromStorageMethod
        public String key() {
            throw new UnsupportedOperationException();
        }

        @Override // StorageInterface.Persistent.v1_0.ReadMethodsFromStorageMethod
        public List<Method> after() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableReadMethodsFromStorageMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.key;
        if (str != null) {
            builder.key(str);
        }
        if (json.afterIsSet) {
            builder.addAllAfter(json.after);
        }
        return builder.build();
    }

    public static ImmutableReadMethodsFromStorageMethod copyOf(ReadMethodsFromStorageMethod readMethodsFromStorageMethod) {
        if (readMethodsFromStorageMethod instanceof ImmutableReadMethodsFromStorageMethod) {
            return (ImmutableReadMethodsFromStorageMethod) readMethodsFromStorageMethod;
        }
        return builder().from(readMethodsFromStorageMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ReadMethodsFromStorageMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_KEY = 2;
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_AFTER = 1;
        private List<Method> after;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private String key;
        private long optBits;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 3L;
            this.after = new ArrayList();
        }

        public final Builder from(ReadMethodsFromStorageMethod readMethodsFromStorageMethod) {
            ImmutableReadMethodsFromStorageMethod.requireNonNull(readMethodsFromStorageMethod, "instance");
            from((Object) readMethodsFromStorageMethod);
            return this;
        }

        public final Builder from(Method method) {
            ImmutableReadMethodsFromStorageMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof ReadMethodsFromStorageMethod) {
                ReadMethodsFromStorageMethod readMethodsFromStorageMethod = (ReadMethodsFromStorageMethod) obj;
                addAllAfter(readMethodsFromStorageMethod.after());
                forced(readMethodsFromStorageMethod.forced());
                queue(readMethodsFromStorageMethod.queue());
                j = 3;
                key(readMethodsFromStorageMethod.key());
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
            this.queue = (Queue) ImmutableReadMethodsFromStorageMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableReadMethodsFromStorageMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("key")
        public final Builder key(String str) {
            this.key = (String) ImmutableReadMethodsFromStorageMethod.requireNonNull(str, "key");
            this.initBits &= -3;
            return this;
        }

        public final Builder addAfter(Method method) {
            this.after.add((Method) ImmutableReadMethodsFromStorageMethod.requireNonNull(method, "after element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addAfter(Method... methodArr) {
            for (Method method : methodArr) {
                this.after.add((Method) ImmutableReadMethodsFromStorageMethod.requireNonNull(method, "after element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("after")
        public final Builder after(Iterable<? extends Method> iterable) {
            this.after.clear();
            return addAllAfter(iterable);
        }

        public final Builder addAllAfter(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.after.add((Method) ImmutableReadMethodsFromStorageMethod.requireNonNull(it.next(), "after element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableReadMethodsFromStorageMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableReadMethodsFromStorageMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean afterIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("key");
            }
            return "Cannot build ReadMethodsFromStorageMethod, some of required attributes are not set " + arrayList;
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
