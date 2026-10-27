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
@Generated(from = "SelectionElement", generator = "Immutables")
public final class ImmutableSelectionElement extends SelectionElement {
    private final boolean isSelected;
    private final List<Method> onItemSelected;
    private final String primaryText;

    @Nullable
    private final String secondaryText;
    private final String selectedIcon;
    private final String unSelectedIcon;

    private ImmutableSelectionElement(Builder builder) {
        List<Method> listCreateUnmodifiableList;
        this.primaryText = builder.primaryText;
        this.secondaryText = builder.secondaryText;
        this.selectedIcon = builder.selectedIcon;
        this.unSelectedIcon = builder.unSelectedIcon;
        this.isSelected = builder.isSelected;
        if (!builder.onItemSelectedIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.onItemSelected(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.onItemSelected);
        }
        this.onItemSelected = listCreateUnmodifiableList;
    }

    private ImmutableSelectionElement(String str, @Nullable String str2, String str3, String str4, boolean z, List<Method> list) {
        this.primaryText = str;
        this.secondaryText = str2;
        this.selectedIcon = str3;
        this.unSelectedIcon = str4;
        this.isSelected = z;
        this.onItemSelected = list;
    }

    @Override // Touch.WidgetsInterface.v1_0.SelectionElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.SelectionElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    @Nullable
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.SelectionElement
    @JsonProperty("selectedIcon")
    public String selectedIcon() {
        return this.selectedIcon;
    }

    @Override // Touch.WidgetsInterface.v1_0.SelectionElement
    @JsonProperty("unSelectedIcon")
    public String unSelectedIcon() {
        return this.unSelectedIcon;
    }

    @Override // Touch.WidgetsInterface.v1_0.SelectionElement
    @JsonProperty("isSelected")
    public boolean isSelected() {
        return this.isSelected;
    }

