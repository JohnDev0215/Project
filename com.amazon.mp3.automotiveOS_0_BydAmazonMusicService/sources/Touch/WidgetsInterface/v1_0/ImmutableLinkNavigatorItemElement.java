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
@Generated(from = "LinkNavigatorItemElement", generator = "Immutables")
public final class ImmutableLinkNavigatorItemElement extends LinkNavigatorItemElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String backgroundColor;

    @Nullable
    private final String icon;
    private volatile transient InitShim initShim;
    private final String mainText;
    private final List<Method> onItemSelected;
    private final List<Method> onViewed;
    private final String widgetId;

    private ImmutableLinkNavigatorItemElement(Builder builder) {
        this.initShim = new InitShim();
        this.mainText = builder.mainText;
        this.icon = builder.icon;
        this.backgroundColor = builder.backgroundColor;
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.onItemSelectedIsSet()) {
            this.initShim.onItemSelected(createUnmodifiableList(true, builder.onItemSelected));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.onItemSelected = this.initShim.onItemSelected();
        this.initShim = null;
    }

    private ImmutableLinkNavigatorItemElement(String str, List<Method> list, String str2, @Nullable String str3, @Nullable String str4, List<Method> list2) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.mainText = str2;
        this.icon = str3;
        this.backgroundColor = str4;
        this.onItemSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "LinkNavigatorItemElement", generator = "Immutables")
    private final class InitShim {
        private List<Method> onItemSelected;
        private byte onItemSelectedBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.onItemSelectedBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableLinkNavigatorItemElement.requireNonNull(ImmutableLinkNavigatorItemElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableLinkNavigatorItemElement.createUnmodifiableList(false, ImmutableLinkNavigatorItemElement.createSafeList(ImmutableLinkNavigatorItemElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        List<Method> onItemSelected() {
            byte b = this.onItemSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onItemSelectedBuildStage = (byte) -1;
                this.onItemSelected = ImmutableLinkNavigatorItemElement.createUnmodifiableList(false, ImmutableLinkNavigatorItemElement.createSafeList(ImmutableLinkNavigatorItemElement.super.onItemSelected(), true, false));
                this.onItemSelectedBuildStage = (byte) 1;
            }
            return this.onItemSelected;
        }

        void onItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.onItemSelectedBuildStage == -1) {
                arrayList.add("onItemSelected");
            }
            return "Cannot build LinkNavigatorItemElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorItemElement
    @JsonProperty("mainText")
    public String mainText() {
        return this.mainText;
    }

    @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorItemElement
    @JsonProperty("icon")
    @Nullable
    public String icon() {
        return this.icon;
    }

    @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorItemElement
    @JsonProperty("backgroundColor")
    @Nullable
    public String backgroundColor() {
        return this.backgroundColor;
    }

    @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorItemElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onItemSelected();
        }
        return this.onItemSelected;
    }

    public final ImmutableLinkNavigatorItemElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableLinkNavigatorItemElement(str2, this.onViewed, this.mainText, this.icon, this.backgroundColor, this.onItemSelected);
    }

    public final ImmutableLinkNavigatorItemElement withOnViewed(Method... methodArr) {
        return new ImmutableLinkNavigatorItemElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.mainText, this.icon, this.backgroundColor, this.onItemSelected);
    }

    public final ImmutableLinkNavigatorItemElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableLinkNavigatorItemElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.mainText, this.icon, this.backgroundColor, this.onItemSelected);
    }

    public final ImmutableLinkNavigatorItemElement withMainText(String str) {
        String str2 = (String) requireNonNull(str, "mainText");
        return this.mainText.equals(str2) ? this : new ImmutableLinkNavigatorItemElement(this.widgetId, this.onViewed, str2, this.icon, this.backgroundColor, this.onItemSelected);
    }

    public final ImmutableLinkNavigatorItemElement withIcon(@Nullable String str) {
        return equals(this.icon, str) ? this : new ImmutableLinkNavigatorItemElement(this.widgetId, this.onViewed, this.mainText, str, this.backgroundColor, this.onItemSelected);
    }

    public final ImmutableLinkNavigatorItemElement withBackgroundColor(@Nullable String str) {
        return equals(this.backgroundColor, str) ? this : new ImmutableLinkNavigatorItemElement(this.widgetId, this.onViewed, this.mainText, this.icon, str, this.onItemSelected);
    }

    public final ImmutableLinkNavigatorItemElement withOnItemSelected(Method... methodArr) {
        return new ImmutableLinkNavigatorItemElement(this.widgetId, this.onViewed, this.mainText, this.icon, this.backgroundColor, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableLinkNavigatorItemElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableLinkNavigatorItemElement(this.widgetId, this.onViewed, this.mainText, this.icon, this.backgroundColor, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableLinkNavigatorItemElement) && equalTo(0, (ImmutableLinkNavigatorItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableLinkNavigatorItemElement immutableLinkNavigatorItemElement) {
        return this.widgetId.equals(immutableLinkNavigatorItemElement.widgetId) && this.onViewed.equals(immutableLinkNavigatorItemElement.onViewed) && this.mainText.equals(immutableLinkNavigatorItemElement.mainText) && equals(this.icon, immutableLinkNavigatorItemElement.icon) && equals(this.backgroundColor, immutableLinkNavigatorItemElement.backgroundColor) && this.onItemSelected.equals(immutableLinkNavigatorItemElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.mainText.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.icon);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.backgroundColor);
        return iHashCode5 + (iHashCode5 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "LinkNavigatorItemElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", mainText=" + this.mainText + ", icon=" + this.icon + ", backgroundColor=" + this.backgroundColor + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "LinkNavigatorItemElement", generator = "Immutables")
    static final class Json extends LinkNavigatorItemElement {

        @Nullable
        String backgroundColor;

        @Nullable
        String icon;

        @Nullable
        String mainText;
        boolean onItemSelectedIsSet;
        boolean onViewedIsSet;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

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

        @JsonProperty("mainText")
        public void setMainText(String str) {
            this.mainText = str;
        }

        @JsonProperty("icon")
        public void setIcon(@Nullable String str) {
            this.icon = str;
        }

        @JsonProperty("backgroundColor")
        public void setBackgroundColor(@Nullable String str) {
            this.backgroundColor = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorItemElement
        public String mainText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorItemElement
        public String icon() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorItemElement
        public String backgroundColor() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LinkNavigatorItemElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableLinkNavigatorItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        String str2 = json.mainText;
        if (str2 != null) {
            builder.mainText(str2);
        }
        String str3 = json.icon;
        if (str3 != null) {
            builder.icon(str3);
        }
        String str4 = json.backgroundColor;
        if (str4 != null) {
            builder.backgroundColor(str4);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        return builder.build();
    }

    public static ImmutableLinkNavigatorItemElement copyOf(LinkNavigatorItemElement linkNavigatorItemElement) {
        if (linkNavigatorItemElement instanceof ImmutableLinkNavigatorItemElement) {
            return (ImmutableLinkNavigatorItemElement) linkNavigatorItemElement;
        }
        return builder().from(linkNavigatorItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "LinkNavigatorItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_MAIN_TEXT = 1;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private String backgroundColor;

        @Nullable
        private String icon;
        private long initBits;

        @Nullable
        private String mainText;
        private List<Method> onItemSelected;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 1L;
            this.onViewed = new ArrayList();
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(LinkNavigatorItemElement linkNavigatorItemElement) {
            ImmutableLinkNavigatorItemElement.requireNonNull(linkNavigatorItemElement, "instance");
            from((Object) linkNavigatorItemElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableLinkNavigatorItemElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof LinkNavigatorItemElement) {
                LinkNavigatorItemElement linkNavigatorItemElement = (LinkNavigatorItemElement) obj;
                addAllOnItemSelected(linkNavigatorItemElement.onItemSelected());
                String strIcon = linkNavigatorItemElement.icon();
                if (strIcon != null) {
                    icon(strIcon);
                }
                mainText(linkNavigatorItemElement.mainText());
                String strBackgroundColor = linkNavigatorItemElement.backgroundColor();
                if (strBackgroundColor != null) {
                    backgroundColor(strBackgroundColor);
                }
                addAllOnViewed(linkNavigatorItemElement.onViewed());
                widgetId(linkNavigatorItemElement.widgetId());
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
            this.widgetId = (String) ImmutableLinkNavigatorItemElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableLinkNavigatorItemElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableLinkNavigatorItemElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableLinkNavigatorItemElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("mainText")
        public final Builder mainText(String str) {
            this.mainText = (String) ImmutableLinkNavigatorItemElement.requireNonNull(str, "mainText");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("icon")
        public final Builder icon(@Nullable String str) {
            this.icon = str;
            return this;
        }

        @JsonProperty("backgroundColor")
        public final Builder backgroundColor(@Nullable String str) {
            this.backgroundColor = str;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableLinkNavigatorItemElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableLinkNavigatorItemElement.requireNonNull(method, "onItemSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onItemSelected")
        public final Builder onItemSelected(Iterable<? extends Method> iterable) {
            this.onItemSelected.clear();
            return addAllOnItemSelected(iterable);
        }

        public final Builder addAllOnItemSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onItemSelected.add((Method) ImmutableLinkNavigatorItemElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableLinkNavigatorItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableLinkNavigatorItemElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemSelectedIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("mainText");
            }
            return "Cannot build LinkNavigatorItemElement, some of required attributes are not set " + arrayList;
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
