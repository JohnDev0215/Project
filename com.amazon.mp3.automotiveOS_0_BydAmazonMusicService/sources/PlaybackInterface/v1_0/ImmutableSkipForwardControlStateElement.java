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
@Generated(from = "SkipForwardControlStateElement", generator = "Immutables")
public final class ImmutableSkipForwardControlStateElement extends SkipForwardControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String altText;
    private final String description;
    private final int duration;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledSkipForwardControlElementSelected;
    private final List<Method> onEnabledSkipForwardControlElementSelected;

    private ImmutableSkipForwardControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.description = builder.description;
        this.altText = builder.altText;
        this.duration = builder.duration;
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledSkipForwardControlElementSelectedIsSet()) {
            this.initShim.onEnabledSkipForwardControlElementSelected(createUnmodifiableList(true, builder.onEnabledSkipForwardControlElementSelected));
        }
        if (builder.onDisabledSkipForwardControlElementSelectedIsSet()) {
            this.initShim.onDisabledSkipForwardControlElementSelected(createUnmodifiableList(true, builder.onDisabledSkipForwardControlElementSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledSkipForwardControlElementSelected = this.initShim.onEnabledSkipForwardControlElementSelected();
        this.onDisabledSkipForwardControlElementSelected = this.initShim.onDisabledSkipForwardControlElementSelected();
        this.initShim = null;
    }

    private ImmutableSkipForwardControlStateElement(String str, String str2, int i, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.description = str;
        this.altText = str2;
        this.duration = i;
        this.isDisabled = bool;
        this.onEnabledSkipForwardControlElementSelected = list;
        this.onDisabledSkipForwardControlElementSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "SkipForwardControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledSkipForwardControlElementSelected;
        private byte onDisabledSkipForwardControlElementSelectedBuildStage;
        private List<Method> onEnabledSkipForwardControlElementSelected;
        private byte onEnabledSkipForwardControlElementSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledSkipForwardControlElementSelectedBuildStage = (byte) 0;
            this.onDisabledSkipForwardControlElementSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableSkipForwardControlStateElement.requireNonNull(ImmutableSkipForwardControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledSkipForwardControlElementSelected() {
            byte b = this.onEnabledSkipForwardControlElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledSkipForwardControlElementSelectedBuildStage = (byte) -1;
                this.onEnabledSkipForwardControlElementSelected = ImmutableSkipForwardControlStateElement.createUnmodifiableList(false, ImmutableSkipForwardControlStateElement.createSafeList(ImmutableSkipForwardControlStateElement.super.onEnabledSkipForwardControlElementSelected(), true, false));
                this.onEnabledSkipForwardControlElementSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledSkipForwardControlElementSelected;
        }

        void onEnabledSkipForwardControlElementSelected(List<Method> list) {
            this.onEnabledSkipForwardControlElementSelected = list;
            this.onEnabledSkipForwardControlElementSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledSkipForwardControlElementSelected() {
            byte b = this.onDisabledSkipForwardControlElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledSkipForwardControlElementSelectedBuildStage = (byte) -1;
                this.onDisabledSkipForwardControlElementSelected = ImmutableSkipForwardControlStateElement.createUnmodifiableList(false, ImmutableSkipForwardControlStateElement.createSafeList(ImmutableSkipForwardControlStateElement.super.onDisabledSkipForwardControlElementSelected(), true, false));
                this.onDisabledSkipForwardControlElementSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledSkipForwardControlElementSelected;
        }

        void onDisabledSkipForwardControlElementSelected(List<Method> list) {
            this.onDisabledSkipForwardControlElementSelected = list;
            this.onDisabledSkipForwardControlElementSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledSkipForwardControlElementSelectedBuildStage == -1) {
                arrayList.add("onEnabledSkipForwardControlElementSelected");
            }
            if (this.onDisabledSkipForwardControlElementSelectedBuildStage == -1) {
                arrayList.add("onDisabledSkipForwardControlElementSelected");
            }
            return "Cannot build SkipForwardControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    public String description() {
        return this.description;
    }

    @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
    @JsonProperty("altText")
    public String altText() {
        return this.altText;
    }

    @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
    @JsonProperty("duration")
    public int duration() {
        return this.duration;
    }

    @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
    @JsonProperty("onEnabledSkipForwardControlElementSelected")
    public List<Method> onEnabledSkipForwardControlElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledSkipForwardControlElementSelected();
        }
        return this.onEnabledSkipForwardControlElementSelected;
    }

    @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
    @JsonProperty("onDisabledSkipForwardControlElementSelected")
    public List<Method> onDisabledSkipForwardControlElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledSkipForwardControlElementSelected();
        }
        return this.onDisabledSkipForwardControlElementSelected;
    }

    public final ImmutableSkipForwardControlStateElement withDescription(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
        return this.description.equals(str2) ? this : new ImmutableSkipForwardControlStateElement(str2, this.altText, this.duration, this.isDisabled, this.onEnabledSkipForwardControlElementSelected, this.onDisabledSkipForwardControlElementSelected);
    }

    public final ImmutableSkipForwardControlStateElement withAltText(String str) {
        String str2 = (String) requireNonNull(str, "altText");
        return this.altText.equals(str2) ? this : new ImmutableSkipForwardControlStateElement(this.description, str2, this.duration, this.isDisabled, this.onEnabledSkipForwardControlElementSelected, this.onDisabledSkipForwardControlElementSelected);
    }

    public final ImmutableSkipForwardControlStateElement withDuration(int i) {
        return this.duration == i ? this : new ImmutableSkipForwardControlStateElement(this.description, this.altText, i, this.isDisabled, this.onEnabledSkipForwardControlElementSelected, this.onDisabledSkipForwardControlElementSelected);
    }

    public final ImmutableSkipForwardControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableSkipForwardControlStateElement(this.description, this.altText, this.duration, bool2, this.onEnabledSkipForwardControlElementSelected, this.onDisabledSkipForwardControlElementSelected);
    }

    public final ImmutableSkipForwardControlStateElement withOnEnabledSkipForwardControlElementSelected(Method... methodArr) {
        return new ImmutableSkipForwardControlStateElement(this.description, this.altText, this.duration, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledSkipForwardControlElementSelected);
    }

    public final ImmutableSkipForwardControlStateElement withOnEnabledSkipForwardControlElementSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledSkipForwardControlElementSelected == iterable) {
            return this;
        }
        return new ImmutableSkipForwardControlStateElement(this.description, this.altText, this.duration, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledSkipForwardControlElementSelected);
    }

    public final ImmutableSkipForwardControlStateElement withOnDisabledSkipForwardControlElementSelected(Method... methodArr) {
        return new ImmutableSkipForwardControlStateElement(this.description, this.altText, this.duration, this.isDisabled, this.onEnabledSkipForwardControlElementSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSkipForwardControlStateElement withOnDisabledSkipForwardControlElementSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledSkipForwardControlElementSelected == iterable) {
            return this;
        }
        return new ImmutableSkipForwardControlStateElement(this.description, this.altText, this.duration, this.isDisabled, this.onEnabledSkipForwardControlElementSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSkipForwardControlStateElement) && equalTo(0, (ImmutableSkipForwardControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableSkipForwardControlStateElement immutableSkipForwardControlStateElement) {
        return this.description.equals(immutableSkipForwardControlStateElement.description) && this.altText.equals(immutableSkipForwardControlStateElement.altText) && this.duration == immutableSkipForwardControlStateElement.duration && this.isDisabled.equals(immutableSkipForwardControlStateElement.isDisabled) && this.onEnabledSkipForwardControlElementSelected.equals(immutableSkipForwardControlStateElement.onEnabledSkipForwardControlElementSelected) && this.onDisabledSkipForwardControlElementSelected.equals(immutableSkipForwardControlStateElement.onDisabledSkipForwardControlElementSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.description.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altText.hashCode();
        int i = iHashCode2 + (iHashCode2 << 5) + this.duration;
        int iHashCode3 = i + (i << 5) + this.isDisabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onEnabledSkipForwardControlElementSelected.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onDisabledSkipForwardControlElementSelected.hashCode();
    }

    public String toString() {
        return "SkipForwardControlStateElement{description=" + this.description + ", altText=" + this.altText + ", duration=" + this.duration + ", isDisabled=" + this.isDisabled + ", onEnabledSkipForwardControlElementSelected=" + this.onEnabledSkipForwardControlElementSelected + ", onDisabledSkipForwardControlElementSelected=" + this.onDisabledSkipForwardControlElementSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SkipForwardControlStateElement", generator = "Immutables")
    static final class Json extends SkipForwardControlStateElement {

        @Nullable
        String altText;

        @Nullable
        String description;
        int duration;
        boolean durationIsSet;

        @Nullable
        Boolean isDisabled;
        boolean onDisabledSkipForwardControlElementSelectedIsSet;
        boolean onEnabledSkipForwardControlElementSelectedIsSet;

        @Nullable
        List<Method> onEnabledSkipForwardControlElementSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledSkipForwardControlElementSelected = Collections.emptyList();

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

        @JsonProperty("onEnabledSkipForwardControlElementSelected")
        public void setOnEnabledSkipForwardControlElementSelected(List<Method> list) {
            this.onEnabledSkipForwardControlElementSelected = list;
            this.onEnabledSkipForwardControlElementSelectedIsSet = true;
        }

        @JsonProperty("onDisabledSkipForwardControlElementSelected")
        public void setOnDisabledSkipForwardControlElementSelected(List<Method> list) {
            this.onDisabledSkipForwardControlElementSelected = list;
            this.onDisabledSkipForwardControlElementSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
        public String altText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
        public int duration() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
        public List<Method> onEnabledSkipForwardControlElementSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SkipForwardControlStateElement
        public List<Method> onDisabledSkipForwardControlElementSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSkipForwardControlStateElement fromJson(Json json) {
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
        if (json.onEnabledSkipForwardControlElementSelectedIsSet) {
            builder.addAllOnEnabledSkipForwardControlElementSelected(json.onEnabledSkipForwardControlElementSelected);
        }
        if (json.onDisabledSkipForwardControlElementSelectedIsSet) {
            builder.addAllOnDisabledSkipForwardControlElementSelected(json.onDisabledSkipForwardControlElementSelected);
        }
        return builder.build();
    }

    public static ImmutableSkipForwardControlStateElement copyOf(SkipForwardControlStateElement skipForwardControlStateElement) {
        if (skipForwardControlStateElement instanceof ImmutableSkipForwardControlStateElement) {
            return (ImmutableSkipForwardControlStateElement) skipForwardControlStateElement;
        }
        return builder().from(skipForwardControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SkipForwardControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ALT_TEXT = 2;
        private static final long INIT_BIT_DESCRIPTION = 1;
        private static final long INIT_BIT_DURATION = 4;
        private static final long OPT_BIT_ON_DISABLED_SKIP_FORWARD_CONTROL_ELEMENT_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_SKIP_FORWARD_CONTROL_ELEMENT_SELECTED = 1;

        @Nullable
        private String altText;

        @Nullable
        private String description;
        private int duration;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledSkipForwardControlElementSelected;
        private List<Method> onEnabledSkipForwardControlElementSelected;
        private long optBits;

        private Builder() {
            this.initBits = 7L;
            this.onEnabledSkipForwardControlElementSelected = new ArrayList();
            this.onDisabledSkipForwardControlElementSelected = new ArrayList();
        }

        public final Builder from(SkipForwardControlStateElement skipForwardControlStateElement) {
            ImmutableSkipForwardControlStateElement.requireNonNull(skipForwardControlStateElement, "instance");
            description(skipForwardControlStateElement.description());
            altText(skipForwardControlStateElement.altText());
            duration(skipForwardControlStateElement.duration());
            isDisabled(skipForwardControlStateElement.isDisabled());
            addAllOnEnabledSkipForwardControlElementSelected(skipForwardControlStateElement.onEnabledSkipForwardControlElementSelected());
            addAllOnDisabledSkipForwardControlElementSelected(skipForwardControlStateElement.onDisabledSkipForwardControlElementSelected());
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(String str) {
            this.description = (String) ImmutableSkipForwardControlStateElement.requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("altText")
        public final Builder altText(String str) {
            this.altText = (String) ImmutableSkipForwardControlStateElement.requireNonNull(str, "altText");
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
            this.isDisabled = (Boolean) ImmutableSkipForwardControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledSkipForwardControlElementSelected(Method method) {
            this.onEnabledSkipForwardControlElementSelected.add((Method) ImmutableSkipForwardControlStateElement.requireNonNull(method, "onEnabledSkipForwardControlElementSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledSkipForwardControlElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledSkipForwardControlElementSelected.add((Method) ImmutableSkipForwardControlStateElement.requireNonNull(method, "onEnabledSkipForwardControlElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledSkipForwardControlElementSelected")
        public final Builder onEnabledSkipForwardControlElementSelected(Iterable<? extends Method> iterable) {
            this.onEnabledSkipForwardControlElementSelected.clear();
            return addAllOnEnabledSkipForwardControlElementSelected(iterable);
        }

        public final Builder addAllOnEnabledSkipForwardControlElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledSkipForwardControlElementSelected.add((Method) ImmutableSkipForwardControlStateElement.requireNonNull(it.next(), "onEnabledSkipForwardControlElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledSkipForwardControlElementSelected(Method method) {
            this.onDisabledSkipForwardControlElementSelected.add((Method) ImmutableSkipForwardControlStateElement.requireNonNull(method, "onDisabledSkipForwardControlElementSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledSkipForwardControlElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledSkipForwardControlElementSelected.add((Method) ImmutableSkipForwardControlStateElement.requireNonNull(method, "onDisabledSkipForwardControlElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledSkipForwardControlElementSelected")
        public final Builder onDisabledSkipForwardControlElementSelected(Iterable<? extends Method> iterable) {
            this.onDisabledSkipForwardControlElementSelected.clear();
            return addAllOnDisabledSkipForwardControlElementSelected(iterable);
        }

        public final Builder addAllOnDisabledSkipForwardControlElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledSkipForwardControlElementSelected.add((Method) ImmutableSkipForwardControlStateElement.requireNonNull(it.next(), "onDisabledSkipForwardControlElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableSkipForwardControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSkipForwardControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledSkipForwardControlElementSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledSkipForwardControlElementSelectedIsSet() {
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
            return "Cannot build SkipForwardControlStateElement, some of required attributes are not set " + arrayList;
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
