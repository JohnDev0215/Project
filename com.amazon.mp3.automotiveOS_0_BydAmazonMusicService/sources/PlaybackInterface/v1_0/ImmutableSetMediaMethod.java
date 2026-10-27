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
@Generated(from = "SetMediaMethod", generator = "Immutables")
public final class ImmutableSetMediaMethod extends SetMediaMethod {
    private final MediaActionsElement actions;
    private final MediaEventsElement events;
    private final Boolean forced;
    private final MediaMetadataElement metadata;

    @Nullable
    private final MediaOptionsElement options;
    private final Queue queue;

    @Nullable
    private final MediaResourcesElement resources;
    private final MediaControlStatesElement states;

    private ImmutableSetMediaMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.metadata = builder.metadata;
        this.actions = builder.actions;
        this.events = builder.events;
        this.states = builder.states;
        this.resources = builder.resources;
        this.options = builder.options;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableSetMediaMethod(Queue queue, Boolean bool, MediaMetadataElement mediaMetadataElement, MediaActionsElement mediaActionsElement, MediaEventsElement mediaEventsElement, MediaControlStatesElement mediaControlStatesElement, @Nullable MediaResourcesElement mediaResourcesElement, @Nullable MediaOptionsElement mediaOptionsElement) {
        this.queue = queue;
        this.forced = bool;
        this.metadata = mediaMetadataElement;
        this.actions = mediaActionsElement;
        this.events = mediaEventsElement;
        this.states = mediaControlStatesElement;
        this.resources = mediaResourcesElement;
        this.options = mediaOptionsElement;
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

    @Override // PlaybackInterface.v1_0.SetMediaMethod
    @JsonProperty("metadata")
    public MediaMetadataElement metadata() {
        return this.metadata;
    }

    @Override // PlaybackInterface.v1_0.SetMediaMethod
    @JsonProperty("actions")
    public MediaActionsElement actions() {
        return this.actions;
    }

    @Override // PlaybackInterface.v1_0.SetMediaMethod
    @JsonProperty("events")
    public MediaEventsElement events() {
        return this.events;
    }

    @Override // PlaybackInterface.v1_0.SetMediaMethod
    @JsonProperty("states")
    public MediaControlStatesElement states() {
        return this.states;
    }

    @Override // PlaybackInterface.v1_0.SetMediaMethod
    @JsonProperty("resources")
    @Nullable
    public MediaResourcesElement resources() {
        return this.resources;
    }

    @Override // PlaybackInterface.v1_0.SetMediaMethod
    @JsonProperty("options")
    @Nullable
    public MediaOptionsElement options() {
        return this.options;
    }

    public final ImmutableSetMediaMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetMediaMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.metadata, this.actions, this.events, this.states, this.resources, this.options);
    }

    public final ImmutableSetMediaMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetMediaMethod(this.queue, bool2, this.metadata, this.actions, this.events, this.states, this.resources, this.options);
    }

    public final ImmutableSetMediaMethod withMetadata(MediaMetadataElement mediaMetadataElement) {
        if (this.metadata == mediaMetadataElement) {
            return this;
        }
        return new ImmutableSetMediaMethod(this.queue, this.forced, (MediaMetadataElement) requireNonNull(mediaMetadataElement, "metadata"), this.actions, this.events, this.states, this.resources, this.options);
    }

    public final ImmutableSetMediaMethod withActions(MediaActionsElement mediaActionsElement) {
        if (this.actions == mediaActionsElement) {
            return this;
        }
        return new ImmutableSetMediaMethod(this.queue, this.forced, this.metadata, (MediaActionsElement) requireNonNull(mediaActionsElement, "actions"), this.events, this.states, this.resources, this.options);
    }

    public final ImmutableSetMediaMethod withEvents(MediaEventsElement mediaEventsElement) {
        if (this.events == mediaEventsElement) {
            return this;
        }
        return new ImmutableSetMediaMethod(this.queue, this.forced, this.metadata, this.actions, (MediaEventsElement) requireNonNull(mediaEventsElement, "events"), this.states, this.resources, this.options);
    }

    public final ImmutableSetMediaMethod withStates(MediaControlStatesElement mediaControlStatesElement) {
        if (this.states == mediaControlStatesElement) {
            return this;
        }
        return new ImmutableSetMediaMethod(this.queue, this.forced, this.metadata, this.actions, this.events, (MediaControlStatesElement) requireNonNull(mediaControlStatesElement, "states"), this.resources, this.options);
    }

    public final ImmutableSetMediaMethod withResources(@Nullable MediaResourcesElement mediaResourcesElement) {
        return this.resources == mediaResourcesElement ? this : new ImmutableSetMediaMethod(this.queue, this.forced, this.metadata, this.actions, this.events, this.states, mediaResourcesElement, this.options);
    }

    public final ImmutableSetMediaMethod withOptions(@Nullable MediaOptionsElement mediaOptionsElement) {
        return this.options == mediaOptionsElement ? this : new ImmutableSetMediaMethod(this.queue, this.forced, this.metadata, this.actions, this.events, this.states, this.resources, mediaOptionsElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetMediaMethod) && equalTo(0, (ImmutableSetMediaMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetMediaMethod immutableSetMediaMethod) {
        return this.queue.equals(immutableSetMediaMethod.queue) && this.forced.equals(immutableSetMediaMethod.forced) && this.metadata.equals(immutableSetMediaMethod.metadata) && this.actions.equals(immutableSetMediaMethod.actions) && this.events.equals(immutableSetMediaMethod.events) && this.states.equals(immutableSetMediaMethod.states) && equals(this.resources, immutableSetMediaMethod.resources) && equals(this.options, immutableSetMediaMethod.options);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.metadata.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.actions.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.events.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.states.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.resources);
        return iHashCode7 + (iHashCode7 << 5) + hashCode(this.options);
    }

    public String toString() {
        return "SetMediaMethod{queue=" + this.queue + ", forced=" + this.forced + ", metadata=" + this.metadata + ", actions=" + this.actions + ", events=" + this.events + ", states=" + this.states + ", resources=" + this.resources + ", options=" + this.options + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetMediaMethod", generator = "Immutables")
    static final class Json extends SetMediaMethod {

        @Nullable
        MediaActionsElement actions;

        @Nullable
        MediaEventsElement events;

        @Nullable
        Boolean forced;

        @Nullable
        MediaMetadataElement metadata;

        @Nullable
        MediaOptionsElement options;

        @Nullable
        Queue queue;

        @Nullable
        MediaResourcesElement resources;

        @Nullable
        MediaControlStatesElement states;

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

        @JsonProperty("metadata")
        public void setMetadata(MediaMetadataElement mediaMetadataElement) {
            this.metadata = mediaMetadataElement;
        }

        @JsonProperty("actions")
        public void setActions(MediaActionsElement mediaActionsElement) {
            this.actions = mediaActionsElement;
        }

        @JsonProperty("events")
        public void setEvents(MediaEventsElement mediaEventsElement) {
            this.events = mediaEventsElement;
        }

        @JsonProperty("states")
        public void setStates(MediaControlStatesElement mediaControlStatesElement) {
            this.states = mediaControlStatesElement;
        }

        @JsonProperty("resources")
        public void setResources(@Nullable MediaResourcesElement mediaResourcesElement) {
            this.resources = mediaResourcesElement;
        }

        @JsonProperty("options")
        public void setOptions(@Nullable MediaOptionsElement mediaOptionsElement) {
            this.options = mediaOptionsElement;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetMediaMethod
        public MediaMetadataElement metadata() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetMediaMethod
        public MediaActionsElement actions() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetMediaMethod
        public MediaEventsElement events() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetMediaMethod
        public MediaControlStatesElement states() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetMediaMethod
        public MediaResourcesElement resources() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetMediaMethod
        public MediaOptionsElement options() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetMediaMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        MediaMetadataElement mediaMetadataElement = json.metadata;
        if (mediaMetadataElement != null) {
            builder.metadata(mediaMetadataElement);
        }
        MediaActionsElement mediaActionsElement = json.actions;
        if (mediaActionsElement != null) {
            builder.actions(mediaActionsElement);
        }
        MediaEventsElement mediaEventsElement = json.events;
        if (mediaEventsElement != null) {
            builder.events(mediaEventsElement);
        }
        MediaControlStatesElement mediaControlStatesElement = json.states;
        if (mediaControlStatesElement != null) {
            builder.states(mediaControlStatesElement);
        }
        MediaResourcesElement mediaResourcesElement = json.resources;
        if (mediaResourcesElement != null) {
            builder.resources(mediaResourcesElement);
        }
        MediaOptionsElement mediaOptionsElement = json.options;
        if (mediaOptionsElement != null) {
            builder.options(mediaOptionsElement);
        }
        return builder.build();
    }

    public static ImmutableSetMediaMethod copyOf(SetMediaMethod setMediaMethod) {
        if (setMediaMethod instanceof ImmutableSetMediaMethod) {
            return (ImmutableSetMediaMethod) setMediaMethod;
        }
        return builder().from(setMediaMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetMediaMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ACTIONS = 4;
        private static final long INIT_BIT_EVENTS = 8;
        private static final long INIT_BIT_METADATA = 2;
        private static final long INIT_BIT_QUEUE = 1;
        private static final long INIT_BIT_STATES = 16;

        @Nullable
        private MediaActionsElement actions;

        @Nullable
        private MediaEventsElement events;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private MediaMetadataElement metadata;

        @Nullable
        private MediaOptionsElement options;

        @Nullable
        private Queue queue;

        @Nullable
        private MediaResourcesElement resources;

        @Nullable
        private MediaControlStatesElement states;

        private Builder() {
            this.initBits = 31L;
        }

        public final Builder from(SetMediaMethod setMediaMethod) {
            ImmutableSetMediaMethod.requireNonNull(setMediaMethod, "instance");
            from((Object) setMediaMethod);
            return this;
        }

        public final Builder from(Method method) {
            ImmutableSetMediaMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof SetMediaMethod) {
                SetMediaMethod setMediaMethod = (SetMediaMethod) obj;
                metadata(setMediaMethod.metadata());
                forced(setMediaMethod.forced());
                MediaOptionsElement mediaOptionsElementOptions = setMediaMethod.options();
                if (mediaOptionsElementOptions != null) {
                    options(mediaOptionsElementOptions);
                }
                MediaResourcesElement mediaResourcesElementResources = setMediaMethod.resources();
                if (mediaResourcesElementResources != null) {
                    resources(mediaResourcesElementResources);
                }
                actions(setMediaMethod.actions());
                queue(setMediaMethod.queue());
                j = 3;
                events(setMediaMethod.events());
                states(setMediaMethod.states());
            } else {
                j = 0;
            }
            if (obj instanceof Method) {
                Method method = (Method) obj;
                if ((j & 1) == 0) {
                    forced(method.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(method.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetMediaMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetMediaMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("metadata")
        public final Builder metadata(MediaMetadataElement mediaMetadataElement) {
            this.metadata = (MediaMetadataElement) ImmutableSetMediaMethod.requireNonNull(mediaMetadataElement, "metadata");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("actions")
        public final Builder actions(MediaActionsElement mediaActionsElement) {
            this.actions = (MediaActionsElement) ImmutableSetMediaMethod.requireNonNull(mediaActionsElement, "actions");
            this.initBits &= -5;
            return this;
        }

        @JsonProperty("events")
        public final Builder events(MediaEventsElement mediaEventsElement) {
            this.events = (MediaEventsElement) ImmutableSetMediaMethod.requireNonNull(mediaEventsElement, "events");
            this.initBits &= -9;
            return this;
        }

        @JsonProperty("states")
        public final Builder states(MediaControlStatesElement mediaControlStatesElement) {
            this.states = (MediaControlStatesElement) ImmutableSetMediaMethod.requireNonNull(mediaControlStatesElement, "states");
            this.initBits &= -17;
            return this;
        }

        @JsonProperty("resources")
        public final Builder resources(@Nullable MediaResourcesElement mediaResourcesElement) {
            this.resources = mediaResourcesElement;
            return this;
        }

        @JsonProperty("options")
        public final Builder options(@Nullable MediaOptionsElement mediaOptionsElement) {
            this.options = mediaOptionsElement;
            return this;
        }

        public ImmutableSetMediaMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetMediaMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("metadata");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("actions");
            }
            if ((this.initBits & 8) != 0) {
                arrayList.add("events");
            }
            if ((this.initBits & 16) != 0) {
                arrayList.add("states");
            }
            return "Cannot build SetMediaMethod, some of required attributes are not set " + arrayList;
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
