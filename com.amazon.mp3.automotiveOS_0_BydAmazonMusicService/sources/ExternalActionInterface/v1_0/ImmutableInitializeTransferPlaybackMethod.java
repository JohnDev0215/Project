package ExternalActionInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
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
@Generated(from = "InitializeTransferPlaybackMethod", generator = "Immutables")
public final class ImmutableInitializeTransferPlaybackMethod extends InitializeTransferPlaybackMethod {
    private final String entityId;
    private final String entityReferenceId;
    private final String entityType;
    private final Boolean forced;
    private final Queue queue;
    private final String queueId;
    private final String sessionId;

    @Nullable
    private final Double startAt;

    private ImmutableInitializeTransferPlaybackMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.sessionId = builder.sessionId;
        this.queueId = builder.queueId;
        this.entityId = builder.entityId;
        this.entityType = builder.entityType;
        this.entityReferenceId = builder.entityReferenceId;
        this.startAt = builder.startAt;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableInitializeTransferPlaybackMethod(Queue queue, Boolean bool, String str, String str2, String str3, String str4, String str5, @Nullable Double d) {
        this.queue = queue;
        this.forced = bool;
        this.sessionId = str;
        this.queueId = str2;
        this.entityId = str3;
        this.entityType = str4;
        this.entityReferenceId = str5;
        this.startAt = d;
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

    @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
    @JsonProperty(Splash.PARAMS_SESSION_ID)
    public String sessionId() {
        return this.sessionId;
    }

    @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
    @JsonProperty("queueId")
    public String queueId() {
        return this.queueId;
    }

    @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
    @JsonProperty(ContextMappingConstants.ENTITY_ID)
    public String entityId() {
        return this.entityId;
    }

    @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
    @JsonProperty(PageUriUtils.ENTITY_TYPE_QUERY_PARAM)
    public String entityType() {
        return this.entityType;
    }

    @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
    @JsonProperty("entityReferenceId")
    public String entityReferenceId() {
        return this.entityReferenceId;
    }

    @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
    @JsonProperty("startAt")
    @Nullable
    public Double startAt() {
        return this.startAt;
    }

    public final ImmutableInitializeTransferPlaybackMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableInitializeTransferPlaybackMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.sessionId, this.queueId, this.entityId, this.entityType, this.entityReferenceId, this.startAt);
    }

    public final ImmutableInitializeTransferPlaybackMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableInitializeTransferPlaybackMethod(this.queue, bool2, this.sessionId, this.queueId, this.entityId, this.entityType, this.entityReferenceId, this.startAt);
    }

    public final ImmutableInitializeTransferPlaybackMethod withSessionId(String str) {
        String str2 = (String) requireNonNull(str, Splash.PARAMS_SESSION_ID);
        return this.sessionId.equals(str2) ? this : new ImmutableInitializeTransferPlaybackMethod(this.queue, this.forced, str2, this.queueId, this.entityId, this.entityType, this.entityReferenceId, this.startAt);
    }

    public final ImmutableInitializeTransferPlaybackMethod withQueueId(String str) {
        String str2 = (String) requireNonNull(str, "queueId");
        return this.queueId.equals(str2) ? this : new ImmutableInitializeTransferPlaybackMethod(this.queue, this.forced, this.sessionId, str2, this.entityId, this.entityType, this.entityReferenceId, this.startAt);
    }

    public final ImmutableInitializeTransferPlaybackMethod withEntityId(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.ENTITY_ID);
        return this.entityId.equals(str2) ? this : new ImmutableInitializeTransferPlaybackMethod(this.queue, this.forced, this.sessionId, this.queueId, str2, this.entityType, this.entityReferenceId, this.startAt);
    }

    public final ImmutableInitializeTransferPlaybackMethod withEntityType(String str) {
        String str2 = (String) requireNonNull(str, PageUriUtils.ENTITY_TYPE_QUERY_PARAM);
        return this.entityType.equals(str2) ? this : new ImmutableInitializeTransferPlaybackMethod(this.queue, this.forced, this.sessionId, this.queueId, this.entityId, str2, this.entityReferenceId, this.startAt);
    }

    public final ImmutableInitializeTransferPlaybackMethod withEntityReferenceId(String str) {
        String str2 = (String) requireNonNull(str, "entityReferenceId");
        return this.entityReferenceId.equals(str2) ? this : new ImmutableInitializeTransferPlaybackMethod(this.queue, this.forced, this.sessionId, this.queueId, this.entityId, this.entityType, str2, this.startAt);
    }

    public final ImmutableInitializeTransferPlaybackMethod withStartAt(@Nullable Double d) {
        return equals(this.startAt, d) ? this : new ImmutableInitializeTransferPlaybackMethod(this.queue, this.forced, this.sessionId, this.queueId, this.entityId, this.entityType, this.entityReferenceId, d);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableInitializeTransferPlaybackMethod) && equalTo(0, (ImmutableInitializeTransferPlaybackMethod) obj);
    }

    private boolean equalTo(int i, ImmutableInitializeTransferPlaybackMethod immutableInitializeTransferPlaybackMethod) {
        return this.queue.equals(immutableInitializeTransferPlaybackMethod.queue) && this.forced.equals(immutableInitializeTransferPlaybackMethod.forced) && this.sessionId.equals(immutableInitializeTransferPlaybackMethod.sessionId) && this.queueId.equals(immutableInitializeTransferPlaybackMethod.queueId) && this.entityId.equals(immutableInitializeTransferPlaybackMethod.entityId) && this.entityType.equals(immutableInitializeTransferPlaybackMethod.entityType) && this.entityReferenceId.equals(immutableInitializeTransferPlaybackMethod.entityReferenceId) && equals(this.startAt, immutableInitializeTransferPlaybackMethod.startAt);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.sessionId.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.queueId.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.entityId.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.entityType.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.entityReferenceId.hashCode();
        return iHashCode7 + (iHashCode7 << 5) + hashCode(this.startAt);
    }

    public String toString() {
        return "InitializeTransferPlaybackMethod{queue=" + this.queue + ", forced=" + this.forced + ", sessionId=" + this.sessionId + ", queueId=" + this.queueId + ", entityId=" + this.entityId + ", entityType=" + this.entityType + ", entityReferenceId=" + this.entityReferenceId + ", startAt=" + this.startAt + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "InitializeTransferPlaybackMethod", generator = "Immutables")
    static final class Json extends InitializeTransferPlaybackMethod {

        @Nullable
        String entityId;

        @Nullable
        String entityReferenceId;

        @Nullable
        String entityType;

        @Nullable
        Boolean forced;

        @Nullable
        Queue queue;

        @Nullable
        String queueId;

        @Nullable
        String sessionId;

        @Nullable
        Double startAt;

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

        @JsonProperty(Splash.PARAMS_SESSION_ID)
        public void setSessionId(String str) {
            this.sessionId = str;
        }

        @JsonProperty("queueId")
        public void setQueueId(String str) {
            this.queueId = str;
        }

        @JsonProperty(ContextMappingConstants.ENTITY_ID)
        public void setEntityId(String str) {
            this.entityId = str;
        }

        @JsonProperty(PageUriUtils.ENTITY_TYPE_QUERY_PARAM)
        public void setEntityType(String str) {
            this.entityType = str;
        }

        @JsonProperty("entityReferenceId")
        public void setEntityReferenceId(String str) {
            this.entityReferenceId = str;
        }

        @JsonProperty("startAt")
        public void setStartAt(@Nullable Double d) {
            this.startAt = d;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
        public String sessionId() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
        public String queueId() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
        public String entityId() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
        public String entityType() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
        public String entityReferenceId() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod
        public Double startAt() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableInitializeTransferPlaybackMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.sessionId;
        if (str != null) {
            builder.sessionId(str);
        }
        String str2 = json.queueId;
        if (str2 != null) {
            builder.queueId(str2);
        }
        String str3 = json.entityId;
        if (str3 != null) {
            builder.entityId(str3);
        }
        String str4 = json.entityType;
        if (str4 != null) {
            builder.entityType(str4);
        }
        String str5 = json.entityReferenceId;
        if (str5 != null) {
            builder.entityReferenceId(str5);
        }
        Double d = json.startAt;
        if (d != null) {
            builder.startAt(d);
        }
        return builder.build();
    }

    public static ImmutableInitializeTransferPlaybackMethod copyOf(InitializeTransferPlaybackMethod initializeTransferPlaybackMethod) {
        if (initializeTransferPlaybackMethod instanceof ImmutableInitializeTransferPlaybackMethod) {
            return (ImmutableInitializeTransferPlaybackMethod) initializeTransferPlaybackMethod;
        }
        return builder().from(initializeTransferPlaybackMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "InitializeTransferPlaybackMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ENTITY_ID = 8;
        private static final long INIT_BIT_ENTITY_REFERENCE_ID = 32;
        private static final long INIT_BIT_ENTITY_TYPE = 16;
        private static final long INIT_BIT_QUEUE = 1;
        private static final long INIT_BIT_QUEUE_ID = 4;
        private static final long INIT_BIT_SESSION_ID = 2;

        @Nullable
        private String entityId;

        @Nullable
        private String entityReferenceId;

        @Nullable
        private String entityType;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private Queue queue;

        @Nullable
        private String queueId;

        @Nullable
        private String sessionId;

        @Nullable
        private Double startAt;

        private Builder() {
            this.initBits = 63L;
        }

        public final Builder from(InitializeTransferPlaybackMethod initializeTransferPlaybackMethod) {
            ImmutableInitializeTransferPlaybackMethod.requireNonNull(initializeTransferPlaybackMethod, "instance");
            from((Object) initializeTransferPlaybackMethod);
            return this;
        }

        public final Builder from(Method method) {
            ImmutableInitializeTransferPlaybackMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof InitializeTransferPlaybackMethod) {
                InitializeTransferPlaybackMethod initializeTransferPlaybackMethod = (InitializeTransferPlaybackMethod) obj;
                queueId(initializeTransferPlaybackMethod.queueId());
                forced(initializeTransferPlaybackMethod.forced());
                entityType(initializeTransferPlaybackMethod.entityType());
                entityId(initializeTransferPlaybackMethod.entityId());
                sessionId(initializeTransferPlaybackMethod.sessionId());
                entityReferenceId(initializeTransferPlaybackMethod.entityReferenceId());
                queue(initializeTransferPlaybackMethod.queue());
                j = 3;
                Double dStartAt = initializeTransferPlaybackMethod.startAt();
                if (dStartAt != null) {
                    startAt(dStartAt);
                }
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
            this.queue = (Queue) ImmutableInitializeTransferPlaybackMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableInitializeTransferPlaybackMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty(Splash.PARAMS_SESSION_ID)
        public final Builder sessionId(String str) {
            this.sessionId = (String) ImmutableInitializeTransferPlaybackMethod.requireNonNull(str, Splash.PARAMS_SESSION_ID);
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("queueId")
        public final Builder queueId(String str) {
            this.queueId = (String) ImmutableInitializeTransferPlaybackMethod.requireNonNull(str, "queueId");
            this.initBits &= -5;
            return this;
        }

        @JsonProperty(ContextMappingConstants.ENTITY_ID)
        public final Builder entityId(String str) {
            this.entityId = (String) ImmutableInitializeTransferPlaybackMethod.requireNonNull(str, ContextMappingConstants.ENTITY_ID);
            this.initBits &= -9;
            return this;
        }

        @JsonProperty(PageUriUtils.ENTITY_TYPE_QUERY_PARAM)
        public final Builder entityType(String str) {
            this.entityType = (String) ImmutableInitializeTransferPlaybackMethod.requireNonNull(str, PageUriUtils.ENTITY_TYPE_QUERY_PARAM);
            this.initBits &= -17;
            return this;
        }

        @JsonProperty("entityReferenceId")
        public final Builder entityReferenceId(String str) {
            this.entityReferenceId = (String) ImmutableInitializeTransferPlaybackMethod.requireNonNull(str, "entityReferenceId");
            this.initBits &= -33;
            return this;
        }

        @JsonProperty("startAt")
        public final Builder startAt(@Nullable Double d) {
            this.startAt = d;
            return this;
        }

        public ImmutableInitializeTransferPlaybackMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableInitializeTransferPlaybackMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(Splash.PARAMS_SESSION_ID);
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("queueId");
            }
            if ((this.initBits & 8) != 0) {
                arrayList.add(ContextMappingConstants.ENTITY_ID);
            }
            if ((this.initBits & 16) != 0) {
                arrayList.add(PageUriUtils.ENTITY_TYPE_QUERY_PARAM);
            }
            if ((this.initBits & 32) != 0) {
                arrayList.add("entityReferenceId");
            }
            return "Cannot build InitializeTransferPlaybackMethod, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
