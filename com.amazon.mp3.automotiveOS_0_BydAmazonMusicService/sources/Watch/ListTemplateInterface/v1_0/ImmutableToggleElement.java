package Watch.ListTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
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
@Generated(from = "ToggleElement", generator = "Immutables")
public final class ImmutableToggleElement extends ToggleElement {
    private final Boolean isOn;
    private final List<Method> onToggledOff;
    private final List<Method> onToggledOn;
    private final String text;

    private ImmutableToggleElement(Builder builder) {
        Boolean bool;
        this.text = builder.text;
        this.onToggledOn = createUnmodifiableList(true, builder.onToggledOn);
        this.onToggledOff = createUnmodifiableList(true, builder.onToggledOff);
        if (builder.isOn == null) {
            bool = (Boolean) requireNonNull(super.isOn(), "isOn");
        } else {
            bool = builder.isOn;
        }
        this.isOn = bool;
    }

    private ImmutableToggleElement(String str, List<Method> list, List<Method> list2, Boolean bool) {
        this.text = str;
        this.onToggledOn = list;
        this.onToggledOff = list2;
        this.isOn = bool;
    }

    @Override // Watch.ListTemplateInterface.v1_0.ToggleElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    @Override // Watch.ListTemplateInterface.v1_0.ToggleElement
    @JsonProperty("onToggledOn")
    public List<Method> onToggledOn() {
        return this.onToggledOn;
    }

    @Override // Watch.ListTemplateInterface.v1_0.ToggleElement
    @JsonProperty("onToggledOff")
    public List<Method> onToggledOff() {
        return this.onToggledOff;
    }

    @Override // Watch.ListTemplateInterface.v1_0.ToggleElement
    @JsonProperty("isOn")
    public Boolean isOn() {
        return this.isOn;
    }

