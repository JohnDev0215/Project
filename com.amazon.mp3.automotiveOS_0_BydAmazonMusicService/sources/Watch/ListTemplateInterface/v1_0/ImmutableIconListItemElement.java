package Watch.ListTemplateInterface.v1_0;

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
@Generated(from = "IconListItemElement", generator = "Immutables")
public final class ImmutableIconListItemElement extends IconListItemElement {
    private final String icon;
    private final List<Method> onItemSelected;
    private final String text;

    private ImmutableIconListItemElement(String str, String str2, List<Method> list) {
        this.icon = str;
        this.text = str2;
        this.onItemSelected = list;
    }

    @Override // Watch.ListTemplateInterface.v1_0.IconListItemElement
    @JsonProperty("icon")
    public String icon() {
        return this.icon;
    }

    @Override // Watch.ListTemplateInterface.v1_0.IconListItemElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    @Override // Watch.ListTemplateInterface.v1_0.IconListItemElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableIconListItemElement withIcon(String str) {
        String str2 = (String) requireNonNull(str, "icon");
        return this.icon.equals(str2) ? this : new ImmutableIconListItemElement(str2, this.text, this.onItemSelected);
    }

    public final ImmutableIconListItemElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutableIconListItemElement(this.icon, str2, this.onItemSelected);
    }

    public final ImmutableIconListItemElement withOnItemSelected(Method... methodArr) {
        return new ImmutableIconListItemElement(this.icon, this.text, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableIconListItemElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableIconListItemElement(this.icon, this.text, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableIconListItemElement) && equalTo(0, (ImmutableIconListItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableIconListItemElement immutableIconListItemElement) {
        return this.icon.equals(immutableIconListItemElement.icon) && this.text.equals(immutableIconListItemElement.text) && this.onItemSelected.equals(immutableIconListItemElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.icon.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.text.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "IconListItemElement{icon=" + this.icon + ", text=" + this.text + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "IconListItemElement", generator = "Immutables")
    static final class Json extends IconListItemElement {

        @Nullable
        String icon;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        @Nullable
        String text;

        Json() {
        }

        @JsonProperty("icon")
        public void setIcon(String str) {
            this.icon = str;
        }

        @JsonProperty("text")
        public void setText(String str) {
            this.text = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
        }

        @Override // Watch.ListTemplateInterface.v1_0.IconListItemElement
        public String icon() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.IconListItemElement
        public String text() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.IconListItemElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableIconListItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.icon;
        if (str != null) {
            builder.icon(str);
        }
        String str2 = json.text;
        if (str2 != null) {
            builder.text(str2);
        }
        List<Method> list = json.onItemSelected;
        if (list != null) {
            builder.addAllOnItemSelected(list);
        }
        return builder.build();
    }

    public static ImmutableIconListItemElement copyOf(IconListItemElement iconListItemElement) {
        if (iconListItemElement instanceof ImmutableIconListItemElement) {
            return (ImmutableIconListItemElement) iconListItemElement;
        }
        return builder().from(iconListItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "IconListItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ICON = 1;
        private static final long INIT_BIT_TEXT = 2;

        @Nullable
        private String icon;
        private long initBits;
        private List<Method> onItemSelected;

        @Nullable
        private String text;

        private Builder() {
            this.initBits = 3L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(IconListItemElement iconListItemElement) {
            ImmutableIconListItemElement.requireNonNull(iconListItemElement, "instance");
            icon(iconListItemElement.icon());
            text(iconListItemElement.text());
            addAllOnItemSelected(iconListItemElement.onItemSelected());
            return this;
        }

        @JsonProperty("icon")
        public final Builder icon(String str) {
            this.icon = (String) ImmutableIconListItemElement.requireNonNull(str, "icon");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutableIconListItemElement.requireNonNull(str, "text");
            this.initBits &= -3;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableIconListItemElement.requireNonNull(method, "onItemSelected element"));
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableIconListItemElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableIconListItemElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            return this;
        }

        public ImmutableIconListItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableIconListItemElement(this.icon, this.text, ImmutableIconListItemElement.createUnmodifiableList(true, this.onItemSelected));
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("icon");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("text");
            }
            return "Cannot build IconListItemElement, some of required attributes are not set " + arrayList;
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
