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
@Generated(from = "IngressButtonElement", generator = "Immutables")
public final class ImmutableIngressButtonElement extends IngressButtonElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean disabled;

    @Nullable
    private final String icon;
    private volatile transient InitShim initShim;
    private final List<Method> onItemSelected;
    private final List<Method> onViewed;

    @Nullable
    private final ResourceId resourceId;

    @Nullable
    private final String text;
    private final String widgetId;

    private ImmutableIngressButtonElement(Builder builder) {
        this.initShim = new InitShim();
        this.resourceId = builder.resourceId;
        this.text = builder.text;
        this.icon = builder.icon;
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.disabled != null) {
            this.initShim.disabled(builder.disabled);
        }
        if (builder.onItemSelectedIsSet()) {
            this.initShim.onItemSelected(createUnmodifiableList(true, builder.onItemSelected));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.disabled = this.initShim.disabled();
        this.onItemSelected = this.initShim.onItemSelected();
        this.initShim = null;
    }

    private ImmutableIngressButtonElement(String str, List<Method> list, Boolean bool, List<Method> list2, @Nullable ResourceId resourceId, @Nullable String str2, @Nullable String str3) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.disabled = bool;
        this.onItemSelected = list2;
        this.resourceId = resourceId;
        this.text = str2;
        this.icon = str3;
        this.initShim = null;
    }

    @Generated(from = "IngressButtonElement", generator = "Immutables")
    private final class InitShim {
        private Boolean disabled;
        private byte disabledBuildStage;
        private List<Method> onItemSelected;
        private byte onItemSelectedBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.disabledBuildStage = (byte) 0;
            this.onItemSelectedBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableIngressButtonElement.requireNonNull(ImmutableIngressButtonElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableIngressButtonElement.createUnmodifiableList(false, ImmutableIngressButtonElement.createSafeList(ImmutableIngressButtonElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        Boolean disabled() {
            byte b = this.disabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.disabledBuildStage = (byte) -1;
                this.disabled = (Boolean) ImmutableIngressButtonElement.requireNonNull(ImmutableIngressButtonElement.super.disabled(), "disabled");
                this.disabledBuildStage = (byte) 1;
            }
            return this.disabled;
        }

        void disabled(Boolean bool) {
            this.disabled = bool;
            this.disabledBuildStage = (byte) 1;
        }

        List<Method> onItemSelected() {
            byte b = this.onItemSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onItemSelectedBuildStage = (byte) -1;
                this.onItemSelected = ImmutableIngressButtonElement.createUnmodifiableList(false, ImmutableIngressButtonElement.createSafeList(ImmutableIngressButtonElement.super.onItemSelected(), true, false));
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
            if (this.disabledBuildStage == -1) {
                arrayList.add("disabled");
            }
            if (this.onItemSelectedBuildStage == -1) {
                arrayList.add("onItemSelected");
            }
            return "Cannot build IngressButtonElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.ButtonElement
    @JsonProperty("disabled")
    public Boolean disabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.disabled();
        }
        return this.disabled;
    }

    @Override // Touch.WidgetsInterface.v1_0.ButtonElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onItemSelected();
        }
        return this.onItemSelected;
    }

    @Override // Touch.WidgetsInterface.v1_0.ButtonElement
    @JsonProperty("resourceId")
    @Nullable
    public ResourceId resourceId() {
        return this.resourceId;
    }

    @Override // Touch.WidgetsInterface.v1_0.IngressButtonElement
    @JsonProperty("text")
    @Nullable
    public String text() {
        return this.text;
    }

    @Override // Touch.WidgetsInterface.v1_0.IngressButtonElement
    @JsonProperty("icon")
    @Nullable
    public String icon() {
        return this.icon;
    }

    public final ImmutableIngressButtonElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableIngressButtonElement(str2, this.onViewed, this.disabled, this.onItemSelected, this.resourceId, this.text, this.icon);
    }

    public final ImmutableIngressButtonElement withOnViewed(Method... methodArr) {
        return new ImmutableIngressButtonElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.disabled, this.onItemSelected, this.resourceId, this.text, this.icon);
    }

    public final ImmutableIngressButtonElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableIngressButtonElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.disabled, this.onItemSelected, this.resourceId, this.text, this.icon);
    }

    public final ImmutableIngressButtonElement withDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "disabled");
        return this.disabled.equals(bool2) ? this : new ImmutableIngressButtonElement(this.widgetId, this.onViewed, bool2, this.onItemSelected, this.resourceId, this.text, this.icon);
    }

    public final ImmutableIngressButtonElement withOnItemSelected(Method... methodArr) {
        return new ImmutableIngressButtonElement(this.widgetId, this.onViewed, this.disabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.resourceId, this.text, this.icon);
    }

    public final ImmutableIngressButtonElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableIngressButtonElement(this.widgetId, this.onViewed, this.disabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.resourceId, this.text, this.icon);
    }

    public final ImmutableIngressButtonElement withResourceId(@Nullable ResourceId resourceId) {
        return this.resourceId == resourceId ? this : new ImmutableIngressButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, resourceId, this.text, this.icon);
    }

    public final ImmutableIngressButtonElement withText(@Nullable String str) {
        return equals(this.text, str) ? this : new ImmutableIngressButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, this.resourceId, str, this.icon);
    }

    public final ImmutableIngressButtonElement withIcon(@Nullable String str) {
        return equals(this.icon, str) ? this : new ImmutableIngressButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, this.resourceId, this.text, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableIngressButtonElement) && equalTo(0, (ImmutableIngressButtonElement) obj);
    }

    private boolean equalTo(int i, ImmutableIngressButtonElement immutableIngressButtonElement) {
        return this.widgetId.equals(immutableIngressButtonElement.widgetId) && this.onViewed.equals(immutableIngressButtonElement.onViewed) && this.disabled.equals(immutableIngressButtonElement.disabled) && this.onItemSelected.equals(immutableIngressButtonElement.onItemSelected) && equals(this.resourceId, immutableIngressButtonElement.resourceId) && equals(this.text, immutableIngressButtonElement.text) && equals(this.icon, immutableIngressButtonElement.icon);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.disabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onItemSelected.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.resourceId);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.text);
        return iHashCode6 + (iHashCode6 << 5) + hashCode(this.icon);
    }

    public String toString() {
        return "IngressButtonElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", disabled=" + this.disabled + ", onItemSelected=" + this.onItemSelected + ", resourceId=" + this.resourceId + ", text=" + this.text + ", icon=" + this.icon + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "IngressButtonElement", generator = "Immutables")
    static final class Json extends IngressButtonElement {

        @Nullable
        Boolean disabled;

        @Nullable
        String icon;
        boolean onItemSelectedIsSet;
        boolean onViewedIsSet;

        @Nullable
        ResourceId resourceId;

        @Nullable
        String text;

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

        @JsonProperty("disabled")
        public void setDisabled(Boolean bool) {
            this.disabled = bool;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @JsonProperty("resourceId")
        public void setResourceId(@Nullable ResourceId resourceId) {
            this.resourceId = resourceId;
        }

        @JsonProperty("text")
        public void setText(@Nullable String str) {
            this.text = str;
        }

        @JsonProperty("icon")
        public void setIcon(@Nullable String str) {
            this.icon = str;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ButtonElement
        public Boolean disabled() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ButtonElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ButtonElement
        public ResourceId resourceId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.IngressButtonElement
        public String text() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.IngressButtonElement
        public String icon() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableIngressButtonElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        Boolean bool = json.disabled;
        if (bool != null) {
            builder.disabled(bool);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        ResourceId resourceId = json.resourceId;
        if (resourceId != null) {
            builder.resourceId(resourceId);
        }
        String str2 = json.text;
        if (str2 != null) {
            builder.text(str2);
        }
        String str3 = json.icon;
        if (str3 != null) {
            builder.icon(str3);
        }
        return builder.build();
    }

    public static ImmutableIngressButtonElement copyOf(IngressButtonElement ingressButtonElement) {
        if (ingressButtonElement instanceof ImmutableIngressButtonElement) {
            return (ImmutableIngressButtonElement) ingressButtonElement;
        }
        return builder().from(ingressButtonElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "IngressButtonElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_ITEM_SELECTED = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private Boolean disabled;

        @Nullable
        private String icon;
        private List<Method> onItemSelected;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private ResourceId resourceId;

        @Nullable
        private String text;

        @Nullable
        private String widgetId;

        private Builder() {
            this.onViewed = new ArrayList();
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(IngressButtonElement ingressButtonElement) {
            ImmutableIngressButtonElement.requireNonNull(ingressButtonElement, "instance");
            from((Object) ingressButtonElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableIngressButtonElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        public final Builder from(ButtonElement buttonElement) {
            ImmutableIngressButtonElement.requireNonNull(buttonElement, "instance");
            from((Object) buttonElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof IngressButtonElement) {
                IngressButtonElement ingressButtonElement = (IngressButtonElement) obj;
                addAllOnItemSelected(ingressButtonElement.onItemSelected());
                ResourceId resourceId = ingressButtonElement.resourceId();
                if (resourceId != null) {
                    resourceId(resourceId);
                }
                widgetId(ingressButtonElement.widgetId());
                String strIcon = ingressButtonElement.icon();
                if (strIcon != null) {
                    icon(strIcon);
                }
                disabled(ingressButtonElement.disabled());
                addAllOnViewed(ingressButtonElement.onViewed());
                j = 31;
                String strText = ingressButtonElement.text();
                if (strText != null) {
                    text(strText);
                }
            } else {
                j = 0;
            }
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                if ((j & 4) == 0) {
                    widgetId(widgetElement.widgetId());
                    j |= 4;
                }
                if ((j & 16) == 0) {
                    addAllOnViewed(widgetElement.onViewed());
                    j |= 16;
                }
            }
            if (obj instanceof ButtonElement) {
                ButtonElement buttonElement = (ButtonElement) obj;
                if ((j & 1) == 0) {
                    addAllOnItemSelected(buttonElement.onItemSelected());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    ResourceId resourceId2 = buttonElement.resourceId();
                    if (resourceId2 != null) {
                        resourceId(resourceId2);
                    }
                    j |= 2;
                }
                if ((j & 8) == 0) {
                    disabled(buttonElement.disabled());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    addAllOnViewed(buttonElement.onViewed());
                    j |= 16;
                }
                if ((j & 4) == 0) {
                    widgetId(buttonElement.widgetId());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableIngressButtonElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableIngressButtonElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableIngressButtonElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableIngressButtonElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("disabled")
        public final Builder disabled(Boolean bool) {
            this.disabled = (Boolean) ImmutableIngressButtonElement.requireNonNull(bool, "disabled");
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableIngressButtonElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableIngressButtonElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableIngressButtonElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("resourceId")
        public final Builder resourceId(@Nullable ResourceId resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        @JsonProperty("text")
        public final Builder text(@Nullable String str) {
            this.text = str;
            return this;
        }

        @JsonProperty("icon")
        public final Builder icon(@Nullable String str) {
            this.icon = str;
            return this;
        }

        public ImmutableIngressButtonElement build() {
            return new ImmutableIngressButtonElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemSelectedIsSet() {
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
