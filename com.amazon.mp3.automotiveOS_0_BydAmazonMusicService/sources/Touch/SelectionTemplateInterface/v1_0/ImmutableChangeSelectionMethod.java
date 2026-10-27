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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "ChangeSelectionMethod", generator = "Immutables")
public final class ImmutableChangeSelectionMethod extends ChangeSelectionMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final Map<String, String> parameters;
    private final Queue queue;

    private ImmutableChangeSelectionMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.parametersIsSet()) {
            this.initShim.parameters(createUnmodifiableMap(false, false, builder.parameters));
        }
        this.forced = this.initShim.forced();
        this.parameters = this.initShim.parameters();
        this.initShim = null;
    }

    private ImmutableChangeSelectionMethod(Queue queue, Boolean bool, Map<String, String> map) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.parameters = map;
        this.initShim = null;
    }

    @Generated(from = "ChangeSelectionMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private Map<String, String> parameters;
        private byte parametersBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.parametersBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableChangeSelectionMethod.requireNonNull(ImmutableChangeSelectionMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        Map<String, String> parameters() {
            byte b = this.parametersBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.parametersBuildStage = (byte) -1;
                this.parameters = ImmutableChangeSelectionMethod.createUnmodifiableMap(true, false, ImmutableChangeSelectionMethod.super.parameters());
                this.parametersBuildStage = (byte) 1;
            }
            return this.parameters;
        }

        void parameters(Map<String, String> map) {
            this.parameters = map;
            this.parametersBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.parametersBuildStage == -1) {
                arrayList.add("parameters");
            }
            return "Cannot build ChangeSelectionMethod, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.SelectionTemplateInterface.v1_0.ChangeSelectionMethod
    @JsonProperty("parameters")
    public Map<String, String> parameters() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.parameters();
        }
        return this.parameters;
    }

    public final ImmutableChangeSelectionMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableChangeSelectionMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.parameters);
    }

    public final ImmutableChangeSelectionMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableChangeSelectionMethod(this.queue, bool2, this.parameters);
    }

    public final ImmutableChangeSelectionMethod withParameters(Map<String, ? extends String> map) {
        if (this.parameters == map) {
            return this;
        }
        return new ImmutableChangeSelectionMethod(this.queue, this.forced, createUnmodifiableMap(true, false, map));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableChangeSelectionMethod) && equalTo(0, (ImmutableChangeSelectionMethod) obj);
    }

    private boolean equalTo(int i, ImmutableChangeSelectionMethod immutableChangeSelectionMethod) {
        return this.queue.equals(immutableChangeSelectionMethod.queue) && this.forced.equals(immutableChangeSelectionMethod.forced) && this.parameters.equals(immutableChangeSelectionMethod.parameters);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.parameters.hashCode();
    }

    public String toString() {
        return "ChangeSelectionMethod{queue=" + this.queue + ", forced=" + this.forced + ", parameters=" + this.parameters + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ChangeSelectionMethod", generator = "Immutables")
    static final class Json extends ChangeSelectionMethod {

        @Nullable
        Boolean forced;

        @Nullable
        Map<String, String> parameters = Collections.emptyMap();
        boolean parametersIsSet;

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

        @JsonProperty("parameters")
        public void setParameters(Map<String, String> map) {
            this.parameters = map;
            this.parametersIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SelectionTemplateInterface.v1_0.ChangeSelectionMethod
        public Map<String, String> parameters() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableChangeSelectionMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        if (json.parametersIsSet) {
            builder.putAllParameters(json.parameters);
        }
        return builder.build();
    }

    public static ImmutableChangeSelectionMethod copyOf(ChangeSelectionMethod changeSelectionMethod) {
        if (changeSelectionMethod instanceof ImmutableChangeSelectionMethod) {
            return (ImmutableChangeSelectionMethod) changeSelectionMethod;
        }
        return builder().from(changeSelectionMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ChangeSelectionMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_PARAMETERS = 1;

        @Nullable
        private Boolean forced;
        private long initBits;
        private long optBits;
        private Map<String, String> parameters;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
            this.parameters = new LinkedHashMap();
        }

        public final Builder from(Method method) {
            ImmutableChangeSelectionMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(ChangeSelectionMethod changeSelectionMethod) {
            ImmutableChangeSelectionMethod.requireNonNull(changeSelectionMethod, "instance");
            from((Object) changeSelectionMethod);
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
            if (obj instanceof ChangeSelectionMethod) {
                ChangeSelectionMethod changeSelectionMethod = (ChangeSelectionMethod) obj;
                putAllParameters(changeSelectionMethod.parameters());
                if ((j & 1) == 0) {
                    forced(changeSelectionMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(changeSelectionMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableChangeSelectionMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableChangeSelectionMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder putParameters(String str, String str2) {
            this.parameters.put((String) ImmutableChangeSelectionMethod.requireNonNull(str, "parameters key"), (String) ImmutableChangeSelectionMethod.requireNonNull(str2, "parameters value"));
            this.optBits |= 1;
            return this;
        }

        public final Builder putParameters(Map.Entry<String, ? extends String> entry) {
            this.parameters.put((String) ImmutableChangeSelectionMethod.requireNonNull(entry.getKey(), "parameters key"), (String) ImmutableChangeSelectionMethod.requireNonNull(entry.getValue(), "parameters value"));
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("parameters")
        public final Builder parameters(Map<String, ? extends String> map) {
            this.parameters.clear();
            this.optBits |= 1;
            return putAllParameters(map);
        }

        public final Builder putAllParameters(Map<String, ? extends String> map) {
            for (Map.Entry<String, ? extends String> entry : map.entrySet()) {
                this.parameters.put((String) ImmutableChangeSelectionMethod.requireNonNull(entry.getKey(), "parameters key"), (String) ImmutableChangeSelectionMethod.requireNonNull(entry.getValue(), "parameters value"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableChangeSelectionMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableChangeSelectionMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean parametersIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build ChangeSelectionMethod, some of required attributes are not set " + arrayList;
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
    public static <K, V> Map<K, V> createUnmodifiableMap(boolean z, boolean z2, Map<? extends K, ? extends V> map) {
        int size = map.size();
        if (size == 0) {
            return Collections.emptyMap();
        }
        if (size == 1) {
            Map.Entry<? extends K, ? extends V> next = map.entrySet().iterator().next();
            K key = next.getKey();
            V value = next.getValue();
            if (z) {
                requireNonNull(key, "key");
                requireNonNull(value, "value");
            }
            if (z2 && (key == null || value == null)) {
                return Collections.emptyMap();
            }
            return Collections.singletonMap(key, value);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map.size());
        if (z2 || z) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                K key2 = entry.getKey();
                V value2 = entry.getValue();
                if (z2) {
                    if (key2 == null || value2 == null) {
                    }
                } else if (z) {
                    requireNonNull(key2, "key");
                    requireNonNull(value2, "value");
                }
                linkedHashMap.put(key2, value2);
            }
        } else {
            linkedHashMap.putAll(map);
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
