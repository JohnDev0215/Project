package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Element;
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
@Generated(from = "SectionElement", generator = "Immutables")
public final class ImmutableSectionElement extends SectionElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;
    private final List<Element> items;
    private final List<Method> onViewed;
    private final String primaryHeader;

    @Nullable
    private final String secondaryHeader;
    private final String widgetId;

    private ImmutableSectionElement(Builder builder) {
        this.initShim = new InitShim();
        this.primaryHeader = builder.primaryHeader;
        this.secondaryHeader = builder.secondaryHeader;
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.itemsIsSet()) {
            this.initShim.items(createUnmodifiableList(true, builder.items));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.items = this.initShim.items();
        this.initShim = null;
    }

    private ImmutableSectionElement(String str, List<Method> list, String str2, @Nullable String str3, List<Element> list2) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.primaryHeader = str2;
        this.secondaryHeader = str3;
        this.items = list2;
        this.initShim = null;
    }

    @Generated(from = "SectionElement", generator = "Immutables")
    private final class InitShim {
        private List<Element> items;
        private byte itemsBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.itemsBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableSectionElement.requireNonNull(ImmutableSectionElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableSectionElement.createUnmodifiableList(false, ImmutableSectionElement.createSafeList(ImmutableSectionElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        List<Element> items() {
            byte b = this.itemsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.itemsBuildStage = (byte) -1;
                this.items = ImmutableSectionElement.createUnmodifiableList(false, ImmutableSectionElement.createSafeList(ImmutableSectionElement.super.items(), true, false));
                this.itemsBuildStage = (byte) 1;
            }
            return this.items;
        }

        void items(List<Element> list) {
            this.items = list;
            this.itemsBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.itemsBuildStage == -1) {
                arrayList.add(ContextMappingConstants.ITEMS);
            }
            return "Cannot build SectionElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.SectionElement
    @JsonProperty("primaryHeader")
    public String primaryHeader() {
        return this.primaryHeader;
    }

    @Override // Touch.WidgetsInterface.v1_0.SectionElement
    @JsonProperty("secondaryHeader")
    @Nullable
    public String secondaryHeader() {
        return this.secondaryHeader;
    }

    @Override // Touch.WidgetsInterface.v1_0.SectionElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<Element> items() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.items();
        }
        return this.items;
    }

    public final ImmutableSectionElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableSectionElement(str2, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items);
    }

    public final ImmutableSectionElement withOnViewed(Method... methodArr) {
        return new ImmutableSectionElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.primaryHeader, this.secondaryHeader, this.items);
    }

    public final ImmutableSectionElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableSectionElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.primaryHeader, this.secondaryHeader, this.items);
    }

    public final ImmutableSectionElement withPrimaryHeader(String str) {
        String str2 = (String) requireNonNull(str, "primaryHeader");
        return this.primaryHeader.equals(str2) ? this : new ImmutableSectionElement(this.widgetId, this.onViewed, str2, this.secondaryHeader, this.items);
    }

    public final ImmutableSectionElement withSecondaryHeader(@Nullable String str) {
        return equals(this.secondaryHeader, str) ? this : new ImmutableSectionElement(this.widgetId, this.onViewed, this.primaryHeader, str, this.items);
    }

    public final ImmutableSectionElement withItems(Element... elementArr) {
        return new ImmutableSectionElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, createUnmodifiableList(false, createSafeList(Arrays.asList(elementArr), true, false)));
    }

    public final ImmutableSectionElement withItems(Iterable<? extends Element> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableSectionElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSectionElement) && equalTo(0, (ImmutableSectionElement) obj);
    }

    private boolean equalTo(int i, ImmutableSectionElement immutableSectionElement) {
        return this.widgetId.equals(immutableSectionElement.widgetId) && this.onViewed.equals(immutableSectionElement.onViewed) && this.primaryHeader.equals(immutableSectionElement.primaryHeader) && equals(this.secondaryHeader, immutableSectionElement.secondaryHeader) && this.items.equals(immutableSectionElement.items);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.primaryHeader.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.secondaryHeader);
        return iHashCode4 + (iHashCode4 << 5) + this.items.hashCode();
    }

    public String toString() {
        return "SectionElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", primaryHeader=" + this.primaryHeader + ", secondaryHeader=" + this.secondaryHeader + ", items=" + this.items + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SectionElement", generator = "Immutables")
    static final class Json extends SectionElement {
        boolean itemsIsSet;
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
        List<Element> items = Collections.emptyList();

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
        public void setPrimaryHeader(String str) {
            this.primaryHeader = str;
        }

        @JsonProperty("secondaryHeader")
        public void setSecondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(List<Element> list) {
            this.items = list;
            this.itemsIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SectionElement
        public String primaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SectionElement
        public String secondaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SectionElement
        public List<Element> items() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSectionElement fromJson(Json json) {
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
        if (json.itemsIsSet) {
            builder.addAllItems(json.items);
        }
        return builder.build();
    }

    public static ImmutableSectionElement copyOf(SectionElement sectionElement) {
        if (sectionElement instanceof ImmutableSectionElement) {
            return (ImmutableSectionElement) sectionElement;
        }
        return builder().from(sectionElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SectionElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_HEADER = 1;
        private static final long OPT_BIT_ITEMS = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private long initBits;
        private List<Element> items;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String primaryHeader;

        @Nullable
        private String secondaryHeader;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 1L;
            this.onViewed = new ArrayList();
            this.items = new ArrayList();
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableSectionElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        public final Builder from(SectionElement sectionElement) {
            ImmutableSectionElement.requireNonNull(sectionElement, "instance");
            from((Object) sectionElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                widgetId(widgetElement.widgetId());
                addAllOnViewed(widgetElement.onViewed());
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof SectionElement) {
                SectionElement sectionElement = (SectionElement) obj;
                String strSecondaryHeader = sectionElement.secondaryHeader();
                if (strSecondaryHeader != null) {
                    secondaryHeader(strSecondaryHeader);
                }
                if ((j & 2) == 0) {
                    addAllOnViewed(sectionElement.onViewed());
                    j |= 2;
                }
                addAllItems(sectionElement.items());
                if ((j & 1) == 0) {
                    widgetId(sectionElement.widgetId());
                }
                primaryHeader(sectionElement.primaryHeader());
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableSectionElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableSectionElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableSectionElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableSectionElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("primaryHeader")
        public final Builder primaryHeader(String str) {
            this.primaryHeader = (String) ImmutableSectionElement.requireNonNull(str, "primaryHeader");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("secondaryHeader")
        public final Builder secondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
            return this;
        }

        public final Builder addItems(Element element) {
            this.items.add((Element) ImmutableSectionElement.requireNonNull(element, "items element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addItems(Element... elementArr) {
            for (Element element : elementArr) {
                this.items.add((Element) ImmutableSectionElement.requireNonNull(element, "items element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends Element> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends Element> iterable) {
            Iterator<? extends Element> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((Element) ImmutableSectionElement.requireNonNull(it.next(), "items element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableSectionElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSectionElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean itemsIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("primaryHeader");
            }
            return "Cannot build SectionElement, some of required attributes are not set " + arrayList;
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
