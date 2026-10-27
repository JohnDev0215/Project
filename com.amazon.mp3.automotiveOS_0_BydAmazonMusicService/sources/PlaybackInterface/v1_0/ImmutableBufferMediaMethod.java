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
@Generated(from = "BufferMediaMethod", generator = "Immutables")
public final class ImmutableBufferMediaMethod extends BufferMediaMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String audioStreamFormat;

    @Nullable
    private final String audioUri;
    private final boolean deferPlayback;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final String mediaId;
    private final Queue queue;

    @Nullable
    private final String videoStreamFormat;

    @Nullable
    private final String videoUri;

    private ImmutableBufferMediaMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.mediaId = builder.mediaId;
        this.audioUri = builder.audioUri;
        this.audioStreamFormat = builder.audioStreamFormat;
        this.videoUri = builder.videoUri;
        this.videoStreamFormat = builder.videoStreamFormat;
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

    private ImmutableBufferMediaMethod(Queue queue, Boolean bool, String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, boolean z) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.mediaId = str;
        this.audioUri = str2;
        this.audioStreamFormat = str3;
        this.videoUri = str4;
        this.videoStreamFormat = str5;
        this.deferPlayback = z;
        this.initShim = null;
    }

    @Generated(from = "BufferMediaMethod", generator = "Immutables")
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
                this.forced = (Boolean) ImmutableBufferMediaMethod.requireNonNull(ImmutableBufferMediaMethod.super.forced(), "forced");
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
                this.deferPlayback = ImmutableBufferMediaMethod.super.deferPlayback();
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
            return "Cannot build BufferMediaMethod, attribute initializers form cycle " + arrayList;
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

    @Override // PlaybackInterface.v1_0.BufferMediaMethod
    @JsonProperty("mediaId")
    public String mediaId() {
        return this.mediaId;
    }

    @Override // PlaybackInterface.v1_0.BufferMediaMethod
    @JsonProperty("audioUri")
    @Nullable
    public String audioUri() {
        return this.audioUri;
    }

    @Override // PlaybackInterface.v1_0.BufferMediaMethod
    @JsonProperty("audioStreamFormat")
    @Nullable
    public String audioStreamFormat() {
        return this.audioStreamFormat;
    }

    @Override // PlaybackInterface.v1_0.BufferMediaMethod
    @JsonProperty("videoUri")
    @Nullable
    public String videoUri() {
        return this.videoUri;
    }

    @Override // PlaybackInterface.v1_0.BufferMediaMethod
    @JsonProperty("videoStreamFormat")
    @Nullable
    public String videoStreamFormat() {
        return this.videoStreamFormat;
    }

    @Override // PlaybackInterface.v1_0.BufferMediaMethod
    @JsonProperty("deferPlayback")
    public boolean deferPlayback() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.deferPlayback();
        }
        return this.deferPlayback;
    }

    public final ImmutableBufferMediaMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableBufferMediaMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.mediaId, this.audioUri, this.audioStreamFormat, this.videoUri, this.videoStreamFormat, this.deferPlayback);
    }

    public final ImmutableBufferMediaMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableBufferMediaMethod(this.queue, bool2, this.mediaId, this.audioUri, this.audioStreamFormat, this.videoUri, this.videoStreamFormat, this.deferPlayback);
    }

    public final ImmutableBufferMediaMethod withMediaId(String str) {
        String str2 = (String) requireNonNull(str, "mediaId");
        return this.mediaId.equals(str2) ? this : new ImmutableBufferMediaMethod(this.queue, this.forced, str2, this.audioUri, this.audioStreamFormat, this.videoUri, this.videoStreamFormat, this.deferPlayback);
    }

    public final ImmutableBufferMediaMethod withAudioUri(@Nullable String str) {
        return equals(this.audioUri, str) ? this : new ImmutableBufferMediaMethod(this.queue, this.forced, this.mediaId, str, this.audioStreamFormat, this.videoUri, this.videoStreamFormat, this.deferPlayback);
    }

    public final ImmutableBufferMediaMethod withAudioStreamFormat(@Nullable String str) {
        return equals(this.audioStreamFormat, str) ? this : new ImmutableBufferMediaMethod(this.queue, this.forced, this.mediaId, this.audioUri, str, this.videoUri, this.videoStreamFormat, this.deferPlayback);
    }

    public final ImmutableBufferMediaMethod withVideoUri(@Nullable String str) {
        return equals(this.videoUri, str) ? this : new ImmutableBufferMediaMethod(this.queue, this.forced, this.mediaId, this.audioUri, this.audioStreamFormat, str, this.videoStreamFormat, this.deferPlayback);
    }

    public final ImmutableBufferMediaMethod withVideoStreamFormat(@Nullable String str) {
        return equals(this.videoStreamFormat, str) ? this : new ImmutableBufferMediaMethod(this.queue, this.forced, this.mediaId, this.audioUri, this.audioStreamFormat, this.videoUri, str, this.deferPlayback);
    }

    public final ImmutableBufferMediaMethod withDeferPlayback(boolean z) {
        return this.deferPlayback == z ? this : new ImmutableBufferMediaMethod(this.queue, this.forced, this.mediaId, this.audioUri, this.audioStreamFormat, this.videoUri, this.videoStreamFormat, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableBufferMediaMethod) && equalTo(0, (ImmutableBufferMediaMethod) obj);
    }

    private boolean equalTo(int i, ImmutableBufferMediaMethod immutableBufferMediaMethod) {
        return this.queue.equals(immutableBufferMediaMethod.queue) && this.forced.equals(immutableBufferMediaMethod.forced) && this.mediaId.equals(immutableBufferMediaMethod.mediaId) && equals(this.audioUri, immutableBufferMediaMethod.audioUri) && equals(this.audioStreamFormat, immutableBufferMediaMethod.audioStreamFormat) && equals(this.videoUri, immutableBufferMediaMethod.videoUri) && equals(this.videoStreamFormat, immutableBufferMediaMethod.videoStreamFormat) && this.deferPlayback == immutableBufferMediaMethod.deferPlayback;
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.mediaId.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.audioUri);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.audioStreamFormat);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.videoUri);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.videoStreamFormat);
        return iHashCode7 + (iHashCode7 << 5) + (this.deferPlayback ? 1231 : 1237);
    }

    public String toString() {
        return "BufferMediaMethod{queue=" + this.queue + ", forced=" + this.forced + ", mediaId=" + this.mediaId + ", audioUri=" + this.audioUri + ", audioStreamFormat=" + this.audioStreamFormat + ", videoUri=" + this.videoUri + ", videoStreamFormat=" + this.videoStreamFormat + ", deferPlayback=" + this.deferPlayback + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "BufferMediaMethod", generator = "Immutables")
    static final class Json extends BufferMediaMethod {

        @Nullable
        String audioStreamFormat;

        @Nullable
        String audioUri;
        boolean deferPlayback;
        boolean deferPlaybackIsSet;

        @Nullable
        Boolean forced;

        @Nullable
        String mediaId;

        @Nullable
        Queue queue;

        @Nullable
        String videoStreamFormat;

        @Nullable
        String videoUri;

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

        @JsonProperty("audioUri")
        public void setAudioUri(@Nullable String str) {
            this.audioUri = str;
        }

        @JsonProperty("audioStreamFormat")
        public void setAudioStreamFormat(@Nullable String str) {
            this.audioStreamFormat = str;
        }

        @JsonProperty("videoUri")
        public void setVideoUri(@Nullable String str) {
            this.videoUri = str;
        }

        @JsonProperty("videoStreamFormat")
        public void setVideoStreamFormat(@Nullable String str) {
            this.videoStreamFormat = str;
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

        @Override // PlaybackInterface.v1_0.BufferMediaMethod
        public String mediaId() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.BufferMediaMethod
        public String audioUri() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.BufferMediaMethod
        public String audioStreamFormat() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.BufferMediaMethod
        public String videoUri() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.BufferMediaMethod
        public String videoStreamFormat() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.BufferMediaMethod
        public boolean deferPlayback() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableBufferMediaMethod fromJson(Json json) {
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
        String str2 = json.audioUri;
        if (str2 != null) {
            builder.audioUri(str2);
        }
        String str3 = json.audioStreamFormat;
        if (str3 != null) {
            builder.audioStreamFormat(str3);
        }
        String str4 = json.videoUri;
        if (str4 != null) {
            builder.videoUri(str4);
        }
        String str5 = json.videoStreamFormat;
        if (str5 != null) {
            builder.videoStreamFormat(str5);
        }
        if (json.deferPlaybackIsSet) {
            builder.deferPlayback(json.deferPlayback);
        }
        return builder.build();
    }

    public static ImmutableBufferMediaMethod copyOf(BufferMediaMethod bufferMediaMethod) {
        if (bufferMediaMethod instanceof ImmutableBufferMediaMethod) {
            return (ImmutableBufferMediaMethod) bufferMediaMethod;
        }
        return builder().from(bufferMediaMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "BufferMediaMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_MEDIA_ID = 2;
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_DEFER_PLAYBACK = 1;

        @Nullable
        private String audioStreamFormat;

        @Nullable
        private String audioUri;
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
        private String videoStreamFormat;

        @Nullable
        private String videoUri;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(Method method) {
            ImmutableBufferMediaMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(BufferMediaMethod bufferMediaMethod) {
            ImmutableBufferMediaMethod.requireNonNull(bufferMediaMethod, "instance");
            from((Object) bufferMediaMethod);
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
            if (obj instanceof BufferMediaMethod) {
                BufferMediaMethod bufferMediaMethod = (BufferMediaMethod) obj;
                String strVideoStreamFormat = bufferMediaMethod.videoStreamFormat();
                if (strVideoStreamFormat != null) {
                    videoStreamFormat(strVideoStreamFormat);
                }
                String strVideoUri = bufferMediaMethod.videoUri();
                if (strVideoUri != null) {
                    videoUri(strVideoUri);
                }
                if ((j & 1) == 0) {
                    forced(bufferMediaMethod.forced());
                    j |= 1;
                }
                mediaId(bufferMediaMethod.mediaId());
                String strAudioUri = bufferMediaMethod.audioUri();
                if (strAudioUri != null) {
                    audioUri(strAudioUri);
                }
                String strAudioStreamFormat = bufferMediaMethod.audioStreamFormat();
                if (strAudioStreamFormat != null) {
                    audioStreamFormat(strAudioStreamFormat);
                }
                if ((j & 2) == 0) {
                    queue(bufferMediaMethod.queue());
                }
                deferPlayback(bufferMediaMethod.deferPlayback());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableBufferMediaMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableBufferMediaMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("mediaId")
        public final Builder mediaId(String str) {
            this.mediaId = (String) ImmutableBufferMediaMethod.requireNonNull(str, "mediaId");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("audioUri")
        public final Builder audioUri(@Nullable String str) {
            this.audioUri = str;
            return this;
        }

        @JsonProperty("audioStreamFormat")
        public final Builder audioStreamFormat(@Nullable String str) {
            this.audioStreamFormat = str;
            return this;
        }

        @JsonProperty("videoUri")
        public final Builder videoUri(@Nullable String str) {
            this.videoUri = str;
            return this;
        }

        @JsonProperty("videoStreamFormat")
        public final Builder videoStreamFormat(@Nullable String str) {
            this.videoStreamFormat = str;
            return this;
        }

        @JsonProperty("deferPlayback")
        public final Builder deferPlayback(boolean z) {
            this.deferPlayback = z;
            this.optBits |= 1;
            return this;
        }

        public ImmutableBufferMediaMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableBufferMediaMethod(this);
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
            return "Cannot build BufferMediaMethod, some of required attributes are not set " + arrayList;
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
