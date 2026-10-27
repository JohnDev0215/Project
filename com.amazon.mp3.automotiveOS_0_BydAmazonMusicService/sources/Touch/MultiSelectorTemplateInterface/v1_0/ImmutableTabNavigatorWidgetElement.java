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
@Generated(from = "TabNavigatorWidgetElement", generator = "Immutables")
public final class ImmutableTabNavigatorWidgetElement extends TabNavigatorWidgetElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;
    private final List<TabNavigatorWidgetItemElement> items;
    private final List<Method> onViewed;
    private final Integer selectedItem;
    private final String widgetId;

    private ImmutableTabNavigatorWidgetElement(Builder builder) {
        this.initShim = new InitShim();
        this.items = createUnmodifiableList(true, builder.items);
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.selectedItem != null) {
            this.initShim.selectedItem(builder.selectedItem);
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.selectedItem = this.initShim.selectedItem();
        this.initShim = null;
    }

    private ImmutableTabNavigatorWidgetElement(String str, List<Method> list, List<TabNavigatorWidgetItemElement> list2, Integer num) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.items = list2;
        this.selectedItem = num;
        this.initShim = null;
    }

    @Generated(from = "TabNavigatorWidgetElement", generator = "Immutables")
    private final class InitShim {
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private Integer selectedItem;
        private byte selectedItemBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.selectedItemBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableTabNavigatorWidgetElement.requireNonNull(ImmutableTabNavigatorWidgetElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableTabNavigatorWidgetElement.createUnmodifiableList(false, ImmutableTabNavigatorWidgetElement.createSafeList(ImmutableTabNavigatorWidgetElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        Integer selectedItem() {
            byte b = this.selectedItemBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.selectedItemBuildStage = (byte) -1;
                this.selectedItem = (Integer) ImmutableTabNavigatorWidgetElement.requireNonNull(ImmutableTabNavigatorWidgetElement.super.selectedItem(), "selectedItem");
                this.selectedItemBuildStage = (byte) 1;
            }
            return this.selectedItem;
        }

        void selectedItem(Integer num) {
            this.selectedItem = num;
            this.selectedItemBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.selectedItemBuildStage == -1) {
                arrayList.add("selectedItem");
            }
            return "Cannot build TabNavigatorWidgetElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.TabNavigatorWidgetElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<TabNavigatorWidgetItemElement> items() {
        return this.items;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.TabNavigatorWidgetElement
    @JsonProperty("selectedItem")
    public Integer selectedItem() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.selectedItem();
        }
        return this.selectedItem;
    }

    public final ImmutableTabNavigatorWidgetElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableTabNavigatorWidgetElement(str2, this.onViewed, this.items, this.selectedItem);
    }

    public final ImmutableTabNavigatorWidgetElement withOnViewed(Method... methodArr) {
        return new ImmutableTabNavigatorWidgetElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.items, this.selectedItem);
    }

    public final ImmutableTabNavigatorWidgetElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableTabNavigatorWidgetElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.items, this.selectedItem);
    }

    public final ImmutableTabNavigatorWidgetElement withItems(TabNavigatorWidgetItemElement... tabNavigatorWidgetItemElementArr) {
        return new ImmutableTabNavigatorWidgetElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(Arrays.asList(tabNavigatorWidgetItemElementArr), true, false)), this.selectedItem);
    }

    public final ImmutableTabNavigatorWidgetElement withItems(Iterable<? extends TabNavigatorWidgetItemElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableTabNavigatorWidgetElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.selectedItem);
    }

    public final ImmutableTabNavigatorWidgetElement withSelectedItem(Integer num) {
        Integer num2 = (Integer) requireNonNull(num, "selectedItem");
        return this.selectedItem.equals(num2) ? this : new ImmutableTabNavigatorWidgetElement(this.widgetId, this.onViewed, this.items, num2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTabNavigatorWidgetElement) && equalTo(0, (ImmutableTabNavigatorWidgetElement) obj);
    }

    private boolean equalTo(int i, ImmutableTabNavigatorWidgetElement immutableTabNavigatorWidgetElement) {
        return this.widgetId.equals(immutableTabNavigatorWidgetElement.widgetId) && this.onViewed.equals(immutableTabNavigatorWidgetElement.onViewed) && this.items.equals(immutableTabNavigatorWidgetElement.items) && this.selectedItem.equals(immutableTabNavigatorWidgetElement.selectedItem);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.items.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.selectedItem.hashCode();
    }

    public String toString() {
        return "TabNavigatorWidgetElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", items=" + this.items + ", selectedItem=" + this.selectedItem + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "TabNavigatorWidgetElement", generator = "Immutables")
    static final class Json extends TabNavigatorWidgetElement {
        boolean onViewedIsSet;

        @Nullable
        Integer selectedItem;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<TabNavigatorWidgetItemElement> items = Collections.emptyList();

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

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(List<TabNavigatorWidgetItemElement> list) {
            this.items = list;
        }

        @JsonProperty("selectedItem")
        public void setSelectedItem(Integer num) {
            this.selectedItem = num;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.TabNavigatorWidgetElement
        public List<TabNavigatorWidgetItemElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.TabNavigatorWidgetElement
        public Integer selectedItem() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTabNavigatorWidgetElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        List<TabNavigatorWidgetItemElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        Integer num = json.selectedItem;
        if (num != null) {
            builder.selectedItem(num);
        }
        return builder.build();
    }

    public static ImmutableTabNavigatorWidgetElement copyOf(TabNavigatorWidgetElement tabNavigatorWidgetElement) {
        if (tabNavigatorWidgetElement instanceof ImmutableTabNavigatorWidgetElement) {
            return (ImmutableTabNavigatorWidgetElement) tabNavigatorWidgetElement;
        }
        return builder().from(tabNavigatorWidgetElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "TabNavigatorWidgetElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_VIEWED = 1;
        private List<TabNavigatorWidgetItemElement> items;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private Integer selectedItem;

        @Nullable
        private String widgetId;

        private Builder() {
            this.onViewed = new ArrayList();
            this.items = new ArrayList();
        }

        public final Builder from(TabNavigatorWidgetElement tabNavigatorWidgetElement) {
            ImmutableTabNavigatorWidgetElement.requireNonNull(tabNavigatorWidgetElement, "instance");
            from((Object) tabNavigatorWidgetElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableTabNavigatorWidgetElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof TabNavigatorWidgetElement) {
                TabNavigatorWidgetElement tabNavigatorWidgetElement = (TabNavigatorWidgetElement) obj;
                addAllOnViewed(tabNavigatorWidgetElement.onViewed());
                addAllItems(tabNavigatorWidgetElement.items());
                widgetId(tabNavigatorWidgetElement.widgetId());
                j = 3;
                selectedItem(tabNavigatorWidgetElement.selectedItem());
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
            this.widgetId = (String) ImmutableTabNavigatorWidgetElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableTabNavigatorWidgetElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableTabNavigatorWidgetElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableTabNavigatorWidgetElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addItems(TabNavigatorWidgetItemElement tabNavigatorWidgetItemElement) {
            this.items.add((TabNavigatorWidgetItemElement) ImmutableTabNavigatorWidgetElement.requireNonNull(tabNavigatorWidgetItemElement, "items element"));
            return this;
        }

        public final Builder addItems(TabNavigatorWidgetItemElement... tabNavigatorWidgetItemElementArr) {
            for (TabNavigatorWidgetItemElement tabNavigatorWidgetItemElement : tabNavigatorWidgetItemElementArr) {
                this.items.add((TabNavigatorWidgetItemElement) ImmutableTabNavigatorWidgetElement.requireNonNull(tabNavigatorWidgetItemElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends TabNavigatorWidgetItemElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends TabNavigatorWidgetItemElement> iterable) {
            Iterator<? extends TabNavigatorWidgetItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((TabNavigatorWidgetItemElement) ImmutableTabNavigatorWidgetElement.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        @JsonProperty("selectedItem")
        public final Builder selectedItem(Integer num) {
            this.selectedItem = (Integer) ImmutableTabNavigatorWidgetElement.requireNonNull(num, "selectedItem");
            return this;
        }

        public ImmutableTabNavigatorWidgetElement build() {
            return new ImmutableTabNavigatorWidgetElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
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
