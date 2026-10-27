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
@Generated(from = "NextControlStateElement", generator = "Immutables")
public final class ImmutableNextControlStateElement extends NextControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String altText;
    private final String description;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledNextControlSelected;
    private final List<Method> onEnabledNextControlSelected;

    private ImmutableNextControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.description = builder.description;
        this.altText = builder.altText;
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledNextControlSelectedIsSet()) {
            this.initShim.onEnabledNextControlSelected(createUnmodifiableList(true, builder.onEnabledNextControlSelected));
        }
        if (builder.onDisabledNextControlSelectedIsSet()) {
            this.initShim.onDisabledNextControlSelected(createUnmodifiableList(true, builder.onDisabledNextControlSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledNextControlSelected = this.initShim.onEnabledNextControlSelected();
        this.onDisabledNextControlSelected = this.initShim.onDisabledNextControlSelected();
        this.initShim = null;
    }

    private ImmutableNextControlStateElement(String str, String str2, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.description = str;
        this.altText = str2;
        this.isDisabled = bool;
        this.onEnabledNextControlSelected = list;
        this.onDisabledNextControlSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "NextControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledNextControlSelected;
        private byte onDisabledNextControlSelectedBuildStage;
        private List<Method> onEnabledNextControlSelected;
        private byte onEnabledNextControlSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledNextControlSelectedBuildStage = (byte) 0;
            this.onDisabledNextControlSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableNextControlStateElement.requireNonNull(ImmutableNextControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledNextControlSelected() {
            byte b = this.onEnabledNextControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledNextControlSelectedBuildStage = (byte) -1;
                this.onEnabledNextControlSelected = ImmutableNextControlStateElement.createUnmodifiableList(false, ImmutableNextControlStateElement.createSafeList(ImmutableNextControlStateElement.super.onEnabledNextControlSelected(), true, false));
                this.onEnabledNextControlSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledNextControlSelected;
        }

        void onEnabledNextControlSelected(List<Method> list) {
            this.onEnabledNextControlSelected = list;
            this.onEnabledNextControlSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledNextControlSelected() {
            byte b = this.onDisabledNextControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledNextControlSelectedBuildStage = (byte) -1;
                this.onDisabledNextControlSelected = ImmutableNextControlStateElement.createUnmodifiableList(false, ImmutableNextControlStateElement.createSafeList(ImmutableNextControlStateElement.super.onDisabledNextControlSelected(), true, false));
                this.onDisabledNextControlSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledNextControlSelected;
        }

        void onDisabledNextControlSelected(List<Method> list) {
            this.onDisabledNextControlSelected = list;
            this.onDisabledNextControlSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledNextControlSelectedBuildStage == -1) {
                arrayList.add("onEnabledNextControlSelected");
            }
            if (this.onDisabledNextControlSelectedBuildStage == -1) {
                arrayList.add("onDisabledNextControlSelected");
            }
            return "Cannot build NextControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.NextControlStateElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    public String description() {
        return this.description;
    }

    @Override // PlaybackInterface.v1_0.NextControlStateElement
    @JsonProperty("altText")
    public String altText() {
        return this.altText;
    }

    @Override // PlaybackInterface.v1_0.NextControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.NextControlStateElement
    @JsonProperty("onEnabledNextControlSelected")
    public List<Method> onEnabledNextControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledNextControlSelected();
        }
        return this.onEnabledNextControlSelected;
    }

    @Override // PlaybackInterface.v1_0.NextControlStateElement
    @JsonProperty("onDisabledNextControlSelected")
    public List<Method> onDisabledNextControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledNextControlSelected();
        }
        return this.onDisabledNextControlSelected;
    }

    public final ImmutableNextControlStateElement withDescription(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
        return this.description.equals(str2) ? this : new ImmutableNextControlStateElement(str2, this.altText, this.isDisabled, this.onEnabledNextControlSelected, this.onDisabledNextControlSelected);
    }

    public final ImmutableNextControlStateElement withAltText(String str) {
        String str2 = (String) requireNonNull(str, "altText");
        return this.altText.equals(str2) ? this : new ImmutableNextControlStateElement(this.description, str2, this.isDisabled, this.onEnabledNextControlSelected, this.onDisabledNextControlSelected);
    }

    public final ImmutableNextControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableNextControlStateElement(this.description, this.altText, bool2, this.onEnabledNextControlSelected, this.onDisabledNextControlSelected);
    }

    public final ImmutableNextControlStateElement withOnEnabledNextControlSelected(Method... methodArr) {
        return new ImmutableNextControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledNextControlSelected);
    }

    public final ImmutableNextControlStateElement withOnEnabledNextControlSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledNextControlSelected == iterable) {
            return this;
        }
        return new ImmutableNextControlStateElement(this.description, this.altText, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledNextControlSelected);
    }

    public final ImmutableNextControlStateElement withOnDisabledNextControlSelected(Method... methodArr) {
        return new ImmutableNextControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledNextControlSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableNextControlStateElement withOnDisabledNextControlSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledNextControlSelected == iterable) {
            return this;
        }
        return new ImmutableNextControlStateElement(this.description, this.altText, this.isDisabled, this.onEnabledNextControlSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableNextControlStateElement) && equalTo(0, (ImmutableNextControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableNextControlStateElement immutableNextControlStateElement) {
        return this.description.equals(immutableNextControlStateElement.description) && this.altText.equals(immutableNextControlStateElement.altText) && this.isDisabled.equals(immutableNextControlStateElement.isDisabled) && this.onEnabledNextControlSelected.equals(immutableNextControlStateElement.onEnabledNextControlSelected) && this.onDisabledNextControlSelected.equals(immutableNextControlStateElement.onDisabledNextControlSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.description.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altText.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.isDisabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onEnabledNextControlSelected.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onDisabledNextControlSelected.hashCode();
    }

    public String toString() {
        return "NextControlStateElement{description=" + this.description + ", altText=" + this.altText + ", isDisabled=" + this.isDisabled + ", onEnabledNextControlSelected=" + this.onEnabledNextControlSelected + ", onDisabledNextControlSelected=" + this.onDisabledNextControlSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "NextControlStateElement", generator = "Immutables")
    static final class Json extends NextControlStateElement {

        @Nullable
        String altText;

        @Nullable
        String description;

        @Nullable
        Boolean isDisabled;
        boolean onDisabledNextControlSelectedIsSet;
        boolean onEnabledNextControlSelectedIsSet;

        @Nullable
        List<Method> onEnabledNextControlSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledNextControlSelected = Collections.emptyList();

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

        @JsonProperty("onEnabledNextControlSelected")
        public void setOnEnabledNextControlSelected(List<Method> list) {
            this.onEnabledNextControlSelected = list;
            this.onEnabledNextControlSelectedIsSet = true;
        }

        @JsonProperty("onDisabledNextControlSelected")
        public void setOnDisabledNextControlSelected(List<Method> list) {
            this.onDisabledNextControlSelected = list;
            this.onDisabledNextControlSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.NextControlStateElement
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.NextControlStateElement
        public String altText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.NextControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.NextControlStateElement
        public List<Method> onEnabledNextControlSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.NextControlStateElement
        public List<Method> onDisabledNextControlSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableNextControlStateElement fromJson(Json json) {
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
        if (json.onEnabledNextControlSelectedIsSet) {
            builder.addAllOnEnabledNextControlSelected(json.onEnabledNextControlSelected);
        }
        if (json.onDisabledNextControlSelectedIsSet) {
            builder.addAllOnDisabledNextControlSelected(json.onDisabledNextControlSelected);
        }
        return builder.build();
    }

    public static ImmutableNextControlStateElement copyOf(NextControlStateElement nextControlStateElement) {
        if (nextControlStateElement instanceof ImmutableNextControlStateElement) {
            return (ImmutableNextControlStateElement) nextControlStateElement;
        }
        return builder().from(nextControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "NextControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ALT_TEXT = 2;
        private static final long INIT_BIT_DESCRIPTION = 1;
        private static final long OPT_BIT_ON_DISABLED_NEXT_CONTROL_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_NEXT_CONTROL_SELECTED = 1;

        @Nullable
        private String altText;

        @Nullable
        private String description;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledNextControlSelected;
        private List<Method> onEnabledNextControlSelected;
        private long optBits;

        private Builder() {
            this.initBits = 3L;
            this.onEnabledNextControlSelected = new ArrayList();
            this.onDisabledNextControlSelected = new ArrayList();
        }

        public final Builder from(NextControlStateElement nextControlStateElement) {
            ImmutableNextControlStateElement.requireNonNull(nextControlStateElement, "instance");
            description(nextControlStateElement.description());
            altText(nextControlStateElement.altText());
            isDisabled(nextControlStateElement.isDisabled());
            addAllOnEnabledNextControlSelected(nextControlStateElement.onEnabledNextControlSelected());
            addAllOnDisabledNextControlSelected(nextControlStateElement.onDisabledNextControlSelected());
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(String str) {
            this.description = (String) ImmutableNextControlStateElement.requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("altText")
        public final Builder altText(String str) {
            this.altText = (String) ImmutableNextControlStateElement.requireNonNull(str, "altText");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableNextControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledNextControlSelected(Method method) {
            this.onEnabledNextControlSelected.add((Method) ImmutableNextControlStateElement.requireNonNull(method, "onEnabledNextControlSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledNextControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledNextControlSelected.add((Method) ImmutableNextControlStateElement.requireNonNull(method, "onEnabledNextControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledNextControlSelected")
        public final Builder onEnabledNextControlSelected(Iterable<? extends Method> iterable) {
            this.onEnabledNextControlSelected.clear();
            return addAllOnEnabledNextControlSelected(iterable);
        }

        public final Builder addAllOnEnabledNextControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledNextControlSelected.add((Method) ImmutableNextControlStateElement.requireNonNull(it.next(), "onEnabledNextControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledNextControlSelected(Method method) {
            this.onDisabledNextControlSelected.add((Method) ImmutableNextControlStateElement.requireNonNull(method, "onDisabledNextControlSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledNextControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledNextControlSelected.add((Method) ImmutableNextControlStateElement.requireNonNull(method, "onDisabledNextControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledNextControlSelected")
        public final Builder onDisabledNextControlSelected(Iterable<? extends Method> iterable) {
            this.onDisabledNextControlSelected.clear();
            return addAllOnDisabledNextControlSelected(iterable);
        }

        public final Builder addAllOnDisabledNextControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledNextControlSelected.add((Method) ImmutableNextControlStateElement.requireNonNull(it.next(), "onDisabledNextControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableNextControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableNextControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledNextControlSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledNextControlSelectedIsSet() {
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
            return "Cannot build NextControlStateElement, some of required attributes are not set " + arrayList;
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
