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
@Generated(from = "VisualItemElement", generator = "Immutables")
public final class ImmutableVisualItemElement extends VisualItemElement {
    private final String image;

    @Nullable
    private final String label;
    private final List<Method> onItemSelected;
    private final String primaryText;

    @Nullable
    private final String secondaryText;

    private ImmutableVisualItemElement(String str, @Nullable String str2, String str3, @Nullable String str4, List<Method> list) {
        this.image = str;
        this.label = str2;
        this.primaryText = str3;
        this.secondaryText = str4;
        this.onItemSelected = list;
    }

    @Override // Watch.ListTemplateInterface.v1_0.VisualItemElement
    @JsonProperty(ContextMappingConstants.IMAGE)
    public String image() {
        return this.image;
    }

    @Override // Watch.ListTemplateInterface.v1_0.VisualItemElement
    @JsonProperty(ContextMappingConstants.LABEL)
    @Nullable
    public String label() {
        return this.label;
    }

    @Override // Watch.ListTemplateInterface.v1_0.VisualItemElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Watch.ListTemplateInterface.v1_0.VisualItemElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    @Nullable
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Watch.ListTemplateInterface.v1_0.VisualItemElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableVisualItemElement withImage(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.IMAGE);
        return this.image.equals(str2) ? this : new ImmutableVisualItemElement(str2, this.label, this.primaryText, this.secondaryText, this.onItemSelected);
    }

    public final ImmutableVisualItemElement withLabel(@Nullable String str) {
        return equals(this.label, str) ? this : new ImmutableVisualItemElement(this.image, str, this.primaryText, this.secondaryText, this.onItemSelected);
    }

    public final ImmutableVisualItemElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableVisualItemElement(this.image, this.label, str2, this.secondaryText, this.onItemSelected);
    }

    public final ImmutableVisualItemElement withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutableVisualItemElement(this.image, this.label, this.primaryText, str, this.onItemSelected);
    }

    public final ImmutableVisualItemElement withOnItemSelected(Method... methodArr) {
        return new ImmutableVisualItemElement(this.image, this.label, this.primaryText, this.secondaryText, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableVisualItemElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableVisualItemElement(this.image, this.label, this.primaryText, this.secondaryText, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVisualItemElement) && equalTo(0, (ImmutableVisualItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableVisualItemElement immutableVisualItemElement) {
        return this.image.equals(immutableVisualItemElement.image) && equals(this.label, immutableVisualItemElement.label) && this.primaryText.equals(immutableVisualItemElement.primaryText) && equals(this.secondaryText, immutableVisualItemElement.secondaryText) && this.onItemSelected.equals(immutableVisualItemElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.image.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.label);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.primaryText.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.secondaryText);
        return iHashCode4 + (iHashCode4 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "VisualItemElement{image=" + this.image + ", label=" + this.label + ", primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VisualItemElement", generator = "Immutables")
    static final class Json extends VisualItemElement {

        @Nullable
        String image;

        @Nullable
        String label;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        @Nullable
        String primaryText;

        @Nullable
        String secondaryText;

        Json() {
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(String str) {
            this.image = str;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public void setLabel(@Nullable String str) {
            this.label = str;
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

        @Override // Watch.ListTemplateInterface.v1_0.VisualItemElement
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.VisualItemElement
        public String label() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.VisualItemElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.VisualItemElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.VisualItemElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVisualItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.image;
        if (str != null) {
            builder.image(str);
        }
        String str2 = json.label;
        if (str2 != null) {
            builder.label(str2);
        }
        String str3 = json.primaryText;
        if (str3 != null) {
            builder.primaryText(str3);
        }
        String str4 = json.secondaryText;
        if (str4 != null) {
            builder.secondaryText(str4);
        }
        List<Method> list = json.onItemSelected;
        if (list != null) {
            builder.addAllOnItemSelected(list);
        }
        return builder.build();
    }

    public static ImmutableVisualItemElement copyOf(VisualItemElement visualItemElement) {
        if (visualItemElement instanceof ImmutableVisualItemElement) {
            return (ImmutableVisualItemElement) visualItemElement;
        }
        return builder().from(visualItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VisualItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_IMAGE = 1;
        private static final long INIT_BIT_PRIMARY_TEXT = 2;

        @Nullable
        private String image;
        private long initBits;

        @Nullable
        private String label;
        private List<Method> onItemSelected;

        @Nullable
        private String primaryText;

        @Nullable
        private String secondaryText;

        private Builder() {
            this.initBits = 3L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(VisualItemElement visualItemElement) {
            ImmutableVisualItemElement.requireNonNull(visualItemElement, "instance");
            image(visualItemElement.image());
            String strLabel = visualItemElement.label();
            if (strLabel != null) {
                label(strLabel);
            }
            primaryText(visualItemElement.primaryText());
            String strSecondaryText = visualItemElement.secondaryText();
            if (strSecondaryText != null) {
                secondaryText(strSecondaryText);
            }
            addAllOnItemSelected(visualItemElement.onItemSelected());
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(String str) {
            this.image = (String) ImmutableVisualItemElement.requireNonNull(str, ContextMappingConstants.IMAGE);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public final Builder label(@Nullable String str) {
            this.label = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableVisualItemElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -3;
            return this;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public final Builder secondaryText(@Nullable String str) {
            this.secondaryText = str;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableVisualItemElement.requireNonNull(method, "onItemSelected element"));
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableVisualItemElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableVisualItemElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            return this;
        }

        public ImmutableVisualItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableVisualItemElement(this.image, this.label, this.primaryText, this.secondaryText, ImmutableVisualItemElement.createUnmodifiableList(true, this.onItemSelected));
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.IMAGE);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            return "Cannot build VisualItemElement, some of required attributes are not set " + arrayList;
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
