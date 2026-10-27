package Watch.ListTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
@Generated(from = "PlayFromSelectorItemElement", generator = "Immutables")
public final class ImmutablePlayFromSelectorItemElement extends PlayFromSelectorItemElement {
    private final String label;
    private final List<Method> onTurnOffTetheredModeSelected;
    private final List<Method> onTurnOnTetheredModeSelected;

    private ImmutablePlayFromSelectorItemElement(String str, List<Method> list, List<Method> list2) {
        this.label = str;
        this.onTurnOffTetheredModeSelected = list;
        this.onTurnOnTetheredModeSelected = list2;
    }

    @Override // Watch.ListTemplateInterface.v1_0.PlayFromSelectorItemElement
    @JsonProperty(ContextMappingConstants.LABEL)
    public String label() {
        return this.label;
    }

    @Override // Watch.ListTemplateInterface.v1_0.PlayFromSelectorItemElement
    @JsonProperty("onTurnOffTetheredModeSelected")
    public List<Method> onTurnOffTetheredModeSelected() {
        return this.onTurnOffTetheredModeSelected;
    }

    @Override // Watch.ListTemplateInterface.v1_0.PlayFromSelectorItemElement
    @JsonProperty("onTurnOnTetheredModeSelected")
    public List<Method> onTurnOnTetheredModeSelected() {
        return this.onTurnOnTetheredModeSelected;
    }

