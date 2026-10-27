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
@Generated(from = "SkipBackwardControlStateElement", generator = "Immutables")
public final class ImmutableSkipBackwardControlStateElement extends SkipBackwardControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String altText;
    private final String description;
    private final int duration;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledSkipBackwardControlStateElementSelected;
    private final List<Method> onEnabledSkipBackwardControlStateElementSelected;

    private ImmutableSkipBackwardControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.description = builder.description;
        this.altText = builder.altText;
        this.duration = builder.duration;
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledSkipBackwardControlStateElementSelectedIsSet()) {
            this.initShim.onEnabledSkipBackwardControlStateElementSelected(createUnmodifiableList(true, builder.onEnabledSkipBackwardControlStateElementSelected));
        }
        if (builder.onDisabledSkipBackwardControlStateElementSelectedIsSet()) {
            this.initShim.onDisabledSkipBackwardControlStateElementSelected(createUnmodifiableList(true, builder.onDisabledSkipBackwardControlStateElementSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledSkipBackwardControlStateElementSelected = this.initShim.onEnabledSkipBackwardControlStateElementSelected();
        this.onDisabledSkipBackwardControlStateElementSelected = this.initShim.onDisabledSkipBackwardControlStateElementSelected();
        this.initShim = null;
    }

    private ImmutableSkipBackwardControlStateElement(String str, String str2, int i, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.description = str;
        this.altText = str2;
        this.duration = i;
        this.isDisabled = bool;
        this.onEnabledSkipBackwardControlStateElementSelected = list;
        this.onDisabledSkipBackwardControlStateElementSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "SkipBackwardControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledSkipBackwardControlStateElementSelected;
        private byte onDisabledSkipBackwardControlStateElementSelectedBuildStage;
        private List<Method> onEnabledSkipBackwardControlStateElementSelected;
        private byte onEnabledSkipBackwardControlStateElementSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledSkipBackwardControlStateElementSelectedBuildStage = (byte) 0;
            this.onDisabledSkipBackwardControlStateElementSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableSkipBackwardControlStateElement.requireNonNull(ImmutableSkipBackwardControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledSkipBackwardControlStateElementSelected() {
            byte b = this.onEnabledSkipBackwardControlStateElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledSkipBackwardControlStateElementSelectedBuildStage = (byte) -1;
                this.onEnabledSkipBackwardControlStateElementSelected = ImmutableSkipBackwardControlStateElement.createUnmodifiableList(false, ImmutableSkipBackwardControlStateElement.createSafeList(ImmutableSkipBackwardControlStateElement.super.onEnabledSkipBackwardControlStateElementSelected(), true, false));
                this.onEnabledSkipBackwardControlStateElementSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledSkipBackwardControlStateElementSelected;
        }

        void onEnabledSkipBackwardControlStateElementSelected(List<Method> list) {
            this.onEnabledSkipBackwardControlStateElementSelected = list;
            this.onEnabledSkipBackwardControlStateElementSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledSkipBackwardControlStateElementSelected() {
            byte b = this.onDisabledSkipBackwardControlStateElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledSkipBackwardControlStateElementSelectedBuildStage = (byte) -1;
                this.onDisabledSkipBackwardControlStateElementSelected = ImmutableSkipBackwardControlStateElement.createUnmodifiableList(false, ImmutableSkipBackwardControlStateElement.createSafeList(ImmutableSkipBackwardControlStateElement.super.onDisabledSkipBackwardControlStateElementSelected(), true, false));
                this.onDisabledSkipBackwardControlStateElementSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledSkipBackwardControlStateElementSelected;
        }

        void onDisabledSkipBackwardControlStateElementSelected(List<Method> list) {
            this.onDisabledSkipBackwardControlStateElementSelected = list;
            this.onDisabledSkipBackwardControlStateElementSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledSkipBackwardControlStateElementSelectedBuildStage == -1) {
                arrayList.add("onEnabledSkipBackwardControlStateElementSelected");
            }
            if (this.onDisabledSkipBackwardControlStateElementSelectedBuildStage == -1) {
                arrayList.add("onDisabledSkipBackwardControlStateElementSelected");
            }
            return "Cannot build SkipBackwardControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    public String description() {
        return this.description;
    }

    @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
    @JsonProperty("altText")
    public String altText() {
        return this.altText;
    }

    @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
    @JsonProperty("duration")
    public int duration() {
        return this.duration;
    }

    @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
    @JsonProperty("onEnabledSkipBackwardControlStateElementSelected")
    public List<Method> onEnabledSkipBackwardControlStateElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledSkipBackwardControlStateElementSelected();
        }
        return this.onEnabledSkipBackwardControlStateElementSelected;
    }

    @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
    @JsonProperty("onDisabledSkipBackwardControlStateElementSelected")
    public List<Method> onDisabledSkipBackwardControlStateElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledSkipBackwardControlStateElementSelected();
        }
        return this.onDisabledSkipBackwardControlStateElementSelected;
    }

    public final ImmutableSkipBackwardControlStateElement withDescription(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
        return this.description.equals(str2) ? this : new ImmutableSkipBackwardControlStateElement(str2, this.altText, this.duration, this.isDisabled, this.onEnabledSkipBackwardControlStateElementSelected, this.onDisabledSkipBackwardControlStateElementSelected);
    }

    public final ImmutableSkipBackwardControlStateElement withAltText(String str) {
        String str2 = (String) requireNonNull(str, "altText");
        return this.altText.equals(str2) ? this : new ImmutableSkipBackwardControlStateElement(this.description, str2, this.duration, this.isDisabled, this.onEnabledSkipBackwardControlStateElementSelected, this.onDisabledSkipBackwardControlStateElementSelected);
    }

    public final ImmutableSkipBackwardControlStateElement withDuration(int i) {
        return this.duration == i ? this : new ImmutableSkipBackwardControlStateElement(this.description, this.altText, i, this.isDisabled, this.onEnabledSkipBackwardControlStateElementSelected, this.onDisabledSkipBackwardControlStateElementSelected);
    }

    public final ImmutableSkipBackwardControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableSkipBackwardControlStateElement(this.description, this.altText, this.duration, bool2, this.onEnabledSkipBackwardControlStateElementSelected, this.onDisabledSkipBackwardControlStateElementSelected);
    }

    public final ImmutableSkipBackwardControlStateElement withOnEnabledSkipBackwardControlStateElementSelected(Method... methodArr) {
        return new ImmutableSkipBackwardControlStateElement(this.description, this.altText, this.duration, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledSkipBackwardControlStateElementSelected);
    }

    public final ImmutableSkipBackwardControlStateElement withOnEnabledSkipBackwardControlStateElementSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledSkipBackwardControlStateElementSelected == iterable) {
            return this;
        }
        return new ImmutableSkipBackwardControlStateElement(this.description, this.altText, this.duration, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledSkipBackwardControlStateElementSelected);
    }

    public final ImmutableSkipBackwardControlStateElement withOnDisabledSkipBackwardControlStateElementSelected(Method... methodArr) {
        return new ImmutableSkipBackwardControlStateElement(this.description, this.altText, this.duration, this.isDisabled, this.onEnabledSkipBackwardControlStateElementSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSkipBackwardControlStateElement withOnDisabledSkipBackwardControlStateElementSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledSkipBackwardControlStateElementSelected == iterable) {
            return this;
        }
        return new ImmutableSkipBackwardControlStateElement(this.description, this.altText, this.duration, this.isDisabled, this.onEnabledSkipBackwardControlStateElementSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSkipBackwardControlStateElement) && equalTo(0, (ImmutableSkipBackwardControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableSkipBackwardControlStateElement immutableSkipBackwardControlStateElement) {
        return this.description.equals(immutableSkipBackwardControlStateElement.description) && this.altText.equals(immutableSkipBackwardControlStateElement.altText) && this.duration == immutableSkipBackwardControlStateElement.duration && this.isDisabled.equals(immutableSkipBackwardControlStateElement.isDisabled) && this.onEnabledSkipBackwardControlStateElementSelected.equals(immutableSkipBackwardControlStateElement.onEnabledSkipBackwardControlStateElementSelected) && this.onDisabledSkipBackwardControlStateElementSelected.equals(immutableSkipBackwardControlStateElement.onDisabledSkipBackwardControlStateElementSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.description.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altText.hashCode();
        int i = iHashCode2 + (iHashCode2 << 5) + this.duration;
        int iHashCode3 = i + (i << 5) + this.isDisabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onEnabledSkipBackwardControlStateElementSelected.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onDisabledSkipBackwardControlStateElementSelected.hashCode();
    }

    public String toString() {
        return "SkipBackwardControlStateElement{description=" + this.description + ", altText=" + this.altText + ", duration=" + this.duration + ", isDisabled=" + this.isDisabled + ", onEnabledSkipBackwardControlStateElementSelected=" + this.onEnabledSkipBackwardControlStateElementSelected + ", onDisabledSkipBackwardControlStateElementSelected=" + this.onDisabledSkipBackwardControlStateElementSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SkipBackwardControlStateElement", generator = "Immutables")
    static final class Json extends SkipBackwardControlStateElement {

        @Nullable
        String altText;

        @Nullable
        String description;
        int duration;
        boolean durationIsSet;

        @Nullable
        Boolean isDisabled;
        boolean onDisabledSkipBackwardControlStateElementSelectedIsSet;
        boolean onEnabledSkipBackwardControlStateElementSelectedIsSet;

        @Nullable
        List<Method> onEnabledSkipBackwardControlStateElementSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledSkipBackwardControlStateElementSelected = Collections.emptyList();

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

        @JsonProperty("duration")
        public void setDuration(int i) {
            this.duration = i;
            this.durationIsSet = true;
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(Boolean bool) {
            this.isDisabled = bool;
        }

        @JsonProperty("onEnabledSkipBackwardControlStateElementSelected")
        public void setOnEnabledSkipBackwardControlStateElementSelected(List<Method> list) {
            this.onEnabledSkipBackwardControlStateElementSelected = list;
            this.onEnabledSkipBackwardControlStateElementSelectedIsSet = true;
        }

        @JsonProperty("onDisabledSkipBackwardControlStateElementSelected")
        public void setOnDisabledSkipBackwardControlStateElementSelected(List<Method> list) {
            this.onDisabledSkipBackwardControlStateElementSelected = list;
            this.onDisabledSkipBackwardControlStateElementSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
        public String altText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
        public int duration() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
        public List<Method> onEnabledSkipBackwardControlStateElementSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SkipBackwardControlStateElement
        public List<Method> onDisabledSkipBackwardControlStateElementSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSkipBackwardControlStateElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.description;
        if (str != null) {
            builder.description(str);
        }
        String str2 = json.altText;
        if (str2 != null) {
            builder.altText(str2);
        }
        if (json.durationIsSet) {
            builder.duration(json.duration);
        }
        Boolean bool = json.isDisabled;
        if (bool != null) {
            builder.isDisabled(bool);
        }
        if (json.onEnabledSkipBackwardControlStateElementSelectedIsSet) {
            builder.addAllOnEnabledSkipBackwardControlStateElementSelected(json.onEnabledSkipBackwardControlStateElementSelected);
        }
        if (json.onDisabledSkipBackwardControlStateElementSelectedIsSet) {
            builder.addAllOnDisabledSkipBackwardControlStateElementSelected(json.onDisabledSkipBackwardControlStateElementSelected);
        }
        return builder.build();
    }

    public static ImmutableSkipBackwardControlStateElement copyOf(SkipBackwardControlStateElement skipBackwardControlStateElement) {
        if (skipBackwardControlStateElement instanceof ImmutableSkipBackwardControlStateElement) {
            return (ImmutableSkipBackwardControlStateElement) skipBackwardControlStateElement;
        }
        return builder().from(skipBackwardControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SkipBackwardControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ALT_TEXT = 2;
        private static final long INIT_BIT_DESCRIPTION = 1;
        private static final long INIT_BIT_DURATION = 4;
        private static final long OPT_BIT_ON_DISABLED_SKIP_BACKWARD_CONTROL_STATE_ELEMENT_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_SKIP_BACKWARD_CONTROL_STATE_ELEMENT_SELECTED = 1;

        @Nullable
        private String altText;

        @Nullable
        private String description;
        private int duration;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledSkipBackwardControlStateElementSelected;
        private List<Method> onEnabledSkipBackwardControlStateElementSelected;
        private long optBits;

        private Builder() {
            this.initBits = 7L;
            this.onEnabledSkipBackwardControlStateElementSelected = new ArrayList();
            this.onDisabledSkipBackwardControlStateElementSelected = new ArrayList();
        }

        public final Builder from(SkipBackwardControlStateElement skipBackwardControlStateElement) {
            ImmutableSkipBackwardControlStateElement.requireNonNull(skipBackwardControlStateElement, "instance");
            description(skipBackwardControlStateElement.description());
            altText(skipBackwardControlStateElement.altText());
            duration(skipBackwardControlStateElement.duration());
            isDisabled(skipBackwardControlStateElement.isDisabled());
            addAllOnEnabledSkipBackwardControlStateElementSelected(skipBackwardControlStateElement.onEnabledSkipBackwardControlStateElementSelected());
            addAllOnDisabledSkipBackwardControlStateElementSelected(skipBackwardControlStateElement.onDisabledSkipBackwardControlStateElementSelected());
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(String str) {
            this.description = (String) ImmutableSkipBackwardControlStateElement.requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("altText")
        public final Builder altText(String str) {
            this.altText = (String) ImmutableSkipBackwardControlStateElement.requireNonNull(str, "altText");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("duration")
        public final Builder duration(int i) {
            this.duration = i;
            this.initBits &= -5;
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableSkipBackwardControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledSkipBackwardControlStateElementSelected(Method method) {
            this.onEnabledSkipBackwardControlStateElementSelected.add((Method) ImmutableSkipBackwardControlStateElement.requireNonNull(method, "onEnabledSkipBackwardControlStateElementSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledSkipBackwardControlStateElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledSkipBackwardControlStateElementSelected.add((Method) ImmutableSkipBackwardControlStateElement.requireNonNull(method, "onEnabledSkipBackwardControlStateElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledSkipBackwardControlStateElementSelected")
        public final Builder onEnabledSkipBackwardControlStateElementSelected(Iterable<? extends Method> iterable) {
            this.onEnabledSkipBackwardControlStateElementSelected.clear();
            return addAllOnEnabledSkipBackwardControlStateElementSelected(iterable);
        }

        public final Builder addAllOnEnabledSkipBackwardControlStateElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledSkipBackwardControlStateElementSelected.add((Method) ImmutableSkipBackwardControlStateElement.requireNonNull(it.next(), "onEnabledSkipBackwardControlStateElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledSkipBackwardControlStateElementSelected(Method method) {
            this.onDisabledSkipBackwardControlStateElementSelected.add((Method) ImmutableSkipBackwardControlStateElement.requireNonNull(method, "onDisabledSkipBackwardControlStateElementSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledSkipBackwardControlStateElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledSkipBackwardControlStateElementSelected.add((Method) ImmutableSkipBackwardControlStateElement.requireNonNull(method, "onDisabledSkipBackwardControlStateElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledSkipBackwardControlStateElementSelected")
        public final Builder onDisabledSkipBackwardControlStateElementSelected(Iterable<? extends Method> iterable) {
            this.onDisabledSkipBackwardControlStateElementSelected.clear();
            return addAllOnDisabledSkipBackwardControlStateElementSelected(iterable);
        }

        public final Builder addAllOnDisabledSkipBackwardControlStateElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledSkipBackwardControlStateElementSelected.add((Method) ImmutableSkipBackwardControlStateElement.requireNonNull(it.next(), "onDisabledSkipBackwardControlStateElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableSkipBackwardControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSkipBackwardControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledSkipBackwardControlStateElementSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledSkipBackwardControlStateElementSelectedIsSet() {
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
            if ((this.initBits & 4) != 0) {
                arrayList.add("duration");
            }
            return "Cannot build SkipBackwardControlStateElement, some of required attributes are not set " + arrayList;
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
