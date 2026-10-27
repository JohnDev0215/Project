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
@Generated(from = "VisualShovelerElement", generator = "Immutables")
public final class ImmutableVisualShovelerElement extends VisualShovelerElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final List<ButtonElement> additionalActionButtons;
    private volatile transient InitShim initShim;
    private final boolean isCompact;
    private final List<VerticalItemElement> items;
    private final List<Method> onViewed;
    private final String primaryHeader;

    @Nullable
    private final String secondaryHeader;

    @Nullable
    private final SeeMoreItemElement seeMore;
    private final String widgetId;

    private ImmutableVisualShovelerElement(Builder builder) {
        this.initShim = new InitShim();
        this.primaryHeader = builder.primaryHeader;
        this.secondaryHeader = builder.secondaryHeader;
        this.items = createUnmodifiableList(true, builder.items);
        this.seeMore = builder.seeMore;
        this.additionalActionButtons = builder.additionalActionButtons == null ? null : createUnmodifiableList(true, builder.additionalActionButtons);
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.isCompactIsSet()) {
            this.initShim.isCompact(builder.isCompact);
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.isCompact = this.initShim.isCompact();
        this.initShim = null;
    }

    private ImmutableVisualShovelerElement(String str, List<Method> list, String str2, @Nullable String str3, List<VerticalItemElement> list2, @Nullable SeeMoreItemElement seeMoreItemElement, @Nullable List<ButtonElement> list3, boolean z) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.primaryHeader = str2;
        this.secondaryHeader = str3;
        this.items = list2;
        this.seeMore = seeMoreItemElement;
        this.additionalActionButtons = list3;
        this.isCompact = z;
        this.initShim = null;
    }

    @Generated(from = "VisualShovelerElement", generator = "Immutables")
    private final class InitShim {
        private boolean isCompact;
        private byte isCompactBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.isCompactBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableVisualShovelerElement.requireNonNull(ImmutableVisualShovelerElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableVisualShovelerElement.createUnmodifiableList(false, ImmutableVisualShovelerElement.createSafeList(ImmutableVisualShovelerElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        boolean isCompact() {
            byte b = this.isCompactBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isCompactBuildStage = (byte) -1;
                this.isCompact = ImmutableVisualShovelerElement.super.isCompact();
                this.isCompactBuildStage = (byte) 1;
            }
            return this.isCompact;
        }

        void isCompact(boolean z) {
            this.isCompact = z;
            this.isCompactBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.isCompactBuildStage == -1) {
                arrayList.add("isCompact");
            }
            return "Cannot build VisualShovelerElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.ShovelerElement
    @JsonProperty("primaryHeader")
    public String primaryHeader() {
        return this.primaryHeader;
    }

    @Override // Touch.WidgetsInterface.v1_0.ShovelerElement
    @JsonProperty("secondaryHeader")
    @Nullable
    public String secondaryHeader() {
        return this.secondaryHeader;
    }

    @Override // Touch.WidgetsInterface.v1_0.VisualShovelerElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<VerticalItemElement> items() {
        return this.items;
    }

    @Override // Touch.WidgetsInterface.v1_0.VisualShovelerElement
    @JsonProperty(PageUriUtils.DO_SEE_MORE_VALUE)
    @Nullable
    public SeeMoreItemElement seeMore() {
        return this.seeMore;
    }

    @Override // Touch.WidgetsInterface.v1_0.VisualShovelerElement
    @JsonProperty("additionalActionButtons")
    @Nullable
    public List<ButtonElement> additionalActionButtons() {
        return this.additionalActionButtons;
    }

    @Override // Touch.WidgetsInterface.v1_0.VisualShovelerElement
    @JsonProperty("isCompact")
    public boolean isCompact() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isCompact();
        }
        return this.isCompact;
    }

    public final ImmutableVisualShovelerElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableVisualShovelerElement(str2, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items, this.seeMore, this.additionalActionButtons, this.isCompact);
    }

    public final ImmutableVisualShovelerElement withOnViewed(Method... methodArr) {
        return new ImmutableVisualShovelerElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.primaryHeader, this.secondaryHeader, this.items, this.seeMore, this.additionalActionButtons, this.isCompact);
    }

    public final ImmutableVisualShovelerElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableVisualShovelerElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.primaryHeader, this.secondaryHeader, this.items, this.seeMore, this.additionalActionButtons, this.isCompact);
    }

    public final ImmutableVisualShovelerElement withPrimaryHeader(String str) {
        String str2 = (String) requireNonNull(str, "primaryHeader");
        return this.primaryHeader.equals(str2) ? this : new ImmutableVisualShovelerElement(this.widgetId, this.onViewed, str2, this.secondaryHeader, this.items, this.seeMore, this.additionalActionButtons, this.isCompact);
    }

    public final ImmutableVisualShovelerElement withSecondaryHeader(@Nullable String str) {
        return equals(this.secondaryHeader, str) ? this : new ImmutableVisualShovelerElement(this.widgetId, this.onViewed, this.primaryHeader, str, this.items, this.seeMore, this.additionalActionButtons, this.isCompact);
    }

    public final ImmutableVisualShovelerElement withItems(VerticalItemElement... verticalItemElementArr) {
        return new ImmutableVisualShovelerElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, createUnmodifiableList(false, createSafeList(Arrays.asList(verticalItemElementArr), true, false)), this.seeMore, this.additionalActionButtons, this.isCompact);
    }

    public final ImmutableVisualShovelerElement withItems(Iterable<? extends VerticalItemElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableVisualShovelerElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.seeMore, this.additionalActionButtons, this.isCompact);
    }

    public final ImmutableVisualShovelerElement withSeeMore(@Nullable SeeMoreItemElement seeMoreItemElement) {
        return this.seeMore == seeMoreItemElement ? this : new ImmutableVisualShovelerElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items, seeMoreItemElement, this.additionalActionButtons, this.isCompact);
    }

    public final ImmutableVisualShovelerElement withAdditionalActionButtons(@Nullable ButtonElement... buttonElementArr) {
        if (buttonElementArr == null) {
            return new ImmutableVisualShovelerElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items, this.seeMore, null, this.isCompact);
        }
        return new ImmutableVisualShovelerElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items, this.seeMore, Arrays.asList(buttonElementArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(buttonElementArr), true, false)), this.isCompact);
    }

    public final ImmutableVisualShovelerElement withAdditionalActionButtons(@Nullable Iterable<? extends ButtonElement> iterable) {
        if (this.additionalActionButtons == iterable) {
            return this;
        }
        return new ImmutableVisualShovelerElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items, this.seeMore, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.isCompact);
    }

    public final ImmutableVisualShovelerElement withIsCompact(boolean z) {
        return this.isCompact == z ? this : new ImmutableVisualShovelerElement(this.widgetId, this.onViewed, this.primaryHeader, this.secondaryHeader, this.items, this.seeMore, this.additionalActionButtons, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVisualShovelerElement) && equalTo(0, (ImmutableVisualShovelerElement) obj);
    }

    private boolean equalTo(int i, ImmutableVisualShovelerElement immutableVisualShovelerElement) {
        return this.widgetId.equals(immutableVisualShovelerElement.widgetId) && this.onViewed.equals(immutableVisualShovelerElement.onViewed) && this.primaryHeader.equals(immutableVisualShovelerElement.primaryHeader) && equals(this.secondaryHeader, immutableVisualShovelerElement.secondaryHeader) && this.items.equals(immutableVisualShovelerElement.items) && equals(this.seeMore, immutableVisualShovelerElement.seeMore) && equals(this.additionalActionButtons, immutableVisualShovelerElement.additionalActionButtons) && this.isCompact == immutableVisualShovelerElement.isCompact;
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.primaryHeader.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.secondaryHeader);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.items.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.seeMore);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.additionalActionButtons);
        return iHashCode7 + (iHashCode7 << 5) + (this.isCompact ? 1231 : 1237);
    }

    public String toString() {
        return "VisualShovelerElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", primaryHeader=" + this.primaryHeader + ", secondaryHeader=" + this.secondaryHeader + ", items=" + this.items + ", seeMore=" + this.seeMore + ", additionalActionButtons=" + this.additionalActionButtons + ", isCompact=" + this.isCompact + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VisualShovelerElement", generator = "Immutables")
    static final class Json extends VisualShovelerElement {
        boolean isCompact;
        boolean isCompactIsSet;
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
        List<VerticalItemElement> items = Collections.emptyList();

        @Nullable
        List<ButtonElement> additionalActionButtons = null;

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
        public void setItems(List<VerticalItemElement> list) {
            this.items = list;
        }

        @JsonProperty(PageUriUtils.DO_SEE_MORE_VALUE)
        public void setSeeMore(@Nullable SeeMoreItemElement seeMoreItemElement) {
            this.seeMore = seeMoreItemElement;
        }

        @JsonProperty("additionalActionButtons")
        public void setAdditionalActionButtons(@Nullable List<ButtonElement> list) {
            this.additionalActionButtons = list;
        }

        @JsonProperty("isCompact")
        public void setIsCompact(boolean z) {
            this.isCompact = z;
            this.isCompactIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ShovelerElement
        public String primaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ShovelerElement
        public String secondaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VisualShovelerElement
        public List<VerticalItemElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VisualShovelerElement
        public SeeMoreItemElement seeMore() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VisualShovelerElement
        public List<ButtonElement> additionalActionButtons() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VisualShovelerElement
        public boolean isCompact() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVisualShovelerElement fromJson(Json json) {
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
        List<VerticalItemElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        SeeMoreItemElement seeMoreItemElement = json.seeMore;
        if (seeMoreItemElement != null) {
            builder.seeMore(seeMoreItemElement);
        }
        List<ButtonElement> list2 = json.additionalActionButtons;
        if (list2 != null) {
            builder.addAllAdditionalActionButtons(list2);
        }
        if (json.isCompactIsSet) {
            builder.isCompact(json.isCompact);
        }
        return builder.build();
    }

    public static ImmutableVisualShovelerElement copyOf(VisualShovelerElement visualShovelerElement) {
        if (visualShovelerElement instanceof ImmutableVisualShovelerElement) {
            return (ImmutableVisualShovelerElement) visualShovelerElement;
        }
        return builder().from(visualShovelerElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VisualShovelerElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_HEADER = 1;
        private static final long OPT_BIT_IS_COMPACT = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private List<ButtonElement> additionalActionButtons;
        private long initBits;
        private boolean isCompact;
        private List<VerticalItemElement> items;
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
            this.additionalActionButtons = null;
        }

        public final Builder from(VisualShovelerElement visualShovelerElement) {
            ImmutableVisualShovelerElement.requireNonNull(visualShovelerElement, "instance");
            from((Object) visualShovelerElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableVisualShovelerElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        public final Builder from(ShovelerElement shovelerElement) {
            ImmutableVisualShovelerElement.requireNonNull(shovelerElement, "instance");
            from((Object) shovelerElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof VisualShovelerElement) {
                VisualShovelerElement visualShovelerElement = (VisualShovelerElement) obj;
                isCompact(visualShovelerElement.isCompact());
                SeeMoreItemElement seeMoreItemElementSeeMore = visualShovelerElement.seeMore();
                if (seeMoreItemElementSeeMore != null) {
                    seeMore(seeMoreItemElementSeeMore);
                }
                widgetId(visualShovelerElement.widgetId());
                List<ButtonElement> listAdditionalActionButtons = visualShovelerElement.additionalActionButtons();
                if (listAdditionalActionButtons != null) {
                    addAllAdditionalActionButtons(listAdditionalActionButtons);
                }
                String strSecondaryHeader = visualShovelerElement.secondaryHeader();
                if (strSecondaryHeader != null) {
                    secondaryHeader(strSecondaryHeader);
                }
                addAllOnViewed(visualShovelerElement.onViewed());
                addAllItems(visualShovelerElement.items());
                primaryHeader(visualShovelerElement.primaryHeader());
                j = 15;
            } else {
                j = 0;
            }
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                if ((j & 1) == 0) {
                    widgetId(widgetElement.widgetId());
                    j |= 1;
                }
                if ((j & 4) == 0) {
                    addAllOnViewed(widgetElement.onViewed());
                    j |= 4;
                }
            }
            if (obj instanceof ShovelerElement) {
                ShovelerElement shovelerElement = (ShovelerElement) obj;
                if ((j & 2) == 0) {
                    String strSecondaryHeader2 = shovelerElement.secondaryHeader();
                    if (strSecondaryHeader2 != null) {
                        secondaryHeader(strSecondaryHeader2);
                    }
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    addAllOnViewed(shovelerElement.onViewed());
                    j |= 4;
                }
                if ((j & 1) == 0) {
                    widgetId(shovelerElement.widgetId());
                    j |= 1;
                }
                if ((j & 8) == 0) {
                    primaryHeader(shovelerElement.primaryHeader());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableVisualShovelerElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableVisualShovelerElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableVisualShovelerElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableVisualShovelerElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("primaryHeader")
        public final Builder primaryHeader(String str) {
            this.primaryHeader = (String) ImmutableVisualShovelerElement.requireNonNull(str, "primaryHeader");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("secondaryHeader")
        public final Builder secondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
            return this;
        }

        public final Builder addItems(VerticalItemElement verticalItemElement) {
            this.items.add((VerticalItemElement) ImmutableVisualShovelerElement.requireNonNull(verticalItemElement, "items element"));
            return this;
        }

        public final Builder addItems(VerticalItemElement... verticalItemElementArr) {
            for (VerticalItemElement verticalItemElement : verticalItemElementArr) {
                this.items.add((VerticalItemElement) ImmutableVisualShovelerElement.requireNonNull(verticalItemElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends VerticalItemElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends VerticalItemElement> iterable) {
            Iterator<? extends VerticalItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((VerticalItemElement) ImmutableVisualShovelerElement.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        @JsonProperty(PageUriUtils.DO_SEE_MORE_VALUE)
        public final Builder seeMore(@Nullable SeeMoreItemElement seeMoreItemElement) {
            this.seeMore = seeMoreItemElement;
            return this;
        }

        public final Builder addAdditionalActionButtons(ButtonElement buttonElement) {
            if (this.additionalActionButtons == null) {
                this.additionalActionButtons = new ArrayList();
            }
            this.additionalActionButtons.add((ButtonElement) ImmutableVisualShovelerElement.requireNonNull(buttonElement, "additionalActionButtons element"));
            return this;
        }

        public final Builder addAdditionalActionButtons(ButtonElement... buttonElementArr) {
            if (this.additionalActionButtons == null) {
                this.additionalActionButtons = new ArrayList();
            }
            for (ButtonElement buttonElement : buttonElementArr) {
                this.additionalActionButtons.add((ButtonElement) ImmutableVisualShovelerElement.requireNonNull(buttonElement, "additionalActionButtons element"));
            }
            return this;
        }

        @JsonProperty("additionalActionButtons")
        public final Builder additionalActionButtons(@Nullable Iterable<? extends ButtonElement> iterable) {
            if (iterable == null) {
                this.additionalActionButtons = null;
                return this;
            }
            this.additionalActionButtons = new ArrayList();
            return addAllAdditionalActionButtons(iterable);
        }

        public final Builder addAllAdditionalActionButtons(Iterable<? extends ButtonElement> iterable) {
            ImmutableVisualShovelerElement.requireNonNull(iterable, "additionalActionButtons element");
            if (this.additionalActionButtons == null) {
                this.additionalActionButtons = new ArrayList();
            }
            Iterator<? extends ButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.additionalActionButtons.add((ButtonElement) ImmutableVisualShovelerElement.requireNonNull(it.next(), "additionalActionButtons element"));
            }
            return this;
        }

        @JsonProperty("isCompact")
        public final Builder isCompact(boolean z) {
            this.isCompact = z;
            this.optBits |= 2;
            return this;
        }

        public ImmutableVisualShovelerElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableVisualShovelerElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isCompactIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("primaryHeader");
            }
            return "Cannot build VisualShovelerElement, some of required attributes are not set " + arrayList;
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
