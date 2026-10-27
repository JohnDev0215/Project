package Watch.AlertTemplateInterface.v1_0;

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
@Generated(from = "AlertButtonElement", generator = "Immutables")
public final class ImmutableAlertButtonElement extends AlertButtonElement {
    private final List<Method> onButtonSelected;
    private final String text;

    private ImmutableAlertButtonElement(String str, List<Method> list) {
        this.text = str;
        this.onButtonSelected = list;
    }

    @Override // Watch.AlertTemplateInterface.v1_0.AlertButtonElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    @Override // Watch.AlertTemplateInterface.v1_0.AlertButtonElement
    @JsonProperty("onButtonSelected")
    public List<Method> onButtonSelected() {
        return this.onButtonSelected;
    }

    public final ImmutableAlertButtonElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutableAlertButtonElement(str2, this.onButtonSelected);
    }

    public final ImmutableAlertButtonElement withOnButtonSelected(Method... methodArr) {
        return new ImmutableAlertButtonElement(this.text, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableAlertButtonElement withOnButtonSelected(Iterable<? extends Method> iterable) {
        if (this.onButtonSelected == iterable) {
            return this;
        }
        return new ImmutableAlertButtonElement(this.text, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableAlertButtonElement) && equalTo(0, (ImmutableAlertButtonElement) obj);
    }

    private boolean equalTo(int i, ImmutableAlertButtonElement immutableAlertButtonElement) {
        return this.text.equals(immutableAlertButtonElement.text) && this.onButtonSelected.equals(immutableAlertButtonElement.onButtonSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.text.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.onButtonSelected.hashCode();
    }

    public String toString() {
        return "AlertButtonElement{text=" + this.text + ", onButtonSelected=" + this.onButtonSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "AlertButtonElement", generator = "Immutables")
    static final class Json extends AlertButtonElement {

        @Nullable
        List<Method> onButtonSelected = Collections.emptyList();

        @Nullable
        String text;

        Json() {
        }

        @JsonProperty("text")
        public void setText(String str) {
            this.text = str;
        }

        @JsonProperty("onButtonSelected")
        public void setOnButtonSelected(List<Method> list) {
            this.onButtonSelected = list;
        }

        @Override // Watch.AlertTemplateInterface.v1_0.AlertButtonElement
        public String text() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.AlertTemplateInterface.v1_0.AlertButtonElement
        public List<Method> onButtonSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableAlertButtonElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        List<Method> list = json.onButtonSelected;
        if (list != null) {
            builder.addAllOnButtonSelected(list);
        }
        return builder.build();
    }

    public static ImmutableAlertButtonElement copyOf(AlertButtonElement alertButtonElement) {
        if (alertButtonElement instanceof ImmutableAlertButtonElement) {
            return (ImmutableAlertButtonElement) alertButtonElement;
        }
        return builder().from(alertButtonElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "AlertButtonElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TEXT = 1;
        private long initBits;
        private List<Method> onButtonSelected;

        @Nullable
        private String text;

        private Builder() {
            this.initBits = 1L;
            this.onButtonSelected = new ArrayList();
        }

        public final Builder from(AlertButtonElement alertButtonElement) {
            ImmutableAlertButtonElement.requireNonNull(alertButtonElement, "instance");
            text(alertButtonElement.text());
            addAllOnButtonSelected(alertButtonElement.onButtonSelected());
            return this;
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutableAlertButtonElement.requireNonNull(str, "text");
            this.initBits &= -2;
            return this;
        }

        public final Builder addOnButtonSelected(Method method) {
            this.onButtonSelected.add((Method) ImmutableAlertButtonElement.requireNonNull(method, "onButtonSelected element"));
            return this;
        }

        public final Builder addOnButtonSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onButtonSelected.add((Method) ImmutableAlertButtonElement.requireNonNull(method, "onButtonSelected element"));
            }
            return this;
        }

        @JsonProperty("onButtonSelected")
        public final Builder onButtonSelected(Iterable<? extends Method> iterable) {
            this.onButtonSelected.clear();
            return addAllOnButtonSelected(iterable);
        }

        public final Builder addAllOnButtonSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onButtonSelected.add((Method) ImmutableAlertButtonElement.requireNonNull(it.next(), "onButtonSelected element"));
            }
            return this;
        }

        public ImmutableAlertButtonElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableAlertButtonElement(this.text, ImmutableAlertButtonElement.createUnmodifiableList(true, this.onButtonSelected));
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("text");
            }
            return "Cannot build AlertButtonElement, some of required attributes are not set " + arrayList;
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
