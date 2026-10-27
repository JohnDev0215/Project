package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.IconButtonElement;
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
@Generated(from = "LabeledIconButtonElement", generator = "Immutables")
public final class ImmutableLabeledIconButtonElement extends LabeledIconButtonElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean disabled;

    @Nullable
    private final String icon;

    @Nullable
    private final String iconDescription;

    @Nullable
    private final ImageResources iconResources;
    private volatile transient InitShim initShim;
    private final boolean isActive;
    private final String label;
    private final List<Method> onItemSelected;
    private final List<Method> onViewed;

    @Nullable
    private final ResourceId resourceId;
    private final boolean shouldToggle;

    @Nullable
    private final IconButtonElement.Style style;
    private final String widgetId;

    private ImmutableLabeledIconButtonElement(Builder builder) {
        this.initShim = new InitShim();
        this.resourceId = builder.resourceId;
        this.style = builder.style;
        this.icon = builder.icon;
        this.iconDescription = builder.iconDescription;
        this.iconResources = builder.iconResources;
        this.label = builder.label;
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
        if (builder.shouldToggleIsSet()) {
            this.initShim.shouldToggle(builder.shouldToggle);
        }
        if (builder.isActiveIsSet()) {
            this.initShim.isActive(builder.isActive);
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.disabled = this.initShim.disabled();
        this.onItemSelected = this.initShim.onItemSelected();
        this.shouldToggle = this.initShim.shouldToggle();
        this.isActive = this.initShim.isActive();
        this.initShim = null;
    }

    private ImmutableLabeledIconButtonElement(String str, List<Method> list, Boolean bool, List<Method> list2, @Nullable ResourceId resourceId, @Nullable IconButtonElement.Style style, @Nullable String str2, @Nullable String str3, @Nullable ImageResources imageResources, boolean z, boolean z2, String str4) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.disabled = bool;
        this.onItemSelected = list2;
        this.resourceId = resourceId;
        this.style = style;
        this.icon = str2;
        this.iconDescription = str3;
        this.iconResources = imageResources;
        this.shouldToggle = z;
        this.isActive = z2;
        this.label = str4;
        this.initShim = null;
    }

    @Generated(from = "LabeledIconButtonElement", generator = "Immutables")
    private final class InitShim {
        private Boolean disabled;
        private byte disabledBuildStage;
        private boolean isActive;
        private byte isActiveBuildStage;
        private List<Method> onItemSelected;
        private byte onItemSelectedBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private boolean shouldToggle;
        private byte shouldToggleBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.disabledBuildStage = (byte) 0;
            this.onItemSelectedBuildStage = (byte) 0;
            this.shouldToggleBuildStage = (byte) 0;
            this.isActiveBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableLabeledIconButtonElement.requireNonNull(ImmutableLabeledIconButtonElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableLabeledIconButtonElement.createUnmodifiableList(false, ImmutableLabeledIconButtonElement.createSafeList(ImmutableLabeledIconButtonElement.super.onViewed(), true, false));
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
                this.disabled = (Boolean) ImmutableLabeledIconButtonElement.requireNonNull(ImmutableLabeledIconButtonElement.super.disabled(), "disabled");
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
                this.onItemSelected = ImmutableLabeledIconButtonElement.createUnmodifiableList(false, ImmutableLabeledIconButtonElement.createSafeList(ImmutableLabeledIconButtonElement.super.onItemSelected(), true, false));
                this.onItemSelectedBuildStage = (byte) 1;
            }
            return this.onItemSelected;
        }

        void onItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedBuildStage = (byte) 1;
        }

        boolean shouldToggle() {
            byte b = this.shouldToggleBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.shouldToggleBuildStage = (byte) -1;
                this.shouldToggle = ImmutableLabeledIconButtonElement.super.shouldToggle();
                this.shouldToggleBuildStage = (byte) 1;
            }
            return this.shouldToggle;
        }

        void shouldToggle(boolean z) {
            this.shouldToggle = z;
            this.shouldToggleBuildStage = (byte) 1;
        }

        boolean isActive() {
            byte b = this.isActiveBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isActiveBuildStage = (byte) -1;
                this.isActive = ImmutableLabeledIconButtonElement.super.isActive();
                this.isActiveBuildStage = (byte) 1;
            }
            return this.isActive;
        }

        void isActive(boolean z) {
            this.isActive = z;
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
            if (this.disabledBuildStage == -1) {
                arrayList.add("disabled");
            }
            if (this.onItemSelectedBuildStage == -1) {
                arrayList.add("onItemSelected");
            }
            if (this.shouldToggleBuildStage == -1) {
                arrayList.add("shouldToggle");
            }
            if (this.isActiveBuildStage == -1) {
                arrayList.add("isActive");
            }
            return "Cannot build LabeledIconButtonElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
    @JsonProperty("style")
    @Nullable
    public IconButtonElement.Style style() {
        return this.style;
    }

    @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
    @JsonProperty("icon")
    @Nullable
    public String icon() {
        return this.icon;
    }

    @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
    @JsonProperty("iconDescription")
    @Nullable
    public String iconDescription() {
        return this.iconDescription;
    }

    @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
    @JsonProperty("iconResources")
    @Nullable
    public ImageResources iconResources() {
        return this.iconResources;
    }

    @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
    @JsonProperty("shouldToggle")
    public boolean shouldToggle() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shouldToggle();
        }
        return this.shouldToggle;
    }

    @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
    @JsonProperty("isActive")
    public boolean isActive() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isActive();
        }
        return this.isActive;
    }

    @Override // Touch.WidgetsInterface.v1_0.LabeledIconButtonElement
    @JsonProperty(ContextMappingConstants.LABEL)
    public String label() {
        return this.label;
    }

    public final ImmutableLabeledIconButtonElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableLabeledIconButtonElement(str2, this.onViewed, this.disabled, this.onItemSelected, this.resourceId, this.style, this.icon, this.iconDescription, this.iconResources, this.shouldToggle, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withOnViewed(Method... methodArr) {
        return new ImmutableLabeledIconButtonElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.disabled, this.onItemSelected, this.resourceId, this.style, this.icon, this.iconDescription, this.iconResources, this.shouldToggle, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableLabeledIconButtonElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.disabled, this.onItemSelected, this.resourceId, this.style, this.icon, this.iconDescription, this.iconResources, this.shouldToggle, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "disabled");
        return this.disabled.equals(bool2) ? this : new ImmutableLabeledIconButtonElement(this.widgetId, this.onViewed, bool2, this.onItemSelected, this.resourceId, this.style, this.icon, this.iconDescription, this.iconResources, this.shouldToggle, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withOnItemSelected(Method... methodArr) {
        return new ImmutableLabeledIconButtonElement(this.widgetId, this.onViewed, this.disabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.resourceId, this.style, this.icon, this.iconDescription, this.iconResources, this.shouldToggle, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableLabeledIconButtonElement(this.widgetId, this.onViewed, this.disabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.resourceId, this.style, this.icon, this.iconDescription, this.iconResources, this.shouldToggle, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withResourceId(@Nullable ResourceId resourceId) {
        return this.resourceId == resourceId ? this : new ImmutableLabeledIconButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, resourceId, this.style, this.icon, this.iconDescription, this.iconResources, this.shouldToggle, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withStyle(@Nullable IconButtonElement.Style style) {
        return this.style == style ? this : new ImmutableLabeledIconButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, this.resourceId, style, this.icon, this.iconDescription, this.iconResources, this.shouldToggle, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withIcon(@Nullable String str) {
        return equals(this.icon, str) ? this : new ImmutableLabeledIconButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, this.resourceId, this.style, str, this.iconDescription, this.iconResources, this.shouldToggle, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withIconDescription(@Nullable String str) {
        return equals(this.iconDescription, str) ? this : new ImmutableLabeledIconButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, this.resourceId, this.style, this.icon, str, this.iconResources, this.shouldToggle, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withIconResources(@Nullable ImageResources imageResources) {
        return this.iconResources == imageResources ? this : new ImmutableLabeledIconButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, this.resourceId, this.style, this.icon, this.iconDescription, imageResources, this.shouldToggle, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withShouldToggle(boolean z) {
        return this.shouldToggle == z ? this : new ImmutableLabeledIconButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, this.resourceId, this.style, this.icon, this.iconDescription, this.iconResources, z, this.isActive, this.label);
    }

    public final ImmutableLabeledIconButtonElement withIsActive(boolean z) {
        return this.isActive == z ? this : new ImmutableLabeledIconButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, this.resourceId, this.style, this.icon, this.iconDescription, this.iconResources, this.shouldToggle, z, this.label);
    }

    public final ImmutableLabeledIconButtonElement withLabel(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.LABEL);
        return this.label.equals(str2) ? this : new ImmutableLabeledIconButtonElement(this.widgetId, this.onViewed, this.disabled, this.onItemSelected, this.resourceId, this.style, this.icon, this.iconDescription, this.iconResources, this.shouldToggle, this.isActive, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableLabeledIconButtonElement) && equalTo(0, (ImmutableLabeledIconButtonElement) obj);
    }

    private boolean equalTo(int i, ImmutableLabeledIconButtonElement immutableLabeledIconButtonElement) {
        return this.widgetId.equals(immutableLabeledIconButtonElement.widgetId) && this.onViewed.equals(immutableLabeledIconButtonElement.onViewed) && this.disabled.equals(immutableLabeledIconButtonElement.disabled) && this.onItemSelected.equals(immutableLabeledIconButtonElement.onItemSelected) && equals(this.resourceId, immutableLabeledIconButtonElement.resourceId) && equals(this.style, immutableLabeledIconButtonElement.style) && equals(this.icon, immutableLabeledIconButtonElement.icon) && equals(this.iconDescription, immutableLabeledIconButtonElement.iconDescription) && equals(this.iconResources, immutableLabeledIconButtonElement.iconResources) && this.shouldToggle == immutableLabeledIconButtonElement.shouldToggle && this.isActive == immutableLabeledIconButtonElement.isActive && this.label.equals(immutableLabeledIconButtonElement.label);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.disabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onItemSelected.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.resourceId);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.style);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.icon);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.iconDescription);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.iconResources);
        int i = iHashCode9 + (iHashCode9 << 5) + (this.shouldToggle ? 1231 : 1237);
        int i2 = i + (i << 5) + (this.isActive ? 1231 : 1237);
        return i2 + (i2 << 5) + this.label.hashCode();
    }

    public String toString() {
        return "LabeledIconButtonElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", disabled=" + this.disabled + ", onItemSelected=" + this.onItemSelected + ", resourceId=" + this.resourceId + ", style=" + this.style + ", icon=" + this.icon + ", iconDescription=" + this.iconDescription + ", iconResources=" + this.iconResources + ", shouldToggle=" + this.shouldToggle + ", isActive=" + this.isActive + ", label=" + this.label + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "LabeledIconButtonElement", generator = "Immutables")
    static final class Json extends LabeledIconButtonElement {

        @Nullable
        Boolean disabled;

        @Nullable
        String icon;

        @Nullable
        String iconDescription;

        @Nullable
        ImageResources iconResources;
        boolean isActive;
        boolean isActiveIsSet;

        @Nullable
        String label;
        boolean onItemSelectedIsSet;
        boolean onViewedIsSet;

        @Nullable
        ResourceId resourceId;
        boolean shouldToggle;
        boolean shouldToggleIsSet;

        @Nullable
        IconButtonElement.Style style;

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

        @JsonProperty("style")
        public void setStyle(@Nullable IconButtonElement.Style style) {
            this.style = style;
        }

        @JsonProperty("icon")
        public void setIcon(@Nullable String str) {
            this.icon = str;
        }

        @JsonProperty("iconDescription")
        public void setIconDescription(@Nullable String str) {
            this.iconDescription = str;
        }

        @JsonProperty("iconResources")
        public void setIconResources(@Nullable ImageResources imageResources) {
            this.iconResources = imageResources;
        }

        @JsonProperty("shouldToggle")
        public void setShouldToggle(boolean z) {
            this.shouldToggle = z;
            this.shouldToggleIsSet = true;
        }

        @JsonProperty("isActive")
        public void setIsActive(boolean z) {
            this.isActive = z;
            this.isActiveIsSet = true;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public void setLabel(String str) {
            this.label = str;
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

        @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
        public IconButtonElement.Style style() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
        public String icon() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
        public String iconDescription() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
        public ImageResources iconResources() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
        public boolean shouldToggle() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.IconButtonElement
        public boolean isActive() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.LabeledIconButtonElement
        public String label() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableLabeledIconButtonElement fromJson(Json json) {
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
        IconButtonElement.Style style = json.style;
        if (style != null) {
            builder.style(style);
        }
        String str2 = json.icon;
        if (str2 != null) {
            builder.icon(str2);
        }
        String str3 = json.iconDescription;
        if (str3 != null) {
            builder.iconDescription(str3);
        }
        ImageResources imageResources = json.iconResources;
        if (imageResources != null) {
            builder.iconResources(imageResources);
        }
        if (json.shouldToggleIsSet) {
            builder.shouldToggle(json.shouldToggle);
        }
        if (json.isActiveIsSet) {
            builder.isActive(json.isActive);
        }
        String str4 = json.label;
        if (str4 != null) {
            builder.label(str4);
        }
        return builder.build();
    }

    public static ImmutableLabeledIconButtonElement copyOf(LabeledIconButtonElement labeledIconButtonElement) {
        if (labeledIconButtonElement instanceof ImmutableLabeledIconButtonElement) {
            return (ImmutableLabeledIconButtonElement) labeledIconButtonElement;
        }
        return builder().from(labeledIconButtonElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "LabeledIconButtonElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_LABEL = 1;
        private static final long OPT_BIT_IS_ACTIVE = 8;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private static final long OPT_BIT_SHOULD_TOGGLE = 4;

        @Nullable
        private Boolean disabled;

        @Nullable
        private String icon;

        @Nullable
        private String iconDescription;

        @Nullable
        private ImageResources iconResources;
        private long initBits;
        private boolean isActive;

        @Nullable
        private String label;
        private List<Method> onItemSelected;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private ResourceId resourceId;
        private boolean shouldToggle;

        @Nullable
        private IconButtonElement.Style style;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 1L;
            this.onViewed = new ArrayList();
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(IconButtonElement iconButtonElement) {
            ImmutableLabeledIconButtonElement.requireNonNull(iconButtonElement, "instance");
            from((Object) iconButtonElement);
            return this;
        }

        public final Builder from(LabeledIconButtonElement labeledIconButtonElement) {
            ImmutableLabeledIconButtonElement.requireNonNull(labeledIconButtonElement, "instance");
            from((Object) labeledIconButtonElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableLabeledIconButtonElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        public final Builder from(ButtonElement buttonElement) {
            ImmutableLabeledIconButtonElement.requireNonNull(buttonElement, "instance");
            from((Object) buttonElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof IconButtonElement) {
                IconButtonElement iconButtonElement = (IconButtonElement) obj;
                addAllOnItemSelected(iconButtonElement.onItemSelected());
                shouldToggle(iconButtonElement.shouldToggle());
                ResourceId resourceId = iconButtonElement.resourceId();
                if (resourceId != null) {
                    resourceId(resourceId);
                }
                String strIconDescription = iconButtonElement.iconDescription();
                if (strIconDescription != null) {
                    iconDescription(strIconDescription);
                }
                widgetId(iconButtonElement.widgetId());
                String strIcon = iconButtonElement.icon();
                if (strIcon != null) {
                    icon(strIcon);
                }
                disabled(iconButtonElement.disabled());
                IconButtonElement.Style style = iconButtonElement.style();
                if (style != null) {
                    style(style);
                }
                addAllOnViewed(iconButtonElement.onViewed());
                ImageResources imageResourcesIconResources = iconButtonElement.iconResources();
                if (imageResourcesIconResources != null) {
                    iconResources(imageResourcesIconResources);
                }
                isActive(iconButtonElement.isActive());
                j = 2047;
            } else {
                j = 0;
            }
            if (obj instanceof LabeledIconButtonElement) {
                LabeledIconButtonElement labeledIconButtonElement = (LabeledIconButtonElement) obj;
                if ((j & 1) == 0) {
                    addAllOnItemSelected(labeledIconButtonElement.onItemSelected());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    shouldToggle(labeledIconButtonElement.shouldToggle());
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    ResourceId resourceId2 = labeledIconButtonElement.resourceId();
                    if (resourceId2 != null) {
                        resourceId(resourceId2);
                    }
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    String strIconDescription2 = labeledIconButtonElement.iconDescription();
                    if (strIconDescription2 != null) {
                        iconDescription(strIconDescription2);
                    }
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    widgetId(labeledIconButtonElement.widgetId());
                    j |= 16;
                }
                if ((j & 32) == 0) {
                    String strIcon2 = labeledIconButtonElement.icon();
                    if (strIcon2 != null) {
                        icon(strIcon2);
                    }
                    j |= 32;
                }
                if ((j & 64) == 0) {
                    disabled(labeledIconButtonElement.disabled());
                    j |= 64;
                }
                if ((j & 128) == 0) {
                    IconButtonElement.Style style2 = labeledIconButtonElement.style();
                    if (style2 != null) {
                        style(style2);
                    }
                    j |= 128;
                }
                if ((j & 256) == 0) {
                    addAllOnViewed(labeledIconButtonElement.onViewed());
                    j |= 256;
                }
                if ((j & 512) == 0) {
                    ImageResources imageResourcesIconResources2 = labeledIconButtonElement.iconResources();
                    if (imageResourcesIconResources2 != null) {
                        iconResources(imageResourcesIconResources2);
                    }
                    j |= 512;
                }
                label(labeledIconButtonElement.label());
                if ((j & 1024) == 0) {
                    isActive(labeledIconButtonElement.isActive());
                    j |= 1024;
                }
            }
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                if ((j & 16) == 0) {
                    widgetId(widgetElement.widgetId());
                    j |= 16;
                }
                if ((j & 256) == 0) {
                    addAllOnViewed(widgetElement.onViewed());
                    j |= 256;
                }
            }
            if (obj instanceof ButtonElement) {
                ButtonElement buttonElement = (ButtonElement) obj;
                if ((j & 1) == 0) {
                    addAllOnItemSelected(buttonElement.onItemSelected());
                    j |= 1;
                }
                if ((j & 4) == 0) {
                    ResourceId resourceId3 = buttonElement.resourceId();
                    if (resourceId3 != null) {
                        resourceId(resourceId3);
                    }
                    j |= 4;
                }
                if ((j & 64) == 0) {
                    disabled(buttonElement.disabled());
                    j |= 64;
                }
                if ((j & 256) == 0) {
                    addAllOnViewed(buttonElement.onViewed());
                    j |= 256;
                }
                if ((j & 16) == 0) {
                    widgetId(buttonElement.widgetId());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableLabeledIconButtonElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableLabeledIconButtonElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableLabeledIconButtonElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableLabeledIconButtonElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("disabled")
        public final Builder disabled(Boolean bool) {
            this.disabled = (Boolean) ImmutableLabeledIconButtonElement.requireNonNull(bool, "disabled");
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableLabeledIconButtonElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableLabeledIconButtonElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableLabeledIconButtonElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("resourceId")
        public final Builder resourceId(@Nullable ResourceId resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        @JsonProperty("style")
        public final Builder style(@Nullable IconButtonElement.Style style) {
            this.style = style;
            return this;
        }

        @JsonProperty("icon")
        public final Builder icon(@Nullable String str) {
            this.icon = str;
            return this;
        }

        @JsonProperty("iconDescription")
        public final Builder iconDescription(@Nullable String str) {
            this.iconDescription = str;
            return this;
        }

        @JsonProperty("iconResources")
        public final Builder iconResources(@Nullable ImageResources imageResources) {
            this.iconResources = imageResources;
            return this;
        }

        @JsonProperty("shouldToggle")
        public final Builder shouldToggle(boolean z) {
            this.shouldToggle = z;
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("isActive")
        public final Builder isActive(boolean z) {
            this.isActive = z;
            this.optBits |= 8;
            return this;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public final Builder label(String str) {
            this.label = (String) ImmutableLabeledIconButtonElement.requireNonNull(str, ContextMappingConstants.LABEL);
            this.initBits &= -2;
            return this;
        }

        public ImmutableLabeledIconButtonElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableLabeledIconButtonElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemSelectedIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldToggleIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isActiveIsSet() {
            return (this.optBits & 8) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.LABEL);
            }
            return "Cannot build LabeledIconButtonElement, some of required attributes are not set " + arrayList;
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
