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
@Generated(from = "PlayMediaMethod", generator = "Immutables")
public final class ImmutablePlayMediaMethod extends PlayMediaMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final boolean deferPlayback;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final String mediaId;
    private final Queue queue;

    @Nullable
    private final Long startOffsetMs;

    private ImmutablePlayMediaMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.mediaId = builder.mediaId;
        this.startOffsetMs = builder.startOffsetMs;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.deferPlaybackIsSet()) {
            this.initShim.deferPlayback(builder.deferPlayback);
        }
        this.forced = this.initShim.forced();
        this.deferPlayback = this.initShim.deferPlayback();
        this.initShim = null;
    }

    private ImmutablePlayMediaMethod(Queue queue, Boolean bool, String str, @Nullable Long l, boolean z) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.mediaId = str;
        this.startOffsetMs = l;
        this.deferPlayback = z;
        this.initShim = null;
    }

    @Generated(from = "PlayMediaMethod", generator = "Immutables")
    private final class InitShim {
        private boolean deferPlayback;
        private byte deferPlaybackBuildStage;
        private Boolean forced;
        private byte forcedBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.deferPlaybackBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutablePlayMediaMethod.requireNonNull(ImmutablePlayMediaMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        boolean deferPlayback() {
            byte b = this.deferPlaybackBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.deferPlaybackBuildStage = (byte) -1;
                this.deferPlayback = ImmutablePlayMediaMethod.super.deferPlayback();
                this.deferPlaybackBuildStage = (byte) 1;
            }
            return this.deferPlayback;
        }

        void deferPlayback(boolean z) {
            this.deferPlayback = z;
            this.deferPlaybackBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.deferPlaybackBuildStage == -1) {
                arrayList.add("deferPlayback");
            }
            return "Cannot build PlayMediaMethod, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("queue")
    public Queue queue() {
        return this.queue;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("forced")
    public Boolean forced() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.forced();
        }
        return this.forced;
    }

    @Override // PlaybackInterface.v1_0.PlayMediaMethod
    @JsonProperty("mediaId")
    public String mediaId() {
        return this.mediaId;
    }

    @Override // PlaybackInterface.v1_0.PlayMediaMethod
    @JsonProperty("startOffsetMs")
    @Nullable
    public Long startOffsetMs() {
        return this.startOffsetMs;
    }

    @Override // PlaybackInterface.v1_0.PlayMediaMethod
    @JsonProperty("deferPlayback")
    public boolean deferPlayback() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.deferPlayback();
        }
        return this.deferPlayback;
    }

    public final ImmutablePlayMediaMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutablePlayMediaMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.mediaId, this.startOffsetMs, this.deferPlayback);
    }

    public final ImmutablePlayMediaMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutablePlayMediaMethod(this.queue, bool2, this.mediaId, this.startOffsetMs, this.deferPlayback);
    }

    public final ImmutablePlayMediaMethod withMediaId(String str) {
        String str2 = (String) requireNonNull(str, "mediaId");
        return this.mediaId.equals(str2) ? this : new ImmutablePlayMediaMethod(this.queue, this.forced, str2, this.startOffsetMs, this.deferPlayback);
    }

    public final ImmutablePlayMediaMethod withStartOffsetMs(@Nullable Long l) {
        return equals(this.startOffsetMs, l) ? this : new ImmutablePlayMediaMethod(this.queue, this.forced, this.mediaId, l, this.deferPlayback);
    }

    public final ImmutablePlayMediaMethod withDeferPlayback(boolean z) {
        return this.deferPlayback == z ? this : new ImmutablePlayMediaMethod(this.queue, this.forced, this.mediaId, this.startOffsetMs, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlayMediaMethod) && equalTo(0, (ImmutablePlayMediaMethod) obj);
    }

    private boolean equalTo(int i, ImmutablePlayMediaMethod immutablePlayMediaMethod) {
        return this.queue.equals(immutablePlayMediaMethod.queue) && this.forced.equals(immutablePlayMediaMethod.forced) && this.mediaId.equals(immutablePlayMediaMethod.mediaId) && equals(this.startOffsetMs, immutablePlayMediaMethod.startOffsetMs) && this.deferPlayback == immutablePlayMediaMethod.deferPlayback;
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.mediaId.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.startOffsetMs);
        return iHashCode4 + (iHashCode4 << 5) + (this.deferPlayback ? 1231 : 1237);
    }

    public String toString() {
        return "PlayMediaMethod{queue=" + this.queue + ", forced=" + this.forced + ", mediaId=" + this.mediaId + ", startOffsetMs=" + this.startOffsetMs + ", deferPlayback=" + this.deferPlayback + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PlayMediaMethod", generator = "Immutables")
    static final class Json extends PlayMediaMethod {
        boolean deferPlayback;
        boolean deferPlaybackIsSet;

        @Nullable
        Boolean forced;

        @Nullable
        String mediaId;

        @Nullable
        Queue queue;

        @Nullable
        Long startOffsetMs;

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

        @JsonProperty("startOffsetMs")
        public void setStartOffsetMs(@Nullable Long l) {
            this.startOffsetMs = l;
        }

        @JsonProperty("deferPlayback")
        public void setDeferPlayback(boolean z) {
            this.deferPlayback = z;
            this.deferPlaybackIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlayMediaMethod
        public String mediaId() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlayMediaMethod
        public Long startOffsetMs() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlayMediaMethod
        public boolean deferPlayback() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePlayMediaMethod fromJson(Json json) {
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
        Long l = json.startOffsetMs;
        if (l != null) {
            builder.startOffsetMs(l);
        }
        if (json.deferPlaybackIsSet) {
            builder.deferPlayback(json.deferPlayback);
        }
        return builder.build();
    }

    public static ImmutablePlayMediaMethod copyOf(PlayMediaMethod playMediaMethod) {
        if (playMediaMethod instanceof ImmutablePlayMediaMethod) {
            return (ImmutablePlayMediaMethod) playMediaMethod;
        }
        return builder().from(playMediaMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PlayMediaMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_MEDIA_ID = 2;
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_DEFER_PLAYBACK = 1;
        private boolean deferPlayback;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private String mediaId;
        private long optBits;

        @Nullable
        private Queue queue;

        @Nullable
        private Long startOffsetMs;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(Method method) {
            ImmutablePlayMediaMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(PlayMediaMethod playMediaMethod) {
            ImmutablePlayMediaMethod.requireNonNull(playMediaMethod, "instance");
            from((Object) playMediaMethod);
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
            if (obj instanceof PlayMediaMethod) {
                PlayMediaMethod playMediaMethod = (PlayMediaMethod) obj;
                mediaId(playMediaMethod.mediaId());
                if ((j & 1) == 0) {
                    forced(playMediaMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(playMediaMethod.queue());
                }
                deferPlayback(playMediaMethod.deferPlayback());
                Long lStartOffsetMs = playMediaMethod.startOffsetMs();
                if (lStartOffsetMs != null) {
                    startOffsetMs(lStartOffsetMs);
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutablePlayMediaMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutablePlayMediaMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("mediaId")
        public final Builder mediaId(String str) {
            this.mediaId = (String) ImmutablePlayMediaMethod.requireNonNull(str, "mediaId");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("startOffsetMs")
        public final Builder startOffsetMs(@Nullable Long l) {
            this.startOffsetMs = l;
            return this;
        }

        @JsonProperty("deferPlayback")
        public final Builder deferPlayback(boolean z) {
            this.deferPlayback = z;
            this.optBits |= 1;
            return this;
        }

        public ImmutablePlayMediaMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePlayMediaMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean deferPlaybackIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("mediaId");
            }
            return "Cannot build PlayMediaMethod, some of required attributes are not set " + arrayList;
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
