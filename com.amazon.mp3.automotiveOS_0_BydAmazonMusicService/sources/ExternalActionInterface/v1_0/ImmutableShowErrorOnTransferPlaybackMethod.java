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
@Generated(from = "ShowErrorOnTransferPlaybackMethod", generator = "Immutables")
public final class ImmutableShowErrorOnTransferPlaybackMethod extends ShowErrorOnTransferPlaybackMethod {
    private final Boolean forced;
    private final Queue queue;

    private ImmutableShowErrorOnTransferPlaybackMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableShowErrorOnTransferPlaybackMethod(Queue queue, Boolean bool) {
        this.queue = queue;
        this.forced = bool;
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

    public final ImmutableShowErrorOnTransferPlaybackMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableShowErrorOnTransferPlaybackMethod((Queue) requireNonNull(queue, "queue"), this.forced);
    }

    public final ImmutableShowErrorOnTransferPlaybackMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableShowErrorOnTransferPlaybackMethod(this.queue, bool2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableShowErrorOnTransferPlaybackMethod) && equalTo(0, (ImmutableShowErrorOnTransferPlaybackMethod) obj);
    }

    private boolean equalTo(int i, ImmutableShowErrorOnTransferPlaybackMethod immutableShowErrorOnTransferPlaybackMethod) {
        return this.queue.equals(immutableShowErrorOnTransferPlaybackMethod.queue) && this.forced.equals(immutableShowErrorOnTransferPlaybackMethod.forced);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.forced.hashCode();
    }

    public String toString() {
        return "ShowErrorOnTransferPlaybackMethod{queue=" + this.queue + ", forced=" + this.forced + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ShowErrorOnTransferPlaybackMethod", generator = "Immutables")
    static final class Json extends ShowErrorOnTransferPlaybackMethod {

        @Nullable
        Boolean forced;

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

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableShowErrorOnTransferPlaybackMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        return builder.build();
    }

    public static ImmutableShowErrorOnTransferPlaybackMethod copyOf(ShowErrorOnTransferPlaybackMethod showErrorOnTransferPlaybackMethod) {
        if (showErrorOnTransferPlaybackMethod instanceof ImmutableShowErrorOnTransferPlaybackMethod) {
            return (ImmutableShowErrorOnTransferPlaybackMethod) showErrorOnTransferPlaybackMethod;
        }
        return builder().from(showErrorOnTransferPlaybackMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ShowErrorOnTransferPlaybackMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(ShowErrorOnTransferPlaybackMethod showErrorOnTransferPlaybackMethod) {
            ImmutableShowErrorOnTransferPlaybackMethod.requireNonNull(showErrorOnTransferPlaybackMethod, "instance");
            from((Object) showErrorOnTransferPlaybackMethod);
            return this;
        }

        public final Builder from(Method method) {
            ImmutableShowErrorOnTransferPlaybackMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof ShowErrorOnTransferPlaybackMethod) {
                ShowErrorOnTransferPlaybackMethod showErrorOnTransferPlaybackMethod = (ShowErrorOnTransferPlaybackMethod) obj;
                forced(showErrorOnTransferPlaybackMethod.forced());
                queue(showErrorOnTransferPlaybackMethod.queue());
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
            this.queue = (Queue) ImmutableShowErrorOnTransferPlaybackMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableShowErrorOnTransferPlaybackMethod.requireNonNull(bool, "forced");
            return this;
        }

        public ImmutableShowErrorOnTransferPlaybackMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableShowErrorOnTransferPlaybackMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build ShowErrorOnTransferPlaybackMethod, some of required attributes are not set " + arrayList;
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
