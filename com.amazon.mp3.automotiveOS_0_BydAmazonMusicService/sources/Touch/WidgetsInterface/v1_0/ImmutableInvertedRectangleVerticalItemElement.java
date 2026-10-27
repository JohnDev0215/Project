package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.android.gms.cast.MediaStatus;
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
@Generated(from = "InvertedRectangleVerticalItemElement", generator = "Immutables")
public final class ImmutableInvertedRectangleVerticalItemElement extends InvertedRectangleVerticalItemElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String backgroundImage;

    @Nullable
    private final List<BadgeItemElement> bottomBadges;

    @Nullable
    private final List<ButtonElement> buttons;

    @Nullable
    private final String icon;

    @Nullable
    private final ImageResources iconResources;
    private final String image;

    @Nullable
    private final String imageDescription;
    private volatile transient InitShim initShim;
    private final boolean isDisabled;
    private final boolean isTextOverlayed;

    @Nullable
    private final String label;
    private final List<Method> onItemContextSelected;
    private final List<Method> onItemSelected;

    @Nullable
    private final ImageResources placeholder;
    private final String primaryText;

    @Nullable
    private final Double rating;

    @Nullable
    private final Integer reviewCount;

    @Nullable
    private final String secondaryText;
    private final boolean shouldAddImagePadding;
    private final boolean shouldBlurBackgroundImage;

    @Nullable
    private final String tertiaryText;

    @Nullable
    private final List<BadgeItemElement> topBadges;

    private ImmutableInvertedRectangleVerticalItemElement(Builder builder) {
        this.initShim = new InitShim();
        this.primaryText = builder.primaryText;
        this.onItemSelected = createUnmodifiableList(true, builder.onItemSelected);
        this.onItemContextSelected = createUnmodifiableList(true, builder.onItemContextSelected);
        this.image = builder.image;
        this.imageDescription = builder.imageDescription;
        this.label = builder.label;
        this.secondaryText = builder.secondaryText;
        this.tertiaryText = builder.tertiaryText;
        this.topBadges = builder.topBadges == null ? null : createUnmodifiableList(true, builder.topBadges);
        this.bottomBadges = builder.bottomBadges == null ? null : createUnmodifiableList(true, builder.bottomBadges);
        this.icon = builder.icon;
        this.iconResources = builder.iconResources;
        this.placeholder = builder.placeholder;
        this.backgroundImage = builder.backgroundImage;
        this.rating = builder.rating;
        this.reviewCount = builder.reviewCount;
        this.buttons = builder.buttons == null ? null : createUnmodifiableList(true, builder.buttons);
        if (builder.shouldAddImagePaddingIsSet()) {
            this.initShim.shouldAddImagePadding(builder.shouldAddImagePadding);
        }
        if (builder.isDisabledIsSet()) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.shouldBlurBackgroundImageIsSet()) {
            this.initShim.shouldBlurBackgroundImage(builder.shouldBlurBackgroundImage);
        }
        if (builder.isTextOverlayedIsSet()) {
            this.initShim.isTextOverlayed(builder.isTextOverlayed);
        }
        this.shouldAddImagePadding = this.initShim.shouldAddImagePadding();
        this.isDisabled = this.initShim.isDisabled();
        this.shouldBlurBackgroundImage = this.initShim.shouldBlurBackgroundImage();
        this.isTextOverlayed = this.initShim.isTextOverlayed();
        this.initShim = null;
    }

    private ImmutableInvertedRectangleVerticalItemElement(String str, List<Method> list, List<Method> list2, String str2, @Nullable String str3, boolean z, boolean z2, boolean z3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable List<BadgeItemElement> list3, @Nullable List<BadgeItemElement> list4, @Nullable String str7, @Nullable ImageResources imageResources, @Nullable ImageResources imageResources2, @Nullable String str8, @Nullable Double d, @Nullable Integer num, @Nullable List<ButtonElement> list5, boolean z4) {
        this.initShim = new InitShim();
        this.primaryText = str;
        this.onItemSelected = list;
        this.onItemContextSelected = list2;
        this.image = str2;
        this.imageDescription = str3;
        this.shouldAddImagePadding = z;
        this.isDisabled = z2;
        this.shouldBlurBackgroundImage = z3;
        this.label = str4;
        this.secondaryText = str5;
        this.tertiaryText = str6;
        this.topBadges = list3;
        this.bottomBadges = list4;
        this.icon = str7;
        this.iconResources = imageResources;
        this.placeholder = imageResources2;
        this.backgroundImage = str8;
        this.rating = d;
        this.reviewCount = num;
        this.buttons = list5;
        this.isTextOverlayed = z4;
        this.initShim = null;
    }

    @Generated(from = "InvertedRectangleVerticalItemElement", generator = "Immutables")
    private final class InitShim {
        private boolean isDisabled;
        private byte isDisabledBuildStage;
        private boolean isTextOverlayed;
        private byte isTextOverlayedBuildStage;
        private boolean shouldAddImagePadding;
        private byte shouldAddImagePaddingBuildStage;
        private boolean shouldBlurBackgroundImage;
        private byte shouldBlurBackgroundImageBuildStage;

        private InitShim() {
            this.shouldAddImagePaddingBuildStage = (byte) 0;
            this.isDisabledBuildStage = (byte) 0;
            this.shouldBlurBackgroundImageBuildStage = (byte) 0;
            this.isTextOverlayedBuildStage = (byte) 0;
        }

        boolean shouldAddImagePadding() {
            byte b = this.shouldAddImagePaddingBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.shouldAddImagePaddingBuildStage = (byte) -1;
                this.shouldAddImagePadding = ImmutableInvertedRectangleVerticalItemElement.super.shouldAddImagePadding();
                this.shouldAddImagePaddingBuildStage = (byte) 1;
            }
            return this.shouldAddImagePadding;
        }

        void shouldAddImagePadding(boolean z) {
            this.shouldAddImagePadding = z;
            this.shouldAddImagePaddingBuildStage = (byte) 1;
        }

        boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = ImmutableInvertedRectangleVerticalItemElement.super.isDisabled();
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(boolean z) {
            this.isDisabled = z;
            this.isDisabledBuildStage = (byte) 1;
        }

        boolean shouldBlurBackgroundImage() {
            byte b = this.shouldBlurBackgroundImageBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.shouldBlurBackgroundImageBuildStage = (byte) -1;
                this.shouldBlurBackgroundImage = ImmutableInvertedRectangleVerticalItemElement.super.shouldBlurBackgroundImage();
                this.shouldBlurBackgroundImageBuildStage = (byte) 1;
            }
            return this.shouldBlurBackgroundImage;
        }

        void shouldBlurBackgroundImage(boolean z) {
            this.shouldBlurBackgroundImage = z;
            this.shouldBlurBackgroundImageBuildStage = (byte) 1;
        }

        boolean isTextOverlayed() {
            byte b = this.isTextOverlayedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isTextOverlayedBuildStage = (byte) -1;
                this.isTextOverlayed = ImmutableInvertedRectangleVerticalItemElement.super.isTextOverlayed();
                this.isTextOverlayedBuildStage = (byte) 1;
            }
            return this.isTextOverlayed;
        }

        void isTextOverlayed(boolean z) {
            this.isTextOverlayed = z;
            this.isTextOverlayedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.shouldAddImagePaddingBuildStage == -1) {
                arrayList.add("shouldAddImagePadding");
            }
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.shouldBlurBackgroundImageBuildStage == -1) {
                arrayList.add("shouldBlurBackgroundImage");
            }
            if (this.isTextOverlayedBuildStage == -1) {
                arrayList.add("isTextOverlayed");
            }
            return "Cannot build InvertedRectangleVerticalItemElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("onItemContextSelected")
    public List<Method> onItemContextSelected() {
        return this.onItemContextSelected;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty(ContextMappingConstants.IMAGE)
    public String image() {
        return this.image;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("imageDescription")
    @Nullable
    public String imageDescription() {
        return this.imageDescription;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("shouldAddImagePadding")
    public boolean shouldAddImagePadding() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shouldAddImagePadding();
        }
        return this.shouldAddImagePadding;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("isDisabled")
    public boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("shouldBlurBackgroundImage")
    public boolean shouldBlurBackgroundImage() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shouldBlurBackgroundImage();
        }
        return this.shouldBlurBackgroundImage;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty(ContextMappingConstants.LABEL)
    @Nullable
    public String label() {
        return this.label;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    @Nullable
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
    @Nullable
    public String tertiaryText() {
        return this.tertiaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("topBadges")
    @Nullable
    public List<BadgeItemElement> topBadges() {
        return this.topBadges;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("bottomBadges")
    @Nullable
    public List<BadgeItemElement> bottomBadges() {
        return this.bottomBadges;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("icon")
    @Nullable
    public String icon() {
        return this.icon;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("iconResources")
    @Nullable
    public ImageResources iconResources() {
        return this.iconResources;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("placeholder")
    @Nullable
    public ImageResources placeholder() {
        return this.placeholder;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("backgroundImage")
    @Nullable
    public String backgroundImage() {
        return this.backgroundImage;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("rating")
    @Nullable
    public Double rating() {
        return this.rating;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("reviewCount")
    @Nullable
    public Integer reviewCount() {
        return this.reviewCount;
    }

    @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
    @JsonProperty("buttons")
    @Nullable
    public List<ButtonElement> buttons() {
        return this.buttons;
    }

    @Override // Touch.WidgetsInterface.v1_0.InvertedRectangleVerticalItemElement
    @JsonProperty("isTextOverlayed")
    public boolean isTextOverlayed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isTextOverlayed();
        }
        return this.isTextOverlayed;
    }

    public final ImmutableInvertedRectangleVerticalItemElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableInvertedRectangleVerticalItemElement(str2, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withOnItemSelected(Method... methodArr) {
        return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withOnItemContextSelected(Method... methodArr) {
        return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withOnItemContextSelected(Iterable<? extends Method> iterable) {
        if (this.onItemContextSelected == iterable) {
            return this;
        }
        return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withImage(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.IMAGE);
        return this.image.equals(str2) ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, str2, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withImageDescription(@Nullable String str) {
        return equals(this.imageDescription, str) ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, str, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withShouldAddImagePadding(boolean z) {
        return this.shouldAddImagePadding == z ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, z, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withIsDisabled(boolean z) {
        return this.isDisabled == z ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, z, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withShouldBlurBackgroundImage(boolean z) {
        return this.shouldBlurBackgroundImage == z ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, z, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withLabel(@Nullable String str) {
        return equals(this.label, str) ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, str, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, str, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withTertiaryText(@Nullable String str) {
        return equals(this.tertiaryText, str) ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, str, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withTopBadges(@Nullable BadgeItemElement... badgeItemElementArr) {
        if (badgeItemElementArr == null) {
            return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, null, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
        }
        return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, Arrays.asList(badgeItemElementArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(badgeItemElementArr), true, false)), this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withTopBadges(@Nullable Iterable<? extends BadgeItemElement> iterable) {
        if (this.topBadges == iterable) {
            return this;
        }
        return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withBottomBadges(@Nullable BadgeItemElement... badgeItemElementArr) {
        if (badgeItemElementArr == null) {
            return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, null, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
        }
        return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, Arrays.asList(badgeItemElementArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(badgeItemElementArr), true, false)), this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withBottomBadges(@Nullable Iterable<? extends BadgeItemElement> iterable) {
        if (this.bottomBadges == iterable) {
            return this;
        }
        return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withIcon(@Nullable String str) {
        return equals(this.icon, str) ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, str, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withIconResources(@Nullable ImageResources imageResources) {
        return this.iconResources == imageResources ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, imageResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withPlaceholder(@Nullable ImageResources imageResources) {
        return this.placeholder == imageResources ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, imageResources, this.backgroundImage, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withBackgroundImage(@Nullable String str) {
        return equals(this.backgroundImage, str) ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, str, this.rating, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withRating(@Nullable Double d) {
        return equals(this.rating, d) ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, d, this.reviewCount, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withReviewCount(@Nullable Integer num) {
        return equals(this.reviewCount, num) ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, num, this.buttons, this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withButtons(@Nullable ButtonElement... buttonElementArr) {
        if (buttonElementArr == null) {
            return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, null, this.isTextOverlayed);
        }
        return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, Arrays.asList(buttonElementArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(buttonElementArr), true, false)), this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withButtons(@Nullable Iterable<? extends ButtonElement> iterable) {
        if (this.buttons == iterable) {
            return this;
        }
        return new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.isTextOverlayed);
    }

    public final ImmutableInvertedRectangleVerticalItemElement withIsTextOverlayed(boolean z) {
        return this.isTextOverlayed == z ? this : new ImmutableInvertedRectangleVerticalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.imageDescription, this.shouldAddImagePadding, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.topBadges, this.bottomBadges, this.icon, this.iconResources, this.placeholder, this.backgroundImage, this.rating, this.reviewCount, this.buttons, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableInvertedRectangleVerticalItemElement) && equalTo(0, (ImmutableInvertedRectangleVerticalItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableInvertedRectangleVerticalItemElement immutableInvertedRectangleVerticalItemElement) {
        return this.primaryText.equals(immutableInvertedRectangleVerticalItemElement.primaryText) && this.onItemSelected.equals(immutableInvertedRectangleVerticalItemElement.onItemSelected) && this.onItemContextSelected.equals(immutableInvertedRectangleVerticalItemElement.onItemContextSelected) && this.image.equals(immutableInvertedRectangleVerticalItemElement.image) && equals(this.imageDescription, immutableInvertedRectangleVerticalItemElement.imageDescription) && this.shouldAddImagePadding == immutableInvertedRectangleVerticalItemElement.shouldAddImagePadding && this.isDisabled == immutableInvertedRectangleVerticalItemElement.isDisabled && this.shouldBlurBackgroundImage == immutableInvertedRectangleVerticalItemElement.shouldBlurBackgroundImage && equals(this.label, immutableInvertedRectangleVerticalItemElement.label) && equals(this.secondaryText, immutableInvertedRectangleVerticalItemElement.secondaryText) && equals(this.tertiaryText, immutableInvertedRectangleVerticalItemElement.tertiaryText) && equals(this.topBadges, immutableInvertedRectangleVerticalItemElement.topBadges) && equals(this.bottomBadges, immutableInvertedRectangleVerticalItemElement.bottomBadges) && equals(this.icon, immutableInvertedRectangleVerticalItemElement.icon) && equals(this.iconResources, immutableInvertedRectangleVerticalItemElement.iconResources) && equals(this.placeholder, immutableInvertedRectangleVerticalItemElement.placeholder) && equals(this.backgroundImage, immutableInvertedRectangleVerticalItemElement.backgroundImage) && equals(this.rating, immutableInvertedRectangleVerticalItemElement.rating) && equals(this.reviewCount, immutableInvertedRectangleVerticalItemElement.reviewCount) && equals(this.buttons, immutableInvertedRectangleVerticalItemElement.buttons) && this.isTextOverlayed == immutableInvertedRectangleVerticalItemElement.isTextOverlayed;
    }

    public int hashCode() {
        int iHashCode = 172192 + this.primaryText.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onItemSelected.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onItemContextSelected.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.image.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.imageDescription);
        int i = iHashCode5 + (iHashCode5 << 5) + (this.shouldAddImagePadding ? 1231 : 1237);
        int i2 = i + (i << 5) + (this.isDisabled ? 1231 : 1237);
        int i3 = i2 + (i2 << 5) + (this.shouldBlurBackgroundImage ? 1231 : 1237);
        int iHashCode6 = i3 + (i3 << 5) + hashCode(this.label);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.secondaryText);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.tertiaryText);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.topBadges);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.bottomBadges);
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + hashCode(this.icon);
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + hashCode(this.iconResources);
        int iHashCode13 = iHashCode12 + (iHashCode12 << 5) + hashCode(this.placeholder);
        int iHashCode14 = iHashCode13 + (iHashCode13 << 5) + hashCode(this.backgroundImage);
        int iHashCode15 = iHashCode14 + (iHashCode14 << 5) + hashCode(this.rating);
        int iHashCode16 = iHashCode15 + (iHashCode15 << 5) + hashCode(this.reviewCount);
        int iHashCode17 = iHashCode16 + (iHashCode16 << 5) + hashCode(this.buttons);
        return iHashCode17 + (iHashCode17 << 5) + (this.isTextOverlayed ? 1231 : 1237);
    }

    public String toString() {
        return "InvertedRectangleVerticalItemElement{primaryText=" + this.primaryText + ", onItemSelected=" + this.onItemSelected + ", onItemContextSelected=" + this.onItemContextSelected + ", image=" + this.image + ", imageDescription=" + this.imageDescription + ", shouldAddImagePadding=" + this.shouldAddImagePadding + ", isDisabled=" + this.isDisabled + ", shouldBlurBackgroundImage=" + this.shouldBlurBackgroundImage + ", label=" + this.label + ", secondaryText=" + this.secondaryText + ", tertiaryText=" + this.tertiaryText + ", topBadges=" + this.topBadges + ", bottomBadges=" + this.bottomBadges + ", icon=" + this.icon + ", iconResources=" + this.iconResources + ", placeholder=" + this.placeholder + ", backgroundImage=" + this.backgroundImage + ", rating=" + this.rating + ", reviewCount=" + this.reviewCount + ", buttons=" + this.buttons + ", isTextOverlayed=" + this.isTextOverlayed + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "InvertedRectangleVerticalItemElement", generator = "Immutables")
    static final class Json extends InvertedRectangleVerticalItemElement {

        @Nullable
        String backgroundImage;

        @Nullable
        String icon;

        @Nullable
        ImageResources iconResources;

        @Nullable
        String image;

        @Nullable
        String imageDescription;
        boolean isDisabled;
        boolean isDisabledIsSet;
        boolean isTextOverlayed;
        boolean isTextOverlayedIsSet;

        @Nullable
        String label;

        @Nullable
        ImageResources placeholder;

        @Nullable
        String primaryText;

        @Nullable
        Double rating;

        @Nullable
        Integer reviewCount;

        @Nullable
        String secondaryText;
        boolean shouldAddImagePadding;
        boolean shouldAddImagePaddingIsSet;
        boolean shouldBlurBackgroundImage;
        boolean shouldBlurBackgroundImageIsSet;

        @Nullable
        String tertiaryText;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        @Nullable
        List<Method> onItemContextSelected = Collections.emptyList();

        @Nullable
        List<BadgeItemElement> topBadges = null;

        @Nullable
        List<BadgeItemElement> bottomBadges = null;

        @Nullable
        List<ButtonElement> buttons = null;

        Json() {
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public void setPrimaryText(String str) {
            this.primaryText = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
        }

        @JsonProperty("onItemContextSelected")
        public void setOnItemContextSelected(List<Method> list) {
            this.onItemContextSelected = list;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(String str) {
            this.image = str;
        }

        @JsonProperty("imageDescription")
        public void setImageDescription(@Nullable String str) {
            this.imageDescription = str;
        }

        @JsonProperty("shouldAddImagePadding")
        public void setShouldAddImagePadding(boolean z) {
            this.shouldAddImagePadding = z;
            this.shouldAddImagePaddingIsSet = true;
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(boolean z) {
            this.isDisabled = z;
            this.isDisabledIsSet = true;
        }

        @JsonProperty("shouldBlurBackgroundImage")
        public void setShouldBlurBackgroundImage(boolean z) {
            this.shouldBlurBackgroundImage = z;
            this.shouldBlurBackgroundImageIsSet = true;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public void setLabel(@Nullable String str) {
            this.label = str;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public void setSecondaryText(@Nullable String str) {
            this.secondaryText = str;
        }

        @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
        public void setTertiaryText(@Nullable String str) {
            this.tertiaryText = str;
        }

        @JsonProperty("topBadges")
        public void setTopBadges(@Nullable List<BadgeItemElement> list) {
            this.topBadges = list;
        }

        @JsonProperty("bottomBadges")
        public void setBottomBadges(@Nullable List<BadgeItemElement> list) {
            this.bottomBadges = list;
        }

        @JsonProperty("icon")
        public void setIcon(@Nullable String str) {
            this.icon = str;
        }

        @JsonProperty("iconResources")
        public void setIconResources(@Nullable ImageResources imageResources) {
            this.iconResources = imageResources;
        }

        @JsonProperty("placeholder")
        public void setPlaceholder(@Nullable ImageResources imageResources) {
            this.placeholder = imageResources;
        }

        @JsonProperty("backgroundImage")
        public void setBackgroundImage(@Nullable String str) {
            this.backgroundImage = str;
        }

        @JsonProperty("rating")
        public void setRating(@Nullable Double d) {
            this.rating = d;
        }

        @JsonProperty("reviewCount")
        public void setReviewCount(@Nullable Integer num) {
            this.reviewCount = num;
        }

        @JsonProperty("buttons")
        public void setButtons(@Nullable List<ButtonElement> list) {
            this.buttons = list;
        }

        @JsonProperty("isTextOverlayed")
        public void setIsTextOverlayed(boolean z) {
            this.isTextOverlayed = z;
            this.isTextOverlayedIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public List<Method> onItemContextSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public String imageDescription() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public boolean shouldAddImagePadding() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public boolean shouldBlurBackgroundImage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public String label() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public String tertiaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public List<BadgeItemElement> topBadges() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public List<BadgeItemElement> bottomBadges() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public String icon() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public ImageResources iconResources() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public ImageResources placeholder() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public String backgroundImage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public Double rating() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public Integer reviewCount() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.VerticalItemElement
        public List<ButtonElement> buttons() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.InvertedRectangleVerticalItemElement
        public boolean isTextOverlayed() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableInvertedRectangleVerticalItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.primaryText;
        if (str != null) {
            builder.primaryText(str);
        }
        List<Method> list = json.onItemSelected;
        if (list != null) {
            builder.addAllOnItemSelected(list);
        }
        List<Method> list2 = json.onItemContextSelected;
        if (list2 != null) {
            builder.addAllOnItemContextSelected(list2);
        }
        String str2 = json.image;
        if (str2 != null) {
            builder.image(str2);
        }
        String str3 = json.imageDescription;
        if (str3 != null) {
            builder.imageDescription(str3);
        }
        if (json.shouldAddImagePaddingIsSet) {
            builder.shouldAddImagePadding(json.shouldAddImagePadding);
        }
        if (json.isDisabledIsSet) {
            builder.isDisabled(json.isDisabled);
        }
        if (json.shouldBlurBackgroundImageIsSet) {
            builder.shouldBlurBackgroundImage(json.shouldBlurBackgroundImage);
        }
        String str4 = json.label;
        if (str4 != null) {
            builder.label(str4);
        }
        String str5 = json.secondaryText;
        if (str5 != null) {
            builder.secondaryText(str5);
        }
        String str6 = json.tertiaryText;
        if (str6 != null) {
            builder.tertiaryText(str6);
        }
        List<BadgeItemElement> list3 = json.topBadges;
        if (list3 != null) {
            builder.addAllTopBadges(list3);
        }
        List<BadgeItemElement> list4 = json.bottomBadges;
        if (list4 != null) {
            builder.addAllBottomBadges(list4);
        }
        String str7 = json.icon;
        if (str7 != null) {
            builder.icon(str7);
        }
        ImageResources imageResources = json.iconResources;
        if (imageResources != null) {
            builder.iconResources(imageResources);
        }
        ImageResources imageResources2 = json.placeholder;
        if (imageResources2 != null) {
            builder.placeholder(imageResources2);
        }
        String str8 = json.backgroundImage;
        if (str8 != null) {
            builder.backgroundImage(str8);
        }
        Double d = json.rating;
        if (d != null) {
            builder.rating(d);
        }
        Integer num = json.reviewCount;
        if (num != null) {
            builder.reviewCount(num);
        }
        List<ButtonElement> list5 = json.buttons;
        if (list5 != null) {
            builder.addAllButtons(list5);
        }
        if (json.isTextOverlayedIsSet) {
            builder.isTextOverlayed(json.isTextOverlayed);
        }
        return builder.build();
    }

    public static ImmutableInvertedRectangleVerticalItemElement copyOf(InvertedRectangleVerticalItemElement invertedRectangleVerticalItemElement) {
        if (invertedRectangleVerticalItemElement instanceof ImmutableInvertedRectangleVerticalItemElement) {
            return (ImmutableInvertedRectangleVerticalItemElement) invertedRectangleVerticalItemElement;
        }
        return builder().from(invertedRectangleVerticalItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "InvertedRectangleVerticalItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_IMAGE = 2;
        private static final long INIT_BIT_PRIMARY_TEXT = 1;
        private static final long OPT_BIT_IS_DISABLED = 2;
        private static final long OPT_BIT_IS_TEXT_OVERLAYED = 8;
        private static final long OPT_BIT_SHOULD_ADD_IMAGE_PADDING = 1;
        private static final long OPT_BIT_SHOULD_BLUR_BACKGROUND_IMAGE = 4;

        @Nullable
        private String backgroundImage;
        private List<BadgeItemElement> bottomBadges;
        private List<ButtonElement> buttons;

        @Nullable
        private String icon;

        @Nullable
        private ImageResources iconResources;

        @Nullable
        private String image;

        @Nullable
        private String imageDescription;
        private long initBits;
        private boolean isDisabled;
        private boolean isTextOverlayed;

        @Nullable
        private String label;
        private List<Method> onItemContextSelected;
        private List<Method> onItemSelected;
        private long optBits;

        @Nullable
        private ImageResources placeholder;

        @Nullable
        private String primaryText;

        @Nullable
        private Double rating;

        @Nullable
        private Integer reviewCount;

        @Nullable
        private String secondaryText;
        private boolean shouldAddImagePadding;
        private boolean shouldBlurBackgroundImage;

        @Nullable
        private String tertiaryText;
        private List<BadgeItemElement> topBadges;

        private Builder() {
            this.initBits = 3L;
            this.onItemSelected = new ArrayList();
            this.onItemContextSelected = new ArrayList();
            this.topBadges = null;
            this.bottomBadges = null;
            this.buttons = null;
        }

        public final Builder from(VerticalItemElement verticalItemElement) {
            ImmutableInvertedRectangleVerticalItemElement.requireNonNull(verticalItemElement, "instance");
            from((Object) verticalItemElement);
            return this;
        }

        public final Builder from(InvertedRectangleVerticalItemElement invertedRectangleVerticalItemElement) {
            ImmutableInvertedRectangleVerticalItemElement.requireNonNull(invertedRectangleVerticalItemElement, "instance");
            from((Object) invertedRectangleVerticalItemElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            String strTertiaryText;
            if (obj instanceof VerticalItemElement) {
                VerticalItemElement verticalItemElement = (VerticalItemElement) obj;
                image(verticalItemElement.image());
                List<BadgeItemElement> list = verticalItemElement.topBadges();
                if (list != null) {
                    addAllTopBadges(list);
                }
                addAllOnItemContextSelected(verticalItemElement.onItemContextSelected());
                List<ButtonElement> listButtons = verticalItemElement.buttons();
                if (listButtons != null) {
                    addAllButtons(listButtons);
                }
                primaryText(verticalItemElement.primaryText());
                String strBackgroundImage = verticalItemElement.backgroundImage();
                if (strBackgroundImage != null) {
                    backgroundImage(strBackgroundImage);
                }
                String strIcon = verticalItemElement.icon();
                if (strIcon != null) {
                    icon(strIcon);
                }
                Double dRating = verticalItemElement.rating();
                if (dRating != null) {
                    rating(dRating);
                }
                String strLabel = verticalItemElement.label();
                if (strLabel != null) {
                    label(strLabel);
                }
                ImageResources imageResourcesIconResources = verticalItemElement.iconResources();
                if (imageResourcesIconResources != null) {
                    iconResources(imageResourcesIconResources);
                }
                String strSecondaryText = verticalItemElement.secondaryText();
                if (strSecondaryText != null) {
                    secondaryText(strSecondaryText);
                }
                addAllOnItemSelected(verticalItemElement.onItemSelected());
                shouldBlurBackgroundImage(verticalItemElement.shouldBlurBackgroundImage());
                Integer numReviewCount = verticalItemElement.reviewCount();
                if (numReviewCount != null) {
                    reviewCount(numReviewCount);
                }
                shouldAddImagePadding(verticalItemElement.shouldAddImagePadding());
                String strImageDescription = verticalItemElement.imageDescription();
                if (strImageDescription != null) {
                    imageDescription(strImageDescription);
                }
                List<BadgeItemElement> listBottomBadges = verticalItemElement.bottomBadges();
                if (listBottomBadges != null) {
                    addAllBottomBadges(listBottomBadges);
                }
                isDisabled(verticalItemElement.isDisabled());
                ImageResources imageResourcesPlaceholder = verticalItemElement.placeholder();
                if (imageResourcesPlaceholder != null) {
                    placeholder(imageResourcesPlaceholder);
                }
                String strTertiaryText2 = verticalItemElement.tertiaryText();
                if (strTertiaryText2 != null) {
                    tertiaryText(strTertiaryText2);
                }
                j = 1048575;
            } else {
                j = 0;
            }
            if (obj instanceof InvertedRectangleVerticalItemElement) {
                InvertedRectangleVerticalItemElement invertedRectangleVerticalItemElement = (InvertedRectangleVerticalItemElement) obj;
                if ((j & 1) == 0) {
                    image(invertedRectangleVerticalItemElement.image());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    List<BadgeItemElement> list2 = invertedRectangleVerticalItemElement.topBadges();
                    if (list2 != null) {
                        addAllTopBadges(list2);
                    }
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    addAllOnItemContextSelected(invertedRectangleVerticalItemElement.onItemContextSelected());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    List<ButtonElement> listButtons2 = invertedRectangleVerticalItemElement.buttons();
                    if (listButtons2 != null) {
                        addAllButtons(listButtons2);
                    }
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    primaryText(invertedRectangleVerticalItemElement.primaryText());
                    j |= 16;
                }
                if ((j & 32) == 0) {
                    String strBackgroundImage2 = invertedRectangleVerticalItemElement.backgroundImage();
                    if (strBackgroundImage2 != null) {
                        backgroundImage(strBackgroundImage2);
                    }
                    j |= 32;
                }
                if ((j & 64) == 0) {
                    String strIcon2 = invertedRectangleVerticalItemElement.icon();
                    if (strIcon2 != null) {
                        icon(strIcon2);
                    }
                    j |= 64;
                }
                if ((j & 128) == 0) {
                    Double dRating2 = invertedRectangleVerticalItemElement.rating();
                    if (dRating2 != null) {
                        rating(dRating2);
                    }
                    j |= 128;
                }
                if ((j & 256) == 0) {
                    String strLabel2 = invertedRectangleVerticalItemElement.label();
                    if (strLabel2 != null) {
                        label(strLabel2);
                    }
                    j |= 256;
                }
                if ((j & 512) == 0) {
                    ImageResources imageResourcesIconResources2 = invertedRectangleVerticalItemElement.iconResources();
                    if (imageResourcesIconResources2 != null) {
                        iconResources(imageResourcesIconResources2);
                    }
                    j |= 512;
                }
                isTextOverlayed(invertedRectangleVerticalItemElement.isTextOverlayed());
                if ((j & 1024) == 0) {
                    String strSecondaryText2 = invertedRectangleVerticalItemElement.secondaryText();
                    if (strSecondaryText2 != null) {
                        secondaryText(strSecondaryText2);
                    }
                    j |= 1024;
                }
                if ((j & 2048) == 0) {
                    addAllOnItemSelected(invertedRectangleVerticalItemElement.onItemSelected());
                    j |= 2048;
                }
                if ((j & 4096) == 0) {
                    shouldBlurBackgroundImage(invertedRectangleVerticalItemElement.shouldBlurBackgroundImage());
                    j |= 4096;
                }
                if ((j & 8192) == 0) {
                    Integer numReviewCount2 = invertedRectangleVerticalItemElement.reviewCount();
                    if (numReviewCount2 != null) {
                        reviewCount(numReviewCount2);
                    }
                    j |= 8192;
                }
                if ((j & 16384) == 0) {
                    shouldAddImagePadding(invertedRectangleVerticalItemElement.shouldAddImagePadding());
                    j |= 16384;
                }
                if ((j & 32768) == 0) {
                    String strImageDescription2 = invertedRectangleVerticalItemElement.imageDescription();
                    if (strImageDescription2 != null) {
                        imageDescription(strImageDescription2);
                    }
                    j |= 32768;
                }
                if ((j & 65536) == 0) {
                    List<BadgeItemElement> listBottomBadges2 = invertedRectangleVerticalItemElement.bottomBadges();
                    if (listBottomBadges2 != null) {
                        addAllBottomBadges(listBottomBadges2);
                    }
                    j |= 65536;
                }
                if ((j & 131072) == 0) {
                    isDisabled(invertedRectangleVerticalItemElement.isDisabled());
                    j |= 131072;
                }
                if ((j & MediaStatus.COMMAND_STREAM_TRANSFER) == 0) {
                    ImageResources imageResourcesPlaceholder2 = invertedRectangleVerticalItemElement.placeholder();
                    if (imageResourcesPlaceholder2 != null) {
                        placeholder(imageResourcesPlaceholder2);
                    }
                    j |= MediaStatus.COMMAND_STREAM_TRANSFER;
                }
                if ((j & 524288) != 0 || (strTertiaryText = invertedRectangleVerticalItemElement.tertiaryText()) == null) {
                    return;
                }
                tertiaryText(strTertiaryText);
            }
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -2;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(method, "onItemSelected element"));
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            return this;
        }

        public final Builder addOnItemContextSelected(Method method) {
            this.onItemContextSelected.add((Method) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(method, "onItemContextSelected element"));
            return this;
        }

        public final Builder addOnItemContextSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemContextSelected.add((Method) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(method, "onItemContextSelected element"));
            }
            return this;
        }

        @JsonProperty("onItemContextSelected")
        public final Builder onItemContextSelected(Iterable<? extends Method> iterable) {
            this.onItemContextSelected.clear();
            return addAllOnItemContextSelected(iterable);
        }

        public final Builder addAllOnItemContextSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onItemContextSelected.add((Method) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(it.next(), "onItemContextSelected element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(String str) {
            this.image = (String) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(str, ContextMappingConstants.IMAGE);
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("imageDescription")
        public final Builder imageDescription(@Nullable String str) {
            this.imageDescription = str;
            return this;
        }

        @JsonProperty("shouldAddImagePadding")
        public final Builder shouldAddImagePadding(boolean z) {
            this.shouldAddImagePadding = z;
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(boolean z) {
            this.isDisabled = z;
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("shouldBlurBackgroundImage")
        public final Builder shouldBlurBackgroundImage(boolean z) {
            this.shouldBlurBackgroundImage = z;
            this.optBits |= 4;
            return this;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public final Builder label(@Nullable String str) {
            this.label = str;
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

        public final Builder addTopBadges(BadgeItemElement badgeItemElement) {
            if (this.topBadges == null) {
                this.topBadges = new ArrayList();
            }
            this.topBadges.add((BadgeItemElement) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(badgeItemElement, "topBadges element"));
            return this;
        }

        public final Builder addTopBadges(BadgeItemElement... badgeItemElementArr) {
            if (this.topBadges == null) {
                this.topBadges = new ArrayList();
            }
            for (BadgeItemElement badgeItemElement : badgeItemElementArr) {
                this.topBadges.add((BadgeItemElement) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(badgeItemElement, "topBadges element"));
            }
            return this;
        }

        @JsonProperty("topBadges")
        public final Builder topBadges(@Nullable Iterable<? extends BadgeItemElement> iterable) {
            if (iterable == null) {
                this.topBadges = null;
                return this;
            }
            this.topBadges = new ArrayList();
            return addAllTopBadges(iterable);
        }

        public final Builder addAllTopBadges(Iterable<? extends BadgeItemElement> iterable) {
            ImmutableInvertedRectangleVerticalItemElement.requireNonNull(iterable, "topBadges element");
            if (this.topBadges == null) {
                this.topBadges = new ArrayList();
            }
            Iterator<? extends BadgeItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.topBadges.add((BadgeItemElement) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(it.next(), "topBadges element"));
            }
            return this;
        }

        public final Builder addBottomBadges(BadgeItemElement badgeItemElement) {
            if (this.bottomBadges == null) {
                this.bottomBadges = new ArrayList();
            }
            this.bottomBadges.add((BadgeItemElement) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(badgeItemElement, "bottomBadges element"));
            return this;
        }

        public final Builder addBottomBadges(BadgeItemElement... badgeItemElementArr) {
            if (this.bottomBadges == null) {
                this.bottomBadges = new ArrayList();
            }
            for (BadgeItemElement badgeItemElement : badgeItemElementArr) {
                this.bottomBadges.add((BadgeItemElement) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(badgeItemElement, "bottomBadges element"));
            }
            return this;
        }

        @JsonProperty("bottomBadges")
        public final Builder bottomBadges(@Nullable Iterable<? extends BadgeItemElement> iterable) {
            if (iterable == null) {
                this.bottomBadges = null;
                return this;
            }
            this.bottomBadges = new ArrayList();
            return addAllBottomBadges(iterable);
        }

        public final Builder addAllBottomBadges(Iterable<? extends BadgeItemElement> iterable) {
            ImmutableInvertedRectangleVerticalItemElement.requireNonNull(iterable, "bottomBadges element");
            if (this.bottomBadges == null) {
                this.bottomBadges = new ArrayList();
            }
            Iterator<? extends BadgeItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.bottomBadges.add((BadgeItemElement) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(it.next(), "bottomBadges element"));
            }
            return this;
        }

        @JsonProperty("icon")
        public final Builder icon(@Nullable String str) {
            this.icon = str;
            return this;
        }

        @JsonProperty("iconResources")
        public final Builder iconResources(@Nullable ImageResources imageResources) {
            this.iconResources = imageResources;
            return this;
        }

        @JsonProperty("placeholder")
        public final Builder placeholder(@Nullable ImageResources imageResources) {
            this.placeholder = imageResources;
            return this;
        }

        @JsonProperty("backgroundImage")
        public final Builder backgroundImage(@Nullable String str) {
            this.backgroundImage = str;
            return this;
        }

        @JsonProperty("rating")
        public final Builder rating(@Nullable Double d) {
            this.rating = d;
            return this;
        }

        @JsonProperty("reviewCount")
        public final Builder reviewCount(@Nullable Integer num) {
            this.reviewCount = num;
            return this;
        }

        public final Builder addButtons(ButtonElement buttonElement) {
            if (this.buttons == null) {
                this.buttons = new ArrayList();
            }
            this.buttons.add((ButtonElement) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(buttonElement, "buttons element"));
            return this;
        }

        public final Builder addButtons(ButtonElement... buttonElementArr) {
            if (this.buttons == null) {
                this.buttons = new ArrayList();
            }
            for (ButtonElement buttonElement : buttonElementArr) {
                this.buttons.add((ButtonElement) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(buttonElement, "buttons element"));
            }
            return this;
        }

        @JsonProperty("buttons")
        public final Builder buttons(@Nullable Iterable<? extends ButtonElement> iterable) {
            if (iterable == null) {
                this.buttons = null;
                return this;
            }
            this.buttons = new ArrayList();
            return addAllButtons(iterable);
        }

        public final Builder addAllButtons(Iterable<? extends ButtonElement> iterable) {
            ImmutableInvertedRectangleVerticalItemElement.requireNonNull(iterable, "buttons element");
            if (this.buttons == null) {
                this.buttons = new ArrayList();
            }
            Iterator<? extends ButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.buttons.add((ButtonElement) ImmutableInvertedRectangleVerticalItemElement.requireNonNull(it.next(), "buttons element"));
            }
            return this;
        }

        @JsonProperty("isTextOverlayed")
        public final Builder isTextOverlayed(boolean z) {
            this.isTextOverlayed = z;
            this.optBits |= 8;
            return this;
        }

        public ImmutableInvertedRectangleVerticalItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableInvertedRectangleVerticalItemElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldAddImagePaddingIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isDisabledIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldBlurBackgroundImageIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isTextOverlayedIsSet() {
            return (this.optBits & 8) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(ContextMappingConstants.IMAGE);
            }
            return "Cannot build InvertedRectangleVerticalItemElement, some of required attributes are not set " + arrayList;
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

    private static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
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

    private static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
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
