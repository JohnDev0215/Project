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
@Generated(from = "SpotlightCarouselSlideElement", generator = "Immutables")
public final class ImmutableSpotlightCarouselSlideElement extends SpotlightCarouselSlideElement {

    @Nullable
    private final PillNavigatorWidgetItemElement callToAction;

    @Nullable
    private final String image;
    private final String primaryText;

    @Nullable
    private final PillNavigatorWidgetItemElement secondaryCallToAction;
    private final String secondaryText;

    @Nullable
    private final String tertiaryText;

    private ImmutableSpotlightCarouselSlideElement(String str, String str2, @Nullable String str3, @Nullable String str4, @Nullable PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement, @Nullable PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement2) {
        this.primaryText = str;
        this.secondaryText = str2;
        this.tertiaryText = str3;
        this.image = str4;
        this.callToAction = pillNavigatorWidgetItemElement;
        this.secondaryCallToAction = pillNavigatorWidgetItemElement2;
    }

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
    @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
    @Nullable
    public String tertiaryText() {
        return this.tertiaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
    @JsonProperty(ContextMappingConstants.IMAGE)
    @Nullable
    public String image() {
        return this.image;
    }

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
    @JsonProperty("callToAction")
    @Nullable
    public PillNavigatorWidgetItemElement callToAction() {
        return this.callToAction;
    }

    @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
    @JsonProperty("secondaryCallToAction")
    @Nullable
    public PillNavigatorWidgetItemElement secondaryCallToAction() {
        return this.secondaryCallToAction;
    }

    public final ImmutableSpotlightCarouselSlideElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableSpotlightCarouselSlideElement(str2, this.secondaryText, this.tertiaryText, this.image, this.callToAction, this.secondaryCallToAction);
    }

