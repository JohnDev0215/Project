package Touch.SelectionTemplateInterface.v1_0;

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
@Generated(from = "OpenSelectionMethod", generator = "Immutables")
public final class ImmutableOpenSelectionMethod extends OpenSelectionMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final List<Method> methods;
    private final Queue queue;

    private ImmutableOpenSelectionMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.methodsIsSet()) {
            this.initShim.methods(createUnmodifiableList(true, builder.methods));
        }
        this.forced = this.initShim.forced();
        this.methods = this.initShim.methods();
        this.initShim = null;
    }

    private ImmutableOpenSelectionMethod(Queue queue, Boolean bool, List<Method> list) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.methods = list;
        this.initShim = null;
    }

    @Generated(from = "OpenSelectionMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private List<Method> methods;
        private byte methodsBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.methodsBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableOpenSelectionMethod.requireNonNull(ImmutableOpenSelectionMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        List<Method> methods() {
            byte b = this.methodsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.methodsBuildStage = (byte) -1;
                this.methods = ImmutableOpenSelectionMethod.createUnmodifiableList(false, ImmutableOpenSelectionMethod.createSafeList(ImmutableOpenSelectionMethod.super.methods(), true, false));
                this.methodsBuildStage = (byte) 1;
            }
            return this.methods;
        }

        void methods(List<Method> list) {
            this.methods = list;
            this.methodsBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.methodsBuildStage == -1) {
                arrayList.add("methods");
            }
            return "Cannot build OpenSelectionMethod, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.SelectionTemplateInterface.v1_0.OpenSelectionMethod
    @JsonProperty("methods")
    public List<Method> methods() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.methods();
        }
        return this.methods;
    }

    public final ImmutableOpenSelectionMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableOpenSelectionMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.methods);
    }

    public final ImmutableOpenSelectionMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableOpenSelectionMethod(this.queue, bool2, this.methods);
    }

    public final ImmutableOpenSelectionMethod withMethods(Method... methodArr) {
        return new ImmutableOpenSelectionMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableOpenSelectionMethod withMethods(Iterable<? extends Method> iterable) {
        if (this.methods == iterable) {
            return this;
        }
        return new ImmutableOpenSelectionMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableOpenSelectionMethod) && equalTo(0, (ImmutableOpenSelectionMethod) obj);
    }

    private boolean equalTo(int i, ImmutableOpenSelectionMethod immutableOpenSelectionMethod) {
        return this.queue.equals(immutableOpenSelectionMethod.queue) && this.forced.equals(immutableOpenSelectionMethod.forced) && this.methods.equals(immutableOpenSelectionMethod.methods);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.methods.hashCode();
    }

    public String toString() {
        return "OpenSelectionMethod{queue=" + this.queue + ", forced=" + this.forced + ", methods=" + this.methods + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "OpenSelectionMethod", generator = "Immutables")
    static final class Json extends OpenSelectionMethod {

        @Nullable
        Boolean forced;

        @Nullable
        List<Method> methods = Collections.emptyList();
        boolean methodsIsSet;

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

        @JsonProperty("methods")
        public void setMethods(List<Method> list) {
            this.methods = list;
            this.methodsIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SelectionTemplateInterface.v1_0.OpenSelectionMethod
        public List<Method> methods() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableOpenSelectionMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        if (json.methodsIsSet) {
            builder.addAllMethods(json.methods);
        }
        return builder.build();
    }

    public static ImmutableOpenSelectionMethod copyOf(OpenSelectionMethod openSelectionMethod) {
        if (openSelectionMethod instanceof ImmutableOpenSelectionMethod) {
            return (ImmutableOpenSelectionMethod) openSelectionMethod;
        }
        return builder().from(openSelectionMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "OpenSelectionMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_METHODS = 1;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<Method> methods;
        private long optBits;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
            this.methods = new ArrayList();
        }

        public final Builder from(OpenSelectionMethod openSelectionMethod) {
            ImmutableOpenSelectionMethod.requireNonNull(openSelectionMethod, "instance");
            from((Object) openSelectionMethod);
            return this;
        }

        public final Builder from(Method method) {
            ImmutableOpenSelectionMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof OpenSelectionMethod) {
                OpenSelectionMethod openSelectionMethod = (OpenSelectionMethod) obj;
                forced(openSelectionMethod.forced());
                addAllMethods(openSelectionMethod.methods());
                queue(openSelectionMethod.queue());
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
            this.queue = (Queue) ImmutableOpenSelectionMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableOpenSelectionMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addMethods(Method method) {
            this.methods.add((Method) ImmutableOpenSelectionMethod.requireNonNull(method, "methods element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addMethods(Method... methodArr) {
            for (Method method : methodArr) {
                this.methods.add((Method) ImmutableOpenSelectionMethod.requireNonNull(method, "methods element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("methods")
        public final Builder methods(Iterable<? extends Method> iterable) {
            this.methods.clear();
            return addAllMethods(iterable);
        }

        public final Builder addAllMethods(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.methods.add((Method) ImmutableOpenSelectionMethod.requireNonNull(it.next(), "methods element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableOpenSelectionMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableOpenSelectionMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean methodsIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build OpenSelectionMethod, some of required attributes are not set " + arrayList;
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
