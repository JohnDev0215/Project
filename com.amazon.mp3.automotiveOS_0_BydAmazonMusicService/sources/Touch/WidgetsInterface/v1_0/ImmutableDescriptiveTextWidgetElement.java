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
@Generated(from = "DescriptiveTextWidgetElement", generator = "Immutables")
public final class ImmutableDescriptiveTextWidgetElement extends DescriptiveTextWidgetElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String footer;
    private volatile transient InitShim initShim;

    @Nullable
    private final String interpunct;
    private final List<Method> onViewed;
    private final List<DescriptiveTextWidgetItemElement> textWidgetItems;
    private final String widgetId;

    private ImmutableDescriptiveTextWidgetElement(Builder builder) {
        this.initShim = new InitShim();
        this.textWidgetItems = createUnmodifiableList(true, builder.textWidgetItems);
        this.interpunct = builder.interpunct;
        this.footer = builder.footer;
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

    private ImmutableDescriptiveTextWidgetElement(String str, List<Method> list, List<DescriptiveTextWidgetItemElement> list2, @Nullable String str2, @Nullable String str3) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.textWidgetItems = list2;
        this.interpunct = str2;
        this.footer = str3;
        this.initShim = null;
    }

    @Generated(from = "DescriptiveTextWidgetElement", generator = "Immutables")
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
                this.widgetId = (String) ImmutableDescriptiveTextWidgetElement.requireNonNull(ImmutableDescriptiveTextWidgetElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableDescriptiveTextWidgetElement.createUnmodifiableList(false, ImmutableDescriptiveTextWidgetElement.createSafeList(ImmutableDescriptiveTextWidgetElement.super.onViewed(), true, false));
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
            return "Cannot build DescriptiveTextWidgetElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetElement
    @JsonProperty("textWidgetItems")
    public List<DescriptiveTextWidgetItemElement> textWidgetItems() {
        return this.textWidgetItems;
    }

    @Override // Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetElement
    @JsonProperty("interpunct")
    @Nullable
    public String interpunct() {
        return this.interpunct;
    }

    @Override // Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetElement
    @JsonProperty("footer")
    @Nullable
    public String footer() {
        return this.footer;
    }

    public final ImmutableDescriptiveTextWidgetElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableDescriptiveTextWidgetElement(str2, this.onViewed, this.textWidgetItems, this.interpunct, this.footer);
    }

    public final ImmutableDescriptiveTextWidgetElement withOnViewed(Method... methodArr) {
        return new ImmutableDescriptiveTextWidgetElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.textWidgetItems, this.interpunct, this.footer);
    }

    public final ImmutableDescriptiveTextWidgetElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableDescriptiveTextWidgetElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.textWidgetItems, this.interpunct, this.footer);
    }

    public final ImmutableDescriptiveTextWidgetElement withTextWidgetItems(DescriptiveTextWidgetItemElement... descriptiveTextWidgetItemElementArr) {
        return new ImmutableDescriptiveTextWidgetElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(Arrays.asList(descriptiveTextWidgetItemElementArr), true, false)), this.interpunct, this.footer);
    }

    public final ImmutableDescriptiveTextWidgetElement withTextWidgetItems(Iterable<? extends DescriptiveTextWidgetItemElement> iterable) {
        if (this.textWidgetItems == iterable) {
            return this;
        }
        return new ImmutableDescriptiveTextWidgetElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.interpunct, this.footer);
    }

    public final ImmutableDescriptiveTextWidgetElement withInterpunct(@Nullable String str) {
        return equals(this.interpunct, str) ? this : new ImmutableDescriptiveTextWidgetElement(this.widgetId, this.onViewed, this.textWidgetItems, str, this.footer);
    }

    public final ImmutableDescriptiveTextWidgetElement withFooter(@Nullable String str) {
        return equals(this.footer, str) ? this : new ImmutableDescriptiveTextWidgetElement(this.widgetId, this.onViewed, this.textWidgetItems, this.interpunct, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableDescriptiveTextWidgetElement) && equalTo(0, (ImmutableDescriptiveTextWidgetElement) obj);
    }

    private boolean equalTo(int i, ImmutableDescriptiveTextWidgetElement immutableDescriptiveTextWidgetElement) {
        return this.widgetId.equals(immutableDescriptiveTextWidgetElement.widgetId) && this.onViewed.equals(immutableDescriptiveTextWidgetElement.onViewed) && this.textWidgetItems.equals(immutableDescriptiveTextWidgetElement.textWidgetItems) && equals(this.interpunct, immutableDescriptiveTextWidgetElement.interpunct) && equals(this.footer, immutableDescriptiveTextWidgetElement.footer);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.textWidgetItems.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.interpunct);
        return iHashCode4 + (iHashCode4 << 5) + hashCode(this.footer);
    }

    public String toString() {
        return "DescriptiveTextWidgetElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", textWidgetItems=" + this.textWidgetItems + ", interpunct=" + this.interpunct + ", footer=" + this.footer + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "DescriptiveTextWidgetElement", generator = "Immutables")
    static final class Json extends DescriptiveTextWidgetElement {

        @Nullable
        String footer;

        @Nullable
        String interpunct;
        boolean onViewedIsSet;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<DescriptiveTextWidgetItemElement> textWidgetItems = Collections.emptyList();

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

        @JsonProperty("textWidgetItems")
        public void setTextWidgetItems(List<DescriptiveTextWidgetItemElement> list) {
            this.textWidgetItems = list;
        }

        @JsonProperty("interpunct")
        public void setInterpunct(@Nullable String str) {
            this.interpunct = str;
        }

        @JsonProperty("footer")
        public void setFooter(@Nullable String str) {
            this.footer = str;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetElement
        public List<DescriptiveTextWidgetItemElement> textWidgetItems() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetElement
        public String interpunct() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetElement
        public String footer() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableDescriptiveTextWidgetElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        List<DescriptiveTextWidgetItemElement> list = json.textWidgetItems;
        if (list != null) {
            builder.addAllTextWidgetItems(list);
        }
        String str2 = json.interpunct;
        if (str2 != null) {
            builder.interpunct(str2);
        }
        String str3 = json.footer;
        if (str3 != null) {
            builder.footer(str3);
        }
        return builder.build();
    }

    public static ImmutableDescriptiveTextWidgetElement copyOf(DescriptiveTextWidgetElement descriptiveTextWidgetElement) {
        if (descriptiveTextWidgetElement instanceof ImmutableDescriptiveTextWidgetElement) {
            return (ImmutableDescriptiveTextWidgetElement) descriptiveTextWidgetElement;
        }
        return builder().from(descriptiveTextWidgetElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "DescriptiveTextWidgetElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private String footer;

        @Nullable
        private String interpunct;
        private List<Method> onViewed;
        private long optBits;
        private List<DescriptiveTextWidgetItemElement> textWidgetItems;

        @Nullable
        private String widgetId;

        private Builder() {
            this.onViewed = new ArrayList();
            this.textWidgetItems = new ArrayList();
        }

        public final Builder from(DescriptiveTextWidgetElement descriptiveTextWidgetElement) {
            ImmutableDescriptiveTextWidgetElement.requireNonNull(descriptiveTextWidgetElement, "instance");
            from((Object) descriptiveTextWidgetElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableDescriptiveTextWidgetElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof DescriptiveTextWidgetElement) {
                DescriptiveTextWidgetElement descriptiveTextWidgetElement = (DescriptiveTextWidgetElement) obj;
                String strInterpunct = descriptiveTextWidgetElement.interpunct();
                if (strInterpunct != null) {
                    interpunct(strInterpunct);
                }
                addAllTextWidgetItems(descriptiveTextWidgetElement.textWidgetItems());
                addAllOnViewed(descriptiveTextWidgetElement.onViewed());
                String strFooter = descriptiveTextWidgetElement.footer();
                if (strFooter != null) {
                    footer(strFooter);
                }
                widgetId(descriptiveTextWidgetElement.widgetId());
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
            this.widgetId = (String) ImmutableDescriptiveTextWidgetElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableDescriptiveTextWidgetElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableDescriptiveTextWidgetElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableDescriptiveTextWidgetElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addTextWidgetItems(DescriptiveTextWidgetItemElement descriptiveTextWidgetItemElement) {
            this.textWidgetItems.add((DescriptiveTextWidgetItemElement) ImmutableDescriptiveTextWidgetElement.requireNonNull(descriptiveTextWidgetItemElement, "textWidgetItems element"));
            return this;
        }

        public final Builder addTextWidgetItems(DescriptiveTextWidgetItemElement... descriptiveTextWidgetItemElementArr) {
            for (DescriptiveTextWidgetItemElement descriptiveTextWidgetItemElement : descriptiveTextWidgetItemElementArr) {
                this.textWidgetItems.add((DescriptiveTextWidgetItemElement) ImmutableDescriptiveTextWidgetElement.requireNonNull(descriptiveTextWidgetItemElement, "textWidgetItems element"));
            }
            return this;
        }

        @JsonProperty("textWidgetItems")
        public final Builder textWidgetItems(Iterable<? extends DescriptiveTextWidgetItemElement> iterable) {
            this.textWidgetItems.clear();
            return addAllTextWidgetItems(iterable);
        }

        public final Builder addAllTextWidgetItems(Iterable<? extends DescriptiveTextWidgetItemElement> iterable) {
            Iterator<? extends DescriptiveTextWidgetItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.textWidgetItems.add((DescriptiveTextWidgetItemElement) ImmutableDescriptiveTextWidgetElement.requireNonNull(it.next(), "textWidgetItems element"));
            }
            return this;
        }

        @JsonProperty("interpunct")
        public final Builder interpunct(@Nullable String str) {
            this.interpunct = str;
            return this;
        }

        @JsonProperty("footer")
        public final Builder footer(@Nullable String str) {
            this.footer = str;
            return this;
        }

        public ImmutableDescriptiveTextWidgetElement build() {
            return new ImmutableDescriptiveTextWidgetElement(this);
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