    public final ImmutableSpotlightCarouselSlideElement withSecondaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.SECONDARY_TEXT);
        return this.secondaryText.equals(str2) ? this : new ImmutableSpotlightCarouselSlideElement(this.primaryText, str2, this.tertiaryText, this.image, this.callToAction, this.secondaryCallToAction);
    }

    public final ImmutableSpotlightCarouselSlideElement withTertiaryText(@Nullable String str) {
        return equals(this.tertiaryText, str) ? this : new ImmutableSpotlightCarouselSlideElement(this.primaryText, this.secondaryText, str, this.image, this.callToAction, this.secondaryCallToAction);
    }

    public final ImmutableSpotlightCarouselSlideElement withImage(@Nullable String str) {
        return equals(this.image, str) ? this : new ImmutableSpotlightCarouselSlideElement(this.primaryText, this.secondaryText, this.tertiaryText, str, this.callToAction, this.secondaryCallToAction);
    }

    public final ImmutableSpotlightCarouselSlideElement withCallToAction(@Nullable PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
        return this.callToAction == pillNavigatorWidgetItemElement ? this : new ImmutableSpotlightCarouselSlideElement(this.primaryText, this.secondaryText, this.tertiaryText, this.image, pillNavigatorWidgetItemElement, this.secondaryCallToAction);
    }

    public final ImmutableSpotlightCarouselSlideElement withSecondaryCallToAction(@Nullable PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
        return this.secondaryCallToAction == pillNavigatorWidgetItemElement ? this : new ImmutableSpotlightCarouselSlideElement(this.primaryText, this.secondaryText, this.tertiaryText, this.image, this.callToAction, pillNavigatorWidgetItemElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSpotlightCarouselSlideElement) && equalTo(0, (ImmutableSpotlightCarouselSlideElement) obj);
    }

    private boolean equalTo(int i, ImmutableSpotlightCarouselSlideElement immutableSpotlightCarouselSlideElement) {
        return this.primaryText.equals(immutableSpotlightCarouselSlideElement.primaryText) && this.secondaryText.equals(immutableSpotlightCarouselSlideElement.secondaryText) && equals(this.tertiaryText, immutableSpotlightCarouselSlideElement.tertiaryText) && equals(this.image, immutableSpotlightCarouselSlideElement.image) && equals(this.callToAction, immutableSpotlightCarouselSlideElement.callToAction) && equals(this.secondaryCallToAction, immutableSpotlightCarouselSlideElement.secondaryCallToAction);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.primaryText.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.secondaryText.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.tertiaryText);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.image);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.callToAction);
        return iHashCode5 + (iHashCode5 << 5) + hashCode(this.secondaryCallToAction);
    }

    public String toString() {
        return "SpotlightCarouselSlideElement{primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", tertiaryText=" + this.tertiaryText + ", image=" + this.image + ", callToAction=" + this.callToAction + ", secondaryCallToAction=" + this.secondaryCallToAction + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SpotlightCarouselSlideElement", generator = "Immutables")
    static final class Json extends SpotlightCarouselSlideElement {

        @Nullable
        PillNavigatorWidgetItemElement callToAction;

        @Nullable
        String image;

        @Nullable
        String primaryText;

        @Nullable
        PillNavigatorWidgetItemElement secondaryCallToAction;

        @Nullable
        String secondaryText;

        @Nullable
        String tertiaryText;

        Json() {
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public void setPrimaryText(String str) {
            this.primaryText = str;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public void setSecondaryText(String str) {
            this.secondaryText = str;
        }

        @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
        public void setTertiaryText(@Nullable String str) {
            this.tertiaryText = str;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(@Nullable String str) {
            this.image = str;
        }

        @JsonProperty("callToAction")
        public void setCallToAction(@Nullable PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
            this.callToAction = pillNavigatorWidgetItemElement;
        }

        @JsonProperty("secondaryCallToAction")
        public void setSecondaryCallToAction(@Nullable PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
            this.secondaryCallToAction = pillNavigatorWidgetItemElement;
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
        public String tertiaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
        public PillNavigatorWidgetItemElement callToAction() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement
        public PillNavigatorWidgetItemElement secondaryCallToAction() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSpotlightCarouselSlideElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.primaryText;
        if (str != null) {
            builder.primaryText(str);
        }
        String str2 = json.secondaryText;
        if (str2 != null) {
            builder.secondaryText(str2);
        }
        String str3 = json.tertiaryText;
        if (str3 != null) {
            builder.tertiaryText(str3);
        }
        String str4 = json.image;
        if (str4 != null) {
            builder.image(str4);
        }
        PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement = json.callToAction;
        if (pillNavigatorWidgetItemElement != null) {
            builder.callToAction(pillNavigatorWidgetItemElement);
        }
        PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement2 = json.secondaryCallToAction;
        if (pillNavigatorWidgetItemElement2 != null) {
            builder.secondaryCallToAction(pillNavigatorWidgetItemElement2);
        }
        return builder.build();
    }

    public static ImmutableSpotlightCarouselSlideElement copyOf(SpotlightCarouselSlideElement spotlightCarouselSlideElement) {
        if (spotlightCarouselSlideElement instanceof ImmutableSpotlightCarouselSlideElement) {
            return (ImmutableSpotlightCarouselSlideElement) spotlightCarouselSlideElement;
        }
        return builder().from(spotlightCarouselSlideElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SpotlightCarouselSlideElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_TEXT = 1;
        private static final long INIT_BIT_SECONDARY_TEXT = 2;

        @Nullable
        private PillNavigatorWidgetItemElement callToAction;

        @Nullable
        private String image;
        private long initBits;

        @Nullable
        private String primaryText;

        @Nullable
        private PillNavigatorWidgetItemElement secondaryCallToAction;

        @Nullable
        private String secondaryText;

        @Nullable
        private String tertiaryText;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(SpotlightCarouselSlideElement spotlightCarouselSlideElement) {
            ImmutableSpotlightCarouselSlideElement.requireNonNull(spotlightCarouselSlideElement, "instance");
            primaryText(spotlightCarouselSlideElement.primaryText());
            secondaryText(spotlightCarouselSlideElement.secondaryText());
            String strTertiaryText = spotlightCarouselSlideElement.tertiaryText();
            if (strTertiaryText != null) {
                tertiaryText(strTertiaryText);
            }
            String strImage = spotlightCarouselSlideElement.image();
            if (strImage != null) {
                image(strImage);
            }
            PillNavigatorWidgetItemElement pillNavigatorWidgetItemElementCallToAction = spotlightCarouselSlideElement.callToAction();
            if (pillNavigatorWidgetItemElementCallToAction != null) {
                callToAction(pillNavigatorWidgetItemElementCallToAction);
            }
            PillNavigatorWidgetItemElement pillNavigatorWidgetItemElementSecondaryCallToAction = spotlightCarouselSlideElement.secondaryCallToAction();
            if (pillNavigatorWidgetItemElementSecondaryCallToAction != null) {
                secondaryCallToAction(pillNavigatorWidgetItemElementSecondaryCallToAction);
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableSpotlightCarouselSlideElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public final Builder secondaryText(String str) {
            this.secondaryText = (String) ImmutableSpotlightCarouselSlideElement.requireNonNull(str, ContextMappingConstants.SECONDARY_TEXT);
            this.initBits &= -3;
            return this;
        }

        @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
        public final Builder tertiaryText(@Nullable String str) {
            this.tertiaryText = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(@Nullable String str) {
            this.image = str;
            return this;
        }

        @JsonProperty("callToAction")
        public final Builder callToAction(@Nullable PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
            this.callToAction = pillNavigatorWidgetItemElement;
            return this;
        }

        @JsonProperty("secondaryCallToAction")
        public final Builder secondaryCallToAction(@Nullable PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
            this.secondaryCallToAction = pillNavigatorWidgetItemElement;
            return this;
        }

        public ImmutableSpotlightCarouselSlideElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSpotlightCarouselSlideElement(this.primaryText, this.secondaryText, this.tertiaryText, this.image, this.callToAction, this.secondaryCallToAction);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(ContextMappingConstants.SECONDARY_TEXT);
            }
            return "Cannot build SpotlightCarouselSlideElement, some of required attributes are not set " + arrayList;
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
