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
@Generated(from = "RetailHeaderElement", generator = "Immutables")
public final class ImmutableRetailHeaderElement extends RetailHeaderElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final List<ButtonElement> buttons;

    @Nullable
    private final ButtonElement callToActionButton;
    private final List<String> carouselImages;

    @Nullable
    private final String image;
    private volatile transient InitShim initShim;

    @Nullable
    private final String label;
    private final List<Method> onViewed;

    @Nullable
    private final ImageResources placeholder;

    @Nullable
    private final String primaryBadge;

    @Nullable
    private final String primaryBadgeDescription;
    private final String primaryText;

    @Nullable
    private final String secondaryText;

    @Nullable
    private final String tertiaryText;
    private final String widgetId;

    private ImmutableRetailHeaderElement(Builder builder) {
        this.initShim = new InitShim();
        this.primaryText = builder.primaryText;
        this.secondaryText = builder.secondaryText;
        this.tertiaryText = builder.tertiaryText;
        this.label = builder.label;
        this.image = builder.image;
        this.callToActionButton = builder.callToActionButton;
        this.carouselImages = createUnmodifiableList(true, builder.carouselImages);
        this.placeholder = builder.placeholder;
        this.primaryBadge = builder.primaryBadge;
        this.primaryBadgeDescription = builder.primaryBadgeDescription;
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.buttonsIsSet()) {
            this.initShim.buttons(createUnmodifiableList(true, builder.buttons));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.buttons = this.initShim.buttons();
        this.initShim = null;
    }

    private ImmutableRetailHeaderElement(String str, List<Method> list, String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable ButtonElement buttonElement, List<ButtonElement> list2, List<String> list3, @Nullable ImageResources imageResources, @Nullable String str7, @Nullable String str8) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.primaryText = str2;
        this.secondaryText = str3;
        this.tertiaryText = str4;
        this.label = str5;
        this.image = str6;
        this.callToActionButton = buttonElement;
        this.buttons = list2;
        this.carouselImages = list3;
        this.placeholder = imageResources;
        this.primaryBadge = str7;
        this.primaryBadgeDescription = str8;
        this.initShim = null;
    }

    @Generated(from = "RetailHeaderElement", generator = "Immutables")
    private final class InitShim {
        private List<ButtonElement> buttons;
        private byte buttonsBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.buttonsBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableRetailHeaderElement.requireNonNull(ImmutableRetailHeaderElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableRetailHeaderElement.createUnmodifiableList(false, ImmutableRetailHeaderElement.createSafeList(ImmutableRetailHeaderElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        List<ButtonElement> buttons() {
            byte b = this.buttonsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.buttonsBuildStage = (byte) -1;
                this.buttons = ImmutableRetailHeaderElement.createUnmodifiableList(false, ImmutableRetailHeaderElement.createSafeList(ImmutableRetailHeaderElement.super.buttons(), true, false));
                this.buttonsBuildStage = (byte) 1;
            }
            return this.buttons;
        }

        void buttons(List<ButtonElement> list) {
            this.buttons = list;
            this.buttonsBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.buttonsBuildStage == -1) {
                arrayList.add("buttons");
            }
            return "Cannot build RetailHeaderElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    @Nullable
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
    @Nullable
    public String tertiaryText() {
        return this.tertiaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty(ContextMappingConstants.LABEL)
    @Nullable
    public String label() {
        return this.label;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty(ContextMappingConstants.IMAGE)
    @Nullable
    public String image() {
        return this.image;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty("callToActionButton")
    @Nullable
    public ButtonElement callToActionButton() {
        return this.callToActionButton;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty("buttons")
    public List<ButtonElement> buttons() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.buttons();
        }
        return this.buttons;
    }

    @Override // Touch.WidgetsInterface.v1_0.RetailHeaderElement
    @JsonProperty("carouselImages")
    public List<String> carouselImages() {
        return this.carouselImages;
    }

    @Override // Touch.WidgetsInterface.v1_0.RetailHeaderElement
    @JsonProperty("placeholder")
    @Nullable
    public ImageResources placeholder() {
        return this.placeholder;
    }

    @Override // Touch.WidgetsInterface.v1_0.RetailHeaderElement
    @JsonProperty("primaryBadge")
    @Nullable
    public String primaryBadge() {
        return this.primaryBadge;
    }

    @Override // Touch.WidgetsInterface.v1_0.RetailHeaderElement
    @JsonProperty("primaryBadgeDescription")
    @Nullable
    public String primaryBadgeDescription() {
        return this.primaryBadgeDescription;
    }

    public final ImmutableRetailHeaderElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableRetailHeaderElement(str2, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.carouselImages, this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withOnViewed(Method... methodArr) {
        return new ImmutableRetailHeaderElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.carouselImages, this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableRetailHeaderElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.carouselImages, this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, str2, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.carouselImages, this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, str, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.carouselImages, this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withTertiaryText(@Nullable String str) {
        return equals(this.tertiaryText, str) ? this : new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, str, this.label, this.image, this.callToActionButton, this.buttons, this.carouselImages, this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withLabel(@Nullable String str) {
        return equals(this.label, str) ? this : new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, str, this.image, this.callToActionButton, this.buttons, this.carouselImages, this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withImage(@Nullable String str) {
        return equals(this.image, str) ? this : new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, str, this.callToActionButton, this.buttons, this.carouselImages, this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withCallToActionButton(@Nullable ButtonElement buttonElement) {
        return this.callToActionButton == buttonElement ? this : new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, buttonElement, this.buttons, this.carouselImages, this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withButtons(ButtonElement... buttonElementArr) {
        return new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, createUnmodifiableList(false, createSafeList(Arrays.asList(buttonElementArr), true, false)), this.carouselImages, this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withButtons(Iterable<? extends ButtonElement> iterable) {
        if (this.buttons == iterable) {
            return this;
        }
        return new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.carouselImages, this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withCarouselImages(String... strArr) {
        return new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, createUnmodifiableList(false, createSafeList(Arrays.asList(strArr), true, false)), this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withCarouselImages(Iterable<String> iterable) {
        if (this.carouselImages == iterable) {
            return this;
        }
        return new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.placeholder, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withPlaceholder(@Nullable ImageResources imageResources) {
        return this.placeholder == imageResources ? this : new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.carouselImages, imageResources, this.primaryBadge, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withPrimaryBadge(@Nullable String str) {
        return equals(this.primaryBadge, str) ? this : new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.carouselImages, this.placeholder, str, this.primaryBadgeDescription);
    }

    public final ImmutableRetailHeaderElement withPrimaryBadgeDescription(@Nullable String str) {
        return equals(this.primaryBadgeDescription, str) ? this : new ImmutableRetailHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.carouselImages, this.placeholder, this.primaryBadge, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableRetailHeaderElement) && equalTo(0, (ImmutableRetailHeaderElement) obj);
    }

    private boolean equalTo(int i, ImmutableRetailHeaderElement immutableRetailHeaderElement) {
        return this.widgetId.equals(immutableRetailHeaderElement.widgetId) && this.onViewed.equals(immutableRetailHeaderElement.onViewed) && this.primaryText.equals(immutableRetailHeaderElement.primaryText) && equals(this.secondaryText, immutableRetailHeaderElement.secondaryText) && equals(this.tertiaryText, immutableRetailHeaderElement.tertiaryText) && equals(this.label, immutableRetailHeaderElement.label) && equals(this.image, immutableRetailHeaderElement.image) && equals(this.callToActionButton, immutableRetailHeaderElement.callToActionButton) && this.buttons.equals(immutableRetailHeaderElement.buttons) && this.carouselImages.equals(immutableRetailHeaderElement.carouselImages) && equals(this.placeholder, immutableRetailHeaderElement.placeholder) && equals(this.primaryBadge, immutableRetailHeaderElement.primaryBadge) && equals(this.primaryBadgeDescription, immutableRetailHeaderElement.primaryBadgeDescription);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.primaryText.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.secondaryText);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.tertiaryText);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.label);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.image);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.callToActionButton);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + this.buttons.hashCode();
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + this.carouselImages.hashCode();
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + hashCode(this.placeholder);
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + hashCode(this.primaryBadge);
        return iHashCode12 + (iHashCode12 << 5) + hashCode(this.primaryBadgeDescription);
    }

    public String toString() {
        return "RetailHeaderElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", tertiaryText=" + this.tertiaryText + ", label=" + this.label + ", image=" + this.image + ", callToActionButton=" + this.callToActionButton + ", buttons=" + this.buttons + ", carouselImages=" + this.carouselImages + ", placeholder=" + this.placeholder + ", primaryBadge=" + this.primaryBadge + ", primaryBadgeDescription=" + this.primaryBadgeDescription + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "RetailHeaderElement", generator = "Immutables")
    static final class Json extends RetailHeaderElement {
        boolean buttonsIsSet;

        @Nullable
        ButtonElement callToActionButton;

        @Nullable
        String image;

        @Nullable
        String label;
        boolean onViewedIsSet;

        @Nullable
        ImageResources placeholder;

        @Nullable
        String primaryBadge;

        @Nullable
        String primaryBadgeDescription;

        @Nullable
        String primaryText;

        @Nullable
        String secondaryText;

        @Nullable
        String tertiaryText;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<ButtonElement> buttons = Collections.emptyList();

        @Nullable
        List<String> carouselImages = Collections.emptyList();

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

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public void setPrimaryText(String str) {
            this.primaryText = str;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public void setSecondaryText(@Nullable String str) {
            this.secondaryText = str;
        }

        @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
        public void setTertiaryText(@Nullable String str) {
            this.tertiaryText = str;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public void setLabel(@Nullable String str) {
            this.label = str;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(@Nullable String str) {
            this.image = str;
        }

        @JsonProperty("callToActionButton")
        public void setCallToActionButton(@Nullable ButtonElement buttonElement) {
            this.callToActionButton = buttonElement;
        }

        @JsonProperty("buttons")
        public void setButtons(List<ButtonElement> list) {
            this.buttons = list;
            this.buttonsIsSet = true;
        }

        @JsonProperty("carouselImages")
        public void setCarouselImages(List<String> list) {
            this.carouselImages = list;
        }

        @JsonProperty("placeholder")
        public void setPlaceholder(@Nullable ImageResources imageResources) {
            this.placeholder = imageResources;
        }

        @JsonProperty("primaryBadge")
        public void setPrimaryBadge(@Nullable String str) {
            this.primaryBadge = str;
        }

        @JsonProperty("primaryBadgeDescription")
        public void setPrimaryBadgeDescription(@Nullable String str) {
            this.primaryBadgeDescription = str;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public String tertiaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public String label() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public ButtonElement callToActionButton() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public List<ButtonElement> buttons() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RetailHeaderElement
        public List<String> carouselImages() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RetailHeaderElement
        public ImageResources placeholder() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RetailHeaderElement
        public String primaryBadge() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.RetailHeaderElement
        public String primaryBadgeDescription() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableRetailHeaderElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        String str2 = json.primaryText;
        if (str2 != null) {
            builder.primaryText(str2);
        }
        String str3 = json.secondaryText;
        if (str3 != null) {
            builder.secondaryText(str3);
        }
        String str4 = json.tertiaryText;
        if (str4 != null) {
            builder.tertiaryText(str4);
        }
        String str5 = json.label;
        if (str5 != null) {
            builder.label(str5);
        }
        String str6 = json.image;
        if (str6 != null) {
            builder.image(str6);
        }
        ButtonElement buttonElement = json.callToActionButton;
        if (buttonElement != null) {
            builder.callToActionButton(buttonElement);
        }
        if (json.buttonsIsSet) {
            builder.addAllButtons(json.buttons);
        }
        List<String> list = json.carouselImages;
        if (list != null) {
            builder.addAllCarouselImages(list);
        }
        ImageResources imageResources = json.placeholder;
        if (imageResources != null) {
            builder.placeholder(imageResources);
        }
        String str7 = json.primaryBadge;
        if (str7 != null) {
            builder.primaryBadge(str7);
        }
        String str8 = json.primaryBadgeDescription;
        if (str8 != null) {
            builder.primaryBadgeDescription(str8);
        }
        return builder.build();
    }

    public static ImmutableRetailHeaderElement copyOf(RetailHeaderElement retailHeaderElement) {
        if (retailHeaderElement instanceof ImmutableRetailHeaderElement) {
            return (ImmutableRetailHeaderElement) retailHeaderElement;
        }
        return builder().from(retailHeaderElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "RetailHeaderElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_TEXT = 1;
        private static final long OPT_BIT_BUTTONS = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private List<ButtonElement> buttons;

        @Nullable
        private ButtonElement callToActionButton;
        private List<String> carouselImages;

        @Nullable
        private String image;
        private long initBits;

        @Nullable
        private String label;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private ImageResources placeholder;

        @Nullable
        private String primaryBadge;

        @Nullable
        private String primaryBadgeDescription;

        @Nullable
        private String primaryText;

        @Nullable
        private String secondaryText;

        @Nullable
        private String tertiaryText;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 1L;
            this.onViewed = new ArrayList();
            this.buttons = new ArrayList();
            this.carouselImages = new ArrayList();
        }

        public final Builder from(HeaderElement headerElement) {
            ImmutableRetailHeaderElement.requireNonNull(headerElement, "instance");
            from((Object) headerElement);
            return this;
        }

        public final Builder from(RetailHeaderElement retailHeaderElement) {
            ImmutableRetailHeaderElement.requireNonNull(retailHeaderElement, "instance");
            from((Object) retailHeaderElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableRetailHeaderElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof HeaderElement) {
                HeaderElement headerElement = (HeaderElement) obj;
                String strImage = headerElement.image();
                if (strImage != null) {
                    image(strImage);
                }
                ButtonElement buttonElementCallToActionButton = headerElement.callToActionButton();
                if (buttonElementCallToActionButton != null) {
                    callToActionButton(buttonElementCallToActionButton);
                }
                addAllButtons(headerElement.buttons());
                widgetId(headerElement.widgetId());
                primaryText(headerElement.primaryText());
                addAllOnViewed(headerElement.onViewed());
                String strLabel = headerElement.label();
                if (strLabel != null) {
                    label(strLabel);
                }
                String strTertiaryText = headerElement.tertiaryText();
                if (strTertiaryText != null) {
                    tertiaryText(strTertiaryText);
                }
                String strSecondaryText = headerElement.secondaryText();
                if (strSecondaryText != null) {
                    secondaryText(strSecondaryText);
                }
                j = 511;
            } else {
                j = 0;
            }
            if (obj instanceof RetailHeaderElement) {
                RetailHeaderElement retailHeaderElement = (RetailHeaderElement) obj;
                if ((j & 1) == 0) {
                    String strImage2 = retailHeaderElement.image();
                    if (strImage2 != null) {
                        image(strImage2);
                    }
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    addAllButtons(retailHeaderElement.buttons());
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    widgetId(retailHeaderElement.widgetId());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    primaryText(retailHeaderElement.primaryText());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    addAllOnViewed(retailHeaderElement.onViewed());
                    j |= 16;
                }
                if ((j & 32) == 0) {
                    String strLabel2 = retailHeaderElement.label();
                    if (strLabel2 != null) {
                        label(strLabel2);
                    }
                    j |= 32;
                }
                addAllCarouselImages(retailHeaderElement.carouselImages());
                if ((j & 64) == 0) {
                    String strSecondaryText2 = retailHeaderElement.secondaryText();
                    if (strSecondaryText2 != null) {
                        secondaryText(strSecondaryText2);
                    }
                    j |= 64;
                }
                if ((j & 128) == 0) {
                    ButtonElement buttonElementCallToActionButton2 = retailHeaderElement.callToActionButton();
                    if (buttonElementCallToActionButton2 != null) {
                        callToActionButton(buttonElementCallToActionButton2);
                    }
                    j |= 128;
                }
                String strPrimaryBadge = retailHeaderElement.primaryBadge();
                if (strPrimaryBadge != null) {
                    primaryBadge(strPrimaryBadge);
                }
                String strPrimaryBadgeDescription = retailHeaderElement.primaryBadgeDescription();
                if (strPrimaryBadgeDescription != null) {
                    primaryBadgeDescription(strPrimaryBadgeDescription);
                }
                ImageResources imageResourcesPlaceholder = retailHeaderElement.placeholder();
                if (imageResourcesPlaceholder != null) {
                    placeholder(imageResourcesPlaceholder);
                }
                if ((j & 256) == 0) {
                    String strTertiaryText2 = retailHeaderElement.tertiaryText();
                    if (strTertiaryText2 != null) {
                        tertiaryText(strTertiaryText2);
                    }
                    j |= 256;
                }
            }
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                if ((j & 4) == 0) {
                    widgetId(widgetElement.widgetId());
                    j |= 4;
                }
                if ((j & 16) == 0) {
                    addAllOnViewed(widgetElement.onViewed());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableRetailHeaderElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableRetailHeaderElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableRetailHeaderElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableRetailHeaderElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableRetailHeaderElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public final Builder secondaryText(@Nullable String str) {
            this.secondaryText = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
        public final Builder tertiaryText(@Nullable String str) {
            this.tertiaryText = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public final Builder label(@Nullable String str) {
            this.label = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(@Nullable String str) {
            this.image = str;
            return this;
        }

        @JsonProperty("callToActionButton")
        public final Builder callToActionButton(@Nullable ButtonElement buttonElement) {
            this.callToActionButton = buttonElement;
            return this;
        }

        public final Builder addButtons(ButtonElement buttonElement) {
            this.buttons.add((ButtonElement) ImmutableRetailHeaderElement.requireNonNull(buttonElement, "buttons element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addButtons(ButtonElement... buttonElementArr) {
            for (ButtonElement buttonElement : buttonElementArr) {
                this.buttons.add((ButtonElement) ImmutableRetailHeaderElement.requireNonNull(buttonElement, "buttons element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("buttons")
        public final Builder buttons(Iterable<? extends ButtonElement> iterable) {
            this.buttons.clear();
            return addAllButtons(iterable);
        }

        public final Builder addAllButtons(Iterable<? extends ButtonElement> iterable) {
            Iterator<? extends ButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.buttons.add((ButtonElement) ImmutableRetailHeaderElement.requireNonNull(it.next(), "buttons element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addCarouselImages(String str) {
            this.carouselImages.add((String) ImmutableRetailHeaderElement.requireNonNull(str, "carouselImages element"));
            return this;
        }

        public final Builder addCarouselImages(String... strArr) {
            for (String str : strArr) {
                this.carouselImages.add((String) ImmutableRetailHeaderElement.requireNonNull(str, "carouselImages element"));
            }
            return this;
        }

        @JsonProperty("carouselImages")
        public final Builder carouselImages(Iterable<String> iterable) {
            this.carouselImages.clear();
            return addAllCarouselImages(iterable);
        }

        public final Builder addAllCarouselImages(Iterable<String> iterable) {
            Iterator<String> it = iterable.iterator();
            while (it.hasNext()) {
                this.carouselImages.add((String) ImmutableRetailHeaderElement.requireNonNull(it.next(), "carouselImages element"));
            }
            return this;
        }

        @JsonProperty("placeholder")
        public final Builder placeholder(@Nullable ImageResources imageResources) {
            this.placeholder = imageResources;
            return this;
        }

        @JsonProperty("primaryBadge")
        public final Builder primaryBadge(@Nullable String str) {
            this.primaryBadge = str;
            return this;
        }

        @JsonProperty("primaryBadgeDescription")
        public final Builder primaryBadgeDescription(@Nullable String str) {
            this.primaryBadgeDescription = str;
            return this;
        }

        public ImmutableRetailHeaderElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableRetailHeaderElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean buttonsIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            return "Cannot build RetailHeaderElement, some of required attributes are not set " + arrayList;
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
