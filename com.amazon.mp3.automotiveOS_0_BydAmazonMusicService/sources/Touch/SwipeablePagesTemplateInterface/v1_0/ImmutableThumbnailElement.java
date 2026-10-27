package Touch.SwipeablePagesTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
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
@Generated(from = "ThumbnailElement", generator = "Immutables")
public final class ImmutableThumbnailElement extends ThumbnailElement {

    @Nullable
    private final LinkElement link;
    private final List<Method> onItemSelected;
    private final String thumbnailImage;

    private ImmutableThumbnailElement(Builder builder) {
        List<Method> listCreateUnmodifiableList;
        this.thumbnailImage = builder.thumbnailImage;
        this.link = builder.link;
        if (!builder.onItemSelectedIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.onItemSelected(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.onItemSelected);
        }
        this.onItemSelected = listCreateUnmodifiableList;
    }

    private ImmutableThumbnailElement(String str, @Nullable LinkElement linkElement, List<Method> list) {
        this.thumbnailImage = str;
        this.link = linkElement;
        this.onItemSelected = list;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ThumbnailElement
    @JsonProperty("thumbnailImage")
    public String thumbnailImage() {
        return this.thumbnailImage;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ThumbnailElement
    @JsonProperty("link")
    @Nullable
    public LinkElement link() {
        return this.link;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ThumbnailElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableThumbnailElement withThumbnailImage(String str) {
        String str2 = (String) requireNonNull(str, "thumbnailImage");
        return this.thumbnailImage.equals(str2) ? this : new ImmutableThumbnailElement(str2, this.link, this.onItemSelected);
    }

    public final ImmutableThumbnailElement withLink(@Nullable LinkElement linkElement) {
        return this.link == linkElement ? this : new ImmutableThumbnailElement(this.thumbnailImage, linkElement, this.onItemSelected);
    }

    public final ImmutableThumbnailElement withOnItemSelected(Method... methodArr) {
        return new ImmutableThumbnailElement(this.thumbnailImage, this.link, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableThumbnailElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableThumbnailElement(this.thumbnailImage, this.link, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableThumbnailElement) && equalTo(0, (ImmutableThumbnailElement) obj);
    }

    private boolean equalTo(int i, ImmutableThumbnailElement immutableThumbnailElement) {
        return this.thumbnailImage.equals(immutableThumbnailElement.thumbnailImage) && equals(this.link, immutableThumbnailElement.link) && this.onItemSelected.equals(immutableThumbnailElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.thumbnailImage.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.link);
        return iHashCode2 + (iHashCode2 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "ThumbnailElement{thumbnailImage=" + this.thumbnailImage + ", link=" + this.link + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ThumbnailElement", generator = "Immutables")
    static final class Json extends ThumbnailElement {

        @Nullable
        LinkElement link;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();
        boolean onItemSelectedIsSet;

        @Nullable
        String thumbnailImage;

        Json() {
        }

        @JsonProperty("thumbnailImage")
        public void setThumbnailImage(String str) {
            this.thumbnailImage = str;
        }

        @JsonProperty("link")
        public void setLink(@Nullable LinkElement linkElement) {
            this.link = linkElement;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ThumbnailElement
        public String thumbnailImage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ThumbnailElement
        public LinkElement link() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ThumbnailElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableThumbnailElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.thumbnailImage;
        if (str != null) {
            builder.thumbnailImage(str);
        }
        LinkElement linkElement = json.link;
        if (linkElement != null) {
            builder.link(linkElement);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        return builder.build();
    }

    public static ImmutableThumbnailElement copyOf(ThumbnailElement thumbnailElement) {
        if (thumbnailElement instanceof ImmutableThumbnailElement) {
            return (ImmutableThumbnailElement) thumbnailElement;
        }
        return builder().from(thumbnailElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ThumbnailElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_THUMBNAIL_IMAGE = 1;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 1;
        private long initBits;

        @Nullable
        private LinkElement link;
        private List<Method> onItemSelected;
        private long optBits;

        @Nullable
        private String thumbnailImage;

        private Builder() {
            this.initBits = 1L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(ThumbnailElement thumbnailElement) {
            ImmutableThumbnailElement.requireNonNull(thumbnailElement, "instance");
            thumbnailImage(thumbnailElement.thumbnailImage());
            LinkElement linkElementLink = thumbnailElement.link();
            if (linkElementLink != null) {
                link(linkElementLink);
            }
            addAllOnItemSelected(thumbnailElement.onItemSelected());
            return this;
        }

        @JsonProperty("thumbnailImage")
        public final Builder thumbnailImage(String str) {
            this.thumbnailImage = (String) ImmutableThumbnailElement.requireNonNull(str, "thumbnailImage");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("link")
        public final Builder link(@Nullable LinkElement linkElement) {
            this.link = linkElement;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableThumbnailElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableThumbnailElement.requireNonNull(method, "onItemSelected element"));
            }
            this.optBits |= 1;
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
                this.onItemSelected.add((Method) ImmutableThumbnailElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableThumbnailElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableThumbnailElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("thumbnailImage");
            }
            return "Cannot build ThumbnailElement, some of required attributes are not set " + arrayList;
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
