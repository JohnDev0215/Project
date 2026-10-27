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
@Generated(from = "AddWidgetsToTemplateMethod", generator = "Immutables")
public final class ImmutableAddWidgetsToTemplateMethod extends AddWidgetsToTemplateMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final List<Method> onEndOfWidget;
    private final Queue queue;
    private final boolean shouldPerformWithAnimation;
    private final boolean shouldReplaceExistingWidgets;
    private final boolean shouldScroll;

    @Nullable
    private final String widgetId;
    private final List<WidgetElement> widgets;

    private ImmutableAddWidgetsToTemplateMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.widgets = createUnmodifiableList(true, builder.widgets);
        this.widgetId = builder.widgetId;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.shouldReplaceExistingWidgetsIsSet()) {
            this.initShim.shouldReplaceExistingWidgets(builder.shouldReplaceExistingWidgets);
        }
        if (builder.shouldPerformWithAnimationIsSet()) {
            this.initShim.shouldPerformWithAnimation(builder.shouldPerformWithAnimation);
        }
        if (builder.onEndOfWidgetIsSet()) {
            this.initShim.onEndOfWidget(createUnmodifiableList(true, builder.onEndOfWidget));
        }
        if (builder.shouldScrollIsSet()) {
            this.initShim.shouldScroll(builder.shouldScroll);
        }
        this.forced = this.initShim.forced();
        this.shouldReplaceExistingWidgets = this.initShim.shouldReplaceExistingWidgets();
        this.shouldPerformWithAnimation = this.initShim.shouldPerformWithAnimation();
        this.onEndOfWidget = this.initShim.onEndOfWidget();
        this.shouldScroll = this.initShim.shouldScroll();
        this.initShim = null;
    }

    private ImmutableAddWidgetsToTemplateMethod(Queue queue, Boolean bool, List<WidgetElement> list, @Nullable String str, boolean z, boolean z2, List<Method> list2, boolean z3) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.widgets = list;
        this.widgetId = str;
        this.shouldReplaceExistingWidgets = z;
        this.shouldPerformWithAnimation = z2;
        this.onEndOfWidget = list2;
        this.shouldScroll = z3;
        this.initShim = null;
    }

    @Generated(from = "AddWidgetsToTemplateMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private List<Method> onEndOfWidget;
        private byte onEndOfWidgetBuildStage;
        private boolean shouldPerformWithAnimation;
        private byte shouldPerformWithAnimationBuildStage;
        private boolean shouldReplaceExistingWidgets;
        private byte shouldReplaceExistingWidgetsBuildStage;
        private boolean shouldScroll;
        private byte shouldScrollBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.shouldReplaceExistingWidgetsBuildStage = (byte) 0;
            this.shouldPerformWithAnimationBuildStage = (byte) 0;
            this.onEndOfWidgetBuildStage = (byte) 0;
            this.shouldScrollBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableAddWidgetsToTemplateMethod.requireNonNull(ImmutableAddWidgetsToTemplateMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        boolean shouldReplaceExistingWidgets() {
            byte b = this.shouldReplaceExistingWidgetsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.shouldReplaceExistingWidgetsBuildStage = (byte) -1;
                this.shouldReplaceExistingWidgets = ImmutableAddWidgetsToTemplateMethod.super.shouldReplaceExistingWidgets();
                this.shouldReplaceExistingWidgetsBuildStage = (byte) 1;
            }
            return this.shouldReplaceExistingWidgets;
        }

        void shouldReplaceExistingWidgets(boolean z) {
            this.shouldReplaceExistingWidgets = z;
            this.shouldReplaceExistingWidgetsBuildStage = (byte) 1;
        }

        boolean shouldPerformWithAnimation() {
            byte b = this.shouldPerformWithAnimationBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.shouldPerformWithAnimationBuildStage = (byte) -1;
                this.shouldPerformWithAnimation = ImmutableAddWidgetsToTemplateMethod.super.shouldPerformWithAnimation();
                this.shouldPerformWithAnimationBuildStage = (byte) 1;
            }
            return this.shouldPerformWithAnimation;
        }

        void shouldPerformWithAnimation(boolean z) {
            this.shouldPerformWithAnimation = z;
            this.shouldPerformWithAnimationBuildStage = (byte) 1;
        }

        List<Method> onEndOfWidget() {
            byte b = this.onEndOfWidgetBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEndOfWidgetBuildStage = (byte) -1;
                this.onEndOfWidget = ImmutableAddWidgetsToTemplateMethod.createUnmodifiableList(false, ImmutableAddWidgetsToTemplateMethod.createSafeList(ImmutableAddWidgetsToTemplateMethod.super.onEndOfWidget(), true, false));
                this.onEndOfWidgetBuildStage = (byte) 1;
            }
            return this.onEndOfWidget;
        }

        void onEndOfWidget(List<Method> list) {
            this.onEndOfWidget = list;
            this.onEndOfWidgetBuildStage = (byte) 1;
        }

        boolean shouldScroll() {
            byte b = this.shouldScrollBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.shouldScrollBuildStage = (byte) -1;
                this.shouldScroll = ImmutableAddWidgetsToTemplateMethod.super.shouldScroll();
                this.shouldScrollBuildStage = (byte) 1;
            }
            return this.shouldScroll;
        }

        void shouldScroll(boolean z) {
            this.shouldScroll = z;
            this.shouldScrollBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.shouldReplaceExistingWidgetsBuildStage == -1) {
                arrayList.add("shouldReplaceExistingWidgets");
            }
            if (this.shouldPerformWithAnimationBuildStage == -1) {
                arrayList.add("shouldPerformWithAnimation");
            }
            if (this.onEndOfWidgetBuildStage == -1) {
                arrayList.add("onEndOfWidget");
            }
            if (this.shouldScrollBuildStage == -1) {
                arrayList.add("shouldScroll");
            }
            return "Cannot build AddWidgetsToTemplateMethod, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
    @JsonProperty("widgets")
    public List<WidgetElement> widgets() {
        return this.widgets;
    }

    @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
    @JsonProperty("widgetId")
    @Nullable
    public String widgetId() {
        return this.widgetId;
    }

    @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
    @JsonProperty("shouldReplaceExistingWidgets")
    public boolean shouldReplaceExistingWidgets() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shouldReplaceExistingWidgets();
        }
        return this.shouldReplaceExistingWidgets;
    }

    @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
    @JsonProperty("shouldPerformWithAnimation")
    public boolean shouldPerformWithAnimation() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shouldPerformWithAnimation();
        }
        return this.shouldPerformWithAnimation;
    }

    @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
    @JsonProperty("onEndOfWidget")
    public List<Method> onEndOfWidget() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEndOfWidget();
        }
        return this.onEndOfWidget;
    }

    @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
    @JsonProperty("shouldScroll")
    public boolean shouldScroll() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shouldScroll();
        }
        return this.shouldScroll;
    }

    public final ImmutableAddWidgetsToTemplateMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableAddWidgetsToTemplateMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.widgets, this.widgetId, this.shouldReplaceExistingWidgets, this.shouldPerformWithAnimation, this.onEndOfWidget, this.shouldScroll);
    }

    public final ImmutableAddWidgetsToTemplateMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableAddWidgetsToTemplateMethod(this.queue, bool2, this.widgets, this.widgetId, this.shouldReplaceExistingWidgets, this.shouldPerformWithAnimation, this.onEndOfWidget, this.shouldScroll);
    }

    public final ImmutableAddWidgetsToTemplateMethod withWidgets(WidgetElement... widgetElementArr) {
        return new ImmutableAddWidgetsToTemplateMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(widgetElementArr), true, false)), this.widgetId, this.shouldReplaceExistingWidgets, this.shouldPerformWithAnimation, this.onEndOfWidget, this.shouldScroll);
    }

    public final ImmutableAddWidgetsToTemplateMethod withWidgets(Iterable<? extends WidgetElement> iterable) {
        if (this.widgets == iterable) {
            return this;
        }
        return new ImmutableAddWidgetsToTemplateMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.widgetId, this.shouldReplaceExistingWidgets, this.shouldPerformWithAnimation, this.onEndOfWidget, this.shouldScroll);
    }

    public final ImmutableAddWidgetsToTemplateMethod withWidgetId(@Nullable String str) {
        return equals(this.widgetId, str) ? this : new ImmutableAddWidgetsToTemplateMethod(this.queue, this.forced, this.widgets, str, this.shouldReplaceExistingWidgets, this.shouldPerformWithAnimation, this.onEndOfWidget, this.shouldScroll);
    }

    public final ImmutableAddWidgetsToTemplateMethod withShouldReplaceExistingWidgets(boolean z) {
        return this.shouldReplaceExistingWidgets == z ? this : new ImmutableAddWidgetsToTemplateMethod(this.queue, this.forced, this.widgets, this.widgetId, z, this.shouldPerformWithAnimation, this.onEndOfWidget, this.shouldScroll);
    }

    public final ImmutableAddWidgetsToTemplateMethod withShouldPerformWithAnimation(boolean z) {
        return this.shouldPerformWithAnimation == z ? this : new ImmutableAddWidgetsToTemplateMethod(this.queue, this.forced, this.widgets, this.widgetId, this.shouldReplaceExistingWidgets, z, this.onEndOfWidget, this.shouldScroll);
    }

    public final ImmutableAddWidgetsToTemplateMethod withOnEndOfWidget(Method... methodArr) {
        return new ImmutableAddWidgetsToTemplateMethod(this.queue, this.forced, this.widgets, this.widgetId, this.shouldReplaceExistingWidgets, this.shouldPerformWithAnimation, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.shouldScroll);
    }

    public final ImmutableAddWidgetsToTemplateMethod withOnEndOfWidget(Iterable<? extends Method> iterable) {
        if (this.onEndOfWidget == iterable) {
            return this;
        }
        return new ImmutableAddWidgetsToTemplateMethod(this.queue, this.forced, this.widgets, this.widgetId, this.shouldReplaceExistingWidgets, this.shouldPerformWithAnimation, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.shouldScroll);
    }

    public final ImmutableAddWidgetsToTemplateMethod withShouldScroll(boolean z) {
        return this.shouldScroll == z ? this : new ImmutableAddWidgetsToTemplateMethod(this.queue, this.forced, this.widgets, this.widgetId, this.shouldReplaceExistingWidgets, this.shouldPerformWithAnimation, this.onEndOfWidget, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableAddWidgetsToTemplateMethod) && equalTo(0, (ImmutableAddWidgetsToTemplateMethod) obj);
    }

    private boolean equalTo(int i, ImmutableAddWidgetsToTemplateMethod immutableAddWidgetsToTemplateMethod) {
        return this.queue.equals(immutableAddWidgetsToTemplateMethod.queue) && this.forced.equals(immutableAddWidgetsToTemplateMethod.forced) && this.widgets.equals(immutableAddWidgetsToTemplateMethod.widgets) && equals(this.widgetId, immutableAddWidgetsToTemplateMethod.widgetId) && this.shouldReplaceExistingWidgets == immutableAddWidgetsToTemplateMethod.shouldReplaceExistingWidgets && this.shouldPerformWithAnimation == immutableAddWidgetsToTemplateMethod.shouldPerformWithAnimation && this.onEndOfWidget.equals(immutableAddWidgetsToTemplateMethod.onEndOfWidget) && this.shouldScroll == immutableAddWidgetsToTemplateMethod.shouldScroll;
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.widgets.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.widgetId);
        int i = iHashCode4 + (iHashCode4 << 5) + (this.shouldReplaceExistingWidgets ? 1231 : 1237);
        int i2 = i + (i << 5) + (this.shouldPerformWithAnimation ? 1231 : 1237);
        int iHashCode5 = i2 + (i2 << 5) + this.onEndOfWidget.hashCode();
        return iHashCode5 + (iHashCode5 << 5) + (this.shouldScroll ? 1231 : 1237);
    }

    public String toString() {
        return "AddWidgetsToTemplateMethod{queue=" + this.queue + ", forced=" + this.forced + ", widgets=" + this.widgets + ", widgetId=" + this.widgetId + ", shouldReplaceExistingWidgets=" + this.shouldReplaceExistingWidgets + ", shouldPerformWithAnimation=" + this.shouldPerformWithAnimation + ", onEndOfWidget=" + this.onEndOfWidget + ", shouldScroll=" + this.shouldScroll + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "AddWidgetsToTemplateMethod", generator = "Immutables")
    static final class Json extends AddWidgetsToTemplateMethod {

        @Nullable
        Boolean forced;
        boolean onEndOfWidgetIsSet;

        @Nullable
        Queue queue;
        boolean shouldPerformWithAnimation;
        boolean shouldPerformWithAnimationIsSet;
        boolean shouldReplaceExistingWidgets;
        boolean shouldReplaceExistingWidgetsIsSet;
        boolean shouldScroll;
        boolean shouldScrollIsSet;

        @Nullable
        String widgetId;

        @Nullable
        List<WidgetElement> widgets = Collections.emptyList();

        @Nullable
        List<Method> onEndOfWidget = Collections.emptyList();

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

        @JsonProperty("shouldReplaceExistingWidgets")
        public void setShouldReplaceExistingWidgets(boolean z) {
            this.shouldReplaceExistingWidgets = z;
            this.shouldReplaceExistingWidgetsIsSet = true;
        }

        @JsonProperty("shouldPerformWithAnimation")
        public void setShouldPerformWithAnimation(boolean z) {
            this.shouldPerformWithAnimation = z;
            this.shouldPerformWithAnimationIsSet = true;
        }

        @JsonProperty("onEndOfWidget")
        public void setOnEndOfWidget(List<Method> list) {
            this.onEndOfWidget = list;
            this.onEndOfWidgetIsSet = true;
        }

        @JsonProperty("shouldScroll")
        public void setShouldScroll(boolean z) {
            this.shouldScroll = z;
            this.shouldScrollIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
        public List<WidgetElement> widgets() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
        public boolean shouldReplaceExistingWidgets() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
        public boolean shouldPerformWithAnimation() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
        public List<Method> onEndOfWidget() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod
        public boolean shouldScroll() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableAddWidgetsToTemplateMethod fromJson(Json json) {
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
        if (json.shouldReplaceExistingWidgetsIsSet) {
            builder.shouldReplaceExistingWidgets(json.shouldReplaceExistingWidgets);
        }
        if (json.shouldPerformWithAnimationIsSet) {
            builder.shouldPerformWithAnimation(json.shouldPerformWithAnimation);
        }
        if (json.onEndOfWidgetIsSet) {
            builder.addAllOnEndOfWidget(json.onEndOfWidget);
        }
        if (json.shouldScrollIsSet) {
            builder.shouldScroll(json.shouldScroll);
        }
        return builder.build();
    }

    public static ImmutableAddWidgetsToTemplateMethod copyOf(AddWidgetsToTemplateMethod addWidgetsToTemplateMethod) {
        if (addWidgetsToTemplateMethod instanceof ImmutableAddWidgetsToTemplateMethod) {
            return (ImmutableAddWidgetsToTemplateMethod) addWidgetsToTemplateMethod;
        }
        return builder().from(addWidgetsToTemplateMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "AddWidgetsToTemplateMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_ON_END_OF_WIDGET = 4;
        private static final long OPT_BIT_SHOULD_PERFORM_WITH_ANIMATION = 2;
        private static final long OPT_BIT_SHOULD_REPLACE_EXISTING_WIDGETS = 1;
        private static final long OPT_BIT_SHOULD_SCROLL = 8;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<Method> onEndOfWidget;
        private long optBits;

        @Nullable
        private Queue queue;
        private boolean shouldPerformWithAnimation;
        private boolean shouldReplaceExistingWidgets;
        private boolean shouldScroll;

        @Nullable
        private String widgetId;
        private List<WidgetElement> widgets;

        private Builder() {
            this.initBits = 1L;
            this.widgets = new ArrayList();
            this.onEndOfWidget = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableAddWidgetsToTemplateMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(AddWidgetsToTemplateMethod addWidgetsToTemplateMethod) {
            ImmutableAddWidgetsToTemplateMethod.requireNonNull(addWidgetsToTemplateMethod, "instance");
            from((Object) addWidgetsToTemplateMethod);
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
            if (obj instanceof AddWidgetsToTemplateMethod) {
                AddWidgetsToTemplateMethod addWidgetsToTemplateMethod = (AddWidgetsToTemplateMethod) obj;
                addAllOnEndOfWidget(addWidgetsToTemplateMethod.onEndOfWidget());
                if ((j & 1) == 0) {
                    forced(addWidgetsToTemplateMethod.forced());
                    j |= 1;
                }
                String strWidgetId = addWidgetsToTemplateMethod.widgetId();
                if (strWidgetId != null) {
                    widgetId(strWidgetId);
                }
                addAllWidgets(addWidgetsToTemplateMethod.widgets());
                shouldReplaceExistingWidgets(addWidgetsToTemplateMethod.shouldReplaceExistingWidgets());
                shouldScroll(addWidgetsToTemplateMethod.shouldScroll());
                if ((j & 2) == 0) {
                    queue(addWidgetsToTemplateMethod.queue());
                }
                shouldPerformWithAnimation(addWidgetsToTemplateMethod.shouldPerformWithAnimation());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableAddWidgetsToTemplateMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableAddWidgetsToTemplateMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addWidgets(WidgetElement widgetElement) {
            this.widgets.add((WidgetElement) ImmutableAddWidgetsToTemplateMethod.requireNonNull(widgetElement, "widgets element"));
            return this;
        }

        public final Builder addWidgets(WidgetElement... widgetElementArr) {
            for (WidgetElement widgetElement : widgetElementArr) {
                this.widgets.add((WidgetElement) ImmutableAddWidgetsToTemplateMethod.requireNonNull(widgetElement, "widgets element"));
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
                this.widgets.add((WidgetElement) ImmutableAddWidgetsToTemplateMethod.requireNonNull(it.next(), "widgets element"));
            }
            return this;
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(@Nullable String str) {
            this.widgetId = str;
            return this;
        }

        @JsonProperty("shouldReplaceExistingWidgets")
        public final Builder shouldReplaceExistingWidgets(boolean z) {
            this.shouldReplaceExistingWidgets = z;
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("shouldPerformWithAnimation")
        public final Builder shouldPerformWithAnimation(boolean z) {
            this.shouldPerformWithAnimation = z;
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnEndOfWidget(Method method) {
            this.onEndOfWidget.add((Method) ImmutableAddWidgetsToTemplateMethod.requireNonNull(method, "onEndOfWidget element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnEndOfWidget(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEndOfWidget.add((Method) ImmutableAddWidgetsToTemplateMethod.requireNonNull(method, "onEndOfWidget element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("onEndOfWidget")
        public final Builder onEndOfWidget(Iterable<? extends Method> iterable) {
            this.onEndOfWidget.clear();
            return addAllOnEndOfWidget(iterable);
        }

        public final Builder addAllOnEndOfWidget(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEndOfWidget.add((Method) ImmutableAddWidgetsToTemplateMethod.requireNonNull(it.next(), "onEndOfWidget element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("shouldScroll")
        public final Builder shouldScroll(boolean z) {
            this.shouldScroll = z;
            this.optBits |= 8;
            return this;
        }

        public ImmutableAddWidgetsToTemplateMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableAddWidgetsToTemplateMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldReplaceExistingWidgetsIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldPerformWithAnimationIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEndOfWidgetIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldScrollIsSet() {
            return (this.optBits & 8) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build AddWidgetsToTemplateMethod, some of required attributes are not set " + arrayList;
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
