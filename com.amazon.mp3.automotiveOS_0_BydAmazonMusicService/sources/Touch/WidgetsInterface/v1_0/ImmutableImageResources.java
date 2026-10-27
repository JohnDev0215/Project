package Touch.WidgetsInterface.v1_0;

import Touch.WidgetsInterface.v1_0.ImageResources;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
@Generated(from = "ImageResources", generator = "Immutables")
public final class ImmutableImageResources extends ImageResources {

    @Nullable
    private final ImageResources.Image activeImage;

    @Nullable
    private final ImageResources.Image image;

    private ImmutableImageResources(@Nullable ImageResources.Image image, @Nullable ImageResources.Image image2) {
        this.image = image;
        this.activeImage = image2;
    }

    @Override // Touch.WidgetsInterface.v1_0.ImageResources
    @JsonProperty(ContextMappingConstants.IMAGE)
    @Nullable
    public ImageResources.Image image() {
        return this.image;
    }

    @Override // Touch.WidgetsInterface.v1_0.ImageResources
    @JsonProperty("activeImage")
    @Nullable
    public ImageResources.Image activeImage() {
        return this.activeImage;
    }

    public final ImmutableImageResources withImage(@Nullable ImageResources.Image image) {
        return this.image == image ? this : new ImmutableImageResources(image, this.activeImage);
    }

    public final ImmutableImageResources withActiveImage(@Nullable ImageResources.Image image) {
        return this.activeImage == image ? this : new ImmutableImageResources(this.image, image);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableImageResources) && equalTo(0, (ImmutableImageResources) obj);
    }

    private boolean equalTo(int i, ImmutableImageResources immutableImageResources) {
        return equals(this.image, immutableImageResources.image) && equals(this.activeImage, immutableImageResources.activeImage);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.image) + 5381;
        return iHashCode + (iHashCode << 5) + hashCode(this.activeImage);
    }

    public String toString() {
        return "ImageResources{image=" + this.image + ", activeImage=" + this.activeImage + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ImageResources", generator = "Immutables")
    static final class Json extends ImageResources {

        @Nullable
        ImageResources.Image activeImage;

        @Nullable
        ImageResources.Image image;

        Json() {
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(@Nullable ImageResources.Image image) {
            this.image = image;
        }

        @JsonProperty("activeImage")
        public void setActiveImage(@Nullable ImageResources.Image image) {
            this.activeImage = image;
        }

        @Override // Touch.WidgetsInterface.v1_0.ImageResources
        public ImageResources.Image image() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ImageResources
        public ImageResources.Image activeImage() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableImageResources fromJson(Json json) {
        Builder builder = builder();
        ImageResources.Image image = json.image;
        if (image != null) {
            builder.image(image);
        }
        ImageResources.Image image2 = json.activeImage;
        if (image2 != null) {
            builder.activeImage(image2);
        }
        return builder.build();
    }

    public static ImmutableImageResources copyOf(ImageResources imageResources) {
        if (imageResources instanceof ImmutableImageResources) {
            return (ImmutableImageResources) imageResources;
        }
        return builder().from(imageResources).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ImageResources", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {

        @Nullable
        private ImageResources.Image activeImage;

        @Nullable
        private ImageResources.Image image;

        private Builder() {
        }

        public final Builder from(ImageResources imageResources) {
            ImmutableImageResources.requireNonNull(imageResources, "instance");
            ImageResources.Image image = imageResources.image();
            if (image != null) {
                image(image);
            }
            ImageResources.Image imageActiveImage = imageResources.activeImage();
            if (imageActiveImage != null) {
                activeImage(imageActiveImage);
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(@Nullable ImageResources.Image image) {
            this.image = image;
            return this;
        }

        @JsonProperty("activeImage")
        public final Builder activeImage(@Nullable ImageResources.Image image) {
            this.activeImage = image;
            return this;
        }

        public ImmutableImageResources build() {
            return new ImmutableImageResources(this.image, this.activeImage);
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
