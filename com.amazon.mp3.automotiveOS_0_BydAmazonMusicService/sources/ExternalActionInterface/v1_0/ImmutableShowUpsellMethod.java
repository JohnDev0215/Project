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
@Generated(from = "ShowUpsellMethod", generator = "Immutables")
public final class ImmutableShowUpsellMethod extends ShowUpsellMethod {
    private final Boolean forced;
    private final String name;
    private final PlayableContent playableContent;
    private final Queue queue;

    private ImmutableShowUpsellMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.playableContent = builder.playableContent;
        this.name = builder.name;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableShowUpsellMethod(Queue queue, Boolean bool, PlayableContent playableContent, String str) {
        this.queue = queue;
        this.forced = bool;
        this.playableContent = playableContent;
        this.name = str;
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

    @Override // ExternalActionInterface.v1_0.ShowUpsellMethod
    @JsonProperty("playableContent")
    public PlayableContent playableContent() {
        return this.playableContent;
    }

    @Override // ExternalActionInterface.v1_0.ShowUpsellMethod
    @JsonProperty("name")
    public String name() {
        return this.name;
    }

    public final ImmutableShowUpsellMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableShowUpsellMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.playableContent, this.name);
    }

    public final ImmutableShowUpsellMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableShowUpsellMethod(this.queue, bool2, this.playableContent, this.name);
    }

    public final ImmutableShowUpsellMethod withPlayableContent(PlayableContent playableContent) {
        if (this.playableContent == playableContent) {
            return this;
        }
        return new ImmutableShowUpsellMethod(this.queue, this.forced, (PlayableContent) requireNonNull(playableContent, "playableContent"), this.name);
    }

    public final ImmutableShowUpsellMethod withName(String str) {
        String str2 = (String) requireNonNull(str, "name");
        return this.name.equals(str2) ? this : new ImmutableShowUpsellMethod(this.queue, this.forced, this.playableContent, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableShowUpsellMethod) && equalTo(0, (ImmutableShowUpsellMethod) obj);
    }

    private boolean equalTo(int i, ImmutableShowUpsellMethod immutableShowUpsellMethod) {
        return this.queue.equals(immutableShowUpsellMethod.queue) && this.forced.equals(immutableShowUpsellMethod.forced) && this.playableContent.equals(immutableShowUpsellMethod.playableContent) && this.name.equals(immutableShowUpsellMethod.name);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.playableContent.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.name.hashCode();
    }

    public String toString() {
        return "ShowUpsellMethod{queue=" + this.queue + ", forced=" + this.forced + ", playableContent=" + this.playableContent + ", name=" + this.name + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ShowUpsellMethod", generator = "Immutables")
    static final class Json extends ShowUpsellMethod {

        @Nullable
        Boolean forced;

        @Nullable
        String name;

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
        public void setPlayableContent(PlayableContent playableContent) {
            this.playableContent = playableContent;
        }

        @JsonProperty("name")
        public void setName(String str) {
            this.name = str;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.ShowUpsellMethod
        public PlayableContent playableContent() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.ShowUpsellMethod
        public String name() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableShowUpsellMethod fromJson(Json json) {
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
        String str = json.name;
        if (str != null) {
            builder.name(str);
        }
        return builder.build();
    }

    public static ImmutableShowUpsellMethod copyOf(ShowUpsellMethod showUpsellMethod) {
        if (showUpsellMethod instanceof ImmutableShowUpsellMethod) {
            return (ImmutableShowUpsellMethod) showUpsellMethod;
        }
        return builder().from(showUpsellMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ShowUpsellMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_NAME = 4;
        private static final long INIT_BIT_PLAYABLE_CONTENT = 2;
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private String name;

        @Nullable
        private PlayableContent playableContent;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 7L;
        }

        public final Builder from(Method method) {
            ImmutableShowUpsellMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(ShowUpsellMethod showUpsellMethod) {
            ImmutableShowUpsellMethod.requireNonNull(showUpsellMethod, "instance");
            from((Object) showUpsellMethod);
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
            if (obj instanceof ShowUpsellMethod) {
                ShowUpsellMethod showUpsellMethod = (ShowUpsellMethod) obj;
                name(showUpsellMethod.name());
                playableContent(showUpsellMethod.playableContent());
                if ((j & 1) == 0) {
                    forced(showUpsellMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(showUpsellMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableShowUpsellMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableShowUpsellMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("playableContent")
        public final Builder playableContent(PlayableContent playableContent) {
            this.playableContent = (PlayableContent) ImmutableShowUpsellMethod.requireNonNull(playableContent, "playableContent");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("name")
        public final Builder name(String str) {
            this.name = (String) ImmutableShowUpsellMethod.requireNonNull(str, "name");
            this.initBits &= -5;
            return this;
        }

        public ImmutableShowUpsellMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableShowUpsellMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("playableContent");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("name");
            }
            return "Cannot build ShowUpsellMethod, some of required attributes are not set " + arrayList;
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
