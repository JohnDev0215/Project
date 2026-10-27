package PlaybackInterface.v1_0;

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
@Generated(from = "ScrubControlStateElement", generator = "Immutables")
public final class ImmutableScrubControlStateElement extends ScrubControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledScrubControlSelected;
    private final List<Method> onEnabledScrubControlSelected;

    private ImmutableScrubControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledScrubControlSelectedIsSet()) {
            this.initShim.onEnabledScrubControlSelected(createUnmodifiableList(true, builder.onEnabledScrubControlSelected));
        }
        if (builder.onDisabledScrubControlSelectedIsSet()) {
            this.initShim.onDisabledScrubControlSelected(createUnmodifiableList(true, builder.onDisabledScrubControlSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledScrubControlSelected = this.initShim.onEnabledScrubControlSelected();
        this.onDisabledScrubControlSelected = this.initShim.onDisabledScrubControlSelected();
        this.initShim = null;
    }

    private ImmutableScrubControlStateElement(Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.isDisabled = bool;
        this.onEnabledScrubControlSelected = list;
        this.onDisabledScrubControlSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "ScrubControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledScrubControlSelected;
        private byte onDisabledScrubControlSelectedBuildStage;
        private List<Method> onEnabledScrubControlSelected;
        private byte onEnabledScrubControlSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledScrubControlSelectedBuildStage = (byte) 0;
            this.onDisabledScrubControlSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableScrubControlStateElement.requireNonNull(ImmutableScrubControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledScrubControlSelected() {
            byte b = this.onEnabledScrubControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledScrubControlSelectedBuildStage = (byte) -1;
                this.onEnabledScrubControlSelected = ImmutableScrubControlStateElement.createUnmodifiableList(false, ImmutableScrubControlStateElement.createSafeList(ImmutableScrubControlStateElement.super.onEnabledScrubControlSelected(), true, false));
                this.onEnabledScrubControlSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledScrubControlSelected;
        }

        void onEnabledScrubControlSelected(List<Method> list) {
            this.onEnabledScrubControlSelected = list;
            this.onEnabledScrubControlSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledScrubControlSelected() {
            byte b = this.onDisabledScrubControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledScrubControlSelectedBuildStage = (byte) -1;
                this.onDisabledScrubControlSelected = ImmutableScrubControlStateElement.createUnmodifiableList(false, ImmutableScrubControlStateElement.createSafeList(ImmutableScrubControlStateElement.super.onDisabledScrubControlSelected(), true, false));
                this.onDisabledScrubControlSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledScrubControlSelected;
        }

        void onDisabledScrubControlSelected(List<Method> list) {
            this.onDisabledScrubControlSelected = list;
            this.onDisabledScrubControlSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledScrubControlSelectedBuildStage == -1) {
                arrayList.add("onEnabledScrubControlSelected");
            }
            if (this.onDisabledScrubControlSelectedBuildStage == -1) {
                arrayList.add("onDisabledScrubControlSelected");
            }
            return "Cannot build ScrubControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.ScrubControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.ScrubControlStateElement
    @JsonProperty("onEnabledScrubControlSelected")
    public List<Method> onEnabledScrubControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledScrubControlSelected();
        }
        return this.onEnabledScrubControlSelected;
    }

    @Override // PlaybackInterface.v1_0.ScrubControlStateElement
    @JsonProperty("onDisabledScrubControlSelected")
    public List<Method> onDisabledScrubControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledScrubControlSelected();
        }
        return this.onDisabledScrubControlSelected;
    }

    public final ImmutableScrubControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableScrubControlStateElement(bool2, this.onEnabledScrubControlSelected, this.onDisabledScrubControlSelected);
    }

    public final ImmutableScrubControlStateElement withOnEnabledScrubControlSelected(Method... methodArr) {
        return new ImmutableScrubControlStateElement(this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledScrubControlSelected);
    }

    public final ImmutableScrubControlStateElement withOnEnabledScrubControlSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledScrubControlSelected == iterable) {
            return this;
        }
        return new ImmutableScrubControlStateElement(this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledScrubControlSelected);
    }

    public final ImmutableScrubControlStateElement withOnDisabledScrubControlSelected(Method... methodArr) {
        return new ImmutableScrubControlStateElement(this.isDisabled, this.onEnabledScrubControlSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableScrubControlStateElement withOnDisabledScrubControlSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledScrubControlSelected == iterable) {
            return this;
        }
        return new ImmutableScrubControlStateElement(this.isDisabled, this.onEnabledScrubControlSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableScrubControlStateElement) && equalTo(0, (ImmutableScrubControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableScrubControlStateElement immutableScrubControlStateElement) {
        return this.isDisabled.equals(immutableScrubControlStateElement.isDisabled) && this.onEnabledScrubControlSelected.equals(immutableScrubControlStateElement.onEnabledScrubControlSelected) && this.onDisabledScrubControlSelected.equals(immutableScrubControlStateElement.onDisabledScrubControlSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.isDisabled.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onEnabledScrubControlSelected.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.onDisabledScrubControlSelected.hashCode();
    }

    public String toString() {
        return "ScrubControlStateElement{isDisabled=" + this.isDisabled + ", onEnabledScrubControlSelected=" + this.onEnabledScrubControlSelected + ", onDisabledScrubControlSelected=" + this.onDisabledScrubControlSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ScrubControlStateElement", generator = "Immutables")
    static final class Json extends ScrubControlStateElement {

        @Nullable
        Boolean isDisabled;
        boolean onDisabledScrubControlSelectedIsSet;
        boolean onEnabledScrubControlSelectedIsSet;

        @Nullable
        List<Method> onEnabledScrubControlSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledScrubControlSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(Boolean bool) {
            this.isDisabled = bool;
        }

        @JsonProperty("onEnabledScrubControlSelected")
        public void setOnEnabledScrubControlSelected(List<Method> list) {
            this.onEnabledScrubControlSelected = list;
            this.onEnabledScrubControlSelectedIsSet = true;
        }

        @JsonProperty("onDisabledScrubControlSelected")
        public void setOnDisabledScrubControlSelected(List<Method> list) {
            this.onDisabledScrubControlSelected = list;
            this.onDisabledScrubControlSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.ScrubControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ScrubControlStateElement
        public List<Method> onEnabledScrubControlSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ScrubControlStateElement
        public List<Method> onDisabledScrubControlSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableScrubControlStateElement fromJson(Json json) {
        Builder builder = builder();
        Boolean bool = json.isDisabled;
        if (bool != null) {
            builder.isDisabled(bool);
        }
        if (json.onEnabledScrubControlSelectedIsSet) {
            builder.addAllOnEnabledScrubControlSelected(json.onEnabledScrubControlSelected);
        }
        if (json.onDisabledScrubControlSelectedIsSet) {
            builder.addAllOnDisabledScrubControlSelected(json.onDisabledScrubControlSelected);
        }
        return builder.build();
    }

    public static ImmutableScrubControlStateElement copyOf(ScrubControlStateElement scrubControlStateElement) {
        if (scrubControlStateElement instanceof ImmutableScrubControlStateElement) {
            return (ImmutableScrubControlStateElement) scrubControlStateElement;
        }
        return builder().from(scrubControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ScrubControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_DISABLED_SCRUB_CONTROL_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_SCRUB_CONTROL_SELECTED = 1;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledScrubControlSelected;
        private List<Method> onEnabledScrubControlSelected;
        private long optBits;

        private Builder() {
            this.onEnabledScrubControlSelected = new ArrayList();
            this.onDisabledScrubControlSelected = new ArrayList();
        }

        public final Builder from(ScrubControlStateElement scrubControlStateElement) {
            ImmutableScrubControlStateElement.requireNonNull(scrubControlStateElement, "instance");
            isDisabled(scrubControlStateElement.isDisabled());
            addAllOnEnabledScrubControlSelected(scrubControlStateElement.onEnabledScrubControlSelected());
            addAllOnDisabledScrubControlSelected(scrubControlStateElement.onDisabledScrubControlSelected());
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableScrubControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledScrubControlSelected(Method method) {
            this.onEnabledScrubControlSelected.add((Method) ImmutableScrubControlStateElement.requireNonNull(method, "onEnabledScrubControlSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledScrubControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledScrubControlSelected.add((Method) ImmutableScrubControlStateElement.requireNonNull(method, "onEnabledScrubControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledScrubControlSelected")
        public final Builder onEnabledScrubControlSelected(Iterable<? extends Method> iterable) {
            this.onEnabledScrubControlSelected.clear();
            return addAllOnEnabledScrubControlSelected(iterable);
        }

        public final Builder addAllOnEnabledScrubControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledScrubControlSelected.add((Method) ImmutableScrubControlStateElement.requireNonNull(it.next(), "onEnabledScrubControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledScrubControlSelected(Method method) {
            this.onDisabledScrubControlSelected.add((Method) ImmutableScrubControlStateElement.requireNonNull(method, "onDisabledScrubControlSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledScrubControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledScrubControlSelected.add((Method) ImmutableScrubControlStateElement.requireNonNull(method, "onDisabledScrubControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledScrubControlSelected")
        public final Builder onDisabledScrubControlSelected(Iterable<? extends Method> iterable) {
            this.onDisabledScrubControlSelected.clear();
            return addAllOnDisabledScrubControlSelected(iterable);
        }

        public final Builder addAllOnDisabledScrubControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledScrubControlSelected.add((Method) ImmutableScrubControlStateElement.requireNonNull(it.next(), "onDisabledScrubControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableScrubControlStateElement build() {
            return new ImmutableScrubControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledScrubControlSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledScrubControlSelectedIsSet() {
            return (this.optBits & 2) != 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
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
