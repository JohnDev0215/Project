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
@Generated(from = "PlaybackSpeedControlStateElement", generator = "Immutables")
public final class ImmutablePlaybackSpeedControlStateElement extends PlaybackSpeedControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String altText;
    private final String description;
    private final String dismissText;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledPlaybackSpeedControlElementSelected;
    private final List<Method> onEnabledPlaybackSpeedControlElementSelected;
    private final List<PlaybackSpeedControlOptionElement> options;

    private ImmutablePlaybackSpeedControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.description = builder.description;
        this.altText = builder.altText;
        this.options = createUnmodifiableList(true, builder.options);
        this.dismissText = builder.dismissText;
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledPlaybackSpeedControlElementSelectedIsSet()) {
            this.initShim.onEnabledPlaybackSpeedControlElementSelected(createUnmodifiableList(true, builder.onEnabledPlaybackSpeedControlElementSelected));
        }
        if (builder.onDisabledPlaybackSpeedControlElementSelectedIsSet()) {
            this.initShim.onDisabledPlaybackSpeedControlElementSelected(createUnmodifiableList(true, builder.onDisabledPlaybackSpeedControlElementSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledPlaybackSpeedControlElementSelected = this.initShim.onEnabledPlaybackSpeedControlElementSelected();
        this.onDisabledPlaybackSpeedControlElementSelected = this.initShim.onDisabledPlaybackSpeedControlElementSelected();
        this.initShim = null;
    }

    private ImmutablePlaybackSpeedControlStateElement(String str, String str2, List<PlaybackSpeedControlOptionElement> list, String str3, Boolean bool, List<Method> list2, List<Method> list3) {
        this.initShim = new InitShim();
        this.description = str;
        this.altText = str2;
        this.options = list;
        this.dismissText = str3;
        this.isDisabled = bool;
        this.onEnabledPlaybackSpeedControlElementSelected = list2;
        this.onDisabledPlaybackSpeedControlElementSelected = list3;
        this.initShim = null;
    }

    @Generated(from = "PlaybackSpeedControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledPlaybackSpeedControlElementSelected;
        private byte onDisabledPlaybackSpeedControlElementSelectedBuildStage;
        private List<Method> onEnabledPlaybackSpeedControlElementSelected;
        private byte onEnabledPlaybackSpeedControlElementSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledPlaybackSpeedControlElementSelectedBuildStage = (byte) 0;
            this.onDisabledPlaybackSpeedControlElementSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutablePlaybackSpeedControlStateElement.requireNonNull(ImmutablePlaybackSpeedControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledPlaybackSpeedControlElementSelected() {
            byte b = this.onEnabledPlaybackSpeedControlElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledPlaybackSpeedControlElementSelectedBuildStage = (byte) -1;
                this.onEnabledPlaybackSpeedControlElementSelected = ImmutablePlaybackSpeedControlStateElement.createUnmodifiableList(false, ImmutablePlaybackSpeedControlStateElement.createSafeList(ImmutablePlaybackSpeedControlStateElement.super.onEnabledPlaybackSpeedControlElementSelected(), true, false));
                this.onEnabledPlaybackSpeedControlElementSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledPlaybackSpeedControlElementSelected;
        }

        void onEnabledPlaybackSpeedControlElementSelected(List<Method> list) {
            this.onEnabledPlaybackSpeedControlElementSelected = list;
            this.onEnabledPlaybackSpeedControlElementSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledPlaybackSpeedControlElementSelected() {
            byte b = this.onDisabledPlaybackSpeedControlElementSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledPlaybackSpeedControlElementSelectedBuildStage = (byte) -1;
                this.onDisabledPlaybackSpeedControlElementSelected = ImmutablePlaybackSpeedControlStateElement.createUnmodifiableList(false, ImmutablePlaybackSpeedControlStateElement.createSafeList(ImmutablePlaybackSpeedControlStateElement.super.onDisabledPlaybackSpeedControlElementSelected(), true, false));
                this.onDisabledPlaybackSpeedControlElementSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledPlaybackSpeedControlElementSelected;
        }

        void onDisabledPlaybackSpeedControlElementSelected(List<Method> list) {
            this.onDisabledPlaybackSpeedControlElementSelected = list;
            this.onDisabledPlaybackSpeedControlElementSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledPlaybackSpeedControlElementSelectedBuildStage == -1) {
                arrayList.add("onEnabledPlaybackSpeedControlElementSelected");
            }
            if (this.onDisabledPlaybackSpeedControlElementSelectedBuildStage == -1) {
                arrayList.add("onDisabledPlaybackSpeedControlElementSelected");
            }
            return "Cannot build PlaybackSpeedControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    public String description() {
        return this.description;
    }

    @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
    @JsonProperty("altText")
    public String altText() {
        return this.altText;
    }

    @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
    @JsonProperty("options")
    public List<PlaybackSpeedControlOptionElement> options() {
        return this.options;
    }

    @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
    @JsonProperty("dismissText")
    public String dismissText() {
        return this.dismissText;
    }

    @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
    @JsonProperty("onEnabledPlaybackSpeedControlElementSelected")
    public List<Method> onEnabledPlaybackSpeedControlElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledPlaybackSpeedControlElementSelected();
        }
        return this.onEnabledPlaybackSpeedControlElementSelected;
    }

    @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
    @JsonProperty("onDisabledPlaybackSpeedControlElementSelected")
    public List<Method> onDisabledPlaybackSpeedControlElementSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledPlaybackSpeedControlElementSelected();
        }
        return this.onDisabledPlaybackSpeedControlElementSelected;
    }

    public final ImmutablePlaybackSpeedControlStateElement withDescription(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
        return this.description.equals(str2) ? this : new ImmutablePlaybackSpeedControlStateElement(str2, this.altText, this.options, this.dismissText, this.isDisabled, this.onEnabledPlaybackSpeedControlElementSelected, this.onDisabledPlaybackSpeedControlElementSelected);
    }

    public final ImmutablePlaybackSpeedControlStateElement withAltText(String str) {
        String str2 = (String) requireNonNull(str, "altText");
        return this.altText.equals(str2) ? this : new ImmutablePlaybackSpeedControlStateElement(this.description, str2, this.options, this.dismissText, this.isDisabled, this.onEnabledPlaybackSpeedControlElementSelected, this.onDisabledPlaybackSpeedControlElementSelected);
    }

    public final ImmutablePlaybackSpeedControlStateElement withOptions(PlaybackSpeedControlOptionElement... playbackSpeedControlOptionElementArr) {
        return new ImmutablePlaybackSpeedControlStateElement(this.description, this.altText, createUnmodifiableList(false, createSafeList(Arrays.asList(playbackSpeedControlOptionElementArr), true, false)), this.dismissText, this.isDisabled, this.onEnabledPlaybackSpeedControlElementSelected, this.onDisabledPlaybackSpeedControlElementSelected);
    }

    public final ImmutablePlaybackSpeedControlStateElement withOptions(Iterable<? extends PlaybackSpeedControlOptionElement> iterable) {
        if (this.options == iterable) {
            return this;
        }
        return new ImmutablePlaybackSpeedControlStateElement(this.description, this.altText, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.dismissText, this.isDisabled, this.onEnabledPlaybackSpeedControlElementSelected, this.onDisabledPlaybackSpeedControlElementSelected);
    }

    public final ImmutablePlaybackSpeedControlStateElement withDismissText(String str) {
        String str2 = (String) requireNonNull(str, "dismissText");
        return this.dismissText.equals(str2) ? this : new ImmutablePlaybackSpeedControlStateElement(this.description, this.altText, this.options, str2, this.isDisabled, this.onEnabledPlaybackSpeedControlElementSelected, this.onDisabledPlaybackSpeedControlElementSelected);
    }

    public final ImmutablePlaybackSpeedControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutablePlaybackSpeedControlStateElement(this.description, this.altText, this.options, this.dismissText, bool2, this.onEnabledPlaybackSpeedControlElementSelected, this.onDisabledPlaybackSpeedControlElementSelected);
    }

    public final ImmutablePlaybackSpeedControlStateElement withOnEnabledPlaybackSpeedControlElementSelected(Method... methodArr) {
        return new ImmutablePlaybackSpeedControlStateElement(this.description, this.altText, this.options, this.dismissText, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledPlaybackSpeedControlElementSelected);
    }

    public final ImmutablePlaybackSpeedControlStateElement withOnEnabledPlaybackSpeedControlElementSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledPlaybackSpeedControlElementSelected == iterable) {
            return this;
        }
        return new ImmutablePlaybackSpeedControlStateElement(this.description, this.altText, this.options, this.dismissText, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledPlaybackSpeedControlElementSelected);
    }

    public final ImmutablePlaybackSpeedControlStateElement withOnDisabledPlaybackSpeedControlElementSelected(Method... methodArr) {
        return new ImmutablePlaybackSpeedControlStateElement(this.description, this.altText, this.options, this.dismissText, this.isDisabled, this.onEnabledPlaybackSpeedControlElementSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutablePlaybackSpeedControlStateElement withOnDisabledPlaybackSpeedControlElementSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledPlaybackSpeedControlElementSelected == iterable) {
            return this;
        }
        return new ImmutablePlaybackSpeedControlStateElement(this.description, this.altText, this.options, this.dismissText, this.isDisabled, this.onEnabledPlaybackSpeedControlElementSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlaybackSpeedControlStateElement) && equalTo(0, (ImmutablePlaybackSpeedControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutablePlaybackSpeedControlStateElement immutablePlaybackSpeedControlStateElement) {
        return this.description.equals(immutablePlaybackSpeedControlStateElement.description) && this.altText.equals(immutablePlaybackSpeedControlStateElement.altText) && this.options.equals(immutablePlaybackSpeedControlStateElement.options) && this.dismissText.equals(immutablePlaybackSpeedControlStateElement.dismissText) && this.isDisabled.equals(immutablePlaybackSpeedControlStateElement.isDisabled) && this.onEnabledPlaybackSpeedControlElementSelected.equals(immutablePlaybackSpeedControlStateElement.onEnabledPlaybackSpeedControlElementSelected) && this.onDisabledPlaybackSpeedControlElementSelected.equals(immutablePlaybackSpeedControlStateElement.onDisabledPlaybackSpeedControlElementSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.description.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altText.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.options.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.dismissText.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.isDisabled.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.onEnabledPlaybackSpeedControlElementSelected.hashCode();
        return iHashCode6 + (iHashCode6 << 5) + this.onDisabledPlaybackSpeedControlElementSelected.hashCode();
    }

    public String toString() {
        return "PlaybackSpeedControlStateElement{description=" + this.description + ", altText=" + this.altText + ", options=" + this.options + ", dismissText=" + this.dismissText + ", isDisabled=" + this.isDisabled + ", onEnabledPlaybackSpeedControlElementSelected=" + this.onEnabledPlaybackSpeedControlElementSelected + ", onDisabledPlaybackSpeedControlElementSelected=" + this.onDisabledPlaybackSpeedControlElementSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PlaybackSpeedControlStateElement", generator = "Immutables")
    static final class Json extends PlaybackSpeedControlStateElement {

        @Nullable
        String altText;

        @Nullable
        String description;

        @Nullable
        String dismissText;

        @Nullable
        Boolean isDisabled;
        boolean onDisabledPlaybackSpeedControlElementSelectedIsSet;
        boolean onEnabledPlaybackSpeedControlElementSelectedIsSet;

        @Nullable
        List<PlaybackSpeedControlOptionElement> options = Collections.emptyList();

        @Nullable
        List<Method> onEnabledPlaybackSpeedControlElementSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledPlaybackSpeedControlElementSelected = Collections.emptyList();

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

        @JsonProperty("options")
        public void setOptions(List<PlaybackSpeedControlOptionElement> list) {
            this.options = list;
        }

        @JsonProperty("dismissText")
        public void setDismissText(String str) {
            this.dismissText = str;
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(Boolean bool) {
            this.isDisabled = bool;
        }

        @JsonProperty("onEnabledPlaybackSpeedControlElementSelected")
        public void setOnEnabledPlaybackSpeedControlElementSelected(List<Method> list) {
            this.onEnabledPlaybackSpeedControlElementSelected = list;
            this.onEnabledPlaybackSpeedControlElementSelectedIsSet = true;
        }

        @JsonProperty("onDisabledPlaybackSpeedControlElementSelected")
        public void setOnDisabledPlaybackSpeedControlElementSelected(List<Method> list) {
            this.onDisabledPlaybackSpeedControlElementSelected = list;
            this.onDisabledPlaybackSpeedControlElementSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
        public String altText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
        public List<PlaybackSpeedControlOptionElement> options() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
        public String dismissText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
        public List<Method> onEnabledPlaybackSpeedControlElementSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlaybackSpeedControlStateElement
        public List<Method> onDisabledPlaybackSpeedControlElementSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePlaybackSpeedControlStateElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.description;
        if (str != null) {
            builder.description(str);
        }
        String str2 = json.altText;
        if (str2 != null) {
            builder.altText(str2);
        }
        List<PlaybackSpeedControlOptionElement> list = json.options;
        if (list != null) {
            builder.addAllOptions(list);
        }
        String str3 = json.dismissText;
        if (str3 != null) {
            builder.dismissText(str3);
        }
        Boolean bool = json.isDisabled;
        if (bool != null) {
            builder.isDisabled(bool);
        }
        if (json.onEnabledPlaybackSpeedControlElementSelectedIsSet) {
            builder.addAllOnEnabledPlaybackSpeedControlElementSelected(json.onEnabledPlaybackSpeedControlElementSelected);
        }
        if (json.onDisabledPlaybackSpeedControlElementSelectedIsSet) {
            builder.addAllOnDisabledPlaybackSpeedControlElementSelected(json.onDisabledPlaybackSpeedControlElementSelected);
        }
        return builder.build();
    }

    public static ImmutablePlaybackSpeedControlStateElement copyOf(PlaybackSpeedControlStateElement playbackSpeedControlStateElement) {
        if (playbackSpeedControlStateElement instanceof ImmutablePlaybackSpeedControlStateElement) {
            return (ImmutablePlaybackSpeedControlStateElement) playbackSpeedControlStateElement;
        }
        return builder().from(playbackSpeedControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PlaybackSpeedControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ALT_TEXT = 2;
        private static final long INIT_BIT_DESCRIPTION = 1;
        private static final long INIT_BIT_DISMISS_TEXT = 4;
        private static final long OPT_BIT_ON_DISABLED_PLAYBACK_SPEED_CONTROL_ELEMENT_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_PLAYBACK_SPEED_CONTROL_ELEMENT_SELECTED = 1;

        @Nullable
        private String altText;

        @Nullable
        private String description;

        @Nullable
        private String dismissText;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledPlaybackSpeedControlElementSelected;
        private List<Method> onEnabledPlaybackSpeedControlElementSelected;
        private long optBits;
        private List<PlaybackSpeedControlOptionElement> options;

        private Builder() {
            this.initBits = 7L;
            this.options = new ArrayList();
            this.onEnabledPlaybackSpeedControlElementSelected = new ArrayList();
            this.onDisabledPlaybackSpeedControlElementSelected = new ArrayList();
        }

        public final Builder from(PlaybackSpeedControlStateElement playbackSpeedControlStateElement) {
            ImmutablePlaybackSpeedControlStateElement.requireNonNull(playbackSpeedControlStateElement, "instance");
            description(playbackSpeedControlStateElement.description());
            altText(playbackSpeedControlStateElement.altText());
            addAllOptions(playbackSpeedControlStateElement.options());
            dismissText(playbackSpeedControlStateElement.dismissText());
            isDisabled(playbackSpeedControlStateElement.isDisabled());
            addAllOnEnabledPlaybackSpeedControlElementSelected(playbackSpeedControlStateElement.onEnabledPlaybackSpeedControlElementSelected());
            addAllOnDisabledPlaybackSpeedControlElementSelected(playbackSpeedControlStateElement.onDisabledPlaybackSpeedControlElementSelected());
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(String str) {
            this.description = (String) ImmutablePlaybackSpeedControlStateElement.requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("altText")
        public final Builder altText(String str) {
            this.altText = (String) ImmutablePlaybackSpeedControlStateElement.requireNonNull(str, "altText");
            this.initBits &= -3;
            return this;
        }

        public final Builder addOptions(PlaybackSpeedControlOptionElement playbackSpeedControlOptionElement) {
            this.options.add((PlaybackSpeedControlOptionElement) ImmutablePlaybackSpeedControlStateElement.requireNonNull(playbackSpeedControlOptionElement, "options element"));
            return this;
        }

        public final Builder addOptions(PlaybackSpeedControlOptionElement... playbackSpeedControlOptionElementArr) {
            for (PlaybackSpeedControlOptionElement playbackSpeedControlOptionElement : playbackSpeedControlOptionElementArr) {
                this.options.add((PlaybackSpeedControlOptionElement) ImmutablePlaybackSpeedControlStateElement.requireNonNull(playbackSpeedControlOptionElement, "options element"));
            }
            return this;
        }

        @JsonProperty("options")
        public final Builder options(Iterable<? extends PlaybackSpeedControlOptionElement> iterable) {
            this.options.clear();
            return addAllOptions(iterable);
        }

        public final Builder addAllOptions(Iterable<? extends PlaybackSpeedControlOptionElement> iterable) {
            Iterator<? extends PlaybackSpeedControlOptionElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.options.add((PlaybackSpeedControlOptionElement) ImmutablePlaybackSpeedControlStateElement.requireNonNull(it.next(), "options element"));
            }
            return this;
        }

        @JsonProperty("dismissText")
        public final Builder dismissText(String str) {
            this.dismissText = (String) ImmutablePlaybackSpeedControlStateElement.requireNonNull(str, "dismissText");
            this.initBits &= -5;
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutablePlaybackSpeedControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledPlaybackSpeedControlElementSelected(Method method) {
            this.onEnabledPlaybackSpeedControlElementSelected.add((Method) ImmutablePlaybackSpeedControlStateElement.requireNonNull(method, "onEnabledPlaybackSpeedControlElementSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledPlaybackSpeedControlElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledPlaybackSpeedControlElementSelected.add((Method) ImmutablePlaybackSpeedControlStateElement.requireNonNull(method, "onEnabledPlaybackSpeedControlElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledPlaybackSpeedControlElementSelected")
        public final Builder onEnabledPlaybackSpeedControlElementSelected(Iterable<? extends Method> iterable) {
            this.onEnabledPlaybackSpeedControlElementSelected.clear();
            return addAllOnEnabledPlaybackSpeedControlElementSelected(iterable);
        }

        public final Builder addAllOnEnabledPlaybackSpeedControlElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledPlaybackSpeedControlElementSelected.add((Method) ImmutablePlaybackSpeedControlStateElement.requireNonNull(it.next(), "onEnabledPlaybackSpeedControlElementSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledPlaybackSpeedControlElementSelected(Method method) {
            this.onDisabledPlaybackSpeedControlElementSelected.add((Method) ImmutablePlaybackSpeedControlStateElement.requireNonNull(method, "onDisabledPlaybackSpeedControlElementSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledPlaybackSpeedControlElementSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledPlaybackSpeedControlElementSelected.add((Method) ImmutablePlaybackSpeedControlStateElement.requireNonNull(method, "onDisabledPlaybackSpeedControlElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledPlaybackSpeedControlElementSelected")
        public final Builder onDisabledPlaybackSpeedControlElementSelected(Iterable<? extends Method> iterable) {
            this.onDisabledPlaybackSpeedControlElementSelected.clear();
            return addAllOnDisabledPlaybackSpeedControlElementSelected(iterable);
        }

        public final Builder addAllOnDisabledPlaybackSpeedControlElementSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledPlaybackSpeedControlElementSelected.add((Method) ImmutablePlaybackSpeedControlStateElement.requireNonNull(it.next(), "onDisabledPlaybackSpeedControlElementSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutablePlaybackSpeedControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePlaybackSpeedControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledPlaybackSpeedControlElementSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledPlaybackSpeedControlElementSelectedIsSet() {
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
                arrayList.add("dismissText");
            }
            return "Cannot build PlaybackSpeedControlStateElement, some of required attributes are not set " + arrayList;
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
