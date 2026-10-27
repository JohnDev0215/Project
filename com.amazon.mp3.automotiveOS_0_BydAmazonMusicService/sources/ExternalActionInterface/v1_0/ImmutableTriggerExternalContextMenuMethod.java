package ExternalActionInterface.v1_0;

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
@Generated(from = "TriggerExternalContextMenuMethod", generator = "Immutables")
public final class ImmutableTriggerExternalContextMenuMethod extends TriggerExternalContextMenuMethod {
    private final Boolean forced;

    @Nullable
    private final PlayableContent playableContent;
    private final Queue queue;

    private ImmutableTriggerExternalContextMenuMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.playableContent = builder.playableContent;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableTriggerExternalContextMenuMethod(Queue queue, Boolean bool, @Nullable PlayableContent playableContent) {
        this.queue = queue;
        this.forced = bool;
        this.playableContent = playableContent;
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

    @Override // ExternalActionInterface.v1_0.TriggerExternalContextMenuMethod
    @JsonProperty("playableContent")
    @Nullable
    public PlayableContent playableContent() {
        return this.playableContent;
    }

    public final ImmutableTriggerExternalContextMenuMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableTriggerExternalContextMenuMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.playableContent);
    }

    public final ImmutableTriggerExternalContextMenuMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableTriggerExternalContextMenuMethod(this.queue, bool2, this.playableContent);
    }

    public final ImmutableTriggerExternalContextMenuMethod withPlayableContent(@Nullable PlayableContent playableContent) {
        return this.playableContent == playableContent ? this : new ImmutableTriggerExternalContextMenuMethod(this.queue, this.forced, playableContent);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTriggerExternalContextMenuMethod) && equalTo(0, (ImmutableTriggerExternalContextMenuMethod) obj);
    }

    private boolean equalTo(int i, ImmutableTriggerExternalContextMenuMethod immutableTriggerExternalContextMenuMethod) {
        return this.queue.equals(immutableTriggerExternalContextMenuMethod.queue) && this.forced.equals(immutableTriggerExternalContextMenuMethod.forced) && equals(this.playableContent, immutableTriggerExternalContextMenuMethod.playableContent);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + hashCode(this.playableContent);
    }

    public String toString() {
        return "TriggerExternalContextMenuMethod{queue=" + this.queue + ", forced=" + this.forced + ", playableContent=" + this.playableContent + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "TriggerExternalContextMenuMethod", generator = "Immutables")
    static final class Json extends TriggerExternalContextMenuMethod {

        @Nullable
        Boolean forced;

        @Nullable
        PlayableContent playableContent;

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

        @JsonProperty("playableContent")
        public void setPlayableContent(@Nullable PlayableContent playableContent) {
            this.playableContent = playableContent;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.TriggerExternalContextMenuMethod
        public PlayableContent playableContent() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTriggerExternalContextMenuMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        PlayableContent playableContent = json.playableContent;
        if (playableContent != null) {
            builder.playableContent(playableContent);
        }
        return builder.build();
    }

    public static ImmutableTriggerExternalContextMenuMethod copyOf(TriggerExternalContextMenuMethod triggerExternalContextMenuMethod) {
        if (triggerExternalContextMenuMethod instanceof ImmutableTriggerExternalContextMenuMethod) {
            return (ImmutableTriggerExternalContextMenuMethod) triggerExternalContextMenuMethod;
        }
        return builder().from(triggerExternalContextMenuMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "TriggerExternalContextMenuMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private PlayableContent playableContent;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(Method method) {
            ImmutableTriggerExternalContextMenuMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(TriggerExternalContextMenuMethod triggerExternalContextMenuMethod) {
            ImmutableTriggerExternalContextMenuMethod.requireNonNull(triggerExternalContextMenuMethod, "instance");
            from((Object) triggerExternalContextMenuMethod);
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
            if (obj instanceof TriggerExternalContextMenuMethod) {
                TriggerExternalContextMenuMethod triggerExternalContextMenuMethod = (TriggerExternalContextMenuMethod) obj;
                PlayableContent playableContent = triggerExternalContextMenuMethod.playableContent();
                if (playableContent != null) {
                    playableContent(playableContent);
                }
                if ((j & 1) == 0) {
                    forced(triggerExternalContextMenuMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(triggerExternalContextMenuMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableTriggerExternalContextMenuMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableTriggerExternalContextMenuMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("playableContent")
        public final Builder playableContent(@Nullable PlayableContent playableContent) {
            this.playableContent = playableContent;
            return this;
        }

        public ImmutableTriggerExternalContextMenuMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableTriggerExternalContextMenuMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build TriggerExternalContextMenuMethod, some of required attributes are not set " + arrayList;
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
