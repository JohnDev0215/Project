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
@Generated(from = "MediaOptionElement", generator = "Immutables")
public final class ImmutableMediaOptionElement extends MediaOptionElement {
    private final String altText;
    private final String description;
    private final List<Method> onOptionSelected;

    private ImmutableMediaOptionElement(Builder builder) {
        List<Method> listCreateUnmodifiableList;
        this.description = builder.description;
        this.altText = builder.altText;
        if (!builder.onOptionSelectedIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.onOptionSelected(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.onOptionSelected);
        }
        this.onOptionSelected = listCreateUnmodifiableList;
    }

    private ImmutableMediaOptionElement(String str, String str2, List<Method> list) {
        this.description = str;
        this.altText = str2;
        this.onOptionSelected = list;
    }

    @Override // PlaybackInterface.v1_0.MediaOptionElement
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    public String description() {
        return this.description;
    }

    @Override // PlaybackInterface.v1_0.MediaOptionElement
    @JsonProperty("altText")
    public String altText() {
        return this.altText;
    }

    @Override // PlaybackInterface.v1_0.MediaOptionElement
    @JsonProperty("onOptionSelected")
    public List<Method> onOptionSelected() {
        return this.onOptionSelected;
    }

    public final ImmutableMediaOptionElement withDescription(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
        return this.description.equals(str2) ? this : new ImmutableMediaOptionElement(str2, this.altText, this.onOptionSelected);
    }

    public final ImmutableMediaOptionElement withAltText(String str) {
        String str2 = (String) requireNonNull(str, "altText");
        return this.altText.equals(str2) ? this : new ImmutableMediaOptionElement(this.description, str2, this.onOptionSelected);
    }

    public final ImmutableMediaOptionElement withOnOptionSelected(Method... methodArr) {
        return new ImmutableMediaOptionElement(this.description, this.altText, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableMediaOptionElement withOnOptionSelected(Iterable<? extends Method> iterable) {
        if (this.onOptionSelected == iterable) {
            return this;
        }
        return new ImmutableMediaOptionElement(this.description, this.altText, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMediaOptionElement) && equalTo(0, (ImmutableMediaOptionElement) obj);
    }

    private boolean equalTo(int i, ImmutableMediaOptionElement immutableMediaOptionElement) {
        return this.description.equals(immutableMediaOptionElement.description) && this.altText.equals(immutableMediaOptionElement.altText) && this.onOptionSelected.equals(immutableMediaOptionElement.onOptionSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.description.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altText.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.onOptionSelected.hashCode();
    }

    public String toString() {
        return "MediaOptionElement{description=" + this.description + ", altText=" + this.altText + ", onOptionSelected=" + this.onOptionSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MediaOptionElement", generator = "Immutables")
    static final class Json extends MediaOptionElement {

        @Nullable
        String altText;

        @Nullable
        String description;

        @Nullable
        List<Method> onOptionSelected = Collections.emptyList();
        boolean onOptionSelectedIsSet;

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

        @JsonProperty("onOptionSelected")
        public void setOnOptionSelected(List<Method> list) {
            this.onOptionSelected = list;
            this.onOptionSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.MediaOptionElement
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaOptionElement
        public String altText() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaOptionElement
        public List<Method> onOptionSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMediaOptionElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.description;
        if (str != null) {
            builder.description(str);
        }
        String str2 = json.altText;
        if (str2 != null) {
            builder.altText(str2);
        }
        if (json.onOptionSelectedIsSet) {
            builder.addAllOnOptionSelected(json.onOptionSelected);
        }
        return builder.build();
    }

    public static ImmutableMediaOptionElement copyOf(MediaOptionElement mediaOptionElement) {
        if (mediaOptionElement instanceof ImmutableMediaOptionElement) {
            return (ImmutableMediaOptionElement) mediaOptionElement;
        }
        return builder().from(mediaOptionElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MediaOptionElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ALT_TEXT = 2;
        private static final long INIT_BIT_DESCRIPTION = 1;
        private static final long OPT_BIT_ON_OPTION_SELECTED = 1;

        @Nullable
        private String altText;

        @Nullable
        private String description;
        private long initBits;
        private List<Method> onOptionSelected;
        private long optBits;

        private Builder() {
            this.initBits = 3L;
            this.onOptionSelected = new ArrayList();
        }

        public final Builder from(MediaOptionElement mediaOptionElement) {
            ImmutableMediaOptionElement.requireNonNull(mediaOptionElement, "instance");
            description(mediaOptionElement.description());
            altText(mediaOptionElement.altText());
            addAllOnOptionSelected(mediaOptionElement.onOptionSelected());
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(String str) {
            this.description = (String) ImmutableMediaOptionElement.requireNonNull(str, MediaTrack.ROLE_DESCRIPTION);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("altText")
        public final Builder altText(String str) {
            this.altText = (String) ImmutableMediaOptionElement.requireNonNull(str, "altText");
            this.initBits &= -3;
            return this;
        }

        public final Builder addOnOptionSelected(Method method) {
            this.onOptionSelected.add((Method) ImmutableMediaOptionElement.requireNonNull(method, "onOptionSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnOptionSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onOptionSelected.add((Method) ImmutableMediaOptionElement.requireNonNull(method, "onOptionSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onOptionSelected")
        public final Builder onOptionSelected(Iterable<? extends Method> iterable) {
            this.onOptionSelected.clear();
            return addAllOnOptionSelected(iterable);
        }

        public final Builder addAllOnOptionSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onOptionSelected.add((Method) ImmutableMediaOptionElement.requireNonNull(it.next(), "onOptionSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableMediaOptionElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableMediaOptionElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onOptionSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(MediaTrack.ROLE_DESCRIPTION);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("altText");
            }
            return "Cannot build MediaOptionElement, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
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

    private static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
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
