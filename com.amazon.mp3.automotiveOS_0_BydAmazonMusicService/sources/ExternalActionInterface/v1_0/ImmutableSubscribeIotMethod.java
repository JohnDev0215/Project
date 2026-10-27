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
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "SubscribeIotMethod", generator = "Immutables")
public final class ImmutableSubscribeIotMethod extends SubscribeIotMethod {
    private final Boolean forced;
    private final Queue queue;
    private final String queueId;
    private final String targetId;

    private ImmutableSubscribeIotMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.targetId = builder.targetId;
        this.queueId = builder.queueId;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableSubscribeIotMethod(Queue queue, Boolean bool, String str, String str2) {
        this.queue = queue;
        this.forced = bool;
        this.targetId = str;
        this.queueId = str2;
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

    @Override // ExternalActionInterface.v1_0.SubscribeIotMethod
    @JsonProperty("targetId")
    public String targetId() {
        return this.targetId;
    }

    @Override // ExternalActionInterface.v1_0.SubscribeIotMethod
    @JsonProperty("queueId")
    public String queueId() {
        return this.queueId;
    }

    public final ImmutableSubscribeIotMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSubscribeIotMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.targetId, this.queueId);
    }

    public final ImmutableSubscribeIotMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSubscribeIotMethod(this.queue, bool2, this.targetId, this.queueId);
    }

    public final ImmutableSubscribeIotMethod withTargetId(String str) {
        String str2 = (String) requireNonNull(str, "targetId");
        return this.targetId.equals(str2) ? this : new ImmutableSubscribeIotMethod(this.queue, this.forced, str2, this.queueId);
    }

    public final ImmutableSubscribeIotMethod withQueueId(String str) {
        String str2 = (String) requireNonNull(str, "queueId");
        return this.queueId.equals(str2) ? this : new ImmutableSubscribeIotMethod(this.queue, this.forced, this.targetId, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSubscribeIotMethod) && equalTo(0, (ImmutableSubscribeIotMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSubscribeIotMethod immutableSubscribeIotMethod) {
        return this.queue.equals(immutableSubscribeIotMethod.queue) && this.forced.equals(immutableSubscribeIotMethod.forced) && this.targetId.equals(immutableSubscribeIotMethod.targetId) && this.queueId.equals(immutableSubscribeIotMethod.queueId);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.targetId.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.queueId.hashCode();
    }

    public String toString() {
        return "SubscribeIotMethod{queue=" + this.queue + ", forced=" + this.forced + ", targetId=" + this.targetId + ", queueId=" + this.queueId + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SubscribeIotMethod", generator = "Immutables")
    static final class Json extends SubscribeIotMethod {

        @Nullable
        Boolean forced;

        @Nullable
        Queue queue;

        @Nullable
        String queueId;

        @Nullable
        String targetId;

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

        @JsonProperty("targetId")
        public void setTargetId(String str) {
            this.targetId = str;
        }

        @JsonProperty("queueId")
        public void setQueueId(String str) {
            this.queueId = str;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.SubscribeIotMethod
        public String targetId() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.SubscribeIotMethod
        public String queueId() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSubscribeIotMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.targetId;
        if (str != null) {
            builder.targetId(str);
        }
        String str2 = json.queueId;
        if (str2 != null) {
            builder.queueId(str2);
        }
        return builder.build();
    }

    public static ImmutableSubscribeIotMethod copyOf(SubscribeIotMethod subscribeIotMethod) {
        if (subscribeIotMethod instanceof ImmutableSubscribeIotMethod) {
            return (ImmutableSubscribeIotMethod) subscribeIotMethod;
        }
        return builder().from(subscribeIotMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SubscribeIotMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long INIT_BIT_QUEUE_ID = 4;
        private static final long INIT_BIT_TARGET_ID = 2;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private Queue queue;

        @Nullable
        private String queueId;

        @Nullable
        private String targetId;

        private Builder() {
            this.initBits = 7L;
        }

        public final Builder from(Method method) {
            ImmutableSubscribeIotMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SubscribeIotMethod subscribeIotMethod) {
            ImmutableSubscribeIotMethod.requireNonNull(subscribeIotMethod, "instance");
            from((Object) subscribeIotMethod);
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
            if (obj instanceof SubscribeIotMethod) {
                SubscribeIotMethod subscribeIotMethod = (SubscribeIotMethod) obj;
                queueId(subscribeIotMethod.queueId());
                targetId(subscribeIotMethod.targetId());
                if ((j & 1) == 0) {
                    forced(subscribeIotMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(subscribeIotMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSubscribeIotMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSubscribeIotMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("targetId")
        public final Builder targetId(String str) {
            this.targetId = (String) ImmutableSubscribeIotMethod.requireNonNull(str, "targetId");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("queueId")
        public final Builder queueId(String str) {
            this.queueId = (String) ImmutableSubscribeIotMethod.requireNonNull(str, "queueId");
            this.initBits &= -5;
            return this;
        }

        public ImmutableSubscribeIotMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSubscribeIotMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("targetId");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("queueId");
            }
            return "Cannot build SubscribeIotMethod, some of required attributes are not set " + arrayList;
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
