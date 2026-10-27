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
@Generated(from = "PillNavigatorWidgetElement", generator = "Immutables")
public final class ImmutablePillNavigatorWidgetElement extends PillNavigatorWidgetElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String header;
    private volatile transient InitShim initShim;
    private final List<PillNavigatorWidgetItemElement> items;
    private final List<Method> onViewed;

    @Nullable
    private final PillNavigatorWidgetSeeMoreItemElement seeMore;
    private final String widgetId;

    private ImmutablePillNavigatorWidgetElement(Builder builder) {
        this.initShim = new InitShim();
        this.items = createUnmodifiableList(true, builder.items);
        this.header = builder.header;
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

    private ImmutablePillNavigatorWidgetElement(String str, List<Method> list, List<PillNavigatorWidgetItemElement> list2, @Nullable String str2, @Nullable PillNavigatorWidgetSeeMoreItemElement pillNavigatorWidgetSeeMoreItemElement) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.items = list2;
        this.header = str2;
        this.seeMore = pillNavigatorWidgetSeeMoreItemElement;
        this.initShim = null;
    }

    @Generated(from = "PillNavigatorWidgetElement", generator = "Immutables")
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
                this.widgetId = (String) ImmutablePillNavigatorWidgetElement.requireNonNull(ImmutablePillNavigatorWidgetElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutablePillNavigatorWidgetElement.createUnmodifiableList(false, ImmutablePillNavigatorWidgetElement.createSafeList(ImmutablePillNavigatorWidgetElement.super.onViewed(), true, false));
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
            return "Cannot build PillNavigatorWidgetElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.PillNavigatorWidgetElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<PillNavigatorWidgetItemElement> items() {
        return this.items;
    }

    @Override // Touch.WidgetsInterface.v1_0.PillNavigatorWidgetElement
    @JsonProperty("header")
    @Nullable
    public String header() {
        return this.header;
    }

    @Override // Touch.WidgetsInterface.v1_0.PillNavigatorWidgetElement
    @JsonProperty(PageUriUtils.DO_SEE_MORE_VALUE)
    @Nullable
    public PillNavigatorWidgetSeeMoreItemElement seeMore() {
        return this.seeMore;
    }

    public final ImmutablePillNavigatorWidgetElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutablePillNavigatorWidgetElement(str2, this.onViewed, this.items, this.header, this.seeMore);
    }

    public final ImmutablePillNavigatorWidgetElement withOnViewed(Method... methodArr) {
        return new ImmutablePillNavigatorWidgetElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.items, this.header, this.seeMore);
    }

    public final ImmutablePillNavigatorWidgetElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutablePillNavigatorWidgetElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.items, this.header, this.seeMore);
    }

    public final ImmutablePillNavigatorWidgetElement withItems(PillNavigatorWidgetItemElement... pillNavigatorWidgetItemElementArr) {
        return new ImmutablePillNavigatorWidgetElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(Arrays.asList(pillNavigatorWidgetItemElementArr), true, false)), this.header, this.seeMore);
    }

    public final ImmutablePillNavigatorWidgetElement withItems(Iterable<? extends PillNavigatorWidgetItemElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutablePillNavigatorWidgetElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.header, this.seeMore);
    }

    public final ImmutablePillNavigatorWidgetElement withHeader(@Nullable String str) {
        return equals(this.header, str) ? this : new ImmutablePillNavigatorWidgetElement(this.widgetId, this.onViewed, this.items, str, this.seeMore);
    }

    public final ImmutablePillNavigatorWidgetElement withSeeMore(@Nullable PillNavigatorWidgetSeeMoreItemElement pillNavigatorWidgetSeeMoreItemElement) {
        return this.seeMore == pillNavigatorWidgetSeeMoreItemElement ? this : new ImmutablePillNavigatorWidgetElement(this.widgetId, this.onViewed, this.items, this.header, pillNavigatorWidgetSeeMoreItemElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePillNavigatorWidgetElement) && equalTo(0, (ImmutablePillNavigatorWidgetElement) obj);
    }

    private boolean equalTo(int i, ImmutablePillNavigatorWidgetElement immutablePillNavigatorWidgetElement) {
        return this.widgetId.equals(immutablePillNavigatorWidgetElement.widgetId) && this.onViewed.equals(immutablePillNavigatorWidgetElement.onViewed) && this.items.equals(immutablePillNavigatorWidgetElement.items) && equals(this.header, immutablePillNavigatorWidgetElement.header) && equals(this.seeMore, immutablePillNavigatorWidgetElement.seeMore);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.items.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.header);
        return iHashCode4 + (iHashCode4 << 5) + hashCode(this.seeMore);
    }

    public String toString() {
        return "PillNavigatorWidgetElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", items=" + this.items + ", header=" + this.header + ", seeMore=" + this.seeMore + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PillNavigatorWidgetElement", generator = "Immutables")
    static final class Json extends PillNavigatorWidgetElement {

        @Nullable
        String header;
        boolean onViewedIsSet;

        @Nullable
        PillNavigatorWidgetSeeMoreItemElement seeMore;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<PillNavigatorWidgetItemElement> items = Collections.emptyList();

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
        public void setItems(List<PillNavigatorWidgetItemElement> list) {
            this.items = list;
        }

        @JsonProperty("header")
        public void setHeader(@Nullable String str) {
            this.header = str;
        }

        @JsonProperty(PageUriUtils.DO_SEE_MORE_VALUE)
        public void setSeeMore(@Nullable PillNavigatorWidgetSeeMoreItemElement pillNavigatorWidgetSeeMoreItemElement) {
            this.seeMore = pillNavigatorWidgetSeeMoreItemElement;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.PillNavigatorWidgetElement
        public List<PillNavigatorWidgetItemElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.PillNavigatorWidgetElement
        public String header() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.PillNavigatorWidgetElement
        public PillNavigatorWidgetSeeMoreItemElement seeMore() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePillNavigatorWidgetElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        List<PillNavigatorWidgetItemElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        String str2 = json.header;
        if (str2 != null) {
            builder.header(str2);
        }
        PillNavigatorWidgetSeeMoreItemElement pillNavigatorWidgetSeeMoreItemElement = json.seeMore;
        if (pillNavigatorWidgetSeeMoreItemElement != null) {
            builder.seeMore(pillNavigatorWidgetSeeMoreItemElement);
        }
        return builder.build();
    }

    public static ImmutablePillNavigatorWidgetElement copyOf(PillNavigatorWidgetElement pillNavigatorWidgetElement) {
        if (pillNavigatorWidgetElement instanceof ImmutablePillNavigatorWidgetElement) {
            return (ImmutablePillNavigatorWidgetElement) pillNavigatorWidgetElement;
        }
        return builder().from(pillNavigatorWidgetElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PillNavigatorWidgetElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private String header;
        private List<PillNavigatorWidgetItemElement> items;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private PillNavigatorWidgetSeeMoreItemElement seeMore;

        @Nullable
        private String widgetId;

        private Builder() {
            this.onViewed = new ArrayList();
            this.items = new ArrayList();
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutablePillNavigatorWidgetElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        public final Builder from(PillNavigatorWidgetElement pillNavigatorWidgetElement) {
            ImmutablePillNavigatorWidgetElement.requireNonNull(pillNavigatorWidgetElement, "instance");
            from((Object) pillNavigatorWidgetElement);
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
            if (obj instanceof PillNavigatorWidgetElement) {
                PillNavigatorWidgetElement pillNavigatorWidgetElement = (PillNavigatorWidgetElement) obj;
                String strHeader = pillNavigatorWidgetElement.header();
                if (strHeader != null) {
                    header(strHeader);
                }
                PillNavigatorWidgetSeeMoreItemElement pillNavigatorWidgetSeeMoreItemElementSeeMore = pillNavigatorWidgetElement.seeMore();
                if (pillNavigatorWidgetSeeMoreItemElementSeeMore != null) {
                    seeMore(pillNavigatorWidgetSeeMoreItemElementSeeMore);
                }
                if ((j & 2) == 0) {
                    addAllOnViewed(pillNavigatorWidgetElement.onViewed());
                    j |= 2;
                }
                addAllItems(pillNavigatorWidgetElement.items());
                if ((j & 1) == 0) {
                    widgetId(pillNavigatorWidgetElement.widgetId());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutablePillNavigatorWidgetElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutablePillNavigatorWidgetElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutablePillNavigatorWidgetElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutablePillNavigatorWidgetElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addItems(PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
            this.items.add((PillNavigatorWidgetItemElement) ImmutablePillNavigatorWidgetElement.requireNonNull(pillNavigatorWidgetItemElement, "items element"));
            return this;
        }

        public final Builder addItems(PillNavigatorWidgetItemElement... pillNavigatorWidgetItemElementArr) {
            for (PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement : pillNavigatorWidgetItemElementArr) {
                this.items.add((PillNavigatorWidgetItemElement) ImmutablePillNavigatorWidgetElement.requireNonNull(pillNavigatorWidgetItemElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends PillNavigatorWidgetItemElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends PillNavigatorWidgetItemElement> iterable) {
            Iterator<? extends PillNavigatorWidgetItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((PillNavigatorWidgetItemElement) ImmutablePillNavigatorWidgetElement.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        @JsonProperty("header")
        public final Builder header(@Nullable String str) {
            this.header = str;
            return this;
        }

        @JsonProperty(PageUriUtils.DO_SEE_MORE_VALUE)
        public final Builder seeMore(@Nullable PillNavigatorWidgetSeeMoreItemElement pillNavigatorWidgetSeeMoreItemElement) {
            this.seeMore = pillNavigatorWidgetSeeMoreItemElement;
            return this;
        }

        public ImmutablePillNavigatorWidgetElement build() {
            return new ImmutablePillNavigatorWidgetElement(this);
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
