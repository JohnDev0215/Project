package PlaybackInterface.v1_0;

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
@Generated(from = "RemoveFromQueueMethod", generator = "Immutables")
public final class ImmutableRemoveFromQueueMethod extends RemoveFromQueueMethod {
    private final Boolean forced;
    private final String mediaId;
    private final Queue queue;

    private ImmutableRemoveFromQueueMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.mediaId = builder.mediaId;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableRemoveFromQueueMethod(Queue queue, Boolean bool, String str) {
        this.queue = queue;
        this.forced = bool;
        this.mediaId = str;
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

    @Override // PlaybackInterface.v1_0.RemoveFromQueueMethod
    @JsonProperty("mediaId")
    public String mediaId() {
        return this.mediaId;
    }

    public final ImmutableRemoveFromQueueMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableRemoveFromQueueMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.mediaId);
    }

    public final ImmutableRemoveFromQueueMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableRemoveFromQueueMethod(this.queue, bool2, this.mediaId);
    }

    public final ImmutableRemoveFromQueueMethod withMediaId(String str) {
        String str2 = (String) requireNonNull(str, "mediaId");
        return this.mediaId.equals(str2) ? this : new ImmutableRemoveFromQueueMethod(this.queue, this.forced, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableRemoveFromQueueMethod) && equalTo(0, (ImmutableRemoveFromQueueMethod) obj);
    }

    private boolean equalTo(int i, ImmutableRemoveFromQueueMethod immutableRemoveFromQueueMethod) {
        return this.queue.equals(immutableRemoveFromQueueMethod.queue) && this.forced.equals(immutableRemoveFromQueueMethod.forced) && this.mediaId.equals(immutableRemoveFromQueueMethod.mediaId);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.mediaId.hashCode();
    }

    public String toString() {
        return "RemoveFromQueueMethod{queue=" + this.queue + ", forced=" + this.forced + ", mediaId=" + this.mediaId + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "RemoveFromQueueMethod", generator = "Immutables")
    static final class Json extends RemoveFromQueueMethod {

        @Nullable
        Boolean forced;

        @Nullable
        String mediaId;

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

        @JsonProperty("mediaId")
        public void setMediaId(String str) {
            this.mediaId = str;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.RemoveFromQueueMethod
        public String mediaId() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableRemoveFromQueueMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.mediaId;
        if (str != null) {
            builder.mediaId(str);
        }
        return builder.build();
    }

    public static ImmutableRemoveFromQueueMethod copyOf(RemoveFromQueueMethod removeFromQueueMethod) {
        if (removeFromQueueMethod instanceof ImmutableRemoveFromQueueMethod) {
            return (ImmutableRemoveFromQueueMethod) removeFromQueueMethod;
        }
        return builder().from(removeFromQueueMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "RemoveFromQueueMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_MEDIA_ID = 2;
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private String mediaId;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(Method method) {
            ImmutableRemoveFromQueueMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(RemoveFromQueueMethod removeFromQueueMethod) {
            ImmutableRemoveFromQueueMethod.requireNonNull(removeFromQueueMethod, "instance");
            from((Object) removeFromQueueMethod);
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
            if (obj instanceof RemoveFromQueueMethod) {
                RemoveFromQueueMethod removeFromQueueMethod = (RemoveFromQueueMethod) obj;
                mediaId(removeFromQueueMethod.mediaId());
                if ((j & 1) == 0) {
                    forced(removeFromQueueMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(removeFromQueueMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableRemoveFromQueueMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableRemoveFromQueueMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("mediaId")
        public final Builder mediaId(String str) {
            this.mediaId = (String) ImmutableRemoveFromQueueMethod.requireNonNull(str, "mediaId");
            this.initBits &= -3;
            return this;
        }

        public ImmutableRemoveFromQueueMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableRemoveFromQueueMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("mediaId");
            }
            return "Cannot build RemoveFromQueueMethod, some of required attributes are not set " + arrayList;
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
