package Touch.WidgetsInterface.v1_0;

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
@Generated(from = "NavigationBarElement", generator = "Immutables")
public final class ImmutableNavigationBarElement extends NavigationBarElement {
    private final List<ButtonElement> buttons;

    @Nullable
    private final ButtonElement callToActionButton;
    private final String primaryHeader;

    @Nullable
    private final RefinementCollectionElement refinementCollection;

    @Nullable
    private final SearchBarElement searchBarElement;

    @Nullable
    private final String secondaryHeader;

    private ImmutableNavigationBarElement(Builder builder) {
        List<ButtonElement> listCreateUnmodifiableList;
        this.primaryHeader = builder.primaryHeader;
        this.secondaryHeader = builder.secondaryHeader;
        this.callToActionButton = builder.callToActionButton;
        this.refinementCollection = builder.refinementCollection;
        this.searchBarElement = builder.searchBarElement;
        if (!builder.buttonsIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.buttons(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.buttons);
        }
        this.buttons = listCreateUnmodifiableList;
    }

    private ImmutableNavigationBarElement(String str, @Nullable String str2, @Nullable ButtonElement buttonElement, @Nullable RefinementCollectionElement refinementCollectionElement, List<ButtonElement> list, @Nullable SearchBarElement searchBarElement) {
        this.primaryHeader = str;
        this.secondaryHeader = str2;
        this.callToActionButton = buttonElement;
        this.refinementCollection = refinementCollectionElement;
        this.buttons = list;
        this.searchBarElement = searchBarElement;
    }

    @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
    @JsonProperty("primaryHeader")
    public String primaryHeader() {
        return this.primaryHeader;
    }

    @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
    @JsonProperty("secondaryHeader")
    @Nullable
    public String secondaryHeader() {
        return this.secondaryHeader;
    }

    @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
    @JsonProperty("callToActionButton")
    @Nullable
    public ButtonElement callToActionButton() {
        return this.callToActionButton;
    }

    @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
    @JsonProperty("refinementCollection")
    @Nullable
    public RefinementCollectionElement refinementCollection() {
        return this.refinementCollection;
    }

    @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
    @JsonProperty("buttons")
    public List<ButtonElement> buttons() {
        return this.buttons;
    }

    @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
    @JsonProperty("searchBarElement")
    @Nullable
    public SearchBarElement searchBarElement() {
        return this.searchBarElement;
    }

    public final ImmutableNavigationBarElement withPrimaryHeader(String str) {
        String str2 = (String) requireNonNull(str, "primaryHeader");
        return this.primaryHeader.equals(str2) ? this : new ImmutableNavigationBarElement(str2, this.secondaryHeader, this.callToActionButton, this.refinementCollection, this.buttons, this.searchBarElement);
    }

    public final ImmutableNavigationBarElement withSecondaryHeader(@Nullable String str) {
        return equals(this.secondaryHeader, str) ? this : new ImmutableNavigationBarElement(this.primaryHeader, str, this.callToActionButton, this.refinementCollection, this.buttons, this.searchBarElement);
    }

    public final ImmutableNavigationBarElement withCallToActionButton(@Nullable ButtonElement buttonElement) {
        return this.callToActionButton == buttonElement ? this : new ImmutableNavigationBarElement(this.primaryHeader, this.secondaryHeader, buttonElement, this.refinementCollection, this.buttons, this.searchBarElement);
    }

    public final ImmutableNavigationBarElement withRefinementCollection(@Nullable RefinementCollectionElement refinementCollectionElement) {
        return this.refinementCollection == refinementCollectionElement ? this : new ImmutableNavigationBarElement(this.primaryHeader, this.secondaryHeader, this.callToActionButton, refinementCollectionElement, this.buttons, this.searchBarElement);
    }

    public final ImmutableNavigationBarElement withButtons(ButtonElement... buttonElementArr) {
        return new ImmutableNavigationBarElement(this.primaryHeader, this.secondaryHeader, this.callToActionButton, this.refinementCollection, createUnmodifiableList(false, createSafeList(Arrays.asList(buttonElementArr), true, false)), this.searchBarElement);
    }

