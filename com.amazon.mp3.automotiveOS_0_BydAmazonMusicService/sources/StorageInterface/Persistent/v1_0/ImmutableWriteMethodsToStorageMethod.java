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
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "WriteMethodsToStorageMethod", generator = "Immutables")
public final class ImmutableWriteMethodsToStorageMethod extends WriteMethodsToStorageMethod {
    private final Boolean forced;
    private final String key;
    private final Queue queue;
    private final String serializedMethods;

    private ImmutableWriteMethodsToStorageMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.key = builder.key;
        this.serializedMethods = builder.serializedMethods;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableWriteMethodsToStorageMethod(Queue queue, Boolean bool, String str, String str2) {
        this.queue = queue;
        this.forced = bool;
        this.key = str;
        this.serializedMethods = str2;
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

    @Override // StorageInterface.Persistent.v1_0.WriteMethodsToStorageMethod
    @JsonProperty("key")
    public String key() {
        return this.key;
    }

    @Override // StorageInterface.Persistent.v1_0.WriteMethodsToStorageMethod
    @JsonProperty("serializedMethods")
    public String serializedMethods() {
        return this.serializedMethods;
    }

    public final ImmutableWriteMethodsToStorageMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableWriteMethodsToStorageMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.key, this.serializedMethods);
    }

    public final ImmutableWriteMethodsToStorageMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableWriteMethodsToStorageMethod(this.queue, bool2, this.key, this.serializedMethods);
    }

    public final ImmutableWriteMethodsToStorageMethod withKey(String str) {
        String str2 = (String) requireNonNull(str, "key");
        return this.key.equals(str2) ? this : new ImmutableWriteMethodsToStorageMethod(this.queue, this.forced, str2, this.serializedMethods);
    }

    public final ImmutableWriteMethodsToStorageMethod withSerializedMethods(String str) {
        String str2 = (String) requireNonNull(str, "serializedMethods");
        return this.serializedMethods.equals(str2) ? this : new ImmutableWriteMethodsToStorageMethod(this.queue, this.forced, this.key, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableWriteMethodsToStorageMethod) && equalTo(0, (ImmutableWriteMethodsToStorageMethod) obj);
    }

    private boolean equalTo(int i, ImmutableWriteMethodsToStorageMethod immutableWriteMethodsToStorageMethod) {
        return this.queue.equals(immutableWriteMethodsToStorageMethod.queue) && this.forced.equals(immutableWriteMethodsToStorageMethod.forced) && this.key.equals(immutableWriteMethodsToStorageMethod.key) && this.serializedMethods.equals(immutableWriteMethodsToStorageMethod.serializedMethods);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.key.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.serializedMethods.hashCode();
    }

    public String toString() {
        return "WriteMethodsToStorageMethod{queue=" + this.queue + ", forced=" + this.forced + ", key=" + this.key + ", serializedMethods=" + this.serializedMethods + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "WriteMethodsToStorageMethod", generator = "Immutables")
    static final class Json extends WriteMethodsToStorageMethod {

        @Nullable
        Boolean forced;

        @Nullable
        String key;

        @Nullable
        Queue queue;

        @Nullable
        String serializedMethods;

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

        @JsonProperty("serializedMethods")
        public void setSerializedMethods(String str) {
            this.serializedMethods = str;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // StorageInterface.Persistent.v1_0.WriteMethodsToStorageMethod
        public String key() {
            throw new UnsupportedOperationException();
        }

        @Override // StorageInterface.Persistent.v1_0.WriteMethodsToStorageMethod
        public String serializedMethods() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableWriteMethodsToStorageMethod fromJson(Json json) {
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
        String str2 = json.serializedMethods;
        if (str2 != null) {
            builder.serializedMethods(str2);
        }
        return builder.build();
    }

    public static ImmutableWriteMethodsToStorageMethod copyOf(WriteMethodsToStorageMethod writeMethodsToStorageMethod) {
        if (writeMethodsToStorageMethod instanceof ImmutableWriteMethodsToStorageMethod) {
            return (ImmutableWriteMethodsToStorageMethod) writeMethodsToStorageMethod;
        }
        return builder().from(writeMethodsToStorageMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "WriteMethodsToStorageMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_KEY = 2;
        private static final long INIT_BIT_QUEUE = 1;
        private static final long INIT_BIT_SERIALIZED_METHODS = 4;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private String key;

        @Nullable
        private Queue queue;

        @Nullable
        private String serializedMethods;

        private Builder() {
            this.initBits = 7L;
        }

        public final Builder from(Method method) {
            ImmutableWriteMethodsToStorageMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(WriteMethodsToStorageMethod writeMethodsToStorageMethod) {
            ImmutableWriteMethodsToStorageMethod.requireNonNull(writeMethodsToStorageMethod, "instance");
            from((Object) writeMethodsToStorageMethod);
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
            if (obj instanceof WriteMethodsToStorageMethod) {
                WriteMethodsToStorageMethod writeMethodsToStorageMethod = (WriteMethodsToStorageMethod) obj;
                serializedMethods(writeMethodsToStorageMethod.serializedMethods());
                if ((j & 1) == 0) {
                    forced(writeMethodsToStorageMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(writeMethodsToStorageMethod.queue());
                }
                key(writeMethodsToStorageMethod.key());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableWriteMethodsToStorageMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableWriteMethodsToStorageMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("key")
        public final Builder key(String str) {
            this.key = (String) ImmutableWriteMethodsToStorageMethod.requireNonNull(str, "key");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("serializedMethods")
        public final Builder serializedMethods(String str) {
            this.serializedMethods = (String) ImmutableWriteMethodsToStorageMethod.requireNonNull(str, "serializedMethods");
            this.initBits &= -5;
            return this;
        }

        public ImmutableWriteMethodsToStorageMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableWriteMethodsToStorageMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("key");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("serializedMethods");
            }
            return "Cannot build WriteMethodsToStorageMethod, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
