package Touch.WidgetsInterface.v1_0;

import com.BV.LinearGradient.LinearGradientManager;
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
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "ColorOptionsElement", generator = "Immutables")
public final class ImmutableColorOptionsElement extends ColorOptionsElement {
    private final List<Map<String, String>> colors;

    @Nullable
    private final String text;

    private ImmutableColorOptionsElement(List<Map<String, String>> list, @Nullable String str) {
        this.colors = list;
        this.text = str;
    }

    @Override // Touch.WidgetsInterface.v1_0.ColorOptionsElement
    @JsonProperty(LinearGradientManager.PROP_COLORS)
    public List<Map<String, String>> colors() {
        return this.colors;
    }

    @Override // Touch.WidgetsInterface.v1_0.ColorOptionsElement
    @JsonProperty("text")
    @Nullable
    public String text() {
        return this.text;
    }

    @SafeVarargs
    public final ImmutableColorOptionsElement withColors(Map<String, String>... mapArr) {
        return new ImmutableColorOptionsElement(createUnmodifiableList(false, createSafeList(Arrays.asList(mapArr), true, false)), this.text);
    }

    public final ImmutableColorOptionsElement withColors(Iterable<? extends Map<String, String>> iterable) {
        return this.colors == iterable ? this : new ImmutableColorOptionsElement(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.text);
    }

    public final ImmutableColorOptionsElement withText(@Nullable String str) {
        return equals(this.text, str) ? this : new ImmutableColorOptionsElement(this.colors, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableColorOptionsElement) && equalTo(0, (ImmutableColorOptionsElement) obj);
    }

    private boolean equalTo(int i, ImmutableColorOptionsElement immutableColorOptionsElement) {
        return this.colors.equals(immutableColorOptionsElement.colors) && equals(this.text, immutableColorOptionsElement.text);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.colors.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + hashCode(this.text);
    }

    public String toString() {
        return "ColorOptionsElement{colors=" + this.colors + ", text=" + this.text + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ColorOptionsElement", generator = "Immutables")
    static final class Json extends ColorOptionsElement {

        @Nullable
        List<Map<String, String>> colors = Collections.emptyList();

        @Nullable
        String text;

        Json() {
        }

        @JsonProperty(LinearGradientManager.PROP_COLORS)
        public void setColors(List<Map<String, String>> list) {
            this.colors = list;
        }

        @JsonProperty("text")
        public void setText(@Nullable String str) {
            this.text = str;
        }

        @Override // Touch.WidgetsInterface.v1_0.ColorOptionsElement
        public List<Map<String, String>> colors() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.ColorOptionsElement
        public String text() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableColorOptionsElement fromJson(Json json) {
        Builder builder = builder();
        List<Map<String, String>> list = json.colors;
        if (list != null) {
            builder.addAllColors(list);
        }
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        return builder.build();
    }

    public static ImmutableColorOptionsElement copyOf(ColorOptionsElement colorOptionsElement) {
        if (colorOptionsElement instanceof ImmutableColorOptionsElement) {
            return (ImmutableColorOptionsElement) colorOptionsElement;
        }
        return builder().from(colorOptionsElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ColorOptionsElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private List<Map<String, String>> colors;

        @Nullable
        private String text;

        private Builder() {
            this.colors = new ArrayList();
        }

        public final Builder from(ColorOptionsElement colorOptionsElement) {
            ImmutableColorOptionsElement.requireNonNull(colorOptionsElement, "instance");
            addAllColors(colorOptionsElement.colors());
            String strText = colorOptionsElement.text();
            if (strText != null) {
                text(strText);
            }
            return this;
        }

        public final Builder addColors(Map<String, String> map) {
            this.colors.add((Map) ImmutableColorOptionsElement.requireNonNull(map, "colors element"));
            return this;
        }

        @SafeVarargs
        public final Builder addColors(Map<String, String>... mapArr) {
            for (Map<String, String> map : mapArr) {
                this.colors.add((Map) ImmutableColorOptionsElement.requireNonNull(map, "colors element"));
            }
            return this;
        }

        @JsonProperty(LinearGradientManager.PROP_COLORS)
        public final Builder colors(Iterable<? extends Map<String, String>> iterable) {
            this.colors.clear();
            return addAllColors(iterable);
        }

        public final Builder addAllColors(Iterable<? extends Map<String, String>> iterable) {
            Iterator<? extends Map<String, String>> it = iterable.iterator();
            while (it.hasNext()) {
                this.colors.add((Map) ImmutableColorOptionsElement.requireNonNull(it.next(), "colors element"));
            }
            return this;
        }

        @JsonProperty("text")
        public final Builder text(@Nullable String str) {
            this.text = str;
            return this;
        }

        public ImmutableColorOptionsElement build() {
            return new ImmutableColorOptionsElement(ImmutableColorOptionsElement.createUnmodifiableList(true, this.colors), this.text);
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
