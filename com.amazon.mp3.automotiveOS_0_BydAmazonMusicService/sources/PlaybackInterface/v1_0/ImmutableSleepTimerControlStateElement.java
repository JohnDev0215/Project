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
@Generated(from = "SleepTimerControlStateElement", generator = "Immutables")
public final class ImmutableSleepTimerControlStateElement extends SleepTimerControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String altText;
    private final String description;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledSleepTimerControlElementSelected;
    private final List<Method> onEnabledSleepTimerControlElementSelected;

    private ImmutableSleepTimerControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.description = builder.description;
        this.altText = builder.altText;
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledSleepTimerControlElementSelectedIsSet()) {
            this.initShim.onEnabledSleepTimerControlElementSelected(createUnmodifiableList(true, builder.onEnabledSleepTimerControlElementSelected));
        }
        if (builder.onDisabledSleepTimerControlElementSelectedIsSet()) {
            this.initShim.onDisabledSleepTimerControlElementSelected(createUnmodifiableList(true, builder.onDisabledSleepTimerControlElementSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledSleepTimerControlElementSelected = this.initShim.onEnabledSleepTimerControlElementSelected();
        this.onDisabledSleepTimerControlElementSelected = this.initShim.onDisabledSleepTimerControlElementSelected();
        this.initShim = null;
    }

    private ImmutableSleepTimerControlStateElement(String str, String str2, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.description = str;
        this.altText = str2;
        this.isDisabled = bool;
        this.onEnabledSleepTimerControlElementSelected = list;
        this.onDisabledSleepTimerControlElementSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "SleepTimerControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledSleepTimerControlElementSelected;
        private byte onDisabledSleepTimerControlElementSelectedBuildStage;
        private List<Method> onEnabledSleepTimerControlElementSelected;
        private byte onEnabledSleepTimerControlElementSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledSleepTimerControlElementSelectedBuildStage = (byte) 0;
            this.onDisabledSleepTimerControlElementSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableSleepTimerControlStateElement.requireNonNull(ImmutableSleepTimerControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledSleepTimerControlElementSelected() {
            byte b = this.onEnabledSleepTimerControlElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledSleepTimerControlElementSelectedBuildStage = (byte) -1;
                this.onEnabledSleepTimerControlElementSelected = ImmutableSleepTimerControlStateElement.createUnmodifiableList(false, ImmutableSleepTimerControlStateElement.createSafeList(ImmutableSleepTimerControlStateElement.super.onEnabledSleepTimerControlElementSelected(), true, false));
                this.onEnabledSleepTimerControlElementSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledSleepTimerControlElementSelected;
        }

        void onEnabledSleepTimerControlElementSelected(List<Method> list) {
            this.onEnabledSleepTimerControlElementSelected = list;
            this.onEnabledSleepTimerControlElementSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledSleepTimerControlElementSelected() {
            byte b = this.onDisabledSleepTimerControlElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledSleepTimerControlElementSelectedBuildStage = (byte) -1;
                this.onDisabledSleepTimerControlElementSelected = ImmutableSleepTimerControlStateElement.createUnmodifiableList(false, ImmutableSleepTimerControlStateElement.createSafeList(ImmutableSleepTimerControlStateElement.super.onDisabledSleepTimerControlElementSelected(), true, false));
                this.onDisabledSleepTimerControlElementSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledSleepTimerControlElementSelected;
        }

        void onDisabledSleepTimerControlElementSelected(List<Method> list) {
            this.onDisabledSleepTimerControlElementSelected = list;
            this.onDisabledSleepTimerControlElementSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledSleepTimerControlElementSelectedBuildStage == -1) {
                arrayList.add("onEnabledSleepTimerControlElementSelected");
            }
            if (this.onDisabledSleepTimerControlElementSelectedBuildStage == -1) {
                arrayList.add("onDisabledSleepTimerControlElementSelected");
            }
            return "Cannot build SleepTimerControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.SleepTimerControlStateElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    public String description() {
        return this.description;
    }

    @Override // PlaybackInterface.v1_0.SleepTimerControlStateElement
    @JsonProperty("altText")
    public String altText() {
        return this.altText;
    }

    @Override // PlaybackInterface.v1_0.SleepTimerControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.SleepTimerControlStateElement
    @JsonProperty("onEnabledSleepTimerControlElementSelected")
    public List<Method> onEnabledSleepTimerControlElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledSleepTimerControlElementSelected();
        }
        return this.onEnabledSleepTimerControlElementSelected;
    }

    @Override // PlaybackInterface.v1_0.SleepTimerControlStateElement
    @JsonProperty("onDisabledSleepTimerControlElementSelected")
    public List<Method> onDisabledSleepTimerControlElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledSleepTimerControlElementSelected();
        }
        return this.onDisabledSleepTimerControlElementSelected;
    }

    public final ImmutableSleepTimerControlStateElement withDescription(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
        return this.description.equals(str2) ? this : new ImmutableSleepTimerControlStateElement(str2, this.altText, this.isDisabled, this.onEnabledSleepTimerControlElementSelected, this.onDisabledSleepTimerControlElementSelected);
    }

    public final ImmutableSleepTimerControlStateElement withAltText(String str) {
        String str2 = (String) requireNonNull(str, "altText");
        return this.altText.equals(str2) ? this : new ImmutableSleepTimerControlStateElement(this.description, str2, this.isDisabled, this.onEnabledSleepTimerControlElementSelected, this.onDisabledSleepTimerControlElementSelected);
    }

    public final ImmutableSleepTimerControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableSleepTimerControlStateElement(this.description, this.altText, bool2, this.onEnabledSleepTimerControlElementSelected, this.onDisabledSleepTimerControlElementSelected);
    }

    public final ImmutableSleepTimerControlStateElement withOnEnabledSleepTimerControlElementSelected(Method... methodArr) {
        return new ImmutableSleepTimerControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledSleepTimerControlElementSelected);
    }

    public final ImmutableSleepTimerControlStateElement withOnEnabledSleepTimerControlElementSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledSleepTimerControlElementSelected == iterable) {
            return this;
        }
        return new ImmutableSleepTimerControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledSleepTimerControlElementSelected);
    }

    public final ImmutableSleepTimerControlStateElement withOnDisabledSleepTimerControlElementSelected(Method... methodArr) {
        return new ImmutableSleepTimerControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledSleepTimerControlElementSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSleepTimerControlStateElement withOnDisabledSleepTimerControlElementSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledSleepTimerControlElementSelected == iterable) {
            return this;
        }
        return new ImmutableSleepTimerControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledSleepTimerControlElementSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSleepTimerControlStateElement) && equalTo(0, (ImmutableSleepTimerControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableSleepTimerControlStateElement immutableSleepTimerControlStateElement) {
        return this.description.equals(immutableSleepTimerControlStateElement.description) && this.altText.equals(immutableSleepTimerControlStateElement.altText) && this.isDisabled.equals(immutableSleepTimerControlStateElement.isDisabled) && this.onEnabledSleepTimerControlElementSelected.equals(immutableSleepTimerControlStateElement.onEnabledSleepTimerControlElementSelected) && this.onDisabledSleepTimerControlElementSelected.equals(immutableSleepTimerControlStateElement.onDisabledSleepTimerControlElementSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.description.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altText.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.isDisabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onEnabledSleepTimerControlElementSelected.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onDisabledSleepTimerControlElementSelected.hashCode();
    }

    public String toString() {
        return "SleepTimerControlStateElement{description=" + this.description + ", altText=" + this.altText + ", isDisabled=" + this.isDisabled + ", onEnabledSleepTimerControlElementSelected=" + this.onEnabledSleepTimerControlElementSelected + ", onDisabledSleepTimerControlElementSelected=" + this.onDisabledSleepTimerControlElementSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SleepTimerControlStateElement", generator = "Immutables")
    static final class Json extends SleepTimerControlStateElement {

        @Nullable
        String altText;

        @Nullable
        String description;

        @Nullable
        Boolean isDisabled;
        boolean onDisabledSleepTimerControlElementSelectedIsSet;
        boolean onEnabledSleepTimerControlElementSelectedIsSet;

        @Nullable
        List<Method> onEnabledSleepTimerControlElementSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledSleepTimerControlElementSelected = Collections.emptyList();

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

        @JsonProperty("onEnabledSleepTimerControlElementSelected")
        public void setOnEnabledSleepTimerControlElementSelected(List<Method> list) {
            this.onEnabledSleepTimerControlElementSelected = list;
            this.onEnabledSleepTimerControlElementSelectedIsSet = true;
        }

        @JsonProperty("onDisabledSleepTimerControlElementSelected")
        public void setOnDisabledSleepTimerControlElementSelected(List<Method> list) {
            this.onDisabledSleepTimerControlElementSelected = list;
            this.onDisabledSleepTimerControlElementSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.SleepTimerControlStateElement
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SleepTimerControlStateElement
        public String altText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SleepTimerControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SleepTimerControlStateElement
        public List<Method> onEnabledSleepTimerControlElementSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SleepTimerControlStateElement
        public List<Method> onDisabledSleepTimerControlElementSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSleepTimerControlStateElement fromJson(Json json) {
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
        if (json.onEnabledSleepTimerControlElementSelectedIsSet) {
            builder.addAllOnEnabledSleepTimerControlElementSelected(json.onEnabledSleepTimerControlElementSelected);
        }
        if (json.onDisabledSleepTimerControlElementSelectedIsSet) {
            builder.addAllOnDisabledSleepTimerControlElementSelected(json.onDisabledSleepTimerControlElementSelected);
        }
        return builder.build();
    }

    public static ImmutableSleepTimerControlStateElement copyOf(SleepTimerControlStateElement sleepTimerControlStateElement) {
        if (sleepTimerControlStateElement instanceof ImmutableSleepTimerControlStateElement) {
            return (ImmutableSleepTimerControlStateElement) sleepTimerControlStateElement;
        }
        return builder().from(sleepTimerControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SleepTimerControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ALT_TEXT = 2;
        private static final long INIT_BIT_DESCRIPTION = 1;
        private static final long OPT_BIT_ON_DISABLED_SLEEP_TIMER_CONTROL_ELEMENT_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_SLEEP_TIMER_CONTROL_ELEMENT_SELECTED = 1;

        @Nullable
        private String altText;

        @Nullable
        private String description;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledSleepTimerControlElementSelected;
        private List<Method> onEnabledSleepTimerControlElementSelected;
        private long optBits;

        private Builder() {
            this.initBits = 3L;
            this.onEnabledSleepTimerControlElementSelected = new ArrayList();
            this.onDisabledSleepTimerControlElementSelected = new ArrayList();
        }

        public final Builder from(SleepTimerControlStateElement sleepTimerControlStateElement) {
            ImmutableSleepTimerControlStateElement.requireNonNull(sleepTimerControlStateElement, "instance");
            description(sleepTimerControlStateElement.description());
            altText(sleepTimerControlStateElement.altText());
            isDisabled(sleepTimerControlStateElement.isDisabled());
            addAllOnEnabledSleepTimerControlElementSelected(sleepTimerControlStateElement.onEnabledSleepTimerControlElementSelected());
            addAllOnDisabledSleepTimerControlElementSelected(sleepTimerControlStateElement.onDisabledSleepTimerControlElementSelected());
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(String str) {
            this.description = (String) ImmutableSleepTimerControlStateElement.requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("altText")
        public final Builder altText(String str) {
            this.altText = (String) ImmutableSleepTimerControlStateElement.requireNonNull(str, "altText");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableSleepTimerControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledSleepTimerControlElementSelected(Method method) {
            this.onEnabledSleepTimerControlElementSelected.add((Method) ImmutableSleepTimerControlStateElement.requireNonNull(method, "onEnabledSleepTimerControlElementSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledSleepTimerControlElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledSleepTimerControlElementSelected.add((Method) ImmutableSleepTimerControlStateElement.requireNonNull(method, "onEnabledSleepTimerControlElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledSleepTimerControlElementSelected")
        public final Builder onEnabledSleepTimerControlElementSelected(Iterable<? extends Method> iterable) {
            this.onEnabledSleepTimerControlElementSelected.clear();
            return addAllOnEnabledSleepTimerControlElementSelected(iterable);
        }

        public final Builder addAllOnEnabledSleepTimerControlElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledSleepTimerControlElementSelected.add((Method) ImmutableSleepTimerControlStateElement.requireNonNull(it.next(), "onEnabledSleepTimerControlElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledSleepTimerControlElementSelected(Method method) {
            this.onDisabledSleepTimerControlElementSelected.add((Method) ImmutableSleepTimerControlStateElement.requireNonNull(method, "onDisabledSleepTimerControlElementSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledSleepTimerControlElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledSleepTimerControlElementSelected.add((Method) ImmutableSleepTimerControlStateElement.requireNonNull(method, "onDisabledSleepTimerControlElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledSleepTimerControlElementSelected")
        public final Builder onDisabledSleepTimerControlElementSelected(Iterable<? extends Method> iterable) {
            this.onDisabledSleepTimerControlElementSelected.clear();
            return addAllOnDisabledSleepTimerControlElementSelected(iterable);
        }

        public final Builder addAllOnDisabledSleepTimerControlElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledSleepTimerControlElementSelected.add((Method) ImmutableSleepTimerControlStateElement.requireNonNull(it.next(), "onDisabledSleepTimerControlElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableSleepTimerControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSleepTimerControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledSleepTimerControlElementSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledSleepTimerControlElementSelectedIsSet() {
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
            return "Cannot build SleepTimerControlStateElement, some of required attributes are not set " + arrayList;
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
