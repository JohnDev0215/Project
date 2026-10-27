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
@Generated(from = "SelectorElement", generator = "Immutables")
public final class ImmutableSelectorElement extends SelectorElement {
    private final String icon;
    private final List<Method> onItemSelected;
    private final String primaryText;

    private ImmutableSelectorElement(Builder builder) {
        List<Method> listCreateUnmodifiableList;
        this.primaryText = builder.primaryText;
        this.icon = builder.icon;
        if (!builder.onItemSelectedIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.onItemSelected(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.onItemSelected);
        }
        this.onItemSelected = listCreateUnmodifiableList;
    }

    private ImmutableSelectorElement(String str, String str2, List<Method> list) {
        this.primaryText = str;
        this.icon = str2;
        this.onItemSelected = list;
    }

    @Override // Touch.WidgetsInterface.v1_0.SelectorElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.SelectorElement
    @JsonProperty("icon")
    public String icon() {
        return this.icon;
    }

    @Override // Touch.WidgetsInterface.v1_0.SelectorElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableSelectorElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableSelectorElement(str2, this.icon, this.onItemSelected);
    }

    public final ImmutableSelectorElement withIcon(String str) {
        String str2 = (String) requireNonNull(str, "icon");
        return this.icon.equals(str2) ? this : new ImmutableSelectorElement(this.primaryText, str2, this.onItemSelected);
    }

    public final ImmutableSelectorElement withOnItemSelected(Method... methodArr) {
        return new ImmutableSelectorElement(this.primaryText, this.icon, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSelectorElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableSelectorElement(this.primaryText, this.icon, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSelectorElement) && equalTo(0, (ImmutableSelectorElement) obj);
    }

    private boolean equalTo(int i, ImmutableSelectorElement immutableSelectorElement) {
        return this.primaryText.equals(immutableSelectorElement.primaryText) && this.icon.equals(immutableSelectorElement.icon) && this.onItemSelected.equals(immutableSelectorElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.primaryText.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.icon.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "SelectorElement{primaryText=" + this.primaryText + ", icon=" + this.icon + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SelectorElement", generator = "Immutables")
    static final class Json extends SelectorElement {

        @Nullable
        String icon;

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

        @JsonProperty("icon")
        public void setIcon(String str) {
            this.icon = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.SelectorElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SelectorElement
        public String icon() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SelectorElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSelectorElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.primaryText;
        if (str != null) {
            builder.primaryText(str);
        }
        String str2 = json.icon;
        if (str2 != null) {
            builder.icon(str2);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        return builder.build();
    }

    public static ImmutableSelectorElement copyOf(SelectorElement selectorElement) {
        if (selectorElement instanceof ImmutableSelectorElement) {
            return (ImmutableSelectorElement) selectorElement;
        }
        return builder().from(selectorElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SelectorElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ICON = 2;
        private static final long INIT_BIT_PRIMARY_TEXT = 1;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 1;

        @Nullable
        private String icon;
        private long initBits;
        private List<Method> onItemSelected;
        private long optBits;

        @Nullable
        private String primaryText;

        private Builder() {
            this.initBits = 3L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(SelectorElement selectorElement) {
            ImmutableSelectorElement.requireNonNull(selectorElement, "instance");
            primaryText(selectorElement.primaryText());
            icon(selectorElement.icon());
            addAllOnItemSelected(selectorElement.onItemSelected());
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableSelectorElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("icon")
        public final Builder icon(String str) {
            this.icon = (String) ImmutableSelectorElement.requireNonNull(str, "icon");
            this.initBits &= -3;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableSelectorElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableSelectorElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableSelectorElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableSelectorElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSelectorElement(this);
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
                arrayList.add("icon");
            }
            return "Cannot build SelectorElement, some of required attributes are not set " + arrayList;
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
