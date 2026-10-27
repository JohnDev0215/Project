package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.PlayableContent;
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
@Generated(from = "RectangleHorizontalItemElement", generator = "Immutables")
public final class ImmutableRectangleHorizontalItemElement extends RectangleHorizontalItemElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String backgroundImage;

    @Nullable
    private final List<BadgeItemElement> badges;

    @Nullable
    private final String icon;

    @Nullable
    private final ImageResources iconResources;
    private final String image;
    private volatile transient InitShim initShim;
    private final boolean isDisabled;
    private final boolean isFeatured;

    @Nullable
    private final String label;
    private final List<Method> onAddSelected;
    private final List<Method> onItemContextSelected;
    private final List<Method> onItemSelected;

    @Nullable
    private final ImageResources placeholder;

    @Nullable
    private final PlayableContent playableContent;
    private final String primaryText;

    @Nullable
    private final ButtonElement secondaryButton;

    @Nullable
    private final String secondaryText;
    private final boolean shouldBlurBackgroundImage;
    private final boolean showNavigationIcon;

    @Nullable
    private final String tertiaryText;

    private ImmutableRectangleHorizontalItemElement(Builder builder) {
        this.initShim = new InitShim();
        this.primaryText = builder.primaryText;
        this.onItemSelected = createUnmodifiableList(true, builder.onItemSelected);
        this.onItemContextSelected = createUnmodifiableList(true, builder.onItemContextSelected);
        this.image = builder.image;
        this.label = builder.label;
        this.secondaryText = builder.secondaryText;
        this.tertiaryText = builder.tertiaryText;
        this.badges = builder.badges == null ? null : createUnmodifiableList(true, builder.badges);
        this.icon = builder.icon;
        this.secondaryButton = builder.secondaryButton;
        this.playableContent = builder.playableContent;
        this.backgroundImage = builder.backgroundImage;
        this.iconResources = builder.iconResources;
        this.placeholder = builder.placeholder;
        if (builder.onAddSelectedIsSet()) {
            this.initShim.onAddSelected(createUnmodifiableList(true, builder.onAddSelected));
        }
        if (builder.isDisabledIsSet()) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.shouldBlurBackgroundImageIsSet()) {
            this.initShim.shouldBlurBackgroundImage(builder.shouldBlurBackgroundImage);
        }
        if (builder.isFeaturedIsSet()) {
            this.initShim.isFeatured(builder.isFeatured);
        }
        if (builder.showNavigationIconIsSet()) {
            this.initShim.showNavigationIcon(builder.showNavigationIcon);
        }
        this.onAddSelected = this.initShim.onAddSelected();
        this.isDisabled = this.initShim.isDisabled();
        this.shouldBlurBackgroundImage = this.initShim.shouldBlurBackgroundImage();
        this.isFeatured = this.initShim.isFeatured();
        this.showNavigationIcon = this.initShim.showNavigationIcon();
        this.initShim = null;
    }

    private ImmutableRectangleHorizontalItemElement(String str, List<Method> list, List<Method> list2, String str2, List<Method> list3, boolean z, boolean z2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable List<BadgeItemElement> list4, @Nullable String str6, @Nullable ButtonElement buttonElement, @Nullable PlayableContent playableContent, @Nullable String str7, @Nullable ImageResources imageResources, @Nullable ImageResources imageResources2, boolean z3, boolean z4) {
        this.initShim = new InitShim();
        this.primaryText = str;
        this.onItemSelected = list;
        this.onItemContextSelected = list2;
        this.image = str2;
        this.onAddSelected = list3;
        this.isDisabled = z;
        this.shouldBlurBackgroundImage = z2;
        this.label = str3;
        this.secondaryText = str4;
        this.tertiaryText = str5;
        this.badges = list4;
        this.icon = str6;
        this.secondaryButton = buttonElement;
        this.playableContent = playableContent;
        this.backgroundImage = str7;
        this.iconResources = imageResources;
        this.placeholder = imageResources2;
        this.isFeatured = z3;
        this.showNavigationIcon = z4;
        this.initShim = null;
    }

    @Generated(from = "RectangleHorizontalItemElement", generator = "Immutables")
    private final class InitShim {
        private boolean isDisabled;
        private byte isDisabledBuildStage;
        private boolean isFeatured;
        private byte isFeaturedBuildStage;
        private List<Method> onAddSelected;
        private byte onAddSelectedBuildStage;
        private boolean shouldBlurBackgroundImage;
        private byte shouldBlurBackgroundImageBuildStage;
        private boolean showNavigationIcon;
        private byte showNavigationIconBuildStage;

        private InitShim() {
            this.onAddSelectedBuildStage = (byte) 0;
            this.isDisabledBuildStage = (byte) 0;
            this.shouldBlurBackgroundImageBuildStage = (byte) 0;
            this.isFeaturedBuildStage = (byte) 0;
            this.showNavigationIconBuildStage = (byte) 0;
        }

        List<Method> onAddSelected() {
            byte b = this.onAddSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onAddSelectedBuildStage = (byte) -1;
                this.onAddSelected = ImmutableRectangleHorizontalItemElement.createUnmodifiableList(false, ImmutableRectangleHorizontalItemElement.createSafeList(ImmutableRectangleHorizontalItemElement.super.onAddSelected(), true, false));
                this.onAddSelectedBuildStage = (byte) 1;
            }
            return this.onAddSelected;
        }

        void onAddSelected(List<Method> list) {
            this.onAddSelected = list;
            this.onAddSelectedBuildStage = (byte) 1;
        }

        boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = ImmutableRectangleHorizontalItemElement.super.isDisabled();
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
                this.shouldBlurBackgroundImage = ImmutableRectangleHorizontalItemElement.super.shouldBlurBackgroundImage();
                this.shouldBlurBackgroundImageBuildStage = (byte) 1;
            }
            return this.shouldBlurBackgroundImage;
        }

        void shouldBlurBackgroundImage(boolean z) {
            this.shouldBlurBackgroundImage = z;
            this.shouldBlurBackgroundImageBuildStage = (byte) 1;
        }

        boolean isFeatured() {
            byte b = this.isFeaturedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isFeaturedBuildStage = (byte) -1;
                this.isFeatured = ImmutableRectangleHorizontalItemElement.super.isFeatured();
                this.isFeaturedBuildStage = (byte) 1;
            }
            return this.isFeatured;
        }

        void isFeatured(boolean z) {
            this.isFeatured = z;
            this.isFeaturedBuildStage = (byte) 1;
        }

        boolean showNavigationIcon() {
            byte b = this.showNavigationIconBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.showNavigationIconBuildStage = (byte) -1;
                this.showNavigationIcon = ImmutableRectangleHorizontalItemElement.super.showNavigationIcon();
                this.showNavigationIconBuildStage = (byte) 1;
            }
            return this.showNavigationIcon;
        }

        void showNavigationIcon(boolean z) {
            this.showNavigationIcon = z;
            this.showNavigationIconBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.onAddSelectedBuildStage == -1) {
                arrayList.add("onAddSelected");
            }
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.shouldBlurBackgroundImageBuildStage == -1) {
                arrayList.add("shouldBlurBackgroundImage");
            }
            if (this.isFeaturedBuildStage == -1) {
                arrayList.add("isFeatured");
            }
            if (this.showNavigationIconBuildStage == -1) {
                arrayList.add("showNavigationIcon");
            }
            return "Cannot build RectangleHorizontalItemElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("onItemContextSelected")
    public List<Method> onItemContextSelected() {
        return this.onItemContextSelected;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty(ContextMappingConstants.IMAGE)
    public String image() {
        return this.image;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("onAddSelected")
    public List<Method> onAddSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onAddSelected();
        }
        return this.onAddSelected;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("isDisabled")
    public boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("shouldBlurBackgroundImage")
    public boolean shouldBlurBackgroundImage() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.shouldBlurBackgroundImage();
        }
        return this.shouldBlurBackgroundImage;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty(ContextMappingConstants.LABEL)
    @Nullable
    public String label() {
        return this.label;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    @Nullable
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
    @Nullable
    public String tertiaryText() {
        return this.tertiaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("badges")
    @Nullable
    public List<BadgeItemElement> badges() {
        return this.badges;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("icon")
    @Nullable
    public String icon() {
        return this.icon;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("secondaryButton")
    @Nullable
    public ButtonElement secondaryButton() {
        return this.secondaryButton;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("playableContent")
    @Nullable
    public PlayableContent playableContent() {
        return this.playableContent;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("backgroundImage")
    @Nullable
    public String backgroundImage() {
        return this.backgroundImage;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("iconResources")
    @Nullable
    public ImageResources iconResources() {
        return this.iconResources;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("placeholder")
    @Nullable
    public ImageResources placeholder() {
        return this.placeholder;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("isFeatured")
    public boolean isFeatured() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isFeatured();
        }
        return this.isFeatured;
    }

    @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
    @JsonProperty("showNavigationIcon")
    public boolean showNavigationIcon() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.showNavigationIcon();
        }
        return this.showNavigationIcon;
    }

    public final ImmutableRectangleHorizontalItemElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableRectangleHorizontalItemElement(str2, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withOnItemSelected(Method... methodArr) {
        return new ImmutableRectangleHorizontalItemElement(this.primaryText, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableRectangleHorizontalItemElement(this.primaryText, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withOnItemContextSelected(Method... methodArr) {
        return new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withOnItemContextSelected(Iterable<? extends Method> iterable) {
        if (this.onItemContextSelected == iterable) {
            return this;
        }
        return new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withImage(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.IMAGE);
        return this.image.equals(str2) ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, str2, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withOnAddSelected(Method... methodArr) {
        return new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withOnAddSelected(Iterable<? extends Method> iterable) {
        if (this.onAddSelected == iterable) {
            return this;
        }
        return new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withIsDisabled(boolean z) {
        return this.isDisabled == z ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, z, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withShouldBlurBackgroundImage(boolean z) {
        return this.shouldBlurBackgroundImage == z ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, z, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withLabel(@Nullable String str) {
        return equals(this.label, str) ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, str, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, str, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withTertiaryText(@Nullable String str) {
        return equals(this.tertiaryText, str) ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, str, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withBadges(@Nullable BadgeItemElement... badgeItemElementArr) {
        if (badgeItemElementArr == null) {
            return new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, null, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
        }
        return new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, Arrays.asList(badgeItemElementArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(badgeItemElementArr), true, false)), this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withBadges(@Nullable Iterable<? extends BadgeItemElement> iterable) {
        if (this.badges == iterable) {
            return this;
        }
        return new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withIcon(@Nullable String str) {
        return equals(this.icon, str) ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, str, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withSecondaryButton(@Nullable ButtonElement buttonElement) {
        return this.secondaryButton == buttonElement ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, buttonElement, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withPlayableContent(@Nullable PlayableContent playableContent) {
        return this.playableContent == playableContent ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withBackgroundImage(@Nullable String str) {
        return equals(this.backgroundImage, str) ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, str, this.iconResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withIconResources(@Nullable ImageResources imageResources) {
        return this.iconResources == imageResources ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, imageResources, this.placeholder, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withPlaceholder(@Nullable ImageResources imageResources) {
        return this.placeholder == imageResources ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, imageResources, this.isFeatured, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withIsFeatured(boolean z) {
        return this.isFeatured == z ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, z, this.showNavigationIcon);
    }

    public final ImmutableRectangleHorizontalItemElement withShowNavigationIcon(boolean z) {
        return this.showNavigationIcon == z ? this : new ImmutableRectangleHorizontalItemElement(this.primaryText, this.onItemSelected, this.onItemContextSelected, this.image, this.onAddSelected, this.isDisabled, this.shouldBlurBackgroundImage, this.label, this.secondaryText, this.tertiaryText, this.badges, this.icon, this.secondaryButton, this.playableContent, this.backgroundImage, this.iconResources, this.placeholder, this.isFeatured, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableRectangleHorizontalItemElement) && equalTo(0, (ImmutableRectangleHorizontalItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableRectangleHorizontalItemElement immutableRectangleHorizontalItemElement) {
        return this.primaryText.equals(immutableRectangleHorizontalItemElement.primaryText) && this.onItemSelected.equals(immutableRectangleHorizontalItemElement.onItemSelected) && this.onItemContextSelected.equals(immutableRectangleHorizontalItemElement.onItemContextSelected) && this.image.equals(immutableRectangleHorizontalItemElement.image) && this.onAddSelected.equals(immutableRectangleHorizontalItemElement.onAddSelected) && this.isDisabled == immutableRectangleHorizontalItemElement.isDisabled && this.shouldBlurBackgroundImage == immutableRectangleHorizontalItemElement.shouldBlurBackgroundImage && equals(this.label, immutableRectangleHorizontalItemElement.label) && equals(this.secondaryText, immutableRectangleHorizontalItemElement.secondaryText) && equals(this.tertiaryText, immutableRectangleHorizontalItemElement.tertiaryText) && equals(this.badges, immutableRectangleHorizontalItemElement.badges) && equals(this.icon, immutableRectangleHorizontalItemElement.icon) && equals(this.secondaryButton, immutableRectangleHorizontalItemElement.secondaryButton) && equals(this.playableContent, immutableRectangleHorizontalItemElement.playableContent) && equals(this.backgroundImage, immutableRectangleHorizontalItemElement.backgroundImage) && equals(this.iconResources, immutableRectangleHorizontalItemElement.iconResources) && equals(this.placeholder, immutableRectangleHorizontalItemElement.placeholder) && this.isFeatured == immutableRectangleHorizontalItemElement.isFeatured && this.showNavigationIcon == immutableRectangleHorizontalItemElement.showNavigationIcon;
    }

    public int hashCode() {
        int iHashCode = 172192 + this.primaryText.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onItemSelected.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onItemContextSelected.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.image.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.onAddSelected.hashCode();
        int i = iHashCode5 + (iHashCode5 << 5) + (this.isDisabled ? 1231 : 1237);
        int i2 = i + (i << 5) + (this.shouldBlurBackgroundImage ? 1231 : 1237);
        int iHashCode6 = i2 + (i2 << 5) + hashCode(this.label);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.secondaryText);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.tertiaryText);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.badges);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.icon);
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + hashCode(this.secondaryButton);
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + hashCode(this.playableContent);
        int iHashCode13 = iHashCode12 + (iHashCode12 << 5) + hashCode(this.backgroundImage);
        int iHashCode14 = iHashCode13 + (iHashCode13 << 5) + hashCode(this.iconResources);
        int iHashCode15 = iHashCode14 + (iHashCode14 << 5) + hashCode(this.placeholder);
        int i3 = iHashCode15 + (iHashCode15 << 5) + (this.isFeatured ? 1231 : 1237);
        return i3 + (i3 << 5) + (this.showNavigationIcon ? 1231 : 1237);
    }

    public String toString() {
        return "RectangleHorizontalItemElement{primaryText=" + this.primaryText + ", onItemSelected=" + this.onItemSelected + ", onItemContextSelected=" + this.onItemContextSelected + ", image=" + this.image + ", onAddSelected=" + this.onAddSelected + ", isDisabled=" + this.isDisabled + ", shouldBlurBackgroundImage=" + this.shouldBlurBackgroundImage + ", label=" + this.label + ", secondaryText=" + this.secondaryText + ", tertiaryText=" + this.tertiaryText + ", badges=" + this.badges + ", icon=" + this.icon + ", secondaryButton=" + this.secondaryButton + ", playableContent=" + this.playableContent + ", backgroundImage=" + this.backgroundImage + ", iconResources=" + this.iconResources + ", placeholder=" + this.placeholder + ", isFeatured=" + this.isFeatured + ", showNavigationIcon=" + this.showNavigationIcon + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "RectangleHorizontalItemElement", generator = "Immutables")
    static final class Json extends RectangleHorizontalItemElement {

        @Nullable
        String backgroundImage;

        @Nullable
        String icon;

        @Nullable
        ImageResources iconResources;

        @Nullable
        String image;
        boolean isDisabled;
        boolean isDisabledIsSet;
        boolean isFeatured;
        boolean isFeaturedIsSet;

        @Nullable
        String label;
        boolean onAddSelectedIsSet;

        @Nullable
        ImageResources placeholder;

        @Nullable
        PlayableContent playableContent;

        @Nullable
        String primaryText;

        @Nullable
        ButtonElement secondaryButton;

        @Nullable
        String secondaryText;
        boolean shouldBlurBackgroundImage;
        boolean shouldBlurBackgroundImageIsSet;
        boolean showNavigationIcon;
        boolean showNavigationIconIsSet;

        @Nullable
        String tertiaryText;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        @Nullable
        List<Method> onItemContextSelected = Collections.emptyList();

        @Nullable
        List<Method> onAddSelected = Collections.emptyList();

        @Nullable
        List<BadgeItemElement> badges = null;

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

        @JsonProperty("onAddSelected")
        public void setOnAddSelected(List<Method> list) {
            this.onAddSelected = list;
            this.onAddSelectedIsSet = true;
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

        @JsonProperty("badges")
        public void setBadges(@Nullable List<BadgeItemElement> list) {
            this.badges = list;
        }

        @JsonProperty("icon")
        public void setIcon(@Nullable String str) {
            this.icon = str;
        }

        @JsonProperty("secondaryButton")
        public void setSecondaryButton(@Nullable ButtonElement buttonElement) {
            this.secondaryButton = buttonElement;
        }

        @JsonProperty("playableContent")
        public void setPlayableContent(@Nullable PlayableContent playableContent) {
            this.playableContent = playableContent;
        }

        @JsonProperty("backgroundImage")
        public void setBackgroundImage(@Nullable String str) {
            this.backgroundImage = str;
        }

        @JsonProperty("iconResources")
        public void setIconResources(@Nullable ImageResources imageResources) {
            this.iconResources = imageResources;
        }

        @JsonProperty("placeholder")
        public void setPlaceholder(@Nullable ImageResources imageResources) {
            this.placeholder = imageResources;
        }

        @JsonProperty("isFeatured")
        public void setIsFeatured(boolean z) {
            this.isFeatured = z;
            this.isFeaturedIsSet = true;
        }

        @JsonProperty("showNavigationIcon")
        public void setShowNavigationIcon(boolean z) {
            this.showNavigationIcon = z;
            this.showNavigationIconIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public List<Method> onItemContextSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public List<Method> onAddSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public boolean shouldBlurBackgroundImage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public String label() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public String tertiaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public List<BadgeItemElement> badges() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public String icon() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public ButtonElement secondaryButton() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public PlayableContent playableContent() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public String backgroundImage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public ImageResources iconResources() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public ImageResources placeholder() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public boolean isFeatured() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HorizontalItemElement
        public boolean showNavigationIcon() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableRectangleHorizontalItemElement fromJson(Json json) {
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
        if (json.onAddSelectedIsSet) {
            builder.addAllOnAddSelected(json.onAddSelected);
        }
        if (json.isDisabledIsSet) {
            builder.isDisabled(json.isDisabled);
        }
        if (json.shouldBlurBackgroundImageIsSet) {
            builder.shouldBlurBackgroundImage(json.shouldBlurBackgroundImage);
        }
        String str3 = json.label;
        if (str3 != null) {
            builder.label(str3);
        }
        String str4 = json.secondaryText;
        if (str4 != null) {
            builder.secondaryText(str4);
        }
        String str5 = json.tertiaryText;
        if (str5 != null) {
            builder.tertiaryText(str5);
        }
        List<BadgeItemElement> list3 = json.badges;
        if (list3 != null) {
            builder.addAllBadges(list3);
        }
        String str6 = json.icon;
        if (str6 != null) {
            builder.icon(str6);
        }
        ButtonElement buttonElement = json.secondaryButton;
        if (buttonElement != null) {
            builder.secondaryButton(buttonElement);
        }
        PlayableContent playableContent = json.playableContent;
        if (playableContent != null) {
            builder.playableContent(playableContent);
        }
        String str7 = json.backgroundImage;
        if (str7 != null) {
            builder.backgroundImage(str7);
        }
        ImageResources imageResources = json.iconResources;
        if (imageResources != null) {
            builder.iconResources(imageResources);
        }
        ImageResources imageResources2 = json.placeholder;
        if (imageResources2 != null) {
            builder.placeholder(imageResources2);
        }
        if (json.isFeaturedIsSet) {
            builder.isFeatured(json.isFeatured);
        }
        if (json.showNavigationIconIsSet) {
            builder.showNavigationIcon(json.showNavigationIcon);
        }
        return builder.build();
    }

    public static ImmutableRectangleHorizontalItemElement copyOf(RectangleHorizontalItemElement rectangleHorizontalItemElement) {
        if (rectangleHorizontalItemElement instanceof ImmutableRectangleHorizontalItemElement) {
            return (ImmutableRectangleHorizontalItemElement) rectangleHorizontalItemElement;
        }
        return builder().from(rectangleHorizontalItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "RectangleHorizontalItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_IMAGE = 2;
        private static final long INIT_BIT_PRIMARY_TEXT = 1;
        private static final long OPT_BIT_IS_DISABLED = 2;
        private static final long OPT_BIT_IS_FEATURED = 8;
        private static final long OPT_BIT_ON_ADD_SELECTED = 1;
        private static final long OPT_BIT_SHOULD_BLUR_BACKGROUND_IMAGE = 4;
        private static final long OPT_BIT_SHOW_NAVIGATION_ICON = 16;

        @Nullable
        private String backgroundImage;
        private List<BadgeItemElement> badges;

        @Nullable
        private String icon;

        @Nullable
        private ImageResources iconResources;

        @Nullable
        private String image;
        private long initBits;
        private boolean isDisabled;
        private boolean isFeatured;

        @Nullable
        private String label;
        private List<Method> onAddSelected;
        private List<Method> onItemContextSelected;
        private List<Method> onItemSelected;
        private long optBits;

        @Nullable
        private ImageResources placeholder;

        @Nullable
        private PlayableContent playableContent;

        @Nullable
        private String primaryText;

        @Nullable
        private ButtonElement secondaryButton;

        @Nullable
        private String secondaryText;
        private boolean shouldBlurBackgroundImage;
        private boolean showNavigationIcon;

        @Nullable
        private String tertiaryText;

        private Builder() {
            this.initBits = 3L;
            this.onItemSelected = new ArrayList();
            this.onItemContextSelected = new ArrayList();
            this.onAddSelected = new ArrayList();
            this.badges = null;
        }

        public final Builder from(RectangleHorizontalItemElement rectangleHorizontalItemElement) {
            ImmutableRectangleHorizontalItemElement.requireNonNull(rectangleHorizontalItemElement, "instance");
            from((Object) rectangleHorizontalItemElement);
            return this;
        }

        public final Builder from(HorizontalItemElement horizontalItemElement) {
            ImmutableRectangleHorizontalItemElement.requireNonNull(horizontalItemElement, "instance");
            from((Object) horizontalItemElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof RectangleHorizontalItemElement) {
                RectangleHorizontalItemElement rectangleHorizontalItemElement = (RectangleHorizontalItemElement) obj;
                image(rectangleHorizontalItemElement.image());
                addAllOnItemContextSelected(rectangleHorizontalItemElement.onItemContextSelected());
                ButtonElement buttonElementSecondaryButton = rectangleHorizontalItemElement.secondaryButton();
                if (buttonElementSecondaryButton != null) {
                    secondaryButton(buttonElementSecondaryButton);
                }
                primaryText(rectangleHorizontalItemElement.primaryText());
                String strBackgroundImage = rectangleHorizontalItemElement.backgroundImage();
                if (strBackgroundImage != null) {
                    backgroundImage(strBackgroundImage);
                }
                String strIcon = rectangleHorizontalItemElement.icon();
                if (strIcon != null) {
                    icon(strIcon);
                }
                String strLabel = rectangleHorizontalItemElement.label();
                if (strLabel != null) {
                    label(strLabel);
                }
                ImageResources imageResourcesIconResources = rectangleHorizontalItemElement.iconResources();
                if (imageResourcesIconResources != null) {
                    iconResources(imageResourcesIconResources);
                }
                String strSecondaryText = rectangleHorizontalItemElement.secondaryText();
                if (strSecondaryText != null) {
                    secondaryText(strSecondaryText);
                }
                addAllOnItemSelected(rectangleHorizontalItemElement.onItemSelected());
                List<BadgeItemElement> listBadges = rectangleHorizontalItemElement.badges();
                if (listBadges != null) {
                    addAllBadges(listBadges);
                }
                shouldBlurBackgroundImage(rectangleHorizontalItemElement.shouldBlurBackgroundImage());
                showNavigationIcon(rectangleHorizontalItemElement.showNavigationIcon());
                isDisabled(rectangleHorizontalItemElement.isDisabled());
                ImageResources imageResourcesPlaceholder = rectangleHorizontalItemElement.placeholder();
                if (imageResourcesPlaceholder != null) {
                    placeholder(imageResourcesPlaceholder);
                }
                isFeatured(rectangleHorizontalItemElement.isFeatured());
                PlayableContent playableContent = rectangleHorizontalItemElement.playableContent();
                if (playableContent != null) {
                    playableContent(playableContent);
                }
                String strTertiaryText = rectangleHorizontalItemElement.tertiaryText();
                if (strTertiaryText != null) {
                    tertiaryText(strTertiaryText);
                }
                addAllOnAddSelected(rectangleHorizontalItemElement.onAddSelected());
                j = 524287;
            } else {
                j = 0;
            }
            if (obj instanceof HorizontalItemElement) {
                HorizontalItemElement horizontalItemElement = (HorizontalItemElement) obj;
                if ((j & 1) == 0) {
                    image(horizontalItemElement.image());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    addAllOnItemContextSelected(horizontalItemElement.onItemContextSelected());
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    ButtonElement buttonElementSecondaryButton2 = horizontalItemElement.secondaryButton();
                    if (buttonElementSecondaryButton2 != null) {
                        secondaryButton(buttonElementSecondaryButton2);
                    }
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    primaryText(horizontalItemElement.primaryText());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    String strBackgroundImage2 = horizontalItemElement.backgroundImage();
                    if (strBackgroundImage2 != null) {
                        backgroundImage(strBackgroundImage2);
                    }
                    j |= 16;
                }
                if ((j & 32) == 0) {
                    String strIcon2 = horizontalItemElement.icon();
                    if (strIcon2 != null) {
                        icon(strIcon2);
                    }
                    j |= 32;
                }
                if ((j & 64) == 0) {
                    String strLabel2 = horizontalItemElement.label();
                    if (strLabel2 != null) {
                        label(strLabel2);
                    }
                    j |= 64;
                }
                if ((j & 128) == 0) {
                    ImageResources imageResourcesIconResources2 = horizontalItemElement.iconResources();
                    if (imageResourcesIconResources2 != null) {
                        iconResources(imageResourcesIconResources2);
                    }
                    j |= 128;
                }
                if ((j & 256) == 0) {
                    String strSecondaryText2 = horizontalItemElement.secondaryText();
                    if (strSecondaryText2 != null) {
                        secondaryText(strSecondaryText2);
                    }
                    j |= 256;
                }
                if ((j & 512) == 0) {
                    addAllOnItemSelected(horizontalItemElement.onItemSelected());
                    j |= 512;
                }
                if ((j & 1024) == 0) {
                    List<BadgeItemElement> listBadges2 = horizontalItemElement.badges();
                    if (listBadges2 != null) {
                        addAllBadges(listBadges2);
                    }
                    j |= 1024;
                }
                if ((j & 2048) == 0) {
                    shouldBlurBackgroundImage(horizontalItemElement.shouldBlurBackgroundImage());
                    j |= 2048;
                }
                if ((j & 4096) == 0) {
                    showNavigationIcon(horizontalItemElement.showNavigationIcon());
                    j |= 4096;
                }
                if ((j & 8192) == 0) {
                    isDisabled(horizontalItemElement.isDisabled());
                    j |= 8192;
                }
                if ((j & 16384) == 0) {
                    ImageResources imageResourcesPlaceholder2 = horizontalItemElement.placeholder();
                    if (imageResourcesPlaceholder2 != null) {
                        placeholder(imageResourcesPlaceholder2);
                    }
                    j |= 16384;
                }
                if ((j & 32768) == 0) {
                    isFeatured(horizontalItemElement.isFeatured());
                    j |= 32768;
                }
                if ((j & 65536) == 0) {
                    PlayableContent playableContent2 = horizontalItemElement.playableContent();
                    if (playableContent2 != null) {
                        playableContent(playableContent2);
                    }
                    j |= 65536;
                }
                if ((j & 131072) == 0) {
                    String strTertiaryText2 = horizontalItemElement.tertiaryText();
                    if (strTertiaryText2 != null) {
                        tertiaryText(strTertiaryText2);
                    }
                    j |= 131072;
                }
                if ((j & MediaStatus.COMMAND_STREAM_TRANSFER) == 0) {
                    addAllOnAddSelected(horizontalItemElement.onAddSelected());
                }
            }
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableRectangleHorizontalItemElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -2;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableRectangleHorizontalItemElement.requireNonNull(method, "onItemSelected element"));
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableRectangleHorizontalItemElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableRectangleHorizontalItemElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            return this;
        }

        public final Builder addOnItemContextSelected(Method method) {
            this.onItemContextSelected.add((Method) ImmutableRectangleHorizontalItemElement.requireNonNull(method, "onItemContextSelected element"));
            return this;
        }

        public final Builder addOnItemContextSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemContextSelected.add((Method) ImmutableRectangleHorizontalItemElement.requireNonNull(method, "onItemContextSelected element"));
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
                this.onItemContextSelected.add((Method) ImmutableRectangleHorizontalItemElement.requireNonNull(it.next(), "onItemContextSelected element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(String str) {
            this.image = (String) ImmutableRectangleHorizontalItemElement.requireNonNull(str, ContextMappingConstants.IMAGE);
            this.initBits &= -3;
            return this;
        }

        public final Builder addOnAddSelected(Method method) {
            this.onAddSelected.add((Method) ImmutableRectangleHorizontalItemElement.requireNonNull(method, "onAddSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnAddSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onAddSelected.add((Method) ImmutableRectangleHorizontalItemElement.requireNonNull(method, "onAddSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onAddSelected")
        public final Builder onAddSelected(Iterable<? extends Method> iterable) {
            this.onAddSelected.clear();
            return addAllOnAddSelected(iterable);
        }

        public final Builder addAllOnAddSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onAddSelected.add((Method) ImmutableRectangleHorizontalItemElement.requireNonNull(it.next(), "onAddSelected element"));
            }
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

        public final Builder addBadges(BadgeItemElement badgeItemElement) {
            if (this.badges == null) {
                this.badges = new ArrayList();
            }
            this.badges.add((BadgeItemElement) ImmutableRectangleHorizontalItemElement.requireNonNull(badgeItemElement, "badges element"));
            return this;
        }

        public final Builder addBadges(BadgeItemElement... badgeItemElementArr) {
            if (this.badges == null) {
                this.badges = new ArrayList();
            }
            for (BadgeItemElement badgeItemElement : badgeItemElementArr) {
                this.badges.add((BadgeItemElement) ImmutableRectangleHorizontalItemElement.requireNonNull(badgeItemElement, "badges element"));
            }
            return this;
        }

        @JsonProperty("badges")
        public final Builder badges(@Nullable Iterable<? extends BadgeItemElement> iterable) {
            if (iterable == null) {
                this.badges = null;
                return this;
            }
            this.badges = new ArrayList();
            return addAllBadges(iterable);
        }

        public final Builder addAllBadges(Iterable<? extends BadgeItemElement> iterable) {
            ImmutableRectangleHorizontalItemElement.requireNonNull(iterable, "badges element");
            if (this.badges == null) {
                this.badges = new ArrayList();
            }
            Iterator<? extends BadgeItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.badges.add((BadgeItemElement) ImmutableRectangleHorizontalItemElement.requireNonNull(it.next(), "badges element"));
            }
            return this;
        }

        @JsonProperty("icon")
        public final Builder icon(@Nullable String str) {
            this.icon = str;
            return this;
        }

        @JsonProperty("secondaryButton")
        public final Builder secondaryButton(@Nullable ButtonElement buttonElement) {
            this.secondaryButton = buttonElement;
            return this;
        }

        @JsonProperty("playableContent")
        public final Builder playableContent(@Nullable PlayableContent playableContent) {
            this.playableContent = playableContent;
            return this;
        }

        @JsonProperty("backgroundImage")
        public final Builder backgroundImage(@Nullable String str) {
            this.backgroundImage = str;
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

        @JsonProperty("isFeatured")
        public final Builder isFeatured(boolean z) {
            this.isFeatured = z;
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("showNavigationIcon")
        public final Builder showNavigationIcon(boolean z) {
            this.showNavigationIcon = z;
            this.optBits |= 16;
            return this;
        }

        public ImmutableRectangleHorizontalItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableRectangleHorizontalItemElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onAddSelectedIsSet() {
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
        public boolean isFeaturedIsSet() {
            return (this.optBits & 8) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean showNavigationIconIsSet() {
            return (this.optBits & 16) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(ContextMappingConstants.IMAGE);
            }
            return "Cannot build RectangleHorizontalItemElement, some of required attributes are not set " + arrayList;
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
