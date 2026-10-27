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
@Generated(from = "RewindControlStateElement", generator = "Immutables")
public final class ImmutableRewindControlStateElement extends RewindControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String altText;
    private final String description;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledRewindControlStateElementSelected;
    private final List<Method> onEnabledRewindControlStateElementSelected;

    private ImmutableRewindControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.description = builder.description;
        this.altText = builder.altText;
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledRewindControlStateElementSelectedIsSet()) {
            this.initShim.onEnabledRewindControlStateElementSelected(createUnmodifiableList(true, builder.onEnabledRewindControlStateElementSelected));
        }
        if (builder.onDisabledRewindControlStateElementSelectedIsSet()) {
            this.initShim.onDisabledRewindControlStateElementSelected(createUnmodifiableList(true, builder.onDisabledRewindControlStateElementSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledRewindControlStateElementSelected = this.initShim.onEnabledRewindControlStateElementSelected();
        this.onDisabledRewindControlStateElementSelected = this.initShim.onDisabledRewindControlStateElementSelected();
        this.initShim = null;
    }

    private ImmutableRewindControlStateElement(String str, String str2, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.description = str;
        this.altText = str2;
        this.isDisabled = bool;
        this.onEnabledRewindControlStateElementSelected = list;
        this.onDisabledRewindControlStateElementSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "RewindControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledRewindControlStateElementSelected;
        private byte onDisabledRewindControlStateElementSelectedBuildStage;
        private List<Method> onEnabledRewindControlStateElementSelected;
        private byte onEnabledRewindControlStateElementSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledRewindControlStateElementSelectedBuildStage = (byte) 0;
            this.onDisabledRewindControlStateElementSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableRewindControlStateElement.requireNonNull(ImmutableRewindControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledRewindControlStateElementSelected() {
            byte b = this.onEnabledRewindControlStateElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledRewindControlStateElementSelectedBuildStage = (byte) -1;
                this.onEnabledRewindControlStateElementSelected = ImmutableRewindControlStateElement.createUnmodifiableList(false, ImmutableRewindControlStateElement.createSafeList(ImmutableRewindControlStateElement.super.onEnabledRewindControlStateElementSelected(), true, false));
                this.onEnabledRewindControlStateElementSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledRewindControlStateElementSelected;
        }

        void onEnabledRewindControlStateElementSelected(List<Method> list) {
            this.onEnabledRewindControlStateElementSelected = list;
            this.onEnabledRewindControlStateElementSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledRewindControlStateElementSelected() {
            byte b = this.onDisabledRewindControlStateElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledRewindControlStateElementSelectedBuildStage = (byte) -1;
                this.onDisabledRewindControlStateElementSelected = ImmutableRewindControlStateElement.createUnmodifiableList(false, ImmutableRewindControlStateElement.createSafeList(ImmutableRewindControlStateElement.super.onDisabledRewindControlStateElementSelected(), true, false));
                this.onDisabledRewindControlStateElementSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledRewindControlStateElementSelected;
        }

        void onDisabledRewindControlStateElementSelected(List<Method> list) {
            this.onDisabledRewindControlStateElementSelected = list;
            this.onDisabledRewindControlStateElementSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledRewindControlStateElementSelectedBuildStage == -1) {
                arrayList.add("onEnabledRewindControlStateElementSelected");
            }
            if (this.onDisabledRewindControlStateElementSelectedBuildStage == -1) {
                arrayList.add("onDisabledRewindControlStateElementSelected");
            }
            return "Cannot build RewindControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.RewindControlStateElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    public String description() {
        return this.description;
    }

    @Override // PlaybackInterface.v1_0.RewindControlStateElement
    @JsonProperty("altText")
    public String altText() {
        return this.altText;
    }

    @Override // PlaybackInterface.v1_0.RewindControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.RewindControlStateElement
    @JsonProperty("onEnabledRewindControlStateElementSelected")
    public List<Method> onEnabledRewindControlStateElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledRewindControlStateElementSelected();
        }
        return this.onEnabledRewindControlStateElementSelected;
    }

    @Override // PlaybackInterface.v1_0.RewindControlStateElement
    @JsonProperty("onDisabledRewindControlStateElementSelected")
    public List<Method> onDisabledRewindControlStateElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledRewindControlStateElementSelected();
        }
        return this.onDisabledRewindControlStateElementSelected;
    }

    public final ImmutableRewindControlStateElement withDescription(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
        return this.description.equals(str2) ? this : new ImmutableRewindControlStateElement(str2, this.altText, this.isDisabled, this.onEnabledRewindControlStateElementSelected, this.onDisabledRewindControlStateElementSelected);
    }

    public final ImmutableRewindControlStateElement withAltText(String str) {
        String str2 = (String) requireNonNull(str, "altText");
        return this.altText.equals(str2) ? this : new ImmutableRewindControlStateElement(this.description, str2, this.isDisabled, this.onEnabledRewindControlStateElementSelected, this.onDisabledRewindControlStateElementSelected);
    }

    public final ImmutableRewindControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableRewindControlStateElement(this.description, this.altText, bool2, this.onEnabledRewindControlStateElementSelected, this.onDisabledRewindControlStateElementSelected);
    }

    public final ImmutableRewindControlStateElement withOnEnabledRewindControlStateElementSelected(Method... methodArr) {
        return new ImmutableRewindControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledRewindControlStateElementSelected);
    }

    public final ImmutableRewindControlStateElement withOnEnabledRewindControlStateElementSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledRewindControlStateElementSelected == iterable) {
            return this;
        }
        return new ImmutableRewindControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledRewindControlStateElementSelected);
    }

    public final ImmutableRewindControlStateElement withOnDisabledRewindControlStateElementSelected(Method... methodArr) {
        return new ImmutableRewindControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledRewindControlStateElementSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableRewindControlStateElement withOnDisabledRewindControlStateElementSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledRewindControlStateElementSelected == iterable) {
            return this;
        }
        return new ImmutableRewindControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledRewindControlStateElementSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableRewindControlStateElement) && equalTo(0, (ImmutableRewindControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableRewindControlStateElement immutableRewindControlStateElement) {
        return this.description.equals(immutableRewindControlStateElement.description) && this.altText.equals(immutableRewindControlStateElement.altText) && this.isDisabled.equals(immutableRewindControlStateElement.isDisabled) && this.onEnabledRewindControlStateElementSelected.equals(immutableRewindControlStateElement.onEnabledRewindControlStateElementSelected) && this.onDisabledRewindControlStateElementSelected.equals(immutableRewindControlStateElement.onDisabledRewindControlStateElementSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.description.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altText.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.isDisabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onEnabledRewindControlStateElementSelected.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onDisabledRewindControlStateElementSelected.hashCode();
    }

    public String toString() {
        return "RewindControlStateElement{description=" + this.description + ", altText=" + this.altText + ", isDisabled=" + this.isDisabled + ", onEnabledRewindControlStateElementSelected=" + this.onEnabledRewindControlStateElementSelected + ", onDisabledRewindControlStateElementSelected=" + this.onDisabledRewindControlStateElementSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "RewindControlStateElement", generator = "Immutables")
    static final class Json extends RewindControlStateElement {

        @Nullable
        String altText;

        @Nullable
        String description;

        @Nullable
        Boolean isDisabled;
        boolean onDisabledRewindControlStateElementSelectedIsSet;
        boolean onEnabledRewindControlStateElementSelectedIsSet;

        @Nullable
        List<Method> onEnabledRewindControlStateElementSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledRewindControlStateElementSelected = Collections.emptyList();

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

        @JsonProperty("onEnabledRewindControlStateElementSelected")
        public void setOnEnabledRewindControlStateElementSelected(List<Method> list) {
            this.onEnabledRewindControlStateElementSelected = list;
            this.onEnabledRewindControlStateElementSelectedIsSet = true;
        }

        @JsonProperty("onDisabledRewindControlStateElementSelected")
        public void setOnDisabledRewindControlStateElementSelected(List<Method> list) {
            this.onDisabledRewindControlStateElementSelected = list;
            this.onDisabledRewindControlStateElementSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.RewindControlStateElement
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.RewindControlStateElement
        public String altText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.RewindControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.RewindControlStateElement
        public List<Method> onEnabledRewindControlStateElementSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.RewindControlStateElement
        public List<Method> onDisabledRewindControlStateElementSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableRewindControlStateElement fromJson(Json json) {
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
        if (json.onEnabledRewindControlStateElementSelectedIsSet) {
            builder.addAllOnEnabledRewindControlStateElementSelected(json.onEnabledRewindControlStateElementSelected);
        }
        if (json.onDisabledRewindControlStateElementSelectedIsSet) {
            builder.addAllOnDisabledRewindControlStateElementSelected(json.onDisabledRewindControlStateElementSelected);
        }
        return builder.build();
    }

    public static ImmutableRewindControlStateElement copyOf(RewindControlStateElement rewindControlStateElement) {
        if (rewindControlStateElement instanceof ImmutableRewindControlStateElement) {
            return (ImmutableRewindControlStateElement) rewindControlStateElement;
        }
        return builder().from(rewindControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "RewindControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ALT_TEXT = 2;
        private static final long INIT_BIT_DESCRIPTION = 1;
        private static final long OPT_BIT_ON_DISABLED_REWIND_CONTROL_STATE_ELEMENT_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_REWIND_CONTROL_STATE_ELEMENT_SELECTED = 1;

        @Nullable
        private String altText;

        @Nullable
        private String description;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledRewindControlStateElementSelected;
        private List<Method> onEnabledRewindControlStateElementSelected;
        private long optBits;

        private Builder() {
            this.initBits = 3L;
            this.onEnabledRewindControlStateElementSelected = new ArrayList();
            this.onDisabledRewindControlStateElementSelected = new ArrayList();
        }

        public final Builder from(RewindControlStateElement rewindControlStateElement) {
            ImmutableRewindControlStateElement.requireNonNull(rewindControlStateElement, "instance");
            description(rewindControlStateElement.description());
            altText(rewindControlStateElement.altText());
            isDisabled(rewindControlStateElement.isDisabled());
            addAllOnEnabledRewindControlStateElementSelected(rewindControlStateElement.onEnabledRewindControlStateElementSelected());
            addAllOnDisabledRewindControlStateElementSelected(rewindControlStateElement.onDisabledRewindControlStateElementSelected());
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(String str) {
            this.description = (String) ImmutableRewindControlStateElement.requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("altText")
        public final Builder altText(String str) {
            this.altText = (String) ImmutableRewindControlStateElement.requireNonNull(str, "altText");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableRewindControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledRewindControlStateElementSelected(Method method) {
            this.onEnabledRewindControlStateElementSelected.add((Method) ImmutableRewindControlStateElement.requireNonNull(method, "onEnabledRewindControlStateElementSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledRewindControlStateElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledRewindControlStateElementSelected.add((Method) ImmutableRewindControlStateElement.requireNonNull(method, "onEnabledRewindControlStateElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledRewindControlStateElementSelected")
        public final Builder onEnabledRewindControlStateElementSelected(Iterable<? extends Method> iterable) {
            this.onEnabledRewindControlStateElementSelected.clear();
            return addAllOnEnabledRewindControlStateElementSelected(iterable);
        }

        public final Builder addAllOnEnabledRewindControlStateElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledRewindControlStateElementSelected.add((Method) ImmutableRewindControlStateElement.requireNonNull(it.next(), "onEnabledRewindControlStateElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledRewindControlStateElementSelected(Method method) {
            this.onDisabledRewindControlStateElementSelected.add((Method) ImmutableRewindControlStateElement.requireNonNull(method, "onDisabledRewindControlStateElementSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledRewindControlStateElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledRewindControlStateElementSelected.add((Method) ImmutableRewindControlStateElement.requireNonNull(method, "onDisabledRewindControlStateElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledRewindControlStateElementSelected")
        public final Builder onDisabledRewindControlStateElementSelected(Iterable<? extends Method> iterable) {
            this.onDisabledRewindControlStateElementSelected.clear();
            return addAllOnDisabledRewindControlStateElementSelected(iterable);
        }

        public final Builder addAllOnDisabledRewindControlStateElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledRewindControlStateElementSelected.add((Method) ImmutableRewindControlStateElement.requireNonNull(it.next(), "onDisabledRewindControlStateElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableRewindControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableRewindControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledRewindControlStateElementSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledRewindControlStateElementSelectedIsSet() {
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
            return "Cannot build RewindControlStateElement, some of required attributes are not set " + arrayList;
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
