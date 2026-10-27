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
@Generated(from = "ReorderMediaMethod", generator = "Immutables")
public final class ImmutableReorderMediaMethod extends ReorderMediaMethod {
    private final Boolean forced;
    private final int fromIndex;
    private final Queue queue;
    private final int toIndex;

    private ImmutableReorderMediaMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.fromIndex = builder.fromIndex;
        this.toIndex = builder.toIndex;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableReorderMediaMethod(Queue queue, Boolean bool, int i, int i2) {
        this.queue = queue;
        this.forced = bool;
        this.fromIndex = i;
        this.toIndex = i2;
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

    @Override // PlaybackInterface.v1_0.ReorderMediaMethod
    @JsonProperty("fromIndex")
    public int fromIndex() {
        return this.fromIndex;
    }

    @Override // PlaybackInterface.v1_0.ReorderMediaMethod
    @JsonProperty("toIndex")
    public int toIndex() {
        return this.toIndex;
    }

    public final ImmutableReorderMediaMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableReorderMediaMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.fromIndex, this.toIndex);
    }

    public final ImmutableReorderMediaMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableReorderMediaMethod(this.queue, bool2, this.fromIndex, this.toIndex);
    }

    public final ImmutableReorderMediaMethod withFromIndex(int i) {
        return this.fromIndex == i ? this : new ImmutableReorderMediaMethod(this.queue, this.forced, i, this.toIndex);
    }

    public final ImmutableReorderMediaMethod withToIndex(int i) {
        return this.toIndex == i ? this : new ImmutableReorderMediaMethod(this.queue, this.forced, this.fromIndex, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableReorderMediaMethod) && equalTo(0, (ImmutableReorderMediaMethod) obj);
    }

    private boolean equalTo(int i, ImmutableReorderMediaMethod immutableReorderMediaMethod) {
        return this.queue.equals(immutableReorderMediaMethod.queue) && this.forced.equals(immutableReorderMediaMethod.forced) && this.fromIndex == immutableReorderMediaMethod.fromIndex && this.toIndex == immutableReorderMediaMethod.toIndex;
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int i = iHashCode2 + (iHashCode2 << 5) + this.fromIndex;
        return i + (i << 5) + this.toIndex;
    }

    public String toString() {
        return "ReorderMediaMethod{queue=" + this.queue + ", forced=" + this.forced + ", fromIndex=" + this.fromIndex + ", toIndex=" + this.toIndex + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ReorderMediaMethod", generator = "Immutables")
    static final class Json extends ReorderMediaMethod {

        @Nullable
        Boolean forced;
        int fromIndex;
        boolean fromIndexIsSet;

        @Nullable
        Queue queue;
        int toIndex;
        boolean toIndexIsSet;

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

        @JsonProperty("fromIndex")
        public void setFromIndex(int i) {
            this.fromIndex = i;
            this.fromIndexIsSet = true;
        }

        @JsonProperty("toIndex")
        public void setToIndex(int i) {
            this.toIndex = i;
            this.toIndexIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ReorderMediaMethod
        public int fromIndex() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ReorderMediaMethod
        public int toIndex() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableReorderMediaMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        if (json.fromIndexIsSet) {
            builder.fromIndex(json.fromIndex);
        }
        if (json.toIndexIsSet) {
            builder.toIndex(json.toIndex);
        }
        return builder.build();
    }

    public static ImmutableReorderMediaMethod copyOf(ReorderMediaMethod reorderMediaMethod) {
        if (reorderMediaMethod instanceof ImmutableReorderMediaMethod) {
            return (ImmutableReorderMediaMethod) reorderMediaMethod;
        }
        return builder().from(reorderMediaMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ReorderMediaMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_FROM_INDEX = 2;
        private static final long INIT_BIT_QUEUE = 1;
        private static final long INIT_BIT_TO_INDEX = 4;

        @Nullable
        private Boolean forced;
        private int fromIndex;
        private long initBits;

        @Nullable
        private Queue queue;
        private int toIndex;

        private Builder() {
            this.initBits = 7L;
        }

        public final Builder from(Method method) {
            ImmutableReorderMediaMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(ReorderMediaMethod reorderMediaMethod) {
            ImmutableReorderMediaMethod.requireNonNull(reorderMediaMethod, "instance");
            from((Object) reorderMediaMethod);
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
            if (obj instanceof ReorderMediaMethod) {
                ReorderMediaMethod reorderMediaMethod = (ReorderMediaMethod) obj;
                toIndex(reorderMediaMethod.toIndex());
                if ((j & 1) == 0) {
                    forced(reorderMediaMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(reorderMediaMethod.queue());
                }
                fromIndex(reorderMediaMethod.fromIndex());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableReorderMediaMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableReorderMediaMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("fromIndex")
        public final Builder fromIndex(int i) {
            this.fromIndex = i;
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("toIndex")
        public final Builder toIndex(int i) {
            this.toIndex = i;
            this.initBits &= -5;
            return this;
        }

        public ImmutableReorderMediaMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableReorderMediaMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("fromIndex");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("toIndex");
            }
            return "Cannot build ReorderMediaMethod, some of required attributes are not set " + arrayList;
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
