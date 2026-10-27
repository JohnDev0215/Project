package VideoPlayerAuthenticationInterface.v1_0;

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
@Generated(from = "SetVideoPlayerTokenMethod", generator = "Immutables")
public final class ImmutableSetVideoPlayerTokenMethod extends SetVideoPlayerTokenMethod {
    private final Boolean forced;

    @Nullable
    private final String header;
    private final Queue queue;

    @Nullable
    private final String token;

    private ImmutableSetVideoPlayerTokenMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.token = builder.token;
        this.header = builder.header;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableSetVideoPlayerTokenMethod(Queue queue, Boolean bool, @Nullable String str, @Nullable String str2) {
        this.queue = queue;
        this.forced = bool;
        this.token = str;
        this.header = str2;
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

    @Override // VideoPlayerAuthenticationInterface.v1_0.SetVideoPlayerTokenMethod
    @JsonProperty("token")
    @Nullable
    public String token() {
        return this.token;
    }

    @Override // VideoPlayerAuthenticationInterface.v1_0.SetVideoPlayerTokenMethod
    @JsonProperty("header")
    @Nullable
    public String header() {
        return this.header;
    }

    public final ImmutableSetVideoPlayerTokenMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetVideoPlayerTokenMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.token, this.header);
    }

    public final ImmutableSetVideoPlayerTokenMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetVideoPlayerTokenMethod(this.queue, bool2, this.token, this.header);
    }

    public final ImmutableSetVideoPlayerTokenMethod withToken(@Nullable String str) {
        return equals(this.token, str) ? this : new ImmutableSetVideoPlayerTokenMethod(this.queue, this.forced, str, this.header);
    }

    public final ImmutableSetVideoPlayerTokenMethod withHeader(@Nullable String str) {
        return equals(this.header, str) ? this : new ImmutableSetVideoPlayerTokenMethod(this.queue, this.forced, this.token, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetVideoPlayerTokenMethod) && equalTo(0, (ImmutableSetVideoPlayerTokenMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetVideoPlayerTokenMethod immutableSetVideoPlayerTokenMethod) {
        return this.queue.equals(immutableSetVideoPlayerTokenMethod.queue) && this.forced.equals(immutableSetVideoPlayerTokenMethod.forced) && equals(this.token, immutableSetVideoPlayerTokenMethod.token) && equals(this.header, immutableSetVideoPlayerTokenMethod.header);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.token);
        return iHashCode3 + (iHashCode3 << 5) + hashCode(this.header);
    }

    public String toString() {
        return "SetVideoPlayerTokenMethod{queue=" + this.queue + ", forced=" + this.forced + ", token=" + this.token + ", header=" + this.header + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetVideoPlayerTokenMethod", generator = "Immutables")
    static final class Json extends SetVideoPlayerTokenMethod {

        @Nullable
        Boolean forced;

        @Nullable
        String header;

        @Nullable
        Queue queue;

        @Nullable
        String token;

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

        @JsonProperty("token")
        public void setToken(@Nullable String str) {
            this.token = str;
        }

        @JsonProperty("header")
        public void setHeader(@Nullable String str) {
            this.header = str;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // VideoPlayerAuthenticationInterface.v1_0.SetVideoPlayerTokenMethod
        public String token() {
            throw new UnsupportedOperationException();
        }

        @Override // VideoPlayerAuthenticationInterface.v1_0.SetVideoPlayerTokenMethod
        public String header() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetVideoPlayerTokenMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.token;
        if (str != null) {
            builder.token(str);
        }
        String str2 = json.header;
        if (str2 != null) {
            builder.header(str2);
        }
        return builder.build();
    }

    public static ImmutableSetVideoPlayerTokenMethod copyOf(SetVideoPlayerTokenMethod setVideoPlayerTokenMethod) {
        if (setVideoPlayerTokenMethod instanceof ImmutableSetVideoPlayerTokenMethod) {
            return (ImmutableSetVideoPlayerTokenMethod) setVideoPlayerTokenMethod;
        }
        return builder().from(setVideoPlayerTokenMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetVideoPlayerTokenMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;

        @Nullable
        private String header;
        private long initBits;

        @Nullable
        private Queue queue;

        @Nullable
        private String token;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(Method method) {
            ImmutableSetVideoPlayerTokenMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SetVideoPlayerTokenMethod setVideoPlayerTokenMethod) {
            ImmutableSetVideoPlayerTokenMethod.requireNonNull(setVideoPlayerTokenMethod, "instance");
            from((Object) setVideoPlayerTokenMethod);
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
            if (obj instanceof SetVideoPlayerTokenMethod) {
                SetVideoPlayerTokenMethod setVideoPlayerTokenMethod = (SetVideoPlayerTokenMethod) obj;
                String strHeader = setVideoPlayerTokenMethod.header();
                if (strHeader != null) {
                    header(strHeader);
                }
                if ((j & 1) == 0) {
                    forced(setVideoPlayerTokenMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(setVideoPlayerTokenMethod.queue());
                }
                String str = setVideoPlayerTokenMethod.token();
                if (str != null) {
                    token(str);
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetVideoPlayerTokenMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetVideoPlayerTokenMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("token")
        public final Builder token(@Nullable String str) {
            this.token = str;
            return this;
        }

        @JsonProperty("header")
        public final Builder header(@Nullable String str) {
            this.header = str;
            return this;
        }

        public ImmutableSetVideoPlayerTokenMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetVideoPlayerTokenMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build SetVideoPlayerTokenMethod, some of required attributes are not set " + arrayList;
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
