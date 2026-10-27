package QueuesInterface.v1_0;

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
@Generated(from = "SingleThreadedQueue", generator = "Immutables")
public final class ImmutableSingleThreadedQueue extends SingleThreadedQueue {
    private final String id;

    private ImmutableSingleThreadedQueue(String str) {
        this.id = str;
    }

    @Override // CoreInterface.v1_0.Queue
    @JsonProperty("id")
    public String id() {
        return this.id;
    }

    public final ImmutableSingleThreadedQueue withId(String str) {
        String str2 = (String) requireNonNull(str, "id");
        return this.id.equals(str2) ? this : new ImmutableSingleThreadedQueue(str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSingleThreadedQueue) && equalTo(0, (ImmutableSingleThreadedQueue) obj);
    }

    private boolean equalTo(int i, ImmutableSingleThreadedQueue immutableSingleThreadedQueue) {
        return this.id.equals(immutableSingleThreadedQueue.id);
    }

    public int hashCode() {
        return 172192 + this.id.hashCode() + 5381;
    }

    public String toString() {
        return "SingleThreadedQueue{id=" + this.id + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SingleThreadedQueue", generator = "Immutables")
    static final class Json extends SingleThreadedQueue {

        @Nullable
        String id;

        Json() {
        }

        @JsonProperty("id")
        public void setId(String str) {
            this.id = str;
        }

        @Override // CoreInterface.v1_0.Queue
        public String id() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSingleThreadedQueue fromJson(Json json) {
        Builder builder = builder();
        String str = json.id;
        if (str != null) {
            builder.id(str);
        }
        return builder.build();
    }

    public static ImmutableSingleThreadedQueue copyOf(SingleThreadedQueue singleThreadedQueue) {
        if (singleThreadedQueue instanceof ImmutableSingleThreadedQueue) {
            return (ImmutableSingleThreadedQueue) singleThreadedQueue;
        }
        return builder().from(singleThreadedQueue).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SingleThreadedQueue", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ID = 1;

        @Nullable
        private String id;
        private long initBits;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(SingleThreadedQueue singleThreadedQueue) {
            ImmutableSingleThreadedQueue.requireNonNull(singleThreadedQueue, "instance");
            from((Object) singleThreadedQueue);
            return this;
        }

        public final Builder from(Queue queue) {
            ImmutableSingleThreadedQueue.requireNonNull(queue, "instance");
            from((Object) queue);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof SingleThreadedQueue) {
                id(((SingleThreadedQueue) obj).id());
                j = 1;
            } else {
                j = 0;
            }
            if (obj instanceof Queue) {
                Queue queue = (Queue) obj;
                if ((j & 1) == 0) {
                    id(queue.id());
                }
            }
        }

        @JsonProperty("id")
        public final Builder id(String str) {
            this.id = (String) ImmutableSingleThreadedQueue.requireNonNull(str, "id");
            this.initBits &= -2;
            return this;
        }

        public ImmutableSingleThreadedQueue build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSingleThreadedQueue(this.id);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("id");
            }
            return "Cannot build SingleThreadedQueue, some of required attributes are not set " + arrayList;
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
