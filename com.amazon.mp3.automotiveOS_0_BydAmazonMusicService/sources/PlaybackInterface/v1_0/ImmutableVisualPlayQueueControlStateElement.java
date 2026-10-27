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
@Generated(from = "VisualPlayQueueControlStateElement", generator = "Immutables")
public final class ImmutableVisualPlayQueueControlStateElement extends VisualPlayQueueControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String altText;
    private final String description;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledVisualPlayQueueControlSelected;
    private final List<Method> onEnabledVisualPlayQueueControlSelected;

    private ImmutableVisualPlayQueueControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.description = builder.description;
        this.altText = builder.altText;
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledVisualPlayQueueControlSelectedIsSet()) {
            this.initShim.onEnabledVisualPlayQueueControlSelected(createUnmodifiableList(true, builder.onEnabledVisualPlayQueueControlSelected));
        }
        if (builder.onDisabledVisualPlayQueueControlSelectedIsSet()) {
            this.initShim.onDisabledVisualPlayQueueControlSelected(createUnmodifiableList(true, builder.onDisabledVisualPlayQueueControlSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledVisualPlayQueueControlSelected = this.initShim.onEnabledVisualPlayQueueControlSelected();
        this.onDisabledVisualPlayQueueControlSelected = this.initShim.onDisabledVisualPlayQueueControlSelected();
        this.initShim = null;
    }

    private ImmutableVisualPlayQueueControlStateElement(String str, String str2, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.description = str;
        this.altText = str2;
        this.isDisabled = bool;
        this.onEnabledVisualPlayQueueControlSelected = list;
        this.onDisabledVisualPlayQueueControlSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "VisualPlayQueueControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledVisualPlayQueueControlSelected;
        private byte onDisabledVisualPlayQueueControlSelectedBuildStage;
        private List<Method> onEnabledVisualPlayQueueControlSelected;
        private byte onEnabledVisualPlayQueueControlSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledVisualPlayQueueControlSelectedBuildStage = (byte) 0;
            this.onDisabledVisualPlayQueueControlSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableVisualPlayQueueControlStateElement.requireNonNull(ImmutableVisualPlayQueueControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledVisualPlayQueueControlSelected() {
            byte b = this.onEnabledVisualPlayQueueControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledVisualPlayQueueControlSelectedBuildStage = (byte) -1;
                this.onEnabledVisualPlayQueueControlSelected = ImmutableVisualPlayQueueControlStateElement.createUnmodifiableList(false, ImmutableVisualPlayQueueControlStateElement.createSafeList(ImmutableVisualPlayQueueControlStateElement.super.onEnabledVisualPlayQueueControlSelected(), true, false));
                this.onEnabledVisualPlayQueueControlSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledVisualPlayQueueControlSelected;
        }

        void onEnabledVisualPlayQueueControlSelected(List<Method> list) {
            this.onEnabledVisualPlayQueueControlSelected = list;
            this.onEnabledVisualPlayQueueControlSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledVisualPlayQueueControlSelected() {
            byte b = this.onDisabledVisualPlayQueueControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledVisualPlayQueueControlSelectedBuildStage = (byte) -1;
                this.onDisabledVisualPlayQueueControlSelected = ImmutableVisualPlayQueueControlStateElement.createUnmodifiableList(false, ImmutableVisualPlayQueueControlStateElement.createSafeList(ImmutableVisualPlayQueueControlStateElement.super.onDisabledVisualPlayQueueControlSelected(), true, false));
                this.onDisabledVisualPlayQueueControlSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledVisualPlayQueueControlSelected;
        }

        void onDisabledVisualPlayQueueControlSelected(List<Method> list) {
            this.onDisabledVisualPlayQueueControlSelected = list;
            this.onDisabledVisualPlayQueueControlSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledVisualPlayQueueControlSelectedBuildStage == -1) {
                arrayList.add("onEnabledVisualPlayQueueControlSelected");
            }
            if (this.onDisabledVisualPlayQueueControlSelectedBuildStage == -1) {
                arrayList.add("onDisabledVisualPlayQueueControlSelected");
            }
            return "Cannot build VisualPlayQueueControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.VisualPlayQueueControlStateElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    public String description() {
        return this.description;
    }

    @Override // PlaybackInterface.v1_0.VisualPlayQueueControlStateElement
    @JsonProperty("altText")
    public String altText() {
        return this.altText;
    }

    @Override // PlaybackInterface.v1_0.VisualPlayQueueControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.VisualPlayQueueControlStateElement
    @JsonProperty("onEnabledVisualPlayQueueControlSelected")
    public List<Method> onEnabledVisualPlayQueueControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledVisualPlayQueueControlSelected();
        }
        return this.onEnabledVisualPlayQueueControlSelected;
    }

    @Override // PlaybackInterface.v1_0.VisualPlayQueueControlStateElement
    @JsonProperty("onDisabledVisualPlayQueueControlSelected")
    public List<Method> onDisabledVisualPlayQueueControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledVisualPlayQueueControlSelected();
        }
        return this.onDisabledVisualPlayQueueControlSelected;
    }

    public final ImmutableVisualPlayQueueControlStateElement withDescription(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
        return this.description.equals(str2) ? this : new ImmutableVisualPlayQueueControlStateElement(str2, this.altText, this.isDisabled, this.onEnabledVisualPlayQueueControlSelected, this.onDisabledVisualPlayQueueControlSelected);
    }

    public final ImmutableVisualPlayQueueControlStateElement withAltText(String str) {
        String str2 = (String) requireNonNull(str, "altText");
        return this.altText.equals(str2) ? this : new ImmutableVisualPlayQueueControlStateElement(this.description, str2, this.isDisabled, this.onEnabledVisualPlayQueueControlSelected, this.onDisabledVisualPlayQueueControlSelected);
    }

    public final ImmutableVisualPlayQueueControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableVisualPlayQueueControlStateElement(this.description, this.altText, bool2, this.onEnabledVisualPlayQueueControlSelected, this.onDisabledVisualPlayQueueControlSelected);
    }

    public final ImmutableVisualPlayQueueControlStateElement withOnEnabledVisualPlayQueueControlSelected(Method... methodArr) {
        return new ImmutableVisualPlayQueueControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledVisualPlayQueueControlSelected);
    }

    public final ImmutableVisualPlayQueueControlStateElement withOnEnabledVisualPlayQueueControlSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledVisualPlayQueueControlSelected == iterable) {
            return this;
        }
        return new ImmutableVisualPlayQueueControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledVisualPlayQueueControlSelected);
    }

    public final ImmutableVisualPlayQueueControlStateElement withOnDisabledVisualPlayQueueControlSelected(Method... methodArr) {
        return new ImmutableVisualPlayQueueControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledVisualPlayQueueControlSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableVisualPlayQueueControlStateElement withOnDisabledVisualPlayQueueControlSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledVisualPlayQueueControlSelected == iterable) {
            return this;
        }
        return new ImmutableVisualPlayQueueControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledVisualPlayQueueControlSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVisualPlayQueueControlStateElement) && equalTo(0, (ImmutableVisualPlayQueueControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableVisualPlayQueueControlStateElement immutableVisualPlayQueueControlStateElement) {
        return this.description.equals(immutableVisualPlayQueueControlStateElement.description) && this.altText.equals(immutableVisualPlayQueueControlStateElement.altText) && this.isDisabled.equals(immutableVisualPlayQueueControlStateElement.isDisabled) && this.onEnabledVisualPlayQueueControlSelected.equals(immutableVisualPlayQueueControlStateElement.onEnabledVisualPlayQueueControlSelected) && this.onDisabledVisualPlayQueueControlSelected.equals(immutableVisualPlayQueueControlStateElement.onDisabledVisualPlayQueueControlSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.description.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altText.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.isDisabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onEnabledVisualPlayQueueControlSelected.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onDisabledVisualPlayQueueControlSelected.hashCode();
    }

    public String toString() {
        return "VisualPlayQueueControlStateElement{description=" + this.description + ", altText=" + this.altText + ", isDisabled=" + this.isDisabled + ", onEnabledVisualPlayQueueControlSelected=" + this.onEnabledVisualPlayQueueControlSelected + ", onDisabledVisualPlayQueueControlSelected=" + this.onDisabledVisualPlayQueueControlSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VisualPlayQueueControlStateElement", generator = "Immutables")
    static final class Json extends VisualPlayQueueControlStateElement {

        @Nullable
        String altText;

        @Nullable
        String description;

        @Nullable
        Boolean isDisabled;
        boolean onDisabledVisualPlayQueueControlSelectedIsSet;
        boolean onEnabledVisualPlayQueueControlSelectedIsSet;

        @Nullable
        List<Method> onEnabledVisualPlayQueueControlSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledVisualPlayQueueControlSelected = Collections.emptyList();

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

        @JsonProperty("onEnabledVisualPlayQueueControlSelected")
        public void setOnEnabledVisualPlayQueueControlSelected(List<Method> list) {
            this.onEnabledVisualPlayQueueControlSelected = list;
            this.onEnabledVisualPlayQueueControlSelectedIsSet = true;
        }

        @JsonProperty("onDisabledVisualPlayQueueControlSelected")
        public void setOnDisabledVisualPlayQueueControlSelected(List<Method> list) {
            this.onDisabledVisualPlayQueueControlSelected = list;
            this.onDisabledVisualPlayQueueControlSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.VisualPlayQueueControlStateElement
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.VisualPlayQueueControlStateElement
        public String altText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.VisualPlayQueueControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.VisualPlayQueueControlStateElement
        public List<Method> onEnabledVisualPlayQueueControlSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.VisualPlayQueueControlStateElement
        public List<Method> onDisabledVisualPlayQueueControlSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVisualPlayQueueControlStateElement fromJson(Json json) {
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
        if (json.onEnabledVisualPlayQueueControlSelectedIsSet) {
            builder.addAllOnEnabledVisualPlayQueueControlSelected(json.onEnabledVisualPlayQueueControlSelected);
        }
        if (json.onDisabledVisualPlayQueueControlSelectedIsSet) {
            builder.addAllOnDisabledVisualPlayQueueControlSelected(json.onDisabledVisualPlayQueueControlSelected);
        }
        return builder.build();
    }

    public static ImmutableVisualPlayQueueControlStateElement copyOf(VisualPlayQueueControlStateElement visualPlayQueueControlStateElement) {
        if (visualPlayQueueControlStateElement instanceof ImmutableVisualPlayQueueControlStateElement) {
            return (ImmutableVisualPlayQueueControlStateElement) visualPlayQueueControlStateElement;
        }
        return builder().from(visualPlayQueueControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VisualPlayQueueControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ALT_TEXT = 2;
        private static final long INIT_BIT_DESCRIPTION = 1;
        private static final long OPT_BIT_ON_DISABLED_VISUAL_PLAY_QUEUE_CONTROL_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_VISUAL_PLAY_QUEUE_CONTROL_SELECTED = 1;

        @Nullable
        private String altText;

        @Nullable
        private String description;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledVisualPlayQueueControlSelected;
        private List<Method> onEnabledVisualPlayQueueControlSelected;
        private long optBits;

        private Builder() {
            this.initBits = 3L;
            this.onEnabledVisualPlayQueueControlSelected = new ArrayList();
            this.onDisabledVisualPlayQueueControlSelected = new ArrayList();
        }

        public final Builder from(VisualPlayQueueControlStateElement visualPlayQueueControlStateElement) {
            ImmutableVisualPlayQueueControlStateElement.requireNonNull(visualPlayQueueControlStateElement, "instance");
            description(visualPlayQueueControlStateElement.description());
            altText(visualPlayQueueControlStateElement.altText());
            isDisabled(visualPlayQueueControlStateElement.isDisabled());
            addAllOnEnabledVisualPlayQueueControlSelected(visualPlayQueueControlStateElement.onEnabledVisualPlayQueueControlSelected());
            addAllOnDisabledVisualPlayQueueControlSelected(visualPlayQueueControlStateElement.onDisabledVisualPlayQueueControlSelected());
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(String str) {
            this.description = (String) ImmutableVisualPlayQueueControlStateElement.requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("altText")
        public final Builder altText(String str) {
            this.altText = (String) ImmutableVisualPlayQueueControlStateElement.requireNonNull(str, "altText");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableVisualPlayQueueControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledVisualPlayQueueControlSelected(Method method) {
            this.onEnabledVisualPlayQueueControlSelected.add((Method) ImmutableVisualPlayQueueControlStateElement.requireNonNull(method, "onEnabledVisualPlayQueueControlSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledVisualPlayQueueControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledVisualPlayQueueControlSelected.add((Method) ImmutableVisualPlayQueueControlStateElement.requireNonNull(method, "onEnabledVisualPlayQueueControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledVisualPlayQueueControlSelected")
        public final Builder onEnabledVisualPlayQueueControlSelected(Iterable<? extends Method> iterable) {
            this.onEnabledVisualPlayQueueControlSelected.clear();
            return addAllOnEnabledVisualPlayQueueControlSelected(iterable);
        }

        public final Builder addAllOnEnabledVisualPlayQueueControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledVisualPlayQueueControlSelected.add((Method) ImmutableVisualPlayQueueControlStateElement.requireNonNull(it.next(), "onEnabledVisualPlayQueueControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledVisualPlayQueueControlSelected(Method method) {
            this.onDisabledVisualPlayQueueControlSelected.add((Method) ImmutableVisualPlayQueueControlStateElement.requireNonNull(method, "onDisabledVisualPlayQueueControlSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledVisualPlayQueueControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledVisualPlayQueueControlSelected.add((Method) ImmutableVisualPlayQueueControlStateElement.requireNonNull(method, "onDisabledVisualPlayQueueControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledVisualPlayQueueControlSelected")
        public final Builder onDisabledVisualPlayQueueControlSelected(Iterable<? extends Method> iterable) {
            this.onDisabledVisualPlayQueueControlSelected.clear();
            return addAllOnDisabledVisualPlayQueueControlSelected(iterable);
        }

        public final Builder addAllOnDisabledVisualPlayQueueControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledVisualPlayQueueControlSelected.add((Method) ImmutableVisualPlayQueueControlStateElement.requireNonNull(it.next(), "onDisabledVisualPlayQueueControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableVisualPlayQueueControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableVisualPlayQueueControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledVisualPlayQueueControlSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledVisualPlayQueueControlSelectedIsSet() {
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
            return "Cannot build VisualPlayQueueControlStateElement, some of required attributes are not set " + arrayList;
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
