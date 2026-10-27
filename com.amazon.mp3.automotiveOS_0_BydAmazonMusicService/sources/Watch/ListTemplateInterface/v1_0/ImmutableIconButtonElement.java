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
@Generated(from = "IconButtonElement", generator = "Immutables")
public final class ImmutableIconButtonElement extends IconButtonElement {
    private final String icon;
    private final List<Method> onItemSelected;

    private ImmutableIconButtonElement(String str, List<Method> list) {
        this.icon = str;
        this.onItemSelected = list;
    }

    @Override // Watch.ListTemplateInterface.v1_0.IconButtonElement
    @JsonProperty("icon")
    public String icon() {
        return this.icon;
    }

    @Override // Watch.ListTemplateInterface.v1_0.IconButtonElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableIconButtonElement withIcon(String str) {
        String str2 = (String) requireNonNull(str, "icon");
        return this.icon.equals(str2) ? this : new ImmutableIconButtonElement(str2, this.onItemSelected);
    }

    public final ImmutableIconButtonElement withOnItemSelected(Method... methodArr) {
        return new ImmutableIconButtonElement(this.icon, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableIconButtonElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableIconButtonElement(this.icon, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableIconButtonElement) && equalTo(0, (ImmutableIconButtonElement) obj);
    }

    private boolean equalTo(int i, ImmutableIconButtonElement immutableIconButtonElement) {
        return this.icon.equals(immutableIconButtonElement.icon) && this.onItemSelected.equals(immutableIconButtonElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.icon.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "IconButtonElement{icon=" + this.icon + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "IconButtonElement", generator = "Immutables")
    static final class Json extends IconButtonElement {

        @Nullable
        String icon;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty("icon")
        public void setIcon(String str) {
            this.icon = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
        }

        @Override // Watch.ListTemplateInterface.v1_0.IconButtonElement
        public String icon() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.IconButtonElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableIconButtonElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.icon;
        if (str != null) {
            builder.icon(str);
        }
        List<Method> list = json.onItemSelected;
        if (list != null) {
            builder.addAllOnItemSelected(list);
        }
        return builder.build();
    }

    public static ImmutableIconButtonElement copyOf(IconButtonElement iconButtonElement) {
        if (iconButtonElement instanceof ImmutableIconButtonElement) {
            return (ImmutableIconButtonElement) iconButtonElement;
        }
        return builder().from(iconButtonElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "IconButtonElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ICON = 1;

        @Nullable
        private String icon;
        private long initBits;
        private List<Method> onItemSelected;

        private Builder() {
            this.initBits = 1L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(IconButtonElement iconButtonElement) {
            ImmutableIconButtonElement.requireNonNull(iconButtonElement, "instance");
            icon(iconButtonElement.icon());
            addAllOnItemSelected(iconButtonElement.onItemSelected());
            return this;
        }

        @JsonProperty("icon")
        public final Builder icon(String str) {
            this.icon = (String) ImmutableIconButtonElement.requireNonNull(str, "icon");
            this.initBits &= -2;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableIconButtonElement.requireNonNull(method, "onItemSelected element"));
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableIconButtonElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableIconButtonElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            return this;
        }

        public ImmutableIconButtonElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableIconButtonElement(this.icon, ImmutableIconButtonElement.createUnmodifiableList(true, this.onItemSelected));
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("icon");
            }
            return "Cannot build IconButtonElement, some of required attributes are not set " + arrayList;
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
