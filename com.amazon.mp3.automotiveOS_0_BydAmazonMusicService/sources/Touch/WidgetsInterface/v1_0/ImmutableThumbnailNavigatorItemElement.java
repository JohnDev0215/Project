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
@Generated(from = "ThumbnailNavigatorItemElement", generator = "Immutables")
public final class ImmutableThumbnailNavigatorItemElement extends ThumbnailNavigatorItemElement {
    private final String image;
    private final List<Method> onItemSelected;
    private final String primaryText;

    private ImmutableThumbnailNavigatorItemElement(Builder builder) {
        List<Method> listCreateUnmodifiableList;
        this.primaryText = builder.primaryText;
        this.image = builder.image;
        if (!builder.onItemSelectedIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.onItemSelected(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.onItemSelected);
        }
        this.onItemSelected = listCreateUnmodifiableList;
    }

    private ImmutableThumbnailNavigatorItemElement(String str, String str2, List<Method> list) {
        this.primaryText = str;
        this.image = str2;
        this.onItemSelected = list;
    }

    @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorItemElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorItemElement
    @JsonProperty(ContextMappingConstants.IMAGE)
    public String image() {
        return this.image;
    }

    @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorItemElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableThumbnailNavigatorItemElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableThumbnailNavigatorItemElement(str2, this.image, this.onItemSelected);
    }

    public final ImmutableThumbnailNavigatorItemElement withImage(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.IMAGE);
        return this.image.equals(str2) ? this : new ImmutableThumbnailNavigatorItemElement(this.primaryText, str2, this.onItemSelected);
    }

    public final ImmutableThumbnailNavigatorItemElement withOnItemSelected(Method... methodArr) {
        return new ImmutableThumbnailNavigatorItemElement(this.primaryText, this.image, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableThumbnailNavigatorItemElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableThumbnailNavigatorItemElement(this.primaryText, this.image, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableThumbnailNavigatorItemElement) && equalTo(0, (ImmutableThumbnailNavigatorItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableThumbnailNavigatorItemElement immutableThumbnailNavigatorItemElement) {
        return this.primaryText.equals(immutableThumbnailNavigatorItemElement.primaryText) && this.image.equals(immutableThumbnailNavigatorItemElement.image) && this.onItemSelected.equals(immutableThumbnailNavigatorItemElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.primaryText.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.image.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "ThumbnailNavigatorItemElement{primaryText=" + this.primaryText + ", image=" + this.image + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ThumbnailNavigatorItemElement", generator = "Immutables")
    static final class Json extends ThumbnailNavigatorItemElement {

        @Nullable
        String image;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();
        boolean onItemSelectedIsSet;

        @Nullable
        String primaryText;

        Json() {
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public void setPrimaryText(String str) {
            this.primaryText = str;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(String str) {
            this.image = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorItemElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorItemElement
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ThumbnailNavigatorItemElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableThumbnailNavigatorItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.primaryText;
        if (str != null) {
            builder.primaryText(str);
        }
        String str2 = json.image;
        if (str2 != null) {
            builder.image(str2);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        return builder.build();
    }

    public static ImmutableThumbnailNavigatorItemElement copyOf(ThumbnailNavigatorItemElement thumbnailNavigatorItemElement) {
        if (thumbnailNavigatorItemElement instanceof ImmutableThumbnailNavigatorItemElement) {
            return (ImmutableThumbnailNavigatorItemElement) thumbnailNavigatorItemElement;
        }
        return builder().from(thumbnailNavigatorItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ThumbnailNavigatorItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_IMAGE = 2;
        private static final long INIT_BIT_PRIMARY_TEXT = 1;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 1;

        @Nullable
        private String image;
        private long initBits;
        private List<Method> onItemSelected;
        private long optBits;

        @Nullable
        private String primaryText;

        private Builder() {
            this.initBits = 3L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(ThumbnailNavigatorItemElement thumbnailNavigatorItemElement) {
            ImmutableThumbnailNavigatorItemElement.requireNonNull(thumbnailNavigatorItemElement, "instance");
            primaryText(thumbnailNavigatorItemElement.primaryText());
            image(thumbnailNavigatorItemElement.image());
            addAllOnItemSelected(thumbnailNavigatorItemElement.onItemSelected());
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableThumbnailNavigatorItemElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(String str) {
            this.image = (String) ImmutableThumbnailNavigatorItemElement.requireNonNull(str, ContextMappingConstants.IMAGE);
            this.initBits &= -3;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableThumbnailNavigatorItemElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableThumbnailNavigatorItemElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableThumbnailNavigatorItemElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableThumbnailNavigatorItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableThumbnailNavigatorItemElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(ContextMappingConstants.IMAGE);
            }
            return "Cannot build ThumbnailNavigatorItemElement, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
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
