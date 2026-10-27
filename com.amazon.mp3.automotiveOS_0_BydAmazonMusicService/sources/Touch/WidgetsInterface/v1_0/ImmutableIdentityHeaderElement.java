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
@Generated(from = "IdentityHeaderElement", generator = "Immutables")
public final class ImmutableIdentityHeaderElement extends IdentityHeaderElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final List<ButtonElement> buttons;

    @Nullable
    private final ButtonElement callToActionButton;

    @Nullable
    private final String identityId;

    @Nullable
    private final String image;
    private volatile transient InitShim initShim;

    @Nullable
    private final String label;
    private final List<Method> onItemSelected;
    private final List<Method> onViewed;
    private final String primaryText;

    @Nullable
    private final String secondaryText;

    @Nullable
    private final String tertiaryText;
    private final String widgetId;

    private ImmutableIdentityHeaderElement(Builder builder) {
        this.initShim = new InitShim();
        this.primaryText = builder.primaryText;
        this.secondaryText = builder.secondaryText;
        this.tertiaryText = builder.tertiaryText;
        this.label = builder.label;
        this.image = builder.image;
        this.callToActionButton = builder.callToActionButton;
        this.identityId = builder.identityId;
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.buttonsIsSet()) {
            this.initShim.buttons(createUnmodifiableList(true, builder.buttons));
        }
        if (builder.onItemSelectedIsSet()) {
            this.initShim.onItemSelected(createUnmodifiableList(true, builder.onItemSelected));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.buttons = this.initShim.buttons();
        this.onItemSelected = this.initShim.onItemSelected();
        this.initShim = null;
    }

    private ImmutableIdentityHeaderElement(String str, List<Method> list, String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable ButtonElement buttonElement, List<ButtonElement> list2, @Nullable String str7, List<Method> list3) {
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
        this.identityId = str7;
        this.onItemSelected = list3;
        this.initShim = null;
    }

    @Generated(from = "IdentityHeaderElement", generator = "Immutables")
    private final class InitShim {
        private List<ButtonElement> buttons;
        private byte buttonsBuildStage;
        private List<Method> onItemSelected;
        private byte onItemSelectedBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.buttonsBuildStage = (byte) 0;
            this.onItemSelectedBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableIdentityHeaderElement.requireNonNull(ImmutableIdentityHeaderElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableIdentityHeaderElement.createUnmodifiableList(false, ImmutableIdentityHeaderElement.createSafeList(ImmutableIdentityHeaderElement.super.onViewed(), true, false));
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
                this.buttons = ImmutableIdentityHeaderElement.createUnmodifiableList(false, ImmutableIdentityHeaderElement.createSafeList(ImmutableIdentityHeaderElement.super.buttons(), true, false));
                this.buttonsBuildStage = (byte) 1;
            }
            return this.buttons;
        }

        void buttons(List<ButtonElement> list) {
            this.buttons = list;
            this.buttonsBuildStage = (byte) 1;
        }

        List<Method> onItemSelected() {
            byte b = this.onItemSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onItemSelectedBuildStage = (byte) -1;
                this.onItemSelected = ImmutableIdentityHeaderElement.createUnmodifiableList(false, ImmutableIdentityHeaderElement.createSafeList(ImmutableIdentityHeaderElement.super.onItemSelected(), true, false));
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
            if (this.buttonsBuildStage == -1) {
                arrayList.add("buttons");
            }
            if (this.onItemSelectedBuildStage == -1) {
                arrayList.add("onItemSelected");
            }
            return "Cannot build IdentityHeaderElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.IdentityHeaderElement
    @JsonProperty("identityId")
    @Nullable
    public String identityId() {
        return this.identityId;
    }

    @Override // Touch.WidgetsInterface.v1_0.IdentityHeaderElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onItemSelected();
        }
        return this.onItemSelected;
    }

    public final ImmutableIdentityHeaderElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableIdentityHeaderElement(str2, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.identityId, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withOnViewed(Method... methodArr) {
        return new ImmutableIdentityHeaderElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.identityId, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableIdentityHeaderElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.identityId, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableIdentityHeaderElement(this.widgetId, this.onViewed, str2, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.identityId, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutableIdentityHeaderElement(this.widgetId, this.onViewed, this.primaryText, str, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.identityId, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withTertiaryText(@Nullable String str) {
        return equals(this.tertiaryText, str) ? this : new ImmutableIdentityHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, str, this.label, this.image, this.callToActionButton, this.buttons, this.identityId, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withLabel(@Nullable String str) {
        return equals(this.label, str) ? this : new ImmutableIdentityHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, str, this.image, this.callToActionButton, this.buttons, this.identityId, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withImage(@Nullable String str) {
        return equals(this.image, str) ? this : new ImmutableIdentityHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, str, this.callToActionButton, this.buttons, this.identityId, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withCallToActionButton(@Nullable ButtonElement buttonElement) {
        return this.callToActionButton == buttonElement ? this : new ImmutableIdentityHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, buttonElement, this.buttons, this.identityId, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withButtons(ButtonElement... buttonElementArr) {
        return new ImmutableIdentityHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, createUnmodifiableList(false, createSafeList(Arrays.asList(buttonElementArr), true, false)), this.identityId, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withButtons(Iterable<? extends ButtonElement> iterable) {
        if (this.buttons == iterable) {
            return this;
        }
        return new ImmutableIdentityHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.identityId, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withIdentityId(@Nullable String str) {
        return equals(this.identityId, str) ? this : new ImmutableIdentityHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, str, this.onItemSelected);
    }

    public final ImmutableIdentityHeaderElement withOnItemSelected(Method... methodArr) {
        return new ImmutableIdentityHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.identityId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableIdentityHeaderElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableIdentityHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.identityId, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableIdentityHeaderElement) && equalTo(0, (ImmutableIdentityHeaderElement) obj);
    }

    private boolean equalTo(int i, ImmutableIdentityHeaderElement immutableIdentityHeaderElement) {
        return this.widgetId.equals(immutableIdentityHeaderElement.widgetId) && this.onViewed.equals(immutableIdentityHeaderElement.onViewed) && this.primaryText.equals(immutableIdentityHeaderElement.primaryText) && equals(this.secondaryText, immutableIdentityHeaderElement.secondaryText) && equals(this.tertiaryText, immutableIdentityHeaderElement.tertiaryText) && equals(this.label, immutableIdentityHeaderElement.label) && equals(this.image, immutableIdentityHeaderElement.image) && equals(this.callToActionButton, immutableIdentityHeaderElement.callToActionButton) && this.buttons.equals(immutableIdentityHeaderElement.buttons) && equals(this.identityId, immutableIdentityHeaderElement.identityId) && this.onItemSelected.equals(immutableIdentityHeaderElement.onItemSelected);
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
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.identityId);
        return iHashCode10 + (iHashCode10 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "IdentityHeaderElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", tertiaryText=" + this.tertiaryText + ", label=" + this.label + ", image=" + this.image + ", callToActionButton=" + this.callToActionButton + ", buttons=" + this.buttons + ", identityId=" + this.identityId + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "IdentityHeaderElement", generator = "Immutables")
    static final class Json extends IdentityHeaderElement {
        boolean buttonsIsSet;

        @Nullable
        ButtonElement callToActionButton;

        @Nullable
        String identityId;

        @Nullable
        String image;

        @Nullable
        String label;
        boolean onItemSelectedIsSet;
        boolean onViewedIsSet;

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

        @JsonProperty("identityId")
        public void setIdentityId(@Nullable String str) {
            this.identityId = str;
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

        @Override // Touch.WidgetsInterface.v1_0.IdentityHeaderElement
        public String identityId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.IdentityHeaderElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableIdentityHeaderElement fromJson(Json json) {
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
        String str7 = json.identityId;
        if (str7 != null) {
            builder.identityId(str7);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        return builder.build();
    }

    public static ImmutableIdentityHeaderElement copyOf(IdentityHeaderElement identityHeaderElement) {
        if (identityHeaderElement instanceof ImmutableIdentityHeaderElement) {
            return (ImmutableIdentityHeaderElement) identityHeaderElement;
        }
        return builder().from(identityHeaderElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "IdentityHeaderElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_TEXT = 1;
        private static final long OPT_BIT_BUTTONS = 2;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 4;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private List<ButtonElement> buttons;

        @Nullable
        private ButtonElement callToActionButton;

        @Nullable
        private String identityId;

        @Nullable
        private String image;
        private long initBits;

        @Nullable
        private String label;
        private List<Method> onItemSelected;
        private List<Method> onViewed;
        private long optBits;

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
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(IdentityHeaderElement identityHeaderElement) {
            ImmutableIdentityHeaderElement.requireNonNull(identityHeaderElement, "instance");
            from((Object) identityHeaderElement);
            return this;
        }

        public final Builder from(HeaderElement headerElement) {
            ImmutableIdentityHeaderElement.requireNonNull(headerElement, "instance");
            from((Object) headerElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableIdentityHeaderElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof IdentityHeaderElement) {
                IdentityHeaderElement identityHeaderElement = (IdentityHeaderElement) obj;
                addAllOnItemSelected(identityHeaderElement.onItemSelected());
                String strImage = identityHeaderElement.image();
                if (strImage != null) {
                    image(strImage);
                }
                ButtonElement buttonElementCallToActionButton = identityHeaderElement.callToActionButton();
                if (buttonElementCallToActionButton != null) {
                    callToActionButton(buttonElementCallToActionButton);
                }
                addAllButtons(identityHeaderElement.buttons());
                widgetId(identityHeaderElement.widgetId());
                primaryText(identityHeaderElement.primaryText());
                String strIdentityId = identityHeaderElement.identityId();
                if (strIdentityId != null) {
                    identityId(strIdentityId);
                }
                addAllOnViewed(identityHeaderElement.onViewed());
                String strLabel = identityHeaderElement.label();
                if (strLabel != null) {
                    label(strLabel);
                }
                String strTertiaryText = identityHeaderElement.tertiaryText();
                if (strTertiaryText != null) {
                    tertiaryText(strTertiaryText);
                }
                String strSecondaryText = identityHeaderElement.secondaryText();
                if (strSecondaryText != null) {
                    secondaryText(strSecondaryText);
                }
                j = 511;
            } else {
                j = 0;
            }
            if (obj instanceof HeaderElement) {
                HeaderElement headerElement = (HeaderElement) obj;
                if ((j & 1) == 0) {
                    String strImage2 = headerElement.image();
                    if (strImage2 != null) {
                        image(strImage2);
                    }
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    ButtonElement buttonElementCallToActionButton2 = headerElement.callToActionButton();
                    if (buttonElementCallToActionButton2 != null) {
                        callToActionButton(buttonElementCallToActionButton2);
                    }
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    addAllButtons(headerElement.buttons());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    widgetId(headerElement.widgetId());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    primaryText(headerElement.primaryText());
                    j |= 16;
                }
                if ((j & 32) == 0) {
                    addAllOnViewed(headerElement.onViewed());
                    j |= 32;
                }
                if ((j & 64) == 0) {
                    String strLabel2 = headerElement.label();
                    if (strLabel2 != null) {
                        label(strLabel2);
                    }
                    j |= 64;
                }
                if ((j & 128) == 0) {
                    String strTertiaryText2 = headerElement.tertiaryText();
                    if (strTertiaryText2 != null) {
                        tertiaryText(strTertiaryText2);
                    }
                    j |= 128;
                }
                if ((j & 256) == 0) {
                    String strSecondaryText2 = headerElement.secondaryText();
                    if (strSecondaryText2 != null) {
                        secondaryText(strSecondaryText2);
                    }
                    j |= 256;
                }
            }
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                if ((j & 8) == 0) {
                    widgetId(widgetElement.widgetId());
                    j |= 8;
                }
                if ((j & 32) == 0) {
                    addAllOnViewed(widgetElement.onViewed());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableIdentityHeaderElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableIdentityHeaderElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableIdentityHeaderElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableIdentityHeaderElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableIdentityHeaderElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
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
            this.buttons.add((ButtonElement) ImmutableIdentityHeaderElement.requireNonNull(buttonElement, "buttons element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addButtons(ButtonElement... buttonElementArr) {
            for (ButtonElement buttonElement : buttonElementArr) {
                this.buttons.add((ButtonElement) ImmutableIdentityHeaderElement.requireNonNull(buttonElement, "buttons element"));
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
                this.buttons.add((ButtonElement) ImmutableIdentityHeaderElement.requireNonNull(it.next(), "buttons element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("identityId")
        public final Builder identityId(@Nullable String str) {
            this.identityId = str;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableIdentityHeaderElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableIdentityHeaderElement.requireNonNull(method, "onItemSelected element"));
            }
            this.optBits |= 4;
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
                this.onItemSelected.add((Method) ImmutableIdentityHeaderElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 4;
            return this;
        }

        public ImmutableIdentityHeaderElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableIdentityHeaderElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean buttonsIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemSelectedIsSet() {
            return (this.optBits & 4) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            return "Cannot build IdentityHeaderElement, some of required attributes are not set " + arrayList;
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
