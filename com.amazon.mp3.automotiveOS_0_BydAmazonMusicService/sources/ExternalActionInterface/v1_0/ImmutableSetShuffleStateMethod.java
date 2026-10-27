package ExternalActionInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import ExternalActionInterface.v1_0.SetShuffleStateMethod;
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
@Generated(from = "SetShuffleStateMethod", generator = "Immutables")
public final class ImmutableSetShuffleStateMethod extends SetShuffleStateMethod {
    private final Boolean forced;
    private final Queue queue;
    private final SetShuffleStateMethod.State state;

    private ImmutableSetShuffleStateMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.state = builder.state;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableSetShuffleStateMethod(Queue queue, Boolean bool, SetShuffleStateMethod.State state) {
        this.queue = queue;
        this.forced = bool;
        this.state = state;
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

    @Override // ExternalActionInterface.v1_0.SetShuffleStateMethod
    @JsonProperty("state")
    public SetShuffleStateMethod.State state() {
        return this.state;
    }

    public final ImmutableSetShuffleStateMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetShuffleStateMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.state);
    }

    public final ImmutableSetShuffleStateMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetShuffleStateMethod(this.queue, bool2, this.state);
    }

    public final ImmutableSetShuffleStateMethod withState(SetShuffleStateMethod.State state) {
        SetShuffleStateMethod.State state2 = (SetShuffleStateMethod.State) requireNonNull(state, "state");
        return this.state == state2 ? this : new ImmutableSetShuffleStateMethod(this.queue, this.forced, state2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetShuffleStateMethod) && equalTo(0, (ImmutableSetShuffleStateMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetShuffleStateMethod immutableSetShuffleStateMethod) {
        return this.queue.equals(immutableSetShuffleStateMethod.queue) && this.forced.equals(immutableSetShuffleStateMethod.forced) && this.state.equals(immutableSetShuffleStateMethod.state);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.state.hashCode();
    }

    public String toString() {
        return "SetShuffleStateMethod{queue=" + this.queue + ", forced=" + this.forced + ", state=" + this.state + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetShuffleStateMethod", generator = "Immutables")
    static final class Json extends SetShuffleStateMethod {

        @Nullable
        Boolean forced;

        @Nullable
        Queue queue;

        @Nullable
        SetShuffleStateMethod.State state;

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

        @JsonProperty("state")
        public void setState(SetShuffleStateMethod.State state) {
            this.state = state;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.SetShuffleStateMethod
        public SetShuffleStateMethod.State state() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetShuffleStateMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        SetShuffleStateMethod.State state = json.state;
        if (state != null) {
            builder.state(state);
        }
        return builder.build();
    }

    public static ImmutableSetShuffleStateMethod copyOf(SetShuffleStateMethod setShuffleStateMethod) {
        if (setShuffleStateMethod instanceof ImmutableSetShuffleStateMethod) {
            return (ImmutableSetShuffleStateMethod) setShuffleStateMethod;
        }
        return builder().from(setShuffleStateMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetShuffleStateMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long INIT_BIT_STATE = 2;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private Queue queue;

        @Nullable
        private SetShuffleStateMethod.State state;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(Method method) {
            ImmutableSetShuffleStateMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SetShuffleStateMethod setShuffleStateMethod) {
            ImmutableSetShuffleStateMethod.requireNonNull(setShuffleStateMethod, "instance");
            from((Object) setShuffleStateMethod);
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
            if (obj instanceof SetShuffleStateMethod) {
                SetShuffleStateMethod setShuffleStateMethod = (SetShuffleStateMethod) obj;
                if ((j & 1) == 0) {
                    forced(setShuffleStateMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(setShuffleStateMethod.queue());
                }
                state(setShuffleStateMethod.state());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetShuffleStateMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetShuffleStateMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("state")
        public final Builder state(SetShuffleStateMethod.State state) {
            this.state = (SetShuffleStateMethod.State) ImmutableSetShuffleStateMethod.requireNonNull(state, "state");
            this.initBits &= -3;
            return this;
        }

        public ImmutableSetShuffleStateMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetShuffleStateMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("state");
            }
            return "Cannot build SetShuffleStateMethod, some of required attributes are not set " + arrayList;
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
