package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
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
@Generated(from = "CallToActionElement", generator = "Immutables")
public final class ImmutableCallToActionElement extends CallToActionElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final PillNavigatorWidgetItemElement callToAction;
    private volatile transient InitShim initShim;
    private final List<Method> onViewed;
    private final String title;
    private final String widgetId;

    private ImmutableCallToActionElement(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.callToAction = builder.callToAction;
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.initShim = null;
    }

    private ImmutableCallToActionElement(String str, List<Method> list, String str2, PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.title = str2;
        this.callToAction = pillNavigatorWidgetItemElement;
        this.initShim = null;
    }

    @Generated(from = "CallToActionElement", generator = "Immutables")
    private final class InitShim {
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableCallToActionElement.requireNonNull(ImmutableCallToActionElement.super.widgetId(), "widgetId");
                this.widgetIdBuildStage = (byte) 1;
            }
            return this.widgetId;
        }

        void widgetId(String str) {
            this.widgetId = str;
            this.widgetIdBuildStage = (byte) 1;
        }

        List<Method> onViewed() {
            byte b = this.onViewedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onViewedBuildStage = (byte) -1;
                this.onViewed = ImmutableCallToActionElement.createUnmodifiableList(false, ImmutableCallToActionElement.createSafeList(ImmutableCallToActionElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            return "Cannot build CallToActionElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // Touch.WidgetsInterface.v1_0.WidgetElement
    @JsonProperty("widgetId")
    public String widgetId() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.widgetId();
        }
        return this.widgetId;
    }

    @Override // Touch.WidgetsInterface.v1_0.WidgetElement
    @JsonProperty("onViewed")
    public List<Method> onViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onViewed();
        }
        return this.onViewed;
    }

    @Override // Touch.WidgetsInterface.v1_0.CallToActionElement
    @JsonProperty("title")
    public String title() {
        return this.title;
    }

    @Override // Touch.WidgetsInterface.v1_0.CallToActionElement
    @JsonProperty("callToAction")
    public PillNavigatorWidgetItemElement callToAction() {
        return this.callToAction;
    }

    public final ImmutableCallToActionElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableCallToActionElement(str2, this.onViewed, this.title, this.callToAction);
    }

    public final ImmutableCallToActionElement withOnViewed(Method... methodArr) {
        return new ImmutableCallToActionElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.title, this.callToAction);
    }

    public final ImmutableCallToActionElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableCallToActionElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.title, this.callToAction);
    }

    public final ImmutableCallToActionElement withTitle(String str) {
        String str2 = (String) requireNonNull(str, "title");
        return this.title.equals(str2) ? this : new ImmutableCallToActionElement(this.widgetId, this.onViewed, str2, this.callToAction);
    }

    public final ImmutableCallToActionElement withCallToAction(PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
        if (this.callToAction == pillNavigatorWidgetItemElement) {
            return this;
        }
        return new ImmutableCallToActionElement(this.widgetId, this.onViewed, this.title, (PillNavigatorWidgetItemElement) requireNonNull(pillNavigatorWidgetItemElement, "callToAction"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableCallToActionElement) && equalTo(0, (ImmutableCallToActionElement) obj);
    }

    private boolean equalTo(int i, ImmutableCallToActionElement immutableCallToActionElement) {
        return this.widgetId.equals(immutableCallToActionElement.widgetId) && this.onViewed.equals(immutableCallToActionElement.onViewed) && this.title.equals(immutableCallToActionElement.title) && this.callToAction.equals(immutableCallToActionElement.callToAction);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.title.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.callToAction.hashCode();
    }

    public String toString() {
        return "CallToActionElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", title=" + this.title + ", callToAction=" + this.callToAction + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "CallToActionElement", generator = "Immutables")
    static final class Json extends CallToActionElement {

        @Nullable
        PillNavigatorWidgetItemElement callToAction;

        @Nullable
        List<Method> onViewed = Collections.emptyList();
        boolean onViewedIsSet;

        @Nullable
        String title;

        @Nullable
        String widgetId;

        Json() {
        }

        @JsonProperty("widgetId")
        public void setWidgetId(String str) {
            this.widgetId = str;
        }

        @JsonProperty("onViewed")
        public void setOnViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedIsSet = true;
        }

        @JsonProperty("title")
        public void setTitle(String str) {
            this.title = str;
        }

        @JsonProperty("callToAction")
        public void setCallToAction(PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
            this.callToAction = pillNavigatorWidgetItemElement;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.CallToActionElement
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.CallToActionElement
        public PillNavigatorWidgetItemElement callToAction() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableCallToActionElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        String str2 = json.title;
        if (str2 != null) {
            builder.title(str2);
        }
        PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement = json.callToAction;
        if (pillNavigatorWidgetItemElement != null) {
            builder.callToAction(pillNavigatorWidgetItemElement);
        }
        return builder.build();
    }

    public static ImmutableCallToActionElement copyOf(CallToActionElement callToActionElement) {
        if (callToActionElement instanceof ImmutableCallToActionElement) {
            return (ImmutableCallToActionElement) callToActionElement;
        }
        return builder().from(callToActionElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "CallToActionElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_CALL_TO_ACTION = 2;
        private static final long INIT_BIT_TITLE = 1;
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private PillNavigatorWidgetItemElement callToAction;
        private long initBits;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String title;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 3L;
            this.onViewed = new ArrayList();
        }

        public final Builder from(CallToActionElement callToActionElement) {
            ImmutableCallToActionElement.requireNonNull(callToActionElement, "instance");
            from((Object) callToActionElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableCallToActionElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof CallToActionElement) {
                CallToActionElement callToActionElement = (CallToActionElement) obj;
                addAllOnViewed(callToActionElement.onViewed());
                title(callToActionElement.title());
                widgetId(callToActionElement.widgetId());
                j = 3;
                callToAction(callToActionElement.callToAction());
            } else {
                j = 0;
            }
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                if ((j & 1) == 0) {
                    widgetId(widgetElement.widgetId());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    addAllOnViewed(widgetElement.onViewed());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableCallToActionElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableCallToActionElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableCallToActionElement.requireNonNull(method, "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onViewed")
        public final Builder onViewed(Iterable<? extends Method> iterable) {
            this.onViewed.clear();
            return addAllOnViewed(iterable);
        }

        public final Builder addAllOnViewed(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onViewed.add((Method) ImmutableCallToActionElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("title")
        public final Builder title(String str) {
            this.title = (String) ImmutableCallToActionElement.requireNonNull(str, "title");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("callToAction")
        public final Builder callToAction(PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
            this.callToAction = (PillNavigatorWidgetItemElement) ImmutableCallToActionElement.requireNonNull(pillNavigatorWidgetItemElement, "callToAction");
            this.initBits &= -3;
            return this;
        }

        public ImmutableCallToActionElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableCallToActionElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("title");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("callToAction");
            }
            return "Cannot build CallToActionElement, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
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
