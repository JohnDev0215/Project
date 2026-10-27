package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
@Generated(from = "SetBackgroundImageMethod", generator = "Immutables")
public final class ImmutableSetBackgroundImageMethod extends SetBackgroundImageMethod {
    private final Boolean forced;
    private final String image;
    private final Queue queue;

    private ImmutableSetBackgroundImageMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.image = builder.image;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableSetBackgroundImageMethod(Queue queue, Boolean bool, String str) {
        this.queue = queue;
        this.forced = bool;
        this.image = str;
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

    @Override // PlaybackInterface.v1_0.SetBackgroundImageMethod
    @JsonProperty(ContextMappingConstants.IMAGE)
    public String image() {
        return this.image;
    }

    public final ImmutableSetBackgroundImageMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetBackgroundImageMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.image);
    }

    public final ImmutableSetBackgroundImageMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetBackgroundImageMethod(this.queue, bool2, this.image);
    }

    public final ImmutableSetBackgroundImageMethod withImage(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.IMAGE);
        return this.image.equals(str2) ? this : new ImmutableSetBackgroundImageMethod(this.queue, this.forced, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetBackgroundImageMethod) && equalTo(0, (ImmutableSetBackgroundImageMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetBackgroundImageMethod immutableSetBackgroundImageMethod) {
        return this.queue.equals(immutableSetBackgroundImageMethod.queue) && this.forced.equals(immutableSetBackgroundImageMethod.forced) && this.image.equals(immutableSetBackgroundImageMethod.image);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.image.hashCode();
    }

    public String toString() {
        return "SetBackgroundImageMethod{queue=" + this.queue + ", forced=" + this.forced + ", image=" + this.image + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetBackgroundImageMethod", generator = "Immutables")
    static final class Json extends SetBackgroundImageMethod {

        @Nullable
        Boolean forced;

        @Nullable
        String image;

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

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(String str) {
            this.image = str;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetBackgroundImageMethod
        public String image() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetBackgroundImageMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.image;
        if (str != null) {
            builder.image(str);
        }
        return builder.build();
    }

    public static ImmutableSetBackgroundImageMethod copyOf(SetBackgroundImageMethod setBackgroundImageMethod) {
        if (setBackgroundImageMethod instanceof ImmutableSetBackgroundImageMethod) {
            return (ImmutableSetBackgroundImageMethod) setBackgroundImageMethod;
        }
        return builder().from(setBackgroundImageMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetBackgroundImageMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_IMAGE = 2;
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;

        @Nullable
        private String image;
        private long initBits;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(Method method) {
            ImmutableSetBackgroundImageMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SetBackgroundImageMethod setBackgroundImageMethod) {
            ImmutableSetBackgroundImageMethod.requireNonNull(setBackgroundImageMethod, "instance");
            from((Object) setBackgroundImageMethod);
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
            if (obj instanceof SetBackgroundImageMethod) {
                SetBackgroundImageMethod setBackgroundImageMethod = (SetBackgroundImageMethod) obj;
                image(setBackgroundImageMethod.image());
                if ((j & 1) == 0) {
                    forced(setBackgroundImageMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(setBackgroundImageMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetBackgroundImageMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetBackgroundImageMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(String str) {
            this.image = (String) ImmutableSetBackgroundImageMethod.requireNonNull(str, ContextMappingConstants.IMAGE);
            this.initBits &= -3;
            return this;
        }

        public ImmutableSetBackgroundImageMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetBackgroundImageMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(ContextMappingConstants.IMAGE);
            }
            return "Cannot build SetBackgroundImageMethod, some of required attributes are not set " + arrayList;
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
