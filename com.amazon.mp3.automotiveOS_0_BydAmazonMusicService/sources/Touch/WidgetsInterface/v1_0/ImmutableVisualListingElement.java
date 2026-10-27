package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
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
@Generated(from = "VisualListingElement", generator = "Immutables")
public final class ImmutableVisualListingElement extends VisualListingElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;
    private final List<VerticalItemElement> items;
    private final List<Method> onEndOfWidget;
    private final List<Method> onViewed;

    @Nullable
    private final String primaryHeader;

    @Nullable
    private final String secondaryHeader;
    private final String widgetId;

    private ImmutableVisualListingElement(Builder builder) {
        this.initShim = new InitShim();
        this.primaryHeader = builder.primaryHeader;
        this.secondaryHeader = builder.secondaryHeader;
        this.items = createUnmodifiableList(true, builder.items);
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.onEndOfWidgetIsSet()) {
            this.initShim.onEndOfWidget(createUnmodifiableList(true, builder.onEndOfWidget));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.onEndOfWidget = this.initShim.onEndOfWidget();
        this.initShim = null;
    }

    private ImmutableVisualListingElement(String str, List<Method> list, @Nullable String str2, @Nullable String str3, List<VerticalItemElement> list2, List<Method> list3) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.primaryHeader = str2;
        this.secondaryHeader = str3;
        this.items = list2;
        this.onEndOfWidget = list3;
        this.initShim = null;
    }

    @Generated(from = "VisualListingElement", generator = "Immutables")
    private final class InitShim {
        private List<Method> onEndOfWidget;
        private byte onEndOfWidgetBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.onEndOfWidgetBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableVisualListingElement.requireNonNull(ImmutableVisualListingElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableVisualListingElement.createUnmodifiableList(false, ImmutableVisualListingElement.createSafeList(ImmutableVisualListingElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        List<Method> onEndOfWidget() {
            byte b = this.onEndOfWidgetBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEndOfWidgetBuildStage = (byte) -1;
                this.onEndOfWidget = ImmutableVisualListingElement.createUnmodifiableList(false, ImmutableVisualListingElement.createSafeList(ImmutableVisualListingElement.super.onEndOfWidget(), true, false));
                this.onEndOfWidgetBuildStage = (byte) 1;
            }
            return this.onEndOfWidget;
        }

        void onEndOfWidget(List<Method> list) {
            this.onEndOfWidget = list;
            this.onEndOfWidgetBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.onEndOfWidgetBuildStage == -1) {
                arrayList.add("onEndOfWidget");
            }
            return "Cannot build VisualListingElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.ListingElement
    @JsonProperty("primaryHeader")
    @Nullable
    public String primaryHeader() {
        return this.primaryHeader;
    }

    @Override // Touch.WidgetsInterface.v1_0.ListingElement
    @JsonProperty("secondaryHeader")
    @Nullable
    public String secondaryHeader() {
        return this.secondaryHeader;
    }

    @Override // Touch.WidgetsInterface.v1_0.VisualListingElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<VerticalItemElement> items() {
        return this.items;
    }

    @Override // Touch.WidgetsInterface.v1_0.VisualListingElement
    @JsonProperty("onEndOfWidget")
    public List<Method> onEndOfWidget() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEndOfWidget();
        }
        return this.onEndOfWidget;
    }

    public final ImmutableVisualListingElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableVisualListingElement(str2, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items, this.onEndOfWidget);
    }

    public final ImmutableVisualListingElement withOnViewed(Method... methodArr) {
        return new ImmutableVisualListingElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.primaryHeader, this.secondaryHeader, this.items, this.onEndOfWidget);
    }

    public final ImmutableVisualListingElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableVisualListingElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.primaryHeader, this.secondaryHeader, this.items, this.onEndOfWidget);
    }

    public final ImmutableVisualListingElement withPrimaryHeader(@Nullable String str) {
        return equals(this.primaryHeader, str) ? this : new ImmutableVisualListingElement(this.widgetId, this.onViewed, str, this.secondaryHeader, this.items, this.onEndOfWidget);
    }

    public final ImmutableVisualListingElement withSecondaryHeader(@Nullable String str) {
        return equals(this.secondaryHeader, str) ? this : new ImmutableVisualListingElement(this.widgetId, this.onViewed, this.primaryHeader, str, this.items, this.onEndOfWidget);
    }

    public final ImmutableVisualListingElement withItems(VerticalItemElement... verticalItemElementArr) {
        return new ImmutableVisualListingElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, createUnmodifiableList(false, createSafeList(Arrays.asList(verticalItemElementArr), true, false)), this.onEndOfWidget);
    }

    public final ImmutableVisualListingElement withItems(Iterable<? extends VerticalItemElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableVisualListingElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onEndOfWidget);
    }

    public final ImmutableVisualListingElement withOnEndOfWidget(Method... methodArr) {
        return new ImmutableVisualListingElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableVisualListingElement withOnEndOfWidget(Iterable<? extends Method> iterable) {
        if (this.onEndOfWidget == iterable) {
            return this;
        }
        return new ImmutableVisualListingElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVisualListingElement) && equalTo(0, (ImmutableVisualListingElement) obj);
    }

    private boolean equalTo(int i, ImmutableVisualListingElement immutableVisualListingElement) {
        return this.widgetId.equals(immutableVisualListingElement.widgetId) && this.onViewed.equals(immutableVisualListingElement.onViewed) && equals(this.primaryHeader, immutableVisualListingElement.primaryHeader) && equals(this.secondaryHeader, immutableVisualListingElement.secondaryHeader) && this.items.equals(immutableVisualListingElement.items) && this.onEndOfWidget.equals(immutableVisualListingElement.onEndOfWidget);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.primaryHeader);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.secondaryHeader);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.items.hashCode();
        return iHashCode5 + (iHashCode5 << 5) + this.onEndOfWidget.hashCode();
    }

    public String toString() {
        return "VisualListingElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", primaryHeader=" + this.primaryHeader + ", secondaryHeader=" + this.secondaryHeader + ", items=" + this.items + ", onEndOfWidget=" + this.onEndOfWidget + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VisualListingElement", generator = "Immutables")
    static final class Json extends VisualListingElement {
        boolean onEndOfWidgetIsSet;
        boolean onViewedIsSet;

        @Nullable
        String primaryHeader;

        @Nullable
        String secondaryHeader;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<VerticalItemElement> items = Collections.emptyList();

        @Nullable
        List<Method> onEndOfWidget = Collections.emptyList();

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

        @JsonProperty("primaryHeader")
        public void setPrimaryHeader(@Nullable String str) {
            this.primaryHeader = str;
        }

        @JsonProperty("secondaryHeader")
        public void setSecondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(List<VerticalItemElement> list) {
            this.items = list;
        }

        @JsonProperty("onEndOfWidget")
        public void setOnEndOfWidget(List<Method> list) {
            this.onEndOfWidget = list;
            this.onEndOfWidgetIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ListingElement
        public String primaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ListingElement
        public String secondaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VisualListingElement
        public List<VerticalItemElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VisualListingElement
        public List<Method> onEndOfWidget() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVisualListingElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        String str2 = json.primaryHeader;
        if (str2 != null) {
            builder.primaryHeader(str2);
        }
        String str3 = json.secondaryHeader;
        if (str3 != null) {
            builder.secondaryHeader(str3);
        }
        List<VerticalItemElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        if (json.onEndOfWidgetIsSet) {
            builder.addAllOnEndOfWidget(json.onEndOfWidget);
        }
        return builder.build();
    }

    public static ImmutableVisualListingElement copyOf(VisualListingElement visualListingElement) {
        if (visualListingElement instanceof ImmutableVisualListingElement) {
            return (ImmutableVisualListingElement) visualListingElement;
        }
        return builder().from(visualListingElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VisualListingElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_END_OF_WIDGET = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private List<VerticalItemElement> items;
        private List<Method> onEndOfWidget;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String primaryHeader;

        @Nullable
        private String secondaryHeader;

        @Nullable
        private String widgetId;

        private Builder() {
            this.onViewed = new ArrayList();
            this.items = new ArrayList();
            this.onEndOfWidget = new ArrayList();
        }

        public final Builder from(VisualListingElement visualListingElement) {
            ImmutableVisualListingElement.requireNonNull(visualListingElement, "instance");
            from((Object) visualListingElement);
            return this;
        }

        public final Builder from(ListingElement listingElement) {
            ImmutableVisualListingElement.requireNonNull(listingElement, "instance");
            from((Object) listingElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableVisualListingElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof VisualListingElement) {
                VisualListingElement visualListingElement = (VisualListingElement) obj;
                addAllOnEndOfWidget(visualListingElement.onEndOfWidget());
                String strSecondaryHeader = visualListingElement.secondaryHeader();
                if (strSecondaryHeader != null) {
                    secondaryHeader(strSecondaryHeader);
                }
                addAllOnViewed(visualListingElement.onViewed());
                addAllItems(visualListingElement.items());
                widgetId(visualListingElement.widgetId());
                String strPrimaryHeader = visualListingElement.primaryHeader();
                if (strPrimaryHeader != null) {
                    primaryHeader(strPrimaryHeader);
                }
                j = 15;
            } else {
                j = 0;
            }
            if (obj instanceof ListingElement) {
                ListingElement listingElement = (ListingElement) obj;
                if ((j & 2) == 0) {
                    String strSecondaryHeader2 = listingElement.secondaryHeader();
                    if (strSecondaryHeader2 != null) {
                        secondaryHeader(strSecondaryHeader2);
                    }
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    addAllOnViewed(listingElement.onViewed());
                    j |= 4;
                }
                if ((j & 1) == 0) {
                    widgetId(listingElement.widgetId());
                    j |= 1;
                }
                if ((j & 8) == 0) {
                    String strPrimaryHeader2 = listingElement.primaryHeader();
                    if (strPrimaryHeader2 != null) {
                        primaryHeader(strPrimaryHeader2);
                    }
                    j |= 8;
                }
            }
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                if ((j & 1) == 0) {
                    widgetId(widgetElement.widgetId());
                    j |= 1;
                }
                if ((j & 4) == 0) {
                    addAllOnViewed(widgetElement.onViewed());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableVisualListingElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableVisualListingElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableVisualListingElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableVisualListingElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("primaryHeader")
        public final Builder primaryHeader(@Nullable String str) {
            this.primaryHeader = str;
            return this;
        }

        @JsonProperty("secondaryHeader")
        public final Builder secondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
            return this;
        }

        public final Builder addItems(VerticalItemElement verticalItemElement) {
            this.items.add((VerticalItemElement) ImmutableVisualListingElement.requireNonNull(verticalItemElement, "items element"));
            return this;
        }

        public final Builder addItems(VerticalItemElement... verticalItemElementArr) {
            for (VerticalItemElement verticalItemElement : verticalItemElementArr) {
                this.items.add((VerticalItemElement) ImmutableVisualListingElement.requireNonNull(verticalItemElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends VerticalItemElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends VerticalItemElement> iterable) {
            Iterator<? extends VerticalItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((VerticalItemElement) ImmutableVisualListingElement.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        public final Builder addOnEndOfWidget(Method method) {
            this.onEndOfWidget.add((Method) ImmutableVisualListingElement.requireNonNull(method, "onEndOfWidget element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnEndOfWidget(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEndOfWidget.add((Method) ImmutableVisualListingElement.requireNonNull(method, "onEndOfWidget element"));
            }
            this.optBits |= 2;
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
                this.onEndOfWidget.add((Method) ImmutableVisualListingElement.requireNonNull(it.next(), "onEndOfWidget element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableVisualListingElement build() {
            return new ImmutableVisualListingElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEndOfWidgetIsSet() {
            return (this.optBits & 2) != 0;
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
