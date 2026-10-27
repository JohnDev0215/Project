package Touch.PageInterface.v1_0;

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
@Generated(from = "RemoveWidgetsFromTemplateMethod", generator = "Immutables")
public final class ImmutableRemoveWidgetsFromTemplateMethod extends RemoveWidgetsFromTemplateMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final Queue queue;
    private final boolean shouldPerformWithAnimation;

    @Nullable
    private final List<String> widgetIds;

    private ImmutableRemoveWidgetsFromTemplateMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.widgetIds = builder.widgetIds == null ? null : createUnmodifiableList(true, builder.widgetIds);
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.shouldPerformWithAnimationIsSet()) {
            this.initShim.shouldPerformWithAnimation(builder.shouldPerformWithAnimation);
        }
        this.forced = this.initShim.forced();
        this.shouldPerformWithAnimation = this.initShim.shouldPerformWithAnimation();
        this.initShim = null;
    }

    private ImmutableRemoveWidgetsFromTemplateMethod(Queue queue, Boolean bool, @Nullable List<String> list, boolean z) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.widgetIds = list;
        this.shouldPerformWithAnimation = z;
        this.initShim = null;
    }

    @Generated(from = "RemoveWidgetsFromTemplateMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private boolean shouldPerformWithAnimation;
        private byte shouldPerformWithAnimationBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.shouldPerformWithAnimationBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableRemoveWidgetsFromTemplateMethod.requireNonNull(ImmutableRemoveWidgetsFromTemplateMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        boolean shouldPerformWithAnimation() {
            byte b = this.shouldPerformWithAnimationBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.shouldPerformWithAnimationBuildStage = (byte) -1;
                this.shouldPerformWithAnimation = ImmutableRemoveWidgetsFromTemplateMethod.super.shouldPerformWithAnimation();
                this.shouldPerformWithAnimationBuildStage = (byte) 1;
            }
            return this.shouldPerformWithAnimation;
        }

        void shouldPerformWithAnimation(boolean z) {
            this.shouldPerformWithAnimation = z;
            this.shouldPerformWithAnimationBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.shouldPerformWithAnimationBuildStage == -1) {
                arrayList.add("shouldPerformWithAnimation");
            }
            return "Cannot build RemoveWidgetsFromTemplateMethod, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.PageInterface.v1_0.RemoveWidgetsFromTemplateMethod
    @JsonProperty("widgetIds")
    @Nullable
    public List<String> widgetIds() {
        return this.widgetIds;
    }

    @Override // Touch.PageInterface.v1_0.RemoveWidgetsFromTemplateMethod
    @JsonProperty("shouldPerformWithAnimation")
    public boolean shouldPerformWithAnimation() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shouldPerformWithAnimation();
        }
        return this.shouldPerformWithAnimation;
    }

    public final ImmutableRemoveWidgetsFromTemplateMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableRemoveWidgetsFromTemplateMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.widgetIds, this.shouldPerformWithAnimation);
    }

    public final ImmutableRemoveWidgetsFromTemplateMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableRemoveWidgetsFromTemplateMethod(this.queue, bool2, this.widgetIds, this.shouldPerformWithAnimation);
    }

    public final ImmutableRemoveWidgetsFromTemplateMethod withWidgetIds(@Nullable String... strArr) {
        if (strArr == null) {
            return new ImmutableRemoveWidgetsFromTemplateMethod(this.queue, this.forced, null, this.shouldPerformWithAnimation);
        }
        return new ImmutableRemoveWidgetsFromTemplateMethod(this.queue, this.forced, Arrays.asList(strArr) != null ? createUnmodifiableList(false, createSafeList(Arrays.asList(strArr), true, false)) : null, this.shouldPerformWithAnimation);
    }

    public final ImmutableRemoveWidgetsFromTemplateMethod withWidgetIds(@Nullable Iterable<String> iterable) {
        if (this.widgetIds == iterable) {
            return this;
        }
        return new ImmutableRemoveWidgetsFromTemplateMethod(this.queue, this.forced, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.shouldPerformWithAnimation);
    }

    public final ImmutableRemoveWidgetsFromTemplateMethod withShouldPerformWithAnimation(boolean z) {
        return this.shouldPerformWithAnimation == z ? this : new ImmutableRemoveWidgetsFromTemplateMethod(this.queue, this.forced, this.widgetIds, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableRemoveWidgetsFromTemplateMethod) && equalTo(0, (ImmutableRemoveWidgetsFromTemplateMethod) obj);
    }

    private boolean equalTo(int i, ImmutableRemoveWidgetsFromTemplateMethod immutableRemoveWidgetsFromTemplateMethod) {
        return this.queue.equals(immutableRemoveWidgetsFromTemplateMethod.queue) && this.forced.equals(immutableRemoveWidgetsFromTemplateMethod.forced) && equals(this.widgetIds, immutableRemoveWidgetsFromTemplateMethod.widgetIds) && this.shouldPerformWithAnimation == immutableRemoveWidgetsFromTemplateMethod.shouldPerformWithAnimation;
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.widgetIds);
        return iHashCode3 + (iHashCode3 << 5) + (this.shouldPerformWithAnimation ? 1231 : 1237);
    }

    public String toString() {
        return "RemoveWidgetsFromTemplateMethod{queue=" + this.queue + ", forced=" + this.forced + ", widgetIds=" + this.widgetIds + ", shouldPerformWithAnimation=" + this.shouldPerformWithAnimation + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "RemoveWidgetsFromTemplateMethod", generator = "Immutables")
    static final class Json extends RemoveWidgetsFromTemplateMethod {

        @Nullable
        Boolean forced;

        @Nullable
        Queue queue;
        boolean shouldPerformWithAnimation;
        boolean shouldPerformWithAnimationIsSet;

        @Nullable
        List<String> widgetIds = null;

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

        @JsonProperty("widgetIds")
        public void setWidgetIds(@Nullable List<String> list) {
            this.widgetIds = list;
        }

        @JsonProperty("shouldPerformWithAnimation")
        public void setShouldPerformWithAnimation(boolean z) {
            this.shouldPerformWithAnimation = z;
            this.shouldPerformWithAnimationIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.RemoveWidgetsFromTemplateMethod
        public List<String> widgetIds() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.RemoveWidgetsFromTemplateMethod
        public boolean shouldPerformWithAnimation() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableRemoveWidgetsFromTemplateMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<String> list = json.widgetIds;
        if (list != null) {
            builder.addAllWidgetIds(list);
        }
        if (json.shouldPerformWithAnimationIsSet) {
            builder.shouldPerformWithAnimation(json.shouldPerformWithAnimation);
        }
        return builder.build();
    }

    public static ImmutableRemoveWidgetsFromTemplateMethod copyOf(RemoveWidgetsFromTemplateMethod removeWidgetsFromTemplateMethod) {
        if (removeWidgetsFromTemplateMethod instanceof ImmutableRemoveWidgetsFromTemplateMethod) {
            return (ImmutableRemoveWidgetsFromTemplateMethod) removeWidgetsFromTemplateMethod;
        }
        return builder().from(removeWidgetsFromTemplateMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "RemoveWidgetsFromTemplateMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_SHOULD_PERFORM_WITH_ANIMATION = 1;

        @Nullable
        private Boolean forced;
        private long initBits;
        private long optBits;

        @Nullable
        private Queue queue;
        private boolean shouldPerformWithAnimation;
        private List<String> widgetIds;

        private Builder() {
            this.initBits = 1L;
            this.widgetIds = null;
        }

        public final Builder from(Method method) {
            ImmutableRemoveWidgetsFromTemplateMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(RemoveWidgetsFromTemplateMethod removeWidgetsFromTemplateMethod) {
            ImmutableRemoveWidgetsFromTemplateMethod.requireNonNull(removeWidgetsFromTemplateMethod, "instance");
            from((Object) removeWidgetsFromTemplateMethod);
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
            if (obj instanceof RemoveWidgetsFromTemplateMethod) {
                RemoveWidgetsFromTemplateMethod removeWidgetsFromTemplateMethod = (RemoveWidgetsFromTemplateMethod) obj;
                if ((j & 1) == 0) {
                    forced(removeWidgetsFromTemplateMethod.forced());
                    j |= 1;
                }
                List<String> listWidgetIds = removeWidgetsFromTemplateMethod.widgetIds();
                if (listWidgetIds != null) {
                    addAllWidgetIds(listWidgetIds);
                }
                if ((j & 2) == 0) {
                    queue(removeWidgetsFromTemplateMethod.queue());
                }
                shouldPerformWithAnimation(removeWidgetsFromTemplateMethod.shouldPerformWithAnimation());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableRemoveWidgetsFromTemplateMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableRemoveWidgetsFromTemplateMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addWidgetIds(String str) {
            if (this.widgetIds == null) {
                this.widgetIds = new ArrayList();
            }
            this.widgetIds.add((String) ImmutableRemoveWidgetsFromTemplateMethod.requireNonNull(str, "widgetIds element"));
            return this;
        }

        public final Builder addWidgetIds(String... strArr) {
            if (this.widgetIds == null) {
                this.widgetIds = new ArrayList();
            }
            for (String str : strArr) {
                this.widgetIds.add((String) ImmutableRemoveWidgetsFromTemplateMethod.requireNonNull(str, "widgetIds element"));
            }
            return this;
        }

        @JsonProperty("widgetIds")
        public final Builder widgetIds(@Nullable Iterable<String> iterable) {
            if (iterable == null) {
                this.widgetIds = null;
                return this;
            }
            this.widgetIds = new ArrayList();
            return addAllWidgetIds(iterable);
        }

        public final Builder addAllWidgetIds(Iterable<String> iterable) {
            ImmutableRemoveWidgetsFromTemplateMethod.requireNonNull(iterable, "widgetIds element");
            if (this.widgetIds == null) {
                this.widgetIds = new ArrayList();
            }
            Iterator<String> it = iterable.iterator();
            while (it.hasNext()) {
                this.widgetIds.add((String) ImmutableRemoveWidgetsFromTemplateMethod.requireNonNull(it.next(), "widgetIds element"));
            }
            return this;
        }

        @JsonProperty("shouldPerformWithAnimation")
        public final Builder shouldPerformWithAnimation(boolean z) {
            this.shouldPerformWithAnimation = z;
            this.optBits |= 1;
            return this;
        }

        public ImmutableRemoveWidgetsFromTemplateMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableRemoveWidgetsFromTemplateMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldPerformWithAnimationIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build RemoveWidgetsFromTemplateMethod, some of required attributes are not set " + arrayList;
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
