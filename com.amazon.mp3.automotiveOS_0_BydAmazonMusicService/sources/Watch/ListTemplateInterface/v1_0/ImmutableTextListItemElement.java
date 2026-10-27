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
@Generated(from = "TextListItemElement", generator = "Immutables")
public final class ImmutableTextListItemElement extends TextListItemElement {
    private final List<Method> onItemSelected;
    private final String text;

    private ImmutableTextListItemElement(String str, List<Method> list) {
        this.text = str;
        this.onItemSelected = list;
    }

    @Override // Watch.ListTemplateInterface.v1_0.TextListItemElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    @Override // Watch.ListTemplateInterface.v1_0.TextListItemElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableTextListItemElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutableTextListItemElement(str2, this.onItemSelected);
    }

    public final ImmutableTextListItemElement withOnItemSelected(Method... methodArr) {
        return new ImmutableTextListItemElement(this.text, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableTextListItemElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableTextListItemElement(this.text, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTextListItemElement) && equalTo(0, (ImmutableTextListItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableTextListItemElement immutableTextListItemElement) {
        return this.text.equals(immutableTextListItemElement.text) && this.onItemSelected.equals(immutableTextListItemElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.text.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "TextListItemElement{text=" + this.text + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "TextListItemElement", generator = "Immutables")
    static final class Json extends TextListItemElement {

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        @Nullable
        String text;

        Json() {
        }

        @JsonProperty("text")
        public void setText(String str) {
            this.text = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
        }

        @Override // Watch.ListTemplateInterface.v1_0.TextListItemElement
        public String text() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.TextListItemElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTextListItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        List<Method> list = json.onItemSelected;
        if (list != null) {
            builder.addAllOnItemSelected(list);
        }
        return builder.build();
    }

    public static ImmutableTextListItemElement copyOf(TextListItemElement textListItemElement) {
        if (textListItemElement instanceof ImmutableTextListItemElement) {
            return (ImmutableTextListItemElement) textListItemElement;
        }
        return builder().from(textListItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "TextListItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TEXT = 1;
        private long initBits;
        private List<Method> onItemSelected;

        @Nullable
        private String text;

        private Builder() {
            this.initBits = 1L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(TextListItemElement textListItemElement) {
            ImmutableTextListItemElement.requireNonNull(textListItemElement, "instance");
            text(textListItemElement.text());
            addAllOnItemSelected(textListItemElement.onItemSelected());
            return this;
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutableTextListItemElement.requireNonNull(str, "text");
            this.initBits &= -2;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableTextListItemElement.requireNonNull(method, "onItemSelected element"));
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableTextListItemElement.requireNonNull(method, "onItemSelected element"));
            }
            return this;
        }

        @JsonProperty("onItemSelected")
        public final Builder onItemSelected(Iterable<? extends Method> iterable) {
            this.onItemSelected.clear();
            return addAllOnItemSelected(iterable);
        }

        public final Builder addAllOnItemSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onItemSelected.add((Method) ImmutableTextListItemElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            return this;
        }

        public ImmutableTextListItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableTextListItemElement(this.text, ImmutableTextListItemElement.createUnmodifiableList(true, this.onItemSelected));
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("text");
            }
            return "Cannot build TextListItemElement, some of required attributes are not set " + arrayList;
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
