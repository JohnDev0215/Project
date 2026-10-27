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
@Generated(from = "IconLinkElement", generator = "Immutables")
public final class ImmutableIconLinkElement extends IconLinkElement {
    private final String icon;
    private final List<Method> onItemSelected;

    private ImmutableIconLinkElement(Builder builder) {
        List<Method> listCreateUnmodifiableList;
        this.icon = builder.icon;
        if (!builder.onItemSelectedIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.onItemSelected(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.onItemSelected);
        }
        this.onItemSelected = listCreateUnmodifiableList;
    }

    private ImmutableIconLinkElement(List<Method> list, String str) {
        this.onItemSelected = list;
        this.icon = str;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.LinkElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.IconLinkElement
    @JsonProperty("icon")
    public String icon() {
        return this.icon;
    }

    public final ImmutableIconLinkElement withOnItemSelected(Method... methodArr) {
        return new ImmutableIconLinkElement((List<Method>) createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.icon);
    }

    public final ImmutableIconLinkElement withOnItemSelected(Iterable<? extends Method> iterable) {
        return this.onItemSelected == iterable ? this : new ImmutableIconLinkElement((List<Method>) createUnmodifiableList(false, createSafeList(iterable, true, false)), this.icon);
    }

    public final ImmutableIconLinkElement withIcon(String str) {
        String str2 = (String) requireNonNull(str, "icon");
        return this.icon.equals(str2) ? this : new ImmutableIconLinkElement(this.onItemSelected, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableIconLinkElement) && equalTo(0, (ImmutableIconLinkElement) obj);
    }

    private boolean equalTo(int i, ImmutableIconLinkElement immutableIconLinkElement) {
        return this.onItemSelected.equals(immutableIconLinkElement.onItemSelected) && this.icon.equals(immutableIconLinkElement.icon);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onItemSelected.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.icon.hashCode();
    }

    public String toString() {
        return "IconLinkElement{onItemSelected=" + this.onItemSelected + ", icon=" + this.icon + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "IconLinkElement", generator = "Immutables")
    static final class Json extends IconLinkElement {

        @Nullable
        String icon;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();
        boolean onItemSelectedIsSet;

        Json() {
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @JsonProperty("icon")
        public void setIcon(String str) {
            this.icon = str;
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.LinkElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.IconLinkElement
        public String icon() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableIconLinkElement fromJson(Json json) {
        Builder builder = builder();
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        String str = json.icon;
        if (str != null) {
            builder.icon(str);
        }
        return builder.build();
    }

    public static ImmutableIconLinkElement copyOf(IconLinkElement iconLinkElement) {
        if (iconLinkElement instanceof ImmutableIconLinkElement) {
            return (ImmutableIconLinkElement) iconLinkElement;
        }
        return builder().from(iconLinkElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "IconLinkElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ICON = 1;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 1;

        @Nullable
        private String icon;
        private long initBits;
        private List<Method> onItemSelected;
        private long optBits;

        private Builder() {
            this.initBits = 1L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(IconLinkElement iconLinkElement) {
            ImmutableIconLinkElement.requireNonNull(iconLinkElement, "instance");
            from((Object) iconLinkElement);
            return this;
        }

        public final Builder from(LinkElement linkElement) {
            ImmutableIconLinkElement.requireNonNull(linkElement, "instance");
            from((Object) linkElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof IconLinkElement) {
                IconLinkElement iconLinkElement = (IconLinkElement) obj;
                addAllOnItemSelected(iconLinkElement.onItemSelected());
                icon(iconLinkElement.icon());
                j = 1;
            } else {
                j = 0;
            }
            if (obj instanceof LinkElement) {
                LinkElement linkElement = (LinkElement) obj;
                if ((j & 1) == 0) {
                    addAllOnItemSelected(linkElement.onItemSelected());
                }
            }
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableIconLinkElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableIconLinkElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableIconLinkElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("icon")
        public final Builder icon(String str) {
            this.icon = (String) ImmutableIconLinkElement.requireNonNull(str, "icon");
            this.initBits &= -2;
            return this;
        }

        public ImmutableIconLinkElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableIconLinkElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("icon");
            }
            return "Cannot build IconLinkElement, some of required attributes are not set " + arrayList;
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
