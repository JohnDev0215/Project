package Touch.MultiSelectorTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
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
@Generated(from = "SelectableTextButtonListingElement", generator = "Immutables")
public final class ImmutableSelectableTextButtonListingElement extends SelectableTextButtonListingElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;
    private final boolean isSearchable;
    private final List<TextButtonElement> items;
    private final List<Method> onEndOfWidget;
    private final List<Method> onViewed;
    private final String widgetId;

    private ImmutableSelectableTextButtonListingElement(Builder builder) {
        this.initShim = new InitShim();
        this.items = createUnmodifiableList(true, builder.items);
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.isSearchableIsSet()) {
            this.initShim.isSearchable(builder.isSearchable);
        }
        if (builder.onEndOfWidgetIsSet()) {
            this.initShim.onEndOfWidget(createUnmodifiableList(true, builder.onEndOfWidget));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.isSearchable = this.initShim.isSearchable();
        this.onEndOfWidget = this.initShim.onEndOfWidget();
        this.initShim = null;
    }

    private ImmutableSelectableTextButtonListingElement(String str, List<Method> list, List<TextButtonElement> list2, boolean z, List<Method> list3) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.items = list2;
        this.isSearchable = z;
        this.onEndOfWidget = list3;
        this.initShim = null;
    }

    @Generated(from = "SelectableTextButtonListingElement", generator = "Immutables")
    private final class InitShim {
        private boolean isSearchable;
        private byte isSearchableBuildStage;
        private List<Method> onEndOfWidget;
        private byte onEndOfWidgetBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.isSearchableBuildStage = (byte) 0;
            this.onEndOfWidgetBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableSelectableTextButtonListingElement.requireNonNull(ImmutableSelectableTextButtonListingElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableSelectableTextButtonListingElement.createUnmodifiableList(false, ImmutableSelectableTextButtonListingElement.createSafeList(ImmutableSelectableTextButtonListingElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        boolean isSearchable() {
            byte b = this.isSearchableBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isSearchableBuildStage = (byte) -1;
                this.isSearchable = ImmutableSelectableTextButtonListingElement.super.isSearchable();
                this.isSearchableBuildStage = (byte) 1;
            }
            return this.isSearchable;
        }

        void isSearchable(boolean z) {
            this.isSearchable = z;
            this.isSearchableBuildStage = (byte) 1;
        }

        List<Method> onEndOfWidget() {
            byte b = this.onEndOfWidgetBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEndOfWidgetBuildStage = (byte) -1;
                this.onEndOfWidget = ImmutableSelectableTextButtonListingElement.createUnmodifiableList(false, ImmutableSelectableTextButtonListingElement.createSafeList(ImmutableSelectableTextButtonListingElement.super.onEndOfWidget(), true, false));
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
            if (this.isSearchableBuildStage == -1) {
                arrayList.add("isSearchable");
            }
            if (this.onEndOfWidgetBuildStage == -1) {
                arrayList.add("onEndOfWidget");
            }
            return "Cannot build SelectableTextButtonListingElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.SelectableTextButtonListingElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<TextButtonElement> items() {
        return this.items;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.SelectableTextButtonListingElement
    @JsonProperty("isSearchable")
    public boolean isSearchable() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isSearchable();
        }
        return this.isSearchable;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.SelectableTextButtonListingElement
    @JsonProperty("onEndOfWidget")
    public List<Method> onEndOfWidget() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEndOfWidget();
        }
        return this.onEndOfWidget;
    }

    public final ImmutableSelectableTextButtonListingElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableSelectableTextButtonListingElement(str2, this.onViewed, this.items, this.isSearchable, this.onEndOfWidget);
    }

    public final ImmutableSelectableTextButtonListingElement withOnViewed(Method... methodArr) {
        return new ImmutableSelectableTextButtonListingElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.items, this.isSearchable, this.onEndOfWidget);
    }

    public final ImmutableSelectableTextButtonListingElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableSelectableTextButtonListingElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.items, this.isSearchable, this.onEndOfWidget);
    }

    public final ImmutableSelectableTextButtonListingElement withItems(TextButtonElement... textButtonElementArr) {
        return new ImmutableSelectableTextButtonListingElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(Arrays.asList(textButtonElementArr), true, false)), this.isSearchable, this.onEndOfWidget);
    }

    public final ImmutableSelectableTextButtonListingElement withItems(Iterable<? extends TextButtonElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableSelectableTextButtonListingElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.isSearchable, this.onEndOfWidget);
    }

    public final ImmutableSelectableTextButtonListingElement withIsSearchable(boolean z) {
        return this.isSearchable == z ? this : new ImmutableSelectableTextButtonListingElement(this.widgetId, this.onViewed, this.items, z, this.onEndOfWidget);
    }

    public final ImmutableSelectableTextButtonListingElement withOnEndOfWidget(Method... methodArr) {
        return new ImmutableSelectableTextButtonListingElement(this.widgetId, this.onViewed, this.items, this.isSearchable, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSelectableTextButtonListingElement withOnEndOfWidget(Iterable<? extends Method> iterable) {
        if (this.onEndOfWidget == iterable) {
            return this;
        }
        return new ImmutableSelectableTextButtonListingElement(this.widgetId, this.onViewed, this.items, this.isSearchable, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSelectableTextButtonListingElement) && equalTo(0, (ImmutableSelectableTextButtonListingElement) obj);
    }

    private boolean equalTo(int i, ImmutableSelectableTextButtonListingElement immutableSelectableTextButtonListingElement) {
        return this.widgetId.equals(immutableSelectableTextButtonListingElement.widgetId) && this.onViewed.equals(immutableSelectableTextButtonListingElement.onViewed) && this.items.equals(immutableSelectableTextButtonListingElement.items) && this.isSearchable == immutableSelectableTextButtonListingElement.isSearchable && this.onEndOfWidget.equals(immutableSelectableTextButtonListingElement.onEndOfWidget);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.items.hashCode();
        int i = iHashCode3 + (iHashCode3 << 5) + (this.isSearchable ? 1231 : 1237);
        return i + (i << 5) + this.onEndOfWidget.hashCode();
    }

    public String toString() {
        return "SelectableTextButtonListingElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", items=" + this.items + ", isSearchable=" + this.isSearchable + ", onEndOfWidget=" + this.onEndOfWidget + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SelectableTextButtonListingElement", generator = "Immutables")
    static final class Json extends SelectableTextButtonListingElement {
        boolean isSearchable;
        boolean isSearchableIsSet;
        boolean onEndOfWidgetIsSet;
        boolean onViewedIsSet;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<TextButtonElement> items = Collections.emptyList();

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

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(List<TextButtonElement> list) {
            this.items = list;
        }

        @JsonProperty("isSearchable")
        public void setIsSearchable(boolean z) {
            this.isSearchable = z;
            this.isSearchableIsSet = true;
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

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.SelectableTextButtonListingElement
        public List<TextButtonElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.SelectableTextButtonListingElement
        public boolean isSearchable() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.SelectableTextButtonListingElement
        public List<Method> onEndOfWidget() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSelectableTextButtonListingElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        List<TextButtonElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        if (json.isSearchableIsSet) {
            builder.isSearchable(json.isSearchable);
        }
        if (json.onEndOfWidgetIsSet) {
            builder.addAllOnEndOfWidget(json.onEndOfWidget);
        }
        return builder.build();
    }

    public static ImmutableSelectableTextButtonListingElement copyOf(SelectableTextButtonListingElement selectableTextButtonListingElement) {
        if (selectableTextButtonListingElement instanceof ImmutableSelectableTextButtonListingElement) {
            return (ImmutableSelectableTextButtonListingElement) selectableTextButtonListingElement;
        }
        return builder().from(selectableTextButtonListingElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SelectableTextButtonListingElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_IS_SEARCHABLE = 2;
        private static final long OPT_BIT_ON_END_OF_WIDGET = 4;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private boolean isSearchable;
        private List<TextButtonElement> items;
        private List<Method> onEndOfWidget;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String widgetId;

        private Builder() {
            this.onViewed = new ArrayList();
            this.items = new ArrayList();
            this.onEndOfWidget = new ArrayList();
        }

        public final Builder from(SelectableTextButtonListingElement selectableTextButtonListingElement) {
            ImmutableSelectableTextButtonListingElement.requireNonNull(selectableTextButtonListingElement, "instance");
            from((Object) selectableTextButtonListingElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableSelectableTextButtonListingElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof SelectableTextButtonListingElement) {
                SelectableTextButtonListingElement selectableTextButtonListingElement = (SelectableTextButtonListingElement) obj;
                addAllOnEndOfWidget(selectableTextButtonListingElement.onEndOfWidget());
                addAllOnViewed(selectableTextButtonListingElement.onViewed());
                isSearchable(selectableTextButtonListingElement.isSearchable());
                addAllItems(selectableTextButtonListingElement.items());
                widgetId(selectableTextButtonListingElement.widgetId());
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
            this.widgetId = (String) ImmutableSelectableTextButtonListingElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableSelectableTextButtonListingElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableSelectableTextButtonListingElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableSelectableTextButtonListingElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addItems(TextButtonElement textButtonElement) {
            this.items.add((TextButtonElement) ImmutableSelectableTextButtonListingElement.requireNonNull(textButtonElement, "items element"));
            return this;
        }

        public final Builder addItems(TextButtonElement... textButtonElementArr) {
            for (TextButtonElement textButtonElement : textButtonElementArr) {
                this.items.add((TextButtonElement) ImmutableSelectableTextButtonListingElement.requireNonNull(textButtonElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends TextButtonElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends TextButtonElement> iterable) {
            Iterator<? extends TextButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((TextButtonElement) ImmutableSelectableTextButtonListingElement.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        @JsonProperty("isSearchable")
        public final Builder isSearchable(boolean z) {
            this.isSearchable = z;
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnEndOfWidget(Method method) {
            this.onEndOfWidget.add((Method) ImmutableSelectableTextButtonListingElement.requireNonNull(method, "onEndOfWidget element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnEndOfWidget(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEndOfWidget.add((Method) ImmutableSelectableTextButtonListingElement.requireNonNull(method, "onEndOfWidget element"));
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
                this.onEndOfWidget.add((Method) ImmutableSelectableTextButtonListingElement.requireNonNull(it.next(), "onEndOfWidget element"));
            }
            this.optBits |= 4;
            return this;
        }

        public ImmutableSelectableTextButtonListingElement build() {
            return new ImmutableSelectableTextButtonListingElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isSearchableIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEndOfWidgetIsSet() {
            return (this.optBits & 4) != 0;
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
