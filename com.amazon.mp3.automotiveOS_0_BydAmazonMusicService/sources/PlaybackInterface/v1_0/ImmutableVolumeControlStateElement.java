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
@Generated(from = "VolumeControlStateElement", generator = "Immutables")
public final class ImmutableVolumeControlStateElement extends VolumeControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledVolumeControlSelected;
    private final List<Method> onEnabledVolumeControlSelected;

    private ImmutableVolumeControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledVolumeControlSelectedIsSet()) {
            this.initShim.onEnabledVolumeControlSelected(createUnmodifiableList(true, builder.onEnabledVolumeControlSelected));
        }
        if (builder.onDisabledVolumeControlSelectedIsSet()) {
            this.initShim.onDisabledVolumeControlSelected(createUnmodifiableList(true, builder.onDisabledVolumeControlSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledVolumeControlSelected = this.initShim.onEnabledVolumeControlSelected();
        this.onDisabledVolumeControlSelected = this.initShim.onDisabledVolumeControlSelected();
        this.initShim = null;
    }

    private ImmutableVolumeControlStateElement(Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.isDisabled = bool;
        this.onEnabledVolumeControlSelected = list;
        this.onDisabledVolumeControlSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "VolumeControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledVolumeControlSelected;
        private byte onDisabledVolumeControlSelectedBuildStage;
        private List<Method> onEnabledVolumeControlSelected;
        private byte onEnabledVolumeControlSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledVolumeControlSelectedBuildStage = (byte) 0;
            this.onDisabledVolumeControlSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableVolumeControlStateElement.requireNonNull(ImmutableVolumeControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledVolumeControlSelected() {
            byte b = this.onEnabledVolumeControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledVolumeControlSelectedBuildStage = (byte) -1;
                this.onEnabledVolumeControlSelected = ImmutableVolumeControlStateElement.createUnmodifiableList(false, ImmutableVolumeControlStateElement.createSafeList(ImmutableVolumeControlStateElement.super.onEnabledVolumeControlSelected(), true, false));
                this.onEnabledVolumeControlSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledVolumeControlSelected;
        }

        void onEnabledVolumeControlSelected(List<Method> list) {
            this.onEnabledVolumeControlSelected = list;
            this.onEnabledVolumeControlSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledVolumeControlSelected() {
            byte b = this.onDisabledVolumeControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledVolumeControlSelectedBuildStage = (byte) -1;
                this.onDisabledVolumeControlSelected = ImmutableVolumeControlStateElement.createUnmodifiableList(false, ImmutableVolumeControlStateElement.createSafeList(ImmutableVolumeControlStateElement.super.onDisabledVolumeControlSelected(), true, false));
                this.onDisabledVolumeControlSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledVolumeControlSelected;
        }

        void onDisabledVolumeControlSelected(List<Method> list) {
            this.onDisabledVolumeControlSelected = list;
            this.onDisabledVolumeControlSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledVolumeControlSelectedBuildStage == -1) {
                arrayList.add("onEnabledVolumeControlSelected");
            }
            if (this.onDisabledVolumeControlSelectedBuildStage == -1) {
                arrayList.add("onDisabledVolumeControlSelected");
            }
            return "Cannot build VolumeControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.VolumeControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.VolumeControlStateElement
    @JsonProperty("onEnabledVolumeControlSelected")
    public List<Method> onEnabledVolumeControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledVolumeControlSelected();
        }
        return this.onEnabledVolumeControlSelected;
    }

    @Override // PlaybackInterface.v1_0.VolumeControlStateElement
    @JsonProperty("onDisabledVolumeControlSelected")
    public List<Method> onDisabledVolumeControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledVolumeControlSelected();
        }
        return this.onDisabledVolumeControlSelected;
    }

    public final ImmutableVolumeControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableVolumeControlStateElement(bool2, this.onEnabledVolumeControlSelected, this.onDisabledVolumeControlSelected);
    }

    public final ImmutableVolumeControlStateElement withOnEnabledVolumeControlSelected(Method... methodArr) {
        return new ImmutableVolumeControlStateElement(this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledVolumeControlSelected);
    }

    public final ImmutableVolumeControlStateElement withOnEnabledVolumeControlSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledVolumeControlSelected == iterable) {
            return this;
        }
        return new ImmutableVolumeControlStateElement(this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledVolumeControlSelected);
    }

    public final ImmutableVolumeControlStateElement withOnDisabledVolumeControlSelected(Method... methodArr) {
        return new ImmutableVolumeControlStateElement(this.isDisabled, this.onEnabledVolumeControlSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableVolumeControlStateElement withOnDisabledVolumeControlSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledVolumeControlSelected == iterable) {
            return this;
        }
        return new ImmutableVolumeControlStateElement(this.isDisabled, this.onEnabledVolumeControlSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVolumeControlStateElement) && equalTo(0, (ImmutableVolumeControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableVolumeControlStateElement immutableVolumeControlStateElement) {
        return this.isDisabled.equals(immutableVolumeControlStateElement.isDisabled) && this.onEnabledVolumeControlSelected.equals(immutableVolumeControlStateElement.onEnabledVolumeControlSelected) && this.onDisabledVolumeControlSelected.equals(immutableVolumeControlStateElement.onDisabledVolumeControlSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.isDisabled.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onEnabledVolumeControlSelected.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.onDisabledVolumeControlSelected.hashCode();
    }

    public String toString() {
        return "VolumeControlStateElement{isDisabled=" + this.isDisabled + ", onEnabledVolumeControlSelected=" + this.onEnabledVolumeControlSelected + ", onDisabledVolumeControlSelected=" + this.onDisabledVolumeControlSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VolumeControlStateElement", generator = "Immutables")
    static final class Json extends VolumeControlStateElement {

        @Nullable
        Boolean isDisabled;
        boolean onDisabledVolumeControlSelectedIsSet;
        boolean onEnabledVolumeControlSelectedIsSet;

        @Nullable
        List<Method> onEnabledVolumeControlSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledVolumeControlSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(Boolean bool) {
            this.isDisabled = bool;
        }

        @JsonProperty("onEnabledVolumeControlSelected")
        public void setOnEnabledVolumeControlSelected(List<Method> list) {
            this.onEnabledVolumeControlSelected = list;
            this.onEnabledVolumeControlSelectedIsSet = true;
        }

        @JsonProperty("onDisabledVolumeControlSelected")
        public void setOnDisabledVolumeControlSelected(List<Method> list) {
            this.onDisabledVolumeControlSelected = list;
            this.onDisabledVolumeControlSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.VolumeControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.VolumeControlStateElement
        public List<Method> onEnabledVolumeControlSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.VolumeControlStateElement
        public List<Method> onDisabledVolumeControlSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVolumeControlStateElement fromJson(Json json) {
        Builder builder = builder();
        Boolean bool = json.isDisabled;
        if (bool != null) {
            builder.isDisabled(bool);
        }
        if (json.onEnabledVolumeControlSelectedIsSet) {
            builder.addAllOnEnabledVolumeControlSelected(json.onEnabledVolumeControlSelected);
        }
        if (json.onDisabledVolumeControlSelectedIsSet) {
            builder.addAllOnDisabledVolumeControlSelected(json.onDisabledVolumeControlSelected);
        }
        return builder.build();
    }

    public static ImmutableVolumeControlStateElement copyOf(VolumeControlStateElement volumeControlStateElement) {
        if (volumeControlStateElement instanceof ImmutableVolumeControlStateElement) {
            return (ImmutableVolumeControlStateElement) volumeControlStateElement;
        }
        return builder().from(volumeControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VolumeControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_DISABLED_VOLUME_CONTROL_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_VOLUME_CONTROL_SELECTED = 1;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledVolumeControlSelected;
        private List<Method> onEnabledVolumeControlSelected;
        private long optBits;

        private Builder() {
            this.onEnabledVolumeControlSelected = new ArrayList();
            this.onDisabledVolumeControlSelected = new ArrayList();
        }

        public final Builder from(VolumeControlStateElement volumeControlStateElement) {
            ImmutableVolumeControlStateElement.requireNonNull(volumeControlStateElement, "instance");
            isDisabled(volumeControlStateElement.isDisabled());
            addAllOnEnabledVolumeControlSelected(volumeControlStateElement.onEnabledVolumeControlSelected());
            addAllOnDisabledVolumeControlSelected(volumeControlStateElement.onDisabledVolumeControlSelected());
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableVolumeControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledVolumeControlSelected(Method method) {
            this.onEnabledVolumeControlSelected.add((Method) ImmutableVolumeControlStateElement.requireNonNull(method, "onEnabledVolumeControlSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledVolumeControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledVolumeControlSelected.add((Method) ImmutableVolumeControlStateElement.requireNonNull(method, "onEnabledVolumeControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledVolumeControlSelected")
        public final Builder onEnabledVolumeControlSelected(Iterable<? extends Method> iterable) {
            this.onEnabledVolumeControlSelected.clear();
            return addAllOnEnabledVolumeControlSelected(iterable);
        }

        public final Builder addAllOnEnabledVolumeControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledVolumeControlSelected.add((Method) ImmutableVolumeControlStateElement.requireNonNull(it.next(), "onEnabledVolumeControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledVolumeControlSelected(Method method) {
            this.onDisabledVolumeControlSelected.add((Method) ImmutableVolumeControlStateElement.requireNonNull(method, "onDisabledVolumeControlSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledVolumeControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledVolumeControlSelected.add((Method) ImmutableVolumeControlStateElement.requireNonNull(method, "onDisabledVolumeControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledVolumeControlSelected")
        public final Builder onDisabledVolumeControlSelected(Iterable<? extends Method> iterable) {
            this.onDisabledVolumeControlSelected.clear();
            return addAllOnDisabledVolumeControlSelected(iterable);
        }

        public final Builder addAllOnDisabledVolumeControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledVolumeControlSelected.add((Method) ImmutableVolumeControlStateElement.requireNonNull(it.next(), "onDisabledVolumeControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableVolumeControlStateElement build() {
            return new ImmutableVolumeControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledVolumeControlSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledVolumeControlSelectedIsSet() {
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
