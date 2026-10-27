package Touch.MultiSelectorTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
@Generated(from = "TabNavigatorWidgetItemElement", generator = "Immutables")
public final class ImmutableTabNavigatorWidgetItemElement extends TabNavigatorWidgetItemElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;

    @Nullable
    private final SelectableTextButtonListingElement items;
    private final String label;
    private final List<Method> onViewed;
    private final String widgetId;

    private ImmutableTabNavigatorWidgetItemElement(Builder builder) {
        this.initShim = new InitShim();
        this.label = builder.label;
        this.items = builder.items;
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

    private ImmutableTabNavigatorWidgetItemElement(String str, List<Method> list, String str2, @Nullable SelectableTextButtonListingElement selectableTextButtonListingElement) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.label = str2;
        this.items = selectableTextButtonListingElement;
        this.initShim = null;
    }

    @Generated(from = "TabNavigatorWidgetItemElement", generator = "Immutables")
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
                this.widgetId = (String) ImmutableTabNavigatorWidgetItemElement.requireNonNull(ImmutableTabNavigatorWidgetItemElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableTabNavigatorWidgetItemElement.createUnmodifiableList(false, ImmutableTabNavigatorWidgetItemElement.createSafeList(ImmutableTabNavigatorWidgetItemElement.super.onViewed(), true, false));
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
            return "Cannot build TabNavigatorWidgetItemElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.TabNavigatorWidgetItemElement
    @JsonProperty(ContextMappingConstants.LABEL)
    public String label() {
        return this.label;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.TabNavigatorWidgetItemElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    @Nullable
    public SelectableTextButtonListingElement items() {
        return this.items;
    }

    public final ImmutableTabNavigatorWidgetItemElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableTabNavigatorWidgetItemElement(str2, this.onViewed, this.label, this.items);
    }

    public final ImmutableTabNavigatorWidgetItemElement withOnViewed(Method... methodArr) {
        return new ImmutableTabNavigatorWidgetItemElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.label, this.items);
    }

    public final ImmutableTabNavigatorWidgetItemElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableTabNavigatorWidgetItemElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.label, this.items);
    }

    public final ImmutableTabNavigatorWidgetItemElement withLabel(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.LABEL);
        return this.label.equals(str2) ? this : new ImmutableTabNavigatorWidgetItemElement(this.widgetId, this.onViewed, str2, this.items);
    }

    public final ImmutableTabNavigatorWidgetItemElement withItems(@Nullable SelectableTextButtonListingElement selectableTextButtonListingElement) {
        return this.items == selectableTextButtonListingElement ? this : new ImmutableTabNavigatorWidgetItemElement(this.widgetId, this.onViewed, this.label, selectableTextButtonListingElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTabNavigatorWidgetItemElement) && equalTo(0, (ImmutableTabNavigatorWidgetItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableTabNavigatorWidgetItemElement immutableTabNavigatorWidgetItemElement) {
        return this.widgetId.equals(immutableTabNavigatorWidgetItemElement.widgetId) && this.onViewed.equals(immutableTabNavigatorWidgetItemElement.onViewed) && this.label.equals(immutableTabNavigatorWidgetItemElement.label) && equals(this.items, immutableTabNavigatorWidgetItemElement.items);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.label.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + hashCode(this.items);
    }

    public String toString() {
        return "TabNavigatorWidgetItemElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", label=" + this.label + ", items=" + this.items + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "TabNavigatorWidgetItemElement", generator = "Immutables")
    static final class Json extends TabNavigatorWidgetItemElement {

        @Nullable
        SelectableTextButtonListingElement items;

        @Nullable
        String label;

        @Nullable
        List<Method> onViewed = Collections.emptyList();
        boolean onViewedIsSet;

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

        @JsonProperty(ContextMappingConstants.LABEL)
        public void setLabel(String str) {
            this.label = str;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(@Nullable SelectableTextButtonListingElement selectableTextButtonListingElement) {
            this.items = selectableTextButtonListingElement;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.TabNavigatorWidgetItemElement
        public String label() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.TabNavigatorWidgetItemElement
        public SelectableTextButtonListingElement items() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTabNavigatorWidgetItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        String str2 = json.label;
        if (str2 != null) {
            builder.label(str2);
        }
        SelectableTextButtonListingElement selectableTextButtonListingElement = json.items;
        if (selectableTextButtonListingElement != null) {
            builder.items(selectableTextButtonListingElement);
        }
        return builder.build();
    }

    public static ImmutableTabNavigatorWidgetItemElement copyOf(TabNavigatorWidgetItemElement tabNavigatorWidgetItemElement) {
        if (tabNavigatorWidgetItemElement instanceof ImmutableTabNavigatorWidgetItemElement) {
            return (ImmutableTabNavigatorWidgetItemElement) tabNavigatorWidgetItemElement;
        }
        return builder().from(tabNavigatorWidgetItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "TabNavigatorWidgetItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_LABEL = 1;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private long initBits;

        @Nullable
        private SelectableTextButtonListingElement items;

        @Nullable
        private String label;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 1L;
            this.onViewed = new ArrayList();
        }

        public final Builder from(TabNavigatorWidgetItemElement tabNavigatorWidgetItemElement) {
            ImmutableTabNavigatorWidgetItemElement.requireNonNull(tabNavigatorWidgetItemElement, "instance");
            from((Object) tabNavigatorWidgetItemElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableTabNavigatorWidgetItemElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof TabNavigatorWidgetItemElement) {
                TabNavigatorWidgetItemElement tabNavigatorWidgetItemElement = (TabNavigatorWidgetItemElement) obj;
                addAllOnViewed(tabNavigatorWidgetItemElement.onViewed());
                label(tabNavigatorWidgetItemElement.label());
                SelectableTextButtonListingElement selectableTextButtonListingElementItems = tabNavigatorWidgetItemElement.items();
                if (selectableTextButtonListingElementItems != null) {
                    items(selectableTextButtonListingElementItems);
                }
                widgetId(tabNavigatorWidgetItemElement.widgetId());
                j = 3;
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
            this.widgetId = (String) ImmutableTabNavigatorWidgetItemElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableTabNavigatorWidgetItemElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableTabNavigatorWidgetItemElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableTabNavigatorWidgetItemElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public final Builder label(String str) {
            this.label = (String) ImmutableTabNavigatorWidgetItemElement.requireNonNull(str, ContextMappingConstants.LABEL);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(@Nullable SelectableTextButtonListingElement selectableTextButtonListingElement) {
            this.items = selectableTextButtonListingElement;
            return this;
        }

        public ImmutableTabNavigatorWidgetItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableTabNavigatorWidgetItemElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.LABEL);
            }
            return "Cannot build TabNavigatorWidgetItemElement, some of required attributes are not set " + arrayList;
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
