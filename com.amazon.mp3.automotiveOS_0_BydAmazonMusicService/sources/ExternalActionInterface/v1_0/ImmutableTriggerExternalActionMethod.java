package ExternalActionInterface.v1_0;

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
@Generated(from = "TriggerExternalActionMethod", generator = "Immutables")
public final class ImmutableTriggerExternalActionMethod extends TriggerExternalActionMethod {
    private final Map<String, String> context;
    private final Boolean forced;
    private final String name;
    private final Queue queue;

    private ImmutableTriggerExternalActionMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.name = builder.name;
        this.context = createUnmodifiableMap(false, false, builder.context);
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableTriggerExternalActionMethod(Queue queue, Boolean bool, String str, Map<String, String> map) {
        this.queue = queue;
        this.forced = bool;
        this.name = str;
        this.context = map;
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

    @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
    @JsonProperty("name")
    public String name() {
        return this.name;
    }

    @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
    @JsonProperty("context")
    public Map<String, String> context() {
        return this.context;
    }

    public final ImmutableTriggerExternalActionMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableTriggerExternalActionMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.name, this.context);
    }

    public final ImmutableTriggerExternalActionMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableTriggerExternalActionMethod(this.queue, bool2, this.name, this.context);
    }

    public final ImmutableTriggerExternalActionMethod withName(String str) {
        String str2 = (String) requireNonNull(str, "name");
        return this.name.equals(str2) ? this : new ImmutableTriggerExternalActionMethod(this.queue, this.forced, str2, this.context);
    }

    public final ImmutableTriggerExternalActionMethod withContext(Map<String, ? extends String> map) {
        if (this.context == map) {
            return this;
        }
        return new ImmutableTriggerExternalActionMethod(this.queue, this.forced, this.name, createUnmodifiableMap(true, false, map));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTriggerExternalActionMethod) && equalTo(0, (ImmutableTriggerExternalActionMethod) obj);
    }

    private boolean equalTo(int i, ImmutableTriggerExternalActionMethod immutableTriggerExternalActionMethod) {
        return this.queue.equals(immutableTriggerExternalActionMethod.queue) && this.forced.equals(immutableTriggerExternalActionMethod.forced) && this.name.equals(immutableTriggerExternalActionMethod.name) && this.context.equals(immutableTriggerExternalActionMethod.context);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.name.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.context.hashCode();
    }

    public String toString() {
        return "TriggerExternalActionMethod{queue=" + this.queue + ", forced=" + this.forced + ", name=" + this.name + ", context=" + this.context + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "TriggerExternalActionMethod", generator = "Immutables")
    static final class Json extends TriggerExternalActionMethod {

        @Nullable
        Map<String, String> context = Collections.emptyMap();

        @Nullable
        Boolean forced;

        @Nullable
        String name;

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

        @JsonProperty("name")
        public void setName(String str) {
            this.name = str;
        }

        @JsonProperty("context")
        public void setContext(Map<String, String> map) {
            this.context = map;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
        public String name() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
        public Map<String, String> context() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTriggerExternalActionMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.name;
        if (str != null) {
            builder.name(str);
        }
        Map<String, String> map = json.context;
        if (map != null) {
            builder.putAllContext(map);
        }
        return builder.build();
    }

    public static ImmutableTriggerExternalActionMethod copyOf(TriggerExternalActionMethod triggerExternalActionMethod) {
        if (triggerExternalActionMethod instanceof ImmutableTriggerExternalActionMethod) {
            return (ImmutableTriggerExternalActionMethod) triggerExternalActionMethod;
        }
        return builder().from(triggerExternalActionMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "TriggerExternalActionMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_NAME = 2;
        private static final long INIT_BIT_QUEUE = 1;
        private Map<String, String> context;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private String name;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 3L;
            this.context = new LinkedHashMap();
        }

        public final Builder from(Method method) {
            ImmutableTriggerExternalActionMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(TriggerExternalActionMethod triggerExternalActionMethod) {
            ImmutableTriggerExternalActionMethod.requireNonNull(triggerExternalActionMethod, "instance");
            from((Object) triggerExternalActionMethod);
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
            if (obj instanceof TriggerExternalActionMethod) {
                TriggerExternalActionMethod triggerExternalActionMethod = (TriggerExternalActionMethod) obj;
                name(triggerExternalActionMethod.name());
                putAllContext(triggerExternalActionMethod.context());
                if ((j & 1) == 0) {
                    forced(triggerExternalActionMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(triggerExternalActionMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableTriggerExternalActionMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableTriggerExternalActionMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("name")
        public final Builder name(String str) {
            this.name = (String) ImmutableTriggerExternalActionMethod.requireNonNull(str, "name");
            this.initBits &= -3;
            return this;
        }

        public final Builder putContext(String str, String str2) {
            this.context.put((String) ImmutableTriggerExternalActionMethod.requireNonNull(str, "context key"), (String) ImmutableTriggerExternalActionMethod.requireNonNull(str2, "context value"));
            return this;
        }

        public final Builder putContext(Map.Entry<String, ? extends String> entry) {
            this.context.put((String) ImmutableTriggerExternalActionMethod.requireNonNull(entry.getKey(), "context key"), (String) ImmutableTriggerExternalActionMethod.requireNonNull(entry.getValue(), "context value"));
            return this;
        }

        @JsonProperty("context")
        public final Builder context(Map<String, ? extends String> map) {
            this.context.clear();
            return putAllContext(map);
        }

        public final Builder putAllContext(Map<String, ? extends String> map) {
            for (Map.Entry<String, ? extends String> entry : map.entrySet()) {
                this.context.put((String) ImmutableTriggerExternalActionMethod.requireNonNull(entry.getKey(), "context key"), (String) ImmutableTriggerExternalActionMethod.requireNonNull(entry.getValue(), "context value"));
            }
            return this;
        }

        public ImmutableTriggerExternalActionMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableTriggerExternalActionMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("name");
            }
            return "Cannot build TriggerExternalActionMethod, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private static <K, V> Map<K, V> createUnmodifiableMap(boolean z, boolean z2, Map<? extends K, ? extends V> map) {
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
