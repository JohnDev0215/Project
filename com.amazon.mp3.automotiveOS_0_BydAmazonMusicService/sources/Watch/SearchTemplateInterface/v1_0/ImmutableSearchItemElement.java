package Watch.SearchTemplateInterface.v1_0;

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
@Generated(from = "SearchItemElement", generator = "Immutables")
public final class ImmutableSearchItemElement extends SearchItemElement {
    private final List<Method> onItemSelected;

    @Nullable
    private final String text;

    private ImmutableSearchItemElement(@Nullable String str, List<Method> list) {
        this.text = str;
        this.onItemSelected = list;
    }

    @Override // Watch.SearchTemplateInterface.v1_0.SearchItemElement
    @JsonProperty("text")
    @Nullable
    public String text() {
        return this.text;
    }

    @Override // Watch.SearchTemplateInterface.v1_0.SearchItemElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableSearchItemElement withText(@Nullable String str) {
        return equals(this.text, str) ? this : new ImmutableSearchItemElement(str, this.onItemSelected);
    }

    public final ImmutableSearchItemElement withOnItemSelected(Method... methodArr) {
        return new ImmutableSearchItemElement(this.text, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSearchItemElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableSearchItemElement(this.text, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSearchItemElement) && equalTo(0, (ImmutableSearchItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableSearchItemElement immutableSearchItemElement) {
        return equals(this.text, immutableSearchItemElement.text) && this.onItemSelected.equals(immutableSearchItemElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.text) + 5381;
        return iHashCode + (iHashCode << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "SearchItemElement{text=" + this.text + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SearchItemElement", generator = "Immutables")
    static final class Json extends SearchItemElement {

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        @Nullable
        String text;

        Json() {
        }

        @JsonProperty("text")
        public void setText(@Nullable String str) {
            this.text = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
        }

        @Override // Watch.SearchTemplateInterface.v1_0.SearchItemElement
        public String text() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.SearchTemplateInterface.v1_0.SearchItemElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSearchItemElement fromJson(Json json) {
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

    public static ImmutableSearchItemElement copyOf(SearchItemElement searchItemElement) {
        if (searchItemElement instanceof ImmutableSearchItemElement) {
            return (ImmutableSearchItemElement) searchItemElement;
        }
        return builder().from(searchItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SearchItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private List<Method> onItemSelected;

        @Nullable
        private String text;

        private Builder() {
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(SearchItemElement searchItemElement) {
            ImmutableSearchItemElement.requireNonNull(searchItemElement, "instance");
            String strText = searchItemElement.text();
            if (strText != null) {
                text(strText);
            }
            addAllOnItemSelected(searchItemElement.onItemSelected());
            return this;
        }

        @JsonProperty("text")
        public final Builder text(@Nullable String str) {
            this.text = str;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableSearchItemElement.requireNonNull(method, "onItemSelected element"));
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableSearchItemElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableSearchItemElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            return this;
        }

        public ImmutableSearchItemElement build() {
            return new ImmutableSearchItemElement(this.text, ImmutableSearchItemElement.createUnmodifiableList(true, this.onItemSelected));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
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
