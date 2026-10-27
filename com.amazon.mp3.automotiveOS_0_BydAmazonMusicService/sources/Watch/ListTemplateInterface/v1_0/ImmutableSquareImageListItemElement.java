package Watch.ListTemplateInterface.v1_0;

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
@Generated(from = "SquareImageListItemElement", generator = "Immutables")
public final class ImmutableSquareImageListItemElement extends SquareImageListItemElement {

    @Nullable
    private final String image;
    private final List<Method> onItemSelected;
    private final String primaryText;

    @Nullable
    private final String secondaryText;

    private ImmutableSquareImageListItemElement(@Nullable String str, String str2, @Nullable String str3, List<Method> list) {
        this.image = str;
        this.primaryText = str2;
        this.secondaryText = str3;
        this.onItemSelected = list;
    }

    @Override // Watch.ListTemplateInterface.v1_0.ImageListItemElement
    @JsonProperty(ContextMappingConstants.IMAGE)
    @Nullable
    public String image() {
        return this.image;
    }

    @Override // Watch.ListTemplateInterface.v1_0.ImageListItemElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Watch.ListTemplateInterface.v1_0.ImageListItemElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    @Nullable
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Watch.ListTemplateInterface.v1_0.ImageListItemElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableSquareImageListItemElement withImage(@Nullable String str) {
        return equals(this.image, str) ? this : new ImmutableSquareImageListItemElement(str, this.primaryText, this.secondaryText, this.onItemSelected);
    }

    public final ImmutableSquareImageListItemElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableSquareImageListItemElement(this.image, str2, this.secondaryText, this.onItemSelected);
    }

    public final ImmutableSquareImageListItemElement withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutableSquareImageListItemElement(this.image, this.primaryText, str, this.onItemSelected);
    }

    public final ImmutableSquareImageListItemElement withOnItemSelected(Method... methodArr) {
        return new ImmutableSquareImageListItemElement(this.image, this.primaryText, this.secondaryText, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSquareImageListItemElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableSquareImageListItemElement(this.image, this.primaryText, this.secondaryText, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSquareImageListItemElement) && equalTo(0, (ImmutableSquareImageListItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableSquareImageListItemElement immutableSquareImageListItemElement) {
        return equals(this.image, immutableSquareImageListItemElement.image) && this.primaryText.equals(immutableSquareImageListItemElement.primaryText) && equals(this.secondaryText, immutableSquareImageListItemElement.secondaryText) && this.onItemSelected.equals(immutableSquareImageListItemElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.image) + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.primaryText.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.secondaryText);
        return iHashCode3 + (iHashCode3 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "SquareImageListItemElement{image=" + this.image + ", primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SquareImageListItemElement", generator = "Immutables")
    static final class Json extends SquareImageListItemElement {

        @Nullable
        String image;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        @Nullable
        String primaryText;

        @Nullable
        String secondaryText;

        Json() {
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(@Nullable String str) {
            this.image = str;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public void setPrimaryText(String str) {
            this.primaryText = str;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public void setSecondaryText(@Nullable String str) {
            this.secondaryText = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
        }

        @Override // Watch.ListTemplateInterface.v1_0.ImageListItemElement
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.ImageListItemElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.ImageListItemElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.ImageListItemElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSquareImageListItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.image;
        if (str != null) {
            builder.image(str);
        }
        String str2 = json.primaryText;
        if (str2 != null) {
            builder.primaryText(str2);
        }
        String str3 = json.secondaryText;
        if (str3 != null) {
            builder.secondaryText(str3);
        }
        List<Method> list = json.onItemSelected;
        if (list != null) {
            builder.addAllOnItemSelected(list);
        }
        return builder.build();
    }

    public static ImmutableSquareImageListItemElement copyOf(SquareImageListItemElement squareImageListItemElement) {
        if (squareImageListItemElement instanceof ImmutableSquareImageListItemElement) {
            return (ImmutableSquareImageListItemElement) squareImageListItemElement;
        }
        return builder().from(squareImageListItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SquareImageListItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_TEXT = 1;

        @Nullable
        private String image;
        private long initBits;
        private List<Method> onItemSelected;

        @Nullable
        private String primaryText;

        @Nullable
        private String secondaryText;

        private Builder() {
            this.initBits = 1L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(ImageListItemElement imageListItemElement) {
            ImmutableSquareImageListItemElement.requireNonNull(imageListItemElement, "instance");
            from((Object) imageListItemElement);
            return this;
        }

        public final Builder from(SquareImageListItemElement squareImageListItemElement) {
            ImmutableSquareImageListItemElement.requireNonNull(squareImageListItemElement, "instance");
            from((Object) squareImageListItemElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            String strSecondaryText;
            if (obj instanceof ImageListItemElement) {
                ImageListItemElement imageListItemElement = (ImageListItemElement) obj;
                addAllOnItemSelected(imageListItemElement.onItemSelected());
                String strImage = imageListItemElement.image();
                if (strImage != null) {
                    image(strImage);
                }
                primaryText(imageListItemElement.primaryText());
                String strSecondaryText2 = imageListItemElement.secondaryText();
                if (strSecondaryText2 != null) {
                    secondaryText(strSecondaryText2);
                }
                j = 15;
            } else {
                j = 0;
            }
            if (obj instanceof SquareImageListItemElement) {
                SquareImageListItemElement squareImageListItemElement = (SquareImageListItemElement) obj;
                if ((j & 1) == 0) {
                    addAllOnItemSelected(squareImageListItemElement.onItemSelected());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    String strImage2 = squareImageListItemElement.image();
                    if (strImage2 != null) {
                        image(strImage2);
                    }
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    primaryText(squareImageListItemElement.primaryText());
                    j |= 4;
                }
                if ((j & 8) != 0 || (strSecondaryText = squareImageListItemElement.secondaryText()) == null) {
                    return;
                }
                secondaryText(strSecondaryText);
            }
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(@Nullable String str) {
            this.image = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableSquareImageListItemElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public final Builder secondaryText(@Nullable String str) {
            this.secondaryText = str;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableSquareImageListItemElement.requireNonNull(method, "onItemSelected element"));
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableSquareImageListItemElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableSquareImageListItemElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            return this;
        }

        public ImmutableSquareImageListItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSquareImageListItemElement(this.image, this.primaryText, this.secondaryText, ImmutableSquareImageListItemElement.createUnmodifiableList(true, this.onItemSelected));
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            return "Cannot build SquareImageListItemElement, some of required attributes are not set " + arrayList;
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
