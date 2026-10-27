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
@Generated(from = "ThumbnailNavigatorWidgetElement", generator = "Immutables")
public final class ImmutableThumbnailNavigatorWidgetElement extends ThumbnailNavigatorWidgetElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String header;
    private volatile transient InitShim initShim;
    private final List<ThumbnailNavigatorItemElement> items;
    private final List<Method> onViewed;

    @Nullable
    private final SeeMoreItemElement seeMore;
    private final String widgetId;

    private ImmutableThumbnailNavigatorWidgetElement(Builder builder) {
        this.initShim = new InitShim();
        this.header = builder.header;
        this.seeMore = builder.seeMore;
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

    private ImmutableThumbnailNavigatorWidgetElement(String str, List<Method> list, List<ThumbnailNavigatorItemElement> list2, @Nullable String str2, @Nullable SeeMoreItemElement seeMoreItemElement) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.items = list2;
        this.header = str2;
        this.seeMore = seeMoreItemElement;
        this.initShim = null;
    }

    @Generated(from = "ThumbnailNavigatorWidgetElement", generator = "Immutables")
    private final class InitShim {
        private List<ThumbnailNavigatorItemElement> items;
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
                this.widgetId = (String) ImmutableThumbnailNavigatorWidgetElement.requireNonNull(ImmutableThumbnailNavigatorWidgetElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableThumbnailNavigatorWidgetElement.createUnmodifiableList(false, ImmutableThumbnailNavigatorWidgetElement.createSafeList(ImmutableThumbnailNavigatorWidgetElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        List<ThumbnailNavigatorItemElement> items() {
            byte b = this.itemsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.itemsBuildStage = (byte) -1;
                this.items = ImmutableThumbnailNavigatorWidgetElement.createUnmodifiableList(false, ImmutableThumbnailNavigatorWidgetElement.createSafeList(ImmutableThumbnailNavigatorWidgetElement.super.items(), true, false));
                this.itemsBuildStage = (byte) 1;
            }
            return this.items;
        }

        void items(List<ThumbnailNavigatorItemElement> list) {
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
            return "Cannot build ThumbnailNavigatorWidgetElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorWidgetElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<ThumbnailNavigatorItemElement> items() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.items();
        }
        return this.items;
    }

    @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorWidgetElement
    @JsonProperty("header")
    @Nullable
    public String header() {
        return this.header;
    }

    @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorWidgetElement
    @JsonProperty(PageUriUtils.DO_SEE_MORE_VALUE)
    @Nullable
    public SeeMoreItemElement seeMore() {
        return this.seeMore;
    }

    public final ImmutableThumbnailNavigatorWidgetElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableThumbnailNavigatorWidgetElement(str2, this.onViewed, this.items, this.header, this.seeMore);
    }

    public final ImmutableThumbnailNavigatorWidgetElement withOnViewed(Method... methodArr) {
        return new ImmutableThumbnailNavigatorWidgetElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.items, this.header, this.seeMore);
    }

    public final ImmutableThumbnailNavigatorWidgetElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableThumbnailNavigatorWidgetElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.items, this.header, this.seeMore);
    }

    public final ImmutableThumbnailNavigatorWidgetElement withItems(ThumbnailNavigatorItemElement... thumbnailNavigatorItemElementArr) {
        return new ImmutableThumbnailNavigatorWidgetElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(Arrays.asList(thumbnailNavigatorItemElementArr), true, false)), this.header, this.seeMore);
    }

    public final ImmutableThumbnailNavigatorWidgetElement withItems(Iterable<? extends ThumbnailNavigatorItemElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableThumbnailNavigatorWidgetElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.header, this.seeMore);
    }

    public final ImmutableThumbnailNavigatorWidgetElement withHeader(@Nullable String str) {
        return equals(this.header, str) ? this : new ImmutableThumbnailNavigatorWidgetElement(this.widgetId, this.onViewed, this.items, str, this.seeMore);
    }

    public final ImmutableThumbnailNavigatorWidgetElement withSeeMore(@Nullable SeeMoreItemElement seeMoreItemElement) {
        return this.seeMore == seeMoreItemElement ? this : new ImmutableThumbnailNavigatorWidgetElement(this.widgetId, this.onViewed, this.items, this.header, seeMoreItemElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableThumbnailNavigatorWidgetElement) && equalTo(0, (ImmutableThumbnailNavigatorWidgetElement) obj);
    }

    private boolean equalTo(int i, ImmutableThumbnailNavigatorWidgetElement immutableThumbnailNavigatorWidgetElement) {
        return this.widgetId.equals(immutableThumbnailNavigatorWidgetElement.widgetId) && this.onViewed.equals(immutableThumbnailNavigatorWidgetElement.onViewed) && this.items.equals(immutableThumbnailNavigatorWidgetElement.items) && equals(this.header, immutableThumbnailNavigatorWidgetElement.header) && equals(this.seeMore, immutableThumbnailNavigatorWidgetElement.seeMore);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.items.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.header);
        return iHashCode4 + (iHashCode4 << 5) + hashCode(this.seeMore);
    }

    public String toString() {
        return "ThumbnailNavigatorWidgetElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", items=" + this.items + ", header=" + this.header + ", seeMore=" + this.seeMore + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ThumbnailNavigatorWidgetElement", generator = "Immutables")
    static final class Json extends ThumbnailNavigatorWidgetElement {

        @Nullable
        String header;
        boolean itemsIsSet;
        boolean onViewedIsSet;

        @Nullable
        SeeMoreItemElement seeMore;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<ThumbnailNavigatorItemElement> items = Collections.emptyList();

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
        public void setItems(List<ThumbnailNavigatorItemElement> list) {
            this.items = list;
            this.itemsIsSet = true;
        }

        @JsonProperty("header")
        public void setHeader(@Nullable String str) {
            this.header = str;
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

        @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorWidgetElement
        public List<ThumbnailNavigatorItemElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorWidgetElement
        public String header() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorWidgetElement
        public SeeMoreItemElement seeMore() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableThumbnailNavigatorWidgetElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        if (json.itemsIsSet) {
            builder.addAllItems(json.items);
        }
        String str2 = json.header;
        if (str2 != null) {
            builder.header(str2);
        }
        SeeMoreItemElement seeMoreItemElement = json.seeMore;
        if (seeMoreItemElement != null) {
            builder.seeMore(seeMoreItemElement);
        }
        return builder.build();
    }

    public static ImmutableThumbnailNavigatorWidgetElement copyOf(ThumbnailNavigatorWidgetElement thumbnailNavigatorWidgetElement) {
        if (thumbnailNavigatorWidgetElement instanceof ImmutableThumbnailNavigatorWidgetElement) {
            return (ImmutableThumbnailNavigatorWidgetElement) thumbnailNavigatorWidgetElement;
        }
        return builder().from(thumbnailNavigatorWidgetElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ThumbnailNavigatorWidgetElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ITEMS = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private String header;
        private List<ThumbnailNavigatorItemElement> items;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private SeeMoreItemElement seeMore;

        @Nullable
        private String widgetId;

        private Builder() {
            this.onViewed = new ArrayList();
            this.items = new ArrayList();
        }

        public final Builder from(ThumbnailNavigatorWidgetElement thumbnailNavigatorWidgetElement) {
            ImmutableThumbnailNavigatorWidgetElement.requireNonNull(thumbnailNavigatorWidgetElement, "instance");
            from((Object) thumbnailNavigatorWidgetElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableThumbnailNavigatorWidgetElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof ThumbnailNavigatorWidgetElement) {
                ThumbnailNavigatorWidgetElement thumbnailNavigatorWidgetElement = (ThumbnailNavigatorWidgetElement) obj;
                String strHeader = thumbnailNavigatorWidgetElement.header();
                if (strHeader != null) {
                    header(strHeader);
                }
                SeeMoreItemElement seeMoreItemElementSeeMore = thumbnailNavigatorWidgetElement.seeMore();
                if (seeMoreItemElementSeeMore != null) {
                    seeMore(seeMoreItemElementSeeMore);
                }
                addAllOnViewed(thumbnailNavigatorWidgetElement.onViewed());
                addAllItems(thumbnailNavigatorWidgetElement.items());
                widgetId(thumbnailNavigatorWidgetElement.widgetId());
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
            this.widgetId = (String) ImmutableThumbnailNavigatorWidgetElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableThumbnailNavigatorWidgetElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableThumbnailNavigatorWidgetElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableThumbnailNavigatorWidgetElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addItems(ThumbnailNavigatorItemElement thumbnailNavigatorItemElement) {
            this.items.add((ThumbnailNavigatorItemElement) ImmutableThumbnailNavigatorWidgetElement.requireNonNull(thumbnailNavigatorItemElement, "items element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addItems(ThumbnailNavigatorItemElement... thumbnailNavigatorItemElementArr) {
            for (ThumbnailNavigatorItemElement thumbnailNavigatorItemElement : thumbnailNavigatorItemElementArr) {
                this.items.add((ThumbnailNavigatorItemElement) ImmutableThumbnailNavigatorWidgetElement.requireNonNull(thumbnailNavigatorItemElement, "items element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends ThumbnailNavigatorItemElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends ThumbnailNavigatorItemElement> iterable) {
            Iterator<? extends ThumbnailNavigatorItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((ThumbnailNavigatorItemElement) ImmutableThumbnailNavigatorWidgetElement.requireNonNull(it.next(), "items element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("header")
        public final Builder header(@Nullable String str) {
            this.header = str;
            return this;
        }

        @JsonProperty(PageUriUtils.DO_SEE_MORE_VALUE)
        public final Builder seeMore(@Nullable SeeMoreItemElement seeMoreItemElement) {
            this.seeMore = seeMoreItemElement;
            return this;
        }

        public ImmutableThumbnailNavigatorWidgetElement build() {
            return new ImmutableThumbnailNavigatorWidgetElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean itemsIsSet() {
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
