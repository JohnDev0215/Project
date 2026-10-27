package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
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
@Generated(from = "DescriptiveShowcaseElement", generator = "Immutables")
public final class ImmutableDescriptiveShowcaseElement extends DescriptiveShowcaseElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;
    private final List<HorizontalItemElement> items;
    private final List<Method> onViewed;
    private final String primaryHeader;

    @Nullable
    private final String secondaryHeader;

    @Nullable
    private final SeeMoreItemElement seeMore;
    private final String widgetId;

    private ImmutableDescriptiveShowcaseElement(Builder builder) {
        this.initShim = new InitShim();
        this.primaryHeader = builder.primaryHeader;
        this.secondaryHeader = builder.secondaryHeader;
        this.items = createUnmodifiableList(true, builder.items);
        this.seeMore = builder.seeMore;
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

    private ImmutableDescriptiveShowcaseElement(String str, List<Method> list, String str2, @Nullable String str3, List<HorizontalItemElement> list2, @Nullable SeeMoreItemElement seeMoreItemElement) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.primaryHeader = str2;
        this.secondaryHeader = str3;
        this.items = list2;
        this.seeMore = seeMoreItemElement;
        this.initShim = null;
    }

    @Generated(from = "DescriptiveShowcaseElement", generator = "Immutables")
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
                this.widgetId = (String) ImmutableDescriptiveShowcaseElement.requireNonNull(ImmutableDescriptiveShowcaseElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableDescriptiveShowcaseElement.createUnmodifiableList(false, ImmutableDescriptiveShowcaseElement.createSafeList(ImmutableDescriptiveShowcaseElement.super.onViewed(), true, false));
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
            return "Cannot build DescriptiveShowcaseElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.ShowcaseElement
    @JsonProperty("primaryHeader")
    public String primaryHeader() {
        return this.primaryHeader;
    }

    @Override // Touch.WidgetsInterface.v1_0.ShowcaseElement
    @JsonProperty("secondaryHeader")
    @Nullable
    public String secondaryHeader() {
        return this.secondaryHeader;
    }

    @Override // Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<HorizontalItemElement> items() {
        return this.items;
    }

    @Override // Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement
    @JsonProperty(PageUriUtils.DO_SEE_MORE_VALUE)
    @Nullable
    public SeeMoreItemElement seeMore() {
        return this.seeMore;
    }

    public final ImmutableDescriptiveShowcaseElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableDescriptiveShowcaseElement(str2, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items, this.seeMore);
    }

    public final ImmutableDescriptiveShowcaseElement withOnViewed(Method... methodArr) {
        return new ImmutableDescriptiveShowcaseElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.primaryHeader, this.secondaryHeader, this.items, this.seeMore);
    }

    public final ImmutableDescriptiveShowcaseElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableDescriptiveShowcaseElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.primaryHeader, this.secondaryHeader, this.items, this.seeMore);
    }

    public final ImmutableDescriptiveShowcaseElement withPrimaryHeader(String str) {
        String str2 = (String) requireNonNull(str, "primaryHeader");
        return this.primaryHeader.equals(str2) ? this : new ImmutableDescriptiveShowcaseElement(this.widgetId, this.onViewed, str2, this.secondaryHeader, this.items, this.seeMore);
    }

    public final ImmutableDescriptiveShowcaseElement withSecondaryHeader(@Nullable String str) {
        return equals(this.secondaryHeader, str) ? this : new ImmutableDescriptiveShowcaseElement(this.widgetId, this.onViewed, this.primaryHeader, str, this.items, this.seeMore);
    }

    public final ImmutableDescriptiveShowcaseElement withItems(HorizontalItemElement... horizontalItemElementArr) {
        return new ImmutableDescriptiveShowcaseElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, createUnmodifiableList(false, createSafeList(Arrays.asList(horizontalItemElementArr), true, false)), this.seeMore);
    }

    public final ImmutableDescriptiveShowcaseElement withItems(Iterable<? extends HorizontalItemElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableDescriptiveShowcaseElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.seeMore);
    }

    public final ImmutableDescriptiveShowcaseElement withSeeMore(@Nullable SeeMoreItemElement seeMoreItemElement) {
        return this.seeMore == seeMoreItemElement ? this : new ImmutableDescriptiveShowcaseElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items, seeMoreItemElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableDescriptiveShowcaseElement) && equalTo(0, (ImmutableDescriptiveShowcaseElement) obj);
    }

    private boolean equalTo(int i, ImmutableDescriptiveShowcaseElement immutableDescriptiveShowcaseElement) {
        return this.widgetId.equals(immutableDescriptiveShowcaseElement.widgetId) && this.onViewed.equals(immutableDescriptiveShowcaseElement.onViewed) && this.primaryHeader.equals(immutableDescriptiveShowcaseElement.primaryHeader) && equals(this.secondaryHeader, immutableDescriptiveShowcaseElement.secondaryHeader) && this.items.equals(immutableDescriptiveShowcaseElement.items) && equals(this.seeMore, immutableDescriptiveShowcaseElement.seeMore);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.primaryHeader.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.secondaryHeader);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.items.hashCode();
        return iHashCode5 + (iHashCode5 << 5) + hashCode(this.seeMore);
    }

    public String toString() {
        return "DescriptiveShowcaseElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", primaryHeader=" + this.primaryHeader + ", secondaryHeader=" + this.secondaryHeader + ", items=" + this.items + ", seeMore=" + this.seeMore + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "DescriptiveShowcaseElement", generator = "Immutables")
    static final class Json extends DescriptiveShowcaseElement {
        boolean onViewedIsSet;

        @Nullable
        String primaryHeader;

        @Nullable
        String secondaryHeader;

        @Nullable
        SeeMoreItemElement seeMore;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<HorizontalItemElement> items = Collections.emptyList();

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
        public void setItems(List<HorizontalItemElement> list) {
            this.items = list;
        }

        @JsonProperty(PageUriUtils.DO_SEE_MORE_VALUE)
        public void setSeeMore(@Nullable SeeMoreItemElement seeMoreItemElement) {
            this.seeMore = seeMoreItemElement;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ShowcaseElement
        public String primaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ShowcaseElement
        public String secondaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement
        public List<HorizontalItemElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement
        public SeeMoreItemElement seeMore() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableDescriptiveShowcaseElement fromJson(Json json) {
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
        List<HorizontalItemElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        SeeMoreItemElement seeMoreItemElement = json.seeMore;
        if (seeMoreItemElement != null) {
            builder.seeMore(seeMoreItemElement);
        }
        return builder.build();
    }

    public static ImmutableDescriptiveShowcaseElement copyOf(DescriptiveShowcaseElement descriptiveShowcaseElement) {
        if (descriptiveShowcaseElement instanceof ImmutableDescriptiveShowcaseElement) {
            return (ImmutableDescriptiveShowcaseElement) descriptiveShowcaseElement;
        }
        return builder().from(descriptiveShowcaseElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "DescriptiveShowcaseElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_HEADER = 1;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private long initBits;
        private List<HorizontalItemElement> items;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String primaryHeader;

        @Nullable
        private String secondaryHeader;

        @Nullable
        private SeeMoreItemElement seeMore;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 1L;
            this.onViewed = new ArrayList();
            this.items = new ArrayList();
        }

        public final Builder from(DescriptiveShowcaseElement descriptiveShowcaseElement) {
            ImmutableDescriptiveShowcaseElement.requireNonNull(descriptiveShowcaseElement, "instance");
            from((Object) descriptiveShowcaseElement);
            return this;
        }

        public final Builder from(ShowcaseElement showcaseElement) {
            ImmutableDescriptiveShowcaseElement.requireNonNull(showcaseElement, "instance");
            from((Object) showcaseElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableDescriptiveShowcaseElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof DescriptiveShowcaseElement) {
                DescriptiveShowcaseElement descriptiveShowcaseElement = (DescriptiveShowcaseElement) obj;
                String strSecondaryHeader = descriptiveShowcaseElement.secondaryHeader();
                if (strSecondaryHeader != null) {
                    secondaryHeader(strSecondaryHeader);
                }
                SeeMoreItemElement seeMoreItemElementSeeMore = descriptiveShowcaseElement.seeMore();
                if (seeMoreItemElementSeeMore != null) {
                    seeMore(seeMoreItemElementSeeMore);
                }
                addAllOnViewed(descriptiveShowcaseElement.onViewed());
                addAllItems(descriptiveShowcaseElement.items());
                widgetId(descriptiveShowcaseElement.widgetId());
                primaryHeader(descriptiveShowcaseElement.primaryHeader());
                j = 15;
            } else {
                j = 0;
            }
            if (obj instanceof ShowcaseElement) {
                ShowcaseElement showcaseElement = (ShowcaseElement) obj;
                if ((j & 2) == 0) {
                    String strSecondaryHeader2 = showcaseElement.secondaryHeader();
                    if (strSecondaryHeader2 != null) {
                        secondaryHeader(strSecondaryHeader2);
                    }
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    addAllOnViewed(showcaseElement.onViewed());
                    j |= 4;
                }
                if ((j & 1) == 0) {
                    widgetId(showcaseElement.widgetId());
                    j |= 1;
                }
                if ((j & 8) == 0) {
                    primaryHeader(showcaseElement.primaryHeader());
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
            this.widgetId = (String) ImmutableDescriptiveShowcaseElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableDescriptiveShowcaseElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableDescriptiveShowcaseElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableDescriptiveShowcaseElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("primaryHeader")
        public final Builder primaryHeader(String str) {
            this.primaryHeader = (String) ImmutableDescriptiveShowcaseElement.requireNonNull(str, "primaryHeader");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("secondaryHeader")
        public final Builder secondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
            return this;
        }

        public final Builder addItems(HorizontalItemElement horizontalItemElement) {
            this.items.add((HorizontalItemElement) ImmutableDescriptiveShowcaseElement.requireNonNull(horizontalItemElement, "items element"));
            return this;
        }

        public final Builder addItems(HorizontalItemElement... horizontalItemElementArr) {
            for (HorizontalItemElement horizontalItemElement : horizontalItemElementArr) {
                this.items.add((HorizontalItemElement) ImmutableDescriptiveShowcaseElement.requireNonNull(horizontalItemElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends HorizontalItemElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends HorizontalItemElement> iterable) {
            Iterator<? extends HorizontalItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((HorizontalItemElement) ImmutableDescriptiveShowcaseElement.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        @JsonProperty(PageUriUtils.DO_SEE_MORE_VALUE)
        public final Builder seeMore(@Nullable SeeMoreItemElement seeMoreItemElement) {
            this.seeMore = seeMoreItemElement;
            return this;
        }

        public ImmutableDescriptiveShowcaseElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableDescriptiveShowcaseElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("primaryHeader");
            }
            return "Cannot build DescriptiveShowcaseElement, some of required attributes are not set " + arrayList;
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