    public final ImmutableToggleElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutableToggleElement(str2, this.onToggledOn, this.onToggledOff, this.isOn);
    }

    public final ImmutableToggleElement withOnToggledOn(Method... methodArr) {
        return new ImmutableToggleElement(this.text, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onToggledOff, this.isOn);
    }

    public final ImmutableToggleElement withOnToggledOn(Iterable<? extends Method> iterable) {
        if (this.onToggledOn == iterable) {
            return this;
        }
        return new ImmutableToggleElement(this.text, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onToggledOff, this.isOn);
    }

    public final ImmutableToggleElement withOnToggledOff(Method... methodArr) {
        return new ImmutableToggleElement(this.text, this.onToggledOn, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.isOn);
    }

    public final ImmutableToggleElement withOnToggledOff(Iterable<? extends Method> iterable) {
        if (this.onToggledOff == iterable) {
            return this;
        }
        return new ImmutableToggleElement(this.text, this.onToggledOn, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.isOn);
    }

    public final ImmutableToggleElement withIsOn(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isOn");
        return this.isOn.equals(bool2) ? this : new ImmutableToggleElement(this.text, this.onToggledOn, this.onToggledOff, bool2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableToggleElement) && equalTo(0, (ImmutableToggleElement) obj);
    }

    private boolean equalTo(int i, ImmutableToggleElement immutableToggleElement) {
        return this.text.equals(immutableToggleElement.text) && this.onToggledOn.equals(immutableToggleElement.onToggledOn) && this.onToggledOff.equals(immutableToggleElement.onToggledOff) && this.isOn.equals(immutableToggleElement.isOn);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.text.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onToggledOn.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onToggledOff.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.isOn.hashCode();
    }

    public String toString() {
        return "ToggleElement{text=" + this.text + ", onToggledOn=" + this.onToggledOn + ", onToggledOff=" + this.onToggledOff + ", isOn=" + this.isOn + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ToggleElement", generator = "Immutables")
    static final class Json extends ToggleElement {

        @Nullable
        Boolean isOn;

        @Nullable
        String text;

        @Nullable
        List<Method> onToggledOn = Collections.emptyList();

        @Nullable
        List<Method> onToggledOff = Collections.emptyList();

        Json() {
        }

        @JsonProperty("text")
        public void setText(String str) {
            this.text = str;
        }

        @JsonProperty("onToggledOn")
        public void setOnToggledOn(List<Method> list) {
            this.onToggledOn = list;
        }

        @JsonProperty("onToggledOff")
        public void setOnToggledOff(List<Method> list) {
            this.onToggledOff = list;
        }

        @JsonProperty("isOn")
        public void setIsOn(Boolean bool) {
            this.isOn = bool;
        }

        @Override // Watch.ListTemplateInterface.v1_0.ToggleElement
        public String text() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.ToggleElement
        public List<Method> onToggledOn() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.ToggleElement
        public List<Method> onToggledOff() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.ToggleElement
        public Boolean isOn() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableToggleElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        List<Method> list = json.onToggledOn;
        if (list != null) {
            builder.addAllOnToggledOn(list);
        }
        List<Method> list2 = json.onToggledOff;
        if (list2 != null) {
            builder.addAllOnToggledOff(list2);
        }
        Boolean bool = json.isOn;
        if (bool != null) {
            builder.isOn(bool);
        }
        return builder.build();
    }

    public static ImmutableToggleElement copyOf(ToggleElement toggleElement) {
        if (toggleElement instanceof ImmutableToggleElement) {
            return (ImmutableToggleElement) toggleElement;
        }
        return builder().from(toggleElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ToggleElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TEXT = 1;
        private long initBits;

        @Nullable
        private Boolean isOn;
        private List<Method> onToggledOff;
        private List<Method> onToggledOn;

        @Nullable
        private String text;

        private Builder() {
            this.initBits = 1L;
            this.onToggledOn = new ArrayList();
            this.onToggledOff = new ArrayList();
        }

        public final Builder from(ToggleElement toggleElement) {
            ImmutableToggleElement.requireNonNull(toggleElement, "instance");
            text(toggleElement.text());
            addAllOnToggledOn(toggleElement.onToggledOn());
            addAllOnToggledOff(toggleElement.onToggledOff());
            isOn(toggleElement.isOn());
            return this;
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutableToggleElement.requireNonNull(str, "text");
            this.initBits &= -2;
            return this;
        }

        public final Builder addOnToggledOn(Method method) {
            this.onToggledOn.add((Method) ImmutableToggleElement.requireNonNull(method, "onToggledOn element"));
            return this;
        }

        public final Builder addOnToggledOn(Method... methodArr) {
            for (Method method : methodArr) {
                this.onToggledOn.add((Method) ImmutableToggleElement.requireNonNull(method, "onToggledOn element"));
            }
            return this;
        }

        @JsonProperty("onToggledOn")
        public final Builder onToggledOn(Iterable<? extends Method> iterable) {
            this.onToggledOn.clear();
            return addAllOnToggledOn(iterable);
        }

        public final Builder addAllOnToggledOn(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onToggledOn.add((Method) ImmutableToggleElement.requireNonNull(it.next(), "onToggledOn element"));
            }
            return this;
        }

        public final Builder addOnToggledOff(Method method) {
            this.onToggledOff.add((Method) ImmutableToggleElement.requireNonNull(method, "onToggledOff element"));
            return this;
        }

        public final Builder addOnToggledOff(Method... methodArr) {
            for (Method method : methodArr) {
                this.onToggledOff.add((Method) ImmutableToggleElement.requireNonNull(method, "onToggledOff element"));
            }
            return this;
        }

        @JsonProperty("onToggledOff")
        public final Builder onToggledOff(Iterable<? extends Method> iterable) {
            this.onToggledOff.clear();
            return addAllOnToggledOff(iterable);
        }

        public final Builder addAllOnToggledOff(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onToggledOff.add((Method) ImmutableToggleElement.requireNonNull(it.next(), "onToggledOff element"));
            }
            return this;
        }

        @JsonProperty("isOn")
        public final Builder isOn(Boolean bool) {
            this.isOn = (Boolean) ImmutableToggleElement.requireNonNull(bool, "isOn");
            return this;
        }

        public ImmutableToggleElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableToggleElement(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("text");
            }
            return "Cannot build ToggleElement, some of required attributes are not set " + arrayList;
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
