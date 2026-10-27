package Touch.WidgetsInterface.v1_0;

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
@Generated(from = "SearchBarElement", generator = "Immutables")
public final class ImmutableSearchBarElement extends SearchBarElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final AlexaButtonElement alexaButton;
    private volatile transient InitShim initShim;
    private final List<Method> onInputSelected;
    private final List<Method> onQueryChanged;
    private final List<Method> onQueryCleared;
    private final List<Method> onQueryEntered;

    @Nullable
    private final String queryHint;

    private ImmutableSearchBarElement(Builder builder) {
        this.initShim = new InitShim();
        this.queryHint = builder.queryHint;
        this.alexaButton = builder.alexaButton;
        if (builder.onInputSelectedIsSet()) {
            this.initShim.onInputSelected(createUnmodifiableList(true, builder.onInputSelected));
        }
        if (builder.onQueryChangedIsSet()) {
            this.initShim.onQueryChanged(createUnmodifiableList(true, builder.onQueryChanged));
        }
        if (builder.onQueryEnteredIsSet()) {
            this.initShim.onQueryEntered(createUnmodifiableList(true, builder.onQueryEntered));
        }
        if (builder.onQueryClearedIsSet()) {
            this.initShim.onQueryCleared(createUnmodifiableList(true, builder.onQueryCleared));
        }
        this.onInputSelected = this.initShim.onInputSelected();
        this.onQueryChanged = this.initShim.onQueryChanged();
        this.onQueryEntered = this.initShim.onQueryEntered();
        this.onQueryCleared = this.initShim.onQueryCleared();
        this.initShim = null;
    }

    private ImmutableSearchBarElement(List<Method> list, List<Method> list2, List<Method> list3, List<Method> list4, @Nullable String str, @Nullable AlexaButtonElement alexaButtonElement) {
        this.initShim = new InitShim();
        this.onInputSelected = list;
        this.onQueryChanged = list2;
        this.onQueryEntered = list3;
        this.onQueryCleared = list4;
        this.queryHint = str;
        this.alexaButton = alexaButtonElement;
        this.initShim = null;
    }

    @Generated(from = "SearchBarElement", generator = "Immutables")
    private final class InitShim {
        private List<Method> onInputSelected;
        private byte onInputSelectedBuildStage;
        private List<Method> onQueryChanged;
        private byte onQueryChangedBuildStage;
        private List<Method> onQueryCleared;
        private byte onQueryClearedBuildStage;
        private List<Method> onQueryEntered;
        private byte onQueryEnteredBuildStage;

        private InitShim() {
            this.onInputSelectedBuildStage = (byte) 0;
            this.onQueryChangedBuildStage = (byte) 0;
            this.onQueryEnteredBuildStage = (byte) 0;
            this.onQueryClearedBuildStage = (byte) 0;
        }

        List<Method> onInputSelected() {
            byte b = this.onInputSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onInputSelectedBuildStage = (byte) -1;
                this.onInputSelected = ImmutableSearchBarElement.createUnmodifiableList(false, ImmutableSearchBarElement.createSafeList(ImmutableSearchBarElement.super.onInputSelected(), true, false));
                this.onInputSelectedBuildStage = (byte) 1;
            }
            return this.onInputSelected;
        }

        void onInputSelected(List<Method> list) {
            this.onInputSelected = list;
            this.onInputSelectedBuildStage = (byte) 1;
        }

        List<Method> onQueryChanged() {
            byte b = this.onQueryChangedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onQueryChangedBuildStage = (byte) -1;
                this.onQueryChanged = ImmutableSearchBarElement.createUnmodifiableList(false, ImmutableSearchBarElement.createSafeList(ImmutableSearchBarElement.super.onQueryChanged(), true, false));
                this.onQueryChangedBuildStage = (byte) 1;
            }
            return this.onQueryChanged;
        }

        void onQueryChanged(List<Method> list) {
            this.onQueryChanged = list;
            this.onQueryChangedBuildStage = (byte) 1;
        }

        List<Method> onQueryEntered() {
            byte b = this.onQueryEnteredBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onQueryEnteredBuildStage = (byte) -1;
                this.onQueryEntered = ImmutableSearchBarElement.createUnmodifiableList(false, ImmutableSearchBarElement.createSafeList(ImmutableSearchBarElement.super.onQueryEntered(), true, false));
                this.onQueryEnteredBuildStage = (byte) 1;
            }
            return this.onQueryEntered;
        }

        void onQueryEntered(List<Method> list) {
            this.onQueryEntered = list;
            this.onQueryEnteredBuildStage = (byte) 1;
        }

        List<Method> onQueryCleared() {
            byte b = this.onQueryClearedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onQueryClearedBuildStage = (byte) -1;
                this.onQueryCleared = ImmutableSearchBarElement.createUnmodifiableList(false, ImmutableSearchBarElement.createSafeList(ImmutableSearchBarElement.super.onQueryCleared(), true, false));
                this.onQueryClearedBuildStage = (byte) 1;
            }
            return this.onQueryCleared;
        }

        void onQueryCleared(List<Method> list) {
            this.onQueryCleared = list;
            this.onQueryClearedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.onInputSelectedBuildStage == -1) {
                arrayList.add("onInputSelected");
            }
            if (this.onQueryChangedBuildStage == -1) {
                arrayList.add("onQueryChanged");
            }
            if (this.onQueryEnteredBuildStage == -1) {
                arrayList.add("onQueryEntered");
            }
            if (this.onQueryClearedBuildStage == -1) {
                arrayList.add("onQueryCleared");
            }
            return "Cannot build SearchBarElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
    @JsonProperty("onInputSelected")
    public List<Method> onInputSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onInputSelected();
        }
        return this.onInputSelected;
    }

    @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
    @JsonProperty("onQueryChanged")
    public List<Method> onQueryChanged() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onQueryChanged();
        }
        return this.onQueryChanged;
    }

    @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
    @JsonProperty("onQueryEntered")
    public List<Method> onQueryEntered() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onQueryEntered();
        }
        return this.onQueryEntered;
    }

    @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
    @JsonProperty("onQueryCleared")
    public List<Method> onQueryCleared() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onQueryCleared();
        }
        return this.onQueryCleared;
    }

    @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
    @JsonProperty("queryHint")
    @Nullable
    public String queryHint() {
        return this.queryHint;
    }

    @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
    @JsonProperty("alexaButton")
    @Nullable
    public AlexaButtonElement alexaButton() {
        return this.alexaButton;
    }

    public final ImmutableSearchBarElement withOnInputSelected(Method... methodArr) {
        return new ImmutableSearchBarElement(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onQueryChanged, this.onQueryEntered, this.onQueryCleared, this.queryHint, this.alexaButton);
    }

    public final ImmutableSearchBarElement withOnInputSelected(Iterable<? extends Method> iterable) {
        return this.onInputSelected == iterable ? this : new ImmutableSearchBarElement(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onQueryChanged, this.onQueryEntered, this.onQueryCleared, this.queryHint, this.alexaButton);
    }

    public final ImmutableSearchBarElement withOnQueryChanged(Method... methodArr) {
        return new ImmutableSearchBarElement(this.onInputSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onQueryEntered, this.onQueryCleared, this.queryHint, this.alexaButton);
    }

    public final ImmutableSearchBarElement withOnQueryChanged(Iterable<? extends Method> iterable) {
        if (this.onQueryChanged == iterable) {
            return this;
        }
        return new ImmutableSearchBarElement(this.onInputSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onQueryEntered, this.onQueryCleared, this.queryHint, this.alexaButton);
    }

    public final ImmutableSearchBarElement withOnQueryEntered(Method... methodArr) {
        return new ImmutableSearchBarElement(this.onInputSelected, this.onQueryChanged, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onQueryCleared, this.queryHint, this.alexaButton);
    }

    public final ImmutableSearchBarElement withOnQueryEntered(Iterable<? extends Method> iterable) {
        if (this.onQueryEntered == iterable) {
            return this;
        }
        return new ImmutableSearchBarElement(this.onInputSelected, this.onQueryChanged, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onQueryCleared, this.queryHint, this.alexaButton);
    }

    public final ImmutableSearchBarElement withOnQueryCleared(Method... methodArr) {
        return new ImmutableSearchBarElement(this.onInputSelected, this.onQueryChanged, this.onQueryEntered, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.queryHint, this.alexaButton);
    }

    public final ImmutableSearchBarElement withOnQueryCleared(Iterable<? extends Method> iterable) {
        if (this.onQueryCleared == iterable) {
            return this;
        }
        return new ImmutableSearchBarElement(this.onInputSelected, this.onQueryChanged, this.onQueryEntered, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.queryHint, this.alexaButton);
    }

    public final ImmutableSearchBarElement withQueryHint(@Nullable String str) {
        return equals(this.queryHint, str) ? this : new ImmutableSearchBarElement(this.onInputSelected, this.onQueryChanged, this.onQueryEntered, this.onQueryCleared, str, this.alexaButton);
    }

    public final ImmutableSearchBarElement withAlexaButton(@Nullable AlexaButtonElement alexaButtonElement) {
        return this.alexaButton == alexaButtonElement ? this : new ImmutableSearchBarElement(this.onInputSelected, this.onQueryChanged, this.onQueryEntered, this.onQueryCleared, this.queryHint, alexaButtonElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSearchBarElement) && equalTo(0, (ImmutableSearchBarElement) obj);
    }

    private boolean equalTo(int i, ImmutableSearchBarElement immutableSearchBarElement) {
        return this.onInputSelected.equals(immutableSearchBarElement.onInputSelected) && this.onQueryChanged.equals(immutableSearchBarElement.onQueryChanged) && this.onQueryEntered.equals(immutableSearchBarElement.onQueryEntered) && this.onQueryCleared.equals(immutableSearchBarElement.onQueryCleared) && equals(this.queryHint, immutableSearchBarElement.queryHint) && equals(this.alexaButton, immutableSearchBarElement.alexaButton);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onInputSelected.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onQueryChanged.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onQueryEntered.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onQueryCleared.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.queryHint);
        return iHashCode5 + (iHashCode5 << 5) + hashCode(this.alexaButton);
    }

    public String toString() {
        return "SearchBarElement{onInputSelected=" + this.onInputSelected + ", onQueryChanged=" + this.onQueryChanged + ", onQueryEntered=" + this.onQueryEntered + ", onQueryCleared=" + this.onQueryCleared + ", queryHint=" + this.queryHint + ", alexaButton=" + this.alexaButton + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SearchBarElement", generator = "Immutables")
    static final class Json extends SearchBarElement {

        @Nullable
        AlexaButtonElement alexaButton;
        boolean onInputSelectedIsSet;
        boolean onQueryChangedIsSet;
        boolean onQueryClearedIsSet;
        boolean onQueryEnteredIsSet;

        @Nullable
        String queryHint;

        @Nullable
        List<Method> onInputSelected = Collections.emptyList();

        @Nullable
        List<Method> onQueryChanged = Collections.emptyList();

        @Nullable
        List<Method> onQueryEntered = Collections.emptyList();

        @Nullable
        List<Method> onQueryCleared = Collections.emptyList();

        Json() {
        }

        @JsonProperty("onInputSelected")
        public void setOnInputSelected(List<Method> list) {
            this.onInputSelected = list;
            this.onInputSelectedIsSet = true;
        }

        @JsonProperty("onQueryChanged")
        public void setOnQueryChanged(List<Method> list) {
            this.onQueryChanged = list;
            this.onQueryChangedIsSet = true;
        }

        @JsonProperty("onQueryEntered")
        public void setOnQueryEntered(List<Method> list) {
            this.onQueryEntered = list;
            this.onQueryEnteredIsSet = true;
        }

        @JsonProperty("onQueryCleared")
        public void setOnQueryCleared(List<Method> list) {
            this.onQueryCleared = list;
            this.onQueryClearedIsSet = true;
        }

        @JsonProperty("queryHint")
        public void setQueryHint(@Nullable String str) {
            this.queryHint = str;
        }

        @JsonProperty("alexaButton")
        public void setAlexaButton(@Nullable AlexaButtonElement alexaButtonElement) {
            this.alexaButton = alexaButtonElement;
        }

        @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
        public List<Method> onInputSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
        public List<Method> onQueryChanged() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
        public List<Method> onQueryEntered() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
        public List<Method> onQueryCleared() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
        public String queryHint() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SearchBarElement
        public AlexaButtonElement alexaButton() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSearchBarElement fromJson(Json json) {
        Builder builder = builder();
        if (json.onInputSelectedIsSet) {
            builder.addAllOnInputSelected(json.onInputSelected);
        }
        if (json.onQueryChangedIsSet) {
            builder.addAllOnQueryChanged(json.onQueryChanged);
        }
        if (json.onQueryEnteredIsSet) {
            builder.addAllOnQueryEntered(json.onQueryEntered);
        }
        if (json.onQueryClearedIsSet) {
            builder.addAllOnQueryCleared(json.onQueryCleared);
        }
        String str = json.queryHint;
        if (str != null) {
            builder.queryHint(str);
        }
        AlexaButtonElement alexaButtonElement = json.alexaButton;
        if (alexaButtonElement != null) {
            builder.alexaButton(alexaButtonElement);
        }
        return builder.build();
    }

    public static ImmutableSearchBarElement copyOf(SearchBarElement searchBarElement) {
        if (searchBarElement instanceof ImmutableSearchBarElement) {
            return (ImmutableSearchBarElement) searchBarElement;
        }
        return builder().from(searchBarElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SearchBarElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_INPUT_SELECTED = 1;
        private static final long OPT_BIT_ON_QUERY_CHANGED = 2;
        private static final long OPT_BIT_ON_QUERY_CLEARED = 8;
        private static final long OPT_BIT_ON_QUERY_ENTERED = 4;

        @Nullable
        private AlexaButtonElement alexaButton;
        private List<Method> onInputSelected;
        private List<Method> onQueryChanged;
        private List<Method> onQueryCleared;
        private List<Method> onQueryEntered;
        private long optBits;

        @Nullable
        private String queryHint;

        private Builder() {
            this.onInputSelected = new ArrayList();
            this.onQueryChanged = new ArrayList();
            this.onQueryEntered = new ArrayList();
            this.onQueryCleared = new ArrayList();
        }

        public final Builder from(SearchBarElement searchBarElement) {
            ImmutableSearchBarElement.requireNonNull(searchBarElement, "instance");
            addAllOnInputSelected(searchBarElement.onInputSelected());
            addAllOnQueryChanged(searchBarElement.onQueryChanged());
            addAllOnQueryEntered(searchBarElement.onQueryEntered());
            addAllOnQueryCleared(searchBarElement.onQueryCleared());
            String strQueryHint = searchBarElement.queryHint();
            if (strQueryHint != null) {
                queryHint(strQueryHint);
            }
            AlexaButtonElement alexaButtonElementAlexaButton = searchBarElement.alexaButton();
            if (alexaButtonElementAlexaButton != null) {
                alexaButton(alexaButtonElementAlexaButton);
            }
            return this;
        }

        public final Builder addOnInputSelected(Method method) {
            this.onInputSelected.add((Method) ImmutableSearchBarElement.requireNonNull(method, "onInputSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnInputSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onInputSelected.add((Method) ImmutableSearchBarElement.requireNonNull(method, "onInputSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onInputSelected")
        public final Builder onInputSelected(Iterable<? extends Method> iterable) {
            this.onInputSelected.clear();
            return addAllOnInputSelected(iterable);
        }

        public final Builder addAllOnInputSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onInputSelected.add((Method) ImmutableSearchBarElement.requireNonNull(it.next(), "onInputSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnQueryChanged(Method method) {
            this.onQueryChanged.add((Method) ImmutableSearchBarElement.requireNonNull(method, "onQueryChanged element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnQueryChanged(Method... methodArr) {
            for (Method method : methodArr) {
                this.onQueryChanged.add((Method) ImmutableSearchBarElement.requireNonNull(method, "onQueryChanged element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onQueryChanged")
        public final Builder onQueryChanged(Iterable<? extends Method> iterable) {
            this.onQueryChanged.clear();
            return addAllOnQueryChanged(iterable);
        }

        public final Builder addAllOnQueryChanged(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onQueryChanged.add((Method) ImmutableSearchBarElement.requireNonNull(it.next(), "onQueryChanged element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnQueryEntered(Method method) {
            this.onQueryEntered.add((Method) ImmutableSearchBarElement.requireNonNull(method, "onQueryEntered element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnQueryEntered(Method... methodArr) {
            for (Method method : methodArr) {
                this.onQueryEntered.add((Method) ImmutableSearchBarElement.requireNonNull(method, "onQueryEntered element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("onQueryEntered")
        public final Builder onQueryEntered(Iterable<? extends Method> iterable) {
            this.onQueryEntered.clear();
            return addAllOnQueryEntered(iterable);
        }

        public final Builder addAllOnQueryEntered(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onQueryEntered.add((Method) ImmutableSearchBarElement.requireNonNull(it.next(), "onQueryEntered element"));
            }
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnQueryCleared(Method method) {
            this.onQueryCleared.add((Method) ImmutableSearchBarElement.requireNonNull(method, "onQueryCleared element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addOnQueryCleared(Method... methodArr) {
            for (Method method : methodArr) {
                this.onQueryCleared.add((Method) ImmutableSearchBarElement.requireNonNull(method, "onQueryCleared element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("onQueryCleared")
        public final Builder onQueryCleared(Iterable<? extends Method> iterable) {
            this.onQueryCleared.clear();
            return addAllOnQueryCleared(iterable);
        }

        public final Builder addAllOnQueryCleared(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onQueryCleared.add((Method) ImmutableSearchBarElement.requireNonNull(it.next(), "onQueryCleared element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("queryHint")
        public final Builder queryHint(@Nullable String str) {
            this.queryHint = str;
            return this;
        }

        @JsonProperty("alexaButton")
        public final Builder alexaButton(@Nullable AlexaButtonElement alexaButtonElement) {
            this.alexaButton = alexaButtonElement;
            return this;
        }

        public ImmutableSearchBarElement build() {
            return new ImmutableSearchBarElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onInputSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onQueryChangedIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onQueryEnteredIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onQueryClearedIsSet() {
            return (this.optBits & 8) != 0;
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

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
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
