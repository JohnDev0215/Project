package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.android.gms.cast.MediaTrack;
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
@Generated(from = "PreviousControlStateElement", generator = "Immutables")
public final class ImmutablePreviousControlStateElement extends PreviousControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String altText;
    private final String description;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledPreviousControlSelected;
    private final List<Method> onEnabledPreviousControlSelected;

    private ImmutablePreviousControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.description = builder.description;
        this.altText = builder.altText;
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledPreviousControlSelectedIsSet()) {
            this.initShim.onEnabledPreviousControlSelected(createUnmodifiableList(true, builder.onEnabledPreviousControlSelected));
        }
        if (builder.onDisabledPreviousControlSelectedIsSet()) {
            this.initShim.onDisabledPreviousControlSelected(createUnmodifiableList(true, builder.onDisabledPreviousControlSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledPreviousControlSelected = this.initShim.onEnabledPreviousControlSelected();
        this.onDisabledPreviousControlSelected = this.initShim.onDisabledPreviousControlSelected();
        this.initShim = null;
    }

    private ImmutablePreviousControlStateElement(String str, String str2, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.description = str;
        this.altText = str2;
        this.isDisabled = bool;
        this.onEnabledPreviousControlSelected = list;
        this.onDisabledPreviousControlSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "PreviousControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledPreviousControlSelected;
        private byte onDisabledPreviousControlSelectedBuildStage;
        private List<Method> onEnabledPreviousControlSelected;
        private byte onEnabledPreviousControlSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledPreviousControlSelectedBuildStage = (byte) 0;
            this.onDisabledPreviousControlSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutablePreviousControlStateElement.requireNonNull(ImmutablePreviousControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledPreviousControlSelected() {
            byte b = this.onEnabledPreviousControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledPreviousControlSelectedBuildStage = (byte) -1;
                this.onEnabledPreviousControlSelected = ImmutablePreviousControlStateElement.createUnmodifiableList(false, ImmutablePreviousControlStateElement.createSafeList(ImmutablePreviousControlStateElement.super.onEnabledPreviousControlSelected(), true, false));
                this.onEnabledPreviousControlSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledPreviousControlSelected;
        }

        void onEnabledPreviousControlSelected(List<Method> list) {
            this.onEnabledPreviousControlSelected = list;
            this.onEnabledPreviousControlSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledPreviousControlSelected() {
            byte b = this.onDisabledPreviousControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledPreviousControlSelectedBuildStage = (byte) -1;
                this.onDisabledPreviousControlSelected = ImmutablePreviousControlStateElement.createUnmodifiableList(false, ImmutablePreviousControlStateElement.createSafeList(ImmutablePreviousControlStateElement.super.onDisabledPreviousControlSelected(), true, false));
                this.onDisabledPreviousControlSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledPreviousControlSelected;
        }

        void onDisabledPreviousControlSelected(List<Method> list) {
            this.onDisabledPreviousControlSelected = list;
            this.onDisabledPreviousControlSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledPreviousControlSelectedBuildStage == -1) {
                arrayList.add("onEnabledPreviousControlSelected");
            }
            if (this.onDisabledPreviousControlSelectedBuildStage == -1) {
                arrayList.add("onDisabledPreviousControlSelected");
            }
            return "Cannot build PreviousControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.PreviousControlStateElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    public String description() {
        return this.description;
    }

    @Override // PlaybackInterface.v1_0.PreviousControlStateElement
    @JsonProperty("altText")
    public String altText() {
        return this.altText;
    }

    @Override // PlaybackInterface.v1_0.PreviousControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.PreviousControlStateElement
    @JsonProperty("onEnabledPreviousControlSelected")
    public List<Method> onEnabledPreviousControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledPreviousControlSelected();
        }
        return this.onEnabledPreviousControlSelected;
    }

    @Override // PlaybackInterface.v1_0.PreviousControlStateElement
    @JsonProperty("onDisabledPreviousControlSelected")
    public List<Method> onDisabledPreviousControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledPreviousControlSelected();
        }
        return this.onDisabledPreviousControlSelected;
    }

    public final ImmutablePreviousControlStateElement withDescription(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
        return this.description.equals(str2) ? this : new ImmutablePreviousControlStateElement(str2, this.altText, this.isDisabled, this.onEnabledPreviousControlSelected, this.onDisabledPreviousControlSelected);
    }

    public final ImmutablePreviousControlStateElement withAltText(String str) {
        String str2 = (String) requireNonNull(str, "altText");
        return this.altText.equals(str2) ? this : new ImmutablePreviousControlStateElement(this.description, str2, this.isDisabled, this.onEnabledPreviousControlSelected, this.onDisabledPreviousControlSelected);
    }

    public final ImmutablePreviousControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutablePreviousControlStateElement(this.description, this.altText, bool2, this.onEnabledPreviousControlSelected, this.onDisabledPreviousControlSelected);
    }

    public final ImmutablePreviousControlStateElement withOnEnabledPreviousControlSelected(Method... methodArr) {
        return new ImmutablePreviousControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledPreviousControlSelected);
    }

    public final ImmutablePreviousControlStateElement withOnEnabledPreviousControlSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledPreviousControlSelected == iterable) {
            return this;
        }
        return new ImmutablePreviousControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledPreviousControlSelected);
    }

    public final ImmutablePreviousControlStateElement withOnDisabledPreviousControlSelected(Method... methodArr) {
        return new ImmutablePreviousControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledPreviousControlSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutablePreviousControlStateElement withOnDisabledPreviousControlSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledPreviousControlSelected == iterable) {
            return this;
        }
        return new ImmutablePreviousControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledPreviousControlSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePreviousControlStateElement) && equalTo(0, (ImmutablePreviousControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutablePreviousControlStateElement immutablePreviousControlStateElement) {
        return this.description.equals(immutablePreviousControlStateElement.description) && this.altText.equals(immutablePreviousControlStateElement.altText) && this.isDisabled.equals(immutablePreviousControlStateElement.isDisabled) && this.onEnabledPreviousControlSelected.equals(immutablePreviousControlStateElement.onEnabledPreviousControlSelected) && this.onDisabledPreviousControlSelected.equals(immutablePreviousControlStateElement.onDisabledPreviousControlSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.description.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altText.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.isDisabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onEnabledPreviousControlSelected.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onDisabledPreviousControlSelected.hashCode();
    }

    public String toString() {
        return "PreviousControlStateElement{description=" + this.description + ", altText=" + this.altText + ", isDisabled=" + this.isDisabled + ", onEnabledPreviousControlSelected=" + this.onEnabledPreviousControlSelected + ", onDisabledPreviousControlSelected=" + this.onDisabledPreviousControlSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PreviousControlStateElement", generator = "Immutables")
    static final class Json extends PreviousControlStateElement {

        @Nullable
        String altText;

        @Nullable
        String description;

        @Nullable
        Boolean isDisabled;
        boolean onDisabledPreviousControlSelectedIsSet;
        boolean onEnabledPreviousControlSelectedIsSet;

        @Nullable
        List<Method> onEnabledPreviousControlSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledPreviousControlSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public void setDescription(String str) {
            this.description = str;
        }

        @JsonProperty("altText")
        public void setAltText(String str) {
            this.altText = str;
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(Boolean bool) {
            this.isDisabled = bool;
        }

        @JsonProperty("onEnabledPreviousControlSelected")
        public void setOnEnabledPreviousControlSelected(List<Method> list) {
            this.onEnabledPreviousControlSelected = list;
            this.onEnabledPreviousControlSelectedIsSet = true;
        }

        @JsonProperty("onDisabledPreviousControlSelected")
        public void setOnDisabledPreviousControlSelected(List<Method> list) {
            this.onDisabledPreviousControlSelected = list;
            this.onDisabledPreviousControlSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.PreviousControlStateElement
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PreviousControlStateElement
        public String altText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PreviousControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PreviousControlStateElement
        public List<Method> onEnabledPreviousControlSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PreviousControlStateElement
        public List<Method> onDisabledPreviousControlSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePreviousControlStateElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.description;
        if (str != null) {
            builder.description(str);
        }
        String str2 = json.altText;
        if (str2 != null) {
            builder.altText(str2);
        }
        Boolean bool = json.isDisabled;
        if (bool != null) {
            builder.isDisabled(bool);
        }
        if (json.onEnabledPreviousControlSelectedIsSet) {
            builder.addAllOnEnabledPreviousControlSelected(json.onEnabledPreviousControlSelected);
        }
        if (json.onDisabledPreviousControlSelectedIsSet) {
            builder.addAllOnDisabledPreviousControlSelected(json.onDisabledPreviousControlSelected);
        }
        return builder.build();
    }

    public static ImmutablePreviousControlStateElement copyOf(PreviousControlStateElement previousControlStateElement) {
        if (previousControlStateElement instanceof ImmutablePreviousControlStateElement) {
            return (ImmutablePreviousControlStateElement) previousControlStateElement;
        }
        return builder().from(previousControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PreviousControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ALT_TEXT = 2;
        private static final long INIT_BIT_DESCRIPTION = 1;
        private static final long OPT_BIT_ON_DISABLED_PREVIOUS_CONTROL_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_PREVIOUS_CONTROL_SELECTED = 1;

        @Nullable
        private String altText;

        @Nullable
        private String description;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledPreviousControlSelected;
        private List<Method> onEnabledPreviousControlSelected;
        private long optBits;

        private Builder() {
            this.initBits = 3L;
            this.onEnabledPreviousControlSelected = new ArrayList();
            this.onDisabledPreviousControlSelected = new ArrayList();
        }

        public final Builder from(PreviousControlStateElement previousControlStateElement) {
            ImmutablePreviousControlStateElement.requireNonNull(previousControlStateElement, "instance");
            description(previousControlStateElement.description());
            altText(previousControlStateElement.altText());
            isDisabled(previousControlStateElement.isDisabled());
            addAllOnEnabledPreviousControlSelected(previousControlStateElement.onEnabledPreviousControlSelected());
            addAllOnDisabledPreviousControlSelected(previousControlStateElement.onDisabledPreviousControlSelected());
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(String str) {
            this.description = (String) ImmutablePreviousControlStateElement.requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("altText")
        public final Builder altText(String str) {
            this.altText = (String) ImmutablePreviousControlStateElement.requireNonNull(str, "altText");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutablePreviousControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledPreviousControlSelected(Method method) {
            this.onEnabledPreviousControlSelected.add((Method) ImmutablePreviousControlStateElement.requireNonNull(method, "onEnabledPreviousControlSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledPreviousControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledPreviousControlSelected.add((Method) ImmutablePreviousControlStateElement.requireNonNull(method, "onEnabledPreviousControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledPreviousControlSelected")
        public final Builder onEnabledPreviousControlSelected(Iterable<? extends Method> iterable) {
            this.onEnabledPreviousControlSelected.clear();
            return addAllOnEnabledPreviousControlSelected(iterable);
        }

        public final Builder addAllOnEnabledPreviousControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledPreviousControlSelected.add((Method) ImmutablePreviousControlStateElement.requireNonNull(it.next(), "onEnabledPreviousControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledPreviousControlSelected(Method method) {
            this.onDisabledPreviousControlSelected.add((Method) ImmutablePreviousControlStateElement.requireNonNull(method, "onDisabledPreviousControlSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledPreviousControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledPreviousControlSelected.add((Method) ImmutablePreviousControlStateElement.requireNonNull(method, "onDisabledPreviousControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledPreviousControlSelected")
        public final Builder onDisabledPreviousControlSelected(Iterable<? extends Method> iterable) {
            this.onDisabledPreviousControlSelected.clear();
            return addAllOnDisabledPreviousControlSelected(iterable);
        }

        public final Builder addAllOnDisabledPreviousControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledPreviousControlSelected.add((Method) ImmutablePreviousControlStateElement.requireNonNull(it.next(), "onDisabledPreviousControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutablePreviousControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePreviousControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledPreviousControlSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledPreviousControlSelectedIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(MediaTrack.ROLE_DESCRIPTION);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("altText");
            }
            return "Cannot build PreviousControlStateElement, some of required attributes are not set " + arrayList;
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
