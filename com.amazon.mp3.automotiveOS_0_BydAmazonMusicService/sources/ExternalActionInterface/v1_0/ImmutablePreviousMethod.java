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
@Generated(from = "PreviousMethod", generator = "Immutables")
public final class ImmutablePreviousMethod extends PreviousMethod {
    private final Boolean forced;
    private final Queue queue;

    private ImmutablePreviousMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutablePreviousMethod(Queue queue, Boolean bool) {
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

    public final ImmutablePreviousMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutablePreviousMethod((Queue) requireNonNull(queue, "queue"), this.forced);
    }

    public final ImmutablePreviousMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutablePreviousMethod(this.queue, bool2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePreviousMethod) && equalTo(0, (ImmutablePreviousMethod) obj);
    }

    private boolean equalTo(int i, ImmutablePreviousMethod immutablePreviousMethod) {
        return this.queue.equals(immutablePreviousMethod.queue) && this.forced.equals(immutablePreviousMethod.forced);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.forced.hashCode();
    }

    public String toString() {
        return "PreviousMethod{queue=" + this.queue + ", forced=" + this.forced + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PreviousMethod", generator = "Immutables")
    static final class Json extends PreviousMethod {

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
    static ImmutablePreviousMethod fromJson(Json json) {
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

    public static ImmutablePreviousMethod copyOf(PreviousMethod previousMethod) {
        if (previousMethod instanceof ImmutablePreviousMethod) {
            return (ImmutablePreviousMethod) previousMethod;
        }
        return builder().from(previousMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PreviousMethod", generator = "Immutables")
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

        public final Builder from(Method method) {
            ImmutablePreviousMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(PreviousMethod previousMethod) {
            ImmutablePreviousMethod.requireNonNull(previousMethod, "instance");
            from((Object) previousMethod);
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
            if (obj instanceof PreviousMethod) {
                PreviousMethod previousMethod = (PreviousMethod) obj;
                if ((j & 1) == 0) {
                    forced(previousMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(previousMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutablePreviousMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutablePreviousMethod.requireNonNull(bool, "forced");
            return this;
        }

        public ImmutablePreviousMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePreviousMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build PreviousMethod, some of required attributes are not set " + arrayList;
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