    @Override // Touch.WidgetsInterface.v1_0.SelectionElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableSelectionElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableSelectionElement(str2, this.secondaryText, this.selectedIcon, this.unSelectedIcon, this.isSelected, this.onItemSelected);
    }

    public final ImmutableSelectionElement withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutableSelectionElement(this.primaryText, str, this.selectedIcon, this.unSelectedIcon, this.isSelected, this.onItemSelected);
    }

    public final ImmutableSelectionElement withSelectedIcon(String str) {
        String str2 = (String) requireNonNull(str, "selectedIcon");
        return this.selectedIcon.equals(str2) ? this : new ImmutableSelectionElement(this.primaryText, this.secondaryText, str2, this.unSelectedIcon, this.isSelected, this.onItemSelected);
    }

    public final ImmutableSelectionElement withUnSelectedIcon(String str) {
        String str2 = (String) requireNonNull(str, "unSelectedIcon");
        return this.unSelectedIcon.equals(str2) ? this : new ImmutableSelectionElement(this.primaryText, this.secondaryText, this.selectedIcon, str2, this.isSelected, this.onItemSelected);
    }

    public final ImmutableSelectionElement withIsSelected(boolean z) {
        return this.isSelected == z ? this : new ImmutableSelectionElement(this.primaryText, this.secondaryText, this.selectedIcon, this.unSelectedIcon, z, this.onItemSelected);
    }

    public final ImmutableSelectionElement withOnItemSelected(Method... methodArr) {
        return new ImmutableSelectionElement(this.primaryText, this.secondaryText, this.selectedIcon, this.unSelectedIcon, this.isSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSelectionElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableSelectionElement(this.primaryText, this.secondaryText, this.selectedIcon, this.unSelectedIcon, this.isSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSelectionElement) && equalTo(0, (ImmutableSelectionElement) obj);
    }

    private boolean equalTo(int i, ImmutableSelectionElement immutableSelectionElement) {
        return this.primaryText.equals(immutableSelectionElement.primaryText) && equals(this.secondaryText, immutableSelectionElement.secondaryText) && this.selectedIcon.equals(immutableSelectionElement.selectedIcon) && this.unSelectedIcon.equals(immutableSelectionElement.unSelectedIcon) && this.isSelected == immutableSelectionElement.isSelected && this.onItemSelected.equals(immutableSelectionElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.primaryText.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.secondaryText);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.selectedIcon.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.unSelectedIcon.hashCode();
        int i = iHashCode4 + (iHashCode4 << 5) + (this.isSelected ? 1231 : 1237);
        return i + (i << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "SelectionElement{primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", selectedIcon=" + this.selectedIcon + ", unSelectedIcon=" + this.unSelectedIcon + ", isSelected=" + this.isSelected + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SelectionElement", generator = "Immutables")
    static final class Json extends SelectionElement {
        boolean isSelected;
        boolean isSelectedIsSet;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();
        boolean onItemSelectedIsSet;

        @Nullable
        String primaryText;

        @Nullable
        String secondaryText;

        @Nullable
        String selectedIcon;

        @Nullable
        String unSelectedIcon;

        Json() {
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public void setPrimaryText(String str) {
            this.primaryText = str;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public void setSecondaryText(@Nullable String str) {
            this.secondaryText = str;
        }

        @JsonProperty("selectedIcon")
        public void setSelectedIcon(String str) {
            this.selectedIcon = str;
        }

        @JsonProperty("unSelectedIcon")
        public void setUnSelectedIcon(String str) {
            this.unSelectedIcon = str;
        }

        @JsonProperty("isSelected")
        public void setIsSelected(boolean z) {
            this.isSelected = z;
            this.isSelectedIsSet = true;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.SelectionElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SelectionElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SelectionElement
        public String selectedIcon() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SelectionElement
        public String unSelectedIcon() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SelectionElement
        public boolean isSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SelectionElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSelectionElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.primaryText;
        if (str != null) {
            builder.primaryText(str);
        }
        String str2 = json.secondaryText;
        if (str2 != null) {
            builder.secondaryText(str2);
        }
        String str3 = json.selectedIcon;
        if (str3 != null) {
            builder.selectedIcon(str3);
        }
        String str4 = json.unSelectedIcon;
        if (str4 != null) {
            builder.unSelectedIcon(str4);
        }
        if (json.isSelectedIsSet) {
            builder.isSelected(json.isSelected);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        return builder.build();
    }

    public static ImmutableSelectionElement copyOf(SelectionElement selectionElement) {
        if (selectionElement instanceof ImmutableSelectionElement) {
            return (ImmutableSelectionElement) selectionElement;
        }
        return builder().from(selectionElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SelectionElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_IS_SELECTED = 8;
        private static final long INIT_BIT_PRIMARY_TEXT = 1;
        private static final long INIT_BIT_SELECTED_ICON = 2;
        private static final long INIT_BIT_UN_SELECTED_ICON = 4;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 1;
        private long initBits;
        private boolean isSelected;
        private List<Method> onItemSelected;
        private long optBits;

        @Nullable
        private String primaryText;

        @Nullable
        private String secondaryText;

        @Nullable
        private String selectedIcon;

        @Nullable
        private String unSelectedIcon;

        private Builder() {
            this.initBits = 15L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(SelectionElement selectionElement) {
            ImmutableSelectionElement.requireNonNull(selectionElement, "instance");
            primaryText(selectionElement.primaryText());
            String strSecondaryText = selectionElement.secondaryText();
            if (strSecondaryText != null) {
                secondaryText(strSecondaryText);
            }
            selectedIcon(selectionElement.selectedIcon());
            unSelectedIcon(selectionElement.unSelectedIcon());
            isSelected(selectionElement.isSelected());
            addAllOnItemSelected(selectionElement.onItemSelected());
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableSelectionElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public final Builder secondaryText(@Nullable String str) {
            this.secondaryText = str;
            return this;
        }

        @JsonProperty("selectedIcon")
        public final Builder selectedIcon(String str) {
            this.selectedIcon = (String) ImmutableSelectionElement.requireNonNull(str, "selectedIcon");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("unSelectedIcon")
        public final Builder unSelectedIcon(String str) {
            this.unSelectedIcon = (String) ImmutableSelectionElement.requireNonNull(str, "unSelectedIcon");
            this.initBits &= -5;
            return this;
        }

        @JsonProperty("isSelected")
        public final Builder isSelected(boolean z) {
            this.isSelected = z;
            this.initBits &= -9;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableSelectionElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableSelectionElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableSelectionElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableSelectionElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSelectionElement(this);
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
                arrayList.add("selectedIcon");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("unSelectedIcon");
            }
            if ((this.initBits & 8) != 0) {
                arrayList.add("isSelected");
            }
            return "Cannot build SelectionElement, some of required attributes are not set " + arrayList;
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
