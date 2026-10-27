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
@Generated(from = "AlexaButtonElement", generator = "Immutables")
public final class ImmutableAlexaButtonElement extends AlexaButtonElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean disabled;
    private volatile transient InitShim initShim;
    private final List<Method> onItemSelected;
    private final List<Method> onViewed;

    @Nullable
    private final ResourceId resourceId;
    private final String widgetId;

    private ImmutableAlexaButtonElement(Builder builder) {
        this.initShim = new InitShim();
        this.resourceId = builder.resourceId;
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

    private ImmutableAlexaButtonElement(String str, List<Method> list, Boolean bool, List<Method> list2, @Nullable ResourceId resourceId) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.disabled = bool;
        this.onItemSelected = list2;
        this.resourceId = resourceId;
        this.initShim = null;
    }

    @Generated(from = "AlexaButtonElement", generator = "Immutables")
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
                this.widgetId = (String) ImmutableAlexaButtonElement.requireNonNull(ImmutableAlexaButtonElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableAlexaButtonElement.createUnmodifiableList(false, ImmutableAlexaButtonElement.createSafeList(ImmutableAlexaButtonElement.super.onViewed(), true, false));
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
                this.disabled = (Boolean) ImmutableAlexaButtonElement.requireNonNull(ImmutableAlexaButtonElement.super.disabled(), "disabled");
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
                this.onItemSelected = ImmutableAlexaButtonElement.createUnmodifiableList(false, ImmutableAlexaButtonElement.createSafeList(ImmutableAlexaButtonElement.super.onItemSelected(), true, false));
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
            return "Cannot build AlexaButtonElement, attribute initializers form cycle " + arrayList;
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

    public final ImmutableAlexaButtonElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableAlexaButtonElement(str2, this.onViewed, this.disabled, this.onItemSelected, this.resourceId);
    }

    public final ImmutableAlexaButtonElement withOnViewed(Method... methodArr) {
        return new ImmutableAlexaButtonElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.disabled, this.onItemSelected, this.resourceId);
    }

    public final ImmutableAlexaButtonElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableAlexaButtonElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.disabled, this.onItemSelected, this.resourceId);
    }

    public final ImmutableAlexaButtonElement withDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "disabled");
        return this.disabled.equals(bool2) ? this : new ImmutableAlexaButtonElement(this.widgetId, this.onViewed, bool2, this.onItemSelected, this.resourceId);
    }

    public final ImmutableAlexaButtonElement withOnItemSelected(Method... methodArr) {
        return new ImmutableAlexaButtonElement(this.widgetId, this.onViewed, this.disabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.resourceId);
    }

    public final ImmutableAlexaButtonElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableAlexaButtonElement(this.widgetId, this.onViewed, this.disabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.resourceId);
    }

    public final ImmutableAlexaButtonElement withResourceId(@Nullable ResourceId resourceId) {
        return this.resourceId == resourceId ? this : new ImmutableAlexaButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, resourceId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableAlexaButtonElement) && equalTo(0, (ImmutableAlexaButtonElement) obj);
    }

    private boolean equalTo(int i, ImmutableAlexaButtonElement immutableAlexaButtonElement) {
        return this.widgetId.equals(immutableAlexaButtonElement.widgetId) && this.onViewed.equals(immutableAlexaButtonElement.onViewed) && this.disabled.equals(immutableAlexaButtonElement.disabled) && this.onItemSelected.equals(immutableAlexaButtonElement.onItemSelected) && equals(this.resourceId, immutableAlexaButtonElement.resourceId);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.disabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onItemSelected.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + hashCode(this.resourceId);
    }

    public String toString() {
        return "AlexaButtonElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", disabled=" + this.disabled + ", onItemSelected=" + this.onItemSelected + ", resourceId=" + this.resourceId + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "AlexaButtonElement", generator = "Immutables")
    static final class Json extends AlexaButtonElement {

        @Nullable
        Boolean disabled;
        boolean onItemSelectedIsSet;
        boolean onViewedIsSet;

        @Nullable
        ResourceId resourceId;

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
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableAlexaButtonElement fromJson(Json json) {
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
        return builder.build();
    }

    public static ImmutableAlexaButtonElement copyOf(AlexaButtonElement alexaButtonElement) {
        if (alexaButtonElement instanceof ImmutableAlexaButtonElement) {
            return (ImmutableAlexaButtonElement) alexaButtonElement;
        }
        return builder().from(alexaButtonElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "AlexaButtonElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_ITEM_SELECTED = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private Boolean disabled;
        private List<Method> onItemSelected;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private ResourceId resourceId;

        @Nullable
        private String widgetId;

        private Builder() {
            this.onViewed = new ArrayList();
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(AlexaButtonElement alexaButtonElement) {
            ImmutableAlexaButtonElement.requireNonNull(alexaButtonElement, "instance");
            from((Object) alexaButtonElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableAlexaButtonElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        public final Builder from(ButtonElement buttonElement) {
            ImmutableAlexaButtonElement.requireNonNull(buttonElement, "instance");
            from((Object) buttonElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof AlexaButtonElement) {
                AlexaButtonElement alexaButtonElement = (AlexaButtonElement) obj;
                addAllOnItemSelected(alexaButtonElement.onItemSelected());
                ResourceId resourceId = alexaButtonElement.resourceId();
                if (resourceId != null) {
                    resourceId(resourceId);
                }
                disabled(alexaButtonElement.disabled());
                addAllOnViewed(alexaButtonElement.onViewed());
                widgetId(alexaButtonElement.widgetId());
                j = 31;
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
            this.widgetId = (String) ImmutableAlexaButtonElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableAlexaButtonElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableAlexaButtonElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableAlexaButtonElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("disabled")
        public final Builder disabled(Boolean bool) {
            this.disabled = (Boolean) ImmutableAlexaButtonElement.requireNonNull(bool, "disabled");
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableAlexaButtonElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableAlexaButtonElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableAlexaButtonElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("resourceId")
        public final Builder resourceId(@Nullable ResourceId resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public ImmutableAlexaButtonElement build() {
            return new ImmutableAlexaButtonElement(this);
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
