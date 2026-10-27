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
@Generated(from = "DescriptiveTextWidgetItemElement", generator = "Immutables")
public final class ImmutableDescriptiveTextWidgetItemElement extends DescriptiveTextWidgetItemElement {
    private final String primaryText;

    @Nullable
    private final String secondaryText;

    private ImmutableDescriptiveTextWidgetItemElement(String str, @Nullable String str2) {
        this.primaryText = str;
        this.secondaryText = str2;
    }

    @Override // Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetItemElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetItemElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    @Nullable
    public String secondaryText() {
        return this.secondaryText;
    }

    public final ImmutableDescriptiveTextWidgetItemElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableDescriptiveTextWidgetItemElement(str2, this.secondaryText);
    }

    public final ImmutableDescriptiveTextWidgetItemElement withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutableDescriptiveTextWidgetItemElement(this.primaryText, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableDescriptiveTextWidgetItemElement) && equalTo(0, (ImmutableDescriptiveTextWidgetItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableDescriptiveTextWidgetItemElement immutableDescriptiveTextWidgetItemElement) {
        return this.primaryText.equals(immutableDescriptiveTextWidgetItemElement.primaryText) && equals(this.secondaryText, immutableDescriptiveTextWidgetItemElement.secondaryText);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.primaryText.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + hashCode(this.secondaryText);
    }

    public String toString() {
        return "DescriptiveTextWidgetItemElement{primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "DescriptiveTextWidgetItemElement", generator = "Immutables")
    static final class Json extends DescriptiveTextWidgetItemElement {

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

        @Override // Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetItemElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetItemElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableDescriptiveTextWidgetItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.primaryText;
        if (str != null) {
            builder.primaryText(str);
        }
        String str2 = json.secondaryText;
        if (str2 != null) {
            builder.secondaryText(str2);
        }
        return builder.build();
    }

    public static ImmutableDescriptiveTextWidgetItemElement copyOf(DescriptiveTextWidgetItemElement descriptiveTextWidgetItemElement) {
        if (descriptiveTextWidgetItemElement instanceof ImmutableDescriptiveTextWidgetItemElement) {
            return (ImmutableDescriptiveTextWidgetItemElement) descriptiveTextWidgetItemElement;
        }
        return builder().from(descriptiveTextWidgetItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "DescriptiveTextWidgetItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_TEXT = 1;
        private long initBits;

        @Nullable
        private String primaryText;

        @Nullable
        private String secondaryText;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(DescriptiveTextWidgetItemElement descriptiveTextWidgetItemElement) {
            ImmutableDescriptiveTextWidgetItemElement.requireNonNull(descriptiveTextWidgetItemElement, "instance");
            primaryText(descriptiveTextWidgetItemElement.primaryText());
            String strSecondaryText = descriptiveTextWidgetItemElement.secondaryText();
            if (strSecondaryText != null) {
                secondaryText(strSecondaryText);
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableDescriptiveTextWidgetItemElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public final Builder secondaryText(@Nullable String str) {
            this.secondaryText = str;
            return this;
        }

        public ImmutableDescriptiveTextWidgetItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableDescriptiveTextWidgetItemElement(this.primaryText, this.secondaryText);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            return "Cannot build DescriptiveTextWidgetItemElement, some of required attributes are not set " + arrayList;
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
