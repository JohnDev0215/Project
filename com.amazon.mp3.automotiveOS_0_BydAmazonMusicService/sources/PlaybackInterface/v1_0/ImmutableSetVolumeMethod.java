package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
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
@Generated(from = "SetVolumeMethod", generator = "Immutables")
public final class ImmutableSetVolumeMethod extends SetVolumeMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final Queue queue;
    private final double volume;

    private ImmutableSetVolumeMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.volumeIsSet()) {
            this.initShim.volume(builder.volume);
        }
        this.forced = this.initShim.forced();
        this.volume = this.initShim.volume();
        this.initShim = null;
    }

    private ImmutableSetVolumeMethod(Queue queue, Boolean bool, double d) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.volume = d;
        this.initShim = null;
    }

    @Generated(from = "SetVolumeMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private double volume;
        private byte volumeBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.volumeBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableSetVolumeMethod.requireNonNull(ImmutableSetVolumeMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        double volume() {
            byte b = this.volumeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.volumeBuildStage = (byte) -1;
                this.volume = ImmutableSetVolumeMethod.super.volume();
                this.volumeBuildStage = (byte) 1;
            }
            return this.volume;
        }

        void volume(double d) {
            this.volume = d;
            this.volumeBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.volumeBuildStage == -1) {
                arrayList.add(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME);
            }
            return "Cannot build SetVolumeMethod, attribute initializers form cycle " + arrayList;
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

    @Override // PlaybackInterface.v1_0.SetVolumeMethod
    @JsonProperty(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME)
    public double volume() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.volume();
        }
        return this.volume;
    }

    public final ImmutableSetVolumeMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetVolumeMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.volume);
    }

    public final ImmutableSetVolumeMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetVolumeMethod(this.queue, bool2, this.volume);
    }

    public final ImmutableSetVolumeMethod withVolume(double d) {
        return Double.doubleToLongBits(this.volume) == Double.doubleToLongBits(d) ? this : new ImmutableSetVolumeMethod(this.queue, this.forced, d);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetVolumeMethod) && equalTo(0, (ImmutableSetVolumeMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetVolumeMethod immutableSetVolumeMethod) {
        return this.queue.equals(immutableSetVolumeMethod.queue) && this.forced.equals(immutableSetVolumeMethod.forced) && Double.doubleToLongBits(this.volume) == Double.doubleToLongBits(immutableSetVolumeMethod.volume);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + Double.valueOf(this.volume).hashCode();
    }

    public String toString() {
        return "SetVolumeMethod{queue=" + this.queue + ", forced=" + this.forced + ", volume=" + this.volume + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetVolumeMethod", generator = "Immutables")
    static final class Json extends SetVolumeMethod {

        @Nullable
        Boolean forced;

        @Nullable
        Queue queue;
        double volume;
        boolean volumeIsSet;

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

        @JsonProperty(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME)
        public void setVolume(double d) {
            this.volume = d;
            this.volumeIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetVolumeMethod
        public double volume() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetVolumeMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        if (json.volumeIsSet) {
            builder.volume(json.volume);
        }
        return builder.build();
    }

    public static ImmutableSetVolumeMethod copyOf(SetVolumeMethod setVolumeMethod) {
        if (setVolumeMethod instanceof ImmutableSetVolumeMethod) {
            return (ImmutableSetVolumeMethod) setVolumeMethod;
        }
        return builder().from(setVolumeMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetVolumeMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_VOLUME = 1;

        @Nullable
        private Boolean forced;
        private long initBits;
        private long optBits;

        @Nullable
        private Queue queue;
        private double volume;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(Method method) {
            ImmutableSetVolumeMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SetVolumeMethod setVolumeMethod) {
            ImmutableSetVolumeMethod.requireNonNull(setVolumeMethod, "instance");
            from((Object) setVolumeMethod);
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
            if (obj instanceof SetVolumeMethod) {
                SetVolumeMethod setVolumeMethod = (SetVolumeMethod) obj;
                volume(setVolumeMethod.volume());
                if ((j & 1) == 0) {
                    forced(setVolumeMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(setVolumeMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetVolumeMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetVolumeMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME)
        public final Builder volume(double d) {
            this.volume = d;
            this.optBits |= 1;
            return this;
        }

        public ImmutableSetVolumeMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetVolumeMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean volumeIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build SetVolumeMethod, some of required attributes are not set " + arrayList;
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