    public final ImmutableNavigationBarElement withButtons(Iterable<? extends ButtonElement> iterable) {
        if (this.buttons == iterable) {
            return this;
        }
        return new ImmutableNavigationBarElement(this.primaryHeader, this.secondaryHeader, this.callToActionButton, this.refinementCollection, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.searchBarElement);
    }

    public final ImmutableNavigationBarElement withSearchBarElement(@Nullable SearchBarElement searchBarElement) {
        return this.searchBarElement == searchBarElement ? this : new ImmutableNavigationBarElement(this.primaryHeader, this.secondaryHeader, this.callToActionButton, this.refinementCollection, this.buttons, searchBarElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableNavigationBarElement) && equalTo(0, (ImmutableNavigationBarElement) obj);
    }

    private boolean equalTo(int i, ImmutableNavigationBarElement immutableNavigationBarElement) {
        return this.primaryHeader.equals(immutableNavigationBarElement.primaryHeader) && equals(this.secondaryHeader, immutableNavigationBarElement.secondaryHeader) && equals(this.callToActionButton, immutableNavigationBarElement.callToActionButton) && equals(this.refinementCollection, immutableNavigationBarElement.refinementCollection) && this.buttons.equals(immutableNavigationBarElement.buttons) && equals(this.searchBarElement, immutableNavigationBarElement.searchBarElement);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.primaryHeader.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.secondaryHeader);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.callToActionButton);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.refinementCollection);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.buttons.hashCode();
        return iHashCode5 + (iHashCode5 << 5) + hashCode(this.searchBarElement);
    }

    public String toString() {
        return "NavigationBarElement{primaryHeader=" + this.primaryHeader + ", secondaryHeader=" + this.secondaryHeader + ", callToActionButton=" + this.callToActionButton + ", refinementCollection=" + this.refinementCollection + ", buttons=" + this.buttons + ", searchBarElement=" + this.searchBarElement + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "NavigationBarElement", generator = "Immutables")
    static final class Json extends NavigationBarElement {

        @Nullable
        List<ButtonElement> buttons = Collections.emptyList();
        boolean buttonsIsSet;

        @Nullable
        ButtonElement callToActionButton;

        @Nullable
        String primaryHeader;

        @Nullable
        RefinementCollectionElement refinementCollection;

        @Nullable
        SearchBarElement searchBarElement;

        @Nullable
        String secondaryHeader;

        Json() {
        }

        @JsonProperty("primaryHeader")
        public void setPrimaryHeader(String str) {
            this.primaryHeader = str;
        }

        @JsonProperty("secondaryHeader")
        public void setSecondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
        }

        @JsonProperty("callToActionButton")
        public void setCallToActionButton(@Nullable ButtonElement buttonElement) {
            this.callToActionButton = buttonElement;
        }

        @JsonProperty("refinementCollection")
        public void setRefinementCollection(@Nullable RefinementCollectionElement refinementCollectionElement) {
            this.refinementCollection = refinementCollectionElement;
        }

        @JsonProperty("buttons")
        public void setButtons(List<ButtonElement> list) {
            this.buttons = list;
            this.buttonsIsSet = true;
        }

        @JsonProperty("searchBarElement")
        public void setSearchBarElement(@Nullable SearchBarElement searchBarElement) {
            this.searchBarElement = searchBarElement;
        }

        @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
        public String primaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
        public String secondaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
        public ButtonElement callToActionButton() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
        public RefinementCollectionElement refinementCollection() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
        public List<ButtonElement> buttons() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.NavigationBarElement
        public SearchBarElement searchBarElement() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableNavigationBarElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.primaryHeader;
        if (str != null) {
            builder.primaryHeader(str);
        }
        String str2 = json.secondaryHeader;
        if (str2 != null) {
            builder.secondaryHeader(str2);
        }
        ButtonElement buttonElement = json.callToActionButton;
        if (buttonElement != null) {
            builder.callToActionButton(buttonElement);
        }
        RefinementCollectionElement refinementCollectionElement = json.refinementCollection;
        if (refinementCollectionElement != null) {
            builder.refinementCollection(refinementCollectionElement);
        }
        if (json.buttonsIsSet) {
            builder.addAllButtons(json.buttons);
        }
        SearchBarElement searchBarElement = json.searchBarElement;
        if (searchBarElement != null) {
            builder.searchBarElement(searchBarElement);
        }
        return builder.build();
    }

    public static ImmutableNavigationBarElement copyOf(NavigationBarElement navigationBarElement) {
        if (navigationBarElement instanceof ImmutableNavigationBarElement) {
            return (ImmutableNavigationBarElement) navigationBarElement;
        }
        return builder().from(navigationBarElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "NavigationBarElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_HEADER = 1;
        private static final long OPT_BIT_BUTTONS = 1;
        private List<ButtonElement> buttons;

        @Nullable
        private ButtonElement callToActionButton;
        private long initBits;
        private long optBits;

        @Nullable
        private String primaryHeader;

        @Nullable
        private RefinementCollectionElement refinementCollection;

        @Nullable
        private SearchBarElement searchBarElement;

        @Nullable
        private String secondaryHeader;

        private Builder() {
            this.initBits = 1L;
            this.buttons = new ArrayList();
        }

        public final Builder from(NavigationBarElement navigationBarElement) {
            ImmutableNavigationBarElement.requireNonNull(navigationBarElement, "instance");
            primaryHeader(navigationBarElement.primaryHeader());
            String strSecondaryHeader = navigationBarElement.secondaryHeader();
            if (strSecondaryHeader != null) {
                secondaryHeader(strSecondaryHeader);
            }
            ButtonElement buttonElementCallToActionButton = navigationBarElement.callToActionButton();
            if (buttonElementCallToActionButton != null) {
                callToActionButton(buttonElementCallToActionButton);
            }
            RefinementCollectionElement refinementCollectionElementRefinementCollection = navigationBarElement.refinementCollection();
            if (refinementCollectionElementRefinementCollection != null) {
                refinementCollection(refinementCollectionElementRefinementCollection);
            }
            addAllButtons(navigationBarElement.buttons());
            SearchBarElement searchBarElement = navigationBarElement.searchBarElement();
            if (searchBarElement != null) {
                searchBarElement(searchBarElement);
            }
            return this;
        }

        @JsonProperty("primaryHeader")
        public final Builder primaryHeader(String str) {
            this.primaryHeader = (String) ImmutableNavigationBarElement.requireNonNull(str, "primaryHeader");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("secondaryHeader")
        public final Builder secondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
            return this;
        }

        @JsonProperty("callToActionButton")
        public final Builder callToActionButton(@Nullable ButtonElement buttonElement) {
            this.callToActionButton = buttonElement;
            return this;
        }

        @JsonProperty("refinementCollection")
        public final Builder refinementCollection(@Nullable RefinementCollectionElement refinementCollectionElement) {
            this.refinementCollection = refinementCollectionElement;
            return this;
        }

        public final Builder addButtons(ButtonElement buttonElement) {
            this.buttons.add((ButtonElement) ImmutableNavigationBarElement.requireNonNull(buttonElement, "buttons element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addButtons(ButtonElement... buttonElementArr) {
            for (ButtonElement buttonElement : buttonElementArr) {
                this.buttons.add((ButtonElement) ImmutableNavigationBarElement.requireNonNull(buttonElement, "buttons element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("buttons")
        public final Builder buttons(Iterable<? extends ButtonElement> iterable) {
            this.buttons.clear();
            return addAllButtons(iterable);
        }

        public final Builder addAllButtons(Iterable<? extends ButtonElement> iterable) {
            Iterator<? extends ButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.buttons.add((ButtonElement) ImmutableNavigationBarElement.requireNonNull(it.next(), "buttons element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("searchBarElement")
        public final Builder searchBarElement(@Nullable SearchBarElement searchBarElement) {
            this.searchBarElement = searchBarElement;
            return this;
        }

        public ImmutableNavigationBarElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableNavigationBarElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean buttonsIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("primaryHeader");
            }
            return "Cannot build NavigationBarElement, some of required attributes are not set " + arrayList;
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
