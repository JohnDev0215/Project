package ExternalActionInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.Nullable;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "TriggerExternalPlaybackMethod", generator = "Immutables")
public final class ImmutableTriggerExternalPlaybackMethod extends TriggerExternalPlaybackMethod {
    private final Boolean forced;
    private final Queue queue;

    @Nullable
    private final List<Track> tracks;

    private ImmutableTriggerExternalPlaybackMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.tracks = builder.tracks == null ? null : createUnmodifiableList(true, builder.tracks);
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableTriggerExternalPlaybackMethod(Queue queue, Boolean bool, @Nullable List<Track> list) {
        this.queue = queue;
        this.forced = bool;
        this.tracks = list;
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

    @Override // ExternalActionInterface.v1_0.TriggerExternalPlaybackMethod
    @JsonProperty("tracks")
    @Nullable
    public List<Track> tracks() {
        return this.tracks;
    }

    public final ImmutableTriggerExternalPlaybackMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableTriggerExternalPlaybackMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.tracks);
    }

    public final ImmutableTriggerExternalPlaybackMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableTriggerExternalPlaybackMethod(this.queue, bool2, this.tracks);
    }

    public final ImmutableTriggerExternalPlaybackMethod withTracks(@Nullable Track... trackArr) {
        if (trackArr == null) {
            return new ImmutableTriggerExternalPlaybackMethod(this.queue, this.forced, null);
        }
        return new ImmutableTriggerExternalPlaybackMethod(this.queue, this.forced, Arrays.asList(trackArr) != null ? createUnmodifiableList(false, createSafeList(Arrays.asList(trackArr), true, false)) : null);
    }

    public final ImmutableTriggerExternalPlaybackMethod withTracks(@Nullable Iterable<? extends Track> iterable) {
        if (this.tracks == iterable) {
            return this;
        }
        return new ImmutableTriggerExternalPlaybackMethod(this.queue, this.forced, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@javax.annotation.Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTriggerExternalPlaybackMethod) && equalTo(0, (ImmutableTriggerExternalPlaybackMethod) obj);
    }

    private boolean equalTo(int i, ImmutableTriggerExternalPlaybackMethod immutableTriggerExternalPlaybackMethod) {
        return this.queue.equals(immutableTriggerExternalPlaybackMethod.queue) && this.forced.equals(immutableTriggerExternalPlaybackMethod.forced) && equals(this.tracks, immutableTriggerExternalPlaybackMethod.tracks);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + hashCode(this.tracks);
    }

    public String toString() {
        return "TriggerExternalPlaybackMethod{queue=" + this.queue + ", forced=" + this.forced + ", tracks=" + this.tracks + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "TriggerExternalPlaybackMethod", generator = "Immutables")
    static final class Json extends TriggerExternalPlaybackMethod {

        @javax.annotation.Nullable
        Boolean forced;

        @javax.annotation.Nullable
        Queue queue;

        @javax.annotation.Nullable
        List<Track> tracks = null;

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

        @JsonProperty("tracks")
        public void setTracks(@Nullable List<Track> list) {
            this.tracks = list;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.TriggerExternalPlaybackMethod
        public List<Track> tracks() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTriggerExternalPlaybackMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<Track> list = json.tracks;
        if (list != null) {
            builder.addAllTracks(list);
        }
        return builder.build();
    }

    public static ImmutableTriggerExternalPlaybackMethod copyOf(TriggerExternalPlaybackMethod triggerExternalPlaybackMethod) {
        if (triggerExternalPlaybackMethod instanceof ImmutableTriggerExternalPlaybackMethod) {
            return (ImmutableTriggerExternalPlaybackMethod) triggerExternalPlaybackMethod;
        }
        return builder().from(triggerExternalPlaybackMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "TriggerExternalPlaybackMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;

        @javax.annotation.Nullable
        private Boolean forced;
        private long initBits;

        @javax.annotation.Nullable
        private Queue queue;
        private List<Track> tracks;

        private Builder() {
            this.initBits = 1L;
            this.tracks = null;
        }

        public final Builder from(Method method) {
            ImmutableTriggerExternalPlaybackMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(TriggerExternalPlaybackMethod triggerExternalPlaybackMethod) {
            ImmutableTriggerExternalPlaybackMethod.requireNonNull(triggerExternalPlaybackMethod, "instance");
            from((Object) triggerExternalPlaybackMethod);
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
            if (obj instanceof TriggerExternalPlaybackMethod) {
                TriggerExternalPlaybackMethod triggerExternalPlaybackMethod = (TriggerExternalPlaybackMethod) obj;
                if ((j & 1) == 0) {
                    forced(triggerExternalPlaybackMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(triggerExternalPlaybackMethod.queue());
                }
                List<Track> listTracks = triggerExternalPlaybackMethod.tracks();
                if (listTracks != null) {
                    addAllTracks(listTracks);
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableTriggerExternalPlaybackMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableTriggerExternalPlaybackMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addTracks(Track track) {
            if (this.tracks == null) {
                this.tracks = new ArrayList();
            }
            this.tracks.add((Track) ImmutableTriggerExternalPlaybackMethod.requireNonNull(track, "tracks element"));
            return this;
        }

        public final Builder addTracks(Track... trackArr) {
            if (this.tracks == null) {
                this.tracks = new ArrayList();
            }
            for (Track track : trackArr) {
                this.tracks.add((Track) ImmutableTriggerExternalPlaybackMethod.requireNonNull(track, "tracks element"));
            }
            return this;
        }

        @JsonProperty("tracks")
        public final Builder tracks(@Nullable Iterable<? extends Track> iterable) {
            if (iterable == null) {
                this.tracks = null;
                return this;
            }
            this.tracks = new ArrayList();
            return addAllTracks(iterable);
        }

        public final Builder addAllTracks(Iterable<? extends Track> iterable) {
            ImmutableTriggerExternalPlaybackMethod.requireNonNull(iterable, "tracks element");
            if (this.tracks == null) {
                this.tracks = new ArrayList();
            }
            Iterator<? extends Track> it = iterable.iterator();
            while (it.hasNext()) {
                this.tracks.add((Track) ImmutableTriggerExternalPlaybackMethod.requireNonNull(it.next(), "tracks element"));
            }
            return this;
        }

        public ImmutableTriggerExternalPlaybackMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableTriggerExternalPlaybackMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build TriggerExternalPlaybackMethod, some of required attributes are not set " + arrayList;
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

    private static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
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

    private static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
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
