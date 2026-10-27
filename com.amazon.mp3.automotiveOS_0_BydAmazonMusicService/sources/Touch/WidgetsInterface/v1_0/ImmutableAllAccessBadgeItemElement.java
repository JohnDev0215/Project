package Touch.WidgetsInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.android.gms.cast.MediaTrack;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "AllAccessBadgeItemElement", generator = "Immutables")
public final class ImmutableAllAccessBadgeItemElement extends AllAccessBadgeItemElement {

    @Nullable
    private final String description;

    @Nullable
    private final String imageUrl;

    private ImmutableAllAccessBadgeItemElement(@Nullable String str, @Nullable String str2) {
        this.imageUrl = str;
        this.description = str2;
    }

    @Override // Touch.WidgetsInterface.v1_0.BadgeItemElement
    @JsonProperty("imageUrl")
    @Nullable
    public String imageUrl() {
        return this.imageUrl;
    }

    @Override // Touch.WidgetsInterface.v1_0.BadgeItemElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    @Nullable
    public String description() {
        return this.description;
    }

    public final ImmutableAllAccessBadgeItemElement withImageUrl(@Nullable String str) {
        return equals(this.imageUrl, str) ? this : new ImmutableAllAccessBadgeItemElement(str, this.description);
    }

    public final ImmutableAllAccessBadgeItemElement withDescription(@Nullable String str) {
        return equals(this.description, str) ? this : new ImmutableAllAccessBadgeItemElement(this.imageUrl, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableAllAccessBadgeItemElement) && equalTo(0, (ImmutableAllAccessBadgeItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableAllAccessBadgeItemElement immutableAllAccessBadgeItemElement) {
        return equals(this.imageUrl, immutableAllAccessBadgeItemElement.imageUrl) && equals(this.description, immutableAllAccessBadgeItemElement.description);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.imageUrl) + 5381;
        return iHashCode + (iHashCode << 5) + hashCode(this.description);
    }

    public String toString() {
        return "AllAccessBadgeItemElement{imageUrl=" + this.imageUrl + ", description=" + this.description + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "AllAccessBadgeItemElement", generator = "Immutables")
    static final class Json extends AllAccessBadgeItemElement {

        @Nullable
        String description;

        @Nullable
        String imageUrl;

        Json() {
        }

        @JsonProperty("imageUrl")
        public void setImageUrl(@Nullable String str) {
            this.imageUrl = str;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public void setDescription(@Nullable String str) {
            this.description = str;
        }

        @Override // Touch.WidgetsInterface.v1_0.BadgeItemElement
        public String imageUrl() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.BadgeItemElement
        public String description() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableAllAccessBadgeItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.imageUrl;
        if (str != null) {
            builder.imageUrl(str);
        }
        String str2 = json.description;
        if (str2 != null) {
            builder.description(str2);
        }
        return builder.build();
    }

    public static ImmutableAllAccessBadgeItemElement copyOf(AllAccessBadgeItemElement allAccessBadgeItemElement) {
        if (allAccessBadgeItemElement instanceof ImmutableAllAccessBadgeItemElement) {
            return (ImmutableAllAccessBadgeItemElement) allAccessBadgeItemElement;
        }
        return builder().from(allAccessBadgeItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "AllAccessBadgeItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {

        @Nullable
        private String description;

        @Nullable
        private String imageUrl;

        private Builder() {
        }

        public final Builder from(AllAccessBadgeItemElement allAccessBadgeItemElement) {
            ImmutableAllAccessBadgeItemElement.requireNonNull(allAccessBadgeItemElement, "instance");
            from((Object) allAccessBadgeItemElement);
            return this;
        }

        public final Builder from(BadgeItemElement badgeItemElement) {
            ImmutableAllAccessBadgeItemElement.requireNonNull(badgeItemElement, "instance");
            from((Object) badgeItemElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            String strImageUrl;
            if (obj instanceof AllAccessBadgeItemElement) {
                AllAccessBadgeItemElement allAccessBadgeItemElement = (AllAccessBadgeItemElement) obj;
                String strDescription = allAccessBadgeItemElement.description();
                if (strDescription != null) {
                    description(strDescription);
                }
                String strImageUrl2 = allAccessBadgeItemElement.imageUrl();
                if (strImageUrl2 != null) {
                    imageUrl(strImageUrl2);
                }
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof BadgeItemElement) {
                BadgeItemElement badgeItemElement = (BadgeItemElement) obj;
                if ((j & 2) == 0) {
                    String strDescription2 = badgeItemElement.description();
                    if (strDescription2 != null) {
                        description(strDescription2);
                    }
                    j |= 2;
                }
                if ((j & 1) != 0 || (strImageUrl = badgeItemElement.imageUrl()) == null) {
                    return;
                }
                imageUrl(strImageUrl);
            }
        }

        @JsonProperty("imageUrl")
        public final Builder imageUrl(@Nullable String str) {
            this.imageUrl = str;
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(@Nullable String str) {
            this.description = str;
            return this;
        }

        public ImmutableAllAccessBadgeItemElement build() {
            return new ImmutableAllAccessBadgeItemElement(this.imageUrl, this.description);
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
