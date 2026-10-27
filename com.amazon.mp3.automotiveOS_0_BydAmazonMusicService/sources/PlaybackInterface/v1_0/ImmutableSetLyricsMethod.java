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
@Generated(from = "SetLyricsMethod", generator = "Immutables")
public final class ImmutableSetLyricsMethod extends SetLyricsMethod {
    private final Boolean forced;
    private final LyricsElement lyrics;
    private final Queue queue;

    private ImmutableSetLyricsMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.lyrics = builder.lyrics;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableSetLyricsMethod(Queue queue, Boolean bool, LyricsElement lyricsElement) {
        this.queue = queue;
        this.forced = bool;
        this.lyrics = lyricsElement;
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

    @Override // PlaybackInterface.v1_0.SetLyricsMethod
    @JsonProperty("lyrics")
    public LyricsElement lyrics() {
        return this.lyrics;
    }

    public final ImmutableSetLyricsMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetLyricsMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.lyrics);
    }

    public final ImmutableSetLyricsMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetLyricsMethod(this.queue, bool2, this.lyrics);
    }

    public final ImmutableSetLyricsMethod withLyrics(LyricsElement lyricsElement) {
        if (this.lyrics == lyricsElement) {
            return this;
        }
        return new ImmutableSetLyricsMethod(this.queue, this.forced, (LyricsElement) requireNonNull(lyricsElement, "lyrics"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetLyricsMethod) && equalTo(0, (ImmutableSetLyricsMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetLyricsMethod immutableSetLyricsMethod) {
        return this.queue.equals(immutableSetLyricsMethod.queue) && this.forced.equals(immutableSetLyricsMethod.forced) && this.lyrics.equals(immutableSetLyricsMethod.lyrics);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.lyrics.hashCode();
    }

    public String toString() {
        return "SetLyricsMethod{queue=" + this.queue + ", forced=" + this.forced + ", lyrics=" + this.lyrics + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetLyricsMethod", generator = "Immutables")
    static final class Json extends SetLyricsMethod {

        @Nullable
        Boolean forced;

        @Nullable
        LyricsElement lyrics;

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

        @JsonProperty("lyrics")
        public void setLyrics(LyricsElement lyricsElement) {
            this.lyrics = lyricsElement;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetLyricsMethod
        public LyricsElement lyrics() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetLyricsMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        LyricsElement lyricsElement = json.lyrics;
        if (lyricsElement != null) {
            builder.lyrics(lyricsElement);
        }
        return builder.build();
    }

    public static ImmutableSetLyricsMethod copyOf(SetLyricsMethod setLyricsMethod) {
        if (setLyricsMethod instanceof ImmutableSetLyricsMethod) {
            return (ImmutableSetLyricsMethod) setLyricsMethod;
        }
        return builder().from(setLyricsMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetLyricsMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_LYRICS = 2;
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private LyricsElement lyrics;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(Method method) {
            ImmutableSetLyricsMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SetLyricsMethod setLyricsMethod) {
            ImmutableSetLyricsMethod.requireNonNull(setLyricsMethod, "instance");
            from((Object) setLyricsMethod);
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
            if (obj instanceof SetLyricsMethod) {
                SetLyricsMethod setLyricsMethod = (SetLyricsMethod) obj;
                lyrics(setLyricsMethod.lyrics());
                if ((j & 1) == 0) {
                    forced(setLyricsMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(setLyricsMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetLyricsMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetLyricsMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("lyrics")
        public final Builder lyrics(LyricsElement lyricsElement) {
            this.lyrics = (LyricsElement) ImmutableSetLyricsMethod.requireNonNull(lyricsElement, "lyrics");
            this.initBits &= -3;
            return this;
        }

        public ImmutableSetLyricsMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetLyricsMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("lyrics");
            }
            return "Cannot build SetLyricsMethod, some of required attributes are not set " + arrayList;
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
