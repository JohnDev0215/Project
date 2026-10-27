package Touch.WidgetsInterface.v1_0;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "ExclusiveCarouselSlideElement", generator = "Immutables")
public final class ImmutableExclusiveCarouselSlideElement extends ExclusiveCarouselSlideElement {

    @Nullable
    private final ButtonElement callToActionButton;

    @Nullable
    private final String image;

    @Nullable
    private final String imageDescription;

    @Nullable
    private final String label;
    private final String primaryText;

    @Nullable
    private final String secondaryText;

    private ImmutableExclusiveCarouselSlideElement(String str, @Nullable String str2, @Nullable String str3, @Nullable ButtonElement buttonElement, @Nullable String str4, @Nullable String str5) {
        this.primaryText = str;
        this.secondaryText = str2;
        this.label = str3;
        this.callToActionButton = buttonElement;
        this.image = str4;
        this.imageDescription = str5;
    }

    @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    @Nullable
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
    @JsonProperty(ContextMappingConstants.LABEL)
    @Nullable
    public String label() {
        return this.label;
    }

    @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
    @JsonProperty("callToActionButton")
    @Nullable
    public ButtonElement callToActionButton() {
        return this.callToActionButton;
    }

    @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
    @JsonProperty(ContextMappingConstants.IMAGE)
    @Nullable
    public String image() {
        return this.image;
    }

    @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
    @JsonProperty("imageDescription")
    @Nullable
    public String imageDescription() {
        return this.imageDescription;
    }

    public final ImmutableExclusiveCarouselSlideElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableExclusiveCarouselSlideElement(str2, this.secondaryText, this.label, this.callToActionButton, this.image, this.imageDescription);
    }

    public final ImmutableExclusiveCarouselSlideElement withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutableExclusiveCarouselSlideElement(this.primaryText, str, this.label, this.callToActionButton, this.image, this.imageDescription);
    }

    public final ImmutableExclusiveCarouselSlideElement withLabel(@Nullable String str) {
        return equals(this.label, str) ? this : new ImmutableExclusiveCarouselSlideElement(this.primaryText, this.secondaryText, str, this.callToActionButton, this.image, this.imageDescription);
    }

    public final ImmutableExclusiveCarouselSlideElement withCallToActionButton(@Nullable ButtonElement buttonElement) {
        return this.callToActionButton == buttonElement ? this : new ImmutableExclusiveCarouselSlideElement(this.primaryText, this.secondaryText, this.label, buttonElement, this.image, this.imageDescription);
    }

    public final ImmutableExclusiveCarouselSlideElement withImage(@Nullable String str) {
        return equals(this.image, str) ? this : new ImmutableExclusiveCarouselSlideElement(this.primaryText, this.secondaryText, this.label, this.callToActionButton, str, this.imageDescription);
    }

    public final ImmutableExclusiveCarouselSlideElement withImageDescription(@Nullable String str) {
        return equals(this.imageDescription, str) ? this : new ImmutableExclusiveCarouselSlideElement(this.primaryText, this.secondaryText, this.label, this.callToActionButton, this.image, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableExclusiveCarouselSlideElement) && equalTo(0, (ImmutableExclusiveCarouselSlideElement) obj);
    }

    private boolean equalTo(int i, ImmutableExclusiveCarouselSlideElement immutableExclusiveCarouselSlideElement) {
        return this.primaryText.equals(immutableExclusiveCarouselSlideElement.primaryText) && equals(this.secondaryText, immutableExclusiveCarouselSlideElement.secondaryText) && equals(this.label, immutableExclusiveCarouselSlideElement.label) && equals(this.callToActionButton, immutableExclusiveCarouselSlideElement.callToActionButton) && equals(this.image, immutableExclusiveCarouselSlideElement.image) && equals(this.imageDescription, immutableExclusiveCarouselSlideElement.imageDescription);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.primaryText.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.secondaryText);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.label);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.callToActionButton);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.image);
        return iHashCode5 + (iHashCode5 << 5) + hashCode(this.imageDescription);
    }

    public String toString() {
        return "ExclusiveCarouselSlideElement{primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", label=" + this.label + ", callToActionButton=" + this.callToActionButton + ", image=" + this.image + ", imageDescription=" + this.imageDescription + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ExclusiveCarouselSlideElement", generator = "Immutables")
    static final class Json extends ExclusiveCarouselSlideElement {

        @Nullable
        ButtonElement callToActionButton;

        @Nullable
        String image;

        @Nullable
        String imageDescription;

        @Nullable
        String label;

        @Nullable
        String primaryText;

        @Nullable
        String secondaryText;

        Json() {
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public void setPrimaryText(String str) {
            this.primaryText = str;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public void setSecondaryText(@Nullable String str) {
            this.secondaryText = str;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public void setLabel(@Nullable String str) {
            this.label = str;
        }

        @JsonProperty("callToActionButton")
        public void setCallToActionButton(@Nullable ButtonElement buttonElement) {
            this.callToActionButton = buttonElement;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(@Nullable String str) {
            this.image = str;
        }

        @JsonProperty("imageDescription")
        public void setImageDescription(@Nullable String str) {
            this.imageDescription = str;
        }

        @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
        public String label() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
        public ButtonElement callToActionButton() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement
        public String imageDescription() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableExclusiveCarouselSlideElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.primaryText;
        if (str != null) {
            builder.primaryText(str);
        }
        String str2 = json.secondaryText;
        if (str2 != null) {
            builder.secondaryText(str2);
        }
        String str3 = json.label;
        if (str3 != null) {
            builder.label(str3);
        }
        ButtonElement buttonElement = json.callToActionButton;
        if (buttonElement != null) {
            builder.callToActionButton(buttonElement);
        }
        String str4 = json.image;
        if (str4 != null) {
            builder.image(str4);
        }
        String str5 = json.imageDescription;
        if (str5 != null) {
            builder.imageDescription(str5);
        }
        return builder.build();
    }

    public static ImmutableExclusiveCarouselSlideElement copyOf(ExclusiveCarouselSlideElement exclusiveCarouselSlideElement) {
        if (exclusiveCarouselSlideElement instanceof ImmutableExclusiveCarouselSlideElement) {
            return (ImmutableExclusiveCarouselSlideElement) exclusiveCarouselSlideElement;
        }
        return builder().from(exclusiveCarouselSlideElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ExclusiveCarouselSlideElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_TEXT = 1;

        @Nullable
        private ButtonElement callToActionButton;

        @Nullable
        private String image;

        @Nullable
        private String imageDescription;
        private long initBits;

        @Nullable
        private String label;

        @Nullable
        private String primaryText;

        @Nullable
        private String secondaryText;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(ExclusiveCarouselSlideElement exclusiveCarouselSlideElement) {
            ImmutableExclusiveCarouselSlideElement.requireNonNull(exclusiveCarouselSlideElement, "instance");
            primaryText(exclusiveCarouselSlideElement.primaryText());
            String strSecondaryText = exclusiveCarouselSlideElement.secondaryText();
            if (strSecondaryText != null) {
                secondaryText(strSecondaryText);
            }
            String strLabel = exclusiveCarouselSlideElement.label();
            if (strLabel != null) {
                label(strLabel);
            }
            ButtonElement buttonElementCallToActionButton = exclusiveCarouselSlideElement.callToActionButton();
            if (buttonElementCallToActionButton != null) {
                callToActionButton(buttonElementCallToActionButton);
            }
            String strImage = exclusiveCarouselSlideElement.image();
            if (strImage != null) {
                image(strImage);
            }
            String strImageDescription = exclusiveCarouselSlideElement.imageDescription();
            if (strImageDescription != null) {
                imageDescription(strImageDescription);
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableExclusiveCarouselSlideElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public final Builder secondaryText(@Nullable String str) {
            this.secondaryText = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public final Builder label(@Nullable String str) {
            this.label = str;
            return this;
        }

        @JsonProperty("callToActionButton")
        public final Builder callToActionButton(@Nullable ButtonElement buttonElement) {
            this.callToActionButton = buttonElement;
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(@Nullable String str) {
            this.image = str;
            return this;
        }

        @JsonProperty("imageDescription")
        public final Builder imageDescription(@Nullable String str) {
            this.imageDescription = str;
            return this;
        }

        public ImmutableExclusiveCarouselSlideElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableExclusiveCarouselSlideElement(this.primaryText, this.secondaryText, this.label, this.callToActionButton, this.image, this.imageDescription);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            return "Cannot build ExclusiveCarouselSlideElement, some of required attributes are not set " + arrayList;
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
}
