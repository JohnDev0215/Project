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
@Generated(from = "MultiThreadedQueue", generator = "Immutables")
public final class ImmutableMultiThreadedQueue extends MultiThreadedQueue {
    private final String id;

    private ImmutableMultiThreadedQueue(String str) {
        this.id = str;
    }

    @Override // CoreInterface.v1_0.Queue
    @JsonProperty("id")
    public String id() {
        return this.id;
    }

    public final ImmutableMultiThreadedQueue withId(String str) {
        String str2 = (String) requireNonNull(str, "id");
        return this.id.equals(str2) ? this : new ImmutableMultiThreadedQueue(str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMultiThreadedQueue) && equalTo(0, (ImmutableMultiThreadedQueue) obj);
    }

    private boolean equalTo(int i, ImmutableMultiThreadedQueue immutableMultiThreadedQueue) {
        return this.id.equals(immutableMultiThreadedQueue.id);
    }

    public int hashCode() {
        return 172192 + this.id.hashCode() + 5381;
    }

    public String toString() {
        return "MultiThreadedQueue{id=" + this.id + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MultiThreadedQueue", generator = "Immutables")
    static final class Json extends MultiThreadedQueue {

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
    static ImmutableMultiThreadedQueue fromJson(Json json) {
        Builder builder = builder();
        String str = json.id;
        if (str != null) {
            builder.id(str);
        }
        return builder.build();
    }

    public static ImmutableMultiThreadedQueue copyOf(MultiThreadedQueue multiThreadedQueue) {
        if (multiThreadedQueue instanceof ImmutableMultiThreadedQueue) {
            return (ImmutableMultiThreadedQueue) multiThreadedQueue;
        }
        return builder().from(multiThreadedQueue).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MultiThreadedQueue", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ID = 1;

        @Nullable
        private String id;
        private long initBits;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(Queue queue) {
            ImmutableMultiThreadedQueue.requireNonNull(queue, "instance");
            from((Object) queue);
            return this;
        }

        public final Builder from(MultiThreadedQueue multiThreadedQueue) {
            ImmutableMultiThreadedQueue.requireNonNull(multiThreadedQueue, "instance");
            from((Object) multiThreadedQueue);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof Queue) {
                id(((Queue) obj).id());
                j = 1;
            } else {
                j = 0;
            }
            if (obj instanceof MultiThreadedQueue) {
                MultiThreadedQueue multiThreadedQueue = (MultiThreadedQueue) obj;
                if ((j & 1) == 0) {
                    id(multiThreadedQueue.id());
                }
            }
        }

        @JsonProperty("id")
        public final Builder id(String str) {
            this.id = (String) ImmutableMultiThreadedQueue.requireNonNull(str, "id");
            this.initBits &= -2;
            return this;
        }

        public ImmutableMultiThreadedQueue build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableMultiThreadedQueue(this.id);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("id");
            }
            return "Cannot build MultiThreadedQueue, some of required attributes are not set " + arrayList;
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
