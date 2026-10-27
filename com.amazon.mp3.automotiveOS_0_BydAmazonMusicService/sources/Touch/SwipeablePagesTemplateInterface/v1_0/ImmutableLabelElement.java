package Touch.SwipeablePagesTemplateInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
@Generated(from = "LabelElement", generator = "Immutables")
public final class ImmutableLabelElement extends LabelElement {
    private final List<String> icons;
    private final String text;

    private ImmutableLabelElement(Builder builder) {
        List<String> listCreateUnmodifiableList;
        this.text = builder.text;
        if (!builder.iconsIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.icons(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.icons);
        }
        this.icons = listCreateUnmodifiableList;
    }

    private ImmutableLabelElement(List<String> list, String str) {
        this.icons = list;
        this.text = str;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.LabelElement
    @JsonProperty("icons")
    public List<String> icons() {
        return this.icons;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.LabelElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    public final ImmutableLabelElement withIcons(String... strArr) {
        return new ImmutableLabelElement((List<String>) createUnmodifiableList(false, createSafeList(Arrays.asList(strArr), true, false)), this.text);
    }

    public final ImmutableLabelElement withIcons(Iterable<String> iterable) {
        return this.icons == iterable ? this : new ImmutableLabelElement((List<String>) createUnmodifiableList(false, createSafeList(iterable, true, false)), this.text);
    }

    public final ImmutableLabelElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutableLabelElement(this.icons, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableLabelElement) && equalTo(0, (ImmutableLabelElement) obj);
    }

    private boolean equalTo(int i, ImmutableLabelElement immutableLabelElement) {
        return this.icons.equals(immutableLabelElement.icons) && this.text.equals(immutableLabelElement.text);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.icons.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.text.hashCode();
    }

    public String toString() {
        return "LabelElement{icons=" + this.icons + ", text=" + this.text + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "LabelElement", generator = "Immutables")
    static final class Json extends LabelElement {

        @Nullable
        List<String> icons = Collections.emptyList();
        boolean iconsIsSet;

        @Nullable
        String text;

        Json() {
        }

        @JsonProperty("icons")
        public void setIcons(List<String> list) {
            this.icons = list;
            this.iconsIsSet = true;
        }

        @JsonProperty("text")
        public void setText(String str) {
            this.text = str;
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.LabelElement
        public List<String> icons() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.LabelElement
        public String text() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableLabelElement fromJson(Json json) {
        Builder builder = builder();
        if (json.iconsIsSet) {
            builder.addAllIcons(json.icons);
        }
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        return builder.build();
    }

    public static ImmutableLabelElement copyOf(LabelElement labelElement) {
        if (labelElement instanceof ImmutableLabelElement) {
            return (ImmutableLabelElement) labelElement;
        }
        return builder().from(labelElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "LabelElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TEXT = 1;
        private static final long OPT_BIT_ICONS = 1;
        private List<String> icons;
        private long initBits;
        private long optBits;

        @Nullable
        private String text;

        private Builder() {
            this.initBits = 1L;
            this.icons = new ArrayList();
        }

        public final Builder from(LabelElement labelElement) {
            ImmutableLabelElement.requireNonNull(labelElement, "instance");
            addAllIcons(labelElement.icons());
            text(labelElement.text());
            return this;
        }

        public final Builder addIcons(String str) {
            this.icons.add((String) ImmutableLabelElement.requireNonNull(str, "icons element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addIcons(String... strArr) {
            for (String str : strArr) {
                this.icons.add((String) ImmutableLabelElement.requireNonNull(str, "icons element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("icons")
        public final Builder icons(Iterable<String> iterable) {
            this.icons.clear();
            return addAllIcons(iterable);
        }

        public final Builder addAllIcons(Iterable<String> iterable) {
            Iterator<String> it = iterable.iterator();
            while (it.hasNext()) {
                this.icons.add((String) ImmutableLabelElement.requireNonNull(it.next(), "icons element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutableLabelElement.requireNonNull(str, "text");
            this.initBits &= -2;
            return this;
        }

        public ImmutableLabelElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableLabelElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean iconsIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("text");
            }
            return "Cannot build LabelElement, some of required attributes are not set " + arrayList;
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
