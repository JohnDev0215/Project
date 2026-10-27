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
@Generated(from = "LinkNavigatorElement", generator = "Immutables")
public final class ImmutableLinkNavigatorElement extends LinkNavigatorElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String header;
    private volatile transient InitShim initShim;
    private final List<LinkNavigatorItemElement> items;
    private final List<Method> onViewed;
    private final String widgetId;

    private ImmutableLinkNavigatorElement(Builder builder) {
        this.initShim = new InitShim();
        this.items = createUnmodifiableList(true, builder.items);
        this.header = builder.header;
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

    private ImmutableLinkNavigatorElement(String str, List<Method> list, List<LinkNavigatorItemElement> list2, @Nullable String str2) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.items = list2;
        this.header = str2;
        this.initShim = null;
    }

    @Generated(from = "LinkNavigatorElement", generator = "Immutables")
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
                this.widgetId = (String) ImmutableLinkNavigatorElement.requireNonNull(ImmutableLinkNavigatorElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableLinkNavigatorElement.createUnmodifiableList(false, ImmutableLinkNavigatorElement.createSafeList(ImmutableLinkNavigatorElement.super.onViewed(), true, false));
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
            return "Cannot build LinkNavigatorElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<LinkNavigatorItemElement> items() {
        return this.items;
    }

    @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorElement
    @JsonProperty("header")
    @Nullable
    public String header() {
        return this.header;
    }

    public final ImmutableLinkNavigatorElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableLinkNavigatorElement(str2, this.onViewed, this.items, this.header);
    }

    public final ImmutableLinkNavigatorElement withOnViewed(Method... methodArr) {
        return new ImmutableLinkNavigatorElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.items, this.header);
    }

    public final ImmutableLinkNavigatorElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableLinkNavigatorElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.items, this.header);
    }

    public final ImmutableLinkNavigatorElement withItems(LinkNavigatorItemElement... linkNavigatorItemElementArr) {
        return new ImmutableLinkNavigatorElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(Arrays.asList(linkNavigatorItemElementArr), true, false)), this.header);
    }

    public final ImmutableLinkNavigatorElement withItems(Iterable<? extends LinkNavigatorItemElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableLinkNavigatorElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.header);
    }

    public final ImmutableLinkNavigatorElement withHeader(@Nullable String str) {
        return equals(this.header, str) ? this : new ImmutableLinkNavigatorElement(this.widgetId, this.onViewed, this.items, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableLinkNavigatorElement) && equalTo(0, (ImmutableLinkNavigatorElement) obj);
    }

    private boolean equalTo(int i, ImmutableLinkNavigatorElement immutableLinkNavigatorElement) {
        return this.widgetId.equals(immutableLinkNavigatorElement.widgetId) && this.onViewed.equals(immutableLinkNavigatorElement.onViewed) && this.items.equals(immutableLinkNavigatorElement.items) && equals(this.header, immutableLinkNavigatorElement.header);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.items.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + hashCode(this.header);
    }

    public String toString() {
        return "LinkNavigatorElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", items=" + this.items + ", header=" + this.header + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "LinkNavigatorElement", generator = "Immutables")
    static final class Json extends LinkNavigatorElement {

        @Nullable
        String header;
        boolean onViewedIsSet;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<LinkNavigatorItemElement> items = Collections.emptyList();

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
        public void setItems(List<LinkNavigatorItemElement> list) {
            this.items = list;
        }

        @JsonProperty("header")
        public void setHeader(@Nullable String str) {
            this.header = str;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorElement
        public List<LinkNavigatorItemElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorElement
        public String header() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableLinkNavigatorElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        List<LinkNavigatorItemElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        String str2 = json.header;
        if (str2 != null) {
            builder.header(str2);
        }
        return builder.build();
    }

    public static ImmutableLinkNavigatorElement copyOf(LinkNavigatorElement linkNavigatorElement) {
        if (linkNavigatorElement instanceof ImmutableLinkNavigatorElement) {
            return (ImmutableLinkNavigatorElement) linkNavigatorElement;
        }
        return builder().from(linkNavigatorElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "LinkNavigatorElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private String header;
        private List<LinkNavigatorItemElement> items;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String widgetId;

        private Builder() {
            this.onViewed = new ArrayList();
            this.items = new ArrayList();
        }

        public final Builder from(LinkNavigatorElement linkNavigatorElement) {
            ImmutableLinkNavigatorElement.requireNonNull(linkNavigatorElement, "instance");
            from((Object) linkNavigatorElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableLinkNavigatorElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof LinkNavigatorElement) {
                LinkNavigatorElement linkNavigatorElement = (LinkNavigatorElement) obj;
                String strHeader = linkNavigatorElement.header();
                if (strHeader != null) {
                    header(strHeader);
                }
                addAllOnViewed(linkNavigatorElement.onViewed());
                addAllItems(linkNavigatorElement.items());
                widgetId(linkNavigatorElement.widgetId());
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
            this.widgetId = (String) ImmutableLinkNavigatorElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableLinkNavigatorElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableLinkNavigatorElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableLinkNavigatorElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addItems(LinkNavigatorItemElement linkNavigatorItemElement) {
            this.items.add((LinkNavigatorItemElement) ImmutableLinkNavigatorElement.requireNonNull(linkNavigatorItemElement, "items element"));
            return this;
        }

        public final Builder addItems(LinkNavigatorItemElement... linkNavigatorItemElementArr) {
            for (LinkNavigatorItemElement linkNavigatorItemElement : linkNavigatorItemElementArr) {
                this.items.add((LinkNavigatorItemElement) ImmutableLinkNavigatorElement.requireNonNull(linkNavigatorItemElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends LinkNavigatorItemElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends LinkNavigatorItemElement> iterable) {
            Iterator<? extends LinkNavigatorItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((LinkNavigatorItemElement) ImmutableLinkNavigatorElement.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        @JsonProperty("header")
        public final Builder header(@Nullable String str) {
            this.header = str;
            return this;
        }

        public ImmutableLinkNavigatorElement build() {
            return new ImmutableLinkNavigatorElement(this);
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
