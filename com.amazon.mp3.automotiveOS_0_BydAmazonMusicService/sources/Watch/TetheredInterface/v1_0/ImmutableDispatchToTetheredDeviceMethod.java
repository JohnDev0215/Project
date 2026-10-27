package Watch.TetheredInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import com.amazon.music.skyfire.models.TetheredMessageKey;
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
@Generated(from = "DispatchToTetheredDeviceMethod", generator = "Immutables")
public final class ImmutableDispatchToTetheredDeviceMethod extends DispatchToTetheredDeviceMethod {
    private final List<String> clientInformation;
    private final Boolean forced;
    private final String methodsString;
    private final List<Method> onTetheredDeviceUnreachable;
    private final Queue queue;

    private ImmutableDispatchToTetheredDeviceMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.methodsString = builder.methodsString;
        this.clientInformation = createUnmodifiableList(true, builder.clientInformation);
        this.onTetheredDeviceUnreachable = createUnmodifiableList(true, builder.onTetheredDeviceUnreachable);
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableDispatchToTetheredDeviceMethod(Queue queue, Boolean bool, String str, List<String> list, List<Method> list2) {
        this.queue = queue;
        this.forced = bool;
        this.methodsString = str;
        this.clientInformation = list;
        this.onTetheredDeviceUnreachable = list2;
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

    @Override // Watch.TetheredInterface.v1_0.DispatchToTetheredDeviceMethod
    @JsonProperty("methodsString")
    public String methodsString() {
        return this.methodsString;
    }

    @Override // Watch.TetheredInterface.v1_0.DispatchToTetheredDeviceMethod
    @JsonProperty(TetheredMessageKey.clientInformation)
    public List<String> clientInformation() {
        return this.clientInformation;
    }

    @Override // Watch.TetheredInterface.v1_0.DispatchToTetheredDeviceMethod
    @JsonProperty("onTetheredDeviceUnreachable")
    public List<Method> onTetheredDeviceUnreachable() {
        return this.onTetheredDeviceUnreachable;
    }

    public final ImmutableDispatchToTetheredDeviceMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableDispatchToTetheredDeviceMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.methodsString, this.clientInformation, this.onTetheredDeviceUnreachable);
    }

    public final ImmutableDispatchToTetheredDeviceMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableDispatchToTetheredDeviceMethod(this.queue, bool2, this.methodsString, this.clientInformation, this.onTetheredDeviceUnreachable);
    }

    public final ImmutableDispatchToTetheredDeviceMethod withMethodsString(String str) {
        String str2 = (String) requireNonNull(str, "methodsString");
        return this.methodsString.equals(str2) ? this : new ImmutableDispatchToTetheredDeviceMethod(this.queue, this.forced, str2, this.clientInformation, this.onTetheredDeviceUnreachable);
    }

    public final ImmutableDispatchToTetheredDeviceMethod withClientInformation(String... strArr) {
        return new ImmutableDispatchToTetheredDeviceMethod(this.queue, this.forced, this.methodsString, createUnmodifiableList(false, createSafeList(Arrays.asList(strArr), true, false)), this.onTetheredDeviceUnreachable);
    }

    public final ImmutableDispatchToTetheredDeviceMethod withClientInformation(Iterable<String> iterable) {
        if (this.clientInformation == iterable) {
            return this;
        }
        return new ImmutableDispatchToTetheredDeviceMethod(this.queue, this.forced, this.methodsString, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onTetheredDeviceUnreachable);
    }

    public final ImmutableDispatchToTetheredDeviceMethod withOnTetheredDeviceUnreachable(Method... methodArr) {
        return new ImmutableDispatchToTetheredDeviceMethod(this.queue, this.forced, this.methodsString, this.clientInformation, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableDispatchToTetheredDeviceMethod withOnTetheredDeviceUnreachable(Iterable<? extends Method> iterable) {
        if (this.onTetheredDeviceUnreachable == iterable) {
            return this;
        }
        return new ImmutableDispatchToTetheredDeviceMethod(this.queue, this.forced, this.methodsString, this.clientInformation, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableDispatchToTetheredDeviceMethod) && equalTo(0, (ImmutableDispatchToTetheredDeviceMethod) obj);
    }

    private boolean equalTo(int i, ImmutableDispatchToTetheredDeviceMethod immutableDispatchToTetheredDeviceMethod) {
        return this.queue.equals(immutableDispatchToTetheredDeviceMethod.queue) && this.forced.equals(immutableDispatchToTetheredDeviceMethod.forced) && this.methodsString.equals(immutableDispatchToTetheredDeviceMethod.methodsString) && this.clientInformation.equals(immutableDispatchToTetheredDeviceMethod.clientInformation) && this.onTetheredDeviceUnreachable.equals(immutableDispatchToTetheredDeviceMethod.onTetheredDeviceUnreachable);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.methodsString.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.clientInformation.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onTetheredDeviceUnreachable.hashCode();
    }

    public String toString() {
        return "DispatchToTetheredDeviceMethod{queue=" + this.queue + ", forced=" + this.forced + ", methodsString=" + this.methodsString + ", clientInformation=" + this.clientInformation + ", onTetheredDeviceUnreachable=" + this.onTetheredDeviceUnreachable + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "DispatchToTetheredDeviceMethod", generator = "Immutables")
    static final class Json extends DispatchToTetheredDeviceMethod {

        @Nullable
        Boolean forced;

        @Nullable
        String methodsString;

        @Nullable
        Queue queue;

        @Nullable
        List<String> clientInformation = Collections.emptyList();

        @Nullable
        List<Method> onTetheredDeviceUnreachable = Collections.emptyList();

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

        @JsonProperty("methodsString")
        public void setMethodsString(String str) {
            this.methodsString = str;
        }

        @JsonProperty(TetheredMessageKey.clientInformation)
        public void setClientInformation(List<String> list) {
            this.clientInformation = list;
        }

        @JsonProperty("onTetheredDeviceUnreachable")
        public void setOnTetheredDeviceUnreachable(List<Method> list) {
            this.onTetheredDeviceUnreachable = list;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.DispatchToTetheredDeviceMethod
        public String methodsString() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.DispatchToTetheredDeviceMethod
        public List<String> clientInformation() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.DispatchToTetheredDeviceMethod
        public List<Method> onTetheredDeviceUnreachable() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableDispatchToTetheredDeviceMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.methodsString;
        if (str != null) {
            builder.methodsString(str);
        }
        List<String> list = json.clientInformation;
        if (list != null) {
            builder.addAllClientInformation(list);
        }
        List<Method> list2 = json.onTetheredDeviceUnreachable;
        if (list2 != null) {
            builder.addAllOnTetheredDeviceUnreachable(list2);
        }
        return builder.build();
    }

    public static ImmutableDispatchToTetheredDeviceMethod copyOf(DispatchToTetheredDeviceMethod dispatchToTetheredDeviceMethod) {
        if (dispatchToTetheredDeviceMethod instanceof ImmutableDispatchToTetheredDeviceMethod) {
            return (ImmutableDispatchToTetheredDeviceMethod) dispatchToTetheredDeviceMethod;
        }
        return builder().from(dispatchToTetheredDeviceMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "DispatchToTetheredDeviceMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_METHODS_STRING = 2;
        private static final long INIT_BIT_QUEUE = 1;
        private List<String> clientInformation;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private String methodsString;
        private List<Method> onTetheredDeviceUnreachable;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 3L;
            this.clientInformation = new ArrayList();
            this.onTetheredDeviceUnreachable = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableDispatchToTetheredDeviceMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(DispatchToTetheredDeviceMethod dispatchToTetheredDeviceMethod) {
            ImmutableDispatchToTetheredDeviceMethod.requireNonNull(dispatchToTetheredDeviceMethod, "instance");
            from((Object) dispatchToTetheredDeviceMethod);
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
            if (obj instanceof DispatchToTetheredDeviceMethod) {
                DispatchToTetheredDeviceMethod dispatchToTetheredDeviceMethod = (DispatchToTetheredDeviceMethod) obj;
                addAllOnTetheredDeviceUnreachable(dispatchToTetheredDeviceMethod.onTetheredDeviceUnreachable());
                addAllClientInformation(dispatchToTetheredDeviceMethod.clientInformation());
                methodsString(dispatchToTetheredDeviceMethod.methodsString());
                if ((j & 1) == 0) {
                    forced(dispatchToTetheredDeviceMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(dispatchToTetheredDeviceMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableDispatchToTetheredDeviceMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableDispatchToTetheredDeviceMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("methodsString")
        public final Builder methodsString(String str) {
            this.methodsString = (String) ImmutableDispatchToTetheredDeviceMethod.requireNonNull(str, "methodsString");
            this.initBits &= -3;
            return this;
        }

        public final Builder addClientInformation(String str) {
            this.clientInformation.add((String) ImmutableDispatchToTetheredDeviceMethod.requireNonNull(str, "clientInformation element"));
            return this;
        }

        public final Builder addClientInformation(String... strArr) {
            for (String str : strArr) {
                this.clientInformation.add((String) ImmutableDispatchToTetheredDeviceMethod.requireNonNull(str, "clientInformation element"));
            }
            return this;
        }

        @JsonProperty(TetheredMessageKey.clientInformation)
        public final Builder clientInformation(Iterable<String> iterable) {
            this.clientInformation.clear();
            return addAllClientInformation(iterable);
        }

        public final Builder addAllClientInformation(Iterable<String> iterable) {
            Iterator<String> it = iterable.iterator();
            while (it.hasNext()) {
                this.clientInformation.add((String) ImmutableDispatchToTetheredDeviceMethod.requireNonNull(it.next(), "clientInformation element"));
            }
            return this;
        }

        public final Builder addOnTetheredDeviceUnreachable(Method method) {
            this.onTetheredDeviceUnreachable.add((Method) ImmutableDispatchToTetheredDeviceMethod.requireNonNull(method, "onTetheredDeviceUnreachable element"));
            return this;
        }

        public final Builder addOnTetheredDeviceUnreachable(Method... methodArr) {
            for (Method method : methodArr) {
                this.onTetheredDeviceUnreachable.add((Method) ImmutableDispatchToTetheredDeviceMethod.requireNonNull(method, "onTetheredDeviceUnreachable element"));
            }
            return this;
        }

        @JsonProperty("onTetheredDeviceUnreachable")
        public final Builder onTetheredDeviceUnreachable(Iterable<? extends Method> iterable) {
            this.onTetheredDeviceUnreachable.clear();
            return addAllOnTetheredDeviceUnreachable(iterable);
        }

        public final Builder addAllOnTetheredDeviceUnreachable(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onTetheredDeviceUnreachable.add((Method) ImmutableDispatchToTetheredDeviceMethod.requireNonNull(it.next(), "onTetheredDeviceUnreachable element"));
            }
            return this;
        }

        public ImmutableDispatchToTetheredDeviceMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableDispatchToTetheredDeviceMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("methodsString");
            }
            return "Cannot build DispatchToTetheredDeviceMethod, some of required attributes are not set " + arrayList;
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
