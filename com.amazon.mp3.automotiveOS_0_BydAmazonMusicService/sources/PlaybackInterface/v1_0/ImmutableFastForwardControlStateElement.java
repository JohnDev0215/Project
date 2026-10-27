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
@Generated(from = "FastForwardControlStateElement", generator = "Immutables")
public final class ImmutableFastForwardControlStateElement extends FastForwardControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String altText;
    private final String description;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledFastForwardControlElementSelected;
    private final List<Method> onEnabledFastForwardControlElementSelected;

    private ImmutableFastForwardControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.description = builder.description;
        this.altText = builder.altText;
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledFastForwardControlElementSelectedIsSet()) {
            this.initShim.onEnabledFastForwardControlElementSelected(createUnmodifiableList(true, builder.onEnabledFastForwardControlElementSelected));
        }
        if (builder.onDisabledFastForwardControlElementSelectedIsSet()) {
            this.initShim.onDisabledFastForwardControlElementSelected(createUnmodifiableList(true, builder.onDisabledFastForwardControlElementSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledFastForwardControlElementSelected = this.initShim.onEnabledFastForwardControlElementSelected();
        this.onDisabledFastForwardControlElementSelected = this.initShim.onDisabledFastForwardControlElementSelected();
        this.initShim = null;
    }

    private ImmutableFastForwardControlStateElement(String str, String str2, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.description = str;
        this.altText = str2;
        this.isDisabled = bool;
        this.onEnabledFastForwardControlElementSelected = list;
        this.onDisabledFastForwardControlElementSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "FastForwardControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledFastForwardControlElementSelected;
        private byte onDisabledFastForwardControlElementSelectedBuildStage;
        private List<Method> onEnabledFastForwardControlElementSelected;
        private byte onEnabledFastForwardControlElementSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledFastForwardControlElementSelectedBuildStage = (byte) 0;
            this.onDisabledFastForwardControlElementSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableFastForwardControlStateElement.requireNonNull(ImmutableFastForwardControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledFastForwardControlElementSelected() {
            byte b = this.onEnabledFastForwardControlElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledFastForwardControlElementSelectedBuildStage = (byte) -1;
                this.onEnabledFastForwardControlElementSelected = ImmutableFastForwardControlStateElement.createUnmodifiableList(false, ImmutableFastForwardControlStateElement.createSafeList(ImmutableFastForwardControlStateElement.super.onEnabledFastForwardControlElementSelected(), true, false));
                this.onEnabledFastForwardControlElementSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledFastForwardControlElementSelected;
        }

        void onEnabledFastForwardControlElementSelected(List<Method> list) {
            this.onEnabledFastForwardControlElementSelected = list;
            this.onEnabledFastForwardControlElementSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledFastForwardControlElementSelected() {
            byte b = this.onDisabledFastForwardControlElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledFastForwardControlElementSelectedBuildStage = (byte) -1;
                this.onDisabledFastForwardControlElementSelected = ImmutableFastForwardControlStateElement.createUnmodifiableList(false, ImmutableFastForwardControlStateElement.createSafeList(ImmutableFastForwardControlStateElement.super.onDisabledFastForwardControlElementSelected(), true, false));
                this.onDisabledFastForwardControlElementSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledFastForwardControlElementSelected;
        }

        void onDisabledFastForwardControlElementSelected(List<Method> list) {
            this.onDisabledFastForwardControlElementSelected = list;
            this.onDisabledFastForwardControlElementSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledFastForwardControlElementSelectedBuildStage == -1) {
                arrayList.add("onEnabledFastForwardControlElementSelected");
            }
            if (this.onDisabledFastForwardControlElementSelectedBuildStage == -1) {
                arrayList.add("onDisabledFastForwardControlElementSelected");
            }
            return "Cannot build FastForwardControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.FastForwardControlStateElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    public String description() {
        return this.description;
    }

    @Override // PlaybackInterface.v1_0.FastForwardControlStateElement
    @JsonProperty("altText")
    public String altText() {
        return this.altText;
    }

    @Override // PlaybackInterface.v1_0.FastForwardControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.FastForwardControlStateElement
    @JsonProperty("onEnabledFastForwardControlElementSelected")
    public List<Method> onEnabledFastForwardControlElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledFastForwardControlElementSelected();
        }
        return this.onEnabledFastForwardControlElementSelected;
    }

    @Override // PlaybackInterface.v1_0.FastForwardControlStateElement
    @JsonProperty("onDisabledFastForwardControlElementSelected")
    public List<Method> onDisabledFastForwardControlElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledFastForwardControlElementSelected();
        }
        return this.onDisabledFastForwardControlElementSelected;
    }

    public final ImmutableFastForwardControlStateElement withDescription(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
        return this.description.equals(str2) ? this : new ImmutableFastForwardControlStateElement(str2, this.altText, this.isDisabled, this.onEnabledFastForwardControlElementSelected, this.onDisabledFastForwardControlElementSelected);
    }

    public final ImmutableFastForwardControlStateElement withAltText(String str) {
        String str2 = (String) requireNonNull(str, "altText");
        return this.altText.equals(str2) ? this : new ImmutableFastForwardControlStateElement(this.description, str2, this.isDisabled, this.onEnabledFastForwardControlElementSelected, this.onDisabledFastForwardControlElementSelected);
    }

    public final ImmutableFastForwardControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableFastForwardControlStateElement(this.description, this.altText, bool2, this.onEnabledFastForwardControlElementSelected, this.onDisabledFastForwardControlElementSelected);
    }

    public final ImmutableFastForwardControlStateElement withOnEnabledFastForwardControlElementSelected(Method... methodArr) {
        return new ImmutableFastForwardControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledFastForwardControlElementSelected);
    }

    public final ImmutableFastForwardControlStateElement withOnEnabledFastForwardControlElementSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledFastForwardControlElementSelected == iterable) {
            return this;
        }
        return new ImmutableFastForwardControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledFastForwardControlElementSelected);
    }

    public final ImmutableFastForwardControlStateElement withOnDisabledFastForwardControlElementSelected(Method... methodArr) {
        return new ImmutableFastForwardControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledFastForwardControlElementSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableFastForwardControlStateElement withOnDisabledFastForwardControlElementSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledFastForwardControlElementSelected == iterable) {
            return this;
        }
        return new ImmutableFastForwardControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledFastForwardControlElementSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableFastForwardControlStateElement) && equalTo(0, (ImmutableFastForwardControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableFastForwardControlStateElement immutableFastForwardControlStateElement) {
        return this.description.equals(immutableFastForwardControlStateElement.description) && this.altText.equals(immutableFastForwardControlStateElement.altText) && this.isDisabled.equals(immutableFastForwardControlStateElement.isDisabled) && this.onEnabledFastForwardControlElementSelected.equals(immutableFastForwardControlStateElement.onEnabledFastForwardControlElementSelected) && this.onDisabledFastForwardControlElementSelected.equals(immutableFastForwardControlStateElement.onDisabledFastForwardControlElementSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.description.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altText.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.isDisabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onEnabledFastForwardControlElementSelected.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onDisabledFastForwardControlElementSelected.hashCode();
    }

    public String toString() {
        return "FastForwardControlStateElement{description=" + this.description + ", altText=" + this.altText + ", isDisabled=" + this.isDisabled + ", onEnabledFastForwardControlElementSelected=" + this.onEnabledFastForwardControlElementSelected + ", onDisabledFastForwardControlElementSelected=" + this.onDisabledFastForwardControlElementSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "FastForwardControlStateElement", generator = "Immutables")
    static final class Json extends FastForwardControlStateElement {

        @Nullable
        String altText;

        @Nullable
        String description;

        @Nullable
        Boolean isDisabled;
        boolean onDisabledFastForwardControlElementSelectedIsSet;
        boolean onEnabledFastForwardControlElementSelectedIsSet;

        @Nullable
        List<Method> onEnabledFastForwardControlElementSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledFastForwardControlElementSelected = Collections.emptyList();

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

        @JsonProperty("onEnabledFastForwardControlElementSelected")
        public void setOnEnabledFastForwardControlElementSelected(List<Method> list) {
            this.onEnabledFastForwardControlElementSelected = list;
            this.onEnabledFastForwardControlElementSelectedIsSet = true;
        }

        @JsonProperty("onDisabledFastForwardControlElementSelected")
        public void setOnDisabledFastForwardControlElementSelected(List<Method> list) {
            this.onDisabledFastForwardControlElementSelected = list;
            this.onDisabledFastForwardControlElementSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.FastForwardControlStateElement
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.FastForwardControlStateElement
        public String altText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.FastForwardControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.FastForwardControlStateElement
        public List<Method> onEnabledFastForwardControlElementSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.FastForwardControlStateElement
        public List<Method> onDisabledFastForwardControlElementSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableFastForwardControlStateElement fromJson(Json json) {
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
        if (json.onEnabledFastForwardControlElementSelectedIsSet) {
            builder.addAllOnEnabledFastForwardControlElementSelected(json.onEnabledFastForwardControlElementSelected);
        }
        if (json.onDisabledFastForwardControlElementSelectedIsSet) {
            builder.addAllOnDisabledFastForwardControlElementSelected(json.onDisabledFastForwardControlElementSelected);
        }
        return builder.build();
    }

    public static ImmutableFastForwardControlStateElement copyOf(FastForwardControlStateElement fastForwardControlStateElement) {
        if (fastForwardControlStateElement instanceof ImmutableFastForwardControlStateElement) {
            return (ImmutableFastForwardControlStateElement) fastForwardControlStateElement;
        }
        return builder().from(fastForwardControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "FastForwardControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ALT_TEXT = 2;
        private static final long INIT_BIT_DESCRIPTION = 1;
        private static final long OPT_BIT_ON_DISABLED_FAST_FORWARD_CONTROL_ELEMENT_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_FAST_FORWARD_CONTROL_ELEMENT_SELECTED = 1;

        @Nullable
        private String altText;

        @Nullable
        private String description;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledFastForwardControlElementSelected;
        private List<Method> onEnabledFastForwardControlElementSelected;
        private long optBits;

        private Builder() {
            this.initBits = 3L;
            this.onEnabledFastForwardControlElementSelected = new ArrayList();
            this.onDisabledFastForwardControlElementSelected = new ArrayList();
        }

        public final Builder from(FastForwardControlStateElement fastForwardControlStateElement) {
            ImmutableFastForwardControlStateElement.requireNonNull(fastForwardControlStateElement, "instance");
            description(fastForwardControlStateElement.description());
            altText(fastForwardControlStateElement.altText());
            isDisabled(fastForwardControlStateElement.isDisabled());
            addAllOnEnabledFastForwardControlElementSelected(fastForwardControlStateElement.onEnabledFastForwardControlElementSelected());
            addAllOnDisabledFastForwardControlElementSelected(fastForwardControlStateElement.onDisabledFastForwardControlElementSelected());
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(String str) {
            this.description = (String) ImmutableFastForwardControlStateElement.requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("altText")
        public final Builder altText(String str) {
            this.altText = (String) ImmutableFastForwardControlStateElement.requireNonNull(str, "altText");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableFastForwardControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledFastForwardControlElementSelected(Method method) {
            this.onEnabledFastForwardControlElementSelected.add((Method) ImmutableFastForwardControlStateElement.requireNonNull(method, "onEnabledFastForwardControlElementSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledFastForwardControlElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledFastForwardControlElementSelected.add((Method) ImmutableFastForwardControlStateElement.requireNonNull(method, "onEnabledFastForwardControlElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledFastForwardControlElementSelected")
        public final Builder onEnabledFastForwardControlElementSelected(Iterable<? extends Method> iterable) {
            this.onEnabledFastForwardControlElementSelected.clear();
            return addAllOnEnabledFastForwardControlElementSelected(iterable);
        }

        public final Builder addAllOnEnabledFastForwardControlElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledFastForwardControlElementSelected.add((Method) ImmutableFastForwardControlStateElement.requireNonNull(it.next(), "onEnabledFastForwardControlElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledFastForwardControlElementSelected(Method method) {
            this.onDisabledFastForwardControlElementSelected.add((Method) ImmutableFastForwardControlStateElement.requireNonNull(method, "onDisabledFastForwardControlElementSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledFastForwardControlElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledFastForwardControlElementSelected.add((Method) ImmutableFastForwardControlStateElement.requireNonNull(method, "onDisabledFastForwardControlElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledFastForwardControlElementSelected")
        public final Builder onDisabledFastForwardControlElementSelected(Iterable<? extends Method> iterable) {
            this.onDisabledFastForwardControlElementSelected.clear();
            return addAllOnDisabledFastForwardControlElementSelected(iterable);
        }

        public final Builder addAllOnDisabledFastForwardControlElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledFastForwardControlElementSelected.add((Method) ImmutableFastForwardControlStateElement.requireNonNull(it.next(), "onDisabledFastForwardControlElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableFastForwardControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableFastForwardControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledFastForwardControlElementSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledFastForwardControlElementSelectedIsSet() {
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
            return "Cannot build FastForwardControlStateElement, some of required attributes are not set " + arrayList;
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
