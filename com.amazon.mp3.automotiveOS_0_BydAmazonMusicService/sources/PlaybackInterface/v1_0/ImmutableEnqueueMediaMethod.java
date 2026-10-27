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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "EnqueueMediaMethod", generator = "Immutables")
public final class ImmutableEnqueueMediaMethod extends EnqueueMediaMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String audioStreamFormat;
    private final String audioUri;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final String mediaId;
    private final List<Method> onEnqueuedStart;
    private final List<Method> onSkippedToNextTrack;
    private final Queue queue;

    private ImmutableEnqueueMediaMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.mediaId = builder.mediaId;
        this.audioUri = builder.audioUri;
        this.audioStreamFormat = builder.audioStreamFormat;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.onEnqueuedStartIsSet()) {
            this.initShim.onEnqueuedStart(createUnmodifiableList(true, builder.onEnqueuedStart));
        }
        if (builder.onSkippedToNextTrackIsSet()) {
            this.initShim.onSkippedToNextTrack(createUnmodifiableList(true, builder.onSkippedToNextTrack));
        }
        this.forced = this.initShim.forced();
        this.onEnqueuedStart = this.initShim.onEnqueuedStart();
        this.onSkippedToNextTrack = this.initShim.onSkippedToNextTrack();
        this.initShim = null;
    }

    private ImmutableEnqueueMediaMethod(Queue queue, Boolean bool, String str, String str2, @Nullable String str3, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.mediaId = str;
        this.audioUri = str2;
        this.audioStreamFormat = str3;
        this.onEnqueuedStart = list;
        this.onSkippedToNextTrack = list2;
        this.initShim = null;
    }

    @Generated(from = "EnqueueMediaMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private List<Method> onEnqueuedStart;
        private byte onEnqueuedStartBuildStage;
        private List<Method> onSkippedToNextTrack;
        private byte onSkippedToNextTrackBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.onEnqueuedStartBuildStage = (byte) 0;
            this.onSkippedToNextTrackBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableEnqueueMediaMethod.requireNonNull(ImmutableEnqueueMediaMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        List<Method> onEnqueuedStart() {
            byte b = this.onEnqueuedStartBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnqueuedStartBuildStage = (byte) -1;
                this.onEnqueuedStart = ImmutableEnqueueMediaMethod.createUnmodifiableList(false, ImmutableEnqueueMediaMethod.createSafeList(ImmutableEnqueueMediaMethod.super.onEnqueuedStart(), true, false));
                this.onEnqueuedStartBuildStage = (byte) 1;
            }
            return this.onEnqueuedStart;
        }

        void onEnqueuedStart(List<Method> list) {
            this.onEnqueuedStart = list;
            this.onEnqueuedStartBuildStage = (byte) 1;
        }

        List<Method> onSkippedToNextTrack() {
            byte b = this.onSkippedToNextTrackBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onSkippedToNextTrackBuildStage = (byte) -1;
                this.onSkippedToNextTrack = ImmutableEnqueueMediaMethod.createUnmodifiableList(false, ImmutableEnqueueMediaMethod.createSafeList(ImmutableEnqueueMediaMethod.super.onSkippedToNextTrack(), true, false));
                this.onSkippedToNextTrackBuildStage = (byte) 1;
            }
            return this.onSkippedToNextTrack;
        }

        void onSkippedToNextTrack(List<Method> list) {
            this.onSkippedToNextTrack = list;
            this.onSkippedToNextTrackBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.onEnqueuedStartBuildStage == -1) {
                arrayList.add("onEnqueuedStart");
            }
            if (this.onSkippedToNextTrackBuildStage == -1) {
                arrayList.add("onSkippedToNextTrack");
            }
            return "Cannot build EnqueueMediaMethod, attribute initializers form cycle " + arrayList;
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

    @Override // PlaybackInterface.v1_0.EnqueueMediaMethod
    @JsonProperty("mediaId")
    public String mediaId() {
        return this.mediaId;
    }

    @Override // PlaybackInterface.v1_0.EnqueueMediaMethod
    @JsonProperty("audioUri")
    public String audioUri() {
        return this.audioUri;
    }

    @Override // PlaybackInterface.v1_0.EnqueueMediaMethod
    @JsonProperty("audioStreamFormat")
    @Nullable
    public String audioStreamFormat() {
        return this.audioStreamFormat;
    }

    @Override // PlaybackInterface.v1_0.EnqueueMediaMethod
    @JsonProperty("onEnqueuedStart")
    public List<Method> onEnqueuedStart() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnqueuedStart();
        }
        return this.onEnqueuedStart;
    }

    @Override // PlaybackInterface.v1_0.EnqueueMediaMethod
    @JsonProperty("onSkippedToNextTrack")
    public List<Method> onSkippedToNextTrack() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onSkippedToNextTrack();
        }
        return this.onSkippedToNextTrack;
    }

    public final ImmutableEnqueueMediaMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableEnqueueMediaMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.mediaId, this.audioUri, this.audioStreamFormat, this.onEnqueuedStart, this.onSkippedToNextTrack);
    }

    public final ImmutableEnqueueMediaMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableEnqueueMediaMethod(this.queue, bool2, this.mediaId, this.audioUri, this.audioStreamFormat, this.onEnqueuedStart, this.onSkippedToNextTrack);
    }

    public final ImmutableEnqueueMediaMethod withMediaId(String str) {
        String str2 = (String) requireNonNull(str, "mediaId");
        return this.mediaId.equals(str2) ? this : new ImmutableEnqueueMediaMethod(this.queue, this.forced, str2, this.audioUri, this.audioStreamFormat, this.onEnqueuedStart, this.onSkippedToNextTrack);
    }

    public final ImmutableEnqueueMediaMethod withAudioUri(String str) {
        String str2 = (String) requireNonNull(str, "audioUri");
        return this.audioUri.equals(str2) ? this : new ImmutableEnqueueMediaMethod(this.queue, this.forced, this.mediaId, str2, this.audioStreamFormat, this.onEnqueuedStart, this.onSkippedToNextTrack);
    }

    public final ImmutableEnqueueMediaMethod withAudioStreamFormat(@Nullable String str) {
        return equals(this.audioStreamFormat, str) ? this : new ImmutableEnqueueMediaMethod(this.queue, this.forced, this.mediaId, this.audioUri, str, this.onEnqueuedStart, this.onSkippedToNextTrack);
    }

    public final ImmutableEnqueueMediaMethod withOnEnqueuedStart(Method... methodArr) {
        return new ImmutableEnqueueMediaMethod(this.queue, this.forced, this.mediaId, this.audioUri, this.audioStreamFormat, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onSkippedToNextTrack);
    }

    public final ImmutableEnqueueMediaMethod withOnEnqueuedStart(Iterable<? extends Method> iterable) {
        if (this.onEnqueuedStart == iterable) {
            return this;
        }
        return new ImmutableEnqueueMediaMethod(this.queue, this.forced, this.mediaId, this.audioUri, this.audioStreamFormat, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onSkippedToNextTrack);
    }

    public final ImmutableEnqueueMediaMethod withOnSkippedToNextTrack(Method... methodArr) {
        return new ImmutableEnqueueMediaMethod(this.queue, this.forced, this.mediaId, this.audioUri, this.audioStreamFormat, this.onEnqueuedStart, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableEnqueueMediaMethod withOnSkippedToNextTrack(Iterable<? extends Method> iterable) {
        if (this.onSkippedToNextTrack == iterable) {
            return this;
        }
        return new ImmutableEnqueueMediaMethod(this.queue, this.forced, this.mediaId, this.audioUri, this.audioStreamFormat, this.onEnqueuedStart, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableEnqueueMediaMethod) && equalTo(0, (ImmutableEnqueueMediaMethod) obj);
    }

    private boolean equalTo(int i, ImmutableEnqueueMediaMethod immutableEnqueueMediaMethod) {
        return this.queue.equals(immutableEnqueueMediaMethod.queue) && this.forced.equals(immutableEnqueueMediaMethod.forced) && this.mediaId.equals(immutableEnqueueMediaMethod.mediaId) && this.audioUri.equals(immutableEnqueueMediaMethod.audioUri) && equals(this.audioStreamFormat, immutableEnqueueMediaMethod.audioStreamFormat) && this.onEnqueuedStart.equals(immutableEnqueueMediaMethod.onEnqueuedStart) && this.onSkippedToNextTrack.equals(immutableEnqueueMediaMethod.onSkippedToNextTrack);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.mediaId.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.audioUri.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.audioStreamFormat);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.onEnqueuedStart.hashCode();
        return iHashCode6 + (iHashCode6 << 5) + this.onSkippedToNextTrack.hashCode();
    }

    public String toString() {
        return "EnqueueMediaMethod{queue=" + this.queue + ", forced=" + this.forced + ", mediaId=" + this.mediaId + ", audioUri=" + this.audioUri + ", audioStreamFormat=" + this.audioStreamFormat + ", onEnqueuedStart=" + this.onEnqueuedStart + ", onSkippedToNextTrack=" + this.onSkippedToNextTrack + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "EnqueueMediaMethod", generator = "Immutables")
    static final class Json extends EnqueueMediaMethod {

        @Nullable
        String audioStreamFormat;

        @Nullable
        String audioUri;

        @Nullable
        Boolean forced;

        @Nullable
        String mediaId;
        boolean onEnqueuedStartIsSet;
        boolean onSkippedToNextTrackIsSet;

        @Nullable
        Queue queue;

        @Nullable
        List<Method> onEnqueuedStart = Collections.emptyList();

        @Nullable
        List<Method> onSkippedToNextTrack = Collections.emptyList();

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
        public void setAudioUri(String str) {
            this.audioUri = str;
        }

        @JsonProperty("audioStreamFormat")
        public void setAudioStreamFormat(@Nullable String str) {
            this.audioStreamFormat = str;
        }

        @JsonProperty("onEnqueuedStart")
        public void setOnEnqueuedStart(List<Method> list) {
            this.onEnqueuedStart = list;
            this.onEnqueuedStartIsSet = true;
        }

        @JsonProperty("onSkippedToNextTrack")
        public void setOnSkippedToNextTrack(List<Method> list) {
            this.onSkippedToNextTrack = list;
            this.onSkippedToNextTrackIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.EnqueueMediaMethod
        public String mediaId() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.EnqueueMediaMethod
        public String audioUri() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.EnqueueMediaMethod
        public String audioStreamFormat() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.EnqueueMediaMethod
        public List<Method> onEnqueuedStart() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.EnqueueMediaMethod
        public List<Method> onSkippedToNextTrack() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableEnqueueMediaMethod fromJson(Json json) {
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
        if (json.onEnqueuedStartIsSet) {
            builder.addAllOnEnqueuedStart(json.onEnqueuedStart);
        }
        if (json.onSkippedToNextTrackIsSet) {
            builder.addAllOnSkippedToNextTrack(json.onSkippedToNextTrack);
        }
        return builder.build();
    }

    public static ImmutableEnqueueMediaMethod copyOf(EnqueueMediaMethod enqueueMediaMethod) {
        if (enqueueMediaMethod instanceof ImmutableEnqueueMediaMethod) {
            return (ImmutableEnqueueMediaMethod) enqueueMediaMethod;
        }
        return builder().from(enqueueMediaMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "EnqueueMediaMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_AUDIO_URI = 4;
        private static final long INIT_BIT_MEDIA_ID = 2;
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_ON_ENQUEUED_START = 1;
        private static final long OPT_BIT_ON_SKIPPED_TO_NEXT_TRACK = 2;

        @Nullable
        private String audioStreamFormat;

        @Nullable
        private String audioUri;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private String mediaId;
        private List<Method> onEnqueuedStart;
        private List<Method> onSkippedToNextTrack;
        private long optBits;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 7L;
            this.onEnqueuedStart = new ArrayList();
            this.onSkippedToNextTrack = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableEnqueueMediaMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(EnqueueMediaMethod enqueueMediaMethod) {
            ImmutableEnqueueMediaMethod.requireNonNull(enqueueMediaMethod, "instance");
            from((Object) enqueueMediaMethod);
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
            if (obj instanceof EnqueueMediaMethod) {
                EnqueueMediaMethod enqueueMediaMethod = (EnqueueMediaMethod) obj;
                addAllOnEnqueuedStart(enqueueMediaMethod.onEnqueuedStart());
                if ((j & 1) == 0) {
                    forced(enqueueMediaMethod.forced());
                    j |= 1;
                }
                addAllOnSkippedToNextTrack(enqueueMediaMethod.onSkippedToNextTrack());
                mediaId(enqueueMediaMethod.mediaId());
                audioUri(enqueueMediaMethod.audioUri());
                String strAudioStreamFormat = enqueueMediaMethod.audioStreamFormat();
                if (strAudioStreamFormat != null) {
                    audioStreamFormat(strAudioStreamFormat);
                }
                if ((j & 2) == 0) {
                    queue(enqueueMediaMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableEnqueueMediaMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableEnqueueMediaMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("mediaId")
        public final Builder mediaId(String str) {
            this.mediaId = (String) ImmutableEnqueueMediaMethod.requireNonNull(str, "mediaId");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("audioUri")
        public final Builder audioUri(String str) {
            this.audioUri = (String) ImmutableEnqueueMediaMethod.requireNonNull(str, "audioUri");
            this.initBits &= -5;
            return this;
        }

        @JsonProperty("audioStreamFormat")
        public final Builder audioStreamFormat(@Nullable String str) {
            this.audioStreamFormat = str;
            return this;
        }

        public final Builder addOnEnqueuedStart(Method method) {
            this.onEnqueuedStart.add((Method) ImmutableEnqueueMediaMethod.requireNonNull(method, "onEnqueuedStart element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnqueuedStart(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnqueuedStart.add((Method) ImmutableEnqueueMediaMethod.requireNonNull(method, "onEnqueuedStart element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnqueuedStart")
        public final Builder onEnqueuedStart(Iterable<? extends Method> iterable) {
            this.onEnqueuedStart.clear();
            return addAllOnEnqueuedStart(iterable);
        }

        public final Builder addAllOnEnqueuedStart(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnqueuedStart.add((Method) ImmutableEnqueueMediaMethod.requireNonNull(it.next(), "onEnqueuedStart element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnSkippedToNextTrack(Method method) {
            this.onSkippedToNextTrack.add((Method) ImmutableEnqueueMediaMethod.requireNonNull(method, "onSkippedToNextTrack element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnSkippedToNextTrack(Method... methodArr) {
            for (Method method : methodArr) {
                this.onSkippedToNextTrack.add((Method) ImmutableEnqueueMediaMethod.requireNonNull(method, "onSkippedToNextTrack element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onSkippedToNextTrack")
        public final Builder onSkippedToNextTrack(Iterable<? extends Method> iterable) {
            this.onSkippedToNextTrack.clear();
            return addAllOnSkippedToNextTrack(iterable);
        }

        public final Builder addAllOnSkippedToNextTrack(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onSkippedToNextTrack.add((Method) ImmutableEnqueueMediaMethod.requireNonNull(it.next(), "onSkippedToNextTrack element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableEnqueueMediaMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableEnqueueMediaMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnqueuedStartIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onSkippedToNextTrackIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("mediaId");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("audioUri");
            }
            return "Cannot build EnqueueMediaMethod, some of required attributes are not set " + arrayList;
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

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
        if ((iterable instanceof Collection) && ((Collection) iterable).size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!z2 || t != null) {
                if (z) {
                    requireNonNull(t, "element");
                }
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
        int size = list.size();
        if (size == 0) {
            return Collections.emptyList();
        }
        if (size == 1) {
            return Collections.singletonList(list.get(0));
        }
        if (z) {
            return Collections.unmodifiableList(new ArrayList(list));
        }
        if (list instanceof ArrayList) {
            ((ArrayList) list).trimToSize();
        }
        return Collections.unmodifiableList(list);
    }
}
