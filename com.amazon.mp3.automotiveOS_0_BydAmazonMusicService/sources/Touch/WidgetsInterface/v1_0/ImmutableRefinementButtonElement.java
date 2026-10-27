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
@Generated(from = "RefinementButtonElement", generator = "Immutables")
public final class ImmutableRefinementButtonElement extends RefinementButtonElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;
    private final Boolean isActive;

    @Nullable
    private final ImageResources leftIconResources;

    @Nullable
    private final List<Method> onItemDeselected;
    private final List<Method> onItemSelected;
    private final List<Method> onViewed;

    @Nullable
    private final ImageResources rightIconResources;
    private final String text;
    private final String widgetId;

    private ImmutableRefinementButtonElement(Builder builder) {
        this.initShim = new InitShim();
        this.text = builder.text;
        this.leftIconResources = builder.leftIconResources;
        this.rightIconResources = builder.rightIconResources;
        this.onItemSelected = createUnmodifiableList(true, builder.onItemSelected);
        this.onItemDeselected = builder.onItemDeselected == null ? null : createUnmodifiableList(true, builder.onItemDeselected);
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.isActive != null) {
            this.initShim.isActive(builder.isActive);
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.isActive = this.initShim.isActive();
        this.initShim = null;
    }

    private ImmutableRefinementButtonElement(String str, List<Method> list, String str2, @Nullable ImageResources imageResources, @Nullable ImageResources imageResources2, List<Method> list2, @Nullable List<Method> list3, Boolean bool) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.text = str2;
        this.leftIconResources = imageResources;
        this.rightIconResources = imageResources2;
        this.onItemSelected = list2;
        this.onItemDeselected = list3;
        this.isActive = bool;
        this.initShim = null;
    }

    @Generated(from = "RefinementButtonElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isActive;
        private byte isActiveBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.isActiveBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableRefinementButtonElement.requireNonNull(ImmutableRefinementButtonElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableRefinementButtonElement.createUnmodifiableList(false, ImmutableRefinementButtonElement.createSafeList(ImmutableRefinementButtonElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        Boolean isActive() {
            byte b = this.isActiveBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isActiveBuildStage = (byte) -1;
                this.isActive = (Boolean) ImmutableRefinementButtonElement.requireNonNull(ImmutableRefinementButtonElement.super.isActive(), "isActive");
                this.isActiveBuildStage = (byte) 1;
            }
            return this.isActive;
        }

        void isActive(Boolean bool) {
            this.isActive = bool;
            this.isActiveBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.isActiveBuildStage == -1) {
                arrayList.add("isActive");
            }
            return "Cannot build RefinementButtonElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
    @JsonProperty("leftIconResources")
    @Nullable
    public ImageResources leftIconResources() {
        return this.leftIconResources;
    }

    @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
    @JsonProperty("rightIconResources")
    @Nullable
    public ImageResources rightIconResources() {
        return this.rightIconResources;
    }

    @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
    @JsonProperty("onItemDeselected")
    @Nullable
    public List<Method> onItemDeselected() {
        return this.onItemDeselected;
    }

    @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
    @JsonProperty("isActive")
    public Boolean isActive() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isActive();
        }
        return this.isActive;
    }

    public final ImmutableRefinementButtonElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableRefinementButtonElement(str2, this.onViewed, this.text, this.leftIconResources, this.rightIconResources, this.onItemSelected, this.onItemDeselected, this.isActive);
    }

    public final ImmutableRefinementButtonElement withOnViewed(Method... methodArr) {
        return new ImmutableRefinementButtonElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.text, this.leftIconResources, this.rightIconResources, this.onItemSelected, this.onItemDeselected, this.isActive);
    }

    public final ImmutableRefinementButtonElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableRefinementButtonElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.text, this.leftIconResources, this.rightIconResources, this.onItemSelected, this.onItemDeselected, this.isActive);
    }

    public final ImmutableRefinementButtonElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutableRefinementButtonElement(this.widgetId, this.onViewed, str2, this.leftIconResources, this.rightIconResources, this.onItemSelected, this.onItemDeselected, this.isActive);
    }

    public final ImmutableRefinementButtonElement withLeftIconResources(@Nullable ImageResources imageResources) {
        return this.leftIconResources == imageResources ? this : new ImmutableRefinementButtonElement(this.widgetId, this.onViewed, this.text, imageResources, this.rightIconResources, this.onItemSelected, this.onItemDeselected, this.isActive);
    }

    public final ImmutableRefinementButtonElement withRightIconResources(@Nullable ImageResources imageResources) {
        return this.rightIconResources == imageResources ? this : new ImmutableRefinementButtonElement(this.widgetId, this.onViewed, this.text, this.leftIconResources, imageResources, this.onItemSelected, this.onItemDeselected, this.isActive);
    }

    public final ImmutableRefinementButtonElement withOnItemSelected(Method... methodArr) {
        return new ImmutableRefinementButtonElement(this.widgetId, this.onViewed, this.text, this.leftIconResources, this.rightIconResources, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onItemDeselected, this.isActive);
    }

    public final ImmutableRefinementButtonElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableRefinementButtonElement(this.widgetId, this.onViewed, this.text, this.leftIconResources, this.rightIconResources, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onItemDeselected, this.isActive);
    }

    public final ImmutableRefinementButtonElement withOnItemDeselected(@Nullable Method... methodArr) {
        if (methodArr == null) {
            return new ImmutableRefinementButtonElement(this.widgetId, this.onViewed, this.text, this.leftIconResources, this.rightIconResources, this.onItemSelected, null, this.isActive);
        }
        return new ImmutableRefinementButtonElement(this.widgetId, this.onViewed, this.text, this.leftIconResources, this.rightIconResources, this.onItemSelected, Arrays.asList(methodArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.isActive);
    }

    public final ImmutableRefinementButtonElement withOnItemDeselected(@Nullable Iterable<? extends Method> iterable) {
        if (this.onItemDeselected == iterable) {
            return this;
        }
        return new ImmutableRefinementButtonElement(this.widgetId, this.onViewed, this.text, this.leftIconResources, this.rightIconResources, this.onItemSelected, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.isActive);
    }

    public final ImmutableRefinementButtonElement withIsActive(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isActive");
        return this.isActive.equals(bool2) ? this : new ImmutableRefinementButtonElement(this.widgetId, this.onViewed, this.text, this.leftIconResources, this.rightIconResources, this.onItemSelected, this.onItemDeselected, bool2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableRefinementButtonElement) && equalTo(0, (ImmutableRefinementButtonElement) obj);
    }

    private boolean equalTo(int i, ImmutableRefinementButtonElement immutableRefinementButtonElement) {
        return this.widgetId.equals(immutableRefinementButtonElement.widgetId) && this.onViewed.equals(immutableRefinementButtonElement.onViewed) && this.text.equals(immutableRefinementButtonElement.text) && equals(this.leftIconResources, immutableRefinementButtonElement.leftIconResources) && equals(this.rightIconResources, immutableRefinementButtonElement.rightIconResources) && this.onItemSelected.equals(immutableRefinementButtonElement.onItemSelected) && equals(this.onItemDeselected, immutableRefinementButtonElement.onItemDeselected) && this.isActive.equals(immutableRefinementButtonElement.isActive);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.text.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.leftIconResources);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.rightIconResources);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.onItemSelected.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.onItemDeselected);
        return iHashCode7 + (iHashCode7 << 5) + this.isActive.hashCode();
    }

    public String toString() {
        return "RefinementButtonElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", text=" + this.text + ", leftIconResources=" + this.leftIconResources + ", rightIconResources=" + this.rightIconResources + ", onItemSelected=" + this.onItemSelected + ", onItemDeselected=" + this.onItemDeselected + ", isActive=" + this.isActive + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "RefinementButtonElement", generator = "Immutables")
    static final class Json extends RefinementButtonElement {

        @Nullable
        Boolean isActive;

        @Nullable
        ImageResources leftIconResources;
        boolean onViewedIsSet;

        @Nullable
        ImageResources rightIconResources;

        @Nullable
        String text;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        @Nullable
        List<Method> onItemDeselected = null;

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

        @JsonProperty("text")
        public void setText(String str) {
            this.text = str;
        }

        @JsonProperty("leftIconResources")
        public void setLeftIconResources(@Nullable ImageResources imageResources) {
            this.leftIconResources = imageResources;
        }

        @JsonProperty("rightIconResources")
        public void setRightIconResources(@Nullable ImageResources imageResources) {
            this.rightIconResources = imageResources;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
        }

        @JsonProperty("onItemDeselected")
        public void setOnItemDeselected(@Nullable List<Method> list) {
            this.onItemDeselected = list;
        }

        @JsonProperty("isActive")
        public void setIsActive(Boolean bool) {
            this.isActive = bool;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
        public String text() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
        public ImageResources leftIconResources() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
        public ImageResources rightIconResources() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
        public List<Method> onItemDeselected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RefinementButtonElement
        public Boolean isActive() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableRefinementButtonElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        String str2 = json.text;
        if (str2 != null) {
            builder.text(str2);
        }
        ImageResources imageResources = json.leftIconResources;
        if (imageResources != null) {
            builder.leftIconResources(imageResources);
        }
        ImageResources imageResources2 = json.rightIconResources;
        if (imageResources2 != null) {
            builder.rightIconResources(imageResources2);
        }
        List<Method> list = json.onItemSelected;
        if (list != null) {
            builder.addAllOnItemSelected(list);
        }
        List<Method> list2 = json.onItemDeselected;
        if (list2 != null) {
            builder.addAllOnItemDeselected(list2);
        }
        Boolean bool = json.isActive;
        if (bool != null) {
            builder.isActive(bool);
        }
        return builder.build();
    }

    public static ImmutableRefinementButtonElement copyOf(RefinementButtonElement refinementButtonElement) {
        if (refinementButtonElement instanceof ImmutableRefinementButtonElement) {
            return (ImmutableRefinementButtonElement) refinementButtonElement;
        }
        return builder().from(refinementButtonElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "RefinementButtonElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TEXT = 1;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private long initBits;

        @Nullable
        private Boolean isActive;

        @Nullable
        private ImageResources leftIconResources;
        private List<Method> onItemDeselected;
        private List<Method> onItemSelected;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private ImageResources rightIconResources;

        @Nullable
        private String text;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 1L;
            this.onViewed = new ArrayList();
            this.onItemSelected = new ArrayList();
            this.onItemDeselected = null;
        }

        public final Builder from(RefinementElement refinementElement) {
            ImmutableRefinementButtonElement.requireNonNull(refinementElement, "instance");
            from((Object) refinementElement);
            return this;
        }

        public final Builder from(RefinementButtonElement refinementButtonElement) {
            ImmutableRefinementButtonElement.requireNonNull(refinementButtonElement, "instance");
            from((Object) refinementButtonElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableRefinementButtonElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof RefinementElement) {
                RefinementElement refinementElement = (RefinementElement) obj;
                widgetId(refinementElement.widgetId());
                addAllOnViewed(refinementElement.onViewed());
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof RefinementButtonElement) {
                RefinementButtonElement refinementButtonElement = (RefinementButtonElement) obj;
                addAllOnItemSelected(refinementButtonElement.onItemSelected());
                if ((j & 1) == 0) {
                    widgetId(refinementButtonElement.widgetId());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    addAllOnViewed(refinementButtonElement.onViewed());
                    j |= 2;
                }
                text(refinementButtonElement.text());
                ImageResources imageResourcesRightIconResources = refinementButtonElement.rightIconResources();
                if (imageResourcesRightIconResources != null) {
                    rightIconResources(imageResourcesRightIconResources);
                }
                List<Method> listOnItemDeselected = refinementButtonElement.onItemDeselected();
                if (listOnItemDeselected != null) {
                    addAllOnItemDeselected(listOnItemDeselected);
                }
                isActive(refinementButtonElement.isActive());
                ImageResources imageResourcesLeftIconResources = refinementButtonElement.leftIconResources();
                if (imageResourcesLeftIconResources != null) {
                    leftIconResources(imageResourcesLeftIconResources);
                }
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
            this.widgetId = (String) ImmutableRefinementButtonElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableRefinementButtonElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableRefinementButtonElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableRefinementButtonElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutableRefinementButtonElement.requireNonNull(str, "text");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("leftIconResources")
        public final Builder leftIconResources(@Nullable ImageResources imageResources) {
            this.leftIconResources = imageResources;
            return this;
        }

        @JsonProperty("rightIconResources")
        public final Builder rightIconResources(@Nullable ImageResources imageResources) {
            this.rightIconResources = imageResources;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableRefinementButtonElement.requireNonNull(method, "onItemSelected element"));
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableRefinementButtonElement.requireNonNull(method, "onItemSelected element"));
            }
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
                this.onItemSelected.add((Method) ImmutableRefinementButtonElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            return this;
        }

        public final Builder addOnItemDeselected(Method method) {
            if (this.onItemDeselected == null) {
                this.onItemDeselected = new ArrayList();
            }
            this.onItemDeselected.add((Method) ImmutableRefinementButtonElement.requireNonNull(method, "onItemDeselected element"));
            return this;
        }

        public final Builder addOnItemDeselected(Method... methodArr) {
            if (this.onItemDeselected == null) {
                this.onItemDeselected = new ArrayList();
            }
            for (Method method : methodArr) {
                this.onItemDeselected.add((Method) ImmutableRefinementButtonElement.requireNonNull(method, "onItemDeselected element"));
            }
            return this;
        }

        @JsonProperty("onItemDeselected")
        public final Builder onItemDeselected(@Nullable Iterable<? extends Method> iterable) {
            if (iterable == null) {
                this.onItemDeselected = null;
                return this;
            }
            this.onItemDeselected = new ArrayList();
            return addAllOnItemDeselected(iterable);
        }

        public final Builder addAllOnItemDeselected(Iterable<? extends Method> iterable) {
            ImmutableRefinementButtonElement.requireNonNull(iterable, "onItemDeselected element");
            if (this.onItemDeselected == null) {
                this.onItemDeselected = new ArrayList();
            }
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onItemDeselected.add((Method) ImmutableRefinementButtonElement.requireNonNull(it.next(), "onItemDeselected element"));
            }
            return this;
        }

        @JsonProperty("isActive")
        public final Builder isActive(Boolean bool) {
            this.isActive = (Boolean) ImmutableRefinementButtonElement.requireNonNull(bool, "isActive");
            return this;
        }

        public ImmutableRefinementButtonElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableRefinementButtonElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("text");
            }
            return "Cannot build RefinementButtonElement, some of required attributes are not set " + arrayList;
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
