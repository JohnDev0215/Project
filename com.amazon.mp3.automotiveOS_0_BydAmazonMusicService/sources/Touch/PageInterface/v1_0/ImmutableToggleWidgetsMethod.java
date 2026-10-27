package Touch.PageInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import Touch.WidgetsInterface.v1_0.WidgetElement;
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
@Generated(from = "ToggleWidgetsMethod", generator = "Immutables")
public final class ImmutableToggleWidgetsMethod extends ToggleWidgetsMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final AddWidgetsToTemplateMethod onAdd;
    private final RemoveWidgetsFromTemplateMethod onRemove;
    private final Queue queue;
    private final boolean shouldPerformWithAnimation;
    private final boolean shouldScroll;

    @Nullable
    private final String widgetId;
    private final List<WidgetElement> widgets;

    private ImmutableToggleWidgetsMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.widgets = createUnmodifiableList(true, builder.widgets);
        this.widgetId = builder.widgetId;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.shouldScrollIsSet()) {
            this.initShim.shouldScroll(builder.shouldScroll);
        }
        if (builder.shouldPerformWithAnimationIsSet()) {
            this.initShim.shouldPerformWithAnimation(builder.shouldPerformWithAnimation);
        }
        if (builder.onAdd != null) {
            this.initShim.onAdd(builder.onAdd);
        }
        if (builder.onRemove != null) {
            this.initShim.onRemove(builder.onRemove);
        }
        this.forced = this.initShim.forced();
        this.shouldScroll = this.initShim.shouldScroll();
        this.shouldPerformWithAnimation = this.initShim.shouldPerformWithAnimation();
        this.onAdd = this.initShim.onAdd();
        this.onRemove = this.initShim.onRemove();
        this.initShim = null;
    }

    private ImmutableToggleWidgetsMethod(Queue queue, Boolean bool, List<WidgetElement> list, @Nullable String str, boolean z, boolean z2, AddWidgetsToTemplateMethod addWidgetsToTemplateMethod, RemoveWidgetsFromTemplateMethod removeWidgetsFromTemplateMethod) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.widgets = list;
        this.widgetId = str;
        this.shouldScroll = z;
        this.shouldPerformWithAnimation = z2;
        this.onAdd = addWidgetsToTemplateMethod;
        this.onRemove = removeWidgetsFromTemplateMethod;
        this.initShim = null;
    }

    @Generated(from = "ToggleWidgetsMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private AddWidgetsToTemplateMethod onAdd;
        private byte onAddBuildStage;
        private RemoveWidgetsFromTemplateMethod onRemove;
        private byte onRemoveBuildStage;
        private boolean shouldPerformWithAnimation;
        private byte shouldPerformWithAnimationBuildStage;
        private boolean shouldScroll;
        private byte shouldScrollBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.shouldScrollBuildStage = (byte) 0;
            this.shouldPerformWithAnimationBuildStage = (byte) 0;
            this.onAddBuildStage = (byte) 0;
            this.onRemoveBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableToggleWidgetsMethod.requireNonNull(ImmutableToggleWidgetsMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        boolean shouldScroll() {
            byte b = this.shouldScrollBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.shouldScrollBuildStage = (byte) -1;
                this.shouldScroll = ImmutableToggleWidgetsMethod.super.shouldScroll();
                this.shouldScrollBuildStage = (byte) 1;
            }
            return this.shouldScroll;
        }

        void shouldScroll(boolean z) {
            this.shouldScroll = z;
            this.shouldScrollBuildStage = (byte) 1;
        }

        boolean shouldPerformWithAnimation() {
            byte b = this.shouldPerformWithAnimationBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.shouldPerformWithAnimationBuildStage = (byte) -1;
                this.shouldPerformWithAnimation = ImmutableToggleWidgetsMethod.super.shouldPerformWithAnimation();
                this.shouldPerformWithAnimationBuildStage = (byte) 1;
            }
            return this.shouldPerformWithAnimation;
        }

        void shouldPerformWithAnimation(boolean z) {
            this.shouldPerformWithAnimation = z;
            this.shouldPerformWithAnimationBuildStage = (byte) 1;
        }

        AddWidgetsToTemplateMethod onAdd() {
            byte b = this.onAddBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onAddBuildStage = (byte) -1;
                this.onAdd = (AddWidgetsToTemplateMethod) ImmutableToggleWidgetsMethod.requireNonNull(ImmutableToggleWidgetsMethod.super.onAdd(), "onAdd");
                this.onAddBuildStage = (byte) 1;
            }
            return this.onAdd;
        }

        void onAdd(AddWidgetsToTemplateMethod addWidgetsToTemplateMethod) {
            this.onAdd = addWidgetsToTemplateMethod;
            this.onAddBuildStage = (byte) 1;
        }

        RemoveWidgetsFromTemplateMethod onRemove() {
            byte b = this.onRemoveBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onRemoveBuildStage = (byte) -1;
                this.onRemove = (RemoveWidgetsFromTemplateMethod) ImmutableToggleWidgetsMethod.requireNonNull(ImmutableToggleWidgetsMethod.super.onRemove(), "onRemove");
                this.onRemoveBuildStage = (byte) 1;
            }
            return this.onRemove;
        }

        void onRemove(RemoveWidgetsFromTemplateMethod removeWidgetsFromTemplateMethod) {
            this.onRemove = removeWidgetsFromTemplateMethod;
            this.onRemoveBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.shouldScrollBuildStage == -1) {
                arrayList.add("shouldScroll");
            }
            if (this.shouldPerformWithAnimationBuildStage == -1) {
                arrayList.add("shouldPerformWithAnimation");
            }
            if (this.onAddBuildStage == -1) {
                arrayList.add("onAdd");
            }
            if (this.onRemoveBuildStage == -1) {
                arrayList.add("onRemove");
            }
            return "Cannot build ToggleWidgetsMethod, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
    @JsonProperty("widgets")
    public List<WidgetElement> widgets() {
        return this.widgets;
    }

    @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
    @JsonProperty("widgetId")
    @Nullable
    public String widgetId() {
        return this.widgetId;
    }

    @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
    @JsonProperty("shouldScroll")
    public boolean shouldScroll() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shouldScroll();
        }
        return this.shouldScroll;
    }

    @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
    @JsonProperty("shouldPerformWithAnimation")
    public boolean shouldPerformWithAnimation() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shouldPerformWithAnimation();
        }
        return this.shouldPerformWithAnimation;
    }

    @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
    @JsonProperty("onAdd")
    public AddWidgetsToTemplateMethod onAdd() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onAdd();
        }
        return this.onAdd;
    }

    @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
    @JsonProperty("onRemove")
    public RemoveWidgetsFromTemplateMethod onRemove() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onRemove();
        }
        return this.onRemove;
    }

    public final ImmutableToggleWidgetsMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableToggleWidgetsMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.widgets, this.widgetId, this.shouldScroll, this.shouldPerformWithAnimation, this.onAdd, this.onRemove);
    }

    public final ImmutableToggleWidgetsMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableToggleWidgetsMethod(this.queue, bool2, this.widgets, this.widgetId, this.shouldScroll, this.shouldPerformWithAnimation, this.onAdd, this.onRemove);
    }

    public final ImmutableToggleWidgetsMethod withWidgets(WidgetElement... widgetElementArr) {
        return new ImmutableToggleWidgetsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(widgetElementArr), true, false)), this.widgetId, this.shouldScroll, this.shouldPerformWithAnimation, this.onAdd, this.onRemove);
    }

    public final ImmutableToggleWidgetsMethod withWidgets(Iterable<? extends WidgetElement> iterable) {
        if (this.widgets == iterable) {
            return this;
        }
        return new ImmutableToggleWidgetsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.widgetId, this.shouldScroll, this.shouldPerformWithAnimation, this.onAdd, this.onRemove);
    }

    public final ImmutableToggleWidgetsMethod withWidgetId(@Nullable String str) {
        return equals(this.widgetId, str) ? this : new ImmutableToggleWidgetsMethod(this.queue, this.forced, this.widgets, str, this.shouldScroll, this.shouldPerformWithAnimation, this.onAdd, this.onRemove);
    }

    public final ImmutableToggleWidgetsMethod withShouldScroll(boolean z) {
        return this.shouldScroll == z ? this : new ImmutableToggleWidgetsMethod(this.queue, this.forced, this.widgets, this.widgetId, z, this.shouldPerformWithAnimation, this.onAdd, this.onRemove);
    }

    public final ImmutableToggleWidgetsMethod withShouldPerformWithAnimation(boolean z) {
        return this.shouldPerformWithAnimation == z ? this : new ImmutableToggleWidgetsMethod(this.queue, this.forced, this.widgets, this.widgetId, this.shouldScroll, z, this.onAdd, this.onRemove);
    }

    public final ImmutableToggleWidgetsMethod withOnAdd(AddWidgetsToTemplateMethod addWidgetsToTemplateMethod) {
        if (this.onAdd == addWidgetsToTemplateMethod) {
            return this;
        }
        return new ImmutableToggleWidgetsMethod(this.queue, this.forced, this.widgets, this.widgetId, this.shouldScroll, this.shouldPerformWithAnimation, (AddWidgetsToTemplateMethod) requireNonNull(addWidgetsToTemplateMethod, "onAdd"), this.onRemove);
    }

    public final ImmutableToggleWidgetsMethod withOnRemove(RemoveWidgetsFromTemplateMethod removeWidgetsFromTemplateMethod) {
        if (this.onRemove == removeWidgetsFromTemplateMethod) {
            return this;
        }
        return new ImmutableToggleWidgetsMethod(this.queue, this.forced, this.widgets, this.widgetId, this.shouldScroll, this.shouldPerformWithAnimation, this.onAdd, (RemoveWidgetsFromTemplateMethod) requireNonNull(removeWidgetsFromTemplateMethod, "onRemove"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableToggleWidgetsMethod) && equalTo(0, (ImmutableToggleWidgetsMethod) obj);
    }

    private boolean equalTo(int i, ImmutableToggleWidgetsMethod immutableToggleWidgetsMethod) {
        return this.queue.equals(immutableToggleWidgetsMethod.queue) && this.forced.equals(immutableToggleWidgetsMethod.forced) && this.widgets.equals(immutableToggleWidgetsMethod.widgets) && equals(this.widgetId, immutableToggleWidgetsMethod.widgetId) && this.shouldScroll == immutableToggleWidgetsMethod.shouldScroll && this.shouldPerformWithAnimation == immutableToggleWidgetsMethod.shouldPerformWithAnimation && this.onAdd.equals(immutableToggleWidgetsMethod.onAdd) && this.onRemove.equals(immutableToggleWidgetsMethod.onRemove);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.widgets.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.widgetId);
        int i = iHashCode4 + (iHashCode4 << 5) + (this.shouldScroll ? 1231 : 1237);
        int i2 = i + (i << 5) + (this.shouldPerformWithAnimation ? 1231 : 1237);
        int iHashCode5 = i2 + (i2 << 5) + this.onAdd.hashCode();
        return iHashCode5 + (iHashCode5 << 5) + this.onRemove.hashCode();
    }

    public String toString() {
        return "ToggleWidgetsMethod{queue=" + this.queue + ", forced=" + this.forced + ", widgets=" + this.widgets + ", widgetId=" + this.widgetId + ", shouldScroll=" + this.shouldScroll + ", shouldPerformWithAnimation=" + this.shouldPerformWithAnimation + ", onAdd=" + this.onAdd + ", onRemove=" + this.onRemove + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ToggleWidgetsMethod", generator = "Immutables")
    static final class Json extends ToggleWidgetsMethod {

        @Nullable
        Boolean forced;

        @Nullable
        AddWidgetsToTemplateMethod onAdd;

        @Nullable
        RemoveWidgetsFromTemplateMethod onRemove;

        @Nullable
        Queue queue;
        boolean shouldPerformWithAnimation;
        boolean shouldPerformWithAnimationIsSet;
        boolean shouldScroll;
        boolean shouldScrollIsSet;

        @Nullable
        String widgetId;

        @Nullable
        List<WidgetElement> widgets = Collections.emptyList();

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

        @JsonProperty("widgets")
        public void setWidgets(List<WidgetElement> list) {
            this.widgets = list;
        }

        @JsonProperty("widgetId")
        public void setWidgetId(@Nullable String str) {
            this.widgetId = str;
        }

        @JsonProperty("shouldScroll")
        public void setShouldScroll(boolean z) {
            this.shouldScroll = z;
            this.shouldScrollIsSet = true;
        }

        @JsonProperty("shouldPerformWithAnimation")
        public void setShouldPerformWithAnimation(boolean z) {
            this.shouldPerformWithAnimation = z;
            this.shouldPerformWithAnimationIsSet = true;
        }

        @JsonProperty("onAdd")
        public void setOnAdd(AddWidgetsToTemplateMethod addWidgetsToTemplateMethod) {
            this.onAdd = addWidgetsToTemplateMethod;
        }

        @JsonProperty("onRemove")
        public void setOnRemove(RemoveWidgetsFromTemplateMethod removeWidgetsFromTemplateMethod) {
            this.onRemove = removeWidgetsFromTemplateMethod;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
        public List<WidgetElement> widgets() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
        public boolean shouldScroll() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
        public boolean shouldPerformWithAnimation() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
        public AddWidgetsToTemplateMethod onAdd() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.ToggleWidgetsMethod
        public RemoveWidgetsFromTemplateMethod onRemove() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableToggleWidgetsMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<WidgetElement> list = json.widgets;
        if (list != null) {
            builder.addAllWidgets(list);
        }
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.shouldScrollIsSet) {
            builder.shouldScroll(json.shouldScroll);
        }
        if (json.shouldPerformWithAnimationIsSet) {
            builder.shouldPerformWithAnimation(json.shouldPerformWithAnimation);
        }
        AddWidgetsToTemplateMethod addWidgetsToTemplateMethod = json.onAdd;
        if (addWidgetsToTemplateMethod != null) {
            builder.onAdd(addWidgetsToTemplateMethod);
        }
        RemoveWidgetsFromTemplateMethod removeWidgetsFromTemplateMethod = json.onRemove;
        if (removeWidgetsFromTemplateMethod != null) {
            builder.onRemove(removeWidgetsFromTemplateMethod);
        }
        return builder.build();
    }

    public static ImmutableToggleWidgetsMethod copyOf(ToggleWidgetsMethod toggleWidgetsMethod) {
        if (toggleWidgetsMethod instanceof ImmutableToggleWidgetsMethod) {
            return (ImmutableToggleWidgetsMethod) toggleWidgetsMethod;
        }
        return builder().from(toggleWidgetsMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ToggleWidgetsMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_SHOULD_PERFORM_WITH_ANIMATION = 2;
        private static final long OPT_BIT_SHOULD_SCROLL = 1;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private AddWidgetsToTemplateMethod onAdd;

        @Nullable
        private RemoveWidgetsFromTemplateMethod onRemove;
        private long optBits;

        @Nullable
        private Queue queue;
        private boolean shouldPerformWithAnimation;
        private boolean shouldScroll;

        @Nullable
        private String widgetId;
        private List<WidgetElement> widgets;

        private Builder() {
            this.initBits = 1L;
            this.widgets = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableToggleWidgetsMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(ToggleWidgetsMethod toggleWidgetsMethod) {
            ImmutableToggleWidgetsMethod.requireNonNull(toggleWidgetsMethod, "instance");
            from((Object) toggleWidgetsMethod);
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
            if (obj instanceof ToggleWidgetsMethod) {
                ToggleWidgetsMethod toggleWidgetsMethod = (ToggleWidgetsMethod) obj;
                if ((j & 1) == 0) {
                    forced(toggleWidgetsMethod.forced());
                    j |= 1;
                }
                String strWidgetId = toggleWidgetsMethod.widgetId();
                if (strWidgetId != null) {
                    widgetId(strWidgetId);
                }
                addAllWidgets(toggleWidgetsMethod.widgets());
                onRemove(toggleWidgetsMethod.onRemove());
                shouldScroll(toggleWidgetsMethod.shouldScroll());
                onAdd(toggleWidgetsMethod.onAdd());
                if ((j & 2) == 0) {
                    queue(toggleWidgetsMethod.queue());
                }
                shouldPerformWithAnimation(toggleWidgetsMethod.shouldPerformWithAnimation());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableToggleWidgetsMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableToggleWidgetsMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addWidgets(WidgetElement widgetElement) {
            this.widgets.add((WidgetElement) ImmutableToggleWidgetsMethod.requireNonNull(widgetElement, "widgets element"));
            return this;
        }

        public final Builder addWidgets(WidgetElement... widgetElementArr) {
            for (WidgetElement widgetElement : widgetElementArr) {
                this.widgets.add((WidgetElement) ImmutableToggleWidgetsMethod.requireNonNull(widgetElement, "widgets element"));
            }
            return this;
        }

        @JsonProperty("widgets")
        public final Builder widgets(Iterable<? extends WidgetElement> iterable) {
            this.widgets.clear();
            return addAllWidgets(iterable);
        }

        public final Builder addAllWidgets(Iterable<? extends WidgetElement> iterable) {
            Iterator<? extends WidgetElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.widgets.add((WidgetElement) ImmutableToggleWidgetsMethod.requireNonNull(it.next(), "widgets element"));
            }
            return this;
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(@Nullable String str) {
            this.widgetId = str;
            return this;
        }

        @JsonProperty("shouldScroll")
        public final Builder shouldScroll(boolean z) {
            this.shouldScroll = z;
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("shouldPerformWithAnimation")
        public final Builder shouldPerformWithAnimation(boolean z) {
            this.shouldPerformWithAnimation = z;
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onAdd")
        public final Builder onAdd(AddWidgetsToTemplateMethod addWidgetsToTemplateMethod) {
            this.onAdd = (AddWidgetsToTemplateMethod) ImmutableToggleWidgetsMethod.requireNonNull(addWidgetsToTemplateMethod, "onAdd");
            return this;
        }

        @JsonProperty("onRemove")
        public final Builder onRemove(RemoveWidgetsFromTemplateMethod removeWidgetsFromTemplateMethod) {
            this.onRemove = (RemoveWidgetsFromTemplateMethod) ImmutableToggleWidgetsMethod.requireNonNull(removeWidgetsFromTemplateMethod, "onRemove");
            return this;
        }

        public ImmutableToggleWidgetsMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableToggleWidgetsMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldScrollIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldPerformWithAnimationIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build ToggleWidgetsMethod, some of required attributes are not set " + arrayList;
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
