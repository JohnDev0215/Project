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
@Generated(from = "RefinementCollectionElement", generator = "Immutables")
public final class ImmutableRefinementCollectionElement extends RefinementCollectionElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;
    private final List<Method> onViewed;
    private final List<RefinementElement> refinementOptions;

    @Nullable
    private final String resetText;
    private final String widgetId;

    private ImmutableRefinementCollectionElement(Builder builder) {
        this.initShim = new InitShim();
        this.resetText = builder.resetText;
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.refinementOptionsIsSet()) {
            this.initShim.refinementOptions(createUnmodifiableList(true, builder.refinementOptions));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.refinementOptions = this.initShim.refinementOptions();
        this.initShim = null;
    }

    private ImmutableRefinementCollectionElement(String str, List<Method> list, List<RefinementElement> list2, @Nullable String str2) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.refinementOptions = list2;
        this.resetText = str2;
        this.initShim = null;
    }

    @Generated(from = "RefinementCollectionElement", generator = "Immutables")
    private final class InitShim {
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private List<RefinementElement> refinementOptions;
        private byte refinementOptionsBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.refinementOptionsBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableRefinementCollectionElement.requireNonNull(ImmutableRefinementCollectionElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableRefinementCollectionElement.createUnmodifiableList(false, ImmutableRefinementCollectionElement.createSafeList(ImmutableRefinementCollectionElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        List<RefinementElement> refinementOptions() {
            byte b = this.refinementOptionsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.refinementOptionsBuildStage = (byte) -1;
                this.refinementOptions = ImmutableRefinementCollectionElement.createUnmodifiableList(false, ImmutableRefinementCollectionElement.createSafeList(ImmutableRefinementCollectionElement.super.refinementOptions(), true, false));
                this.refinementOptionsBuildStage = (byte) 1;
            }
            return this.refinementOptions;
        }

        void refinementOptions(List<RefinementElement> list) {
            this.refinementOptions = list;
            this.refinementOptionsBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.refinementOptionsBuildStage == -1) {
                arrayList.add("refinementOptions");
            }
            return "Cannot build RefinementCollectionElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.RefinementCollectionElement
    @JsonProperty("refinementOptions")
    public List<RefinementElement> refinementOptions() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.refinementOptions();
        }
        return this.refinementOptions;
    }

    @Override // Touch.WidgetsInterface.v1_0.RefinementCollectionElement
    @JsonProperty("resetText")
    @Nullable
    public String resetText() {
        return this.resetText;
    }

    public final ImmutableRefinementCollectionElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableRefinementCollectionElement(str2, this.onViewed, this.refinementOptions, this.resetText);
    }

    public final ImmutableRefinementCollectionElement withOnViewed(Method... methodArr) {
        return new ImmutableRefinementCollectionElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.refinementOptions, this.resetText);
    }

    public final ImmutableRefinementCollectionElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableRefinementCollectionElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.refinementOptions, this.resetText);
    }

    public final ImmutableRefinementCollectionElement withRefinementOptions(RefinementElement... refinementElementArr) {
        return new ImmutableRefinementCollectionElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(Arrays.asList(refinementElementArr), true, false)), this.resetText);
    }

    public final ImmutableRefinementCollectionElement withRefinementOptions(Iterable<? extends RefinementElement> iterable) {
        if (this.refinementOptions == iterable) {
            return this;
        }
        return new ImmutableRefinementCollectionElement(this.widgetId, this.onViewed, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.resetText);
    }

    public final ImmutableRefinementCollectionElement withResetText(@Nullable String str) {
        return equals(this.resetText, str) ? this : new ImmutableRefinementCollectionElement(this.widgetId, this.onViewed, this.refinementOptions, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableRefinementCollectionElement) && equalTo(0, (ImmutableRefinementCollectionElement) obj);
    }

    private boolean equalTo(int i, ImmutableRefinementCollectionElement immutableRefinementCollectionElement) {
        return this.widgetId.equals(immutableRefinementCollectionElement.widgetId) && this.onViewed.equals(immutableRefinementCollectionElement.onViewed) && this.refinementOptions.equals(immutableRefinementCollectionElement.refinementOptions) && equals(this.resetText, immutableRefinementCollectionElement.resetText);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.refinementOptions.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + hashCode(this.resetText);
    }

    public String toString() {
        return "RefinementCollectionElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", refinementOptions=" + this.refinementOptions + ", resetText=" + this.resetText + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "RefinementCollectionElement", generator = "Immutables")
    static final class Json extends RefinementCollectionElement {
        boolean onViewedIsSet;
        boolean refinementOptionsIsSet;

        @Nullable
        String resetText;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<RefinementElement> refinementOptions = Collections.emptyList();

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

        @JsonProperty("refinementOptions")
        public void setRefinementOptions(List<RefinementElement> list) {
            this.refinementOptions = list;
            this.refinementOptionsIsSet = true;
        }

        @JsonProperty("resetText")
        public void setResetText(@Nullable String str) {
            this.resetText = str;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RefinementCollectionElement
        public List<RefinementElement> refinementOptions() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RefinementCollectionElement
        public String resetText() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableRefinementCollectionElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        if (json.refinementOptionsIsSet) {
            builder.addAllRefinementOptions(json.refinementOptions);
        }
        String str2 = json.resetText;
        if (str2 != null) {
            builder.resetText(str2);
        }
        return builder.build();
    }

    public static ImmutableRefinementCollectionElement copyOf(RefinementCollectionElement refinementCollectionElement) {
        if (refinementCollectionElement instanceof ImmutableRefinementCollectionElement) {
            return (ImmutableRefinementCollectionElement) refinementCollectionElement;
        }
        return builder().from(refinementCollectionElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "RefinementCollectionElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_VIEWED = 1;
        private static final long OPT_BIT_REFINEMENT_OPTIONS = 2;
        private List<Method> onViewed;
        private long optBits;
        private List<RefinementElement> refinementOptions;

        @Nullable
        private String resetText;

        @Nullable
        private String widgetId;

        private Builder() {
            this.onViewed = new ArrayList();
            this.refinementOptions = new ArrayList();
        }

        public final Builder from(RefinementCollectionElement refinementCollectionElement) {
            ImmutableRefinementCollectionElement.requireNonNull(refinementCollectionElement, "instance");
            from((Object) refinementCollectionElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableRefinementCollectionElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof RefinementCollectionElement) {
                RefinementCollectionElement refinementCollectionElement = (RefinementCollectionElement) obj;
                addAllRefinementOptions(refinementCollectionElement.refinementOptions());
                addAllOnViewed(refinementCollectionElement.onViewed());
                String strResetText = refinementCollectionElement.resetText();
                if (strResetText != null) {
                    resetText(strResetText);
                }
                widgetId(refinementCollectionElement.widgetId());
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
            this.widgetId = (String) ImmutableRefinementCollectionElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableRefinementCollectionElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableRefinementCollectionElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableRefinementCollectionElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addRefinementOptions(RefinementElement refinementElement) {
            this.refinementOptions.add((RefinementElement) ImmutableRefinementCollectionElement.requireNonNull(refinementElement, "refinementOptions element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addRefinementOptions(RefinementElement... refinementElementArr) {
            for (RefinementElement refinementElement : refinementElementArr) {
                this.refinementOptions.add((RefinementElement) ImmutableRefinementCollectionElement.requireNonNull(refinementElement, "refinementOptions element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("refinementOptions")
        public final Builder refinementOptions(Iterable<? extends RefinementElement> iterable) {
            this.refinementOptions.clear();
            return addAllRefinementOptions(iterable);
        }

        public final Builder addAllRefinementOptions(Iterable<? extends RefinementElement> iterable) {
            Iterator<? extends RefinementElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.refinementOptions.add((RefinementElement) ImmutableRefinementCollectionElement.requireNonNull(it.next(), "refinementOptions element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("resetText")
        public final Builder resetText(@Nullable String str) {
            this.resetText = str;
            return this;
        }

        public ImmutableRefinementCollectionElement build() {
            return new ImmutableRefinementCollectionElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean refinementOptionsIsSet() {
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
