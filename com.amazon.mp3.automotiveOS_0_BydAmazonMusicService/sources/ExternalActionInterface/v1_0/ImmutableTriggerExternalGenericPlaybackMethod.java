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
@Generated(from = "TriggerExternalGenericPlaybackMethod", generator = "Immutables")
public final class ImmutableTriggerExternalGenericPlaybackMethod extends TriggerExternalGenericPlaybackMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final List<PlayableContent> content;
    private final Boolean forced;
    private volatile transient InitShim initShim;

    @Nullable
    private final String nextToken;
    private final Queue queue;
    private final int startingIndex;

    private ImmutableTriggerExternalGenericPlaybackMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.content = builder.content == null ? null : createUnmodifiableList(true, builder.content);
        this.nextToken = builder.nextToken;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.startingIndexIsSet()) {
            this.initShim.startingIndex(builder.startingIndex);
        }
        this.forced = this.initShim.forced();
        this.startingIndex = this.initShim.startingIndex();
        this.initShim = null;
    }

    private ImmutableTriggerExternalGenericPlaybackMethod(Queue queue, Boolean bool, @Nullable List<PlayableContent> list, int i, @Nullable String str) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.content = list;
        this.startingIndex = i;
        this.nextToken = str;
        this.initShim = null;
    }

    @Generated(from = "TriggerExternalGenericPlaybackMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private int startingIndex;
        private byte startingIndexBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.startingIndexBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableTriggerExternalGenericPlaybackMethod.requireNonNull(ImmutableTriggerExternalGenericPlaybackMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        int startingIndex() {
            byte b = this.startingIndexBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.startingIndexBuildStage = (byte) -1;
                this.startingIndex = ImmutableTriggerExternalGenericPlaybackMethod.super.startingIndex();
                this.startingIndexBuildStage = (byte) 1;
            }
            return this.startingIndex;
        }

        void startingIndex(int i) {
            this.startingIndex = i;
            this.startingIndexBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.startingIndexBuildStage == -1) {
                arrayList.add("startingIndex");
            }
            return "Cannot build TriggerExternalGenericPlaybackMethod, attribute initializers form cycle " + arrayList;
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

    @Override // ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod
    @JsonProperty("content")
    @Nullable
    public List<PlayableContent> content() {
        return this.content;
    }

    @Override // ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod
    @JsonProperty("startingIndex")
    public int startingIndex() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.startingIndex();
        }
        return this.startingIndex;
    }

    @Override // ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod
    @JsonProperty("nextToken")
    @Nullable
    public String nextToken() {
        return this.nextToken;
    }

    public final ImmutableTriggerExternalGenericPlaybackMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableTriggerExternalGenericPlaybackMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.content, this.startingIndex, this.nextToken);
    }

    public final ImmutableTriggerExternalGenericPlaybackMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableTriggerExternalGenericPlaybackMethod(this.queue, bool2, this.content, this.startingIndex, this.nextToken);
    }

    public final ImmutableTriggerExternalGenericPlaybackMethod withContent(@Nullable PlayableContent... playableContentArr) {
        if (playableContentArr == null) {
            return new ImmutableTriggerExternalGenericPlaybackMethod(this.queue, this.forced, null, this.startingIndex, this.nextToken);
        }
        return new ImmutableTriggerExternalGenericPlaybackMethod(this.queue, this.forced, Arrays.asList(playableContentArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(playableContentArr), true, false)), this.startingIndex, this.nextToken);
    }

    public final ImmutableTriggerExternalGenericPlaybackMethod withContent(@Nullable Iterable<? extends PlayableContent> iterable) {
        if (this.content == iterable) {
            return this;
        }
        return new ImmutableTriggerExternalGenericPlaybackMethod(this.queue, this.forced, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.startingIndex, this.nextToken);
    }

    public final ImmutableTriggerExternalGenericPlaybackMethod withStartingIndex(int i) {
        return this.startingIndex == i ? this : new ImmutableTriggerExternalGenericPlaybackMethod(this.queue, this.forced, this.content, i, this.nextToken);
    }

    public final ImmutableTriggerExternalGenericPlaybackMethod withNextToken(@Nullable String str) {
        return equals(this.nextToken, str) ? this : new ImmutableTriggerExternalGenericPlaybackMethod(this.queue, this.forced, this.content, this.startingIndex, str);
    }

    public boolean equals(@javax.annotation.Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTriggerExternalGenericPlaybackMethod) && equalTo(0, (ImmutableTriggerExternalGenericPlaybackMethod) obj);
    }

    private boolean equalTo(int i, ImmutableTriggerExternalGenericPlaybackMethod immutableTriggerExternalGenericPlaybackMethod) {
        return this.queue.equals(immutableTriggerExternalGenericPlaybackMethod.queue) && this.forced.equals(immutableTriggerExternalGenericPlaybackMethod.forced) && equals(this.content, immutableTriggerExternalGenericPlaybackMethod.content) && this.startingIndex == immutableTriggerExternalGenericPlaybackMethod.startingIndex && equals(this.nextToken, immutableTriggerExternalGenericPlaybackMethod.nextToken);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.content);
        int i = iHashCode3 + (iHashCode3 << 5) + this.startingIndex;
        return i + (i << 5) + hashCode(this.nextToken);
    }

    public String toString() {
        return "TriggerExternalGenericPlaybackMethod{queue=" + this.queue + ", forced=" + this.forced + ", content=" + this.content + ", startingIndex=" + this.startingIndex + ", nextToken=" + this.nextToken + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "TriggerExternalGenericPlaybackMethod", generator = "Immutables")
    static final class Json extends TriggerExternalGenericPlaybackMethod {

        @javax.annotation.Nullable
        List<PlayableContent> content = null;

        @javax.annotation.Nullable
        Boolean forced;

        @javax.annotation.Nullable
        String nextToken;

        @javax.annotation.Nullable
        Queue queue;
        int startingIndex;
        boolean startingIndexIsSet;

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

        @JsonProperty("content")
        public void setContent(@Nullable List<PlayableContent> list) {
            this.content = list;
        }

        @JsonProperty("startingIndex")
        public void setStartingIndex(int i) {
            this.startingIndex = i;
            this.startingIndexIsSet = true;
        }

        @JsonProperty("nextToken")
        public void setNextToken(@Nullable String str) {
            this.nextToken = str;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod
        public List<PlayableContent> content() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod
        public int startingIndex() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod
        public String nextToken() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTriggerExternalGenericPlaybackMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<PlayableContent> list = json.content;
        if (list != null) {
            builder.addAllContent(list);
        }
        if (json.startingIndexIsSet) {
            builder.startingIndex(json.startingIndex);
        }
        String str = json.nextToken;
        if (str != null) {
            builder.nextToken(str);
        }
        return builder.build();
    }

    public static ImmutableTriggerExternalGenericPlaybackMethod copyOf(TriggerExternalGenericPlaybackMethod triggerExternalGenericPlaybackMethod) {
        if (triggerExternalGenericPlaybackMethod instanceof ImmutableTriggerExternalGenericPlaybackMethod) {
            return (ImmutableTriggerExternalGenericPlaybackMethod) triggerExternalGenericPlaybackMethod;
        }
        return builder().from(triggerExternalGenericPlaybackMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "TriggerExternalGenericPlaybackMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_STARTING_INDEX = 1;
        private List<PlayableContent> content;

        @javax.annotation.Nullable
        private Boolean forced;
        private long initBits;

        @javax.annotation.Nullable
        private String nextToken;
        private long optBits;

        @javax.annotation.Nullable
        private Queue queue;
        private int startingIndex;

        private Builder() {
            this.initBits = 1L;
            this.content = null;
        }

        public final Builder from(Method method) {
            ImmutableTriggerExternalGenericPlaybackMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(TriggerExternalGenericPlaybackMethod triggerExternalGenericPlaybackMethod) {
            ImmutableTriggerExternalGenericPlaybackMethod.requireNonNull(triggerExternalGenericPlaybackMethod, "instance");
            from((Object) triggerExternalGenericPlaybackMethod);
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
            if (obj instanceof TriggerExternalGenericPlaybackMethod) {
                TriggerExternalGenericPlaybackMethod triggerExternalGenericPlaybackMethod = (TriggerExternalGenericPlaybackMethod) obj;
                if ((j & 1) == 0) {
                    forced(triggerExternalGenericPlaybackMethod.forced());
                    j |= 1;
                }
                String strNextToken = triggerExternalGenericPlaybackMethod.nextToken();
                if (strNextToken != null) {
                    nextToken(strNextToken);
                }
                if ((j & 2) == 0) {
                    queue(triggerExternalGenericPlaybackMethod.queue());
                }
                List<PlayableContent> listContent = triggerExternalGenericPlaybackMethod.content();
                if (listContent != null) {
                    addAllContent(listContent);
                }
                startingIndex(triggerExternalGenericPlaybackMethod.startingIndex());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableTriggerExternalGenericPlaybackMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableTriggerExternalGenericPlaybackMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addContent(PlayableContent playableContent) {
            if (this.content == null) {
                this.content = new ArrayList();
            }
            this.content.add((PlayableContent) ImmutableTriggerExternalGenericPlaybackMethod.requireNonNull(playableContent, "content element"));
            return this;
        }

        public final Builder addContent(PlayableContent... playableContentArr) {
            if (this.content == null) {
                this.content = new ArrayList();
            }
            for (PlayableContent playableContent : playableContentArr) {
                this.content.add((PlayableContent) ImmutableTriggerExternalGenericPlaybackMethod.requireNonNull(playableContent, "content element"));
            }
            return this;
        }

        @JsonProperty("content")
        public final Builder content(@Nullable Iterable<? extends PlayableContent> iterable) {
            if (iterable == null) {
                this.content = null;
                return this;
            }
            this.content = new ArrayList();
            return addAllContent(iterable);
        }

        public final Builder addAllContent(Iterable<? extends PlayableContent> iterable) {
            ImmutableTriggerExternalGenericPlaybackMethod.requireNonNull(iterable, "content element");
            if (this.content == null) {
                this.content = new ArrayList();
            }
            Iterator<? extends PlayableContent> it = iterable.iterator();
            while (it.hasNext()) {
                this.content.add((PlayableContent) ImmutableTriggerExternalGenericPlaybackMethod.requireNonNull(it.next(), "content element"));
            }
            return this;
        }

        @JsonProperty("startingIndex")
        public final Builder startingIndex(int i) {
            this.startingIndex = i;
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("nextToken")
        public final Builder nextToken(@Nullable String str) {
            this.nextToken = str;
            return this;
        }

        public ImmutableTriggerExternalGenericPlaybackMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableTriggerExternalGenericPlaybackMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean startingIndexIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build TriggerExternalGenericPlaybackMethod, some of required attributes are not set " + arrayList;
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