    public final ImmutablePlayFromSelectorItemElement withLabel(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.LABEL);
        return this.label.equals(str2) ? this : new ImmutablePlayFromSelectorItemElement(str2, this.onTurnOffTetheredModeSelected, this.onTurnOnTetheredModeSelected);
    }

    public final ImmutablePlayFromSelectorItemElement withOnTurnOffTetheredModeSelected(Method... methodArr) {
        return new ImmutablePlayFromSelectorItemElement(this.label, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onTurnOnTetheredModeSelected);
    }

    public final ImmutablePlayFromSelectorItemElement withOnTurnOffTetheredModeSelected(Iterable<? extends Method> iterable) {
        if (this.onTurnOffTetheredModeSelected == iterable) {
            return this;
        }
        return new ImmutablePlayFromSelectorItemElement(this.label, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onTurnOnTetheredModeSelected);
    }

    public final ImmutablePlayFromSelectorItemElement withOnTurnOnTetheredModeSelected(Method... methodArr) {
        return new ImmutablePlayFromSelectorItemElement(this.label, this.onTurnOffTetheredModeSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutablePlayFromSelectorItemElement withOnTurnOnTetheredModeSelected(Iterable<? extends Method> iterable) {
        if (this.onTurnOnTetheredModeSelected == iterable) {
            return this;
        }
        return new ImmutablePlayFromSelectorItemElement(this.label, this.onTurnOffTetheredModeSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlayFromSelectorItemElement) && equalTo(0, (ImmutablePlayFromSelectorItemElement) obj);
    }

    private boolean equalTo(int i, ImmutablePlayFromSelectorItemElement immutablePlayFromSelectorItemElement) {
        return this.label.equals(immutablePlayFromSelectorItemElement.label) && this.onTurnOffTetheredModeSelected.equals(immutablePlayFromSelectorItemElement.onTurnOffTetheredModeSelected) && this.onTurnOnTetheredModeSelected.equals(immutablePlayFromSelectorItemElement.onTurnOnTetheredModeSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.label.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onTurnOffTetheredModeSelected.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.onTurnOnTetheredModeSelected.hashCode();
    }

    public String toString() {
        return "PlayFromSelectorItemElement{label=" + this.label + ", onTurnOffTetheredModeSelected=" + this.onTurnOffTetheredModeSelected + ", onTurnOnTetheredModeSelected=" + this.onTurnOnTetheredModeSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PlayFromSelectorItemElement", generator = "Immutables")
    static final class Json extends PlayFromSelectorItemElement {

        @Nullable
        String label;

        @Nullable
        List<Method> onTurnOffTetheredModeSelected = Collections.emptyList();

        @Nullable
        List<Method> onTurnOnTetheredModeSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public void setLabel(String str) {
            this.label = str;
        }

        @JsonProperty("onTurnOffTetheredModeSelected")
        public void setOnTurnOffTetheredModeSelected(List<Method> list) {
            this.onTurnOffTetheredModeSelected = list;
        }

        @JsonProperty("onTurnOnTetheredModeSelected")
        public void setOnTurnOnTetheredModeSelected(List<Method> list) {
            this.onTurnOnTetheredModeSelected = list;
        }

        @Override // Watch.ListTemplateInterface.v1_0.PlayFromSelectorItemElement
        public String label() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.PlayFromSelectorItemElement
        public List<Method> onTurnOffTetheredModeSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.PlayFromSelectorItemElement
        public List<Method> onTurnOnTetheredModeSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePlayFromSelectorItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.label;
        if (str != null) {
            builder.label(str);
        }
        List<Method> list = json.onTurnOffTetheredModeSelected;
        if (list != null) {
            builder.addAllOnTurnOffTetheredModeSelected(list);
        }
        List<Method> list2 = json.onTurnOnTetheredModeSelected;
        if (list2 != null) {
            builder.addAllOnTurnOnTetheredModeSelected(list2);
        }
        return builder.build();
    }

    public static ImmutablePlayFromSelectorItemElement copyOf(PlayFromSelectorItemElement playFromSelectorItemElement) {
        if (playFromSelectorItemElement instanceof ImmutablePlayFromSelectorItemElement) {
            return (ImmutablePlayFromSelectorItemElement) playFromSelectorItemElement;
        }
        return builder().from(playFromSelectorItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PlayFromSelectorItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_LABEL = 1;
        private long initBits;

        @Nullable
        private String label;
        private List<Method> onTurnOffTetheredModeSelected;
        private List<Method> onTurnOnTetheredModeSelected;

        private Builder() {
            this.initBits = 1L;
            this.onTurnOffTetheredModeSelected = new ArrayList();
            this.onTurnOnTetheredModeSelected = new ArrayList();
        }

        public final Builder from(PlayFromSelectorItemElement playFromSelectorItemElement) {
            ImmutablePlayFromSelectorItemElement.requireNonNull(playFromSelectorItemElement, "instance");
            label(playFromSelectorItemElement.label());
            addAllOnTurnOffTetheredModeSelected(playFromSelectorItemElement.onTurnOffTetheredModeSelected());
            addAllOnTurnOnTetheredModeSelected(playFromSelectorItemElement.onTurnOnTetheredModeSelected());
            return this;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public final Builder label(String str) {
            this.label = (String) ImmutablePlayFromSelectorItemElement.requireNonNull(str, ContextMappingConstants.LABEL);
            this.initBits &= -2;
            return this;
        }

        public final Builder addOnTurnOffTetheredModeSelected(Method method) {
            this.onTurnOffTetheredModeSelected.add((Method) ImmutablePlayFromSelectorItemElement.requireNonNull(method, "onTurnOffTetheredModeSelected element"));
            return this;
        }

        public final Builder addOnTurnOffTetheredModeSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onTurnOffTetheredModeSelected.add((Method) ImmutablePlayFromSelectorItemElement.requireNonNull(method, "onTurnOffTetheredModeSelected element"));
            }
            return this;
        }

        @JsonProperty("onTurnOffTetheredModeSelected")
        public final Builder onTurnOffTetheredModeSelected(Iterable<? extends Method> iterable) {
            this.onTurnOffTetheredModeSelected.clear();
            return addAllOnTurnOffTetheredModeSelected(iterable);
        }

        public final Builder addAllOnTurnOffTetheredModeSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onTurnOffTetheredModeSelected.add((Method) ImmutablePlayFromSelectorItemElement.requireNonNull(it.next(), "onTurnOffTetheredModeSelected element"));
            }
            return this;
        }

        public final Builder addOnTurnOnTetheredModeSelected(Method method) {
            this.onTurnOnTetheredModeSelected.add((Method) ImmutablePlayFromSelectorItemElement.requireNonNull(method, "onTurnOnTetheredModeSelected element"));
            return this;
        }

        public final Builder addOnTurnOnTetheredModeSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onTurnOnTetheredModeSelected.add((Method) ImmutablePlayFromSelectorItemElement.requireNonNull(method, "onTurnOnTetheredModeSelected element"));
            }
            return this;
        }

        @JsonProperty("onTurnOnTetheredModeSelected")
        public final Builder onTurnOnTetheredModeSelected(Iterable<? extends Method> iterable) {
            this.onTurnOnTetheredModeSelected.clear();
            return addAllOnTurnOnTetheredModeSelected(iterable);
        }

        public final Builder addAllOnTurnOnTetheredModeSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onTurnOnTetheredModeSelected.add((Method) ImmutablePlayFromSelectorItemElement.requireNonNull(it.next(), "onTurnOnTetheredModeSelected element"));
            }
            return this;
        }

        public ImmutablePlayFromSelectorItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePlayFromSelectorItemElement(this.label, ImmutablePlayFromSelectorItemElement.createUnmodifiableList(true, this.onTurnOffTetheredModeSelected), ImmutablePlayFromSelectorItemElement.createUnmodifiableList(true, this.onTurnOnTetheredModeSelected));
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.LABEL);
            }
            return "Cannot build PlayFromSelectorItemElement, some of required attributes are not set " + arrayList;
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
